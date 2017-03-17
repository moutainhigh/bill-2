
package com.herongtech.online.trans.trans105101;

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
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 可执行出票登记的票据查询
 */
public class T105101Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var105101 transVar = new Var105101();
		
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
	 * @param Var105101
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var105101 var105101) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		RcBaseSearchBean rcsb=new RcBaseSearchBean();
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var105101.class);
        Var105101 temp=(Var105101)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子签名未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getOverdueMark())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "逾期标记未上送");
		}
		if (StringUtils.isEmpty(temp.getSettlementMark())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "线上清算标记未上送");
		}

		
		if ("1".equals(temp.getOverdueMark())) {
			if (StringUtils.isEmpty(temp.getOverDueReason())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "逾期原因说明未上送");
			}
		}
		BeanUtils.copyProperties(temp, var105101);
		}

	
		

	
	/**
	 * 查询处理
	 * @param context
	 * @param Var105101
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var105101 Var105101) throws BizAppException{
    	int k=0;
    	String rgctIds = Var105101.getRgctIds();
    	String [] ids =CommUtils.couvertLong(rgctIds);
    	List<Var105101InfoBean> result =new ArrayList<Var105101InfoBean>();
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var105101InfoBean bean=new Var105101InfoBean();
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
    				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, Var105101.getCustAccount());
    				if(applySignProd==null){
    					throw new BizAppException("客户未签约");
    				}
    			}  
    			org.springframework.util.Assert.isTrue(Var105101.getCustAccount().equals(bill.getHist().getHoldAcctNo()),"发起人帐号" + Var105101.getCustAccount()
    						+ "与持票人帐号" + bill.getHist().getHoldAcctNo() + "不一致,请检查");
    			
    			
    			bill.getHist().setFromAcctNo(Var105101.getCustAccount());//申请人 账号
    			bill.getHist().setFromSign(Var105101.getSignature());//申请人 电子签名
    			bill.getHist().setEndorseDt(DateTimeUtil.getWorkdayString());//申请 日期、、、、、日期是自己输入的没有调用函数
    			bill.getHist().setFromBankNo(bill.getHist().getHoldBankNo());//申请人 行号
    			bill.getHist().setFromCustNo(signProd.getCustNo());//申请人客户号
    			bill.getHist().setFromRole(RcConstants.BUSSINESS_ROLE1);//申请人参与者类型
    			bill.getHist().setFromOrgcode(signProd.getIdNumber());//申请人组织机构代码
    			bill.getHist().setFromName(signProd.getCustName());//申请人名称
    			
    			bill.getHist().setIsOnline(Var105101.getSettlementMark());
    			bill.getHist().setFromRemark(Var105101.getRemark());
    			bill.getHist().setIsDelegate("0");
    			bill.getHist().setOverdueRs(Var105101.getOverDueReason());
    			
    			bill.getHist().setToAcctNo(bill.getInfo().getAcceptorAcct());
    			bill.getHist().setToBankNo(bill.getInfo().getAcceptorBankNo());
    			bill.getHist().setToName(bill.getInfo().getAcceptor());
    			
    			
    			
    			
    			RcServiceFactory.getRcPresentationService().payEndorse(bill);
//    			待定Rc方法判断
//    			if (!CommUtils.isOverDueWithHolidayPostPone(DateTimeUtil.parseStringToDate(bill.getInfo().getDueDt()))) {
//    				// 未逾期
//    				RcServiceFactory.getRcPresentationService().payEndorse(bill);
//    			} else { 
//    				// 逾期
//    				RcServiceFactory.getRcPresentationService().;
//    			}
    			
    			
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
    	Var105101.setErrNum(String.valueOf(k));
    	Var105101.setTotNum(String.valueOf(result.size()));
    	Var105101.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var105101
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var105101 Var105101) throws BizAppException{
    	   TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	   T105101ProResult proResult=new T105101ProResult();
           proResult.setType("S");
           if(!"0".equals(Var105101.getErrNum())){
   			proResult.setType("E");
   			}
           proResult.setTotNum(Var105101.getTotNum());
           proResult.setErrNum(Var105101.getErrNum());
           proResult.setExSerial(trans.getExSerial());
           proResult.setFunctionId(trans.getFunctionId());
           List<Var105101InfoBean> infoList=Var105101.getResult();
           Var105101Result result=new Var105101Result();
           result.setT105101ProResult(proResult);
           result.setResult(infoList);
           
           Map<String, Class> clazzMap = new HashMap<String, Class>();
           clazzMap.put("Document", Var105101Result.class);
           clazzMap.put("info", Var105101InfoBean.class);
          
           String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
           ContextUtil.setResponseData(context, resp);
          
    }
	
	public String getTransName() {		
		return "提示付款申请";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "105101";
	}
}
