package com.herongtech.console.web.busicontroller.print;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;


import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.dao.DiscApplyInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.disc.IDiscService;

import com.herongtech.exception.impl.BizAppException;

public class DiscPrint extends AbstractPrint {
	/**
	 * 贴现凭证打印
	 * @author qzs
	 */
	@Override
	public List getPrintList(String ids, String batch_id,String handleType)
			throws Exception {
		
		String[] billIds = ids.split(",");
		List list = new ArrayList();
		PublicItemBean piBean =	new PublicItemBean();//显示bean
		List beanList = new ArrayList();
		try {
			//IPublicPrintService service=BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean("PublicPrintService");
			IDiscService service = ServiceFactory.getDiscService();
			List<DiscBillInfo> billInfoList =service.getDiscBillInfolistbyids(ids);
			DiscBillInfo billInfo = billInfoList.get(0);
			
			DiscApplyInfoDao dao = new DiscApplyInfoDao();
			DiscApplyInfo apply=dao.getDiscApplyInfo(billInfo.getDiscId(),null);
			if("1".equals(this.getIsAdd())){//凭证补打
				String payType = apply.getPayType();//付息方式 1买 2卖 3协议     1、3 第三方付息凭证
				String[] mudleIds = this.getModuleId().split(",");//moduleId=1030301,1030102
				if("2".equals(payType)){//卖方付息
					this.setModuleId(mudleIds[1]);
				}else{
					this.setModuleId(mudleIds[0]);
				}
			}
			
			
			for (int i = 0; i < billInfoList.size(); i++) {
				DiscBean discBean = new DiscBean();//贴现属性bean
				DiscBillInfo discBillInfo = ((DiscBillInfo) (billInfoList.get(i)));
				copyProperties(discBean, discBillInfo,apply);
				beanList.add(discBean);
			}
			//汇总打印使用 董大鹏 增加 2012-5-2
			if("1".equals(this.isSum)){
				DiscBean tempBean=(DiscBean)beanList.get(0);
				//进行金额汇总计算
				for(int i=1;i<beanList.size();i++){
					DiscBean tmp=(DiscBean)beanList.get(i);
					if(tempBean.getPayType()!=null && !"2".equals(tempBean.getPayType())){
						//买方金额
						tempBean.setBuyerInterestMoney(CommUtils.addForMoney(tempBean.getBuyerInterestMoney(),tmp.getBuyerInterestMoney()));
						//卖方金额
						tempBean.setSalerInterestMoney(CommUtils.addForMoney(tempBean.getSalerInterestMoney(),tmp.getSalerInterestMoney()));
					}
					//汇票金额 
					tempBean.setBillAmount(CommUtils.addForMoney(tempBean.getBillAmount(),tmp.getBillAmount()));
					//贴现利息
					tempBean.setInterestMoney(CommUtils.addForMoney(tempBean.getInterestMoney(),tmp.getInterestMoney()));
					//实付 贴现金额
					tempBean.setPayMoney(CommUtils.addForMoney(tempBean.getPayMoney(),tmp.getPayMoney()));					
				}
				//贴现利息
				tempBean.setInterest(CommUtils.NumberFormat3(tempBean.getInterestMoney()));
				//行号
				tempBean.setBillNo("");
				
				//利息
				String intStr=CommUtils.NumberFormat5(tempBean.getInterestMoney());
				if(intStr.length()>10){
					int len = intStr.length();
					len=len-11;					
					String beStr = intStr.substring(0,len);
					intStr = intStr.substring(len,intStr.length());
					tempBean.setInstMoneyFlag("￥"+beStr);
				}else{
					tempBean.setInstMoneyFlag("");
					intStr="￥"+intStr;
				}
				//汇票金额
				String billStr=CommUtils.NumberFormat5(tempBean.getBillAmount());
				if(billStr.length()>10){
					int len = billStr.length();
					len=len-11;					
					String beStr = billStr.substring(0,len);
					billStr = billStr.substring(len,billStr.length());
					tempBean.setBillMoneyFlag("￥"+beStr);
				}else{
					tempBean.setBillMoneyFlag("");
					billStr="￥"+billStr;
				}
				//实付贴现金额
				String payStr=CommUtils.NumberFormat5(tempBean.getPayMoney());
				if(payStr.length()>10){
					int len = payStr.length();
					len=len-11;					
					String beStr = payStr.substring(0,len);
					payStr = payStr.substring(len,payStr.length());
					tempBean.setPayMoneyFlag("￥"+beStr);
				}else{
					tempBean.setPayMoneyFlag("");
					payStr="￥"+payStr;
				}
				//小写金额格式化处
				if(tempBean.getPayType()!=null && !"2".equals(tempBean.getPayType())){//第三方付息金额相关
					//格式化输出 买方金额
					String buyStr=CommUtils.NumberFormat5(tempBean.getBuyerInterestMoney());
					if(buyStr.length()>10){
						int len = buyStr.length();
						len=len-11;					
						String beStr = buyStr.substring(0,len);
						buyStr = buyStr.substring(len,buyStr.length());
						tempBean.setBuyMoneyFlag("￥"+beStr);
					}else{
						tempBean.setBuyMoneyFlag("");
						buyStr="￥"+buyStr;
					}
					
					String salStr=CommUtils.NumberFormat5(tempBean.getSalerInterestMoney());
					if(salStr.length()>10){
						int len = salStr.length();
						len=len-11;					
						String beStr = salStr.substring(0,len);
						salStr = salStr.substring(len,salStr.length());
						tempBean.setSaleMoneyFlag("￥"+beStr);
					}else{
						tempBean.setSaleMoneyFlag("");
						salStr="￥"+salStr;
					}					
					
					tempBean.setSalerInterest(CommUtils.formateNumberToTableOut1(salStr,"15","19","center"));	
					tempBean.setBuyerInterest(CommUtils.formateNumberToTableOut1(buyStr,"15","32","center"));
					//汇票金额						
					tempBean.setBillAmountString(CommUtils.formateNumberToTableOut1(billStr,"15","26","center"));
					//利息	
					tempBean.setInterestString(CommUtils.formateNumberToTableOut1(intStr,"15","19","center"));
					//实付 贴现金额
					tempBean.setPayMoneyString(CommUtils.formateNumberToTableOut1(payStr,"15","37","center"));
				}else{
					//汇票金额
					tempBean.setBillAmountString(CommUtils.formateNumberToTableOut1(billStr,"20","30","center"));
					//利息
					tempBean.setInterestString(CommUtils.formateNumberToTableOut1(intStr,"20","30","center"));
					//实付 贴现金额
					tempBean.setPayMoneyString(CommUtils.formateNumberToTableOut1(payStr,"20","30","center"));
				}
				//汇票金额大写
//				tempBean.setBillAmountBig(CommUtils.lowerMoneyToUpperMoney(CommUtils.NumberFormat2(tempBean.getBillAmount())));
				//出票日 到期日 不用打印
				tempBean.setOutBillDate("");
				tempBean.setOutYear("");
				tempBean.setOutMonth("");
				tempBean.setOutDay("");
				tempBean.setEndBillDate("");
				tempBean.setEndYear("");
				tempBean.setEndMonth("");
				tempBean.setEndDay("");
				tempBean.setAcceptor("");
				tempBean.setOutBillBank("");
				tempBean.setBuyerName("");
				tempBean.setPayAccount("");
				tempBean.setBuyerBankName("");
				
				beanList.clear();
				beanList.add(tempBean);
			}
			piBean.setItemList(beanList);
			list.add(piBean);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
	
	public void copyProperties(DiscBean discBean,DiscBillInfo discBillInfo,DiscApplyInfo apply) throws Exception{
		BeanUtils.copyProperties(discBean, discBillInfo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(apply.getDiscDt()!=null){
			String dateStr [] = DateTimeUtil.getYear_Month_Day_Date(sdf.parse(apply.getDiscDt()));
			discBean.setYear(dateStr[0]);
			discBean.setMonth(dateStr[1]);
			discBean.setDay(dateStr[2]);
		}else{
//			String dateStr [] = DateTimeUtil.getYear_Month_Day_Date(DateTimeUtil.getWorkday());
			String dateStr [] = DateTimeUtil.getYear_Month_Day_Date(sdf.parse(apply.getDiscDt()));
			discBean.setYear(dateStr[0]);
			discBean.setMonth(dateStr[1]);
			discBean.setDay(dateStr[2]);
		}
//		种类
		discBean.setBillTypeString(discBillInfo.getBillType());
		//贴现 号码
		discBean.setBillNo(discBillInfo.getBillNo());
		//出票日 到期日
		discBean.setOutBillDate(discBillInfo.getIssueDt());
		discBean.setEndBillDate(discBillInfo.getDueDt());
		
		//持票人 名称 账户 开户行		
		discBean.setCustAccount(discBillInfo.getCustAccount());
		discBean.setCustName(apply.getCustName());
		
		/*CustAcctBean  bean = CustomerServiceFactory.getCustomerService().findLocalCustByAcctNo(discBillInfo.getCustAccountNo());
		
		if(bean==null){
			discBean.setCustBankName(SessionManager.getUserLogonInfo().getBranchName());
		}else{
			discBean.setCustBankName(bean.getAcctBrchName());
		}*/
		discBean.setPayType(apply.getPayType());
		String myPayType=apply.getPayType();
		if(StringUtils.isNotEmpty(myPayType)&&!"2".equals(myPayType)){
				/*CustAcctBean  bean2 = CustomerServiceFactory.getCustomerService().findLocalCustByAcctNo(apply.getPayAccount());
				//第三方 买方 名称
				discBean.setBuyerName(apply.getPayCustName());
				//第三方 买方 开户银行名称 账号
				if(bean2!=null){
					discBean.setBuyerBankName(bean2.getAcctBrchName());	
				}				*/
				discBean.setPayAccount(apply.getPayAccount());	
				//买方金额
				discBean.setBuyerInterestMoney(discBillInfo.getBuyerInterest());				
				//卖方金额
				discBean.setSalerInterestMoney(discBillInfo.getSalerInterest());	
				
				//第三方企业贴现利息
				String buyStr=CommUtils.NumberFormat5(discBillInfo.getBuyerInterest());
				if(buyStr.length()>10){
					int len = buyStr.length();
					len=len-11;					
					String beStr = buyStr.substring(0,len);
					buyStr = buyStr.substring(len,buyStr.length());
					discBean.setBuyMoneyFlag("￥"+beStr);
				}else{
					discBean.setBuyMoneyFlag("");
					buyStr="￥"+buyStr;
				}
				
				//持票人贴现利息
				String salStr=CommUtils.NumberFormat5(discBillInfo.getSalerInterest());
				if(salStr.length()>10){
					int len = salStr.length();
					len=len-11;					
					String beStr = salStr.substring(0,len);
					salStr = salStr.substring(len,salStr.length());
					discBean.setSaleMoneyFlag("￥"+beStr);
				}else{
					discBean.setSaleMoneyFlag("");
					salStr="￥"+salStr;
				}
				//贴现利息 合计
				String intStr=CommUtils.NumberFormat5(discBillInfo.getSalerInterest());
				if(intStr.length()>10){
					int len = intStr.length();
					len=len-11;					
					String beStr = intStr.substring(0,len);
					intStr = intStr.substring(len,intStr.length());
					discBean.setInstMoneyFlag("￥"+beStr);
				}else{
					discBean.setInstMoneyFlag("");
					intStr="￥"+intStr;
				}

				
				//汇票金额
				String billStr=CommUtils.NumberFormat5(discBillInfo.getBillMoney());
				if(billStr.length()>10){
					int len = billStr.length();
					len=len-11;					
					String beStr = billStr.substring(0,len);
					billStr = billStr.substring(len,billStr.length());
					discBean.setBillMoneyFlag("￥"+beStr);
				}else{
					discBean.setBillMoneyFlag("");
					billStr="￥"+billStr;
				}

				//实付贴现金额
				String payStr=CommUtils.NumberFormat5(discBillInfo.getPayMoney());
				if(payStr.length()>10){
					int len = payStr.length();
					len=len-11;					
					String beStr = payStr.substring(0,len);
					payStr = payStr.substring(len,payStr.length());
					discBean.setPayMoneyFlag("￥"+beStr);
				}else{
					discBean.setPayMoneyFlag("");
					payStr="￥"+payStr;
				}
				discBean.setBuyerInterest(CommUtils.formateNumberToTableOut1(buyStr,"15","32","center"));
				discBean.setSalerInterest(CommUtils.formateNumberToTableOut1(salStr,"15","19","center"));
				discBean.setInterestString(CommUtils.formateNumberToTableOut1(intStr,"15","19","center"));
				discBean.setBillAmountString(CommUtils.formateNumberToTableOut1(billStr,"15","26","center"));
				discBean.setPayMoneyString(CommUtils.formateNumberToTableOut1(payStr,"15","37","center"));
				if(discBillInfo.getIssueDt()!=null){
					String[] outDates = DateTimeUtil.getYear_Month_Day_Date(sdf.parse(discBillInfo.getIssueDt()));
					discBean.setOutYear(outDates[0]);
					discBean.setOutMonth(outDates[1]);
					discBean.setOutDay(outDates[2]);
				}
				if(discBillInfo.getDueDt()!=null){
					String[] endDates = DateTimeUtil.getYear_Month_Day_Date(sdf.parse(discBillInfo.getDueDt()));
					discBean.setEndYear(endDates[0]);
					discBean.setEndMonth(endDates[1]);
					discBean.setEndDay(endDates[2]);
				}
		}else{
//			票面金额
			String billStr=CommUtils.NumberFormat5(discBillInfo.getBillMoney());
			if(billStr.length()>10){
				int len = billStr.length();
				len=len-11;					
				String beStr = billStr.substring(0,len);
				billStr = billStr.substring(len,billStr.length());
				discBean.setBillMoneyFlag("￥"+beStr);
			}else{
				discBean.setBillMoneyFlag("");
				billStr="￥"+billStr;
			}
			discBean.setBillAmountString(CommUtils.formateNumberToTableOut1(billStr,"20","30","center"));
			//贴现利息
			String intStr=CommUtils.NumberFormat5(discBillInfo.getSalerInterest());
			if(intStr.length()>10){
				int len = intStr.length();
				len=len-11;					
				String beStr = intStr.substring(0,len);
				intStr = intStr.substring(len,intStr.length());
				discBean.setInstMoneyFlag("￥"+beStr);
			}else{
				discBean.setInstMoneyFlag("");
				intStr="￥"+intStr;
			}
			discBean.setInterestString(CommUtils.formateNumberToTableOut1(intStr,"20","30","center"));
			//实付金额
			String payStr=CommUtils.NumberFormat5(discBillInfo.getPayMoney());
			if(payStr.length()>10){
				int len = payStr.length();
				len=len-11;					
				String beStr = payStr.substring(0,len);
				payStr = payStr.substring(len,payStr.length());
				discBean.setPayMoneyFlag("￥"+beStr);
			}else{
				discBean.setPayMoneyFlag("");
				payStr="￥"+payStr;
			}
			discBean.setPayMoneyString(CommUtils.formateNumberToTableOut1(payStr,"20","30","center"));
		}
		//汇票承兑人 名称  开户银行
		discBean.setAcceptor(discBillInfo.getAcceptor());
		//银票  承兑人开户银行=承兑人
		//其他 出票人开户行
		if("1".equals(discBillInfo.getBillType())){
			discBean.setOutBillBank(discBillInfo.getAcceptor());
		}else{
			if(discBillInfo.getRemitterBankNo()==null){
				discBean.setOutBillBank("");
			}else{
				discBean.setOutBillBank(discBillInfo.getRemitterBankNo());
			}
		}
		//汇票金额 
		discBean.setBillAmount(discBillInfo.getBillMoney());
//		discBean.setBillAmountBig(CommUtils.lowerMoneyToUpperMoney(CommUtils.NumberFormat2(discBillInfo.getBillAmount())));

		//贴现利率
		discBean.setDiscRate(CommUtils.doubleFormateForString(apply.getRate()));
		//贴现利息
		discBean.setInterestMoney(discBillInfo.getSalerInterest());
		discBean.setInterest(CommUtils.NumberFormat3(discBillInfo.getSalerInterest()));

		//实付 贴现金额
		discBean.setPayMoney(discBillInfo.getPayMoney());	
		
		discBean.setPayeeBankName(discBillInfo.getPayeeBankName());
		discBean.setPayeeAccount(discBillInfo.getPayeeAcct());
		discBean.setPayee(discBillInfo.getPayee()); 
	}
}
