/********************************************
 * 文件名称: LoginController.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 登录
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.system.manager.Client;
import com.herongtech.console.core.system.manager.ClientManager;
import com.herongtech.console.core.util.ContextHolderUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.IpConfigMac;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.RoleUser;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IMenuService;
import com.herongtech.console.service.interfaces.ISystemService;
import com.herongtech.console.service.interfaces.IUserService;
import com.herongtech.console.service.user.UserService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;


/**
 * 
 * 文件名称: bbsp_platform com.herongtech.console.web.syscontroller.LoginController.java
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: superCheng
 * 开发时间: 20162016-8-6 上午11:18:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 */
@Scope("prototype")
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	private String message = null;

	/**
	 * 进入tab标签
	 */
	@RequestMapping(params = "method=tab")
	public String tab() {
		return "system/admin/tab";
	}

	@RequestMapping(params = "method=checkUserPsw")
	@ResponseBody
	public AjaxJson checkUserPsw(HttpServletRequest request,@RequestParam("user_password") String user_password,@RequestParam("confirmNewPsw") String newPassword,@RequestParam("userId") String user_id)
			throws BizAppException, SQLException {
		AjaxJson retJson = new AjaxJson();
		IUserService userService = ServiceFactory.getUserService();
		User user =userService.getUserByUserId(user_id);
		System.out.println("------------------------>>>>."+user_password);
		boolean flag=userService.checkUserPsw(user,user_password);
		if(!flag){
			retJson.setSuccess(false);
			retJson.setMsg("用户密码输入错误，请重新输入！！！");
			return retJson;
		}else{
			modifyUser(user.getUserId(), newPassword);
		retJson.setSuccess(true);
		return retJson;}
	}

	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException 
	 */

	@RequestMapping(params = "method=checkuser")
	@ResponseBody
	public AjaxJson checkuser(User params, HttpServletRequest req) throws BizAppException{

		AjaxJson retJson = new AjaxJson();
//		Map<Object, Object> retMap = new HashMap<Object, Object>();
		// 取用户处理类
		IUserService userService = ServiceFactory.getUserService();

		// 取用户基本信息
		User user = null;
		try {
			user = userService.checkUserExits(params);
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("取用户信息出错");
			throw new BizAppException(e.getErrorNo(), "取用户信息出错");
		}

		if (user == null) {//根据用户名、密码查询不到用户
			retJson.setMsg("用户名或者密码错误");
			retJson.setSuccess(false);
			return retJson;
		}
		/*if ("0".equals(user.getUserStatus())) {
			//密码修改日期为0，表示此用户为新建用户或者是重置密码用户
			if ("0".equals(user.getPassModifyDate())||StringUtil.isBlank(user.getPassModifyDate())) {
				//return "system/admin/modifyUserPsw";
				retJson.setObj(1);
				System.out.println("用户是新增用户，需要重置密码！！");
				return retJson;
			}
			
			//IUserService userService= ServiceFactory.getUserService();
			int retValue=userService.checkPassModifyDate(user.getPassModifyDate());
			if(retValue>=30){
				//return "system/admin/modifyUserPsw";//超过30天期限，强制修改
				System.out.println("用户密码已过期，需要重置密码！！");
				retJson.setObj(1);
				return retJson;
			}else if(retValue>=27&&retValue<30){
				//大于27天且小于30天，距离密码修改还剩余3天，提示修改，正常登录
				//modelMap.put("alertInfo", "密码还剩"+(30-retValue)+"天过期，请及时修改！！！");
				System.out.println("用户密码即将过期，需要提醒！！");
				retJson.setObj(2);
				retJson.setMsg("您的密码还剩"+(30-retValue)+"天过期，请及时修改！！！");
				Map<String, Object> attrMap = new HashMap<String, Object>();
				retJson.setAttributes(attrMap);
				saveLoginSuccessInfo(req, user);
			}else{
				Map<String, Object> attrMap = new HashMap<String, Object>();
				retJson.setAttributes(attrMap);
				saveLoginSuccessInfo(req, user);
			}
		} else {
			if("1".equals(user.getUserStatus()))
			{
				retJson.setMsg("当前用户已注销！");
				retJson.setSuccess(false);
			}else{
				retJson.setMsg("当前用户已锁定,请联系管理员解锁！");
				retJson.setSuccess(false);
			}
		}*/
		//保存用户登陆信息
		try {
			this.saveLoginSuccessInfo(req, user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException("保存用户登录信息异常");
		}
		retJson.setSuccess(true);
		return retJson;
	}

	@RequestMapping(params = "method=tomodifyUserPsw")
	public ModelAndView tomodifyUserPsw(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("user_id") String user_id) throws BizAppException, SQLException {
		ModelAndView mv = new ModelAndView();
		UserService us= new UserService();
		System.out.println("进入用户密码修改");
		User user = us.getUserByUserId(user_id);
		mv.setViewName("system/admin/modifyUserPsw");
		mv.addObject("user", user);
		return mv;
	}
	
	
	
	/**
	 * 检查用户登陆失败次数，返回map，用于更新用户信息
	 * @param dateString
	 * @param failtimes
	 * @return
	 */
	public Map<Object, Object> checkFailTimes(String dateString, int failtimes) {

		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			long nowDate = Calendar.getInstance().getTime().getTime(); // Date.getTime() 获得毫秒型日期
			long specialDate = DateTimeUtil.parse(dateString, "yyyy-MM-dd HH:mm:ss").getTime();
			long betweenDate = (nowDate - specialDate) / (1000 * 60 * 60); // 计算间隔多少小时，则除以毫秒到小时的转换公式
			CommonLog.getCommonLogCache().infoMessage(betweenDate * 60 + "分钟");
			if (betweenDate < 3) {
				if (failtimes < 2) {
					map.put("failtimes", failtimes + 1);
					map.put("faildate", dateString);
					map.put("UserStatus", "0");
				} else {//3小时之内连续失败3次，锁定用户
					map.put("failtimes", failtimes + 1);
					map.put("faildate", dateString);
					map.put("UserStatus", "2");
				}
			} else {
				map.put("failtimes", 1);
				map.put("faildate", DateTimeUtil.getNowDateTimeString());
				map.put("UserStatus", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * 保存用户登录的信息，并将当前登录用户的组织机构赋值到用户实体中；
	 * 
	 * @param req
	 *            request
	 * @param user
	 *            当前登录用户
	 * @param orgId
	 *            组织主键
	 * @throws Exception
	 */
	private void saveLoginSuccessInfo(HttpServletRequest req, User user) throws Exception{
		HttpSession session = ContextHolderUtils.getSession();
		session.setAttribute(ResourceUtil.LOCAL_CLINET_USER, user);
		IUserService userService = ServiceFactory.getUserService();
		message = "用户登录成功" + user.getUserId() + "登陆成功!";
		String rolecodes = userService.getUserRoleByUserId(user.getUserId());
		user.setUserRoleCode(rolecodes);
		Client clientOld = ClientManager.getInstance().getClient(session.getId());
		
		UserLoginfo uLogInfo = this.getUserLoginfoByUser(user);
		if (clientOld == null || clientOld.getUser() == null
				|| user.getUserId().equals(clientOld.getUser().getUserId())) {
			Client client = new Client();
			//更新用户信息
			user.setLastLogonDate(DateUtil.getNowDate());
			user.setLastIpAddress(IpConfigMac.getIpAddr(req));
			user.setLastLogonTime(DateUtil.getNowTime());
			userService.addLoginSuccess(user);
			//保存用户登录信息到Client
			client.setIp(IpConfigMac.getIpAddr(req));
			client.setLogindatetime(new Date());
			client.setUserloginfor(uLogInfo);
			client.setUser(user);
			ClientManager.getInstance().addclient(session.getId(), client);
		} else {
			// 如果不一致，则注销session并通过session=req.getSession(true)初始化session
			ClientManager.getInstance().removeClient(session.getId());
			session.invalidate();
			session = req.getSession(true);// session初始化
			session.setAttribute(ResourceUtil.LOCAL_CLINET_USERLOGINFO, uLogInfo);
			session.setAttribute(ResourceUtil.LOCAL_CLINET_USER, user);
//			checkuser(user, req);//用于登录，要不然会显示登录页面，登录不上
		}
		// 取用户处理类
//		ISystemService systemService = ServiceFactory.getSystemService();
		// 添加登陆日志
		// systemService.addLog(message, IConstants.Log_Type_LOGIN,
		// IConstants.Log_Leavel_INFO);
	}
	/**
	 * 根据User信息获取UserLoginfo
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public UserLoginfo getUserLoginfoByUser(User user) throws Exception{
		UserLoginfo uLogInfo = new UserLoginfo();
		Branch branch = ServiceFactory.getBranchService().getBranch(user.getBranchNo());
		BeanUtils.copyProperties(uLogInfo, user);
		uLogInfo.setLastLoginTime(DateTimeUtil.get_YYYY_MM_DD_Date());
		uLogInfo.setSysId(1);
		uLogInfo.setBrchNo(branch.getBranchNo());
		uLogInfo.setBranchId(branch.getBranchId());
		uLogInfo.setBranchName(branch.getBranchName());
		uLogInfo.setBrchBankNo(branch.getPayBankNo());//登陆者行号
		uLogInfo.setElecAuth(branch.getElecAuth());
		uLogInfo.setBranchLevel(branch.getBranchLevel());
		
		uLogInfo.setUserNo(user.getUserId());
		uLogInfo.setPassword(user.getUserPwd());
		return uLogInfo;
	}
	
	//@RequestMapping(params = "method=modifyUser")
	public void modifyUser(String userId,String password) throws BizAppException{
		IUserService userSerivce=ServiceFactory.getUserService();
		userSerivce.modifyUser(userId,password);
		/*ModelAndView modelAndView = new ModelAndView(new RedirectView(
				"loginController?login"));
				return modelAndView;*/
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=login")
	public String login(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) throws BizAppException, ParseException {

		User user = ResourceUtil.getSessionUser();
		String roles = "";
		if (user != null) {
			
			List<RoleUser> rUsers = new ArrayList<RoleUser>();
			try {
				rUsers = ServiceFactory.getSystemService().getUserRoleList(user);
			} catch (BizAppException e) {
				e.printStackTrace();
			}
			for (RoleUser ru : rUsers) {
				String roleCode = ru.getRoleCode();
				roles += roleCode + ",";
			}
			if (roles.length() > 0) {
				roles = roles.substring(0, roles.length() - 1);
			}
			modelMap.put("roleName", roles);
			modelMap.put("userName", user.getUserName());

			List<Menu> menuList = new ArrayList<Menu>();
			try {
				menuList = ServiceFactory.getMenuService().getFirstLevelMenu(user);
//				menuList = menuService.getAllMenu(user);
				
			} catch (BizAppException e) {
				CommonLog.getCommonLogCache().errorMessage("取用户菜单信息出错");
				throw new BizAppException(e.getErrorNo(), "取用户菜单信息出错");
			}
			modelMap.put("menuList", menuList);
			modelMap.put("user", user);
			modelMap.put("currentDate", DateTimeUtil.getWorkdayString());
			return "system/admin/index";
		} else {
			return "system/admin/login";
		}

	}

	/**
	 * 退出系统
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();

		// 取用户处理类
		ISystemService systemService = ServiceFactory.getSystemService();

		// systemService.addLog("用户" + user.getUserName() + "已退出",
		// IConstants.Log_Type_EXIT, IConstants.Log_Leavel_INFO);
		ClientManager.getInstance().removeClient(session.getId());
		session.invalidate();

		ModelAndView modelAndView = new ModelAndView(new RedirectView(
				"loginController.do?method=login"));
		return modelAndView;
	}

	/**
	 * 无权限页面提示跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=noAuth")
	public ModelAndView noAuth(HttpServletRequest request) {
		return new ModelAndView("common/noAuth");
	}

	/**
	 * 进入首页标签
	 */
	@RequestMapping(params = "method=goMain")
	public ModelAndView goMain() {
		return new ModelAndView("system/admin/main");
	}
}