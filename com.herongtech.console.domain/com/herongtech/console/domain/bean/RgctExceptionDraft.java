/********************************************
* 文件名称: RgctExceptionDraft.java
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

public class RgctExceptionDraft{
	//日志编号
	private String logId = " ";
	public String getLogId(){
		return logId;
	}
	public void setLogId(String logId){
		this.logId = logId;
	}

	//报文id
	private String msgSid = " ";
	public String getMsgSid(){
		return msgSid;
	}
	public void setMsgSid(String msgSid){
		this.msgSid = msgSid;
	}

	//报文日期
	private String draftDt = " ";
	public String getDraftDt(){
		return draftDt;
	}
	public void setDraftDt(String draftDt){
		this.draftDt = draftDt;
	}

	//报文时间
	private String draftTime = " ";
	public String getDraftTime(){
		return draftTime;
	}
	public void setDraftTime(String draftTime){
		this.draftTime = draftTime;
	}

	//报文类型编号
	private String draftNo = " ";
	public String getDraftNo(){
		return draftNo;
	}
	public void setDraftNo(String draftNo){
		this.draftNo = draftNo;
	}

	//报文内容#
	private String draftTxt = " ";
	public String getDraftTxt(){
		return draftTxt;
	}
	public void setDraftTxt(String draftTxt){
		this.draftTxt = draftTxt;
	}

	//原报文id
	private String orgnlMsgSid = " ";
	public String getOrgnlMsgSid(){
		return orgnlMsgSid;
	}
	public void setOrgnlMsgSid(String orgnlMsgSid){
		this.orgnlMsgSid = orgnlMsgSid;
	}

	//原报文日期
	private String orgnlDraftDt = " ";
	public String getOrgnlDraftDt(){
		return orgnlDraftDt;
	}
	public void setOrgnlDraftDt(String orgnlDraftDt){
		this.orgnlDraftDt = orgnlDraftDt;
	}

	//原报文时间
	private String orgnlDraftTime = " ";
	public String getOrgnlDraftTime(){
		return orgnlDraftTime;
	}
	public void setOrgnlDraftTime(String orgnlDraftTime){
		this.orgnlDraftTime = orgnlDraftTime;
	}

	//原报文类型编号
	private String orgnlDraftNo = " ";
	public String getOrgnlDraftNo(){
		return orgnlDraftNo;
	}
	public void setOrgnlDraftNo(String orgnlDraftNo){
		this.orgnlDraftNo = orgnlDraftNo;
	}

	//原报文内容#
	private String orgnlDraftTxt = " ";
	public String getOrgnlDraftTxt(){
		return orgnlDraftTxt;
	}
	public void setOrgnlDraftTxt(String orgnlDraftTxt){
		this.orgnlDraftTxt = orgnlDraftTxt;
	}

	//票号
	private String billNo = " ";
	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	//接收发送标识
	private String inOut = " ";
	public String getInOut(){
		return inOut;
	}
	public void setInOut(String inOut){
		this.inOut = inOut;
	}

	//状态
	private String status = " ";
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	//err_count#
	private Long errCount = 0l;
	public Long getErrCount(){
		return errCount;
	}
	public void setErrCount(Long errCount){
		this.errCount = errCount;
	}


}
