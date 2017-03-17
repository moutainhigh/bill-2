package com.herongtech.xmlchannel.pkg;


/**
 * 通用返回信息
 *
 */
public class ProResult {

    private String type;//成功失败
    private String code;//错误编码
    private String message;//错误信息
    private String domain;//系统编码
    private String exSerial;//外部系统流水号
    private String functionId;
    
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
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
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
    
    
}
