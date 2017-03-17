/********************************************
* 文件名称: DayDetailRespInfoDao.java
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
import com.herongtech.console.domain.bean.DayDetailRespInfo;
public class DayDetailRespInfoDao{

public int addDayDetailRespInfo(DayDetailRespInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tday_detail_resp_info(id,draft_log_id,check_dt,draft_head,msgid,status)values(?,?,?,?,?,?)",
	obj.getId(),obj.getDraftLogId(),obj.getCheckDt(),obj.getDraftHead(),obj.getMsgid(),
	obj.getStatus());
}

public int deleteDayDetailRespInfo(DayDetailRespInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tday_detail_resp_info where draft_log_id=? and check_dt=?",obj.getDraftLogId(),obj.getCheckDt());
}

public int deleteDayDetailRespInfo(Long draftLogId,String checkDt) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tday_detail_resp_info where draft_log_id=? and check_dt=?",draftLogId,checkDt);
}

public int modifyDayDetailRespInfo(DayDetailRespInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tday_detail_resp_info set id=?,draft_head=?,msgid=?,status=? where draft_log_id=? and check_dt=?",
	obj.getId(),obj.getDraftHead(),obj.getMsgid(),obj.getStatus(),
	obj.getDraftLogId(),obj.getCheckDt());
}

public int modifyDayDetailRespInfo(DayDetailRespInfo obj,Long draftLogId,String checkDt) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tday_detail_resp_info set id=?,draft_head=?,msgid=?,status=? where draft_log_id=? and check_dt=?",
	obj.getId(),obj.getDraftHead(),obj.getMsgid(),obj.getStatus(),
	draftLogId,checkDt);
}

public DayDetailRespInfo getDayDetailRespInfo(Long draftLogId,String checkDt) throws SQLException {
	IDB session = DBFactory.getDB();
	DayDetailRespInfo obj = (DayDetailRespInfo)session.getObject("select * from tday_detail_resp_info where draft_log_id=? and check_dt=?",DayDetailRespInfo.class,draftLogId,checkDt);
	return obj;
}

}
