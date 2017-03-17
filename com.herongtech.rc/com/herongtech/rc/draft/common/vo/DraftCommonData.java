package com.herongtech.rc.draft.common.vo;


/**
 * 055报文公共数据变更对象
 */
public class DraftCommonData {

    private String altrnTp;     //变更类型 （01：新增、02：撤销、03：变更）
    private String fctvTp;      //生效类型（1：立即生效、2：指定日生效）
    private String fctvDt;      //生效日期
    private String cmonDtTp;    //数据类型（1：日期、2：字符串、3：金额、4：数字、5：时间 ）
    private String cmonDtCd;    //数据代码
    private String cmonDtNm;    //数据名称
    private String cmonDtVal;   //数据值
    
    public String getAltrnTp() {
        return altrnTp;
    }
    
    public void setAltrnTp(String altrnTp) {
        this.altrnTp = altrnTp;
    }
    
    public String getFctvTp() {
        return fctvTp;
    }
    
    public void setFctvTp(String fctvTp) {
        this.fctvTp = fctvTp;
    }
    
    public String getFctvDt() {
        return fctvDt;
    }
    
    public void setFctvDt(String fctvDt) {
        this.fctvDt = fctvDt;
    }
    
    public String getCmonDtTp() {
        return cmonDtTp;
    }
    
    public void setCmonDtTp(String cmonDtTp) {
        this.cmonDtTp = cmonDtTp;
    }
    
    public String getCmonDtCd() {
        return cmonDtCd;
    }
    
    public void setCmonDtCd(String cmonDtCd) {
        this.cmonDtCd = cmonDtCd;
    }
    
    public String getCmonDtNm() {
        return cmonDtNm;
    }
    
    public void setCmonDtNm(String cmonDtNm) {
        this.cmonDtNm = cmonDtNm;
    }
    
    public String getCmonDtVal() {
        return cmonDtVal;
    }
    
    public void setCmonDtVal(String cmonDtVal) {
        this.cmonDtVal = cmonDtVal;
    }
    
    
}
