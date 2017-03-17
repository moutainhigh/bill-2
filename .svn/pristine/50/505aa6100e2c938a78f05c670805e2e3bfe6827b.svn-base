package com.herongtech.console.service.common.dayend;

import java.util.List;

import com.herongtech.console.cache.SysParamCache;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.common.bean.TaskPool;
import com.herongtech.console.domain.common.dao.TaskPoolDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.interfaces.IDayEndSchedulerService;
import com.herongtech.console.service.common.dayend.interfaces.ITaskPoolService;


public class TaskPoolService implements ITaskPoolService{

    
    
    @Override
    public void initBatch() throws Exception{
        IDayEndSchedulerService scheduleService=ServiceFactory.getDayEndSchedulerService();
        TaskPoolDao dao=new TaskPoolDao();
        String BATCH_BUSIDATE =SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.BATCH_BUSIDATE);
        // 获取清算日期
        String BatchDate = BATCH_BUSIDATE.substring(0,BATCH_BUSIDATE.indexOf("-"));
        //该任务是初始化操作，所以需要做特殊处理，在此处登记日志
        List<TaskPool> poolList = this.getActiveTaskpools(IConstants.DAY_END.TASK_TYPE_INIT);
        for(TaskPool pool : poolList){
        	pool.setDealStatus(IConstants.DAY_END.DEAL_STATUS_FINISH);
        	pool.setEndTime(DateTimeUtil.get_hhmmss_time());
        	//记录日志
            ServiceFactory.getTaskLogService().addTaskLogByTaskPool(pool);
        }
        // 初始化作业调度表信息
        dao.updateTaskPoolInit();
        // 修改清算日期-清算标志为2
        ServiceFactory.getParamService().updateparam(IConstants.SysParamConstant.BATCH_BUSIDATE, BatchDate + "-2");
        // 修改系统状态为"正常"
        ServiceFactory.getParamService().updateparam(IConstants.SysParamConstant.BATCH_START_FLAG, IConstants.SWITCH_FLAG.CLOSE);

        // 停止日终服务
        scheduleService.stopBatch();
    }

    @Override
    public List<TaskPool> getActiveTaskpools(String taskType) throws Exception {
        TaskPoolDao dao=new TaskPoolDao();
        return dao.getActiveTaskpools(taskType);
    }
    public TaskPool getTaskPoolByTaskNo(String taskNo) throws Exception{
    	TaskPoolDao dao=new TaskPoolDao();
		return dao.getTaskPoolByTaskNo(taskNo);
    }

    
}
