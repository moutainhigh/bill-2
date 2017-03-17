/********************************************
* 文件名称: CustManageDao.java
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
import com.herongtech.console.domain.bean.CustManage;
public class CustManageDao{

public int addCustManage(CustManage obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tcust_manage(cust_manager_no,cust_manager_name,status,manage_type,branch_no,del_flag,del_dt,del_time,del_oper_name,del_oper_no,create_dt,create_time,create_oper_name,create_oper_no,dept_name,dept_no)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getCustManagerNo(),obj.getCustManagerName(),obj.getStatus(),obj.getManageType(),
	obj.getBranchNo(),obj.getDelFlag(),obj.getDelDt(),obj.getDelTime(),
	obj.getDelOperName(),obj.getDelOperNo(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getCreateOperName(),obj.getCreateOperNo(),obj.getDeptName(),obj.getDeptNo());
}

public int deleteCustManage(CustManage obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_manage where cust_manager_no=?",obj.getCustManagerNo());
}

public int deleteCustManage(String custManagerNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_manage where cust_manager_no=?",custManagerNo);
}

public int modifyCustManage(CustManage obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_manage set cust_manager_name=?,status=?,manage_type=?,branch_no=?,del_flag=?,del_dt=?,del_time=?,del_oper_name=?,del_oper_no=?,create_oper_name=?,create_oper_no=?,dept_name=?,dept_no=? where cust_manager_no=?",
	obj.getCustManagerName(),obj.getStatus(),obj.getManageType(),
	obj.getBranchNo(),obj.getDelFlag(),obj.getDelDt(),obj.getDelTime(),
	obj.getDelOperName(),obj.getDelOperNo(),
	obj.getCreateOperName(),obj.getCreateOperNo(),obj.getDeptName(),obj.getDeptNo(),
	obj.getCustManagerNo());
}

public int modifyCustManage(CustManage obj,String custManagerNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_manage set cust_manager_name=?,status=?,manage_type=?,branch_no=?,del_flag=?,del_dt=?,del_time=?,del_oper_name=?,del_oper_no=?,create_oper_name=?,create_oper_no=?,dept_name=?,dept_no=? where cust_manager_no=?",
	obj.getCustManagerName(),obj.getStatus(),obj.getManageType(),
	obj.getBranchNo(),obj.getDelFlag(),obj.getDelDt(),obj.getDelTime(),
	obj.getDelOperName(),obj.getDelOperNo(),
	obj.getCreateOperName(),obj.getCreateOperNo(),obj.getDeptName(),obj.getDeptNo(),
	custManagerNo);
}

public CustManage getCustManage(String custManagerNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustManage obj = (CustManage)session.getObject("select * from tcust_manage where cust_manager_no=?",CustManage.class,custManagerNo);
	return obj;
}
public CustManage getCustManageByDeptNo(String deptNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustManage obj = (CustManage)session.getObject("select * from tcust_manage where dept_no=?",CustManage.class,deptNo);
	return obj;
}

}
