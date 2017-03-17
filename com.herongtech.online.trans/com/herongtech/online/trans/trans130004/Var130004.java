/********************************************
 * 文件名称: Var130004.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 20160808
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.online.trans.trans130004;

import java.util.List;

/**
 * 客户信息查询
 * @author Administrator
 *
 */
public class Var130004 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	private String custAccount;
	private String billClass;
	private String rgctId;
	private List<Var130004InfoBean> result;
	public String getCustAccount() {
		return custAccount;
	}
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}
	public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
	public List<Var130004InfoBean> getResult() {
		return result;
	}
	public void setResult(List<Var130004InfoBean> result) {
		this.result = result;
	}
	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}
	public String getRgctId() {
		return rgctId;
	}
	
	
}
