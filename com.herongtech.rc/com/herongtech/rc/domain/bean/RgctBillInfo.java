/********************************************
* 文件名称: RgctBillInfo.java
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


public class RgctBillInfo{
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
	private String billSource = " ";	//票据来源字段(网银录入/银行录入/人行转发)
	
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
	private String curStatus = " ";	//票据当前状态
	private String acctBranchNo = " ";	//账务机构号
	private String storageBranchNo = " ";	//存放机构号
	
	private String reqDraftId = " ";	//请求报文id
	private String respDraftId = " ";	//应答报文id
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getHistId(){
		return histId;
	}
	public void setHistId(String histId){
		this.histId = histId;
	}

	public String getTempHistId(){
		return tempHistId;
	}
	public void setTempHistId(String tempHistId){
		this.tempHistId = tempHistId;
	}

	public String getEbsNo(){
		return ebsNo;
	}
	public void setEbsNo(String ebsNo){
		this.ebsNo = ebsNo;
	}

	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
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

	public String getIsAccpt(){
		return isAccpt;
	}
	public void setIsAccpt(String isAccpt){
		this.isAccpt = isAccpt;
	}

	public String getRemitter(){
		return remitter;
	}
	public void setRemitter(String remitter){
		this.remitter = remitter;
	}

	public String getRemitterCustNo(){
		return remitterCustNo;
	}
	public void setRemitterCustNo(String remitterCustNo){
		this.remitterCustNo = remitterCustNo;
	}

	public String getRemitterAcct(){
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct){
		this.remitterAcct = remitterAcct;
	}

	public String getRemitterSign(){
		return remitterSign;
	}
	public void setRemitterSign(String remitterSign){
		this.remitterSign = remitterSign;
	}

	public String getRemitterBankNo(){
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo){
		this.remitterBankNo = remitterBankNo;
	}

	public String getRemitterBankName(){
		return remitterBankName;
	}
	public void setRemitterBankName(String remitterBankName){
		this.remitterBankName = remitterBankName;
	}

	public String getDraweeBankName(){
		return draweeBankName;
	}
	public void setDraweeBankName(String draweeBankName){
		this.draweeBankName = draweeBankName;
	}

	public String getDraweeBankNo(){
		return draweeBankNo;
	}
	public void setDraweeBankNo(String draweeBankNo){
		this.draweeBankNo = draweeBankNo;
	}

	public String getDraweeBranchNo(){
		return draweeBranchNo;
	}
	public void setDraweeBranchNo(String draweeBranchNo){
		this.draweeBranchNo = draweeBranchNo;
	}

	public String getDraweeAddr(){
		return draweeAddr;
	}
	public void setDraweeAddr(String draweeAddr){
		this.draweeAddr = draweeAddr;
	}

	public String getPayeeName(){
		return payeeName;
	}
	public void setPayeeName(String payeeName){
		this.payeeName = payeeName;
	}

	public String getPayeeAcct(){
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct){
		this.payeeAcct = payeeAcct;
	}

	public String getPayeeBankNo(){
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo){
		this.payeeBankNo = payeeBankNo;
	}

	public String getPayeeBankName(){
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName){
		this.payeeBankName = payeeBankName;
	}

	public String getAcceptor(){
		return acceptor;
	}
	public void setAcceptor(String acceptor){
		this.acceptor = acceptor;
	}

	public String getAcceptorCustNo(){
		return acceptorCustNo;
	}
	public void setAcceptorCustNo(String acceptorCustNo){
		this.acceptorCustNo = acceptorCustNo;
	}

	public String getAcceptorAcct(){
		return acceptorAcct;
	}
	public void setAcceptorAcct(String acceptorAcct){
		this.acceptorAcct = acceptorAcct;
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

	public String getAcceptorSign(){
		return acceptorSign;
	}
	public void setAcceptorSign(String acceptorSign){
		this.acceptorSign = acceptorSign;
	}

	public String getAssureBankName(){
		return assureBankName;
	}
	public void setAssureBankName(String assureBankName){
		this.assureBankName = assureBankName;
	}

	public String getAssureBankNo(){
		return assureBankNo;
	}
	public void setAssureBankNo(String assureBankNo){
		this.assureBankNo = assureBankNo;
	}

	public String getLetterNo(){
		return letterNo;
	}
	public void setLetterNo(String letterNo){
		this.letterNo = letterNo;
	}

	public String getAssureSelf(){
		return assureSelf;
	}
	public void setAssureSelf(String assureSelf){
		this.assureSelf = assureSelf;
	}

	public String getBillSource(){
		return billSource;
	}
	public void setBillSource(String billSource){
		this.billSource = billSource;
	}

	public double getBillMoney(){
		return billMoney;
	}
	public void setBillMoney(double billMoney){
		this.billMoney = billMoney;
	}

	public String getDueDt(){
		return dueDt;
	}
	public void setDueDt(String dueDt){
		this.dueDt = dueDt;
	}

	public String getConferNo(){
		return conferNo;
	}
	public void setConferNo(String conferNo){
		this.conferNo = conferNo;
	}

	public String getInfoForbidFlag(){
		return infoForbidFlag;
	}
	public void setInfoForbidFlag(String infoForbidFlag){
		this.infoForbidFlag = infoForbidFlag;
	}

	public String getDeductFlag(){
		return deductFlag;
	}
	public void setDeductFlag(String deductFlag){
		this.deductFlag = deductFlag;
	}

	public String getIsAcptAcct(){
		return isAcptAcct;
	}
	public void setIsAcptAcct(String isAcptAcct){
		this.isAcptAcct = isAcptAcct;
	}

	public String getBillUsage(){
		return billUsage;
	}
	public void setBillUsage(String billUsage){
		this.billUsage = billUsage;
	}

	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getCancelReason(){
		return cancelReason;
	}
	public void setCancelReason(String cancelReason){
		this.cancelReason = cancelReason;
	}

	public String getCancelRemark(){
		return cancelRemark;
	}
	public void setCancelRemark(String cancelRemark){
		this.cancelRemark = cancelRemark;
	}

	public String getCreateDt(){
		return createDt;
	}
	public void setCreateDt(String createDt){
		this.createDt = createDt;
	}

	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getDelFlag(){
		return delFlag;
	}
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}

	public String getInvoiceNo(){
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo){
		this.invoiceNo = invoiceNo;
	}

	public String getRemitterRemark(){
		return remitterRemark;
	}
	public void setRemitterRemark(String remitterRemark){
		this.remitterRemark = remitterRemark;
	}

	public String getDrwrCreditrating(){
		return drwrCreditrating;
	}
	public void setDrwrCreditrating(String drwrCreditrating){
		this.drwrCreditrating = drwrCreditrating;
	}

	public String getDrwrCreditratingAgency(){
		return drwrCreditratingAgency;
	}
	public void setDrwrCreditratingAgency(String drwrCreditratingAgency){
		this.drwrCreditratingAgency = drwrCreditratingAgency;
	}

	public String getDrwrCreditratingDuedt(){
		return drwrCreditratingDuedt;
	}
	public void setDrwrCreditratingDuedt(String drwrCreditratingDuedt){
		this.drwrCreditratingDuedt = drwrCreditratingDuedt;
	}

	public String getAcptrCreditrating(){
		return acptrCreditrating;
	}
	public void setAcptrCreditrating(String acptrCreditrating){
		this.acptrCreditrating = acptrCreditrating;
	}

	public String getAcptrCreditratingAgency(){
		return acptrCreditratingAgency;
	}
	public void setAcptrCreditratingAgency(String acptrCreditratingAgency){
		this.acptrCreditratingAgency = acptrCreditratingAgency;
	}

	public String getAcptrCreditratingDuedt(){
		return acptrCreditratingDuedt;
	}
	public void setAcptrCreditratingDuedt(String acptrCreditratingDuedt){
		this.acptrCreditratingDuedt = acptrCreditratingDuedt;
	}

	public Long getBusiDeep(){
		return busiDeep;
	}
	public void setBusiDeep(Long busiDeep){
		this.busiDeep = busiDeep;
	}

	public String getAcceptorDate(){
		return acceptorDate;
	}
	public void setAcceptorDate(String acceptorDate){
		this.acceptorDate = acceptorDate;
	}

	public String getRecourseFlag(){
		return recourseFlag;
	}
	public void setRecourseFlag(String recourseFlag){
		this.recourseFlag = recourseFlag;
	}

	public String getRemitterRole(){
		return remitterRole;
	}
	public void setRemitterRole(String remitterRole){
		this.remitterRole = remitterRole;
	}

	public String getRemitterOrgCode(){
		return remitterOrgCode;
	}
	public void setRemitterOrgCode(String remitterOrgCode){
		this.remitterOrgCode = remitterOrgCode;
	}

	public String getAcceptorOrgCode(){
		return acceptorOrgCode;
	}
	public void setAcceptorOrgCode(String acceptorOrgCode){
		this.acceptorOrgCode = acceptorOrgCode;
	}

	public String getPayeeOrgCode(){
		return payeeOrgCode;
	}
	public void setPayeeOrgCode(String payeeOrgCode){
		this.payeeOrgCode = payeeOrgCode;
	}

	

	public String getCurStatus(){
		return curStatus;
	}
	public void setCurStatus(String curStatus){
		this.curStatus = curStatus;
	}

	public String getAcctBranchNo(){
		return acctBranchNo;
	}
	public void setAcctBranchNo(String acctBranchNo){
		this.acctBranchNo = acctBranchNo;
	}

	public String getStorageBranchNo(){
		return storageBranchNo;
	}
	public void setStorageBranchNo(String storageBranchNo){
		this.storageBranchNo = storageBranchNo;
	}

	public String getAcceptorRole(){
		return acceptorRole;
	}
	public void setAcceptorRole(String acceptorRole){
		this.acceptorRole = acceptorRole;
	}

	public String getReqDraftId(){
		return reqDraftId;
	}
	public void setReqDraftId(String reqDraftId){
		this.reqDraftId = reqDraftId;
	}

	public String getRespDraftId(){
		return respDraftId;
	}
	public void setRespDraftId(String respDraftId){
		this.respDraftId = respDraftId;
	}


}
