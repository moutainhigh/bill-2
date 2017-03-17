/********************************************
 * 文件名称: Var100301.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 20160716
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.rc.trans.trans054;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.rc.domain.bean.RgctEcdsStatus;

/**
 * 客户信息查询
 * @author Administrator
 *
 */
public class Var054 {
	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	/**
	 * 系统状态
	 */
	private String sequence = " ";

	/**
	 * 
	 * @return sequence
	 */
	public String getSequence() {
		return sequence;
	}
	
	/**
	 * 
	 * @return sequence
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * 系统状态
	 */
	List<RgctEcdsStatus> ecdsStatusList = new ArrayList<RgctEcdsStatus>();

	/**
	 * 
	 * @return sequence
	 */
	public List<RgctEcdsStatus> getEcdsStatusList() {
		return ecdsStatusList;
	}
	
	/**
	 * 
	 * @return sequence
	 */
	public void setEcdsStatusList(List<RgctEcdsStatus> ecdsStatusList) {
		this.ecdsStatusList = ecdsStatusList;
	}
}
