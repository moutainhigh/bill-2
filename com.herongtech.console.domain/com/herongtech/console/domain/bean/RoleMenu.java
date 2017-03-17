/********************************************
* 文件名称: RoleMenu.java
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


public class RoleMenu{
	//id
	private String id = " ";
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	//角色代码
	private String roleCode = " ";
	public String getRoleCode(){
		return roleCode;
	}
	public void setRoleCode(String roleCode){
		this.roleCode = roleCode;
	}

	//菜单编号
	private String menuCode = " ";
	public String getMenuCode(){
		return menuCode;
	}
	public void setMenuCode(String menuCode){
		this.menuCode = menuCode;
	}
	
	//功能列表
	private String operationCode = " ";
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
}
