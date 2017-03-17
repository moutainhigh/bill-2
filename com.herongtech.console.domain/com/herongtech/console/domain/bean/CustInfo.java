/********************************************
* 文件名称: CustInfo.java
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

public class CustInfo{
	//id号#
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//客户名称
	private String custName = " ";
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	//客户类型
	private String custType = " ";
	public String getCustType(){
		return custType;
	}
	public void setCustType(String custType){
		this.custType = custType;
	}

	//客户号
	private String custNo = " ";
	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	//贷款合同号
	private String loanNo = " ";
	public String getLoanNo(){
		return loanNo;
	}
	public void setLoanNo(String loanNo){
		this.loanNo = loanNo;
	}

	//组织机构代码
	private String orgCode = " ";
	public String getOrgCode(){
		return orgCode;
	}
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}

	//标记
	private String flag = " ";
	public String getFlag(){
		return flag;
	}
	public void setFlag(String flag){
		this.flag = flag;
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

	//参与者类别
	private String partnerType = " ";
	public String getPartnerType(){
		return partnerType;
	}
	public void setPartnerType(String partnerType){
		this.partnerType = partnerType;
	}

	//地址
	private String address = " ";
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}

	//评级机构
	private String creditAgency = " ";
	public String getCreditAgency(){
		return creditAgency;
	}
	public void setCreditAgency(String creditAgency){
		this.creditAgency = creditAgency;
	}

	//评级到期日
	private String creditDuedt = " ";
	public String getCreditDuedt(){
		return creditDuedt;
	}
	public void setCreditDuedt(String creditDuedt){
		this.creditDuedt = creditDuedt;
	}

	//信用等级
	private String creditRate = " ";
	public String getCreditRate(){
		return creditRate;
	}
	public void setCreditRate(String creditRate){
		this.creditRate = creditRate;
	}


}
