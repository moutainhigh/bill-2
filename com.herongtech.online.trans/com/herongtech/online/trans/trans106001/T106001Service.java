/********************************************
 * 文件名称: T106001Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 查询可贴现的票据
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-07-16
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans106001;

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
import com.herongtech.online.trans.trans102001.Var102001;
import com.herongtech.online.trans.trans106101.Var106101InfoBean;
import com.herongtech.online.trans.trans106101.Var106101Result;
import com.herongtech.online.trans.trans106102.Var106102InfoBean;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 查询可贴现的票据
 */

public class T106001Service extends OnlineBaseService{


	/**
	 * 入口方法
	 * @param context
	 */
	@Override
	protected void action(Context context) throws Exception {
		Var106001 transVar = new Var106001();
		
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
	 * @param var106001
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var106001 var106001) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);

		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var106001.class);
        Var106001 temp=(Var106001)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(temp.getBillType())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据类型未上送");
		}
		
		org.springframework.beans.BeanUtils.copyProperties(temp, var106001);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param var106001
	 * @throws BizAppException
	 */
	protected void ToLocal(Context context, Var106001 var106001) throws BizAppException{

		RcBaseSearchBean rcSb = new RcBaseSearchBean();
		rcSb.setBillType(var106001.getBillType());
		rcSb.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		rcSb.setHoldAcctNo(var106001.getCustAccount());
		
    	IDB session = DBFactory.getDB();
    	String sql = RcServiceFactory.getRcDiscService().queryScatteredBill(rcSb);

    	List<RgctBill> result = null;
    	List<Var106001InfoBean> resultData = new ArrayList<Var106001InfoBean>();
    	List<Object> param = new ArrayList<Object>();
		try {
			result = session.getBeanListByListForPage(sql, RgctBill.class, Integer.valueOf(var106001.getCurrentPage()), Integer.valueOf(var106001.getPageSize()), param);
			for (int i = 0; i < result.size(); i++) {
				Var106001InfoBean var106001InfoBean = new Var106001InfoBean();
				org.springframework.beans.BeanUtils.copyProperties(result.get(i).getInfo(), var106001InfoBean);
				org.springframework.beans.BeanUtils.copyProperties(result.get(i).getHist(), var106001InfoBean);
				var106001InfoBean.setAcceptDt(result.get(i).getInfo().getAcceptorDate());
				var106001InfoBean.setRemitterBank(result.get(i).getInfo().getRemitterBankName());
				var106001InfoBean.setPayee(result.get(i).getInfo().getPayeeName());
				var106001InfoBean.setPayeeBank(result.get(i).getInfo().getPayeeBankName());
				var106001InfoBean.setAcceptorBank(result.get(i).getInfo().getAcceptorBankName());
				var106001InfoBean.setBanEndorsementMark(result.get(i).getHist().getForbidFlag());
				resultData.add(var106001InfoBean);
			}
				
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	if(result.size() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}
    	
    	var106001.setResult(resultData);
    	var106001.setTotalRows(String.valueOf(result.size()));
    	
    	try {
    		var106001.setRetNum(
					String.valueOf(session.account("select count(*) from (" + sql + ")")));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询记录数失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询记录数失败");
		}
	}
	
	/**
     * 应答包处理
     * @param context
     * @param var106001
     * @throws BizAppException
     */
	protected void PackAnswer(Context context, Var106001 var106001) throws BizAppException{
		
		TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
		Var106001ProResult proResult = new Var106001ProResult();
		proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        proResult.setCurrentPage(var106001.getCurrentPage());
        proResult.setTotalRows(var106001.getTotalRows());
        proResult.setRetNum(var106001.getRetNum());
        List<Var106001InfoBean> infoList = var106001.getResult();
        Var106001Result result = new Var106001Result();
        result.setVar106001ProResult(proResult);
        result.setResult(infoList);
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var106001Result.class);
        clazzMap.put("info", Var106001InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
		
	}



	@Override
	public String getTransName() {
		return "查询可贴现的票据";
	}

	@Override
	public String getTransVersion() {
		return "2.0.0.1";
	}

	@Override
	public String getServiceId() {
		return "106001";
	}
	
}
