
package com.herongtech.online.trans.trans105002;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class T105002Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var105002 transVar = new Var105002();
		
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
	protected void CheckData(Context context, Var105002 Var105002) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String,Class> clazzMap = new HashMap<String,Class>();
		clazzMap.put("Document", Var105002.class);
		Var105002 temp = (Var105002)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		Var105002.setCustAccount(temp.getCustAccount());
		Var105002.setCurrentPage(temp.getCurrentPage());
		Var105002.setPageSize(temp.getPageSize());
		
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param transVar
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var105002 Var105002) throws BizAppException{
    	RcBaseSearchBean searchBean = new RcBaseSearchBean();
    	searchBean.setHoldAcctNo(Var105002.getCustAccount());
    	searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
    	
    	IDB session = DBFactory.getDB();
    	
    	String sql = RcServiceFactory.getRcPresentationService().queryReversibleBill(searchBean);
    	List<RgctBill> rgctBillList = null;
		try {
			List<Object> list = new ArrayList<Object>();
			rgctBillList = session.getBeanListByListForPage(sql, RgctBill.class, Integer.parseInt(Var105002.getCurrentPage()), Integer.parseInt(Var105002.getPageSize()), list);
			Var105002.setTotalRows(Integer.toString(session.account("select count(*) from ("+sql+")")));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
		if(rgctBillList.size() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}
    	Var105002.setBillList(this.getVar105002InfiBeanByRgctBillList(rgctBillList));
    }
    /**
     * 应答包处理
     * @param context
     * @param Var105001
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var105002 Var105002) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	Var105002Result resp = new Var105002Result();
    	resp.setType("S");
    	resp.setExSerial(trans.getFunctionId());
    	resp.setFunctionId(trans.getFunctionId());
    	resp.setBillList(Var105002.getBillList());
    	resp.setCurrentPage(Var105002.getCurrentPage());
    	resp.setRetNum(Integer.toString(Var105002.getBillList().size()));
    	resp.setTotalRows(Var105002.getTotalRows());
    	
    	Map<String,Class> clazzMap = new HashMap<String,Class>();
    	clazzMap.put("Document",Var105002Result.class);
    	clazzMap.put("bean", Var105002InfoBean.class);
    	String response = XmlBeanUtil.bean2xml(clazzMap, resp);
		
		ContextUtil.setResponseData(context, response);
    
    }
	
	public String getTransName() {		
		return "查询可提示付款撤回票据";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "105002";
	}
	public List<Var105002InfoBean> getVar105002InfiBeanByRgctBillList(List<RgctBill> rgctBillList){
		List<Var105002InfoBean> billList = new ArrayList<Var105002InfoBean>();
		for(RgctBill rgctBill : rgctBillList){
			Var105002InfoBean bean = new Var105002InfoBean();
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
			bean.setApplicantName(rgctBill.getHist().getFromName());
			bean.setApplicantAcctNo(rgctBill.getHist().getFromAcctNo());
			bean.setApplicantOrgCode(rgctBill.getHist().getFromBranchNo());
			bean.setApplicantBankNo(rgctBill.getHist().getFromBankNo());
			bean.setApplyDate(rgctBill.getHist().getEndorseDt());
			bean.setReceiverName(rgctBill.getHist().getToName());
			bean.setReceiverAcctNo(rgctBill.getHist().getToAcctNo());
			bean.setReceiverBankNo(rgctBill.getHist().getToBankNo());
			bean.setRemark(rgctBill.getInfo().getRemark());
			bean.setOverDueReason(rgctBill.getHist().getOverdueRs());
			billList.add(bean);
		}
		return billList;
}
}
