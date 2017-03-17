package com.herongtech.console.service.busiservice.disc;

import java.sql.SQLException;

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.dao.DiscBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.web.busicontroller.common.DiscCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.ITrigger;

public class DiscDraftCallback implements ITrigger{
	
	private DiscBillInfoDao discBillDao = new DiscBillInfoDao();
	
	@Override
	public void transFor034execute(RgctBill rgctBill) throws BizAppException {
		RgctBillInfo info=rgctBill.getInfo();
		RgctBillHist hist=rgctBill.getHist();
		try {
			if(RcConstants.SETTLEMENTMARK_ONE.equals(hist.getIsOnline()) 
	        		|| RcConstants.REGRESS_YES.equals(hist.getIsRegress())){
				
				//拒绝处理 
				Branch branch=ServiceFactory.getBranchService().getBranchByBankNo(
						hist.getToBankNo());
	            hist.setToRole(branch.getPartnerType());
	            hist.setToOrgcode(branch.getOrgCode());
	//            hist.setSignerSign(CommonUtil.getSignerSign(hist.getToBankNo()));
	            hist.setSignDt(DateTimeUtil.getWorkday());
	            if(RcConstants.REGRESS_YES.equals(hist.getIsRegress())){
	            	hist.setRejectReason("本行暂不支持回购式贴现交易");
	            }else{
	            	hist.setRejectReason("本行暂不支持线上清算贴现交易");
	            }
				RcServiceFactory.getRcDiscService().rejectSignBuy(rgctBill);
			}else{
					DiscBillInfo discBill = new DiscBillInfo();
		//			Branch branch=BranchServiceFactory.getBranchService().getBranchByBankNo(hist.getToBankNo());
		////			discBill.setBrchId(branch.getBrchId());
		//			discBill.setAdscriptionId(branch.getBrchId());
					discBill.setDiscmxId(ServiceFactory.getSequenceService().getDISC_BILL_INFO_ID());
					discBill.setBillNo(info.getBillNo());
					discBill.setLastBillNo(CommUtils.getLastBillNo(info.getBillNo()));
					discBill.setBillMoney(info.getBillMoney());
					discBill.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
					discBill.setBillType(info.getBillType());
					discBill.setIssueDt(info.getIssueDt());
					discBill.setDueDt(info.getDueDt());
					discBill.setForbidFlag(hist.getForbidFlag());
					discBill.setSalerInterest(hist.getInterest());
					discBill.setIsOnline(hist.getIsOnline());
					discBill.setDiscType(hist.getIsRegress());
					try {
						discBill.setOperStatus(StatusUtils.queryStatus("DiscApplyController", "electricReceive", null)[0]);
					} catch (Exception e) {
						discBill.setOperStatus("BS011");
					}
					discBill.setOperBankNo(hist.getToBankNo());
					discBill.setIsCancel(DiscCodeConst.IS_CANCEL_FALSE);
					discBill.setIsSameCity(DiscCodeConst.IS_SAME_CITY);
					
					discBill.setInAcctNo(hist.getInAcctNo());
					discBill.setInBankNo(hist.getInBankNo());
					discBill.setDiscDt(hist.getEndorseDt());
					discBill.setDraftPayMoney(hist.getDealMoney());
			//		discBill.setTotalIntrstPayment(MathScaleUtil.divide(info.getBillMoney(), hist.getDealMoney()));
		//			discBill.setRate(MathScaleUtil.multiply(hist.getInterestRate(),100));
					discBill.setRate(hist.getInterestRate()*100);
					discBill.setRateType("360");
					discBill.setBillOwner(hist.getFromName());
					discBill.setOrgCode(hist.getFromOrgcode());
					discBill.setRgctId(hist.getRgctId());
					discBill.setBillBeforeOwner(info.getLetterNo());//票据前手
		//			discBill.setConferNo(TxlCtrctNb);//交易合同编号 待处理
		//			discBill.setInvoiceNo(InvcNb);//发票号码
					discBill.setBillSource("3");/*票据来源   3 为网银过来的票据 */
					discBill.setYzSource("0");
					
					discBill.setRemitter(info.getRemitter());
					discBill.setRemitterBankName(info.getRemitterBankName());
					discBill.setRemitterBankNo(info.getDraweeBankNo());
					discBill.setRemitterAcct(info.getRemitterAcct());
					
					discBill.setPayee(info.getPayeeName());
					discBill.setPayeeAcct(info.getPayeeAcct());
					discBill.setPayeeBankName(info.getPayeeBankName());
					discBill.setPayeeBankNo(info.getPayeeBankNo());
					discBill.setAcceptorAcct(info.getAcceptorAcct());
					discBill.setAcceptor(info.getAcceptor());
					discBill.setAcceptorBankNo(info.getAcceptorBankNo());
					discBill.setAcceptorBankName(info.getAcceptorBankName());
					discBill.setDraweeAddr(info.getDraweeAddr());
					if(CommUtils.isSelfBank(info.getAcceptorBankNo())){/*是否本行承兑*/
						discBill.setIsAccpt(RcConstants.ACPT_YES);
					}else{
						discBill.setIsAccpt(RcConstants.ACPT_NO);
					}
					discBill.setCustNo(hist.getFromCustNo());
					discBill.setReqMsgId(info.getReqDraftId());
					discBill.setInvoiceNo(hist.getInvcNb());
					discBill.setConferNo(hist.getConferNo());
					discBillDao.addDiscBillInfo(discBill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		
	}

	@Override
	public void signFor033execute(RgctBill rgctBill, String isSign,
			boolean isSuccess) throws BizAppException {
		if(RcConstants.SIGN_NO.equals(isSign)){
			try {
				DiscBillInfo bill =discBillDao.getDiscBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
				if(bill!=null){
//					RcServiceFactory.getRcDiscService().lock(rgctBill.getInfo().getId());
					if(isSuccess){
						String status= bill.getOperStatus();
						String afterStatus= StatusUtils.handleStatus("DiscApplyController", "finalNoReceive", null, status);
						bill.setOperStatus(afterStatus);
					}else{
						String[] s=StatusUtils.queryStatus("DiscApplyController", "noReceive", null);
						bill.setOperStatus(s[0]);
					}
					discBillDao.modifyDiscBillInfo(bill);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				DiscBillInfo bill =discBillDao.getDiscBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
				if(bill!=null){
					if(isSuccess){
						rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_YES);
						RcServiceFactory.getRcDiscService().updateRgctBillHist(rgctBill.getHist());
						String[] s=StatusUtils.queryStatus("DiscAccountController", "doFinalAccount", null);
						bill.setOperStatus(s[0]);
					}else{
						String[] s=StatusUtils.queryStatus("DiscAccountController", "doElecAccount", null);
						bill.setOperStatus(s[0]);
					}
					discBillDao.modifyDiscBillInfo(bill);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void transFor031execute(RgctBill rgctBill, String isSign)
			throws BizAppException {
		
	}

	@Override
	public void transFor032execute(RgctBill rgctBill) throws BizAppException {
		try {
			DiscBillInfo bill =discBillDao.getDiscBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
			if(bill!=null){
				String s = StatusUtils.handleStatusNoCheck("DiscApplyController", "finalNoReceive", null);
				bill.setIsCancel(DiscCodeConst.IS_CANCEL_TRUE);
				bill.setOperStatus(s);
				discBillDao.modifyDiscBillInfo(bill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		
	}

	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		String rgctId = rgctBill.getInfo().getId();
		try {
			DiscBillInfo discBill = discBillDao.getDiscBillInfoByRgctId(rgctId);
			if (discBill == null) {
				throw new BizAppException("票据不存在");
			}
			discBill.setOperStatus("BS000");
			discBillDao.modifyDiscBillInfo(discBill);
		} catch (SQLException e) {
			throw new BizAppException("查询票据失败，"+e.getMessage());
		}
		
	}

}
