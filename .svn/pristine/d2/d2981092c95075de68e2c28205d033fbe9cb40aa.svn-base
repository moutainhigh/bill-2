package com.herongtech.console.service.common.audit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditNode;
import com.herongtech.console.domain.common.audit.bean.AuditProcess;
import com.herongtech.console.domain.common.audit.bean.AuditProcessSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;
import com.herongtech.console.domain.common.audit.bean.AuditStation;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.audit.bean.ReAsRole;
import com.herongtech.console.domain.common.audit.bean.ReAsUser;
import com.herongtech.console.domain.common.audit.dao.AuditProcessDao;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.common.bean.RemindDTO;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditCommonService;
import com.herongtech.console.service.common.audit.interfaces.IAuditProcessService;
import com.herongtech.console.service.common.audit.util.AuditTriggerUtil;


public class AuditProcessService implements IAuditProcessService{
    
    private IAuditCommonService auditCommonService;

    @Override
    public List<RemindDTO> queryAuditProcessByUserNo(UserLoginfo userLoginInfo)
            throws Exception {
        Page page=new Page();
       List<AuditProcess> apList=searchAuditProcessByUserNo(page, userLoginInfo, null, null);
       List<String> productNameList = new ArrayList<String>();
       List<Integer> numberList = new ArrayList<Integer>();
       List<RemindDTO> remindDtolist = new ArrayList<RemindDTO>();
       if(apList != null && apList.size()>0){
           for(AuditProcess auditProcess:apList){
               AuditTask auditTask = auditCommonService.getAuditTaskByAtId(auditProcess.getAtId());
               AuditRoute auditRoute = auditCommonService.getAuditRouteById(auditTask.getArId());
               if(productNameList.contains(auditRoute.getProdNo())){
                   int index=productNameList.indexOf(auditRoute.getProdNo());
                   numberList.set(index, numberList.get(index)+1);
               } else {
                   productNameList.add(auditRoute.getProdNo());
                   numberList.add(1);
               }
           }
       }
       for(int i=0;i<productNameList.size();i++){
           RemindDTO remindDto = new RemindDTO();
//           remindDto.setSucessNum(numberList.get(i));
           Product product=ServiceFactory.getProductService().getProductInfoByProdNo(productNameList.get(i));
           remindDto.setRemindName("您有"+numberList.get(i)+"条"+product.getProdName());
           remindDto.setUrl("gotoAuditTask()");
           if(remindDtolist.size()<10)
           remindDtolist.add(remindDto);
       }
       return remindDtolist;
    }

    @Override
    public List<AuditProcess> searchAuditProcessByUserNo(Page page,
            UserLoginfo userLoginInfo, String atId, String apCommitDate)
            throws Exception {
       String roleCodes = ServiceFactory.getUserService().getUserRoleByUserId(userLoginInfo.getUserId());
       Set<String> asIdSet = new HashSet<String>(); 
       if(StringUtils.isNotEmpty(roleCodes)){
          //根据角色id查询审批岗位集合
            for(String roleCode : roleCodes.split(",")) {
                //获取审批岗位角色关系列表
                List<ReAsRole> reAsRole = auditCommonService.getReAsRoleByRoleId(roleCode);
                for(ReAsRole role : reAsRole) {
                    asIdSet.add(role.getAsId());
                }
            }
        }
     //获取审批岗位用户关系列表
       List<ReAsUser> reAsUser = auditCommonService.getReAsUserByUserId(userLoginInfo.getUserId());
       for(ReAsUser user : reAsUser) {
           asIdSet.add(user.getAsId());
       }   
       if(asIdSet.size()>0){
           AuditProcessSearchBean bean=new AuditProcessSearchBean();
           bean.setAsIdArr(asIdSet.toArray());
           bean.addSpecialOpertion("asIdArr", BaseSearchBean.IN);
           bean.addSqlPropretyMapping("asIdArr", "apExecStationId");
           //AP_STATUS_UNTREATED 审批状态：1-未受理
           bean.setApStatus(IConstants.AuditConstant.AP_STATUS_UNTREATED);
           bean.setApExecBrchNo(userLoginInfo.getBranchNo());
           bean.setApCommitDt(apCommitDate);
           bean.setAtId(atId);
          return getAuditProcessBySearchBean(bean, page);
       }
       return null;
    }

    @Override
    public List<AuditProcess> getAuditProcessListByProdNoAndBatchNo(
            String batchNo, String prodNo) throws Exception {
//        auditCommonService.get
        return getAuditProcessByAtId(null);
    }

    @Override
    public List<AuditProcess> getAuditProcessByAtId(String atId)
            throws Exception {
        return auditCommonService.getAuditProcessByAtId(atId);
    }

    @Override
    public AuditTask getAuditTaskByAtId(String atId) throws Exception {
        return auditCommonService.getAuditTaskByAtId(atId);
    }

    @Override
    public void addAuditProcess(AuditProcess auditProcess) throws Exception {
        //根据审批流程id获取对应的审批流程信息
        AuditProcess originalAuditProcess = getAuditProcessById(auditProcess.getId());
        //判断审批状态
        if(!IConstants.AuditConstant.AP_STATUS_UNTREATED.endsWith(originalAuditProcess.getApStatus()))
            throw new Exception("此审批流程已被【"+originalAuditProcess.getApExecPersonName()+"】受理，不允许重复操作" );
        
        //提交审批流程逻辑
      //获取审批任务信息
        AuditTask auditTask = getAuditTaskByAtId(originalAuditProcess.getAtId());
        //获取审批岗位信息
        AuditStation auditStation = auditCommonService.getAuditStationById(originalAuditProcess.getApExecStationId());
        //获取审批节点
        AuditNode auditNode = auditCommonService.getAuditNodeById(auditStation.getAnId());
        //获取审批路线
        AuditRoute auditRoute = auditCommonService.getAuditRouteById(auditNode.getArId());
        //审批节点集合
        List<AuditNode> auditNodeList = auditCommonService.getAuditNodeByArId(auditRoute.getId());
        //审批岗位集合
        List<AuditStation> auditStationList = auditCommonService.getAuditStationByAnId(auditNode.getId());
        
        int stationIndex = getStationIndex(auditStationList, auditStation);
        int nodeIndex = getNodeIndex(auditNodeList, auditNode);
        String branchNo = auditProcess.getApExecBrchNo();
//        Map<String, String> cacheMap = getCacheBranchNoMap(auditTask.getBrchNo(), auditNodeList);
        //AP_EXEC_RESULT_AGREE 审批结果：1-同意 
        if(IConstants.AuditConstant.AP_EXEC_RESULT_AGREE.equals(auditProcess.getApExecResult())) {
            //AN_EXEC_MODE_POST 审批岗位执行模式：岗位审批权限控制执行
            if(IConstants.AuditConstant.AN_EXEC_MODE_POST.equals(auditRoute.getAnExecMode())) {
                if(judgeAsPrivilegeToUpdateInfo(auditTask, auditStation, originalAuditProcess, auditProcess)) {
                    return;
                }
            }
            approveAuditProcessToNextStation(stationIndex, nodeIndex, branchNo,  auditStationList, 
                    auditNodeList, originalAuditProcess, auditProcess, auditTask);
            //AP_EXEC_RESULT_REFUSE 审批结果：2-拒绝
        }else if(IConstants.AuditConstant.AP_EXEC_RESULT_REFUSE.equals(auditProcess.getApExecResult())) {
            refuse(originalAuditProcess, auditProcess, auditTask);
        }else{//页面暂时不开放驳回功能
            reject(stationIndex, nodeIndex, branchNo, auditNodeList, 
                    auditStationList, originalAuditProcess, auditProcess, auditTask);
        }
    }
    
    /**
     * 获取当前审批岗位是当前审批节点的第几岗
     *
     * @param auditStationList 当前审批节点的审批岗位集合
     * @param auditStation 当前审批岗位信息
     * @return
     */
    private int getStationIndex(List<AuditStation> auditStationList, AuditStation auditStation) {
        for(int i = 0; i < auditStationList.size(); i++) {
            if(auditStation.getId().equals(auditStationList.get(i).getId())) {
                return i;
            }
        }
        return 0;
    }
    
    /**
     * 获取当前审批节点时当前审批路线的第几个节点
     *
     * @param auditNodeList 当前审批路线的审批节点集合
     * @param auditNode 当前审批节点信息
     * @return
     */
    private int getNodeIndex(List<AuditNode> auditNodeList, AuditNode auditNode) {
        for(int i  = 0; i < auditNodeList.size(); i ++) {
            if(auditNode.getId().equals(auditNodeList.get(i).getId())) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 根据待审批金额判断审批流程是否结束
     *      <br>业务发起的整个批次需要审批的金额与当前审批岗位的审批权限比较
     *      <br>如果当前审批岗位的审批权限大于等于待审批的金额，则流程结束
     *      <br>如果流程结束，更新审批流程和更新审批任务状态
     * @param auditTask 审批任务
     * @param auditStation 当前审批岗位
     * @param originalAuditProcess 待审批的审批流程
     * @param auditProcess 
     * @return true 流程结束
     *  <br> false 流程未结束 
     */
    private boolean judgeAsPrivilegeToUpdateInfo(AuditTask auditTask, AuditStation auditStation, 
            AuditProcess originalAuditProcess, AuditProcess auditProcess)throws Exception {
        //待审批金额小于岗位审批权限，审批通过
        if(MathScaleUtil.isLessThan(auditTask.getWaitAuditAmt(), auditStation.getAsPrivilege())) {
            //审批通过
            auditApproved(auditTask, originalAuditProcess, auditProcess);
            //回调方法： 最后一岗审批通过调用此方法
            AuditTriggerUtil.lastStationAdopt(auditTask.getProdNo(), auditTask.getBatchId());
            return true;
        }
        return false;
    }
    
    /**
     * 审批通过
     *
     * @param auditTask
     * @param originalAuditProcess
     * @param auditProcess
     */
    private void auditApproved(AuditTask auditTask, AuditProcess originalAuditProcess, AuditProcess auditProcess)throws Exception {
        updateAuditProcess(originalAuditProcess, auditProcess);
        //AT_STATUS_SUCCESS 审批任务状态：2-审批通过
        updateAuditTask(auditTask, IConstants.AuditConstant.AT_STATUS_SUCCESS);
    }
    
    /**
     * 提交审批流程到下一个岗位
     *  
     * @param stationIndex
     * @param branchNo
     * @param auditStationList
     * @param auditNodeList
     * @param originalAuditProcess
     * @param auditProcess
     */
    private void approveAuditProcessToNextStation(int stationIndex, int nodeIndex, String branchNo,  List<AuditStation> auditStationList, 
            List<AuditNode> auditNodeList, AuditProcess originalAuditProcess, AuditProcess auditProcess, AuditTask auditTask)throws Exception {
        //第一个节点的第一个岗位
        if(0 == stationIndex && stationIndex<(auditStationList.size()-1) && nodeIndex == auditNodeList.size()-1) {
            AuditTriggerUtil.firstStationAdopt(auditTask.getProdNo(), auditTask.getBatchId());
        }
        AuditStation nextStation = null;
        //岗位是当前节点的最后一岗
        if(stationIndex == auditStationList.size()-1) {
            if(0 == nodeIndex) {
                //当前节点是最后一个节点，审批通过
                auditApproved(auditTask, originalAuditProcess, auditProcess);
                AuditTriggerUtil.lastStationAdopt(auditTask.getProdNo(), auditTask.getBatchId());
                return;
            }
            AuditNode nextNode = auditNodeList.get(nodeIndex-1);
            auditStationList = auditCommonService.getAuditStationByAnId(nextNode.getId());
            if(CollectionUtils.isEmpty(auditStationList)) {
                auditApproved(auditTask, originalAuditProcess, auditProcess);
                AuditTriggerUtil.lastStationAdopt(auditTask.getProdNo(), auditTask.getBatchId());
                return;
            }
            nextStation = auditStationList.get(0);
        }else{
            nextStation = auditStationList.get(stationIndex+1);
        }
        updateAndAddNextAuditProcess(originalAuditProcess, auditProcess, nextStation, branchNo);
    }
    
    /**
     * 拒绝审批流程
     *  <br>更新审批流程和审批流程状态，置为失败
     *  
     * @param originalAuditProcess
     * @param auditProcess
     * @param auditTask
     */
    private void refuse(AuditProcess originalAuditProcess, AuditProcess auditProcess, AuditTask auditTask)throws Exception {
        updateAuditProcess(originalAuditProcess, auditProcess);
        //AT_STATUS_FAIL 审批任务状态：3-审批不通过
        updateAuditTask(auditTask, IConstants.AuditConstant.AT_STATUS_FAIL);
        AuditTriggerUtil.anyStationReject(auditTask.getProdNo(), auditTask.getBatchId());
    }
    
    /**
     * 驳回审批流程
     * <br>审批将被退回至上一岗，审批继续
     * <br>若本身就是第一岗位，即审批不通过，审批结束
     *
     * @param stationIndex 
     * @param nodeIndex
     * @param branchNo
     * @param cacheMap
     * @param auditNodeList
     * @param auditStationList
     * @param originalAuditProcess
     * @param auditProcess
     * @param auditTask
     */
    private void reject(int stationIndex, int nodeIndex, String branchNo, 
            List<AuditNode> auditNodeList, List<AuditStation> auditStationList, AuditProcess originalAuditProcess, 
            AuditProcess auditProcess, AuditTask auditTask)throws Exception {
        AuditStation beforeStation = null;
        if(0 == stationIndex) {
            if(nodeIndex == auditNodeList.size()-1) {
                refuse(originalAuditProcess, auditProcess, auditTask);
                return;
            }else{
                AuditNode beforeNode = auditNodeList.get(nodeIndex+1);
                List<AuditStation> asList = auditCommonService.getAuditStationByAnId(beforeNode.getId());
                beforeStation = asList.get(asList.size()-1);
            }
        }else{
            beforeStation = auditStationList.get(stationIndex-1);
        }
        updateAndAddNextAuditProcess(originalAuditProcess, auditProcess, beforeStation, branchNo);
    }
    
   
    
    /**
     * 更新当前审批流程并且新增下一个审批流程
     *
     * @param originalAuditProcess
     * @param auditProcess
     * @param nextStation
     * @param branchNo
     */
    private void updateAndAddNextAuditProcess(AuditProcess originalAuditProcess, AuditProcess auditProcess, AuditStation nextStation, String branchNo)throws Exception {
        updateAuditProcess(originalAuditProcess, auditProcess);
        saveAuditProcess(originalAuditProcess, nextStation, branchNo);
//        AuditTask auditTask = auditCommonService.getAuditTaskByAtId(originalAuditProcess.getAtId());
//        AuditRoute auditRoute = auditCommonService.getAuditRouteById(auditTask.getArId());
    }
    
    /**
     * 更新审批流程
     *
     *  @param originalAuditProcess 待更新的审批流程信息
     * @param auditProcess 前台审批流程信息
     */
    private void updateAuditProcess(AuditProcess originalAuditProcess, AuditProcess auditProcess)throws Exception {
        originalAuditProcess.setApExecPersonId(auditProcess.getApExecPersonId());
        originalAuditProcess.setApExecPersonName(auditProcess.getApExecPersonName());
        //AP_STATUS_SUCCESS 审批状态：3-受理完毕
        originalAuditProcess.setApStatus(IConstants.AuditConstant.AP_STATUS_SUCCESS);
        originalAuditProcess.setApExecResult(auditProcess.getApExecResult());
        originalAuditProcess.setApExecRemark(auditProcess.getApExecRemark());
        originalAuditProcess.setApDoneDt(DateTimeUtil.getWorkdayString());
        originalAuditProcess.setApDoneTime(DateTimeUtil.getTime());
        AuditProcessDao apDao=new AuditProcessDao();
        apDao.modifyAuditProcess(originalAuditProcess);
    }
    /**
     * 保存审批流程
     *
     * @param originalAuditProcess 
     * @param nextStation
     */
    private void saveAuditProcess(AuditProcess originalAuditProcess, AuditStation nextStation, String branchNo)throws Exception {
        AuditProcess nextProcess = new AuditProcess();
        nextProcess.setAtId(originalAuditProcess.getAtId());
        nextProcess.setApCommitStationId(String.valueOf(originalAuditProcess.getApExecStationId()));
        nextProcess.setApCommitStationName(originalAuditProcess.getApExecStationName());
        nextProcess.setApCommitPersonId(originalAuditProcess.getApExecPersonId());
        nextProcess.setApCommitPersonName(originalAuditProcess.getApExecPersonName());
        nextProcess.setApCommitBrchNo(originalAuditProcess.getApExecBrchNo());
        nextProcess.setApCommitRemark(originalAuditProcess.getApExecRemark());
        nextProcess.setApExecStationId(nextStation.getId());
        nextProcess.setApExecStationName(nextStation.getAsName());
        nextProcess.setApExecBrchNo(branchNo);
        //AP_STATUS_UNTREATED 审批状态：1-未受理
        nextProcess.setApStatus(IConstants.AuditConstant.AP_STATUS_UNTREATED);
        nextProcess.setApCommitDt(DateTimeUtil.getWorkdayString());
        nextProcess.setApCommitTime(DateTimeUtil.getTime());
        nextProcess.setSortNo(originalAuditProcess.getSortNo()+1);
        nextProcess.setId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
        AuditProcessDao apDao=new AuditProcessDao();
        apDao.addAuditProcess(nextProcess);
    }
    
    /**
     * 更新审批任务
     *
     * @param auditTask 审批任务
     * @param atStatus  审批任务状态
     */
    private void updateAuditTask(AuditTask auditTask, String atStatus)throws Exception {
        auditTask.setAtStatus(atStatus);
        auditTask.setAtDoneDt(DateTimeUtil.getWorkdayString());
        auditTask.setAtDoneTime(DateTimeUtil.getTime());
        auditCommonService.updateAuditTask(auditTask);
    }
    
    
    @Override
    public List<AuditProcess> getAuditProcessBySearchBean(AuditProcessSearchBean bean,
            Page page) throws Exception {
        AuditProcessDao apDao=new AuditProcessDao();
        return apDao.getAuditProcessBySearchBean(bean, page);
    }

    @Override
    public void deleteAuditProcess(List<AuditProcess> auditProcessList)
            throws Exception {
        AuditProcessDao apDao=new AuditProcessDao();
        apDao.deleteAuditProcess(auditProcessList);
    }

    @Override
    public AuditProcess getAuditProcessById(String apId) throws Exception {
        AuditProcessDao apDao=new AuditProcessDao();
        return apDao.getAuditProcess(apId);
    }

    
    public void setAuditCommonService(IAuditCommonService auditCommonService) {
        this.auditCommonService = auditCommonService;
    }

   
    
    
}
