/********************************************
* 文件名称: MonthBillRespInfoDao.java
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
import com.herongtech.console.domain.bean.MonthBillRespInfo;
public class MonthBillRespInfoDao{

public int addMonthBillRespInfo(MonthBillRespInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tmonth_bill_resp_info(id,draft_log_id,check_dt)values(?,?,?)",
	obj.getId(),obj.getDraftLogId(),obj.getCheckDt());
}

public int deleteMonthBillRespInfo(MonthBillRespInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tmonth_bill_resp_info where draft_log_id=? and check_dt=?",obj.getDraftLogId(),obj.getCheckDt());
}

public int deleteMonthBillRespInfo(String draftLogId,String checkDt) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tmonth_bill_resp_info where draft_log_id=? and check_dt=?",draftLogId,checkDt);
}

public int modifyMonthBillRespInfo(MonthBillRespInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tmonth_bill_resp_info set id=? where draft_log_id=? and check_dt=?",
	obj.getId(),obj.getDraftLogId(),obj.getCheckDt());
}

public int modifyMonthBillRespInfo(MonthBillRespInfo obj,String draftLogId,String checkDt) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tmonth_bill_resp_info set id=? where draft_log_id=? and check_dt=?",
	obj.getId(),draftLogId,checkDt);
}

public MonthBillRespInfo getMonthBillRespInfo(String draftLogId,String checkDt) throws SQLException {
	IDB session = DBFactory.getDB();
	MonthBillRespInfo obj = (MonthBillRespInfo)session.getObject("select * from tmonth_bill_resp_info where draft_log_id=? and check_dt=?",MonthBillRespInfo.class,draftLogId,checkDt);
	return obj;
}

}
