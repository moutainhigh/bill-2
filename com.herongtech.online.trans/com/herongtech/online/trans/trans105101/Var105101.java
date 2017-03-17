

package com.herongtech.online.trans.trans105101;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.loading.PrivateClassLoader;

import com.herongtech.data.interfaces.data.IData;
import com.herongtech.online.trans.trans101101.Var101101InfoBean;

/**
 * 客户信息查询
 * @author Administrator
 *
 */
public class Var105101 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	public String getTotNum() {
		return totNum;
	}
	public void setTotNum(String totNum) {
		this.totNum = totNum;
	}
	public String getErrNum() {
		return errNum;
	}
	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}
	private String totNum;
	private String errNum;
	private String custAccount = null;
	private String rgctIds = null;
	private String signature = null;
	private String settlementMark = null;
	private String overdueMark = null;
	private String overDueReason = null;
	private String Type = null;
	private String remark = null;
	private List<Var105101InfoBean> result;
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
	public String getSettlementMark() {
		return settlementMark;
	}
	public void setSettlementMark(String settlementMark) {
		this.settlementMark = settlementMark;
	}
	public String getOverdueMark() {
		return overdueMark;
	}
	public void setOverdueMark(String overdueMark) {
		this.overdueMark = overdueMark;
	}
	public String getOverDueReason() {
		return overDueReason;
	}
	public void setOverDueReason(String overDueReason) {
		this.overDueReason = overDueReason;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<Var105101InfoBean> getResult() {
		return result;
	}
	public void setResult(List<Var105101InfoBean> result) {
		this.result = result;
	}
	public static String getHundsunVersion() {
		return HUNDSUN_VERSION;
	}
	


}
