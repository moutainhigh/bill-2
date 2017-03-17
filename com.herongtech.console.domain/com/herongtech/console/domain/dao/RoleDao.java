/********************************************
* 文件名称: RoleDao.java
* 系统名称: 合融基础技术平台V3.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.Role;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class RoleDao{
	
	public int addRole(Role obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("insert into trole(role_code,role_name,creator,role_status,remark)values(?,?,?,?,?)",
		obj.getRoleCode(),obj.getRoleName(),obj.getCreator(),obj.getRoleStatus(),obj.getRemark());
	}
	
	public int deleteRole(Role obj) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from trole where role_code=?",obj.getRoleCode());
	}
	
	public int deleteRole(String roleCode) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from trole where role_code=?",roleCode);
	}
	
	public int modifyRole(Role obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update trole set role_name=?,creator=?,role_status=?,remark=? where role_code=?",
		obj.getRoleName(),obj.getCreator(),obj.getRoleStatus(),obj.getRemark(),
		obj.getRoleCode());
	}
	
	public int modifyRole(Role obj,String roleCode) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update trole set role_name=?,creator=?,role_status=?,remark=? where role_code=?",
		obj.getRoleName(),obj.getCreator(),obj.getRoleStatus(),obj.getRemark(),
		roleCode);
	}
	
	public Role getRole(String roleCode) throws SQLException {
		IDB session = DBFactory.getDB();
		Role obj = (Role)session.getObject("select * from trole where role_code=?",Role.class,roleCode);
		return obj;
	}
	
	/**
	 * 是否角色表记录
	 * @throws SQLException 
	 */
	public int checkExists(String roleCode) throws SQLException{
		String sql = "select count(0) from trole where role_code = ?";
		IDB session = DBFactory.getDB();
		return session.account(sql, roleCode);
	}

	/**
	 * 获取全部角色清单
	 * @return
	 * @throws SQLException
	 */
	public List<Role> getAllRole() throws SQLException {
		IDB session = DBFactory.getDB();
		List<Role> roleList = (List<Role>)session.getObjectList("select * from trole",Role.class);
		return roleList;
	}
}
