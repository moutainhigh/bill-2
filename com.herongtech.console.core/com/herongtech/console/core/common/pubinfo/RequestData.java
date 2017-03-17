/********************************************
 * 文件名称: RequestData.java
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
package com.herongtech.console.core.common.pubinfo;

import com.herongtech.data.common.data.DataService;
import com.herongtech.data.interfaces.data.DataColumnType;
import com.herongtech.data.interfaces.data.IData;

/**
 * 
 * @author Administrator
 *
 */
public class RequestData {
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	protected IData requestData = null;  

	/**
	 * @return the requestData
	 */
	public IData getRequestData() {
		return requestData;
	}

	/**
	 * @param requestData
	 *            the requestData to set
	 */
	public void setRequestData(IData requestData) {
		this.requestData = requestData;
	}

	public RequestData() {
		requestData = DataService.getDefaultInstance().getData();
		
		requestData.appendRow();
	}


	/**
	 * 设置对应的列值，列不存在则新增列
	 * 
	 * @param colName
	 * @param value
	 */
	public void setValue(String colName, String value) {

		if (this.requestData.findColumn(colName) > 0) {
			requestData.updateString(colName, value);
		} else {
			requestData.addColumn(colName, DataColumnType.DS_STRING);
			requestData.updateString(colName, value);
		}
	}

	/**
	 * 设置对应的列值，列不存在则新增列
	 * 
	 * @param colName
	 * @param value
	 */
	public void setValue(String colName, int value) {
		if (requestData.findColumn(colName) > 0) {
			requestData.updateInt(colName, value);
		} else {
			requestData.addColumn(colName, DataColumnType.DS_INT);
			requestData.updateInt(colName, value);
		}
	}

	/**
	 * 设置对应的列值，列不存在则新增列
	 * 
	 * @param colName
	 * @param value
	 */
	public void setValue(String colName, double value) {
		if (requestData.findColumn(colName) > 0) {
			requestData.updateDouble(colName, value);
		} else {
			requestData.addColumn(colName, DataColumnType.DS_DOUBLE);
			requestData.updateDouble(colName, value);
		}
	}

	/**
	 * 
	 * @param row
	 * @param colName
	 * @param value
	 */
	public void setValue(int row, String colName, double value) {

		if (row > requestData.getRowCount() + 1) {
			throw new java.lang.IllegalArgumentException("row[" + row + "]非法");
		}
		if (row > requestData.getRowCount()) {
			requestData.appendRow();
		}

		requestData.beforeFirst();
		for (int i = 1; i <= row; i++) {
			requestData.next();
		}
		if (requestData.findColumn(colName) > 0) {
			requestData.updateDouble(colName, value);
		} else {
			requestData.addColumn(colName, DataColumnType.DS_DOUBLE);
			requestData.updateDouble(colName, value);
		}

	}

	/**
	 * 
	 * @param row
	 * @param colName
	 * @param value
	 */
	public void setValue(int row, String colName, int value) {

		if (row > requestData.getRowCount() + 1) {
			throw new java.lang.IllegalArgumentException("row[" + row + "]非法");
		}
		if (row > requestData.getRowCount()) {
			requestData.appendRow();
		}

		requestData.beforeFirst();
		for (int i = 1; i <= row; i++) {
			requestData.next();
		}
		if (requestData.findColumn(colName) > 0) {
			requestData.updateInt(colName, value);
		} else {
			requestData.addColumn(colName, DataColumnType.DS_INT);
			requestData.updateInt(colName, value);
		}

	}

	/**
	 * 
	 * @param row
	 * @param colName
	 * @param value
	 */
	public void setValue(int row, String colName, String value) {
		if (row > requestData.getRowCount() + 1) {
			throw new java.lang.IllegalArgumentException("row[" + row + "]非法");
		}
		if (row > requestData.getRowCount()) {
			requestData.appendRow();
		}
		requestData.beforeFirst();
		for (int i = 1; i <= row; i++) {
			requestData.next();

		}

		if (requestData.findColumn(colName) > 0) {
			requestData.updateString(colName, value);
		} else {
			requestData.addColumn(colName, DataColumnType.DS_STRING);
			requestData.updateString(colName, value);
		}
	}
}
