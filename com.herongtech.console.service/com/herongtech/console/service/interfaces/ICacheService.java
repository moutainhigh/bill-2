/********************************************
 * 文件名称: ICacheService.java
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
package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.CacheItem;
import com.herongtech.exception.impl.BizAppException;

public interface ICacheService {

	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取缓存参数
	 * 
	 * @param cacheCode 部门标识
	 */
	public  CacheItem getParam(String cacheCode) throws BizAppException;
	
	/**
	 * 插入缓存表
	 *
	 */
	public void addParam(CacheItem cache)throws BizAppException;
	
	/**
	 * 修改缓存表
	 */
	
	public void modifyParam(CacheItem cache)throws BizAppException;
}
