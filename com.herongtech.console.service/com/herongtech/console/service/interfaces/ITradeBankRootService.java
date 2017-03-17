/********************************************
 * 文件名称: ITradeBankRootService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-2 下午15:45:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.TradeBankRoot;
import com.herongtech.exception.impl.BizAppException;

public interface ITradeBankRootService {
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取交易对手总行对照表
	 * 
	 * @param bankNo 行号 
	 */
	public TradeBankRoot getTradeBankRoot(String bankNo) throws BizAppException;
	
	/**
	 * 插入交易对手总行对照表
	 *
	 */
	public void addTradeBankRoot(TradeBankRoot tradeBankRoot) throws BizAppException;
	
	/**
	 * 修改交易对手总行对照表
	 */
	public void modifyTradeBankRoot(TradeBankRoot tradeBankRoot) throws BizAppException; 
}
