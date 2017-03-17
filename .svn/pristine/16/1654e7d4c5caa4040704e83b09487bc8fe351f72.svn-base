/********************************************
* 文件名称: RgctBillData.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: zcz
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.rc.domain.bean;

/**
 * 将RgctBillInfo与RgctBillHist合并成一个一个大对象，供外部使用
 * @author zcz
 * 
 */
public class RgctBillData implements java.io.Serializable{
	private String id = " ";	//主键
	private String histId = " ";	//登记中心最近历史id
	private String tempHistId = " ";	//临时历史id
	
	/**票据信息**/
	private String ebsNo = " ";	//电子商业汇票序列号
	private String billClass = " ";	//票据分类
	private String billType = " ";	//票据种类
	private String billNo = " ";	//票号
	private String issueDt = " ";	//出票日
	private String dueDt = " ";	//到期日
	private double billMoney = 0.0;	//票面金额
	private String isAccpt = " ";	//是否我行承兑
	
	/**出票人信息**/
	private String remitter = " ";	//出票人全称
	private String remitterCustNo = " ";	//出票人客户号
	private String remitterAcct = " ";	//出票人账号
	private String remitterSign = " ";	//出票人电子签名
	private String remitterBankNo = " ";	//出票人行号
	private String remitterBankName = " ";	//出票人行名
	private String remitterRemark = " ";	//出票人备注
	private String remitterRole = " ";	//出票人参与角色类型
	private String remitterOrgCode = " ";	//出票人组织机构代码
	private String drwrCreditrating = " ";	//出票人信用等级
	private String drwrCreditratingAgency = " ";	//出票人信用评级机构
	private String drwrCreditratingDuedt = " ";	//出票人信用评级到期日
	
	/**付款方信息**/
	private String draweeBankName = " ";	//付款人行名
	private String draweeBankNo = " ";	//付款行行号
	private String draweeBranchNo = " ";	//付款人开户机构
	private String draweeAddr = " ";	//付款人开户行地址
	
	/**收款人信息**/
	private String payeeName = " ";	//收款人名称
	private String payeeAcct = " ";	//收款人账号
	private String payeeBankNo = " ";	//收款人开户行行号
	private String payeeBankName = " ";	//收款人行名
	private String payeeOrgCode = " ";	//收款人组织机构代码
	
	/**承兑人信息**/
	private String acceptorDate = " ";	//承兑日期
	private String acceptor = " ";	//承兑方全称
	private String acceptorCustNo = " ";	//承兑人客户号
	private String acceptorAcct = " ";	//承兑人帐号
	private String acceptorBankNo = " ";	//承兑人开户行行号
	private String acceptorBankName = " ";	//承兑人开户行名称
	private String acceptorRole = " ";	//承兑人参与角色类型
	private String acceptorOrgCode = " ";	//承兑人组织机构代码
	private String acceptorSign = " ";	//承兑人电子签名
	private String acptrCreditrating = " ";	//承兑人信用评估等级
	private String acptrCreditratingAgency = " ";	//承兑人信用评估等级机构
	private String acptrCreditratingDuedt = " ";	//承兑人信用评估等级到期日
	
	private String assureBankName = " ";	//保贴行全称
	private String assureBankNo = " ";	//保贴行行号
	private String letterNo = " ";	//保贴函编号
	private String assureSelf = " ";	//是否本行保贴
	private String billSource = " ";	//票据来源字段
	
	private String invoiceNo = " ";	//发票号码
	private String conferNo = " ";	//交易合同编号
	private String infoForbidFlag = " ";	//正面禁止背书标志
	private String deductFlag = " ";	//扣款标记
	private String isAcptAcct = " ";	//是否承兑完成
	private String billUsage = " ";	//票据用途
	private String remark = " ";	//备注
	private String cancelReason = " ";	//退票原因
	private String cancelRemark = " ";	//退票补充说明
	private String createDt = " ";	//创建日期
	private String createTime = " ";	//创建时间
	private String delFlag = " ";	//删除状态
	private Long busiDeep = 0l;	//回购嵌套次数
	
	private String recourseFlag = " ";	//是否清偿标记
	
	
	private String reqDraftId = " ";	//请求报文id
	private String respDraftId = " ";	//应答报文id
	
	private String rgctId = " ";	//登记中心id
	private String curStatus = " ";	//票据当前状态
	private String preStatus = " ";	//票据前阶段状态#
	private String runStatus = " ";	//运行状态#
	private String batchId = " ";	//批次号
	private String fromName = " ";	//背书人全称
	private String fromCustNo = " ";	//背书人客户号
	private String fromAcctNo = " ";	//背书人账号
	private String fromBankNo = " ";	//背书人行号
	private String fromBranchNo = " ";	//背书人机构号
	private String fromSign = " ";	//背书人电子签名
	private String toName = " ";	//被背书人全称
	private String toCustNo = " ";	//被背书人客户号
	private String toAcctNo = " ";	//被背书人账号
	private String toBankNo = " ";	//被背书人行号
	private String toBranchNo = " ";	//被背书人机构号
	private String signDt = " ";	//签收日期
	private String signTime = " ";	//签收时间
	private double interestRate = 0.0;	//利率
	private double interest = 0.0;	//总利息
	private double dealMoney = 0.0;	//总金额
	private String isRegress = " ";	//回购标记
	private String regressDt = " ";	//回购日期
	private String regressTime = " ";	//回购时间
	private double regressPrice = 0.0;	//回购价格
	private String backOpenDt = " ";	//赎回开放日
	private String backEndDt = " ";	//赎回截止日
	private double backRate = 0.0;	//赎回利率
	private double backAmount = 0.0;	//赎回实付金额
	private String ifInner = " ";	//是否系统内
	private String isBuy = " ";	//是否买入
	private String signer = " ";	//签收人名称
	private String signerSign = " ";	//签收人电子签名
	private String signFlag = " ";	//签收标识
	private String holdCustNo = " ";	//票据持有人客户号
	private String holdCustName = " ";	//票据持有人名称
	private String holdAcctNo = " ";	//票据持有人账号
	private String holdBankNo = " ";	//票据持有人行号
	private String obligeeCustNo = " ";	//当前权利人客户号
	private String obligeeCustName = " ";	//当前权利人客户名称
	private String obligeeBankNo = " ";	//当前权利人行号
	private String obligeeAcctNo = " ";	//当前权利人账号
	private String returnCode = " ";	//返回码
	private String operDate = " ";	//操作日期
	private String operTime = " ";	//操作时间
	private String operNo = " ";	//操作柜员
	private String channel = " ";	//渠道
	private String isLock = " ";	//是否锁定
	private String lockBranchNo = " ";	//锁定机构
	private String lockFlowName = " ";	//锁定流程名称
	private String stepName = " ";	//当前步骤
	private String validHistId = " ";	//有效历史id#
	private String lastHistId = " ";	//上次交易id
	private String isOnline = " ";	//线上清算标识
	private String overdueRs = " ";	//逾期原因
	private String draftLogId = " ";	//报文日志id
	private String rejectCode = " ";	//拒付代码
	private String rejectReason = " ";	//拒付原因
	private String isRediscCenter = " ";	//是否再贴现
	private String assuId = " ";	//保证id
	private String recourseId = " ";	//追索id
	private String isDelegate = " ";	//是否待客托收
	private String inAcctNo = " ";	//入账帐号
	private String inBankNo = " ";	//入账行号
	private String partnerCode = " ";	//partner_code
	private String payTradeNo = " ";	//支付交易序号
	private String forbidFlag = " ";	//禁止背书标志
	private String fromRemark = " ";	//背书人备注
	private String toRemark = " ";	//被背书人备注
	private String draftInfo = " ";	//报文内容
	private double oldInterestRate = 0.0;	//上一次贴现/转入利率
	private String oldDisDt = " ";	//上一次贴现/转入计息到期日
	private String delayDays = " ";	//顺延天数
	private String delayType = " ";	//顺延方式
	private String billTrack = " ";	//票据系统轨迹#
	private String isSameCity = " ";	//是否同城市
	private String interestDueDt = " ";	//计息到期日
	private Long interestDays = 0l;	//计息天数
	private double firstbuyInterest = 0.0;	//最初利率
	private String fromOrgcode = " ";	//背书人组织机构代码
	private String toOrgcode = " ";	//被背书人组织机构代码
	private String fromRole = " ";	//背书人参与者类型
	private String toRole = " ";	//被背书人参与者类型
	private String billBeforeOwner = " ";	//票据前手
	private String prodAttr = " ";	//prod_attr
	private String acctBranchNo = " ";	//账务机构号
	private String storageBranchNo = " ";	//存放机构号
	private String buyType = " ";	//买入类型
	private String workingadsNo = " ";	//经营机构
	private String workingadsName = " ";	//经营机构归属名称
	private String storageBranchName = " ";	//存放机构名称
	private String endoHistId = " ";	//背书历史id
	private String endorsesBankName = " ";	//背书行行名
	private String endorsesBankNo = " ";	//背书行行号
	private String endorseDt = " ";	//申请日期
	private String endoStatus = " ";	//背书状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHistId() {
		return histId;
	}
	public void setHistId(String histId) {
		this.histId = histId;
	}
	public String getTempHistId() {
		return tempHistId;
	}
	public void setTempHistId(String tempHistId) {
		this.tempHistId = tempHistId;
	}
	public String getEbsNo() {
		return ebsNo;
	}
	public void setEbsNo(String ebsNo) {
		this.ebsNo = ebsNo;
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
	public String getIssueDt() {
		return issueDt;
	}
	public void setIssueDt(String issueDt) {
		this.issueDt = issueDt;
	}
	public String getDueDt() {
		return dueDt;
	}
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}
	public double getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}
	public String getIsAccpt() {
		return isAccpt;
	}
	public void setIsAccpt(String isAccpt) {
		this.isAccpt = isAccpt;
	}
	public String getRemitter() {
		return remitter;
	}
	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}
	public String getRemitterCustNo() {
		return remitterCustNo;
	}
	public void setRemitterCustNo(String remitterCustNo) {
		this.remitterCustNo = remitterCustNo;
	}
	public String getRemitterAcct() {
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct) {
		this.remitterAcct = remitterAcct;
	}
	public String getRemitterSign() {
		return remitterSign;
	}
	public void setRemitterSign(String remitterSign) {
		this.remitterSign = remitterSign;
	}
	public String getRemitterBankNo() {
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo) {
		this.remitterBankNo = remitterBankNo;
	}
	public String getRemitterBankName() {
		return remitterBankName;
	}
	public void setRemitterBankName(String remitterBankName) {
		this.remitterBankName = remitterBankName;
	}
	public String getRemitterRemark() {
		return remitterRemark;
	}
	public void setRemitterRemark(String remitterRemark) {
		this.remitterRemark = remitterRemark;
	}
	public String getRemitterRole() {
		return remitterRole;
	}
	public void setRemitterRole(String remitterRole) {
		this.remitterRole = remitterRole;
	}
	public String getRemitterOrgCode() {
		return remitterOrgCode;
	}
	public void setRemitterOrgCode(String remitterOrgCode) {
		this.remitterOrgCode = remitterOrgCode;
	}
	public String getDrwrCreditrating() {
		return drwrCreditrating;
	}
	public void setDrwrCreditrating(String drwrCreditrating) {
		this.drwrCreditrating = drwrCreditrating;
	}
	public String getDrwrCreditratingAgency() {
		return drwrCreditratingAgency;
	}
	public void setDrwrCreditratingAgency(String drwrCreditratingAgency) {
		this.drwrCreditratingAgency = drwrCreditratingAgency;
	}
	public String getDrwrCreditratingDuedt() {
		return drwrCreditratingDuedt;
	}
	public void setDrwrCreditratingDuedt(String drwrCreditratingDuedt) {
		this.drwrCreditratingDuedt = drwrCreditratingDuedt;
	}
	public String getDraweeBankName() {
		return draweeBankName;
	}
	public void setDraweeBankName(String draweeBankName) {
		this.draweeBankName = draweeBankName;
	}
	public String getDraweeBankNo() {
		return draweeBankNo;
	}
	public void setDraweeBankNo(String draweeBankNo) {
		this.draweeBankNo = draweeBankNo;
	}
	public String getDraweeBranchNo() {
		return draweeBranchNo;
	}
	public void setDraweeBranchNo(String draweeBranchNo) {
		this.draweeBranchNo = draweeBranchNo;
	}
	public String getDraweeAddr() {
		return draweeAddr;
	}
	public void setDraweeAddr(String draweeAddr) {
		this.draweeAddr = draweeAddr;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeAcct() {
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct) {
		this.payeeAcct = payeeAcct;
	}
	public String getPayeeBankNo() {
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo) {
		this.payeeBankNo = payeeBankNo;
	}
	public String getPayeeBankName() {
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}
	public String getPayeeOrgCode() {
		return payeeOrgCode;
	}
	public void setPayeeOrgCode(String payeeOrgCode) {
		this.payeeOrgCode = payeeOrgCode;
	}
	public String getAcceptorDate() {
		return acceptorDate;
	}
	public void setAcceptorDate(String acceptorDate) {
		this.acceptorDate = acceptorDate;
	}
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getAcceptorCustNo() {
		return acceptorCustNo;
	}
	public void setAcceptorCustNo(String acceptorCustNo) {
		this.acceptorCustNo = acceptorCustNo;
	}
	public String getAcceptorAcct() {
		return acceptorAcct;
	}
	public void setAcceptorAcct(String acceptorAcct) {
		this.acceptorAcct = acceptorAcct;
	}
	public String getAcceptorBankNo() {
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo) {
		this.acceptorBankNo = acceptorBankNo;
	}
	public String getAcceptorBankName() {
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName) {
		this.acceptorBankName = acceptorBankName;
	}
	public String getAcceptorRole() {
		return acceptorRole;
	}
	public void setAcceptorRole(String acceptorRole) {
		this.acceptorRole = acceptorRole;
	}
	public String getAcceptorOrgCode() {
		return acceptorOrgCode;
	}
	public void setAcceptorOrgCode(String acceptorOrgCode) {
		this.acceptorOrgCode = acceptorOrgCode;
	}
	public String getAcceptorSign() {
		return acceptorSign;
	}
	public void setAcceptorSign(String acceptorSign) {
		this.acceptorSign = acceptorSign;
	}
	public String getAcptrCreditrating() {
		return acptrCreditrating;
	}
	public void setAcptrCreditrating(String acptrCreditrating) {
		this.acptrCreditrating = acptrCreditrating;
	}
	public String getAcptrCreditratingAgency() {
		return acptrCreditratingAgency;
	}
	public void setAcptrCreditratingAgency(String acptrCreditratingAgency) {
		this.acptrCreditratingAgency = acptrCreditratingAgency;
	}
	public String getAcptrCreditratingDuedt() {
		return acptrCreditratingDuedt;
	}
	public void setAcptrCreditratingDuedt(String acptrCreditratingDuedt) {
		this.acptrCreditratingDuedt = acptrCreditratingDuedt;
	}
	public String getAssureBankName() {
		return assureBankName;
	}
	public void setAssureBankName(String assureBankName) {
		this.assureBankName = assureBankName;
	}
	public String getAssureBankNo() {
		return assureBankNo;
	}
	public void setAssureBankNo(String assureBankNo) {
		this.assureBankNo = assureBankNo;
	}
	public String getLetterNo() {
		return letterNo;
	}
	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}
	public String getAssureSelf() {
		return assureSelf;
	}
	public void setAssureSelf(String assureSelf) {
		this.assureSelf = assureSelf;
	}
	public String getBillSource() {
		return billSource;
	}
	public void setBillSource(String billSource) {
		this.billSource = billSource;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getConferNo() {
		return conferNo;
	}
	public void setConferNo(String conferNo) {
		this.conferNo = conferNo;
	}
	public String getInfoForbidFlag() {
		return infoForbidFlag;
	}
	public void setInfoForbidFlag(String infoForbidFlag) {
		this.infoForbidFlag = infoForbidFlag;
	}
	public String getDeductFlag() {
		return deductFlag;
	}
	public void setDeductFlag(String deductFlag) {
		this.deductFlag = deductFlag;
	}
	public String getIsAcptAcct() {
		return isAcptAcct;
	}
	public void setIsAcptAcct(String isAcptAcct) {
		this.isAcptAcct = isAcptAcct;
	}
	public String getBillUsage() {
		return billUsage;
	}
	public void setBillUsage(String billUsage) {
		this.billUsage = billUsage;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getCancelRemark() {
		return cancelRemark;
	}
	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Long getBusiDeep() {
		return busiDeep;
	}
	public void setBusiDeep(Long busiDeep) {
		this.busiDeep = busiDeep;
	}
	public String getRecourseFlag() {
		return recourseFlag;
	}
	public void setRecourseFlag(String recourseFlag) {
		this.recourseFlag = recourseFlag;
	}
	public String getReqDraftId() {
		return reqDraftId;
	}
	public void setReqDraftId(String reqDraftId) {
		this.reqDraftId = reqDraftId;
	}
	public String getRespDraftId() {
		return respDraftId;
	}
	public void setRespDraftId(String respDraftId) {
		this.respDraftId = respDraftId;
	}
	public String getRgctId() {
		return rgctId;
	}
	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	public String getCurStatus() {
		return curStatus;
	}
	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}
	public String getPreStatus() {
		return preStatus;
	}
	public void setPreStatus(String preStatus) {
		this.preStatus = preStatus;
	}
	public String getRunStatus() {
		return runStatus;
	}
	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromCustNo() {
		return fromCustNo;
	}
	public void setFromCustNo(String fromCustNo) {
		this.fromCustNo = fromCustNo;
	}
	public String getFromAcctNo() {
		return fromAcctNo;
	}
	public void setFromAcctNo(String fromAcctNo) {
		this.fromAcctNo = fromAcctNo;
	}
	public String getFromBankNo() {
		return fromBankNo;
	}
	public void setFromBankNo(String fromBankNo) {
		this.fromBankNo = fromBankNo;
	}
	public String getFromBranchNo() {
		return fromBranchNo;
	}
	public void setFromBranchNo(String fromBranchNo) {
		this.fromBranchNo = fromBranchNo;
	}
	public String getFromSign() {
		return fromSign;
	}
	public void setFromSign(String fromSign) {
		this.fromSign = fromSign;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getToCustNo() {
		return toCustNo;
	}
	public void setToCustNo(String toCustNo) {
		this.toCustNo = toCustNo;
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
	public String getToBranchNo() {
		return toBranchNo;
	}
	public void setToBranchNo(String toBranchNo) {
		this.toBranchNo = toBranchNo;
	}
	public String getSignDt() {
		return signDt;
	}
	public void setSignDt(String signDt) {
		this.signDt = signDt;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getDealMoney() {
		return dealMoney;
	}
	public void setDealMoney(double dealMoney) {
		this.dealMoney = dealMoney;
	}
	public String getIsRegress() {
		return isRegress;
	}
	public void setIsRegress(String isRegress) {
		this.isRegress = isRegress;
	}
	public String getRegressDt() {
		return regressDt;
	}
	public void setRegressDt(String regressDt) {
		this.regressDt = regressDt;
	}
	public String getRegressTime() {
		return regressTime;
	}
	public void setRegressTime(String regressTime) {
		this.regressTime = regressTime;
	}
	public double getRegressPrice() {
		return regressPrice;
	}
	public void setRegressPrice(double regressPrice) {
		this.regressPrice = regressPrice;
	}
	public String getBackOpenDt() {
		return backOpenDt;
	}
	public void setBackOpenDt(String backOpenDt) {
		this.backOpenDt = backOpenDt;
	}
	public String getBackEndDt() {
		return backEndDt;
	}
	public void setBackEndDt(String backEndDt) {
		this.backEndDt = backEndDt;
	}
	public double getBackRate() {
		return backRate;
	}
	public void setBackRate(double backRate) {
		this.backRate = backRate;
	}
	public double getBackAmount() {
		return backAmount;
	}
	public void setBackAmount(double backAmount) {
		this.backAmount = backAmount;
	}
	public String getIfInner() {
		return ifInner;
	}
	public void setIfInner(String ifInner) {
		this.ifInner = ifInner;
	}
	public String getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(String isBuy) {
		this.isBuy = isBuy;
	}
	public String getSigner() {
		return signer;
	}
	public void setSigner(String signer) {
		this.signer = signer;
	}
	public String getSignerSign() {
		return signerSign;
	}
	public void setSignerSign(String signerSign) {
		this.signerSign = signerSign;
	}
	public String getSignFlag() {
		return signFlag;
	}
	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}
	public String getHoldCustNo() {
		return holdCustNo;
	}
	public void setHoldCustNo(String holdCustNo) {
		this.holdCustNo = holdCustNo;
	}
	public String getHoldCustName() {
		return holdCustName;
	}
	public void setHoldCustName(String holdCustName) {
		this.holdCustName = holdCustName;
	}
	public String getHoldAcctNo() {
		return holdAcctNo;
	}
	public void setHoldAcctNo(String holdAcctNo) {
		this.holdAcctNo = holdAcctNo;
	}
	public String getHoldBankNo() {
		return holdBankNo;
	}
	public void setHoldBankNo(String holdBankNo) {
		this.holdBankNo = holdBankNo;
	}
	public String getObligeeCustNo() {
		return obligeeCustNo;
	}
	public void setObligeeCustNo(String obligeeCustNo) {
		this.obligeeCustNo = obligeeCustNo;
	}
	public String getObligeeCustName() {
		return obligeeCustName;
	}
	public void setObligeeCustName(String obligeeCustName) {
		this.obligeeCustName = obligeeCustName;
	}
	public String getObligeeBankNo() {
		return obligeeBankNo;
	}
	public void setObligeeBankNo(String obligeeBankNo) {
		this.obligeeBankNo = obligeeBankNo;
	}
	public String getObligeeAcctNo() {
		return obligeeAcctNo;
	}
	public void setObligeeAcctNo(String obligeeAcctNo) {
		this.obligeeAcctNo = obligeeAcctNo;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	public String getOperNo() {
		return operNo;
	}
	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getIsLock() {
		return isLock;
	}
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}
	public String getLockBranchNo() {
		return lockBranchNo;
	}
	public void setLockBranchNo(String lockBranchNo) {
		this.lockBranchNo = lockBranchNo;
	}
	public String getLockFlowName() {
		return lockFlowName;
	}
	public void setLockFlowName(String lockFlowName) {
		this.lockFlowName = lockFlowName;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getValidHistId() {
		return validHistId;
	}
	public void setValidHistId(String validHistId) {
		this.validHistId = validHistId;
	}
	public String getLastHistId() {
		return lastHistId;
	}
	public void setLastHistId(String lastHistId) {
		this.lastHistId = lastHistId;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public String getOverdueRs() {
		return overdueRs;
	}
	public void setOverdueRs(String overdueRs) {
		this.overdueRs = overdueRs;
	}
	public String getDraftLogId() {
		return draftLogId;
	}
	public void setDraftLogId(String draftLogId) {
		this.draftLogId = draftLogId;
	}
	public String getRejectCode() {
		return rejectCode;
	}
	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getIsRediscCenter() {
		return isRediscCenter;
	}
	public void setIsRediscCenter(String isRediscCenter) {
		this.isRediscCenter = isRediscCenter;
	}
	public String getAssuId() {
		return assuId;
	}
	public void setAssuId(String assuId) {
		this.assuId = assuId;
	}
	public String getRecourseId() {
		return recourseId;
	}
	public void setRecourseId(String recourseId) {
		this.recourseId = recourseId;
	}
	public String getIsDelegate() {
		return isDelegate;
	}
	public void setIsDelegate(String isDelegate) {
		this.isDelegate = isDelegate;
	}
	public String getInAcctNo() {
		return inAcctNo;
	}
	public void setInAcctNo(String inAcctNo) {
		this.inAcctNo = inAcctNo;
	}
	public String getInBankNo() {
		return inBankNo;
	}
	public void setInBankNo(String inBankNo) {
		this.inBankNo = inBankNo;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getPayTradeNo() {
		return payTradeNo;
	}
	public void setPayTradeNo(String payTradeNo) {
		this.payTradeNo = payTradeNo;
	}
	public String getForbidFlag() {
		return forbidFlag;
	}
	public void setForbidFlag(String forbidFlag) {
		this.forbidFlag = forbidFlag;
	}
	public String getFromRemark() {
		return fromRemark;
	}
	public void setFromRemark(String fromRemark) {
		this.fromRemark = fromRemark;
	}
	public String getToRemark() {
		return toRemark;
	}
	public void setToRemark(String toRemark) {
		this.toRemark = toRemark;
	}
	public String getDraftInfo() {
		return draftInfo;
	}
	public void setDraftInfo(String draftInfo) {
		this.draftInfo = draftInfo;
	}
	public double getOldInterestRate() {
		return oldInterestRate;
	}
	public void setOldInterestRate(double oldInterestRate) {
		this.oldInterestRate = oldInterestRate;
	}
	public String getOldDisDt() {
		return oldDisDt;
	}
	public void setOldDisDt(String oldDisDt) {
		this.oldDisDt = oldDisDt;
	}
	public String getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}
	public String getDelayType() {
		return delayType;
	}
	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}
	public String getBillTrack() {
		return billTrack;
	}
	public void setBillTrack(String billTrack) {
		this.billTrack = billTrack;
	}
	public String getIsSameCity() {
		return isSameCity;
	}
	public void setIsSameCity(String isSameCity) {
		this.isSameCity = isSameCity;
	}
	public String getInterestDueDt() {
		return interestDueDt;
	}
	public void setInterestDueDt(String interestDueDt) {
		this.interestDueDt = interestDueDt;
	}
	public Long getInterestDays() {
		return interestDays;
	}
	public void setInterestDays(Long interestDays) {
		this.interestDays = interestDays;
	}
	public double getFirstbuyInterest() {
		return firstbuyInterest;
	}
	public void setFirstbuyInterest(double firstbuyInterest) {
		this.firstbuyInterest = firstbuyInterest;
	}
	public String getFromOrgcode() {
		return fromOrgcode;
	}
	public void setFromOrgcode(String fromOrgcode) {
		this.fromOrgcode = fromOrgcode;
	}
	public String getToOrgcode() {
		return toOrgcode;
	}
	public void setToOrgcode(String toOrgcode) {
		this.toOrgcode = toOrgcode;
	}
	public String getFromRole() {
		return fromRole;
	}
	public void setFromRole(String fromRole) {
		this.fromRole = fromRole;
	}
	public String getToRole() {
		return toRole;
	}
	public void setToRole(String toRole) {
		this.toRole = toRole;
	}
	public String getBillBeforeOwner() {
		return billBeforeOwner;
	}
	public void setBillBeforeOwner(String billBeforeOwner) {
		this.billBeforeOwner = billBeforeOwner;
	}
	public String getProdAttr() {
		return prodAttr;
	}
	public void setProdAttr(String prodAttr) {
		this.prodAttr = prodAttr;
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
	public String getBuyType() {
		return buyType;
	}
	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}
	public String getWorkingadsNo() {
		return workingadsNo;
	}
	public void setWorkingadsNo(String workingadsNo) {
		this.workingadsNo = workingadsNo;
	}
	public String getWorkingadsName() {
		return workingadsName;
	}
	public void setWorkingadsName(String workingadsName) {
		this.workingadsName = workingadsName;
	}
	public String getStorageBranchName() {
		return storageBranchName;
	}
	public void setStorageBranchName(String storageBranchName) {
		this.storageBranchName = storageBranchName;
	}
	public String getEndoHistId() {
		return endoHistId;
	}
	public void setEndoHistId(String endoHistId) {
		this.endoHistId = endoHistId;
	}
	public String getEndorsesBankName() {
		return endorsesBankName;
	}
	public void setEndorsesBankName(String endorsesBankName) {
		this.endorsesBankName = endorsesBankName;
	}
	public String getEndorsesBankNo() {
		return endorsesBankNo;
	}
	public void setEndorsesBankNo(String endorsesBankNo) {
		this.endorsesBankNo = endorsesBankNo;
	}
	public String getEndorseDt() {
		return endorseDt;
	}
	public void setEndorseDt(String endorseDt) {
		this.endorseDt = endorseDt;
	}
	public String getEndoStatus() {
		return endoStatus;
	}
	public void setEndoStatus(String endoStatus) {
		this.endoStatus = endoStatus;
	}
	
	
	
	
}
