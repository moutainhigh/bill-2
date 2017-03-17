package com.herongtech.console.service.common.dayend;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.cache.SysParamCache;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.interfaces.IDayEndSchedulerService;
import com.herongtech.log.CommonLog;
import com.herongtech.schedule.AbstractScheduleProcessor;


public class BatchAutoProcessor extends AbstractScheduleProcessor{

    @Override
    public void execute() {
        // 获取作业调度逻辑业务类
        IDayEndSchedulerService scheduleService=ServiceFactory.getDayEndSchedulerService();
        try {
            // 判断是否日终开启 true-开启 false-未开启
            if (scheduleService.isStartBatch()) {
                // 日终作业调度
                scheduleService.schedulerTask();
            } else {
                // 判断是否自动清算 true--自动 false--手动
                if (scheduleService.isAuto()) {
                    // 获取日终定时时间
                    String startTime = scheduleService.getStartTime();
                    if ("0".equals(startTime)) {
                        CommonLog.getCommonLogCache().infoMessage("时间配置错误");
                    } else {
                        // 获取 日终清算日期 参数值
                        String batchBusiDt = SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.BATCH_BUSIDATE);
                        // 判断系统时间大于等于定时时间开启日终清算
                        if (isAutoStart(batchBusiDt, startTime)) {
                            // 开启日终清算
                            scheduleService.startBatch();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    /**
     * 是否自动清算开启
     * @param batchBusiDate  日终清算日期
     * @param startTime      自动清算开始时间
     * @return  true--是   false--否
     */
    private boolean isAutoStart(String batchBusiDate, String startTime) throws Exception{
        boolean flag = false;
        String batchDate = "";// 清算日期
        String batchFlag = "0";// 清算标志
       String workDay=DateTimeUtil.getWorkday();
        if (StringUtils.isEmpty(batchBusiDate)) {
            return flag;
        }

       
        batchDate = batchBusiDate.substring(0, batchBusiDate.indexOf("-"));
        batchFlag = batchBusiDate.substring(batchBusiDate.indexOf("-") + 1);

        //到了对应的时点 并且当前营业日不等于上一次清算日期
        if (DateTimeUtil.getTime().compareTo(startTime) >= 0
                && !workDay.equals(batchDate)) {
            /**日终完成     只有日终完成的才能自动开始下一个营业日的日终*/
            
            if (IConstants.DAY_END.BATCH_FINISH.equals(batchFlag)
                    && DateTimeUtil.get_YYYYMMDD_Date().equals(workDay)) {
                flag = true;
            }
        }
        return flag;
    }
    

}
