/********************************************
* 文件名称: TradeBankRoot.java
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

public class TradeBankRoot{
	//ID
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//前三位行号
	private String leftThreeBankNo = " ";
	public String getLeftThreeBankNo(){
		return leftThreeBankNo;
	}
	public void setLeftThreeBankNo(String leftThreeBankNo){
		this.leftThreeBankNo = leftThreeBankNo;
	}

	//行名
	private String bankName = " ";
	public String getBankName(){
		return bankName;
	}
	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	//行号
	private String bankNo = " ";
	public String getBankNo(){
		return bankNo;
	}
	public void setBankNo(String bankNo){
		this.bankNo = bankNo;
	}


}
