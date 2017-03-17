/********************************************
* 文件名称: DiscBillInfo.java

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
package com.herongtech.console.domain.disc.bean;

import java.io.Serializable;
import java.sql.SQLException;

import com.herongtech.commons.tools.BigDecimalUtil;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.common.ICommonService;
import com.herongtech.console.web.busicontroller.common.DiscCodeConst;


public class DiscBillInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//贴现明细流水号(清单id)
	private String discmxId = " ";
	public String getDiscmxId(){
		return discmxId;
	}
	public void setDiscmxId(String discmxId){
		this.discmxId = discmxId;
	}

	//票据种类
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//出票日
	private String issueDt = " ";
	public String getIssueDt(){
		return issueDt;
	}
	public void setIssueDt(String issueDt){
		this.issueDt = issueDt;
	}

	//到期日---当做交易日期来记载
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

	//承兑人
	private String acceptor = " ";
	public String getAcceptor(){
		return acceptor;
	}
	public void setAcceptor(String acceptor){
		this.acceptor = acceptor;
	}

	//承兑人账号
	private String acceptorAcct = " ";
	public String getAcceptorAcct(){
		return acceptorAcct;
	}
	public void setAcceptorAcct(String acceptorAcct){
		this.acceptorAcct = acceptorAcct;
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

	//承兑人组织机构代码
	private String acceptorBranchNo = " ";
	public String getAcceptorBranchNo(){
		return acceptorBranchNo;
	}
	public void setAcceptorBranchNo(String acceptorBranchNo){
		this.acceptorBranchNo = acceptorBranchNo;
	}

	//票面金额
	private double billMoney = 0.0;
	public double getBillMoney(){
		return BigDecimalUtil.round(billMoney, 2);
	}
	public void setBillMoney(double billMoney){
		this.billMoney = billMoney;
	}

	//付款人开户行地址
	private String draweeAddr = " ";
	public String getDraweeAddr(){
		return draweeAddr;
	}
	public void setDraweeAddr(String draweeAddr){
		this.draweeAddr = draweeAddr;
	}


	//付款人开户行行号
	private String draweeBankNo;
	public String getDraweeBankNo() {
		return draweeBankNo;
	}
	public void setDraweeBankNo(String draweeBankNo) {
		this.draweeBankNo = draweeBankNo;
	}

	//收款人开户行行号
	private String payeeBankNo = " ";
	public String getPayeeBankNo(){
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo){
		this.payeeBankNo = payeeBankNo;
	}

	//收款人名称
	private String payee = " ";
	public String getPayee(){
		return payee;
	}
	public void setPayee(String payee){
		this.payee = payee;
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

	//交易前手
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

	//操作状态
	private String operStatus = " ";
	public String getOperStatus(){
		return operStatus;
	}
	public void setOperStatus(String operStatus){
		this.operStatus = operStatus;
	}

	//贴现流水号(批次id)
	private String discId = " ";
	public String getDiscId(){
		return discId;
	}
	public void setDiscId(String discId){
		this.discId = discId;
	}

	//登记中心id
	private String rgctId = " ";
	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	//客户号
	private String custNo = " ";
	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	//客户账号
	private String custAccount = " ";
	public String getCustAccount(){
		return custAccount;
	}
	public void setCustAccount(String custAccount){
		this.custAccount = custAccount;
	}

	//总利息
	private double interest = 0.0;
	public double getInterest(){
		this.interest = this.salerInterest + this.buyerInterest;
		return this.interest;
	}
	
	public void setInterest(double interest){
		this.interest = interest;
	}
	
	//贴现利息
	private double salerInterest = 0.0;
	public double getSalerInterest(){
		return salerInterest;
	}
	public void setSalerInterest(double salerInterest){
		this.salerInterest = salerInterest;
	}

	//顺延天数
	private Long delayDays = 0l;
	public Long getDelayDays(){
		return delayDays;
	}
	public void setDelayDays(Long delayDays){
		this.delayDays = delayDays;
	}

	//计息到期日
	private String galeDate = " ";
	public String getGaleDate(){
		return galeDate;
	}
	public void setGaleDate(String galeDate){
		this.galeDate = galeDate;
	}

	//计息天数
	private Long interestDays = 0l;
	public Long getInterestDays(){
		return interestDays;
	}
	public void setInterestDays(Long interestDays){
		this.interestDays = interestDays;
	}

	//买方付息金额
	private double buyerInterest = 0.0;
	public double getBuyerInterest(){
		return buyerInterest;
	}
	public void setBuyerInterest(double buyerInterest){
		this.buyerInterest = buyerInterest;
	}

	//实付金额
	private double payMoney = 0.0;
	public double getPayMoney(){
		return payMoney;
	}
	public void setPayMoney(double payMoney){
		this.payMoney = payMoney;
	}

	//是否同城
	private String isSameCity = " ";
	public String getIsSameCity(){
		return isSameCity;
	}
	public void setIsSameCity(String isSameCity){
		this.isSameCity = isSameCity;
	}

	//备注
	private String remark = " ";
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	//是否发起查询查复
	private String isAudited = " ";
	public String getIsAudited(){
		return isAudited;
	}
	public void setIsAudited(String isAudited){
		this.isAudited = isAudited;
	}

	//交易合同编号
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

	//额度产品代码
	private String limitProdNo = " ";
	public String getLimitProdNo(){
		return limitProdNo;
	}
	public void setLimitProdNo(String limitProdNo){
		this.limitProdNo = limitProdNo;
	}

	//利率
	private double rate = 0.0;
	public double getRate(){
		return rate;
	}
	public void setRate(double rate){
		this.rate = rate;
	}

	//利率类型
	private String rateType = " ";
	public String getRateType(){
		return rateType;
	}
	public void setRateType(String rateType){
		this.rateType = rateType;
	}

	//贴现日
	private String discDt = " ";
	public String getDiscDt(){
		return discDt;
	}
	public void setDiscDt(String discDt){
		this.discDt = discDt;
	}

	//贴现类型
	private String discType = " ";
	public String getDiscType(){
		return discType;
	}
	public void setDiscType(String discType){
		this.discType = discType;
	}

	//档案编号
	private String fileNo = " ";
	public String getFileNo(){
		return fileNo;
	}
	public void setFileNo(String fileNo){
		this.fileNo = fileNo;
	}

	//放款借据号
	private String noLouLoans = " ";
	public String getNoLouLoans(){
		return noLouLoans;
	}
	public void setNoLouLoans(String noLouLoans){
		this.noLouLoans = noLouLoans;
	}

	//是否线上清算
	private String isOnline = " ";
	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline;
	}

	//赎回截止日
	private String redeemEndDate = " ";
	public String getRedeemEndDate(){
		return redeemEndDate;
	}
	public void setRedeemEndDate(String redeemEndDate){
		this.redeemEndDate = redeemEndDate;
	}

	//银行产品编号
	private String bankProdNo = " ";
	public String getBankProdNo(){
		return bankProdNo;
	}
	public void setBankProdNo(String bankProdNo){
		this.bankProdNo = bankProdNo;
	}

	//电子票利息试算实付金额
	private double localPayMoney = 0.0;
	public double getLocalPayMoney(){
		return localPayMoney;
	}
	public void setLocalPayMoney(double localPayMoney){
		this.localPayMoney = localPayMoney;
	}

	//额度扣减是否成功
	private String isAmount = " ";
	public String getIsAmount(){
		return isAmount;
	}
	public void setIsAmount(String isAmount){
		this.isAmount = isAmount;
	}

	//禁止背书标记
	private String forbidFlag = " ";
	public String getForbidFlag(){
		return forbidFlag;
	}
	public void setForbidFlag(String forbidFlag){
		this.forbidFlag = forbidFlag;
	}

	//是否扣减第三方额度
	private String isThirdAmount = " ";
	public String getIsThirdAmount(){
		return isThirdAmount;
	}
	public void setIsThirdAmount(String isThirdAmount){
		this.isThirdAmount = isThirdAmount;
	}

	//出账申请书号
	private String outApplyNo = " ";
	public String getOutApplyNo(){
		return outApplyNo;
	}
	public void setOutApplyNo(String outApplyNo){
		this.outApplyNo = outApplyNo;
	}

	//机构
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//柜员操作行号
	private String operBankNo = " ";
	public String getOperBankNo(){
		return operBankNo;
	}
	public void setOperBankNo(String operBankNo){
		this.operBankNo = operBankNo;
	}

	//移植来源
	private String yzSource = " ";
	public String getYzSource(){
		return yzSource;
	}
	public void setYzSource(String yzSource){
		this.yzSource = yzSource;
	}

	//产品编码
	private String productId = " ";
	public String getProductId(){
		return productId;
	}
	public void setProductId(String productId){
		this.productId = productId;
	}

	//额度所属客户
	private String ownerPartyId = " ";
	public String getOwnerPartyId(){
		return ownerPartyId;
	}
	public void setOwnerPartyId(String ownerPartyId){
		this.ownerPartyId = ownerPartyId;
	}

	//是否可循环
	private String isCyc = " ";
	public String getIsCyc(){
		return isCyc;
	}
	public void setIsCyc(String isCyc){
		this.isCyc = isCyc;
	}

	//网银是否可撤销
	private String isCancel = " ";
	public String getIsCancel(){
		return isCancel;
	}
	public void setIsCancel(String isCancel){
		this.isCancel = isCancel;
	}

	//赎回开放日
	private String redeemOpenDt = " ";
	public String getRedeemOpenDt(){
		return redeemOpenDt;
	}
	public void setRedeemOpenDt(String redeemOpenDt){
		this.redeemOpenDt = redeemOpenDt;
	}

	//记账批次
	private String discSquareId = " ";
	public String getDiscSquareId(){
		return discSquareId;
	}
	public void setDiscSquareId(String discSquareId){
		this.discSquareId = discSquareId;
	}

	//入账帐号
	private String inAcctNo = " ";
	public String getInAcctNo(){
		return inAcctNo;
	}
	public void setInAcctNo(String inAcctNo){
		this.inAcctNo = inAcctNo;
	}

	//入账行号
	private String inBankNo = " ";
	public String getInBankNo(){
		return inBankNo;
	}
	public void setInBankNo(String inBankNo){
		this.inBankNo = inBankNo;
	}

	//记账前台流水号
	private String exSerial = " ";
	public String getExSerial(){
		return exSerial;
	}
	public void setExSerial(String exSerial){
		this.exSerial = exSerial;
	}

	//初审意见
	private String auditReason = " ";
	public String getAuditReason() {
		return auditReason;
	}
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	//更新日期
	private String updateDt = " ";
	public String getUpdateDt(){
		return updateDt;
	}
	public void setUpdateDt(String updateDt){
		this.updateDt = updateDt;
	}

	//更新时间
	private String updateTime = " ";
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	//记账日期
	private String accountDate = " ";
	public String getAccountDate(){
		return accountDate;
	}
	public void setAccountDate(String accountDate){
		this.accountDate = accountDate;
	}

	//记账时间
	private String accountTime = " ";
	public String getAccountTime(){
		return accountTime;
	}
	public void setAccountTime(String accountTime){
		this.accountTime = accountTime;
	}

	//发托日
	private String collDt = " ";
	public String getCollDt(){
		return collDt;
	}
	public void setCollDt(String collDt){
		this.collDt = collDt;
	}

	//票号
	private String billNo = " ";
	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
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

	//收回票款类型
	private String gathMneyType = " ";
	public String getGathMneyType(){
		return gathMneyType;
	}
	public void setGathMneyType(String gathMneyType){
		this.gathMneyType = gathMneyType;
	}

	//收回票款日
	private String gathMneyDate = " ";
	public String getGathMneyDate(){
		return gathMneyDate;
	}
	public void setGathMneyDate(String gathMneyDate){
		this.gathMneyDate = gathMneyDate;
	}

	//申请提交日期
	private String applyCommitDate = " ";
	public String getApplyCommitDate(){
		return applyCommitDate;
	}
	public void setApplyCommitDate(String applyCommitDate){
		this.applyCommitDate = applyCommitDate;
	}

	//申请提交时间
	private String applyCommitTime = " ";
	public String getApplyCommitTime(){
		return applyCommitTime;
	}
	public void setApplyCommitTime(String applyCommitTime){
		this.applyCommitTime = applyCommitTime;
	}

	//审核提交日期
	private String auditCommitDate = " ";
	public String getAuditCommitDate(){
		return auditCommitDate;
	}
	public void setAuditCommitDate(String auditCommitDate){
		this.auditCommitDate = auditCommitDate;
	}

	//审核提交时间
	private String auditCommitTime = " ";
	public String getAuditCommitTime(){
		return auditCommitTime;
	}
	public void setAuditCommitTime(String auditCommitTime){
		this.auditCommitTime = auditCommitTime;
	}

	//记账提交日期
	private String acctCommitDate = " ";
	public String getAcctCommitDate(){
		return acctCommitDate;
	}
	public void setAcctCommitDate(String acctCommitDate){
		this.acctCommitDate = acctCommitDate;
	}

	//记账提交时间
	private String acctCommitTime = " ";
	public String getAcctCommitTime(){
		return acctCommitTime;
	}
	public void setAcctCommitTime(String acctCommitTime){
		this.acctCommitTime = acctCommitTime;
	}

	//总计利息支出
	private double totalIntrstPayment = 0.0;
	public double getTotalIntrstPayment(){
		return totalIntrstPayment;
	}
	public void setTotalIntrstPayment(double totalIntrstPayment){
		this.totalIntrstPayment = totalIntrstPayment;
	}

	//顺延方式
	private String delayType = " ";
	public String getDelayType(){
		return delayType;
	}
	public void setDelayType(String delayType){
		this.delayType = delayType;
	}

	//组织机构代码
	private String orgCode = " ";
	public String getOrgCode(){
		return orgCode;
	}
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}

	//查复结果
	private String queryResult = " ";
	public String getQueryResult(){
		return queryResult;
	}
	public void setQueryResult(String queryResult){
		this.queryResult = queryResult;
	}

	//查询内容
	private String queryContent = " ";
	public String getQueryContent(){
		return queryContent;
	}
	public void setQueryContent(String queryContent){
		this.queryContent = queryContent;
	}

	//查复内容
	private String returnContent = " ";
	public String getReturnContent(){
		return returnContent;
	}
	public void setReturnContent(String returnContent){
		this.returnContent = returnContent;
	}

	//原申请报文id
	private String reqMsgId = " ";
	public String getReqMsgId(){
		return reqMsgId;
	}
	public void setReqMsgId(String reqMsgId){
		this.reqMsgId = reqMsgId;
	}

	//入库日期
	private String inStoreDt = " ";
	public String getInStoreDt(){
		return inStoreDt;
	}
	public void setInStoreDt(String inStoreDt){
		this.inStoreDt = inStoreDt;
	}

	//入库时间
	private String inStoreTime = " ";
	public String getInStoreTime(){
		return inStoreTime;
	}
	public void setInStoreTime(String inStoreTime){
		this.inStoreTime = inStoreTime;
	}

	//发票号码
	private String invoiceNo = " ";
	public String getInvoiceNo(){
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo){
		this.invoiceNo = invoiceNo;
	}

	//报文实付金额
	private double draftPayMoney = 0.0;
	public double getDraftPayMoney(){
		return draftPayMoney;
	}
	public void setDraftPayMoney(double draftPayMoney){
		this.draftPayMoney = draftPayMoney;
	}

	//托收记账日期
	private String collAccountDt = " ";
	public String getCollAccountDt(){
		return collAccountDt;
	}
	public void setCollAccountDt(String collAccountDt){
		this.collAccountDt = collAccountDt;
	}

	//银承协议编号
	private String protocalNo = " ";
	public String getProtocalNo(){
		return protocalNo;
	}
	public void setProtocalNo(String protocalNo){
		this.protocalNo = protocalNo;
	}

	//修改日期
	private String changeDt = " ";
	public String getChangeDt(){
		return changeDt;
	}
	public void setChangeDt(String changeDt){
		this.changeDt = changeDt;
	}

	//修改时间
	private String changeTime = " ";
	public String getChangeTime(){
		return changeTime;
	}
	public void setChangeTime(String changeTime){
		this.changeTime = changeTime;
	}

	//票号后八位
	private String lastBillNo = " ";
	public String getLastBillNo(){
		return lastBillNo;
	}
	public void setLastBillNo(String lastBillNo){
		this.lastBillNo = lastBillNo;
	}

	//票据当前状态
	private String curStatus = " ";
	public String getCurStatus(){
		return curStatus;
	}
	public void setCurStatus(String curStatus){
		this.curStatus = curStatus;
	}

	//票据流转id
	private String transId = " ";
	public String getTransId(){
		return transId;
	}
	public void setTransId(String transId){
		this.transId = transId;
	}

	//回购明细id
	private String buybackId = " ";
	public String getBuybackId(){
		return buybackId;
	}
	public void setBuybackId(String buybackId){
		this.buybackId = buybackId;
	}

	//票据流转类型
	private String transType = " ";
	public String getTransType(){
		return transType;
	}
	public void setTransType(String transType){
		this.transType = transType;
	}

	//是否赎回
	private String isRedeem = " ";
	public String getIsRedeem(){
		return isRedeem;
	}
	public void setIsRedeem(String isRedeem){
		this.isRedeem = isRedeem;
	}

	//额度扣减行 
	private String limitReduceRow = " ";
	public String getLimitReduceRow(){
		return limitReduceRow;
	}
	public void setLimitReduceRow(String limitReduceRow){
		this.limitReduceRow = limitReduceRow;
	}

	//额度所属客户名称
	private String ownerPartyName = " ";
	public String getOwnerPartyName(){
		return ownerPartyName;
	}
	public void setOwnerPartyName(String ownerPartyName){
		this.ownerPartyName = ownerPartyName;
	}
	
	
	/**根据billtype数字得到中文汉字
	 * @throws SQLException */
	public String getBillTypeString(){
		ICommonService service = ServiceFactory.getCommonService();
		
		String billtp;
		try {
			if(this.getBillType()==null || "".equals(this.getBillType())) {
				return "";
			}
			billtp = service.getBillTypeStringbybilltype(this.getBillType());
			return billtp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**根据billclass数字得到中文汉字
	 * @throws SQLException */
	public String getBillClassString(){
		ICommonService service = ServiceFactory.getCommonService();
		
		String billcs;
		try {
			if(this.getBillClass()==null || "".equals(this.getBillClass())) {
				return "";
			}
			billcs = service.getBillClassStringbybillclass(this.getBillClass());
			return billcs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	
	/**
	 * 贴现记账行为
	 * @return
	 */
	public DiscBillInfo doDiscAccount(){
		this.gathMneyType=DiscCodeConst.GATH_TYPE_COMMON;
		this.gathMneyDate=null;
		this.totalIntrstPayment=0.0d;
		this.transType=DiscCodeConst.NORMAL;
		this.transId=null;
		this.curStatus=DiscCodeConst.CUR_STATUS_DISC;
		return this;
	}
	
	/**
	 * 贴现记账撤销行为
	 * @return
	 */
	public DiscBillInfo doDiscAccountReverse(){
		this.gathMneyType=DiscCodeConst.GATH_TYPE_DEFULT;
		this.gathMneyDate=" ";
		this.totalIntrstPayment=0.0d;
		this.transType=DiscCodeConst.DEFAULT;
		this.transId=" ";
		this.curStatus=" ";
		return this;
	}
}
