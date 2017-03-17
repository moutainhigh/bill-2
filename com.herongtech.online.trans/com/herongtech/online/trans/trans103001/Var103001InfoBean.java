package com.herongtech.online.trans.trans103001;

import java.util.List;

import com.herongtech.rc.domain.bean.RgctBill;


public class Var103001InfoBean {
	private List<RgctBill> rgctBill;
	private  String rgctId;
	private  String result;
	private  String message;
	private  String billNo;
	private  String conferNo;
	private  String invoiceNo;
	private  String billType;
	private  String billClass;
	private  String issueDt;
	private  String dueDt;
	private  String acceptDt;
	private  String billMoney;
	private  String remitter;
	private  String remitterAcct;
	private  String remitterBank;
	private  String remitterBankNo;
	private  String payee;
	private  String payeeAcct;
	private  String payeeBank;
	private  String payeeBankNo;
	private  String acceptor;
	private  String acceptorAcct;
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

	public void setBillMoney(String d) {
		this.billMoney = d;
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

	String acceptorBank;
    String acceptorBankNo;
    String banEndorsementMark;
    
    
    public String getRgctId() {
        return rgctId;
    }
    
    public void setRgctId(String rgctId) {
        this.rgctId = rgctId;
    }
    
    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

	public List<RgctBill> getRgctBill() {
		return rgctBill;
	}

	public void setRgctBill(List<RgctBill> rgctBill) {
		this.rgctBill = rgctBill;
	}

}
