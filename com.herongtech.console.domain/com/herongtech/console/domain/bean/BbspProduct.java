/********************************************
* 文件名称: BbspProduct.java
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

public class BbspProduct{
	//ID
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//产品名称
	private String prodName = " ";
	public String getProdName(){
		return prodName;
	}
	public void setProdName(String prodName){
		this.prodName = prodName;
	}

	//产品编号
	private String prodNo = " ";
	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	//启用标志
	private String prodType = " ";
	public String getProdType(){
		return prodType;
	}
	public void setProdType(String prodType){
		this.prodType = prodType;
	}

	//产品状态
	private String prodStatus = " ";
	public String getProdStatus(){
		return prodStatus;
	}
	public void setProdStatus(String prodStatus){
		this.prodStatus = prodStatus;
	}

	//开立时间
	private String createDate = " ";
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	//创建柜员名称
	private String createUserName = " ";
	public String getCreateUserName(){
		return createUserName;
	}
	public void setCreateUserName(String createUserName){
		this.createUserName = createUserName;
	}

	//创建柜员号
	private String createUserNo = " ";
	public String getCreateUserNo(){
		return createUserNo;
	}
	public void setCreateUserNo(String createUserNo){
		this.createUserNo = createUserNo;
	}

	//删除标示
	private String delFlag = " ";
	public String getDelFlag(){
		return delFlag;
	}
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}

	//删除柜员
	private String delUserName = " ";
	public String getDelUserName(){
		return delUserName;
	}
	public void setDelUserName(String delUserName){
		this.delUserName = delUserName;
	}

	//删除柜员号
	private String delUserNo = " ";
	public String getDelUserNo(){
		return delUserNo;
	}
	public void setDelUserNo(String delUserNo){
		this.delUserNo = delUserNo;
	}

	//删除日期
	private String delDate = " ";
	public String getDelDate(){
		return delDate;
	}
	public void setDelDate(String delDate){
		this.delDate = delDate;
	}

	//产品编码
	private String prodCode = " ";
	public String getProdCode(){
		return prodCode;
	}
	public void setProdCode(String prodCode){
		this.prodCode = prodCode;
	}

	//收费类型编码
	private String chargeType = " ";
	public String getChargeType(){
		return chargeType;
	}
	public void setChargeType(String chargeType){
		this.chargeType = chargeType;
	}

	//备注
	private String summary = " ";
	public String getSummary(){
		return summary;
	}
	public void setSummary(String summary){
		this.summary = summary;
	}

	//开通权限
	private String branchsPower = " ";
	public String getBranchsPower(){
		return branchsPower;
	}
	public void setBranchsPower(String branchsPower){
		this.branchsPower = branchsPower;
	}


}
