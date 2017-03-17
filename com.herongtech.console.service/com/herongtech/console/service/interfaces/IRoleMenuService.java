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

import java.util.List;

import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.MenuTreeNode;
import com.herongtech.exception.impl.BizAppException;


public interface IRoleMenuService {
	
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 功能授权
	 * @param roleCode
	 * @param menus
	 */
	public void saveRoleMenu(String roleCode, String menus) throws BizAppException;
	
	/**
	 * 菜单树列表
	 * @param roleCode
	 * @param menus
	 */
	public List<MenuTreeNode> getMenuTreeNodeList(Menu menu,String roleCode) throws BizAppException;
	
	/**
	 * 删除角色的所有菜单
	 * @param roleCode
	 * @return
	 * @throws BizAppException
	 */
	public int clearMenuListByRoleCode(String roleCode) throws BizAppException;
}
