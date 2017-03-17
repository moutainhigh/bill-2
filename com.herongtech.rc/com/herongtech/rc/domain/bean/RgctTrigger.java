/********************************************
* 文件名称: RgctTrigger.java
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

public class RgctTrigger{
	//id
	private String id = " ";
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	//报文类型
	private String draftType = " ";
	public String getDraftType(){
		return draftType;
	}
	public void setDraftType(String draftType){
		this.draftType = draftType;
	}

	//报文类型中文名称
	private String draftTypeCn = " ";
	public String getDraftTypeCn(){
		return draftTypeCn;
	}
	public void setDraftTypeCn(String draftTypeCn){
		this.draftTypeCn = draftTypeCn;
	}

	//方法名称
	private String methodName = " ";
	public String getMethodName(){
		return methodName;
	}
	public void setMethodName(String methodName){
		this.methodName = methodName;
	}

	//方法中文名称
	private String methodCnName = " ";
	public String getMethodCnName(){
		return methodCnName;
	}
	public void setMethodCnName(String methodCnName){
		this.methodCnName = methodCnName;
	}

	//回调spring方法
	private String springId = " ";
	public String getSpringId(){
		return springId;
	}
	public void setSpringId(String springId){
		this.springId = springId;
	}

	//备注
	private String springDesc = " ";
	public String getSpringDesc(){
		return springDesc;
	}
	public void setSpringDesc(String springDesc){
		this.springDesc = springDesc;
	}


}
