/********************************************
* 文件名称: tRgctMethod.java
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

public class RgctMethod{
	//ID
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//SUB_SYSTEM
	private String subSystem = " ";
	public String getSubSystem(){
		return subSystem;
	}
	public void setSubSystem(String subSystem){
		this.subSystem = subSystem;
	}

	//INTERFACE_NAME
	private String interfaceName = " ";
	public String getInterfaceName(){
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName){
		this.interfaceName = interfaceName;
	}

	//METHOD_NAME
	private String methodName = " ";
	public String getMethodName(){
		return methodName;
	}
	public void setMethodName(String methodName){
		this.methodName = methodName;
	}

	//METHOD_CN_NAME
	private String methodCnName = " ";
	public String getMethodCnName(){
		return methodCnName;
	}
	public void setMethodCnName(String methodCnName){
		this.methodCnName = methodCnName;
	}

	//IS_CHECK_STATUS
	private String isCheckStatus = " ";
	public String getIsCheckStatus(){
		return isCheckStatus;
	}
	public void setIsCheckStatus(String isCheckStatus){
		this.isCheckStatus = isCheckStatus;
	}

	//IS_CHECK_DEL
	private String isCheckDel = " ";
	public String getIsCheckDel(){
		return isCheckDel;
	}
	public void setIsCheckDel(String isCheckDel){
		this.isCheckDel = isCheckDel;
	}

	//IS_ADD_ENDORSE
	private String isAddEndorse = " ";
	public String getIsAddEndorse(){
		return isAddEndorse;
	}
	public void setIsAddEndorse(String isAddEndorse){
		this.isAddEndorse = isAddEndorse;
	}

	//IS_BACK_HISTORY
	private String isBackHistory = " ";
	public String getIsBackHistory(){
		return isBackHistory;
	}
	public void setIsBackHistory(String isBackHistory){
		this.isBackHistory = isBackHistory;
	}

	//IS_ADD_OBLIGEE
	private String isAddObligee = " ";
	public String getIsAddObligee(){
		return isAddObligee;
	}
	public void setIsAddObligee(String isAddObligee){
		this.isAddObligee = isAddObligee;
	}

	
	private String param="";//传值用（可表示 是否再贴现/是否逾期等。。。）
    
    public String getParam() {
        return param;
    }
    
    public void setParam(String param) {
        this.param = param;
    }
	
	

}
