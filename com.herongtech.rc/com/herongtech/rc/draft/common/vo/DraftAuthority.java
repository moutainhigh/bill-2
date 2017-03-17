package com.herongtech.rc.draft.common.vo;


/**
 * 056业务权限对象
 *
 */
public class DraftAuthority {
    
    private String sndAuthrtyNb;        //发起业务权限数目
    private String rcvAuthrtyNb;        //接收业务权限数目
    private String sndAuthrtyList;      //发起业务权限列表
    private String rcvAuthrtyLis;       //接收业务权限列表
    private String ptcptAcctSvcr;       //参与者行号
    
    public String getSndAuthrtyNb() {
        return sndAuthrtyNb;
    }
    
    public void setSndAuthrtyNb(String sndAuthrtyNb) {
        this.sndAuthrtyNb = sndAuthrtyNb;
    }
    
    public String getRcvAuthrtyNb() {
        return rcvAuthrtyNb;
    }
    
    public void setRcvAuthrtyNb(String rcvAuthrtyNb) {
        this.rcvAuthrtyNb = rcvAuthrtyNb;
    }
    
    public String getSndAuthrtyList() {
        return sndAuthrtyList;
    }
    
    public void setSndAuthrtyList(String sndAuthrtyList) {
        this.sndAuthrtyList = sndAuthrtyList;
    }
    
    public String getRcvAuthrtyLis() {
        return rcvAuthrtyLis;
    }
    
    public void setRcvAuthrtyLis(String rcvAuthrtyLis) {
        this.rcvAuthrtyLis = rcvAuthrtyLis;
    }
    
    public String getPtcptAcctSvcr() {
        return ptcptAcctSvcr;
    }
    
    public void setPtcptAcctSvcr(String ptcptAcctSvcr) {
        this.ptcptAcctSvcr = ptcptAcctSvcr;
    }
    
    
    
}
