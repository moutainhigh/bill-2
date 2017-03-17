package com.herongtech.online.trans.trans107006;

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
import com.herongtech.online.trans.trans107002.Var107002InfoBean;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;
/**
 * 待解质签收票据查询
 */
public class T107006Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var107006 transVar = new Var107006();
		
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
	 * @param Var107006
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var107006 var107006) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
		clazzMap.put("Document", Var107006.class);
		Var107006 temp=(Var107006)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		BeanUtils.copyProperties(temp, var107006);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var107006
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var107006 var107006) throws BizAppException{
    	RcBaseSearchBean searchBean=new RcBaseSearchBean();
        searchBean.setToAcctNo(var107006.getCustAccount());
        searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        List<Var107006InfoBean> result =new ArrayList<Var107006InfoBean>();
    	IDB session = DBFactory.getDB();
//    	String sql="select billinfo.*,billhist.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.hist_id";
    	String sql = RcServiceFactory.getRcUnimpawnService().queryPresignBill(searchBean);
    	List<Object> list = new ArrayList<Object>();
    	List<RgctBill> rgctBill = null;
		try {
			rgctBill = session.getBeanListByListForPage(
					sql,
					RgctBill.class,
					Integer.valueOf(var107006.getCurrentPage()),
					Integer.valueOf(var107006.getPageSize()),
					list);
			var107006.setTotalRows(Integer.toString(session.account("select count(*) from ("+sql+")")));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	/*if(resultData.getRowCount() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}*/
		if(rgctBill.size() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}else{
    		for (RgctBill rgctBillList:rgctBill) {
    			Var107006InfoBean bean=new Var107006InfoBean();
    			bean.setRgctId(rgctBillList.getInfo().getId());
    			bean.setBillNo(rgctBillList.getInfo().getBillNo());
    			bean.setConferNo(rgctBillList.getInfo().getConferNo());
    			bean.setInvoiceNo(rgctBillList.getInfo().getInvoiceNo());
    			bean.setBillType(rgctBillList.getInfo().getBillType());
    			bean.setBillClass(rgctBillList.getInfo().getBillClass());
    			bean.setIssueDt(rgctBillList.getInfo().getIssueDt());
    			bean.setDueDt(rgctBillList.getInfo().getDueDt());
    			bean.setAcceptDt(rgctBillList.getInfo().getAcceptorDate());
    			bean.setBillMoney(Double.toString(rgctBillList.getInfo().getBillMoney()));
    			bean.setRemitter(rgctBillList.getInfo().getRemitter());
    			bean.setRemitterAcct(rgctBillList.getInfo().getRemitterAcct());
    			bean.setRemitterBank(rgctBillList.getInfo().getRemitterBankName());
    			bean.setRemitterBankNo(rgctBillList.getInfo().getRemitterBankNo());
    			bean.setPayee(rgctBillList.getInfo().getPayeeName());
    			bean.setPayeeAcct(rgctBillList.getInfo().getPayeeAcct());
    			bean.setPayeeBank(rgctBillList.getInfo().getPayeeBankName());
    			bean.setPayeeBankNo(rgctBillList.getInfo().getPayeeBankNo());
    			bean.setAcceptor(rgctBillList.getInfo().getAcceptor());
    			bean.setAcceptorAcct(rgctBillList.getInfo().getAcceptorAcct());
    			bean.setAcceptorBank(rgctBillList.getInfo().getAcceptorBankName());
    			bean.setAcceptorBankNo(rgctBillList.getInfo().getAcceptorBankNo());
    			bean.setBanEndorsementMark(rgctBillList.getHist().getForbidFlag());
    			bean.setApplicantName(rgctBillList.getHist().getFromName());
    			bean.setApplicantAcctNo(rgctBillList.getHist().getFromAcctNo());
    			bean.setApplicantOrgCode(rgctBillList.getHist().getFromBranchNo());
    			bean.setApplicantBankNo(rgctBillList.getHist().getFromBankNo());
    			bean.setApplyDate(rgctBillList.getHist().getEndorseDt());
    			bean.setReceiverAcctNo(rgctBillList.getHist().getToAcctNo());
    			bean.setReceiverName(rgctBillList.getHist().getToName());
    			bean.setReceiverBankNo(rgctBillList.getHist().getToBankNo());
    			
    			result.add(bean);
    		}
    	}
    	var107006.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var107006
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var107006 var107006) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var107006InfoBean> infoList=var107006.getResult();
        Var107006Result result=new Var107006Result();
        result.setProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var107006Result.class);
        clazzMap.put("info", Var107006InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
    	
	public String getTransName() {		
		return "待解质签收票据查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "107006";
	}
}