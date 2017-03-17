package com.herongtech.console.service.common.audit.interfaces;

import java.util.List;

import com.herongtech.console.domain.common.audit.bean.AuditNode;
import com.herongtech.console.domain.common.audit.bean.AuditProcess;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;
import com.herongtech.console.domain.common.audit.bean.AuditRouteSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditStation;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.audit.bean.ReArProd;
import com.herongtech.console.domain.common.audit.bean.ReAsRole;
import com.herongtech.console.domain.common.audit.bean.ReAsUser;


/**
 * 审批公共服务类
 *
 */
public interface IAuditCommonService {

    
    /**
     * 判断审批路线下是否存在尚未结束的审批，若存在，则不能做相应的操作
     * @param arId 审批路线ID
     * @throws Exception
     */
    public void judgeAuditTaskProcessStatus(String arId)throws Exception;
    
    /**
     * 根据指定使用产品号、审批路线使用模式编号、生效状态和指定使用机构号进行判断是否允许新增审批路线
     * @param auditRoute
     * @param isEdit
     * @param arId
     * @throws Exception
     */
    public void judgeAuditRouteExist(AuditRoute auditRoute, boolean isEdit, String arId)throws Exception;
    
    /**
     * 判断审批路线下是否存在节点
     * @param auditNode
     * @return
     * @throws Exception
     */
    public String judgeAuditNodeBrchLvl(AuditNode auditNode)throws Exception;
    
    /**
     * 判断要删除节点的机构级别是否是审批路线的起始审批机构级别，如果是，则不能删除
     * @param arId
     * @param anId
     * @throws Exception
     */
    public void judgeAuditNodeAndAuditRouteBrchLvl(String arId,String anId) throws Exception; 
    
    
    
    /**
     * 根据审批节点id删除审批节点对应的全部记录
     *
     * @param anId
     * @throws Exception
     */
    public void deleteAuditSeries(String anId) throws Exception;
    
    /**
     * 根据审批岗位id删除审批岗位角色和用户的关系
     * @param asId
     * @throws Exception
     */
    public void deleteAuditReRoleAndUser(String asId)throws Exception;
    
    /**
     * 根据机构号和产品号判断是否需要审批
     *  
     * @param branchNo 机构号
     * @param prodNo 产品号
     * @return true - 需要审批
     *      <br>false - 不需要审批
     * @throws Exception
     */
    public boolean isNeedToAudit(String branchNo, String prodNo) throws Exception;
    
    /**
     * 判断审批路线下的审批岗位是否有此审批权限
     *
     * @param arId 审批路线id
     * @param privilegeAmount 审批权限
     * @throws Exception
     */
    public void judgeAuditStationPrivilege(String arId, Double privilegeAmount) throws Exception;
    
    /**
     * 根据审批路线id和机构级别查询审批节点
     *
     * @param arId 审批路线id
     * @param brchLvl 机构级别
     * @return 审批节点
     * @throws Exception
     */
    public AuditNode getAuditNodeByArIdAndBrchLevel(String arId, String brchLvl) throws Exception; 
    
    /**
     * 根据审批节点id获取审批岗位集合
     *
     * @param anId 审批节点id
     * @return 审批岗位结合
     * @throws Exception
     */
    public List<AuditStation> getAuditStationByAnId(String anId) throws Exception; 
    
    /**
     * 根据查询条件查询审批路线集合
     *
     * @param bean 查询条件QUERYBEAN
     * @return 审批路线集合
     * @throws Exception
     */
    public List<AuditRoute> getAditRouteList(AuditRouteSearchBean bean) throws Exception;
    
    /**
     * 根据审批路线id和机构级别获取第一个审批岗位信息
     *
     * @param arId 审批路线id
     * @param brchLvl 机构级别
     * @return 什么岗位
     * @throws Exception
     */
    public AuditStation getFirstAuditStationByArIdAndBrchLevel(String arId, String brchLvl) throws Exception;
    
    /**
     * 保存审批流程
     *
     * @param auditProcess
     * @return
     * @throws Exception
     */
    public void addAuditProcess(AuditProcess auditProcess) throws Exception; 
    
    /**
     * 根据审批任务id获取审批流程集合
     *
     * @param atId 审批任务id
     * @return 审批流程集合
     * @throws Exception
     */
    public List<AuditProcess> getAuditProcessByAtId(String atId) throws Exception; 
    
    /**
     * 批量删除审批流程
     *
     * @param auditProcessList 待删除审批流程
     * @throws Exception
     */
    public void deleteAuditProcess(List<AuditProcess> auditProcessList) throws Exception;

    /**
     * 根据审批岗位id查询审批岗位角色关系集合
     *
     * @param asId 审批岗位id
     * @return  审批岗位角色关系集合
     * @throws Exception
     */
    public List<ReAsRole> getReAuditStationRoleList(String asId) throws Exception;
    
    /**
     * 根据角色id查询审批岗位角色关系集合
     *
     * @param roleId 角色id
     * @return 审批岗位角色关系集合
     * @throws Exception
     */
    public List<ReAsRole> getReAsRoleByRoleId(String roleId) throws Exception; 
    
    /**
     * 根据用户id查询审批岗位用户关系集合
     *
     * @param roleId 角色id
     * @return 审批岗位角色关系集合
     * @throws Exception
     */
    public List<ReAsUser> getReAsUserByUserId(String userId) throws Exception;
    
    /**
     * @param atId
     * @return
     * @throws Exception
     */
    public AuditTask getAuditTaskByAtId(String atId) throws Exception;
    
    /**
     * 根据审批岗位id获取审批岗位信息
     *
     * @param asId 审批岗位id
     * @return 审批岗位信息
     * @throws Exception
     */
    public AuditStation getAuditStationById(String asId) throws Exception;
    
    /**
     * 根据审批节点id查询审批节点信息
     *
     * @param id 审批节点id
     * @return 审批节点信息
     * @throws Exception
     */
    public AuditNode getAuditNodeById(String anId) throws Exception;
    
    /**
     * 根据审批路线id查询审批路线信息
     *
     * @param arId 审批路线id
     * @return 审批路线信息
     * @throws Exception
     */
    public AuditRoute getAuditRouteById(String arId) throws Exception;
    
    /**
     * 根据审批路线ID查询审批节点信息
     * @param arId
     * @return
     * @throws Exception
     */
    public List<AuditNode> getAuditNodeByArId(String arId) throws Exception;
    
    public void updateAuditTask(AuditTask auditTask)throws Exception;
    
    
    /**
     * 根据机构号和产品号获取审批机构产品信息
     * 
     * @param branchNo
     *            机构号
     * @param prodNo
     *            产品号
     * @return
     * @throws Exception
     */
    public List<ReArProd> getReBrchProdList(String branchNo, String prodNo)throws Exception;
    
    /**
     * 新增审批节点
     * @param auditRoute
     * @throws Exception
     */
    public void addAuditNode(AuditNode auditNode)throws Exception;
    /**
     * 新增审批节点
     * @param auditRoute
     * @throws Exception
     */
    public void addAuditNode(AuditRoute auditRoute)throws Exception;
    
}
