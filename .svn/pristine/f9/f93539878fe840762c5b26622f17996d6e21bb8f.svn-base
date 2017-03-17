/********************************************
 * 文件名称: RcBuybackService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 2016-8-24 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.service.rcservice;

import java.sql.SQLException;

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;

public class RcBuybackService extends RcBaseService implements
		IRcBuybackService {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	private RgctMethodDao rgctMethodDao = new RgctMethodDao();

	public void buyBackSign(RgctBill bill) throws BizAppException {
		// 检查前置状态
		RgctMethod method;
		String methodName = "";
		try {
			RgctValidateUtils.checkElecResponse(bill);
			method = rgctMethodDao.getRgctMethod("IRcSaleBackService","buyBackSign");
			this.checkPreStatus(bill, method, null);
			if (RcConstants.REDISCOUNT_CENTER.equals(bill.getHist().getIsRediscCenter())) {
				// 再贴现
				methodName=RcConstants.COMMON_REDISCOUNT_CENTER_BACK;
//				method.setParam("1");
			} else {
				methodName=RcConstants.COMMON_REDISCOUNT_BACK;
//				method.setParam("0");
			}
			if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())) {
				// 发送报文
				sendDraft(bill, method, RcConstants.DRAFT_SIGN,RcConstants.SIGN_YES);
			} else {
				// 嵌套业务未实现
				//非嵌套业务回退
				bill.getInfo().setBusiDeep(null);
				bill.getHist().setIsRegress(RcConstants.REGRESS_NO);
				if(RcConstants.INNER_YES.equals(bill.getHist().getIfInner())){//系统内回购回退至最近来源
					String oldBillTrack = bill.getHist().getBillTrack();
					if("3".equals(oldBillTrack.substring(oldBillTrack.length() - 1))){
						bill.getHist().setBillTrack(oldBillTrack.substring(0,oldBillTrack.length() - 1));
					}
				}
				RgctMethod methodInRegress=rgctMethodDao.getRgctMethod(RcConstants.CA, RcConstants.TYPE_SIGNUP, methodName);
				this.changeStatus(bill, methodInRegress, "1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}

	public void rejectSaleBackEndorse(RgctBill bill) throws BizAppException {
		// 检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaleBackService",
					"rejectSaleBackEndorse");
			this.checkPreStatus(bill, method, null);
			String methodName = "";
			if (RcConstants.REDISCOUNT_CENTER.equals(bill.getHist()
					.getIsRediscCenter())) {
				// 再贴现
				methodName = RcConstants.COMMON_REDISCOUNT_CENTER_BACK;
//				method.setParam("1");
			} else {
				methodName = RcConstants.COMMON_REDISCOUNT_BACK;
//				method.setParam("0");
			}
			if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo()
					.getBillClass())) {
				// 发送报文
				sendDraft(bill, method, RcConstants.DRAFT_SIGN,
						RcConstants.SIGN_NO);
			} else {
				// 走内部流程
				// this.commonStatus(getRbDAO().getRgctBillById(bill.getInfo().getId()),
				// BillConst.COMMON_SIGNUP, "11");
				this.commonSignUp(bill, methodName, RcConstants.SIGN_NO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}

}
