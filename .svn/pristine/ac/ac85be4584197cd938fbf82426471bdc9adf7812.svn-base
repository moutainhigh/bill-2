package com.herongtech.console.web.busicontroller.print;


import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.dao.SaleApplyInfoDao;
import com.herongtech.console.domain.sale.dao.SaleBillInfoDao;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;
/**
 * 转贴现转出
 *电票签收确认 清单
 *只打印签收的
 *1060301 
 */

public class SaleListPrint extends AbstractPrint {

	@SuppressWarnings("unchecked")
	public List getPrintList(String ids,String batch_id,String handleType) throws Exception {
		SaleBillInfoDao billdao = new SaleBillInfoDao();
		SaleApplyInfoDao applydao = new SaleApplyInfoDao();
		List list = new ArrayList();
		List beanList = new ArrayList();
		HeadPrintBean hp = new HeadPrintBean();
		PublicItemBean pi = new PublicItemBean();
		SaleBillInfo info = null;
		SaleApplyInfo appInfo = null;
		// 票面总金额
		double totalBillAmount = 0.0;
		// 总实付金额
		double totalPayMoney = 0.0;
		// 总利息
		double totalInvest = 0.0;

		appInfo = applydao.getSaleApplyInfo(batch_id);
		List<SaleBillInfo> infoList = billdao.getSaleBillInfoByIds(ids.split(","));
		if (appInfo==null||infoList.isEmpty()) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持该操作");
		}
		for (int i = 0; i < infoList.size(); i++) {
			info = infoList.get(i);
			SaleInfoBean saleBean = new SaleInfoBean();
			saleBean.setSequence(i + 1);// 序号
			copyProperties(saleBean, info);
			// 总票面金额
			if (info.getBillMoney() >=0) {
				totalBillAmount = CommUtils.addForMoney(totalBillAmount,info.getBillMoney());
			}
			if (info.getReceiveMoney() >= 0) {
				// 实收金额
				totalPayMoney = CommUtils.addForMoney(totalPayMoney,info.getReceiveMoney());
			}
			if (info.getInterest()>=0) {
				// 总利息
				totalInvest = CommUtils.addForMoney(totalInvest,info.getInterest());
			}
			beanList.add(saleBean);
		}
		copyHeadProperties(hp,appInfo);
		hp.setTopic("转  出  "+handleType+ " 清  单");
		// 票据张数
		hp.setNo(Long.valueOf(infoList.size()));
		// 总票面金额
		hp.setAllBillMoneyString(MoneyUtils.doubleToFormatStr(totalBillAmount));
		// 总实收金额
		hp.setAllPayMoneyString(MoneyUtils.doubleToFormatStr(totalPayMoney));
		// 总利息
		hp.setAllInterest(MoneyUtils.doubleToFormatStr(totalInvest));
		pi.setItemList(beanList);
		pi.setHeadPrintBean(hp);
		list.add(pi);
		return list;
		
	}

	public void copyHeadProperties(HeadPrintBean hp, SaleApplyInfo appInfo) {

		// 转卖银行
		hp.setAimBankName(appInfo.getCustName());
		// 往来账号
		hp.setInAcctNo(appInfo.getInAcctNo());
		// 批次号
		hp.setApplyNo(appInfo.getSaleId());
		// 票据类型、种类
		hp.setBillClass(appInfo.getBillClass());
		hp.setBillClassString(appInfo.getBillClass().equals("1")?"纸票":"电票");
		hp.setBillType(appInfo.getBillType());
		hp.setBillTypeString(appInfo.getBillType().equals("1")?"银票":"商票");
		// 卖出类型
		hp.setProdNoString(appInfo.getProdNoString());
		// 转出起息日，（转卖日）
		hp.setSaleDtString(appInfo.getSaleDt());
		// 转卖利率
		hp.setRate(CommUtils.doubleFormateForString(appInfo.getRate()));
		// 赎回开放日
		if (appInfo.getBuybackOpenDt() != null) {
			hp.setRebuyOpenDt(appInfo.getBuybackOpenDt());
		}
		// 赎回截止日
		if (appInfo.getRebuyDueDt() != null) {
			hp.setRebuyDueDt(appInfo.getRebuyDueDt());
		}
		// 清算方式
		hp.setQsTypeString(appInfo.getIsOnline().equals("1")?"线上清算":"线下清算");
		// 不得转让标记
		hp.setForbiddenFlagStr(appInfo.getForbidFlag().equals("1")?"不可转让":"可转让");
	}

	public void copyProperties(SaleInfoBean saleBean, SaleBillInfo info)
			throws Exception {
		// 票号
		saleBean.setBillNo(MoneyUtils.billNoSubString(info.getBillNo()));
		// 票面到期日
		if (info.getDueDt() != null) {
			saleBean.setEndBillDate(info.getDueDt());
		}
		// 计息到期日
		if (info.getGaleDate() == null) {
			saleBean.setEndCalulateInherest("");
		} else {
			saleBean.setGaleDate(info.getGaleDate());// 计息到期日
		}
		// 计息天数
		saleBean.setInterestCalDays(info.getInterestDays());
		// 票面金额
		saleBean.setBillAmount(info.getBillMoney());
		saleBean.setBillAmountString(CommUtils.doubleFormateForString(info.getBillMoney()));
		// 应付利息
		saleBean.setInterest(info.getInterest());
		saleBean.setInterestStr(MoneyUtils.doubleToFormatStr(info.getInterest()));
		// 实收金额
		saleBean.setPayMoney(info.getReceiveMoney());
		saleBean.setPayMoneyString(MoneyUtils.doubleToFormatStr(info
				.getReceiveMoney()));
		// 承兑人
		saleBean.setAcceptor(info.getAcceptor());
	}

}
