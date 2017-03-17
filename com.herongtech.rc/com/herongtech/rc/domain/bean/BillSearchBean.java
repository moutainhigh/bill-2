/********************************************
* 文件名称: RgctBillSearchBean.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:RgctBill查询条件类
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: songzx
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.rc.domain.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

public class BillSearchBean extends BaseSearchBean{
	private String prodNo;//产品编号
	private String billNo;//票号
	private String[] statusArray;//状态--对个状态时使用
	private String status;//状态
	private String billSource;//票据来源
	private String branchNo;//机构号
	
	public String getBillSource() {
		return billSource;
	}
	public void setBillSource(String billSource) {
		this.billSource = billSource;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getStatusArray() {
		return statusArray;
	}
	public void setStatusArray(String[] statusArray) {
		this.statusArray = statusArray;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	
	
}
