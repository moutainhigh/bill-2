/********************************************
* 文件名称: ProfessionInvestDirection.java
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


public class ProfessionInvestDirection{
	private String id = " ";	//id
	private String professionName = " ";	//行业类型
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getProfessionName(){
		return professionName;
	}
	public void setProfessionName(String professionName){
		this.professionName = professionName;
	}


}
