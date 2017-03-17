package com.herongtech.console.cache;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.console.domain.bean.DelayRule;

import com.herongtech.console.domain.dao.DelayRuleDao;
import com.herongtech.log.CommonLog;
/**顺延缓存类*/
public class DelayRuleCache {

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	private Map<String,DelayRule> delayrulemap = new HashMap<String,DelayRule>();
	private static DelayRuleCache delayRuleCache = null;
	private DelayRuleCache(){
		init();
	}
	/**顺延初始化方法*/
	private void init(){
		try {
			DelayRuleDao dao = new DelayRuleDao();
			List<DelayRule> delayrulelsit = dao.getAllDelayRuleList();
			for (int i = 0; i < delayrulelsit.size(); i++) {
				DelayRule delayrule = delayrulelsit.get(i);
				String key = delayrule.getProdNo()+delayrule.getBillClass()+delayrule.getBillType();
				delayrulemap.put(key,delayrule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogNoCache().errorMessage("初始化失败",e);
		}
	}
	
	/**获得顺延缓存类*/
	public static DelayRuleCache getDelayRuleCache(){
		if(null==delayRuleCache){
			delayRuleCache=new DelayRuleCache();
		}
		return delayRuleCache;
	}
	
	/**获得顺延对象*/
	public DelayRule getDelayRulemapvalue(String key){
		try {
			r.lock();
			DelayRule value = delayrulemap.get(key);
			if(null==value){
				return new DelayRule();
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
			delayrulemap.clear();
			init();
		} finally {
			w.unlock();
		}
	}
	
	/**清除缓存*/
	public static void clearmap(){
		if(delayRuleCache!=null){
			delayRuleCache.delayrulemap.clear();	
		}
		delayRuleCache=null;
	}
	
}
