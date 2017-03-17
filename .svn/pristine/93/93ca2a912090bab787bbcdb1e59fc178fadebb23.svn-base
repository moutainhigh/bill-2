package com.herongtech.console.service.common.dayend.task;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.DayEndBaseService;
import com.herongtech.console.service.common.dayend.interfaces.ITaskPoolService;
import com.herongtech.context.Context;
import com.herongtech.log.CommonLog;


/**
 * 作业初始化
 *
 */
public class BatchInitService extends DayEndBaseService {

    @Override
    public String getServiceId() {
        return "DAYEND-init";
    }

    @Override
    protected void action(Context arg0) throws Exception {
       ITaskPoolService taskPoolService=ServiceFactory.getTaskPoolService();
       taskPoolService.initBatch();
       CommonLog.getCommonLogCache().infoMessage("作业初始化完成");
    }

    
    
    @Override
    protected void afterAction(Context context1) throws Exception {
        //重写父类方法不做任何处理，因为该任务是最后一个任务 需重新初始化状态 而不是改为作业完成状态
    	
    }

    @Override
    public String getTransName() {
        return "作业初始化";
    }

    @Override
    public String getTransVersion() {
        return "1.0";
    }

}
