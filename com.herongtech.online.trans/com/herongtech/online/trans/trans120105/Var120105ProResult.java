package com.herongtech.online.trans.trans120105;

public class Var120105ProResult {
	
	private String type;//成功失败
    private String code;//错误编码
    private String errMsg;//错误信息
    private String domain;//系统编码
    private String exSerial;//外部系统流水号
    private String functionId;
    private String errNum;
    private String totNum;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getExSerial() {
		return exSerial;
	}
	public void setExSerial(String exSerial) {
		this.exSerial = exSerial;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
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
