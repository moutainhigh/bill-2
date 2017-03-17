/********************************************
* 文件名称: Errmsg.java
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
import java.math.*;

public class Errmsg{
	//错误代码
	private String errCode = " ";
	public String getErrCode(){
		return errCode;
	}
	public void setErrCode(String errCode){
		this.errCode = errCode;
	}

	//错误信息
	private String errMsg = " ";
	public String getErrMsg(){
		return errMsg;
	}
	public void setErrMsg(String errMsg){
		this.errMsg = errMsg;
	}


}
