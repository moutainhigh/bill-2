/********************************************
 * 文件名称: ExportDao.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称: 统一导出
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-10-14 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.domain.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.common.bean.ExportInfoBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class ExportDao {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20161015 @describe ";
	
			
			
	/**
	 * 更新导出excel信息
	 * @author songzx
	 * @param isExport 是否导出 0否 1是
	 * @param user 操作用户
	 * @param order 到处顺序
	 * @param tableName 导出业务表
	 * @param fieldName 导出业务字段
	 * @throws SQLException 
	 */
	public void updatexportInfo(String isExport,String user,String order,String tableName,String fieldName) throws SQLException{
		IDB session = DBFactory.getDB();
		StringBuffer sb = new StringBuffer("UPDATE T_EXPORT_CONFIG T SET T.IS_EXPORT = ?,T.OPER_TIME = ?, T.OPER_USER = ? , T.EX_ORDER = ?");
		sb.append(" WHERE T.TABLE_NAME = ? AND T.FIELD_NAME = ? ");
		session.execute(sb.toString(),isExport,DateTimeUtil.toDateTimeString(new Date()),user,order,tableName,fieldName);
	}
	
	/**
	 * 得到导出excel信息
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public List<ExportInfoBean> exportInfoList(String tableName) throws SQLException{
		IDB session = DBFactory.getDB();
		StringBuffer sb = new StringBuffer("SELECT TEC.FIELD_NAME,TEC.EX_FIELD_NAME,TEC.IS_TOTAL,TEC.EX_TYPE,TEC.EX_DICT,TEC.EX_ALIGN,TEC.IS_EXPORT");
		sb.append(",TEC.TABLE_ALIAS,TEC.EX_ORDER,TEC.EX_TABLE_NAME,TEC.EX_CONDITION,TEC.CONDITION_VALUE FROM T_EXPORT_CONFIG TEC WHERE TEC.TABLE_NAME = ? ORDER BY TEC.EX_ORDER");
		List<ExportInfoBean> data = session.getObjectList(sb.toString(),ExportInfoBean.class,tableName);
		return data;
	}


	public ResultSet getResultSet(String string, BaseSearchBean bean,QueryCondition qc) throws SQLException {
		IDB session = DBFactory.getDB();
		try {
			qc.initFromSearchBean(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String allSql = qc.getAllSqlString(string);
		return session.getResultSetByList(allSql,qc.getParameterValues());
	}
}
