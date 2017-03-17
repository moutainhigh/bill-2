package com.herongtech.online.trans.common;

import java.util.List;


public class CommonResult {
	
	private String totNum;//总记录数
    private String errNum;//错误记录数
    private List<CommonInfoBean> result;
	public String getTotNum() {
		return totNum;
	}
	public void setTotNum(String totNum) {
		this.totNum = totNum;
	}
	public String getErrNum() {
		return errNum;
	}
	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}
	public List<CommonInfoBean> getResult() {
		return result;
	}
	public void setResult(List<CommonInfoBean> result) {
		this.result = result;
	}
    
    
}
