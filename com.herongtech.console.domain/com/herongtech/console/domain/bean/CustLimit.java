/********************************************
* 文件名称: CustLimit.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.bean;


public class CustLimit{
	//客户号
	private String custNo = "";
	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	//客户名称
	private String custName = "";
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	//客户类型
	private String custType = "1";
	public String getCustType(){
		return custType;
	}
	public void setCustType(String custType){
		this.custType = custType;
	}

	//额度品种
	private String balanceClass = "1";
	public String getBalanceClass(){
		return balanceClass;
	}
	public void setBalanceClass(String balanceClass){
		this.balanceClass = balanceClass;
	}

	//总额度
	private double totalBalance = 0.00d;
	public double getTotalBalance(){
		return totalBalance;
	}
	public void setTotalBalance(double totalBalance){
		this.totalBalance = totalBalance;
	}

	//可用额度
	private double ableBalance = 0.00d;
	public double getAbleBalance(){
		return ableBalance;
	}
	public void setAbleBalance(double ableBalance){
		this.ableBalance = ableBalance;
	}

	//已用额度
	private double usedBalance = 0.00d;
	public double getUsedBalance(){
		return usedBalance;
	}
	public void setUsedBalance(double usedBalance){
		this.usedBalance = usedBalance;
	}

	//有效期
	private String effectiveDate = "";
	public String getEffectiveDate(){
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate){
		this.effectiveDate = effectiveDate;
	}

	//是否可循环
	private String isCircle = "1";
	public String getIsCircle(){
		return isCircle;
	}
	public void setIsCircle(String isCircle){
		this.isCircle = isCircle;
	}

	//状态
	private String status = "1";
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	//操作时间
	private String operDate = "";
	public String getOperDate(){
		return operDate;
	}
	public void setOperDate(String operDate){
		this.operDate = operDate;
	}

	//操作人
	private String operUser = "";
	public String getOperUser(){
		return operUser;
	}
	public void setOperUser(String operUser){
		this.operUser = operUser;
	}


}
