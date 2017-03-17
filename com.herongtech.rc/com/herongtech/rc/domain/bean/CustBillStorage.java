/********************************************
* 文件名称: CustBillStorage.java
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
package com.herongtech.rc.domain.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class CustBillStorage{
	private String id = " ";	//主键
	private String billNo = " ";	//票号
	private String holdAcctNo = " ";	//票据持有人账号
	private String holdBankNo = " ";	//票据持有人行号
	private String holdCustName = " ";	//票据持有人名称
	private String isRegister;//是否已出票
	private String isAccept;//是否已承兑
	private String isImpawn;//是否已质押
	private String isBalance;//是否在库 1;在库
	private String rgctId = " ";	//登记中心id
	private String updateDt = " ";	//更新日期
	private String updateTime = " ";	//更新时间
	
	public CustBillStorage() {
		this.isRegister="0";
		this.isAccept="0";
		this.isImpawn="0";
		this.isBalance="0";
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	public String getHoldAcctNo(){
		return holdAcctNo;
	}
	public void setHoldAcctNo(String holdAcctNo){
		this.holdAcctNo = holdAcctNo;
	}

	public String getHoldBankNo(){
		return holdBankNo;
	}
	public void setHoldBankNo(String holdBankNo){
		this.holdBankNo = holdBankNo;
	}

	public String getHoldCustName(){
		return holdCustName;
	}
	public void setHoldCustName(String holdCustName){
		this.holdCustName = holdCustName;
	}

	public String getIsRegister(){
		return isRegister;
	}
	public void setIsRegister(String isRegister){
		this.isRegister = isRegister;
	}

	public String getIsAccept(){
		return isAccept;
	}
	public void setIsAccept(String isAccept){
		this.isAccept = isAccept;
	}

	public String getIsImpawn(){
		return isImpawn;
	}
	public void setIsImpawn(String isImpawn){
		this.isImpawn = isImpawn;
	}

	public String getIsBalance(){
		return isBalance;
	}
	public void setIsBalance(String isBalance){
		this.isBalance = isBalance;
	}

	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	public String getUpdateDt(){
		return updateDt;
	}
	public void setUpdateDt(String updateDt){
		this.updateDt = updateDt;
	}

	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}


}
