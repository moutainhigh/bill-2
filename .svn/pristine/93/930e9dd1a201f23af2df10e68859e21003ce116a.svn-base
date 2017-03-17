/********************************************
 * 文件名称: T201001Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 贴现申请
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-07-16
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.acpt.trans201001;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.acpt.AcptApplyBean;
import com.herongtech.console.service.busiservice.acpt.AcptBillInfoBean;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans101101.Var101101;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 信贷放款
 */

public class T201001Service extends OnlineBaseService{


	/**
	 * 入口方法
	 * @param context
	 */
	@Override
	protected void action(Context context) throws Exception {
		Var201001 transVar = new Var201001();
		
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
	 * @param var106101
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var201001 var201001) throws BizAppException {
		String request = (String)ContextUtil.getRequestData(context);
//		BusiDate busiDate = (BusiDate) ContextUtil.getContextAttribute(context, IConstants.SYSBUSININFO);
		ArrayList<AcptBillInfoBean> list=new ArrayList<AcptBillInfoBean>();
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var201001.class);
        clazzMap.put("bean", AcptBillInfoBean.class);
        Var201001 temp=(Var201001)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		

		if (StringUtils.isEmpty(temp.getBranchNo())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "签发机构未上送");
		}
		if (StringUtils.isEmpty(temp.getProtocalNo())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "银承协议编号未上送");
		}
		if (StringUtils.isEmpty(temp.getRemitterAcct())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "出票人客户账号未上送");
		}
		if (StringUtils.isEmpty(temp.getIssueDt())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "出票日未上送");
		}
		if (StringUtils.isEmpty(temp.getDueDt())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "到期日未上送");
		}
		if (StringUtils.isEmpty(temp.getBillType())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据类型未上送");
		}
		if (StringUtils.isEmpty(temp.getBillClass())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据分类未上送");
		}
		if (StringUtils.isEmpty(temp.getAccountNo1())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "第一还款账号未上送");
		}
		if (StringUtils.isEmpty(temp.getGrantAmt1())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "第一保证金限额未上送");
		}
		if (StringUtils.isEmpty(temp.getLoanSystem())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "贷款系统未上送");
		}
		if (StringUtils.isEmpty(temp.getRemitterCustNo())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "出票人客户号未上送");
		}
		var201001.setAccountNo1(temp.getAccountNo1());
		var201001.setGrantAmt1(temp.getGrantAmt1());
		var201001.setBillClass(temp.getBillClass());
		var201001.setBillType(temp.getBillType());
		var201001.setBranchNo(temp.getBranchNo());
		var201001.setDueDt(temp.getDueDt());
		var201001.setIssueDt(temp.getIssueDt());
		var201001.setLoanSystem(temp.getLoanSystem());
		var201001.setProtocalNo(temp.getProtocalNo());
		var201001.setRemitterAcct(temp.getRemitterAcct());
		var201001.setRemitterCustNo(temp.getRemitterCustNo());
		var201001.setBatchNo(temp.getBatchNo());
	
		
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(temp.getBillClass())){
			if (StringUtils.isEmpty(temp.getBatchNo())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "批次号未上送");
			}
			var201001.setBatchNo(temp.getBatchNo());
		}
//		var201001.setTransPub(transPub)
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(temp.getBillClass())){
//		request.beforeFirst();
		
		for(AcptBillInfoBean acptBillInfoBeanDate:temp.getBillList()){
	//		request.next();
			if (StringUtils.isEmpty(acptBillInfoBeanDate.getPayeeAcct())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "收款人账号未上送");
			}
			if (StringUtils.isEmpty(acptBillInfoBeanDate.getBillMoney())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票面金额未上送");
			}
			if (StringUtils.isEmpty(acptBillInfoBeanDate.getCurrencyCategory())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "币种未上送");
			}
			if (StringUtils.isEmpty(acptBillInfoBeanDate.getPayee())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "收款人名称未上送");
			}
			if (StringUtils.isEmpty(acptBillInfoBeanDate.getPayeeBankName())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "收款人开户行名称未上送");
			}
			
			String billMoney = acptBillInfoBeanDate.getBillMoney();
		    String currencyCategory = acptBillInfoBeanDate.getCurrencyCategory();
		    String payeeAcct = acptBillInfoBeanDate.getPayeeAcct();
			String payee = acptBillInfoBeanDate.getPayee();
		    String payeeBank = acptBillInfoBeanDate.getPayeeBankName();
		    AcptBillInfoBean bill=new AcptBillInfoBean();
		    bill.setBillMoney(billMoney);
		    bill.setPayee(payee);
		    bill.setPayeeAcct(payeeAcct);
		    bill.setPayeeBankName(payeeBank);
		    bill.setCurrencyCategory(currencyCategory);
			list.add(bill);
		}
		}
		try {
			BeanUtils.copyProperties(temp, var201001);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		var201001.setBillList(list);
	}

	/**
	 * 放款处理
	 * @param context
	 * @param var106101
	 * @throws BizAppException
	 */
	protected void ToLocal(Context context, Var201001 var201001) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		List<AcptBillInfoBean> billList=var201001.getBillList();
		IDB session = DBFactory.getDB();
		AcptApplyBean batchBean=new AcptApplyBean();
		try {
			BeanUtils.copyProperties(batchBean, var201001);
			session.beginTransaction();
			ServiceFactory.getAcptService().loanNotification(batchBean, billList);
	    	session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			throw new BizAppException(e.getMessage());	
		}
    	
	}

	 /**
     * 应答包处理
     * @param context
     * @param Var106101
     * @throws BizAppException
     */
	protected void PackAnswer(Context context, Var201001 var201001) throws BizAppException{
        TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult result=new ProResult();
        result.setType("S");
        result.setExSerial(trans.getExSerial());
        result.setFunctionId(trans.getFunctionId());
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", ProResult.class);
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);		
		ContextUtil.setResponseData(context, resp);
	}

	
	@Override
	public String getServiceId() {
		return "201001";
	}
	
	@Override
	public String getTransName() {
		return "信贷放款通知";
	}

	@Override
	public String getTransVersion() {
		return "2.0.0.1";
	}

}
