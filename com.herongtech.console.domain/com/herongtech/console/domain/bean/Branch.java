/********************************************
* 文件名称: Branch.java
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


public class Branch{
	
	private String parentName = "";
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	//机构编号
	private String branchNo = " ";
	/**机构号（机构编号）*/
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//机构
	private String branchId = " ";
	/**机构id*/
	public String getBranchId(){
		return branchId;
	}
	public void setBranchId(String branchId){
		this.branchId = branchId;
	}

	//机构级别
	private String branchLevel = " ";
	/**机构级别*/
	public String getBranchLevel(){
		return branchLevel;
	}
	/**机构级别*/
	public void setBranchLevel(String branchLevel){
		this.branchLevel = branchLevel;
	}

	//机构名称
	private String branchName = " ";
	/**机构名称*/
	public String getBranchName(){
		return branchName;
	}
	/**机构名称*/
	public void setBranchName(String branchName){
		this.branchName = branchName;
	}

	//机构简称
	private String shortName = " ";
	/**机构简称*/
	public String getShortName(){
		return shortName;
	}
	/**机构简称*/
	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	//机构内码
	private String branchPath = " ";
	public String getBranchPath(){
		return branchPath;
	}
	public void setBranchPath(String branchPath){
		this.branchPath = branchPath;
	}

	//组织机构代码
	private String orgCode = " ";
	/**组织机构代码*/
	public String getOrgCode(){
		return orgCode;
	}
	/**组织机构代码*/
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}

	//上级机构编码
	private String parentBrchCode = " ";
	public String getParentBrchCode(){
		return parentBrchCode;
	}
	public void setParentBrchCode(String parentBrchCode){
		this.parentBrchCode = parentBrchCode;
	}

	//上级机构ID
	private String parentBrchId = " ";
	public String getParentBrchId(){
		return parentBrchId;
	}
	public void setParentBrchId(String parentBrchId){
		this.parentBrchId = parentBrchId;
	}

	//联行行号
	private String payBankNo = " ";
	/**联行行号*/
	public String getPayBankNo(){
		return payBankNo;
	}
	/**联行行号*/
	public void setPayBankNo(String payBankNo){
		this.payBankNo = payBankNo;
	}

	//电票权限
	private String elecAuth = " ";
	/**电票权限*/
	public String getElecAuth(){
		return elecAuth;
	}
	/**电票权限*/
	public void setElecAuth(String elecAuth){
		this.elecAuth = elecAuth;
	}

	//是否生效
	private String ifEffective = " ";
	public String getIfEffective(){
		return ifEffective;
	}
	public void setIfEffective(String ifEffective){
		this.ifEffective = ifEffective;
	}

	//法人行编号
	private String bankNo = " ";
	/**法人行编号*/
	public String getBankNo(){
		return bankNo;
	}
	/**法人行编号*/
	public void setBankNo(String bankNo){
		this.bankNo = bankNo;
	}
	
	//备注1
	private String remark1 = " ";
	public String getRemark1(){
		return remark1;
	}
	public void setRemark1(String remark1){
		this.remark1 = remark1;
	}

	//备注2
	private String remark2 = " ";
	public String getRemark2(){
		return remark2;
	}
	public void setRemark2(String remark2){
		this.remark2 = remark2;
	}

	//备注3
	private String remark3 = " ";
	public String getRemark3(){
		return remark3;
	}
	public void setRemark3(String remark3){
		this.remark3 = remark3;
	}

	//地址
	private String address = " ";
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	//参与者类别
	private String partnerType = "RC00";
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
}
