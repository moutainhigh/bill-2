/********************************************
* 文件名称: ReAsRole.java
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
package com.herongtech.console.domain.common.audit.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

import com.herongtech.console.domain.bean.Role;

public class ReAsRoleDTO{
	private String id = " ";	//id
	private String asId = " ";	//审批岗位ID
	private String bindRoleId = " ";	//绑定角色ID(角色编号)
	private String updateDt = " ";	//更新日期
	private String updateTm = " ";	//更新时间
	
	private String roleName = " ";//角色名称
	private String creator = " ";//创建者
	private String roleStatus = " ";//角色状态
	private String remark = " ";//备注
	
	
	public ReAsRoleDTO() {
		super();
	}

	public ReAsRoleDTO(ReAsRole reAsRole, Role role) {
		this.id = reAsRole.getId();
		this.asId = reAsRole.getAsId();
		this.bindRoleId = reAsRole.getBindRoleId();
		this.updateDt = reAsRole.getUpdateDt();
		this.updateTm = reAsRole.getUpdateTm();
		this.roleName = role.getRoleName();
		this.creator = role.getCreator();
		this.roleStatus = role.getRoleStatus();
		this.remark = role.getRemark();
	}
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getAsId(){
		return asId;
	}
	public void setAsId(String asId){
		this.asId = asId;
	}

	public String getBindRoleId(){
		return bindRoleId;
	}
	public void setBindRoleId(String bindRoleId){
		this.bindRoleId = bindRoleId;
	}

	public String getUpdateDt(){
		return updateDt;
	}
	public void setUpdateDt(String updateDt){
		this.updateDt = updateDt;
	}

	public String getUpdateTm(){
		return updateTm;
	}
	public void setUpdateTm(String updateTm){
		this.updateTm = updateTm;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
