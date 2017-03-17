/********************************************
* 文件名称: HappenSearchBean.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:RgctBill查询条件类
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: fqq
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

public class HappenSearchBean extends BaseSearchBean{
	//申请票据类型
    private String billType;
    //票据类型
  	private String billClass;   
    //当前状态(多个用数组传递)
  	private Object[] opers;
  	//当前状态(一个用String传递)
  	private String operStatus;
  //批次当前状态(一个用String传递)
  	private String applyStatus;
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
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	
	
}
