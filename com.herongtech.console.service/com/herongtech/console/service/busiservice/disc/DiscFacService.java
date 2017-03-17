package com.herongtech.console.service.busiservice.disc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.FacBean;
import com.herongtech.console.domain.common.bean.FacDealResult;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.dao.DiscBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IFacService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;

public class DiscFacService implements IFacService {

	@Override
	public boolean isDealCredit(Object apply) {
		//商票不操作额度
		if(IDict.K_BILL_TYPE.K_BANK_BILL.equals(((DiscApplyInfo)apply).getBillType())){
			return true;
		}
		return false;
	}

	@Override
	public FacDealResult dealFac(Object apply, List bills,UserLoginfo user, String facOperType) throws Exception {
		if(StringUtils.equals(facOperType, CommonConst.FAC_OPER_OCCUPY)){//扣减
			return occupy((DiscApplyInfo)apply,bills, user);
		}else if(StringUtils.equals(facOperType, CommonConst.FAC_OPER_RELEASE)){//释放
			return release((DiscApplyInfo)apply, bills, user,facOperType);
		}else{
			throw new BizAppException("未定义的额度操作方式：" + facOperType);
		}
	}
	/**
	 * 额度占用
	 * @param apply
	 * @param bills
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public FacDealResult occupy(DiscApplyInfo apply,List<DiscBillInfo> bills,UserLoginfo user) throws Exception{
		FacDealResult result = new FacDealResult();
		//1、检查是否需要扣减额度
		if(!this.isDealCredit(apply)){
			return this.unDealFac(bills, CommonConst.FAC_OPER_NO);
		}
		//2、分拣票据：需要扣减额度&&扣减失败的   才能扣减额度--因为一个批次下的所有票据必须都扣减成功才算成功，所以不需要分拣票据
		List<DiscBillInfo> occupyBillList = new ArrayList<DiscBillInfo>();
		List<DiscBillInfo> unOccupyBillList = new ArrayList<DiscBillInfo>();
		/*for(DiscBillInfo bill : bills){
			if(!IConstants.NO.equals(bill.getIsThirdAmount()) && !IConstants.YES.equals(bill.getIsAmount())){
				occupyBillList.add(bill);
			}else{
				unOccupyBillList.add(bill);
			}
		}*/
		occupyBillList.addAll(bills);
		//3、额度扣减，并统计结果
		Map<String,FacBean> map = ServiceFactory.getFmsAgentService().acceptorBankCredit(apply, CommonConst.FAC_SOURCE_DISC, occupyBillList, user);
		for(DiscBillInfo bill : occupyBillList){
			FacBean discAccountBean = map.get(bill.getRgctId());
			if(discAccountBean.isType()){
				result.successNumIncrease(1);
			}else{
				result.failNumIncrease(1);
				result.appandFailMsg("</br>票号 " + discAccountBean.getBillNo() + ":"
						+ discAccountBean.getMessage(), ";");
			}
		}
		result.successNumIncrease(unOccupyBillList.size());
		return result;
	}
	/**
	 * 额度释放
	 * @param apply
	 * @param bills
	 * @param user
	 * @param facOperType
	 * @return
	 * @throws Exception 
	 */
	public FacDealResult release(DiscApplyInfo apply,List<DiscBillInfo> bills,UserLoginfo user, String facOperType) throws Exception{
		FacDealResult result = new FacDealResult();
		//1、检查是否需要释放额度
		if(!this.isDealCredit(apply)){
			return this.unDealFac(bills, facOperType);
		}
		//2、分拣票据: 
		
		//3、额度释放，并统计结果
		Map<String,FacBean> map = ServiceFactory.getFmsAgentService().facReleaseByBillBatch(apply, bills, user);
		for(DiscBillInfo bill : bills){
			FacBean discAccountBean= map.get(bill.getRgctId());
			if(discAccountBean.isType()){
				result.successNumIncrease(1);
			}else{
				result.failNumIncrease(1);
				result.appandFailMsg("</br>票号 " + discAccountBean.getBillNo() + ":"
						+ discAccountBean.getMessage(), ";");
			}
		}
		return result;
	}

	/**
	 * 不需要操作额度
	 * @param bills
	 * @param facOperType
	 * @return
	 * @throws Exception 
	 */
	public FacDealResult unDealFac(List<DiscBillInfo> bills,String facOperType) throws Exception{
		//1、更新清单信息
		if(CommonConst.FAC_OPER_NO.equals(facOperType)){//不需要扣减额度
			DiscBillInfoDao dao = new DiscBillInfoDao();
			for(int i=0;i<bills.size();i++){
//				bills.get(i).setFacOperType(facOperType);
				bills.get(i).setIsAmount(IConstants.YES);//额度扣减是否成功
				bills.get(i).setIsCyc(null);
				bills.get(i).setIsThirdAmount(IConstants.NO);//是否需要扣减第三方额度
//				bills.get(i).setFacMsg("该业务无需扣减额度");
				dao.modifyDiscBillInfo(bills.get(i));
			}
		}
		//2、组装result对象
		FacDealResult result = new FacDealResult();
		result.setSuccessInfoList(bills);
		result.setSuccessNum(bills.size());
		return result;
	}

}
