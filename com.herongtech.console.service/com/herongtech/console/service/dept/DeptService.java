/********************************************
 * 文件名称: DeptService.java
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
package com.herongtech.console.service.dept;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.Dept;
import com.herongtech.console.domain.dao.DeptDao;
import com.herongtech.console.service.interfaces.IDeptService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 部门参数取法
 *
 */
public class DeptService implements IDeptService {

	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取部门表
	 */
	@Override
	public Dept getParam(String depCode) throws BizAppException {
		DeptDao dao=new DeptDao();
		try {
			return dao.getDept(depCode);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 插入部门表
	 */
	@Override
	public void addParam(Dept dept) throws BizAppException {
		DeptDao dao=new DeptDao();
		try {
			if (dao.addDept(dept) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加部门失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}

	}

	/**
	 * 修改部门表
	 */
	@Override
	public void modifyParam(Dept dept) throws BizAppException {
		DeptDao dao=new DeptDao();
		try {
			if (dao.modifyDept(dept) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改部门失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		

	}

	
}
