package com.herongtech.rc.domain.bean;

/**
 * 电票发生明细查询结果
 * 
 */
public class BillFlowQueryResult extends RgctBillInfo{
	private String rgctId;
	private String billNo;
    private String conferNo;
    private String invoiceNo;
    private String billType;
    private String billClass;
    private String issueDt;
    private String dueDt;
    private String acceptorDate;
    private double billMoney = 0.0;
	private String remitter;
    private String remitterAcct;
    private String remitterBankName;
    private String remitterBankNo;
    private String payeeName;
    private String payeeAcct;
    private String payeeBankName;
    private String payeeBankNo;
    private String acceptor;
    private String acceptorAcct;
    private String acceptorBankName;
    private String acceptorBankNo;
	private String settlementMark;//线上清算标记 （1－线上0－线下）
	private String banEndorsementMark;//禁止背书标志
	private String fromAcctNo;
	private String fromBankNo;
	private String fromName;
	private String toAcctNo;
	private String toBankNo;
	private String toName;
	private String remark;//备注
	private String status;
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
	public double getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(double billMoney) {
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
	public String getRemitterBankNo() {
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo) {
		this.remitterBankNo = remitterBankNo;
	}
	public String getPayeeAcct() {
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct) {
		this.payeeAcct = payeeAcct;
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
	public String getAcceptorBankNo() {
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo) {
		this.acceptorBankNo = acceptorBankNo;
	}
	public String getSettlementMark() {
		return settlementMark;
	}
	public void setSettlementMark(String settlementMark) {
		this.settlementMark = settlementMark;
	}
	public String getBanEndorsementMark() {
		return banEndorsementMark;
	}
	public void setBanEndorsementMark(String banEndorsementMark) {
		this.banEndorsementMark = banEndorsementMark;
	}
	public String getFromAcctNo() {
		return fromAcctNo;
	}
	public void setFromAcctNo(String fromAcctNo) {
		this.fromAcctNo = fromAcctNo;
	}
	public String getFromBankNo() {
		return fromBankNo;
	}
	public void setFromBankNo(String fromBankNo) {
		this.fromBankNo = fromBankNo;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToAcctNo() {
		return toAcctNo;
	}
	public void setToAcctNo(String toAcctNo) {
		this.toAcctNo = toAcctNo;
	}
	public String getToBankNo() {
		return toBankNo;
	}
	public void setToBankNo(String toBankNo) {
		this.toBankNo = toBankNo;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAcceptorDate() {
		return acceptorDate;
	}
	public void setAcceptorDate(String acceptorDate) {
		this.acceptorDate = acceptorDate;
	}
	public String getRemitterBankName() {
		return remitterBankName;
	}
	public void setRemitterBankName(String remitterBankName) {
		this.remitterBankName = remitterBankName;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeBankName() {
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}
	public String getAcceptorBankName() {
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName) {
		this.acceptorBankName = acceptorBankName;
	}
	
}
