/********************************************
 * 文件名称: T103101Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 背书申请
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 2016-08-08
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans103101;

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
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.XmlBeanUtil;

import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 背书申请
 */
public class T103101Service extends OnlineBaseService {

	/**
	 * 入口方法
	 * 
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var103101 transVar = new Var103101();

		// 交易预处理
		transRequest(context);

		// 常规校验
		CheckData(context, transVar);

		// 查询处理
		ToLocal(context, transVar);

		// 应答包处理
		PackAnswer(context, transVar);
	}

	/**
	 * 交易预处理
	 * 
	 * @param context
	 * @throws Exception
	 */
	protected void transRequest(Context context) throws BizAppException {
		// 父类交易请求预处理
		super.transRequest(context);

		// 银行接口化处理
		// BankInterfaceFactory.getBankInterface().bankInterface(context);
	}

	/**
	 * 常规校验
	 * 
	 * @param context
	 * @param Var103101
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var103101 var103101)
			throws BizAppException {
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var103101.class);
        Var103101 temp=(Var103101)XmlBeanUtil.xml2Bean(clazzMap, request);
        BeanUtils.copyProperties(temp, var103101);
		if (StringUtils.isEmpty(var103101.getCustAccount())) {
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(var103101.getReceiverName())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL,"接收人客户名称未上送");
		}
		if (StringUtils.isEmpty(var103101.getReceiverAcctNo())) {
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "接收人账号未上送");
		}
		if (StringUtils.isEmpty(var103101.getReceiverBankNo())) {
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL,"接收人开户行行号未上送");
		}
		if (StringUtils.isEmpty(var103101.getBanEndorsementMark())) {
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "不得转让标记未上送");
		}
		if (StringUtils.isEmpty(var103101.getRgctIds())) {
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL,"登记中心票据ID未上送");
		}
		if (StringUtils.isEmpty(var103101.getSignature())) {
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子签名未上送");
		}
		/*rcSb.setBillClass("2");
		rcSb.setHoldAcctNo(request.getString(IFieldName.custAccount));
		
		Var103101.setRcBaseSearchBean(rcSb);*/
		BeanUtils.copyProperties(temp, var103101);

	}

	/**
	 * 查询处理
	 * 
	 * @param context
	 * @param Var103101
	 * @throws BizAppException
	 */
	protected void ToLocal(Context context, Var103101 var103101)
			throws BizAppException {
		String rgctIds = var103101.getRgctIds();
		String[] ids = CommUtils.couvertLong(rgctIds);
		List<Var103101InfoBean> result =new ArrayList<Var103101InfoBean>();

		for (int i = 0; i < ids.length; i++) {
			Var103101InfoBean bean=new Var103101InfoBean();
			IDB db = DBFactory.getDB();
			try {
				db.beginTransaction();
				String rgctId = ids[i];
				if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(rgctId)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
				RgctBill bill = RcServiceFactory.getRcEndorseService().getRgctBillById(rgctId);
				SignProd applySignProd = null;
				ISignProdService signProdService = ServiceFactory.getSignProdService();
				if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ENTY_BILL)) {
					applySignProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD,var103101.getCustAccount());
				} else if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
					applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD,var103101.getCustAccount());
				}
				if (applySignProd == null) {
					throw new BizAppException("客户未签约");
				}
				org.springframework.util.Assert.isTrue(var103101.getCustAccount().equals( bill.getHist().getHoldAcctNo()), "发起人帐号"+
						 var103101.getCustAccount() + "与持票人帐号" + bill.getHist().getHoldAcctNo()+ "不一致,请检查");
				bill.getHist().setFromAcctNo(var103101.getCustAccount());// 申请人账号
				bill.getHist().setFromSign(var103101.getSignature());// 申请人 电子签名
				bill.getHist().setEndorseDt(DateTimeUtil.getWorkdayString());//申请日期
				bill.getHist().setFromBankNo(bill.getHist().getHoldBankNo());// 申请人行号
				bill.getHist().setFromCustNo(applySignProd.getCustNo());// 申请人客户号
				bill.getHist().setFromRole(RcConstants.BUSSINESS_ROLE1);// 申请人参与者类型
				bill.getHist().setFromOrgcode(applySignProd.getIdNumber());// 申请人组织机构代码
				bill.getHist().setFromName(applySignProd.getCustName());// 申请人名称
				bill.getHist().setForbidFlag(var103101.getBanEndorsementMark());
				bill.getHist().setFromRemark(var103101.getRemark());
				if (var103101.getReceiverBankNo() != null && !"".equals(var103101.getReceiverBankNo())) {
					bill.getHist().setToAcctNo(var103101.getReceiverAcctNo());
					bill.getHist().setFromBankNo(bill.getHist().getToBankNo());
					bill.getHist().setToBankNo(var103101.getReceiverBankNo());
					bill.getHist().setToName(var103101.getReceiverName());
				}
				RcServiceFactory.getRcEndorseService().regEndorse(bill);
				bean.setRgctId(ids[i]);
    			bean.setResult("S");
				db.endTransaction();
			} catch (Exception e) {
				 bean.setRgctId(ids[i]);
	             bean.setResult("E");
	             bean.setMessage(e.getMessage());
				try {
					db.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

			result.add(bean);

		}
		var103101.setResult(result);
	}

	/**
	 * 应答包处理
	 * 
	 * @param context
	 * @param Var103101
	 * @throws BizAppException
	 */
	protected void PackAnswer(Context context, Var103101 var103101)
			throws BizAppException {

		TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var103101InfoBean> infoList=var103101.getResult();
        Var103101Result result=new Var103101Result();
        int errNum = 0;
    	int totNum = 0;
        if(infoList != null){
        	totNum = infoList.size();
        	for(int i = 0; i < infoList.size(); i++){
        		Var103101InfoBean bean = infoList.get(i);
        		if("E".equals(bean.getResult())){
        			errNum ++;
        		}
        	}
        }
		
        if(!"0".equals(Integer.toString(errNum))){
			proResult.setType("E");
		}
        result.setTotNum(Integer.toString(totNum));
        result.setErrNum(Integer.toString(errNum));
        result.setProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var103101Result.class);
        clazzMap.put("info", Var103101InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);

	}

	public String getTransName() {
		return "背书申请";
	}

	public String getTransVersion() {
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "103101";
	}
}
