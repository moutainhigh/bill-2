/********************************************
* 文件名称: RoleUserDao.java
* 系统名称: 合融基础技术平台V3.0
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
import com.herongtech.console.domain.bean.RoleUser;
public class RoleUserDao{

public int addRoleUser(RoleUser obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trole_user(id,user_id,role_code,right_flag,remark)values(?,?,?,?,?)",
	obj.getId(),obj.getUserId(),obj.getRoleCode(),obj.getRightFlag(),obj.getRemark());
}

public int deleteRoleUser(RoleUser obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trole_user where user_id=? and role_code=? and right_flag=?",obj.getUserId(),obj.getRoleCode(),obj.getRightFlag());
}

public int deleteRoleUser(String userId,String roleCode,String rightFlag) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trole_user where user_id=? and role_code=? and right_flag=?",userId,roleCode,rightFlag);
}

public int modifyRoleUser(RoleUser obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trole_user set id=?,remark=? where user_id=? and role_code=? and right_flag=?",
	obj.getId(),obj.getRemark(),obj.getUserId(),obj.getRoleCode(),
	obj.getRightFlag());
}

public int modifyRoleUser(RoleUser obj,String userId,String roleCode,String rightFlag) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trole_user set id=?,remark=? where user_id=? and role_code=? and right_flag=?",
	obj.getId(),obj.getRemark(),userId,roleCode,
	rightFlag);
}

public RoleUser getRoleUser(String userId,String roleCode,String rightFlag) throws SQLException {
	IDB session = DBFactory.getDB();
	RoleUser obj = (RoleUser)session.getObject("select * from trole_user where user_id=? and role_code=? and right_flag=?",RoleUser.class,userId,roleCode,rightFlag);
	return obj;
}

}
