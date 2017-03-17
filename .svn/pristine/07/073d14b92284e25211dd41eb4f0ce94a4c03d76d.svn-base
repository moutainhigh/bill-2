/********************************************
* 文件名称: DaySendDataDao.java
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
import com.herongtech.console.domain.bean.DaySendData;
public class DaySendDataDao{

public int addDaySendData(DaySendData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tday_send_data(id,check_num,bank_no,draft_log_id,check_date,in_out)values(?,?,?,?,?,?)",
	obj.getId(),obj.getCheckNum(),obj.getBankNo(),obj.getDraftLogId(),obj.getCheckDate(),
	obj.getInOut());
}

public int deleteDaySendData(DaySendData obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tday_send_data where draft_log_id=? and check_date=? and in_out=?",obj.getDraftLogId(),obj.getCheckDate(),obj.getInOut());
}

public int deleteDaySendData(Long draftLogId,String checkDate,String inOut) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tday_send_data where draft_log_id=? and check_date=? and in_out=?",draftLogId,checkDate,inOut);
}

public int modifyDaySendData(DaySendData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tday_send_data set id=?,check_num=?,bank_no=? where draft_log_id=? and check_date=? and in_out=?",
	obj.getId(),obj.getCheckNum(),obj.getBankNo(),obj.getDraftLogId(),
	obj.getCheckDate(),obj.getInOut());
}

public int modifyDaySendData(DaySendData obj,Long draftLogId,String checkDate,String inOut) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tday_send_data set id=?,check_num=?,bank_no=? where draft_log_id=? and check_date=? and in_out=?",
	obj.getId(),obj.getCheckNum(),obj.getBankNo(),draftLogId,
	checkDate,inOut);
}

public DaySendData getDaySendData(Long draftLogId,String checkDate,String inOut) throws SQLException {
	IDB session = DBFactory.getDB();
	DaySendData obj = (DaySendData)session.getObject("select * from tday_send_data where draft_log_id=? and check_date=? and in_out=?",DaySendData.class,draftLogId,checkDate,inOut);
	return obj;
}

}
