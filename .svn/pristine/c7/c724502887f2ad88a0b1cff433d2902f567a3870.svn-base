/********************************************
 * 文件名称: ITradeBankService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-1 下午15:45:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.TradeBank;
import com.herongtech.exception.impl.BizAppException;

public interface ITradeBankService {
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取交易对手信息维护表
	 * 
	 * @param childBankno 分支行行号 
	 */
	public TradeBank getTradeBank(String childBankno) throws BizAppException;
	
	/**
	 * 插入交易对手信息维护表
	 *
	 */
	public void addTradeBank(TradeBank tradeBank) throws BizAppException;
	
	/**
	 * 修改交易对手信息维护表
	 */
	public void modifyTradeBank(TradeBank tradeBank) throws BizAppException; 
}
