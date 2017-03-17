package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.Role;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IRoleMenuService;
import com.herongtech.console.service.interfaces.IRoleService;
import com.herongtech.console.service.interfaces.IUserService;
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
@RequestMapping("/roleController")
public class RoleController extends BaseController{
public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	//取角色处理类
	private IRoleService roleService = ServiceFactory.getRoleService();
	//取功能授权处理类
	private IRoleMenuService roleMenuService = ServiceFactory.getRoleMenuService();
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(Page page, Role role)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		
		HsSqlString dbSql = new HsSqlString("TROLE");
		
		if (StringUtils.isNotBlank(role.getRoleCode())){
			dbSql.setWhere("role_code like '%" + role.getRoleCode().trim() + "%'");
		}
		
		if (StringUtils.isNotBlank(role.getRoleName())){
			dbSql.setWhere("role_name like '%" + role.getRoleName().trim() + "%'");
		}
		
		/*if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}*/
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<Role> resultList = new ArrayList<Role>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), Role.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}
		mv.setViewName("system/role/role_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		//mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(Role role,String isedit) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			User user = ResourceUtil.getSessionUser();
			role.setCreator(user.getUserId());
			if (isedit.equals("1")){   //编辑操作
				roleService.modifyRole(role);
			}else{
				roleService.addRole(role);  //插入
			}
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录trole失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(params="method=toAdd")
	public ModelAndView toAdd(Page page){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/role/role_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 判断编码是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(String roleCode) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
    	try{
    		int count = roleService.checkExists(roleCode);
    		if (count >= 1){
    			retJson.setSuccess(false);
    		}
		} catch (BizAppException e){
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [验证角色编码失败]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		return retJson;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(String roleCode) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		Role role = null;
    	try{
    		role = roleService.getRole(roleCode);
		} catch (Exception e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [查询角色失败]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/role/role_edit");
		mv.addObject("role", role);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping(params="method=del")
	@ResponseBody
	public String del(String roleCodes) throws BizAppException{
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try{
			session.beginTransaction();
			// 取用户处理类
			IUserService userService = ServiceFactory.getUserService();
			//删除角色
			String code[] = roleCodes.split(",");
			for (String string : code) {
				if(userService.getUsersOfThisRole(string) > 0){
					session.rollback();
					return "角色中存在用户，不可以删除！";
				}
				//删除角色菜单
				roleMenuService.clearMenuListByRoleCode(string);
				roleService.deleteRole(string);
			}
    		session.endTransaction();
		} catch (SQLException e){
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [删除角色失败]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		return "操作成功！";
	}
}
