package com.herongtech.console.service.common.audit.interfaces;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;
import com.herongtech.console.domain.common.audit.bean.AuditRouteSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditRouteTreeDto;

public interface IAuditRouteService {

    public List<AuditRoute> searchAuditRoute(Page page,
            AuditRouteSearchBean bean) throws Exception;

    public void addAuditRoute(UserLoginfo userLoginInfo, AuditRoute route)
            throws Exception;

    public void delAuditRoute(AuditRoute route) throws Exception;

    public void updateAuditRoute(AuditRoute route) throws Exception;

    /**
     * 根据审批路线id获取审批路线tree
     * @param arId
     * @return
     * @throws Exception
     */
    public List<AuditRouteTreeDto> getRouteTree(String arId) throws Exception;

    /**
     * 根据审批路线id查询审批路线信息
     * 
     * @param arId
     * @return
     * @throws Exception
     */
    public AuditRoute getAuditRouteById(String arId) throws Exception;
   
    
}
