/********************************************
* 文件名称: DiscQueryCondition.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:清单查询条件类
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: songzx
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.disc.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

public class DiscSearchBean extends BaseSearchBean{
	//组织机构代码
	private String orgCode;
	//操作行行号
	private String operBankNo;
	//申请岗操作柜员编号
	private String applyOperNo;
	//审核岗操作柜员编号
	private String auditOperNo;
	//记账岗操作柜员编号
	private String acctOperNo;
	
	//批次号
	private String batchNo;
	//贴现流水号（批次id）
	private String discId;
	//客户账号
	private String acctNo;
	//客户号
	private String custNo;
	//当前状态(多个用数组传递)
	private Object[] opers;
	//当前状态(一个用String传递)
	private String operStatus;
	private String rgctId;
	private Object[] rgctIds;
	//批次当前状态(一个用String传递)
	private String applyStatus;
	//贴现明细流水
	private String discmxId;
	//贴现利率
	private String rate;
	//申请票据类型
    private String billType;
    //票号后8位
    private String lastBillNo;
    //查询起始日期
  	private String startDay;
    //查询结束日期
  	private String endDay;
    //排序
  	private String orderBy;
    //机构号
  	private String branchNo;
    //票据类型
  	private String billClass;
  	//贴现申请人社会信用代码 
	private String socialCreditCode;
	//贴现申请人企业规模
	private String enterpriseScale;
	//贴现申请人是否为三农企业
	private String isRuralEnterprises;
	//贴现记账日
	private String accountDate;
	public String getSocialCreditCode() {
		return socialCreditCode;
	}

	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}
	public String getAcctOperNo() {
		return acctOperNo;
	}

	public void setAcctOperNo(String acctOperNo) {
		this.acctOperNo = acctOperNo;
	}
	public String getEnterpriseScale() {
		return enterpriseScale;
	}

	public void setEnterpriseScale(String enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}

	public String getIsRuralEnterprises() {
		return isRuralEnterprises;
	}

	public void setIsRuralEnterprises(String isRuralEnterprises) {
		this.isRuralEnterprises = isRuralEnterprises;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
    public Object[] getRgctIds() {
        return rgctIds;
    }
    
    public void setRgctIds(Object[] rgctIds) {
        this.rgctIds = rgctIds;
    }
	
    public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getDiscId() {
		return discId;
	}
	public void setDiscId(String discId) {
		this.discId = discId;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getLastBillNo() {
		return lastBillNo;
	}
	public void setLastBillNo(String lastBillNo) {
		this.lastBillNo = lastBillNo;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
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

	public String getDiscmxId() {
		return discmxId;
	}

	public void setDiscmxId(String discmxId) {
		this.discmxId = discmxId;
	}

	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
    //贴现日
	private String discDt;
	
	public String getDiscDt() {
		return discDt;
	}

	public void setDiscDt(String discDt) {
		this.discDt = discDt;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getRgctId() {
		return rgctId;
	}

	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	public String getApplyOperNo() {
		return applyOperNo;
	}

	public void setApplyOperNo(String applyOperNo) {
		this.applyOperNo = applyOperNo;
	}

	public String getAuditOperNo() {
		return auditOperNo;
	}

	public void setAuditOperNo(String auditOperNo) {
		this.auditOperNo = auditOperNo;
	}

	public String getOperBankNo() {
		return operBankNo;
	}

	public void setOperBankNo(String operBankNo) {
		this.operBankNo = operBankNo;
	}
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
	
}
