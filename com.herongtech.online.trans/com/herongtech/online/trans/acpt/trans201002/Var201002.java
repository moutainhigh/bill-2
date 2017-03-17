/********************************************
 * 文件名称: Var201003.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 20160826
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.acpt.trans201002;

import com.herongtech.console.domain.bean.TransPub;

public class Var201002 {

	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	//ids 逗号分隔
	private String acptmxIds;
	//公共对象
	TransPub transPub;
	public String getAcptmxIds() {
		return acptmxIds;
	}
	public void setAcptmxIds(String acptmxIds) {
		this.acptmxIds = acptmxIds;
	}
	public TransPub getTransPub() {
		return transPub;
	}
	public void setTransPub(TransPub transPub) {
		this.transPub = transPub;
	}
	
}
