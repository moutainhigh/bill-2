/********************************************
 * 文件名称: T106002Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 查询可贴现撤回票据
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-07-16
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans106002;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans106001.Var106001;
import com.herongtech.online.trans.trans106001.Var106001InfoBean;
import com.herongtech.online.trans.trans106001.Var106001ProResult;
import com.herongtech.online.trans.trans106001.Var106001Result;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 查询可贴现撤回票据
 */

public class T106002Service extends OnlineBaseService{

	/**
	 * 入口方法
	 * @param context
	 */
	@Override
	protected void action(Context context) throws Exception {
		Var106002 transVar = new Var106002();
		
		// 交易预处理
		transRequest(context);

		// 常规校验
		CheckData(context, transVar);

		// 查询处理
		ToLocal(context, transVar);

		// 应当包处理
		PackAnswer(context, transVar);
		
	}

	/**
	 * 交易预处理
	 * @param context
	 * @throws Exception
	 */
	protected void transRequest(Context context) throws BizAppException {
		// 父类交易请求预处理
		super.transRequest(context);
		
		//银行接口化处理
		//BankInterfaceFactory.getBankInterface().bankInterface(context);
	}
	/**
	 * 常规校验
	 * @param context
	 * @param var106002
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var106002 var106002) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);

		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var106002.class);
        Var106002 temp=(Var106002)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(temp.getBillType())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据类型未上送");
		}
		
		org.springframework.beans.BeanUtils.copyProperties(temp, var106002);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param var106002
	 * @throws BizAppException
	 */
	protected void ToLocal(Context context, Var106002 var106002) throws BizAppException{
		RcBaseSearchBean rcSb = new RcBaseSearchBean();
		rcSb.setBillType(var106002.getBillType());
		rcSb.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		rcSb.setHoldAcctNo(var106002.getCustAccount());
    	
    	IDB session = DBFactory.getDB();
		
    	String sql =RcServiceFactory.getRcDiscService().queryReversibleBill(rcSb);
    	
    	List<RgctBill> result = null;
    	List<Object> param = new ArrayList<Object>();
    	List<Var106002InfoBean> resultData = new ArrayList<Var106002InfoBean>();
		try {
			result = session.getBeanListByListForPage(sql, RgctBill.class, Integer.valueOf(var106002.getCurrentPage()), Integer.valueOf(var106002.getPageSize()), param);
			for (int i = 0; i < result.size(); i++) {
				Var106002InfoBean var106002InfoBean = new Var106002InfoBean();
				org.springframework.beans.BeanUtils.copyProperties(result.get(i).getInfo(), var106002InfoBean);
				org.springframework.beans.BeanUtils.copyProperties(result.get(i).getHist(), var106002InfoBean);
				var106002InfoBean.setDiscType(RcConstants.DISCTYPE_ZERO);
				var106002InfoBean.setDiscRate(result.get(i).getHist().getInterestRate());
				var106002InfoBean.setDiscMoney(result.get(i).getHist().getDealMoney());
				var106002InfoBean.setSettlementMark(result.get(i).getHist().getIsOnline());
				var106002InfoBean.setRpdDiscAmt(result.get(i).getHist().getBackAmount());
				var106002InfoBean.setRpdDiscRate(result.get(i).getHist().getBackRate());
				var106002InfoBean.setRpdOpenDt(result.get(i).getHist().getBackOpenDt());
				var106002InfoBean.setRpdDueDt(result.get(i).getHist().getBackEndDt());
				var106002InfoBean.setRemitterBank(result.get(i).getInfo().getRemitterBankName());
				var106002InfoBean.setPayee(result.get(i).getInfo().getPayeeName());
				var106002InfoBean.setPayeeBank(result.get(i).getInfo().getPayeeBankName());
				var106002InfoBean.setAcceptorBank(result.get(i).getInfo().getAcceptorBankName());
				var106002InfoBean.setBanEndorsementMark(result.get(i).getHist().getForbidFlag());
				var106002InfoBean.setApplicantName(result.get(i).getHist().getFromName());
				var106002InfoBean.setApplicantAcctNo(result.get(i).getHist().getFromAcctNo());
				var106002InfoBean.setApplicantOrgCode(result.get(i).getHist().getFromOrgcode());
				var106002InfoBean.setApplicantBankNo(result.get(i).getHist().getFromBankNo());
				var106002InfoBean.setApplyDate(result.get(i).getHist().getOperDate());
				var106002InfoBean.setReceiverAcct(result.get(i).getHist().getToAcctNo());
				var106002InfoBean.setReceiverName(result.get(i).getHist().getToName());
				var106002InfoBean.setReceiverBankNo(result.get(i).getHist().getToBankNo());
				resultData.add(var106002InfoBean);
			}
				
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	if(result.size() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}
    	
    	var106002.setResult(resultData);
    	var106002.setTotalRows(String.valueOf(result.size()));
    	
    	try {
    		var106002.setRetNum(
					String.valueOf(session.account("select count(*) from (" + sql + ")")));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询记录数失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询记录数失败");
		}
	}
	
	/**
     * 应答包处理
     * @param context
     * @param var106002
     * @throws BizAppException
     */
	protected void PackAnswer(Context context, Var106002 var106002) throws BizAppException{
		TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
		Var106002ProResult proResult = new Var106002ProResult();
		proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        proResult.setCurrentPage(var106002.getCurrentPage());
        proResult.setTotalRows(var106002.getTotalRows());
        proResult.setRetNum(var106002.getRetNum());
        List<Var106002InfoBean> infoList = var106002.getResult();
        Var106002Result result = new Var106002Result();
        result.setVar106002ProResult(proResult);
        result.setResult(infoList);
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var106002Result.class);
        clazzMap.put("info", Var106002InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
		
	}



	@Override
	public String getTransName() {
		return "查询可贴现撤回票据";
	}

	@Override
	public String getTransVersion() {
		return "2.0.0.1";
	}

	@Override
	public String getServiceId() {
		return "106002";
	}
}
