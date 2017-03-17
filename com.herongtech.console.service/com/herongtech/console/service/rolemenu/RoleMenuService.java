/********************************************
 * 文件名称: RoleMenuService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-07-14 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.rolemenu;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.MenuTreeNode;
import com.herongtech.console.domain.bean.RoleMenu;
import com.herongtech.console.domain.dao.RoleMenuDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IRoleMenuService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 角色
 */
public class RoleMenuService implements IRoleMenuService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private RoleMenuDao dao=new RoleMenuDao();
	/**
	 * 功能授权
	 * @param roleCode
	 * @param menus
	 */
	public void saveRoleMenu(String roleCode, String menus) throws BizAppException{
		try {
			//清空角色权限从新复权
			clearMenuListByRoleCode(roleCode);
			if(StringUtils.isNotEmpty(menus)){
				String[] menuCodes = menus.split(",");
				for( String menuCode : menuCodes ){
					RoleMenu rMenu = new RoleMenu();
					//rMenu.setId(UUID.randomUUID().toString());
					rMenu.setRoleCode(roleCode);
					rMenu.setMenuCode(menuCode);
					dao.addRoleMenu(rMenu);
				}
			}
		} catch (SQLException e) {
			 throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 删除角色的所有菜单
	 * @param roleCode
	 * @return
	 * @throws BizAppException
	 */
	public int clearMenuListByRoleCode(String roleCode) throws BizAppException{
		try {
			return dao.clearMenuListByRoleCode(roleCode);
		} catch (SQLException e) {
			 throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	public List<MenuTreeNode> getMenuTreeNodeList(Menu menu,String roleCode) throws BizAppException{
		try {
			return dao.getMenuTreeNodeList(menu,roleCode);
		} catch (SQLException e) {
			 throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
}
