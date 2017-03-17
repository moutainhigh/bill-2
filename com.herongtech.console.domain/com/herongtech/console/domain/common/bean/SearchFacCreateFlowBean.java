package com.herongtech.console.domain.common.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

public class SearchFacCreateFlowBean extends BaseSearchBean {
	private String id;
	private String billId;//票据ID
	private String loanRequisition;//出账申请书号(唯一值)
	private String rgctId;//登记中心ID
	private String billNo;//票号
	private Double billMoney;// 票面金额
	private String source;//业务类型（1,贴现，2,转贴现 3,质押 4,票据池）
	private String outBillBankNo;//承兑行行号
	private String billType;//票据类型
	private String billClass;//票据种类
	private String aimBrchNo;//票据对手行行号
	private String createTime;// 创建时间
	private String status;	//状态 (0,额度扣减直接占用成功  1，释放  2,预扣成功 3,直接占用不成功 4,预扣恢复成功)
	private String facType;//额度种类  01-第三方额度  02-客户贴现额度  03-贴查同步额度  04-单笔单批额度 
	private String custNo;//额度客户号
	private String facilityCode;//单笔单批额度编号
	private String dueDt;//票面到期日
	private String acceptor;//承兑人
	private String productId;//额度品种
	private String ownerPartyId;//所属客户
	private String isCyc;//是否可循环 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getLoanRequisition() {
		return loanRequisition;
	}
	public void setLoanRequisition(String loanRequisition) {
		this.loanRequisition = loanRequisition;
	}
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
	public Double getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(Double billMoney) {
		this.billMoney = billMoney;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOutBillBankNo() {
		return outBillBankNo;
	}
	public void setOutBillBankNo(String outBillBankNo) {
		this.outBillBankNo = outBillBankNo;
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
	public String getAimBrchNo() {
		return aimBrchNo;
	}
	public void setAimBrchNo(String aimBrchNo) {
		this.aimBrchNo = aimBrchNo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFacType() {
		return facType;
	}
	public void setFacType(String facType) {
		this.facType = facType;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getFacilityCode() {
		return facilityCode;
	}
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}
	public String getDueDt() {
		return dueDt;
	}
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
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
}
