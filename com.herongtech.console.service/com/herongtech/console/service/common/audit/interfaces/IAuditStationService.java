package com.herongtech.console.service.common.audit.interfaces;

import java.util.List;

import com.herongtech.console.domain.common.audit.bean.AuditStation;


public interface IAuditStationService {
    
    /**
     * 根据审批岗位id查询审批岗位信息
     * @param asId
     * @return
     * @throws Exception
     */
    public AuditStation getAuditStation(String asId)throws Exception;
    
    /**
     * 新增审批岗位
     *
     * @param auditStation 审批岗位信息
     * @throws Exception
     */
    public void addAuditStation(AuditStation auditStation) throws Exception;
    
    /**
     * 编辑审批岗位
     *
     * @param auditStation 审批岗位信息
     * @throws Exception
     */
    public void editAuditStation(AuditStation auditStation) throws Exception;
    
    /**
     * 删除审批岗位
     *
     * @param asId 审批岗位id 
     * @param arId 审批路线id 
     * @throws Exception
     */
    public void deleteAuditStation( String asId, String arId) throws Exception;
    
    /**
     * 移动审批岗位
     *
     * @param id 审批岗位id 
     * @param anId 审批节点id
     * @param arId 审批路线id 
     * @param upOrDown 上移下移标志位 
     * <br> 0-上移 1-下移
     * @throws Exception
     */
    public void moveAuditStation(String id, String anId, String arId, Integer upOrDown) throws Exception;
    
    /**
     * 判断审批岗位权限
     *
     * @param arId 审批路线id 
     * @throws Exception
     */
    public void judgeAuditStationPrivilege(String arId) throws Exception;
    
    
    /**
     * 通过审批节点获取节点下所有岗位
     * @param anId
     * @return
     * @throws Exception
     */
    public List<AuditStation> getAuditStationByAnId(String anId)throws Exception;
    
    public List<AuditStation> getAuditStationByArId(String arId)throws Exception;
    
}
