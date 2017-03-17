/********************************************
 * 文件名称: IDelayRuleService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-12
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.DelayRule;
import com.herongtech.exception.impl.BizAppException;


public interface IDelayRuleService {
	
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * @delayrule Id 标识
	 */
	public  DelayRule getDelayRule(Long id) throws BizAppException;
	
	public DelayRule getDelayRule(String productNo,String billClass,String billType) throws BizAppException;
	
	/**
	 * 插入顺延规则表
	 *
	 */
	public void addDelayRule(DelayRule delayrule)throws BizAppException;
	
	/**
	 * 修改顺延规则表
	 */
	
	public void modifyDelayRule(DelayRule delayrule)throws BizAppException;
}
