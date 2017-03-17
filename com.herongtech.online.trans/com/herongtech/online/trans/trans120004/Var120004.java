package com.herongtech.online.trans.trans120004;

import java.util.List;

public class Var120004 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	private String custAccount;
	private String billClass;
	private String pageSize;
	private String currentPage;
	private String totalRows;
	private String retNum;
	private List<Var120004InfoBean> result;
	public String getCustAccount() {
		return custAccount;
	}
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}
	public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
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
	public List<Var120004InfoBean> getResult() {
		return result;
	}
	public void setResult(List<Var120004InfoBean> result) {
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
