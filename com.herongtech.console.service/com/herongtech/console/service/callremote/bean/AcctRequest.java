package com.herongtech.console.service.callremote.bean;

import java.util.List;

import com.herongtech.console.domain.acct.bean.AcctFlow;
import com.herongtech.console.domain.acct.bean.AcctFlowInfo;


public class AcctRequest extends DynamicProperty {
    
    private String transNo;
    private AcctFlow af;
    private List list;
    private Object apply;
    
    public String getTransNo() {
        return transNo;
    }
    
    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }
    
    public AcctFlow getAf() {
        return af;
    }
    
    public void setAf(AcctFlow af) {
        this.af = af;
    }

    
    public List getList() {
        return list;
    }

    
    public void setList(List list) {
        this.list = list;
    }

    public Object getApply() {
        return apply;
    }

    
    public void setApply(Object apply) {
        this.apply = apply;
    }
    

}
