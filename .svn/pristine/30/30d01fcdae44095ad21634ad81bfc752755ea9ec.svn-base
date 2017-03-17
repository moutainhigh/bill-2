/********************************************
 * 文件名称: AnswerData.java
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

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.data.common.data.DataService;
import com.herongtech.data.interfaces.data.DataColumnType;
import com.herongtech.data.interfaces.data.IData;


public class AnswerData {
	public static final String HUNDSUN_VERSION="@system 票据管理平台@version 3.0.0.1 @lastModiDate 20120327 @describe ";
	protected IData answerData = null;

	/**
	 * @return the answerData
	 */
	public IData getAnswerData() {
		return answerData;
	}

	/**
	 * @param answerData
	 *            the answerData to set
	 */
	public void setAnswerData(IData answerData) {
		this.answerData = answerData;
	}

	/**
	 * 
	 */
	public AnswerData() {

		answerData = DataService.getDefaultInstance().getData();
		answerData.addColumn(IFieldName.functionId, DataColumnType.DS_STRING);
		answerData.addColumn(IFieldName.exSerial, DataColumnType.DS_STRING);
		answerData.addColumn(IFieldName.errorNo, DataColumnType.DS_STRING);
		answerData.addColumn(IFieldName.errorInfo, DataColumnType.DS_STRING);
		answerData.addColumn(IFieldName.sysDate, DataColumnType.DS_INT);
		answerData.addColumn(IFieldName.sysTime, DataColumnType.DS_INT);
		
		answerData.appendRow();
	}

	/**
	 * 通过请求包构造应答包
	 * @param requestData
	 */
	public AnswerData(IData requestData){

		answerData = DataService.getDefaultInstance().getData();
		answerData.addColumn(IFieldName.functionId, DataColumnType.DS_STRING);
		answerData.addColumn(IFieldName.exSerial, DataColumnType.DS_STRING);
		answerData.addColumn(IFieldName.errorNo, DataColumnType.DS_STRING);
		answerData.addColumn(IFieldName.errorInfo, DataColumnType.DS_STRING);
		answerData.addColumn(IFieldName.sysDate, DataColumnType.DS_INT);
		answerData.addColumn(IFieldName.sysTime, DataColumnType.DS_INT);

		answerData.appendRow();

        setValue(IFieldName.functionId, requestData.getString(IFieldName.functionId));  //应答包功能号为请求包功能号
        setValue(IFieldName.exSerial, requestData.getString(IFieldName.exSerial));      //应答包流水号为请求包流水号
        setValue(IFieldName.errorNo, IErrorNo.ERR_SUCCESS);   //刚构建应答包，错误码，错误信息为空
        setValue(IFieldName.errorInfo, "交易成功");
        setValue(IFieldName.sysDate, requestData.getInt(IFieldName.sysDate));
        setValue(IFieldName.sysTime, DateUtil.getSysTime());
		
	}

	/**
	 * @return the exSerail
	 */
	public String getExSerail() {
		return this.answerData.getString(IFieldName.exSerial);
	}

	/**
	 * @param exSerail
	 *            the exSerail to set
	 */
	public void setExSerail(String exSerail) {
		this.answerData.updateString(IFieldName.exSerial, exSerail);

	}

	/**
	 * @param functionId
	 */
	public void setFunctionId(String functionId) {
		this.answerData.updateString(IFieldName.functionId, functionId);
	}

	/**
	 * 
	 * @return
	 */
	public String getFunctionId() {
		return this.answerData.getString(IFieldName.functionId);
	}

	/**
	 * @return the exSerail
	 */
	public String getErrorNo() {
		return this.answerData.getString(IFieldName.errorNo);
	}

	/**
	 * @param exSerail
	 *            the exSerail to set
	 */
	public void setErrorNo(String errorNo) {
		this.answerData.updateString(IFieldName.errorNo, errorNo);

	}

	/**
	 * @param functionId
	 */
	public void setErrorInfo(String errorInfo) {
		this.answerData.updateString(IFieldName.errorInfo, errorInfo);
	}

	/**
	 * 
	 * @return
	 */
	public String getErrorInfo() {
		return this.answerData.getString(IFieldName.errorInfo);
	}

	/**
	 * 设置对应的列值，列不存在则新增列
	 * 
	 * @param colName
	 * @param value
	 */
	public void setValue(String colName, String value) {

		if (this.answerData.findColumn(colName) > 0) {
			answerData.updateString(colName, value);
		} else {
			answerData.addColumn(colName, DataColumnType.DS_STRING);
			answerData.updateString(colName, value);
		}
	}

	/**
	 * 设置对应的列值，列不存在则新增列
	 * 
	 * @param colName
	 * @param value
	 */
	public void setValue(String colName, int value) {
		if (answerData.findColumn(colName) > 0) {
			answerData.updateInt(colName, value);
		} else {
			answerData.addColumn(colName, DataColumnType.DS_INT);
			answerData.updateInt(colName, value);
		}
	}

	/**
	 * 设置对应的列值，列不存在则新增列
	 * 
	 * @param colName
	 * @param value
	 */
	public void setValue(String colName, double value) {
		if (answerData.findColumn(colName) > 0) {
			answerData.updateDouble(colName, value);
		} else {
			answerData.addColumn(colName, DataColumnType.DS_DOUBLE);
			answerData.updateDouble(colName, value);
		}
	}

	/**
	 * 
	 * @param row
	 * @param colName
	 * @param value
	 */
	public void setValue(int row, String colName, double value) {

		if (row > answerData.getRowCount() + 1) {
			throw new java.lang.IllegalArgumentException("row[" + row + "]非法");
		}
		if (row > answerData.getRowCount()) {
			answerData.appendRow();
		}

		answerData.locateLine(row);
		
		if (answerData.findColumn(colName) > 0) {
			answerData.updateDouble(colName, value);
		} else {
			answerData.addColumn(colName, DataColumnType.DS_DOUBLE);
			answerData.updateDouble(colName, value);
		}

	}

	/**
	 * 
	 * @param row
	 * @param colName
	 * @param value
	 */
	public void setValue(int row, String colName, int value) {

		if (row > answerData.getRowCount() + 1) {
			throw new java.lang.IllegalArgumentException("row[" + row + "]非法");
		}
		if (row > answerData.getRowCount()) {
			answerData.appendRow();
		}

		answerData.locateLine(row);
		
		if (answerData.findColumn(colName) > 0) {
			answerData.updateInt(colName, value);
		} else {
			answerData.addColumn(colName, DataColumnType.DS_INT);
			answerData.updateInt(colName, value);
		}
	}

	/**
	 * 
	 * @param row
	 * @param colName
	 * @param value
	 */
	public void setValue(int row, String colName, String value) {
		if (row > answerData.getRowCount() + 1) {
			throw new java.lang.IllegalArgumentException("row[" + row + "]非法");
		}
		if (row > answerData.getRowCount()) {
			answerData.appendRow();
		}
		
		answerData.locateLine(row);
		
		if (answerData.findColumn(colName) > 0) {
			answerData.updateString(colName, value);
		} else {
			answerData.addColumn(colName, DataColumnType.DS_STRING);
			answerData.updateString(colName, value);
		}
	}
}
