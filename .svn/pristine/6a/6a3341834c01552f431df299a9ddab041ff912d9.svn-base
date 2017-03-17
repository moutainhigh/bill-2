package com.herongtech.console.service.busiservice.collateralization;

import java.sql.SQLException;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.into.bean.IntoBillInfo;
import com.herongtech.console.domain.into.dao.IntoBillInfoDao;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.dao.SaveBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.IntoCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.ITrigger;
import com.herongtech.sysconstant.ISysErrorNo;

public class CollateDraftCallback implements ITrigger{
	
	private SaveBillInfoDao saveBillDao = new SaveBillInfoDao();
	private IntoBillInfoDao intoBillDao = new IntoBillInfoDao();

	@Override
	public void transFor034execute(RgctBill rgctBill) throws BizAppException {
		RgctBillInfo info = rgctBill.getInfo();
		RgctBillHist hist = rgctBill.getHist();
		try {
			if(RcConstants.BUY_INPOOL.equals(hist.getBuyType())){
				IntoBillInfo intoBill = new IntoBillInfo();
				intoBill.setIntomxId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
				intoBill.setRgctId(info.getId());
				intoBill.setYzSource("0");
				intoBill.setBillNo(rgctBill.getInfo().getBillNo());
				intoBill.setBillSource("3");
	//			saveBill.setBillSource(rgctBill.getInfo().getBillSource());
				intoBill.setBillClass(CommonConst.BILL_CLASS_ELEC);
				intoBill.setBillType(info.getBillType());
				intoBill.setIssueDt(info.getIssueDt());
				intoBill.setDueDt(info.getDueDt());
				intoBill.setBillMoney(info.getBillMoney());
				intoBill.setBillBeforeOwner(info.getLetterNo());
				intoBill.setProdNo(IntoCodeConst.DERIVATIVE_PROD_NO);
				intoBill.setRemark(info.getRemark());
				intoBill.setCustNo(hist.getFromCustNo());
				intoBill.setCustName(hist.getFromName());
				intoBill.setOperStatus(IntoCodeConst.FIRST_STATUS);
				intoBill.setOrgCode(hist.getFromOrgcode());
				intoBill.setReqMsgId(info.getReqDraftId());
				intoBill.setCreateDt(DateTimeUtil.getWorkdayString());
				intoBill.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				intoBill.setRemitter(info.getRemitter());
				intoBill.setRemitterAcct(info.getRemitterAcct());
				intoBill.setRemitterBankName(info.getRemitterBankName());
				intoBill.setRemitterBankNo(info.getRemitterBankNo());
				intoBill.setRemitterAddr(info.getDraweeAddr());
				intoBill.setPayee(info.getPayeeName());
				intoBill.setPayeeBankName(info.getPayeeBankName());
				intoBill.setPayeeAcct(info.getPayeeAcct());
				intoBill.setAcceptor(info.getAcceptor());
				intoBill.setAcceptorBankName(info.getAcceptorBankName());
				intoBill.setAcceptorBankNo(info.getAcceptorBankNo());
				intoBill.setBillBeforeOwner(info.getLetterNo());
				intoBill.setBranchNo(ServiceFactory.getBranchService().getBranchByBankNo(hist.getToBankNo()).getBranchNo());
				intoBillDao.addIntoBillInfo(intoBill);
			}else{
				SaveBillInfo saveBill = new SaveBillInfo();
				saveBill.setSavemxId(ServiceFactory.getSequenceService().getSAVE_BILL_INFO_ID());
				saveBill.setRgctId(info.getId());
				saveBill.setYzSource("0");
				saveBill.setBillNo(rgctBill.getInfo().getBillNo());
				saveBill.setBillSource("3");
	//			saveBill.setBillSource(rgctBill.getInfo().getBillSource());
				saveBill.setBillClass(CommonConst.BILL_CLASS_ELEC);
				saveBill.setBillType(info.getBillType());
				saveBill.setIssueDt(info.getIssueDt());
				saveBill.setDueDt(info.getDueDt());
				saveBill.setBillMoney(info.getBillMoney());
				saveBill.setBillBeforeOwner(info.getLetterNo());
				saveBill.setProdNo(CollateCodeConst.impawnBillProd_no);
				saveBill.setRemark(info.getRemark());
				saveBill.setCustNo(hist.getFromCustNo());
				saveBill.setCustName(hist.getFromName());
				saveBill.setOperStatus(CollateCodeConst.FIRST_STATUS);
				saveBill.setOrgCode(hist.getFromOrgcode());
				saveBill.setReqMsgId(info.getReqDraftId());
				saveBill.setCreateDt(DateTimeUtil.getWorkdayString());
				saveBill.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				saveBill.setRemitter(info.getRemitter());
				saveBill.setRemitterAcct(info.getRemitterAcct());
				saveBill.setRemitterBankName(info.getRemitterBankName());
				saveBill.setRemitterBankNo(info.getRemitterBankNo());
				saveBill.setRemitterAddr(info.getDraweeAddr());
				saveBill.setPayee(info.getPayeeName());
				saveBill.setPayeeBankName(info.getPayeeBankName());
				saveBill.setPayeeAcct(info.getPayeeAcct());
				saveBill.setAcceptor(info.getAcceptor());
				saveBill.setAcceptorBankName(info.getAcceptorBankName());
				saveBill.setAcceptorBankNo(info.getAcceptorBankNo());
				saveBill.setBillBeforeOwner(info.getLetterNo());
				saveBill.setBranchNo(ServiceFactory.getBranchService().getBranchByBankNo(hist.getToBankNo()).getBranchNo());
				saveBillDao.addSaveBillInfo(saveBill);
			
			}
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("\n034回调异常 票号:"+rgctBill.getInfo().getBillNo());
			e.printStackTrace();
		}
	}
	
	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		/*RgctBillInfo info = rgctBill.getInfo();
		RgctBillHist hist = rgctBill.getHist();
		try{
			if(isSuccess){
				rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
				RcServiceFactory.getRcImpawnService().updateRgctBillHist(rgctBill.getHist());
				SaveBillInfo bill = new SaveBillInfo();
				bill.setSavemxId(ServiceFactory.getSequenceService().getSAVE_BILL_INFO_ID());
				bill.setRgctId(info.getId());
				bill.setYzSource("0");
				bill.setBillNo(rgctBill.getInfo().getBillNo());
//				bill.setBillSource("3");
				bill.setBillSource(rgctBill.getInfo().getBillSource());
				bill.setBillClass(CommonConst.BILL_CLASS_ELEC);
				bill.setBillType(info.getBillType());
				bill.setIssueDt(info.getIssueDt());
				bill.setDueDt(info.getDueDt());
				bill.setBillMoney(info.getBillMoney());
				bill.setBillBeforeOwner(info.getLetterNo());
				bill.setProdNo(CollateCodeConst.impawnBillProd_no);
				bill.setRemark(info.getRemark());
				bill.setCustNo(hist.getFromCustNo());
				bill.setCustName(hist.getFromName());
				bill.setOperStatus(CollateCodeConst.FIRST_STATUS);
				bill.setOrgCode(hist.getFromOrgcode());
				bill.setReqMsgId(info.getReqDraftId());
				bill.setCreateDt(DateTimeUtil.getWorkdayString());
				bill.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				bill.setRemitter(info.getRemitter());
				bill.setRemitterAcct(info.getRemitterAcct());
				bill.setRemitterBankName(info.getRemitterBankName());
				bill.setRemitterBankNo(info.getRemitterBankNo());
				bill.setRemitterAddr(info.getDraweeAddr());
				bill.setPayee(info.getPayeeName());
				bill.setPayeeBankName(info.getPayeeBankName());
				bill.setPayeeAcct(info.getPayeeAcct());
				bill.setAcceptor(info.getAcceptor());
				bill.setAcceptorBankName(info.getAcceptorBankName());
				bill.setAcceptorBankNo(info.getAcceptorBankNo());
				bill.setBillBeforeOwner(info.getLetterNo());
				saveBillDao.addSaveBillInfo(bill);
			}
		}catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("\n质押申请回调异常 票号:"+rgctBill.getInfo().getBillNo());
			e.printStackTrace();
		}*/
		
	}

	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		/*if(isSuccess){
			SaveBillInfo bill;
			try {
				bill = saveBillDao.getSaveBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
				if(bill!=null){
					rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
					RcServiceFactory.getRcImpawnService().updateRgctBillHist(rgctBill.getHist());
					bill.setApplyCancelFlag("1");
					bill.setOperStatus(CollateCodeConst.DELETE_STATUS);
					saveBillDao.modifySaveBillInfo(bill);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				CommonLog.getCommonLogCache().errorMessage("\n质押032-033回调异常 票号:"+rgctBill.getInfo().getBillNo());
				e.printStackTrace();
			}
		}*/
	}

	@Override
	public void signFor033execute(RgctBill rgctBill, String isSign,
			boolean isSuccess) throws BizAppException {
		RgctBillHist hist = rgctBill.getHist();
		if(RcConstants.SIGN_NO.equals(isSign)){
			try {
				if(rgctBill.getHist().getBuyType().equals(RcConstants.BUY_INPOOL)){	
					IntoBillInfo bill =intoBillDao.getIntoBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
					if(bill!=null){
					if(isSuccess){
						bill.setOperStatus(StatusUtils.handleStatusNoCheck("IntoApplyController", "elecRefuseFinal", null));
					}else{
						bill.setOperStatus(IntoCodeConst.FIRST_STATUS);
					}
					intoBillDao.modifyIntoBillInfo(bill);
					}
				}else{
					SaveBillInfo bill =saveBillDao.getSaveBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
					if(bill!=null){
//					RcServiceFactory.getRcDiscService().lock(rgctBill.getInfo().getId());
						if(isSuccess){
//						bill.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "elecRefuseFinal", null, bill.getOperStatus()));
							bill.setOperStatus(StatusUtils.handleStatusNoCheck("CollateralizationApplyController", "elecRefuseFinal", null));
						}else{
							bill.setOperStatus(CollateCodeConst.FIRST_STATUS);
						}
						saveBillDao.modifySaveBillInfo(bill);
					}
					
					
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				CommonLog.getCommonLogCache().errorMessage("\n031-033回调异常 票号:"+rgctBill.getInfo().getBillNo());
				e.printStackTrace();
			}
		}else{
			try {
				if(rgctBill.getHist().getBuyType().equals(RcConstants.BUY_INPOOL)){
					IntoBillInfo bill =intoBillDao.getIntoBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
					if(bill!=null){
						if(isSuccess){
							bill.setOperStatus(IntoCodeConst.ACCOUNT_STATUS);
							bill.setPosition(IntoCodeConst.SAVE_ACCOUNT_OVER);//存票记账完成
			    			bill.setGathType(IntoCodeConst.GATH_TYPE_COMMON);//正常--记账完成
			    			bill.setAccountDate(DateTimeUtil.getWorkdayString());//入库记账日期
			                bill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			    			bill.setGathDate(null);
						}else{
							bill.setOperStatus("BS934");
						}
						intoBillDao.modifyIntoBillInfo(bill);
					}
					}else{
					SaveBillInfo bill =saveBillDao.getSaveBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
					if(bill!=null){
						if(isSuccess){
//							rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_YES);
//							RcServiceFactory.getRcImpawnService().updateRgctBillHist(rgctBill.getHist());
						//	bill.setOperStatus(StatusUtils.handleStatus("CollateralizationAuditController", "audit", null, bill.getOperStatus()));
							bill.setOperStatus(CollateCodeConst.ACCOUNT_STATUS);
							bill.setPosition(CollateCodeConst.COLLZTN_ACCOUNT_OVER);//质押记账完成
			    			bill.setGathType(CollateCodeConst.GATH_TYPE_COMMON);//正常--记账完成
			    			bill.setAccountDate(DateTimeUtil.getWorkdayString());//入库记账日期
			                bill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			    			bill.setGathDate(null);
						}else{
						//	bill.setOperStatus(StatusUtils.handleStatusNoCheck("CollateralizationApplyController", "apply", null));
							bill.setOperStatus("BS734");
						}
						saveBillDao.modifySaveBillInfo(bill);
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				CommonLog.getCommonLogCache().errorMessage("\n031-033回调异常 票号:"+rgctBill.getInfo().getBillNo());
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
			if(rgctBill.getHist().getBuyType().equals(RcConstants.BUY_INPOOL)){
				IntoBillInfo bill = intoBillDao.getIntoBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
				if(bill!=null){
					bill.setApplyCancelFlag("1");
					bill.setOperStatus(IntoCodeConst.DELETE_STATUS);
					intoBillDao.modifyIntoBillInfo(bill);
							}
			}else{
				SaveBillInfo bill = saveBillDao.getSaveBillInfoByReqDraftId(rgctBill.getInfo().getReqDraftId());
				if(bill!=null){
					bill.setApplyCancelFlag("1");
					bill.setOperStatus(CollateCodeConst.DELETE_STATUS);
					saveBillDao.modifySaveBillInfo(bill);
							}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		String rgctId = rgctBill.getInfo().getId();
		try {
			SaveBillInfo saveBill = saveBillDao.getSaveBillInfoByRgctId(rgctId);
			if (saveBill == null) {
				throw new BizAppException("票据不存在");
			}
			saveBill.setOperStatus("BS700");
			saveBillDao.modifySaveBillInfo(saveBill);
		} catch (SQLException e) {
			throw new BizAppException("查询票据失败，"+e.getMessage());
		}
		
	}
	

}
