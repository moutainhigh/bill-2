package com.herongtech.console.service.common.dayend.task;

import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.DayEndBaseService;
import com.herongtech.console.service.interfaces.IBusiDateService;
import com.herongtech.context.Context;
import com.herongtech.log.CommonLog;


/**
 * 营业日期切换
 *
 */
public class BusiDateChangeService extends DayEndBaseService {

    @Override
    public String getServiceId() {
        return "DAYEND-busiDateChange";
    }

    @Override
    protected void action(Context arg0) throws Exception {
        CommonLog.getCommonLogCache().infoMessage("切换营业日完成");
        IBusiDateService busiDateService=ServiceFactory.getBusiDateService();
        BusiDate busiDate=busiDateService.getDefaultBusiDate();
        busiDate.setWorkday(DateTimeUtil.getNextWorkdayString());
        busiDateService.modifyBusiDate(busiDate);
    }

    @Override
    public String getTransName() {
        return "营业日期切换";
    }

    @Override
    public String getTransVersion() {
        return "1.0";
    }

}
