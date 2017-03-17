package com.herongtech.console.domain.out.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

public class OutSearchBean extends BaseSearchBean{
	private String outmxId;
	private String billNo;
	private String billType;
	private String billClass;
	private String operStatus;
	private Object[] opers;
	private String operNo;
	private String dueDt;
	private String orderBy;
	private String outId;
	private String custNo;
	private String custName;
	private String applyStatus;
	private String batchClass;
	private String batchType;
	private String batchNo;
	private String orgCode;
	private String isTc;
	private String intobillRelaId;
	private String branchNo;
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	//申请柜员
	private String applyOperNo;
	//审核柜员
	private String checkOperNo;
	public String getCheckOperNo() {
		return checkOperNo;
	}
	public void setCheckOperNo(String checkOperNo) {
		this.checkOperNo = checkOperNo;
	}
	public String getApplyOperNo() {
		return applyOperNo;
	}
	public void setApplyOperNo(String applyOperNo) {
		this.applyOperNo = applyOperNo;
	}
	public String getOutmxId() {
		return outmxId;
	}
	public void setOutmxId(String outmxId) {
		this.outmxId = outmxId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
	public String getOperStatus() {
		return operStatus;
	}
	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}
	public Object[] getOpers() {
		return opers;
	}
	public void setOpers(Object[] opers) {
		this.opers = opers;
	}
	public String getOperNo() {
		return operNo;
	}
	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}
	public String getDueDt() {
		return dueDt;
	}
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
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
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getBatchClass() {
		return batchClass;
	}
	public void setBatchClass(String batchClass) {
		this.batchClass = batchClass;
	}
	public String getBatchType() {
		return batchType;
	}
	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public void setIsTc(String isTc) {
		this.isTc = isTc;
	}
	public String getIsTc() {
		return isTc;
	}
	public void setIntobillRelaId(String intobillRelaId) {
		this.intobillRelaId = intobillRelaId;
	}
	public String getIntobillRelaId() {
		return intobillRelaId;
	}
	
	

}
