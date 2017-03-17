/********************************************
 * 文件名称: RcUnimpawnService.java
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

public class RcUnimpawnService extends RcBaseService implements IRcUnimpawnService{
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	private RgctMethodDao rgctMethodDao=new RgctMethodDao();
	
	@Override
	public String queryPreunimpawnBill(RcBaseSearchBean sb) throws BizAppException {
		RgctMethod method;
		try {
			sb.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			method = rgctMethodDao.getRgctMethod("IRcUnimpawnService","queryPreunimpawnBill");
		//	return queryRC(sb, page, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method, false);
	}
	
	@Override
	public void submitUnimpawnApply(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcUnimpawnService","submitUnimpawnApply");
			this.checkPreCondi(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
		}else{
			//走内部流程
			this.changeStatus(bill, method, null);
		}
	}
	
	@Override
	public void cancelUnimpawnApply(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcUnimpawnService","cancelUnimpawnApply");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_APPLY,RcConstants.TYPE_CANCEL);
		}else{
			//走内部流程
			this.commonCancel(bill,bill.getHist().getCurStatus());
			this.commonStatus(bill, RcConstants.TYPE_CANCEL, "1");
		}
		
	}
	
	@Override
	public String queryPresignBill(RcBaseSearchBean sb)throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcUnimpawnService","queryPresignBill");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method, false);
	}
	
	@Override
	public void signUnimpawn(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcUnimpawnService","signUnimpawn");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_YES);
		}else{
			//走内部流程
			this.changeStatus(bill, method, null);
		}
		
	}
	
	@Override
	public void rejectSignUnimpawn(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcUnimpawnService","rejectSignUnimpawn");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_NO);
		}else{
			//走内部流程
			this.commonStatus(bill, RcConstants.COMMON_SIGNUP, "11");
			this.commonSignUp(bill,RcConstants.COMMON_UNCOLLECT, RcConstants.SIGN_NO);
		}
	}

}
