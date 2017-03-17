/********************************************
* 文件名称: Holiday.java
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

public class Holiday{
	//ID
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//DT
	private String dt = " ";
	public String getDt(){
		return dt;
	}
	public void setDt(String dt){
		this.dt = dt;
	}

	//WEEK
	private String week = " ";
	public String getWeek(){
		return week;
	}
	public void setWeek(String week){
		this.week = week;
	}

	//HOLIDAY_NAME
	private String holidayName = " ";
	public String getHolidayName(){
		return holidayName;
	}
	public void setHolidayName(String holidayName){
		this.holidayName = holidayName;
	}

	//IS_HOLIDAY
	private String isHoliday = " ";
	public String getIsHoliday(){
		return isHoliday;
	}
	public void setIsHoliday(String isHoliday){
		this.isHoliday = isHoliday;
	}


}
