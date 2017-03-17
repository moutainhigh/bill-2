package com.herongtech.console.service.common.dayend.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.common.bean.TaskLog;
import com.herongtech.console.domain.common.bean.TaskPool;
import com.herongtech.console.domain.common.bean.TaskSearchBean;

public interface ITaskLogService {
	public List<TaskLog> getTaskLogList(TaskSearchBean bean,Page page) throws SQLException;
	public void addTaskLog(TaskLog log) throws SQLException;
	public void delTaskLog(TaskLog log) throws SQLException;
	public void addTaskLogByTaskPool(TaskPool pool) throws Exception;
}
