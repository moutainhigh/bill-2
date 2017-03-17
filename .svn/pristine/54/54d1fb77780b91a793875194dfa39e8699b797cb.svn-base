package com.herongtech.online.trans.trans107105;

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
import com.herongtech.online.trans.trans107105.Var107105;
import com.herongtech.online.trans.trans107105.Var107105InfoBean;
import com.herongtech.online.trans.trans107105.Var107105Result;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;
/**
 * 票据解质押撤消
 */
public class T107105Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var107105 transVar = new Var107105();
		
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
	 * @param Var107105
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var107105 var107105) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var107105.class);
        Var107105 temp=(Var107105)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "申请人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子签名未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据ID未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var107105);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var107105
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var107105 var107105) throws BizAppException{
    	String [] ids =CommUtils.couvertLong(var107105.getRgctIds());
    	List<Var107105InfoBean> result =new ArrayList<Var107105InfoBean>();
    	int k = 0;
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var107105InfoBean bean=new Var107105InfoBean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcImpawnService().getRgctBillById(id);
    			SignProd applySignProd;
    			ISignProdService signProdService= ServiceFactory.getSignProdService();
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var107105.getCustAccount());
    				if(applySignProd==null){
    					throw new BizAppException("客户未签约");
    				}
    			}  
    			org.springframework.util.Assert.isTrue(var107105.getCustAccount().equals(bill.getHist().getHoldAcctNo()),"发起人帐号" + var107105.getCustAccount()
						+ "与持票人帐号" + bill.getHist().getHoldAcctNo() + "不一致,请检查");
    			bill.getHist().setFromSign(var107105.getSignature());//申请人电子签名
    	//		bill.getHist().setEndorseDt(var107105.getApplyDate());//申请日期
    			RcServiceFactory.getRcUnimpawnService().cancelUnimpawnApply(bill);
    			
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
    	var107105.setErrNum(String.valueOf(k));
    	var107105.setTotNum(String.valueOf(result.size()));
    	var107105.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var107105
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var107105 var107105) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	Var107105ProResult proResult=new Var107105ProResult();
        proResult.setType("S");
        if(!"0".equals(var107105.getErrNum())){
  			proResult.setType("E");
  	    }
        proResult.setTotNum(var107105.getTotNum());
        proResult.setErrNum(var107105.getErrNum());
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var107105InfoBean> infoList=var107105.getResult();
        Var107105Result result=new Var107105Result();
        result.setVar107105ProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var107105Result.class);
        clazzMap.put("info", Var107105InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "票据解质押撤消";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "107105";
	}
}