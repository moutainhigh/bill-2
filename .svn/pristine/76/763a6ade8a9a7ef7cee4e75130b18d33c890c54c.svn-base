/********************************************
 * 文件名称: OnlineListener.java
 * 系统名称: 基础技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 多语言缓存类
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 上午08:39:00
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期     修改人员        修改说明
 * 20160615-01   yanjl  创建  
 *********************************************/

package com.herongtech.console.core.system.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.herongtech.console.core.system.manager.ClientManager;
import com.herongtech.log.CommonLog;


/**
 * 监听在线用户上线下线  add by duanql 2013-06-07
 */
public class OnlineListener implements ServletContextListener,HttpSessionListener {
	
   	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";

	
	private static ApplicationContext ctx = null;
	
	public OnlineListener() {
	}

	
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		CommonLog.getCommonLogCache().infoMessage("创建session , #sessionID="+httpSessionEvent.getSession().getId());
	}

	
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		CommonLog.getCommonLogCache().infoMessage("关闭session , #sessionID="+httpSessionEvent.getSession().getId());
		try {
			ClientManager.getInstance().removeClient(httpSessionEvent.getSession().getId());
		} catch (Exception e) {
			
		}
	}

	/**
	 * 服务器初始化
	 */
	
	public void contextInitialized(ServletContextEvent evt) {
		ctx = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
	}

	public static ApplicationContext getCtx() {
		return ctx;
	}
	
	
	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
		
	}

}
