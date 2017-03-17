/********************************************
* 文件名称: SerialnoDao.java
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
import com.herongtech.console.domain.bean.Serialno;
public class SerialnoDao{

public int addSerialno(Serialno obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tserialno(key_no,curr_value,inc,auto_reset,max_value,min_value)values(?,?,?,?,?,?)",
	obj.getKeyNo(),obj.getCurrValue(),obj.getInc(),obj.getAutoReset(),obj.getMaxValue(),
	obj.getMinValue());
}

public int deleteSerialno(Serialno obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tserialno where key_no=?",obj.getKeyNo());
}

public int deleteSerialno(String keyNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tserialno where key_no=?",keyNo);
}

public int modifySerialno(Serialno obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tserialno set curr_value=?,inc=?,auto_reset=?,max_value=?,min_value=? where key_no=?",
	obj.getCurrValue(),obj.getInc(),obj.getAutoReset(),obj.getMaxValue(),
	obj.getMinValue(),obj.getKeyNo());
}

public int modifySerialno(Serialno obj,String keyNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tserialno set curr_value=?,inc=?,auto_reset=?,max_value=?,min_value=? where key_no=?",
	obj.getCurrValue(),obj.getInc(),obj.getAutoReset(),obj.getMaxValue(),
	obj.getMinValue(),keyNo);
}

public Serialno getSerialno(String keyNo) throws SQLException {
	IDB session = DBFactory.getDB();
	Serialno obj = (Serialno)session.getObject("select * from tserialno where key_no=?",Serialno.class,keyNo);
	return obj;
}

}
