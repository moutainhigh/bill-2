
/********************************************
 * 文件名称: RcBillNotifyService.java
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

public class RcBillNotifyService extends RcBaseService implements IRcBillNotifyService{
	
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	private RgctMethodDao rgctMethodDao=new RgctMethodDao();
	
	@Override
	public String queryIssuance(RcBaseSearchBean sb) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBillNotifyService","queryIssuance");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method,false);
	}

	@Override
	public void issuanceRequest(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBillNotifyService","issuanceRequest");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
		}
		/*else {
			//走内部流程
			this.commonStatus(bill, RcConstants.COMMON_ISSURANCE, "1");
		}*/
		
	}

	@Override
	public String queryIssuanceWaitConfirm(RcBaseSearchBean sb)
			throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBillNotifyService","queryIssuanceWaitConfirm");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method,false);
	}

	@Override
	public void cancelRequest(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBillNotifyService","cancelRequest");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_APPLY,RcConstants.TYPE_CANCEL);
		} 
		/*else {
			//走内部流程
			this.commonCancel(bill,bill.getHist().getCurStatus());
			this.commonStatus(bill,BillConst.COMMON_DRAWBACK,"1");
		}*/
		
	}

	@Override
	public void payeeSign(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBillNotifyService","payeeSign");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_YES);
		}
		/*else {
			//走内部流程
			this.commonSignUp(bill, BillConst.COMMON_ISSURANCE, BillConst.SIGN_YES);
			RgctBill newBill = this.getRbDAO().getRgctBillById(bill.getInfo().getId());
			this.commonStatus(newBill, BillConst.COMMON_SIGNUP, newBill.getHist().getCurStatus());
		}*/
		
	}

	@Override
	public void payeeReject(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBillNotifyService","payeeReject");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_NO);
		} 
		/*else {
			//走内部流程
			this.commonStatus(getRbDAO().getRgctBillById(bill.getInfo().getId()), BillConst.COMMON_SIGNUP, "11");
			this.commonSignUp(bill, BillConst.COMMON_ISSURANCE, BillConst.SIGN_NO);
		}*/
		
	}

	@Override
	public String queryBillWaitSign(RcBaseSearchBean sb) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBillNotifyService","queryBillWaitSign");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method,false);
	}

}
