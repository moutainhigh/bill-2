/********************************************
* 文件名称: DiscInfo.java
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
package com.herongtech.console.domain.disc.bean;

/**
 * DiscInfo entity.
 * 将DiscBillInfo与DiscApplyInfo合并成一个类，供外部使用
 */

public class DiscInfo implements java.io.Serializable{
	/**
	 * 
	 */
	
	public DiscInfo() {
		super();
		
	}

	public DiscBillInfo getBill() {
		return bill;
	}
	public void setBill(DiscBillInfo bill) {
		this.bill = bill;
	}
	public DiscApplyInfo getApply() {
		return apply;
	}
	public void setApply(DiscApplyInfo apply) {
		this.apply = apply;
	}
	private DiscBillInfo bill;
	private DiscApplyInfo apply;

	
}