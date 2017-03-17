/********************************************
 * 文件名称: Var130102.java
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

package com.herongtech.online.trans.trans130102;

import java.util.List;

/**
 * 客户信息查询
 * @author Administrator
 *
 */
public class Var130102 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	private String errNum;
	private String totNum;
	private String custAccount = null;
	private String signature = null;
	private String rgctIds = null;
	private List<Var130102InfoBean> result;
	
	public String getCustAccount() {
		return custAccount;
	}
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}
	public String getRgctIds() {
		return rgctIds;
	}
	public void setRgctIds(String rgctIds) {
		this.rgctIds = rgctIds;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public List<Var130102InfoBean> getResult() {
		return result;
	}
	public void setResult(List<Var130102InfoBean> result) {
		this.result = result;
	}
	public String getErrNum() {
		return errNum;
	}
	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}
	public String getTotNum() {
		return totNum;
	}
	public void setTotNum(String totNum) {
		this.totNum = totNum;
	}
	
	
}
