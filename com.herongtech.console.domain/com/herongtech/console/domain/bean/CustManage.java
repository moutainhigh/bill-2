/********************************************
* 文件名称: CustManage.java
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
package com.herongtech.console.domain.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class CustManage{
	//主键
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//客户经理编码
	private String custManagerNo = " ";
	public String getCustManagerNo(){
		return custManagerNo;
	}
	public void setCustManagerNo(String custManagerNo){
		this.custManagerNo = custManagerNo;
	}

	//客户经理名称
	private String custManagerName = " ";
	public String getCustManagerName(){
		return custManagerName;
	}
	public void setCustManagerName(String custManagerName){
		this.custManagerName = custManagerName;
	}

	//是否启用
	private String status = " ";
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	//客户经理类型
	private String manageType = " ";
	public String getManageType(){
		return manageType;
	}
	public void setManageType(String manageType){
		this.manageType = manageType;
	}

	//所属机构
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//删除状态
	private String delFlag = " ";
	public String getDelFlag(){
		return delFlag;
	}
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}

	//删除日期
	private String delDt = " ";
	public String getDelDt(){
		return delDt;
	}
	public void setDelDt(String delDt){
		this.delDt = delDt;
	}

	//删除时间
	private String delTime = " ";
	public String getDelTime(){
		return delTime;
	}
	public void setDelTime(String delTime){
		this.delTime = delTime;
	}

	//删除用户名
	private String delOperName = " ";
	public String getDelOperName(){
		return delOperName;
	}
	public void setDelOperName(String delOperName){
		this.delOperName = delOperName;
	}

	//删除用户号
	private String delOperNo = " ";
	public String getDelOperNo(){
		return delOperNo;
	}
	public void setDelOperNo(String delOperNo){
		this.delOperNo = delOperNo;
	}

	//创建日期
	private String createDt = " ";
	public String getCreateDt(){
		return createDt;
	}
	public void setCreateDt(String createDt){
		this.createDt = createDt;
	}

	//创建时间
	private String createTime = " ";
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	//创建柜员名
	private String createOperName = " ";
	public String getCreateOperName(){
		return createOperName;
	}
	public void setCreateOperName(String createOperName){
		this.createOperName = createOperName;
	}

	//创建柜员号
	private String createOperNo = " ";
	public String getCreateOperNo(){
		return createOperNo;
	}
	public void setCreateOperNo(String createOperNo){
		this.createOperNo = createOperNo;
	}

	//部门名称
	private String deptName = " ";
	public String getDeptName(){
		return deptName;
	}
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	//部门号
	private Long deptNo = 0l;
	public Long getDeptNo(){
		return deptNo;
	}
	public void setDeptNo(Long deptNo){
		this.deptNo = deptNo;
	}


}
