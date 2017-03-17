/********************************************
* 文件名称: DiscApplyInfo.java
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
package com.herongtech.console.domain.disc.bean;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.common.ICommonService;




public class DiscApplyInfo{
	//贴现流水号
	private String discId = " ";
	public String getDiscId(){
		return discId;
	}
	public void setDiscId(String discId){
		this.discId = discId;
	}

	//批次号
	private String batchNo = " ";
	public String getBatchNo(){
		return batchNo;
	}
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}

	//利率
	private double rate = 0.0;
	public double getRate(){
		return rate;
	}
	public void setRate(double rate){
		this.rate = rate;
	}

	//付息比例
	private double buyPayRate = 0.0;
	public double getBuyPayRate(){
		return buyPayRate;
	}
	public void setBuyPayRate(double buyPayRate){
		this.buyPayRate = buyPayRate;
	}

	//客户账号
	private String custAccount = " ";
	public String getCustAccount(){
		return custAccount;
	}
	public void setCustAccount(String custAccount){
		this.custAccount = custAccount;
	}

	//机构
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//付息方式
	private String payType = " ";
	public String getPayType(){
		return payType;
	}
	public void setPayType(String payType){
		this.payType = payType;
	}

	//付息人行号
	private String payBankNo = " ";
	public String getPayBankNo(){
		return payBankNo;
	}
	public void setPayBankNo(String payBankNo){
		this.payBankNo = payBankNo;
	}

	//付息人名称
	private String payCustName = " ";
	public String getPayCustName(){
		return payCustName;
	}
	public void setPayCustName(String payCustName){
		this.payCustName = payCustName;
	}

	//付息人账号
	private String payAccount = " ";
	public String getPayAccount(){
		return payAccount;
	}
	public void setPayAccount(String payAccount){
		this.payAccount = payAccount;
	}

	//产品编号
	private String prodNo = " ";
	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	//利率类型
	private String rateType = " ";
	public String getRateType(){
		return rateType;
	}
	public void setRateType(String rateType){
		this.rateType = rateType;
	}

	//申请票据类型
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//申请种类
	private String billClass = " ";
	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
	}

	//贴现日
	private String discDt = " ";
	public String getDiscDt(){
		return discDt;
	}
	public void setDiscDt(String discDt){
		this.discDt = discDt;
	}

	//客户经理名称
	private String custManagerName = " ";
	public String getCustManagerName(){
		return custManagerName;
	}
	public void setCustManagerName(String custManagerName){
		this.custManagerName = custManagerName;
	}

	//是否贴查同步
	private String isTc = " ";
	public String getIsTc(){
		return isTc;
	}
	public void setIsTc(String isTc){
		this.isTc = isTc;
	}

	//代理贴现人名称
	private String agentCustName = " ";
	public String getAgentCustName(){
		return agentCustName;
	}
	public void setAgentCustName(String agentCustName){
		this.agentCustName = agentCustName;
	}

	//代理贴现人账号
	private String agentCustAccount = " ";
	public String getAgentCustAccount(){
		return agentCustAccount;
	}
	public void setAgentCustAccount(String agentCustAccount){
		this.agentCustAccount = agentCustAccount;
	}

	//顺延方式
	private String delayType = " ";
	public String getDelayType(){
		return delayType;
	}
	public void setDelayType(String delayType){
		this.delayType = delayType;
	}

	//赎回截止日
	private String redemptionDt = " ";
	public String getRedemptionDt(){
		return redemptionDt;
	}
	public void setRedemptionDt(String redemptionDt){
		this.redemptionDt = redemptionDt;
	}

	//部门号
	private String deptNo = " ";
	public String getDeptNo(){
		return deptNo;
	}
	public void setDeptNo(String deptNo){
		this.deptNo = deptNo;
	}

	//创建时间
	private String createTime = " ";
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	//部门名称
	private String deptName = " ";
	public String getDeptName(){
		return deptName;
	}
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	//赎回开放日
	private String redeemDate = " ";
	public String getRedeemDate(){
		return redeemDate;
	}
	public void setRedeemDate(String redeemDate){
		this.redeemDate = redeemDate;
	}

	//客户名称
	private String custName = " ";
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	//委托人名称
	private String trusteeName = " ";
	public String getTrusteeName(){
		return trusteeName;
	}
	public void setTrusteeName(String trusteeName){
		this.trusteeName = trusteeName;
	}

	//委托人他行账号
	private String trusteeAcct = " ";
	public String getTrusteeAcct(){
		return trusteeAcct;
	}
	public void setTrusteeAcct(String trusteeAcct){
		this.trusteeAcct = trusteeAcct;
	}

	//委托人他行行名
	private String trusteeBankName = " ";
	public String getTrusteeBankName(){
		return trusteeBankName;
	}
	public void setTrusteeBankName(String trusteeBankName){
		this.trusteeBankName = trusteeBankName;
	}

	//委托人他行行号
	private String trusteeBankNo = " ";
	public String getTrusteeBankNo(){
		return trusteeBankNo;
	}
	public void setTrusteeBankNo(String trusteeBankNo){
		this.trusteeBankNo = trusteeBankNo;
	}

	//经营机构归属号
	private String profOwnerNo = " ";
	public String getProfOwnerNo(){
		return profOwnerNo;
	}
	public void setProfOwnerNo(String profOwnerNo){
		this.profOwnerNo = profOwnerNo;
	}

	//经营机构归属名称
	private String profOwner = " ";
	public String getProfOwner(){
		return profOwner;
	}
	public void setProfOwner(String profOwner){
		this.profOwner = profOwner;
	}

	//归属客户名称
	private String billOwner = " ";
	public String getBillOwner(){
		return billOwner;
	}
	public void setBillOwner(String billOwner){
		this.billOwner = billOwner;
	}
	//贴现申请人社会信用代码 
	private String socialCreditCode;
	public String getSocialCreditCode() {
		return socialCreditCode;
	}
	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}
	//贴现申请人企业规模
	private String enterpriseScale;
	public String getEnterpriseScale() {
		return enterpriseScale;
	}
	public void setEnterpriseScale(String enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}
	//贴现申请人是否为三农企业
	private String isRuralEnterprises;
	public String getIsRuralEnterprises() {
		return isRuralEnterprises;
	}
	public void setIsRuralEnterprises(String isRuralEnterprises) {
		this.isRuralEnterprises = isRuralEnterprises;
	}
	//行业投向
	private String professionName = " ";
	
	public String getProfessionName(){
		return professionName;
	}
	public void setProfessionName(String professionName){
		this.professionName = professionName;
	}
	

	//经营机构归属#
	private String workingadsNo1 = " ";
	public String getWorkingadsNo1(){
		return workingadsNo1;
	}
	public void setWorkingadsNo1(String workingadsNo1){
		this.workingadsNo1 = workingadsNo1;
	}

	//成本利率
	private double cbRate = 0.0;
	public double getCbRate(){
		return cbRate;
	}
	public void setCbRate(double cbRate){
		this.cbRate = cbRate;
	}

	//银行产品编号
	private String bankProdNo = " ";
	public String getBankProdNo(){
		return bankProdNo;
	}
	public void setBankProdNo(String bankProdNo){
		this.bankProdNo = bankProdNo;
	}

	//经营机构id
	private String workingadsNo = " ";
	public String getWorkingadsNo(){
		return workingadsNo;
	}
	public void setWorkingadsNo(String workingadsNo){
		this.workingadsNo = workingadsNo;
	}

	//签发机构
	private String signBranchNo = " ";
	public String getSignBranchNo(){
		return signBranchNo;
	}
	public void setSignBranchNo(String signBranchNo){
		this.signBranchNo = signBranchNo;
	}

	//客户经理编号
	private String custManage = " ";
	public String getCustManage(){
		return custManage;
	}
	public void setCustManage(String custManage){
		this.custManage = custManage;
	}

	//客户号
	private String custNo = " ";
	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	//买方付息账号类型
	private String payAccountType = " ";
	public String getPayAccountType(){
		return payAccountType;
	}
	public void setPayAccountType(String payAccountType){
		this.payAccountType = payAccountType;
	}

	//订单编号
	private String orderId = " ";
	public String getOrderId(){
		return orderId;
	}
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	//卖方账号类型
	private String custAccountType = " ";
	public String getCustAccountType(){
		return custAccountType;
	}
	public void setCustAccountType(String custAccountType){
		this.custAccountType = custAccountType;
	}

	//账务机构号
	private String acctBranchNo = " ";
	public String getAcctBranchNo(){
		return acctBranchNo;
	}
	public void setAcctBranchNo(String acctBranchNo){
		this.acctBranchNo = acctBranchNo;
	}

	//产品业务类型
	private String prodBusiType = " ";
	public String getProdBusiType(){
		return prodBusiType;
	}
	public void setProdBusiType(String prodBusiType){
		this.prodBusiType = prodBusiType;
	}

	//创建日期
	private String createDt = " ";
	public String getCreateDt(){
		return createDt;
	}
	public void setCreateDt(String createDt){
		this.createDt = createDt;
	}

	//批次状态
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
	
	//总利息
	private String totalInterest;
	public String getTotalInterest() {
		return totalInterest;
	}
	public void setTotalInterest(String totalInterest) {
		this.totalInterest = totalInterest;
	}
	
	//实付金额
	private String actualAmount;
	public String getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}
	/**获取付息方式的字符串*/
	public String getPayTypeString() {
		ICommonService service = ServiceFactory.getCommonService();
		try {
			if(this.getPayType()==null || "".equals(this.getPayType())) {
				return "";
			}
			return service.getPayTypeString(this.getPayType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
