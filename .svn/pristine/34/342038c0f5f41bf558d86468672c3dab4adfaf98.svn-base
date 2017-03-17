/********************************************
 * 文件名称: IRoleService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-07-06 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.domain.bean.Role;
import com.herongtech.exception.impl.BizAppException;


public interface IRoleService {
	
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取系统参数
	 * 
	 * @param paramId 参数标识
	 */
	public Role getRole(String roleCode) throws BizAppException;
	
	/**
	 * 插入参数表
	 *
	 */
	public void addRole(Role role)throws BizAppException;
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRole(Role role)throws BizAppException;
	/**
	 * 删除角色表记录
	 */
	public int deleteRole(String codes)throws BizAppException;
	
	/**
	 * 是否角色表记录
	 */
	public int checkExists(String roleCode) throws BizAppException;
	
	/**
	 * 获取全部角色清单
	 * @return
	 * @throws SQLException
	 */
	public List<Role> getAllRoleList() throws SQLException;

	/**
	 * 根据角色码获取角色清单
	 * @param roleIds
	 * @return
	 * @throws SQLException 
	 */
	public List<Role> getRoleListByCodes(List<String> roleIds) throws SQLException;
}
