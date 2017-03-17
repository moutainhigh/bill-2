package com.herongtech.console.service.busiservice.sale;

import java.sql.SQLException;

import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.dao.SaleBillInfoDao;
import com.herongtech.console.web.busicontroller.common.SaleCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcSaleService;
import com.herongtech.rc.service.rcservice.ITrigger;

public class SaleDraftCallback implements ITrigger {
	private SaleBillInfoDao saleBillDao = new SaleBillInfoDao();
	private IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();

	@Override
	public void transFor034execute(RgctBill rgctBill) throws BizAppException {
		// TODO Auto-generated method stub

	}

	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		String rgctId=rgctBill.getInfo().getId();
		CommonLog.getCommonLogCache().infoMessage("转出背书 收到人行回复033回调"+rgctId);
		try{
			String status=StatusUtils.queryStatus("SaleEndorseController", "endorseSuccess", null)[0];
			SaleBillInfo saleBill = saleBillDao.getSaleBillInfoByRgctId(rgctId,status);
			if(saleBill==null){
				throw new BizAppException("转出背书回调异常.未查到清单表中相应的票据,登记中心ID:"+rgctId);
			}
			if (isSuccess) {
				saleBill.setOperStatus(StatusUtils.handleStatus("SaleEndorseController", "endorseSuccess", null, saleBill.getOperStatus()));	
			} else {
				saleBill.setOperStatus(StatusUtils.handleStatusNoCheck("SaleApplyController", "submitApply", null));
				//失败上锁
				rcSaleService.lock(saleBill.getRgctId());
			}
			saleBillDao.modifySaleBillInfo(saleBill);
		}catch(Exception e){
			e.printStackTrace();
			throw new BizAppException("转卖背书申请的033回调失败，"+e.getMessage());
		}
		

	}

	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		// TODO Auto-generated method stub

	}

	@Override
	public void signFor033execute(RgctBill rgctBill, String isSign, boolean isSuccess) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 线下清算对方签收或拒绝，收到人行发的031报文
	 * 线上清算收到人行发的036报文
	 */
	@Override
	public void transFor031execute(RgctBill rgctBill, String isSign)
			throws BizAppException {
		String rgctId = rgctBill.getInfo().getId();
		String status =SaleCodeConst.SALE_BILL_CODE_STATUS_CONVEY;
		SaleBillInfo saleBill;
		try {
			saleBill = saleBillDao.getSaleBillInfoByRgctId(rgctId,status);
			if (saleBill == null) {
				throw new BizAppException("未查到清单表中相应的票据");
			}
			CommonLog.getCommonLogCache().infoMessage("转出背书在收到对方(签收或者拒绝)的031报文后的回调开始－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
			CommonLog.getCommonLogCache().infoMessage("票号------:" + rgctBill.getInfo().getBillNo());
			//获取申请信息判断是否系统外的票，若是系统外的票据则拒绝后加锁，在电票确认菜单中解锁后才能回库推出转出流程。
			if (RcConstants.SIGN_YES.equals(isSign)) {
				saleBill.setPayTradeNo(rgctBill.getHist().getPayTradeNo());
				saleBill.setOperStatus(SaleCodeConst.SALE_BILL_CODE_STATUS_SIGNED);// 签收进入电票已签收状态,在电票确认菜单可以查询到
			} else {
				saleBill.setOperStatus(SaleCodeConst.SALE_BILL_CODE_STATUS_UNSIGN);// 未签收进入对方签收结果查询
				if (RcConstants.INNER_YES.equals(saleBill.getIsInner())) {//系统内置为已删除状态
					saleBill.setOperStatus(StatusUtils.handleStatusNoCheck("SaleEndorseController", "elecDelBill", null));
				}else{
					rcSaleService.lock(rgctId);
				}
			}
			saleBillDao.modifySaleBillInfo(saleBill);
		} catch (Exception e) {
			throw new BizAppException("031签收回调异常，"+e.getMessage());
		}


	}

	@Override
	public void transFor032execute(RgctBill rgctBill) throws BizAppException {
		// TODO Auto-generated method stub

	}

	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		String rgctId = rgctBill.getInfo().getId();
		try {
			SaleBillInfo bill = saleBillDao.getSaleBillInfoByRgcId(rgctId);
			if (bill == null) {
				throw new BizAppException("票据不存在");
			}
			bill.setOperStatus("BS200");
			saleBillDao.modifySaleBillInfo(bill);
		} catch (SQLException e) {
			throw new BizAppException("查询票据失败，"+e.getMessage());
		}
		
	}

}
