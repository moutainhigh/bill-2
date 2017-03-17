/********************************************
* 文件名称: SaleSquare.java
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
package com.herongtech.console.domain.sale.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class SaleSquare{
	//入账流水号
	private String saleSquareId = " ";
	public String getSaleSquareId(){
		return saleSquareId;
	}
	public void setSaleSquareId(String saleSquareId){
		this.saleSquareId = saleSquareId;
	}

	//转卖明细流水号
	private String salemxId = " ";
	public String getSalemxId(){
		return salemxId;
	}
	public void setSalemxId(String salemxId){
		this.salemxId = salemxId;
	}

	//转卖流水号
	private String saleId = " ";
	public String getSaleId(){
		return saleId;
	}
	public void setSaleId(String saleId){
		this.saleId = saleId;
	}

	//登记中心id
	private String rgctId = " ";
	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	//备注
	private String remark = " ";
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	//操作日期
	private String operDate = " ";
	public String getOperDate(){
		return operDate;
	}
	public void setOperDate(String operDate){
		this.operDate = operDate;
	}

	//操作时间
	private String operTime = " ";
	public String getOperTime(){
		return operTime;
	}
	public void setOperTime(String operTime){
		this.operTime = operTime;
	}

	//生成日期
	private String createDate = " ";
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	//生成时间
	private String createTime = " ";
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	//发起方流水号
	private String exSerial = " ";
	public String getExSerial(){
		return exSerial;
	}
	public void setExSerial(String exSerial){
		this.exSerial = exSerial;
	}

	//主机记账流水号
	private String afterFlowNo = " ";
	public String getAfterFlowNo(){
		return afterFlowNo;
	}
	public void setAfterFlowNo(String afterFlowNo){
		this.afterFlowNo = afterFlowNo;
	}

	//状态
	private String status = " ";
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	//机构
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//操作柜员
	private String operNo = " ";
	public String getOperNo(){
		return operNo;
	}
	public void setOperNo(String operNo){
		this.operNo = operNo;
	}

	//订单编号
	private String orderId = " ";
	public String getOrderId(){
		return orderId;
	}
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}


}
