/********************************************
* 文件名称: CustLimitFlow.java
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

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.util.ResourceUtil;

public class CustLimitFlow{
	//流水号
	private String flowNo = " ";
	public String getFlowNo(){
		return flowNo;
	}
	public void setFlowNo(String flowNo){
		this.flowNo = flowNo;
	}

	//操作清单明细id
	private String operMxId = " ";
	public String getOperMxId(){
		return operMxId;
	}
	public void setOperMxId(String operMxId){
		this.operMxId = operMxId;
	}

	//操作类型
	private String operType = " ";
	public String getOperType(){
		return operType;
	}
	public void setOperType(String operType){
		this.operType = operType;
	}

	//额度品种
	private String operClass = " ";
	public String getOperClass(){
		return operClass;
	}
	public void setOperClass(String operClass){
		this.operClass = operClass;
	}

	//操作时间
	private String operDate = DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss");
	public String getOperDate(){
		return operDate;
	}
	public void setOperDate(String operDate){
		this.operDate = operDate;
	}

	//操作操作员
	private String operUser = ResourceUtil.getVirtualUser().getUserId();
	public String getOperUser(){
		return operUser;
	}
	public void setOperUser(String operUser){
		this.operUser = operUser;
	}

	//操作金额
	private double operMoney = 0.0;
	public double getOperMoney(){
		return operMoney;
	}
	public void setOperMoney(double operMoney){
		this.operMoney = operMoney;
	}

	//操作客户号
	private String operCustNo = " ";
	public String getOperCustNo(){
		return operCustNo;
	}
	public void setOperCustNo(String operCustNo){
		this.operCustNo = operCustNo;
	}

	//操作客户名称
	private String operCustName = " ";
	public String getOperCustName(){
		return operCustName;
	}
	public void setOperCustName(String operCustName){
		this.operCustName = operCustName;
	}


}
