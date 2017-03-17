package com.herongtech.console.domain.common.bean;

import java.math.BigDecimal;
import java.util.Date;

public class FacBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String billId;//票ID
	private String acceptanceBankCode;	//承兑行行号
	private String billNo;
	private BigDecimal faceAmount;	//票面金额
	private String loanRequisition;//出账申请书号（唯一）  流水号
	private String billType;//票据类型
	private String billClass;
	private String rgctId;
	private String aimBankNo; //对手行行号
	private boolean type;//成功与否
	private String message;//返回信息信息
	private String facType;//额度种类
	private Date endBillDate;//票面到期日
	private String acceptor;//承兑人
	private String productId;//额度品种
	private String productName;//额度品种名称
	private String ownerPartyId;//所属客户
	private String ownerPartyName;//所属客户名称
	private String isCyc;//是否可循环 
	private String isCycName;//是否可循环名称 
	private String OperatorId;//操作员
	private String frontendJournalId;//流水号
	private String brchNo;//交易机构
	private BigDecimal bAmount;	//保证金金额
	public String getOperatorId() {
		return OperatorId;
	}
	public void setOperatorId(String operatorId) {
		OperatorId = operatorId;
	}
	public String getFrontendJournalId() {
		return frontendJournalId;
	}
	public void setFrontendJournalId(String frontendJournalId) {
		this.frontendJournalId = frontendJournalId;
	}
	public String getBrchNo() {
		return brchNo;
	}
	public void setBrchNo(String brchNo) {
		this.brchNo = brchNo;
	}
	public String getIsCycName() {
		return isCycName;
	}
	public void setIsCycName(String isCycName) {
		this.isCycName = isCycName;
	}
	public String getOwnerPartyName() {
		return ownerPartyName;
	}
	public void setOwnerPartyName(String ownerPartyName) {
		this.ownerPartyName = ownerPartyName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOwnerPartyId() {
		return ownerPartyId;
	}
	public void setOwnerPartyId(String ownerPartyId) {
		this.ownerPartyId = ownerPartyId;
	}
	public String getIsCyc() {
		return isCyc;
	}
	public void setIsCyc(String isCyc) {
		this.isCyc = isCyc;
	}
	public FacBean(){
		
	}
	public FacBean(String loanRequisition,Double faceAmount) {
		super();
		this.faceAmount = BigDecimal.valueOf(faceAmount);
		this.loanRequisition = loanRequisition;
	}
	public FacBean(String loanRequisition,Double faceAmount,Long billId ) {
		super();
		this.faceAmount = BigDecimal.valueOf(faceAmount);
		this.loanRequisition = loanRequisition;
		this.billId = billId.toString();
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getMessage() {
		return message;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAcceptanceBankCode() {
		return acceptanceBankCode;
	}
	public void setAcceptanceBankCode(String acceptanceBankCode) {
		this.acceptanceBankCode = acceptanceBankCode;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public BigDecimal getFaceAmount() {
		return faceAmount;
	}
	public void setFaceAmount(BigDecimal faceAmount) {
		this.faceAmount = faceAmount;
	}
	public String getLoanRequisition() {
		return loanRequisition;
	}
	public void setLoanRequisition(String loanRequisition) {
		this.loanRequisition = loanRequisition;
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
	public String getRgctId() {
		return rgctId;
	}
	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	public String getAimBankNo() {
		return aimBankNo;
	}
	public void setAimBankNo(String aimBankNo) {
		this.aimBankNo = aimBankNo;
	}

	public Date getEndBillDate() {
		return endBillDate;
	}
	public void setEndBillDate(Date endBillDate) {
		this.endBillDate = endBillDate;
	}
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getFacType() {
		return facType;
	}
	public void setFacType(String facType) {
		this.facType = facType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getBAmount() {
		return bAmount;
	}
	public void setBAmount(BigDecimal amount) {
		bAmount = amount;
	}
}
