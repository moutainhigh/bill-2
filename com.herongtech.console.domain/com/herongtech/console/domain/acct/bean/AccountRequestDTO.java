package com.herongtech.console.domain.acct.bean;

import java.util.List;

import com.herongtech.console.domain.bean.UserLoginfo;


public class AccountRequestDTO<T,E> {
    
    private T apply;
    private List<E> billList;
    private UserLoginfo userLogonInfo;
    
    public T getApply() {
        return apply;
    }
    
    public void setApply(T apply) {
        this.apply = apply;
    }
    
    public List<E> getBillList() {
        return billList;
    }
    
    public void setBillList(List<E> billList) {
        this.billList = billList;
    }
    
    public UserLoginfo getUserLogonInfo() {
        return userLogonInfo;
    }
    
    public void setUserLogonInfo(UserLoginfo userLogonInfo) {
        this.userLogonInfo = userLogonInfo;
    }
    
    

}
