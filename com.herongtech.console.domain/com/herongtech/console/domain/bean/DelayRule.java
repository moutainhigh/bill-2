/********************************************
* 文件名称: DelayRule.java
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

public class DelayRule{
	//主键
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//产品编号
	private String prodNo = " ";
	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	//票据分类
	private String billClass = " ";
	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
	}

	//票据种类
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//顺延规则
	private String delayType = " ";
	public String getDelayType(){
		return delayType;
	}
	public void setDelayType(String delayType){
		this.delayType = delayType;
	}

	//顺延天数
	private Long delayDays = 0l;
	public Long getDelayDays(){
		return delayDays;
	}
	public void setDelayDays(Long delayDays){
		this.delayDays = delayDays;
	}

	//执行方式
	private String operType = " ";
	public String getOperType(){
		return operType;
	}
	public void setOperType(String operType){
		this.operType = operType;
	}

	//规则描述
	private String ruleDesc = " ";
	public String getRuleDesc(){
		return ruleDesc;
	}
	public void setRuleDesc(String ruleDesc){
		this.ruleDesc = ruleDesc;
	}

	//机构
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}


}
