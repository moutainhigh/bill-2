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
package com.herongtech.console.domain.rebuy.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

public class RebuySearchBean  extends BaseSearchBean{
	//贴现流水号（批次id）
	private String rebuyId;
	//产品编号
	private String prodNo;
	//票据类型
	private String billType;
	//票据种类
	private String billClass;
	//利率
	private String rate;
	//客户开户行账号
	private String custBankNo;
	//客户开户行名称
	private String custBankName;
	//票号后8位
    private String lastBillNo;
    //转入起始日期
  	private String startRebuyDt;
  	//转入结束日期
  	private String endRebuyDt;
  	//删除标记
  	private String delFlag;
    //机构号
  	private String branchNo;
	//是否系统内
  	private String isInner;
  	 //排序
  	private String orderBy;
  	//明细id
  	private String rebuymxId;
  	private Object[] mxIds;
    //当前状态(多个用数组传递)
  	private Object[] opers;
  	//当前状态(一个用String传递)
  	private String operStatus;
  	//批次状态
  	private String applyStatus;
  	//登记中心ID
  	private String rgctId;
  	//申请岗
  	private String applyOperNo;	
  	//审核岗
	private String auditOperNo;	
	//记账岗柜员
	private String acctOperNo;
  	//记账日
  	private String accountDate;	
  	//对方撤销申请标记
  	private String applyCancelFlag;
  	
  	//电票接收查询条件
  	private String isOnline; //是否线上清算
  	private String isRegress; //是否回购
  	private String rebuyDt; //转入日
  	
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public String getIsRegress() {
		return isRegress;
	}
	public void setIsRegress(String isRegress) {
		this.isRegress = isRegress;
	}
	public String getRebuyDt() {
		return rebuyDt;
	}
	public void setRebuyDt(String rebuyDt) {
		this.rebuyDt = rebuyDt;
	}
	public String getApplyCancelFlag() {
		return applyCancelFlag;
	}
	public void setApplyCancelFlag(String applyCancelFlag) {
		this.applyCancelFlag = applyCancelFlag;
	}
	public String getRebuyId() {
		return rebuyId;
	}
	public void setRebuyId(String rebuyId) {
		this.rebuyId = rebuyId;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
	public String getOperStatus() {
		return operStatus;
	}
	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getCustBankNo() {
		return custBankNo;
	}
	public void setCustBankNo(String custBankNo) {
		this.custBankNo = custBankNo;
	}
	public String getCustBankName() {
		return custBankName;
	}
	public void setCustBankName(String custBankName) {
		this.custBankName = custBankName;
	}
	public String getLastBillNo() {
		return lastBillNo;
	}
	public void setLastBillNo(String lastBillNo) {
		this.lastBillNo = lastBillNo;
	}
	public String getStartRebuyDt() {
		return startRebuyDt;
	}
	public void setStartRebuyDt(String startRebuyDt) {
		this.startRebuyDt = startRebuyDt;
	}
	public String getEndRebuyDt() {
		return endRebuyDt;
	}
	public void setEndRebuyDt(String endRebuyDt) {
		this.endRebuyDt = endRebuyDt;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getIsInner() {
		return isInner;
	}
	public void setIsInner(String isInner) {
		this.isInner = isInner;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Object[] getOpers() {
		return opers;
	}
	public void setOpers(Object[] opers) {
		this.opers = opers;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getRgctId() {
		return rgctId;
	}
	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
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
	public String getAcctOperNo() {
		return acctOperNo;
	}
	public void setAcctOperNo(String acctOperNo) {
		this.acctOperNo = acctOperNo;
	}
	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
	public String getRebuymxId() {
		return rebuymxId;
	}
	public void setRebuymxId(String rebuymxId) {
		this.rebuymxId = rebuymxId;
	}
	public Object[] getMxIds() {
		return mxIds;
	}
	public void setMxIds(Object[] mxIds) {
		this.mxIds = mxIds;
	}
  	
}
