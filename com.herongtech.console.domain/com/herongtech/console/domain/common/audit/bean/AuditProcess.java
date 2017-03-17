/********************************************
* 文件名称: AuditProcess.java
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


public class AuditProcess{
	private String id = " ";	//id
	private String atId = " ";	//审批任务ID
	private String apCommitStationId = " ";	//审批提交岗位ID
	private String apCommitStationName = " ";	//审批提交岗位名称
	private String apCommitPersonId = " ";	//审批提交人ID
	private String apCommitPersonName = " ";	//审批提交人名称
	private String apCommitBrchNo = " ";	//审批提交人所在机构号
	private String apCommitRemark = " ";	//审批提交人意见
	private String apExecStationId = " ";	//审批岗位ID
	private String apExecStationName = " ";	//审批岗位名称
	private String apExecPersonId = " ";	//审批人ID
	private String apExecPersonName = " ";	//审批人名称
	private String apExecBrchNo = " ";	//审批人所在机构号
	private String apStatus = " ";	//审批状态
	private String apExecResult = " ";	//审批结果
	private String apExecRemark = " ";	//审批意见
	private String apDoneDt = " ";	//审批日期
	private String apDoneTime = " ";	//审批时间
	private String apCommitDt = " ";	//上一个审批提交日期
	private String apCommitTime = " ";	//上一个审批提交时间
	private String sortNo = " ";	//审批过程序号
	private String updateDt = " ";	//更新日期
	private String updateTm = " ";	//更新时间
	
	
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getAtId(){
		return atId;
	}
	public void setAtId(String atId){
		this.atId = atId;
	}

	public String getApCommitStationId(){
		return apCommitStationId;
	}
	public void setApCommitStationId(String apCommitStationId){
		this.apCommitStationId = apCommitStationId;
	}

	public String getApCommitStationName(){
		return apCommitStationName;
	}
	public void setApCommitStationName(String apCommitStationName){
		this.apCommitStationName = apCommitStationName;
	}

	public String getApCommitPersonId(){
		return apCommitPersonId;
	}
	public void setApCommitPersonId(String apCommitPersonId){
		this.apCommitPersonId = apCommitPersonId;
	}

	public String getApCommitPersonName(){
		return apCommitPersonName;
	}
	public void setApCommitPersonName(String apCommitPersonName){
		this.apCommitPersonName = apCommitPersonName;
	}

	public String getApCommitBrchNo(){
		return apCommitBrchNo;
	}
	public void setApCommitBrchNo(String apCommitBrchNo){
		this.apCommitBrchNo = apCommitBrchNo;
	}

	public String getApCommitRemark(){
		return apCommitRemark;
	}
	public void setApCommitRemark(String apCommitRemark){
		this.apCommitRemark = apCommitRemark;
	}

	public String getApExecStationId(){
		return apExecStationId;
	}
	public void setApExecStationId(String apExecStationId){
		this.apExecStationId = apExecStationId;
	}

	public String getApExecStationName(){
		return apExecStationName;
	}
	public void setApExecStationName(String apExecStationName){
		this.apExecStationName = apExecStationName;
	}

	public String getApExecPersonId(){
		return apExecPersonId;
	}
	public void setApExecPersonId(String apExecPersonId){
		this.apExecPersonId = apExecPersonId;
	}

	public String getApExecPersonName(){
		return apExecPersonName;
	}
	public void setApExecPersonName(String apExecPersonName){
		this.apExecPersonName = apExecPersonName;
	}

	public String getApExecBrchNo(){
		return apExecBrchNo;
	}
	public void setApExecBrchNo(String apExecBrchNo){
		this.apExecBrchNo = apExecBrchNo;
	}

	public String getApStatus(){
		return apStatus;
	}
	public void setApStatus(String apStatus){
		this.apStatus = apStatus;
	}

	public String getApExecResult(){
		return apExecResult;
	}
	public void setApExecResult(String apExecResult){
		this.apExecResult = apExecResult;
	}

	public String getApExecRemark(){
		return apExecRemark;
	}
	public void setApExecRemark(String apExecRemark){
		this.apExecRemark = apExecRemark;
	}

	public String getApDoneDt(){
		return apDoneDt;
	}
	public void setApDoneDt(String apDoneDt){
		this.apDoneDt = apDoneDt;
	}

	public String getApDoneTime(){
		return apDoneTime;
	}
	public void setApDoneTime(String apDoneTime){
		this.apDoneTime = apDoneTime;
	}

	public String getApCommitDt(){
		return apCommitDt;
	}
	public void setApCommitDt(String apCommitDt){
		this.apCommitDt = apCommitDt;
	}

	public String getApCommitTime(){
		return apCommitTime;
	}
	public void setApCommitTime(String apCommitTime){
		this.apCommitTime = apCommitTime;
	}

	public String getSortNo(){
		return sortNo;
	}
	public void setSortNo(String sortNo){
		this.sortNo = sortNo;
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


}
