/********************************************
 * 文件名称: TableUtils.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-7-15 下午13:14:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.core.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.cache.CacheManager;
import com.herongtech.console.service.interfaces.ICache;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class TableUtils {
	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	public static String getTableLabel(String tableName, String listValue,String listKey,String value) throws Exception{
		if(StringUtils.isNotBlank(value)){
			ICache cache = CacheManager.getICache(tableName);
			if(cache==null){
				//方法2：目前使用该方法
				IDB session = DBFactory.getDB();
				String sql = "select t."+listKey+" from "+tableName+" t where t."+listValue+"=?";
				IData data = session.getData(sql, value);
//				System.out.println("TotalCount="+data.getTotalCount());
//				System.out.println("ColumnCount="+data.getColumnCount());
//				System.out.println("RowCount="+data.getRowCount());
				if(data.getColumnCount()!=0){
					return data.getString(1);
				}
			}else{
				Object obj = cache.getValue(value);
				if(obj!= null){
					return (String) ReflectionUtils.getFieldValue(obj, listKey);
				}
			}
		}
		return value;
		
		
	}
	public static String getTableLabel(List list, String listValue,String listKey,String value){
		//方法1：想使用该方法，需要在fns.tld中配置
		String disPlay = "";
		for (int i = 0; i < list.size(); i++) {
			Object aValue =  list.get(i);
			String valueStr = (String) ReflectionUtils.getFieldValue(aValue, listValue);
			if(valueStr.equals(value)){
				disPlay = (String) ReflectionUtils.getFieldValue(aValue, listKey);
				return disPlay;
			}
		}
		return disPlay;
		
		
	}
}
