/********************************************
 * 文件名称: MenuCache.java
 * 系统名称: 基础技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 菜单信息缓存类
 * 系统版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-12-12 上午09:39:00
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期     修改人员        修改说明
 * 20161212-01   fqz  创建  
 *********************************************/
package com.herongtech.console.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.RoleMenu;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.log.CommonLog;

public class MenuCache {

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	private static MenuCache menuCache = null;
	private static Map<String,Menu> menuMap = new HashMap<String, Menu>();//存储全部菜单缓存
	private static Map<String,List<Menu>> roleMenuMap = new HashMap<String,List<Menu>>();//存储角色对应菜单缓存
//	private static Map<String,List<Menu>> childrenMenuMap = new HashMap<String, List<Menu>>();
	
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	/**
	 * 返回MenuCache实例，采用启动的时候加载实例，故不需要锁
	 * @return
	 */
	public static MenuCache getInstance(){
		if(menuCache==null){
			menuCache = new MenuCache();
		}
		return menuCache;
	}
	/**
	 * 构造方法，初始化
	 */
	public MenuCache(){
		init();
	}
	/**
	 * 初始化方法
	 */
	public void init(){
		menuMap.clear();
		roleMenuMap.clear();
		IDB session = DBFactory.getNewDB();
		try {
			
			//将全部菜单放入缓存
			List<Menu> menuList = session.getObjectList("select  m.* from tmenu m", Menu.class);
			for(Menu menu : menuList){
				menuMap.put(menu.getMenuCode(), menu);
			}
			//将角色对应菜单放入缓存
			List<RoleMenu> rmList = session.getObjectList("select * from trole_menu", RoleMenu.class);
			for(RoleMenu rm : rmList){
				Menu menu = menuMap.get(rm.getMenuCode());
				if(menu!=null){
					if(roleMenuMap.containsKey(rm.getRoleCode())){
						roleMenuMap.get(rm.getRoleCode()).add(menu);
					}else{
						List<Menu> mList = new ArrayList<Menu>();
						mList.add(menu);
						roleMenuMap.put(rm.getRoleCode(), mList);
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("菜单信息加载失败",e);
		}finally{
			try {
				DBFactory.closeDB(session);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据角色编号获取菜单列表信息
	 * @param roleCode
	 * @return
	 */
	public List<Menu> getRoleMenuValue(String roleCode){
		try{
			r.lock();
			List<Menu> menuList = roleMenuMap.get(roleCode);
			if(menuList==null){
				return new ArrayList<Menu>();
			}
			return menuList;
		}finally{
			r.unlock();
		}
	}
	/**
	 * 根据菜单编号获取菜单信息
	 * @param menuCode
	 * @return
	 */
	public Menu getMenuValue(String menuCode){
		try{
			r.lock();
			Menu menu = menuMap.get(menuCode);
			return menu;
		}finally{
			r.unlock();
		}
	}
	/**
	 * 对象销毁
	 */
	public static void destroy(){
		if(menuCache!=null){
			menuCache.menuMap.clear();
		}
		menuCache = null;
	}
	/**
	 * 刷新缓存
	 */
	public void refresh(){
		try{
			w.lock();
			menuMap.clear();
			roleMenuMap.clear();
			init();
		}finally{
			w.unlock();
		}
	}
}
