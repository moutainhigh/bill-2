/********************************************
* 文件名称: SaveApplyInfo.java
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
package com.herongtech.console.domain.save.bean;
public class SaveApplyInfo{
	//存票流水号
	private String saveId = " ";
	public String getSaveId(){
		return saveId;
	}
	public void setSaveId(String saveId){
		this.saveId = saveId;
	}

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

	//创建日期
	private String createDate = " ";
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}
	//创建时间
	private String createTime = " ";
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	//产品编号
	private String prodNo = " ";
	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	//客户经理编号
	private String custManager = " ";
	public String getCustManager(){
		return custManager;
	}
	public void setCustManager(String custManager){
		this.custManager = custManager;
	}

	//是否贴查同步
	private String isTc = " ";
	public String getIsTc(){
		return isTc;
	}
	public void setIsTc(String isTc){
		this.isTc = isTc;
	}

	//部门号
	private String deptNo = " ";
	public String getDeptNo(){
		return deptNo;
	}
	public void setDeptNo(String deptNo){
		this.deptNo = deptNo;
	}

	//批次类型
	private String batchType = " ";
	public String getBatchType(){
		return batchType;
	}
	public void setBatchType(String batchType){
		this.batchType = batchType;
	}

	//批次种类
	private String batchClass = " ";
	public String getBatchClass(){
		return batchClass;
	}
	public void setBatchClass(String batchClass){
		this.batchClass = batchClass;
	}

	//操作柜员
	private String operNo = " ";
	public String getOperNo(){
		return operNo;
	}
	public void setOperNo(String operNo){
		this.operNo = operNo;
	}

	//批次号
	private String batchNo = " ";
	public String getBatchNo(){
		return batchNo;
	}
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}

	//部门名称
	private String deptName = " ";
	public String getDeptName(){
		return deptName;
	}
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	//客户经理名称
	private String custManagerName = " ";
	public String getCustManagerName(){
		return custManagerName;
	}
	public void setCustManagerName(String custManagerName){
		this.custManagerName = custManagerName;
	}

	//签发机构
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//质押保证金账号
	private String impawnBailAccount = " ";
	public String getImpawnBailAccount(){
		return impawnBailAccount;
	}
	public void setImpawnBailAccount(String impawnBailAccount){
		this.impawnBailAccount = impawnBailAccount;
	}

	//质押保证金户名
	private String impawnBailName = " ";
	public String getImpawnBailName(){
		return impawnBailName;
	}
	public void setImpawnBailName(String impawnBailName){
		this.impawnBailName = impawnBailName;
	}

	//删除标识
	private String applyStatus = " ";
	public String getApplyStatus(){
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus){
		this.applyStatus = applyStatus;
	}
	
	// 票据总张数
	private String totalNum = "";

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	// 票面总金额
	private String totalMoney = "";

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	//外加
	private String custAccount = " ";
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}
	public String getCustAccount() {
		return custAccount;
	}
	
}
