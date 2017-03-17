/********************************************
* 文件名称: DayCheckDatahzDao.java
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
import com.herongtech.console.domain.bean.DayCheckDatahz;
public class DayCheckDatahzDao{

public int addDayCheckDatahz(DayCheckDatahz obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tday_check_datahz(id,draft_log_id,bank_no,draft_no,ecds_num,bbsp_num,check_date,send_or_receive,inaccurary)values(?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getDraftLogId(),obj.getBankNo(),obj.getDraftNo(),obj.getEcdsNum(),
	obj.getBbspNum(),obj.getCheckDate(),obj.getSendOrReceive(),obj.getInaccurary());
}

public int deleteDayCheckDatahz(DayCheckDatahz obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tday_check_datahz where bank_no=? and draft_no=? and check_date=?",obj.getBankNo(),obj.getDraftNo(),obj.getCheckDate());
}

public int deleteDayCheckDatahz(String bankNo,String draftNo,String checkDate) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tday_check_datahz where bank_no=? and draft_no=? and check_date=?",bankNo,draftNo,checkDate);
}

public int modifyDayCheckDatahz(DayCheckDatahz obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tday_check_datahz set id=?,draft_log_id=?,ecds_num=?,bbsp_num=?,send_or_receive=?,inaccurary=? where bank_no=? and draft_no=? and check_date=?",
	obj.getId(),obj.getDraftLogId(),obj.getEcdsNum(),obj.getBbspNum(),
	obj.getSendOrReceive(),obj.getInaccurary(),obj.getBankNo(),obj.getDraftNo(),
	obj.getCheckDate());
}

public int modifyDayCheckDatahz(DayCheckDatahz obj,String bankNo,String draftNo,String checkDate) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tday_check_datahz set id=?,draft_log_id=?,ecds_num=?,bbsp_num=?,send_or_receive=?,inaccurary=? where bank_no=? and draft_no=? and check_date=?",
	obj.getId(),obj.getDraftLogId(),obj.getEcdsNum(),obj.getBbspNum(),
	obj.getSendOrReceive(),obj.getInaccurary(),bankNo,draftNo,
	checkDate);
}

public DayCheckDatahz getDayCheckDatahz(String bankNo,String draftNo,String checkDate) throws SQLException {
	IDB session = DBFactory.getDB();
	DayCheckDatahz obj = (DayCheckDatahz)session.getObject("select * from tday_check_datahz where bank_no=? and draft_no=? and check_date=?",DayCheckDatahz.class,bankNo,draftNo,checkDate);
	return obj;
}

}
