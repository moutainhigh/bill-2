/********************************************
 * 文件名称: ISystemService.java
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

import java.util.List;

import com.herongtech.console.domain.bean.RoleUser;
import com.herongtech.console.domain.bean.User;
import com.herongtech.exception.impl.BizAppException;

public interface ISystemService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	/**
	 * 日志添加
	 * @param LogContent 内容
	 * @param loglevel 级别
	 * @param operatetype 类型
	 * @param TUser 操作人
	 */
	public void addLog(String LogContent, Short loglevel, Short operatetype);
	
	/**
	 * 获取用户关联角色
	 * @param RoleUser 用户信息
	 */
	public List<RoleUser> getUserRoleList(User user) throws BizAppException;
	
	
}
