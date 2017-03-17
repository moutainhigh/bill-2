package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.MenuTreeNode;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IRoleMenuService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：DictionariesController
 * 创建人：FH
 * 创建时间：2014年9月2日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/roleMenuController")
public class RoleMenuController extends BaseController{
public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	//取功能授权处理类
	private IRoleMenuService roleMenuService = ServiceFactory.getRoleMenuService();
	/**
	 * 角色授权
	 * @throws BizAppException 
	 * @throws SQLException 
	 */
	@RequestMapping(params="method=saveRoleMenu")
	@ResponseBody
	public String saveRoleMenu(String roleCode,String menus) throws BizAppException, SQLException{
		
		try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		session.beginTransaction();
    		//判断该角色是否有效
    		if("0".equals(ServiceFactory.getRoleService().getRole(roleCode).getRoleStatus())){
    			return "该角色已无效";
    		}else{
    			//功能授权
        		roleMenuService.saveRoleMenu(roleCode,menus);
        		session.endTransaction();
        		return "授权成功!";
    		}
			
		} catch (BizAppException e){
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [验证角色编码失败]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
	}
	
	/**
	 * 菜单树形结构json
	 */
	@RequestMapping(params = "method=jsonList")
	@ResponseBody
	public String jsonList(Menu menu,String roleCode) throws Exception {
		List<MenuTreeNode> resultList = new ArrayList<MenuTreeNode>();
		try {
			resultList = roleMenuService.getMenuTreeNodeList(menu,roleCode);
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage(
					"数据库错误   [获取菜单列表失败]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询菜单列表失败");
		}
		return getJsonString(resultList);
	}
	
	private String getJsonString(List<MenuTreeNode> list){
		for(int i = 0 ; i < list.size() ; i ++ ){
			MenuTreeNode node = (MenuTreeNode)list.get(i);
			node.setpId(node.getParent());
			if(StringUtils.equals("1",node.getOpen())){
				node.setOpen("true");
			}else{
				node.setOpen("false");
			}
		}
		return JSONArray.fromObject(list).toString();
	}
}
