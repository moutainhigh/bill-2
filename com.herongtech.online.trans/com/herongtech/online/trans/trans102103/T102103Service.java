/********************************************
 * 文件名称: T102103Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 承兑回复(签收或拒绝)
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzx
 * 开发时间: 2016-08-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans102103;

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
import com.herongtech.online.trans.common.CommonInfoBean;
import com.herongtech.online.trans.common.CommonResult;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;


/**
 * 承兑回复(签收或拒绝)
 */
public class T102103Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var102103 transVar = new Var102103();
		
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
	 * @param Var102103
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var102103 var102103) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var102103.class);
        Var102103 temp=(Var102103)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "登记中心票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "网银电子签名未上送");
		}
		if (StringUtils.isEmpty(temp.getSignUpMark())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "签收标识ID未上送");
		}
		var102103.setCustAccount(temp.getCustAccount());
		var102103.setRgctIds(temp.getRgctIds());
		var102103.setSignature(temp.getSignature());	
		var102103.setSignUpMark(temp.getSignUpMark());	
		

	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var102103
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var102103 var102103) throws BizAppException{
    	
    	String signUpMark = var102103.getSignUpMark();
    	String [] ids =CommUtils.couvertLong(var102103.getRgctIds());
    	List<CommonInfoBean> result = new ArrayList<CommonInfoBean>();
    	IDB db = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		CommonInfoBean bean = new CommonInfoBean();
    		try{
    			db.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill = RcServiceFactory.getRcAcptBillService().getRgctBillById(id);
    			SignProd signProd = null;
    			ISignProdService signProdService = ServiceFactory.getSignProdService();
    			String CustAccount = var102103.getCustAccount();
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ENTY_BILL)) {
    				signProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, var102103.getCustAccount());
    			} else if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var102103.getCustAccount());
    			}
    			if (signProd == null) {
    				throw new Exception("客户未签约");
    			}
    			bill.getHist().setToAcctNo(var102103.getCustAccount());// 签收人 帐号
    			bill.getHist().setToCustNo(signProd.getCustNo());// 签收人客户号
    			bill.getHist().setToName(signProd.getCustName());// 签收人名称
    			bill.getHist().setToRole(RcConstants.BUSSINESS_ROLE1);// 签收人 参与者类型
    			bill.getHist().setToOrgcode(signProd.getIdNumber());// 签收人 组织机构代码
    		//	bill.getHist().setSignDt("2016-08-16");// 签收日期
    			bill.getHist().setSignDt(DateTimeUtil.getWorkdayString());
    			bill.getHist().setSignerSign(var102103.getSignature());// 签收人电子签名
    			bill.getHist().setSignFlag(var102103.getSignUpMark());// /签收标识
    			bill.getHist().setToRemark(var102103.getRemark());// 签收人备注
    			
  	    	   bill.getHist().setSigner(var102103.getSignature());
  	    	
   	    	   if(var102103.getPayRefuseCode()!=null||!"".equals(var102103.getPayRefuseCode())){
	   				bill.getHist().setRejectCode(var102103.getPayRefuseCode());
	   				bill.getHist().setRejectReason(var102103.getRejectReason());
    				bill.getHist().setSignFlag(RcConstants.SIGN_NO);
   			    }else{
    				bill.getHist().setSignFlag(RcConstants.SIGN_YES);
    			}
    			/*bill.getHist().setIsDelegate(AcptCodeConst.DELEGATE_NO_0);*/
    	    	
    	    	if(signUpMark.equals(RcConstants.SIGN_NO)){
    	    	RcServiceFactory.getRcAcptBillService().acptBack(bill);
    	    	}else if(signUpMark.equals(RcConstants.SIGN_YES)){
    	    	RcServiceFactory.getRcAcptBillService().acptSign(bill);
    	    	}
    	    	bean.setRgctId(ids[i]);
    	    	bean.setIsSuccess("S");
    	    	bean.setErrMsg("交易成功");
        		db.endTransaction();
    		}catch(Exception e){
    			bean.setRgctId(ids[i]);
    	    	bean.setIsSuccess("E");
    	    	bean.setErrMsg(e.getMessage());
    			try {
					db.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
    			e.printStackTrace();
    		}
    		
    		result.add(bean);
		}
    	var102103.setResult(result);
    }

    
    /**
     * 应答包处理
     * @param context
     * @param Var102103
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var102103 var102103) throws BizAppException{
    	CommonResult resp = new CommonResult();
    	int errNum = 0;
    	int totNum = 0;
        List<CommonInfoBean> result=var102103.getResult();
        if(result != null){
        	totNum = result.size();
        	for(int i = 0; i < result.size(); i++){
        		CommonInfoBean bean = result.get(i);
        		if("E".equals(bean.getIsSuccess())){
        			errNum ++;
        		}
        	}
        }
		
		resp.setResult(result);
		resp.setTotNum(Integer.toString(totNum));
		resp.setErrNum(Integer.toString(errNum));
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", CommonResult.class);
        clazzMap.put("info", CommonInfoBean.class);
        String response=XmlBeanUtil.bean2xml(clazzMap, resp);
		ContextUtil.setResponseData(context,response);
    }
	
	public String getTransName() {		
		return "承兑回复(签收或拒绝)";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "102103";
	}
}
