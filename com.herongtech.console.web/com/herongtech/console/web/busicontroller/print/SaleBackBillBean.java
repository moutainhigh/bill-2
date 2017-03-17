package com.herongtech.console.web.busicontroller.print;

/**
 * @author ljtmq
 * 返售打印类
 */
public class SaleBackBillBean {

	private String billNo;//票号
	private String outBillDate;//出票日
	private String endBillDate;//票面到期日
	private String galeDate;//计息到期日
	
	private Long interestCalDays;//计息天数
	private String acceptor;//承兑人
	
	private Double billAmount;//票面金额
	private String billAmountString;
	
	private Double interest;//利息
	private String interestString;
	
	private Double salebackMoney;//实收金额
	private String salebackMoneyString;
	
	private int sequence;// 序号
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getOutBillDate() {
		return outBillDate;
	}
	public void setOutBillDate(String outBillDate) {
		this.outBillDate = outBillDate;
	}
	public String getEndBillDate() {
		return endBillDate;
	}
	public void setEndBillDate(String endBillDate) {
		this.endBillDate = endBillDate;
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
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public Double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	public String getBillAmountString() {
		return billAmountString;
	}
	public void setBillAmountString(String billAmountString) {
		this.billAmountString = billAmountString;
	}
	public Double getInterest() {
		return interest;
	}
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	public String getInterestString() {
		return interestString;
	}
	public void setInterestString(String interestString) {
		this.interestString = interestString;
	}
	public Double getSalebackMoney() {
		return salebackMoney;
	}
	public void setSalebackMoney(Double salebackMoney) {
		this.salebackMoney = salebackMoney;
	}
	public String getSalebackMoneyString() {
		return salebackMoneyString;
	}
	public void setSalebackMoneyString(String salebackMoneyString) {
		this.salebackMoneyString = salebackMoneyString;
	}
	
}
