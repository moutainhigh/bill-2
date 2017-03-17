/********************************************
 * 文件名称: Var103103.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 20160808
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.online.trans.trans103103;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.herongtech.data.interfaces.data.IData;
import com.herongtech.online.trans.trans103102.Var103102InfoBean;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;


/**
 * 客户信息查询
 * @author Administrator
 *
 */
public class Var103103 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	/**
	 * 查询结果
	 */
	private IData resultData = null;
	
	
	private  String custAccount = null;
	private String rgctIds = null;
	private String signature = null;
	private String signUpMark =null;
    private String remark = null;
    private String assuAddress;//保证人地址
	
	private String payRefuseCode;//拒付代码
	
	private String rejectReason;//拒付理由
	
	private Long recourseId;//追索ID
	private List<Var103103InfoBean> result;
	
	
	/**
	 * 总记录数
	 */
	private int totRecNum = 0;
	/**
	 * 请求查询对象
	 */
	private RcBaseSearchBean rcSb = null;

	/**
	 * 
	 * @return IDataset
	 */
	public IData getResultData() {
		return resultData;
	}

	/**
	 * 
	 * @param resultDataSet
	 */
	public void setResultData(IData resultData) {
		this.resultData = resultData;
	}

	public int getTotRecNum() {
		return totRecNum;
	}

	public void setTotRecNum(int totRecNum) {
		this.totRecNum = totRecNum;
	}
	public RcBaseSearchBean getRcBaseSearchBean() {
		return rcSb;
	}
	public void setRcBaseSearchBean(RcBaseSearchBean rcSb) {
		this.rcSb = rcSb;
	}

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

	public String getSignUpMark() {
		return signUpMark;
	}

	public void setSignUpMark(String signUpMark) {
		this.signUpMark = signUpMark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAssuAddress() {
		return assuAddress;
	}

	public void setAssuAddress(String assuAddress) {
		this.assuAddress = assuAddress;
	}

	public String getPayRefuseCode() {
		return payRefuseCode;
	}

	public void setPayRefuseCode(String payRefuseCode) {
		this.payRefuseCode = payRefuseCode;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Long getRecourseId() {
		return recourseId;
	}

	public void setRecourseId(Long recourseId) {
		this.recourseId = recourseId;
	}

	public List<Var103103InfoBean> getResult() {
		return result;
	}

	public void setResult(List<Var103103InfoBean> result) {
		this.result = result;
	}
	
}
