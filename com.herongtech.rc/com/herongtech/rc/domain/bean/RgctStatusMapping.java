/********************************************
* 文件名称: RgctStatusMapping.java
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


public class RgctStatusMapping{
	private String id = " ";	//主键
	private String bbspStatusCode = " ";	//系统状态
	private String ecdsStatusCode = " ";	//ecds状态
	private String statusName = " ";	//状态名称
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getBbspStatusCode(){
		return bbspStatusCode;
	}
	public void setBbspStatusCode(String bbspStatusCode){
		this.bbspStatusCode = bbspStatusCode;
	}

	public String getEcdsStatusCode(){
		return ecdsStatusCode;
	}
	public void setEcdsStatusCode(String ecdsStatusCode){
		this.ecdsStatusCode = ecdsStatusCode;
	}

	public String getStatusName(){
		return statusName;
	}
	public void setStatusName(String statusName){
		this.statusName = statusName;
	}


}
