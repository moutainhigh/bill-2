/********************************************
* 文件名称: TransPub.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.bean;

import com.herongtech.data.interfaces.data.IData;
import com.herongtech.online.trans.trans106101.Var106101;
import com.herongtech.rc.domain.bean.RgctBill;
public class TransPub {

	//网银请求数据集
	
	//网银请求接口bean
	private EcdsBillBean bean = new EcdsBillBean();
	public EcdsBillBean getEcdsBillBean(){
		return bean;
	}
	public void setEcdsBillBean(EcdsBillBean bean){
		this.bean = bean;
	}
	
	//票据处理对象
	private RgctBill rgctBill = new RgctBill();
	public RgctBill getRgctBill(){
		return rgctBill;
	}
	public void setRgctBill(RgctBill rgctBill){
		this.rgctBill = rgctBill;
	}
	
}
