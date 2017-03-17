package com.herongtech.console.cache;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.rc.domain.bean.DraftMapping;
import com.herongtech.rc.domain.dao.DraftMappingDao;
/**DraftMapping缓存类*/
public class DraftMappingCache {

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	private static DraftMappingCache draftmappingcache = null;
	private Map<String,DraftMapping> draftmappingmap = new HashMap<String,DraftMapping>();
	private DraftMappingCache(){
		init();
	}
	
	/**DraftMapping缓存类初始化*/
	private void init(){
		DraftMappingDao dao = new DraftMappingDao();
		try {
			List<DraftMapping> draftmappinglist = dao.getAllDraftMapping();
			for (int i = 0; i < draftmappinglist.size(); i++) {
				DraftMapping draftmapping = draftmappinglist.get(i);
				String key = draftmapping.getMethodId()+draftmapping.getParam();
				draftmappingmap.put(key, draftmapping);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**获得DraftMapping缓存类对象*/
	public static DraftMappingCache getDraftMappingCache(){
		if(null==draftmappingcache){
			draftmappingcache = new DraftMappingCache();
		}
		return draftmappingcache;
	}
	
	/**获得DraftMapping对象*/
	public DraftMapping getDraftMappingmapvalue(String key){
		try {
			r.lock();
			DraftMapping value = draftmappingmap.get(key);
			if(null==value){
				return new DraftMapping();
			}else{
				return value;			
			}
		}finally{
			r.unlock();			
		}
	}
	
	/**刷新缓存*/
	public void refresh() {
		try {
			w.lock();
			draftmappingmap.clear();
			init();
		} finally {
			w.unlock();
		}
	}
	
	/**清除缓存*/
	public static void clearmap(){
		if(draftmappingcache!=null){
			draftmappingcache.draftmappingmap.clear();	
		}
		draftmappingcache=null;
	}
	
}
