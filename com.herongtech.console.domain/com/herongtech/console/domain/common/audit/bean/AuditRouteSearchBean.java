package com.herongtech.console.domain.common.audit.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;


public class AuditRouteSearchBean extends BaseSearchBean {
    
    private String arName;
    private String prodNo;              //产品号
    private String bindBranchNo;  //指定使用机构号
    private String pubFlag;   //是否通用
    private String effectFlag;    //是否生效标志
    private String auditStartBrchLvl;
    
    
    public String getArName() {
        return arName;
    }
    
    public void setArName(String arName) {
        this.arName = arName;
    }
    
    public String getProdNo() {
        return prodNo;
    }
    
    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    
    public String getBindBranchNo() {
        return bindBranchNo;
    }

    
    public void setBindBranchNo(String bindBranchNo) {
        this.bindBranchNo = bindBranchNo;
    }

    
    public String getPubFlag() {
        return pubFlag;
    }

    
    public void setPubFlag(String pubFlag) {
        this.pubFlag = pubFlag;
    }

    
    public String getEffectFlag() {
        return effectFlag;
    }

    
    public void setEffectFlag(String effectFlag) {
        this.effectFlag = effectFlag;
    }

    
    public String getAuditStartBrchLvl() {
        return auditStartBrchLvl;
    }

    
    public void setAuditStartBrchLvl(String auditStartBrchLvl) {
        this.auditStartBrchLvl = auditStartBrchLvl;
    }
    

}
