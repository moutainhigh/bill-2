package com.herongtech.console.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.rc.domain.bean.RgctStatusMapping;
import com.herongtech.rc.domain.dao.RgctStatusMappingDao;

/**RgctStatusMappingCache缓存类*/
public class RgctStatusMappingCache {

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	private Map<String,RgctStatusMapping> rgctstatusmappingcachemapp = new HashMap<String, RgctStatusMapping>();
	private Map<String,String> rgctstatusmappingcachemapps = new HashMap<String,String>();
	private static RgctStatusMappingCache rgctstatusmapping = null;
	
	private RgctStatusMappingCache(){
		init();
	}

	/**初始化数据*/
	private void init() {
		RgctStatusMappingDao dao = new RgctStatusMappingDao();
		List<RgctStatusMapping> list = dao.getRgctStatusMappingAllData();
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i).getBbspStatusCode();
			RgctStatusMapping value = list.get(i);
			
			String keys = list.get(i).getEcdsStatusCode();
			String values = list.get(i).getBbspStatusCode();
			rgctstatusmappingcachemapps.put(keys, values);
			rgctstatusmappingcachemapp.put(key, value);
		}
	}
	
	/**获得缓存类*/
	public static RgctStatusMappingCache getRgctStatusMappingCache(){
		if(null==rgctstatusmapping){
			rgctstatusmapping = new RgctStatusMappingCache();
		}
		return rgctstatusmapping;
	}
	/**根据bbsp_status_code从缓存中获得RgctStatusMapping对象*/
	public RgctStatusMapping getRgctStatusMapping(String bbsp_status_code){
		try {
			r.lock();
			RgctStatusMapping rgctststumapping = rgctstatusmappingcachemapp.get(bbsp_status_code);
			if(null==rgctststumapping){
				return new RgctStatusMapping();
			}else{
				return rgctststumapping;
			}
		}finally{
			r.unlock();			
		}
	}
	/**根据EcdsStatusCode得到对应的BbspStatusCode*/
	public String getBbspStatusCodebyEcdsStatusCode(String EcdsStatusCode){
		try {
			r.lock();
			String BbspStatusCode = rgctstatusmappingcachemapps.get(EcdsStatusCode);
			if(null==BbspStatusCode){
				return "";
			}else{
				return BbspStatusCode;
			}
		}finally{
			r.unlock();			
		}
	}

	
	/**刷新缓存*/
	public void refresh() {
		try {
			w.lock();
			rgctstatusmappingcachemapp.clear();
			rgctstatusmappingcachemapps.clear();
			init();
		} finally {
			w.unlock();
		}
	}
	/**清除缓存*/
	public static void clearmap(){
		if(rgctstatusmapping!=null){
			rgctstatusmapping.rgctstatusmappingcachemapp.clear();	
			rgctstatusmapping.rgctstatusmappingcachemapps.clear();	
		}
		rgctstatusmapping=null;
	}
}
