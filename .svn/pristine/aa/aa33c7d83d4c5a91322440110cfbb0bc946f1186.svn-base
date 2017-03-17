package com.herongtech.console.service.common.audit.interfaces;

import java.util.List;

import com.herongtech.console.domain.common.audit.bean.ReAsRole;


public interface IReAsRoleService {
    /**
     * 审批岗位角色关系查询
     * @return          审批岗位角色关系
     * @throws Exception 
     */
    public List<ReAsRole> searchReAsRole(String asId) throws Exception;
    
    /**
     * 新增审批岗位角色关系
     *
     * @param roleIds 角色id字符串
     * @param id 岗位id
     * @throws Exception
     */
    public void addReAsRole(String roleIds, String asId) throws Exception;

    /**
     * 删除审批岗位角色关系
     *
     * @param reIds 岗位角色表主键
     * @throws Exception
     */
    public void deleteReAsRole(String roleIds) throws Exception;
}
