/********************************************
 * 文件名称: RcEndorseService.java
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

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctEndoHist;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctEndoHistDao;
import com.herongtech.rc.domain.dao.RgctMethodDao;
import com.herongtech.rc.service.rcservice.RcBaseService;

import java.sql.SQLException;
import java.util.List;

public class RcEndorseService extends RcBaseService implements IRcEndorseService {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	private RgctMethodDao rgctMethodDao=new RgctMethodDao();
	
	
	public String queryEndorseFrom(RcBaseSearchBean sb) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcEndorseService","queryEndorseFrom");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method,false);
	}

	@Override
	public void regEndorse(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcEndorseService","regEndorse");
			this.checkPreCondi(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
		}  
		/*else {			
			//走内部流程
			this.commonStatus(bill, BillConst.COMMON_ENDORSE, "1");
		}*/

	}

	@Override
	public void cancelEndorse(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcEndorseService","cancelEndorse");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_APPLY,RcConstants.TYPE_CANCEL);
		} 

	}

	public String queryEndorseTo(RcBaseSearchBean sb) throws BizAppException {
		sb.setIsLock(IDict.K_LOCK.K_LOCK_NO);
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcEndorseService","queryEndorseTo");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		return queryBillSql(sb, method,false);
	}

	public void signEndorse(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcEndorseService","signEndorse");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_YES);
		} 

	}

	public void rejectEndorse(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcEndorseService","rejectEndorse");
			this.checkPreStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			// 发送报文
			sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_NO);
		} 
				
	}

	@Override
	public List<RgctEndoHist> getRgctEndoList(String rgctId)
			throws BizAppException {
		RgctEndoHistDao dao = new RgctEndoHistDao();
		List<RgctEndoHist> list = null;
		try {
			list = dao.getRgctEndoHistList(rgctId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
