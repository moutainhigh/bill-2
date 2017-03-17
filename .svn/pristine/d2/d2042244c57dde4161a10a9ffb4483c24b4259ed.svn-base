/********************************************
 * 文件名称: Var103101.java
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

package com.herongtech.online.trans.trans103101;


import java.util.List;

import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;


/**
 * 客户信息查询
 * @author Administrator
 *
 */
public class Var103101 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	

	private String custAccount = null;
	private String receiverName = null;
	private String receiverAcctNo = null;
	private String receiverBankNo = null;
	private String banEndorsementMark = null;
	private String rgctIds = null;
	private String signature = null;
	private String remark=null;
	private String operator=null;
	/**
	 * 交易类型
	 */
	private String type;

	
	private List<Var103101InfoBean> result;
	
	

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
	
	
	
	/**
	 * 查询结果
	 */
	private IData resultData = null;
	
	//公共对象
		TransPub transPub = null;
		
		public void setTransPub(TransPub transPub){
			this.transPub = transPub;
		}
		
		public TransPub getTransPub(){
			return transPub;
		}
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

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAcctNo() {
		return receiverAcctNo;
	}

	public void setReceiverAcctNo(String receiverAcctNo) {
		this.receiverAcctNo = receiverAcctNo;
	}

	public String getReceiverBankNo() {
		return receiverBankNo;
	}

	public void setReceiverBankNo(String receiverBankNo) {
		this.receiverBankNo = receiverBankNo;
	}

	public String getBanEndorsementMark() {
		return banEndorsementMark;
	}

	public void setBanEndorsementMark(String banEndorsementMark) {
		this.banEndorsementMark = banEndorsementMark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<Var103101InfoBean> getResult() {
		return result;
	}

	public void setResult(List<Var103101InfoBean> result) {
		this.result = result;
	}
	
	
	
}
