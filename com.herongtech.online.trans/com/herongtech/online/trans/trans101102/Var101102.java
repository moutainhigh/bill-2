/********************************************
 * 文件名称: Var101102.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 20160809
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.online.trans.trans101102;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.herongtech.data.interfaces.data.IData;

/**
 * 客户信息查询
 * @author Administrator
 *
 */
public class Var101102 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
private String totNum;
	
	private String errNum;
	
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
	
	private List<Var101102InfoBean> resultList;
	
	
	
	public List<Var101102InfoBean> getResultList() {
		return resultList;
	}

	public void setResultList(List<Var101102InfoBean> resultList) {
		this.resultList = resultList;
	}

	private String custAccount = null;
	private String rgctIds = null;
	private String signature = null;
	private ArrayList<Object> result;
	
	public ArrayList<Object> getResult() {
		return result;
	}

	public void setResult(ArrayList<Object> result) {
		this.result = result;
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

	/**
	 * 查询结果
	 */
	private IData resultData = null;
	
	/**
	 * 总记录数
	 */
	private int totRecNum = 0;

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
}
