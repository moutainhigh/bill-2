package com.herongtech.console.service.common.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditNode;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;
import com.herongtech.console.domain.common.audit.bean.AuditRouteSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditRouteTreeDto;
import com.herongtech.console.domain.common.audit.bean.AuditStation;
import com.herongtech.console.domain.common.audit.dao.AuditRouteDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditCommonService;
import com.herongtech.console.service.common.audit.interfaces.IAuditNodeService;
import com.herongtech.console.service.common.audit.interfaces.IAuditRouteService;


public class AuditRouteService implements IAuditRouteService {

    private IAuditCommonService auditCommonService;
    
    
    @Override
    public List<AuditRoute> searchAuditRoute(Page page,
            AuditRouteSearchBean bean) throws Exception {
    	bean.setEffectFlag(IConstants.AuditConstant.EFFECT_STATUS_YES);
        AuditRouteDao dao=new AuditRouteDao();
        return dao.searchAuditRoute(page, bean);
    }

    @Override
    public void addAuditRoute(UserLoginfo userLoginInfo,AuditRoute route) throws Exception {
        auditCommonService.judgeAuditRouteExist(route,false,"0");
        //审批路线创建人所在机构
        route.setCreateBrchNo(userLoginInfo.getBranchNo());
        //创建日期
        route.setCreateDt(DateTimeUtil.getWorkdayString());
        //创建时间
        route.setCreateTime(DateTimeUtil.get_hhmmss_time());
        //新增的时候，默认生效
        route.setEffectFlag(IConstants.SWITCH_FLAG.OPEN);
        route.setId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
       
        if(IConstants.AuditConstant.PUB_FLAG_ASSIGN_BRCH.equals(route.getPubFlag())) {
            //机构级别 1：总行，2：分行，3：支行，4：网点。 
            route.setAuditStartBrchLvl(userLoginInfo.getBranchLevel());
        }
        
        
        AuditRouteDao dao=new AuditRouteDao();
        dao.addAuditRoute(route);
        auditCommonService.addAuditNode(route);
        
        
    }

    @Override
    public void updateAuditRoute(AuditRoute route) throws Exception {
        auditCommonService.judgeAuditTaskProcessStatus(route.getId());
        auditCommonService.judgeAuditRouteExist(route,true,route.getId());
        AuditRouteDao dao=new AuditRouteDao();
        dao.modifyAuditRoute(route);
    }
    
    @Override
    public void delAuditRoute(AuditRoute route) throws Exception {
        auditCommonService.judgeAuditTaskProcessStatus(route.getId());
        AuditRouteDao dao=new AuditRouteDao();
        route=getAuditRouteById(route.getId());
        route.setEffectFlag(IConstants.AuditConstant.EFFECT_STATUS_NO);
        dao.modifyAuditRoute(route);
    }


    @Override
    public List<AuditRouteTreeDto> getRouteTree(String arId) throws Exception {
        if(StringUtils.isEmpty(arId)){
            throw new Exception("请选择审批路线");
        }
        List<AuditRouteTreeDto> tree=new ArrayList<AuditRouteTreeDto>();
        AuditRoute ar=auditCommonService.getAuditRouteById(arId);
        AuditRouteTreeDto rootDto=new AuditRouteTreeDto();
        rootDto.setParent("0");
        rootDto.setId(arId);
        rootDto.setName(ar.getArName());
        rootDto.setTreeNodeType(IConstants.AuditConstant.TREE_NODE_TYPE_ROOT);
        tree.add(rootDto);
        List<AuditNode>  nodeList=auditCommonService.getAuditNodeByArId(arId);
        for(AuditNode node:nodeList){
            AuditRouteTreeDto ndto=new AuditRouteTreeDto();
            ndto.setParent(arId);
            ndto.setId(node.getId());
            ndto.setName(node.getAnName());
            ndto.setTreeNodeType(IConstants.AuditConstant.TREE_NODE_TYPE_CHILD);
            tree.add(ndto);
            //获取叶子节点
           List<AuditStation> stationList =auditCommonService.getAuditStationByAnId(node.getId());
           for(AuditStation stat:stationList){
               AuditRouteTreeDto sdto=new AuditRouteTreeDto();
               sdto.setParent(node.getId());
               sdto.setId(stat.getId());
               sdto.setName(stat.getAsName());
               sdto.setTreeNodeType(IConstants.AuditConstant.TREE_NODE_TYPE_LEAF);
               tree.add(sdto);
           }
        }
        return tree;
    }
    
    @Override
    public AuditRoute getAuditRouteById(String arId) throws Exception {
        if(StringUtils.isEmpty(arId)){
            throw new Exception("请选择审批路线");
        }
        return auditCommonService.getAuditRouteById(arId);
    }
    

    
    
    public void setAuditCommonService(IAuditCommonService auditCommonService) {
        this.auditCommonService = auditCommonService;
    }



}
