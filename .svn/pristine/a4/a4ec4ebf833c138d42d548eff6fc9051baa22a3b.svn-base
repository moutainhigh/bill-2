/********************************************
* 文件名称: TaskPoolDao.java
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
import java.util.ArrayList;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.common.bean.TaskPool;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class TaskPoolDao{

public int addTaskPool(TaskPool obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into ttask_pool(task_no,task_name,parent_task_no,task_type,repeat_flag,delay_flag,delay_time,deal_status,deal_msg,oper_no,repeat_num,begin_time,end_time,err_msg,effective_flag)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getTaskNo(),obj.getTaskName(),obj.getParentTaskNo(),obj.getTaskType(),obj.getRepeatFlag(),
	obj.getDelayFlag(),obj.getDelayTime(),obj.getDealStatus(),obj.getDealMsg(),
	obj.getOperNo(),obj.getRepeatNum(),obj.getBeginTime(),obj.getEndTime(),
	obj.getErrMsg(),obj.getEffectiveFlag());
}

public int modifyTaskPool(TaskPool obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update ttask_pool set task_name=?,parent_task_no=?,task_type=?,repeat_flag=?,delay_flag=?,delay_time=?,deal_status=?,deal_msg=?,oper_no=?,repeat_num=?,begin_time=?,end_time=?,err_msg=?,effective_flag=? where task_no=?",
	obj.getTaskName(),obj.getParentTaskNo(),obj.getTaskType(),
	obj.getRepeatFlag(),obj.getDelayFlag(),obj.getDelayTime(),obj.getDealStatus(),
	obj.getDealMsg(),obj.getOperNo(),obj.getRepeatNum(),obj.getBeginTime(),
	obj.getEndTime(),obj.getErrMsg(),obj.getEffectiveFlag(),obj.getTaskNo());
}

public TaskPool getTaskPool() throws SQLException {
	IDB session = DBFactory.getDB();
	TaskPool obj = (TaskPool)session.getObject("select * from ttask_pool",TaskPool.class);
	return obj;
}

public int countNotDealStatusNum(String dealStatus)throws Exception{
   String sql = "SELECT count(1) FROM TTASK_POOL WHERE DEAL_STATUS!=? AND DEAL_STATUS!='0' AND EFFECTIVE_FLAG='1'";
   IDB session = DBFactory.getDB();
   return  session.account(sql, dealStatus);
}

public void updateAllTaskpoolsStatus(String dealStatus)throws Exception{
    IDB session = DBFactory.getDB();
    session.execute("UPDATE TTASK_POOL SET DEAL_STATUS=?",dealStatus);
}

/**
 * 统计 某个作业类型下有效作业数
 * @param taskType 作业类型
 * @return
 * @throws Exception 
 */
public int countTaskTypeNum(String taskType) throws Exception{
    String sql="SELECT COUNT(1) FROM TTASK_POOL WHERE TASK_TYPE=?";
    IDB session = DBFactory.getDB();
    return  session.account(sql, taskType);
}

public int countDealStatusNumForTaskType(String taskType,String dealStatus) throws Exception{
    String sql="SELECT count(1) FROM TTASK_POOL WHERE TASK_TYPE=? AND DEAL_STATUS=?";
    IDB session = DBFactory.getDB();
    return  session.account(sql, taskType,dealStatus);
}
/**
 * 根据作业类型获取已激活的作业列表
 * @param taskType
 * @return
 */
public List<TaskPool> getActiveTaskpools(String taskType) throws Exception{
    String sql="SELECT * FROM TTASK_POOL WHERE TASK_TYPE=?";
    IDB session = DBFactory.getDB();
    List list=new ArrayList();
    list.add(taskType);
    return session.getObjectListByList(sql, TaskPool.class,list);
}

/**
 * 根据作业编号查询作业信息
 * @param taskNo
 * @return
 */
public TaskPool getTaskPoolByTaskNo(String taskNo) throws Exception{
    String sql="SELECT * FROM TTASK_POOL WHERE TASK_NO=?";
    IDB session = DBFactory.getDB();
   
    return  session.getObject(sql, TaskPool.class, taskNo);
}

public void updateTaskPoolInit() throws Exception{
    String sql="update ttask_pool set deal_status='0',deal_msg='',oper_no='',repeat_num=0,begin_time='',end_time='',err_msg=''";
    IDB session = DBFactory.getDB();
    session.execute(sql);
}
/**
 * 查询所有的作业信息
 * @return
 * @throws Exception
 */
public List<TaskPool> getAllTaskPool() throws Exception{
	String sql="SELECT * FROM TTASK_POOL ";
    IDB session = DBFactory.getDB();
    return session.getObjectList(sql, TaskPool.class);
}
}
