/********************************************
 * 文件名称: CustInfoService.java
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
package com.herongtech.console.service.custinfo;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.dao.CustInfoDao;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 客户信息参数取法
 *
 */

public class CustInfoService implements ICustInfoService {

	@Override
	public CustInfo getParam(String custNo) throws BizAppException {
		CustInfoDao dao=new CustInfoDao();
		try {
			return dao.getCustInfo(custNo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 插入客户信息表
	 */
	@Override
	public void addParam(CustInfo custI) throws BizAppException {
		CustInfoDao dao=new CustInfoDao();
		try {
			if (dao.addCustInfo(custI) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加客户信息失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}

	}

	/**
	 * 修改客户信息表
	 */
	@Override
	public void modifyParam(CustInfo custI) throws BizAppException {
		CustInfoDao dao=new CustInfoDao();
		try {
			if (dao.modifyCustInfo(custI) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改客户信息失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}

	}

	/**
	 * 根据组织机构代码查找是否存在客户
	 */
	@Override
	public boolean validateCustInfoByOrgNo(String orgNo) throws BizAppException {
		CustInfoDao dao=new CustInfoDao();
		CustInfo custInfo;
		try {
			custInfo = dao.getCustInfoByOrgNo(orgNo);
			if(custInfo == null){
				return false;
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"查询失败");
		}
		return true;
	}

	/**
	 * 根据组织机构代码和客户名称查找客户信息
	 */
	@Override
	public CustInfo getCustInfoByOrgNoAndName(String orgNo, String custName)
			throws BizAppException {
		CustInfoDao dao=new CustInfoDao();
		CustInfo custInfo;
		try {
			custInfo = dao.getCustInfoByOrgNoAndName(orgNo, custName);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"查询失败");
		}
		return custInfo;
	}

}
