/********************************************
* 文件名称: MonthCheckDataDao.java
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
import com.herongtech.console.domain.bean.MonthCheckData;
public class MonthCheckDataDao{

public int addMonthCheckData(MonthCheckData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tmonth_check_data(id,check_date,bill_no,draft_log_id,error_code)values(?,?,?,?,?)",
	obj.getId(),obj.getCheckDate(),obj.getBillNo(),obj.getDraftLogId(),obj.getErrorCode());
}

public int deleteMonthCheckData(MonthCheckData obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tmonth_check_data where check_date=? and draft_log_id=?",obj.getCheckDate(),obj.getDraftLogId());
}

public int deleteMonthCheckData(String checkDate,Long draftLogId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tmonth_check_data where check_date=? and draft_log_id=?",checkDate,draftLogId);
}

public int modifyMonthCheckData(MonthCheckData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tmonth_check_data set id=?,bill_no=?,error_code=? where check_date=? and draft_log_id=?",
	obj.getId(),obj.getBillNo(),obj.getErrorCode(),obj.getCheckDate(),
	obj.getDraftLogId());
}

public int modifyMonthCheckData(MonthCheckData obj,String checkDate,Long draftLogId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tmonth_check_data set id=?,bill_no=?,error_code=? where check_date=? and draft_log_id=?",
	obj.getId(),obj.getBillNo(),obj.getErrorCode(),checkDate,
	draftLogId);
}

public MonthCheckData getMonthCheckData(String checkDate,Long draftLogId) throws SQLException {
	IDB session = DBFactory.getDB();
	MonthCheckData obj = (MonthCheckData)session.getObject("select * from tmonth_check_data where check_date=? and draft_log_id=?",MonthCheckData.class,checkDate,draftLogId);
	return obj;
}

}
