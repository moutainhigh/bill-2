/********************************************
 * 文件名称: Var103001.java
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

package com.herongtech.online.trans.trans111001;

import java.util.List;

import com.herongtech.console.domain.bean.SignProd;


/**
 * 签约信息查询
 * @author Administrator
 *
 */
public class Var111001 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	private String productNo = null;//签约产品编号
	private String custNo = null;//签约客户号
	private List<SignProd> result;

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public List<SignProd> getResult() {
		return result;
	}

	public void setResult(List<SignProd> result) {
		this.result = result;
	}


	

}
