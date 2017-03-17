package com.herongtech.online.trans.common;


public class CommonInfoBean {
    String rgctId;
    String isSuccess;//0-失败，1-成功
    String errMsg;//错误信息
    
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
