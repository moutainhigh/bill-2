/********************************************
* 文件名称: MenuDao.java
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.Menu;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
public class MenuDao{

public int addMenu(Menu obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tmenu(menu_code,menu_name,menu_url,menu_type,menu_level,order_no,parent_menu_code,open_flag,tree_idx,logon_flag,icon_display,menu_icon,menu_class,remark)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getMenuCode().trim(),obj.getMenuName().trim(),obj.getMenuUrl().trim(),obj.getMenuType().trim(),obj.getMenuLevel().trim(),
	obj.getOrderNo(),obj.getParentMenuCode().trim(),obj.getOpenFlag().trim(),obj.getTreeIdx().trim(),
	obj.getLogonFlag().trim(),obj.getIconDisplay().trim(),obj.getMenuIcon().trim(),obj.getMenuClass().trim(),
	obj.getRemark().trim());
}

public int deleteMenu(Menu obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tmenu where menu_code=?",obj.getMenuCode());
}

public int deleteMenu(String menuCode) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tmenu where menu_code=?",menuCode);
}

public int modifyMenu(Menu obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tmenu set menu_name=?,menu_url=?,menu_type=?,menu_level=?,order_no=?,parent_menu_code=?,open_flag=?,tree_idx=?,logon_flag=?,icon_display=?,menu_icon=?,menu_class=?,remark=? where menu_code=?",
	obj.getMenuName().trim(),obj.getMenuUrl().trim(),obj.getMenuType().trim(),obj.getMenuLevel().trim(),
	obj.getOrderNo(),obj.getParentMenuCode().trim(),obj.getOpenFlag().trim(),obj.getTreeIdx().trim(),
	obj.getLogonFlag().trim(),obj.getIconDisplay().trim(),obj.getMenuIcon().trim(),obj.getMenuClass().trim(),
	obj.getRemark().trim(),obj.getMenuCode().trim());
}

public int modifyMenu(Menu obj,String menuCode) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tmenu set menu_name=?,menu_url=?,menu_type=?,menu_level=?,order_no=?,parent_menu_code=?,open_flag=?,tree_idx=?,logon_flag=?,icon_display=?,menu_icon=?,menu_class=?,remark=? where menu_code=?",
	obj.getMenuName().trim(),obj.getMenuUrl(),obj.getMenuType(),obj.getMenuLevel(),
	obj.getOrderNo(),obj.getParentMenuCode().trim(),obj.getOpenFlag().trim(),obj.getTreeIdx(),
	obj.getLogonFlag().trim(),obj.getIconDisplay().trim(),obj.getMenuIcon().trim(),obj.getMenuClass().trim(),
	obj.getRemark().trim(),menuCode);
}

public Menu getMenu(String menuCode) throws SQLException {
	IDB session = DBFactory.getDB();
	Menu obj = (Menu)session.getObject("select * from tmenu where menu_code=?",Menu.class,menuCode);
	return obj;
}

public int checkExists(String menuCode) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.account("select count(0) from tmenu where menu_code=?",menuCode);
}

public List<Menu> getMenuList(Menu menu,Page page) throws SQLException {
	IDB session = DBFactory.getDB();
	List<Object> param = new ArrayList<Object>();
	StringBuffer sql = new StringBuffer("SELECT * FROM TMENU WHERE 1=1 ");
	if(StringUtils.isNotBlank(menu.getMenuLevel())){
		sql.append(" AND MENU_LEVEL = ? ");
		param.add(menu.getMenuLevel().trim());
	}
	if(StringUtils.isNotBlank(menu.getMenuCode())){
		sql.append(" AND MENU_CODE like '%"+menu.getMenuCode().trim()+"%'");
		/*sql.append(" AND MENU_CODE like ? ");
		param.add("'%"+menu.getMenuCode().trim()+"%'");*/
	}
	if(StringUtils.isNotBlank(menu.getMenuName())){
		sql.append(" AND MENU_NAME like '%"+menu.getMenuName().trim()+"%'");
	}
	if(StringUtils.isNotBlank(menu.getParentMenuCode())){
		sql.append(" AND PARENT_MENU_CODE LIKE ? ");
		param.add("'%"+menu.getParentMenuCode().trim()+"%'");
	}
	if(StringUtils.isNotBlank(menu.getTreeIdx())){
		sql.append(" AND TREE_IDX LIKE ? ");
		param.add("%"+menu.getTreeIdx().trim()+"%");
	}
	sql.append(" ORDER BY MENU_LEVEL,ORDER_NO");
	return session.getObjectListByListForPage(sql.toString(),Menu.class,page.getCurrentResult(),page.getShowCount(),param);
}
/**
 * 功能描述：根据父类索引得到最大的子类树索引
 * @param pTreeIdx
 * @return
 * @throws BizAppException
 */
public String getMaxTreeIdxForParentIdx(String pTreeIdx) throws SQLException{
	IDB session = DBFactory.getDB();
	return session.getData("SELECT MAX(rtrim(tree_idx)) as treeidx FROM tmenu t WHERE t.tree_idx like ?",pTreeIdx+"%").getString(1);
}


/**
 * 功能描述：根据父类树索引删除所有子类菜单以及本身
 * @param pTreeIdx
 * @return
 * @throws BizAppException
 */
public void deleteForParentIdx(String pTreeIdx) throws SQLException{
	IDB session = DBFactory.getDB();
	session.execute("delete FROM tmenu t WHERE t.tree_idx like ?",pTreeIdx+"%");
}

/**
 * 功能描述：根据父类菜单编号查询子菜单的个数来进行删除判断
 * @param pTreeIdx
 * @return
 * @throws BizAppException
 */
public int getMenuCountByParentMenuCode(String menuCode) throws SQLException{
	IDB session = DBFactory.getDB(); // 获取数据库连接
	String sql = "select count(1) from tmenu t where t.parent_menu_code = ?";
	int count = session.account(sql,menuCode);
	return count;
}

/**
 * 功能描述：根据条件查询集合
 * @param pTreeIdx
 * @return
 * @throws BizAppException
 */
public List<Menu> getMenuCountByParentMenuCode(String rolecodes,String likestr) throws SQLException{
	IDB session = DBFactory.getDB();
	List<Menu> obj = (List<Menu>)session.getObjectList("select distinct m.* from TMENU m, TROLE_MENU r where m.MENU_CODE=r.MENU_CODE and m.MENU_LEVEL='2'and menu_name like '%"+likestr+"%' and r.ROLE_CODE in (?)",Menu.class,rolecodes);
	return obj;
}


public List<Menu> getMenuListByParentMenuCode(String parent_menu_code) throws SQLException{
	IDB session = DBFactory.getDB(); // 获取数据库连接
	String sql = "select * from tmenu  where parent_menu_code = ? order by MENU_CODE desc";
	return session.getObjectList(sql, Menu.class,parent_menu_code);
}

}
