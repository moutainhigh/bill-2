/********************************************
 * 文件名称: RcSaveBillService.java
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

public class RcSaveBillService extends RcBaseService implements IRcSaveBillService{
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private RgctMethodDao rgctMethodDao=new RgctMethodDao();
	
	public RgctBill cancelSaveApply(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "cancelSaveApply");
			this.checkPreStatus(bill, method, null);
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill cancelSignSave(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "cancelSignSave");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill inputSaveBill(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "inputSaveBill");
			return addBill(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public List queryPresaveBill(RcBaseSearchBean sb, Page pg)throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "queryPresaveBill");
			return query(sb, pg, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public List queryScatteredBill(RcBaseSearchBean sb, Page pg)throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "queryScatteredBill");
			return query(sb, pg, method, false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill rejectSignSave(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "rejectSignSave");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill reverseSaveInpool(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "reverseSaveInpool");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill saveInpool(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "saveInpool");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill signSave(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "signSave");
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

	public RgctBill submitSaveApply(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaveBillService", "submitSaveApply");
			this.checkPreStatus(bill, method, null);
			return changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}

}
