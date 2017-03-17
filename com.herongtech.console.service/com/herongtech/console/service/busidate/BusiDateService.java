/********************************************
 * 文件名称: BusiDateService.java
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

package com.herongtech.console.service.busidate;

import java.sql.SQLException;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.console.domain.dao.BusiDateDao;
import com.herongtech.console.service.interfaces.IBusiDateService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 系统参数取法
 *
 */
public class BusiDateService implements IBusiDateService{
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取系统信息表
	 */
	public  BusiDate getBusiDate(String sysBankNo)throws BizAppException{
		BusiDateDao dao=new BusiDateDao();
		try {
			return dao.getBusiDate(sysBankNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		//ISysConstant.DEFAULT_BANK_NO
	}
	
	/**
	 * 获取系统信息表
	 */
	public  BusiDate getDefaultBusiDate()throws BizAppException{
		BusiDateDao dao=new BusiDateDao();
		try {
			return dao.getBusiDate(IConstants.DEFAULT_BANK_NO);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		//ISysConstant.DEFAULT_BANK_NO
	}
	
	/**
	 * 插入系统信息表
	 */
	public void addBusiDate(BusiDate busiDate)throws BizAppException{
		BusiDateDao dao=new BusiDateDao();
		try {
			if (dao.addBusiDate(busiDate) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加BusiDate失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改系统信息表
	 */
	public void modifyBusiDate(BusiDate busiDate)throws BizAppException{
		BusiDateDao dao=new BusiDateDao();
		try {
			if (dao.modifyBusiDate(busiDate) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改BusiDate失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
}
