/********************************************
 * 文件名称: T101002Service.java
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
package com.herongtech.online.trans.trans101102;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans106101.Var106101;
import com.herongtech.online.trans.trans106101.Var106101InfoBean;
import com.herongtech.online.trans.trans106101.Var106101ProResult;
import com.herongtech.online.trans.trans106101.Var106101Result;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 提示收票 
 */
public class T101102Service extends OnlineBaseService {

	//private IRcBillNotifyService rcBillNotifyService;
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var101102 transVar = new Var101102();
		
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
	 * @param Var101102
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var101102 var101102) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var101102.class);
        Var101102 temp=(Var101102)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "申请人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "登记中心票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "网银电子签名未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var101102);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var101102
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var101102 var101102) throws BizAppException{
    	String rgctIds = var101102.getRgctIds();
    	String signature = var101102.getSignature();
    	String [] ids =CommUtils.couvertLong(rgctIds);
    	List<Var101102InfoBean> result =new ArrayList<Var101102InfoBean>();
    	int k = 0;
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var101102InfoBean bean = new Var101102InfoBean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			ISignProdService signProdService = ServiceFactory.getSignProdService();
    			RgctBill bill=RcServiceFactory.getRcRegBillService().getRgctBillById(id);
    			//检查客户是否电票签约
    			SignProd applySignProd;
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var101102.getCustAccount());
    				if(applySignProd==null){
    					throw new BizAppException("客户未签约");
    				}
    			}
    			bill.getHist().setFromBankNo(bill.getInfo().getRemitterBankNo());
				bill.getInfo().setRemitterSign(signature);
				bill.getHist().setToAcctNo(bill.getInfo().getPayeeAcct());
				bill.getHist().setToBankNo(bill.getInfo().getPayeeBankNo());
				bill.getHist().setToName(bill.getInfo().getPayeeName());
				bill.getHist().setToCustNo(null);
				bill.getHist().setToOrgcode(null);
				RcServiceFactory.getRcBillNotifyService().issuanceRequest(bill);
				bean.setRgctId(id);
    			bean.setIsSuccess("S");
        		session.endTransaction();
    		}catch(Exception e){
    			
    			bean.setRgctId(ids[i]);
    			bean.setIsSuccess("E");
                bean.setErrMsg(e.getMessage());
    			try {
                    session.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
    		}
    		
    		result.add(bean);
    		
		}
    	
    	for (int i = 0; i < result.size(); i++) {
			if("E".equals(result.get(i).getIsSuccess())){
				k=k+1;
			}
		
	}
    	var101102.setErrNum(String.valueOf(k));
    	var101102.setTotNum(String.valueOf(result.size()));
    	var101102.setResultList(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var101102
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var101102 var101102) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
		Var101102ProResult proResult = new Var101102ProResult();
		proResult.setType("S");
		if(!"0".equals(var101102.getErrNum())){
			proResult.setType("E");
		}
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        proResult.setErrNum(var101102.getErrNum());
        proResult.setTotNum(var101102.getTotNum());
        List<Var101102InfoBean> infoList = var101102.getResultList();
        Var101102Result result = new Var101102Result();
        result.setVar101102ProResult(proResult);
        result.setResult(infoList);
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var101102Result.class);
        clazzMap.put("info", Var101102InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "提示收票";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "101102";
	}
}
