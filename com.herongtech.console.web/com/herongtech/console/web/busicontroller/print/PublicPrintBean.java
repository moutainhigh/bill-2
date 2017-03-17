package com.herongtech.console.web.busicontroller.print;



public class PublicPrintBean {
	private String year;
	private String month;
	private String day;
	private int head;//标志位
	private int sequence;//序号
	private String outBillDate;//出票日
	private String endBillDate;//票面到期日
	private String outBillPerson;//出票人
	private String outBillAccount;//出票人账号
	private String outBillBank;//出票人开户行
	private String outBillBankNo;//出票人开户行行号
	private Double billAmount;//票面金额(小写)
	private String billAmountStr;
	private String billAmountBig;//票面金额(大写)
	private String acceptor;//承兑人
	private String acptAccount;//承兑人账号
	private String acptBank;//承兑人开户行
	private String acptBankNo;//承兑人开户行行号
	private String operStatus;//操作状态
	private String custAccount;//客户账号||持票人客户账号
	private String custName;//客户名称
	private String custBankName;//客户开户行名称
	private String billTypeString;//票据类型（中文）
	private String billClassString;//票据种类(中文)
	private String payee;//收款人
	private String payeeBankName;//收款人开户行
	private String payeeAccount;//收款人账号
	private String payeeAdrr;//收款人地址
	private String billNo;//票号
	private Double payMoney;//实付金额
	private String payBankName;//查询查复凭证
	private String replyContext;//查复内容
	private String repDt;//查复日期
	private Long isNomal;//查复状态 0:不正常1:正常
	private String billMoney;//没科学记数法
	private String payMoneyString;
	private String reMark;
	private String draftLog;//报文日志
	private String belongDept;//部门归属
	private String rateDt;//计息到期日
	private Long canculeRateDay;//计息天数
	private Double Interest;//贴现利息
	public String getBackRate() {
		return backRate;
	}
	public void setBackRate(String backRate) {
		this.backRate = backRate;
	}
	private String ifDelay;//是否逾期
	private String proName;//票据产品
	private String custMagName;//客户经理
	private String impwnDt;//质押入库日期
	private String conferNo;//交易合同号
	private String chuPiaoGuartrName;//出票保证人名称
	private String chuPiaoGuartrAddr;//出票保证人地址
	private String chuPiaoWarteeDt;//出票保证日期
	private String acctGuartrName;//承兑保证人名称
	private String acctGuartrAddr;//承兑保证人地址
	private String acctWarteeDt;//承兑保证日期
	private String backRate;//赎回利率
	
	private String acctcollOpinion;//托收意见
	private String acctcollReason;//托收原因
	private String acctcollAccount;//托收方账号
	private String acctcollBank;	// 托收方开户行
	private String acctcollBankNo;//托收方开户行号
	private String onlinemark;//清算方式
	private String receiveDt;//接收托收日期
	private String collRemark;//托收备注，财务公司会使用
	private String overDurRs;//逾期原因说明
	private String moneyFlag;//人民币符合
	private String status;//票据状态
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMoneyFlag() {
		return moneyFlag;
	}
	public void setMoneyFlag(String moneyFlag) {
		this.moneyFlag = moneyFlag;
	}
	/**
	 * @return the collRemark
	 */
	public String getCollRemark() {
		return collRemark;
	}
	/**
	 * @param collRemark the collRemark to set
	 */
	public void setCollRemark(String collRemark) {
		this.collRemark = collRemark;
	}
	/**
	 * @return the receiveDt
	 */
	public String getReceiveDt() {
		return receiveDt;
	}
	/**
	 * @param receiveDt the receiveDt to set
	 */
	public void setReceiveDt(String receiveDt) {
		this.receiveDt = receiveDt;
	}
	/**
	 * @return the acctcollOpinion
	 */
	public String getAcctcollOpinion() {
		return acctcollOpinion;
	}
	/**
	 * @param acctcollOpinion the acctcollOpinion to set
	 */
	public void setAcctcollOpinion(String acctcollOpinion) {
		this.acctcollOpinion = acctcollOpinion;
	}
	/**
	 * @return the acctcollReason
	 */
	public String getAcctcollReason() {
		return acctcollReason;
	}
	/**
	 * @param acctcollReason the acctcollReason to set
	 */
	public void setAcctcollReason(String acctcollReason) {
		this.acctcollReason = acctcollReason;
	}
	/**
	 * @return the acctcollAccount
	 */
	public String getAcctcollAccount() {
		return acctcollAccount;
	}
	/**
	 * @param acctcollAccount the acctcollAccount to set
	 */
	public void setAcctcollAccount(String acctcollAccount) {
		this.acctcollAccount = acctcollAccount;
	}
	/**
	 * @return the acctcollBank
	 */
	public String getAcctcollBank() {
		return acctcollBank;
	}
	/**
	 * @param acctcollBank the acctcollBank to set
	 */
	public void setAcctcollBank(String acctcollBank) {
		this.acctcollBank = acctcollBank;
	}
	/**
	 * @return the acctcollBankNo
	 */
	public String getAcctcollBankNo() {
		return acctcollBankNo;
	}
	/**
	 * @param acctcollBankNo the acctcollBankNo to set
	 */
	public void setAcctcollBankNo(String acctcollBankNo) {
		this.acctcollBankNo = acctcollBankNo;
	}
	/**
	 * @return the onlinemark
	 */
	public String getOnlinemark() {
		return onlinemark;
	}
	/**
	 * @param onlinemark the onlinemark to set
	 */
	public void setOnlinemark(String onlinemark) {
		this.onlinemark = onlinemark;
	}
	public String getConferNo() {
		return conferNo;
	}
	public void setConferNo(String conferNo) {
		this.conferNo = conferNo;
	}
	public String getReMark() {
		return reMark;
	}
	public void setReMark(String reMark) {
		this.reMark = reMark;
	}
	public String getPayMoneyString() {
		return payMoneyString;
	}
	public void setPayMoneyString(String payMoneyString) {
		this.payMoneyString = payMoneyString;
	}
	public String getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(String billMoney) {
		this.billMoney = billMoney;
	}
	public Long getIsNomal() {
		return isNomal;
	}
	public void setIsNomal(Long isNomal) {
		this.isNomal = isNomal;
	}
	public String getRepDt() {
		return repDt;
	}
	public void setRepDt(String repDt) {
		this.repDt = repDt;
	}
	public String getReplyContext() {
		return replyContext;
	}
	public void setReplyContext(String replyContext) {
		this.replyContext = replyContext;
	}
	public String getPayBankName() {
		return payBankName;
	}
	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
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
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
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
	public String getOutBillPerson() {
		return outBillPerson;
	}
	public void setOutBillPerson(String outBillPerson) {
		this.outBillPerson = outBillPerson;
	}
	public String getOutBillAccount() {
		return outBillAccount;
	}
	public void setOutBillAccount(String outBillAccount) {
		this.outBillAccount = outBillAccount;
	}
	public String getOutBillBank() {
		return outBillBank;
	}
	public void setOutBillBank(String outBillBank) {
		this.outBillBank = outBillBank;
	}
	public String getOutBillBankNo() {
		return outBillBankNo;
	}
	public void setOutBillBankNo(String outBillBankNo) {
		this.outBillBankNo = outBillBankNo;
	}
	public Double getBillAmount() {
		return billAmount;
	}
	
	public String getBillAmountString(){
//		return CommonUtil.getAccountantMoney(getBillAmount(), 2);;
		return String.valueOf(billAmount);
	}
	
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getOperStatus() {
		return operStatus;
	}
	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}
	public String getBillTypeString() {
		return billTypeString;
	}
	public void setBillTypeString(String billTypeString) {
		this.billTypeString = billTypeString;
	}
	public String getBillClassString() {
		return billClassString;
	}
	public void setBillClassString(String billClassString) {
		this.billClassString = billClassString;
	}
	public String getAcptBank() {
		return acptBank;
	}
	public void setAcptBank(String acptBank) {
		this.acptBank = acptBank;
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
	public String getPayeeAccount() {
		return payeeAccount;
	}
	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}
	public String getAcptAccount() {
		return acptAccount;
	}
	public void setAcptAccount(String acptAccount) {
		this.acptAccount = acptAccount;
	}
	public String getCustAccount() {
		return custAccount;
	}
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustBankName() {
		return custBankName;
	}
	public void setCustBankName(String custBankName) {
		this.custBankName = custBankName;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
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
	public String getPayeeAdrr() {
		return payeeAdrr;
	}
	public void setPayeeAdrr(String payeeAdrr) {
		this.payeeAdrr = payeeAdrr;
	}
	public String getImpwnDt() {
		return impwnDt;
	}
	public void setImpwnDt(String impwnDt) {
		this.impwnDt = impwnDt;
	}
	public String getAcptBankNo() {
		return acptBankNo;
	}
	public void setAcptBankNo(String acptBankNo) {
		this.acptBankNo = acptBankNo;
	}
	public String getChuPiaoGuartrName() {
		return chuPiaoGuartrName;
	}
	public void setChuPiaoGuartrName(String chuPiaoGuartrName) {
		this.chuPiaoGuartrName = chuPiaoGuartrName;
	}
	public String getChuPiaoGuartrAddr() {
		return chuPiaoGuartrAddr;
	}
	public void setChuPiaoGuartrAddr(String chuPiaoGuartrAddr) {
		this.chuPiaoGuartrAddr = chuPiaoGuartrAddr;
	}
	public String getChuPiaoWarteeDt() {
		return chuPiaoWarteeDt;
	}
	public void setChuPiaoWarteeDt(String chuPiaoWarteeDt) {
		this.chuPiaoWarteeDt = chuPiaoWarteeDt;
	}
	public String getAcctGuartrName() {
		return acctGuartrName;
	}
	public void setAcctGuartrName(String acctGuartrName) {
		this.acctGuartrName = acctGuartrName;
	}
	public String getAcctGuartrAddr() {
		return acctGuartrAddr;
	}
	public void setAcctGuartrAddr(String acctGuartrAddr) {
		this.acctGuartrAddr = acctGuartrAddr;
	}
	public String getAcctWarteeDt() {
		return acctWarteeDt;
	}
	public void setAcctWarteeDt(String acctWarteeDt) {
		this.acctWarteeDt = acctWarteeDt;
	}
	public String getDraftLog() {
		return draftLog;
	}
	public void setDraftLog(String draftLog) {
		this.draftLog = draftLog;
	}
	public String getBelongDept() {
		return belongDept;
	}
	public void setBelongDept(String belongDept) {
		this.belongDept = belongDept;
	}
	public String getRateDt() {
		return rateDt;
	}
	public void setRateDt(String rateDt) {
		this.rateDt = rateDt;
	}
	public Long getCanculeRateDay() {
		return canculeRateDay;
	}
	public void setCanculeRateDay(Long canculeRateDay) {
		this.canculeRateDay = canculeRateDay;
	}
	public Double getInterest() {
		return Interest;
	}
	public void setInterest(Double interest) {
		Interest = interest;
	}
	public String getIfDelay() {
		return ifDelay;
	}
	public void setIfDelay(String ifDelay) {
		this.ifDelay = ifDelay;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCustMagName() {
		return custMagName;
	}
	public void setCustMagName(String custMagName) {
		this.custMagName = custMagName;
	}
	
	public String getOverDurRs() {
		return overDurRs;
	}
	public void setOverDurRs(String overDurRs) {
		this.overDurRs = overDurRs;
	}
	public String getBillAmountStr() {
		return billAmountStr;
	}
	public void setBillAmountStr(String billAmountStr) {
		this.billAmountStr = billAmountStr;
	}
}
