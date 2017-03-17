/********************************************
* 文件名称: EcdsApData.java
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

public class EcdsApData{
	//接入点代码
	private String meetCode = " ";
	public String getMeetCode(){
		return meetCode;
	}
	public void setMeetCode(String meetCode){
		this.meetCode = meetCode;
	}

	//MEET_NAME
	private String meetName = " ";
	public String getMeetName(){
		return meetName;
	}
	public void setMeetName(String meetName){
		this.meetName = meetName;
	}

	//NONCE_CCPC
	private String nonceCcpc = " ";
	public String getNonceCcpc(){
		return nonceCcpc;
	}
	public void setNonceCcpc(String nonceCcpc){
		this.nonceCcpc = nonceCcpc;
	}

	//MEET_STATE
	private String meetState = " ";
	public String getMeetState(){
		return meetState;
	}
	public void setMeetState(String meetState){
		this.meetState = meetState;
	}

	//INURE_DATE
	private String inureDate = " ";
	public String getInureDate(){
		return inureDate;
	}
	public void setInureDate(String inureDate){
		this.inureDate = inureDate;
	}

	//LOGOUT_DATE
	private String logoutDate = " ";
	public String getLogoutDate(){
		return logoutDate;
	}
	public void setLogoutDate(String logoutDate){
		this.logoutDate = logoutDate;
	}

	//UPDATE_TIME
	private String updateTime = " ";
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	//LATELY_UPDATE_WORK
	private String latelyUpdateWork = " ";
	public String getLatelyUpdateWork(){
		return latelyUpdateWork;
	}
	public void setLatelyUpdateWork(String latelyUpdateWork){
		this.latelyUpdateWork = latelyUpdateWork;
	}

	//MEET_ENTER_STATE
	private String meetEnterState = " ";
	public String getMeetEnterState(){
		return meetEnterState;
	}
	public void setMeetEnterState(String meetEnterState){
		this.meetEnterState = meetEnterState;
	}

	//ENTER_IDENTIFY
	private String enterIdentify = " ";
	public String getEnterIdentify(){
		return enterIdentify;
	}
	public void setEnterIdentify(String enterIdentify){
		this.enterIdentify = enterIdentify;
	}

	//THIS_MONTH_SET_FIRST_TIME
	private String thisMonthSetFirstTime = " ";
	public String getThisMonthSetFirstTime(){
		return thisMonthSetFirstTime;
	}
	public void setThisMonthSetFirstTime(String thisMonthSetFirstTime){
		this.thisMonthSetFirstTime = thisMonthSetFirstTime;
	}

	//THIS_MONTH_SET_SECOND
	private String thisMonthSetSecond = " ";
	public String getThisMonthSetSecond(){
		return thisMonthSetSecond;
	}
	public void setThisMonthSetSecond(String thisMonthSetSecond){
		this.thisMonthSetSecond = thisMonthSetSecond;
	}

	//FIRST_LIMITED
	private String firstLimited = " ";
	public String getFirstLimited(){
		return firstLimited;
	}
	public void setFirstLimited(String firstLimited){
		this.firstLimited = firstLimited;
	}

	//SECOND_LIMITED
	private String secondLimited = " ";
	public String getSecondLimited(){
		return secondLimited;
	}
	public void setSecondLimited(String secondLimited){
		this.secondLimited = secondLimited;
	}


}
