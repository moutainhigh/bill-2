package com.herongtech.console.service.common.audit;

import java.util.List;

import com.herongtech.commons.tools.StringUtil;
import com.herongtech.console.cache.DictCache;
import com.herongtech.console.domain.common.audit.bean.AuditNode;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;
import com.herongtech.console.domain.common.audit.dao.AuditNodeDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditCommonService;
import com.herongtech.console.service.common.audit.interfaces.IAuditNodeService;


public class AuditNodeService implements IAuditNodeService{
    
    private IAuditCommonService auditCommonService;

    @Override
    public void addAuditNode(AuditNode auditNode) throws Exception {
        auditCommonService.addAuditNode(auditNode);
    }
   

    @Override
    public List<AuditNode> getAuditNodeByArId(String arId) throws Exception {
        return auditCommonService.getAuditNodeByArId(arId);
    }

    @Override
    public AuditNode getAuditNode(String anId) throws Exception {
        return auditCommonService.getAuditNodeById(anId);
    }

    @Override
    public void editAuditNode(AuditNode auditNode) throws Exception {
        //判断审批路线下是否存在尚未结束的审批
        auditCommonService.judgeAuditTaskProcessStatus(auditNode.getArId());
        
        AuditNodeDao dao=new AuditNodeDao();
        dao.modifyAuditNode(auditNode,auditNode.getId());
    }

    @Override
    public void deleteAuditNode(String arId, String anId) throws Exception {
      //判断审批路线下是否存在尚未结束的审批
        auditCommonService.judgeAuditTaskProcessStatus(arId);
        //判断要删除节点的机构级别是否是审批路线的起始审批机构级别，如果是，则不能删除
        auditCommonService.judgeAuditNodeAndAuditRouteBrchLvl(arId,anId);
        //删除审批节点、审批岗位以及审批角色用户关系
        auditCommonService.deleteAuditSeries(anId);
        //删除审批路线
        AuditNodeDao dao=new AuditNodeDao();
        dao.deleteAuditNode(anId);
    }


    
    public void setAuditCommonService(IAuditCommonService auditCommonService) {
        this.auditCommonService = auditCommonService;
    }

    
    
}
