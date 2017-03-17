package com.herongtech.console.web.busicontroller.print;


/**
 * 转入申请打印所需类
 * @author Administrator
 *
 */
public class RebuyBean {
	private String outBillPerson;//出票人
	private String endBillDate;//到期日
	private String outBillDate;//出票日
	private String billClass;
	private String billType;
	private String billTypeString;//票据种类
	private String billNo;//票号
	
	private Double billAmount;
	private String billAmountBig;//大写票面金额
	private String billAmountString;//小写票面金额
	
	private String acceptor;//承兑人
	private String outBillBank;//出票人开户行
	private String acptBank;//承兑人开户行
	private String outBillAccount;//承兑人帐号
	
	
	
	private Double interest;//贴现利息
	private String interestString;//贴现利息
	private Double payMoney;//实付金额
	private String payMoneyString;//实付金额
	
	private String applicant;//申请人（交易对手）
	private String applicantAccount;//申请人账号（交易对手账号）
	private String applicantBankName;//申请人开户行名称（交易对手）
	
	private String discRate;//贴现利率
	private int sequence;//序号
	private String galeDate;//计息到期日
	private Long interestCalDays;//计息天数
	
	private String year;
	private String month;
	private String day;
	
	private String moneyFlag;//￥
	private String invMoneyFlag;
	private String payMoneyFlag;
	private String elecDepositString;
	
	
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
	public String getInvMoneyFlag() {
		return invMoneyFlag;
	}
	public void setInvMoneyFlag(String invMoneyFlag) {
		this.invMoneyFlag = invMoneyFlag;
	}
	public String getPayMoneyFlag() {
		return payMoneyFlag;
	}
	public void setPayMoneyFlag(String payMoneyFlag) {
		this.payMoneyFlag = payMoneyFlag;
	}
	public String getMoneyFlag() {
		return moneyFlag;
	}
	public void setMoneyFlag(String moneyFlag) {
		this.moneyFlag = moneyFlag;
	}
	public String getEndBillDate() {
		return endBillDate;
	}
	public void setEndBillDate(String endBillDate) {
		this.endBillDate = endBillDate;
	}
	public String getOutBillDate() {
		return outBillDate;
	}
	public void setOutBillDate(String outBillDate) {
		this.outBillDate = outBillDate;
	}
	
	public String getBillTypeString() {
		return billTypeString;
	}
	public void setBillTypeString(String billTypeString) {
		this.billTypeString = billTypeString;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillAmountBig() {
		return billAmountBig;
	}
	public void setBillAmountBig(String billAmountBig) {
		this.billAmountBig = billAmountBig;
	}
	 
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getBillAmountString() {
		return billAmountString;
	}
	public void setBillAmountString(String billAmountString) {
		this.billAmountString = billAmountString;
	}
	public String getOutBillBank() {
		return outBillBank;
	}
	public void setOutBillBank(String outBillBank) {
		this.outBillBank = outBillBank;
	}
	public String getOutBillAccount() {
		return outBillAccount;
	}
	public void setOutBillAccount(String outBillAccount) {
		this.outBillAccount = outBillAccount;
	}
	public String getPayMoneyString() {
		return payMoneyString;
	}
	public void setPayMoneyString(String payMoneyString) {
		this.payMoneyString = payMoneyString;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getOutBillPerson() {
		return outBillPerson;
	}
	public void setOutBillPerson(String outBillPerson) {
		this.outBillPerson = outBillPerson;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getGaleDate() {
		return galeDate;
	}
	public void setGaleDate(String galeDate) {
		this.galeDate = galeDate;
	}
	public Long getInterestCalDays() {
		return interestCalDays;
	}
	public void setInterestCalDays(Long interestCalDays) {
		this.interestCalDays = interestCalDays;
	}
	public String getAcptBank() {
		return acptBank;
	}
	public void setAcptBank(String acptBank) {
		this.acptBank = acptBank;
	}
	public String getDiscRate() {
		return discRate;
	}
	public void setDiscRate(String discRate) {
		this.discRate = discRate;
	}
	public Double getBillAmount() {
		return billAmount == null ? 0 :  billAmount;
	}
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	public String getInterestString() {
		return interestString;
	}
	public void setInterestString(String interestString) {
		this.interestString = interestString;
	}
	public Double getInterest() {
		return interest;
	}
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getApplicantAccount() {
		return applicantAccount;
	}
	public void setApplicantAccount(String applicantAccount) {
		this.applicantAccount = applicantAccount;
	}
	public String getApplicantBankName() {
		return applicantBankName;
	}
	public void setApplicantBankName(String applicantBankName) {
		this.applicantBankName = applicantBankName;
	}
	public String getElecDepositString() {
		return elecDepositString;
	}
	public void setElecDepositString(String elecDepositString) {
		this.elecDepositString = elecDepositString;
	}
	

}
