/********************************************
* 文件名称: MachineStatus.java
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
package com.herongtech.console.domain.common.bean;


public class MachineStatus{
	//id
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//controller_name
	private String controllerName = " ";
	public String getControllerName(){
		return controllerName;
	}
	public void setControllerName(String controllerName){
		this.controllerName = controllerName;
	}

	//方法名
	private String methodName = " ";
    public String getMethodName() {
        return methodName;
    }
    
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    //前置状态
	private String beforeStatus = " ";
	public String getBeforeStatus(){
		return beforeStatus;
	}
	public void setBeforeStatus(String beforeStatus){
		this.beforeStatus = beforeStatus;
	}

	//后置状态
	private String afterStatus = " ";
	public String getAfterStatus(){
		return afterStatus;
	}
	public void setAfterStatus(String afterStatus){
		this.afterStatus = afterStatus;
	}

	//参数值
	private String paramValue = " ";
	public String getParamValue(){
		return paramValue;
	}
	public void setParamValue(String paramValue){
		this.paramValue = paramValue;
	}

	//描述
	private String description = " ";
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}


}
