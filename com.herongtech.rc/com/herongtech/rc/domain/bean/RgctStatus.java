/********************************************
* 文件名称: RgctStatus.java
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

public class RgctStatus{
	//ID
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//METHOD_ID
	private Long methodId = 0l;
	public Long getMethodId(){
		return methodId;
	}
	public void setMethodId(Long methodId){
		this.methodId = methodId;
	}

	//BEFORE_STATUS
	private String beforeStatus = " ";
	public String getBeforeStatus(){
		return beforeStatus;
	}
	public void setBeforeStatus(String beforeStatus){
		this.beforeStatus = beforeStatus;
	}

	//AFTER_STATUS
	private String afterStatus = " ";
	public String getAfterStatus(){
		return afterStatus;
	}
	public void setAfterStatus(String afterStatus){
		this.afterStatus = afterStatus;
	}

	//RUN_STATUS
	private String runStatus = " ";
	public String getRunStatus(){
		return runStatus;
	}
	public void setRunStatus(String runStatus){
		this.runStatus = runStatus;
	}

	//PARAM
	private String param = " ";
	public String getParam(){
		return param;
	}
	public void setParam(String param){
		this.param = param;
	}


}
