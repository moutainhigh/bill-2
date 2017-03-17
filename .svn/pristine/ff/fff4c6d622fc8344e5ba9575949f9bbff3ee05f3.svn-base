/********************************************
 * 文件名称: AuthInterceptor.java
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

package com.herongtech.console.core.interceptors;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.herongtech.console.core.system.manager.Client;
import com.herongtech.console.core.system.manager.ClientManager;
import com.herongtech.console.core.util.ContextHolderUtils;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.User;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.log.CommonLog;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 权限拦截器
 * 
 * @author  张代浩
 * 
 */
public class AuthInterceptor implements HandlerInterceptor {
	
	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";

	
	private List<String> excludeUrls;
	private static List<Menu> menuList;


	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 在controller后拦截, 抛出异常后仍然会执行这个方法
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object object, Exception exception) {
		//关闭数据库连接
		try {
			IDB session = DBFactory.getDB();
			if (!session.transactionIsClosed()) {
				session.rollback();
			}
		}catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("异常", e);;
		}finally{
        	DBFactory.clear();
    	}
		
		//日志输出
		CommonLog.getCommonLogCache().endTransaction();
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object, ModelAndView modelAndView) {

	}

	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
		
		CommonLog.getCommonLogCache().infoMessage("请求信息URL [" + requestPath + "]");
		
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		if(client == null){ 
			client = ClientManager.getInstance().getClient(
					request.getParameter("sessionId"));
		}
		
		String uri = request.getRequestURI().substring(request.getContextPath().length() + 1);
		String realRequestPath = null;
		if(uri.endsWith(".do")||uri.endsWith(".action")){
			realRequestPath = requestPath;
		}else {
			realRequestPath=uri;
		}
		
		IDB dbsession = null;
		List<Menu>  lists = null;
    	try{
    		dbsession = DBFactory.getDB(); //获取数据库连接
    		lists = dbsession.getObjectList("select * from tmenu where menu_url=? ", Menu.class, realRequestPath);
		
    	} catch(Exception e){
    		e.printStackTrace();			
		}
    	
    	Menu menu = null;
    	if (lists.size() >0 ){
    		menu = lists.get(0);
		}
    	//是否需要登录校验,无需校验直接返回
		if (menu == null || menu.getLogonFlag().equals("0")){
			return true;
		}
		
		
		if (client != null && client.getUser() != null ) {
			if((!hasMenuAuth(request))){
				response.sendRedirect("loginController.do?noAuth");
				return false;
			} 
			return true;
		} else {
			forward(request, response);
			return false;
		}

	}
	
	/**
	 * 判断用户是否有菜单访问权限
	 * @param request
	 * @return
	 */
	private boolean hasMenuAuth(HttpServletRequest request){
		String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
		//是否是功能表中管理的url
		boolean bMgrUrl = false;
		if (menuList == null) {
			String sql = "select * from tmenu where menu_type = '0' ";
			IDB dbsession = null;
			
			try {
				dbsession = DBFactory.getDB(); // 获取数据库连接
				menuList = dbsession.getObjectList(sql, Menu.class);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (Menu function : menuList) {
			if (function.getMenuUrl() != null && function.getMenuUrl().startsWith(requestPath)) {
				bMgrUrl = true;
				break;
			}
		}
		if (!bMgrUrl) {
			return true;
		}
		
		if(!bMgrUrl && requestPath.indexOf("loginController.do") !=-1 ){
			return true;
		} 
		User currLoginUser = ClientManager.getInstance().getClient(
				ContextHolderUtils.getSession().getId()).getUser();
        String userid = currLoginUser.getUserId();
        
		String sql = "SELECT count(*) FROM tmenu f, trole_menu  rf, trole_user ru " +
					" WHERE f.menu_code=rf.menu_code AND rf.role_code=ru.role_code AND " +
					"ru.user_id='" + userid + "' AND f.menu_url like '" + requestPath + "%'";
		
		IDB dbsession = null;
		int count = 0;
		try {
			dbsession = DBFactory.getDB(); // 获取数据库连接
			
			count = dbsession.account(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		if (count == 0){
			return false;
        } else{
			return true;
		}
	}
	/**
	 * 转发
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "method=forword")
	public ModelAndView forword(HttpServletRequest request) {
		return new ModelAndView(new RedirectView("loginController.do?login"));
	}

	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("webpage/timeout.jsp").forward(request, response);
	}

}
