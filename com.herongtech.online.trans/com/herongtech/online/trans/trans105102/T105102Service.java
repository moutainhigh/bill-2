package com.herongtech.online.trans.trans105102;

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
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.util.CommUtils;
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
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

public class T105102Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var105102 transVar = new Var105102();
		
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
	 * @param Var105102
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var105102 var105102) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		RcBaseSearchBean rcsb=new RcBaseSearchBean();
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var105102.class);
        Var105102 temp=(Var105102)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子签名未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据ID未上送");
		}
		BeanUtils.copyProperties(temp, var105102);
	
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var105102
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var105102 Var105102) throws BizAppException{
    	String rgctIds = Var105102.getRgctIds();
    	int k= 0 ;
    	String signature = Var105102.getSignature();
    	String [] ids =CommUtils.couvertLong(rgctIds);
    	List<Var105102InfoBean> result =new ArrayList<Var105102InfoBean>();
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var105102InfoBean bean=new Var105102InfoBean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcPresentationService().getRgctBillById(id);
    			//检查客户是否电票签约
    			SignProd applySignProd;
    			ISignProdService signProdService =ServiceFactory.getSignProdService();
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, Var105102.getCustAccount());
    				if(applySignProd==null){
    					throw new BizAppException("客户未签约");
    				}
    			}  
    			bill.getHist().setFromSign(signature);
    			String cur_status = bill.getHist().getCurStatus();// R_08提示付款待签收
    			if ("R_08".equals(cur_status)) {
    				RcServiceFactory.getRcPresentationService().cancelPayEndorse(bill);
    			} else if ("S_08".equals(cur_status)) {
    				RcServiceFactory.getRcPresentationService().cancelOverdue(bill);
    			}
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
//    			e.printStackTrace();
    		}
    		
    		result.add(bean);
    		
		}
    	for(int i=0; i<result.size();i++){
    		if("E".equals(result.get(i).getIsSuccess())){
    			k=k+1;
    		}
    		
    	}
    	Var105102.setErrNum(String.valueOf(k));
    	Var105102.setTotNum(String.valueOf(result.size()));
    	Var105102.setResult(result);
    	
    	
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var105102
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var105102 Var105102) throws BizAppException{
        TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        T105102ProResult proResult=new T105102ProResult();
        proResult.setType("S");
        if(!"0".equals(Var105102.getErrNum())){
   			proResult.setType("E");
   		}
        proResult.setExSerial(trans.getExSerial());
        proResult.setTotNum(Var105102.getTotNum());
        proResult.setErrNum(Var105102.getErrNum());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var105102InfoBean> infoList=Var105102.getResult();
        Var105102Result result=new Var105102Result();
        result.setT105102ProResult(proResult);
        result.setResult(infoList);
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var105102Result.class);
        clazzMap.put("info", Var105102InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
       
    }
	
	public String getTransName() {		
		return "提示付款撤回";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "105102";
	}
}
