/********************************************
* 文件名称: AcctBalanceHisDao.java
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

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.acct.bean.AcctBalanceHis;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class AcctBalanceHisDao{

public int addAcctBalanceHis(AcctBalanceHis obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tacct_balance_his(his_id,busi_dt,info_id,rgct_id,bill_no,start_dt,end_dt,cust_no,cust_name,prod_no,status,create_dt,create_tm,update_dt,update_tm,storage_brch_no,storage_brch_name,acct_brch_no,acct_brch_name)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getHisId(),obj.getBusiDt(),obj.getInfoId(),obj.getRgctId(),obj.getBillNo(),
	obj.getStartDt(),obj.getEndDt(),obj.getCustNo(),obj.getCustName(),
	obj.getProdNo(),obj.getStatus(),obj.getCreateDt(),obj.getCreateTm(),
	obj.getUpdateDt(),obj.getUpdateTm(),obj.getStorageBrchNo(),obj.getStorageBrchName(),
	obj.getAcctBrchNo(),obj.getAcctBrchName());
}

public int deleteAcctBalanceHis(AcctBalanceHis obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacct_balance_his where his_id=?",obj.getHisId());
}

public int deleteAcctBalanceHis(String hisId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacct_balance_his where his_id=?",hisId);
}

public int modifyAcctBalanceHis(AcctBalanceHis obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacct_balance_his set busi_dt=?,info_id=?,rgct_id=?,bill_no=?,start_dt=?,end_dt=?,cust_no=?,cust_name=?,prod_no=?,status=?,create_dt=?,create_tm=?,update_dt=?,update_tm=?,storage_brch_no=?,storage_brch_name=?,acct_brch_no=?,acct_brch_name=? where his_id=?",
	obj.getBusiDt(),obj.getInfoId(),obj.getRgctId(),obj.getBillNo(),
	obj.getStartDt(),obj.getEndDt(),obj.getCustNo(),obj.getCustName(),
	obj.getProdNo(),obj.getStatus(),obj.getCreateDt(),obj.getCreateTm(),
	obj.getUpdateDt(),obj.getUpdateTm(),obj.getStorageBrchNo(),obj.getStorageBrchName(),
	obj.getAcctBrchNo(),obj.getAcctBrchName(),obj.getHisId());
}

public int modifyAcctBalanceHis(AcctBalanceHis obj,String hisId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacct_balance_his set busi_dt=?,info_id=?,rgct_id=?,bill_no=?,start_dt=?,end_dt=?,cust_no=?,cust_name=?,prod_no=?,status=?,create_dt=?,create_tm=?,update_dt=?,update_tm=?,storage_brch_no=?,storage_brch_name=?,acct_brch_no=?,acct_brch_name=? where his_id=?",
	obj.getBusiDt(),obj.getInfoId(),obj.getRgctId(),obj.getBillNo(),
	obj.getStartDt(),obj.getEndDt(),obj.getCustNo(),obj.getCustName(),
	obj.getProdNo(),obj.getStatus(),obj.getCreateDt(),obj.getCreateTm(),
	obj.getUpdateDt(),obj.getUpdateTm(),obj.getStorageBrchNo(),obj.getStorageBrchName(),
	obj.getAcctBrchNo(),obj.getAcctBrchName(),hisId);
}

public AcctBalanceHis getAcctBalanceHis(String hisId) throws SQLException {
	IDB session = DBFactory.getDB();
	AcctBalanceHis obj = (AcctBalanceHis)session.getObject("select * from tacct_balance_his where his_id=?",AcctBalanceHis.class,hisId);
	return obj;
}

}
