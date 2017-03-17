/********************************************
 * 文件名称: IBankInfoService.java
 * 名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-12 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.BankInfo;
import com.herongtech.exception.impl.BizAppException;


public interface IBankInfoService {
	
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取大行信息
	 * 
	 * @bankInfo id 主键
	 */
	public  BankInfo getBankInfo(String bankNo) throws BizAppException;
	
	/**
	 * 插入大行信息表
	 *
	 */
	public void addBankInfo(BankInfo bankInfo)throws BizAppException;
	
	/**
	 * 修改大行信息表
	 */
	
	public void modifyBankInfo(BankInfo bankInfo)throws BizAppException;
}
