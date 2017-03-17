/********************************************
 * 文件名称: UserContextMenuController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 2016-12-15 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.UserContextMenu;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IUserContextMenuService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：UserContextMenuController
 * 创建人：fqq
 * 创建时间：2016年12月15日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/UserContextMenuController")
public class UserContextMenuController extends BaseController{
public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	@ResponseBody
	public AjaxJson list()throws BizAppException{
		AjaxJson  ajax = new AjaxJson();
		IUserContextMenuService userContextMenuService = ServiceFactory.getUserContextMenuService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		try {
			ajax.setObj(userContextMenuService.getUserContextMenuInfo(user.getUserId()));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		return ajax;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params="method=save")
	@ResponseBody
	public AjaxJson save(UserContextMenu userContextMenu) throws BizAppException {
		AjaxJson retJson = new AjaxJson();
		IUserContextMenuService userContextMenuService = ServiceFactory.getUserContextMenuService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			String menuName=java.net.URLDecoder.decode(userContextMenu.getMenuName(), "UTF-8");
			userContextMenu.setMenuName(menuName);
			userContextMenuService.addUserContextMenu(userContextMenu);  //插入
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			retJson.setSuccess(false);
			retJson.setMsg("添加快捷菜单失败");
			e.getMessage();
		}
		return retJson;
	}
	
	@RequestMapping(params="method=del")
	@ResponseBody
	public AjaxJson del(String menuCode) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IUserContextMenuService userContextMenuService = ServiceFactory.getUserContextMenuService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		String menuCode1=menuCode.substring(1);
		try{
			session.beginTransaction();
			userContextMenuService.delMenuInfo(user.getUserId(), menuCode1);
			session.endTransaction();
		} catch (Exception e){
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库错误");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		
		return retJson;
	}
	
	
	
	
}
