/********************************************
* 文件名称: RoleMenuDao.java
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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.MenuTreeNode;
import com.herongtech.console.domain.bean.RoleMenu;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class RoleMenuDao{
	public int addRoleMenu(RoleMenu obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("insert into trole_menu(id,role_code,menu_code, operation_code) values (?,?,?,?)",
		obj.getId(),obj.getRoleCode(),obj.getMenuCode(), obj.getOperationCode());
	}
	
	public int deleteRoleMenu(RoleMenu obj) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from trole_menu where role_code=? and menu_code=?",obj.getRoleCode(),obj.getMenuCode());
	}
	
	public int deleteRoleMenu(String roleCode,String menuCode) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from trole_menu where role_code=? and menu_code=?",roleCode,menuCode);
	}
	
	public int modifyRoleMenu(RoleMenu obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update trole_menu set id=?, operation_code=? where role_code=? and menu_code=?",
		obj.getId(),obj.getRoleCode(),obj.getMenuCode(), obj.getOperationCode());
	}
	
	public int modifyRoleMenu(RoleMenu obj,String roleCode,String menuCode) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update trole_menu set id=?, operation_code=? where role_code=? and menu_code=?",
		obj.getId(),roleCode,menuCode, obj.getOperationCode());
	}
	
	public RoleMenu getRoleMenu(String roleCode,String menuCode) throws SQLException {
		IDB session = DBFactory.getDB();
		RoleMenu obj = (RoleMenu)session.getObject("select * from trole_menu where role_code=? and menu_code=?",RoleMenu.class,roleCode,menuCode);
		return obj;
	}

	/**
	 * 功能描述：根据角色编码清空功能菜单
	 * @param roleCode
	 * @return
	 * @throws SQLException 
	 */
	public int clearMenuListByRoleCode(String roleCode) throws SQLException {
		String sql = "delete from Trole_Menu where role_code = ?";
		IDB session = DBFactory.getDB();
		return session.execute(sql, roleCode);
	}
	
	public List<MenuTreeNode> getMenuTreeNodeList(Menu menu,String roleCode) throws SQLException {
		IDB session = DBFactory.getDB();
		List<Object> param = new ArrayList<Object>();
		param.add(roleCode);
		param.add(roleCode);
		StringBuffer sql = new StringBuffer("SELECT M.MENU_CODE AS ID,M.MENU_NAME AS NAME,M.PARENT_MENU_CODE PARENT,");
		sql.append("M.OPEN_FLAG AS OPEN,case RM.ROLE_CODE when ? then 'true' else 'false' end CHECKED FROM TMENU M ");
		sql.append("LEFT JOIN (select * from TROLE_MENU t where t.role_code = ?) RM ON RM.MENU_CODE = M.MENU_CODE WHERE 1=1 ");
		if(StringUtils.isNotBlank(menu.getMenuLevel())){
			sql.append(" AND M.MENU_LEVEL = ? ");
			param.add(menu.getMenuLevel());
		}
		if(StringUtils.isNotBlank(menu.getMenuName())){
			sql.append(" AND M.MENU_NAME = ? ");
			param.add(menu.getMenuName());
		}
		if(StringUtils.isNotBlank(menu.getParentMenuCode())){
			sql.append(" AND M.PARENT_MENU_CODE = ? ");
			param.add(menu.getParentMenuCode());
		}
		if(StringUtils.isNotBlank(menu.getTreeIdx())){
			sql.append(" AND M.TREE_IDX LIKE ? ");
			param.add("%"+menu.getTreeIdx()+"%");
		}
		sql.append(" ORDER BY M.ORDER_NO");
		return session.getObjectListByList(sql.toString(),MenuTreeNode.class,param);
	}
	
	/**
	 * 功能描述：根据菜单检查是否存在角色信息
	 * @param menuCode
	 * @return
	 * @throws SQLException
	 */
	public int checkRoleInfo(String menuCode) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.account("select count(0) from trole_menu where menu_code=?",menuCode);
	}
}
