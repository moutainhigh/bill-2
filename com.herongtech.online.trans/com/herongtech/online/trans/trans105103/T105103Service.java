package com.herongtech.online.trans.trans105103;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.DraftConstants;
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
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;
/**
 * 可执行出票登记的票据查询
 */
public class T105103Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var105103 transVar = new Var105103();
		
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
	 * @param Var105103
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var105103 Var105103) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		RcBaseSearchBean rcsb=new RcBaseSearchBean();
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var105103.class);
        Var105103 temp=(Var105103)XmlBeanUtil.xml2Bean(clazzMap, request);
    	if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
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
		BeanUtils.copyProperties(temp, Var105103);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var105103
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var105103 Var105103) throws BizAppException{
    	String rgctIds = Var105103.getRgctIds();
    	int k = 0;
    	String [] ids =CommUtils.couvertLong(rgctIds);
    	List<Var105103InfoBean> result =new ArrayList<Var105103InfoBean>();
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var105103InfoBean bean=new Var105103InfoBean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcPresentationService().getRgctBillById(id);

       			SignProd signProd = null;
       			
       			ISignProdService signProdService= ServiceFactory.getSignProdService();
    			//检查客户是否电票签约
    			SignProd applySignProd;
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, Var105103.getCustAccount());
    				if(applySignProd==null){
    					throw new BizAppException("客户未签约");
    				}
    			}  
    			//bill.getHist().setToAcctNo(applicantAcctNo);// 签收人 帐号
    			bill.getHist().setToCustNo(signProd.getCustNo());// 签收人客户号
    			bill.getHist().setToName(signProd.getCustName());// 签收人名称
    			bill.getHist().setToRole(RcConstants.BUSSINESS_ROLE1);// 签收人 参与者类型
    			bill.getHist().setToOrgcode(signProd.getIdNumber());// 签收人 组织机构代码
    			bill.getHist().setSignDt(DateTimeUtil.getWorkdayString());// 签收日期
    			bill.getHist().setSignerSign(Var105103.getSignature());// 签收人电子签名
    			bill.getHist().setSignFlag(Var105103.getSignUpMark());// /签收标识
    			bill.getHist().setToRemark(Var105103.getRemark());// 签收人备注
    			
    			
    			if(Var105103.getRejectCode()!=null||!"".equals(Var105103.getRejectCode())){
    				bill.getHist().setRejectCode(Var105103.getRejectCode());
    				bill.getHist().setRejectReason(Var105103.getRejectReason());
    				bill.getHist().setSignFlag(RcConstants.SIGN_NO);
    			}else{
    				bill.getHist().setSignFlag(RcConstants.SIGN_YES);
    			}
//    			bill.getHist().setIsDelegate(AcptCodeConst.DELEGATE_NO_0);//非代理签收X
    			if (IDict.K_BILL_TYPE.K_BANK_BILL.equals(bill.getInfo().getBillType())){
    				return ;
    			}
//    			CorpBillInfo corpBill = xbankDao.queryCorpBillByBillNo(bill.getInfo().getBillNo());
//    			if(StringUtils.isNotEmpty(corpBill.getPayWaitOrder())){//上线行代理操作并且已经扣款挂帐的，不允许操作
//    				throw new BizAppException("该票据提示付款三天内未应答, 已经由银行代理签收!");
//    			}
    			
    			String curStatus = bill.getHist().getCurStatus();
    			if(!(curStatus.equals("R_08") || curStatus.equals("S_08"))){
    				throw new BizAppException("票据" + bill.getInfo().getBillNo() + "尚未收到提示付款申请，禁止做付款签收操作！");
    			}
    			if(DraftConstants.ECDS_SETTLEMENT_ONLINE.equals(bill.getHist().getIsOnline())){
    				if(!DraftConstants.ECDS_SETTLEMENT_ON_CN.equals(bill.getHist().getIsOnline())){
    					throw new BizAppException("大额线上清算窗口暂未开启，不支持线上清算!"); 
    				}
//    				//线上清算,签收则扣款挂帐并纪录挂帐订单
//    				ProResult result = XbankServiceHelper.corpBillDedecut(corpBill, deductService);
//    				if (!"S".equalsIgnoreCase(result.getType())) {
//    					throw new ServiceException(result.getMessage());
//    				}
//        			sendSignUpDraft(bill, true);
    				if (Var105103.getSignUpMark().equals(RcConstants.SIGN_YES)){
    	    			RcServiceFactory.getRcPresentationService().collectSign(bill);
    	    			}
    			}else{
    			if (Var105103.getSignUpMark().equals(RcConstants.SIGN_YES)){
    			RcServiceFactory.getRcPresentationService().collectSign(bill);
    			}
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
    	Var105103.setErrNum(String.valueOf(k));
    	Var105103.setTotNum(String.valueOf(result.size()));
    	Var105103.setResult(result);
    	
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var105103
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var105103 Var105103) throws BizAppException{
 	   TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
 	  T105103ProResult proResult=new T105103ProResult();
       proResult.setType("S");
       if(!"0".equals(Var105103.getErrNum())){
  			proResult.setType("E");
  	   }
       proResult.setExSerial(trans.getExSerial());
       proResult.setTotNum(Var105103.getTotNum());
       proResult.setErrNum(Var105103.getErrNum());
       proResult.setFunctionId(trans.getFunctionId());
       List<Var105103InfoBean> infoList=Var105103.getResult();
       Var105103Result result=new Var105103Result();
       result.setT105103ProResult(proResult);
       result.setResult(infoList);
       
       Map<String, Class> clazzMap = new HashMap<String, Class>();
       clazzMap.put("Document", Var105103Result.class);
       clazzMap.put("info", Var105103InfoBean.class);
      
       String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
       ContextUtil.setResponseData(context, resp);
      
}
	
	public String getTransName() {		
		return "付款签收";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "105103";
	}
}
