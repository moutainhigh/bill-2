package com.herongtech.console.service.busiservice.repurchasedcollateralization;

import java.sql.SQLException;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.get.bean.GetBillInfo;
import com.herongtech.console.domain.get.dao.GetBillInfoDao;
import com.herongtech.console.domain.into.bean.IntoBillInfo;
import com.herongtech.console.domain.into.dao.IntoBillInfoDao;
import com.herongtech.console.domain.out.bean.OutBillInfo;
import com.herongtech.console.domain.out.dao.OutBillInfoDao;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.dao.SaveBillInfoDao;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.IntoCodeConst;
import com.herongtech.console.web.busicontroller.common.OutCodeConst;
import com.herongtech.console.web.busicontroller.common.RepurCollateCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.rcservice.ITrigger;

public class RepurCollateDraftCallback implements ITrigger{
	
	private GetBillInfoDao getBillDao = new GetBillInfoDao();
	private OutBillInfoDao outBillDao = new OutBillInfoDao();
	private SaveBillInfoDao saveBillDao = new SaveBillInfoDao();
	private IntoBillInfoDao intoBillDao = new IntoBillInfoDao();
	
	@Override
	public void transFor034execute(RgctBill rgctBill) 
			throws BizAppException {
		
	}
	
	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		try {
			if(isSuccess){
//				ISignProdService sign = ServiceFactory.getSignProdService();
				if(rgctBill.getHist().getBuyType().equals(RcConstants.BUY_INPOOL)){
					OutBillInfo outBill = outBillDao.getOutBillInfoByRgctId(rgctBill.getInfo().getId());
					outBill.setPosition(OutCodeConst.GET_WAIT_031_FOR_APPLY);	
					outBillDao.modifyOutBillInfo(outBill);
					/*SignProd signProd =sign.getSignProdInfoByCust(IConstants.BILLPOOL_SINGPROD, rgctBill.getHist().getToAcctNo());
					rgctBill.getHist().setToCustNo(signProd.getPartner());// 签收人客户号
					rgctBill.getHist().setToName(signProd.getCustName());// 签收人名称
					rgctBill.getHist().setToRole(RcConstants.BUSSINESS_ROLE1);// 签收人 参与者类型
					rgctBill.getHist().setToOrgcode(signProd.getIdNumber());// 签收人 组织机构代码
					rgctBill.getHist().setSignDt(DateTimeUtil.getWorkdayString());// 签收日期
//					rgctBill.getHist().setSignerSign(rgctBill.getHist().getFromSign());// 签收人电子签名
					rgctBill.getHist().setSignFlag(RcConstants.SIGN_YES);// /签收标识
					rgctBill.getHist().setToRemark("");// 签收人备注
					RcServiceFactory.getRcUnimpawnService().signUnimpawn(rgctBill);*/
				}else{
					GetBillInfo getBill = getBillDao.getGetBillInfoByRgctId(rgctBill.getInfo().getId());
					getBill.setPosition(RepurCollateCodeConst.RPDCOLLZTN_WAIT_031_FOR_APPLY);
					getBillDao.modifyGetBillInfo(getBill);
				}
			}else{
				if(rgctBill.getHist().getBuyType().equals(RcConstants.BUY_INPOOL)){
					OutBillInfo outBill = outBillDao.getOutBillInfoByRgctId(rgctBill.getInfo().getId());
					outBill.setOperStatus("BS1034");
	        		outBillDao.modifyOutBillInfo(outBill);
				}else{
					GetBillInfo getBill = getBillDao.getGetBillInfoByRgctId(rgctBill.getInfo().getId());
					getBill.setOperStatus("BS834");
	        		getBillDao.modifyGetBillInfo(getBill);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		try {
			if(isSuccess){
				if(rgctBill.getHist().getBuyType().equals(RcConstants.BUY_INPOOL)){
					OutBillInfo bill = outBillDao.getOutBillInfoByRgctId(rgctBill.getInfo().getId());
					if(bill!=null){
						bill.setOperStatus(OutCodeConst.DELETE_STATUS);
						outBillDao.modifyOutBillInfo(bill);
								}
				}else{
					GetBillInfo bill = getBillDao.getGetBillInfoByRgctId(rgctBill.getInfo().getId());
					if(bill!=null){
						bill.setOperStatus(RepurCollateCodeConst.DELETE_STATUS);
						getBillDao.modifyGetBillInfo(bill);
								}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void signFor033execute(RgctBill rgctBill, String isSign,
			boolean isSuccess) throws BizAppException {
		
	}
	
	@Override
	public void transFor031execute(RgctBill rgctBill, String isSign)
			throws BizAppException {
		try {
			if(RcConstants.SIGN_YES.equals(isSign)){
	        	if(rgctBill.getHist().getBuyType().equals(RcConstants.BUY_INPOOL)){
	        		OutBillInfo outBill = outBillDao.getOutBillInfoByRgctId(rgctBill.getInfo().getId());
	    			IntoBillInfo intoBill = intoBillDao.getIntoBillInfo(outBill.getIntobillRelaId());
	    			outBill.setSignFlag(RepurCollateCodeConst.SIGN_YES);
					outBill.setOperStatus(OutCodeConst.ACCOUNT_STATUS);
					outBill.setPosition(OutCodeConst.GET_ACCOUNT_OVER);
					outBillDao.modifyOutBillInfo(outBill);
					intoBill.setIsFac("0");
					intoBill.setGathType(IntoCodeConst.GATH_TYPE_SALE);
					intoBill.setGathDate(DateTimeUtil.getWorkdayString());
					intoBillDao.modifyIntoBillInfo(intoBill);
	        	}else{
					GetBillInfo getBill = getBillDao.getGetBillInfoByRgctId(rgctBill.getInfo().getId());
	    			SaveBillInfo saveBill = saveBillDao.getSaveBillInfo(getBill.getSavebillRelaId());
	    			/*//额度释放
	    			UserLoginfo user = new UserLoginfo();
	    			user.setUserNo(getBill.getAccountOperNo());
	    			user.setBrchNo(ServiceFactory.getUserService().getUserByUserId(getBill.getAccountOperNo()).getBranchNo());
	    			List<SaveBillInfo> saveBills = new ArrayList<SaveBillInfo>();
	    			saveBills.add(saveBill);
	    			SaveApplyInfo apply = ServiceFactory.getCollateralizationService().getSaveApplyInfo(saveBill.getSaveId(), null);
	    			FacDealResult result = ServiceFactory.getCollateFacService().dealFac(apply, saveBills, user, CommonConst.FAC_OPER_RELEASE);
	    			result.checkFacResult(CommonConst.FAC_OPER_RELEASE);*/
	    	        /*Branch brch = ServiceFactory.getBranchService().getBranch(getBill.getAdscriptionId());
	    	        UserLoginfo user = new UserLoginfo();
	    	        user.setUserNo(getBill.getAccountOperNo());
	    	        user.setBrchNo(brch.getBranchNo());*/
	    			getBill.setSignFlag(RepurCollateCodeConst.SIGN_YES);
	    	        getBill.setOperStatus(RepurCollateCodeConst.ACCOUNT_STATUS);
	        		getBill.setPosition(RepurCollateCodeConst.RPDCOLLZTN_ACCOUNT_OVER);
	        		getBill.setAccountDate(DateTimeUtil.getWorkdayString());//入库记账日期
	        		getBill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
	        		getBillDao.modifyGetBillInfo(getBill);
					saveBill.setGathType(CollateCodeConst.GATH_TYPE_SALE);
					saveBill.setGathDate(DateTimeUtil.getWorkdayString());
					saveBillDao.modifySaveBillInfo(saveBill);
        		}
	        }else{
	        	if(rgctBill.getHist().getBuyType().equals(RcConstants.BUY_INPOOL)){
	        		OutBillInfo outBill = outBillDao.getOutBillInfoByRgctId(rgctBill.getInfo().getId());
	        		outBill.setSignFlag(RepurCollateCodeConst.SIGN_NO);
	        		outBill.setOperStatus("BS1034");
	        		outBillDao.modifyOutBillInfo(outBill);
//	        		RcServiceFactory.getRcUnimpawnService().lock(outBill.getRgctId());
	        	}else{
	        		GetBillInfo getBill = getBillDao.getGetBillInfoByRgctId(rgctBill.getInfo().getId());
	        		getBill.setSignFlag(RepurCollateCodeConst.SIGN_NO);
	        		getBill.setOperStatus("BS834");
	        		getBillDao.modifyGetBillInfo(getBill);
//	        		RcServiceFactory.getRcUnimpawnService().lock(getBill.getRgctId());
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void transFor032execute(RgctBill rgctBill) 
			throws BizAppException {
		
	}

	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		// TODO Auto-generated method stub
		
	}
		
	

}
