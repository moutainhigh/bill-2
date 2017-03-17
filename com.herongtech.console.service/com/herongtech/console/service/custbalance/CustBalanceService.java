package com.herongtech.console.service.custbalance;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.CustBalance;
import com.herongtech.console.domain.dao.CustBalanceDao;
import com.herongtech.console.service.interfaces.ICustBalanceService;

public class CustBalanceService  implements ICustBalanceService{
	
	private CustBalanceDao dao = new CustBalanceDao();
	/**
	 * 功能描述L：得到客户余额信息列表
	 * @param page
	 * @return
	 * @throws SQLException 
	 */
	public List<CustBalance> getList(Page page) throws SQLException{
		return dao.getCustBalanceList(page);
	}
	
	/**
	 * 功能描述：添加客户余额信息
	 * @param cb
	 * @return
	 * @throws SQLException 
	 */
	public int addCustBalance(CustBalance cb) throws SQLException{
		cb.setOperUser(ResourceUtil.getSessionLoginfo().getUserId());
		cb.setOperDate(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss"));
		return dao.addCustBalance(cb);
	}
	
	
	/**
	 * 功能描述：修改客户余额信息
	 * @param cb
	 * @return
	 * @throws SQLException 
	 */
	public int modCustBalance(CustBalance cb) throws SQLException{
		cb.setOperUser(ResourceUtil.getSessionLoginfo().getUserId());
		cb.setOperDate(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss"));
		return dao.modifyCustBalance(cb);
	}
	
	/**
	 * 功能描述：得到客户余额信息
	 * @param cb
	 * @return
	 * @throws SQLException 
	 */
	public CustBalance getCustBalance(String custNo) throws SQLException{
		return dao.getCustBalance(custNo);
	}
}
