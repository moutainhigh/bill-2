/********************************************
* 文件名称: CustLimitFlowDao.java
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
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.CustLimitFlow;
public class CustLimitFlowDao{

public int addCustLimitFlow(CustLimitFlow obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tcust_limit_flow(flow_no,oper_mx_id,oper_type,oper_class,oper_date,oper_user,oper_money,oper_cust_no,oper_cust_name)values(?,?,?,?,?,?,?,?,?)",
	obj.getFlowNo(),obj.getOperMxId(),obj.getOperType(),obj.getOperClass(),obj.getOperDate(),
	obj.getOperUser(),obj.getOperMoney(),obj.getOperCustNo(),obj.getOperCustName());
}

public int deleteCustLimitFlow(CustLimitFlow obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_limit_flow where flow_no=?",obj.getFlowNo());
}

public int deleteCustLimitFlow(String flowNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_limit_flow where flow_no=?",flowNo);
}

public int modifyCustLimitFlow(CustLimitFlow obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_limit_flow set oper_mx_id=?,oper_type=?,oper_class=?,oper_date=?,oper_user=?,oper_money=?,oper_cust_no=?,oper_cust_name=? where flow_no=?",
	obj.getOperMxId(),obj.getOperType(),obj.getOperClass(),obj.getOperDate(),
	obj.getOperUser(),obj.getOperMoney(),obj.getOperCustNo(),obj.getOperCustName(),
	obj.getFlowNo());
}

public int modifyCustLimitFlow(CustLimitFlow obj,String flowNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_limit_flow set oper_mx_id=?,oper_type=?,oper_class=?,oper_date=?,oper_user=?,oper_money=?,oper_cust_no=?,oper_cust_name=? where flow_no=?",
	obj.getOperMxId(),obj.getOperType(),obj.getOperClass(),obj.getOperDate(),
	obj.getOperUser(),obj.getOperMoney(),obj.getOperCustNo(),obj.getOperCustName(),
	flowNo);
}

public CustLimitFlow getCustLimitFlow(String flowNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustLimitFlow obj = (CustLimitFlow)session.getObject("select * from tcust_limit_flow where flow_no=?",CustLimitFlow.class,flowNo);
	return obj;
}

}
