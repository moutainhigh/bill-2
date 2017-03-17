/********************************************
 * 文件名称: IMenuService.java
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
import java.util.Map;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.User;
import com.herongtech.exception.impl.BizAppException;

public interface IMenuService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 获取用户权限的Menumap
	 * 
	 * @param user
	 * @return
	 */
	public Map<Integer, List<Menu>> getLevelMenuMap(User user) throws BizAppException;
	
	/**
	 * 获取用户菜单列表
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Menu> getUserMenuMap(User user) throws BizAppException;
	
	/**
	 * 获取用户权限的菜单json返回
	 * 
	 * @param user
	 * @return
	 */
	public String getMenuJson(User user, String menuCode) throws BizAppException;
	
	/**
	 * 获取用户权限的第一级列表
	 * 
	 * @param user
	 * @return
	 */
	public List<Menu> getFirstLevelMenu(User user) throws BizAppException;
	
	/**
	 * 功能描述：添加菜单
	 * 
	 * @param menu
	 * @throws BizAppException 
	 */
	public void addMenu(Menu menu) throws BizAppException;
	
	/**
	 * 功能描述：修改菜单
	 * @param menu
	 * @throws BizAppException 
	 */
	public void modifyMenu(Menu menu) throws BizAppException;

	/**
	 * 功能描述：删除菜单
	 * @param menuCodes
	 */
	public void deleteMenu(String menuCodes)throws BizAppException;

	/**
	 * 功能描述：得到菜单
	 * @param menuCode
	 * @return
	 */
	public Menu getMenu(String menuCode)throws BizAppException;

	/**
	 * 功能描述：检查菜单是否存在
	 * @param menuCode
	 * @return
	 */
	public int checkExists(String menuCode)throws BizAppException;
	
	/**
	 * 功能描述：得到菜单列表根据条件
	 * @param menuCode
	 * @return
	 */
	public List<Menu> getMenuList(Menu menu,Page page) throws BizAppException;
	
	/**
	 * 功能描述：根据父类索引得到最大的子类树索引
	 * @param pTreeIdx
	 * @return
	 * @throws BizAppException
	 */
	public String getMaxTreeIdxForParentIdx(String pTreeIdx) throws BizAppException;
	
	
	/**
	 * 功能描述：根据角色和模糊查询条件查出所有对应的菜单集合
	 * @param likestr
	 * @param rolecodes
	 * @return
	 * @throws BizAppException
	 */
	public List<Menu> getMenuList(String rolecodes, String likestr) throws BizAppException;
	
	public List<Menu> getMenuListByParentMenuCode(String parent_menu_code) throws BizAppException;
	public List<Menu> getAllMenu(User user) throws BizAppException;
}
