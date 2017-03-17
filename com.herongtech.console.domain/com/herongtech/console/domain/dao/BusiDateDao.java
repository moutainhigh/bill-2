/********************************************
* 文件名称: BusiDateDao.java
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
import com.herongtech.console.domain.bean.BusiDate;
public class BusiDateDao{

public int addBusiDate(BusiDate obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tBUSI_DATE(id,sys_bank_no,workday,host_check_date,trans_check_date,sys_status,flag1,flag2)values(1,?,?,?,?,?,?,?)",
	obj.getId(),obj.getSysBankNo(),obj.getWorkday(),obj.getHostCheckDate(),obj.getTransCheckDate(),
	obj.getSysStatus(),obj.getFlag1(),obj.getFlag2());
}

public int deleteBusiDate(BusiDate obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tBUSI_DATE where sys_bank_no=?",obj.getSysBankNo());
}

public int deleteBusiDate(String sysBankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tBUSI_DATE where sys_bank_no=?",sysBankNo);
}

public int modifyBusiDate(BusiDate obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tBUSI_DATE set id=?,workday=?,host_check_date=?,trans_check_date=?,sys_status=?,flag1=?,flag2=? where sys_bank_no=?",
	obj.getId(),obj.getWorkday(),obj.getHostCheckDate(),obj.getTransCheckDate(),
	obj.getSysStatus(),obj.getFlag1(),obj.getFlag2(),obj.getSysBankNo());
}

public int modifyBusiDate(BusiDate obj,String sysBankNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tBUSI_DATE set id=?,workday=?,host_check_date=?,trans_check_date=?,sys_status=?,flag1=?,flag2=? where sys_bank_no=?",
	obj.getId(),obj.getWorkday(),obj.getHostCheckDate(),obj.getTransCheckDate(),
	obj.getSysStatus(),obj.getFlag1(),obj.getFlag2(),sysBankNo);
}

public BusiDate getBusiDate(String sysBankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	BusiDate obj = (BusiDate)session.getObject("select * from tBUSI_DATE where sys_bank_no=?",BusiDate.class,sysBankNo);
	return obj;
}

}
