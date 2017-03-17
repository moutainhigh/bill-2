package com.herongtech.console.core.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.cache.TokenCache;
import com.herongtech.console.core.tag.TokenHelper;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;

public class TokenInterceptor implements HandlerInterceptor {
	
	private static Logger LOG = LoggerFactory.getLogger(TokenInterceptor.class);
	private static List<String> actionUrls = new ArrayList<String>();
	private Object clock = new Object();
	
	static{
		actionUrls = TokenCache.getInstance().getActionUrlsList();
	}
	
	/**
	 * 拦截方法，验证token
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String url = request.getRequestURI();
		String method = request.getQueryString();
		String holeUrl = url+"?"+method;
		if(actionUrls.contains(holeUrl)){
			LOG.debugMessage("Intercepting invocation to check for valid transaction token.");
			return handleToken(request,response,handler);
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object object, Exception exception){
	
	}
	
	protected boolean handleToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		synchronized(clock){
			if(!TokenHelper.validToken(request)){
				LOG.debugMessage("未通过验证。。。");
				forward(request, response);
				return false;
			}
		}
		LOG.debugMessage("通过验证。。。");
		return true;
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("exceptionMessage", "请勿重复提交");
		request.setAttribute("exceptionStackTrace","请求已提交，请勿重复提交。");
		request.getRequestDispatcher("webpage/error.jsp").forward(request, response);
	}
}
