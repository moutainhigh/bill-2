/********************************************
* 文件名称: AcctFlow.java
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
package com.herongtech.console.domain.acct.bean;


public class AcctFlow{
	private String afId = " ";	//主键
	private String foreFlowNo = " ";	//前台流水号
	private String backFlowNo = " ";	//后台流水号
	private String transNo = " ";	//交易编码
	private String transName = " ";	//交易名称
	private String transDt = " ";	//交易日期
	private String transTm = " ";	//交易时间
	private String acctType = " ";	//交易类型
	private String acctStatus = " ";	//交易状态
	private String prodNo = " ";	//产品编码
	private String transBranchNo = " ";	//交易机构号
	private String transBranchName = " ";	//交易机构名称
	private String transUserNo = " ";	//交易记账柜员
	private String acctBranchNo = " ";	//账务所属机构号
	private Double totalAmount;	//票面总额
	private Double settlementMoney;	//实收（付）金额
	private Double settlementInterest;	//应收（付）利息
	public String getAfId(){
		return afId;
	}
	public void setAfId(String afId){
		this.afId = afId;
	}

	public String getForeFlowNo(){
		return foreFlowNo;
	}
	public void setForeFlowNo(String foreFlowNo){
		this.foreFlowNo = foreFlowNo;
	}

	public String getBackFlowNo(){
		return backFlowNo;
	}
	public void setBackFlowNo(String backFlowNo){
		this.backFlowNo = backFlowNo;
	}

	public String getTransNo(){
		return transNo;
	}
	public void setTransNo(String transNo){
		this.transNo = transNo;
	}

	public String getTransName(){
		return transName;
	}
	public void setTransName(String transName){
		this.transName = transName;
	}

	public String getTransDt(){
		return transDt;
	}
	public void setTransDt(String transDt){
		this.transDt = transDt;
	}

	public String getTransTm(){
		return transTm;
	}
	public void setTransTm(String transTm){
		this.transTm = transTm;
	}

	public String getAcctType(){
		return acctType;
	}
	public void setAcctType(String acctType){
		this.acctType = acctType;
	}

	public String getAcctStatus(){
		return acctStatus;
	}
	public void setAcctStatus(String acctStatus){
		this.acctStatus = acctStatus;
	}

	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	public String getTransBranchNo(){
		return transBranchNo;
	}
	public void setTransBranchNo(String transBranchNo){
		this.transBranchNo = transBranchNo;
	}

	public String getTransBranchName(){
		return transBranchName;
	}
	public void setTransBranchName(String transBranchName){
		this.transBranchName = transBranchName;
	}

	public String getTransUserNo(){
		return transUserNo;
	}
	public void setTransUserNo(String transUserNo){
		this.transUserNo = transUserNo;
	}

	public String getAcctBranchNo(){
		return acctBranchNo;
	}
	public void setAcctBranchNo(String acctBranchNo){
		this.acctBranchNo = acctBranchNo;
	}

	public Double getTotalAmount(){
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount){
		this.totalAmount = totalAmount;
	}

	public Double getSettlementMoney(){
		return settlementMoney;
	}
	public void setSettlementMoney(Double settlementMoney){
		this.settlementMoney = settlementMoney;
	}

	public Double getSettlementInterest(){
		return settlementInterest;
	}
	public void setSettlementInterest(Double settlementInterest){
		this.settlementInterest = settlementInterest;
	}


}
