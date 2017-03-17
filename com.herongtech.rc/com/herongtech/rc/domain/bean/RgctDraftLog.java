/********************************************
* 文件名称: RgctDraftLog.java
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

public class RgctDraftLog{
	//日志编号
	private String logId = " ";
	public String getLogId(){
		return logId;
	}
	public void setLogId(String logId){
		this.logId = logId;
	}

	//登记中心id
	private String rgctId = " ";
	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	//登记中心流水号
	private String rgctHistId = " ";
	public String getRgctHistId(){
		return rgctHistId;
	}
	public void setRgctHistId(String rgctHistId){
		this.rgctHistId = rgctHistId;
	}

	//请求报文类型编号
	private String draftNoReq = " ";
	public String getDraftNoReq(){
		return draftNoReq;
	}
	public void setDraftNoReq(String draftNoReq){
		this.draftNoReq = draftNoReq;
	}

	//请求报文内容#
	private String reqDraft = " ";
	public String getReqDraft(){
		return reqDraft;
	}
	public void setReqDraft(String reqDraft){
		this.reqDraft = reqDraft;
	}

	//请求日期
	private String reqDt = " ";
	public String getReqDt(){
		return reqDt;
	}
	public void setReqDt(String reqDt){
		this.reqDt = reqDt;
	}

	//请求时间
	private String reqTime = " ";
	public String getReqTime(){
		return reqTime;
	}
	public void setReqTime(String reqTime){
		this.reqTime = reqTime;
	}

	//请求报文id
	private String reqSid = " ";
	public String getReqSid(){
		return reqSid;
	}
	public void setReqSid(String reqSid){
		this.reqSid = reqSid;
	}

	//请求行行号
	private String reqBankNo = " ";
	public String getReqBankNo(){
		return reqBankNo;
	}
	public void setReqBankNo(String reqBankNo){
		this.reqBankNo = reqBankNo;
	}

	//应答报文内容#
	private String respDraft = " ";
	public String getRespDraft(){
		return respDraft;
	}
	public void setRespDraft(String respDraft){
		this.respDraft = respDraft;
	}

	//应答日期
	private String respDt = " ";
	public String getRespDt(){
		return respDt;
	}
	public void setRespDt(String respDt){
		this.respDt = respDt;
	}

	//应答时间
	private String respTime = " ";
	public String getRespTime(){
		return respTime;
	}
	public void setRespTime(String respTime){
		this.respTime = respTime;
	}

	//应答报文id
	private String respSid = " ";
	public String getRespSid(){
		return respSid;
	}
	public void setRespSid(String respSid){
		this.respSid = respSid;
	}

	//应答行行号
	private String respBankNo = " ";
	public String getRespBankNo(){
		return respBankNo;
	}
	public void setRespBankNo(String respBankNo){
		this.respBankNo = respBankNo;
	}

	//应答报文类型编号
	private String draftNoResp = " ";
	public String getDraftNoResp(){
		return draftNoResp;
	}
	public void setDraftNoResp(String draftNoResp){
		this.draftNoResp = draftNoResp;
	}

	//原申请报文id
	private String reqMsgId = " ";
	public String getReqMsgId(){
		return reqMsgId;
	}
	public void setReqMsgId(String reqMsgId){
		this.reqMsgId = reqMsgId;
	}

	//原申请报文类型编号
	private String fromDraftNo = " ";
	public String getFromDraftNo(){
		return fromDraftNo;
	}
	public void setFromDraftNo(String fromDraftNo){
		this.fromDraftNo = fromDraftNo;
	}

	//发送接收标记
	private String inOut = " ";
	public String getInOut(){
		return inOut;
	}
	public void setInOut(String inOut){
		this.inOut = inOut;
	}

	//票号
	private String billNo = " ";
	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	//回复标记
	private String replyFlag = " ";
	public String getReplyFlag(){
		return replyFlag;
	}
	public void setReplyFlag(String replyFlag){
		this.replyFlag = replyFlag;
	}

	//是否核对
	private String isCheckDraft = " ";
	public String getIsCheckDraft(){
		return isCheckDraft;
	}
	public void setIsCheckDraft(String isCheckDraft){
		this.isCheckDraft = isCheckDraft;
	}

	//报文接收行行号
	private String acceptBankNo = " ";
	public String getAcceptBankNo(){
		return acceptBankNo;
	}
	public void setAcceptBankNo(String acceptBankNo){
		this.acceptBankNo = acceptBankNo;
	}

	//处理标记
	private String processFlag = " ";
	public String getProcessFlag(){
		return processFlag;
	}
	public void setProcessFlag(String processFlag){
		this.processFlag = processFlag;
	}

	//发送标记
	private String sendFlag = " ";
	public String getSendFlag(){
		return sendFlag;
	}
	public void setSendFlag(String sendFlag){
		this.sendFlag = sendFlag;
	}

	//报文内容
	private String draftInfo = " ";
	public String getDraftInfo(){
		return draftInfo;
	}
	public void setDraftInfo(String draftInfo){
		this.draftInfo = draftInfo;
	}

	//实体id
	private String entityId = " ";
	public String getEntityId(){
		return entityId;
	}
	public void setEntityId(String entityId){
		this.entityId = entityId;
	}

	//实体报文类型编号
	private String entityNo = " ";
	public String getEntityNo(){
		return entityNo;
	}
	public void setEntityNo(String entityNo){
		this.entityNo = entityNo;
	}

	//实体报文类型
	private String entityType = " ";
	public String getEntityType(){
		return entityType;
	}
	public void setEntityType(String entityType){
		this.entityType = entityType;
	}


}
