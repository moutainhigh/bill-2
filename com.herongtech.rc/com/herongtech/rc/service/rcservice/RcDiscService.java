/********************************************
 * 文件名称: RcDiscService.java
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

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;
import com.herongtech.rc.service.rcservice.RcBaseService;

public class RcDiscService extends RcBaseService implements IRcDiscService {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	private RgctMethodDao rgctMethodDao=new RgctMethodDao();

	public RgctBill inputSignBuy(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBuyService","inputSignBuy");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		return addBill(bill,method, null);// 贴现导入时未指定买断式还是回购式，暂时定为0
	}

	public void cancelBuyApply(RgctBill bill) throws BizAppException {
		
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBuyService","cancelBuyApply");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())) {
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_APPLY,RcConstants.TYPE_CANCEL);
		} else {
			// 走内部流程
			// this.commonCancel(bill,bill.getHist().getCurStatus());
			// this.commonStatus(bill,BillConst.COMMON_DRAWBACK, "1");
			try {
				processBill(bill, method, "0");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BizAppException(e.getMessage());
			}
		}
	}

	public void rejectSignBuy(RgctBill bill) throws BizAppException {
		// 检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBuyService","rejectSignBuy");
			this.checkPreStatus(bill, method, null);
			if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())) {
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_NO);
			} else {
				// 走内部流程
				// this.commonStatus(getRbDAO().getRgctBillById(bill.getInfo().getId()),
				// BillConst.COMMON_SIGNUP, "11");
				// this.commonSignUp(bill, methodName, BillConst.SIGN_NO);
				processBill(bill, method, "0");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}

	public void submitBuyApply(RgctBill bill) throws BizAppException {
		// 检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBuyService","submitBuyApply");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
//		RgctMethod method = rgctMethodDao.getRgctMethod("IRcBuyService","submitBuyApply");
		this.checkPreCondi(bill, method, null);
		String methodName = RcConstants.REGRESS_YES.equals(bill.getHist()
				.getIsRegress()) ? RcConstants.COMMON_DISCOUNT2
				: RcConstants.COMMON_DISCOUNT1;
		if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())) {
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
		} else {
			// 走内部流程
			this.commonStatus(bill, methodName, "1");
		}
	}

	public void discountSign(RgctBill bill) throws BizAppException {
		try {
			// 检查前置状态
			RgctMethod method = rgctMethodDao.getRgctMethod("IRcBuyService","discountSign");
			this.checkPreStatus(bill, method, null);
			RgctBillHist hist = bill.getHist();
			/*ProdLimitType plt = (ProdLimitType) CacheInitServlet.SESSON_PROD_LIMIT_TYPE
					.get(hist.getProdNo());
			if (plt != null) {
				hist.setBuyType(plt.getBuyIntoType());
			}*/
			hist.setBuyType(RcConstants.BUY_DISC);//暂时不考虑赎回式贴现
//			String methodName = BillConst.REGRESS_YES.equals(bill.getHist()
//					.getIsRegress()) ? BillConst.COMMON_DISCOUNT2
//					: BillConst.COMMON_DISCOUNT1;
			if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo()
							.getBillClass())) {
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_YES);
//				sendDraft(bill, BillConst.SIGN_YES, method);
			} else {
				// 走内部流程

				// changeStatus(bill, rgctMethodDao.getRgctMethod("CA", "commonSignup",
				// methodName), BillConst.SIGN_YES, null);
				changeStatus(bill, method, bill.getHist().getIsRegress());
			}

		} catch (Exception e) {
			throw new BizAppException(e);
		}
	}

	public RgctBill cancelSign(RgctBill bill) throws BizAppException {
		if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass()))
			throw new BizAppException("电票不支持此操作");
		// 检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBuyService","cancelSign");
			this.checkPreStatus(bill, method, null);
			
			processBill(bill, method, "0");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return bill;
	}

	public String queryReversibleBill(RcBaseSearchBean sb)
			throws BizAppException {
		sb.setIsLock(IDict.K_LOCK.K_LOCK_NO);
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBuyService","queryReversibleBill");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method,false);
	}

	@Override
	public String queryScatteredBill(RcBaseSearchBean sb)
			throws BizAppException {
		sb.setIsLock(IDict.K_LOCK.K_LOCK_NO);
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBuyService","queryScatteredBill");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method,false);
			
	}
	
	
}
