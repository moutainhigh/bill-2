/********************************************
* 文件名称: SalebackBillInfo.java
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
package com.herongtech.console.domain.saleback.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class SalebackBillInfo{
	//返售明细流水号
	private String salebackmxId = " ";
	public String getSalebackmxId(){
		return salebackmxId;
	}
	public void setSalebackmxId(String salebackmxId){
		this.salebackmxId = salebackmxId;
	}

	//票据种类
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//票号
	private String billNo = " ";
	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	//出票日
	private String issueDt = " ";
	public String getIssueDt(){
		return issueDt;
	}
	public void setIssueDt(String issueDt){
		this.issueDt = issueDt;
	}

	//到期日
	private String dueDt = " ";
	public String getDueDt(){
		return dueDt;
	}
	public void setDueDt(String dueDt){
		this.dueDt = dueDt;
	}

	//出票人
	private String remitter = " ";
	public String getRemitter(){
		return remitter;
	}
	public void setRemitter(String remitter){
		this.remitter = remitter;
	}

	//出票人账号
	private String remitterAcct = " ";
	public String getRemitterAcct(){
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct){
		this.remitterAcct = remitterAcct;
	}

	//出票人行名
	private String remitterBankName = " ";
	public String getRemitterBankName(){
		return remitterBankName;
	}
	public void setRemitterBankName(String remitterBankName){
		this.remitterBankName = remitterBankName;
	}

	//出票人行号
	private String remitterBankNo = " ";
	public String getRemitterBankNo(){
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo){
		this.remitterBankNo = remitterBankNo;
	}

	//票面金额
	private double billMoney = 0.0;
	public double getBillMoney(){
		return billMoney;
	}
	public void setBillMoney(double billMoney){
		this.billMoney = billMoney;
	}

	//承兑人
	private String acceptor = " ";
	public String getAcceptor(){
		return acceptor;
	}
	public void setAcceptor(String acceptor){
		this.acceptor = acceptor;
	}

	//收款人名称
	private String payeeName = " ";
	public String getPayeeName(){
		return payeeName;
	}
	public void setPayeeName(String payeeName){
		this.payeeName = payeeName;
	}

	//收款人开户行名称
	private String payeeBankName = " ";
	public String getPayeeBankName(){
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName){
		this.payeeBankName = payeeBankName;
	}

	//收款人账号
	private String payeeAcct = " ";
	public String getPayeeAcct(){
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct){
		this.payeeAcct = payeeAcct;
	}

	//交易前手r1
	private String billBeforeOwner = " ";
	public String getBillBeforeOwner(){
		return billBeforeOwner;
	}
	public void setBillBeforeOwner(String billBeforeOwner){
		this.billBeforeOwner = billBeforeOwner;
	}

	//所属客户名称
	private String billOwner = " ";
	public String getBillOwner(){
		return billOwner;
	}
	public void setBillOwner(String billOwner){
		this.billOwner = billOwner;
	}

	//票据分类
	private String billClass = " ";
	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
	}

	//票据来源
	private String billSource = " ";
	public String getBillSource(){
		return billSource;
	}
	public void setBillSource(String billSource){
		this.billSource = billSource;
	}

	//操作柜员
	private String operNo = " ";
	public String getOperNo(){
		return operNo;
	}
	public void setOperNo(String operNo){
		this.operNo = operNo;
	}

	//操作状态
	private String operStatus = " ";
	public String getOperStatus(){
		return operStatus;
	}
	public void setOperStatus(String operStatus){
		this.operStatus = operStatus;
	}

	//返售流水号
	private String salebackId = " ";
	public String getSalebackId(){
		return salebackId;
	}
	public void setSalebackId(String salebackId){
		this.salebackId = salebackId;
	}

	//登记中心id
	private String rgctId = " ";
	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	//操作机构号码
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//客户账号
	private String custAccount = " ";
	public String getCustAccount(){
		return custAccount;
	}
	public void setCustAccount(String custAccount){
		this.custAccount = custAccount;
	}

	//创建日期
	private String createDt = " ";
	public String getCreateDt(){
		return createDt;
	}
	public void setCreateDt(String createDt){
		this.createDt = createDt;
	}

	//创建时间
	private String createTime = " ";
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	//是否审核
	private String isAudited = " ";
	public String getIsAudited(){
		return isAudited;
	}
	public void setIsAudited(String isAudited){
		this.isAudited = isAudited;
	}

	//买入明细流水号
	private String rebuymxId = " ";
	public String getRebuymxId(){
		return rebuymxId;
	}
	public void setRebuymxId(String rebuymxId){
		this.rebuymxId = rebuymxId;
	}

	//付款人开户行地址
	private String draweeAddr = " ";
	public String getDraweeAddr(){
		return draweeAddr;
	}
	public void setDraweeAddr(String draweeAddr){
		this.draweeAddr = draweeAddr;
	}

	//收款人开户行行号
	private String payeeBankNo = " ";
	public String getPayeeBankNo(){
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo){
		this.payeeBankNo = payeeBankNo;
	}

	//交易合同号
	private String conferNo = " ";
	public String getConferNo(){
		return conferNo;
	}
	public void setConferNo(String conferNo){
		this.conferNo = conferNo;
	}

	//是否我行承兑
	private String isAccpt = " ";
	public String getIsAccpt(){
		return isAccpt;
	}
	public void setIsAccpt(String isAccpt){
		this.isAccpt = isAccpt;
	}

	//总利息
	private double interest = 0.0;
	public double getInterest(){
		return interest;
	}
	public void setInterest(double interest){
		this.interest = interest;
	}

	//额度编号
	private String limitBillId = " ";
	public String getLimitBillId(){
		return limitBillId;
	}
	public void setLimitBillId(String limitBillId){
		this.limitBillId = limitBillId;
	}

	//额度扣减产品
	private String limitProdNo = " ";
	public String getLimitProdNo(){
		return limitProdNo;
	}
	public void setLimitProdNo(String limitProdNo){
		this.limitProdNo = limitProdNo;
	}

	//买入部门号
	private String buyDeptNo = " ";
	public String getBuyDeptNo(){
		return buyDeptNo;
	}
	public void setBuyDeptNo(String buyDeptNo){
		this.buyDeptNo = buyDeptNo;
	}

	//是否内部买入
	private String isInner = " ";
	public String getIsInner(){
		return isInner;
	}
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}

	//出票人客户号
	private String remitterCustNo = " ";
	public String getRemitterCustNo(){
		return remitterCustNo;
	}
	public void setRemitterCustNo(String remitterCustNo){
		this.remitterCustNo = remitterCustNo;
	}

	//实收金额
	private double salebackMoney = 0.0;
	public double getSalebackMoney(){
		return salebackMoney;
	}
	public void setSalebackMoney(double salebackMoney){
		this.salebackMoney = salebackMoney;
	}

	//是否线上清算
	private String isOnline = " ";
	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline;
	}

	//记账前台流水号
	private String exSerial = " ";
	public String getExSerial(){
		return exSerial;
	}
	public void setExSerial(String exSerial){
		this.exSerial = exSerial;
	}

	//计息天数
	private Long interestDays = 0l;
	public Long getInterestDays(){
		return interestDays;
	}
	public void setInterestDays(Long interestDays){
		this.interestDays = interestDays;
	}

	//支付交易序号
	private String payTradeNo = " ";
	public String getPayTradeNo(){
		return payTradeNo;
	}
	public void setPayTradeNo(String payTradeNo){
		this.payTradeNo = payTradeNo;
	}

	//记账日期
	private String accountDt = " ";
	public String getAccountDt(){
		return accountDt;
	}
	public void setAccountDt(String accountDt){
		this.accountDt = accountDt;
	}

	//记账时间
	private String accountTime = " ";
	public String getAccountTime(){
		return accountTime;
	}
	public void setAccountTime(String accountTime){
		this.accountTime = accountTime;
	}

	//申请岗
	private String applyOperNo = " ";
	public String getApplyOperNo(){
		return applyOperNo;
	}
	public void setApplyOperNo(String applyOperNo){
		this.applyOperNo = applyOperNo;
	}

	//审核岗
	private String auditOperNo = " ";
	public String getAuditOperNo(){
		return auditOperNo;
	}
	public void setAuditOperNo(String auditOperNo){
		this.auditOperNo = auditOperNo;
	}

	//记账岗柜员
	private String acctOperNo = " ";
	public String getAcctOperNo(){
		return acctOperNo;
	}
	public void setAcctOperNo(String acctOperNo){
		this.acctOperNo = acctOperNo;
	}

	//顺延类型
	private String delayType = " ";
	public String getDelayType(){
		return delayType;
	}
	public void setDelayType(String delayType){
		this.delayType = delayType;
	}

	//顺延天数
	private Long delayDays = 0l;
	public Long getDelayDays(){
		return delayDays;
	}
	public void setDelayDays(Long delayDays){
		this.delayDays = delayDays;
	}

	//返售到期日
	private String salebackDueDt = " ";
	public String getSalebackDueDt(){
		return salebackDueDt;
	}
	public void setSalebackDueDt(String salebackDueDt){
		this.salebackDueDt = salebackDueDt;
	}

	//禁止背书
	private String forbidFlag = " ";
	public String getForbidFlag(){
		return forbidFlag;
	}
	public void setForbidFlag(String forbidFlag){
		this.forbidFlag = forbidFlag;
	}

	//背书日期
	private String endorseDt = " ";
	public String getEndorseDt(){
		return endorseDt;
	}
	public void setEndorseDt(String endorseDt){
		this.endorseDt = endorseDt;
	}

	//背书时间
	private String endorseTime = " ";
	public String getEndorseTime(){
		return endorseTime;
	}
	public void setEndorseTime(String endorseTime){
		this.endorseTime = endorseTime;
	}

	//承兑行行号
	private String acceptorBankNo = " ";
	public String getAcceptorBankNo(){
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo){
		this.acceptorBankNo = acceptorBankNo;
	}

	//承兑人开户行名称
	private String acceptorBankName = " ";
	public String getAcceptorBankName(){
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName){
		this.acceptorBankName = acceptorBankName;
	}

	//是否移票
	private String isDummy = " ";
	public String getIsDummy(){
		return isDummy;
	}
	public void setIsDummy(String isDummy){
		this.isDummy = isDummy;
	}

	//保管机构
	private String billStorageOrg = " ";
	public String getBillStorageOrg(){
		return billStorageOrg;
	}
	public void setBillStorageOrg(String billStorageOrg){
		this.billStorageOrg = billStorageOrg;
	}

	//保管机构名称
	private String billStorageOrgName = " ";
	public String getBillStorageOrgName(){
		return billStorageOrgName;
	}
	public void setBillStorageOrgName(String billStorageOrgName){
		this.billStorageOrgName = billStorageOrgName;
	}

	//票据当前状态
	private String curStatus = " ";
	public String getCurStatus(){
		return curStatus;
	}
	public void setCurStatus(String curStatus){
		this.curStatus = curStatus;
	}

	//审批意见
	private String auditReason = " ";
	public String getAuditReason(){
		return auditReason;
	}
	public void setAuditReason(String auditReason){
		this.auditReason = auditReason;
	}

	//原买入日
	private String buyDt = " ";
	public String getBuyDt(){
		return buyDt;
	}
	public void setBuyDt(String buyDt){
		this.buyDt = buyDt;
	}

	//原买入批次id
	private String rebuyId = " ";
	public String getRebuyId(){
		return rebuyId;
	}
	public void setRebuyId(String rebuyId){
		this.rebuyId = rebuyId;
	}


}
