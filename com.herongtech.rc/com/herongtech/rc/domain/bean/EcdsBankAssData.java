/********************************************
* 文件名称: EcdsBankAssData.java
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

public class EcdsBankAssData{
	//主键
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//行号
	private String rowNumber = " ";
	public String getRowNumber(){
		return rowNumber;
	}
	public void setRowNumber(String rowNumber){
		this.rowNumber = rowNumber;
	}

	//参与者类型
	private String actorType = " ";
	public String getActorType(){
		return actorType;
	}
	public void setActorType(String actorType){
		this.actorType = actorType;
	}

	//行别代码
	private String rowOtherCode = " ";
	public String getRowOtherCode(){
		return rowOtherCode;
	}
	public void setRowOtherCode(String rowOtherCode){
		this.rowOtherCode = rowOtherCode;
	}

	//上级参与者
	private String superActor = " ";
	public String getSuperActor(){
		return superActor;
	}
	public void setSuperActor(String superActor){
		this.superActor = superActor;
	}

	//所在节点代码
	private String localNodeCode = " ";
	public String getLocalNodeCode(){
		return localNodeCode;
	}
	public void setLocalNodeCode(String localNodeCode){
		this.localNodeCode = localNodeCode;
	}

	//本行上级参与者
	private String rootSuperActor = " ";
	public String getRootSuperActor(){
		return rootSuperActor;
	}
	public void setRootSuperActor(String rootSuperActor){
		this.rootSuperActor = rootSuperActor;
	}

	//所属人行代码
	private String catePeopleCode = " ";
	public String getCatePeopleCode(){
		return catePeopleCode;
	}
	public void setCatePeopleCode(String catePeopleCode){
		this.catePeopleCode = catePeopleCode;
	}

	//地市代码
	private String cityCode = " ";
	public String getCityCode(){
		return cityCode;
	}
	public void setCityCode(String cityCode){
		this.cityCode = cityCode;
	}

	//参与者全称
	private String actorFullCall = " ";
	public String getActorFullCall(){
		return actorFullCall;
	}
	public void setActorFullCall(String actorFullCall){
		this.actorFullCall = actorFullCall;
	}

	//参与者简称
	private String actorForShort = " ";
	public String getActorForShort(){
		return actorForShort;
	}
	public void setActorForShort(String actorForShort){
		this.actorForShort = actorForShort;
	}

	//地址
	private String address = " ";
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}

	//邮编
	private String postEdit = " ";
	public String getPostEdit(){
		return postEdit;
	}
	public void setPostEdit(String postEdit){
		this.postEdit = postEdit;
	}

	//手机号码
	private String phone = " ";
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}

	//邮箱
	private String email = " ";
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}

	//生效日期
	private String inureDate = " ";
	public String getInureDate(){
		return inureDate;
	}
	public void setInureDate(String inureDate){
		this.inureDate = inureDate;
	}

	//更新日期
	private String updateDt = " ";
	public String getUpdateDt(){
		return updateDt;
	}
	public void setUpdateDt(String updateDt){
		this.updateDt = updateDt;
	}

	//更新时间
	private String updateTime = " ";
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	//记录更新期数
	private String logUpdateExpect = " ";
	public String getLogUpdateExpect(){
		return logUpdateExpect;
	}
	public void setLogUpdateExpect(String logUpdateExpect){
		this.logUpdateExpect = logUpdateExpect;
	}

	//state
	private String state = " ";
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}

	//操作状态
	private String operStatus = " ";
	public String getOperStatus(){
		return operStatus;
	}
	public void setOperStatus(String operStatus){
		this.operStatus = operStatus;
	}

	//备注
	private String remark = " ";
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}


}
