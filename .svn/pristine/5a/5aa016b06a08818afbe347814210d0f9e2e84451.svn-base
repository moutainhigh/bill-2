/********************************************
 * 文件名称: CacheManageController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.syscontroller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.cache.CacheRefresh;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.CacheItem;
import com.herongtech.console.domain.bean.Dept;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;
import com.sun.org.apache.bcel.internal.generic.LoadClass;

/** 
 * 类名称：DictionariesController
 * 创建人：FH
 * 创建时间：2014年9月2日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/cacheManageController")

public class CacheManageController extends BaseController{

public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=listCacheManage")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		
		HsSqlString dbSql = new HsSqlString("tcacheitem");
		
		List<CacheItem> resultList = new ArrayList<CacheItem>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), CacheItem.class, 0 , page.getShowCount(), dbSql.getParamList());
        	
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询缓存数据列表失败");
		}
    	
		mv.setViewName("system/cacheitem/cacheitem_manage");
		mv.addObject("resultList", resultList);
		
		return mv;
		
	}
	
	@RequestMapping(params="method=refre")
	@ResponseBody
	public AjaxJson refre(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String cachei_ids = request.getParameter("cachei_ids");
		String[] id=cachei_ids.split(",");
		for(int i=0;i<id.length;i++){
			 String sql = "select * from tcacheitem where cache_code = ?";
				
				CacheItem cachei=new CacheItem();
		    	try{
		    		
		    		IDB session = DBFactory.getDB(); // 获取数据库连接
		    		cachei = session.getObject(sql, CacheItem.class, id[i]);
		    		/*ClassLoader cl=CacheManageController.class.getClassLoader();
		    		Object o= cl.loadClass(cachei.getCacheClass());*/
		        	Class<?> clz=Class.forName(cachei.getCacheClass());
		        	Object o=clz.newInstance();
		        	Method m=clz.getMethod(cachei.getCacheMethod());
		        	if(o!=null&&m!=null){
		        		m.invoke(o);
		        	}
				} catch (SQLException e){
					//e.printStackTrace();	
					CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
				} catch (ClassNotFoundException e) {
					CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
				} catch (InstantiationException e) {
					CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
				} catch (IllegalAccessException e) {
					CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
				} catch (SecurityException e) {
					CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
				} catch (NoSuchMethodException e) {
					CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
				} catch (IllegalArgumentException e) {
					CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
				} catch (InvocationTargetException e) {
					CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
				}
		    	
		}
		return retJson;
		
	}
}
