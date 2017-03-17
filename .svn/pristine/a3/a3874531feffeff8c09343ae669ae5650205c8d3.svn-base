/********************************************
 * 文件名称: DraftDrwrWrapper.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.rgctbill;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.bean.EcdsBillBean;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IDraftDrwrWrapper;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRcRegBillService;

public class DraftDrwrWrapper implements IDraftDrwrWrapper{
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";

	public void deleteDraftEcdsRequest(Long rgctId) throws BizAppException {
		/*AcptMateBill mateBill = new AcptMateBill();
		mateBill = acptMateBillDao.loadAcptMateBillByRgctId(rgctId);
		if(mateBill!=null){
			mateBill.setStatus("9");
			acptMateBillDao.updateAcptMateBill(mateBill);
		}
		rcRegBillService.delete(rgctId);*/	
	}

	public void checkNewBillDraft(TransPub transPub) throws BizAppException {
		
		EcdsBillBean bean = transPub.getEcdsBillBean();
		RgctBillInfo info = new RgctBillInfo();
		RgctBillHist hist = new RgctBillHist();
		RgctBill bill = new RgctBill(info, hist);
		
		//删除纸票处理逻辑
		/*if("1".equals(bean.getBillClass())){
			bill = dealnewRgctEntityBill(bill,bean);
		}else{*/
		//}
		//对象转换
		ServiceFactory.getTransPubService().ecdsBill2BillInfo(bean, info);
		
		info.setAcceptorAcct(bean.getAcceptorAcct());
		info.setRemitterBankName(bean.getRemitterBank());
		info.setAcceptorBankName(bean.getAcceptorBank());
		info.setPayeeBankName(bean.getPayeeBank());
		info.setIssueDt(bean.getIssueDt());
		info.setDueDt(bean.getDueDt());		
		info.setPayeeName(bean.getPayee());
		info.setPayeeBankNo(bean.getPayeeBankNo());
		info.setPayeeName(bean.getPayee());
		info.setBillMoney(Double.parseDouble(bean.getBillMoney()));

		if (info.getIssueDt().compareTo(info.getDueDt()) > 0) {
			throw new BizAppException(IErrorNo.BBSP0112, "清单出票日必须小于票面到期日");
		}
		if (DateTimeUtil.isOver12MonthInterval(info.getIssueDt(), info.getDueDt()) ) {
			throw new BizAppException(IErrorNo.BBSP0112, "票面到期日不能超过一年");
		}

		EcdsBankData draweeBank = RcServiceFactory.getEcdsBankDataService().getEcdsBankData(info.getAcceptorBankNo());
		if (draweeBank==null){
			throw new BizAppException(IErrorNo.BBSP0112, "根据AcceptorBankNo没有找到对应行信息！");
		}
		if (IDict.K_ECDS_CERT_STATUS.K_ECDS_CERT_STATUS_NO.equals(draweeBank.getCertBindStatus())){
			throw new BizAppException(IErrorNo.BBSP0112, "承兑行" + draweeBank.getActorFullCall() + "尚未开通电子票据权限!");
		}
		info.setDraweeBankName(draweeBank.getActorFullCall());
		info.setDraweeAddr(draweeBank.getAddress());
		info.setDraweeBankNo(draweeBank.getRowNumber());
		info.setBillNo(null);
		info.setInfoForbidFlag(bean.getBanEndorsementMark());
		info.setInvoiceNo(bean.getInvoiceNo());
		info.setConferNo(bean.getConferNo());
		
		ISignProdService signProdService = ServiceFactory.getSignProdService();
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getInfo().getBillClass())){
			
			SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, bean.getRemitterAcct());
			if(signProd==null){
				throw new BizAppException(IErrorNo.BBSP0112, "出票人未做电票签约");
			}
			info.setRemitterCustNo(signProd.getCustNo());
			info.setRemitterOrgCode(signProd.getIdNumber());
			info.setRemitterRole(IDict.K_BUSSINESS_ROLE.BUSSINESS_ROLE1);
		}
		
		if(IDict.K_BILL_TYPE.K_BANK_BILL.equals(bean.getBillType()) && CommUtils.isSelfBank(bean.getAcceptorBankNo()) && !"0".equals(bean.getAcceptorAcct())){
			throw new BizAppException(IErrorNo.BBSP0112, "银承承兑人为民生银行时，承兑人账号应当为0");
		}
		
		//检查商承承兑人账号和账号开户行关系是否一致
		if(IDict.K_BILL_TYPE.K_CORP_BILL.equals(bean.getBillType()) && CommUtils.isSelfBank(bean.getAcceptorBankNo())){
			//SignProd signProd = signProdService.searchSignProdByAct(IConstants.ELECTRON_SINGPROD, bean.getAcceptorAcct());
			SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, bean.getAcceptorAcct());
			if (signProd==null){
				throw new BizAppException(IErrorNo.BBSP0112, "票面承兑人未做电票签约");
			}
			if (!bean.getAcceptorBankNo().equals(signProd.getRemitterBankNo())){
				throw new BizAppException(IErrorNo.BBSP0112, "票面承兑人帐号和帐号开户机构对应关系不一致");
			}
			if (!signProd.getCustName().equals(bean.getAcceptor())){
				throw new BizAppException(IErrorNo.BBSP0112,"承兑人名称和客户签约名称不一致");
			}
		}
		
		//检查收款人账号和账号开户行关系是否一致
		if(CommUtils.isSelfBank(bean.getPayeeBankNo())){
			//SignProd signProd = signProdService.searchSignProdByAct(IConstants.ELECTRON_SINGPROD, bean.getPayeeAcct());
			SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, bean.getPayeeAcct());
			if (signProd==null){
				throw new BizAppException(IErrorNo.BBSP0112, "票面收款人未做电票签约");
			}
			if (!bean.getPayee().equals(signProd.getCustName())){ 
		        throw new BizAppException(IErrorNo.BBSP0112, "票面收款人名称应为"+signProd.getCustName()); 
		    }
			if (!bean.getPayeeBankNo().equals(signProd.getRemitterBankNo())){
				throw new BizAppException(IErrorNo.BBSP0112, "票面收款人帐号和帐号开户机构对应关系不一致");
			}
		} else{
			EcdsBankData payeeBank = RcServiceFactory.getEcdsBankDataService().getEcdsBankData(info.getPayeeBankNo());//rc暂时有问题
			if(payeeBank==null){
				throw new BizAppException(IErrorNo.BBSP0112, "票面收款人开户行"+info.getPayeeBankNo()+"不存在");
			}
			if(IDict.K_ECDS_CERT_STATUS.K_ECDS_CERT_STATUS_NO.equals(payeeBank.getCertBindStatus())){
				throw new BizAppException(IErrorNo.BBSP0112, "票面收款人开户行" + payeeBank.getActorFullCall() + "尚未开通电子票据权限");
			}
		}
		//
		String infoId = ServiceFactory.getSequenceService().getBillInfoId();
		String histId = ServiceFactory.getSequenceService().getBillInfoHist();
		
		info.setId(infoId);
		info.setIsAccpt(CommUtils.isSelfBank(info.getDraweeBankNo())?"1":"0");
		String ebsNo = ServiceFactory.getTransPubService().generateEBSNO(info);
		
		info.setEbsNo(ebsNo);
		info.setCreateTime(DateUtil.getTime());
		info.setDelFlag(IDict.K_YORN.K_YORN_NO);
		info.setHistId(histId);
		
		bill.getHist().setBillBeforeOwner(bean.getBillBeforeOwner());
		//发起人
		bill.getHist().setFromAcctNo(bean.getRemitterAcct());
		bill.getHist().setFromName(bean.getRemitter());
		bill.getHist().setFromBankNo(bean.getRemitterBankNo());
		bill.getHist().setFromRemark(bean.getRemark());
		bill.getHist().setToAcctNo(bean.getAcceptorAcct());
		bill.getHist().setToName(bean.getAcceptor());
		bill.getHist().setToBankNo(bean.getAcceptorBankNo());
		//持票人
		bill.getHist().setHoldAcctNo(bean.getRemitterAcct());
		bill.getHist().setHoldCustName(bean.getRemitter());
		bill.getHist().setHoldBankNo(bean.getRemitterBankNo());
		
		bill.getHist().setHistId(histId);
		bill.getHist().setRgctId(infoId);
		ServiceFactory.getTransPubService().setBillHistStatus(hist);
		bill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
		bill.setInfo(info);
		transPub.setRgctBill(bill);
		return;
		
	}
	
	public void checkOldBillDraft(TransPub transPub) throws BizAppException {
		
		EcdsBillBean bean = transPub.getEcdsBillBean();
		
		RgctBill bill = RcServiceFactory.getRcRegBillService().getRgctBillById(bean.getRgctId());
		
		RgctBillInfo info = bill.getInfo();


		
		//删除纸票处理逻辑
		/*if("1".equals(bean.getBillClass())){
			bill = dealnewRgctEntityBill(bill,bean);
		}else{*/
		//}
		//对象转换
		ServiceFactory.getTransPubService().ecdsBill2BillInfo(bean, info);
		
		if (info.getIssueDt().compareTo(info.getDueDt()) > 0) {
			throw new BizAppException(IErrorNo.BBSP0112, "清单出票日必须小于票面到期日");
		}
		
		if (DateTimeUtil.isOver12MonthInterval(info.getIssueDt(), info.getDueDt())) {
			throw new BizAppException(IErrorNo.BBSP0112, "票面到期日不能超过一年");
		}

		EcdsBankData draweeBank = RcServiceFactory.getEcdsBankDataService().getEcdsBankData(info.getAcceptorBankNo());
		if (draweeBank==null){
			throw new BizAppException(IErrorNo.BBSP0112, "根据AcceptorBankNo没有找到对应行信息！");
		}
		if (IDict.K_ECDS_CERT_STATUS.K_ECDS_CERT_STATUS_NO.equals(draweeBank.getCertBindStatus())){
			throw new BizAppException(IErrorNo.BBSP0112, "承兑行" + draweeBank.getActorFullCall() + "尚未开通电子票据权限!");
		}
		info.setAcceptorAcct(bean.getAcceptorAcct());
		info.setRemitterBankName(bean.getRemitterBank());
		info.setAcceptorBankName(bean.getAcceptorBank());
		info.setPayeeBankName(bean.getPayeeBank());
		info.setIssueDt(bean.getIssueDt());
		info.setDueDt(bean.getDueDt());
		
		info.setDraweeBankName(draweeBank.getActorFullCall());
		info.setDraweeAddr(draweeBank.getAddress());
		info.setDraweeBankNo(draweeBank.getRowNumber());
		info.setBillNo(null);
		info.setInfoForbidFlag(bean.getBanEndorsementMark());
		info.setInvoiceNo(bean.getInvoiceNo());
		info.setConferNo(bean.getConferNo());
		
		info.setPayeeName(bean.getPayee());
		info.setPayeeBankNo(bean.getPayeeBankNo());
		info.setPayeeName(bean.getPayee());
		info.setBillMoney(Double.parseDouble(bean.getBillMoney()));
		
		info.setId(bean.getRgctId());
		
		ISignProdService signProdService = ServiceFactory.getSignProdService();
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getInfo().getBillClass())){
			
			SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, bean.getRemitterAcct());
			if(signProd==null){
				throw new BizAppException(IErrorNo.BBSP0112, "出票人未做电票签约");
			}
			info.setRemitterCustNo(signProd.getCustNo());
			info.setRemitterOrgCode(signProd.getIdNumber());
			info.setRemitterRole(IDict.K_BUSSINESS_ROLE.BUSSINESS_ROLE1);
		}
		
		if(IDict.K_BILL_TYPE.K_BANK_BILL.equals(bean.getBillType()) && CommUtils.isSelfBank(bean.getAcceptorBankNo()) && !"0".equals(bean.getAcceptorAcct())){
			throw new BizAppException(IErrorNo.BBSP0112, "银承承兑人为民生银行时，承兑人账号应当为0");
		}
		
		//检查商承承兑人账号和账号开户行关系是否一致
		if(IDict.K_BILL_TYPE.K_CORP_BILL.equals(bean.getBillType()) && CommUtils.isSelfBank(bean.getAcceptorBankNo())){
			//SignProd signProd = signProdService.searchSignProdByAct(IConstants.ELECTRON_SINGPROD, bean.getAcceptorAcct());
			SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, bean.getAcceptorAcct());
			if (signProd==null){
				throw new BizAppException(IErrorNo.BBSP0112, "票面承兑人未做电票签约");
			}
			if (!bean.getAcceptorBankNo().equals(signProd.getRemitterBankNo())){
				throw new BizAppException(IErrorNo.BBSP0112, "票面承兑人帐号和帐号开户机构对应关系不一致");
			}
			if (!signProd.getCustName().equals(bean.getAcceptor())){
				throw new BizAppException(IErrorNo.BBSP0112,"承兑人名称和客户签约名称不一致");
			}
		}
		
		//检查收款人账号和账号开户行关系是否一致
		if(CommUtils.isSelfBank(bean.getPayeeBankNo())){
			//SignProd signProd = signProdService.searchSignProdByAct(IConstants.ELECTRON_SINGPROD, bean.getPayeeAcct());
			SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, bean.getPayeeAcct());
			if (signProd==null){
				throw new BizAppException(IErrorNo.BBSP0112, "票面收款人未做电票签约");
			}
			if (!bean.getPayee().equals(signProd.getCustName())){ 
		        throw new BizAppException(IErrorNo.BBSP0112, "票面收款人名称应为"+signProd.getCustName()); 
		    }
			if (!bean.getPayeeBankNo().equals(signProd.getRemitterBankNo())){
				throw new BizAppException(IErrorNo.BBSP0112, "票面收款人帐号和帐号开户机构对应关系不一致");
			}
		} else{
			EcdsBankData payeeBank = RcServiceFactory.getEcdsBankDataService().getEcdsBankData(info.getPayeeBankNo());
			if(payeeBank==null){
				throw new BizAppException(IErrorNo.BBSP0112, "票面收款人开户行"+info.getPayeeBankNo()+"不存在");
			}
			if(IDict.K_ECDS_CERT_STATUS.K_ECDS_CERT_STATUS_NO.equals(payeeBank.getCertBindStatus())){
				throw new BizAppException(IErrorNo.BBSP0112, "票面收款人开户行" + payeeBank.getActorFullCall() + "尚未开通电子票据权限");
			}
		}
		String infoId = ServiceFactory.getSequenceService().getBillInfoId();
		String histId = ServiceFactory.getSequenceService().getBillInfoHist();
		
		bill.getHist().setBillBeforeOwner(bean.getBillBeforeOwner());
		//发起人
		bill.getHist().setFromAcctNo(bean.getRemitterAcct());
		bill.getHist().setFromName(bean.getRemitter());
		bill.getHist().setFromBankNo(bean.getRemitterBankNo());
		bill.getHist().setFromRemark(bean.getRemark());
		bill.getHist().setToAcctNo(bean.getAcceptorAcct());
		bill.getHist().setToName(bean.getAcceptor());
		bill.getHist().setToBankNo(bean.getAcceptorBankNo());
		//持票人
		bill.getHist().setHoldAcctNo(bean.getRemitterAcct());
		bill.getHist().setHoldCustName(bean.getRemitter());
		bill.getHist().setHoldBankNo(bean.getRemitterBankNo());
				
		bill.setInfo(info);
		
		transPub.setRgctBill(bill);
		return;
	}
	
	public void addNewBillDraft(TransPub transPub) throws BizAppException {
		
		IRcRegBillService rcRegService = RcServiceFactory.getRcRegBillService();
		//System.out.println("111");
		rcRegService.regBill(transPub.getRgctBill());   //添加参数 onfo 和  hist 添加到别的属性里
		
		//删除支票处理逻辑
		/*实物票据， 完成承兑，收票操作
		if("1".equals(info.getBillClass())){
			SignProd signProd = signproddao.getSignProdByAct(AgentConst.BILLPOOL_SINGPROD, bean.getCustAccount());
			if(signProd==null){
				throw new BizAppException("账号"+bean.getCustAccount()+"未签约票管!");
			}
			//hold信息为userNO对应信息
			bill.getHist().setHoldAcctNo(bean.getCustAccount());
			bill.getHist().setHoldCustName(signProd.getCustName());
			bill.getHist().setHoldCustNo(signProd.getPartner());
			bill.getHist().setToName(signProd.getCustName());
			this.rcBillNotifyService.inputAcpt(bill);
			
			bill.getInfo().setAcptDt(bean.getIssueDt());
			bill.getInfo().setDraweeBankNo(bean.getAcceptorBankNo());
			rcBillNotifyService.updateRgctBillInfo(bill.getInfo());
		}*/
	}
	

	/*
	 * 修改票据
	 */
	public void updateDraftEcdsRequest(EcdsBillBean bean) throws BizAppException {
		/*RgctBill rgctBill = rcRegBillService.getRgctBillById(bean.getRgctId());
		
		if(bean.getBillClass().equals("1")){
			if(!(rgctBill.getHist().getCurStatus().equals("C_02"))){
				throw new BizAppException("票据已做过交易，不允许更改!");
			}
			rgctBill = this.dealnewRgctEntityBill(rgctBill, bean);
			CustBean cust = custAcctDao.findCustBeanByAcctNo(bean.getCustAccount());
			final CustInfo custInfo = cust.getInfo();
			//hold信息为userNO对应信息
			rgctBill.getHist().setHoldAcctNo(bean.getCustAccount());
			rgctBill.getHist().setHoldCustName(custInfo.getCustName());
			rgctBill.getHist().setHoldCustNo(custInfo.getCustNo());
			rgctBill.getHist().setToName(custInfo.getCustName());
		}else{
			rgctBill = this.dealNewRgctBill(rgctBill, bean);
		}
		
		RgctBillInfo info = rgctBill.getInfo();
		RgctBillHist hist = rgctBill.getHist();
		
		rcRegBillService.updateRgctBillInfo(info);
		rcRegBillService.updateRgctBillHist(hist);*/
	}
}
