/********************************************
 * 文件名称: UserContextMenuService.java
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
package com.herongtech.console.service.userContextMenu;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.UserContextMenu;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.dao.UserContextMenuDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IUserContextMenuService;
import com.herongtech.console.service.interfaces.IMenuService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 快捷菜单信息表方法
 *
 */
public class UserContextMenuService implements IUserContextMenuService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/***
	 * 新增快捷菜单	
	 * @param contextMenu
	 * @throws BizAppException
	 */
	public void addUserContextMenu(UserContextMenu userContextMenu) throws BizAppException {
		UserContextMenuDao dao=new UserContextMenuDao();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		IMenuService menu=ServiceFactory.getMenuService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		try {
			String menuCode=userContextMenu.getMenuCode().substring(1);
			userContextMenu.setMenuUrl(menu.getMenu(menuCode).getMenuUrl());
			userContextMenu.setMenuName(userContextMenu.getMenuName());
			userContextMenu.setId(sequenceService.getConMenu_ID());
			userContextMenu.setUserId(user.getUserId());
			userContextMenu.setCreateTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
			userContextMenu.setMenuCode(menuCode);
			if (dao.addUserContextMenu(userContextMenu) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加SignProd失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/***
	 * 查询特定用户下的所有快捷菜单
	 * @param userId
	 * @return
	 * @throws BizAppException
	 */
	public List<UserContextMenu> getUserContextMenuInfo(String userId) throws BizAppException{
		UserContextMenuDao dao=new UserContextMenuDao();
		try {
			return dao.getUserContextMenuByUserId(userId);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/***
	 * 根据条件删除快捷菜单
	 * @param userId
	 * @param menuCode
	 * @return
	 * @throws BizAppException
	 */
	public int delMenuInfo(String userId,String menuCode) throws BizAppException{
		UserContextMenuDao dao=new UserContextMenuDao();
		try {
			return dao.delUserContextMenu(userId,menuCode);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "删除数据库数据失败");
		}
	}
	
}
