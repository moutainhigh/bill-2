/********************************************
 * 文件名称: CacheService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.cache;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.CacheItem;
import com.herongtech.console.domain.dao.CacheItemDao;
import com.herongtech.console.service.interfaces.ICacheService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 缓存参数取法
 *
 */

public class CacheService implements ICacheService {
	
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 获取缓存表
	 */

	@Override
	public CacheItem getParam(String cacheCode) throws BizAppException {
		CacheItemDao dao=new CacheItemDao();
		try {
			return dao.getCacheItem(cacheCode);
			} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 插入缓存表
	 */
	@Override
	public void addParam(CacheItem cache) throws BizAppException {
		CacheItemDao dao=new CacheItemDao();
		try {
			if (dao.addCacheItem(cache)!=1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加缓存失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}

	}

	/**
	 * 修改缓存表
	 */
	@Override
	public void modifyParam(CacheItem cache) throws BizAppException {
		CacheItemDao dao=new CacheItemDao();
		try {
			if (dao.modifyCacheItem(cache) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改缓存失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}

	}

}
