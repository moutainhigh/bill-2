/********************************************
 * 文件名称: OperationService.java
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

package com.herongtech.console.service.operation;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.Operation;
import com.herongtech.console.domain.dao.OperationDao;
import com.herongtech.console.service.interfaces.IOperationService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 系统参数取法
 *
 */
public class OperationService implements IOperationService{
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取系统参数表
	 */
	public  Operation getOperation(String operationCode,String menuCode)throws BizAppException{
		OperationDao dao=new OperationDao();
		try {
			return dao.getOperation(operationCode,menuCode);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入系统参数表
	 */
	public void addOperation(Operation operation)throws BizAppException{
		OperationDao dao=new OperationDao();
		try {
			if (dao.addOperation(operation) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Operation失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改系统参数表
	 */
	public void modifyOperation(Operation operation)throws BizAppException{
		OperationDao dao=new OperationDao();
		try {
			if (dao.modifyOperation(operation) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改Operation失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}

	
}
