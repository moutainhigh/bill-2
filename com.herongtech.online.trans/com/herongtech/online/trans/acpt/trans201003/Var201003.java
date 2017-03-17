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
package com.herongtech.online.trans.acpt.trans201003;

import java.util.List;

import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.console.service.busiservice.acpt.AcptApplyBean;
import com.herongtech.console.service.busiservice.acpt.AcptBillInfoBean;
import com.herongtech.online.trans.trans101101.Var101101InfoBean;

public class Var201003 {

	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	private AcptApplyBean apply;
	private List<Var201003InfoBean> infoList;
	
	private List<Var201003InfoBean> result;
	public List<Var201003InfoBean> getResult() {
		return result;
	}
	public void setResult(List<Var201003InfoBean> result) {
		this.result = result;
	}

	//批次号
	private String batchNo;
	//公共对象
	TransPub transPub = null;

	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public TransPub getTransPub() {
		return transPub;
	}
	public void setTransPub(TransPub transPub) {
		this.transPub = transPub;
	}
	public AcptApplyBean getApply() {
		return apply;
	}
	public void setApply(AcptApplyBean apply) {
		this.apply = apply;
	}
	public List<Var201003InfoBean> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<Var201003InfoBean> infoList) {
		this.infoList = infoList;
	}
	
	
}
