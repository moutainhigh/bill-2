package com.herongtech.console.service.common.dayend.interfaces;

import java.util.List;

import com.herongtech.console.domain.common.bean.TaskPool;


public interface ITaskPoolService {

    public void initBatch() throws Exception;
    
    
    /**
     * 根据作业类型获取已激活的作业列表
     * @param taskType
     * @return
     * @throws Exception
     */
    public List<TaskPool> getActiveTaskpools(String taskType) throws Exception;
    /**
     * 根据taskNo查询作业信息
     * @param taskNo
     * @return
     * @throws Exception
     */
    public TaskPool getTaskPoolByTaskNo(String taskNo) throws Exception;
}
