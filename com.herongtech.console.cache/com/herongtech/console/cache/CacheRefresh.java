package com.herongtech.console.cache;

public class CacheRefresh {
    public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
     
	public void dictCacheRefresh(){
		DictCache.getInstance().refresh();
	}
	public void errMsgCacheRefresh(){
		ErrMsgCache.getInstance().refresh();
	}
	
}
