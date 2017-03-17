package com.herongtech.console.service.common.audit.interfaces;

import java.util.List;

import com.herongtech.console.domain.common.audit.bean.AuditNode;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;


public interface IAuditNodeService {

    /**
     * 根据审批节点id查询审批节点信息
     * @param anId
     * @return
     * @throws Exception
     */
    public AuditNode getAuditNode(String anId)throws Exception;
    
    /**
     * 新增审批节点
     * @param auditRoute
     * @throws Exception
     */
    public void addAuditNode(AuditNode auditNode)throws Exception;
    
    /**
     * 编辑审批节点
     * @param auditRoute
     * @throws Exception
     */
    public void editAuditNode(AuditNode auditNode)throws Exception;
    
    /**
     * 删除审批节点
     * @param arId 审批路线ID
     * @param anId 节点ID
     * @throws Exception
     */
    public void deleteAuditNode(String arId,String anId)throws Exception;
    
    /**
     * 根据审批路线id获取审批节点
     * @param arId 审批路线id
     * @return
     * @throws Exception
     */
    public List<AuditNode> getAuditNodeByArId(String arId)throws Exception;
}
