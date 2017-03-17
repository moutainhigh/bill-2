package com.herongtech.console.service.common.audit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.herongtech.appconfig.SysConfigUtil;
import com.herongtech.commons.tools.StringUtil;
import com.herongtech.console.cache.DictCache;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.common.audit.bean.AuditNode;
import com.herongtech.console.domain.common.audit.bean.AuditProcess;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;
import com.herongtech.console.domain.common.audit.bean.AuditRouteSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditStation;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.audit.bean.AuditTaskSearchBean;
import com.herongtech.console.domain.common.audit.bean.ReArProd;
import com.herongtech.console.domain.common.audit.bean.ReArProdSearchBean;
import com.herongtech.console.domain.common.audit.bean.ReAsRole;
import com.herongtech.console.domain.common.audit.bean.ReAsUser;
import com.herongtech.console.domain.common.audit.dao.AuditNodeDao;
import com.herongtech.console.domain.common.audit.dao.AuditProcessDao;
import com.herongtech.console.domain.common.audit.dao.AuditRouteDao;
import com.herongtech.console.domain.common.audit.dao.AuditStationDao;
import com.herongtech.console.domain.common.audit.dao.AuditTaskDao;
import com.herongtech.console.domain.common.audit.dao.ReArProdDao;
import com.herongtech.console.domain.common.audit.dao.ReAsRoleDao;
import com.herongtech.console.domain.common.audit.dao.ReAsUserDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditCommonService;


public class AuditCommonService implements IAuditCommonService {

    @Override
    public void judgeAuditTaskProcessStatus(String arId)
            throws Exception {
        AuditTaskDao taskDao=new AuditTaskDao();
        AuditTaskSearchBean bean=new AuditTaskSearchBean();
        bean.setArId(arId);
        //获取对应条件的审批任务集合
          List<AuditTask> auditTaskList = taskDao.getAuditTaskListBySearchBean(null,bean);
          //判断集合里是否存在符合状态的审批任务，如果存在，则抛对应的业务异常
          for(AuditTask at : auditTaskList) {
              if("1".equals(at.getAtStatus())) {//1 审批中
                  throw new Exception("审批路线存在尚未结束的审批，不允许进行该操作，请确认后再操作");
              }
          }

    }

    @Override
    public void judgeAuditRouteExist(AuditRoute auditRoute, boolean isEdit,
            String arId) throws Exception {
        //获取审批路线结果集
        AuditRouteSearchBean bean=new AuditRouteSearchBean();
        bean.setProdNo(auditRoute.getProdNo());
        bean.setPubFlag(auditRoute.getPubFlag());
        if(!StringUtil.isBlank(auditRoute.getBindBranchNo())){
            bean.setBindBranchNo(auditRoute.getBindBranchNo());
        }
        
        AuditRouteDao dao=new AuditRouteDao();
        Page page=new Page();
        page.setShowCount(Integer.MAX_VALUE);
        List<AuditRoute> auditRouteList = dao.searchAuditRoute(page,bean);
      //如果结果集有数据，则抛异常，提示操作员
        if(!CollectionUtils.isEmpty(auditRouteList)) {
            outer:
            if(isEdit) {
                //isEdit：true 编辑状态
                for(AuditRoute ar : auditRouteList) {
                    if(arId.equals(ar.getId()))
                        continue;
                    else 
                        break outer;
                }
                return;
            }
            throw new Exception("指定产品下已存在对应的审批路线，请确认后再操作");
        }


    }

    @Override
    public String judgeAuditNodeBrchLvl(AuditNode auditNode) throws Exception {
        //获取审批路线id下所有的审批节点
        AuditNodeDao nodeDao=new AuditNodeDao();
        AuditRoute auditRoute = getAuditRouteById(auditNode.getArId());
        List<AuditNode> auditNodeList = nodeDao.getAuditNodeByArId(auditNode.getArId());
        if(!CollectionUtils.isEmpty(auditNodeList)) {
            if(auditNode.getAnBrchLvl().compareTo(auditRoute.getAuditStartBrchLvl()) > 0) {
                throw new Exception("新增节点的机构级别不能低于起始机构级别，请确认后再操作");
            }
            for(AuditNode an : auditNodeList) {
                if(auditNode.getAnBrchLvl().equals(an.getAnBrchLvl())) {
                    throw new Exception("已存在相同机构级别的审批节点，不允许重复添加，请确认后再操作");
                }
            }
        }
        return auditNode.getAnBrchLvl();
    }

    @Override
    public void judgeAuditNodeAndAuditRouteBrchLvl(String arId,String anId)
            throws Exception {
      //获取审批路线id下所有的审批节点
        AuditRoute auditRoute = getAuditRouteById(arId);
        AuditNode auditNode = getAuditNodeById(anId);
        if(auditRoute.getAuditStartBrchLvl().equals(auditNode.getAnBrchLvl())) {
            throw new Exception("不允许删除起始审批节点，请确认后再操作");
        }
    }

    
    public AuditRoute getAuditRouteById(String arId)throws Exception{
        AuditRouteDao routeDao=new AuditRouteDao();
        return routeDao.getAuditRoute(arId);
    }
    
    public AuditNode getAuditNodeById(String anId)throws Exception{
        AuditNodeDao nodeDao=new AuditNodeDao();
        return nodeDao.getAuditNode(anId);
    }

    @Override
    public void deleteAuditSeries(String anId) throws Exception {
      //根据审批节点id获取节点对应的审批岗位集合
        AuditStationDao stationDao=new AuditStationDao();
        ReAsRoleDao roleDao=new ReAsRoleDao();
        ReAsUserDao userDao=new ReAsUserDao();
        List<AuditStation> auditStationList = stationDao.getAuditStationByAnId(anId);
        //待删除的审批岗位角色关系集合
        List<ReAsRole> toDelReAsRoleList = new ArrayList<ReAsRole>();
        //待删除的审批岗位用户关系集合
        List<ReAsUser> toDelReAsUserList = new ArrayList<ReAsUser>();
        for(AuditStation as : auditStationList) {
            //根据审批岗位id获取审批岗位角色关系集合
            toDelReAsRoleList.addAll(roleDao.getReAuditStationRoleList(as.getId()));
            //根据审批岗位id获取审批岗位用户关系集合
            toDelReAsUserList.addAll(userDao.getReAsUserList(as.getId()));
        }
        //删除审批岗位角色关系集合
        if(toDelReAsRoleList.size()>0){
            roleDao.deleteReAsRole(toDelReAsRoleList);
         }
        //删除审批岗位用户关系集合
        if(toDelReAsUserList.size()>0){
            userDao.deleteReAsUser(toDelReAsUserList);
        }
           
        //删除审批岗位
        if(auditStationList.size()>0){
            stationDao.deleteAuditStation(auditStationList);
        }
            
    }

    @Override
    public void deleteAuditReRoleAndUser(String asId) throws Exception {
        ReAsRoleDao roleDao=new ReAsRoleDao();
        ReAsUserDao userDao=new ReAsUserDao();
        List<ReAsRole> toDelReAsRoleList=roleDao.getReAuditStationRoleList(asId);
        List<ReAsUser> toDelReAsUserList=userDao.getReAsUserList(asId);
        //删除审批岗位角色关系集合
        if(toDelReAsRoleList.size()>0){
        	roleDao.deleteReAsRole(toDelReAsRoleList);
        }
        //删除审批岗位用户关系集合
        if(toDelReAsUserList.size()>0){
        	userDao.deleteReAsUser(toDelReAsUserList);
        }
    }

    @Override
    public boolean isNeedToAudit(String branchNo, String prodNo)
            throws Exception {
        boolean isDefaultAudit = IConstants.SWITCH_FLAG.OPEN.equals(SysConfigUtil.getSysConfig().getValue(IConstants.AuditConstant.SWITCH_NAME));
        if (isDefaultAudit) {
            if (!IConstants.AuditConstant.ZERO.equals(prodNo)) {
                List<ReArProd> list = getReBrchProdList(branchNo, prodNo);
                if (!CollectionUtils.isEmpty(list)) {
                    if (IConstants.YES.equals(list.get(0)
                            .getAuditFlag())) {
                        return true;
                    } else if (IConstants.NO.equals(list.get(
                            0).getAuditFlag())) {
                        return false;
                    }
                }
            }
        }
        return isDefaultAudit;
    }

    @Override
    public void judgeAuditStationPrivilege(String arId, Double privilegeAmount)
            throws Exception {
        AuditStationDao stationDao=new AuditStationDao();
        List<AuditStation> auditStationList = stationDao.getAuditStationByArId(arId);
        for(AuditStation as : auditStationList) {
            //只要审批权限大于等于待审批的岗位权限，则直接返回
            if(MathScaleUtil.isMoreThan(as.getAsPrivilege(), privilegeAmount)) {
                return;
            }
        }
        throw new Exception("产品审批路线中不存在比"+MoneyUtils.moneyToString(privilegeAmount)+"大的审批权限，不允许提交审批");
    }

    @Override
    public AuditNode getAuditNodeByArIdAndBrchLevel(String arId, String brchLvl)
            throws Exception {
        AuditNodeDao nodeDao=new AuditNodeDao();
        List<AuditNode> nodeList=nodeDao.getAuditNodeByArIdAndBrchLevel(arId, brchLvl);
        return nodeList.get(0);
    }

    @Override
    public List<AuditStation> getAuditStationByAnId(String anId)
            throws Exception {
        AuditStationDao stationDao=new AuditStationDao();
        return stationDao.getAuditStationByAnId(anId);
    }

    @Override
    public List<AuditRoute> getAditRouteList(AuditRouteSearchBean bean)
            throws Exception {
        AuditRouteDao routeDao=new AuditRouteDao();
        return routeDao.searchAuditRoute(null, bean);
    }

    @Override
    public AuditStation getFirstAuditStationByArIdAndBrchLevel(String arId,
            String brchLvl) throws Exception {
        AuditNode auditNode = getAuditNodeByArIdAndBrchLevel(arId, brchLvl);
        //获取审批节点下所有的审批岗位
        return getAuditStationByAnId(auditNode.getId()).get(0);
    }

    @Override
    public void addAuditProcess(AuditProcess auditProcess) throws Exception {
        if(StringUtil.isBlank(auditProcess.getId())){
            auditProcess.setId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
        }
        
        AuditProcessDao processDao=new AuditProcessDao();
        processDao.addAuditProcess(auditProcess);
        
    }

    @Override
    public List<AuditProcess> getAuditProcessByAtId(String atId)
            throws Exception {
        AuditProcessDao processDao=new AuditProcessDao();
        return processDao.getAuditProcessByAtId(atId);
    }

    @Override
    public void deleteAuditProcess(List<AuditProcess> auditProcessList)
            throws Exception {
        AuditProcessDao processDao=new AuditProcessDao();
        processDao.deleteAuditProcess(auditProcessList);
    }

    @Override
    public List<ReAsRole> getReAuditStationRoleList(String asId)
            throws Exception {
        ReAsRoleDao roleDao=new ReAsRoleDao();
        
        return roleDao.getReAuditStationRoleList(asId);
    }

    @Override
    public List<ReAsRole> getReAsRoleByRoleId(String roleId) throws Exception {
        ReAsRoleDao roleDao=new ReAsRoleDao();
        return roleDao.getReAsRoleByRoleId(roleId);
    }

    @Override
    public List<ReAsUser> getReAsUserByUserId(String userId) throws Exception {
        ReAsUserDao userDao=new ReAsUserDao();
        return userDao.getReAsUserByUserId(userId);
    }

    @Override
    public AuditTask getAuditTaskByAtId(String atId) throws Exception {
        AuditTaskDao atDao=new AuditTaskDao();
        return atDao.getAuditTask(atId);
    }

    @Override
    public AuditStation getAuditStationById(String asId) throws Exception {
        AuditStationDao stationDao=new AuditStationDao();
        return stationDao.getAuditStation(asId);
    }

    @Override
    public List<AuditNode> getAuditNodeByArId(String arId) throws Exception {
        AuditNodeDao nodeDao=new AuditNodeDao();
        return nodeDao.getAuditNodeByArId(arId);
    }

    @Override
    public void updateAuditTask(AuditTask auditTask) throws Exception {
        AuditTaskDao taskDao=new AuditTaskDao();
        taskDao.modifyAuditTask(auditTask);
    }

    @Override
    public List<ReArProd> getReBrchProdList(String branchNo, String prodNo)
            throws Exception {
        ReArProdSearchBean searchBean=new ReArProdSearchBean();
        searchBean.setBranchNo(branchNo);
        searchBean.setProdNo(prodNo);
        ReArProdDao bpDao=new ReArProdDao();
        return bpDao.getReArProdBySeachBean(null, searchBean);
    }
    
    @Override
    public void addAuditNode(AuditRoute auditRoute) throws Exception {
        AuditNode auditNode = new AuditNode();
        //设置节点机构级别
        auditNode.setAnBrchLvl(auditRoute.getAuditStartBrchLvl());
        //设置审批节点名称
        auditNode.setAnName(DictCache.getInstance().getItemValue("K_BRCH_LEVEL", auditRoute.getAuditStartBrchLvl()));
        //设置审批路线ID
        auditNode.setArId(auditRoute.getId());
        
        auditNode.setId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
        
        addAuditNode(auditNode);
    }
    
    @Override
    public void addAuditNode(AuditNode auditNode) throws Exception {
        
        //判断审批路线下是否存在尚未结束的审批
        judgeAuditTaskProcessStatus(auditNode.getArId());
        //判断审批路线下是否存在节点
        String sortNo =judgeAuditNodeBrchLvl(auditNode);
        auditNode.setSortNo(sortNo);
        
        if(StringUtil.isBlank(auditNode.getId())){
            auditNode.setId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
        }
        
        AuditNodeDao dao=new AuditNodeDao();
        dao.addAuditNode(auditNode);
        
    }
    
}
