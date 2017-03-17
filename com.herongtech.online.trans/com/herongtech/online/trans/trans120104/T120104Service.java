package com.herongtech.online.trans.trans120104;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
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
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 票据解质押
 */
public class T120104Service extends OnlineBaseService{
	
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var120104 transVar = new Var120104();
		
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
	 * @param Var120104
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var120104 var120104) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var120104.class);
        Var120104 temp=(Var120104)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "出质人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "出质人电子签名未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var120104);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var120104
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var120104 var120104) throws BizAppException{
    	String [] ids =CommUtils.couvertLong(var120104.getRgctIds());
    	List<Var120104InfoBean> result =new ArrayList<Var120104InfoBean>();
    	int k = 0;
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var120104InfoBean bean=new Var120104InfoBean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcImpawnService().getRgctBillById(id);
    			//校验客户是否电票签约和票据池签约
    			SignProd applySignProd = null;
    			SignProd applySaveSignProd=null;
    			ISignProdService signProdService = ServiceFactory.getSignProdService();
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var120104.getCustAccount());
    				applySaveSignProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, var120104.getCustAccount());
    				if(applySignProd==null||applySaveSignProd==null){
        				throw new BizAppException("客户未签约");
        			}
    			}
    			bill.getHist().setEndorseDt(DateTimeUtil.getWorkday());
				String fromCustNo = bill.getHist().getToCustNo();
//				String fromName = rgctBill.getHist().getToName();
				String fromRole = bill.getHist().getToRole();
				String fromOrgcode = bill.getHist().getToOrgcode();
				bill.getHist().setToName(bill.getHist().getFromName());
				bill.getHist().setToAcctNo(bill.getHist().getFromAcctNo());
				bill.getHist().setToBankNo(bill.getHist().getFromBankNo());
				
				bill.getHist().setFromAcctNo(bill.getHist().getHoldAcctNo());
				bill.getHist().setFromBankNo(bill.getHist().getHoldBankNo());
				bill.getHist().setFromCustNo(fromCustNo);
				bill.getHist().setFromName(bill.getHist().getHoldCustName());
				bill.getHist().setFromRole(fromRole);
				bill.getHist().setFromRemark(var120104.getRemark());
				bill.getHist().setFromSign(var120104.getSignature());
				bill.getHist().setFromOrgcode(fromOrgcode);
    			RcServiceFactory.getRcUnimpawnService().submitUnimpawnApply(bill);

    			bean.setRgctId(ids[i]);
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
//				e.printStackTrace();
    		}
    		result.add(bean);
    	}
    	for(int i=0; i<result.size();i++){
    		if("E".equals(result.get(i).getIsSuccess())){
    			k=k+1;
    		}
    	}
    	var120104.setErrNum(String.valueOf(k));
    	var120104.setTotNum(String.valueOf(result.size()));
    	var120104.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var120104
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var120104 var120104) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	Var120104ProResult proResult=new Var120104ProResult();
        proResult.setType("S");
        if(!"0".equals(var120104.getErrNum())){
  			proResult.setType("E");
  	    }
        proResult.setTotNum(var120104.getTotNum());
        proResult.setErrNum(var120104.getErrNum());
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var120104InfoBean> infoList=var120104.getResult();
        Var120104Result result=new Var120104Result();
        result.setVar120104ProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var120104Result.class);
        clazzMap.put("info", Var120104InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
			
    }
	
	public String getTransName() {		
		return "票据解质押";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "120104";
	}
	

}
