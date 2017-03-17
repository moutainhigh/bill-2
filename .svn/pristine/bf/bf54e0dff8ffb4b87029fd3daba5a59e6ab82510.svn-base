/********************************************
 * 文件名称: T101105Service.java
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
package com.herongtech.online.trans.trans101105;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.XmlBeanUtil;
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
import com.herongtech.online.trans.trans101105.Var101105Bean;
import com.herongtech.online.trans.trans101105.Var101105Result;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;
/**
 * 撤票申请服务
 */
public class T101105Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var101105 transVar = new Var101105();
		
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
	 * @param var101105
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var101105 var101105) throws BizAppException{
String request = (String)ContextUtil.getRequestData(context);
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var101105.class);
        Var101105 temp=(Var101105)XmlBeanUtil.xml2Bean(clazzMap, request);
		
        if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "申请人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "登记中心票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "网银电子签名未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var101105);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param var101105
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var101105 var101105) throws BizAppException{
//    	IRcBillNotifyService rcBillNotifyService = new RcBillNotifyService();
//    	IData request = (IData)ContextUtil.getRequestData(context);
//    	String rgctIds = Var101105.getRgctIds();
//    	String signature = Var101105.getSignature();
//    	String [] ids =CommUtils.couvertLong(rgctIds);
//    	ArrayList<Object> result =new ArrayList<Object>();
//    	IDB session = DBFactory.getDB();
//    	for (int i = 0; i < ids.length; i++) {
//    		String[] ret=new String[3];
//    		try{
//    			session.beginTransaction();
//    			String id = ids[i];
//    			RgctBill bill=RcServiceFactory.getRcRegBillService().getRgctBillById(id);
//    			bill.getHist().setFromBankNo(bill.getInfo().getRemitterBankNo());
//				bill.getInfo().setRemitterSign(signature);
//				RcServiceFactory.getRcRegBillService().remitterBack(bill);
//    			ret[0]=ids[i];
//        		ret[1]="交易成功";
//        		ret[2]=null;
//        		session.endTransaction();
//    		}catch(Exception e){
//    			ret[0]=ids[i];
//    			ret[1]="交易失败";
//    			ret[2]=e.getMessage();
//    			try {
//                    session.rollback();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//    			 e.printStackTrace();
//    		}
//    		
//    		result.add(ret);
//    		
//		}
//    	Var101105.setResult(result);
    	String custAccount = var101105.getCustAccount();
    	String rgctIds = var101105.getRgctIds();
    	String signature = var101105.getSignature();
    	String [] ids =CommUtils.couvertLong(rgctIds);
    	List<Var101105Bean> result =new ArrayList<Var101105Bean>();
		
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    	    Var101105Bean bean=new Var101105Bean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcRegBillService().getRgctBillById(id);
    			ISignProdService signProdService = ServiceFactory.getSignProdService();
    			SignProd applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD,var101105.getCustAccount());
    			/*if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ENTY_BILL)) {
					applySignProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, custAccount);
				} else if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {*/
				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, custAccount);
    			if(applySignProd==null){
					throw new BizAppException("客户未签约");
				}
    			org.springframework.util.Assert.isTrue(custAccount.equals(bill.getHist().getHoldAcctNo()), "发起人帐号" + custAccount
						+ "与持票人帐号" + bill.getHist().getHoldAcctNo() + "不一致,请检查");
    			
    			bill.getHist().setFromSign(signature);
				bill.getHist().setFromAcctNo(var101105.getCustAccount());//申请人 账号
				bill.getHist().setEndorseDt(DateTimeUtil.getWorkdayString());//申请 日期
//				bill.getHist().setFromBankNo(bill.getHist().getHoldBankNo());//申请人 行号
				bill.getHist().setFromCustNo(applySignProd.getCustNo());//申请人客户号
				bill.getHist().setFromRole(IDict.K_BUSSINESS_ROLE.BUSSINESS_ROLE1);//申请人参与者类型
				bill.getHist().setFromOrgcode(applySignProd.getIdNumber());//申请人组织机构代码
				bill.getHist().setFromName(applySignProd.getCustName());//申请人名称
				bill.getHist().setFromBankNo(bill.getInfo().getRemitterBankNo());
				bill.getInfo().setRemitterSign(signature);
    			RcServiceFactory.getRcRegBillService().remitterBack(bill);
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
                e.printStackTrace();
    		}
    		result.add(bean);
		}
    	var101105.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var101105
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var101105 Var101105) throws BizAppException{
//    	IData request = (IData)ContextUtil.getRequestData(context);
//		AnswerData response = new AnswerData(request);
//		List<Var101105Bean> result=Var101105.getResult();
//		
//		for(int i = 0; i < result.size(); i++){
//			String[] resultData= (String[]) result.get(i);
//			if(i == 0){
//				response.setValue(i+1, IFieldName.totNum, result.size());
//				response.setValue(i+1, IFieldName.errNum, result.size());
//			}	
//				response.setValue(i+1, IFieldName.rgctId            , (String)resultData[0]);            
//				response.setValue(i+1, IFieldName.isSuccess            , (String)resultData[1]);
//				if("交易成功".equals(resultData[1])){
//					response.setValue(i+1, IFieldName.errMsg            , (String)resultData[2]); 
//				}
//		}
//		
//		ContextUtil.setResponseData(context, response.getAnswerData());
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
   	 Var101105Result result=new Var101105Result();
        ProResult proResult=new ProResult();
        result.setType("S");
        result.setExSerial(trans.getExSerial());
        result.setFunctionId(trans.getFunctionId());
        result.setBeanlist(Var101105.getResult());
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var101105Result.class);
        clazzMap.put("info", Var101105Bean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "撤票申请服务";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "101105";
	}
}
