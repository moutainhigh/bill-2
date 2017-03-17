package com.herongtech.rc.domain.bean;

import java.util.Date;

import com.herongtech.console.domain.bean.EcdsBillBean;


/**
 * 网银接口查询结果BEAN
 * 
 * @date   2011-11-14
 */
public class EcdsQueryResult extends EcdsBillBean{

private String applicantName;//申请人名称;
	
	private String applicantAcctNo;//申请人账号
	
	private String applicantOrgCode;//申请人组织机构代码
	
	private String applicantBankNo;//申请人开户行号;
	
	private Date   applyDate;//申请日期
	
	private String receiverName;//接收人名称
	
	private String receiverAcctNo;//接收人账号
	
	private String receiverBankNo;//接收人行号
	
	private String remark;//备注
	
	private String settlementMark;//线上清算标记 （1－线上0－线下）

	private String overDueFlag; //是否逾期（1－已逾期0－未逾期）
	
	private String overDueReason;//逾期原因说明

	private String processResult;//处理结果（申请成功与否）
	
	private String processMessage;//处理失败原因
	
	private String signUpMark; //签收拒绝标记
	
	private String rejectReason; //拒绝说明
	
	private String operStatus;//操作状态
	
	private String billBeforeOwner;//操作签收
	private String prodNo;//追索类型/存票类型 
	
	private String inBankNo;
	private String inAcctNo;
	
	
	
	public String getInBankNo() {
		return inBankNo;
	}

	public void setInBankNo(String inBankNo) {
		this.inBankNo = inBankNo;
	}

	public String getInAcctNo() {
		return inAcctNo;
	}

	public void setInAcctNo(String inAcctNo) {
		this.inAcctNo = inAcctNo;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}


	public String getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getProcessMessage() {
		return processMessage;
	}

	public void setProcessMessage(String processMessage) {
		this.processMessage = processMessage;
	}

	public String getSignUpMark() {
		return signUpMark;
	}

	public void setSignUpMark(String signUpMark) {
		this.signUpMark = signUpMark;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public void setReceiverAcctNo(String receiverAcctNo) {
		this.receiverAcctNo = receiverAcctNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getSettlementMark() {
		return settlementMark;
	}

	public void setSettlementMark(String settlementMark) {
		this.settlementMark = settlementMark;
	}


	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAcctNo() {
		return receiverAcctNo;
	}

	public void setReceiverAcct(String receiverAcctNo) {
		this.receiverAcctNo = receiverAcctNo;
	}

	public String getReceiverBankNo() {
		return receiverBankNo;
	}

	public void setReceiverBankNo(String receiverBankNo) {
		this.receiverBankNo = receiverBankNo;
	}

	public String getOverDueFlag() {
		return overDueFlag;
	}

	public void setOverDueFlag(String overDueFlag) {
		this.overDueFlag = overDueFlag;
	}

	public String getApplicantAcctNo() {
		return applicantAcctNo;
	}

	public void setApplicantAcctNo(String applicantAcctNo) {
		this.applicantAcctNo = applicantAcctNo;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantBankNo() {
		return applicantBankNo;
	}

	public void setApplicantBankNo(String applicantBankNo) {
		this.applicantBankNo = applicantBankNo;
	}

	public String getApplicantOrgCode() {
		return applicantOrgCode;
	}

	public void setApplicantOrgCode(String applicantOrgCode) {
		this.applicantOrgCode = applicantOrgCode;
	}

	
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}


	public String getOverDueReason() {
		return overDueReason;
	}

	public void setOverDueReason(String overDueReason) {
		this.overDueReason = overDueReason;
	}

	public String getBillBeforeOwner() {
		return billBeforeOwner;
	}

	public void setBillBeforeOwner(String billBeforeOwner) {
		this.billBeforeOwner = billBeforeOwner;
	}
}
