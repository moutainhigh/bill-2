/********************************************
 * 文件名称: T120101Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 票据存票
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 20160808
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans120101;

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
 * 票据存票
 */
public class T120101Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var120101 transVar = new Var120101();
		
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
	 * @param Var120101
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var120101 var120101) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var120101.class);
        Var120101 temp=(Var120101)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "出质人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "出质人电子签名未上送");
		}
		if (StringUtils.isEmpty(temp.getReceiverAcctNo())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "质权人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getReceiverBankNo())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "质权人开户行行号未上送");
		}
		if (StringUtils.isEmpty(temp.getReceiverName())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "质权人名称未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var120101);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var120101
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var120101 var120101) throws BizAppException{
    	String [] ids =CommUtils.couvertLong(var120101.getRgctIds());
    	List<Var120101InfoBean> result =new ArrayList<Var120101InfoBean>();
    	int k = 0;
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var120101InfoBean bean=new Var120101InfoBean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcSaveBillService().getRgctBillById(id);
    			SignProd applySignProd = null;
    			SignProd applySaveSignProd=null;
    			ISignProdService signProdService = ServiceFactory.getSignProdService();
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ENTY_BILL)) {
    				applySignProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, var120101.getCustAccount());
    			}else if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var120101.getCustAccount());
    				applySaveSignProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, var120101.getCustAccount());
    				if(applySignProd==null||applySaveSignProd==null){
        				throw new BizAppException("客户未签约");
        			}
    			}
    			org.springframework.util.Assert.isTrue(var120101.getCustAccount().equals(bill.getHist().getHoldAcctNo()),"发起人帐号" + var120101.getCustAccount()
    						+ "与持票人帐号" + bill.getHist().getHoldAcctNo() + "不一致,请检查");
    			
                bill.getHist().setFromAcctNo(var120101.getCustAccount());//申请人账号
    			bill.getHist().setFromSign(var120101.getSignature());//申请人电子签名
    			bill.getHist().setEndorseDt(DateTimeUtil.getWorkdayString());//申请日期
    			bill.getHist().setFromBankNo(bill.getHist().getHoldBankNo());//申请人行号
    			bill.getHist().setFromCustNo(applySignProd.getCustNo());//申请人客户号
    			bill.getHist().setFromRole(RcConstants.BUSSINESS_ROLE1);//申请人参与者类型
    			bill.getHist().setFromOrgcode(applySignProd.getIdNumber());//申请人组织机构代码
    			bill.getHist().setFromName(applySignProd.getCustName());//申请人名称
    			
    			bill.getHist().setBatchId(var120101.getBatchNo());
    			bill.getHist().setFromRemark(var120101.getRemark());
    			if(var120101.getReceiverBankNo()!=null&&!"".equals(var120101.getReceiverBankNo())){
    				bill.getHist().setToName(var120101.getReceiverName());
    				bill.getHist().setFromBankNo(bill.getHist().getToBankNo());
    				bill.getHist().setToAcctNo(var120101.getReceiverAcctNo());
    				bill.getHist().setToBankNo(var120101.getReceiverBankNo());
    			}
    			bill.getHist().setBuyType(RcConstants.BUY_INPOOL);
    			RcServiceFactory.getRcImpawnService().submitImpawnApply(bill);

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
    		}
    		result.add(bean);
    	}
    	for(int i=0; i<result.size();i++){
    		if("E".equals(result.get(i).getIsSuccess())){
    			k=k+1;
    		}
    	}
    	var120101.setErrNum(String.valueOf(k));
    	var120101.setTotNum(String.valueOf(result.size()));
    	var120101.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var120101
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var120101 var120101) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	Var120101ProResult proResult=new Var120101ProResult();
        proResult.setType("S");
        if(!"0".equals(var120101.getErrNum())){
  			proResult.setType("E");
  	    }
        proResult.setTotNum(var120101.getTotNum());
        proResult.setErrNum(var120101.getErrNum());
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var120101InfoBean> infoList=var120101.getResult();
        Var120101Result result=new Var120101Result();
        result.setVar120101ProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var120101Result.class);
        clazzMap.put("info", Var120101InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
			
    }
	
	public String getTransName() {		
		return "票据存票";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "120101";
	}
}
