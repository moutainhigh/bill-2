package com.herongtech.console.web.busicontroller.print;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;


/**转贴现转入申请岗 清单打印类*/
public class RebuyListPrint extends AbstractPrint{

	@SuppressWarnings("unchecked")
	@Override
	public List getPrintList(String ids, String batch_id,String handleType) throws Exception {
		double allBillMoney = 0.0;//总金额
		double allPayMoney = 0.0;//总实付金额
		double allInterest = 0.0;//总利息
		List list = new ArrayList();
		List beanList = new ArrayList();

		PublicItemBean piBean = new PublicItemBean();
		RebuyBillInfo info = null;
		RebuyApplyInfo apply = null;
		HeadPrintBean headBean = new HeadPrintBean();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();		
		try {
			String[] idds =ids.split(",");
			
			apply = rebuyService.getApplyInfoById(batch_id);
			long size = idds.length;
			for (int i = 0; i < idds.length; i++) {
				RebuyBean rebuyBean = new RebuyBean();
				info = rebuyService.getBillInfoByRebuymxId(idds[i]);
				copyProperty(rebuyBean, info);
				rebuyBean.setSequence(i + 1);//序号
				if (info.getGaleDate() == null) {
					rebuyBean.setGaleDate(" ");// 计息到期日
				} else {
					rebuyBean.setGaleDate(info.getGaleDate());// 计息到期日
				}
				if (info.getInterestDays() == null) {
					rebuyBean.setInterestCalDays(Long.valueOf(0)); // 计息天数
				} else {
					rebuyBean.setInterestCalDays(info.getInterestDays()); // 计息天数
				}
				allBillMoney = CommUtils.addForMoney(allBillMoney, info.getBillMoney());//总金额
				allPayMoney = CommUtils.addForMoney(allPayMoney, info.getPayMoney());//总实付金额
				allInterest = CommUtils.addForMoney(allInterest,  info.getBillMoney()-info.getPayMoney());//总利息
				beanList.add(rebuyBean);
			}
			copyPropertys(headBean, apply,info);
			headBean.setAllBillMoneyString(CommUtils.NumberFormat3(allBillMoney));//总金额
			headBean.setAllInterest(CommUtils.NumberFormat3(allInterest));//总利息
			headBean.setNo(size);//总张数
			headBean.setPayMoneyString(CommUtils.NumberFormat3(allPayMoney));//总实付金额
			headBean.setTopic("转 入 "+handleType+" 清 单");
			piBean.setHeadPrintBean(headBean);
			piBean.setItemList(beanList);
			list.add(piBean);
			return list;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void copyProperty(RebuyBean rebuyBean, RebuyBillInfo info) {
		rebuyBean.setBillNo(MoneyUtils.billNoSubString(info.getBillNo()));//票号
		rebuyBean.setOutBillDate(info.getIssueDt());//出票日
		rebuyBean.setEndBillDate(info.getDueDt());//到期日
		// 计息到期日
		if (info.getGaleDate() == null) {
			rebuyBean.setGaleDate("");
		} else {
			rebuyBean.setGaleDate(info.getGaleDate());// 计息到期日
		}
		// 计息天数
		if (info.getInterestDays() == null) {
			rebuyBean.setInterestCalDays(Long.valueOf(0)); // 计息天数
		} else {
			rebuyBean.setInterestCalDays(info.getInterestDays());
		}
		//票面金额
		rebuyBean.setBillAmountString(CommUtils.NumberFormat3(info.getBillMoney()));
		//贴现利息
		rebuyBean.setInterestString(CommUtils.NumberFormat3(info.getInterest()));
		//实付金额
		rebuyBean.setPayMoneyString(CommUtils.NumberFormat3(info.getPayMoney()));
		//承兑人
		rebuyBean.setAcceptor(info.getAcceptor());
		//出票人开户行
		if (StringUtils.isEmpty(info.getRemitterBankName())) {
			rebuyBean.setOutBillBank("");
		} else {
			rebuyBean.setOutBillBank(info.getRemitterBankName());//出票人开户行
		}
	}

	public void copyPropertys(HeadPrintBean headBean, RebuyApplyInfo apply,RebuyBillInfo info) {
		if (apply == null || headBean == null) {
			return;
		}
		//批次号
		headBean.setApplyNo(apply.getBatchNo());
		//赎回开放日
		if (apply.getResaleStaDt() == null) {
			headBean.setRebuyOpenDt("");
		} else {
			headBean.setRebuyOpenDt(apply.getResaleStaDt());
		}
		//赎回截止日：
		if (apply.getResaleDueDt() == null) {
			headBean.setRebuyDueDt("");
		} else {
			headBean.setRebuyDueDt(apply.getResaleDueDt());
		}

		// 交易对手全称
		headBean.setAimbrchName(apply.getCustBankName());
		//我行入账账号
		headBean.setTradeAcct(apply.getTradeAcct());

		//票据种类
		headBean.setBillClass(apply.getBillClass());
		headBean.setBillClassString(apply.getBillClassString());
		//票据类型
		headBean.setBillType(apply.getBillType());
		headBean.setBillTypeString(apply.getBillTypeString());
		//产品名称 转入类型
		headBean.setProdNoString(apply.getProdNoString());
		//转入日 
		if(apply.getRebuyDt()!=null){
			headBean.setRebuyDt(apply.getRebuyDt());
		}
		
		//贴现利率
		headBean.setRate(CommUtils.doubleFormateForString(apply.getRate()));

		//清算方式
		if ("1".equals(apply.getIsOnline())) {
			headBean.setQsTypeString("线上");
		} else {
			headBean.setQsTypeString("线下");
		}
		//不得转让标记
		headBean.setForbiddenFlagStr(info.getForbidFlagString());
	}
}
