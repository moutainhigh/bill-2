/********************************************
 * 文件名称: T101104Service.java
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
package com.herongtech.online.trans.trans101104;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans101102.Var101102;
import com.herongtech.online.trans.trans101102.Var101102InfoBean;
import com.herongtech.online.trans.trans101102.Var101102ProResult;
import com.herongtech.online.trans.trans101102.Var101102Result;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcBillNotifyService;
import com.herongtech.rc.service.rcservice.RcBillNotifyService;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 提示收票回复(签收或拒绝)
 */
public class T101104Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var101104 transVar = new Var101104();
		
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
	 * @param Var101104
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var101104 var101104) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var101104.class);
        Var101104 temp=(Var101104)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "申请人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getSignUpMark())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "签收标识未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "登记中心票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "网银电子签名未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var101104);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var101104
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var101104 var101104) throws BizAppException{
    	String rgctIds = var101104.getRgctIds();
    	String signUpMark = var101104.getSignUpMark();
    	String [] ids =CommUtils.couvertLong(rgctIds);
    	List<Var101104InfoBean> result =new ArrayList<Var101104InfoBean>();
    	int k = 0;
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var101104InfoBean bean = new Var101104InfoBean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcRegBillService().getRgctBillById(id);
    			ISignProdService signProdService = ServiceFactory.getSignProdService();
    			SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD,var101104.getCustAccount());
    			if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ENTY_BILL)) {
    				signProd = signProdService.getSignProdByBusAct(IConstants.BILLPOOL_SINGPROD, var101104.getCustAccount());
				} else if (bill.getInfo().getBillClass().equals(IDict.K_BILL_CLASS.K_ELEC_BILL)) {
					signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var101104.getCustAccount());
				}
    			if(signProd==null){
					throw new BizAppException("客户未签约");
				}
    			//bill.getHist().setToAcctNo(applicantAcctNo);// 签收人 帐号
    			bill.getHist().setToCustNo(signProd.getCustNo());// 签收人客户号
    			bill.getHist().setToName(signProd.getCustName());// 签收人名称
    			bill.getHist().setToRole(RcConstants.BUSSINESS_ROLE1);// 签收人 参与者类型
    			bill.getHist().setToOrgcode(signProd.getIdNumber());// 签收人 组织机构代码
    			bill.getHist().setSignDt(DateTimeUtil.getWorkdayString());// 签收日期
    			//bill.getHist().setSignDt(MsgUtil.getEcdsCurrentDate());// 签收日期
    			bill.getHist().setSignerSign(var101104.getSignature());// 签收人电子签名
    			bill.getHist().setSignFlag(signUpMark);// /签收标识
    			//bill.getHist().setToRemark(sign.getRemark());// 签收人备注
    		
    			if (RcConstants.SIGN_YES.equals(signUpMark)) {
    				RcServiceFactory.getRcBillNotifyService().payeeSign(bill);
				} else {
					RcServiceFactory.getRcBillNotifyService().payeeReject(bill);
				}
    			bean.setRgctId(id);
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
    	
    	for (int i = 0; i < result.size(); i++) {
			if("E".equals(result.get(i).getIsSuccess())){
				k=k+1;
			}
		
	}
    	var101104.setErrNum(String.valueOf(k));
    	var101104.setTotNum(String.valueOf(result.size()));
    	var101104.setResultList(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var101104
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var101104 var101104) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
		Var101104ProResult proResult = new Var101104ProResult();
		proResult.setType("S");
		if(!"0".equals(var101104.getErrNum())){
			proResult.setType("E");
		}
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        proResult.setErrNum(var101104.getErrNum());
        proResult.setTotNum(var101104.getTotNum());
        List<Var101104InfoBean> infoList = var101104.getResultList();
        Var101104Result result = new Var101104Result();
        result.setVar101104ProResult(proResult);
        result.setResult(infoList);
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var101104Result.class);
        clazzMap.put("info", Var101104InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "提示收票回复";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "101104";
	}
}
