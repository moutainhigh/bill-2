package com.herongtech.online.trans.trans120106;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.herongtech.xmlchannel.pkg.TransInfo;
/**
 * 解质押签收(签收或拒绝)
 */
public class T120106Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var120106 transVar = new Var120106();
		
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
	 * @param Var120106
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var120106 var120106) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var120106.class);
        Var120106 temp=(Var120106)XmlBeanUtil.xml2Bean(clazzMap, request);
        
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户帐号未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子签名未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getSignUpMark())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "签收标识未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var120106);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var120106
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var120106 var120106) throws BizAppException{
    	String [] ids =CommUtils.couvertLong(var120106.getRgctIds());
    	List<Var120106InfoBean> result =new ArrayList<Var120106InfoBean>();
    	int k = 0;
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var120106InfoBean bean=new Var120106InfoBean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcImpawnService().getRgctBillById(id);
    		//	bill.getHist().setFromSign(signUpMark);
    			SignProd signProd = null;
    			SignProd applySaveSignProd=null;
    			ISignProdService signProdService = ServiceFactory.getSignProdService();
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ENTY_BILL)) {
    				signProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, var120106.getCustAccount());
    			}else if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var120106.getCustAccount());
    				//校验客户是否电票签约和票据池签约
        			applySaveSignProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, var120106.getCustAccount());
        				if(signProd==null||applySaveSignProd==null){
            				throw new BizAppException("客户未签约");
            			}
        		}
    			//bill.getHist().setToAcctNo(applicantAcctNo);// 签收人 帐号
    			bill.getHist().setToCustNo(signProd.getCustNo());// 签收人客户号
    			bill.getHist().setToName(signProd.getCustName());// 签收人名称
    			bill.getHist().setToRole(RcConstants.BUSSINESS_ROLE1);// 签收人 参与者类型
    			bill.getHist().setToOrgcode(signProd.getIdNumber());// 签收人 组织机构代码
    			bill.getHist().setSignDt(DateTimeUtil.getWorkdayString());// 签收日期
    			bill.getHist().setSignerSign(var120106.getSignature());// 签收人电子签名
    			bill.getHist().setSignFlag(var120106.getSignUpMark());// /签收标识
    			bill.getHist().setToRemark(var120106.getRemark());// 签收人备注
    			if (var120106.getSignUpMark().equals(RcConstants.SIGN_YES)) {
    				RcServiceFactory.getRcUnimpawnService().signUnimpawn(bill);
				}else if(var120106.getSignUpMark().equals(RcConstants.SIGN_NO)){
					RcServiceFactory.getRcUnimpawnService().rejectSignUnimpawn(bill);
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
//				e.printStackTrace();
    		}
    		result.add(bean);
    	}
    	for(int i=0; i<result.size();i++){
    		if("E".equals(result.get(i).getIsSuccess())){
    			k=k+1;
    		}
    	}
    	var120106.setErrNum(String.valueOf(k));
    	var120106.setTotNum(String.valueOf(result.size()));
    	var120106.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var120106
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var120106 var120106) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	Var120106ProResult proResult=new Var120106ProResult();
        proResult.setType("S");
        if(!"0".equals(var120106.getErrNum())){
  			proResult.setType("E");
  	    }
        proResult.setTotNum(var120106.getTotNum());
        proResult.setErrNum(var120106.getErrNum());
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var120106InfoBean> infoList=var120106.getResult();
        Var120106Result result=new Var120106Result();
        result.setVar120106ProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var120106Result.class);
        clazzMap.put("info", Var120106InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "解质押签收(签收或拒绝)";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "120106";
	}
}