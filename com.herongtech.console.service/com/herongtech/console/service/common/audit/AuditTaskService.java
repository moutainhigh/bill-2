package com.herongtech.console.service.common.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IConstants.AuditConstant;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.common.audit.bean.AuditNode;
import com.herongtech.console.domain.common.audit.bean.AuditProcess;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;
import com.herongtech.console.domain.common.audit.bean.AuditRouteSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditStation;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.audit.bean.AuditTaskSearchBean;
import com.herongtech.console.domain.common.audit.dao.AuditTaskDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditCommonService;
import com.herongtech.console.service.common.audit.interfaces.IAuditTaskService;
import com.herongtech.console.service.common.audit.util.AuditTriggerUtil;


public class AuditTaskService implements IAuditTaskService {

    private IAuditCommonService auditCommonService;
    
    @Override
    public List<AuditTask> getAuditTaskListBySearchBean(Page page,
            AuditTaskSearchBean searchBean)throws Exception {
        AuditTaskDao dao=new AuditTaskDao();
        return dao.getAuditTaskListBySearchBean(page, searchBean);
    }

    @Override
    public void addAuditTask(AuditTask auditTask) throws Exception {
      //根据机构号和产品号判断是否需要审批
        boolean isAuditFlag = auditCommonService.isNeedToAudit(auditTask.getBrchNo(), auditTask.getProdNo());
        if(isAuditFlag) {
//            Branch branch=ServiceFactory.getBranchService().getBranch(auditTask.getBrchNo());
            boolean ifExist = judgeAuditRouteExist(auditTask, new ArrayList<AuditRoute>());
            if(!ifExist){//判断三级机构后若审批路线不存在则不进行审批
                AuditTriggerUtil.lastStationAdopt(auditTask.getProdNo(), auditTask.getBatchId());
                return;// 无需审批,流程通过
            }
        } else {
            AuditTriggerUtil.lastStationAdopt(auditTask.getProdNo(), auditTask.getBatchId());
            return;// 无需审批,流程通过
        }
        //获取可用的审批路线
        AuditRoute auditRoute = getAvailableAuditRoute(auditTask);
        //保存审批任务逻辑方法
        saveTaskLogic(auditRoute, auditTask);
        
    }

    @Override
    public void cancelAuditTask(AuditTask auditTask) throws Exception {
        AuditTaskSearchBean searchBean=new AuditTaskSearchBean();
        searchBean.setProdNo(auditTask.getProdNo());
        //AT_STATUS_PROCESS 审批任务状态：1-审批中
        searchBean.setAtStatus(IConstants.AuditConstant.AT_STATUS_PROCESS);
        searchBean.setBatchId(auditTask.getBatchId());
     // 根据产品id和批次id查询审批中的审批任务
        List<AuditTask> auditTaskList = getAuditTaskListBySearchBean(null,searchBean);
        if(CollectionUtils.isEmpty(auditTaskList)) {
            return ;
//            throw new Exception("审批流程已结束，不允许该操作");
        }
        AuditTask ausitTask = auditTaskList.get(0);
        //删除审批任务下的审批流程
        deleteAuditProcessByAtId(ausitTask.getId());
        //删除审批任务
        AuditTaskDao taskDao=new AuditTaskDao();
        taskDao.deleteAuditTask(ausitTask.getId());
    }

    @Override
    public AuditTask queryAuditTaskInfo(String atId) throws Exception {
        if(StringUtils.isEmpty(atId))
            throw new Exception("请选择任务");
        AuditTaskDao taskDao=new AuditTaskDao();
        return taskDao.getAuditTask(atId);
    }

    @Override
    public List queryHandlersList(String atId) throws Exception {
        List<AuditProcess> auditProcessList = auditCommonService.getAuditProcessByAtId(atId);
        AuditProcess auditProcess = auditProcessList.get(auditProcessList.size()-1);
        if(StringUtils.isNotBlank(auditProcess.getApExecResult())) {
            return null;
        }
        return auditCommonService.getReAuditStationRoleList(auditProcess.getApExecStationId());
    }

    
    private boolean judgeAuditRouteExist(AuditTask auditTask,List<AuditRoute> auditRouteList)throws Exception{
        boolean isAuditRouteExist = judgeCurrentAuditRouteExist(auditTask, auditRouteList);
        
        if(!isAuditRouteExist) {
            //定位全行通用及当前产品路线
            isAuditRouteExist = judgeAuditRouteExistForGeneral(auditTask, auditRouteList);
        }
        return isAuditRouteExist;
    }
    /**
     * 获取可用的审批路线
     *  <br>审批路线的匹配规则：
     *  <br>定位当前机构及当前产品的路线--->定位当前机构父级产品路线
     *  <br>--->定位全行通用及当前产品路线--->定位全行通用及父级产品路线
     * @param auditTask 审批任务
     * @return
     */
    private AuditRoute getAvailableAuditRoute(AuditTask auditTask)throws Exception {
        List<AuditRoute> auditRouteList = new ArrayList<AuditRoute>();
        //定位当前机构及当前产品的路线
        boolean isAuditRouteExist = judgeCurrentAuditRouteExist(auditTask, auditRouteList);
        
        if(!isAuditRouteExist) {
            //定位全行通用及当前产品路线
            isAuditRouteExist = judgeAuditRouteExistForGeneral(auditTask, auditRouteList);
        }
        if(!isAuditRouteExist && CollectionUtils.isEmpty(auditRouteList)) {
            throw new Exception(" 产品没有设置相应的审批路线，请设置后再进行该操作");
        }
        //判断审批路线的起始审批节点下是否有审批岗位
        judgeStartAuditStationExist(auditRouteList.get(0));
        //AN_EXEC_MODE_POST 审批岗位执行模式：岗位审批权限控制执行
        if(IConstants.AuditConstant.AN_EXEC_MODE_POST.equals(auditRouteList.get(0).getAnExecMode())) {
            auditCommonService.judgeAuditStationPrivilege(auditRouteList.get(0).getId(), auditTask.getWaitAuditAmt());
        }
        return auditRouteList.get(0);
    }
    
    /**
     * 判断审批路线的起始审批节点下是否有审批岗位
     *  
     * @param auditRoute 审批路线
     */
    private void judgeStartAuditStationExist(AuditRoute auditRoute)throws Exception {
        //获取起始审批节点
        AuditNode auditNode = auditCommonService.getAuditNodeByArIdAndBrchLevel(auditRoute.getId(), auditRoute.getAuditStartBrchLvl());
        //判断起始审批节点下是否有审批岗位
        if(CollectionUtils.isEmpty(auditCommonService.getAuditStationByAnId(auditNode.getId()))) {
            throw new Exception("审批路线下的起始节点没有设置岗位，请设置后再进行该操作");
        }
    }
    
    /**
     * 根据审批任务判断当前机构和当前产品对应的审批路线是否存在
     *
     * @param auditTask 审批任务
     * @return false 不存在
     *      <br>true 存在
     */
    private boolean judgeCurrentAuditRouteExist(AuditTask auditTask, List<AuditRoute> list)throws Exception {
        List<AuditRoute> auditRouteList = getAuditRouteByBrchNoAndProdNo(auditTask.getBrchNo(), auditTask.getProdNo());
        if(CollectionUtils.isEmpty(auditRouteList)) {
            return false;
        }
        list.addAll(auditRouteList);
        return true;
    }
    
   
    
    /**
     * 根据审批任务判断全行通用及当前产品对应的审批路线是否存在
     *
     * @param auditTask 审批任务
     * @return
     */
    private boolean judgeAuditRouteExistForGeneral(AuditTask auditTask, List<AuditRoute> list)throws Exception {
        List<AuditRoute> auditRouteList = getAuditRouteByBrchLvlAndProdNo(auditTask.getBrchNo(), auditTask.getProdNo());
        if(CollectionUtils.isEmpty(auditRouteList)) {
            return false;
        }
        list.addAll(auditRouteList);
        return true;
    }
    
   
    
    /**
     * 根据机构号和产品号获取审批路线
     * 指定机构使用模式
     * @param brchId 机构id
     * @param prodId 产品id
     * @return
     */
    private List<AuditRoute> getAuditRouteByBrchNoAndProdNo(String branchNo, String prodNo)throws Exception {
        AuditRouteSearchBean searchBean = new AuditRouteSearchBean();
        //EFFECT_STATUS_YES 审批路线生效标志：1-生效
        searchBean.setEffectFlag(IConstants.AuditConstant.EFFECT_STATUS_YES);
        searchBean.setBindBranchNo(branchNo);
        searchBean.setProdNo(prodNo);
        //PUB_FLAG_ASSIGN_BRCH 审批路线使用模式：指定机构使用
        searchBean.setPubFlag(IConstants.AuditConstant.PUB_FLAG_ASSIGN_BRCH);
        return auditCommonService.getAditRouteList(searchBean);      
    }
    
    /**
     * 根据产品号和审批起始机构级别获取审批路线
     *
     * @param brchNo 机构编号
     * @param prodNo 产品编号
     * @return
     */
    private List<AuditRoute> getAuditRouteByBrchLvlAndProdNo(String brchNo, String prodNo)throws Exception {
        Branch branch=ServiceFactory.getBranchService().getBranch(brchNo);
        AuditRouteSearchBean searchBean = new AuditRouteSearchBean();
        //EFFECT_STATUS_YES 审批路线生效标志：1-生效
        searchBean.setEffectFlag(AuditConstant.EFFECT_STATUS_YES);
        searchBean.setProdNo(prodNo);
        //PUB_FLAG_ALL 审批路线使用模式：全行通用
        searchBean.setPubFlag(AuditConstant.PUB_FLAG_ALL);
//        searchBean.setAuditStartBrchLvl(branch.getBranchLevel());
        return auditCommonService.getAditRouteList(searchBean);
    }
    
    /**
     * 保存审批任务逻辑方法
     *
     * @param auditRoute 
     * @param auditTask
     */
    private void saveTaskLogic(AuditRoute auditRoute, AuditTask auditTask)throws Exception {
        AuditTaskDao taskDao=new AuditTaskDao();
        auditTask.setArId(auditRoute.getId());
        //AT_STATUS_PROCESS 审批任务状态：1-审批中
        auditTask.setAtStatus(IConstants.AuditConstant.AT_STATUS_PROCESS);
        auditTask.setId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
        taskDao.addAuditTask(auditTask);
        
        saveAuditProcess(auditRoute, auditTask);
    }
    
    /**
     * 保存审批流程首岗信息
     *
     * @param auditRoute    审批路线实体
     * @param auditTask     审批任务实体
     */
    private void saveAuditProcess(AuditRoute auditRoute, AuditTask auditTask)throws Exception {
        AuditStation auditStation = auditCommonService.getFirstAuditStationByArIdAndBrchLevel(
                auditRoute.getId(), auditRoute.getAuditStartBrchLvl());
        AuditProcess auditProcess = new AuditProcess();
        auditProcess.setAtId(auditTask.getId());
        auditProcess.setApCommitPersonId(String.valueOf(auditTask.getAtAuthorId()));
        auditProcess.setApCommitPersonName(auditTask.getAtAuthorName());
        auditProcess.setApCommitBrchNo(auditTask.getBrchNo());
        auditProcess.setApExecStationId(auditStation.getId());
        auditProcess.setApExecStationName(auditStation.getAsName());
        auditProcess.setApExecBrchNo(auditTask.getBrchNo());
        //AP_STATUS_UNTREATED 审批状态：1-未受理
        auditProcess.setApStatus(IConstants.AuditConstant.AP_STATUS_UNTREATED);
        auditProcess.setApCommitDt(DateTimeUtil.getWorkdayString());
        auditProcess.setApCommitTime(DateTimeUtil.getTime());
        //ONE 序号：1
        auditProcess.setSortNo(IConstants.AuditConstant.ONE+"");
        auditCommonService.addAuditProcess(auditProcess);        
    }
    
    /**
     * 根据审批任务id删除该审批任务下所有的审批流程
     *  
     * @param atId 审批任务id
     * @return 返回第一个审批流程
     */
    private AuditProcess deleteAuditProcessByAtId(String atId)throws Exception {
        List<AuditProcess> auditProcessList = auditCommonService.getAuditProcessByAtId(atId);
        auditCommonService.deleteAuditProcess(auditProcessList);
        return auditProcessList.get(0);
    }
    
    public void setAuditCommonService(IAuditCommonService auditCommonService) {
        this.auditCommonService = auditCommonService;
    }

    
}
