/********************************************
 * 文件名称: ICustInfoService.java
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

import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.exception.impl.BizAppException;

public interface ICustInfoService {

	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取客户信息参数
	 * 
	 * @param id 客户信息标识
	 */
    public  CustInfo getParam(String custNo) throws BizAppException;
	
	/**
	 * 插入客户信息表
	 *
	 */
	public void addParam(CustInfo custI)throws BizAppException;
	
	/**
	 * 修改客户信息表
	 */
	
	public void modifyParam(CustInfo custI)throws BizAppException;
	
	/**
	 * 根据组织机构代码查找是否存在客户
	 */
	
	public boolean validateCustInfoByOrgNo(String orgNo)throws BizAppException;
	
	/**
	 * 根据组织机构代码和客户名称查找客户信息
	 */
	
	public CustInfo getCustInfoByOrgNoAndName(String orgNo,String custName) throws BizAppException;
	
}
