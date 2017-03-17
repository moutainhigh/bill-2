package com.herongtech.console.service.common.audit.interfaces;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.audit.bean.AuditTaskSearchBean;


public interface IAuditTaskService {

    
    /**
     * 通过searchBean 查询审批任务
     * @param page
     * @param searchBean
     * @return
     * @throws Exception
     */
    public List<AuditTask> getAuditTaskListBySearchBean(Page page,AuditTaskSearchBean searchBean)throws Exception;
    
    /**
     * 开启审批任务，用于产品提交审批的处理接口
     *
     * @param auditTask 审批任务对象
     * @throws Exception
     */
    public void addAuditTask(AuditTask auditTask) throws Exception;
    
    
    /**
     * 撤销审批任务
     *  <br>当产品撤销审批，审批任务需要强制终止
     *  <br>只有审批还没开始，产品才能撤销审批
     * @param auditTask 审批任务对象
     * @throws Exception
     */
    public void cancelAuditTask(AuditTask auditTask) throws Exception;
    
    /**
     * 获取审批任务信息
     * @param atId 审批任务id
     * @return
     * @throws Exception
     */
    public AuditTask queryAuditTaskInfo(String atId) throws Exception;
    
    /**
     * 获取当前节点操作者列表
     *
     * @param requestDto  服务请求对象
     * @param atId 审批任务id
     * @return
     * @throws Exception
     */
    public List queryHandlersList(String atId) throws Exception;
    
    
    
    
    
}
