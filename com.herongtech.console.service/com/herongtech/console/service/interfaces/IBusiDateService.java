/********************************************
 * 文件名称: IBusiDateService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzc
 * 开发时间: 2016-8-11 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.interfaces;


import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.exception.impl.BizAppException;

public interface IBusiDateService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 取系统信息
	 * 
	 * @busidate sysBankNo 法人行编号
	 */
	public  BusiDate getBusiDate(String sysBankNo) throws BizAppException;
	
	/**
	 * 获取系统信息表
	 */
	public  BusiDate getDefaultBusiDate()throws BizAppException;
	
	/**
	 * 插入系统信息
	 *
	 */
	public void addBusiDate(BusiDate sysBankNo)throws BizAppException;
	
	/**
	 * 修改系统信息
	 */
	
	public void modifyBusiDate(BusiDate sysBankNo)throws BizAppException;
}
