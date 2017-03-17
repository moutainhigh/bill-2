/********************************************
 * 文件名称: T103002Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 可执行背书撤回操作的票据查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 2016-08-08
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans103002;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 可执行背书撤回操作的票据查询
 */
public class T103002Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var103002 transVar = new Var103002();
		
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
	 * @param Var101002
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var103002 var103002) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var103002.class);
        Var103002 temp=(Var103002)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		
		BeanUtils.copyProperties(temp, var103002);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var101002
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var103002 var103002) throws BizAppException{
    	RcBaseSearchBean searchBean = new RcBaseSearchBean();
    	searchBean.setHoldAcctNo(var103002.getCustAccount());
    	searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
    	List<Var103002InfoBean> result =new ArrayList<Var103002InfoBean>();
    	IDB session = DBFactory.getDB();
    	String sql = RcServiceFactory.getRcEndorseService().queryEndorseTo(searchBean);
        List<Object> list =new ArrayList<Object>();
       	List<RgctBill> rgctBill=null;
		try {
			
			rgctBill = session.getBeanListByListForPage(sql, 
					RgctBill.class,
					Integer.valueOf(var103002.getCurrentPage()),
					Integer.valueOf(var103002.getPageSize()),
					list);
			int count=session.account("select count(*) from ("+ sql +")");
			var103002.setTotRecNum(Integer.toString(count));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	/*if(resultData.getRowCount() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}
    	Var103002.setResultData(resultData);*/
		
		if(rgctBill== null){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}else{
    		for (RgctBill rgctHistdata:rgctBill) {
    			Var103002InfoBean bean=new Var103002InfoBean();
    			//BeanUtils.copyProperties(bean, rgctHistdata);
    			bean.setAcceptDt(rgctHistdata.getInfo().getAcceptorDate());
    			bean.setAcceptor(rgctHistdata.getInfo().getAcceptor());
    			bean.setAcceptorAcct(rgctHistdata.getInfo().getAcceptorAcct());
    			bean.setAcceptorBank(rgctHistdata.getInfo().getAcceptorBankName());
    			bean.setAcceptorBankNo(rgctHistdata.getInfo().getAcceptorBankNo());
    			bean.setBanEndorsementMark(rgctHistdata.getHist().getForbidFlag());
    			bean.setBillClass(rgctHistdata.getInfo().getBillClass());
    			bean.setBillNo(rgctHistdata.getInfo().getBillNo());
    			bean.setBillType(rgctHistdata.getInfo().getBillType());
    			bean.setConferNo(rgctHistdata.getInfo().getConferNo());
    			bean.setDueDt(rgctHistdata.getInfo().getDueDt());
    			bean.setInvoiceNo(rgctHistdata.getInfo().getInvoiceNo());
    			bean.setIssueDt(rgctHistdata.getInfo().getIssueDt());
    			bean.setPayee(rgctHistdata.getInfo().getPayeeName());
    			bean.setPayeeAcct(rgctHistdata.getInfo().getPayeeAcct());
    			bean.setPayeeBank(rgctHistdata.getInfo().getPayeeBankName());
    			bean.setPayeeBankNo(rgctHistdata.getInfo().getPayeeBankNo());
    			bean.setRemitter(rgctHistdata.getInfo().getRemitter());
    			bean.setRemitterAcct(rgctHistdata.getInfo().getRemitterAcct());
    			bean.setRemitterBank(rgctHistdata.getInfo().getRemitterBankName());
    			bean.setRemitterBankNo(rgctHistdata.getInfo().getRemitterBankNo());
    			bean.setRgctId(rgctHistdata.getHist().getRgctId());
    			bean.setApplicantAcctNo(rgctHistdata.getHist().getFromAcctNo());
    			bean.setApplicantBankNo(rgctHistdata.getHist().getFromBankNo());
    			bean.setApplicantName(rgctHistdata.getHist().getFromName());
    			bean.setApplicantOrgCode(rgctHistdata.getHist().getFromOrgcode());
    			bean.setApplyDate(rgctHistdata.getHist().getEndorseDt());
    			bean.setBillMoney(rgctHistdata.getInfo().getBillMoney()+"");
    			bean.setReceiverAcctNo(rgctHistdata.getHist().getToAcctNo());
    			bean.setReceiverBankNo(rgctHistdata.getHist().getToBankNo());
    			bean.setReceiverName(rgctHistdata.getHist().getToName());

    			
    			result.add(bean);
    		}
    	}
    	
    	
    	var103002.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var103002
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var103002 var103002) throws BizAppException{
    	
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var103002InfoBean> infoList=var103002.getResult();
        Var103002Result result=new Var103002Result();
        result.setCurrentPage(var103002.getCurrentPage());
        result.setPageSize(var103002.getPageSize());
        result.setTotRecNum(var103002.getTotRecNum());
        result.setProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var103002Result.class);
        clazzMap.put("info", Var103002InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "可执行背书撤回操作的票据查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "103002";
	}
}
