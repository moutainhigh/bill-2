/********************************************
 * 文件名称: RcRegBillService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.service.rcservice;

import java.sql.SQLException;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRcRegBillService;

public class RcRegBillService extends RcBaseService implements IRcRegBillService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**登记票据登记
	 */
	
	public void regBill(RgctBill rgctBill)throws BizAppException{
		
		RcServiceFactory.getRgctBillInfoService().addRgctBillInfo(rgctBill.getInfo());
		
		RcServiceFactory.getRgctBillHistService().addRgctBillHist(rgctBill.getHist());
	}
	
	
	/*
	 * 查询可出票信息  */
	public String getRegBillSql(RcBaseSearchBean sb) throws BizAppException {
		
    	RgctMethod method = RcServiceFactory.getRgctMethodService().
    			getRgctMethod(IConstants.BA.BA, IConstants.BA.IRcRegBillService, "queryRegBill");
    	
    	if (method == null){
    		throw new BizAppException(IErrorNo.ERR_RC_007, "参数配置错误");
    	}
    	
    	String sql = queryBillSql(sb, method, false);
    	
    	return sql;
	}
	
	public void registerRequest(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			RgctMethodDao dao=new RgctMethodDao();
			method = dao.getRgctMethod(IConstants.BA.IRcRegBillService,"registerRequest");
			this.checkPreStatus(bill, method, null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
				
		
	}
	
	public String queryRemitterBack(RcBaseSearchBean sb)
			throws BizAppException {
		RgctMethodDao rgctMethodDao=new RgctMethodDao();
		RgctMethod method;
		String sql ="";
		try {
			method = rgctMethodDao.getRgctMethod("IRcCancelBillService", "queryRemitterBack");
			if (method == null){
				throw new BizAppException(IErrorNo.ERR_RC_007, "参数配置错误");
			}
			sql = queryBillSql(sb, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return sql;
	}

	public void remitterBack(RgctBill bill) throws BizAppException {
		// 检查基本的报文内容
		RgctMethodDao rgctMethodDao=new RgctMethodDao();
		RgctValidateUtils.checkElecRequest(bill);
		// 检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcCancelBillService","remitterBack");
			this.checkPreStatus(bill, method, null);
			if (!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())) {
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
			} /*else {
				// 走内部流程
				this.commonStatus(bill, RcConstants.COMMON_REBACK, null);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		
	}
	public void registerYes(RgctBill bill) throws BizAppException {
		try {
			RgctMethodDao dao=new RgctMethodDao();
			RgctMethod method = dao.getRgctMethod(IConstants.BA.IRcRegBillService,"registerYes");
//			bill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
//			updateRgctBillInfo(bill.getInfo());
			changeStatus(bill, method, null);

		} catch (Exception e) {
			throw new BizAppException(e);
		}
	}
}
