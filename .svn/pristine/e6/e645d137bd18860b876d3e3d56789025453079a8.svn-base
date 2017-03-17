/********************************************
* 文件名称: SaleQueryCondition.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:转出批次查询条件类
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: songzx
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.sale.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

public class SaleSearchBean extends BaseSearchBean{
	/*tsale_apply_info表*/
	//转贴现转出流水号（批次id）
	private String saleId;
	//票据类型
	private String billType;
	//票据品种
  	private String billClass;
  //转卖类型
  	private String saleType;
  	
    //是否系统内
  	private String isInner;
  	//排序
  	private String orderBy;
  	//机构号
  	//private String branchNo;
  	//
  	private String billStorageBrchno;
  	//批次状态
  	private String applyStatus;
  	
  	
  	/*tsale_bill_info表*/
  	//操作状态(一个状态时)
  	private String operStatus;
  	//操作状态(多个状态时)
  	private Object[] statusArray;
  	//票号
  	private String billNo;
  	/**票面到期日*/
  	//查询起始日期
  	private String startDay;
  	//查询结束日期
  	private String endDay;
  	/**票面金额*/
  //查询起始日期
  	private Double startMoney;
  	//查询结束日期
  	private Double endMoney;
  	
  	//申请岗柜员
  	private String applyOperNo;
  	//审核岗柜员
  	private String auditOperNo;
  	//记账岗柜员
  	private String acctOperNo;
  	//记账日期
  	private String accountDate;
  	
  	
  //转贴现出明细流水号
  	private String salemxId;
	//票号后8位
    private String lastBillNo;
    //rgctIds
    private String rgctIds;
    
    /**下面的参数暂时用于参数传递，当然也能用于设置查询条件     */
    private String salemxIds;
    //是否同城
    private String isSameCity;
    //顺延类型
    private String delayType;
    //顺延天数
    private Long delayDays;
    /*
    private String flag="0";
    
  	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}*/
    
	public String getApplyOperNo() {
		return applyOperNo;
	}
	public String getBillStorageBrchno() {
		return billStorageBrchno;
	}
	public void setBillStorageBrchno(String billStorageBrchno) {
		this.billStorageBrchno = billStorageBrchno;
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
	public String getRgctIds() {
		return rgctIds;
	}
	public void setRgctIds(String rgctIds) {
		this.rgctIds = rgctIds;
	}
	public String getSalemxIds() {
		return salemxIds;
	}
	public void setSalemxIds(String salemxIds) {
		this.salemxIds = salemxIds;
	}
	public String getIsSameCity() {
		return isSameCity;
	}
	public void setIsSameCity(String isSameCity) {
		this.isSameCity = isSameCity;
	}
	public String getDelayType() {
		return delayType;
	}
	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}
	public Long getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(Long delayDays) {
		this.delayDays = delayDays;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
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
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
    
    public Object[] getStatusArray() {
        return statusArray;
    }
    
    public void setStatusArray(Object[] statusArray) {
        this.statusArray = statusArray;
    }
    
    public String getSalemxId() {
        return salemxId;
    }
    
    public void setSalemxId(String salemxId) {
        this.salemxId = salemxId;
    }
    
    public String getLastBillNo() {
        return lastBillNo;
    }
    
    public void setLastBillNo(String lastBillNo) {
        this.lastBillNo = lastBillNo;
    }
    
    public String getStartDay() {
        return startDay;
    }
    
    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }
    
    public String getEndDay() {
        return endDay;
    }
    
    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
    
   /* public String getBranchNo() {
        return branchNo;
    }
    
    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }*/
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Double getStartMoney() {
		return startMoney;
	}
	public void setStartMoney(Double startMoney) {
		this.startMoney = startMoney;
	}
	public Double getEndMoney() {
		return endMoney;
	}
	public void setEndMoney(Double endMoney) {
		this.endMoney = endMoney;
	}
	public String getIsInner() {
		return isInner;
	}
	public void setIsInner(String isInner) {
		this.isInner = isInner;
	}
	public String getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}
    
    
}
