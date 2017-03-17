/********************************************
 * 文件名称: UserService.java
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
package com.herongtech.console.service.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.herongtech.console.cache.MenuCache;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.system.manager.Client;
import com.herongtech.console.core.system.manager.ClientManager;
import com.herongtech.console.core.util.ContextHolderUtils;
import com.herongtech.console.core.util.NumberComparator;
import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.RoleUser;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.domain.dao.MenuDao;
import com.herongtech.console.domain.dao.RoleMenuDao;
import com.herongtech.console.service.interfaces.IMenuService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

public class MenuService implements IMenuService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	private MenuDao dao=new MenuDao();
	/**
	 * 获取用户权限的Menumap
	 * 
	 * @param user
	 * @return
	 */
	public Map<Integer, List<Menu>> getLevelMenuMap(User user) throws BizAppException {
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		
		if (client.getMenuMap() == null || client.getMenuMap().size() == 0) {
			Map<Integer, List<Menu>> functionMap = new HashMap<Integer, List<Menu>>();
			Map<String, Menu> loginActionlist = getUserMenuMap(user);
			
			if (loginActionlist.size() > 0) {
				Collection<Menu> allFunctions = loginActionlist.values();
				for (Menu function : allFunctions) {
		            if(function.getMenuType().equals(IDict.K_MENUYPE.MENUTYPE_OTHER)){
						continue;
					}
		            
					if (!functionMap.containsKey(Integer.parseInt(function.getMenuLevel()))) {
						functionMap.put(Integer.parseInt(function.getMenuLevel()),
								new ArrayList<Menu>());
					}
					functionMap.get(Integer.parseInt(function.getMenuLevel())).add(function);
				}
				
				// 菜单栏排序
				Collection<List<Menu>> c = functionMap.values();
				for (List<Menu> list : c) {
					Collections.sort(list, new NumberComparator());
				}
			}
			client.setMenuMap(functionMap);
			
			return functionMap;
		} else {
			return client.getMenuMap();
		}
		
	}
	/**
	 * 获取用户角色列表
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public List<RoleUser> getUserRoleList(User user) throws SQLException{
		IDB dbsession = DBFactory.getDB();
		List<RoleUser> rList = dbsession.getObjectList("select distinct  * from trole_user where user_id=?", RoleUser.class, user.getUserId());
		return rList;
	}
	
	/**
	 * 获取用户菜单列表
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Menu> getUserMenuMap(User user) throws BizAppException {
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		
		if (client.getMenus() == null || client.getMenus().size() == 0) {
			
			Map<String, Menu> loginActionlist = new HashMap<String, Menu>();
			
//			StringBuilder sql = new StringBuilder("select distinct f.* from trole_user ru, trole_menu rf, tmenu f  ")
//	           .append("where ru.role_code = rf.role_code and rf.menu_code=f.menu_code and ru.user_id=? ");
			
			
			List<Menu> menuList = new ArrayList<Menu>();
			try {

				List<RoleUser> ruList = this.getUserRoleList(user);
				for(RoleUser ru:ruList){
					List<Menu> list = MenuCache.getInstance().getRoleMenuValue(ru.getRoleCode());
					if(list!=null && !list.isEmpty()){
						menuList.addAll(list);
					}
				}
				/*if(menuList.isEmpty()){
					menuList = dbsession.getObjectList(sql.toString(), Menu.class, user.getUserId());
				}*/
			} catch (SQLException e) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库查询异常");
			} 
	        for(Menu menu: menuList){
	        	loginActionlist.put(menu.getMenuCode(), menu);//能够去除menuList中的重复项
	        }
	        
            client.setMenus(loginActionlist);
		}
		return client.getMenus();
	}
	/**
	 * 获取用户权限的第一级列表
	 * 
	 * @param user
	 * @return
	 */
	public List<Menu> getFirstLevelMenu(User user) throws BizAppException {
		return getLevelMenuMap(user).get(new Integer(0));
	}
	
	public List<Menu> getAllMenu(User user) throws BizAppException{
		List<Menu> mList = new ArrayList<Menu>();
		Collection<Menu> menus = getUserMenuMap(user).values();
		for(Menu menu : menus){
			mList.add(menu);
		}
		return mList;
	}
	/**
	 * 获取三级菜单Tab页显示名称
	 * @param menu
	 * @return
	 */
	public String getMenuTabName(Menu menu){
		String menuName = menu.getMenuName();
		String menuCode_1 = menu.getMenuCode().substring(0, 2);
		String menuCode_2 = menu.getMenuCode().substring(2, 4);
		String name = "";
		if("04".equals(menuCode_1)){//票据池
			name += "["+IDict.K_MENU_CODE_1_NAME.get(menuCode_2)+"]"+menuName;
		}else if("03".equals(menuCode_1)){//公共模块
			return menuName;
		}else{//01-纸票系统 02-电票系统
			if(IDict.K_MENU_CODE_2_NAME.containsKey(menuCode_2)){
				name += "["+IDict.K_MENU_CODE_1_NAME.get(menuCode_1);
				String menuCode_2_name = IDict.K_MENU_CODE_2_NAME.get(menuCode_2);
				if(menuName.indexOf(menuCode_2_name)<0){//菜单名中不包含识别交易的字段（如撤销申请   不像贴现申请，一眼就能够看出是贴现交易）
					name += "."+IDict.K_MENU_CODE_2_NAME.get(menuCode_2);
				}
				name += "]"+menuName;
			}else{
				return menuName;
			}
		}
		return name;
	}
	/**
	 * 获取用户权限对应MenuCode的菜单json，用菜单加载；非树加载
	 * 
	 * @param user
	 * @return
	 */
	public String getMenuJson(User user, String menuCode) throws BizAppException {
		List<Menu> subMenuList = this.listSubMenuByParentCode(user, menuCode);
		
		StringBuffer sb = new StringBuffer();
		/*sb.append("<li id=\"lm"+menuCode	+"\">");
		sb.append("<a style=\"cursor:pointer;\" class=\"dropdown-toggle\">");
		Menu menu = this.getMenu(menuCode);
		
		String menuIcon = null;
		String menuName = menu.getMenuName();
		String disPlayFlag = menu.getIconDisplay();
		
		//图片格式
		if (disPlayFlag.equals("0")){
			menuIcon = menu.getMenuIcon();
			
			//默认图标，需要继续处理，暂时方案
			if (StringUtils.isEmpty(menuIcon)){
				sb.append("<img src='icon-desktop'></img>");
			}else{
				sb.append("<img src='"+menuIcon+"'></img>");
			}
			
		} else{
			menuIcon = menu.getMenuClass();
			//默认样式
			if (StringUtils.isEmpty(menuIcon)){
				sb.append("<i class='icon-desktop'></i>");
			}else{
				sb.append("<i class='"+menuIcon+"'></i>");
			}
		}
		
		sb.append("<span class=\"menu-text\">"+menuName+"</span>");
		sb.append("<b class=\"arrow icon-angle-down\"></b>");
		sb.append("</a>");*/
		sb.append("<ul class=\"submenu\" id=\"c"+menuCode+"\" style=\"display: block;\">");
		
		for	(Menu subMenuNextLevel :subMenuList){
			
			/*sb.append("<li id=\"lm"+subMenu.getMENU_ID()+"\">");
			sb.append("<a style=\"cursor:pointer;\" class=\"dropdown-toggle\">");
			if(subMenu.getMENU_ICON() == null){
				sb.append("<i class='icon-desktop'></i>");
			}else{
				sb.append("<i class='"+subMenu.getMENU_ICON()+"'></i>");
			}
					
			sb.append("<span>"+subMenu.getMENU_NAME()+"</span>");
			sb.append("<b class=\"arrow icon-angle-down\"></b>");
			sb.append("</a>");
			*/
			if (!this.hasSubMenus(user, subMenuNextLevel.getMenuCode())) {//没有下级菜单
				if (subMenuNextLevel.getMenuUrl() != null && !"".equals(subMenuNextLevel.getMenuUrl().trim())){
					sb.append("<li class=\"\" id=\"z" + subMenuNextLevel.getMenuCode()+"\">");

					sb.append("<a class=\"func\" title=\""+subMenuNextLevel.getMenuName()+"\" dateLink=\""+subMenuNextLevel.getMenuUrl()+"\" style=\"cursor:pointer;padding:8px 0 8px 35px;font-size:13px;margin-left:0px;\" target=\"mainFrame\"  onclick=\"siMenu('z"+subMenuNextLevel.getMenuCode()+"','lm"+subMenuNextLevel.getMenuCode()+"','"+this.getMenuTabName(subMenuNextLevel)+"','"+subMenuNextLevel.getMenuUrl()+"')\">" +
							"<i class=\"\" style=\"font-size:14px;\"></i>"+subMenuNextLevel.getMenuName()+"</a></li>");
				} else{
					sb.append("<li><a href=\"javascript:void(0);\">");
					sb.append("<i class=\"icon-double-angle-right\" style=\"font-size:14px;\"></i>");
					sb.append(subMenuNextLevel.getMenuName());
					sb.append("</a></li>");
				}
			} else{
				sb.append(getChildMenuJson(user, subMenuNextLevel.getMenuCode()));
			}
		}
		sb.append("</ul>");
		//sb.append("</li>");
		
		return sb.toString();
	}
	
	
	/**
	 * 获取用户权限对应MenuCode的菜单json，用菜单加载；非树加载
	 * 
	 * @param user
	 * @return
	 */
	public String getChildMenuJson(User user, String menuCode) throws BizAppException {
		List<Menu> subMenuList = this.listSubMenuByParentCode(user, menuCode);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<li class=\"second_nav\" id=\"lm"+menuCode+"\">");
		sb.append("<a style=\"cursor:pointer;padding:7px 0 9px 27px;\" class=\"dropdown-toggle second_child\">");
		Menu menu = this.getMenu(menuCode);
		
		String menuIcon = null;
		String menuName = menu.getMenuName();
		String disPlayFlag = menu.getIconDisplay();
		
		//图片格式
		/*if (disPlayFlag.equals("0")){
			menuIcon = menu.getMenuIcon();
			
			//默认图标，需要继续处理，暂时方案
			if (StringUtils.isEmpty(menuIcon)){
				sb.append("<img src='icon-desktop'></img>");
			}else{
				sb.append("<img src='"+menuIcon+"'></img>");
			}
			
		} else{
			menuIcon = menu.getMenuClass();
			//默认样式
			if (StringUtils.isEmpty(menuIcon)){
				sb.append("<i class='icon-desktop'></i>");
			}else{
				sb.append("<i class='"+menuIcon+"'></i>");
			}
		}*/
		sb.append("<span style=\"background:url(weblib/assets/images/gallery/arrow_2.png) no-repeat;display: inline-block;width: 5px;height: 9px;margin-right: 3px;\"></span>");
		sb.append("<span title=\""+menuName+"\" style=\"\" class=\"menu-text\">"+menuName+"</span>");
		/*sb.append("<b class=\"arrow icon-angle-down\" style=\"margin-right: 10px;\"></b>");*/
		sb.append("</a>");
		sb.append("<ul class=\"submenu\" style=\"background:#d2edff;display:none;\">");
		
		for	(Menu subMenuNextLevel :subMenuList){
			
			/*sb.append("<li id=\"lm"+subMenu.getMENU_ID()+"\">");
			sb.append("<a style=\"cursor:pointer;\" class=\"dropdown-toggle\">");
			if(subMenu.getMENU_ICON() == null){
				sb.append("<i class='icon-desktop'></i>");
			}else{
				sb.append("<i class='"+subMenu.getMENU_ICON()+"'></i>");
			}
					
			sb.append("<span>"+subMenu.getMENU_NAME()+"</span>");
			sb.append("<b class=\"arrow icon-angle-down\"></b>");
			sb.append("</a>");
			*/
			if (!this.hasSubMenus(user, subMenuNextLevel.getMenuCode())) {//没有下级菜单
				if (subMenuNextLevel.getMenuUrl() != null && !"".equals(subMenuNextLevel.getMenuUrl().trim())){
					sb.append("<li id=\"z" + subMenuNextLevel.getMenuCode()+"\" style=\"border:1px dashed #ddd;line-height:30px;\">");
					sb.append("<a class=\"func\" title=\""+subMenuNextLevel.getMenuName()+"\" dateLink=\""+subMenuNextLevel.getMenuUrl()+"\" name=\""+this.getMenuTabName(subMenuNextLevel)+"\" style=\"cursor:pointer;padding:8px 0 8px 35px;font-size:13px;margin-left:0px;color:rgb(88,88,88)\"  onclick=\"siMenu(this,'z"+subMenuNextLevel.getMenuCode()+"','lm"+subMenuNextLevel.getMenuCode()+"','"+this.getMenuTabName(subMenuNextLevel)+"','"+subMenuNextLevel.getMenuUrl()+"')\">" +
							/*"<i class=\"\" style=\"font-size:14px;width: 8px;\"></i>"+*/subMenuNextLevel.getMenuName()+"</a></li>");
				} else{
					sb.append("<li><a href=\"javascript:void(0);\">");
					sb.append("<i class=\"icon-double-angle-right\" style=\"font-size:14px;\"></i>");
					sb.append(subMenuNextLevel.getMenuName());
					sb.append("</a></li>");
				}
			} else{
				sb.append(getChildMenuJson(user, subMenuNextLevel.getMenuCode()));
			}
		}
		sb.append("</ul>");
		//sb.append("</li>");
		
		return sb.toString();
	}
	
	/**
	 * 获取用户菜单列表
	 * 
	 * @param user
	 * @return
	 */
	public List<Menu> listSubMenuByParentCode(User user, String menuCode) throws BizAppException {
		
//		StringBuilder sql = new StringBuilder("select distinct f.* from trole_user ru, trole_menu rf, tmenu f  ")
//        	.append("where ru.role_code = rf.role_code and rf.menu_code=f.menu_code and ru.user_id=? and f.parent_menu_code = ? order by f.order_no asc");
		
		
		List<Menu> menuList = new ArrayList<Menu>();
		try {
			List<RoleUser> ruList = this.getUserRoleList(user);
			List<Menu> mList = new ArrayList<Menu>();
			for(RoleUser ru : ruList){
				List<Menu> list = MenuCache.getInstance().getRoleMenuValue(ru.getRoleCode());
				if(list!=null&&!list.isEmpty()){
					mList.addAll(list);
				}
			}
			for(Menu menu : mList){
				if(menu.getParentMenuCode().equals(menuCode)){
					menuList.add(menu);
				}
			}
			//去重
			HashSet<Menu> hs = new HashSet<Menu>(menuList);
			menuList.clear();
			menuList.addAll(hs);
			//排序
			Collections.sort(menuList, new NumberComparator());
//			if(menuList.isEmpty()){
//				menuList = dbsession.getObjectList(sql.toString(), Menu.class, user.getUserId(), menuCode);
//			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库查询异常");
		} 
		
		return menuList;
	}
	
	public boolean hasSubMenus(User user, String menuCode) throws BizAppException {
		
		List<Menu> subMenuNextLevelList = this.listSubMenuByParentCode(user, menuCode);
		if	(subMenuNextLevelList.size() >0){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 获取菜单信息
	 * 
	 * @param menuCode 菜单编码

	 */
	public  Menu getMenu(String menuCode)throws BizAppException{
		try {
			return dao.getMenu(menuCode);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	
	/**
	 * 功能描述：添加菜单
	 * 
	 * @param menu
	 * @throws BizAppException 
	 */
	public void addMenu(Menu menu) throws BizAppException{
		try {
			dao.addMenu(menu);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 功能描述：修改菜单
	 * @param menu
	 * @throws BizAppException 
	 */
	public void modifyMenu(Menu menu) throws BizAppException{
		try {
			dao.modifyMenu(menu);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 功能描述：删除菜单
	 * @param menuCodes
	 */
	public void deleteMenu(String menuCodes)throws BizAppException{
		
			String code[] = menuCodes.split(",");
			for (String string : code) {
				try {
					if(dao.getMenuCountByParentMenuCode(string)!=0){
						throw new BizAppException(ISysErrorNo.ERR_DBERR,"该菜单还有下级菜单，不能删除");
					}
					if(new RoleMenuDao().checkRoleInfo(string)!=0){
						throw new BizAppException(ISysErrorNo.ERR_DBERR,"该菜单下存在角色信息，不能删除");
					}
					dao.deleteMenu(string);
				} catch (SQLException e) {
					throw new BizAppException(ISysErrorNo.ERR_DBERR,"删除菜单失败");
				}
			}
		
	}

	/**
	 * 功能描述：检查菜单是否存在
	 * @param menuCode
	 * @return
	 */
	public int checkExists(String menuCode)throws BizAppException{
		try {
			return dao.checkExists(menuCode);
		} catch (SQLException e) {
			 throw new BizAppException(ISysErrorNo.ERR_DBERR,"检查菜单是否存在失败");
		}
	}
	
	/**
	 * 功能描述：得到菜单列表根据条件
	 * @param menuCode
	 * @return
	 */
	public List<Menu> getMenuList(Menu menu,Page page) throws BizAppException{
		try {
			return dao.getMenuList(menu,page);
		} catch (SQLException e) {
			 throw new BizAppException(ISysErrorNo.ERR_DBERR,"根据条件得到菜单列表失败");
		}
	}
	
	/**
	 * 功能描述：根据父类索引得到最大的子类树索引
	 * @param pTreeIdx
	 * @return
	 * @throws BizAppException
	 */
	public String getMaxTreeIdxForParentIdx(String pTreeIdx) throws BizAppException{
		try {
			return dao.getMaxTreeIdxForParentIdx(pTreeIdx);
		} catch (SQLException e) {
			 throw new BizAppException(ISysErrorNo.ERR_DBERR,"根据父类索引得到最大的子类树索引失败");
		}
	}

	@Override
	public List<Menu> getMenuList(String rolecodes, String likestr)
			throws BizAppException {
		List<Menu> listmenu = new ArrayList<Menu>();
		try {
			listmenu=dao.getMenuCountByParentMenuCode(rolecodes,likestr);
			for(int i=0;i<listmenu.size();i++){
				String name=getMenuTabName(listmenu.get(i));
					listmenu.get(i).setMenuName(name);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listmenu;
	}
	
	public List<Menu> getMenuListByParentMenuCode(String parent_menu_code) throws BizAppException{
		try {
			return dao.getMenuListByParentMenuCode(parent_menu_code);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"根据父类菜单code获取所有子类菜单失败");
		}
	}
	
}
