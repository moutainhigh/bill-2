/********************************************
 * 文件名称: ErrMsgCache.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.herongtech.console.domain.bean.Errmsg;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.log.CommonLog;
/**
 * 错误信息缓存类
 * 
 * @author Administrator
 * 
 */
public class ErrMsgCache {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	private Map<String, Errmsg> errMsgMap = new HashMap<String, Errmsg>();
	private static ErrMsgCache errMsgCache = null;
	private ErrMsgCache() {
		init();
	}

	/**
	 *初始化错误码信息信息到内存
	 */
	private void init() {
		IDB dbsession = null;
		try {
			dbsession = DBFactory.getNewDB(); // 获取数据库连接
	   	 	List<Errmsg> list = dbsession.getObjectList(
					"select * from terrmsg order by err_code ",
					Errmsg.class);
			for (int i = 0; i < list.size();i++) {
				Errmsg obj = (Errmsg) list.get(i);
				errMsgMap.put(obj.getErrCode(), obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogNoCache().errorMessage("错误码信息加载失败",e);
		} finally {
			try {
				DBFactory.closeDB(dbsession);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 获取错误码信息缓存类
	 */
	public static ErrMsgCache getInstance() {
		if (null == errMsgCache) {
			errMsgCache = new ErrMsgCache();
		}
		return errMsgCache;
	}

	
	public static void destroy(){
		if(errMsgCache != null){
			errMsgCache.errMsgMap.clear();
		}
		errMsgCache = null;
	}
	/**
	 * 按制定错误码，返回错误信息
	 */
	public String getErrMsg(String errCode) {
		try {
			r.lock();
			Errmsg errorMsg = (Errmsg) errMsgMap.get(errCode);
			if (null == errorMsg) {
				return errCode;
			} else {
				return errorMsg.getErrMsg();
			}
		}finally {
			r.unlock();
		}
	}

	/**
	 *刷新参数缓存类
	 */
	public void refresh() {
		try {
			w.lock();
			errMsgMap.clear();
			init();
		} finally {
			w.unlock();
		}
	}

}
