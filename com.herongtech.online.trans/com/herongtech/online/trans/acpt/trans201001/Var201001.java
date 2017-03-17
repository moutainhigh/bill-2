/********************************************
 * 文件名称: Var201001.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 20160826
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.acpt.trans201001;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.console.service.busiservice.acpt.AcptBillInfoBean;

public class Var201001 {

	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	//签发机构
	private String branchNo;
	//银承协议编号
	private String protocalNo;
	//出票人客户号
	private String remitterCustNo;
	//出票人客户账号
	private String remitterAcct;
	//出票日
	private String issueDt;
	//到期日
	private String dueDt;
	//票据分类
	private String billClass;
	//票据种类
	private String billType;
	//第一还款账号
	private String accountNo1;
	//第一保证金限额
	private String grantAmt1 = "0.00";
	//第二还款账号
	private String accountNo2 ;
	//第二保证金限额
	private String grantAmt2 = "0.00";
	//第三还款账号
	private String accountNo3;
	//第三保证金限额
	private String grantAmt3 = "0.00";
	//第四还款账号
	private String accountNo4;
	//第四保证金限额
	private String grantAmt4 = "0.00";
	//第五还款账号
	private String accountNo5;
	//第五保证金限额
	private String grantAmt5 = "0.00";
	//贷款系统
	private String loanSystem;//1-对公贷款 2-对私贷款
	
	//批次号
	private String batchNo;
	


	//公共对象
	TransPub transPub = null;
	private List<AcptBillInfoBean> billList;
	
	//收款人账号
	private String payeeAcct;
	
	//票面金额
	private String billMoney;
	
	//币种
	private String currencyCategory;
	
	//收款人名称
	
	private String payee;
	
	//收款人开户行名称
	private String payeeBankName;
	
	public String getPayeeBankName() {
		return payeeBankName;
	}

	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getCurrencyCategory() {
		return currencyCategory;
	}

	public void setCurrencyCategory(String currencyCategory) {
		this.currencyCategory = currencyCategory;
	}

	public String getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(String billMoney) {
		this.billMoney = billMoney;
	}

	public String getPayeeAcct() {
		return payeeAcct;
	}

	public void setPayeeAcct(String payeeAcct) {
		this.payeeAcct = payeeAcct;
	}

	public List<AcptBillInfoBean> getBillList() {
		return billList;
	}

	public void setBillList(List<AcptBillInfoBean> billList) {
		this.billList = billList;
	}

	public void setTransPub(TransPub transPub){
		this.transPub = transPub;
	}
	
	public TransPub getTransPub(){
		return transPub;
	}
		
	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public String getProtocalNo() {
		return protocalNo;
	}

	public void setProtocalNo(String protocalNo) {
		this.protocalNo = protocalNo;
	}

	public String getRemitterCustNo() {
		return remitterCustNo;
	}

	public void setRemitterCustNo(String remitterCustNo) {
		this.remitterCustNo = remitterCustNo;
	}

	public String getRemitterAcct() {
		return remitterAcct;
	}

	public void setRemitterAcct(String remitterAcct) {
		this.remitterAcct = remitterAcct;
	}

	public String getIssueDt() {
		return issueDt;
	}

	public void setIssueDt(String issueDt) {
		this.issueDt = issueDt;
	}

	public String getDueDt() {
		return dueDt;
	}

	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}

	public String getBillClass() {
		return billClass;
	}

	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getAccountNo1() {
		return accountNo1;
	}

	public void setAccountNo1(String accountNo1) {
		this.accountNo1 = accountNo1;
	}

	public String getGrantAmt1() {
		return grantAmt1;
	}

	public void setGrantAmt1(String grantAmt1) {
		this.grantAmt1 = grantAmt1;
	}

	public String getAccountNo2() {
		return accountNo2;
	}

	public void setAccountNo2(String accountNo2) {
		this.accountNo2 = accountNo2;
	}

	public String getGrantAmt2() {
		return grantAmt2;
	}

	public void setGrantAmt2(String grantAmt2) {
		this.grantAmt2 = grantAmt2;
	}

	public String getAccountNo3() {
		return accountNo3;
	}

	public void setAccountNo3(String accountNo3) {
		this.accountNo3 = accountNo3;
	}

	public String getGrantAmt3() {
		return grantAmt3;
	}

	public void setGrantAmt3(String grantAmt3) {
		this.grantAmt3 = grantAmt3;
	}

	public String getAccountNo4() {
		return accountNo4;
	}

	public void setAccountNo4(String accountNo4) {
		this.accountNo4 = accountNo4;
	}

	public String getGrantAmt4() {
		return grantAmt4;
	}

	public void setGrantAmt4(String grantAmt4) {
		this.grantAmt4 = grantAmt4;
	}

	public String getAccountNo5() {
		return accountNo5;
	}

	public void setAccountNo5(String accountNo5) {
		this.accountNo5 = accountNo5;
	}

	public String getGrantAmt5() {
		return grantAmt5;
	}

	public void setGrantAmt5(String grantAmt5) {
		this.grantAmt5 = grantAmt5;
	}

	public String getLoanSystem() {
		return loanSystem;
	}

	public void setLoanSystem(String loanSystem) {
		this.loanSystem = loanSystem;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
		
}
