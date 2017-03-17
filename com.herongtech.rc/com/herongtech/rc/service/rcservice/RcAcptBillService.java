/********************************************
 * 文件名称: RcAcptBillService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 2016-8-10 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.service.rcservice;
import java.sql.SQLException;

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;
import com.herongtech.rc.service.rcservice.RcBaseService;

public class RcAcptBillService extends RcBaseService implements IRcAcptBillService{
	
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	private RgctMethodDao rgctMethodDao=new RgctMethodDao();

	public String queryAcpt(RcBaseSearchBean sb) throws BizAppException{
		
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcAcptBillService","queryAcpt");
			return queryBillSql(sb, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	public String queryAcceptanceWaitConfirm(RcBaseSearchBean sb) throws BizAppException{
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcAcptBillService","queryAcceptanceWaitConfirm");
			return queryBillSql(sb, method,false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	public String queryReversibleBill(RcBaseSearchBean sb) throws BizAppException  {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcAcptBillService","queryReversibleBill");
			return queryBillSql(sb, method,false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	public void acceptanceRequest(RgctBill bill) throws BizAppException {
		// 检查基本的报文内容
		RgctValidateUtils.checkElecRequest(bill);
		// 检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcAcptBillService","acceptanceRequest");
			this.checkPreStatus(bill, method, null);
			if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())) {
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
			} else {
				// 走内部流程
				this.commonStatus(bill, "acceptor", "1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	public void acptBack(RgctBill bill) throws BizAppException {
		// 检查基本的报文内容
		RgctValidateUtils.checkElecResponse(bill);
		// 检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod( "IRcAcptBillService", "acptBack");
			this.checkPreStatus(bill, method, null);
			if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())) {
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_NO);
			} 
			/*else {
			// 走内部流程
			this.commonStatus(getRbDAO().getRgctBillById(bill.getInfo().getId()), BillConst.COMMON_SIGNUP, "11");
			this.commonSignUp(bill, BillConst.COMMON_ACCEPTOR, BillConst.SIGN_NO);
		}*/
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	public void acptSign(RgctBill bill)  throws BizAppException {
		// 检查基本的报文内容
		RgctValidateUtils.checkElecResponse(bill);
		// 检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod( "IRcAcptBillService", "acptSign");
			this.checkPreStatus(bill, method, null);
			if ( !IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())) {
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_YES);
			} 
			/*else {
			// 走内部流程
			this.commonSignUp(bill, BillConst.COMMON_ACCEPTOR, BillConst.SIGN_YES);
			RgctBill newBill = this.getRbDAO().getRgctBillById(bill.getInfo().getId());
			this.commonStatus(newBill, BillConst.COMMON_SIGNUP, newBill.getHist().getCurStatus());
		}*/
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	public void cancelRequest(RgctBill bill) throws BizAppException  {
		// 检查基本的报文内容
		RgctValidateUtils.checkElecCancel(bill);
		// 检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod( "IRcAcptBillService", "cancelRequest");
			this.checkPreStatus(bill, method, null);
			if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())) {
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,RcConstants.TYPE_CANCEL);
			}
			/*else {
			// 走内部流程
			this.commonCancel(bill, bill.getHist().getCurStatus());
			this.commonStatus(bill, BillConst.COMMON_DRAWBACK, "1");
		}*/
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	@Override
	public String queryAcceptNotifyWaitSign(RcBaseSearchBean sb)
			throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcAcptBillService","queryAcceptNotifyWaitSign");
			return queryBillSql(sb, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	@Override
	public RgctBill preSign(RgctBill bill) throws BizAppException {
		// 检查前置状态
			RgctMethod method;
			try {
				method = rgctMethodDao.getRgctMethod( "IRcAcptBillService", "preSign");
				this.checkPreStatus(bill, method, null);
				return this.changeStatus(bill, method, null);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BizAppException(e);
			}
	}

	@Override
	public RgctBill cancelPreSign(RgctBill bill) throws BizAppException {
		// 检查前置状态
		try {
			RgctMethod method = rgctMethodDao.getRgctMethod( "IRcAcptBillService", "cancelPreSign");
			this.checkPreStatus(bill, method, null);
			return this.changeStatus(bill, method, null);
		} catch (Exception e) {
			throw new BizAppException(e);
		}
	}
	
	
	
}
