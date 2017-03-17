/********************************************
* 文件名称: ProdLimitType.java
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
package com.herongtech.console.domain.common.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class ProdLimitType{
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

	//买断标记
	private String limitType = " ";
	public String getLimitType(){
		return limitType;
	}
	public void setLimitType(String limitType){
		this.limitType = limitType;
	}

	//买入类型1
	private String buyType = " ";
	public String getBuyType(){
		return buyType;
	}
	public void setBuyType(String buyType){
		this.buyType = buyType;
	}

	//是否系统内
	private String isInner = " ";
	public String getIsInner(){
		return isInner;
	}
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}

	//票据种类
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//买入类型
	private String buyIntoType = " ";
	public String getBuyIntoType(){
		return buyIntoType;
	}
	public void setBuyIntoType(String buyIntoType){
		this.buyIntoType = buyIntoType;
	}


}
