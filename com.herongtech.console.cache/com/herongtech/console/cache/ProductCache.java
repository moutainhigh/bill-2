package com.herongtech.console.cache;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ICache;

public class ProductCache implements ICache {
	private static ProductCache prodCache = null;
	private static Map<String,Product> prodMap = new HashMap<String,Product>();
	
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	
	public static ProductCache getInstance(){
		if(prodCache==null){
			prodCache = new ProductCache();
		}
		return prodCache;
	}

	public ProductCache(){
		init();
	}
	
	public void init(){
		try {
			List<Product> prodList = ServiceFactory.getProductService().getAllProduct();
			for(Product prod : prodList){
				prodMap.put(prod.getProdNo(), prod);
			}
			CacheManager.addICache("tproduct", prodCache);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void destroy(){
		if(prodCache != null){
			prodCache.prodMap.clear();
		}
		prodCache = null;
	}
	/**
	 * 刷新内存
	 */
	public void refresh(){
		try {
			w.lock();
			prodMap.clear();
			init();
		}finally{
			w.unlock();
		}
	}
	@Override
	public Map getCacheMap() {
		return prodMap;
	}

	@Override
	public Product getValue(String key) {

		Product prod = prodMap.get(key);
		return prod;
	}

	public static void main(String[] args) {
		ProductCache.getInstance();
	}
}
