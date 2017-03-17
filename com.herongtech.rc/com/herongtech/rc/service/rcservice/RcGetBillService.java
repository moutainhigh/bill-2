/********************************************
 * 文件名称: RcGetBillService.java
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
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;

public class RcGetBillService extends RcBaseService implements IRcGetBillService{
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private RgctMethodDao rgctMethodDao=new RgctMethodDao();
	
	public RgctBill cancelGetApply(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "cancelGetApply");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill cancelSignGet(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "cancelSignGet");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill getOutpool(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "getOutpool");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public List queryPregetBill(RcBaseSearchBean sb, Page pg)throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "queryPregetBill");
			return query(sb, pg, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public List queryScatteredBill(RcBaseSearchBean sb, Page pg)throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "queryScatteredBill");
			return query(sb, pg, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	public List queryGetBillOutpoolBill(RcBaseSearchBean sb, Page pg, String param)throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillOutpoolService", "queryScatteredBill");
			return query(sb, pg, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill rejectSignGet(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "rejectSignGet");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill reverseGetOutpool(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "reverseGetOutpool");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill signGet(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "signGet");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill submitGetApply(RgctBill bill) throws BizAppException {
		convertEndorsorInfo(bill);
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "submitGetApply");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill signGetPool(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "signGetPool");
			return changeStatus( bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	public RgctBill getOutpool(RgctBill bill, int type) throws BizAppException {
		return null;
	}
	
	public RgctBill storageChgBuy(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "storageChgBuy");
			return changeStatus( bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill storageChgBuyBack(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "storageChgBuyBack");
			return changeStatus( bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	
	public RgctBill storageBuyBackBefore(RgctBill bill) throws BizAppException {
		RgctMethod method;
		// 状态从J_02转BPA_19
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "storageBuyBackBefore");
			return changeStatus( bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	public RgctBill storageBuyBackAfter(RgctBill bill) throws BizAppException {
		RgctMethod method;
		// 状态从J_02转BPA_20
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "storageBuyBackAfter");
			return changeStatus( bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public List queryRejectSignGetBill(RcBaseSearchBean sb, Page pg)throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "queryRejectSignGetBill");
			return query(sb, pg, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public List queryWSignGetBill(RcBaseSearchBean sb, Page pg)throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "queryWSignGetBill");
			return query(sb, pg, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		
	}

	public RgctBill reject2WSign(RgctBill bill) throws BizAppException {
		RgctMethod method;
		// 状态转BPA_02
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "reject2WSign");
			return changeStatus( bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill rejectSignInput(RgctBill bill) throws BizAppException {
		RgctMethod method;
		// Run_status状态转BPB1_97
		try {
			method = rgctMethodDao.getRgctMethod("IRcGetBillService", "rejectSignInput");
			return changeStatus( bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	

}
