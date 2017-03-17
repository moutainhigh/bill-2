/********************************************
 * 文件名称: Var102103.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 承兑回复(签收或拒绝) 
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzc
 * 开发时间: 20160810
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.online.trans.trans102103;

import java.util.List;

import com.herongtech.online.trans.common.CommonInfoBean;

/**
 * 承兑回复(签收或拒绝)
 * @author Administrator
 *
 */
public class Var102103 {
	private String custAccount = null;
	private String rgctIds = null;
	private String signature = null;
	private String signUpMark = null;
//	private String type = null;
	private String remark = null;
	private String payRefuseCode= null;
	private String rejectReason= null;
	
	private List<CommonInfoBean> result;

	public List<CommonInfoBean> getResult() {
		return result;
	}

	public void setResult(List<CommonInfoBean> result) {
		this.result = result;
	}

	public String getPayRefuseCode() {
		return payRefuseCode;
	}

	public void setPayRefuseCode(String payRefuseCode) {
		this.payRefuseCode = payRefuseCode;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSignUpMark() {
		return signUpMark;
	}

	public void setSignUpMark(String signUpMark) {
		this.signUpMark = signUpMark;
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
