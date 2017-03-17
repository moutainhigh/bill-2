/********************************************
* 文件名称: MonthBillDetailInfo.java
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

import java.lang.*;
import java.util.*;
import java.math.*;

public class MonthBillDetailInfo{
	//主键
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//明细id
	private String storemxId = " ";
	public String getStoremxId(){
		return storemxId;
	}
	public void setStoremxId(String storemxId){
		this.storemxId = storemxId;
	}

	//票号
	private String billNo = " ";
	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	//票据种类
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//票面金额
	private String billMoney = " ";
	public String getBillMoney(){
		return billMoney;
	}
	public void setBillMoney(String billMoney){
		this.billMoney = billMoney;
	}

	//出票日
	private String issueDt = " ";
	public String getIssueDt(){
		return issueDt;
	}
	public void setIssueDt(String issueDt){
		this.issueDt = issueDt;
	}

	//到期日
	private String dueDt = " ";
	public String getDueDt(){
		return dueDt;
	}
	public void setDueDt(String dueDt){
		this.dueDt = dueDt;
	}

	//不得转让标记
	private String converyMark = " ";
	public String getConveryMark(){
		return converyMark;
	}
	public void setConveryMark(String converyMark){
		this.converyMark = converyMark;
	}

	//出票人类别
	private String remitterRole = " ";
	public String getRemitterRole(){
		return remitterRole;
	}
	public void setRemitterRole(String remitterRole){
		this.remitterRole = remitterRole;
	}

	//出票人组织机构代码
	private String remitterOrgcode = " ";
	public String getRemitterOrgcode(){
		return remitterOrgcode;
	}
	public void setRemitterOrgcode(String remitterOrgcode){
		this.remitterOrgcode = remitterOrgcode;
	}

	//出票人名称
	private String remitter = " ";
	public String getRemitter(){
		return remitter;
	}
	public void setRemitter(String remitter){
		this.remitter = remitter;
	}

	//出票人账号
	private String remitterAcct = " ";
	public String getRemitterAcct(){
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct){
		this.remitterAcct = remitterAcct;
	}

	//出票人行号
	private String remitterBankNo = " ";
	public String getRemitterBankNo(){
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo){
		this.remitterBankNo = remitterBankNo;
	}

	//信用等级
	private String creditRate = " ";
	public String getCreditRate(){
		return creditRate;
	}
	public void setCreditRate(String creditRate){
		this.creditRate = creditRate;
	}

	//评级机构
	private String creditAgency = " ";
	public String getCreditAgency(){
		return creditAgency;
	}
	public void setCreditAgency(String creditAgency){
		this.creditAgency = creditAgency;
	}

	//评级到期日期
	private String creditDuedt = " ";
	public String getCreditDuedt(){
		return creditDuedt;
	}
	public void setCreditDuedt(String creditDuedt){
		this.creditDuedt = creditDuedt;
	}

	//承兑人名称
	private String acceptor = " ";
	public String getAcceptor(){
		return acceptor;
	}
	public void setAcceptor(String acceptor){
		this.acceptor = acceptor;
	}

	//承兑人帐号
	private String acceptorAcct = " ";
	public String getAcceptorAcct(){
		return acceptorAcct;
	}
	public void setAcceptorAcct(String acceptorAcct){
		this.acceptorAcct = acceptorAcct;
	}

	//承兑行行号
	private String acceptorBankNo = " ";
	public String getAcceptorBankNo(){
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo){
		this.acceptorBankNo = acceptorBankNo;
	}

	//收款人名称
	private String payee = " ";
	public String getPayee(){
		return payee;
	}
	public void setPayee(String payee){
		this.payee = payee;
	}

	//收款人账号
	private String payeeAcct = " ";
	public String getPayeeAcct(){
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct){
		this.payeeAcct = payeeAcct;
	}

	//收款人开户行行号
	private String payeeBankNo = " ";
	public String getPayeeBankNo(){
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo){
		this.payeeBankNo = payeeBankNo;
	}

	//撤票日期
	private String repealDt = " ";
	public String getRepealDt(){
		return repealDt;
	}
	public void setRepealDt(String repealDt){
		this.repealDt = repealDt;
	}

	//撤票时间
	private String repealTime = " ";
	public String getRepealTime(){
		return repealTime;
	}
	public void setRepealTime(String repealTime){
		this.repealTime = repealTime;
	}

	//撤票历史出票人类别
	private String histRemitterRole = " ";
	public String getHistRemitterRole(){
		return histRemitterRole;
	}
	public void setHistRemitterRole(String histRemitterRole){
		this.histRemitterRole = histRemitterRole;
	}

	//撤票历史出票人组织机构代码
	private String histOrgcode = " ";
	public String getHistOrgcode(){
		return histOrgcode;
	}
	public void setHistOrgcode(String histOrgcode){
		this.histOrgcode = histOrgcode;
	}

	//撤票历史出票人账号
	private String histRemitterAcct = " ";
	public String getHistRemitterAcct(){
		return histRemitterAcct;
	}
	public void setHistRemitterAcct(String histRemitterAcct){
		this.histRemitterAcct = histRemitterAcct;
	}

	//撤票历史出票人开户行名
	private String histRemitterBankno = " ";
	public String getHistRemitterBankno(){
		return histRemitterBankno;
	}
	public void setHistRemitterBankno(String histRemitterBankno){
		this.histRemitterBankno = histRemitterBankno;
	}

	//撤票历史出票人承接行行号
	private String histRemitterAcptBankno = " ";
	public String getHistRemitterAcptBankno(){
		return histRemitterAcptBankno;
	}
	public void setHistRemitterAcptBankno(String histRemitterAcptBankno){
		this.histRemitterAcptBankno = histRemitterAcptBankno;
	}


}
