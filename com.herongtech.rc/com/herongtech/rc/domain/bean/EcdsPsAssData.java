/********************************************
* 文件名称: EcdsPsAssData.java
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

public class EcdsPsAssData{
	//被承接行行号
	private String byContinueBankNo = " ";
	public String getByContinueBankNo(){
		return byContinueBankNo;
	}
	public void setByContinueBankNo(String byContinueBankNo){
		this.byContinueBankNo = byContinueBankNo;
	}

	//被承接行组织机构代码
	private String byContinueOrgCode = " ";
	public String getByContinueOrgCode(){
		return byContinueOrgCode;
	}
	public void setByContinueOrgCode(String byContinueOrgCode){
		this.byContinueOrgCode = byContinueOrgCode;
	}

	//被承接行角色代码
	private String byContinueRole = " ";
	public String getByContinueRole(){
		return byContinueRole;
	}
	public void setByContinueRole(String byContinueRole){
		this.byContinueRole = byContinueRole;
	}

	//报文标识号
	private String msgId = " ";
	public String getMsgId(){
		return msgId;
	}
	public void setMsgId(String msgId){
		this.msgId = msgId;
	}

	//承接行行号
	private String continueBankNo = " ";
	public String getContinueBankNo(){
		return continueBankNo;
	}
	public void setContinueBankNo(String continueBankNo){
		this.continueBankNo = continueBankNo;
	}

	//承接行组织机构代码
	private String continueOrgCode = " ";
	public String getContinueOrgCode(){
		return continueOrgCode;
	}
	public void setContinueOrgCode(String continueOrgCode){
		this.continueOrgCode = continueOrgCode;
	}

	//承接行角色代码
	private String continueRole = " ";
	public String getContinueRole(){
		return continueRole;
	}
	public void setContinueRole(String continueRole){
		this.continueRole = continueRole;
	}

	//生效日期
	private String inureDate = " ";
	public String getInureDate(){
		return inureDate;
	}
	public void setInureDate(String inureDate){
		this.inureDate = inureDate;
	}

	//被承接行发起标志
	private String byContinueLaunch = " ";
	public String getByContinueLaunch(){
		return byContinueLaunch;
	}
	public void setByContinueLaunch(String byContinueLaunch){
		this.byContinueLaunch = byContinueLaunch;
	}

	//承接行发起标志
	private String continueLaunch = " ";
	public String getContinueLaunch(){
		return continueLaunch;
	}
	public void setContinueLaunch(String continueLaunch){
		this.continueLaunch = continueLaunch;
	}

	//发起时间
	private String launchTime = " ";
	public String getLaunchTime(){
		return launchTime;
	}
	public void setLaunchTime(String launchTime){
		this.launchTime = launchTime;
	}

	//更新状态
	private String updateState = " ";
	public String getUpdateState(){
		return updateState;
	}
	public void setUpdateState(String updateState){
		this.updateState = updateState;
	}


}
