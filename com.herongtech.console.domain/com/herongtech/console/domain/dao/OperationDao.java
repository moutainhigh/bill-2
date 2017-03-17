/********************************************
* 文件名称: OperationDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组fqq
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.dao;


import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.Operation;
public class OperationDao{

public int addOperation(Operation obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into toperation(operation_code,operation_name,operation_type,menu_code,status,auth_flag,logon_flag,remark)values(?,?,?,?,?,?,?,?)",
	obj.getOperationCode(),obj.getOperationName(),obj.getOperationType(),obj.getMenuCode(),obj.getStatus(),
	obj.getAuthFlag(),obj.getLogonFlag(),obj.getRemark());
}

public int deleteOperation(Operation obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from toperation where operation_code=? and menu_code=?",obj.getOperationCode(),obj.getMenuCode());
}

public int deleteOperation(String operationCode,String menuCode) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from toperation where operation_code=? and menu_code=?",operationCode,menuCode);
}

public int modifyOperation(Operation obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update toperation set operation_name=?,operation_type=?,status=?,auth_flag=?,logon_flag=?,remark=? where operation_code=? and menu_code=? ",
			obj.getOperationName(),obj.getOperationType(),obj.getStatus(),
			obj.getAuthFlag(),obj.getLogonFlag(),obj.getRemark(),obj.getOperationCode(),obj.getMenuCode());
}

public int modifyOperation(Operation obj,String operationCode,String menuCode) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update toperation set operation_name=?,operation_type=?,status=?,auth_flag=?,logon_flag=?,remark=? where operation_code=? and menu_code=?",
			obj.getOperationName(),obj.getOperationType(),obj.getStatus(),
			obj.getAuthFlag(),obj.getLogonFlag(),obj.getRemark(),operationCode,menuCode);
}

public Operation getOperation(String operationCode,String menuCode) throws SQLException {
	IDB session = DBFactory.getDB();
	Operation obj = (Operation)session.getObject("select * from toperation where operation_code=? and menu_code=?",Operation.class,operationCode,menuCode);
	return obj;
}

}
