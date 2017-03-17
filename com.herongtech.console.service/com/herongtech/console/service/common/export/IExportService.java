/********************************************
 * 文件名称: IExportService.java
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
package com.herongtech.console.service.common.export;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.common.bean.ExportInfoBean;
import com.herongtech.console.domain.common.bean.SearchConditionBean;

public interface IExportService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20161015 @describe ";
	
	/**
	 * 得到导出的标题 ，表头 ， 字段 ， 字段类型 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public List<ExportInfoBean> exportInfoList(String tableName) throws SQLException;
	
	/**
	 * 功能描述：得到导出表格的标题行根据业务表
	 * @author songzx
	 * @param String tableName
	 * @exception SQLException
	 * @return ResultSet
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 */
	public Map<String,ResultSet> getHeaderListAndResultSet(String tableName,BaseSearchBean bean,QueryCondition qc,String alias) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, CloneNotSupportedException;
	/**
	 * 功能描述：得到到处的数据，与数据汇总
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public Map<String,List<ExportInfoBean>> getPlanExportInfo(String tableName) throws SQLException;
	
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
	public void updatexportInfo(String isExport,String user,String order,String tableName,String fieldName) throws SQLException;
	
	
	/**
	 * 功能描述：导出excel表根据条件
	 * @param tableName
	 * @param bean
	 * @param response
	 * @param request
	 * @throws SQLException
	 * @throws Throwable
	 */
	public void exportInfo(String tableName,SearchConditionBean bean,HttpServletResponse response,HttpServletRequest request) throws SQLException, Throwable;
}
