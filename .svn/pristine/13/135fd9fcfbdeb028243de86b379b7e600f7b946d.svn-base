/********************************************
* 文件名称: CustInfoAcctDao.java
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
package com.herongtech.console.domain.dao;

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import java.util.List;
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
public class CustInfoAcctDao{

public int addCustInfoAcct(CustInfoAcct obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tcust_info_acct(id,cust_no,acct_no,acct_type,acct_branch_no,acct_branch_name,open_date,open_time,acct_bank_no)values(?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getCustNo(),obj.getAcctNo(),obj.getAcctType(),obj.getAcctBranchNo(),
	obj.getAcctBranchName(),obj.getOpenDate(),obj.getOpenTime(),obj.getAcctBankNo());
}

public int deleteCustInfoAcct(CustInfoAcct obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_info_acct where cust_no=? and acct_no=?",obj.getCustNo(),obj.getAcctNo());
}

public int deleteCustInfoAcct(String custNo,String acctNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_info_acct where cust_no=? and acct_no=?",custNo,acctNo);
}

public int modifyCustInfoAcct(CustInfoAcct obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_info_acct set id=?,acct_type=?,acct_branch_no=?,acct_branch_name=?,open_date=?,open_time=?,acct_bank_no=? where cust_no=? and acct_no=?",
	obj.getId(),obj.getAcctType(),obj.getAcctBranchNo(),obj.getAcctBranchName(),
	obj.getOpenDate(),obj.getOpenTime(),obj.getAcctBankNo(),obj.getCustNo(),
	obj.getAcctNo());
}

public int modifyCustInfoAcct(CustInfoAcct obj,String custNo,String acctNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_info_acct set id=?,acct_type=?,acct_branch_no=?,acct_branch_name=?,open_date=?,open_time=?,acct_bank_no=? where cust_no=? and acct_no=?",
	obj.getId(),obj.getAcctType(),obj.getAcctBranchNo(),obj.getAcctBranchName(),
	obj.getOpenDate(),obj.getOpenTime(),obj.getAcctBankNo(),custNo,
	acctNo);
}

public CustInfoAcct getCustInfoAcct(String custNo,String acctNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustInfoAcct obj = (CustInfoAcct)session.getObject("select * from tcust_info_acct where cust_no=? and acct_no=?",CustInfoAcct.class,custNo,acctNo);
	return obj;
}

public CustInfoAcct getCustInfoAcct(String acctNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustInfoAcct obj = (CustInfoAcct)session.getObject("select * from tcust_info_acct where acct_no=?",CustInfoAcct.class,acctNo);
	return obj;
}

public CustInfoAcct getCustInfoAcctByCustNo(String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustInfoAcct obj = (CustInfoAcct)session.getObject("select * from tcust_info_acct where cust_no=?",CustInfoAcct.class,custNo);
	return obj;
}
public List<CustInfoAcct> getCustInfoAcctByCustNo1(String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	List<CustInfoAcct>  custInfoAcct= session.getObjectList("select * from tcust_info_acct where cust_no=?", CustInfoAcct.class,custNo);
	return custInfoAcct;
}

}
