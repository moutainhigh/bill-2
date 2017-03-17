/********************************************
* 文件名称: DeptDao.java
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

import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.Dept;
public class DeptDao{

public int addDept(Dept obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tdept(dep_code,dep_name,short_name,parent_code,branch_code,dep_path,bank_no,remark)values(?,?,?,?,?,?,?,?)",
	obj.getDepCode(),obj.getDepName(),obj.getShortName(),obj.getParentCode(),obj.getBranchCode(),
	obj.getDepPath(),obj.getBankNo(),obj.getRemark());
}

public int deleteDept(Dept obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdept where dep_code=?",obj.getDepCode());
}

public int deleteDept(String depCode) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdept where dep_code=?",depCode);
}

public int modifyDept(Dept obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdept set dep_name=?,short_name=?,parent_code=?,branch_code=?,dep_path=?,bank_no=?,remark=? where dep_code=?",
	obj.getDepName(),obj.getShortName(),obj.getParentCode(),obj.getBranchCode(),
	obj.getDepPath(),obj.getBankNo(),obj.getRemark(),obj.getDepCode());
}

public int modifyDept(Dept obj,String depCode) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdept set dep_name=?,short_name=?,parent_code=?,branch_code=?,dep_path=?,bank_no=?,remark=? where dep_code=?",
	obj.getDepName(),obj.getShortName(),obj.getParentCode(),obj.getBranchCode(),
	obj.getDepPath(),obj.getBankNo(),obj.getRemark(),depCode);
}

public Dept getDept(String depCode) throws SQLException {
	IDB session = DBFactory.getDB();
	Dept obj = (Dept)session.getObject("select * from tdept where dep_code=?",Dept.class,depCode);
	return obj;
}

}
