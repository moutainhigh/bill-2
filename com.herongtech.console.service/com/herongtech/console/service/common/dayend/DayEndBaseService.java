package com.herongtech.console.service.common.dayend;

import java.sql.SQLException;

import com.herongtech.baseservice.BaseService;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.common.bean.TaskPool;
import com.herongtech.console.domain.common.dao.TaskPoolDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;


public abstract class DayEndBaseService extends BaseService {

    /**
     * 作业执行异常将任务状态修改为正在处理中
     * 
     */
    @Override
    protected void packErrorAnswer(Context context, Exception e) {
        super.packErrorAnswer(context, e);
        TaskPool taskPool=(TaskPool) ContextUtil.getRequestData(context);
        taskPool.setDealStatus(IConstants.DAY_END.DEAL_STATUS_FAIL);
        taskPool.setEndTime(DateTimeUtil.get_hhmmss_time());
        try {
            new TaskPoolDao().modifyTaskPool(taskPool);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 作业执行前将任务状态修改为正在处理中
     * 
     */
    @Override
    protected void beforeAction(Context context1) throws Exception {
        super.beforeAction(context1);
        TaskPool taskPool=(TaskPool) ContextUtil.getRequestData(context1);
        taskPool.setDealStatus(IConstants.DAY_END.DEAL_STATUS_ING);
        taskPool.setBeginTime(DateTimeUtil.get_hhmmss_time());
        taskPool.setRepeatNum(taskPool.getRepeatNum()+1);
        new TaskPoolDao().modifyTaskPool(taskPool);
        ContextUtil.setRequestData(context1, taskPool);
    }

    /**
     * 作业执行完成后将任务状态修改为作业完成
     * 
     */
    @Override
    protected void afterAction(Context context1) throws Exception {
        super.afterAction(context1);
        TaskPool afterTaskPool=(TaskPool) ContextUtil.getRequestData(context1);
        afterTaskPool.setDealStatus(IConstants.DAY_END.DEAL_STATUS_FINISH);
        afterTaskPool.setEndTime(DateTimeUtil.get_hhmmss_time());
        //作业初始化节点不需要更新处理成功
        new TaskPoolDao().modifyTaskPool(afterTaskPool);
        //记录日志
        ServiceFactory.getTaskLogService().addTaskLogByTaskPool(afterTaskPool);
    }



}
