/********************************************
 * 文件名称: ICustLimitFlowService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-10-28 11:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.CustLimitFlow;
import com.herongtech.exception.impl.BizAppException;

public interface ICustLimitFlowService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 功能描述:客户占用额度操作流水
	 * @param clFlow
	 * @return
	 * @throws BizAppException 
	 */
	public int usedCustLimitFlow(CustLimitFlow clFlow) throws BizAppException;
	
	/**
	 * 功能描述:释放客户占用额度操作流水
	 * @param clFlow
	 * @return
	 * @throws SQLException
	 */
	public int releaseCustLimitFlow(CustLimitFlow clFlow) throws BizAppException;
}
