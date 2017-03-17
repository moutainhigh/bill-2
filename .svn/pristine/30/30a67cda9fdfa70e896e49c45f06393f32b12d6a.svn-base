/********************************************
 * 文件名称: Var106001.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 20160716
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans106001;

import java.util.List;

import com.herongtech.data.interfaces.data.IData;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;

/**
 * 客户信息查询
 * @author Administrator
 *
 */

public class Var106001 {

	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	private String custAccount;
	private String billClass;
	private String billType;
	private String pageSize;
	private String currentPage;
	private String maxBillMoney;
	private String minBillMoney;
	private String totalRows;
	private String retNum;
	private List<Var106001InfoBean> result;
	
	
	
	
	
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

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
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

	public String getMaxBillMoney() {
		return maxBillMoney;
	}

	public void setMaxBillMoney(String maxBillMoney) {
		this.maxBillMoney = maxBillMoney;
	}

	public String getMinBillMoney() {
		return minBillMoney;
	}

	public void setMinBillMoney(String minBillMoney) {
		this.minBillMoney = minBillMoney;
	}

	public String getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}

	public String getRetNum() {
		return retNum;
	}

	public void setRetNum(String retNum) {
		this.retNum = retNum;
	}

	public List<Var106001InfoBean> getResult() {
		return result;
	}

	public void setResult(List<Var106001InfoBean> result) {
		this.result = result;
	}

	/**
	 * 查询结果
	 */
	private IData resultData = null;
	
	/**
	 * 总记录数
	 */
	private int totRecNum = 0;
	
	/**
	 * 请求查询对象
	 */
	private RcBaseSearchBean rcSb = null;

	/**
	 * 
	 * @return IDataset
	 */
	public IData getResultData() {
		return resultData;
	}

	/**
	 * 
	 * @param resultDataSet
	 */
	public void setResultData(IData resultData) {
		this.resultData = resultData;
	}

	public int getTotRecNum() {
		return totRecNum;
	}

	public void setTotRecNum(int totRecNum) {
		this.totRecNum = totRecNum;
	}
	
	public RcBaseSearchBean getRcBaseSearchBean() {
		return rcSb;
	}

	public void setRcBaseSearchBean(RcBaseSearchBean rcSb) {
		this.rcSb = rcSb;
	}
}
