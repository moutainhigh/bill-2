/********************************************
 * 文件名称: ITransPubService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.EcdsBillBean;
import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.context.Context;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctMethod;

public interface ITransPubService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	//请求信息转成公共对象
	public TransPub getTransPub(Context context) throws BizAppException;
	
	//请求信息转成公共对象
	public void ecdsBill2BillInfo(EcdsBillBean bean, RgctBillInfo info) throws BizAppException;
	
	//计算EBSNO
	public String generateEBSNO(RgctBillInfo billInfo);
	
	//设置票据历史登记信息状态和方法名
	public void setBillHistStatus(RgctBillHist hist) throws BizAppException;
	
	//设置票据历史登记信息状态和方法名
	public String getBillhistStatusSql(RgctMethod method, boolean bDelFlag) throws BizAppException;
}
