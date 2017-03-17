/********************************************
* 文件名称: Logs.java
* 系统名称: 合融基础技术平台V3.0
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
import java.math.*;

public class Logs{
	//日志编号
	private int logSerial = 0;
	public int getLogSerial(){
		return logSerial;
	}
	public void setLogSerial(int logSerial){
		this.logSerial = logSerial;
	}

	//用户编号
	private String userId = " ";
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}

	//菜单代码
	private String menuCode = " ";
	public String getMenuCode(){
		return menuCode;
	}
	public void setMenuCode(String menuCode){
		this.menuCode = menuCode;
	}

	//按钮编码
	private String operationCode = " ";
	public String getOperationCode(){
		return operationCode;
	}
	public void setOperationCode(String operationCode){
		this.operationCode = operationCode;
	}

	//终端编号
	private String terminalNo = " ";
	public String getTerminalNo(){
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo){
		this.terminalNo = terminalNo;
	}

	//操作日期
	private String opDate = " ";
	public String getOpDate(){
		return opDate;
	}
	public void setOpDate(String opDate){
		this.opDate = opDate;
	}

	//操作时间
	private String opTime = " ";
	public String getOpTime(){
		return opTime;
	}
	public void setOpTime(String opTime){
		this.opTime = opTime;
	}

	//授权用户
	private String authUser = " ";
	public String getAuthUser(){
		return authUser;
	}
	public void setAuthUser(String authUser){
		this.authUser = authUser;
	}

	//发起IP
	private String ip = " ";
	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}

	//日志摘要
	private String summary = " ";
	public String getSummary(){
		return summary;
	}
	public void setSummary(String summary){
		this.summary = summary;
	}

	//日志文件
	private String logFile = " ";
	public String getLogFile(){
		return logFile;
	}
	public void setLogFile(String logFile){
		this.logFile = logFile;
	}

	//浏览器类型
	private String broswer = " ";
	public String getBroswer(){
		return broswer;
	}
	public void setBroswer(String broswer){
		this.broswer = broswer;
	}


}
