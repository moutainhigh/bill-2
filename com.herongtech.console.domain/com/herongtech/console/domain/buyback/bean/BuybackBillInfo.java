/********************************************
* 文件名称: BuybackBillInfo.java
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
package com.herongtech.console.domain.buyback.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class BuybackBillInfo{
	private String buybackmxId = " ";	//回购明细流水号
	private String billType = " ";	//票据种类
	private String billNo = " ";	//票号
	private String issueDt = " ";	//出票日
	private String dueDt = " ";	//到期日
	private String remitter = " ";	//出票人
	private String remitterAcct = " ";	//出票人账号
	private String remitterBankName = " ";	//出票人行名
	private String remitterBankNo = " ";	//出票人行号
	private double billMoney = 0.0;	//票面金额
	private String acceptor = " ";	//承兑人
	private String payeeName = " ";	//收款人名称
	private String payeeBankName = " ";	//收款人开户行名称
	private String payeeAcct = " ";	//收款人账号
	private String billBeforeOwner = " ";	//交易前手
	private String billOwner = " ";	//客户名称
	private String billClass = " ";	//票据分类
	private String billSource = " ";	//票据来源
	private String operStatus = " ";	//操作状态
	private String buybackId = " ";	//回购批次流水号
	private String rgctId = " ";	//登记中心id
	private String branchNo = " ";	//操作机构id
	private String custAccount = " ";	//客户账号
	private String createTime = " ";	//创建时间
	private String isAudited = " ";	//是否审核
	private String regressDt = " ";	//回购到期日
	private String draweeAddr = " ";	//付款人开户行地址
	private String payeeBankNo = " ";	//收款人开户行行号
	private String conferNo = " ";	//交易合同编号
	private String isAccpt = " ";	//是否我行承兑
	private double interest = 0.0;	//总利息
	private String saleDt = " ";	//原卖出日
	private String isDummy = " ";	//是否移票
	private String isInner = " ";	//是否系统内
	private String aimBranchNo = " ";	//交易对手机构
	private String innerAccount = " ";	//往来账号
	private Long limitBillId = 0l;	//额度编号
	private String limitProdNo = " ";	//额度扣减产品编码
	private String buyDeptNo = " ";	//买入部门编号
	private String remitterCustNo = " ";	//出票人客户号
	private double buybackMoney = 0.0;	//实付金额
	private String isOnline = " ";	//是否线上清算
	private String salemxId = " ";	//原卖出明细流水号
	private String exSerial = " ";	//记账前台流水号
	private Long interestDays = 0l;	//计息天数
	private String payTradeNo = " ";	//支付交易序号
	private String accountDate = " ";	//记账日期
	private String applyOperNo = " ";	//申请岗
	private String auditOperNo = " ";	//审核岗
	private String acctOperNo = " ";	//记账岗柜员
	private String delayType = " ";	//顺延类型
	private Long delayDays = 0l;	//顺延天数
	private String forbidFlag = " ";	//禁止背书标志
	private double saleReceiveMoney = 0.0;	//原转出实收金额
	private double saleInterest = 0.0;	//原转出利率
	private double draftPayMoney = 0.0;	//报文实付金额
	private String applyCancelFlag = " ";	//申请撤销标记
	private String reqDraftNo = " ";	//请求报文编号
	private String acceptorBankNo = " ";	//承兑行行号
	private String acceptorBankName = " ";	//承兑人开户行名称
	private String curStatus = " ";	//票据当前状态
	private String saleId = " ";	//原转卖批次id
	private String buybackDt = " ";	//实际赎回日
	public String getBuybackmxId(){
		return buybackmxId;
	}
	public void setBuybackmxId(String buybackmxId){
		this.buybackmxId = buybackmxId;
	}

	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	public String getIssueDt(){
		return issueDt;
	}
	public void setIssueDt(String issueDt){
		this.issueDt = issueDt;
	}

	public String getDueDt(){
		return dueDt;
	}
	public void setDueDt(String dueDt){
		this.dueDt = dueDt;
	}

	public String getRemitter(){
		return remitter;
	}
	public void setRemitter(String remitter){
		this.remitter = remitter;
	}

	public String getRemitterAcct(){
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct){
		this.remitterAcct = remitterAcct;
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

	public double getBillMoney(){
		return billMoney;
	}
	public void setBillMoney(double billMoney){
		this.billMoney = billMoney;
	}

	public String getAcceptor(){
		return acceptor;
	}
	public void setAcceptor(String acceptor){
		this.acceptor = acceptor;
	}

	public String getPayeeName(){
		return payeeName;
	}
	public void setPayeeName(String payeeName){
		this.payeeName = payeeName;
	}

	public String getPayeeBankName(){
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName){
		this.payeeBankName = payeeBankName;
	}

	public String getPayeeAcct(){
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct){
		this.payeeAcct = payeeAcct;
	}

	public String getBillBeforeOwner(){
		return billBeforeOwner;
	}
	public void setBillBeforeOwner(String billBeforeOwner){
		this.billBeforeOwner = billBeforeOwner;
	}

	public String getBillOwner(){
		return billOwner;
	}
	public void setBillOwner(String billOwner){
		this.billOwner = billOwner;
	}

	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
	}

	public String getBillSource(){
		return billSource;
	}
	public void setBillSource(String billSource){
		this.billSource = billSource;
	}

	public String getOperStatus(){
		return operStatus;
	}
	public void setOperStatus(String operStatus){
		this.operStatus = operStatus;
	}

	public String getBuybackId(){
		return buybackId;
	}
	public void setBuybackId(String buybackId){
		this.buybackId = buybackId;
	}

	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	public String getCustAccount(){
		return custAccount;
	}
	public void setCustAccount(String custAccount){
		this.custAccount = custAccount;
	}

	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getIsAudited(){
		return isAudited;
	}
	public void setIsAudited(String isAudited){
		this.isAudited = isAudited;
	}

	public String getRegressDt(){
		return regressDt;
	}
	public void setRegressDt(String regressDt){
		this.regressDt = regressDt;
	}

	public String getDraweeAddr(){
		return draweeAddr;
	}
	public void setDraweeAddr(String draweeAddr){
		this.draweeAddr = draweeAddr;
	}

	public String getPayeeBankNo(){
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo){
		this.payeeBankNo = payeeBankNo;
	}

	public String getConferNo(){
		return conferNo;
	}
	public void setConferNo(String conferNo){
		this.conferNo = conferNo;
	}

	public String getIsAccpt(){
		return isAccpt;
	}
	public void setIsAccpt(String isAccpt){
		this.isAccpt = isAccpt;
	}

	public double getInterest(){
		return interest;
	}
	public void setInterest(double interest){
		this.interest = interest;
	}

	public String getSaleDt(){
		return saleDt;
	}
	public void setSaleDt(String saleDt){
		this.saleDt = saleDt;
	}

	public String getIsDummy(){
		return isDummy;
	}
	public void setIsDummy(String isDummy){
		this.isDummy = isDummy;
	}

	public String getIsInner(){
		return isInner;
	}
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}

	public String getAimBranchNo(){
		return aimBranchNo;
	}
	public void setAimBranchNo(String aimBranchNo){
		this.aimBranchNo = aimBranchNo;
	}

	public String getInnerAccount(){
		return innerAccount;
	}
	public void setInnerAccount(String innerAccount){
		this.innerAccount = innerAccount;
	}

	public Long getLimitBillId(){
		return limitBillId;
	}
	public void setLimitBillId(Long limitBillId){
		this.limitBillId = limitBillId;
	}

	public String getLimitProdNo(){
		return limitProdNo;
	}
	public void setLimitProdNo(String limitProdNo){
		this.limitProdNo = limitProdNo;
	}

	public String getBuyDeptNo(){
		return buyDeptNo;
	}
	public void setBuyDeptNo(String buyDeptNo){
		this.buyDeptNo = buyDeptNo;
	}

	public String getRemitterCustNo(){
		return remitterCustNo;
	}
	public void setRemitterCustNo(String remitterCustNo){
		this.remitterCustNo = remitterCustNo;
	}

	public double getBuybackMoney(){
		return buybackMoney;
	}
	public void setBuybackMoney(double buybackMoney){
		this.buybackMoney = buybackMoney;
	}

	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline;
	}

	public String getSalemxId(){
		return salemxId;
	}
	public void setSalemxId(String salemxId){
		this.salemxId = salemxId;
	}

	public String getExSerial(){
		return exSerial;
	}
	public void setExSerial(String exSerial){
		this.exSerial = exSerial;
	}

	public Long getInterestDays(){
		return interestDays;
	}
	public void setInterestDays(Long interestDays){
		this.interestDays = interestDays;
	}

	public String getPayTradeNo(){
		return payTradeNo;
	}
	public void setPayTradeNo(String payTradeNo){
		this.payTradeNo = payTradeNo;
	}

	public String getAccountDate(){
		return accountDate;
	}
	public void setAccountDate(String accountDate){
		this.accountDate = accountDate;
	}

	public String getApplyOperNo(){
		return applyOperNo;
	}
	public void setApplyOperNo(String applyOperNo){
		this.applyOperNo = applyOperNo;
	}

	public String getAuditOperNo(){
		return auditOperNo;
	}
	public void setAuditOperNo(String auditOperNo){
		this.auditOperNo = auditOperNo;
	}

	public String getAcctOperNo(){
		return acctOperNo;
	}
	public void setAcctOperNo(String acctOperNo){
		this.acctOperNo = acctOperNo;
	}

	public String getDelayType(){
		return delayType;
	}
	public void setDelayType(String delayType){
		this.delayType = delayType;
	}

	public Long getDelayDays(){
		return delayDays;
	}
	public void setDelayDays(Long delayDays){
		this.delayDays = delayDays;
	}

	public String getForbidFlag(){
		return forbidFlag;
	}
	public void setForbidFlag(String forbidFlag){
		this.forbidFlag = forbidFlag;
	}

	public double getSaleReceiveMoney(){
		return saleReceiveMoney;
	}
	public void setSaleReceiveMoney(double saleReceiveMoney){
		this.saleReceiveMoney = saleReceiveMoney;
	}

	public double getSaleInterest(){
		return saleInterest;
	}
	public void setSaleInterest(double saleInterest){
		this.saleInterest = saleInterest;
	}

	public double getDraftPayMoney(){
		return draftPayMoney;
	}
	public void setDraftPayMoney(double draftPayMoney){
		this.draftPayMoney = draftPayMoney;
	}

	public String getApplyCancelFlag(){
		return applyCancelFlag;
	}
	public void setApplyCancelFlag(String applyCancelFlag){
		this.applyCancelFlag = applyCancelFlag;
	}

	public String getReqDraftNo(){
		return reqDraftNo;
	}
	public void setReqDraftNo(String reqDraftNo){
		this.reqDraftNo = reqDraftNo;
	}

	public String getAcceptorBankNo(){
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo){
		this.acceptorBankNo = acceptorBankNo;
	}

	public String getAcceptorBankName(){
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName){
		this.acceptorBankName = acceptorBankName;
	}

	public String getCurStatus(){
		return curStatus;
	}
	public void setCurStatus(String curStatus){
		this.curStatus = curStatus;
	}

	public String getSaleId(){
		return saleId;
	}
	public void setSaleId(String saleId){
		this.saleId = saleId;
	}

	public String getBuybackDt(){
		return buybackDt;
	}
	public void setBuybackDt(String buybackDt){
		this.buybackDt = buybackDt;
	}


}