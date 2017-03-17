/********************************************
* 文件名称: SaleInfo.java
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
package com.herongtech.console.domain.sale.bean;

/**
 * SaleInfo entity.
 * 将SaleBillInfo与SaleApplyInfo合并成一个类，供外部使用
 */

public class SaleInfo implements java.io.Serializable{
	
	public SaleInfo() {
		super();
		
	}


	private SaleBillInfo bill;
	private SaleApplyInfo apply;
	public SaleBillInfo getBill() {
		return bill;
	}
	public void setBill(SaleBillInfo bill) {
		this.bill = bill;
	}
	public SaleApplyInfo getApply() {
		return apply;
	}
	public void setApply(SaleApplyInfo apply) {
		this.apply = apply;
	}
	

	
}