package com.herongtech.console.service.common.dayend.interfaces;


public interface IDayEndSchedulerService {
    /**
     * 是否开启
     * @return
     * @throws Exception
     */
    public boolean isStartBatch()throws Exception;
    
    /**
     * 作业调度
     * 日终作业调度分三块，1：清算前、2：清算中:3：清算完成后初始化
     * 
     * @throws Exception
     */
    public void schedulerTask()throws Exception;
    
    /**
     * 是否自动清算
     * @return
     * @throws Exception
     */
    public boolean isAuto()throws Exception;
    
    /**
     * 获取日终任务定时开启时间
     * @return
     * @throws Exception
     */
    public String getStartTime()throws Exception;
    
    /**
     * 开启批量作业
     * @throws Exception
     */
    public void startBatch()throws Exception;
    
    /**
     * 停止批量作业
     * @throws Exception
     */
    public void stopBatch()throws Exception;
    /**
     * 执行某一个任务
     * @param taskNo
     * @throws Exception
     */
    public void runOneTask(String taskNo,String taskType) throws Exception ;

}
