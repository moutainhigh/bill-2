
package com.herongtech.online.trans.trans105001;

import java.util.List;


/**
 * 客户信息查询
 * @author Administrator
 *
 */
public class Var105001 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";

	private String currentPage;
	private String pageSize;
	private String totalRows;//总记录数
	private String custAccount;
	
	private List<Var105001InfoBean> billList;

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}

	public String getCustAccount() {
		return custAccount;
	}

	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

	public List<Var105001InfoBean> getBillList() {
		return billList;
	}

	public void setBillList(List<Var105001InfoBean> billList) {
		this.billList = billList;
	}

}
