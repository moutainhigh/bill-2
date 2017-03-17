/********************************************
* 文件名称: CustBillStorageDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.rc.domain.dao;

import java.sql.SQLException;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.CustBillStorage;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
public class CustBillStorageDao{

public int addCustBillStorage(CustBillStorage obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tcust_bill_storage(id,bill_no,hold_acct_no,hold_bank_no,hold_cust_name,is_register,is_accept,is_impawn,is_balance,rgct_id,update_dt,update_time)values(?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getBillNo(),obj.getHoldAcctNo(),obj.getHoldBankNo(),obj.getHoldCustName(),
	obj.getIsRegister(),obj.getIsAccept(),obj.getIsImpawn(),obj.getIsBalance(),
	obj.getRgctId(),obj.getUpdateDt(),obj.getUpdateTime());
}

public int deleteCustBillStorage(CustBillStorage obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_bill_storage where id=?",obj.getId());
}

public int deleteCustBillStorage(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_bill_storage where id=?",id);
}

public int modifyCustBillStorage(CustBillStorage obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_bill_storage set bill_no=?,hold_acct_no=?,hold_bank_no=?,hold_cust_name=?,is_register=?,is_accept=?,is_impawn=?,is_balance=?,rgct_id=?,update_dt=?,update_time=? where id=?",
	obj.getBillNo(),obj.getHoldAcctNo(),obj.getHoldBankNo(),obj.getHoldCustName(),
	obj.getIsRegister(),obj.getIsAccept(),obj.getIsImpawn(),obj.getIsBalance(),
	obj.getRgctId(),obj.getUpdateDt(),obj.getUpdateTime(),obj.getId());
}

public int modifyCustBillStorage(CustBillStorage obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_bill_storage set bill_no=?,hold_acct_no=?,hold_bank_no=?,hold_cust_name=?,is_register=?,is_accept=?,is_impawn=?,is_balance=?,rgct_id=?,update_dt=?,update_time=? where id=?",
	obj.getBillNo(),obj.getHoldAcctNo(),obj.getHoldBankNo(),obj.getHoldCustName(),
	obj.getIsRegister(),obj.getIsAccept(),obj.getIsImpawn(),obj.getIsBalance(),
	obj.getRgctId(),obj.getUpdateDt(),obj.getUpdateTime(),id);
}

public CustBillStorage getCustBillStorage(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	CustBillStorage obj = (CustBillStorage)session.getObject("select * from tcust_bill_storage where id=?",CustBillStorage.class,id);
	return obj;
}

public CustBillStorage serchCustBillStorage(String rgctId) throws SQLException {
	IDB session = DBFactory.getDB();
	CustBillStorage obj = (CustBillStorage)session.getObject("select * from tcust_bill_storage where rgct_id=?",CustBillStorage.class,rgctId);
	return obj;
}
/**
 * 出票余额
 */
public String serchBalanceRegister(RcBaseSearchBean searchBean)throws SQLException {
		String sql = "select rbi.*,rbh.* from trgct_bill_info as rbi, tcust_bill_storage as bs, trgct_bill_hist as rbh where rbi.id = bs.rgct_id and rbi.hist_id = rbh.hist_id "
				+ " and bs.is_register = '1' and rbh.cur_status not in ('R_16','S_16','D_07') " 
				+ " and rbi.remitter_acct = '" + searchBean.getHoldAcctNo() + "' ";
		return sql;
}
/**
 * 承兑余额（网银端）
 */
public String serchBalanceAcceptNet(RcBaseSearchBean searchBean)throws SQLException {
		String sql = "select rbi.*,rbh.* from trgct_bill_info as rbi, tcust_bill_storage as bs, trgct_bill_hist as rbh where rbi.id = bs.rgct_id and rbi.hist_id = rbh.hist_id "
				+ " and bs.is_accept = '1' and rbh.cur_status not in ('R_16','S_16','D_07') " 
				+ " and rbi.bill_type = '2' and rbi.acceptor_acct = '" + searchBean.getHoldAcctNo()+ "' ";
		return sql;
}
/**
 * 库存余额（网银端）
 */
public String serchBalanceInventoryNet(RcBaseSearchBean searchBean) throws SQLException {
		String sql = "select rbi.*,rbh.* from trgct_bill_info as rbi, tcust_bill_storage as bs, trgct_bill_hist as rbh where rbi.id = bs.rgct_id and rbi.hist_id = rbh.hist_id "
				+ " and bs.is_balance = '1' and rbh.cur_status not in ('R_16','S_16') and bs.hold_acct_no = '" + searchBean.getHoldAcctNo()+ "' ";
		return sql;
}
/**
 * 质押余额（网银端）
 */
public String serchBalanceImpawnNet(RcBaseSearchBean searchBean)throws SQLException {
		String sql = "select rbi.*,rbh.* from trgct_bill_info as rbi, tcust_bill_storage as bs, trgct_bill_hist as rbh where rbi.id = bs.rgct_id and rbi.hist_id = rbh.hist_id "
				+ " and bs.is_impawn = '1' and rbh.cur_status not in ('R_16','S_16') and bs.hold_acct_no = '"
				+ searchBean.getHoldAcctNo() + "' ";
		return sql;
}

/**
 * 托收在途余额（网银端）
 */
public String serchBalanceCollectionNet(RcBaseSearchBean searchBean)throws SQLException {
		String sql = "select rbi.*,rbh.* from trgct_bill_info as rbi,trgct_bill_hist as rbh where rbi.hist_id = rbh.hist_id "
				+ " and rbh.cur_status in ('R_08','S_08') and rbh.hold_acct_no = '" + searchBean.getHoldAcctNo()+ "' ";
		return sql;
}
/**
 * 到期未收回余额
 */
public String serchBalanceDueBackNot(RcBaseSearchBean searchBean)throws SQLException {
	String sql="";
		try {
			 sql = "select rbi.*,rbh.* from trgct_bill_info as rbi, tcust_bill_storage as bs, trgct_bill_hist as rbh where rbi.id = bs.rgct_id and rbi.hist_id = rbh.hist_id "
					+ "and bs.is_balance = '1' and (rbh.cur_status <> 'R_16' and  rbh.cur_status <> 'S_16') and bs.hold_acct_no = '"
					+ searchBean.getHoldAcctNo() + "' and rbi.due_dt<='"+DateTimeUtil.getWorkday()+"' ";
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		return sql;
}
///**与质押余额相对重复暂时不用
// * 质权票据余额
// */
//public String serchBalancePledge(RcBaseSearchBean searchBean)throws SQLException  {
//		String sql = "select rbi.*,rbh.* from trgct_bill_info as rbi,trgct_bill_hist as rbh where rbi.hist_id = rbh.hist_id "
//				+ "and rbh.cur_status='O_02' and rbh.endo_status='O_02' " + "and rbh.hold_acct_no = '"
//				+ searchBean.getHoldAcctNo() + "' ";
//		return sql;
//}
}
