/********************************************
* 文件名称: UserContextMenuDao.java
* 系统名称: 合融基础技术平台V2.0
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

import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.UserContextMenu;
public class UserContextMenuDao{

public int addUserContextMenu(UserContextMenu obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tuser_context_menu(id,user_id,menu_name,menu_code,menu_url,context_menu_src,create_time)values(?,?,?,?,?,?,?)",
	obj.getId(),obj.getUserId(),obj.getMenuName(),obj.getMenuCode(),obj.getMenuUrl(),
	obj.getContextMenuSrc(),obj.getCreateTime());
}

public int deleteUserContextMenu(UserContextMenu obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tuser_context_menu where id=?",obj.getId());
}

public int deleteUserContextMenu(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tuser_context_menu where id=?",id);
}

public int modifyUserContextMenu(UserContextMenu obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tuser_context_menu set user_id=?,menu_name=?,menu_code=?,menu_url=?,context_menu_src=?,create_time=? where id=?",
	obj.getUserId(),obj.getMenuName(),obj.getMenuCode(),obj.getMenuUrl(),
	obj.getContextMenuSrc(),obj.getCreateTime(),obj.getId());
}

public int modifyUserContextMenu(UserContextMenu obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tuser_context_menu set user_id=?,menu_name=?,menu_code=?,menu_url=?,context_menu_src=?,create_time=? where id=?",
	obj.getUserId(),obj.getMenuName(),obj.getMenuCode(),obj.getMenuUrl(),
	obj.getContextMenuSrc(),obj.getCreateTime(),id);
}

public UserContextMenu getUserContextMenu(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	UserContextMenu obj = (UserContextMenu)session.getObject("select * from tuser_context_menu where id=?",UserContextMenu.class,id);
	return obj;
}
/**
 * 根据用户Id获取快捷菜单
 * @param userId
 * @return
 * @throws SQLException
 */
public List<UserContextMenu> getUserContextMenuByUserId(String userId) throws SQLException {
	IDB session = DBFactory.getDB();
	List<UserContextMenu> obj = session.getObjectList("select * from tuser_context_menu where user_id=?",UserContextMenu.class,userId);
	return obj;
}
/***
 * 根据用户Id和菜单编号删除菜单
 * @param userId
 * @return
 * @throws SQLException
 */
public int delUserContextMenu(String userId,String menuCode) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tuser_context_menu where user_id=? and menu_code=?",userId,menuCode);
}

}
