/********************************************
* 文件名称: RgctStatusDao.java
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
import com.herongtech.rc.domain.bean.RgctStatus;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;

public class RgctStatusDao{

public int addRgctStatus(RgctStatus obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tRGCT_STATUS(id,method_id,before_status,after_status,run_status,param)values(?,?,?,?,?,?)",
	obj.getId(),obj.getMethodId(),obj.getBeforeStatus(),obj.getAfterStatus(),obj.getRunStatus(),
	obj.getParam());
}

public int deleteRgctStatus(RgctStatus obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tRGCT_STATUS where id=?", obj.getId());
}

public int modifyRgctStatus(RgctStatus obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tRGCT_STATUS set id=?,method_id=?,before_status=?,after_status=?,run_status=?,param=? where id=?",
	obj.getId(),obj.getMethodId(),obj.getBeforeStatus(),obj.getAfterStatus(),
	obj.getRunStatus(),obj.getParam(),obj.getId());
}

public List<RgctStatus> getRgctStatusList(long method_id, String param) throws SQLException {
	IDB session = DBFactory.getDB();
	List<RgctStatus> list=null;
	if(param!=null && !"".equals(param)){
		list = session.getObjectList("select * from tRGCT_STATUS where method_id=? and param=?", RgctStatus.class, method_id, param);
		
	}else{
		list = session.getObjectList("select * from tRGCT_STATUS where method_id=? ", RgctStatus.class, method_id);
	}
	return list;
}

public RgctStatus getRgctStatus(long method_id, String status) throws SQLException {
	IDB session = DBFactory.getDB();
	List<RgctStatus> list = session.getObjectList("select * from tRGCT_STATUS where method_id=? and before_Status=?", RgctStatus.class, method_id, status);
	return list.get(0);
}

public RgctStatus getRgctStatus(long method_id, String status, String param) throws SQLException {
	IDB session = DBFactory.getDB();
	List<RgctStatus> list = session.getObjectList("select * from tRGCT_STATUS where method_id=? and before_Status=? and param=?", RgctStatus.class, method_id,status, param);
	return list.get(0);
}

}
