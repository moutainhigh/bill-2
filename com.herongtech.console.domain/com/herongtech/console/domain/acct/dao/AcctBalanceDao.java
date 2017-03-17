/********************************************
* 文件名称: AcctBalanceDao.java
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
package com.herongtech.console.domain.acct.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.domain.acct.bean.AcctBalance;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class AcctBalanceDao{

public int addAcctBalance(AcctBalance obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tacct_balance(balance_id,info_id,rgct_id,bill_no,start_dt,end_dt,cust_no,cust_name,prod_no,status,create_dt,create_tm,update_dt,update_tm,storage_brch_no,storage_brch_name,acct_brch_no,acct_brch_name)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getBalanceId(),obj.getInfoId(),obj.getRgctId(),obj.getBillNo(),obj.getStartDt(),
	obj.getEndDt(),obj.getCustNo(),obj.getCustName(),obj.getProdNo(),
	obj.getStatus(),obj.getCreateDt(),obj.getCreateTm(),obj.getUpdateDt(),
	obj.getUpdateTm(),obj.getStorageBrchNo(),obj.getStorageBrchName(),obj.getAcctBrchNo(),
	obj.getAcctBrchName());
}

public int deleteAcctBalance(AcctBalance obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacct_balance where balance_id=?",obj.getBalanceId());
}

public int deleteAcctBalance(String balanceId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacct_balance where balance_id=?",balanceId);
}

public int modifyAcctBalance(AcctBalance obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacct_balance set info_id=?,rgct_id=?,bill_no=?,start_dt=?,end_dt=?,cust_no=?,cust_name=?,prod_no=?,status=?,create_dt=?,create_tm=?,update_dt=?,update_tm=?,storage_brch_no=?,storage_brch_name=?,acct_brch_no=?,acct_brch_name=? where balance_id=?",
	obj.getInfoId(),obj.getRgctId(),obj.getBillNo(),obj.getStartDt(),
	obj.getEndDt(),obj.getCustNo(),obj.getCustName(),obj.getProdNo(),
	obj.getStatus(),obj.getCreateDt(),obj.getCreateTm(),obj.getUpdateDt(),
	obj.getUpdateTm(),obj.getStorageBrchNo(),obj.getStorageBrchName(),obj.getAcctBrchNo(),
	obj.getAcctBrchName(),obj.getBalanceId());
}

public int modifyAcctBalance(AcctBalance obj,String balanceId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacct_balance set info_id=?,rgct_id=?,bill_no=?,start_dt=?,end_dt=?,cust_no=?,cust_name=?,prod_no=?,status=?,create_dt=?,create_tm=?,update_dt=?,update_tm=?,storage_brch_no=?,storage_brch_name=?,acct_brch_no=?,acct_brch_name=? where balance_id=?",
	obj.getInfoId(),obj.getRgctId(),obj.getBillNo(),obj.getStartDt(),
	obj.getEndDt(),obj.getCustNo(),obj.getCustName(),obj.getProdNo(),
	obj.getStatus(),obj.getCreateDt(),obj.getCreateTm(),obj.getUpdateDt(),
	obj.getUpdateTm(),obj.getStorageBrchNo(),obj.getStorageBrchName(),obj.getAcctBrchNo(),
	obj.getAcctBrchName(),balanceId);
}

public AcctBalance getAcctBalance(String balanceId) throws SQLException {
	IDB session = DBFactory.getDB();
	AcctBalance obj = (AcctBalance)session.getObject("select * from tacct_balance where balance_id=?",AcctBalance.class,balanceId);
	return obj;
}

/**
 * 通过登记中心ID查询余额信息
 * @param rgctId
 * @param prodNo
 * @return
 * @throws SQLException
 */
public AcctBalance getAcctBalanceByRgctId(String rgctId)throws SQLException{
    IDB session = DBFactory.getDB();
    AcctBalance obj = (AcctBalance)session.getObject("select * from tacct_balance where rgct_id=? and status='"+IConstants.BALANCE_STATUS.BALANCE+"'",AcctBalance.class,rgctId);
    return obj;
}
/**
 * 通过登记中心ID和余额状态查询余额信息
 * @param rgctId
 * @param prodNo
 * @return
 * @throws SQLException
 */
public AcctBalance getAcctBalanceByRgctIdAndStatus(String rgctId,String status)throws SQLException{
    IDB session = DBFactory.getDB();
    AcctBalance obj = (AcctBalance)session.getObject("select * from tacct_balance where rgct_id=? and status=?",AcctBalance.class,rgctId,status);
    return obj;
}
/**
 * 通过票号查询余额信息
 * @param billNos	票号信息，使用逗号分隔
 * @return	余额信息
 * @throws SQLException
 */
public List<AcctBalance> getAcctBalanceByBillNo(String billNos,String acctBrchNo)throws SQLException{
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer();
	sb.append("SELECT * FROM TACCT_BALANCE WHERE  STATUS='" )
			.append(IConstants.BALANCE_STATUS.BALANCE)
			.append("' AND ACCT_BRCH_NO= '")
			.append(acctBrchNo)
			.append("' AND BILL_NO IN (")
			.append(billNos)
			.append(")");
	List<AcctBalance> objs = session.getObjectList(sb.toString(),AcctBalance.class);
    return objs;
}
	/**
	 * 获取余额表中所有数据
	 * @return
	 * @throws Exception 
	 */
	public List<AcctBalance> getAllAcctBalance() throws Exception{
		IDB session = DBFactory.getDB();
		return session.getObjectList("select * from tacct_balance", AcctBalance.class);
	}

}
