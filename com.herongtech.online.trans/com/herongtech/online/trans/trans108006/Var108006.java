/********************************************
 * 文件名称: Var108006.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 20160808
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans108006;

import java.util.List;

/**
 * 电子票据交易类查询
 *
 */
public class Var108006 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	private String custAccount;
	private String type;
	private String beginDate;
	private String endEndt;
	private String pageSize;
	private String currentPage;
	private String totalRows;
	private String retNum;
	private List<Var108006InfoBean> result;
	public String getCustAccount() {
		return custAccount;
	}
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndEndt() {
		return endEndt;
	}
	public void setEndEndt(String endEndt) {
		this.endEndt = endEndt;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public List<Var108006InfoBean> getResult() {
		return result;
	}
	public void setResult(List<Var108006InfoBean> result) {
		this.result = result;
	}
	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}
	public String getTotalRows() {
		return totalRows;
	}
	public void setRetNum(String retNum) {
		this.retNum = retNum;
	}
	public String getRetNum() {
		return retNum;
	}
	

}
