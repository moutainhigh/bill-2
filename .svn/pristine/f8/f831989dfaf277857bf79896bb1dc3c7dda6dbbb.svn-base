/********************************************
* 文件名称: RcBaseSearchBean.java
* 系统名称: 合融基础技术平台V2.0
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

package com.herongtech.rc.domain.bean;

import java.util.Date;
import java.util.List;

import com.herongtech.console.core.common.search.BaseSearchBean;

public class RcBaseSearchBean extends BaseSearchBean{

	/**
	 * 票据分类：实物、电子
	 */
	private String billClass;
	/**
	 * 票据类型：商票、银票
	 */
	private String billType;
	/**
	 * 票号
	 */
	private String billNo;
	/**
	 * 是否我行承兑
	 */
	private String isAccp;
	/**
	 * 到期日
	 */
	private String dueDt;
	/**
	 * 最小到期日
	 */
	private String minDueDt;
	/**
	 * 最大到期日
	 */
	private String maxDueDt;
	
	/**
	 * 票面金额
	 */
	private Double billMoney;
	/**
	 * 最小票面金额
	 */
	private Double minBillMoney;
	/**
	 * 最大票面金额
	 */
	private Double maxBillMoney;
	
	//票据持有人账号
	private String holdAcctNo;
	//背书人账号
	private String fromAcctNo;
	//被背书人账号
	private String toAcctNo;
	//被背书人行号
	private String toBankNo;
	//是否锁定
	private String isLock;
	//账务机构号
	private String acctBranchNo;
	//存放机构号
	private String storageBranchNo;
	
	//承兑行行号
	private String acceptorBankNo;
	//承兑人开户行行名
	private String acceptorBankName;
	public String getAcceptorBankName() {
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName) {
		this.acceptorBankName = acceptorBankName;
	}
	//票据状态
	private String billStatus;
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	//承兑人
	private String acceptor;
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	//票据持有人行号
	private String holdBankNo;
	
	public String getHoldBankNo() {
		return holdBankNo;
	}
	public void setHoldBankNo(String holdBankNo) {
		this.holdBankNo = holdBankNo;
	}
	public String getAcceptorBankNo(){
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo){
		this.acceptorBankNo = acceptorBankNo;
	}
	private String balaceType;      //余额类型
	//保证相关
	private String warteeCustNo;	//被保证人客户号	
	private String warteeSign;		//被保证人电子签名
	private String warteeAcctNo;	//被保证人客户号
	private String warteeBankNo;	//被保证人开户行行号
	private String warteeOrgCode;   //被保证人组织机构代码
	
	private String agcyBankNo;		//承接行行号
	private String guarntrCustNo;	//保证人客户号
	private String guarntrAcctNo;	//保证人帐号	
	private String guarntrBankNo;	//保证人开户行行号
	private String assuStatus;		//保证状态
	private String assureType;		//保证类型
	
	private String rgctId;		//票据rcid
	
	
	public String getRgctId() {
		return rgctId;
	}
	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	public String getMinDueDt() {
		return minDueDt;
	}
	public void setMinDueDt(String minDueDt) {
		this.minDueDt = minDueDt;
	}
	public String getMaxDueDt() {
		return maxDueDt;
	}
	public void setMaxDueDt(String maxDueDt) {
		this.maxDueDt = maxDueDt;
	}
	public Double getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(Double billMoney) {
		this.billMoney = billMoney;
	}
	public Double getMinBillMoney() {
		return minBillMoney;
	}
	public void setMinBillMoney(Double minBillMoney) {
		this.minBillMoney = minBillMoney;
	}
	public Double getMaxBillMoney() {
		return maxBillMoney;
	}
	public void setMaxBillMoney(Double maxBillMoney) {
		this.maxBillMoney = maxBillMoney;
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
	public String getIsAccp() {
		return isAccp;
	}
	public void setIsAccp(String isAccp) {
		this.isAccp = isAccp;
	}
	public String getDueDt() {
		return dueDt;
	}
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}
	public String getHoldAcctNo() {
		return holdAcctNo;
	}
	public void setHoldAcctNo(String holdAcctNo) {
		this.holdAcctNo = holdAcctNo;
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
	public String getIsLock() {
		return isLock;
	}
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}
	public String getAcctBranchNo() {
		return acctBranchNo;
	}
	public void setAcctBranchNo(String acctBranchNo) {
		this.acctBranchNo = acctBranchNo;
	}
	public String getStorageBranchNo() {
		return storageBranchNo;
	}
	public void setStorageBranchNo(String storageBranchNo) {
		this.storageBranchNo = storageBranchNo;
	}
	public void setWarteeCustNo(String warteeCustNo) {
		this.warteeCustNo = warteeCustNo;
	}
	public String getWarteeCustNo() {
		return warteeCustNo;
	}
	public void setWarteeSign(String warteeSign) {
		this.warteeSign = warteeSign;
	}
	public String getWarteeSign() {
		return warteeSign;
	}
	public void setWarteeAcctNo(String warteeAcctNo) {
		this.warteeAcctNo = warteeAcctNo;
	}
	public String getWarteeAcctNo() {
		return warteeAcctNo;
	}
	public void setWarteeBankNo(String warteeBankNo) {
		this.warteeBankNo = warteeBankNo;
	}
	public String getWarteeBankNo() {
		return warteeBankNo;
	}
	public void setWarteeOrgCode(String warteeOrgCode) {
		this.warteeOrgCode = warteeOrgCode;
	}
	public String getWarteeOrgCode() {
		return warteeOrgCode;
	}
	public void setAgcyBankNo(String agcyBankNo) {
		this.agcyBankNo = agcyBankNo;
	}
	public String getAgcyBankNo() {
		return agcyBankNo;
	}
	public void setGuarntrCustNo(String guarntrCustNo) {
		this.guarntrCustNo = guarntrCustNo;
	}
	public String getGuarntrCustNo() {
		return guarntrCustNo;
	}
	public void setGuarntrAcctNo(String guarntrAcctNo) {
		this.guarntrAcctNo = guarntrAcctNo;
	}
	public String getGuarntrAcctNo() {
		return guarntrAcctNo;
	}
	public void setGuarntrBankNo(String guarntrBankNo) {
		this.guarntrBankNo = guarntrBankNo;
	}
	public String getGuarntrBankNo() {
		return guarntrBankNo;
	}
	public void setAssuStatus(String assuStatus) {
		this.assuStatus = assuStatus;
	}
	public String getAssuStatus() {
		return assuStatus;
	}
	public void setFromAcctNo(String fromAcctNo) {
		this.fromAcctNo = fromAcctNo;
	}
	public String getFromAcctNo() {
		return fromAcctNo;
	}
	public void setAssureType(String assureType) {
		this.assureType = assureType;
	}
	public String getAssureType() {
		return assureType;
	}
	public String getBalaceType() {
		return balaceType;
	}
	public void setBalaceType(String balaceType) {
		this.balaceType = balaceType;
	}
	
	
}
