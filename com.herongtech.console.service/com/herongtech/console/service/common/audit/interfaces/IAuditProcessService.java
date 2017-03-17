package com.herongtech.console.service.common.audit.interfaces;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditProcess;
import com.herongtech.console.domain.common.audit.bean.AuditProcessSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.bean.RemindDTO;


public interface IAuditProcessService {
    
    public AuditProcess getAuditProcessById(String apId)throws Exception;

    /**
     * 查询当前登录用户需审批的任务 首页提醒
     * @param userLoginInfo
     * @return
     * @throws Exception
     */
    public List<RemindDTO> queryAuditProcessByUserNo(UserLoginfo userLoginInfo)throws Exception;
    
    /**
     * 查询当前登录用户需审批的任务
     *
     * @param page 分页对象
     * @param userLoginInfo 用户 
     * @param atId 任务编号
     * @param apCommitDate 上级提交日期
     * @return 
     * @throws Exception
     */
    public List<AuditProcess> searchAuditProcessByUserNo(Page page, UserLoginfo userLoginInfo, String atId, String apCommitDate) throws Exception;
    
    /**
     * @param batchNo
     * @param prodNo
     * @return
     * @throws Exception
     */
    public List<AuditProcess> getAuditProcessListByProdNoAndBatchNo(String batchNo, String prodNo)throws Exception;
    
    /**
     * 根据审批任务id获取审批流程集合
     *
     * @param atId 审批任务id
     * @return 审批流程集合
     * @throws Exception
     */
    public List<AuditProcess> getAuditProcessByAtId(String atId) throws Exception;
    
    /**
     * 根据审批任务id获取审批任务详细信息
     *
     * @param atId 审批任务id
     * @return 审批任务详细信息
     * @throws Exception
     */
    public AuditTask getAuditTaskByAtId(String atId) throws Exception;
    
    /**
     * 保存当前登录用户审批流程
     *
     * @param auditProcess 审批流程对象
     * @throws Exception
     */
    public void addAuditProcess(AuditProcess auditProcess) throws Exception;
    
    /**
     * 通过searchbean查询审批流程
     *
     * @param bean查询条件
     * @param page 分页对象
     * @return  审批流程结果集
     * @throws Exception
     */
    public List<AuditProcess> getAuditProcessBySearchBean(AuditProcessSearchBean bean, Page page) throws Exception;
    
    /**
     * 批量删除审批流程
     *
     * @param auditProcessList 待删除审批流程
     * @throws Exception
     */
    public void deleteAuditProcess(List<AuditProcess> auditProcessList) throws Exception;
}
