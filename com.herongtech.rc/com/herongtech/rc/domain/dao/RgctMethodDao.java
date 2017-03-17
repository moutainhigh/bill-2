/********************************************
* 文件名称: RgctMethodDao.java
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
package com.herongtech.rc.domain.dao;

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctMethod;

import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;

public class RgctMethodDao{

public int addRgctMethod(RgctMethod obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tRGCT_METHOD(id,sub_system,interface_name,method_name,method_cn_name,is_check_status,is_check_del,is_add_endorse,is_back_history,is_add_obligee)values(?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getSubSystem(),obj.getInterfaceName(),obj.getMethodName(),obj.getMethodCnName(),
	obj.getIsCheckStatus(),obj.getIsCheckDel(),obj.getIsAddEndorse(),obj.getIsBackHistory(),
	obj.getIsAddObligee());
}

public int deleteRgctMethod(RgctMethod obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tRGCT_METHOD where id=?",obj.getId());
}

public int deleteRgctMethod(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tRGCT_METHOD where id=?",id);
}

public int modifyRgctMethod(RgctMethod obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tRGCT_METHOD set sub_system=?,interface_name=?,method_name=?,method_cn_name=?,is_check_status=?,is_check_del=?,is_add_endorse=?,is_back_history=?,is_add_obligee=? where id=?",
	obj.getSubSystem(),obj.getInterfaceName(),obj.getMethodName(),obj.getMethodCnName(),
	obj.getIsCheckStatus(),obj.getIsCheckDel(),obj.getIsAddEndorse(),obj.getIsBackHistory(),
	obj.getIsAddObligee(),obj.getId());
}

public int modifyRgctMethod(RgctMethod obj,Long id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tRGCT_METHOD set sub_system=?,interface_name=?,method_name=?,method_cn_name=?,is_check_status=?,is_check_del=?,is_add_endorse=?,is_back_history=?,is_add_obligee=? where id=?",
	obj.getSubSystem(),obj.getInterfaceName(),obj.getMethodName(),obj.getMethodCnName(),
	obj.getIsCheckStatus(),obj.getIsCheckDel(),obj.getIsAddEndorse(),obj.getIsBackHistory(),
	obj.getIsAddObligee(),id);
}

public RgctMethod getRgctMethod(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctMethod obj = (RgctMethod)session.getObject("select * from tRGCT_METHOD where id=?",RgctMethod.class,id);
	return obj;
}

public RgctMethod getRgctMethod(String interfaceName, String methodName) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctMethod obj = (RgctMethod)session.getObject("select * from tRGCT_METHOD where  interface_name=? and method_name=?", 
			RgctMethod.class,  interfaceName, methodName);
	return obj;
}


public RgctMethod getRgctMethod(String subSystem, String interfaceName, String methodName) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctMethod obj = (RgctMethod)session.getObject("select * from tRGCT_METHOD where sub_system=? and interface_name=? and method_name=?", 
			RgctMethod.class, subSystem, interfaceName, methodName);
	return obj;
}



}
