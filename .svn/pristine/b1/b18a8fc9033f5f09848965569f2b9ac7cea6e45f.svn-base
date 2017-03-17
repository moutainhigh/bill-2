/********************************************
 * 文件名称: Client.java
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

package com.herongtech.console.core.system.manager;

import java.util.List;
import java.util.Map;

import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.domain.bean.UserLoginfo;

/**
 * 在线用户对象
 * 
 * @author JueYue
 * @date 2013-9-28
 * @version 1.0
 */
public class Client implements java.io.Serializable {
	
   	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";

	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private UserLoginfo userloginfor;        //登陆人信息
	public UserLoginfo getUserloginfor() {
		return userloginfor;
	}

	public void setUserloginfor(UserLoginfo userloginfor) {
		this.userloginfor = userloginfor;
	}

	private Map<String, Menu> menus;
	private Map<Integer, List<Menu>> menuMap;
	public Map<Integer, List<Menu>> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<Integer, List<Menu>> menuMap) {
		this.menuMap = menuMap;
	}

	/**
	 * 用户IP
	 */
	private java.lang.String ip;
	/**
	 *登录时间
	 */
	private java.util.Date logindatetime;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Map<String, Menu> getMenus() {
		return menus;
	}

	public void setMenus(Map<String, Menu> menus) {
		this.menus = menus;
	}

	public java.lang.String getIp() {
		return ip;
	}

	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}

	public java.util.Date getLogindatetime() {
		return logindatetime;
	}

	public void setLogindatetime(java.util.Date logindatetime) {
		this.logindatetime = logindatetime;
	}


}
