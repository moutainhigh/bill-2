/********************************************
 * 文件名称: RcCollectService.java
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
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;

public class RcPresentationService extends RcBaseService implements IRcPresentationService{
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private RgctMethodDao rgctMethodDao=new RgctMethodDao();
	
	public List<RgctBillData> queryPrecollectBill(RcBaseSearchBean sb,Page page)
			throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcCollectService","queryPrecollectBill");
			return queryRC(sb, page, method, false,sb.getBillClass());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	@Override
	public String queryReversibleBill(RcBaseSearchBean sb)
			throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcCollectService","queryReversibleBill");
			return queryBillSql(sb, method,false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill inputGether(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcCollectService","inputGether");
			return changeStatus(bill,method,null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	@Override
	public void cancelPayEndorse(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcCollectService","cancelPayEndorse");
			this.checkPreStatus(bill, method, null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,RcConstants.TYPE_CANCEL);
			}else{
				//走内部流程
				this.commonCancel(bill,bill.getHist().getCurStatus());
				this.commonStatus(bill, RcConstants.COMMON_DRAWBACK, "1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		
		
	}

	@Override
	public void cancelOverdue(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcOverdueService","cancelOverdue");
			this.checkPreStatus(bill, method, null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,RcConstants.TYPE_CANCEL);
			}else{
				//走内部流程
				this.commonCancel(bill,bill.getHist().getCurStatus());
				this.commonStatus(bill, RcConstants.COMMON_DRAWBACK, "1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	@Override
	public String queryPrecollectBillClient(RcBaseSearchBean sb)
			throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcCollectService","queryPrecollectBillClient");
			return queryBillSql(sb, method,false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	@Override
	public void payEndorse(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcCollectService","payEndorse");
			this.checkPreStatus(bill, method, null);
			bill.getHist().setRejectCode(null);
			bill.getHist().setRejectReason(null);
			bill.getHist().setOverdueRs(null);
			
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				method.setParam("0");//取报文映射
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
			}else{
				//走内部流程
				this.commonStatus(bill, RcConstants.COMMON_PRESENTATION, "1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}

	}
	
	public void regOverdue(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcOverdueService","regOverdue");
			this.checkPreStatus(bill, method, null);
			bill.getHist().setRejectCode(null);
			bill.getHist().setRejectReason(null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				method.setParam("1");//取报文映射
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
			}else{
				//走内部流程
				this.commonStatus(bill, RcConstants.COMMON_OVERDUE, "1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	@Override
	public String queryFreeWaitSign(RcBaseSearchBean sb) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcFreeService","queryFreeWaitSign");
			return queryBillSql(sb, method,false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	@Override
	public void rejectCollect(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcFreeService","rejectCollect");
			this.checkPreStatus(bill, method, null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_NO);
			}else{
				//走内部流程
				this.commonStatus(bill, RcConstants.COMMON_SIGNUP, "11");
				this.commonSignUp(bill, RcConstants.COMMON_PRESENTATION, RcConstants.SIGN_NO);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	@Override
	public void collectSign(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcFreeService","collectSign");
			this.checkPreStatus(bill, method, null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_YES);
			}else{
				//走内部流程
				this.commonSignUp(bill, RcConstants.COMMON_PRESENTATION, RcConstants.SIGN_YES);
				this.commonStatus(bill, RcConstants.COMMON_SIGNUP, bill.getHist().getCurStatus());
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		
	}
	
	@Override
	public RgctBill cancelSign(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcFreeService","cancelSign");
			this.checkPreStatus(bill, method, null);
			return this.changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	@Override
	public void signOverdue(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcOverdueService","signOverdue");
			this.checkPreStatus(bill, method, null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_YES);
			}else{
				//走内部流程
				this.commonSignUp(bill, RcConstants.COMMON_OVERDUE, RcConstants.SIGN_YES);
				this.commonStatus(bill, RcConstants.COMMON_SIGNUP, bill.getHist().getCurStatus());
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}

		
	}

	@Override
	public void overduePresentationNo(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcOverdueService","overduePresentationNo");
			this.checkPreStatus(bill, method, null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_NO);
			}else{
				//走内部流程
				this.commonStatus(bill, RcConstants.COMMON_SIGNUP, "11");
				this.commonSignUp(bill, RcConstants.COMMON_OVERDUE, RcConstants.SIGN_NO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}

	}

	
}
