/********************************************
* 文件名称: BillFlowInfo.java
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
package com.herongtech.rc.domain.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class BillFlowInfo{
	private String id = " ";	//主键
	private String billNo = " ";	//票号
	private String rgctId = " ";	//登记中心id
	private String histId = " ";	//登记中心最近历史id
	private String fromName = " ";	//背书人全称
	private String fromCustNo = " ";	//背书人客户号
	private String fromAcctNo = " ";	//背书人账号
	private String fromBankNo = " ";	//背书人行号
	private String toName = " ";	//被背书人全称
	private String toCustNo = " ";	//被背书人客户号
	private String toAcctNo = " ";	//被背书人账号
	private String toBankNo = " ";	//被背书人行号
	private String billStatus = " ";	//票据状态
	private String holdCustNo = " ";	//票据持有人客户号
	private String holdCustName = " ";	//票据持有人名称
	private String holdAcctNo = " ";	//票据持有人账号
	private String holdBankNo = " ";	//票据持有人行号
	private String createDt = " ";	//生成日期
	private String createTime = " ";	//生成时间
	private String operResult = " ";	//操作结果
	private String draftNoReq = " ";	//请求报文类型编号
	private String signupDate = " ";	//签收日期
	private String signupTime = " ";	//签收时间
	private String signupMark = " ";	//签收标记
	private String signupInfo = " ";	//签收信息
	private String resultMsg = " ";	//操作信息
	private String isOnline = " ";	//清算标记
	private String inAcctNo = " ";	//入账帐号
	private String inBankNo = " ";	//入账行号
	private String remark = " ";	//备注
	private String operType = " ";	//执行方式
	private String remitter = " ";	//出票人
	private String applyDate = " ";	//申请日期
	private String applyTime = " ";	//申请时间
	private String busiType = " ";	//业务类型
	private String reqSid = " ";	//请求报文id
	private String transDate = " ";	//交易日期
	private String transTime = " ";	//交易时间
	private String billType = " ";	//票据种类
	private Double billMoney;	//票面金额
	private String issueDt = " ";	//出票日
	private String dueDt = " ";	//到期日
	private String acceptor = " ";	//承兑人
	private String acceptorAcct = " ";	//承兑人账号
	private String acceptorBankName = " ";	//承兑人开户行名称
	private String banEndorsementMark = " ";	//ban_endorsement_mark
	private String printStaus = " ";	//打印状态
	private String status = " ";	//状态
	private String transfor = " ";	//transfor
	private String curStatusString = " ";	//当前状态名称#
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	public String getHistId(){
		return histId;
	}
	public void setHistId(String histId){
		this.histId = histId;
	}

	public String getFromName(){
		return fromName;
	}
	public void setFromName(String fromName){
		this.fromName = fromName;
	}

	public String getFromCustNo(){
		return fromCustNo;
	}
	public void setFromCustNo(String fromCustNo){
		this.fromCustNo = fromCustNo;
	}

	public String getFromAcctNo(){
		return fromAcctNo;
	}
	public void setFromAcctNo(String fromAcctNo){
		this.fromAcctNo = fromAcctNo;
	}

	public String getFromBankNo(){
		return fromBankNo;
	}
	public void setFromBankNo(String fromBankNo){
		this.fromBankNo = fromBankNo;
	}

	public String getToName(){
		return toName;
	}
	public void setToName(String toName){
		this.toName = toName;
	}

	public String getToCustNo(){
		return toCustNo;
	}
	public void setToCustNo(String toCustNo){
		this.toCustNo = toCustNo;
	}

	public String getToAcctNo(){
		return toAcctNo;
	}
	public void setToAcctNo(String toAcctNo){
		this.toAcctNo = toAcctNo;
	}

	public String getToBankNo(){
		return toBankNo;
	}
	public void setToBankNo(String toBankNo){
		this.toBankNo = toBankNo;
	}

	public String getBillStatus(){
		return billStatus;
	}
	public void setBillStatus(String billStatus){
		this.billStatus = billStatus;
	}

	public String getHoldCustNo(){
		return holdCustNo;
	}
	public void setHoldCustNo(String holdCustNo){
		this.holdCustNo = holdCustNo;
	}

	public String getHoldCustName(){
		return holdCustName;
	}
	public void setHoldCustName(String holdCustName){
		this.holdCustName = holdCustName;
	}

	public String getHoldAcctNo(){
		return holdAcctNo;
	}
	public void setHoldAcctNo(String holdAcctNo){
		this.holdAcctNo = holdAcctNo;
	}

	public String getHoldBankNo(){
		return holdBankNo;
	}
	public void setHoldBankNo(String holdBankNo){
		this.holdBankNo = holdBankNo;
	}

	public String getCreateDt(){
		return createDt;
	}
	public void setCreateDt(String createDt){
		this.createDt = createDt;
	}

	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getOperResult(){
		return operResult;
	}
	public void setOperResult(String operResult){
		this.operResult = operResult;
	}

	public String getDraftNoReq(){
		return draftNoReq;
	}
	public void setDraftNoReq(String draftNoReq){
		this.draftNoReq = draftNoReq;
	}

	public String getSignupDate(){
		return signupDate;
	}
	public void setSignupDate(String signupDate){
		this.signupDate = signupDate;
	}

	public String getSignupTime(){
		return signupTime;
	}
	public void setSignupTime(String signupTime){
		this.signupTime = signupTime;
	}

	public String getSignupMark(){
		return signupMark;
	}
	public void setSignupMark(String signupMark){
		this.signupMark = signupMark;
	}

	public String getSignupInfo(){
		return signupInfo;
	}
	public void setSignupInfo(String signupInfo){
		this.signupInfo = signupInfo;
	}

	public String getResultMsg(){
		return resultMsg;
	}
	public void setResultMsg(String resultMsg){
		this.resultMsg = resultMsg;
	}

	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline;
	}

	public String getInAcctNo(){
		return inAcctNo;
	}
	public void setInAcctNo(String inAcctNo){
		this.inAcctNo = inAcctNo;
	}

	public String getInBankNo(){
		return inBankNo;
	}
	public void setInBankNo(String inBankNo){
		this.inBankNo = inBankNo;
	}

	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getOperType(){
		return operType;
	}
	public void setOperType(String operType){
		this.operType = operType;
	}

	public String getRemitter(){
		return remitter;
	}
	public void setRemitter(String remitter){
		this.remitter = remitter;
	}

	public String getApplyDate(){
		return applyDate;
	}
	public void setApplyDate(String applyDate){
		this.applyDate = applyDate;
	}

	public String getApplyTime(){
		return applyTime;
	}
	public void setApplyTime(String applyTime){
		this.applyTime = applyTime;
	}

	public String getBusiType(){
		return busiType;
	}
	public void setBusiType(String busiType){
		this.busiType = busiType;
	}

	public String getReqSid(){
		return reqSid;
	}
	public void setReqSid(String reqSid){
		this.reqSid = reqSid;
	}

	public String getTransDate(){
		return transDate;
	}
	public void setTransDate(String transDate){
		this.transDate = transDate;
	}

	public String getTransTime(){
		return transTime;
	}
	public void setTransTime(String transTime){
		this.transTime = transTime;
	}

	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	public Double getBillMoney(){
		return billMoney;
	}
	public void setBillMoney(Double billMoney){
		this.billMoney = billMoney;
	}

	public String getIssueDt(){
		return issueDt;
	}
	public void setIssueDt(String issueDt){
		this.issueDt = issueDt;
	}

	public String getDueDt(){
		return dueDt;
	}
	public void setDueDt(String dueDt){
		this.dueDt = dueDt;
	}

	public String getAcceptor(){
		return acceptor;
	}
	public void setAcceptor(String acceptor){
		this.acceptor = acceptor;
	}

	public String getAcceptorAcct(){
		return acceptorAcct;
	}
	public void setAcceptorAcct(String acceptorAcct){
		this.acceptorAcct = acceptorAcct;
	}

	public String getAcceptorBankName(){
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName){
		this.acceptorBankName = acceptorBankName;
	}

	public String getBanEndorsementMark(){
		return banEndorsementMark;
	}
	public void setBanEndorsementMark(String banEndorsementMark){
		this.banEndorsementMark = banEndorsementMark;
	}

	public String getPrintStaus(){
		return printStaus;
	}
	public void setPrintStaus(String printStaus){
		this.printStaus = printStaus;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public String getTransfor(){
		return transfor;
	}
	public void setTransfor(String transfor){
		this.transfor = transfor;
	}

	public String getCurStatusString(){
		return curStatusString;
	}
	public void setCurStatusString(String curStatusString){
		this.curStatusString = curStatusString;
	}


}
