/********************************************
* 文件名称: EcdsBillBean.java
* 系统名称: 合融基础技术平台V3.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.bean;

public class EcdsBillBean {
	/**
	 * 网银接口清单编辑BEAN （清单编辑 新增 修改）
	 * @String   2011-11-14
	 */
	private String custAccount;//操作人账号
	private String billType;//票据类型（1－银票2－商票）
	private String billClass;//票据种类（1：实物 2：电子）
	private String issueDt ;//出票日
	private String dueDt;//票面到期日
	private String acceptDt;//承兑日期
	private String billNo;//票据号码
	private String billMoney;//票面金额
	private String remitter;//出票人名称
	private String remitterAcct;//出票人账号
	private String remitterBank;//出票人开户行
	private String remitterBankNo;//出票人开户行行号
	private String payee;//收款人名称
	private String payeeAcct;//收款人账号
	private String payeeBank;//收款人开户行
	private String payeeBankNo ;//收款人开户行行号
	private String payeeOrgCode ;//收款人组织机构代码
	private String acceptor;//承兑人名称
	private String acceptorAcct;//承兑人账号
	private String acceptorBank ;//承兑人开户行
	private String acceptorBankNo;//承兑人开户行行号
	private String banEndorsementMark ;//禁止背书标记 （0：不禁止1:禁止）
	private String conferNo ;//合同文本号
	private String invoiceNo ;//发票号
	private String rgctId;//登记中心ID
	private String remark;//备注
	private String remitterOrgCode;//出票人组织机构代码
	private String remitterCreditAgency; //出票人评级主体
	private String remitterCreditClass; //出票人信用等级
	private String remitterCreditDueDt; //出票人评级到期日
	private String acceptorOrgCode;//承兑人组织机构代码
	private String acceptorCreditAgency; //承兑人评级主体
	private String acceptorCreditClass; //承兑人信用等级
	private String acceptorCreditDueDt; //承兑人评级到期日
	private String curStatus;//当前票据状态
	private String curStatusName;//状态名称
	private String billBeforeOwner;//票据前手
	private String operator;//操作柜员
	
	public String getBillBeforeOwner() {
		return billBeforeOwner;
	}
	public void setBillBeforeOwner(String billBeforeOwner) {
		this.billBeforeOwner = billBeforeOwner;
	}
	public String getCurStatusName() {
		return curStatusName;
	}
	public void setCurStatusName(String curStatusName) {
		this.curStatusName = curStatusName;
	}
	public String getCurStatus() {
		return curStatus;
	}
	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getAcceptDt() {
		return acceptDt;
	}
	public void setAcceptDt(String acceptDt) {
		this.acceptDt = acceptDt;
	}
	public String getDueDt() {
		return dueDt;
	}
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}
	public String getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(String billMoney) {
		this.billMoney = billMoney;
	}
	
	public String getRemitterBank() {
		return remitterBank;
	}
	public void setRemitterBank(String remitterBank) {
		this.remitterBank = remitterBank;
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
	
	public String getAcceptorAcct() {
		return acceptorAcct;
	}
	public void setAcceptorAcct(String acceptorAcct) {
		this.acceptorAcct = acceptorAcct;
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

	public String getCustAccount() {
		return custAccount;
	}

	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

	public String getRgctId() {
		return rgctId;
	}

	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	public String getBanEndorsementMark() {
		return banEndorsementMark;
	}
	public void setBanEndorsementMark(String banEndorsementMark) {
		this.banEndorsementMark = banEndorsementMark;
	}
	public String getRemitterOrgCode() {
		return remitterOrgCode;
	}
	public void setRemitterOrgCode(String remitterOrgCode) {
		this.remitterOrgCode = remitterOrgCode;
	}
	public String getAcceptorOrgCode() {
		return acceptorOrgCode;
	}
	public void setAcceptorOrgCode(String acceptorOrgCode) {
		this.acceptorOrgCode = acceptorOrgCode;
	}
	public String getRemitterCreditAgency() {
		return remitterCreditAgency;
	}
	public void setRemitterCreditAgency(String remitterCreditAgency) {
		this.remitterCreditAgency = remitterCreditAgency;
	}
	public String getRemitterCreditClass() {
		return remitterCreditClass;
	}
	public void setRemitterCreditClass(String remitterCreditClass) {
		this.remitterCreditClass = remitterCreditClass;
	}
	public String getRemitterCreditDueDt() {
		return remitterCreditDueDt;
	}
	public void setRemitterCreditDueDt(String remitterCreditDueDt) {
		this.remitterCreditDueDt = remitterCreditDueDt;
	}
	public String getAcceptorCreditAgency() {
		return acceptorCreditAgency;
	}
	public void setAcceptorCreditAgency(String acceptorCreditAgency) {
		this.acceptorCreditAgency = acceptorCreditAgency;
	}
	public String getAcceptorCreditClass() {
		return acceptorCreditClass;
	}
	public void setAcceptorCreditClass(String acceptorCreditClass) {
		this.acceptorCreditClass = acceptorCreditClass;
	}
	public String getAcceptorCreditDueDt() {
		return acceptorCreditDueDt;
	}
	public void setAcceptorCreditDueDt(String acceptorCreditDueDt) {
		this.acceptorCreditDueDt = acceptorCreditDueDt;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getPayeeOrgCode() {
		return payeeOrgCode;
	}
	public void setPayeeOrgCode(String payeeOrgCode) {
		this.payeeOrgCode = payeeOrgCode;
	}
	
}
