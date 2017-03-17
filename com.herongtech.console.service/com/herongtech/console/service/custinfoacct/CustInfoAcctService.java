/********************************************
 * 文件名称: CustInfoAcctService.java
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
package com.herongtech.console.service.custinfoacct;

import java.sql.SQLException;
import java.util.List;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.domain.dao.CustInfoAcctDao;
import com.herongtech.console.domain.dao.CustInfoDao;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 客户帐号信息参数取法
 *
 */

public class CustInfoAcctService implements ICustInfoAcctService {

	@Override
	public CustInfoAcct getParam(String custNo, String acctNo)
			throws BizAppException {
		CustInfoAcctDao dao=new CustInfoAcctDao();
		try {
			return dao.getCustInfoAcct(custNo, acctNo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	@Override
	public CustInfoAcct getParam(String acctNo)
			throws BizAppException {
		CustInfoAcctDao dao=new CustInfoAcctDao();
		try {
			return dao.getCustInfoAcct(acctNo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	@Override
	public CustInfoAcct getParamByCustNo(String custNo)
			throws BizAppException {
		CustInfoAcctDao dao=new CustInfoAcctDao();
		try {
			return dao.getCustInfoAcctByCustNo(custNo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入客户帐号信息表
	 */
	@Override
	public void addParam(CustInfoAcct custIA) throws BizAppException {
		CustInfoAcctDao dao=new CustInfoAcctDao();
		try {
			if (dao.addCustInfoAcct(custIA) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加客户帐号信息失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}

	}

	/**
	 * 修改客户帐号信息表
	 */
	@Override
	public void modifyParam(CustInfoAcct custIA) throws BizAppException {
		CustInfoAcctDao dao=new CustInfoAcctDao();
		try {
			if (dao.modifyCustInfoAcct(custIA) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改客户帐号信息失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}

	}
	/**
	 * 查找list方法
	 */
	public List<CustInfoAcct> resultList1(String custNo)throws BizAppException{
		CustInfoAcctDao dao=new CustInfoAcctDao();
		List<CustInfoAcct> custInfoAcct;
		try {
			custInfoAcct = dao.getCustInfoAcctByCustNo1(custNo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"查询失败");
		}
		return custInfoAcct;
	}

}
