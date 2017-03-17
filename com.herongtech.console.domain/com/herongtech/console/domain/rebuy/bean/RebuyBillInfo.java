/********************************************
* 文件名称: RebuyBillInfo.java
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
package com.herongtech.console.domain.rebuy.bean;

import java.sql.SQLException;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.exception.impl.BizAppException;

public class RebuyBillInfo{
	private String rebuymxId = " ";	//转入明细流水号
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
	private String payee = " ";	//收款人名称
	private String payeeBankName = " ";	//收款人开户行名称
	private String payeeAcct = " ";	//收款人账号
	private String billBeforeOwner = " ";	//交易前手
	private String billOwner = " ";	//客户名称
	private String billClass = " ";	//票据分类
	private String billSource = " ";	//票据来源
	private String operStatus = " ";	//操作状态
	private String rebuyId = " ";	//转入流水号
	private String rgctId = " ";	//登记中心id
	private String custAccount = " ";	//客户账号
	private String isSameCity = " ";	//是否同城
	private double interest = 0.0;	//总利息
	private Long delayDays = 0l;	//顺延天数
	private String galeDate = " ";	//计息到期日
	private Long interestDays = 0l;	//计息天数
	private double payMoney = 0.0;	//实付金额
	private String remark = " ";	//备注
	private String createDate = " ";	//生成日期
	private String createTime = " ";	//生成时间
	private String isAudited = " ";	//是否审核
	private String draweeAddr = " ";	//付款人开户行地址
	private String payeeBankNo = " ";	//收款人开户行行号
	private String conferNo = " ";	//交易合同编号
	private String isAccpt = " ";	//是否我行承兑
	private String isInner = " ";	//是否系统内
	private String limitBillId = " ";	//额度编号
	private String limitProdNo = " ";	//额度扣减产品号
	private double rate = 0.0;	//利率
	private String rateType = " ";	//利率类型
	private String remitterCustNo = " ";	//出票人客户号
	private String channel = " ";	//渠道
	private String isDelayIn = " ";	//是否回购
	private String saleId = " ";	//卖出流水号
	private String isOnline = " ";	//是否线上清算
	private String forbidFlag = " ";	//禁止背书标记
	private String rebuyDt = " ";	//买入日
	private String resaleStaDt = " ";	//买入返售开放日
	private String resaleDueDt = " ";	//买入返售到期日
	private String limitType = " ";	//额度类型
	private String limitNo = " ";	//额度编号3
	private String billStorageOrg = " ";	//代保管机构号
	private String yzSource = " ";	//移植来源
	private String prodNo = " ";	//产品编码
	private String ownerPartyId = " ";	//额度扣减对象
	private String isCyc = " ";	//是否可循环
	private String productName = " ";	//额度品种
	private String ownerPartyName = " ";	//额度扣减对象名称
	private String isCycName = " ";	//是否可循环中文#
	private String exSerial = " ";	//记账前台流水号
	private String isBuyback = " ";	//是否回购记账完成
	private String rebuySquareId = " ";	//记账流水号
	private String isRediscCenter = " ";	//是否央行卖票
	private String payTradeNo = " ";	//付款交易号
	private String beginDate = " ";	//起息日
	private double checkInterest = 0.0;	//试算利息
	private String custNo = " ";	//客户号
	private String custBankNo = " ";	//客户所属行号
	private String custBankName = " ";	//客户所属行名
	private String delayType = " ";	//顺延类型
	private String isRegress = " ";	//是否回购标志
	private String facMsg = " ";	//额度扣减处理结果信息
	private String isAmount = " ";	//额度扣减是否成功
	private String endorsesBankNo = " ";	//票据中间背书人行号
	private String facOperType = " ";	//额度操作方式
	private String collDate = " ";	//发托日期
	private String accountDate = " ";	//记账日期
	private String accountTime = " ";	//记账时间
	private double totalIntrstPayment = 0.0;	//总计利息支出
	private String gathDate = " ";	//票款收回日期
	private String gathType = " ";	//票款收类型
	private String endorsesBankName = " ";	//票据中间背书人行名
	private String salemxId = " ";	//卖出明细流水号
	private double checkPayMoney = 0.0;	//试算实付金额
	private String adjustFlag = " ";	//调整标记
	private double interestSaleback1 = 0.0;	//卖出回购利息
	private double paymoneySaleback1 = 0.0;	//卖出回购金额
	private String applyCancelFlag = " ";	//申请撤销标记
	private String reqDraftNo = " ";	//原申请报文类型编号
	private String applyOperNo = " ";	//申请岗
	private String auditOperNo = " ";	//审核岗
	private String acctOperNo = " ";	//记账岗柜员
	private String custOrgCode = " ";	//客户组织机构代码
	private String position = " ";	//流转岗位
	private String custType = " ";	//客户类型
	private String acceptorBankNo = " ";	//承兑行行号
	private String acceptorBankName = " ";	//承兑人开户行名称
	private String curStatus = " ";	//票据当前状态
	private String transId = " ";	//票据流转id
	private String transType = " ";	//票据流转类型
	private String buybackId = " ";	//回购明细id
	private String isElecDeposit = " ";	//是否电票托管
	private String zcSource = " ";	//zc_source
	private String zcSourceId = " ";	//zc_source_id
	private String limitReduceRow = " ";	//limit_reduce_row
	private String applyCommitDate = " ";	//申请提交日期
	private String applyCommitTime = " ";	//申请提交时间
	private String branchNo = " ";	//操作机构
	private String operBankNo = " ";	//操作行号
	private String auditReason = " ";	//审核意见
	//银承协议号--假字段
	private String protocalNo = " ";
	
	public String getRebuymxId(){
		return rebuymxId;
	}
	public void setRebuymxId(String rebuymxId){
		this.rebuymxId = rebuymxId;
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

	public String getRebuyId(){
		return rebuyId;
	}
	public void setRebuyId(String rebuyId){
		this.rebuyId = rebuyId;
	}

	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	public String getCustAccount(){
		return custAccount;
	}
	public void setCustAccount(String custAccount){
		this.custAccount = custAccount;
	}

	public String getIsSameCity(){
		return isSameCity;
	}
	public void setIsSameCity(String isSameCity){
		this.isSameCity = isSameCity;
	}

	public double getInterest(){
		return interest;
	}
	public void setInterest(double interest){
		this.interest = interest;
	}

	public Long getDelayDays(){
		return delayDays;
	}
	public void setDelayDays(Long delayDays){
		this.delayDays = delayDays;
	}

	public String getGaleDate(){
		return galeDate;
	}
	public void setGaleDate(String galeDate){
		this.galeDate = galeDate;
	}

	public Long getInterestDays(){
		return interestDays;
	}
	public void setInterestDays(Long interestDays){
		this.interestDays = interestDays;
	}

	public double getPayMoney(){
		return payMoney;
	}
	public void setPayMoney(double payMoney){
		this.payMoney = payMoney;
	}

	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate;
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

	public String getIsInner(){
		return isInner;
	}
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}

	public String getLimitBillId(){
		return limitBillId;
	}
	public void setLimitBillId(String limitBillId){
		this.limitBillId = limitBillId;
	}

	public String getLimitProdNo(){
		return limitProdNo;
	}
	public void setLimitProdNo(String limitProdNo){
		this.limitProdNo = limitProdNo;
	}

	public double getRate(){
		return rate;
	}
	public void setRate(double rate){
		this.rate = rate;
	}

	public String getRateType(){
		return rateType;
	}
	public void setRateType(String rateType){
		this.rateType = rateType;
	}

	public String getRemitterCustNo(){
		return remitterCustNo;
	}
	public void setRemitterCustNo(String remitterCustNo){
		this.remitterCustNo = remitterCustNo;
	}

	public String getChannel(){
		return channel;
	}
	public void setChannel(String channel){
		this.channel = channel;
	}

	public String getIsDelayIn(){
		return isDelayIn;
	}
	public void setIsDelayIn(String isDelayIn){
		this.isDelayIn = isDelayIn;
	}

	public String getSaleId(){
		return saleId;
	}
	public void setSaleId(String saleId){
		this.saleId = saleId;
	}

	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline;
	}

	public String getForbidFlag(){
		return forbidFlag;
	}
	public void setForbidFlag(String forbidFlag){
		this.forbidFlag = forbidFlag;
	}
	/**根据禁止背书标记数值 获得汉字
	 * @throws SQLException */
	public String getForbidFlagString(){
		if(RebuyCodeConst.ENDORSE_FORBID_FACE.equals(this.getForbidFlag())){
			return "不可转让";
		}else if(RebuyCodeConst.ENDORSE_NO_FORBID.equals(this.getForbidFlag())){
			return "可转让";
		}else{
			return "";
		}
	}

	public String getRebuyDt(){
		return rebuyDt;
	}
	public void setRebuyDt(String rebuyDt){
		this.rebuyDt = rebuyDt;
	}

	public String getResaleStaDt(){
		return resaleStaDt;
	}
	public void setResaleStaDt(String resaleStaDt){
		this.resaleStaDt = resaleStaDt;
	}

	public String getResaleDueDt(){
		return resaleDueDt;
	}
	public void setResaleDueDt(String resaleDueDt){
		this.resaleDueDt = resaleDueDt;
	}

	public String getLimitType(){
		return limitType;
	}
	public void setLimitType(String limitType){
		this.limitType = limitType;
	}

	public String getLimitNo(){
		return limitNo;
	}
	public void setLimitNo(String limitNo){
		this.limitNo = limitNo;
	}

	public String getBillStorageOrg(){
		return billStorageOrg;
	}
	public void setBillStorageOrg(String billStorageOrg){
		this.billStorageOrg = billStorageOrg;
	}

	public String getYzSource(){
		return yzSource;
	}
	public void setYzSource(String yzSource){
		this.yzSource = yzSource;
	}

	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	public String getOwnerPartyId(){
		return ownerPartyId;
	}
	public void setOwnerPartyId(String ownerPartyId){
		this.ownerPartyId = ownerPartyId;
	}

	public String getIsCyc(){
		return isCyc;
	}
	public void setIsCyc(String isCyc){
		this.isCyc = isCyc;
	}

	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getOwnerPartyName(){
		return ownerPartyName;
	}
	public void setOwnerPartyName(String ownerPartyName){
		this.ownerPartyName = ownerPartyName;
	}

	public String getIsCycName(){
		return isCycName;
	}
	public void setIsCycName(String isCycName){
		this.isCycName = isCycName;
	}

	public String getExSerial(){
		return exSerial;
	}
	public void setExSerial(String exSerial){
		this.exSerial = exSerial;
	}

	public String getIsBuyback(){
		return isBuyback;
	}
	public void setIsBuyback(String isBuyback){
		this.isBuyback = isBuyback;
	}

	public String getRebuySquareId(){
		return rebuySquareId;
	}
	public void setRebuySquareId(String rebuySquareId){
		this.rebuySquareId = rebuySquareId;
	}

	public String getIsRediscCenter(){
		return isRediscCenter;
	}
	public void setIsRediscCenter(String isRediscCenter){
		this.isRediscCenter = isRediscCenter;
	}

	public String getPayTradeNo(){
		return payTradeNo;
	}
	public void setPayTradeNo(String payTradeNo){
		this.payTradeNo = payTradeNo;
	}

	public String getBeginDate(){
		return beginDate;
	}
	public void setBeginDate(String beginDate){
		this.beginDate = beginDate;
	}

	public double getCheckInterest(){
		return checkInterest;
	}
	public void setCheckInterest(double checkInterest){
		this.checkInterest = checkInterest;
	}

	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	public String getCustBankNo(){
		return custBankNo;
	}
	public void setCustBankNo(String custBankNo){
		this.custBankNo = custBankNo;
	}

	public String getCustBankName(){
		return custBankName;
	}
	public void setCustBankName(String custBankName){
		this.custBankName = custBankName;
	}

	public String getDelayType(){
		return delayType;
	}
	public void setDelayType(String delayType){
		this.delayType = delayType;
	}

	public String getIsRegress(){
		return isRegress;
	}
	public void setIsRegress(String isRegress){
		this.isRegress = isRegress;
	}

	public String getFacMsg(){
		return facMsg;
	}
	public void setFacMsg(String facMsg){
		this.facMsg = facMsg;
	}

	public String getIsAmount(){
		return isAmount;
	}
	public void setIsAmount(String isAmount){
		this.isAmount = isAmount;
	}

	public String getEndorsesBankNo(){
		return endorsesBankNo;
	}
	public void setEndorsesBankNo(String endorsesBankNo){
		this.endorsesBankNo = endorsesBankNo;
	}

	public String getFacOperType(){
		return facOperType;
	}
	public void setFacOperType(String facOperType){
		this.facOperType = facOperType;
	}

	public String getCollDate(){
		return collDate;
	}
	public void setCollDate(String collDate){
		this.collDate = collDate;
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

	public double getTotalIntrstPayment(){
		return totalIntrstPayment;
	}
	public void setTotalIntrstPayment(double totalIntrstPayment){
		this.totalIntrstPayment = totalIntrstPayment;
	}

	public String getGathDate(){
		return gathDate;
	}
	public void setGathDate(String gathDate){
		this.gathDate = gathDate;
	}

	public String getGathType(){
		return gathType;
	}
	public void setGathType(String gathType){
		this.gathType = gathType;
	}

	public String getEndorsesBankName(){
		return endorsesBankName;
	}
	public void setEndorsesBankName(String endorsesBankName){
		this.endorsesBankName = endorsesBankName;
	}

	public String getSalemxId(){
		return salemxId;
	}
	public void setSalemxId(String salemxId){
		this.salemxId = salemxId;
	}

	public double getCheckPayMoney(){
		return checkPayMoney;
	}
	public void setCheckPayMoney(double checkPayMoney){
		this.checkPayMoney = checkPayMoney;
	}

	public String getAdjustFlag(){
		return adjustFlag;
	}
	public void setAdjustFlag(String adjustFlag){
		this.adjustFlag = adjustFlag;
	}

	public double getInterestSaleback1(){
		return interestSaleback1;
	}
	public void setInterestSaleback1(double interestSaleback1){
		this.interestSaleback1 = interestSaleback1;
	}

	public double getPaymoneySaleback1(){
		return paymoneySaleback1;
	}
	public void setPaymoneySaleback1(double paymoneySaleback1){
		this.paymoneySaleback1 = paymoneySaleback1;
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

	public String getCustOrgCode(){
		return custOrgCode;
	}
	public void setCustOrgCode(String custOrgCode){
		this.custOrgCode = custOrgCode;
	}

	public String getPosition(){
		return position;
	}
	public void setPosition(String position){
		this.position = position;
	}

	public String getCustType(){
		return custType;
	}
	public void setCustType(String custType){
		this.custType = custType;
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

	public String getTransId(){
		return transId;
	}
	public void setTransId(String transId){
		this.transId = transId;
	}

	public String getTransType(){
		return transType;
	}
	public void setTransType(String transType){
		this.transType = transType;
	}

	public String getBuybackId(){
		return buybackId;
	}
	public void setBuybackId(String buybackId){
		this.buybackId = buybackId;
	}

	public String getIsElecDeposit(){
		return isElecDeposit;
	}
	public void setIsElecDeposit(String isElecDeposit){
		this.isElecDeposit = isElecDeposit;
	}

	public String getZcSource(){
		return zcSource;
	}
	public void setZcSource(String zcSource){
		this.zcSource = zcSource;
	}

	public String getZcSourceId(){
		return zcSourceId;
	}
	public void setZcSourceId(String zcSourceId){
		this.zcSourceId = zcSourceId;
	}

	public String getLimitReduceRow(){
		return limitReduceRow;
	}
	public void setLimitReduceRow(String limitReduceRow){
		this.limitReduceRow = limitReduceRow;
	}

	public String getApplyCommitDate(){
		return applyCommitDate;
	}
	public void setApplyCommitDate(String applyCommitDate){
		this.applyCommitDate = applyCommitDate;
	}

	public String getApplyCommitTime(){
		return applyCommitTime;
	}
	public void setApplyCommitTime(String applyCommitTime){
		this.applyCommitTime = applyCommitTime;
	}

	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	public String getOperBankNo(){
		return operBankNo;
	}
	public void setOperBankNo(String operBankNo){
		this.operBankNo = operBankNo;
	}

	public String getAuditReason(){
		return auditReason;
	}
	public void setAuditReason(String auditReason){
		this.auditReason = auditReason;
	}
	
	public String getProtocalNo() {
		return protocalNo;
	}
	public void setProtocalNo(String protocalNo) {
		this.protocalNo = protocalNo;
	}
	/**根据billtype数字得到中文汉字
	 * @throws BizAppException 
	 * @throws SQLException */
	public String getBillTypeString(){
		IRebuyService service = ServiceFactory.getRebuyService();			
		
		String billtp = "";
		if(this.getBillType()==null || "".equals(this.getBillType())) {
			return "";
		}
		try {
			billtp = service.getBillTypeStringbybilltype(this.getBillType());
		} catch (BizAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return billtp;
	}

}
