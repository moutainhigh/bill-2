/********************************************
* 文件名称: AssuBillInfo.java
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
package com.herongtech.console.domain.assu.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class AssuBillInfo{
	private String assumxId = " ";	//保证明细流水号
	private String assuId = " ";	//保证批次id
	private String rgctId = " ";	//登记中心id
	private String rgctHistId = " ";	//登记中心历史id
	private String warteeDt = " ";	//被保证日期
	private String warteeTime = " ";	//被保证时间
	private String warteeCustNo = " ";	//被保证人客户号
	private String warteeSign = " ";	//被保证人签名
	private String warteeAcctNo = " ";	//被保证人账号
	private String warteeBankNo = " ";	//被保证人开户行行号
	private String guartrOrgcode = " ";	//保证人组织机构代码
	private String warteeAcptBankNo = " ";	//被保证人承接行行号
	private String guartrName = " ";	//保证人
	private String guarntrCustNo = " ";	//保证人客户号
	private String guarntrAcctNo = " ";	//保证人账号
	private String guartrBankNo = " ";	//保证人开户行行号
	private String warteeOrgcode = " ";	//被保证人组织机构代码
	private String assuStatus = " ";	//保证状态
	private String isAccpt = " ";	//是否我行签收
	private String assuType = " ";	//保证类型
	private String guartrAddr = " ";	//保证人地址
	private String warteeCustName = " ";	//被保证人客户姓名
	private String guartrPartnerType = " ";	//保证人参与者类别
	private String warteePartnerType = " ";	//被保证人参与者类别
	private String accountDate = " ";	//记账日期
	private String accountTime = " ";	//记账时间
	private String acctStatus = " ";	//记账状态
	private String acctOperNo = " ";	//记账柜员
	private String acceptorBankName = " ";	//承兑人行名
	private String acceptorBankNo = " ";	//承兑人行号
	private String acceptor = " ";	//承兑人
	private String adjustFlag = " ";	//调整标记
	private String auditDate = " ";	//复核日期
	private String auditTime = " ";	//复核时间
	private String auditOperNo = " ";	//复核柜员
	private Double billMoney;	//票面金额
	private String billClass = " ";	//票据分类
	private String billNo = " ";	//票号
	private String billType = " ";	//票据种类
	private String branchNo = " ";	//交易机构
	private String checkOperNo = " ";	//审核柜员
	private String dueDt = " ";	//到期日
	private Double fee;	//手续费金额
	private Double finalFee;	//调整后手续金额
	private String exSerial = " ";	//记账前台流水号
	private Double impawnMoney;	//保证金金额
	private String loanNo = " ";	//贷款合同号
	private String issueDt = " ";	//出票日
	private String payee = " ";	//收款人名称
	private String payeeBankName = " ";	//收款人行名
	private String payeeBankNo = " ";	//收款人开户行行号
	private Double rate;	//利率
	private String remitter = " ";	//出票人
	private String remitterBankName = " ";	//出票人行名
	private String remitterBankNo = " ";	//出票人行号
	private Double validAmount;	//有效余额
	private Double validQuarter;	//算费季度数
	private String operStatus = " ";	//操作状态
	private String remitterAcct = " ";	//出票人账号
	private String payeeAcct = " ";	//收款人账号
	private String draweeAddr = " ";	//付款人开户行地址
	private String applyOperNo = " ";	//申请柜员号
	private String deadlineOperNo = " ";	//到期申请岗
	private String ifFeeCharging = " ";	//是否费用收取
	private String deadApplyId = " ";	//到期请求序号
	private String hasFreeze = " ";	//是否已解圈存
	private String deadApplyDate = " ";	//到期申请日期
	private String deadacctOperNo = " ";	//到期销账员号
	private String deadacctDate = " ";	//到期销账日期
	private String deadFlowNo = " ";	//到期销账流水号
	public String getAssumxId(){
		return assumxId;
	}
	public void setAssumxId(String assumxId){
		this.assumxId = assumxId;
	}

	public String getAssuId(){
		return assuId;
	}
	public void setAssuId(String assuId){
		this.assuId = assuId;
	}

	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	public String getRgctHistId(){
		return rgctHistId;
	}
	public void setRgctHistId(String rgctHistId){
		this.rgctHistId = rgctHistId;
	}

	public String getWarteeDt(){
		return warteeDt;
	}
	public void setWarteeDt(String warteeDt){
		this.warteeDt = warteeDt;
	}

	public String getWarteeTime(){
		return warteeTime;
	}
	public void setWarteeTime(String warteeTime){
		this.warteeTime = warteeTime;
	}

	public String getWarteeCustNo(){
		return warteeCustNo;
	}
	public void setWarteeCustNo(String warteeCustNo){
		this.warteeCustNo = warteeCustNo;
	}

	public String getWarteeSign(){
		return warteeSign;
	}
	public void setWarteeSign(String warteeSign){
		this.warteeSign = warteeSign;
	}

	public String getWarteeAcctNo(){
		return warteeAcctNo;
	}
	public void setWarteeAcctNo(String warteeAcctNo){
		this.warteeAcctNo = warteeAcctNo;
	}

	public String getWarteeBankNo(){
		return warteeBankNo;
	}
	public void setWarteeBankNo(String warteeBankNo){
		this.warteeBankNo = warteeBankNo;
	}

	public String getGuartrOrgcode(){
		return guartrOrgcode;
	}
	public void setGuartrOrgcode(String guartrOrgcode){
		this.guartrOrgcode = guartrOrgcode;
	}

	public String getWarteeAcptBankNo(){
		return warteeAcptBankNo;
	}
	public void setWarteeAcptBankNo(String warteeAcptBankNo){
		this.warteeAcptBankNo = warteeAcptBankNo;
	}

	public String getGuartrName(){
		return guartrName;
	}
	public void setGuartrName(String guartrName){
		this.guartrName = guartrName;
	}

	public String getGuarntrCustNo(){
		return guarntrCustNo;
	}
	public void setGuarntrCustNo(String guarntrCustNo){
		this.guarntrCustNo = guarntrCustNo;
	}

	public String getGuarntrAcctNo(){
		return guarntrAcctNo;
	}
	public void setGuarntrAcctNo(String guarntrAcctNo){
		this.guarntrAcctNo = guarntrAcctNo;
	}

	public String getGuartrBankNo(){
		return guartrBankNo;
	}
	public void setGuartrBankNo(String guartrBankNo){
		this.guartrBankNo = guartrBankNo;
	}

	public String getWarteeOrgcode(){
		return warteeOrgcode;
	}
	public void setWarteeOrgcode(String warteeOrgcode){
		this.warteeOrgcode = warteeOrgcode;
	}

	public String getAssuStatus(){
		return assuStatus;
	}
	public void setAssuStatus(String assuStatus){
		this.assuStatus = assuStatus;
	}

	public String getIsAccpt(){
		return isAccpt;
	}
	public void setIsAccpt(String isAccpt){
		this.isAccpt = isAccpt;
	}

	public String getAssuType(){
		return assuType;
	}
	public void setAssuType(String assuType){
		this.assuType = assuType;
	}

	public String getGuartrAddr(){
		return guartrAddr;
	}
	public void setGuartrAddr(String guartrAddr){
		this.guartrAddr = guartrAddr;
	}

	public String getWarteeCustName(){
		return warteeCustName;
	}
	public void setWarteeCustName(String warteeCustName){
		this.warteeCustName = warteeCustName;
	}

	public String getGuartrPartnerType(){
		return guartrPartnerType;
	}
	public void setGuartrPartnerType(String guartrPartnerType){
		this.guartrPartnerType = guartrPartnerType;
	}

	public String getWarteePartnerType(){
		return warteePartnerType;
	}
	public void setWarteePartnerType(String warteePartnerType){
		this.warteePartnerType = warteePartnerType;
	}

	public String getAccountDate(){
		return accountDate;
	}
	public void setAccountDate(String accountDate){
		this.accountDate = accountDate;
	}

	public String getAccountTime(){
		return accountTime;
	}
	public void setAccountTime(String accountTime){
		this.accountTime = accountTime;
	}

	public String getAcctStatus(){
		return acctStatus;
	}
	public void setAcctStatus(String acctStatus){
		this.acctStatus = acctStatus;
	}

	public String getAcctOperNo(){
		return acctOperNo;
	}
	public void setAcctOperNo(String acctOperNo){
		this.acctOperNo = acctOperNo;
	}

	public String getAcceptorBankName(){
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName){
		this.acceptorBankName = acceptorBankName;
	}

	public String getAcceptorBankNo(){
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo){
		this.acceptorBankNo = acceptorBankNo;
	}

	public String getAcceptor(){
		return acceptor;
	}
	public void setAcceptor(String acceptor){
		this.acceptor = acceptor;
	}

	public String getAdjustFlag(){
		return adjustFlag;
	}
	public void setAdjustFlag(String adjustFlag){
		this.adjustFlag = adjustFlag;
	}

	public String getAuditDate(){
		return auditDate;
	}
	public void setAuditDate(String auditDate){
		this.auditDate = auditDate;
	}

	public String getAuditTime(){
		return auditTime;
	}
	public void setAuditTime(String auditTime){
		this.auditTime = auditTime;
	}

	public String getAuditOperNo(){
		return auditOperNo;
	}
	public void setAuditOperNo(String auditOperNo){
		this.auditOperNo = auditOperNo;
	}

	public Double getBillMoney(){
		return billMoney;
	}
	public void setBillMoney(Double billMoney){
		this.billMoney = billMoney;
	}

	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
	}

	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	public String getCheckOperNo(){
		return checkOperNo;
	}
	public void setCheckOperNo(String checkOperNo){
		this.checkOperNo = checkOperNo;
	}

	public String getDueDt(){
		return dueDt;
	}
	public void setDueDt(String dueDt){
		this.dueDt = dueDt;
	}

	public Double getFee(){
		return fee;
	}
	public void setFee(Double fee){
		this.fee = fee;
	}

	public Double getFinalFee(){
		return finalFee;
	}
	public void setFinalFee(Double finalFee){
		this.finalFee = finalFee;
	}

	public String getExSerial(){
		return exSerial;
	}
	public void setExSerial(String exSerial){
		this.exSerial = exSerial;
	}

	public Double getImpawnMoney(){
		return impawnMoney;
	}
	public void setImpawnMoney(Double impawnMoney){
		this.impawnMoney = impawnMoney;
	}

	public String getLoanNo(){
		return loanNo;
	}
	public void setLoanNo(String loanNo){
		this.loanNo = loanNo;
	}

	public String getIssueDt(){
		return issueDt;
	}
	public void setIssueDt(String issueDt){
		this.issueDt = issueDt;
	}

	public String getPayee(){
		return payee;
	}
	public void setPayee(String payee){
		this.payee = payee;
	}

	public String getPayeeBankName(){
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName){
		this.payeeBankName = payeeBankName;
	}

	public String getPayeeBankNo(){
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo){
		this.payeeBankNo = payeeBankNo;
	}

	public Double getRate(){
		return rate;
	}
	public void setRate(Double rate){
		this.rate = rate;
	}

	public String getRemitter(){
		return remitter;
	}
	public void setRemitter(String remitter){
		this.remitter = remitter;
	}

	public String getRemitterBankName(){
		return remitterBankName;
	}
	public void setRemitterBankName(String remitterBankName){
		this.remitterBankName = remitterBankName;
	}

	public String getRemitterBankNo(){
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo){
		this.remitterBankNo = remitterBankNo;
	}

	public Double getValidAmount(){
		return validAmount;
	}
	public void setValidAmount(Double validAmount){
		this.validAmount = validAmount;
	}

	public Double getValidQuarter(){
		return validQuarter;
	}
	public void setValidQuarter(Double validQuarter){
		this.validQuarter = validQuarter;
	}

	public String getOperStatus(){
		return operStatus;
	}
	public void setOperStatus(String operStatus){
		this.operStatus = operStatus;
	}

	public String getRemitterAcct(){
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct){
		this.remitterAcct = remitterAcct;
	}

	public String getPayeeAcct(){
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct){
		this.payeeAcct = payeeAcct;
	}

	public String getDraweeAddr(){
		return draweeAddr;
	}
	public void setDraweeAddr(String draweeAddr){
		this.draweeAddr = draweeAddr;
	}

	public String getApplyOperNo(){
		return applyOperNo;
	}
	public void setApplyOperNo(String applyOperNo){
		this.applyOperNo = applyOperNo;
	}

	public String getDeadlineOperNo(){
		return deadlineOperNo;
	}
	public void setDeadlineOperNo(String deadlineOperNo){
		this.deadlineOperNo = deadlineOperNo;
	}

	public String getIfFeeCharging(){
		return ifFeeCharging;
	}
	public void setIfFeeCharging(String ifFeeCharging){
		this.ifFeeCharging = ifFeeCharging;
	}

	public String getDeadApplyId(){
		return deadApplyId;
	}
	public void setDeadApplyId(String deadApplyId){
		this.deadApplyId = deadApplyId;
	}

	public String getHasFreeze(){
		return hasFreeze;
	}
	public void setHasFreeze(String hasFreeze){
		this.hasFreeze = hasFreeze;
	}

	public String getDeadApplyDate(){
		return deadApplyDate;
	}
	public void setDeadApplyDate(String deadApplyDate){
		this.deadApplyDate = deadApplyDate;
	}

	public String getDeadacctOperNo(){
		return deadacctOperNo;
	}
	public void setDeadacctOperNo(String deadacctOperNo){
		this.deadacctOperNo = deadacctOperNo;
	}

	public String getDeadacctDate(){
		return deadacctDate;
	}
	public void setDeadacctDate(String deadacctDate){
		this.deadacctDate = deadacctDate;
	}

	public String getDeadFlowNo(){
		return deadFlowNo;
	}
	public void setDeadFlowNo(String deadFlowNo){
		this.deadFlowNo = deadFlowNo;
	}


}
