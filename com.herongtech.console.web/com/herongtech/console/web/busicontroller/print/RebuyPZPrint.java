package com.herongtech.console.web.busicontroller.print;


import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;


/**转贴现转入 凭证打印*/
public class RebuyPZPrint extends AbstractPrint{

	@SuppressWarnings("unchecked")
	@Override
	public List getPrintList(String ids, String batch_id,String handleType) throws Exception {
		RebuyApplyInfo apply = new RebuyApplyInfo();//转入申请批次
//		RebuyBean rebuyBean = null;
		RebuyBillInfo info = null;
		PublicItemBean piBean = new PublicItemBean();//打印最终BEAN;
		List beanList = new ArrayList();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();		
		try{
			String[] billIds = ids.split(",");
			
			apply= rebuyService.getApplyInfoById(batch_id);
			List list = new ArrayList();
			
			for (int i = 0; i < billIds.length; i++) {
				info = rebuyService.getBillInfoByRebuymxId(billIds[i]);
				RebuyBean rebuyBean = new RebuyBean();
				copyProperty(rebuyBean,info);
				
				//交易对手账号/入账账号 				
				rebuyBean.setApplicant(apply.getCustName());
				rebuyBean.setApplicantAccount(apply.getTradeAcct());
				//rebuyBean.setApplicantBankName(apply.getCustBankName());
				//2012-07-26 修改  焦
				if(apply.getTradeAcctOrg()!=null){
					Branch  brch =ServiceFactory.getBranchService().getBranch(apply.getTradeAcctOrg());
					if(brch!=null){
						rebuyBean.setApplicantBankName(brch.getBranchName());
					}
				}
				
				//贴现利率
				rebuyBean.setDiscRate(CommUtils.doubleFormateForString(apply.getRate()));
				
				String time =apply.getRebuyDt();//买入日（贴现日）
				if(time != null && time.length()>0){
					String[] times = time.split("-");
					rebuyBean.setYear(times[0]);//贴现日(年)
					rebuyBean.setMonth(times[1]);//贴现日(月)
					rebuyBean.setDay(times[2]);//贴现日(日)
				}
				if(RebuyCodeConst.ELEC_DEPOSIT_YES.equals(info.getIsElecDeposit())){
					rebuyBean.setElecDepositString("电票托管,非真实付款");
				}
				beanList.add(rebuyBean);
			}
			//汇总打印
			if("1".equals(isSum)&&beanList.size()>0){
				
				RebuyBean tmpBean=(RebuyBean)beanList.get(0);
				tmpBean.setOutBillDate(null);
				tmpBean.setEndBillDate(null);
				tmpBean.setBillNo(null);
				//承兑人
				tmpBean.setAcceptor(null);
				//承兑人开户行
				tmpBean.setOutBillBank(null);
				
				//票据总金额
				double allBillAmount=tmpBean.getBillAmount();
				//贴现利息
				Double allInvest=tmpBean.getInterest();
				//实付金额
				Double allPayMoney=tmpBean.getPayMoney();
				for(int i=1;i<beanList.size();i++){
					RebuyBean tmp=(RebuyBean)beanList.get(i);
					allBillAmount = CommUtils.addForMoney(allBillAmount,tmp.getBillAmount());
					allInvest = CommUtils.addForMoney(allInvest,tmp.getInterest());
					allPayMoney= CommUtils.addForMoney(allPayMoney,tmp.getPayMoney());
				}
				tmpBean.setBillAmountBig(MoneyUtils.toUpper(allBillAmount));
				String billAmouStr = CommUtils.NumberFormat5(allBillAmount);
				if(billAmouStr.length()>10){
					int len = billAmouStr.length();
					len=len-11;					
					String beStr = billAmouStr.substring(0,len);
					billAmouStr = billAmouStr.substring(len,billAmouStr.length());
					tmpBean.setMoneyFlag("￥"+beStr);
				}else{
					tmpBean.setMoneyFlag("");
					billAmouStr="￥"+billAmouStr;
				}
				//小写票面金额
				tmpBean.setBillAmountString(CommUtils.formateNumberToTableOut1(billAmouStr,"19","30","center"));
				//贴现利息
				String intStr = CommUtils.NumberFormat5(allInvest);
				if(intStr.length()>10){
					int len = intStr.length();
					len=len-11;					
					String beStr = intStr.substring(0,len);
					intStr = intStr.substring(len,intStr.length());
					tmpBean.setInvMoneyFlag("￥"+beStr);
				}else{
					tmpBean.setInvMoneyFlag("");
					intStr="￥"+intStr;
				}
				tmpBean.setInterestString(CommUtils.formateNumberToTableOut1(intStr,"19","30","center"));
				//实付贴现金额
				String payStr = CommUtils.NumberFormat5(allPayMoney);
				if(payStr.length()>10){					
					int len = payStr.length();
					len=len-11;					
					String beStr = payStr.substring(0,len);
					payStr = payStr.substring(len,payStr.length());
					tmpBean.setPayMoneyFlag("￥"+beStr);
				}else{
					tmpBean.setPayMoneyFlag("");
					payStr="￥"+payStr;
				}
				tmpBean.setPayMoneyString(CommUtils.formateNumberToTableOut1(payStr,"19","30","center"));
				beanList.clear();
				beanList.add(tmpBean);
			}
			piBean.setItemList(beanList);
			list.add(piBean);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	//复制汇票信息
	public void copyProperty(RebuyBean rebuyBean,RebuyBillInfo info){
		rebuyBean.setBillClass(info.getBillClass());
		rebuyBean.setBillType(info.getBillType());
		//票据种类
		rebuyBean.setBillTypeString(info.getBillTypeString());
		//票号
		rebuyBean.setBillNo(info.getBillNo());		
		//到期日
		rebuyBean.setEndBillDate(info.getDueDt());
		//出票日
		rebuyBean.setOutBillDate(info.getIssueDt());
		
		//大写票面金额
		rebuyBean.setBillAmountBig(MoneyUtils.toUpper(info.getBillMoney()));
		rebuyBean.setBillAmount(info.getBillMoney());
		String billAmouStr = CommUtils.NumberFormat5(info.getBillMoney());
		if(billAmouStr.length()>10){
			int len = billAmouStr.length();
			len=len-11;					
			String beStr = billAmouStr.substring(0,len);
			billAmouStr = billAmouStr.substring(len,billAmouStr.length());
			rebuyBean.setMoneyFlag("￥"+beStr);
		}else{
			rebuyBean.setMoneyFlag("");
			billAmouStr="￥"+billAmouStr;
		}
		//小写票面金额
		rebuyBean.setBillAmountString(CommUtils.formateNumberToTableOut1(billAmouStr,"19","30","center"));
		
		//承兑人
		rebuyBean.setAcceptor(info.getAcceptor());
		//承兑人开户行
		rebuyBean.setOutBillBank(info.getRemitterBankName());
		rebuyBean.setOutBillAccount("");//承兑人帐号
		
		//贴现利息
		rebuyBean.setInterest(info.getCheckInterest());
		String intStr = CommUtils.NumberFormat5(info.getInterest());//以前是    CheckInterest  试算
		if(intStr.length()>10){
			int len = intStr.length();
			len=len-11;					
			String beStr = intStr.substring(0,len);
			intStr = intStr.substring(len,intStr.length());
			rebuyBean.setInvMoneyFlag("￥"+beStr);
		}else{
			rebuyBean.setInvMoneyFlag("");
			intStr="￥"+intStr;
		}
		rebuyBean.setInterestString(CommUtils.formateNumberToTableOut1(intStr,"19","30","center"));
		//实付贴现金额
		rebuyBean.setPayMoney(info.getCheckPayMoney());
		String payStr = CommUtils.NumberFormat5(info.getPayMoney());//getCheckPayMoney试算
		if(payStr.length()>10){
			int len = payStr.length();
			len=len-11;					
			String beStr = payStr.substring(0,len);
			payStr = payStr.substring(len,payStr.length());
			rebuyBean.setPayMoneyFlag("￥"+beStr);
		}else{
			rebuyBean.setPayMoneyFlag("");
			payStr="￥"+payStr;
		}		
		rebuyBean.setPayMoneyString(CommUtils.formateNumberToTableOut1(payStr,"19","30","center"));
	}
}
