package com.herongtech.rc.draft.common.vo;


public class BindingDetail {
    
    private String ptcptBankNo;     //参与者行号
    private String bindTp;         //变更类型  AB00 新增， AB01 删除
    private String certInfCN;       //CN号
    private String certInfSN;       //SN号
    
    public String getPtcptBankNo() {
        return ptcptBankNo;
    }
    
    public void setPtcptBankNo(String ptcptBankNo) {
        this.ptcptBankNo = ptcptBankNo;
    }
    
    public String getBindTp() {
        return bindTp;
    }
    
    public void setBindTp(String bindTp) {
        this.bindTp = bindTp;
    }
    
    public String getCertInfCN() {
        return certInfCN;
    }
    
    public void setCertInfCN(String certInfCN) {
        this.certInfCN = certInfCN;
    }
    
    public String getCertInfSN() {
        return certInfSN;
    }
    
    public void setCertInfSN(String certInfSN) {
        this.certInfSN = certInfSN;
    }
    
    
    
}
