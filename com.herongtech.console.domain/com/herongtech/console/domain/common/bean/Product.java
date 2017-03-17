/********************************************
* 文件名称: Product.java
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

public class Product{
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

	//产品名称
	private String prodName = " ";
	public String getProdName(){
		return prodName;
	}
	public void setProdName(String prodName){
		this.prodName = prodName;
	}

	//产品状态
	private String prodStatus = " ";
	public String getProdStatus(){
		return prodStatus;
	}
	public void setProdStatus(String prodStatus){
		this.prodStatus = prodStatus;
	}

	//创建时间
	private String createTime = " ";
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	//父节点产品编号
	private String parentProdNo = " ";
	public String getParentProdNo(){
		return parentProdNo;
	}
	public void setParentProdNo(String parentProdNo){
		this.parentProdNo = parentProdNo;
	}


}
