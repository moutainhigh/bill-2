/********************************************
 * 文件名称: IParamService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-8 下午03:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.Errmsg;
import com.herongtech.exception.impl.BizAppException;

public interface IErrmsgService {

	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取错误信息表
	 * 
	 * @param errCode 错误信息
	 */
	public  Errmsg getErrmsg(String errCode) throws BizAppException;
	
	/**
	 * 插入错误信息表
	 *
	 */
	public void addErrmsg(Errmsg errmsg)throws BizAppException;
	
	/**
	 * 修改错误信息表
	 */
	
	public void modifyErrmsg(Errmsg errmsg)throws BizAppException;
}
