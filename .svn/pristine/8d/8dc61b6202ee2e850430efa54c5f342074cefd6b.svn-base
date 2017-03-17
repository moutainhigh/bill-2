/********************************************
* 文件名称: BillInfoDTO.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: fanmin
* 开发时间:  2016年9月20日13:45:01
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.domain.acct.bean.AcctBalance;

public class BillInfoDTO {
	private String billNo = " ";	//票号
	private String billType = " ";	//票据种类
	private String billOwner = " ";//所属客户名称
	private String remitter = " ";	//出票人名称
	private String remitterBankNo = " ";	//出票人开户行行号
	private String remitterBankName = " ";	//出票人开户行名称
	private String acceptor = " ";	//承兑人名称
	private String acceptorBankNo = " ";	//承兑人行号
	private String acceptorBankName = " ";	//承兑人开户行名称
	private String issueDt = " ";	//出票日
	private String dueDt = " ";	//到期日
	private double billMoney = 0.0;	//票面金额
	private String custBankNo = " ";	//客户所属行号
	private String mxId = " ";	//明细流水号
	private String billClass = " ";	//票据分类
	private List<BlackList> blackList= new ArrayList<BlackList>();// 黑名单
	private List<RiskBill> riskBillList = new ArrayList<RiskBill>();// 风险票
	private List<AcctBalance> acctBalanceBillList = new ArrayList<AcctBalance>();// 账务库存票
	private Set<String> checkBankNo  = new HashSet<String>() ;// 待检查的行号
	@SuppressWarnings("unused")
	private String isRiskBill ="0";//是否有风险票
	@SuppressWarnings("unused")
	private String isBlackBill ="0";//是否有黑名单
	@SuppressWarnings("unused")
	private String isAcctBalanceBill ="0";//是否账务库存票
	private String checkResult;// 风险描述
	private String enterpriseName;// 黑名单企业
	private String desc;// 黑名单理由
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillOwner() {
		return billOwner;
	}
	public void setBillOwner(String billOwner) {
		this.billOwner = billOwner;
	}
	public String getRemitter() {
		return remitter;
	}
	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}
	public String getRemitterBankNo() {
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo) {
		this.remitterBankNo = remitterBankNo;
	}
	public String getRemitterBankName() {
		return remitterBankName;
	}
	public void setRemitterBankName(String remitterBankName) {
		this.remitterBankName = remitterBankName;
	}
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getAcceptorBankNo() {
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo) {
		this.acceptorBankNo = acceptorBankNo;
	}
	public String getAcceptorBankName() {
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName) {
		this.acceptorBankName = acceptorBankName;
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
	public double getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}
	public String getCustBankNo() {
		return custBankNo;
	}
	public void setCustBankNo(String custBankNo) {
		this.custBankNo = custBankNo;
	}
	public String getMxId() {
		return mxId;
	}
	public void setMxId(String mxId) {
		this.mxId = mxId;
	}
	public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
	public List<BlackList> getBlackList() {
		return blackList;
	}
	public void setBlackList(List<BlackList> blackList) {
		this.blackList = blackList;
	}
	public List<RiskBill> getRiskBillList() {
		return riskBillList;
	}
	public void setRiskBillList(List<RiskBill> riskBillList) {
		this.riskBillList = riskBillList;
	}
	public List<AcctBalance> getAcctBalanceBillList() {
		return acctBalanceBillList;
	}
	public void setAcctBalanceBillList(List<AcctBalance> acctBalanceBillList) {
		this.acctBalanceBillList = acctBalanceBillList;
	}
	public Set<String> getCheckBankNo() {
		return checkBankNo;
	}
	public void setCheckBankNo(Set<String> checkBankNo) {
		this.checkBankNo = checkBankNo;
	}
	public String getIsRiskBill() {
		return riskBillList.size()==0 ? IDict.K_YORN.K_YORN_NO :IDict.K_YORN.K_YORN_YES;
	}
	public void setIsRiskBill(String isRiskBill) {
		this.isRiskBill = isRiskBill;
	}
	public String getIsBlackBill() {
		return blackList.size()==0 ? IDict.K_YORN.K_YORN_NO :IDict.K_YORN.K_YORN_YES;
	}
	public void setIsBlackBill(String isBlackBill) {
		this.isBlackBill = isBlackBill;
	}
	public String getIsAcctBalanceBill() {
		return acctBalanceBillList.size()==0 ? IDict.K_YORN.K_YORN_NO :IDict.K_YORN.K_YORN_YES;
	}
	public void setIsAcctBalanceBill(String isAcctBalanceBill) {
		this.isAcctBalanceBill = isAcctBalanceBill;
	}
	public String getCheckResult() {
		if (riskBillList.size()>0) {
			checkResult = riskBillList.get(0).getDescription();
		}
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 添加黑名单
	 * 
	 * @param black
	 */
	public void addBlackList(BlackList black) {
		if (black != null) {
			blackList.add(black);
		}
	}

	/**
	 * 添加风险票
	 * 
	 * @param riskBill
	 */
	public void addRiskBill(RiskBill riskBill) {
		if (riskBill != null) {
			riskBillList.add(riskBill);
		}
	}
	/**
	 * 添加账务库存票
	 * 
	 * @param riskBill
	 */
	public void addAcctBalanceBill(AcctBalance acctBalance) {
		if (acctBalance != null) {
			acctBalanceBillList.add(acctBalance);
		}
	}

	/**
	 * 添加待检查的行号
	 * 
	 * @param bankNo
	 */
	public void addCheckBankNo(String bankNo) {
		if(StringUtils.isNotEmpty(bankNo)){
			checkBankNo.add(bankNo);
		}
	}


	/**
	 * 是否检查风险票
	 * 纸票检查，电票不检查
	 * @return	是否需要检查
	 */
	public boolean isNeedCheckRiskBill() {
		return StringUtils.equals(billClass, IDict.K_BILL_CLASS.K_ENTY_BILL);
	}
}
