package com.herongtech.console.web.busicontroller.print;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.subcoll.ISubcollService;

/**
 * @author 李江涛
 * 托收凭证打印   根据tsubcoll_bill_info  的主键subcollmxId查询出记录
 */
public class SubCollPzOtherPrint extends AbstractPrint{

	@Override
	public List<PublicItemBean<SubBean>> getPrintList(String ids, String batchId,String handleType) throws Exception {
		
		List<PublicItemBean<SubBean>> list = new ArrayList<PublicItemBean<SubBean>>();
		List<SubBean> beanList = new ArrayList<SubBean>();
		PublicItemBean<SubBean> pibean = new PublicItemBean<SubBean>();	

		ISubcollService service = ServiceFactory.getSubcollService();
		String[] repeatId = null;
		if(!StringUtils.isEmpty(repeatIds)){ 
			repeatId = repeatIds.split(",");
		}
		Set repeatIdSet = new HashSet();
		if(repeatId!=null){
			for(int i=0;i<repeatId.length;i++){
				repeatIdSet.add(repeatId[i]);
			}
		}
		try{
			//ids为 发托清单的ids
				String[] id = ids.split(",");
				for (int i = 0; i < id.length; i++) {
					SubcollBillInfo billInfo = service.getSubcollBillInfobyid(id[i]);
					SubBean subBean = new SubBean();
					copyPropertys(subBean, billInfo,repeatIdSet);
					beanList.add(subBean);
				}
			
			pibean.setItemList(beanList);
			list.add(pibean);
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return list;
	}
	public void copyPropertys(SubBean subBean, SubcollBillInfo billInfo,Set repeatIdSet) {
		UserLoginfo userloginfo = ResourceUtil.getSessionLoginfo();
		subBean.setBillClass(billInfo.getBillClass());
		subBean.setBillType(billInfo.getBillType());
		String[] times = DateTimeUtil.getYear_Month_Day_Date(new Date());//截取日期
		subBean.setYear(times[0]);//托收日期(年)
		subBean.setMonth(times[1]);//托收日期(月)
		subBean.setDay(times[2]);//托收日期(日)
		
		//付款人全称	
		//2012-8-1 ；柳    纸票 取 remitterCustBank;出票人开户行
		//电票 取 承兑人
		//2012-8-29 统一取承兑人， 在录入的时候自己区分 纸票电票  商承银承
		//2012-10-07 纸票、银承取 付款行全称  其他取承兑人
		if("1".equals(billInfo.getBillClass())&&"1".equals(billInfo.getBillType())){
			subBean.setOutBillPerson(billInfo.getRemitterBankName());
		}else{
			subBean.setOutBillPerson(billInfo.getAcceptor());	
		}	
		//付款人账号  只显示 商承
		if("2".equals(billInfo.getBillType())){
			subBean.setPayAccount(billInfo.getRemitterAcct());	
		}		
		subBean.setOutBillBank(billInfo.getDraweeBankNo());//付款人开户行
		
		//凭证收款人信息指发托人信息
		//2012-9-6 票管、票据池 直接就是custName ,其他的去当前登录柜员的开户机构
	
		subBean.setPayee(userloginfo.getCollBrchName());//收款人全称（没票据管家 ）
	
		
		if(repeatIdSet.contains(billInfo.getSubcollmxId())){
			subBean.setPayeeAccount(billInfo.getInAccount()+billInfo.getBelongCustNo());//收款人帐号
		}else{
			subBean.setPayeeAccount(billInfo.getPayeeAcct());//收款人帐号
		}
		
		
		subBean.setPayeeBankName(userloginfo.getCollBrchName());//收款人开户行
		
				
		subBean.setBillNo(billInfo.getBillNo());//票号
		Double billMoney = billInfo.getBillMoney();
		String billMoneyStr = CommUtils.NumberFormat5(billMoney);
		if(billMoneyStr.length()>11){
			int len = billMoneyStr.length();
			len=len-12;					
			String beStr = billMoneyStr.substring(0,len);
			billMoneyStr = billMoneyStr.substring(len,billMoneyStr.length());
			subBean.setMoneyFlag("￥"+beStr);
		}else{
			billMoneyStr="￥"+billMoneyStr;
		}
		subBean.setBillAmountString(CommUtils.formateNumberToTableOut4(billMoneyStr,"14","30","center"));
		subBean.setBillAmountBig(MoneyUtils.toUpper(billMoney));//大写金额
		//票据来源
		subBean.setBillSource(billInfo.getBillSource());
		subBean.setBillSourceString(billInfo.getBillSourceString());
		//票面到期日  凭证 备注使用
		subBean.setGaleDate(billInfo.getDueDt());
	}
	
}
