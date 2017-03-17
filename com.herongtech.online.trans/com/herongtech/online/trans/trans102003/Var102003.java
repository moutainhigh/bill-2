/********************************************
 * 文件名称: Var102003.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:查询提示承兑待签收票据
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzc
 * 开发时间: 20160810
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.online.trans.trans102003;

import java.util.List;

/**
 * 查询提示承兑待签收票据
 * @author Administrator
 *
 */
public class Var102003 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	private String currentPage;
	private String pageSize;
	private String totalRows;//总记录数
	private String custAccount;
	
	private List<Var102003InfoBean> billList;

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

	public List<Var102003InfoBean> getBillList() {
		return billList;
	}

	public void setBillList(List<Var102003InfoBean> billList) {
		this.billList = billList;
	}
	
}
