package com.herongtech.console.service.common.dayend;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.common.bean.TaskLog;
import com.herongtech.console.domain.common.bean.TaskPool;
import com.herongtech.console.domain.common.bean.TaskSearchBean;
import com.herongtech.console.domain.common.dao.TaskLogDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.interfaces.ITaskLogService;
import com.herongtech.console.service.interfaces.ISequenceService;

public class TaskLogService implements ITaskLogService {
	TaskLogDao tasklogDao = new TaskLogDao();
	public List<TaskLog> getTaskLogList(TaskSearchBean bean,Page page) throws SQLException{
		return tasklogDao.getTaskLogList(bean,page);
	}
	
	public void addTaskLog(TaskLog log) throws SQLException{
		tasklogDao.addTaskLog(log);
	}
	
	public void delTaskLog(TaskLog log) throws SQLException{
		tasklogDao.deleteTaskLog(log);
	}
	public void addTaskLogByTaskPool(TaskPool pool) throws Exception{
		TaskLog log = new TaskLog();
		BeanUtils.copyProperties(log, pool);
		ISequenceService sequenceService = ServiceFactory.getSequenceService();
		log.setId(sequenceService.getTaskLogId());
		log.setWorkday(DateTimeUtil.getLastWorkdayString());
		this.addTaskLog(log);
	}

}
