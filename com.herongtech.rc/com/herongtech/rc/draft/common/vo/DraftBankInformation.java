package com.herongtech.rc.draft.common.vo;

import java.util.Date;


public class DraftBankInformation {
    
    private String changeType;          //变更类型 1：新增，2:修改,3:删除
    private String orgBankNo;           //行号
    private String addedInfCtgy;        //参与者类型
    private String addedInfClss;         //行别
    private String addedInfDrct;        //上级参与者
    private String addedInfNdCd;        //节点代码
    private String addedInfSprrLst;     //  上级行列表
    private String addedInfPBCBk;       //所属人行
    private String addedInfCityCd;      //地市代码   
    private String addedInfFullNm;      //全称   
    private String addedInfShrtNm;      //简称
    private String addedInfAdr;         //地址
    private String addedInfZpCd;        //邮编
    private String addedInfPhneNb;      //联系电话
    private String addedInfEmai;        //Email地址
    private Date   addedInfFctvDt;      //生效日期
    private String addedInfRmrk;        //备注
    /**失效日期*/
    private Date   xpryDt;              //失效日期
    
    public String getOrgBankNo() {
        return orgBankNo;
    }
    
    public void setOrgBankNo(String orgBankNo) {
        this.orgBankNo = orgBankNo;
    }
    
    public String getAddedInfCtgy() {
        return addedInfCtgy;
    }
    
    public void setAddedInfCtgy(String addedInfCtgy) {
        this.addedInfCtgy = addedInfCtgy;
    }
    
    public String getAddedInfClss() {
        return addedInfClss;
    }
    
    public void setAddedInfClss(String addedInfClss) {
        this.addedInfClss = addedInfClss;
    }
    
    public String getAddedInfDrct() {
        return addedInfDrct;
    }
    
    public void setAddedInfDrct(String addedInfDrct) {
        this.addedInfDrct = addedInfDrct;
    }
    
    public String getAddedInfNdCd() {
        return addedInfNdCd;
    }
    
    public void setAddedInfNdCd(String addedInfNdCd) {
        this.addedInfNdCd = addedInfNdCd;
    }
    
    public String getAddedInfSprrLst() {
        return addedInfSprrLst;
    }
    
    public void setAddedInfSprrLst(String addedInfSprrLst) {
        this.addedInfSprrLst = addedInfSprrLst;
    }
    
    public String getAddedInfPBCBk() {
        return addedInfPBCBk;
    }
    
    public void setAddedInfPBCBk(String addedInfPBCBk) {
        this.addedInfPBCBk = addedInfPBCBk;
    }
    
    public String getAddedInfCityCd() {
        return addedInfCityCd;
    }
    
    public void setAddedInfCityCd(String addedInfCityCd) {
        this.addedInfCityCd = addedInfCityCd;
    }
    
    public String getAddedInfFullNm() {
        return addedInfFullNm;
    }
    
    public void setAddedInfFullNm(String addedInfFullNm) {
        this.addedInfFullNm = addedInfFullNm;
    }
    
    public String getAddedInfShrtNm() {
        return addedInfShrtNm;
    }
    
    public void setAddedInfShrtNm(String addedInfShrtNm) {
        this.addedInfShrtNm = addedInfShrtNm;
    }
    
    public String getAddedInfAdr() {
        return addedInfAdr;
    }
    
    public void setAddedInfAdr(String addedInfAdr) {
        this.addedInfAdr = addedInfAdr;
    }
    
    public String getAddedInfZpCd() {
        return addedInfZpCd;
    }
    
    public void setAddedInfZpCd(String addedInfZpCd) {
        this.addedInfZpCd = addedInfZpCd;
    }
    
    public String getAddedInfPhneNb() {
        return addedInfPhneNb;
    }
    
    public void setAddedInfPhneNb(String addedInfPhneNb) {
        this.addedInfPhneNb = addedInfPhneNb;
    }
    
    public String getAddedInfEmai() {
        return addedInfEmai;
    }
    
    public void setAddedInfEmai(String addedInfEmai) {
        this.addedInfEmai = addedInfEmai;
    }
    
    public Date getAddedInfFctvDt() {
        return addedInfFctvDt;
    }
    
    public void setAddedInfFctvDt(Date addedInfFctvDt) {
        this.addedInfFctvDt = addedInfFctvDt;
    }
    
    public String getAddedInfRmrk() {
        return addedInfRmrk;
    }
    
    public void setAddedInfRmrk(String addedInfRmrk) {
        this.addedInfRmrk = addedInfRmrk;
    }
    
    public Date getXpryDt() {
        return xpryDt;
    }
    
    public void setXpryDt(Date xpryDt) {
        this.xpryDt = xpryDt;
    }

    
    public String getChangeType() {
        return changeType;
    }

    
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
    
    

}
