/********************************************
 * 文件名称: T101003Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 可执行出票登记的票据查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-08-9
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans101003;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans101003.Var101003Bean;
import com.herongtech.online.trans.trans101003.Var101003Result;

import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 *  可撤消提示收票的票据查询
 */
public class T101003Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var101003 transVar = new Var101003();
		
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
	 * @param Var101003
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var101003 Var101003) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var101003.class);
        Var101003 temp=(Var101003)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, Var101003);
		
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var101003
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var101003 Var101003) throws BizAppException{
    	RcBaseSearchBean rcbean = new RcBaseSearchBean();
    	rcbean.setBillClass("2");
    	rcbean.setHoldAcctNo(Var101003.getCustAccount());
    	try {
    	
    	IDB session = DBFactory.getDB();
    	int startIndex = Var101003.getCurrentPage();//记录开始值
    	String sql = RcServiceFactory.getRcBillNotifyService().queryBillWaitSign(rcbean);
    	//临时测试sql="select billinfo.*,billhist.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.hist_id and billinfo.bill_class = '1'  and billhist.hold_acct_no ='622688213855776' and ( billhist.cur_status='F_11' )  and billinfo.del_flag='0'";

    	List<RgctBill> rgctbilllist = null;
    	List<Object> rgctlist = new ArrayList<Object>();
    	List<Var101003Bean> var101003beanlist = new ArrayList<Var101003Bean>();
    	rgctbilllist = session.getBeanListByListForPage(sql, RgctBill.class, startIndex, Var101003.getPageSize(), rgctlist);
			
    	for (int i = 0; i < rgctbilllist.size(); i++) {
    		session.beginTransaction();
 	    	Var101003Bean bean = new Var101003Bean();
 	    	org.springframework.beans.BeanUtils.copyProperties(rgctbilllist.get(i).getInfo(), bean);
 	    	org.springframework.beans.BeanUtils.copyProperties(rgctbilllist.get(i).getHist(), bean);
 	    	bean.setAcceptDt(rgctbilllist.get(i).getInfo().getAcceptorDate());
 	    	bean.setPayee(rgctbilllist.get(i).getInfo().getPayeeName());
 	    	bean.setPayeeBank(rgctbilllist.get(i).getInfo().getPayeeBankName());
 	    	bean.setAcceptorBank(rgctbilllist.get(i).getInfo().getAcceptorBankName());
 	    	bean.setBanEndorsementMark(rgctbilllist.get(i).getInfo().getInfoForbidFlag());
 	    	bean.setRemitterBank(rgctbilllist.get(i).getInfo().getRemitterBankName()); 
 	    	bean.setRgctId(rgctbilllist.get(i).getHist().getRgctId());
 	    	bean.setApplicantName(rgctbilllist.get(i).getHist().getFromName());
 	    	bean.setApplicantAcctNo(rgctbilllist.get(i).getHist().getFromAcctNo());
 	    	bean.setApplicantOrgCode(rgctbilllist.get(i).getHist().getFromOrgcode());
 	    	bean.setApplicantBankNo(rgctbilllist.get(i).getHist().getFromBankNo());
 	    	bean.setApplyString(rgctbilllist.get(i).getHist().getEndorseDt());
 	    	bean.setReceiverAcctNo(rgctbilllist.get(i).getHist().getToAcctNo());
 	    	bean.setReceiverName(rgctbilllist.get(i).getHist().getToName());
 	    	bean.setReceiverBankNo(rgctbilllist.get(i).getHist().getToBankNo());
 	    	var101003beanlist.add(bean);
 	    	session.endTransaction();
 		}	
//    	if(resultData.getRowCount() <= 0){
//    		throw new BizAppException(IErrorNo.ERR_DBERR, "");
//    	}
    	Var101003.setBeanlist(var101003beanlist);
    	Var101003.setRetNum(var101003beanlist.size());
			Var101003.setTotRecNum(
					session.account("select count(*) from ("+sql+")"));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询记录数失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询记录数失败");
		}
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var101003
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var101003 Var101003) throws BizAppException{
//    	IData request = (IData)ContextUtil.getRequestData(context);
//		AnswerData response = new AnswerData(request);
//		
//		IData resultData = Var101003.getResultData();
//		resultData.beforeFirst();
//		for(int i = 1; i <= resultData.getRowCount(); i++){
//			resultData.next();
//			if(i == 1){
//				response.setValue(i, IFieldName.totalRows, Var101003.getTotRecNum());
//				response.setValue(i, IFieldName.retNum, resultData.getRowCount());
//				response.setValue(i, IFieldName.currentPage, request.getInt(IFieldName.currentPage));
//			}			
//			/*
//			response.setValue(i, ITag.BankAcc, resultData.getString("bank_acc"));
//			response.setValue(i, ITag.OpenBranch, resultData.getString("open_branch"));*/
//			
//			response.setValue(i, IFieldName.rgctId            , resultData.getString("id"));            
//			response.setValue(i, IFieldName.billNo            , resultData.getString("bill_no"));
//			response.setValue(i, IFieldName.conferNo          , resultData.getString("confer_no"));      
//			response.setValue(i, IFieldName.invoiceNo         , resultData.getString("invoice_no"));
//			response.setValue(i, IFieldName.billType          , resultData.getString("bill_type"));      
//			response.setValue(i, IFieldName.billClass         , resultData.getString("bill_class"));
//			response.setValue(i, IFieldName.issueDt           , resultData.getString("issue_dt"));      
//			response.setValue(i, IFieldName.dueDt             , resultData.getString("due_dt"));
//			response.setValue(i, IFieldName.acceptDt          , resultData.getString("accept_dt"));      
//			response.setValue(i, IFieldName.billMoney         , resultData.getDouble("bill_money"));
//			response.setValue(i, IFieldName.remitter          , resultData.getString("remitter"));      
//			response.setValue(i, IFieldName.remitterAcct      , resultData.getString("remitter_acct"));
//			response.setValue(i, IFieldName.remitterBank      , resultData.getString("remitter_bank"));      
//			response.setValue(i, IFieldName.remitterBankNo    , resultData.getString("remitter_bankNo"));
//			response.setValue(i, IFieldName.payee             , resultData.getString("payee"));      
//			response.setValue(i, IFieldName.payeeAcct         , resultData.getString("payee_acct"));
//			response.setValue(i, IFieldName.payeeBank         , resultData.getString("payee_bank"));      
//			response.setValue(i, IFieldName.payeeBankNo       , resultData.getString("payee_bankNo"));
//			response.setValue(i, IFieldName.acceptor          , resultData.getString("acceptor"));      
//			response.setValue(i, IFieldName.acceptorAcct      , resultData.getString("acceptor_acct"));
//			response.setValue(i, IFieldName.acceptorBank      , resultData.getString("acceptor_bank"));      
//			response.setValue(i, IFieldName.acceptorBankNo    , resultData.getString("acceptor_bankNo"));
//			response.setValue(i, IFieldName.banEndorsementMark, resultData.getString("forbid_flag"));           
//			response.setValue(i, IFieldName.applicantName, resultData.getString("from_name"));      
//			response.setValue(i, IFieldName.applicantAcctNo, resultData.getString("from_acct_no"));      
//			response.setValue(i, IFieldName.applicantOrgCode, resultData.getString("from_orgcode"));      
//			response.setValue(i, IFieldName.applicantBankNo, resultData.getString("from_bank_no"));      
//			response.setValue(i, IFieldName.applyDate, resultData.getString("endorse_dt"));      
//			response.setValue(i, IFieldName.receiverAcctNo, resultData.getString("to_acct_no"));      
//			response.setValue(i, IFieldName.receiverName, resultData.getString("to_name"));      
//			response.setValue(i, IFieldName.receiverBankNo, resultData.getString("to_bank_no"));      
//			
//		}
//		
//		//ContextUtil.setRequestData(context, response.getAnswerData());
//		ContextUtil.setResponseData(context, response.getAnswerData());
    	 TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	 List<Var101003Bean> infoList=Var101003.getBeanlist();

    	 
    	 Var101003Result proResult=new Var101003Result();
    	 proResult.setType("S");
    	 proResult.setExSerial(trans.getExSerial());
    	 proResult.setFunctionId(trans.getFunctionId());
    	 proResult.setCurrentPage(Var101003.getCurrentPage());//当前页
    	 proResult.setTotalRows(Var101003.getTotRecNum());//总条数
    	 proResult.setRetNum(Var101003.getRetNum());//返回条数
         proResult.setBeanlist(infoList);
               
         Map<String, Class> clazzMap = new HashMap<String, Class>();
         clazzMap.put("Document", Var101003Result.class);
         clazzMap.put("info", Var101003Bean.class);
        
         String resp=XmlBeanUtil.bean2xml(clazzMap, proResult);     
         ContextUtil.setResponseData(context, resp);
    	
    	
    }
	
	public String getTransName() {		
		return "可撤消提示收票的票据查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "101003";
	}
}
