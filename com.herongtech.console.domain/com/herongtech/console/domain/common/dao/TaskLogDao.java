/********************************************
* 文件名称: TaskLogDao.java
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
package com.herongtech.console.domain.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.common.bean.TaskLog;
import com.herongtech.console.domain.common.bean.TaskSearchBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class TaskLogDao{

public int addTaskLog(TaskLog obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into TTASK_LOG(id,task_no,task_name,parent_task_no,task_type,repeat_flag,delay_flag,delay_time,deal_status,deal_msg,oper_no,repeat_num,begin_time,end_time,err_msg,effective_flag,workday)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getTaskNo(),obj.getTaskName(),obj.getParentTaskNo(),obj.getTaskType(),
	obj.getRepeatFlag(),obj.getDelayFlag(),obj.getDelayTime(),obj.getDealStatus(),
	obj.getDealMsg(),obj.getOperNo(),obj.getRepeatNum(),obj.getBeginTime(),
	obj.getEndTime(),obj.getErrMsg(),obj.getEffectiveFlag(),obj.getWorkday());
}

public int deleteTaskLog(TaskLog obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from TTASK_LOG where id=?",obj.getId());
}

public int deleteTaskLog(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from TTASK_LOG where id=?",id);
}

public int modifyTaskLog(TaskLog obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update TTASK_LOG set task_no=?,task_name=?,parent_task_no=?,task_type=?,repeat_flag=?,delay_flag=?,delay_time=?,deal_status=?,deal_msg=?,oper_no=?,repeat_num=?,begin_time=?,end_time=?,err_msg=?,effective_flag=?,workday=? where id=?",
	obj.getTaskNo(),obj.getTaskName(),obj.getParentTaskNo(),obj.getTaskType(),
	obj.getRepeatFlag(),obj.getDelayFlag(),obj.getDelayTime(),obj.getDealStatus(),
	obj.getDealMsg(),obj.getOperNo(),obj.getRepeatNum(),obj.getBeginTime(),
	obj.getEndTime(),obj.getErrMsg(),obj.getEffectiveFlag(),obj.getWorkday(),
	obj.getId());
}

public int modifyTaskLog(TaskLog obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update TTASK_LOG set task_no=?,task_name=?,parent_task_no=?,task_type=?,repeat_flag=?,delay_flag=?,delay_time=?,deal_status=?,deal_msg=?,oper_no=?,repeat_num=?,begin_time=?,end_time=?,err_msg=?,effective_flag=?,workday=? where id=?",
	obj.getTaskNo(),obj.getTaskName(),obj.getParentTaskNo(),obj.getTaskType(),
	obj.getRepeatFlag(),obj.getDelayFlag(),obj.getDelayTime(),obj.getDealStatus(),
	obj.getDealMsg(),obj.getOperNo(),obj.getRepeatNum(),obj.getBeginTime(),
	obj.getEndTime(),obj.getErrMsg(),obj.getEffectiveFlag(),obj.getWorkday(),
	id);
}

public TaskLog getTaskLog(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	TaskLog obj = (TaskLog)session.getObject("select * from TTASK_LOG where id=?",TaskLog.class,id);
	return obj;
}
/**
 * ***********************************************************************************************************/
public List<TaskLog> getTaskLogList(TaskSearchBean bean,Page page) throws SQLException{
	IDB session = DBFactory.getDB();
	QueryCondition qc = new QueryCondition();
	try {
		qc.initFromSearchBean(bean);
	} catch (Exception e) {
		e.printStackTrace();
	}
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	String baseSql = "select log.* from TTASK_LOG log ";
	String sql = qc.getAllSqlString(baseSql);
	page.setTotalResult(session.accountByList(qc.getCountSql("1"), qc.getParameterValues()));
	return session.getObjectListByListForPage(sql, TaskLog.class, startIndex, page.getShowCount(),qc.getParameterValues());
}
}

