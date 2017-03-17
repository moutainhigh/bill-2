/********************************************
 * 文件名称: UserController.java
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

package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.herongtech.commons.tools.DateUtil;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.BeanValidators;
import com.herongtech.console.core.util.ExportExcel;
import com.herongtech.console.core.util.ImportExcel;
import com.herongtech.console.core.util.PasswordUtil;
import com.herongtech.console.domain.bean.Role;
import com.herongtech.console.domain.bean.RoleUser;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IUserService;
import com.herongtech.console.service.user.UserService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 
 * 文件名称: bbsp_platform
 * com.herongtech.console.web.syscontrollerUserController.java 系统名称: 票据管理平台
 * 模块名称: 软件版权: 北京合融科技有限公司 功能说明: 系统版本: @version2.0.0.1 开发人员: superCheng
 * 开发时间:2016-7-28 上午8:59:23 审核人员: 相关文档: 修改记录: 修改日期 修改人员 修改说明
 */
@Scope("prototype")
@Controller
@RequestMapping("/userController")
public class UserController extends BaseController {

	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	public final String psw = "03b2e14a12b57b8c";

	/**
	 * 显示用户列表(用户组)
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=listUsers")
	public ModelAndView listUsers(Page page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		List<Object> param = new ArrayList<Object>();
		Map<String, String> mapField = new HashMap<String, String>();
		StringBuffer sql = new StringBuffer(
				"select a.*,b.dep_name,c.branch_name  from tuser a left join tdept b on a.dep_code=b.dep_code left join tbranch c on a.branch_no=c.branch_no  ");
		StringBuffer sql_count = new StringBuffer(
				"select count(*) from tuser a left join tdept b on a.dep_code=b.dep_code left join tbranch c on a.branch_no=c.branch_no  ");
		String userId = request.getParameter("userId");
		String userStatus = request.getParameter("userStatus");
		String order_by = request.getParameter("order_by");

		String currentPage = request.getParameter("page.currentPage");
		if (!StringUtils.isEmpty(currentPage)) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}

		String showCount = request.getParameter("page.showCount");
		if (!StringUtils.isEmpty(showCount)) {
			page.setShowCount(Integer.parseInt(showCount));
		}

		String totalResult = request.getParameter("page.totalResult");
		if (!StringUtils.isEmpty(totalResult)) {
			page.setTotalResult(Integer.parseInt(totalResult));
		}

		// 角色
		String userRole = request.getParameter("user_rolecode");
		if (!StringUtils.isEmpty(userRole)) {
			sql.append(" left join trole_user d on a.user_id=d.user_id where 1=1 and a.user_status !='1'");
			sql_count
					.append(" left join trole_user d on a.user_id=d.user_id where 1=1 and a.user_status !='1'");
			sql.append(" and d.role_code like '%" + userRole.trim() + "%'");
			sql_count.append(" and d.role_code like '%" + userRole.trim()
					+ "%'");
		} else {
			sql.append("  where 1=1 and a.user_status !='1'");
			sql_count.append(" where 1=1 and a.user_status !='1'");
		}

		// HsSqlString dbSql = new HsSqlString("tuser");
		if (!StringUtils.isEmpty(userId)) {
			sql.append(" and a.user_id like '%" + userId + "%'");
			sql_count.append(" and a.user_id like '%" + userId + "%'");
		}

		if (!StringUtils.isEmpty(userStatus)) {
			sql.append(" and a.user_status like '%" + userStatus + "%'");
			sql_count.append(" and a.user_status like '%" + userStatus + "%'");
		}
		// 所属机构
		String branchNo = request.getParameter("branchNo");
		if (!StringUtils.isEmpty(branchNo)) {
			sql.append(" and a.branch_no = '" + branchNo.trim() + "'");
			sql_count.append(" and a.branch_no = '" + branchNo.trim() + "'");
		}

		// 所属部门
		String depCode = request.getParameter("depCode");
		if (!StringUtils.isEmpty(depCode)) {
			sql.append(" and a.dep_code = '" + depCode.trim() + "'");
			sql_count.append(" and a.dep_code = '" + depCode.trim() + "'");
		}

		// 姓名
		String userName = request.getParameter("userName");
		if (!StringUtils.isEmpty(userName)) {
			sql.append(" and a.user_name like '%" + userName.trim() + "%'");
			sql_count.append(" and a.user_name like '%" + userName.trim()
					+ "%'");
		}

		int startIndex = page.getCurrentResult();
		List<User> userList = new ArrayList<User>();
		try {

			IDB session = DBFactory.getDB(); // 获取数据库连接
			userList = session.getObjectListByListForPage(sql.toString(),
					User.class, startIndex, page.getShowCount(), param);

			// 获得并返回本次查询的总条数
			int count = 0;
			count = session.account(sql_count.toString());
			page.setTotalResult(count);

		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage(
					"数据库错误   [" + sql.toString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}

		mapField.put("userId", userId);
		mapField.put("userName", userName);
		mapField.put("userRole", userRole);
		mapField.put("branchNo", branchNo);

		mapField.put("userStatus", userStatus);
		mapField.put("order_by", order_by);
		mv.setViewName("system/user/user_list");
		mv.addObject("userList", userList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		return mv;
	}

	@RequestMapping(params = "method=import", method = RequestMethod.POST)
	public ModelAndView importFile(HttpServletRequest request,
			HttpServletResponse response) {

		long a = System.currentTimeMillis();
		ModelAndView mv = new ModelAndView();
		List<User> errorList = new ArrayList<User>();// 保存错误的信息
		int countAll = 0;// 导入的总条数
		int successNum = 0;
		int failureNum = 0;
		User headRow = null;
		System.out.println("导入开始--------------->>>>" + a);
		IUserService userService = ServiceFactory.getUserService();
		MultipartHttpServletRequest mtRequest = (MultipartHttpServletRequest) request;// 多部分httpRquest对象
																						// 是HttpServletRequest类的一个子类接口
																						// 支持文件分段上传对象
		MultipartFile file = mtRequest.getFile("uploadFile"); // 直接获取文件对象
		String name = file.getOriginalFilename();
		System.out.println("文件名------〉>>>" + file.getSize());
		try {

			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			countAll = list.size();
			headRow = list.get(0);
			for (User user : list) {
				System.out.println(user.toString());
				try {
					if (userService.checkUserExitsById(user) > 0) {
						failureMsg.append("<br/>登录名 " + user.getUserId()
								+ " 已存在; ");
						errorList.add(user);
						failureNum++;
						System.out.println(failureMsg.toString());
					} else {
						ServiceFactory.getUserService().addUser(user);
						successNum++;
					}

				} catch (ConstraintViolationException ex) {
					failureMsg
							.append("<br/>登录名 " + user.getUserId() + " 导入失败：");
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>登录名 " + user.getUserName()
							+ " 导入失败：" + ex.getMessage());
				}
			}

		} catch (Exception e) {
		}
		long b = System.currentTimeMillis();
		mv.addObject("countAll", countAll);

		if (failureNum > 0) {
			System.out.println("共有" + countAll + "条信息" + "出错条数为" + failureNum
					+ "出错的信息-----〉〉" + errorList.toString());
			mv.addObject("failureNum", failureNum);
			mv.addObject("errorList", errorList);
			mv.addObject("headRow", headRow);
			mv.setViewName("uploadError");
		} else {
			mv.setViewName("uploadSuccess");
		}
		System.out.println("执行时间----------------〉〉〉" + (b - a));
		// return new ModelAndView("redirect:userController?listUsers");

		return mv;
	}

	/**
	 * 下载导入用户数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(params ="template")
	public ModelAndView importFileTemplate(HttpServletRequest request,HttpServletResponse response
			) {
		//RedirectAttributes redirectAttributes
		try {
			String fileName = "用户数据导入模板.xlsx";
			List<User> list = Lists.newArrayList();
			//list.add(UserUtils.getUser());
			new ExportExcel("用户数据", User.class, 2).setDataList(list)
					.write(request,response, fileName).dispose();
			return null;
		} catch (Exception e) {
			//addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return new ModelAndView("redirect:userController?listUsers");
	}

	@RequestMapping(params = "method=exportuser")
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response) {
		try {
            String fileName = "用户数据"+DateUtil.getDateTime("yyyyMMddHHmmss")+".xlsx";
            
            //Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
    		System.out.println("------------------->>>>"+user.getUserId());
    		System.out.println("-------------------->>>>>>>>"+request.getParameter("userId"));
			IDB session = DBFactory.getDB(); // 获取数据库连接
			StringBuffer sql = new StringBuffer(
					"select *  from tuser a where 1=1");

			if(!StringUtils.isEmpty(user.getUserId())){
				sql.append(" and a.user_id like'%"+user.getUserId()+"%'");
			}
			List<User> list=session.getObjectList(sql.toString(), User.class);
    		new ExportExcel("用户数据", User.class).setDataList(list).write(request,response, fileName).dispose();
            
    		return null;
		} catch (Exception e) {
			//addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:userController?listUsers";
    }
	
	/**
	 * 保存修改或新增的用户信息
	 */
	@RequestMapping(params = "method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, User user,
			@RequestParam("isedit") String isedit) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		// 取用户处理类
		IUserService userService = ServiceFactory.getUserService();
		try {
			if (isedit.equals("1")) { // 编辑操作
				userService.modifyUser(user);
			} else {
				userService.addUser(user); // 插入
			}
		} catch (Exception e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 去新增页面
	 */
	@RequestMapping(params = "method=toAdd")
	public ModelAndView toAdd(Page page) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("system/user/user_edit");
		mv.addObject("isedit", "0");
		return mv;
	}

	/**
	 * 去查看页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=viewUser")
	public ModelAndView viewUser(Page page, HttpServletRequest request,
			HttpServletResponse response, @RequestParam("user_id") String userId)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		System.out.println("user_id===============>>>>" + userId);
		// HsSqlString dbSql = new HsSqlString("tuser");
		StringBuffer sql = new StringBuffer(
				"select a.*,b.dep_name,c.branch_name  from tuser a left join tdept b on a.dep_code=b.dep_code left join tbranch c on a.branch_no=c.branch_no where 1=1");
		if (!StringUtils.isEmpty(userId)) {
			sql.append(" and user_id = ?");
		}
		User user = new User();
		System.out.println("sql==============>>>>>" + sql.toString());
		System.out.println("参数列表：" + userId.toString());
		try {

			IDB session = DBFactory.getDB(); // 获取数据库连接
			user = session.getObject(sql.toString(), User.class, userId);

			System.out.println("从页面获取到的用户信息为：" + user);

		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage(
					"数据库错误   [" + sql.toString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}

		mv.setViewName("system/user/user_view");
		mv.addObject("userView", user);
		return mv;
	}

	/**
	 * 判断用户是否存在
	 * 
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException {

		AjaxJson retJson = new AjaxJson();

		String user_id = request.getParameter("user_id");

		String sql = "select count(*) from tuser where user_id = ?";

		try {

			IDB session = DBFactory.getDB(); // 获取数据库连接
			int count = session.account(sql, user_id);

			if (count >= 1) {
				retJson.setSuccess(false);
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}

		return retJson;
	}

	/**
	 * 重置密码
	 * 
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=resetPwd")
	@ResponseBody
	public AjaxJson resetPwd(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException {

		AjaxJson retJson = new AjaxJson();

		String user_id = request.getParameter("user_id");
		System.out.println("前台传入的用户信息-------->>" + user_id);
		String params = StringUtils
				.fillStringInPart(StringUtils.split(user_id));
		System.out.println("处理后的用户信息-------------->>>>>" + params);
		String user_pwd = "password";

		user_pwd = PasswordUtil.encrypt(user_id, user_pwd,
				PasswordUtil.getStaticSalt());
		// 重置密码后，将密码修改日期置为0，以确保用户登录时跳转到强制修改密码页面
		String sql = "update tuser set user_pwd = '" + user_pwd
				+ "',pass_modify_date= '0' where user_id in (" + params + ")";

		try {

			IDB session = DBFactory.getDB(); // 获取数据库连接
			int count = session.execute(sql);

			if (count >= 1) {
				retJson.setSuccess(true);
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}

		return retJson;
	}

	/**
	 * 
	 * 文件名称: bbspNew com.herongtech.console.web.syscontrollerUserController.java
	 * 系统名称: 票据管理平台 模块名称: 解锁 软件版权: 北京合融科技有限公司 功能说明: 系统版本: @version2.0.0.1 开发人员:
	 * superCheng 开发时间: 2016-7-19 下午3:01:13 审核人员: 相关文档: 修改记录: 修改日期 修改人员 修改说明
	 */
	@RequestMapping(params = "method=unclockUser")
	@ResponseBody
	public AjaxJson unlockUser(String user_id) throws BizAppException {

		AjaxJson retJson = new AjaxJson();

		String params = StringUtils.fillStringInPart(StringUtils.split(user_id));

		String user_status = "0";//正常

		String sql = "update tuser set user_status = '" + user_status
				+ "', fail_times= 0 where user_id in (" + params + ")";

		try {

			IDB session = DBFactory.getDB(); // 获取数据库连接
			int count = session.execute(sql);

			if (count >= 1) {
				retJson.setSuccess(true);
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}

		return retJson;
	}

	/**
	 * 
	 * 文件名称: bbspNew com.herongtech.console.web.syscontrollerUserController.java
	 * 系统名称: 票据管理平台 模块名称: 加锁 软件版权: 北京合融科技有限公司 功能说明: 给用户加锁状态 系统版本:
	 * 
	 * @version2.0.0.1 开发人员: superCheng 开发时间: 2016-7-19 下午3:02:04 审核人员: 相关文档:
	 *                 修改记录: 修改日期 修改人员 修改说明
	 */
	@RequestMapping(params = "method=lockUser")
	@ResponseBody
	public AjaxJson lockUser(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException {

		AjaxJson retJson = new AjaxJson();

		String user_id = request.getParameter("user_id");

		String params = StringUtils
				.fillStringInPart(StringUtils.split(user_id));
		String sql = "update tuser set user_status = '2' where user_id in ("
				+ params + ")";

		try {

			IDB session = DBFactory.getDB(); // 获取数据库连接
			int count = session.execute(sql);

			if (count >= 1) {
				retJson.setSuccess(true);
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}

		return retJson;
	}

	/**
	 * 去编辑页面
	 * 
	 * @throws SQLException
	 */
	@RequestMapping(params = "method=toEdit")
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("user_id") String user_id) throws BizAppException,
			SQLException {
		ModelAndView mv = new ModelAndView();
		UserService us = new UserService();
		User user = us.getUserByUserId(user_id);
		mv.setViewName("system/user/user_edit");
		mv.addObject("sysparam", user);
		mv.addObject("isedit", "1");

		return mv;
	}

	/**
	 * 去分配角色页面
	 */
	@RequestMapping(params = "method=toDepoly")
	public ModelAndView toDepoly(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("user_id") String user_id) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		List<Role> all_role = new ArrayList<Role>();// 全部的角色
		List<Role> user_role = new ArrayList<Role>();// 用户角色
		System.out.println("传入的用户编号======》》" + user_id);
		UserService us = new UserService();

		String sql_roleCode = "select distinct role_code  from trole_user where user_id=? ";
		String sql = null, sql_user = null;
		try {

			sql = "select *  from trole a where role_status='1' and a.role_code not in ("
					+ sql_roleCode + ")";
			sql_user = "select *  from trole a where role_status='1' and a.role_code  in ("
					+ sql_roleCode + ")";
			System.out.println("=============获取全部角色（不包含当前用户）sql============"
					+ sql);
			System.out.println("=============获取当前用户角色sql============"
					+ sql_user);
			all_role = us.getRoleByUserId(user_id, sql);
			user_role = us.getRoleByUserId(user_id, sql_user);
			System.out.println("全部的角色" + all_role.toString());
			System.out.println("该用户" + user_id + "的角色" + user_role.toString());

		} catch (SQLException e) {
			// e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}

		mv.setViewName("system/user/user_deploy");
		mv.addObject("all_role", all_role);
		mv.addObject("user_role", user_role);
		mv.addObject("user_id", user_id);
		return mv;
	}

	/**
	 * 分配角色
	 */
	@RequestMapping(params = "method=deploy")
	public ModelAndView distribute(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException {
		System.out.println("=====================进入角色分配===============");
		ModelAndView mv = new ModelAndView();
		String[] userRole = null;
		String user_id = request.getParameter("user_id");
		String role_code = request.getParameter("role_code");
		if (!StringUtils.isBlank(role_code)) {
			role_code = role_code.substring(0, role_code.length() - 1);
			userRole = StringUtils.split(role_code);
		} else {
			userRole = null;
		}

		System.out.println("该用户本次分配的角色为---------->>>" + userRole);
		System.out.println(user_id
				+ "<<<<====user_id===============role_code====>>>" + role_code);
		UserService userservice = new UserService();
		RoleUser roleuser = new RoleUser();
		roleuser.setUserId(user_id);

		String sql_count = "select count(*) from trole_user where user_id = ?";

		try {

			IDB session = DBFactory.getDB(); // 获取数据库连接
			int count = session.account(sql_count, user_id);

			if (count >= 1) {
				userservice.delUserRole(user_id);
			}
			for (String user_role : userRole) {
				roleuser.setRoleCode(user_role);
				userservice.addUserRole(roleuser);
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage(
					"数据库错误   [" + sql_count + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		mv.addObject("msg", "success");
		mv.setViewName("save_result");

		return mv;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping(params = "method=del")
	@ResponseBody
	public AjaxJson del(HttpServletRequest request, HttpServletResponse response)
			throws BizAppException {

		AjaxJson retJson = new AjaxJson();

		String param_ids = request.getParameter("user_ids");

		String sqlInParts = StringUtils.fillStringInPart(StringUtils
				.split(param_ids));
		String sql2roleUser = "delete from trole_user where user_id in("
				+ sqlInParts + ")";

		String sql = "update tuser set user_status='1' where user_id in("
				+ sqlInParts + ")";
		IDB session = DBFactory.getDB(); // 获取数据库连接

		try {
			session.beginTransaction();
			session.execute(sql2roleUser);
			CommonLog.getCommonLogCache().infoMessage("取消用户与角色之间的关联成功---------------->>>>"+ sql2roleUser);
			CommonLog.getCommonLogCache().infoMessage("执行用户删除操作开始--------------------->>>>sql=" + sql
					+ ",参数离列表为：" + sqlInParts);
			session.execute(sql);
			retJson.setSuccess(true);
			session.endTransaction();
			CommonLog.getCommonLogCache().infoMessage("执行用户删除操作结束");
		} catch (SQLException e) {
			// e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}

		return retJson;
	}
}
