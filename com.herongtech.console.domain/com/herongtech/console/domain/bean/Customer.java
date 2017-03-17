package com.herongtech.console.domain.bean;

public class Customer {
	//客户号
	private String custNo = " ";
	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}
	
	//客户名称
	private String custName = " ";
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	//客户类型
	private String custType = " ";
	public String getCustType(){
		return custType;
	}
	public void setCustType(String custType){
		this.custType = custType;
	}
	
	//客户账号
	private String acctNo = " ";
	public String getAcctNo(){
		return acctNo;
	}
	public void setAcctNo(String acctNo){
		this.acctNo = acctNo;
	}
	
	//账户类型
	private String acctType = " ";
	public String getAcctType(){
		return acctType;
	}
	public void setAcctType(String acctType){
		this.acctType = acctType;
	}
	
}
