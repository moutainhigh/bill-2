/********************************************
 * 文件名称: T102003Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 查询提示承兑待签收票据
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzx
 * 开发时间: 2016-08-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans102003;

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
 * 查询提示承兑待签收票据
 */
public class T102003Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var102003 transVar = new Var102003();
		
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
	 * @param Var102003
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var102003 var102003) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String,Class> clazzMap = new HashMap<String,Class>();
		clazzMap.put("Document", Var102003.class);
		Var102003 temp = (Var102003)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		var102003.setCustAccount(temp.getCustAccount());
		var102003.setCurrentPage(temp.getCurrentPage());
        var102003.setPageSize(temp.getPageSize());
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var102003
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var102003 var102003) throws BizAppException{
    	RcBaseSearchBean searchBean = new RcBaseSearchBean();
    	searchBean.setHoldAcctNo(var102003.getCustAccount());
    	searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
    	IDB session = DBFactory.getDB();
    	String sql =RcServiceFactory.getRcAcptBillService().queryAcceptNotifyWaitSign(searchBean);
    	List<RgctBill> rgctBillList = null;
		try {
			List<Object> list = new ArrayList<Object>();
			rgctBillList = session.getBeanListByListForPage(sql, RgctBill.class, Integer.parseInt(var102003.getCurrentPage()), Integer.parseInt(var102003.getPageSize()), list);
			var102003.setTotalRows(Integer.toString(session.account("select count(*) from ("+sql+")")));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
		if(rgctBillList.size() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}
    	
		var102003.setBillList(this.getVar102003InfiBeanByRgctBillList(rgctBillList));
    	
    	/*try {
			Var102003.setTotRecNum(
					session.account(sql.toString()));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询记录数失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询记录数失败");
		}*/
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var102003
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var102003 var102003) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	Var102003Result resp = new Var102003Result();
    	resp.setType("S");
    	resp.setExSerial(trans.getExSerial());
    	resp.setFunctionId(trans.getFunctionId());
    	resp.setBillList(var102003.getBillList());
    	resp.setCurrentPage(var102003.getCurrentPage());
    	resp.setRetNum(Integer.toString(var102003.getBillList().size()));
    	resp.setTotalRows(var102003.getTotalRows());
    	
    	Map<String,Class> clazzMap = new HashMap<String,Class>();
    	clazzMap.put("Document",Var102003Result.class);
    	clazzMap.put("bean", Var102003InfoBean.class);
    	String response = XmlBeanUtil.bean2xml(clazzMap, resp);
		ContextUtil.setResponseData(context, response);
    }
	
	public String getTransName() {		
		return "查询提示承兑待签收票据";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "102003";
	}
	public List<Var102003InfoBean> getVar102003InfiBeanByRgctBillList(List<RgctBill> rgctBillList){
		List<Var102003InfoBean> billList = new ArrayList<Var102003InfoBean>();
		for(RgctBill rgctBill : rgctBillList){
			Var102003InfoBean bean = new Var102003InfoBean();
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
			billList.add(bean);
		}
		return billList;
	}
}
