/********************************************
* 文件名称: CacheItemDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.dao;

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.CacheItem;
public class CacheItemDao{

public int addCacheItem(CacheItem obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tcacheitem(cache_code,cache_name,cache_class,cache_method,remark,bank_no,reserve1)values(?,?,?,?,?,?,?)",
	obj.getCacheCode(),obj.getCacheName(),obj.getCacheClass(),obj.getCacheMethod(),obj.getRemark(),
	obj.getBankNo(),obj.getReserve1());
}

public int deleteCacheItem(CacheItem obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcacheitem where cache_code=?",obj.getCacheCode());
}

public int deleteCacheItem(String cacheCode) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcacheitem where cache_code=?",cacheCode);
}

public int modifyCacheItem(CacheItem obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcacheitem set cache_name=?,cache_class=?,cache_method=?,remark=?,bank_no=?,reserve1=? where cache_code=?",
	obj.getCacheName(),obj.getCacheClass(),obj.getCacheMethod(),obj.getRemark(),
	obj.getBankNo(),obj.getReserve1(),obj.getCacheCode());
}

public int modifyCacheItem(CacheItem obj,String cacheCode) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcacheitem set cache_name=?,cache_class=?,cache_method=?,remark=?,bank_no=?,reserve1=? where cache_code=?",
	obj.getCacheName(),obj.getCacheClass(),obj.getCacheMethod(),obj.getRemark(),
	obj.getBankNo(),obj.getReserve1(),cacheCode);
}

public CacheItem getCacheItem(String cacheCode) throws SQLException {
	IDB session = DBFactory.getDB();
	CacheItem obj = (CacheItem)session.getObject("select * from tcacheitem where cache_code=?",CacheItem.class,cacheCode);
	return obj;
}

}
