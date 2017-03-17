/********************************************
 * 文件名称: TradeBankRootCache.java
 * 系统名称: 基础技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 大行信息缓存类
 * 系统版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-12-12 上午09:39:00
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期     修改人员        修改说明
 * 20161212-01   fqz  创建  
 *********************************************/
package com.herongtech.console.cache;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.console.domain.bean.TradeBankRoot;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.log.CommonLog;

public class TradeBankRootCache {

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	private static TradeBankRootCache tradeBankRootCache = null;
	private static Map<String,TradeBankRoot> bankRootMap = new HashMap<String,TradeBankRoot>();
	
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	/**
	 * 返回TradeBankRootCache实例，采用启动的时候加载实例，故不需要锁
	 * @return
	 */
	public static TradeBankRootCache getInstance(){
		if(tradeBankRootCache==null){
			tradeBankRootCache = new TradeBankRootCache();
		}
		return tradeBankRootCache;
	}
	/**
	 * 构造方法，初始化
	 */
	public TradeBankRootCache(){
		init();
	}
	/**
	 * 初始化方法
	 */
	public void init(){
		bankRootMap.clear();
		IDB session = DBFactory.getNewDB();
		try {
			List<TradeBankRoot> list = session.getObjectList("select * from ttradebank_root ", TradeBankRoot.class);
			for(TradeBankRoot bankRoot : list){
				bankRootMap.put(bankRoot.getLeftThreeBankNo(), bankRoot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("大行信息加载失败",e);
		}finally{
			try {
				DBFactory.closeDB(session);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据行号前三位获取大行信息
	 * @param leftThreeBankNo
	 * @return
	 */
	public TradeBankRoot getValue(String leftThreeBankNo){
		try{
			r.lock();
			TradeBankRoot bankRoot = bankRootMap.get(leftThreeBankNo);
			return bankRoot;
		}finally{
			r.unlock();
		}
	}
	/**
	 * 对象销毁
	 */
	public static void destroy(){
		if(tradeBankRootCache!=null){
			tradeBankRootCache.bankRootMap.clear();
		}
		tradeBankRootCache = null;
	}
	/**
	 * 刷新缓存
	 */
	public void refresh(){
		try{
			w.lock();
			bankRootMap.clear();
			init();
		}finally{
			w.unlock();
		}
	}
}
