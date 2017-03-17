/********************************************
* 文件名称: ErrmsgDao.java
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
import com.herongtech.console.domain.bean.Errmsg;
public class ErrmsgDao{

public int addErrmsg(Errmsg obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into terrmsg(err_code,err_msg)values(?,?)",
	obj.getErrCode(),obj.getErrMsg());
}

public int deleteErrmsg(Errmsg obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from terrmsg where err_code=?",obj.getErrCode());
}

public int deleteErrmsg(String errCode) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from terrmsg where err_code=?",errCode);
}

public int modifyErrmsg(Errmsg obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update terrmsg set err_msg=? where err_code=?",
	obj.getErrMsg(),obj.getErrCode());
}

public int modifyErrmsg(Errmsg obj,String errCode) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update terrmsg set err_msg=? where err_code=?",
	obj.getErrMsg(),errCode);
}

public Errmsg getErrmsg(String errCode) throws SQLException {
	IDB session = DBFactory.getDB();
	Errmsg obj = (Errmsg)session.getObject("select * from terrmsg where err_code=?",Errmsg.class,errCode);
	return obj;
}

}
