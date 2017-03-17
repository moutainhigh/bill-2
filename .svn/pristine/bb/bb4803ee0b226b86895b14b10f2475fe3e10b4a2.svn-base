/********************************************
* 文件名称: CustLimitDao.java
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
import com.herongtech.console.domain.bean.CustLimit;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class CustLimitDao{

public int addCustLimit(CustLimit obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tcust_limit(cust_no,cust_name,cust_type,balance_class,total_balance,able_balance,used_balance,effective_date,is_circle,status,oper_date,oper_user)values(?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getCustNo(),obj.getCustName(),obj.getCustType(),obj.getBalanceClass(),obj.getTotalBalance(),
	obj.getAbleBalance(),obj.getUsedBalance(),obj.getEffectiveDate(),obj.getIsCircle(),
	obj.getStatus(),obj.getOperDate(),obj.getOperUser());
}

public int deleteCustLimit(CustLimit obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_limit where cust_no=?",obj.getCustNo());
}

public int deleteCustLimit(String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_limit where cust_no=?",custNo);
}

public int modifyCustLimit(CustLimit obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_limit set cust_name=?,cust_type=?,balance_class=?,total_balance=?,able_balance=?,used_balance=?,effective_date=?,is_circle=?,status=?,oper_date=?,oper_user=? where cust_no=?",
	obj.getCustName(),obj.getCustType(),obj.getBalanceClass(),obj.getTotalBalance(),
	obj.getAbleBalance(),obj.getUsedBalance(),obj.getEffectiveDate(),obj.getIsCircle(),
	obj.getStatus(),obj.getOperDate(),obj.getOperUser(),obj.getCustNo());
}

public int modifyCustLimit(CustLimit obj,String custNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_limit set cust_name=?,cust_type=?,balance_class=?,total_balance=?,able_balance=?,used_balance=?,effective_date=?,is_circle=?,status=?,oper_date=?,oper_user=? where cust_no=?",
	obj.getCustName(),obj.getCustType(),obj.getBalanceClass(),obj.getTotalBalance(),
	obj.getAbleBalance(),obj.getUsedBalance(),obj.getEffectiveDate(),obj.getIsCircle(),
	obj.getStatus(),obj.getOperDate(),obj.getOperUser(),custNo);
}

public CustLimit getCustLimit(String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustLimit obj = (CustLimit)session.getObject("select * from tcust_limit where cust_no=?",CustLimit.class,custNo);
	return obj;
}

public List<CustLimit> getCustLimitList(Page page) throws SQLException {
	IDB session = DBFactory.getDB();
	int count = 0;
	count = session.account("select count(*) from tcust_limit");
	page.setTotalResult(count);
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	return session.getObjectListForPage("select * from tcust_limit",CustLimit.class,startIndex,page.getShowCount());
}

}
