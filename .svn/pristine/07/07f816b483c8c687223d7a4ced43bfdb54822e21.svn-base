package com.herongtech.console.web.busicontroller.print;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.domain.buyback.bean.BuybackApplyInfo;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.dao.BuybackApplyInfoDao;
import com.herongtech.console.domain.buyback.dao.BuybackBillInfoDao;

/**
 * @author ljtmq
 * 到期回购打印
 */
public class BuybackListPrint extends AbstractPrint{

	@SuppressWarnings("unchecked")
	@Override
	public List getPrintList(String ids, String batchId, String handleType)
			throws Exception {
		
		HeadPrintBean hp=new HeadPrintBean();
		PublicItemBean piBean = new PublicItemBean();//显示bean
		BuybackApplyInfoDao applydao = new BuybackApplyInfoDao(); 
		BuybackBillInfoDao billdao = new BuybackBillInfoDao();
		//批次信息		
		BuybackApplyInfo appInfo=null;
		List<BuybackBillBean> backBeanList = new ArrayList<BuybackBillBean>();
		List list = new ArrayList();
		double allBillMoney = 0.0;//总金额
		double allPayMoney = 0.0;//总实付金额
		double allInterest = 0.0;//应返还总利息

		//批次信息
		appInfo = applydao.getBuybackApplyInfo(batchId,null);
		copyProperties(hp,appInfo);
			
		//电子票据打印
		//BuybackBillInfo  
		List<BuybackBillInfo> billlist = billdao.getBuybackBillListByIds(ids);
		for(int i=0;i<billlist.size();i++){
			BuybackBillInfo backBillInfo = billlist.get(i);
			//打印对象
			BuybackBillBean bean = new BuybackBillBean();
			copyProperty(bean ,backBillInfo,appInfo);
			bean.setInterestCalDays(backBillInfo.getInterestDays());
			allBillMoney=CommUtils.addForMoney(allBillMoney, bean.getBillAmount());
			allInterest=CommUtils.addForMoney(allInterest, bean.getInterest());
			allPayMoney = CommUtils.addForMoney(allPayMoney, bean.getBuybackMoney());
			bean.setSequence(i+1);
			backBeanList.add(bean);
		}
		hp.setAllBillMoneyString(CommUtils.NumberFormat3(allBillMoney));
		hp.setAllInterest(CommUtils.NumberFormat3(allInterest));
		hp.setAllPayMoneyString(CommUtils.NumberFormat3(allPayMoney));
		hp.setNo(Long.valueOf(backBeanList.size()));
		piBean.setItemList(backBeanList);
		piBean.setHeadPrintBean(hp);
		list.add(piBean);
		return list;
		
	}
	public void copyProperties(HeadPrintBean hp, BuybackApplyInfo appInfo){
		
		//类型、种类
		hp.setBillClass(appInfo.getBillClass());
		hp.setBillClassString(appInfo.getBillClass().equals("1")?"纸票":"电票");
		hp.setBillType(appInfo.getBillType());
		hp.setBillTypeString(appInfo.getBillType().equals("1")?"银票":"商票");
		
		//转卖银行
		hp.setAimbrchName(appInfo.getCustName());		
		//往来账号
		hp.setInnerAccount(appInfo.getInnerAccount());
		
		//批次号
		hp.setApplyNo(appInfo.getBatchNo());
		//贴现类型
		hp.setProdNoString(appInfo.getProdNoString());
		//实际赎回日
		if(appInfo.getCreateDt()!=null){
			hp.setDueDt(appInfo.getCreateDt());
		}
		//赎回利率
		hp.setRate(CommUtils.doubleFormateForString(appInfo.getBuybackRate()));
		
		//赎回开放日(买入返售开放日)
		if(appInfo.getBuybackOpenDt()!=null){
			hp.setRebuyOpenDt(appInfo.getBuybackOpenDt());
		}
		//赎回截止日 (返售到期日)
		if(appInfo.getBuybackDueDt()!=null){
			hp.setRebuyDueDt(appInfo.getBuybackDueDt());
		}
		//结算方式
		hp.setQsTypeString(appInfo.getIsOnline().equals("1")?"线上清算":"线下清算");
		hp.setForbiddenFlagStr("可转让");
	}
	public void copyProperty(BuybackBillBean bean,BuybackBillInfo info, BuybackApplyInfo appInfo){
		//票号
		bean.setBillNo(info.getBillNo());
		//出票日
		bean.setOutBillDate(info.getIssueDt());
		//票面到期日
		if(info.getDueDt()!=null){
			bean.setEndBillDate(info.getDueDt());
		}
		//计息到期日
		bean.setGaleDate(appInfo.getBuybackDueDt());
		//票面金额
		bean.setBillAmount(info.getBillMoney());
		bean.setBillAmountString(CommUtils.NumberFormat3(info.getBillMoney()));
		//利息
		bean.setInterest(info.getInterest());
		bean.setInterestString(CommUtils.NumberFormat3(info.getInterest()));
		//实付金额
		bean.setBuybackMoney(info.getBuybackMoney());
		bean.setBuybackMoneyString(CommUtils.NumberFormat3(info.getBuybackMoney()));
		//承兑人
		bean.setAcceptor(info.getAcceptor());
	}
	
}
