/********************************************
 * 文件名称: T106101Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 贴现申请
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-07-16
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans106101;

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
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.core.util.RequestCheckTools;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.disc.DiscBillInfoBean;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans101101.Var101101;
import com.herongtech.online.trans.trans101101.Var101101InfoBean;
import com.herongtech.online.trans.trans101101.Var101101Result;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 贴现申请
 */

public class T106101Service extends OnlineBaseService{


	/**
	 * 入口方法
	 * @param context
	 */
	@Override
	protected void action(Context context) throws Exception {
        Var106101 transVar = new Var106101();
		
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
	 * @param var106101
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var106101 var106101) throws BizAppException {
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var106101.class);
        clazzMap.put("bean", DiscBillInfoBean.class);
        Var106101 temp=(Var106101)XmlBeanUtil.xml2Bean(clazzMap, request);
        List<DiscBillInfoBean> billlist = temp.getBillList();
		for(int i = 0; i < billlist.size(); i++){
			if (StringUtils.isEmpty(billlist.get(i).getDiscType())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "贴现方式未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getDiscRate())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "贴现利率未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getDiscMoney())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "应付金额未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getSettlementMark())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "线上清算未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getInAcctNo())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "入账帐号未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getInBankNo())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "入账行号未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getApplicantAcctNo())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "申请人账号未上送");
			}
			if (temp.getApplyDate()!=billlist.get(i).getDiscDt()){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "贴现申请日未与贴现日统一");
			}
			if (StringUtils.isEmpty(billlist.get(i).getSignature())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子签名未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getRgctId())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据ID未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getBanEndorsementMark())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "不得转让标记未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getReceiverAcctNo())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "贴入人账号未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getReceiverBankNo())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "贴入人行号未上送");
			}
			if (StringUtils.isEmpty(billlist.get(i).getReceiverName())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "贴入人名称未上送");
			}
			if(RcConstants.DISCTYPE_ONE.equals(billlist.get(i).getDiscType())){
				if (StringUtils.isEmpty(billlist.get(i).getRpdOpenDt())){
					throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "赎回开放日未上送");
				}
				if (StringUtils.isEmpty(billlist.get(i).getRpdDueDt())){
					throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "赎回截止日未上送");
				}
				if (StringUtils.isEmpty(billlist.get(i).getRpdDiscRate())){
					throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "赎回利率未上送");
				}
				if (StringUtils.isEmpty(billlist.get(i).getRpdDiscAmt())){
					throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "赎回金额未上送");
				}
			}
			String inBankNo = billlist.get(i).getInBankNo();
		    String toBankNo = billlist.get(i).getReceiverBankNo();
		    RgctBillHist hist=RcServiceFactory.getRcRegBillService().getRgctBillById(billlist.get(i).getRgctId()).getHist();
		    String holdBankNo = hist.getHoldBankNo();
		    if(!StringUtils.contains(holdBankNo+"|"+toBankNo, inBankNo)){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL,"入账行号[inBankNo]必须是贴现人开户行[holdBankNo]与贴入行[toBankNo]二者之一");
			}
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var106101);
	}

	/**
	 * 查询处理
	 * @param context
	 * @param var106101
	 * @throws BizAppException
	 */
	protected void ToLocal(Context context, Var106101 var106101) throws BizAppException{
		List<Var106101InfoBean> result =new ArrayList<Var106101InfoBean>();
		List<DiscBillInfoBean> billList = var106101.getBillList();
		int k = 0;
		IDB session = DBFactory.getDB();
    	for (int i = 0; i < billList.size(); i++) {
    		Var106101InfoBean bean = new Var106101InfoBean();
    		try {
    			session.beginTransaction();
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(billList.get(i).getRgctId())==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcRegBillService().getRgctBillById(billList.get(i).getRgctId());
    			String billClass=bill.getInfo().getBillClass();
    			if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(billClass)){
    				RequestCheckTools.checkIsNull("receiverName",billList.get(i).getReceiverName());
    				RequestCheckTools.checkIsNull("receiverAcctNo",billList.get(i).getReceiverAcctNo());
    				String receiverBankNo = billList.get(i).getReceiverBankNo();
    				RequestCheckTools.checkIsNull("receiverBankNo",billList.get(i).getReceiverBankNo());
    				EcdsBankData draweeBank=RcServiceFactory.getEcdsBankDataService().getEcdsBankData(receiverBankNo);
    				if(draweeBank==null){
    					throw new BizAppException("根据贴入行行号没有找到对应行信息！");
    				}
    				RequestCheckTools.checkIsNull("discMoney",billList.get(i).getDiscMoney());
    				RequestCheckTools.checkIsNull("discRate",billList.get(i).getDiscRate());
    				RequestCheckTools.checkIsNull("discType",billList.get(i).getDiscType());
    				if(RcConstants.DISCTYPE_ONE.equals(billList.get(i).getDiscType())){
    					/* 20120922-01-回购式贴现判断赎回起始、截止日不能为空 */
    					RequestCheckTools.checkIsNull("rpdOpenDt",billList.get(i).getRpdOpenDt());
    					RequestCheckTools.checkIsNull("rpdDueDt",billList.get(i).getRpdDueDt());
    					RequestCheckTools.checkIsNull("rpdDiscRate",billList.get(i).getRpdDiscRate());
    					RequestCheckTools.checkIsNull("rpdDiscAmt",billList.get(i).getRpdDiscAmt());
    				}
    				RequestCheckTools.checkIsNull("inAcctNo",billList.get(i).getInAcctNo());
    				RequestCheckTools.checkBankNo("inBankNo",billList.get(i).getInBankNo());
    				RequestCheckTools.checkIsNull("banEndorsementMark",billList.get(i).getBanEndorsementMark());
    				RequestCheckTools.checkIsNull("settlementMark",billList.get(i).getSettlementMark());
    				if(RcConstants.SETTLEMENTMARK_ONE.equals(billList.get(i).getSettlementMark())){
    					//if(MsgUtil.(receiverBankNo)){
    						throw new BizAppException("贴现交易不支持线上清算");
    					//}
    				}
    				String sDiscRate = MathScaleUtil.round(billList.get(i).getDiscRate(), 6);
    				if (MathScaleUtil.compareTo(sDiscRate, "1.0", 6)>0 || MathScaleUtil.compareTo(sDiscRate, "0.0", 6)<0) {
    					throw new BizAppException("贴现利率应大于等于0且小于等于1");
    				}
    			}
    			RgctBillHist hist = bill.getHist();
    			final String name = hist.getToName();
    			hist.setFromName(name);
    			if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getInfo().getBillClass())){
    				final String acctNo = hist.getToAcctNo();
    				final String bankNo = hist.getToBankNo();
    				final String custNo = hist.getToCustNo();
    				//检查客户是否电票签约
    				ISignProdService signProdService = ServiceFactory.getSignProdService();
    				SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD,billList.get(i).getApplicantAcctNo());	
    				if(signProd==null){
    					throw new BizAppException("客户未签约");
    				}
    				hist.setFromCustNo(custNo);
    				hist.setFromAcctNo(acctNo);
    				hist.setFromBankNo(bankNo);
    				hist.setFromOrgcode(signProd.getIdNumber());
    				hist.setFromRole(RcConstants.BUSSINESS_ROLE1);
    				hist.setIsOnline(billList.get(i).getSettlementMark());
    				hist.setInAcctNo(billList.get(i).getInAcctNo());
    				hist.setInBankNo(billList.get(i).getInBankNo());
    				//临时借用info的以下字段传值
    				/*if(!StringUtils.isEmpty(map.get("invoiceNo"))){
    					bill.getInfo().setCheckResult(map.get("invoiceNo"));
    				}
    				if(!StringUtils.isEmpty(map.get("conferNo"))){
    					bill.getInfo().setBlackList(map.get("conferNo"));
    				}*/
    				hist.setBatchId(billList.get(i).getBatchNo());
    				hist.setForbidFlag(billList.get(i).getBanEndorsementMark());
    				hist.setIsRegress(billList.get(i).getDiscType());
    				if(RcConstants.DISCTYPE_ONE.equals(billList.get(i).getDiscType())){
    					hist.setBackOpenDt(billList.get(i).getRpdOpenDt());
    					hist.setBackEndDt(billList.get(i).getRpdDueDt());
    					hist.setBackRate(Double.valueOf(billList.get(i).getRpdDiscRate()));
    					hist.setBackAmount(Double.valueOf(billList.get(i).getRpdDiscAmt()));
    				}
    			}
    			hist.setInterestRate(MathScaleUtil.round(Double.valueOf(billList.get(i).getDiscRate()),6));
    			hist.setDealMoney(MathScaleUtil.round(Double.valueOf(billList.get(i).getDiscMoney()),4));
    			hist.setEndorseDt(var106101.getApplyDate());
    			hist.setToName(billList.get(i).getReceiverName());
    			hist.setToAcctNo(billList.get(i).getReceiverAcctNo());
    			hist.setToBankNo(billList.get(i).getReceiverBankNo());
    			hist.setFromSign(billList.get(i).getSignature());
    			RcServiceFactory.getRcDiscService().submitBuyApply(bill);
    			bean.setRgctId(billList.get(i).getRgctId());
    			bean.setIsSuccess("S");
    			session.endTransaction();
    		} catch (Exception e) {
    			bean.setRgctId(billList.get(i).getRgctId());
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
    	var106101.setErrNum(String.valueOf(k));
    	var106101.setTotNum(String.valueOf(billList.size()));
    	var106101.setResult(result);
    	
    	
	}

	 /**
     * 应答包处理
     * @param context
     * @param Var106101
     * @throws BizAppException
     */
	protected void PackAnswer(Context context, Var106101 var106101) throws BizAppException{
		TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
		Var106101ProResult proResult = new Var106101ProResult();
		proResult.setType("S");
		if(!"0".equals(var106101.getErrNum())){
  			proResult.setType("E");
  	    }
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        proResult.setErrNum(var106101.getErrNum());
        proResult.setTotNum(var106101.getTotNum());
        List<Var106101InfoBean> infoList = var106101.getResult();
        Var106101Result result = new Var106101Result();
        result.setVar106101ProResult(proResult);
        result.setResult(infoList);
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var106101Result.class);
        clazzMap.put("info", Var106101InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
	}

	
	@Override
	public String getServiceId() {
		return "106101";
	}
	
	@Override
	public String getTransName() {
		return "贴现申请";
	}

	@Override
	public String getTransVersion() {
		return "2.0.0.1";
	}

}
