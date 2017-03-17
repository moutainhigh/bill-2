package com.herongtech.console.service.busiservice.rebuy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.FacBean;
import com.herongtech.console.domain.common.bean.FacDealResult;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IFacService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;

public class RebuyFacService implements IFacService {

	@Override
	public boolean isDealCredit(Object apply) {
		if(((RebuyApplyInfo)apply).isInner()){//系统内转入不扣减
			return false;
		}
		/*if(rebuyApp.isElecDeposit()){
			return false;
		}
		
		 同业 银承买入要扣,
		 * 买入约定要扣,卖断到期不扣
		 
		if (rebuyApp.isBirdSaleDue()) {//卖断到期
			return false;
		}else{
			return true;
		}*/
		return true;
	}

	@Override
	public FacDealResult dealFac(Object apply,List rebuyBills, UserLoginfo user, String facOperType) throws Exception {
		
		if(StringUtils.equals(facOperType, CommonConst.FAC_OPER_OCCUPY)){//扣减
			return occupy((RebuyApplyInfo)apply,rebuyBills, user);
		}else if(StringUtils.equals(facOperType, CommonConst.FAC_OPER_RELEASE)){//释放
			return release((RebuyApplyInfo)apply, rebuyBills, user,facOperType);
		}else{
			throw new BizAppException("未定义的额度操作方式：" + facOperType);
		}
	}
	/**
	 * 扣减额度
	 * @param apply
	 * @param rebuyBills
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public FacDealResult occupy(RebuyApplyInfo apply,List<RebuyBillInfo> rebuyBills, UserLoginfo user) throws Exception{
		FacDealResult result = new FacDealResult();
		//1、检查是否需要扣减额度
		if(!this.isDealCredit(apply)){//不需要扣减额度
			return this.unDealFac(rebuyBills, CommonConst.FAC_OPER_NO);
		}
		//2、分拣票据:处理异常数据，上一次提交失败的，不重复扣减额度
		List<RebuyBillInfo> occupyBillList = new ArrayList<RebuyBillInfo>();
		List<RebuyBillInfo> unOccupyBillList = new ArrayList<RebuyBillInfo>();
		for(RebuyBillInfo bill : rebuyBills){
			if(CommonConst.FAC_OPER_OCCUPY.equals(bill.getFacOperType()) && IConstants.YES.equals(bill.getIsAmount())){//额度扣减成功
				unOccupyBillList.add(bill);
			}else{
				occupyBillList.add(bill);
			}
		}
		//3、扣减额度，并统计结果（组装result对象）
		if(!occupyBillList.isEmpty()){
			Map<String,FacBean> map = ServiceFactory.getFmsAgentService().acceptorBankCredit(apply, CommonConst.FAC_SOURCE_REBUY, occupyBillList, user);
			for(RebuyBillInfo bill : occupyBillList){
				FacBean discAccountBean = map.get(bill.getRgctId());
				if(discAccountBean.isType()){//扣减成功
					//TODO 没有设置result的infoList
					result.successNumIncrease(1);
				}else{
					result.failNumIncrease(1);
					result.appandFailMsg("</br>票号 " + discAccountBean.getBillNo() + ":"
							+ discAccountBean.getMessage(), ";");
				}
			}
		}
		result.successNumIncrease(unOccupyBillList.size());
		return result;
		
	}
	/**
	 * 释放额度
	 * @param apply
	 * @param rebuyBills
	 * @param user
	 * @param facOperType
	 * @return
	 * @throws Exception 
	 */
	public FacDealResult release(RebuyApplyInfo apply,List<RebuyBillInfo> rebuyBills, UserLoginfo user, String facOperType) throws Exception{
		FacDealResult result = new FacDealResult();
		//1、检查是否需要释放额度
		if(!this.isDealCredit(apply)){//不需要释放额度
			return this.unDealFac(rebuyBills, facOperType);
		}
		//2、分拣票据:上一步操作为 扣减成功||释放失败 才能再次释放额度
		List<RebuyBillInfo> releaseBillList = new ArrayList<RebuyBillInfo>();
		List<RebuyBillInfo> unReleaseBillList = new ArrayList<RebuyBillInfo>();
		for(RebuyBillInfo bill : rebuyBills){
			if((CommonConst.FAC_OPER_OCCUPY.equals(bill.getFacOperType()) && IConstants.YES.equals(bill.getIsAmount())) 
					|| (CommonConst.FAC_OPER_RELEASE.equals(bill.getFacOperType()) && IConstants.NO.equals(bill.getIsAmount()))){
				releaseBillList.add(bill);
			}else{
				unReleaseBillList.add(bill);
			}
		}
		//3、释放额度，并统计结果
		if(!releaseBillList.isEmpty()){
			Map<String,FacBean> map = ServiceFactory.getFmsAgentService().facReleaseByBillBatch(apply, releaseBillList, user);
			for(RebuyBillInfo bill : releaseBillList){
				FacBean discAccountBean = map.get(bill.getRgctId());
				if(discAccountBean.isType()){
					result.successNumIncrease(1);
				}else{
					result.successNumIncrease(1);
					result.appandFailMsg("</br>票号 " + discAccountBean.getBillNo() + ":"
							+ discAccountBean.getMessage(), ";");
				}
			}
		}
		result.successNumIncrease(unReleaseBillList.size());
		return result;
	}
	/**
	 * 不需要操作额度
	 * @param rebuyBills
	 * @param facOperType
	 * @return
	 * @throws Exception 
	 */
	public FacDealResult unDealFac(List<RebuyBillInfo> rebuyBills,String facOperType) throws Exception{
		//1、更新清单信息
		if(CommonConst.FAC_OPER_NO.equals(facOperType)){//不需要扣减额度
			for(int i=0;i<rebuyBills.size();i++){
				rebuyBills.get(i).setFacOperType(facOperType);
				rebuyBills.get(i).setIsAmount(IConstants.YES);//额度扣减是否成功
				rebuyBills.get(i).setIsCyc(null);
				rebuyBills.get(i).setFacMsg("该业务无需扣减额度");
			}
			new RebuyBillInfoDao().modifyRebuyBillInfoList(rebuyBills);
		}
		//2、组装result对象
		FacDealResult result = new FacDealResult();
		result.setSuccessInfoList(rebuyBills);
		result.setSuccessNum(rebuyBills.size());
		return result;
	}
}
