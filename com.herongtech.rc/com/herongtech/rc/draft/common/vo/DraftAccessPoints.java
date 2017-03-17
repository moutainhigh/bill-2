package com.herongtech.rc.draft.common.vo;


/**
 * 062接入点信息变更
 */
public class DraftAccessPoints {

    private String accssPtCd;   //接入点号码
    private String accssPtNm;   //接入点名称
    private String altrnTp;     //变更类型（01-新增/变更 ，02-删除）
    private String ccpcCd;      //所属CCPC号码
    private String ccpcNm;      //所属CCPC名称
    
    public String getAccssPtCd() {
        return accssPtCd;
    }
    
    public void setAccssPtCd(String accssPtCd) {
        this.accssPtCd = accssPtCd;
    }
    
    public String getAccssPtNm() {
        return accssPtNm;
    }
    
    public void setAccssPtNm(String accssPtNm) {
        this.accssPtNm = accssPtNm;
    }
    
    public String getAltrnTp() {
        return altrnTp;
    }
    
    public void setAltrnTp(String altrnTp) {
        this.altrnTp = altrnTp;
    }
    
    public String getCcpcCd() {
        return ccpcCd;
    }
    
    public void setCcpcCd(String ccpcCd) {
        this.ccpcCd = ccpcCd;
    }
    
    public String getCcpcNm() {
        return ccpcNm;
    }
    
    public void setCcpcNm(String ccpcNm) {
        this.ccpcNm = ccpcNm;
    }

    
}
