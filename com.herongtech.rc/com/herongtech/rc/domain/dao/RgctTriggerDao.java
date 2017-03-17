/********************************************
* 文件名称: RgctTriggerDao.java
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

import java.sql.SQLException;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctTrigger;
public class RgctTriggerDao{

public int addRgctTrigger(RgctTrigger obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trgct_trigger(id,draft_type,draft_type_cn,method_name,method_cn_name,spring_id,spring_desc)values(?,?,?,?,?,?,?)",
	obj.getId(),obj.getDraftType(),obj.getDraftTypeCn(),obj.getMethodName(),obj.getMethodCnName(),
	obj.getSpringId(),obj.getSpringDesc());
}

public int deleteRgctTrigger(RgctTrigger obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trgct_trigger where id=?",obj.getId());
}

public int deleteRgctTrigger(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trgct_trigger where id=?",id);
}

public int modifyRgctTrigger(RgctTrigger obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_trigger set draft_type=?,draft_type_cn=?,method_name=?,method_cn_name=?,spring_id=?,spring_desc=? where id=?",
	obj.getDraftType(),obj.getDraftTypeCn(),obj.getMethodName(),obj.getMethodCnName(),
	obj.getSpringId(),obj.getSpringDesc(),obj.getId());
}

public int modifyRgctTrigger(RgctTrigger obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_trigger set draft_type=?,draft_type_cn=?,method_name=?,method_cn_name=?,spring_id=?,spring_desc=? where id=?",
	obj.getDraftType(),obj.getDraftTypeCn(),obj.getMethodName(),obj.getMethodCnName(),
	obj.getSpringId(),obj.getSpringDesc(),id);
}

public RgctTrigger getRgctTrigger(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctTrigger obj = (RgctTrigger)session.getObject("select * from trgct_trigger where id=?",RgctTrigger.class,id);
	return obj;
}

public RgctTrigger getRgctTrigger(String draftType,String methodName) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctTrigger obj = (RgctTrigger)session.getObject("select * from trgct_trigger where  draft_type=? and method_name=? ",RgctTrigger.class,draftType,methodName);
	return obj;
}

}
