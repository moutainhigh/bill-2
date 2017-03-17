/********************************************
 * 文件名称: RoleService.java
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

package com.herongtech.console.service.role;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.domain.bean.Role;
import com.herongtech.console.domain.dao.RoleDao;
import com.herongtech.console.service.interfaces.IRoleService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 角色
 */
public class RoleService implements IRoleService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private RoleDao dao=new RoleDao();
	/**
	 * 根据角色编码获取橘色信息
	 */
	public  Role getRole(String roleId) throws BizAppException{
		try {
			return dao.getRole(roleId);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入角色表
	 */
	public void addRole(Role Role)throws BizAppException{
		try {
			if (dao.addRole(Role) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Role失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改角色表
	 */
	public void modifyRole(Role role)throws BizAppException{
		try {
			if (dao.modifyRole(role) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改Role失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 删除角色表记录
	 */
	public int deleteRole(String code) throws BizAppException{
		try {
			return dao.deleteRole(code) ;
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 是否角色表存在该角色编码的记录
	 * @throws BizAppException 
	 */
	public int checkExists(String roleCode) throws BizAppException{
		try {
			return dao.checkExists(roleCode);
		} catch (SQLException e) {
			 throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	public List<Role> getAllRoleList() throws SQLException{
		return dao.getAllRole();
	}

	@Override
	public List<Role> getRoleListByCodes(List<String> roleIds) throws SQLException {
		List<Role> roleList = new ArrayList<Role>();
		for(String roleCode:roleIds){
			roleList.add(dao.getRole(roleCode));
		}
		return roleList;
	}
	
}
