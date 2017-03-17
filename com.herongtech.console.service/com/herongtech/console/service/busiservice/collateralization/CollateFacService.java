package com.herongtech.console.service.busiservice.collateralization;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.FacBean;
import com.herongtech.console.domain.common.bean.FacDealResult;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.dao.SaveBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IFacService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;

public class CollateFacService implements IFacService {

	@Override
	public boolean isDealCredit(Object apply) {
		//商票不操作额度
		if(IDict.K_BILL_TYPE.K_BANK_BILL.equals(((SaveApplyInfo)apply).getBatchType())){
			return true;
		}
		return false;
	}

	@Override
	public FacDealResult dealFac(Object apply, List bills,UserLoginfo user, String facOperType) throws Exception {
		if(StringUtils.equals(facOperType, CommonConst.FAC_OPER_OCCUPY)){//扣减
			return occupy((SaveApplyInfo)apply,bills, user);
		}else if(StringUtils.equals(facOperType, CommonConst.FAC_OPER_RELEASE)){//释放
			return release((SaveApplyInfo)apply, bills, user,facOperType);
		}else{
			throw new BizAppException("未定义的额度操作方式：" + facOperType);
		}
	}

	/**
	 * 额度扣减
	 * @param apply
	 * @param billList
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public FacDealResult occupy(SaveApplyInfo apply,List<SaveBillInfo> billList,UserLoginfo user) throws Exception{
		FacDealResult result = new FacDealResult();
		//1、检查是否需要扣减额度
		if(!this.isDealCredit(apply)){
			return this.unDealFac(billList, CommonConst.FAC_OPER_NO);
		}
		//2、分拣票据
		
		//3、扣减额度
		Map<String,FacBean> map = ServiceFactory.getFmsAgentService().acceptorBankCredit(apply, CommonConst.FAC_SOURCE_SAVE, billList, user);
		for(SaveBillInfo bill : billList){
			FacBean discAccountBean = map.get(bill.getRgctId());
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
	 * 额度释放
	 * @param apply
	 * @param billList
	 * @param user
	 * @param facOperType
	 * @return
	 * @throws Exception 
	 */
	public FacDealResult release(SaveApplyInfo apply,List<SaveBillInfo> billList,UserLoginfo user,String facOperType) throws Exception{
		FacDealResult result = new FacDealResult();
		//1、检查是否需要释放额度
		if(!this.isDealCredit(apply)){
			return this.unDealFac(billList, facOperType);
		}
		//2、分拣票据
		
		//3、释放额度
		Map<String,FacBean> map = ServiceFactory.getFmsAgentService().facReleaseByBillBatch(apply, billList, user);
		for(SaveBillInfo bill : billList){
			FacBean discAccountBean = map.get(bill.getRgctId());
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
	public FacDealResult unDealFac(List<SaveBillInfo> bills,String facOperType) throws Exception{
		//1、更新清单信息
		if(CommonConst.FAC_OPER_NO.equals(facOperType)){//不需要扣减额度
			SaveBillInfoDao dao = new SaveBillInfoDao();
			for(int i=0;i<bills.size();i++){
//				bills.get(i).setFacOperType(facOperType);
				bills.get(i).setIsAmount(IConstants.YES);//额度扣减是否成功
				bills.get(i).setIsCyc(null);
//				bills.get(i).setIsThirdAmount(IConstants.NO);//是否需要扣减第三方额度
//				bills.get(i).setFacMsg("该业务无需扣减额度");
				dao.modifySaveBillInfo(bills.get(i));
			}
		}
		//2、组装result对象
		FacDealResult result = new FacDealResult();
		result.setSuccessInfoList(bills);
		result.setSuccessNum(bills.size());
		return result;
	}
}
