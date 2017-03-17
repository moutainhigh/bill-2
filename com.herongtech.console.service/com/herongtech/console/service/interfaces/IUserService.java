/********************************************
 * 文件名称: IUserService.java
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

package com.herongtech.console.service.interfaces;


import java.sql.SQLException;
import java.text.ParseException;

import com.herongtech.console.domain.bean.User;
import com.herongtech.exception.impl.BizAppException;

public interface IUserService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	public User checkUserExits(User user) throws BizAppException;
	
	/**
	 * 通过用户ID 取用户角色ID  多个角色以“,”拼接
	 * @param userId
	 * @return
	 * @throws BizAppException
	 */
	public String getUserRoleByUserId(String userId) throws BizAppException;
	
	public void pwdInit(User user, String newPwd) throws BizAppException;
	
	public int getUsersOfThisRole(String id) throws BizAppException;

	public void modifyUser(User user) throws BizAppException;

	public void addUser(User user) throws BizAppException;

	public void addLoginSuccess(User user) throws BizAppException;

	public int checkUserExitsById(User params) throws BizAppException;

	public void addLoginFail(User params) throws BizAppException;

	public User getUserByUserId(String userId) throws SQLException, BizAppException;

	public boolean checkUserPsw(User user, String password) throws BizAppException;

	public void modifyUser(String userId, String confirmNewPsw) throws BizAppException;

	public int checkPassModifyDate(String passModifyDate) throws ParseException;

	public User getUserByDeptNo(String deptNo) throws BizAppException;
}
