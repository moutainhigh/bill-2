/********************************************
* 文件名称: AuditRoute.java
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

import com.herongtech.console.domain.bean.Branch;

public class AuditRoute{
	private String id = " ";	//主键
	private String arName = " ";	//审批路线名称
	private String anExecMode = " ";	//审批岗位执行模式
	private String arRemark = " ";	//备注
	private String bindBranchNo = " ";	//指定使用机构号
	private String prodNo = " ";	//产品号
	private String pubFlag = " ";	//是否通用
	private String effectFlag = " ";	//是否生效标志
	private String auditStartBrchLvl = " ";	//审批起始机构级别
	private String createBrchNo = " ";	//审批路线创建人所在机构
	private String createDt = " ";	//创建日期
	private String createTime = " ";	//创建时间
	private String updateDt = " ";	//更新日期
	private String updateTm = " ";	//更新时间
	private Branch branch;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getArName(){
		return arName;
	}
	public void setArName(String arName){
		this.arName = arName;
	}

	public String getAnExecMode(){
		return anExecMode;
	}
	public void setAnExecMode(String anExecMode){
		this.anExecMode = anExecMode;
	}

	public String getArRemark(){
		return arRemark;
	}
	public void setArRemark(String arRemark){
		this.arRemark = arRemark;
	}

	public String getBindBranchNo(){
		return bindBranchNo;
	}
	public void setBindBranchNo(String bindBranchNo){
		this.bindBranchNo = bindBranchNo;
	}

	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	public String getPubFlag(){
		return pubFlag;
	}
	public void setPubFlag(String pubFlag){
		this.pubFlag = pubFlag;
	}

	public String getEffectFlag(){
		return effectFlag;
	}
	public void setEffectFlag(String effectFlag){
		this.effectFlag = effectFlag;
	}

	public String getAuditStartBrchLvl(){
		return auditStartBrchLvl;
	}
	public void setAuditStartBrchLvl(String auditStartBrchLvl){
		this.auditStartBrchLvl = auditStartBrchLvl;
	}

	public String getCreateBrchNo(){
		return createBrchNo;
	}
	public void setCreateBrchNo(String createBrchNo){
		this.createBrchNo = createBrchNo;
	}

	public String getCreateDt(){
		return createDt;
	}
	public void setCreateDt(String createDt){
		this.createDt = createDt;
	}

	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
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
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	

}
