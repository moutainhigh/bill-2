/********************************************
 * 文件名称: T102102Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 撤销提示承兑服务
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzx
 * 开发时间: 2016-08-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans102102;

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
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.common.CommonInfoBean;
import com.herongtech.online.trans.common.CommonResult;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;


/**
 * 撤销提示承兑服务
 */
public class T102102Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var102102 transVar = new Var102102();
		
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
	 * @param Var102102
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var102102 var102102) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var102102.class);
        Var102102 temp=(Var102102)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "登记中心票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "网银电子签名未上送");
		}
		var102102.setCustAccount(temp.getCustAccount());
		var102102.setRgctIds(temp.getRgctIds());
		var102102.setSignature(temp.getSignature());
		
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var102102
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var102102 var102102) throws BizAppException{
    	
    	String [] ids =CommUtils.couvertLong(var102102.getRgctIds());
    	List<CommonInfoBean> result = new ArrayList<CommonInfoBean>();
    	for (int i = 0; i < ids.length; i++) {
    		CommonInfoBean bean = new CommonInfoBean();
    		try{  			
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill = RcServiceFactory.getRcAcptBillService().getRgctBillById(id);
    	    	bill.getHist().setSigner(var102102.getSignature());
    	    	//检查客户是否电票签约
    	    	ISignProdService signProdService = ServiceFactory.getSignProdService();
    			SignProd applySignProd;
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
    				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var102102.getCustAccount());
    				if(applySignProd==null){
    					throw new BizAppException("客户未签约");
    				}
    			}  	    	
//    	    	if(!RcConstants.COMMON_GUARNT.equals(Var102102.getType())){
//    	    		Assert.notNull(Var102102.getSignature(), "网银撤销交易校验：申请人电子签名为空");
//    	    		Assert.notNull(Var102102.getApplyDate(), "网银撤销交易校验：申请日期为空");
//    	    		Assert.isTrue(Var102102.getApplicantAcctNo().equals(bill.getHist().getHoldAcctNo()), 
//        				"发起人帐号"+Var102102.getApplicantAcctNo()+"与持票人帐号"+bill.getHist().getHoldAcctNo()+"不一致,请检查");*/
//   	    	 	bill.getHist().setFromSign(Var102102.getSignature());//申请人 电子签名
//   	    		bill.getHist().setEndorseDt(DateTimeUtil.getWorkdayString());//申请 日期
   	    		

	   	 		String cur_status = bill.getHist().getCurStatus();
	   	 		if("B_11".equals(cur_status)){
	   	 			throw new BizAppException("该票的当前状态不支持该处理！");
	   	 		}
	   	 		RcServiceFactory.getRcAcptBillService().cancelRequest(bill);
	   	 		bean.setRgctId(ids[i]);
	   	 		bean.setIsSuccess("S");
	   	 		bean.setErrMsg("交易成功");
    		}catch(Exception e){
    			bean.setRgctId(ids[i]);
	   	 		bean.setIsSuccess("E");
	   	 		bean.setErrMsg(e.getMessage());
    		}
    		result.add(bean);
		}
    	var102102.setResult(result);
    	
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var102102
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var102102 var102102) throws BizAppException{
    	CommonResult resp = new CommonResult();
    	int errNum = 0;
    	int totNum = 0;
        List<CommonInfoBean> result=var102102.getResult();
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
		return "撤销提示承兑服务";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "102102";
	}
}
