/********************************************
 * 文件名称: T120003Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 存票待签收票据查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 20160808
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans120003;

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
 * 存票待签收票据查询
 */
public class T120003Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var120003 transVar = new Var120003();
		
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
	 * @param Var120003
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var120003 var120003) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
		clazzMap.put("Document", Var120003.class);
		Var120003 temp=(Var120003)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		BeanUtils.copyProperties(temp, var120003);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var120003
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var120003 var120003) throws BizAppException{
    	RcBaseSearchBean searchBean=new RcBaseSearchBean();
        searchBean.setHoldAcctNo(var120003.getCustAccount());
        searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        List<Var120003InfoBean> result =new ArrayList<Var120003InfoBean>();
    	IDB session = DBFactory.getDB();
    	String sql = RcServiceFactory.getRcImpawnService().queryPreimpawnBill(searchBean);
    	List<Object> list = new ArrayList<Object>();
    	List<RgctBill> rgctBill = null;
		try {
			rgctBill = session.getBeanListByListForPage(
					sql,
					RgctBill.class,
					Integer.valueOf(var120003.getCurrentPage()),
					Integer.valueOf(var120003.getPageSize()),
					list);
			var120003.setTotalRows(Integer.toString(session.account("select count(*) from ("+sql+")")));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
		if(rgctBill.size() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}else{
    		for (RgctBill rgctBillList:rgctBill) {
    			Var120003InfoBean bean=new Var120003InfoBean();
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
    			bean.setRemark(rgctBillList.getHist().getFromRemark());
    			bean.setReceiverAcctNo(rgctBillList.getHist().getToAcctNo());
    			bean.setReceiverName(rgctBillList.getHist().getToName());
    			bean.setReceiverBankNo(rgctBillList.getHist().getToBankNo());
    			
    			result.add(bean);
    		}
    	}
    	var120003.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var120003
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var120003 var120003) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var120003InfoBean> infoList=var120003.getResult();
        Var120003Result result=new Var120003Result();
        result.setProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var120003Result.class);
        clazzMap.put("info", Var120003InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
    	
	public String getTransName() {		
		return "存票待签收票据查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "120003";
	}
}
