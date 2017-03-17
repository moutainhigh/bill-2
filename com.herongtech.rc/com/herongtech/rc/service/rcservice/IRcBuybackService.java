/********************************************
 * 文件名称: IRcBuybackService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 2016-8-24 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.service.rcservice;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IRcBuybackService {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 转贴现到期赎回签收
	 * @param bill
	 * @throws BizAppException
	 */
	public void buyBackSign(RgctBill bill) throws BizAppException;
	
	/**
	 *  转贴现到期赎回拒绝签收
	 * @param bill
	 * @throws BizAppException
	 */
	public void rejectSaleBackEndorse(RgctBill bill) throws BizAppException;
}
