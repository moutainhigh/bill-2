package com.herongtech.console.service.busiservice.rebuy;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.dao.SaleBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.console.web.busicontroller.common.SaleCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.logger.Logger;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.service.rcservice.ITrigger;

public class RebuyDraftCallback implements ITrigger{
	
	private RebuyBillInfoDao rebuyBillDao = new RebuyBillInfoDao();
	private SaleBillInfoDao saleInfoDao = new SaleBillInfoDao();
	
	private IRcBaseService rcBaseService = RcServiceFactory.getRcBaseService();
	
	private Logger LOGGER = CommonLog.getCommonLogCache();
	
	@Override
	public void transFor034execute(RgctBill rgctBill) throws BizAppException {
		RgctBillInfo info = rgctBill.getInfo();
		RgctBillHist hist = rgctBill.getHist();
		
		ISequenceService seqService = ServiceFactory.getSequenceService();
		IEcdsBankDataService ecdsBank=ServiceFactory.getEcdsBankDataService();
		RebuyBillInfo bill = new RebuyBillInfo();
		try{
			bill.setRebuymxId(seqService.getREBUY_BILL_INFO_ID());
			bill.setRgctId(info.getId());// 关联登记中心
			bill.setOperStatus(StatusUtils.queryStatus("RebuyApplyController", "searchReceivableBill", null)[0]);//录入
			bill.setBillType(info.getBillType());
			bill.setBillClass(info.getBillClass());
			bill.setBillNo(info.getBillNo());
			bill.setIssueDt(info.getIssueDt());
			bill.setDueDt(info.getDueDt());
			bill.setBillMoney(info.getBillMoney());
			bill.setRemitter(info.getRemitter());
			bill.setRemitterAcct(info.getRemitterAcct());
			bill.setRemitterBankName(info.getRemitterBankName());
			bill.setRemitterBankNo(info.getRemitterBankNo());
			bill.setAcceptor(info.getAcceptor());
			bill.setAcceptorBankName(ecdsBank.getEcdsBankData(info.getAcceptorBankNo()).getActorFullCall());//承兑人开户行名
			bill.setAcceptorBankNo(info.getAcceptorBankNo());//承兑人开户行
			bill.setPayee(info.getPayeeName());
			bill.setPayeeBankName(info.getPayeeBankName());
			bill.setPayeeBankNo(info.getPayeeBankNo());
			bill.setPayeeAcct(info.getPayeeAcct());
	//		bill.setLimitReduceRow(info.getLimitReduceRow());//额度扣减行
			bill.setRebuyDt(hist.getEndorseDt());
			
			if (null==bill.getBillSource()||"".equals(bill.getBillSource())) {
				bill.setBillSource(RebuyCodeConst.billSourceForCenter);
			}
			bill.setCreateTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
			bill.setCreateDate(DateTimeUtil.getNowDateTime());
			bill.setIsAccpt(info.getIsAccpt());

			bill.setIsInner(CommUtils.isSelfBank(hist.getFromBankNo()) ? "1" : "0");
			
			//交易对手
			bill.setBillOwner(hist.getFromName());//客户名称
			bill.setCustNo(hist.getFromCustNo());//客户号
			bill.setCustOrgCode(hist.getFromOrgcode());
			bill.setCustBankNo(hist.getFromBankNo());//客户开户行行号
			bill.setCustBankName(hist.getFromName());//客户开户行名称
			bill.setCustAccount(hist.getFromAcctNo());// 账号
			
			Branch branch = ServiceFactory.getBranchService().getBranchByBankNo(hist.getToBankNo());
			bill.setBranchNo(branch.getBranchNo());
			
			bill.setIsOnline(hist.getIsOnline());
			bill.setIsRegress(hist.getIsRegress());
			if(IConstants.YES.equals(bill.getIsRegress())){
				bill.setResaleStaDt(hist.getBackOpenDt());
				bill.setResaleDueDt(hist.getBackEndDt());
			}
			
			Double rcInterestRate = notNullValue(hist.getInterestRate());// 利率
			bill.setRate(MathScaleUtil.multiply(rcInterestRate, 100));
			bill.setRateType("360");
			bill.setInterest(hist.getInterest());// 利息
			double dealMoney = notNullValue(hist.getDealMoney());
			bill.setPayMoney(dealMoney); // 报文实付金额
			
			bill.setIsSameCity(IConstants.YES);// 同城，默认值
			bill.setForbidFlag( StringUtils.isEmpty(hist.getForbidFlag()) 
					? CommonConst.ENDORSE_FORBID_NO
					: hist.getForbidFlag());// 禁止背书标记
			bill.setIsBuyback(IConstants.NO);// 是否回购记账完成
			bill.setIsRediscCenter(
					StringUtils.equals("2", hist.getIsRediscCenter()) ? "1" : "0");
			bill.setRemark(info.getRemark());
			
			//原申请报文编号，用于032撤销时唯一定位清单
			bill.setReqDraftNo(info.getReqDraftId());
			bill.setApplyCancelFlag(RebuyCodeConst.APPLY_CANCEL_FLAG_NO);
			//系统内电票,建立转卖关联
			if(CommUtils.isSelfBank(bill.getCustBankNo())){
				String status=StatusUtils.handleStatusNoCheck("SaleEndorseController", "endorseSuccess", null); //加入查询条件背书申请状态，保证查询出的记录唯一
				SaleBillInfo  saleBill = saleInfoDao.getSaleBillInfoByRgctId(bill.getRgctId(),status);
				bill.setSaleId(saleBill.getSaleId());
				bill.setSalemxId(saleBill.getSalemxId());
				bill.setZcSource(saleBill.getBillSource());//同业间默认为2 系统内根据转卖获取
				bill.setZcSourceId(saleBill.getBillSourceId());
			}
			
			rebuyBillDao.addRebuyBillInfo(bill);
		}catch(Exception e){
			throw new BizAppException(e);
		}
	}
	
	private double notNullValue(Double value) {
		return value == null ? 0d : value;
	}
	
	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		
	}

	@Override
	public void signFor033execute(RgctBill rgctBill, String isSign,
			boolean isSuccess) throws BizAppException {
		try{
			//拒绝签收
			if(CommonConst.SIGN_NO.equals(isSign)){
				
				if(isSuccess){//拒绝签收处理成功
					LOGGER.debugMessage("\n删除收到034时插入的清单:"+rgctBill.getInfo().getBillNo());
					
					RebuyBillInfo bill = rebuyBillDao.findRebuyBillByRgctIdAndDraftLogId(rgctBill.getInfo().getId(),rgctBill.getInfo().getReqDraftId());
					
					if(bill!=null){
						String status = StatusUtils.handleStatus("RebuyApplyController", "rejectElecBill", null, bill.getOperStatus());
						bill.setOperStatus(status);
						bill.setApplyCancelFlag(RebuyCodeConst.APPLY_CANCEL_FLAG_YES);
						rebuyBillDao.modifyRebuyBillInfo(bill);
					}
	
				}
				
			}else{//签收
				
				RebuySearchBean searchBean = new RebuySearchBean();
				searchBean.setDfaultSrchTbAliasName("bill");
				searchBean.setRgctId(rgctBill.getInfo().getId());
				searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
				searchBean.addSqlPropretyMapping("opers", "operStatus");
				searchBean.setOpers(StatusUtils.queryStatus("RebuyAccountController", "accountSubmitCallback", "1"));
				List<RebuyBillInfo> billList = rebuyBillDao.getRebuyBillListBySearchBean(searchBean);
				
				if(billList==null || billList.size()==0){
					throw new BizAppException("未查到该票据");
				}
				RebuyBillInfo bill = billList.get(0);
				
				if(isSuccess){//签收成功
					
					bill.setPayTradeNo(rgctBill.getHist().getPayTradeNo());//支付交易序号
					String afterStatus = StatusUtils.handleStatus("RebuyAccountController", "accountSubmitCallback", "1", bill.getOperStatus());
					bill.setOperStatus(afterStatus); //票据状态：记账通过
				}else{//签收失败
					String afterStatus = StatusUtils.handleStatus("RebuyAccountController", "accountSubmitCallback", "0", bill.getOperStatus());
					bill.setOperStatus(afterStatus); //票据状态：审核通过
				}	
				
				rebuyBillDao.modifyRebuyBillInfo(bill);
				
				//上锁，避免后续流程查到该票，确认记账后解锁
				RgctBillHist hist = rgctBill.getHist();
				hist.setIsLock(CommonConst.LOCK_YES);
				rcBaseService.updateRgctBillHist(hist);
				
			}
		}catch(Exception e){
			throw new BizAppException(e);
		}
	}

	@Override
	public void transFor031execute(RgctBill rgctBill, String isSign)
			throws BizAppException {
		
	}

	@Override
	public void transFor032execute(RgctBill rgctBill) throws BizAppException {
	}

	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		
	}

	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		// TODO Auto-generated method stub
		
	}

}
