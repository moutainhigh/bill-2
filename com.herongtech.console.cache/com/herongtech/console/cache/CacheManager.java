package com.herongtech.console.cache;

import java.util.HashMap;
import java.util.Map;

import com.herongtech.console.service.interfaces.ICache;

public class CacheManager {

	private static Map<String,ICache> map = new HashMap<String,ICache>();
	
	public static ICache getICache(String cacheName){
		ICache cache = map.get(cacheName);
		return cache;
	}
	
	public static void addICache(String cacheName,ICache cache){
		map.put(cacheName, cache);
	}
}
