/********************************************
 * 文件名称: ICustmanagerService.java
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

import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.exception.impl.BizAppException;


public interface ICustManageService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取客户经理信息
	 * 
	 * @param custManagerNo 客户经理标识
	 */
	public  CustManage getCustManage(String custManagerNo) throws BizAppException;
	/**
	 * 获取客户经理信息
	 * 
	 * @param custManagerNo 客户经理标识
	 */
	public  CustManage getCustManageByDeptNo(String ids) throws BizAppException;
	
	/**
	 * 插入客户经理表
	 *
	 */
	public void addCustManage(CustManage custManager)throws BizAppException;
	
	/**
	 * 修改客户经理表
	 */
	
	public void modifyCustManage(CustManage custManager)throws BizAppException;
}
