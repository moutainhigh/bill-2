package com.herongtech.online.trans.trans105001;

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
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 可执行出票登记的票据查询
 */
public class T105001Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var105001 transVar = new Var105001();
		
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
	 * @param transVar
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var105001 var105001) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
//		BusiDate busiDate = (BusiDate) ContextUtil.getContextAttribute(context, IConstants.SYSBUSININFO);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var105001.class);
        Var105001 temp=(Var105001)XmlBeanUtil.xml2Bean(clazzMap, request);
        
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		BeanUtils.copyProperties(temp, var105001);
		
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param transVar
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var105001 Var105001) throws BizAppException{
    	//获取记录开始值
    	RcBaseSearchBean searchBean = new RcBaseSearchBean();
    	searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
    	searchBean.setHoldAcctNo(Var105001.getCustAccount());
    	IDB session = DBFactory.getDB();
    	String sql = RcServiceFactory.getRcPresentationService().queryPrecollectBillClient(searchBean);
    	List<RgctBill> rgctBillList=null;
		try {
			List<Object> list = new ArrayList<Object>();
			rgctBillList = session.getBeanListByListForPage(
					sql, 
					RgctBill.class, 
					Integer.parseInt(Var105001.getCurrentPage()), 
					Integer.parseInt(Var105001.getPageSize()), 
					list);
			Var105001.setTotalRows(Integer.toString(session.account("select count(*) from ("+sql+")")));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
//    	if(resultData.getRowCount() <= 0){
//    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
//    	}
//    	
//    	Var105001.setResultData(resultData);
    	
		if(rgctBillList.size() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}
    	
		Var105001.setBillList(this.getVar105001InfoBeanListByRgctBillList(rgctBillList));
    	
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var105001
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var105001 Var105001) throws BizAppException{
   /* 	IData request = (IData)ContextUtil.getRequestData(context);
		AnswerData response = new AnswerData(request);
		
		IData resultData = Var105001.getResultData();
		resultData.beforeFirst();
		for(int i = 1; i <= resultData.getRowCount(); i++){
			resultData.next();
			if(i == 1){
				response.setValue(i, IFieldName.totalRows, Var105001.getTotRecNum());
				response.setValue(i, IFieldName.retNum, resultData.getRowCount());
				response.setValue(i, IFieldName.currentPage, request.getInt(IFieldName.currentPage));
			}			
			
			response.setValue(i, ITag.BankAcc, resultData.getString("bank_acc"));
			response.setValue(i, ITag.OpenBranch, resultData.getString("open_branch"));
			
			response.setValue(i, IFieldName.rgctId1            , resultData.getString("id"));            
			response.setValue(i, IFieldName.billNo 1           , resultData.getString("bill_no"));
			response.setValue(i, IFieldName.conferNo 1         , resultData.getString("confer_no"));      
			response.setValue(i, IFieldName.invoiceNo1         , resultData.getString("invoice_no"));
			response.setValue(i, IFieldName.billType 1         , resultData.getString("bill_type"));      
			response.setValue(i, IFieldName.billClass 1        , resultData.getString("bill_class"));
			response.setValue(i, IFieldName.issueDt  1         , resultData.getString("issue_dt"));      
			response.setValue(i, IFieldName.dueDt    1         , resultData.getString("due_dt"));
			response.setValue(i, IFieldName.acceptDt 1         , resultData.getString("accept_dt"));      
			response.setValue(i, IFieldName.billMoney  1       , resultData.getDouble("bill_money"));
			response.setValue(i, IFieldName.remitter   1       , resultData.getString("remitter"));      
			response.setValue(i, IFieldName.remitterAcct  1    , resultData.getString("remitter_acct"));
			response.setValue(i, IFieldName.remitterBank   1   , resultData.getString("remitter_bank"));      
			response.setValue(i, IFieldName.remitterBankNo  1  , resultData.getString("remitter_bank_no"));
			response.setValue(i, IFieldName.payee       1      , resultData.getString("payee"));      
			response.setValue(i, IFieldName.payeeAcct  1       , resultData.getString("payee_acct"));
			response.setValue(i, IFieldName.payeeBank  1       , resultData.getString("payee_bank"));      
			response.setValue(i, IFieldName.payeeBankNo 1      , resultData.getString("payee_bank_no"));
			response.setValue(i, IFieldName.acceptor    1      , resultData.getString("acceptor"));      
			response.setValue(i, IFieldName.acceptorAcct  1    , resultData.getString("acceptor_acct"));
			response.setValue(i, IFieldName.acceptorBank  1    , resultData.getString("acceptor_bank"));      
			response.setValue(i, IFieldName.acceptorBankNo1    , resultData.getString("acceptor_bank_no"));
			response.setValue(i, IFieldName.banEndorsementMark,1 resultData.getString("forbid_flag"));      
			response.setValue(i, IFieldName.billBeforeOwner, resultData.getString("bill_before_owner"));
		}
		
		ContextUtil.setResponseData(context, response.getAnswerData());*/
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	Var105001Result resp=new Var105001Result();
    	resp.setType("S");
    	resp.setExSerial(trans.getExSerial());
    	resp.setFunctionId(trans.getFunctionId());
        resp.setCurrentPage(Var105001.getCurrentPage());
        resp.setBillList(Var105001.getBillList());
        resp.setTotalRows(Var105001.getTotalRows());
        resp.setRetNum(Integer.toString(Var105001.getBillList().size()));
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var105001Result.class);
        clazzMap.put("bean", Var105001InfoBean.class);
        String response=XmlBeanUtil.bean2xml(clazzMap, resp);     
        ContextUtil.setResponseData(context, response);
    
    }
	
	public String getTransName() {		
		return "查询可提示付款票据";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "105001";
	}
	private List<Var105001InfoBean> getVar105001InfoBeanListByRgctBillList(List<RgctBill> rgctBillList){
		List<Var105001InfoBean> billList = new ArrayList<Var105001InfoBean>();
		for(RgctBill rgctBill : rgctBillList){
			Var105001InfoBean bean = new Var105001InfoBean();
			bean.setRgctId(rgctBill.getInfo().getId());
			bean.setBillNo(rgctBill.getInfo().getBillNo());
			bean.setConferNo(rgctBill.getInfo().getConferNo());
			bean.setInvoiceNo(rgctBill.getInfo().getInvoiceNo());
			bean.setBillType(rgctBill.getInfo().getBillType());
			bean.setBillClass(rgctBill.getInfo().getBillClass());
			bean.setIssueDt(rgctBill.getInfo().getIssueDt());
			bean.setDueDt(rgctBill.getInfo().getDueDt());
			bean.setBillMoney(Double.toString(rgctBill.getInfo().getBillMoney()));
			bean.setAcceptDt(rgctBill.getInfo().getAcceptorDate());
			bean.setAcceptor(rgctBill.getInfo().getAcceptor());
			bean.setAcceptorAcct(rgctBill.getInfo().getAcceptorAcct());
			bean.setAcceptorBank(rgctBill.getInfo().getAcceptorBankName());
			bean.setAcceptorBankNo(rgctBill.getInfo().getAcceptorBankNo());
			bean.setRemitter(rgctBill.getInfo().getRemitter());
			bean.setRemitterAcct(rgctBill.getInfo().getRemitterAcct());
			bean.setRemitterBank(rgctBill.getInfo().getRemitterBankName());
			bean.setRemitterBankNo(rgctBill.getInfo().getRemitterBankNo());
			bean.setPayee(rgctBill.getInfo().getPayeeName());
			bean.setPayeeAcct(rgctBill.getInfo().getPayeeAcct());
			bean.setPayeeBank(rgctBill.getInfo().getPayeeBankName());
			bean.setPayeeBankNo(rgctBill.getInfo().getPayeeBankNo());
			bean.setBanEndorsementMark(rgctBill.getHist().getForbidFlag());
			bean.setBillBeforeOwner(rgctBill.getHist().getBillBeforeOwner());
			billList.add(bean);
		}
		return billList;
	}
}
