/********************************************
* 文件名称: CustBalanceDao.java
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

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.CustBalance;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class CustBalanceDao{

public int addCustBalance(CustBalance obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tcust_balance(cust_no,cust_name,cust_type,balance_class,total_balance,able_balance,used_balance,effective_date,is_circle,status,oper_date,oper_user)values(?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getCustNo(),obj.getCustName(),obj.getCustType(),obj.getBalanceClass(),obj.getTotalBalance(),
	obj.getAbleBalance(),obj.getUsedBalance(),obj.getEffectiveDate(),obj.getIsCircle(),
	obj.getStatus(),obj.getOperDate(),obj.getOperUser());
}

public int deleteCustBalance(CustBalance obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_balance where cust_no=?",obj.getCustNo());
}

public int deleteCustBalance(String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_balance where cust_no=?",custNo);
}

public int modifyCustBalance(CustBalance obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_balance set cust_name=?,cust_type=?,balance_class=?,total_balance=?,able_balance=?,used_balance=?,effective_date=?,is_circle=?,status=?,oper_date=?,oper_user=? where cust_no=?",
	obj.getCustName(),obj.getCustType(),obj.getBalanceClass(),obj.getTotalBalance(),
	obj.getAbleBalance(),obj.getUsedBalance(),obj.getEffectiveDate(),obj.getIsCircle(),
	obj.getStatus(),obj.getOperDate(),obj.getOperUser(),obj.getCustNo());
}

public int modifyCustBalance(CustBalance obj,String custNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_balance set cust_name=?,cust_type=?,balance_class=?,total_balance=?,able_balance=?,used_balance=?,effective_date=?,is_circle=?,status=?,oper_date=?,oper_user=? where cust_no=?",
	obj.getCustName(),obj.getCustType(),obj.getBalanceClass(),obj.getTotalBalance(),
	obj.getAbleBalance(),obj.getUsedBalance(),obj.getEffectiveDate(),obj.getIsCircle(),
	obj.getStatus(),obj.getOperDate(),obj.getOperUser(),custNo);
}

public CustBalance getCustBalance(String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustBalance obj = (CustBalance)session.getObject("select * from tcust_balance where cust_no=?",CustBalance.class,custNo);
	return obj;
}

public List<CustBalance> getCustBalanceList(Page page) throws SQLException {
	IDB session = DBFactory.getDB();
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	return session.getObjectListForPage("select * from t_cust_balance",CustBalance.class,startIndex,page.getShowCount());
}
}
