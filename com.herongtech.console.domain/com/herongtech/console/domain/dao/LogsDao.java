/********************************************
* 文件名称: LogsDao.java
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
import com.herongtech.console.domain.bean.Logs;
public class LogsDao{

public int addLogs(Logs obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tlogs(log_serial,user_id,menu_code,operation_code,terminal_no,op_date,op_time,auth_user,ip,summary,log_file,broswer)values(?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getLogSerial(),obj.getUserId(),obj.getMenuCode(),obj.getOperationCode(),obj.getTerminalNo(),
	obj.getOpDate(),obj.getOpTime(),obj.getAuthUser(),obj.getIp(),
	obj.getSummary(),obj.getLogFile(),obj.getBroswer());
}


public int modifyLogs(Logs obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tlogs set log_serial=?,user_id=?,menu_code=?,operation_code=?,terminal_no=?,op_date=?,op_time=?,auth_user=?,ip=?,summary=?,log_file=?,broswer=? ",
	obj.getLogSerial(),obj.getUserId(),obj.getMenuCode(),obj.getOperationCode(),
	obj.getTerminalNo(),obj.getOpDate(),obj.getOpTime(),obj.getAuthUser(),
	obj.getIp(),obj.getSummary(),obj.getLogFile(),obj.getBroswer());
}

public Logs getLogs() throws SQLException {
	IDB session = DBFactory.getDB();
	Logs obj = (Logs)session.getObject("select * from tlogs",Logs.class);
	return obj;
}

}
