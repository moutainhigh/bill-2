package com.herongtech.console.service.common.dayend.task;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.DayEndBaseService;
import com.herongtech.console.service.common.entitydraftregister.IEntityDraftRegisterService;
import com.herongtech.context.Context;
import com.herongtech.log.CommonLog;


public class EntityDraftRegService extends DayEndBaseService {

    @Override
    public String getServiceId() {
        return "DAYEND-entityDraftReg";
    }

    @Override
    protected void action(Context arg0) throws Exception {
    	CommonLog.getCommonLogCache().infoMessage("批量纸票登记任务开始=======");
    	IEntityDraftRegisterService entityRegister = ServiceFactory.getEntityDraftRegisterService();
    	entityRegister.entityRegister();
    	CommonLog.getCommonLogCache().infoMessage("批量纸票登记任务完成=======");

    }

    @Override
    public String getTransName() {
        return "纸票登记日终";
    }

    @Override
    public String getTransVersion() {
        return "1.0";
    }

}
