package com.herongtech.online.trans.trans105001;


public class Var105001InfoBean {
	private String rgctId;
	private String billNo;
	private String conferNo;//交易合同编号
	private String invoiceNo;//发票号码
	private String billType;
	private String billClass;
	
	private String issueDt;//出票日
	private String dueDt;
	private String billMoney;
	private String acceptDt;//承兑日期
	private String acceptor;
	private String acceptorAcct;
	private String acceptorBank;
	private String acceptorBankNo;
	
	private String remitter;//出票人全称
	private String remitterAcct;
	private String remitterBank;
	private String remitterBankNo;
	private String payee;//收款人
	private String payeeAcct;
	private String payeeBank;
	private String payeeBankNo;
	private String banEndorsementMark;//禁止背书标志
	private String billBeforeOwner;
	public String getRgctId() {
		return rgctId;
	}
	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getConferNo() {
		return conferNo;
	}
	public void setConferNo(String conferNo) {
		this.conferNo = conferNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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
	public String getIssueDt() {
		return issueDt;
	}
	public void setIssueDt(String issueDt) {
		this.issueDt = issueDt;
	}
	public String getDueDt() {
		return dueDt;
	}
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}
	public String getAcceptDt() {
		return acceptDt;
	}
	public void setAcceptDt(String acceptDt) {
		this.acceptDt = acceptDt;
	}
	public String getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(String billMoney) {
		this.billMoney = billMoney;
	}
	public String getRemitter() {
		return remitter;
	}
	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}
	public String getRemitterAcct() {
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct) {
		this.remitterAcct = remitterAcct;
	}
	public String getRemitterBank() {
		return remitterBank;
	}
	public void setRemitterBank(String remitterBank) {
		this.remitterBank = remitterBank;
	}
	public String getRemitterBankNo() {
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo) {
		this.remitterBankNo = remitterBankNo;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getPayeeAcct() {
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct) {
		this.payeeAcct = payeeAcct;
	}
	public String getPayeeBank() {
		return payeeBank;
	}
	public void setPayeeBank(String payeeBank) {
		this.payeeBank = payeeBank;
	}
	public String getPayeeBankNo() {
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo) {
		this.payeeBankNo = payeeBankNo;
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
	public String getAcceptorBank() {
		return acceptorBank;
	}
	public void setAcceptorBank(String acceptorBank) {
		this.acceptorBank = acceptorBank;
	}
	public String getAcceptorBankNo() {
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo) {
		this.acceptorBankNo = acceptorBankNo;
	}
	public String getBanEndorsementMark() {
		return banEndorsementMark;
	}
	public void setBanEndorsementMark(String banEndorsementMark) {
		this.banEndorsementMark = banEndorsementMark;
	}
	public String getBillBeforeOwner() {
		return billBeforeOwner;
	}
	public void setBillBeforeOwner(String billBeforeOwner) {
		this.billBeforeOwner = billBeforeOwner;
	}
	
	
}