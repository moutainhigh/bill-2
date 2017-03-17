/********************************************
* 文件名称: AcptColltnReg.java
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
package com.herongtech.console.domain.acpt.bean;


public class AcptColltnReg{
	private String id = " ";	//主键
	private String acptId = " ";	//银承清单id
	private String branchNo = " ";	//签发机构
	private String regFlowNo = " ";	//委托登记流水号
	private String billNo = " ";	//票号
	private String billClass = " ";	//票据分类
	private String billType = " ";	//票据种类
	private String busiType = " ";	//业务类型
	private String draweeAcct = " ";	//付款人帐号
	private String draweeName = " ";	//付款人户名
	private String draweeBankNo = " ";	//付款行行号
	private String draweeBankNameName = " ";	//付款行行名
	private String payeeAcct = " ";	//收款人账号
	private String payeeName = " ";	//收款人名称
	private String payeeBankNo = " ";	//收款人开户行行号
	private String payeeBankName = " ";	//收款人开户行名称
	private double colltnAmt = 0.0;	//colltn_amt//委收金额
	private double payAmt = 0.0;	//pay_amt//付款金额
	private double compensAmt = 0.0;	//compens_amt//赔偿金额
	private String paymentPath = " ";	//资金通道
	private String bankType = " ";	//行别
	private String city = " ";	//城市
	private String payWaitOrder = " ";	//应付挂账订单号
	private String regDt = " ";	//reg_dt//登记日期
	private String isDelay = " ";	//is_delay//是否延期
	private String delayReason = " ";	//delay_reason//延期原因
	private String accountDt = " ";	//account_dt//销记日期
	private String rejectDt = " ";	//reject_dt//退票日期
	private String rejectCode = " ";	//reject_code//退票编码
	private String rejectReason = " ";	//reject_reason//退票理由
	private String custRemark = " ";	//客户附言
	private String bankRemark = " ";	//银行附言
	private String colltnStatus = " ";	//委托收款状态
	private String operNo = " ";	//操作柜员
	private String operTime = " ";	//操作时间
	private String auditUserNo = " ";	//复核柜员
	private String auditTime = " ";	//复核时间
	private String settleMark = " ";	//settle_mark//清算方式 SM00线上,SM01线下
	private String fund = " ";	//fund//资金去向 0-转帐 1-挂帐
	private String yzSource = " ";	//移植来源
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getAcptId(){
		return acptId;
	}
	public void setAcptId(String acptId){
		this.acptId = acptId;
	}

	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	public String getRegFlowNo(){
		return regFlowNo;
	}
	public void setRegFlowNo(String regFlowNo){
		this.regFlowNo = regFlowNo;
	}

	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
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

	public String getBusiType(){
		return busiType;
	}
	public void setBusiType(String busiType){
		this.busiType = busiType;
	}

	public String getDraweeAcct(){
		return draweeAcct;
	}
	public void setDraweeAcct(String draweeAcct){
		this.draweeAcct = draweeAcct;
	}

	public String getDraweeName(){
		return draweeName;
	}
	public void setDraweeName(String draweeName){
		this.draweeName = draweeName;
	}

	public String getDraweeBankNo(){
		return draweeBankNo;
	}
	public void setDraweeBankNo(String draweeBankNo){
		this.draweeBankNo = draweeBankNo;
	}

	public String getDraweeBankNameName(){
		return draweeBankNameName;
	}
	public void setDraweeBankNameName(String draweeBankNameName){
		this.draweeBankNameName = draweeBankNameName;
	}

	public String getPayeeAcct(){
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct){
		this.payeeAcct = payeeAcct;
	}

	public String getPayeeName(){
		return payeeName;
	}
	public void setPayeeName(String payeeName){
		this.payeeName = payeeName;
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

	public double getColltnAmt(){
		return colltnAmt;
	}
	public void setColltnAmt(double colltnAmt){
		this.colltnAmt = colltnAmt;
	}

	public double getPayAmt(){
		return payAmt;
	}
	public void setPayAmt(double payAmt){
		this.payAmt = payAmt;
	}

	public double getCompensAmt(){
		return compensAmt;
	}
	public void setCompensAmt(double compensAmt){
		this.compensAmt = compensAmt;
	}

	public String getPaymentPath(){
		return paymentPath;
	}
	public void setPaymentPath(String paymentPath){
		this.paymentPath = paymentPath;
	}

	public String getBankType(){
		return bankType;
	}
	public void setBankType(String bankType){
		this.bankType = bankType;
	}

	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}

	public String getPayWaitOrder(){
		return payWaitOrder;
	}
	public void setPayWaitOrder(String payWaitOrder){
		this.payWaitOrder = payWaitOrder;
	}

	public String getRegDt(){
		return regDt;
	}
	public void setRegDt(String regDt){
		this.regDt = regDt;
	}

	public String getIsDelay(){
		return isDelay;
	}
	public void setIsDelay(String isDelay){
		this.isDelay = isDelay;
	}

	public String getDelayReason(){
		return delayReason;
	}
	public void setDelayReason(String delayReason){
		this.delayReason = delayReason;
	}

	public String getAccountDt(){
		return accountDt;
	}
	public void setAccountDt(String accountDt){
		this.accountDt = accountDt;
	}

	public String getRejectDt(){
		return rejectDt;
	}
	public void setRejectDt(String rejectDt){
		this.rejectDt = rejectDt;
	}

	public String getRejectCode(){
		return rejectCode;
	}
	public void setRejectCode(String rejectCode){
		this.rejectCode = rejectCode;
	}

	public String getRejectReason(){
		return rejectReason;
	}
	public void setRejectReason(String rejectReason){
		this.rejectReason = rejectReason;
	}

	public String getCustRemark(){
		return custRemark;
	}
	public void setCustRemark(String custRemark){
		this.custRemark = custRemark;
	}

	public String getBankRemark(){
		return bankRemark;
	}
	public void setBankRemark(String bankRemark){
		this.bankRemark = bankRemark;
	}

	public String getColltnStatus(){
		return colltnStatus;
	}
	public void setColltnStatus(String colltnStatus){
		this.colltnStatus = colltnStatus;
	}

	public String getOperNo(){
		return operNo;
	}
	public void setOperNo(String operNo){
		this.operNo = operNo;
	}

	public String getOperTime(){
		return operTime;
	}
	public void setOperTime(String operTime){
		this.operTime = operTime;
	}

	public String getAuditUserNo(){
		return auditUserNo;
	}
	public void setAuditUserNo(String auditUserNo){
		this.auditUserNo = auditUserNo;
	}

	public String getAuditTime(){
		return auditTime;
	}
	public void setAuditTime(String auditTime){
		this.auditTime = auditTime;
	}

	public String getSettleMark(){
		return settleMark;
	}
	public void setSettleMark(String settleMark){
		this.settleMark = settleMark;
	}

	public String getFund(){
		return fund;
	}
	public void setFund(String fund){
		this.fund = fund;
	}

	public String getYzSource(){
		return yzSource;
	}
	public void setYzSource(String yzSource){
		this.yzSource = yzSource;
	}


}
