package com.herongtech.console.web.busicontroller.print;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;



import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.dao.DiscApplyInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.disc.DiscService;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;


/**
 * 贴现审核：打印审核清单
 * 
 * @author liujin edit by rpf 2009-12-07
 * 
 */
public class DiscAuditPrint extends AbstractPrint {

	@Override
	public List getPrintList(String ids, String batchId,String handleType) throws Exception {
		DiscApplyInfo apply = null;
		List billList = null;
		List beanList = new ArrayList();
		List list = new ArrayList();
		DiscBillInfo billInfo = null;
		PublicItemBean piBean = new PublicItemBean();// 显示bean
		HeadPrintBean hpBean = new HeadPrintBean();// 表头bean
		double totalBillMoney = 0.0;// 票面总金额
		double totalInterest = 0.0;// 总利息
		double totalPayMoney = 0.0;// 总实付金额
		try {
			IDiscService service = ServiceFactory.getDiscService();
			billList =service.getDiscBillInfolistbyids(ids);
			if("1".equals(isAdd)){//补打清单
				billInfo = (DiscBillInfo) billList.get(0);
				batchId = billInfo.getDiscId();
			}
			apply = ServiceFactory.getDiscService().getDiscApplyInfoBydiscid(batchId);
			
			String custBankName="";
			//下面的注释：现在没有CustAcctBean实体类 暂时先从session中取出机构名称
//			CustAcctBean  bean = CustomerServiceFactory.getCustomerService().findLocalCustByAcctNo(apply.getCustAccount());
//			
//			if(bean == null){
//				custBankName=ResourceUtil.getSessionLoginfo().getBranchName();
//			}else{
//			custBankName=bean.getAcctBrchName();
//			}
			custBankName=ResourceUtil.getSessionLoginfo().getBranchName();
			for (int i = 0; i < billList.size(); i++) {
				DiscBean discBean = new DiscBean();// 贴现属性bean
				billInfo = ((DiscBillInfo) (billList.get(i)));
				this.copyProperties(discBean, billInfo);
				this.copyProperties(hpBean, billInfo,custBankName,apply);
				hpBean.setNo(Long.valueOf(billList.size()));// 票据张数
				discBean.setSequence(i + 1);// 序号
				totalPayMoney = CommUtils.addForMoney(totalPayMoney, billInfo.getPayMoney());// 总实付金额
				totalInterest = CommUtils.addForMoney(totalInterest, billInfo.getTotalIntrstPayment());// 总利息
				totalBillMoney = CommUtils.addForMoney(totalBillMoney, billInfo.getBillMoney());// 总金额
				beanList.add(discBean);
			}
			if ("0".equals((billInfo.getDiscType()))) {
				hpBean.setTopic("买 断 式 贴  现  "+handleType+" 清  单");
				hpBean.setDiscType("买断式贴现");
				hpBean.setPayType(apply.getPayTypeString());
			} else {
				hpBean.setTopic("赎  回  式  贴  现  "+handleType+" 清  单");
				hpBean.setDiscType("赎回式贴现");
				hpBean.setPayType(apply.getPayTypeString());
				//赎回开放日（卖出回购业务有）
				hpBean.setRebuyOpenDt(apply.getRedeemDate());
				//赎回截止日（卖出回购业务有
				hpBean.setRebuyDueDt(apply.getRedemptionDt());
			}
			hpBean.setApplyNo(apply.getBatchNo());// 批次号
			hpBean.setPayTypeString(apply.getPayTypeString());// 付息方式
			hpBean.setPayType(apply.getPayTypeString());
			hpBean.setPayMoneyString(CommUtils.NumberFormat3(totalPayMoney));// 总实付金额
			hpBean.setAllInterest(CommUtils.NumberFormat3(totalBillMoney-totalPayMoney));// 总利息
			hpBean.setAllBillMoneyString(CommUtils.NumberFormat3(totalBillMoney));// 总金额
			hpBean.setCustName(apply.getCustName());
			piBean.setItemList(beanList);
			piBean.setHeadPrintBean(hpBean);
			list.add(piBean);
			return list;
		} catch (Exception e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "获得打印的清单时异常");
		}
	}

	protected void copyProperties(HeadPrintBean hpBean, DiscBillInfo billInfo,String custBankName,DiscApplyInfo apply) throws Exception {
		BeanUtils.copyProperties(hpBean, billInfo);
		hpBean.setCustName(billInfo.getBillOwner());// 客户名称
		hpBean.setCustAccount(billInfo.getCustAccount());// 客户账号
		hpBean.setCustBankName(custBankName);	
		hpBean.setDiscDt(billInfo.getDiscDt());
		hpBean.setBillTypeString(billInfo.getBillTypeString());// 种类
		hpBean.setBillClassString(billInfo.getBillClassString());// 类别
		hpBean.setBillClass(billInfo.getBillClass());
		hpBean.setBillType(billInfo.getBillType());
		String isOnline=billInfo.getIsOnline();
		if("1".equals(isOnline)){//清算方式 清算方式：线上/线下
			hpBean.setQsTypeString("线上");
		}else{
			hpBean.setQsTypeString("线下");
		}
		hpBean.setRate(CommUtils.doubleFormateForString(apply.getRate()));
	}

	protected void copyProperties(DiscBean discBean, DiscBillInfo billInfo) throws Exception {
		BeanUtils.copyProperties(discBean, billInfo);
		
		discBean.setBillNo(MoneyUtils.billNoSubString(billInfo.getBillNo()));
		// ppBean.setBillAmountString(MoneyUtils.double2String(billInfo.getBillAmount().doubleValue()));//票面金额
		discBean.setBillMoney(CommUtils.NumberFormat2(billInfo.getBillMoney()));// 票面金额
		discBean.setOutBillDate(billInfo.getIssueDt());// 出票日
		discBean.setEndBillDate(billInfo.getDueDt());// 票面到期日
		if ("2".equalsIgnoreCase(billInfo.getBillType())) {
			discBean.setOutBillPerson(billInfo.getAcceptor());
			discBean.setOutBillBank(billInfo.getRemitterBankName());//
		}
		discBean.setDiscDt(billInfo.getDiscDt());// 贴现日
		if (billInfo.getGaleDate() == null) {
			discBean.setGaleDate("");// 计息到期日
		} else {
			discBean.setGaleDate(billInfo.getGaleDate());// 计息到期日
		}
		if (billInfo.getInterestDays() == null) {
			discBean.setInterestCalDays(Long.valueOf(0));// 计息天数
		} else {
			discBean.setInterestCalDays(billInfo.getInterestDays());// 计息天数
		}
		discBean.setDiscRate(CommUtils.doubleFormateForString(billInfo.getRate()));// 贴现利率
		discBean.setInterest(CommUtils.doubleFormateForString(billInfo.getSalerInterest()));// 贴现利息
		// discBean.setPayMoneyString(MoneyUtils.double2String(billInfo.getPayMoney().doubleValue()));//实付金额
		discBean.setPayMoneyString(CommUtils.NumberFormat3(billInfo.getPayMoney()));// 实付金额
		if (billInfo.getRedeemOpenDt() == null) {
			discBean.setRedeemOpenDt("");
		} else {
			discBean.setRedeemOpenDt(billInfo.getRedeemOpenDt());
		}
		if (billInfo.getRedeemEndDate() == null) {
			discBean.setRedeemEndDate("");
		} else {
			discBean.setRedeemEndDate(billInfo.getRedeemEndDate());
		}
		//承兑人
		discBean.setAcceptor(billInfo.getAcceptor());
		
	}

}
