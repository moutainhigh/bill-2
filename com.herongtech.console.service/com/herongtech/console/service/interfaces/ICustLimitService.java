/********************************************
 * 文件名称: ICustLimitService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 客户额度
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-10-25 11:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.CustLimit;

public interface ICustLimitService{
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 功能描述L：得到客户余额信息列表
	 * @param page
	 * @return
	 * @throws SQLException 
	 */
	public List<CustLimit> getList(Page page) throws SQLException;

	/**
	 * 功能描述：添加客户余额
	 * @param cb
	 * @return
	 * @throws SQLException 
	 */
	public int addCustLimit(CustLimit cb) throws SQLException;
	
	/**
	 * 功能描述：修改客户余额
	 * @param cb
	 * @return
	 * @throws SQLException 
	 */
	public int modCustLimit(CustLimit cb) throws SQLException;

	/**
	 * 功能描述：得到客户余额信息
	 * @param cb
	 * @return
	 * @throws SQLException 
	 */
	public CustLimit getCustLimit(String custNo) throws SQLException;
	
}
