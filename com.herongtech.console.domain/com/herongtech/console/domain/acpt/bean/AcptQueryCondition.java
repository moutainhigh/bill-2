/********************************************
* 文件名称: AcptQueryCondition.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:清单查询条件类
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: wuzc
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.acpt.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

public class AcptQueryCondition extends BaseSearchBean{
	//承兑明细流水号
	private String acptmxId;

	//承兑流水号
	private String acptId;
	//操作状态(一个状态时)
    private String status;
  	private String branchNo;
  	private String acceptorBankNo;
    //票据分类
  	private String billClass;
  	//票据种类
  	private String billType;

	//到期日
  	private String dueDt;
  	//出票日
  	private String issueDt; 	
	private String billNo;
	//票据状态
	private String billStatus;
	//当前营业日
	
	//票据状态数组
	private Object[] billStatusArray;
	
	//客户附言
	private String custRemark;

	//银行附言
	private String bankRemark;
	
	//出票人客户账号
	private String remitterAcct;
	//备注
	private String remark;
	//挂失人账号
	private String sspdAcct;
	//挂失人名称
	private String sspdName;
	//挂失日期
	private  String sspdDt;
	//是否办理公示催告
	private String isPubExhort;
	//解挂人账号
	private String anlgSspdAcct;
	//解挂人名称
	private String anlgSspdName;
	//解挂日期
	private String anlgSspdDt;
	//是否有解挂通知书
	private String isAnlgNotification;
	//挂失状态
	private String sspdStatus;
	
	private String isedit;
	//当前状态(多个用数组传递)
	private Object[] opers;
	//当前状态(一个用String传递)
	private String operStatus;
	
	//拒绝代码
    private String rejectCode;	//reject_code

	//拒绝原因
	private String rejectReason;	//reject_reason
	
	//当前营业日
	private String workday;
	
	//委托收款登记簿主键
	private String colltnId ;	
	
    public String getColltnId() {
		return colltnId;
	}
	public void setColltnId(String colltnId) {
		this.colltnId = colltnId;
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
	

	public String getWorkday() {
		return workday;
	}
	public void setWorkday(String workday) {
		this.workday = workday;
	}
  	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getAcptmxId() {
		return acptmxId;
	}
	public void setAcptmxId(String acptmxId) {
		this.acptmxId = acptmxId;
	}
	
	public Object[] getOpers() {
		return opers;
	}
	public void setOpers(Object[] opers) {
		this.opers = opers;
	}
	public String getOperStatus() {
		return operStatus;
	}
	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}
	public Object[] getBillStatusArray() {
		return billStatusArray;
	}
	public void setBillStatusArray(Object[] billStatusArray) {
		this.billStatusArray = billStatusArray;
	}
	
	
	public String getCustRemark() {
		return custRemark;
	}
	public void setCustRemark(String custRemark) {
		this.custRemark = custRemark;
	}
	public String getBankRemark() {
		return bankRemark;
	}
	public void setBankRemark(String bankRemark) {
		this.bankRemark = bankRemark;
	}
	
	
	
	
	public String getIsedit() {
		return isedit;
	}
	public void setIsedit(String isedit) {
		this.isedit = isedit;
	}
	public String getSspdStatus() {
		return sspdStatus;
	}
	public void setSspdStatus(String sspdStatus) {
		this.sspdStatus = sspdStatus;
	}
	public String getIsAnlgNotification() {
		return isAnlgNotification;
	}
	public void setIsAnlgNotification(String isAnlgNotification) {
		this.isAnlgNotification = isAnlgNotification;
	}
	public String getAnlgSspdDt() {
		return anlgSspdDt;
	}
	public void setAnlgSspdDt(String anlgSspdDt) {
		this.anlgSspdDt = anlgSspdDt;
	}
	public String getAnlgSspdName() {
		return anlgSspdName;
	}
	public void setAnlgSspdName(String anlgSspdName) {
		this.anlgSspdName = anlgSspdName;
	}
	public String getAnlgSspdAcct() {
		return anlgSspdAcct;
	}
	public void setAnlgSspdAcct(String anlgSspdAcct) {
		this.anlgSspdAcct = anlgSspdAcct;
	}
	public String getIsPubExhort() {
		return isPubExhort;
	}
	public void setIsPubExhort(String isPubExhort) {
		this.isPubExhort = isPubExhort;
	}
	public String getSspdDt() {
		return sspdDt;
	}
	public void setSspdDt(String sspdDt) {
		this.sspdDt = sspdDt;
	}
	public String getSspdName() {
		return sspdName;
	}
	public void setSspdName(String sspdName) {
		this.sspdName = sspdName;
	}
	public String getSspdAcct() {
		return sspdAcct;
	}
	public void setSspdAcct(String sspdAcct) {
		this.sspdAcct = sspdAcct;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemitterAcct() {
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct) {
		this.remitterAcct = remitterAcct;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getAcptId() {
		return acptId;
	}
	public void setAcptId(String acptId) {
		this.acptId = acptId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getAcceptorBankNo() {
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo) {
		this.acceptorBankNo = acceptorBankNo;
	}
	public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
	public String getDueDt() {
		return dueDt;
	}
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}
	public String getIssueDt() {
		return issueDt;
	}
	public void setIssueDt(String issueDt) {
		this.issueDt = issueDt;
	}

		
}
