/********************************************
 * 文件名称: DictCache.java
 * 系统名称: 基础技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 多语言缓存类
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 上午08:39:00
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期     修改人员        修改说明
 * 20160615-01   yanjl  创建  
 *********************************************/

package com.herongtech.console.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.console.domain.bean.Dict;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.log.CommonLog;

public class DictCache {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	private Map<String, DictGroupSet> dictMap = new HashMap<String, DictGroupSet>();
	/**
	 * 数据字典对象,采用单例模式
	 */
	private static DictCache dictCache = null;
	
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
    
	private DictCache(){
    	init();
    }
    
	/**
	 *初始化数据字典信息到内存 
	 */
    private void init(){
    	IDB session = null;
    	try{
    		dictMap.clear();
    		
    		session = DBFactory.getNewDB(); // 获取数据库连接
    		
    	    List<Dict>  list= session.getObjectList("select * from vdict order by key_no, item_code ", Dict.class);
		    for(int i=0;i<list.size();){
		    	Dict dictObj=(Dict)list.get(i);
		    	DictGroupSet set = new DictGroupSet(dictObj.getKeyNo(), dictObj.getKeyName());
		    	do{
					set.addValue(dictObj);
					i++;
					if( i >= list.size()){
						break;
					}
					dictObj=(Dict)list.get(i);
				}while( set.getKeyNo().equals(dictObj.getKeyNo()) );
		    	
		        dictMap.put(set.getKeyNo(), set);
		    }
		}catch(SQLException e){
			e.printStackTrace();
			CommonLog.getCommonLogNoCache().errorMessage("数据字典加载失败",e);
		}finally{
			try {
				DBFactory.closeDB(session);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
    }
    
    /**
	 * 返回DictCache实例，采用启动的时候加载实例，故不需要锁
	 * @return
	 */
	 public static DictCache getInstance(){
		if(dictCache==null){
			dictCache = new DictCache();
		}
	   return dictCache;	
	}
	
	 /**
	  * 对象销毁
	  */
	public static void destroy(){
		if(dictCache != null){
			dictCache.dictMap.clear();
		}
		dictCache = null;
	}
	
	/**
	 * 刷新内存
	 */
	public void refresh(){
		try {
			w.lock();
			dictMap.clear();
			init();
		} finally {
			w.unlock();
		}
	}

	/**
	 * 获取数据字典中文提示信息
	 * @param keyno
	 * @param val
	 * @return
	 */
	public String getItemValue(String keyno,String itemcode){
		try {
			r.lock();
		    DictGroupSet set= (DictGroupSet)dictMap.get(keyno);
	        if(set==null){
	    	   return itemcode;
	        }else{
	    	  return set.getItemValue(itemcode);   
	        }
		}finally {
			r.unlock();
		}
	}
	
	/**
	 * 判断指定数据字典是否存在
	 * @param keyno  字典key
	 * @param val    字典项
	 * @return
	 */
	public boolean isExist(String keyno,String itemcode){
		try {
			r.lock();
			DictGroupSet set= (DictGroupSet)dictMap.get(keyno);
			  if(null==set){
				  return false;
			  }else{
				 return set.isExist(itemcode);
			  }
		}finally {
			r.unlock();
		}
	}
	
	/**
	 * 根据指定返还
	 * @param keyno
	 * @return
	 */
	public  List<Dict> getDictList(String keyno){
		try {
			r.lock();
			DictGroupSet set=(DictGroupSet)dictMap.get(keyno);
		    if(set==null){
				return new ArrayList<Dict>();
			}else{
				return set.getValue();
			}
		}finally {
			r.unlock();
		}
	}
	
	/**
	 * 根据指定返还keyname
	 * @param keyno
	 * @return
	 */
	public  String getKeyName(String keyno){
		try {
			r.lock();
			
			DictGroupSet set=(DictGroupSet)dictMap.get(keyno);
		    if (set==null) {
				return keyno;
			}else{
				return set.getKeyName();
			}
		}finally {
			r.unlock();
		}
	}
	
	   /**
     *刷新参数缓存类     
     */
    
	/**
	 * 数据字典内部类
	 * @author Administrator
	 *
	 */
	private class DictGroupSet {
		private String  keyNo = null;
		private String  keyName = null;
		private List<Dict> list = new ArrayList<Dict>();
		
		/**
		 * 
		 * @param hsKey
		 */
		public DictGroupSet(String keyNo, String keyName){
			this.keyNo = keyNo;
			this.keyName = keyName;
			list = new ArrayList<Dict>();
		}
		
		/**
		 * 
		 * @param dict
		 */
		public void addValue(Dict dict ) {
			list.add(dict);
		}

		/**
		 * 
		 * @return
		 */
		public String getKeyName(){
			return this.keyName;
		}
		
		/**
		 * 
		 * @return
		 */
		public String getKeyNo(){
			return this.keyNo;
		}

		/**
		 * 
		 * @param itemCode
		 * @return
		 */
		public String getItemValue(String itemCode) {
			for(int i = 0; i<list.size(); i++ ){
				Dict row = list.get(i);
				if(row.getItemCode().equals(itemCode))
					return row.getItemValue();
			}
			return itemCode;
		}
		
		/**
		 * 判断子数据字典是否存在
		 * @param itemCode
		 * @return
		 */
		public boolean isExist(String itemCode){
			for(int i = 0; i<list.size(); i++ ){
				Dict row = list.get(i);
				if(row.getItemCode().equals(itemCode)){
			        return true;
				}
			}
			return false;
			
		}
		
		
		/**
		 * 获取所有值。
		 */
		public List<Dict> getValue() {
			return list;
		}
	}
	
    
}
