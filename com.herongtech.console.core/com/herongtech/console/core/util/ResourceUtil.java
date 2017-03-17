package com.herongtech.console.core.util;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.herongtech.appconfig.SysConfigUtil;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.domain.bean.RoleMenu;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.core.system.manager.Client;
import com.herongtech.console.core.system.manager.ClientManager;

/**
 * 项目参数工具类
 * 
 */
public class ResourceUtil {
	
   	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	public static final String LOCAL_CLINET_USER = "LOCAL_CLINET_USER";
	
	public static final String LOCAL_CLINET_USERLOGINFO = "LOCAL_CLINET_USERLOGINFO";
	
	//private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

	/**
	 * 获取session定义名称
	 * 
	 * @return
	 */
/*	public static final String getSessionattachmenttitle(String sessionName) {
		return bundle.getString(sessionName);
	}*/
	
	
	public static final UserLoginfo getSessionLoginfo() {
		HttpSession session = ContextHolderUtils.getSession();
		if(ClientManager.getInstance().getClient(session.getId()) != null){
			return ClientManager.getInstance().getClient(session.getId()).getUserloginfor();
		}else{
			UserLoginfo u = (UserLoginfo) session.getAttribute(ResourceUtil.LOCAL_CLINET_USERLOGINFO);
			Client client = new Client();
	        client.setIp("");
	        client.setLogindatetime(new Date());
	        client.setUserloginfor(u);
	        ClientManager.getInstance().addclient(session.getId(), client);
		}
		return null;
	}
	
	public static final User getSessionUser() {
		HttpSession session = ContextHolderUtils.getSession();
		if(ClientManager.getInstance().getClient(session.getId()) != null){
			return ClientManager.getInstance().getClient(session.getId()).getUser();
		}else{
			User u = (User) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			Client client = new Client();
	        client.setIp("");
	        client.setLogindatetime(new Date());
	        client.setUser(u);
	        ClientManager.getInstance().addclient(session.getId(), client);
	        return u;
		}
//		return null;
	}
	
	public static final List<RoleMenu> getSessionRoleMenu(String roleId) {
		HttpSession session = ContextHolderUtils.getSession();
		if (session.getAttributeNames().hasMoreElements()) {
			List<RoleMenu> roleMenuList = (List<RoleMenu>)session.getAttribute(roleId);
			if (roleMenuList != null) {
				return roleMenuList;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	/**
	 * 获取配置文件参数
	 * 
	 * @param name
	 * @return
	 */
/*	public static final String getConfigByName(String name) {
		return bundle.getString(name);
	}*/

	
	
	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
		return resultPath;
	}

	/**
	 * 获取项目根目录
	 * 
	 * @return
	 */
	public static String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static String getParameter(String field) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		return request.getParameter(field);
	}
    
    /**
     * 获取用户session 中的变量
     * @param key
     * 			Session 中的值
     * @return
     */
	private static String getSessionData(String key) {
		//${myVar}%
		//得到${} 后面的值
		String moshi = "";
		if(key.indexOf("}")!=-1){
			 moshi = key.substring(key.indexOf("}")+1);
		}
		String returnValue = null;

		if (key.contains("#{")) {
			key = key.substring(2,key.indexOf("}"));
		}
		//从session中取得值
		if (!StringUtils.isEmpty(key)) {
			HttpSession session = ContextHolderUtils.getSession();
			returnValue = (String) session.getAttribute(key);
		}
		
		//结果加上${} 后面的值
		if(returnValue!=null){
			returnValue = returnValue + moshi;
		}
		
		return returnValue;
	}
	/**
	 * 获取虚拟柜员登陆信息
	 * @return
	 */
	public static final UserLoginfo getVirtualUserLoginfo(){
		UserLoginfo userLoginfo = new UserLoginfo();
		userLoginfo.setUserId(SysConfigUtil.getSysConfig().getValue("virtualUserId"));
		userLoginfo.setBrchNo(SysConfigUtil.getSysConfig().getValue("virtualUserBranchNo"));
		return userLoginfo;
	}
	/**
	 * 获取虚拟柜员信息
	 * @return
	 */
	public static final User getVirtualUser(){
		User user = new User();
		user.setUserId(SysConfigUtil.getSysConfig().getValue("virtualUserId"));
		user.setBranchNo(SysConfigUtil.getSysConfig().getValue("virtualUserBranchNo"));
		return user;
	}
	
	public static void main(String[] args) {
		//com.herongtech.console.core.util.LogUtil.info(getPorjectPath());
		//com.herongtech.console.core.util.LogUtil.info(getSysPath());
	}
}
