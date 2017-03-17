/********************************************
 * 文件名称: IUserContextMenuService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 2016-12-15 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import java.util.List;

import com.herongtech.console.domain.bean.UserContextMenu;
import com.herongtech.exception.impl.BizAppException;

public interface IUserContextMenuService {

	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/***
	 * 新增快捷菜单	
	 * @param UserContextMenu
	 * @throws BizAppException
	 */
	public void addUserContextMenu(UserContextMenu contextMenu) throws BizAppException;
	/***
	 * 查询特定用户下的所有快捷菜单
	 * @param userId
	 * @return
	 * @throws BizAppException
	 */
	public List<UserContextMenu> getUserContextMenuInfo(String userId) throws BizAppException;
	/***
	 * 根据条件删除快捷菜单
	 * @param userId
	 * @param menuCode
	 * @return
	 * @throws BizAppException
	 */
	public int delMenuInfo(String userId,String menuCode) throws BizAppException;
	
}
