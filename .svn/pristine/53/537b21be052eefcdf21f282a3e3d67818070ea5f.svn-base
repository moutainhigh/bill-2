/********************************************
 * 文件名称: T106102Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 贴现撤回
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-07-16
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans106102;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
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
import com.herongtech.online.trans.trans101101.Var101101InfoBean;
import com.herongtech.online.trans.trans101101.Var101101Result;
import com.herongtech.online.trans.trans106002.Var106002;
import com.herongtech.online.trans.trans106101.Var106101;
import com.herongtech.online.trans.trans106101.Var106101InfoBean;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 贴现撤回
 */

public class T106102Service extends OnlineBaseService{

	/**
	 * 入口方法
	 * @param context
	 */
	@Override
	protected void action(Context context) throws Exception {
		Var106102 transVar = new Var106102();
		
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
	 * @param var106102
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var106102 var106102) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var106102.class);
        Var106102 temp = (Var106102)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "申请人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getSignature())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子签名未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var106102);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param var106102
	 * @throws BizAppException
	 */
	protected void ToLocal(Context context, Var106102 var106102) throws BizAppException{
        List<Var106102InfoBean> result =new ArrayList<Var106102InfoBean>();
        int k=0;
        String ids=var106102.getRgctIds();
		String[] s=ids.split(",");
    	IDB session = DBFactory.getDB();
		for (int i = 0; i < s.length; i++) {
			Var106102InfoBean bean = new Var106102InfoBean();
		try {
			session.beginTransaction();
			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(s[i])==null){
				throw new BizAppException("登记中心ID检查失败");
			}
			RgctBill bill=RcServiceFactory.getRcRegBillService().getRgctBillById(s[i]);
			//检查客户是否电票签约
			ISignProdService signProdService = ServiceFactory.getSignProdService();
			SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD,var106102.getCustAccount());	
			if(signProd==null){
				throw new BizAppException("客户未签约");
			}
			bill.getHist().setFromSign(var106102.getSignature());
			bill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
			RcServiceFactory.getRcDiscService().cancelBuyApply(bill);
			bean.setRgctId(s[i]);
			bean.setIsSuccess("S");
			/*if(!"guarnt".equals()){
				Assert.notNull(req.getSignature(), "网银撤销交易校验：申请人电子签名为空");
				Assert.notNull(req.getApplyDate(), "网银撤销交易校验：申请日期为空");
				Assert.isTrue(req.getApplicantAcctNo().equals(bill.getHist().getHoldAcctNo()), 
						"发起人帐号"+req.getApplicantAcctNo()+"与持票人帐号"+bill.getHist().getHoldAcctNo()+"不一致,请检查");
				bill.getHist().setFromSign(req.getSignature());//申请人 电子签名
				bill.getHist().setEndorseDt(req.getApplyDate());//申请 日期
			}*/
			session.endTransaction();
		} catch (Exception e) {
			bean.setRgctId(s[i]);
            bean.setIsSuccess("E");
            bean.setErrMsg(e.getMessage());
			try {
				session.rollback();
			} catch (Exception e2) {
				
				e2.printStackTrace();
			}
			
		
		}
		result.add(bean);
		}
		for (int i = 0; i < result.size(); i++) {
				if("E".equals(result.get(i).getIsSuccess())){
					k=k+1;
				}
		}
		var106102.setResultList(result);
		var106102.setErrNum(String.valueOf(k));
		var106102.setTotNum(String.valueOf(s.length));
	}
	
	/**
     * 应答包处理
     * @param context
     * @param var106102
     * @throws BizAppException
     */
	protected void PackAnswer(Context context, Var106102 var106102) throws BizAppException{
		TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
		Var106102ProResult proResult = new Var106102ProResult();
		proResult.setType("S");
		if(!"0".equals(var106102.getErrNum())){
  			proResult.setType("E");
  	    }
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        proResult.setErrNum(var106102.getErrNum());
        proResult.setTotNum(var106102.getTotNum());
        List<Var106102InfoBean> infoList = var106102.getResultList();
        Var106102Result result = new Var106102Result();
        result.setVar106102ProResult(proResult);
        result.setResult(infoList);
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var106102Result.class);
        clazzMap.put("info", Var106102InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
		
	}



	@Override
	public String getTransName() {
		return "贴现撤回";
	}

	@Override
	public String getTransVersion() {
		return "2.0.0.1";
	}

	@Override
	public String getServiceId() {
		return "106102";
	}
}
