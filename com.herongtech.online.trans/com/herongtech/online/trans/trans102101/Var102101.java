/********************************************
 * 文件名称: Var102101.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzc
 * 开发时间: 20160810
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.online.trans.trans102101;

import java.util.List;

import com.herongtech.online.trans.common.CommonInfoBean;

/**
 * 提示承兑
 * @author Administrator
 *
 */
public class Var102101 {
	private String custAccount = null;
	private String rgctIds = null;
	private String signature = null;
	private String remark = null;
	private String batchNo = null;
	private String invoiceNo = null;
	
	private List<CommonInfoBean> result;
//	private ArrayList<Map<String, String>> resultList;
	/*private ArrayList<Object> result;
	
	public ArrayList<Object> getResult() {
		return result;
	}

	public void setResult(ArrayList<Object> result) {
		this.result = result;
	}*/

//	public ArrayList<Map<String, String>> getResultList() {
//		return resultList;
//	}
//
//	public void setResultList(ArrayList<Map<String, String>> resultList) {
//		this.resultList = resultList;
//	}
	
    public String getInvoiceNo() {
		return invoiceNo;
	}

	public List<CommonInfoBean> getResult() {
		return result;
	}

	public void setResult(List<CommonInfoBean> result) {
		this.result = result;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	public String getCustAccount() {
		return custAccount;
	}

	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

	public String getRgctIds() {
		return rgctIds;
	}

	public void setRgctIds(String rgctIds) {
		this.rgctIds = rgctIds;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
