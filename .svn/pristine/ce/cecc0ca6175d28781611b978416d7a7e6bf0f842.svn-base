/********************************************
 * 文件名称: T130001Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 可保证票据查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 20160808
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans130001;

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
import com.herongtech.console.web.busicontroller.common.GuarCodeConst;
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
 * 可保证票据查询
 */
public class T130001Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var130001 transVar = new Var130001();
		
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
	 * @param Var130001
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var130001 var130001) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
		clazzMap.put("Document", Var130001.class);
		Var130001 temp=(Var130001)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(temp.getBalanceType())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "保证类型未上送");
		}
		if (!(temp.getBalanceType().equals("1")||temp.getBalanceType().equals("2")||temp.getBalanceType().equals("3"))){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "保证类型不正确");
		}
		BeanUtils.copyProperties(temp, var130001);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var130001
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var130001 var130001) throws BizAppException{
    	RcBaseSearchBean searchBean=new RcBaseSearchBean();
    	searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
    	searchBean.setAssureType(var130001.getBalanceType());
    	if(var130001.getBalanceType().equals(GuarCodeConst.ASSU_TYPE_ACPT_2)){//承兑保证 需要根据承兑人账号查询 其他保证通过holdAcctNo
//    		searchBean.setAcceptorAcctNo(var130001.getCustAccount());
    	}else{
    		searchBean.setHoldAcctNo(var130001.getCustAccount());
    	}
        List<Var130001InfoBean> result =new ArrayList<Var130001InfoBean>();
        IDB session = DBFactory.getDB();
    	String sql=RcServiceFactory.getRcAssuranceService().queryAssure(searchBean);
    	List<Object> list = new ArrayList<Object>();
    	List<RgctBill> rgctBill = null;
		try {
			rgctBill = session.getBeanListByListForPage(
					sql,
					RgctBill.class,
					Integer.valueOf(var130001.getCurrentPage()),
					Integer.valueOf(var130001.getPageSize()),
					list);
			var130001.setTotalRows(Integer.toString(session.account("select count(*) from ("+sql+")")));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
		if(rgctBill.size() <= 0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}else{
    		for (RgctBill rgctBillList:rgctBill) {
    			Var130001InfoBean bean=new Var130001InfoBean();
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
    			bean.setBillBeforeOwner(rgctBillList.getHist().getBillBeforeOwner());
    			
    			result.add(bean);
    		}
    	}
		var130001.setResult(result);
    	
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var130001
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var130001 var130001) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var130001InfoBean> infoList=var130001.getResult();
        Var130001Result result=new Var130001Result();
        result.setProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var130001Result.class);
        clazzMap.put("info", Var130001InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "可保证票据查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "130001";
	}
}
