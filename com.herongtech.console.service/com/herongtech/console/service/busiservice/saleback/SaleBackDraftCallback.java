package com.herongtech.console.service.busiservice.saleback;

import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISaleBackService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.rcservice.ITrigger;

public class SaleBackDraftCallback implements ITrigger{

	ISaleBackService saleBackService = ServiceFactory.getSaleBackService();
	
	@Override
	public void transFor034execute(RgctBill rgctBill) throws BizAppException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws Exception {
		SalebackBillInfo billInfo = saleBackService.getSalebackBillInfoForRgctId(rgctBill.getHist().getRgctId());
		if(billInfo == null){
			return;
		}
		if(isSuccess){
			billInfo.setOperStatus(StatusUtils.handleStatusNoCheck("SalebackApplyController", "endorsedelec", null));
		}else{
			billInfo.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applycancelelec", null)[0]);
		}
		saleBackService.modifySalebackBillInfo(billInfo);
	}

	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws Exception {
		SalebackBillInfo billInfo = saleBackService.getSalebackBillInfoForRgctId(rgctBill.getHist().getRgctId());
		if(billInfo == null){
			return;
		}
		if(isSuccess){
			billInfo.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applycancelelec", null)[0]);
			billInfo.setEndorseDt(null);
			billInfo.setEndorseTime(null);
		}else{
			billInfo.setOperStatus(StatusUtils.handleStatusNoCheck("SalebackApplyController", "endorsedelec", null));
		}
		saleBackService.modifySalebackBillInfo(billInfo);
	}

	@Override
	public void signFor033execute(RgctBill rgctBill, String isSign,
			boolean isSuccess) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transFor031execute(RgctBill rgctBill, String isSign)
			throws Exception {
		SalebackBillInfo billInfo = saleBackService.getSalebackBillInfoForRgctId(rgctBill.getHist().getRgctId());
		if(billInfo == null){
			return;
		}
		if(RcConstants.SIGN_YES.equals(isSign)){
			billInfo.setOperStatus(StatusUtils.handleStatusNoCheck("SalebackApplyController", "endorseyeselec", null));
		}else{
			billInfo.setOperStatus(StatusUtils.handleStatusNoCheck("SalebackApplyController", "endorsenotelec", null));
		}
		saleBackService.modifySalebackBillInfo(billInfo);
	
		
	}

	@Override
	public void transFor032execute(RgctBill rgctBill) throws BizAppException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		
		String rgctId = rgctBill.getInfo().getId();
		SalebackBillInfo  saleBackBill = saleBackService.getSalebackBillInfoForRgctId(rgctId);
		if (saleBackBill == null) {
			throw new BizAppException("票据不存在"); 
		}
		saleBackBill.setOperStatus("BSb600");
		saleBackService.modifySalebackBillInfo(saleBackBill);
	}
}
