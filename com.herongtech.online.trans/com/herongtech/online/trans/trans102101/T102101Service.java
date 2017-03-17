/********************************************

 * 文件名称: T102101Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 提示承兑服务
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzx
 * 开发时间: 2016-08-09
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans102101;

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
 * 提示承兑服务
 */
public class T102101Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var102101 transVar = new Var102101();
		
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
	 * @param Var102001
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var102101 var102101) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var102101.class);
        Var102101 temp=(Var102101)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "登陆中心票据ID未上送");
		}
		
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "网银电子签名未上送");
		} 
		
		var102101.setCustAccount(temp.getCustAccount());
		var102101.setRgctIds(temp.getRgctIds());
		var102101.setSignature(temp.getSignature());
		var102101.setRemark(temp.getRemark());
		var102101.setInvoiceNo(temp.getInvoiceNo());
		var102101.setBatchNo(temp.getBatchNo());
		
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var102101
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var102101 var102101) throws BizAppException{
    	String [] ids =CommUtils.couvertLong(var102101.getRgctIds());
    	List<CommonInfoBean> result =new ArrayList<CommonInfoBean>();
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
                if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ENTY_BILL)) {
                	signProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, var102101.getCustAccount());
   			    } else if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
   			    	signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var102101.getCustAccount());
   			    }
   			    if(signProd==null){
   				    throw new Exception("客户未签约");
   			    }
    			org.springframework.util.Assert.isTrue(var102101.getCustAccount().equals(bill.getHist().getHoldAcctNo()),"发起人帐号" + var102101.getCustAccount()
    						+ "与持票人帐号" + bill.getHist().getHoldAcctNo() + "不一致,请检查");
    			bill.getHist().setFromAcctNo(var102101.getCustAccount());//申请人 账号
    			bill.getHist().setFromSign(var102101.getSignature());//申请人 电子签名
    			bill.getHist().setFromBankNo(bill.getHist().getHoldBankNo());//申请人 行号
				bill.getHist().setEndorseDt(DateTimeUtil.getEcds_YYYY_MM_DD_Date());//申请 日期
    			bill.getHist().setFromCustNo(signProd.getCustNo());//申请人客户号
    			bill.getHist().setFromRole(RcConstants.BUSSINESS_ROLE1);//申请人参与者类型
    			bill.getHist().setFromOrgcode(signProd.getIdNumber());//申请人组织机构代码
    			bill.getHist().setFromName(signProd.getCustName());//申请人名称
    			bill.getInfo().setRemitterRemark(var102101.getRemark());//出票人备注
    			bill.getInfo().setRemitterSign(var102101.getSignature());//出票人电子签名
    			bill.getHist().setBatchId(var102101.getBatchNo());//批次号
    			bill.getHist().setToAcctNo(bill.getInfo().getAcceptorAcct());//承兑人账号
         		bill.getHist().setToBankNo(bill.getInfo().getAcceptorBankNo());// 承兑人行号
         		bill.getHist().setToName(bill.getInfo().getAcceptor());//承兑人名称
    	    	bill.getHist().setSigner(var102101.getSignature());
    	    	RcServiceFactory.getRcAcptBillService().acceptanceRequest(bill);
    			bean.setRgctId(ids[i]);
    			bean.setIsSuccess("S");
        		db.endTransaction();
    		}catch(Exception e){
    			e.printStackTrace();
    			bean.setRgctId(ids[i]);
    			bean.setIsSuccess("E");
    			bean.setErrMsg(e.getMessage());
    			try {
    				db.rollback();
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    			}
    			
    		}
    		result.add(bean);
		}
    	var102101.setResult(result);
    }
    	
   
    
    /**
     * 应答包处理
     * @param context
     * @param Var102101
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var102101 Var102101) throws BizAppException{
    	CommonResult resp = new CommonResult();
    	int errNum = 0;
    	int totNum = 0;
        List<CommonInfoBean> result=Var102101.getResult();
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
		
		ContextUtil.setResponseData(context, response);
    }
	
	public String getTransName() {		
		return "提示承兑服务";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "102101";
	}
}
