/********************************************
* 文件名称: RgctBill.java
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

/**
 * RgctBill entity.
 * 将RgctBillInfo与RgctBillHist合并成一个类，供外部使用
 */

public class RgctBill implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RgctBill() {
		super();
		
	}
	private RgctBillInfo info;
	private RgctBillHist hist;

	public RgctBill(RgctBillInfo info, RgctBillHist hist) {
		super();
		this.info = info;
		this.hist = hist;
	}
	public RgctBillInfo getInfo() {
		return info;
	}
	public void setInfo(RgctBillInfo info) {
		this.info = info;
	}
	public RgctBillHist getHist() {
		return hist;
	}
	public void setHist(RgctBillHist hist) {
		this.hist = hist;
	}

}