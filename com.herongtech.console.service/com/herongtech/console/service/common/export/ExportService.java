/********************************************
 * 文件名称: ExportService.java
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
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.ExportExcel;
import com.herongtech.console.domain.common.bean.ExportInfoBean;
import com.herongtech.console.domain.common.bean.SearchConditionBean;
import com.herongtech.console.domain.common.dao.ExportDao;
import com.herongtech.exception.impl.BizAppException;

public class ExportService implements IExportService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20161015 @describe ";
	private ExportDao eDao = new ExportDao();
	/**
	 * 功能描述：得到导出表格的标题行根据业务表
	 * @author songzx
	 * @param String tableName
	 * @exception SQLException
	 * @return List<String>
	 */
	public List<ExportInfoBean> exportInfoList(String tableName) throws SQLException {
		return eDao.exportInfoList(tableName);
	}
	
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
	 * @throws CloneNotSupportedException 
	 */
	public Map<String,ResultSet> getHeaderListAndResultSet(String tableName,BaseSearchBean bean,QueryCondition qc,String alias) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, CloneNotSupportedException {
		Map<String,ResultSet> rsMap = new HashMap<String, ResultSet>();
		List<ExportInfoBean> rsList = eDao.exportInfoList(tableName);
		StringBuilder sb = new StringBuilder("SELECT ");
		StringBuilder totalS = new StringBuilder();
		for (ExportInfoBean ebean : rsList) {
			if("1".equals(ebean.getIsExport())){
				if(StringUtils.isBlank(alias)){
					alias = ebean.getTableAlias();
				}
				sb.append(alias).append('.').append(ebean.getFieldName()).append(',');
				if("1".equals(ebean.getIsTotal())){
					totalS.append("SUM(").append(alias).append('.').append(ebean.getFieldName()).append("),");
				}
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" FROM ").append(tableName).append(' ').append(alias);
		rsMap.put("baseData", eDao.getResultSet(sb.toString(),bean,qc));
		if(StringUtils.isNotEmpty(totalS.toString())){
			totalS.deleteCharAt(totalS.length()-1);
			totalS.append(" FROM ").append(tableName).append(' ').append(alias);
			rsMap.put("totalData", eDao.getResultSet("SELECT "+totalS.toString(),bean,qc));
		}
		return rsMap;
	}
	
	/**
	 * 功能描述：得到到处的数据，与数据汇总
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public Map<String,List<ExportInfoBean>> getPlanExportInfo(String tableName) throws SQLException{
		List<ExportInfoBean> list = eDao.exportInfoList(tableName);
		List<ExportInfoBean> leftList = Lists.newArrayList();
		List<ExportInfoBean> rightList = Lists.newArrayList();
		for (ExportInfoBean e: list) {
			if("0".equals(e.getIsExport())){
				leftList.add(e);
			}else if("1".equals(e.getIsExport())){
				rightList.add(e);
			}
		}
		Map<String,List<ExportInfoBean>> rs = new HashMap<String, List<ExportInfoBean>>();
		rs.put("leftList", leftList);
		rs.put("rightList", rightList);
		return rs;
	}
	
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
		eDao.updatexportInfo(isExport,user,order,tableName,fieldName);
	}
	
	/**
	 * 功能描述：导出excel表根据条件
	 * @param tableName
	 * @param bean
	 * @param response
	 * @param request
	 * @throws SQLException
	 * @throws Throwable
	 */
	public void exportInfo(String tableName,SearchConditionBean bean,HttpServletResponse response,HttpServletRequest request) throws SQLException, Throwable{
		QueryCondition qc = new QueryCondition();
		//得到导出标题
		List<ExportInfoBean> rsList = exportInfoList(tableName);
		List<String> list = Lists.newArrayList();
		String excelName = null;
		String tableAlias = null;
		for (ExportInfoBean eBean : rsList) {
			if( excelName == null ){
				excelName = eBean.getExTableName();
				tableAlias = eBean.getTableAlias();
			}
			//查询条件
			if("2".equals(eBean.getIsExport())){
				if(eBean.getExCondition()==null){
					 throw new BizAppException("查询条件不可以为空");
				}else{
					String[] cArr = eBean.getExCondition().split(",");
					//设置表别名
					bean.setDfaultSrchTbAliasName(tableAlias);
					//固定查询条件
					if(StringUtils.isNotEmpty(eBean.getConditionValue())){
						String[] values = eBean.getConditionValue().split(",");
						//查询表达式为in特俗处理
						if(eBean.getExCondition().toLowerCase().contains("in")){
							qc.add(eBean.getTableAlias()+"."+eBean.getFieldName()+" in (:param)", values);
						}else{
							qc.add(eBean.getTableAlias()+"."+eBean.getFieldName()+" =:param",eBean.getConditionValue());
						}
					}else{
						//查询条件值 前台传入 
						String obj = (String)bean.getClass().getDeclaredMethod("get"+StringUtils.capitalize(eBean.getFieldName())).invoke(bean);
						if(StringUtils.isNotEmpty(obj)){
							if(cArr.length == 1){
								//查询表达式为in特俗处理
								if(eBean.getExCondition().toLowerCase().contains("in")){
									qc.add(eBean.getTableAlias()+"."+eBean.getFieldName()+" in (:param)", obj.split(","));
									Method mSet = bean.getClass().getDeclaredMethod("set"+StringUtils.capitalize(eBean.getFieldName()), String.class);
									mSet.invoke(bean, "");
								}else{
									//查询表达式为like特俗处理
									if(eBean.getExCondition().toLowerCase().contains("like")){
										Method mSet = bean.getClass().getDeclaredMethod("set"+StringUtils.capitalize(eBean.getFieldName()), String.class);
										String value = "%"+obj+"%";
										mSet.invoke(bean, value);
									}
									bean.addSpecialOpertion(eBean.getFieldName()," "+eBean.getExCondition()+" ");
								}
							}else{
								bean.addSpecialOpertion(eBean.getFieldName()+"End"," "+cArr[1]+" ");
								bean.addSqlPropretyMapping(eBean.getFieldName()+"End",eBean.getFieldName());
								bean.addSpecialOpertion(eBean.getFieldName()+"Start"," "+cArr[0]+" ");
								bean.addSqlPropretyMapping(eBean.getFieldName()+"Start",eBean.getFieldName());
							}
						}
					}
				}
			}
			if("1".equals(eBean.getIsExport())) list.add(eBean.getExFieldName());
		}
		//生成excel
		ExportExcel.exportData(request,response,excelName,rsList,list,getHeaderListAndResultSet(tableName, bean,qc,null));
	}
}
