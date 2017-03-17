/********************************************
 * 文件名称: T103103Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 背书回复
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 2016-08-08
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans103103;

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
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

import com.herongtech.exception.impl.BizAppException;

import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;




/**
 * 背书回复
 */
public class T103103Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var103103 transVar = new Var103103();
		
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
	 * @param Var103103
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var103103 var103103) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var103103.class);
       Var103103 temp=(Var103103)XmlBeanUtil.xml2Bean(clazzMap, request);
       BeanUtils.copyProperties(temp, var103103);
		if (StringUtils.isEmpty(var103103.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(var103103.getSignUpMark())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "签收标识未上送");
		}
		if (StringUtils.isEmpty(var103103.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "登记中心票据ID未上送");
	    }
		if (StringUtils.isEmpty(var103103.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子签名未上送");
		}
		
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var103103
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var103103 var103103) throws BizAppException{
    	//IData request = (IData)ContextUtil.getRequestData(context);
    	String rgctIds = var103103.getRgctIds();
    	String [] ids =CommUtils.couvertLong(rgctIds);
    	List<Var103103InfoBean> result =new ArrayList<Var103103InfoBean>();
    	
    	for (int i = 0; i < ids.length; i++) {
    		Var103103InfoBean bean=new Var103103InfoBean();
    		IDB db = DBFactory.getDB();
    		
    		try{
    			db.beginTransaction();
    			String rgctId = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(rgctId)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill = RcServiceFactory.getRcEndorseService().getRgctBillById(rgctId);
    			SignProd signProd = null;
    			ISignProdService signProdService =ServiceFactory.getSignProdService();
    			String CustAccount = var103103.getCustAccount();
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ENTY_BILL)) {
    				signProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, CustAccount);
    			} else if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, CustAccount);
    			}
    			if (signProd == null) {
    				throw new BizAppException("客户未签约");
    			}
    			bill.getHist().setToCustNo(signProd.getCustNo());// 签收人客户号
    			bill.getHist().setToName(signProd.getCustName());// 签收人名称
    			bill.getHist().setToRole(RcConstants.BUSSINESS_ROLE1);// 签收人 参与者类型
    			bill.getHist().setToOrgcode(signProd.getIdNumber());// 签收人 组织机构代码
    			bill.getHist().setSignDt(DateTimeUtil.getWorkdayString());// 签收日期//直接给的日期没有调用MsgUtil.getEcdsCurrentDate()
    			bill.getHist().setSignerSign(var103103.getSignature());// 签收人电子签名
    			bill.getHist().setSignFlag(var103103.getSignUpMark());// /签收标识
    			bill.getHist().setToRemark(var103103.getRemark());// 签收人备注
    			if(var103103.getPayRefuseCode()!=null||!"".equals(var103103.getPayRefuseCode())){
    				bill.getHist().setRejectCode(var103103.getPayRefuseCode());
    				bill.getHist().setRejectReason(var103103.getRejectReason());
    				bill.getHist().setSignFlag(RcConstants.SIGN_NO);
    			}else{
    				bill.getHist().setSignFlag(RcConstants.SIGN_YES);
    			}
    			bill.getHist().setIsDelegate(IConstants.Zero);
    			if (RcConstants.SIGN_YES.equals(var103103.getSignUpMark())) {
    				RcServiceFactory.getRcEndorseService().signEndorse(bill);
    			} else {
    				RcServiceFactory.getRcEndorseService().rejectEndorse(bill);
    			}
    				
    			bean.setRgctId(ids[i]);
     			bean.setResult("S");
        		db.endTransaction();
    		}catch(Exception e){
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
    	var103103.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var103103
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var103103 var103103) throws BizAppException{
    	
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var103103InfoBean> infoList=var103103.getResult();
        Var103103Result result=new Var103103Result();
        int errNum = 0;
    	int totNum = 0;
        if(infoList != null){
        	totNum = infoList.size();
        	for(int i = 0; i < infoList.size(); i++){
        		Var103103InfoBean bean = infoList.get(i);
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
        clazzMap.put("Document", Var103103Result.class);
        clazzMap.put("info", Var103103InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
		
	}
	
	public String getTransName() {		
		return "背书回复";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "103103";
	}
}
