/********************************************
 * 文件名称: DiscService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-09-21
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.domain.importbean;

import java.io.Serializable;
/**
 * 
 * 导入数据父类，用于返回前台显示成功和失败的数据，所有导入的模块需继承此类
 * @author songzx
 *
 */
public class ImportBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 错误信息，返回该记录的失败原因，eg：票号重复
	 */
	String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
