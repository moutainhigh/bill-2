package com.herongtech.online.trans.trans101105;
/**循环域 中的bean  */
public class Var101105Bean {

	private String rgctId;	//票据Id	Long	N	票据Id
	private String isSuccess;	//	是否成功	String(1)	N	0-失败，1-成功
	private String errMsg;	//	错误信息	String	N	isSuccess为0时有效，处理错误信息
	public String getRgctId() {
		return rgctId;
	}
	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
