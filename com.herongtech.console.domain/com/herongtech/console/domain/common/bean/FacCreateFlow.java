/********************************************
* 文件名称: FacCreateFlow.java
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
package com.herongtech.console.domain.common.bean;


public class FacCreateFlow{
	
	public FacCreateFlow(String id){
		this.id = id;
	}
	public FacCreateFlow(){
	}
	//主键
	private String id = "";
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	//票号
	private String billNo = " ";
	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	//登记中心id
	private String rgctId = "";
	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	//票据来源
	private String source = " ";
	public String getSource(){
		return source;
	}
	public void setSource(String source){
		this.source = source;
	}

	//出票人行号
	private String remitterBankNo = " ";
	public String getRemitterBankNo(){
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo){
		this.remitterBankNo = remitterBankNo;
	}

	//出账申请流水号
	private String loanRequisition = " ";
	public String getLoanRequisition(){
		return loanRequisition;
	}
	public void setLoanRequisition(String loanRequisition){
		this.loanRequisition = loanRequisition;
	}

	//票面金额
	private double billMoney = 0.0;
	public double getBillMoney(){
		return billMoney;
	}
	public void setBillMoney(double billMoney){
		this.billMoney = billMoney;
	}

	//票据种类
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//票据分类
	private String billClass = " ";
	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
	}

	//交易对手机构
	private String aimBranchNo = " ";
	public String getAimBranchNo(){
		return aimBranchNo;
	}
	public void setAimBranchNo(String aimBranchNo){
		this.aimBranchNo = aimBranchNo;
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

	//状态
	private String status = " ";
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	//对应最近票据来源的清单id
	private String billId = "";
	public String getBillId(){
		return billId;
	}
	public void setBillId(String billId){
		this.billId = billId;
	}

	//额度类型
	private String facType = " ";
	public String getFacType(){
		return facType;
	}
	public void setFacType(String facType){
		this.facType = facType;
	}

	//额度编号
	private String facilityCode = " ";
	public String getFacilityCode(){
		return facilityCode;
	}
	public void setFacilityCode(String facilityCode){
		this.facilityCode = facilityCode;
	}

	//客户号
	private String custNo = " ";
	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	//承兑人
	private String acceptor = " ";
	public String getAcceptor(){
		return acceptor;
	}
	public void setAcceptor(String acceptor){
		this.acceptor = acceptor;
	}

	//到期日
	private String dueDt = " ";
	public String getDueDt(){
		return dueDt;
	}
	public void setDueDt(String dueDt){
		this.dueDt = dueDt;
	}

	//产品编码
	private String productId = " ";
	public String getProductId(){
		return productId;
	}
	public void setProductId(String productId){
		this.productId = productId;
	}

	//额度所属客户
	private String ownerPartyId = " ";
	public String getOwnerPartyId(){
		return ownerPartyId;
	}
	public void setOwnerPartyId(String ownerPartyId){
		this.ownerPartyId = ownerPartyId;
	}

	//是否可循环
	private String isCyc = " ";
	public String getIsCyc(){
		return isCyc;
	}
	public void setIsCyc(String isCyc){
		this.isCyc = isCyc;
	}

	//签发机构
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//释放日期
	private String releaseDate = " ";
	public String getReleaseDate(){
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	//释放时间
	private String releaseTime = " ";
	public String getReleaseTime(){
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime){
		this.releaseTime = releaseTime;
	}

}
