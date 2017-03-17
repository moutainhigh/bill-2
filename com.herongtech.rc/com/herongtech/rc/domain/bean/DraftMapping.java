/********************************************
* 文件名称: DraftMapping.java
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

public class DraftMapping{
	//方法ID
	private String methodId = " ";
	public String getMethodId(){
		return methodId;
	}
	public void setMethodId(String methodId){
		this.methodId = methodId;
	}

	//报文编号
	private String draftNo = " ";
	public String getDraftNo(){
		return draftNo;
	}
	public void setDraftNo(String draftNo){
		this.draftNo = draftNo;
	}

	//报文构造器
	private String draftBuilder = " ";
	public String getDraftBuilder(){
		return draftBuilder;
	}
	public void setDraftBuilder(String draftBuilder){
		this.draftBuilder = draftBuilder;
	}

	//是否加签
	private String isAddSign = " ";
	public String getIsAddSign(){
		return isAddSign;
	}
	public void setIsAddSign(String isAddSign){
		this.isAddSign = isAddSign;
	}

	//是否实时
	private String isRealTime = " ";
	public String getIsRealTime(){
		return isRealTime;
	}
	public void setIsRealTime(String isRealTime){
		this.isRealTime = isRealTime;
	}

	//允许时序
	private String allowSts = " ";
	public String getAllowSts(){
		return allowSts;
	}
	public void setAllowSts(String allowSts){
		this.allowSts = allowSts;
	}

	//参数
	private String param = " ";
	public String getParam(){
		return param;
	}
	public void setParam(String param){
		this.param = param;
	}


}
