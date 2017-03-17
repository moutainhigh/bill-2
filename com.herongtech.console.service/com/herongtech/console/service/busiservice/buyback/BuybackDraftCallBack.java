package com.herongtech.console.service.busiservice.buyback;

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.dao.BuybackBillInfoDao;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.dao.SaleApplyInfoDao;
import com.herongtech.console.domain.sale.dao.SaleBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.web.busicontroller.common.BuybackCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.ITrigger;
import com.herongtech.sysconstant.ISysErrorNo;
public class BuybackDraftCallBack implements ITrigger{
	private BuybackBillInfoDao billDao = new BuybackBillInfoDao();
	private SaleBillInfoDao saleBillDao = new SaleBillInfoDao();
	private SaleApplyInfoDao saleApplyDao = new SaleApplyInfoDao();
	@Override
	public void transFor034execute(RgctBill rgctBill) throws BizAppException {
		try{
			/*IBuybackService buybackService = ServiceFactory.getBuybackService();
			buybackService.buyBackBillInit(rgctBill,null,null);*/
			BuybackBillInfo buybackBillInfo = new BuybackBillInfo();
			SaleBillInfo saleBill = saleBillDao.getSaleBillInfoByRgctId(rgctBill.getInfo().getId(),StatusUtils.handleStatusNoCheck("SaleAccountController", "elecSaleAccount",null));
			SaleApplyInfo saleApply=saleApplyDao.getSaleApplyInfo(saleBill.getSaleId());
			buybackBillInfo.setBuybackmxId(ServiceFactory.getSequenceService().getBUYBACK_BILL_INFO_ID());
			buybackBillInfo.setBillClass(rgctBill.getInfo().getBillClass());
			buybackBillInfo.setBillType(rgctBill.getInfo().getBillType());
			buybackBillInfo.setBillNo(rgctBill.getInfo().getBillNo());
			buybackBillInfo.setAcceptor(rgctBill.getInfo().getAcceptor());
			buybackBillInfo.setAcceptorBankNo(saleBill.getAcceptorBankNo());
			buybackBillInfo.setAcceptorBankName(saleBill.getAcceptorBankName());
			buybackBillInfo.setPayeeName(rgctBill.getInfo().getPayeeName());
			buybackBillInfo.setDraweeAddr(rgctBill.getInfo().getDraweeAddr());
			buybackBillInfo.setPayeeBankNo(rgctBill.getInfo().getPayeeBankNo());
			buybackBillInfo.setConferNo(rgctBill.getInfo().getConferNo());
			buybackBillInfo.setIsAccpt(rgctBill.getInfo().getIsAccpt());
			buybackBillInfo.setRemitterCustNo(rgctBill.getInfo().getRemitterCustNo());
			buybackBillInfo.setDueDt(rgctBill.getInfo().getDueDt());
			buybackBillInfo.setIssueDt(rgctBill.getInfo().getIssueDt());
			buybackBillInfo.setRemitter(rgctBill.getInfo().getRemitter());
			buybackBillInfo.setRemitterAcct(rgctBill.getInfo().getRemitterAcct());
			buybackBillInfo.setRemitterBankName(rgctBill.getInfo().getDraweeBankName());
			buybackBillInfo.setRemitterBankNo(rgctBill.getInfo().getDraweeBankNo());
			buybackBillInfo.setBillMoney(rgctBill.getInfo().getBillMoney());
			buybackBillInfo.setPayeeBankName(rgctBill.getInfo().getPayeeBankName());
			buybackBillInfo.setPayeeAcct(rgctBill.getInfo().getPayeeAcct());
			buybackBillInfo.setBillBeforeOwner(rgctBill.getHist().getFromName());// 交易前手
			buybackBillInfo.setBillOwner(rgctBill.getHist().getHoldCustName());// 持票人
			buybackBillInfo.setRgctId(rgctBill.getInfo().getId());
			buybackBillInfo.setRegressDt(saleBill.getResaleDueDt());
			buybackBillInfo.setIsInner(saleBill.getIsInner());
			buybackBillInfo.setIsDummy(saleApply.getIsDummy());
			buybackBillInfo.setAimBranchNo(saleApply.getAimBranchNo());
//			buybackBillInfo.setInnerAccount(saleApplyInfo.getInnerAccount());
			buybackBillInfo.setSaleDt(saleApply.getSaleDt());
			buybackBillInfo.setBranchNo(saleApply.getBillStorageBrchno());
			buybackBillInfo.setBuyDeptNo(saleBill.getBuyDeptNo());
			buybackBillInfo.setCreateTime(DateTimeUtil.getWorkdayString());
			buybackBillInfo.setSaleId(saleApply.getSaleId());
			buybackBillInfo.setSalemxId(saleBill.getSalemxId());
			buybackBillInfo.setIsOnline(rgctBill.getHist().getIsOnline());
			// buybackBillInfo.setReceiveMoney(rgctBillHist.getDealMoney());
			buybackBillInfo.setDraftPayMoney(rgctBill.getHist().getBackAmount());
			// buybackBillInfo.setInterest(MathScaleUtil.subtract(saleBillInfo.getBillAmount(),rgctBillHist.getDealMoney()));
			buybackBillInfo.setSaleInterest(saleBill.getInterest());
			buybackBillInfo.setSaleReceiveMoney(saleBill.getReceiveMoney());
			buybackBillInfo.setBuybackDt(rgctBill.getHist().getRegressDt());
			if (rgctBill.getHist().getRegressDt().compareTo(rgctBill.getHist().getBackEndDt()) < 0) {
				buybackBillInfo.setInterestDays(DateTimeUtil.getDiffDays(rgctBill.getHist().getBackEndDt(),rgctBill.getHist().getRegressDt()));
			} else {
				buybackBillInfo.setInterestDays(Long.valueOf("0"));
			}
			//FISME BD460 暂未定义状态机
			// 清单状态
			buybackBillInfo.setOperStatus(BuybackCodeConst.ELEC_FIRST_STATUS);
			// 批次ID
//			buybackBillInfo.setBuybackId(buybackapId);
			buybackBillInfo.setReqDraftNo(rgctBill.getHist().getDraftLogId());// 响应报文
			buybackBillInfo.setApplyCancelFlag("0");// 对方未撤销
			// buybackBillInfo.setApplyTellerNo(userInfo.getUserNo());
			buybackBillInfo.setForbidFlag(rgctBill.getHist().getForbidFlag());
			//保存回购清单
			billDao.addBuybackBillInfo(buybackBillInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		
	}

	@Override
	public void signFor033execute(RgctBill rgctBill,String isSign,boolean isSuccess) throws BizAppException {
		try {
			BuybackBillInfo bill =billDao.getBuybackBillInfoByReqDraftNo(rgctBill.getInfo().getReqDraftId());
			if (RcConstants.SIGN_NO.equals(isSign)) {// 拒绝签收回调
				if(isSuccess){
					bill.setOperStatus(StatusUtils.handleStatus("BuybackApplyController", "buybackApply","0", bill.getOperStatus()));
				}else{
					bill.setOperStatus(StatusUtils.handleStatus("BuybackApplyController", "buybackApply", "0",bill.getOperStatus()));
				}
			} else {//记账回调
				if(isSuccess){
					RgctBillHist hist = rgctBill.getHist();
					hist.setIsLock(IDict.K_LOCK.K_LOCK_YES);
					RcServiceFactory.getRcBaseService().updateRgctBillHist(hist);
					bill.setOperStatus(StatusUtils.handleStatusNoCheck("BuybackAccountController", "buybackApplyAccount", null));
				}else{
					bill.setOperStatus(StatusUtils.queryStatus("BuybackAccountController", "buybackApplyAccount", null)[0]);
				}
			}
			bill.setPayTradeNo(rgctBill.getHist().getPayTradeNo());
			billDao.modifyBuybackBillInfo(bill);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("赎回回调失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
	}

	@Override
	public void transFor031execute(RgctBill rgctBill, String isSign)
			throws BizAppException {
		
	}

	@Override
	public void transFor032execute(RgctBill rgctBill) throws BizAppException {
		try {
			BuybackBillInfo bill = billDao.getBuybackBillInfoByReqDraftNo(rgctBill.getInfo().getReqDraftId());
			if(bill!=null){
				bill.setApplyCancelFlag(CommonConst.IS_CANCEL_TRUE);
				bill.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "delete", null)[0]);
				billDao.modifyBuybackBillInfo(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		try {
			BuybackBillInfo bill = billDao.getBuybackBillInfoByReqDraftNo(rgctBill.getInfo().getReqDraftId());
			if(bill!=null){
				bill.setApplyCancelFlag(CommonConst.IS_CANCEL_TRUE);
				bill.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "delete", null)[0]);
				billDao.modifyBuybackBillInfo(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		// TODO Auto-generated method stub
		
	}

}
