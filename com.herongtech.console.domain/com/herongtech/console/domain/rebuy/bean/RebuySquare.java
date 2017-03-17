/********************************************
* 文件名称: RebuySquare.java
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
package com.herongtech.console.domain.rebuy.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class RebuySquare{
	private String rebuySquareId = " ";	//记账流水号
	private String rebuymxId = " ";	//转入明细流水号
	private String custNo = " ";	//客户号
	private String rebuyId = " ";	//转入流水号
	private String rgctId = " ";	//登记中心id
	private String remark = " ";	//备注
	private String operDate = " ";	//操作日期
	private String operTime = " ";	//操作时间
	private String createDate = " ";	//创建日期
	private String createTime = " ";	//创建时间
	private String exSerial = " ";	//前端流水号
	private String afterFlowNo = " ";	//发送主机流水号
	private String status = " ";	//状态
	private String branchNo = " ";	//机构
	private String operNo = " ";	//操作柜员
	private String orderId = " ";	//订单编号
	public String getRebuySquareId(){
		return rebuySquareId;
	}
	public void setRebuySquareId(String rebuySquareId){
		this.rebuySquareId = rebuySquareId;
	}

	public String getRebuymxId(){
		return rebuymxId;
	}
	public void setRebuymxId(String rebuymxId){
		this.rebuymxId = rebuymxId;
	}

	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	public String getRebuyId(){
		return rebuyId;
	}
	public void setRebuyId(String rebuyId){
		this.rebuyId = rebuyId;
	}

	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getOperDate(){
		return operDate;
	}
	public void setOperDate(String operDate){
		this.operDate = operDate;
	}

	public String getOperTime(){
		return operTime;
	}
	public void setOperTime(String operTime){
		this.operTime = operTime;
	}

	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getExSerial(){
		return exSerial;
	}
	public void setExSerial(String exSerial){
		this.exSerial = exSerial;
	}

	public String getAfterFlowNo(){
		return afterFlowNo;
	}
	public void setAfterFlowNo(String afterFlowNo){
		this.afterFlowNo = afterFlowNo;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	public String getOperNo(){
		return operNo;
	}
	public void setOperNo(String operNo){
		this.operNo = operNo;
	}

	public String getOrderId(){
		return orderId;
	}
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}


}
