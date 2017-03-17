package com.herongtech.console.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.CustBalance;

public interface ICustBalanceService {
	/**
	 * 功能描述L：得到客户余额信息列表
	 * @param page
	 * @return
	 * @throws SQLException 
	 */
	public List<CustBalance> getList(Page page) throws SQLException;

	/**
	 * 功能描述：添加客户余额
	 * @param cb
	 * @return
	 * @throws SQLException 
	 */
	public int addCustBalance(CustBalance cb) throws SQLException;
	
	/**
	 * 功能描述：修改客户余额
	 * @param cb
	 * @return
	 * @throws SQLException 
	 */
	public int modCustBalance(CustBalance cb) throws SQLException;
	
}
