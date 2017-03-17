/********************************************
 * 文件名称: RcSaleService.java
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
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.web.busicontroller.common.SalebackCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;
import com.herongtech.rc.service.RcServiceFactory;


public class RcSaleBackService extends RcBaseService implements IRcSaleBackService {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private RgctMethodDao rgctMethodDao=new RgctMethodDao();
	
	public void regBackEndorse(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBuyBackService","regBackEndorse");
			// 检查前置状态
			this.checkPreCondi(bill, method, null);
//			String methodName = "";
			// 回购
			/*if (RcConstants.REDISCOUNT_CENTER.equals(bill.getHist().getIsRediscCenter())) {
				// 再贴现
//				methodName = RcConstants.COMMON_REDISCOUNT_CENTER_BACK;
				method.setParam("1");
			} else {
//				methodName = RcConstants.COMMON_REDISCOUNT_BACK;
				method.setParam("0");
			}*/
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
			} else {
				// 走内部流程
				// this.commonStatus(bill, methodName, "1");
				this.changeStatus(bill, method, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
			
		
	}
	
	@Override
	public void cancelBackEndorse(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcBuyBackService","cancelBackEndorse");
			this.checkPreStatus(bill, method, null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,RcConstants.TYPE_CANCEL);
			}else{
				// 走内部流程
				this.commonCancel(bill, bill.getHist().getCurStatus());
				this.commonStatus(bill, RcConstants.COMMON_DRAWBACK, "1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}

	/**赎回方同意返售背书登记
	 * @throws BizAppException 
	 * @throws SQLException */
	@Override
	public RgctBill regAgreeEndorse(RgctBill bill) throws BizAppException, SQLException {
			bill.getHist().setIsBuy("0");//卖出

			if (bill == null) {
				throw new BizAppException("赎回方同意返售背书登记失败，");
			}
			String rgctId = bill.getInfo().getId();
			RgctBill rgctBill = RcServiceFactory.getRcSaleService().getRgctBillById(rgctId);
			if (SalebackCodeConst.IS_INNER_TRUE.equals(bill.getHist().getIfInner())) {
				return rgctBill;
			} else {
				return changeStatus(bill,rgctMethodDao.getRgctMethod("IRcBuyBackService","regAgreeEndorse"), null);
			}
		
	}
}
