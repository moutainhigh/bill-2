package com.herongtech.console.cache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.herongtech.log.CommonLog;

public class CacheServiceServlet extends HttpServlet {

	
	@Override
	public void init() throws ServletException {
		super.init();
		CommonLog.getCommonLogCache().infoMessage("加载缓存------开始");
		MenuCache.getInstance();//TODO 目前只加载菜单缓存，且为直接调用MenuCache.getInstance()方法，以后待优化，使用CacheManager
		CommonLog.getCommonLogCache().infoMessage("加载缓存------结束");
	}

}
