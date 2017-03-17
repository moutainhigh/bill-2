/********************************************
* 文件名称: WorkingAds.java
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

public class WorkingAds{
	//WORKING_ADS_NO
	private String workingAdsNo = " ";
	public String getWorkingAdsNo(){
		return workingAdsNo;
	}
	public void setWorkingAdsNo(String workingAdsNo){
		this.workingAdsNo = workingAdsNo;
	}

	//WORKING_ADS_NAME
	private String workingAdsName = " ";
	public String getWorkingAdsName(){
		return workingAdsName;
	}
	public void setWorkingAdsName(String workingAdsName){
		this.workingAdsName = workingAdsName;
	}

	//STATUS
	private String status = " ";
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	//DEL_FLAG
	private String delFlag = " ";
	public String getDelFlag(){
		return delFlag;
	}
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}


}
