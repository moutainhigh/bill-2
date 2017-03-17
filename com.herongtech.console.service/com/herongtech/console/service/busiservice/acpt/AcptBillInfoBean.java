package com.herongtech.console.service.busiservice.acpt;   //

import com.thoughtworks.xstream.annotations.XStreamAlias;

//银承明细
public class AcptBillInfoBean {
	private String billMoney;//票据金额
	private String currencyCategory;
	private String payeeAcct;
	private String payee;
	private String payeeBankName;
	private String billClass;
	private String billType;
	private String billNo ;
	//承兑明细流水号
	private String acptmxId;
	//登记中心id
	private String rgctId;
	
	private String acptId;
	
	private String esno;
	private String branchNo;
	private String protocalNo;
	private String facNo;
	private String remitter;
	
	private String remitterCustNo;
	private String remitterAcct;
	private String remitterBranchNo;
	private String remitterBankNo;
	private String remitterBankName;
	private String voucherType;
	private String billStatus;
	
	private String draweeBankNo;
	private String draweeBankName;
	private String draweeAddr;
	private String payeeBankNo;
	
	private String dueDt;
	private String issueDt;
	private String acceptor;
	private String acceptorAcct;
	private String acceptorBankNo;
	private String acceptorBankName;
	private String colltnStatus;
	private String colltnId;
	private String sspdStatus;
	private String sspdId;
	private String printStatus;
	private String prsnttnAcctNo;
	private String prsnttnName;
	private String prsnttnBankNo;
	private String prsnttnBankName;
	private String paymentPath;
	private String paymentDt;
	private String custRemark;
	private String bankRemark;
	private String payWaitOrder;
	private String txlCtrctNb;
	private String invcNb;
	private String realAcctDt;
	private String realAcctTime;
	private String drwgBckOperNo;
	private String drwgBckDt;
	private String drwgBckTime;
	private String drwgBckFlowNo;
	private String status;
	private String applyCancelFlag;
	private String requestMsgId;
	private String yzSource;
	private String conferNo;
	
	
	
	
	public String getAcptId() {
		return acptId;
	}
	public void setAcptId(String acptId) {
		this.acptId = acptId;
	}
	public String getEsno() {
		return esno;
	}
	public void setEsno(String esno) {
		this.esno = esno;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getProtocalNo() {
		return protocalNo;
	}
	public void setProtocalNo(String protocalNo) {
		this.protocalNo = protocalNo;
	}
	public String getFacNo() {
		return facNo;
	}
	public void setFacNo(String facNo) {
		this.facNo = facNo;
	}
	public String getRemitter() {
		return remitter;
	}
	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}
	public String getRemitterCustNo() {
		return remitterCustNo;
	}
	public void setRemitterCustNo(String remitterCustNo) {
		this.remitterCustNo = remitterCustNo;
	}
	public String getRemitterAcct() {
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct) {
		this.remitterAcct = remitterAcct;
	}
	public String getRemitterBranchNo() {
		return remitterBranchNo;
	}
	public void setRemitterBranchNo(String remitterBranchNo) {
		this.remitterBranchNo = remitterBranchNo;
	}
	public String getRemitterBankNo() {
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo) {
		this.remitterBankNo = remitterBankNo;
	}
	public String getRemitterBankName() {
		return remitterBankName;
	}
	public void setRemitterBankName(String remitterBankName) {
		this.remitterBankName = remitterBankName;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getDraweeBankNo() {
		return draweeBankNo;
	}
	public void setDraweeBankNo(String draweeBankNo) {
		this.draweeBankNo = draweeBankNo;
	}
	public String getDraweeBankName() {
		return draweeBankName;
	}
	public void setDraweeBankName(String draweeBankName) {
		this.draweeBankName = draweeBankName;
	}
	public String getDraweeAddr() {
		return draweeAddr;
	}
	public void setDraweeAddr(String draweeAddr) {
		this.draweeAddr = draweeAddr;
	}
	public String getPayeeBankNo() {
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo) {
		this.payeeBankNo = payeeBankNo;
	}
	public String getDueDt() {
		return dueDt;
	}
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}
	public String getIssueDt() {
		return issueDt;
	}
	public void setIssueDt(String issueDt) {
		this.issueDt = issueDt;
	}
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getAcceptorAcct() {
		return acceptorAcct;
	}
	public void setAcceptorAcct(String acceptorAcct) {
		this.acceptorAcct = acceptorAcct;
	}
	public String getAcceptorBankNo() {
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo) {
		this.acceptorBankNo = acceptorBankNo;
	}
	public String getAcceptorBankName() {
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName) {
		this.acceptorBankName = acceptorBankName;
	}
	public String getColltnStatus() {
		return colltnStatus;
	}
	public void setColltnStatus(String colltnStatus) {
		this.colltnStatus = colltnStatus;
	}
	public String getColltnId() {
		return colltnId;
	}
	public void setColltnId(String colltnId) {
		this.colltnId = colltnId;
	}
	public String getSspdStatus() {
		return sspdStatus;
	}
	public void setSspdStatus(String sspdStatus) {
		this.sspdStatus = sspdStatus;
	}
	public String getSspdId() {
		return sspdId;
	}
	public void setSspdId(String sspdId) {
		this.sspdId = sspdId;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public String getPrsnttnAcctNo() {
		return prsnttnAcctNo;
	}
	public void setPrsnttnAcctNo(String prsnttnAcctNo) {
		this.prsnttnAcctNo = prsnttnAcctNo;
	}
	public String getPrsnttnName() {
		return prsnttnName;
	}
	public void setPrsnttnName(String prsnttnName) {
		this.prsnttnName = prsnttnName;
	}
	public String getPrsnttnBankNo() {
		return prsnttnBankNo;
	}
	public void setPrsnttnBankNo(String prsnttnBankNo) {
		this.prsnttnBankNo = prsnttnBankNo;
	}
	public String getPrsnttnBankName() {
		return prsnttnBankName;
	}
	public void setPrsnttnBankName(String prsnttnBankName) {
		this.prsnttnBankName = prsnttnBankName;
	}
	public String getPaymentPath() {
		return paymentPath;
	}
	public void setPaymentPath(String paymentPath) {
		this.paymentPath = paymentPath;
	}
	public String getPaymentDt() {
		return paymentDt;
	}
	public void setPaymentDt(String paymentDt) {
		this.paymentDt = paymentDt;
	}
	public String getCustRemark() {
		return custRemark;
	}
	public void setCustRemark(String custRemark) {
		this.custRemark = custRemark;
	}
	public String getBankRemark() {
		return bankRemark;
	}
	public void setBankRemark(String bankRemark) {
		this.bankRemark = bankRemark;
	}
	public String getPayWaitOrder() {
		return payWaitOrder;
	}
	public void setPayWaitOrder(String payWaitOrder) {
		this.payWaitOrder = payWaitOrder;
	}
	public String getTxlCtrctNb() {
		return txlCtrctNb;
	}
	public void setTxlCtrctNb(String txlCtrctNb) {
		this.txlCtrctNb = txlCtrctNb;
	}
	public String getInvcNb() {
		return invcNb;
	}
	public void setInvcNb(String invcNb) {
		this.invcNb = invcNb;
	}
	public String getRealAcctDt() {
		return realAcctDt;
	}
	public void setRealAcctDt(String realAcctDt) {
		this.realAcctDt = realAcctDt;
	}
	public String getRealAcctTime() {
		return realAcctTime;
	}
	public void setRealAcctTime(String realAcctTime) {
		this.realAcctTime = realAcctTime;
	}
	public String getDrwgBckOperNo() {
		return drwgBckOperNo;
	}
	public void setDrwgBckOperNo(String drwgBckOperNo) {
		this.drwgBckOperNo = drwgBckOperNo;
	}
	public String getDrwgBckDt() {
		return drwgBckDt;
	}
	public void setDrwgBckDt(String drwgBckDt) {
		this.drwgBckDt = drwgBckDt;
	}
	public String getDrwgBckTime() {
		return drwgBckTime;
	}
	public void setDrwgBckTime(String drwgBckTime) {
		this.drwgBckTime = drwgBckTime;
	}
	public String getDrwgBckFlowNo() {
		return drwgBckFlowNo;
	}
	public void setDrwgBckFlowNo(String drwgBckFlowNo) {
		this.drwgBckFlowNo = drwgBckFlowNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplyCancelFlag() {
		return applyCancelFlag;
	}
	public void setApplyCancelFlag(String applyCancelFlag) {
		this.applyCancelFlag = applyCancelFlag;
	}
	public String getRequestMsgId() {
		return requestMsgId;
	}
	public void setRequestMsgId(String requestMsgId) {
		this.requestMsgId = requestMsgId;
	}
	public String getYzSource() {
		return yzSource;
	}
	public void setYzSource(String yzSource) {
		this.yzSource = yzSource;
	}
	public String getConferNo() {
		return conferNo;
	}
	public void setConferNo(String conferNo) {
		this.conferNo = conferNo;
	}
	public String getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(String billMoney) {
		this.billMoney = billMoney;
	}
	public String getCurrencyCategory() {
		return currencyCategory;
	}
	public void setCurrencyCategory(String currencyCategory) {
		this.currencyCategory = currencyCategory;
	}
	public String getPayeeAcct() {
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct) {
		this.payeeAcct = payeeAcct;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	
	public String getPayeeBankName() {
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
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
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getAcptmxId() {
		return acptmxId;
	}
	public void setAcptmxId(String acptmxId) {
		this.acptmxId = acptmxId;
	}
	public String getRgctId() {
		return rgctId;
	}
	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	
	
}
