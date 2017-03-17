/********************************************
* 文件名称: EcdsAuthlistDataDao.java
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
import com.herongtech.rc.domain.bean.EcdsAuthlistData;

import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
public class EcdsAuthlistDataDao{

public int addEcdsAuthlistData(EcdsAuthlistData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tECDS_AUTHLIST_DATA(actor_row_number,launch_purview_list,incept_purview_list)values(?,?,?)",
	obj.getActorRowNumber(),obj.getLaunchPurviewList(),obj.getInceptPurviewList());
}

/**清空表中数据
 * @throws SQLException */
public void truncateEcdsAuthlistData() throws SQLException{
	IDB session = DBFactory.getNewDB(); // 获取数据库连接
    String dialectName=session.getDialectName();
    if("DB2".equalsIgnoreCase(dialectName)){
    	session.execute("ALTER TABLE tECDS_AUTHLIST_DATA ACTIVATE NOT LOGGED INITIALLY WITH EMPTY TABLE;");
    }else if("ORACLE".equalsIgnoreCase(dialectName)){
    	session.execute("truncate table tECDS_AUTHLIST_DATA");
    }
	
}
public int deleteEcdsAuthlistData(EcdsAuthlistData obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tECDS_AUTHLIST_DATA where actor_row_number=?",obj.getActorRowNumber());
}

public int deleteEcdsAuthlistData(String actorRowNumber) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tECDS_AUTHLIST_DATA where actor_row_number=?",actorRowNumber);
}

public int modifyEcdsAuthlistData(EcdsAuthlistData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tECDS_AUTHLIST_DATA set launch_purview_list=?,incept_purview_list=? where actor_row_number=?",
	obj.getLaunchPurviewList(),obj.getInceptPurviewList(),obj.getActorRowNumber());
}

public int modifyEcdsAuthlistData(EcdsAuthlistData obj,String actorRowNumber) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tECDS_AUTHLIST_DATA set launch_purview_list=?,incept_purview_list=? where actor_row_number=?",
	obj.getLaunchPurviewList(),obj.getInceptPurviewList(),actorRowNumber);
}

public EcdsAuthlistData getEcdsAuthlistData(String actorRowNumber) throws SQLException {
	IDB session = DBFactory.getDB();
	EcdsAuthlistData obj = (EcdsAuthlistData)session.getObject("select * from tECDS_AUTHLIST_DATA where actor_row_number=?",EcdsAuthlistData.class,actorRowNumber);
	return obj;
}

}
