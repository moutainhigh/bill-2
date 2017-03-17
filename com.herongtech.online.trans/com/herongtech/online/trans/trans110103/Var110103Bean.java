package com.herongtech.online.trans.trans110103;
/**票据删除 的返数据*/
public class Var110103Bean {

	
	private String rgctId;
	public String getRgctId() {
		return rgctId;
	}
	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	private int isSuccess;
	private String errMsg;
}
