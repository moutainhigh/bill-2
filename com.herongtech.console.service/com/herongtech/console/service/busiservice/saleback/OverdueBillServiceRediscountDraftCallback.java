package com.herongtech.console.service.busiservice.saleback;

import java.sql.SQLException;

import java.util.Date;
import java.util.List;

import org.apache.cxf.binding.corba.wsdl.Sequence;
import org.springframework.beans.BeanUtils;

import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.dao.BuybackBillInfoDao;

import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.dao.DiscBillInfoDao;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.dao.SaleBillInfoDao;
import com.herongtech.console.domain.saleback.dao.SalebackApplyInfoDao;
import com.herongtech.console.domain.saleback.dao.SalebackBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;

import com.herongtech.console.service.interfaces.ISaleBackService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.service.sequence.SequenceService;
import com.herongtech.console.web.busicontroller.common.BuybackCodeConst;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.console.web.busicontroller.common.SaleCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.dao.RgctBillHistDao;
import com.herongtech.rc.domain.dao.RgctBillInfoDao;
import com.herongtech.rc.service.rcservice.ISysTrigger;


/**
 * 返售  回购买断
 * @author 李江涛
 *
 */
public class OverdueBillServiceRediscountDraftCallback implements ISysTrigger{

	IRebuyService service = ServiceFactory.getRebuyService();
	RebuyBillInfoDao rebuydao = new RebuyBillInfoDao();
	SalebackApplyInfoDao salebackapplydao = new SalebackApplyInfoDao();
	SalebackBillInfoDao salebackbilldao = new SalebackBillInfoDao();
	ISaleBackService salebackservice = ServiceFactory.getSaleBackService();
	BuybackBillInfoDao buybackdao = new BuybackBillInfoDao();
	SaleBillInfoDao salebilldao = new SaleBillInfoDao();
	DiscBillInfoDao discbilldao = new DiscBillInfoDao();
	RgctBillInfoDao infodao = new RgctBillInfoDao();
	RgctBillHistDao histdao = new RgctBillHistDao();
	
	/** 
	 * 040返售逾期回调    判断是买入返售逾期还是卖出回购逾期 
	 */
	@Override
	public void execute(RgctBill rgctBill) throws BizAppException {
		RgctBillHist hist=rgctBill.getHist();
		String rgctId = rgctBill.getInfo().getId();
		if(RcConstants.BUY_OUT_REGRESS.equals(hist.getBuyType())||hist.getBuyType().equals(RcConstants.BUY_INNER_REGRESS)){//买入返售
			try {
				RebuyBillInfo oldrebuybill = rebuydao.getRebuyBillInfoByRgctId(hist.getRgctId());
				if(RebuyCodeConst.SALE_REBUY_TYPE_SALE.equals(oldrebuybill.getIsRegress())){
					return;
				}
				if(oldrebuybill!=null){//更改转入清单为逾期          并新增一条行的卖段转入清单
					oldrebuybill.setIsBuyback(RebuyCodeConst.RPD_COMPLETE);//是否回购完成
					oldrebuybill.setOperStatus(RebuyCodeConst.OVERDUE_BUYBACK_DATE);//逾期BD410
					oldrebuybill.setGathDate(DateTimeUtil.getWorkdayString());
					oldrebuybill.setGathType("4");//贴现赎回/同业返售/系统内返售(系统内回购记账)
					oldrebuybill.setCurStatus(RebuyCodeConst.CUR_STATUS_REBUY);//回购库存
					oldrebuybill.setTransType("1");//初始值  默认
					oldrebuybill.setTransId(null);
					rebuydao.modifyRebuyBillInfo(oldrebuybill);
					//专买断处理
					this.createNewRebuy(oldrebuybill,rgctBill);
					//账务库存 以后有再加（rebuymxId账务库存会用）
				}
				salebackservice.overdueBill(rgctId);//删除返售票据
				if(RebuyCodeConst.IS_INNER_TRUE.equals(rgctBill.getHist().getIfInner())){//系统内   处理转出方
					List<BuybackBillInfo> buybacklist = buybackdao.getBuybackBillInfoByRgctid(rgctId);
					if (!buybacklist.isEmpty()) {
						BuybackBillInfo billInfo=(BuybackBillInfo) buybacklist.get(0);
						billInfo.setOperStatus(BuybackCodeConst.BUYBACK_BILL_CODE_STATUS_REFUSE);
						buybackdao.modifyBuybackBillInfo(billInfo);		
					}
					List<SaleBillInfo> saleList = salebilldao.getSaleBillInfoByRgctId(rgctId);
					if (!saleList.isEmpty()) {//系统内需要更新转出方的当前状态为卖断					
						SaleBillInfo saleBillInfo=(SaleBillInfo) saleList.get(0);
						saleBillInfo.setCurStatus(RebuyCodeConst.CUR_STATUS_SALE);//卖段
						salebilldao.modifySaleBillInfo(saleBillInfo);
						//处理买入来源
						overdueBuyBill(saleBillInfo);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizAppException("040回调失败，"+e.getMessage());
			}
			
			
		}else{//账务库存为转出业务,则为卖出回购
			SaleBillInfo oldSale;
			try {
				oldSale = salebilldao.getSaleBillInfoByRgcId(hist.getRgctId());
				oldSale.setOperStatus(RebuyCodeConst.OVERDUE_BUYBACK_DATE);//BD410
				oldSale.setIsBuyback(RebuyCodeConst.RPD_COMPLETE);
				oldSale.setGathMneyDate(DateTimeUtil.getWorkday());
				oldSale.setCurStatus(RebuyCodeConst.CUR_STATUS_SALE);
				salebilldao.modifySaleBillInfo(oldSale);
				this.overdueBuybackBill(rgctId);//变状态回购
				createNewSale(oldSale, rgctBill);//建立行的转卖清单
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizAppException("回购逾期040回调失败，"+e.getMessage());
			}

		}
		
	}

	/**
	 * 040 转买断处理
	 * 
	 * @param rgctId
	 * @throws SQLException 
	 * @throws ServiceException
	 */
	public void overdueBuybackBill(String rgctId) throws SQLException{
		BuybackBillInfo bill = buybackdao.getSaleBillInfoByRgcId(rgctId);
		if (bill != null) {
			bill.setOperStatus(BuybackCodeConst.BUYBACK_BILL_CODE_STATUS_REFUSE);
			buybackdao.modifyBuybackBillInfo(bill);
		}
	}

	/**
	 * 根据老清单创建转买断清单,利率为0
	 * @param oldRebuy
	 * @return
	 * @throws Exception 
	 */
	private String createNewRebuy(RebuyBillInfo oldRebuy, RgctBill rgctBill) throws Exception{
		RebuyBillInfo newRebuy = new RebuyBillInfo();
		BeanUtils.copyProperties(oldRebuy, newRebuy);
		ISequenceService sequenceService=ServiceFactory.getSequenceService();	
		String signDt = rgctBill.getHist().getSignDt();
			try {
				newRebuy.setRebuymxId(sequenceService.getREBUY_BILL_INFO_ID());
				newRebuy.setRate(0.0);
				newRebuy.setInterest(0.0);//利息放0
				newRebuy.setInterestSaleback1(0.0);
				newRebuy.setCheckInterest(0.0);
				newRebuy.setCheckPayMoney(0.0);
				newRebuy.setPayMoney(0.0);
				newRebuy.setPaymoneySaleback1(0.0);
				newRebuy.setTotalIntrstPayment(0.0);
				newRebuy.setIsBuyback(RebuyCodeConst.RPD_NO_COMPLETE);
				newRebuy.setIsRegress(RebuyCodeConst.SALE_REBUY_TYPE_SALE);//卖段
				newRebuy.setOperStatus(StatusUtils.handleStatusNoCheck("RebuyAccountController", "accountConfirmSubmitElec", null));// 记账通过BS140
				newRebuy.setAccountDate(signDt);
				newRebuy.setBeginDate(null);
				newRebuy.setCollDate(null);
				newRebuy.setGathDate(null);
				newRebuy.setGathType("1");//记账完成
				newRebuy.setRebuyDt(signDt);
				newRebuy.setResaleDueDt(null);
				newRebuy.setResaleStaDt(null);
				rebuydao.addRebuyBillInfo(newRebuy);
			} catch (BizAppException e) {
				
				e.printStackTrace();
				throw new BizAppException("创建新清单失败，"+e.getMessage());
			}
		return  newRebuy.getRebuymxId();
	}
	
	/**
	 * 根据老清单创建转卖断清单,利率为0
	 * @param oldSale
	 * @return
	 * @throws Exception 
	 */
	private void createNewSale(SaleBillInfo oldSale, RgctBill rgctBill) throws Exception {
		SaleBillInfo newSale = new SaleBillInfo();
		ISequenceService sequence = ServiceFactory.getSequenceService();
			String signDate = rgctBill.getHist().getSignDt();
			BeanUtils.copyProperties(oldSale, newSale);
			newSale.setSalemxId(sequence.getSALE_BILL_INFO_ID());
			newSale.setRate(0.0);
			newSale.setInterest(0.0);//利息放0
			newSale.setInterestBuyback(0.0);
			newSale.setBuybackMoney(0.0);
			newSale.setBuyMoney(0.0);
			newSale.setOldInterest(0.0);
			newSale.setOldRate(0.0);
			newSale.setReceiveMoney(0.0);
			
			newSale.setIsBuyback(SaleCodeConst.RPD_NO_COMPLETE);
			newSale.setIsDelayIn(SaleCodeConst.IS_DELAY_IN_NO);
			newSale.setOperStatus(StatusUtils.handleStatusNoCheck("SaleAccountController", "elecSaleAccount", null));//转卖记账完成状态BS236
			newSale.setAccountDate(signDate);
			newSale.setAccountDate(signDate);//hhx2014-09-12
			newSale.setCreateTime(signDate);
			newSale.setGaleDate(null);
			newSale.setOldGaleDate(null);
			newSale.setResaleDueDt(null);
			newSale.setResaleOpenDt(null);
		    salebilldao.addSaleBillInfo(newSale);
	}
	/**
	 * @param saleBillInfo
	 * @throws BizAppException 
	 */
	private void overdueBuyBill(SaleBillInfo saleBillInfo) throws BizAppException {
			try {
				if("1".equals(saleBillInfo.getPriorSrc())){
					DiscBillInfo  disc  =  discbilldao.getDiscBillInfo(saleBillInfo.getPriorSrcId());
					disc.setGathMneyType("2");
					disc.setGathMneyDate(DateTimeUtil.getWorkday());
					disc.setTransType("2");
					disc.setTransId(saleBillInfo.getSaleId());
					disc.setCurStatus("5");//卖断
					discbilldao.modifyDiscBillInfo(disc);
				}else{
					RebuyBillInfo rebuy = rebuydao.getRebuyBillInfo(saleBillInfo.getPriorSrcId());
					rebuy.setGathType("2");
					rebuy.setGathDate(DateTimeUtil.getWorkday());
					rebuy.setTransType("2");
					rebuy.setTransId(saleBillInfo.getSaleId());
					rebuy.setCurStatus("5");//卖断
					rebuydao.modifyRebuyBillInfo(rebuy);
				}
			} catch (BizAppException e) {
				throw new BizAppException("更新清单失败，"+e.getMessage());
			} catch (SQLException e) {
				throw new BizAppException("更新清单失败，"+e.getMessage());
			}
	}
}
