package com.herongtech.console.service.busiservice.saleback;

import java.sql.SQLException;

import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.dao.RebuyApplyInfoDao;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.domain.saleback.dao.SalebackBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.dao.EcdsBankDataDao;
import com.herongtech.rc.service.rcservice.ISysTrigger;

/**
 * 转入返售040回调处理
 *
 */
public class RpdRediscountDraftCallback implements ISysTrigger{
	
	private RebuyBillInfoDao dao = new RebuyBillInfoDao();
	private RebuyApplyInfoDao applyDao = new RebuyApplyInfoDao();
	private SalebackBillInfoDao saleBackDao = new SalebackBillInfoDao();
	private EcdsBankDataDao ecdsBankDao=new EcdsBankDataDao();
	
	@Override
	public void execute(RgctBill rgctBill) throws BizAppException {
		RgctBillHist hist=rgctBill.getHist();
		if(RcConstants.BUY_OUT_REGRESS.equals(hist.getBuyType())||RcConstants.BUY_INNER_REGRESS.equals(hist.getBuyType())){
			RgctBillInfo info=rgctBill.getInfo();
			SalebackBillInfo saleBackBill = new SalebackBillInfo();
			try {
				RebuyBillInfo rebuyBill = dao.getRebuyBillInfoByRgctId(hist.getRgctId());
				RebuyApplyInfo rebuyApply = applyDao.getRebuyApplyInfo(rebuyBill.getRebuyId());
				SalebackBillInfo saleBackbill = saleBackDao.getsalebackbillinfobyrgctidandstatus(rebuyBill.getRgctId(),StatusUtils.queryStatus("SalebackApplyController", "applysubmitelec", null)[0]);
				if(saleBackbill!=null){
					return ;
				}
				saleBackBill.setRebuyId(rebuyApply.getRebuyId());
				ISequenceService sequenceService=ServiceFactory.getSequenceService();
				saleBackBill.setSalebackmxId(sequenceService.getSALEBACK_BILL_INFO_ID());
//			saleBackBill.setDelayType(rebuyApply.getDelayType());
//			saleBackBill.setDelayDays(Long.parseLong(rebuyApply.getDelayDays()));
				saleBackBill.setAcceptor(rebuyBill.getAcceptor());
				saleBackBill.setAcceptorBankNo(rebuyBill.getAcceptorBankNo());
				saleBackBill.setAcceptorBankName(rebuyBill.getAcceptorBankName());
				saleBackBill.setBranchNo(rebuyBill.getBranchNo());
				saleBackBill.setBillMoney(rebuyBill.getBillMoney());
				saleBackBill.setBillClass(rebuyBill.getBillClass());
				saleBackBill.setBillNo(rebuyBill.getBillNo());
				saleBackBill.setBillOwner(rebuyBill.getBillOwner());
				saleBackBill.setBillSource(rebuyBill.getBillSource());
				saleBackBill.setBillType(rebuyBill.getBillType());
				saleBackBill.setDraweeAddr(info.getDraweeAddr());
				saleBackBill.setDueDt(rebuyBill.getDueDt());
//			saleBackBill.setInterest(MathScaleUtil.subtract(rebuyBill.getBillMoney(),rebuyBill.getPaymoneySaleback1()));
//			saleBackBill.setSalebackMoney(rebuyBill.getPaymoneySaleback1());
				saleBackBill.setIsAccpt(rebuyBill.getIsAccpt());
				saleBackBill.setRemitterAcct(rebuyBill.getRemitterAcct());
				saleBackBill.setRemitterBankName(ecdsBankDao.getEcdsBankData(rebuyBill.getRemitterBankNo()).getActorFullCall());
				saleBackBill.setRemitterBankNo(rebuyBill.getRemitterBankNo());
				saleBackBill.setIssueDt(rebuyBill.getIssueDt());
				saleBackBill.setRemitter(rebuyBill.getRemitter());
				saleBackBill.setPayeeName(rebuyBill.getPayee());
				saleBackBill.setPayeeAcct(rebuyBill.getPayeeAcct());
				saleBackBill.setPayeeBankName(ecdsBankDao.getEcdsBankData(rebuyBill.getPayeeBankNo()).getActorFullCall());
				saleBackBill.setPayeeBankNo(rebuyBill.getPayeeBankNo());
				saleBackBill.setRgctId(rebuyBill.getRgctId());
				saleBackBill.setRemitterCustNo(rebuyBill.getRemitterCustNo());
				saleBackBill.setCreateDt(DateTimeUtil.getWorkdayString());
				saleBackBill.setCreateTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
				saleBackBill.setForbidFlag(rebuyBill.getForbidFlag());
				saleBackBill.setRebuyId(rebuyBill.getRebuyId());
				saleBackBill.setRebuymxId(rebuyBill.getRebuymxId());
				saleBackBill.setBuyDeptNo(rebuyApply.getDeptNo());
				saleBackBill.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applysubmitelec", null)[0]);
				saleBackBill.setIsInner(rebuyBill.getIsInner());
				saleBackBill.setSalebackDueDt(rebuyApply.getResaleDueDt());
				saleBackBill.setIsDummy("0");
				saleBackBill.setBillStorageOrg(hist.getStorageBranchNo());
				saleBackBill.setBillStorageOrgName(hist.getStorageBranchName());
				saleBackDao.addSalebackBillInfo(saleBackBill);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
