/********************************************
* 文件名称: CustInfoDao.java
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
import com.herongtech.console.domain.bean.CustInfo;
public class CustInfoDao{

public int addCustInfo(CustInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tcust_info(id,cust_name,cust_type,cust_no,loan_no,org_code,flag,create_dt,create_time,update_dt,update_time,partner_type,address,credit_agency,credit_duedt,credit_rate)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getCustName(),obj.getCustType(),obj.getCustNo(),obj.getLoanNo(),
	obj.getOrgCode(),obj.getFlag(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getUpdateDt(),obj.getUpdateTime(),obj.getPartnerType(),obj.getAddress(),
	obj.getCreditAgency(),obj.getCreditDuedt(),obj.getCreditRate());
}

public int deleteCustInfo(CustInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_info where cust_no=?",obj.getCustNo());
}

public int deleteCustInfo(String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tcust_info where cust_no=?",custNo);
}

public int modifyCustInfo(CustInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_info set id=?,cust_name=?,cust_type=?,loan_no=?,org_code=?,flag=?,create_dt=?,create_time=?,update_dt=?,update_time=?,partner_type=?,address=?,credit_agency=?,credit_duedt=?,credit_rate=? where cust_no=?",
	obj.getId(),obj.getCustName(),obj.getCustType(),obj.getLoanNo(),
	obj.getOrgCode(),obj.getFlag(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getUpdateDt(),obj.getUpdateTime(),obj.getPartnerType(),obj.getAddress(),
	obj.getCreditAgency(),obj.getCreditDuedt(),obj.getCreditRate(),obj.getCustNo());
}

public int modifyCustInfo(CustInfo obj,String custNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tcust_info set id=?,cust_name=?,cust_type=?,loan_no=?,org_code=?,flag=?,create_dt=?,create_time=?,update_dt=?,update_time=?,partner_type=?,address=?,credit_agency=?,credit_duedt=?,credit_rate=? where cust_no=?",
	obj.getId(),obj.getCustName(),obj.getCustType(),obj.getLoanNo(),
	obj.getOrgCode(),obj.getFlag(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getUpdateDt(),obj.getUpdateTime(),obj.getPartnerType(),obj.getAddress(),
	obj.getCreditAgency(),obj.getCreditDuedt(),obj.getCreditRate(),custNo);
}

public CustInfo getCustInfo(String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustInfo obj = (CustInfo)session.getObject("select * from tcust_info where cust_no=?",CustInfo.class,custNo);
	return obj;
}

public CustInfo getCustInfoByOrgNo(String orgNo) throws SQLException {
	IDB session = DBFactory.getDB();
	CustInfo obj = (CustInfo)session.getObject("select * from tcust_info where org_code=?",CustInfo.class,orgNo);
	return obj;
}

public CustInfo getCustInfoByOrgNoAndName(String orgNo,String custName) throws SQLException {
	IDB session = DBFactory.getDB();
	CustInfo obj = (CustInfo)session.getObject("select * from tcust_info where org_code=? and cust_name=?",CustInfo.class,orgNo,custName);
	return obj;
}

}
