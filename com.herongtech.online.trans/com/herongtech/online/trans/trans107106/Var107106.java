package com.herongtech.online.trans.trans107106;

import java.util.List;

public class Var107106 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	private String errNum;
	private String totNum;
	private String custAccount = null;
	private String signature = null;
	private String rgctIds = null;
	private String signUpMark = null;
	private String remark = null;
	private List<Var107106InfoBean> result;
	
	public String getCustAccount() {
		return custAccount;
	}
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getRgctIds() {
		return rgctIds;
	}
	public void setRgctIds(String rgctIds) {
		this.rgctIds = rgctIds;
	}
	public String getSignUpMark() {
		return signUpMark;
	}
	public void setSignUpMark(String signUpMark) {
		this.signUpMark = signUpMark;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<Var107106InfoBean> getResult() {
		return result;
	}
	public void setResult(List<Var107106InfoBean> result) {
		this.result = result;
	}
	public String getErrNum() {
		return errNum;
	}
	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}
	public String getTotNum() {
		return totNum;
	}
	public void setTotNum(String totNum) {
		this.totNum = totNum;
	}
	
	

}
