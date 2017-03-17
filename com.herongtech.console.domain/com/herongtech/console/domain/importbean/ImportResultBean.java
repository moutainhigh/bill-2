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
import java.util.List;
/**
 * 
 * 导入数据返回数据的成功记录失败记录标题记录，所有导入的模块数据处理之后需返回此类
 * @author songzx
 *
 */
public class ImportResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 标题列表
	 */
	private List<Object> titleList;

	/**
	 * 成功数据列表
	 */
	private List<List<Object>> successList;
	/**
	 * 失败数据列表
	 */
	private List<List<Object>> failList;
	public List<Object> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<Object> titleList) {
		this.titleList = titleList;
	}
	public List<List<Object>> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<List<Object>> successList) {
		this.successList = successList;
	}
	public List<List<Object>> getFailList() {
		return failList;
	}
	public void setFailList(List<List<Object>> failList) {
		this.failList = failList;
	}
	
	
	public ImportResultBean(List<Object> titleList,List<List<Object>> successList,List<List<Object>> failList){
		this.titleList = titleList;
		this.successList = successList ;
		this.failList = failList;
	}
}
