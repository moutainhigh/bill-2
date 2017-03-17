package com.herongtech.console.cache;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.console.domain.bean.Param;
import com.herongtech.console.domain.dao.ParamDao;
import com.herongtech.rc.domain.bean.DraftMapping;

/**参数管理缓存类*/
public class SysParamCache {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	private static SysParamCache paramcache = null;
	private Map<String,String> paranmcachemap = new HashMap<String,String>();
	
	private SysParamCache(){
		init();
	}
	/**初始化*/
	private void init(){
		ParamDao dao = new ParamDao();
		try {
			List<Param> paramlist = dao.getParamList();
			for (int i = 0; i < paramlist.size(); i++) {
				paranmcachemap.put(paramlist.get(i).getParamId(), paramlist.get(i).getParamValue());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    /**获得参数缓存对象*/
    public static SysParamCache getInstance(){
    	if(paramcache==null){
    		paramcache=new SysParamCache();
    	}
        return paramcache;
    }
    
    /**根据参数标识号获得参数值*/
    public String getSysParam(String paramCode){
        return paranmcachemap.get(paramCode);
    }
    /**给缓存中增加一条数据*/
    public void addparammap(String paramid,String paramvalue){
    	 paranmcachemap.put(paramid,paramvalue);
    }

    /**根据参数标识号更新缓存中的参数值*/
    public void updateSysParam(String paramCode,String paramValue){
    	
    	 paranmcachemap.put(paramCode,paramValue);
    }
    
    /**刷新缓存*/
	public void refresh() {
		try {
			w.lock();
			paranmcachemap.clear();
			init();
		} finally {
			w.unlock();
		}
	}
	
	/**清除缓存*/
	public static void clearmap(){
		if(paramcache!=null){
			paramcache.paranmcachemap.clear();	
		}
		paramcache=null;
	}
}
