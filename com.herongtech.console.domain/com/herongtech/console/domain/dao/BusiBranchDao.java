/********************************************
* 文件名称: BusiBranchDao.java
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
import com.herongtech.console.domain.bean.BusiBranch;
public class BusiBranchDao{

public int addBusiBranch(BusiBranch obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tBUSI_BRANCH(brch_no,name,branch_level,status,parent_id,valid_date,invalid_date,syb_category)values(?,?,?,?,?,?,?,?)",
	/*obj.getId(),*/obj.getBrchNo(),obj.getName(),obj.getLevel(),obj.getStatus(),
	obj.getParentId(),obj.getValidDate(),obj.getInvalidDate(),obj.getSybCategory());
}

public int deleteBusiBranch(BusiBranch obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tBUSI_BRANCH where brch_no=?",obj.getBrchNo());
}

public int deleteBusiBranch(String brchNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tBUSI_BRANCH where brch_no=?",brchNo);
}

public int modifyBusiBranch(BusiBranch obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tBUSI_BRANCH set name=?,branch_level=?,status=?,parent_id=?,valid_date=?,invalid_date=?,syb_category=? where brch_no=?",
	obj.getName(),obj.getLevel(),obj.getStatus(),
	obj.getParentId(),obj.getValidDate(),obj.getInvalidDate(),obj.getSybCategory(),
	obj.getBrchNo());
}

public int modifyBusiBranch(BusiBranch obj,String brchNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tBUSI_BRANCH set name=?,branch_level=?,status=?,parent_id=?,valid_date=?,invalid_date=?,syb_category=? where brch_no=?",
	obj.getName(),obj.getLevel(),obj.getStatus(),
	obj.getParentId(),obj.getValidDate(),obj.getInvalidDate(),obj.getSybCategory(),
	brchNo);
}

public BusiBranch getBusiBranch(String brchNo) throws SQLException {
	IDB session = DBFactory.getDB();
	BusiBranch obj = (BusiBranch)session.getObject("select * from tBUSI_BRANCH where brch_no=?",BusiBranch.class,brchNo);
	return obj;
}

}
