/********************************************
 * 文件名称: RcAssuranceService.java
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

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;

public class RcAssuranceService extends RcBaseService implements IRcAssuranceService{
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	private RgctMethodDao rgctMethodDao=new RgctMethodDao();
	
	/*public RgctBill inputSignGuarant(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IAssuranceService","inputSignGuarant");
			//getRgctMethod("CA","commonStatus","guarnt")
			return addBill(bill, method, "0",null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}*/
	
	public String queryAssure(RcBaseSearchBean sb) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IAssuranceService","queryAssure");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method, false);
	}
	
	public void assuranceApply(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IAssuranceService","assuranceApply");
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
			this.commonStatus(bill, RcConstants.COMMON_GUARNT, "1");
		}
	}
	
	public String queryAssuWaitSign(RcBaseSearchBean sb)throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IAssuranceService","queryAssuWaitSign");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method, false);
	}
	
	public void cancelAssure(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IAssuranceService","cancelAssure");
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
//			this.changeStatus(bill, method, null);
			this.commonCancel(bill, bill.getHist().getCurStatus());
			this.commonStatus(bill, RcConstants.COMMON_DRAWBACK, "1");
		}
	}
	
	public void assuSign(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IAssuranceService","assuSign");
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
//			this.changeStatus(bill, method, null);
			this.commonSignUp(bill, RcConstants.COMMON_GUARNT, RcConstants.SIGN_YES);
			this.commonStatus(bill, RcConstants.COMMON_GUARNT, bill.getHist().getCurStatus());
		}
	}
	
	public void registerAssuReject(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IAssuranceService","registerAssuReject");
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
			this.commonSignUp(bill,RcConstants.COMMON_GUARNT, RcConstants.SIGN_NO);
		}
	}
	
}
