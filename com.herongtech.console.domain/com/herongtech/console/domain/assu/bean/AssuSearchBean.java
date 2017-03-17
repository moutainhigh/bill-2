package com.herongtech.console.domain.assu.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

/**
 * 保证中间类
 * @author Administrator
 *
 */
public class AssuSearchBean extends BaseSearchBean{
	private String batchId;//保证批次号
	private String assuId;//保证批次流水号
	private String custNo;//客户号
	private String custName;//客户名称
	private String totalMoney;//票据总额
	private String totalNum;//票据张数
	private String createDt;//保证日
	private String custManager;//客户经理编号
	private String custManagerName;//客户经理名称
	private String deptName;//所属部门
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getAssuId() {
		return assuId;
	}
	public void setAssuId(String assuId) {
		this.assuId = assuId;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public String getCustManager() {
		return custManager;
	}
	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}
	public String getCustManagerName() {
		return custManagerName;
	}
	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
