package com.herongtech.console.service.common.audit;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.domain.common.audit.bean.ReAsRole;
import com.herongtech.console.domain.common.audit.dao.ReAsRoleDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditCommonService;
import com.herongtech.console.service.common.audit.interfaces.IReAsRoleService;


public class ReAsRoleService implements IReAsRoleService {

    private IAuditCommonService auditCommonService;
    
    @Override
    public List<ReAsRole> searchReAsRole(String asId)
            throws Exception {
        return auditCommonService.getReAuditStationRoleList(asId);
    }

    
    public void setAuditCommonService(IAuditCommonService auditCommonService) {
        this.auditCommonService = auditCommonService;
    }


    @Override
    public void addReAsRole(String roleIds, String asId) throws Exception {
        if(StringUtils.isEmpty(roleIds)){
//            throw new Exception();
            return ;
        }
        ReAsRoleDao roleDao=new ReAsRoleDao();
        String[] roleIdArray = roleIds.split(",");
        for(String role : roleIdArray) {
            ReAsRole reAsRole = new ReAsRole();
            reAsRole.setAsId(asId);
            reAsRole.setBindRoleId(role);
            reAsRole.setId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
            roleDao.addReAsRole(reAsRole);
        }
        
    }
    
    /**
     * 删除审批岗位角色关系
     *
     * @param reIds 岗位角色表主键
     * @throws Exception
     */
    public void deleteReAsRole(String reIds) throws Exception {
        if(StringUtils.isEmpty(reIds)){
          return ;
      }
        String[] idArray = reIds.split(",");
        ReAsRoleDao roleDao=new ReAsRoleDao();
        for(String reId : idArray) {
            roleDao.deleteReAsRole(reId);
        }
    }

}
