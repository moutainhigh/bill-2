package com.herongtech.console.web.busicontroller.print;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.saleback.bean.SalebackApplyInfo;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.domain.saleback.dao.SalebackApplyInfoDao;
import com.herongtech.console.domain.saleback.dao.SalebackBillInfoDao;



/**
 * @author ljtmq
 * 返售清单打印
 */
public class SaleBackListPrint extends AbstractPrint{

	@SuppressWarnings("unchecked")
	@Override
	public List getPrintList(String ids, String batchId, String handleType)
			throws Exception {
		SalebackApplyInfoDao applydao = new SalebackApplyInfoDao();
		SalebackBillInfoDao billdao = new SalebackBillInfoDao();
		List list = new ArrayList();
		List beanlist = new ArrayList();
		List<SaleBackBillBean> retList=new ArrayList<SaleBackBillBean>();
		HeadPrintBean hp = new HeadPrintBean() ;
		PublicItemBean piBean = new PublicItemBean();
		//批次信息		
		SalebackApplyInfo appInfo=null;
		double allBillMoney = 0.0;//总金额
		double allPayMoney = 0.0;//总实付金额
		double allInterest = 0.0;//总利息
		
		
		appInfo = applydao.getSalebackApplyInfo(batchId);
		
		int size=0;
		
		List<SalebackBillInfo> billlist = billdao.getConfirmReceiveBillForId(ids);
		
		for (int i = 0; i < billlist.size(); i++) {
			SalebackBillInfo info =billlist.get(i);
			SaleBackBillBean bean=new SaleBackBillBean();
			bean.setSequence(i+1);
			copyProperty(bean,info);
			bean.setInterestCalDays(info.getInterestDays());
			allBillMoney = CommUtils.addForMoney(allBillMoney, bean.getBillAmount());//总金额
			allPayMoney = CommUtils.addForMoney(allPayMoney, bean.getSalebackMoney());//总实付金额
			allInterest = CommUtils.addForMoney(allInterest, bean.getInterest());//总利息
			retList.add(bean);
		}
		size=billlist.size();
	
		copyProperties(hp,appInfo);	
		hp.setNo(Long.valueOf(size));
		hp.setAllBillMoneyString(CommUtils.NumberFormat3(allBillMoney));
		hp.setAllPayMoneyString(CommUtils.NumberFormat3(allPayMoney));
		hp.setAllInterest(CommUtils.NumberFormat3(allInterest));
		piBean.setHeadPrintBean(hp);
		piBean.setItemList(retList);
		list.add(piBean);
		return list;
		
	}
	public void copyProperties(HeadPrintBean hp, SalebackApplyInfo appInfo){
		hp.setBillClass(appInfo.getBillClass());
		hp.setBillClassString(appInfo.getBillClass().equals("1")?"纸票":"电票");
		hp.setBillType(appInfo.getBillType());
		hp.setBillTypeString(appInfo.getBillType().equals("1")?"银票":"商票");
		//交易对手名称
		hp.setAimbrchName(appInfo.getCustName());
		
		//入账账号
		hp.setInnerAccount(appInfo.getInnerAccount());	//往来账号
		if("1".equals(isAdd)){//补打
			hp.setInnerAccount(appInfo.getInAcctNo());			
		}
		//批次号
		hp.setApplyNo(appInfo.getBatchNo());
		//贴现类型
		hp.setProdNoString(appInfo.getProdNoString());
		//实际赎回日
		hp.setDueDt(appInfo.getCreateDt());
		//赎回利率
		hp.setRate(CommUtils.doubleFormateForString(appInfo.getRate()));
		//赎回开放日(买入返售开放日)
		hp.setSalebackOpenDt(appInfo.getSalebackOpenDt());
		//赎回截止日 (返售到期日)
		hp.setCreateTime(appInfo.getSalebackDueDt());
		//结算方式
		hp.setQsTypeString(appInfo.getIsOnline().equals("1")?"线上清算":"线下清算");
		hp.setForbiddenFlagStr("可转让");
	}
	public void copyProperty(SaleBackBillBean bean,SalebackBillInfo info){
		//票号
		bean.setBillNo(MoneyUtils.billNoSubString(info.getBillNo()));
		//票面到期日
		if(info.getDueDt()!=null){
			bean.setEndBillDate(info.getDueDt());
		}
		//计息到期日
		bean.setGaleDate(info.getSalebackDueDt());

		//计息天数
//		bean.setInterestCalDays(info.getInterestCalDays());
		//票面金额
		bean.setBillAmount(info.getBillMoney());
		bean.setBillAmountString(CommUtils.NumberFormat3(info.getBillMoney()));
		//应返还利息
		bean.setInterest(info.getInterest());
		bean.setInterestString(CommUtils.NumberFormat3(info.getInterest()));
		//实收金额
		bean.setSalebackMoney(info.getSalebackMoney());
		bean.setSalebackMoneyString(CommUtils.NumberFormat3(info.getSalebackMoney()));
		//承兑人
		bean.setAcceptor(info.getAcceptor());
	}

}
