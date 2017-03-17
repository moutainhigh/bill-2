package com.herongtech.console.service.common.audit;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IConstants.AuditConstant;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.domain.common.audit.bean.AuditStation;
import com.herongtech.console.domain.common.audit.dao.AuditStationDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditCommonService;
import com.herongtech.console.service.common.audit.interfaces.IAuditStationService;


public class AuditStationService implements IAuditStationService{
    
    private IAuditCommonService auditCommonService;

    public List<AuditStation> getAuditStationByArId(String arId)throws Exception{
        AuditStationDao dao=new AuditStationDao();
        return dao.getAuditStationByArId(arId);
    }
    
    @Override
    public List<AuditStation> getAuditStationByAnId(String anId)
            throws Exception {
        return  auditCommonService.getAuditStationByAnId(anId);
    }

    @Override
    public AuditStation getAuditStation(String asId) throws Exception {
        return auditCommonService.getAuditStationById(asId);
    }

    @Override
    public void addAuditStation(AuditStation auditStation) throws Exception {
        //判断审批路线下是否存在尚未结束的审批，若存在，则不能做相应的操作
        auditCommonService.judgeAuditTaskProcessStatus(auditStation.getArId());
        //获取审批节点下所有的审批岗位
        List<AuditStation> auditStationList = getAuditStationByAnId(auditStation.getAnId());
        //获取审批岗位最大的序号
        int sortNo = 0;
        for(AuditStation as : auditStationList) {
            int temp=Integer.parseInt(as.getSortNo());
            if( temp> sortNo) {
                sortNo = temp;
            }
        }
        auditStation.setSortNo((++sortNo)+"");
        auditStation.setId(ServiceFactory.getSequenceService().getPrimaryKeyValue());
        AuditStationDao dao=new AuditStationDao();
        dao.addAuditStation(auditStation);
    }

    @Override
    public void editAuditStation(AuditStation auditStation) throws Exception {
        AuditStationDao dao=new AuditStationDao();
      //判断审批路线下是否存在尚未结束的审批，若存在，则不能做相应的操作
        List<AuditStation> list = dao.getAuditStationByArId(auditStation.getArId());
        double authMoney = 0.0;
        for(AuditStation auditSttn:list){
            if(auditSttn.getId().equals(auditStation.getId())){
                authMoney = auditSttn.getAsPrivilege();
            }
        }
        for(AuditStation auditSttn:list){
            if(!auditSttn.getId().equals(auditStation.getId()) && MathScaleUtil.isMoreThan(auditSttn.getAsPrivilege(), authMoney)){
                auditCommonService.judgeAuditTaskProcessStatus(auditStation.getArId());
            }
            if(auditSttn.getId().equals(auditStation.getId()) && MathScaleUtil.isMoreThan(auditSttn.getAsPrivilege(), auditStation.getAsPrivilege())){
                auditCommonService.judgeAuditTaskProcessStatus(auditStation.getArId());
            }
        }
        
        dao.modifyAuditStation(auditStation);
    }

    @Override
    public void deleteAuditStation(String asId, String arId) throws Exception {
        auditCommonService.judgeAuditTaskProcessStatus(arId);
        auditCommonService.deleteAuditReRoleAndUser(asId);
        AuditStationDao dao=new AuditStationDao();
        dao.deleteAuditStation(asId);
    }

    @Override
    public void moveAuditStation(String id, String anId, String arId,
            Integer upOrDown) throws Exception {
      //判断审批路线下是否存在尚未结束的审批，若存在，则不能做相应的操作
        auditCommonService.judgeAuditTaskProcessStatus(arId);
        //获取审批节点下所有的审批岗位
        List<AuditStation> auditStationList = getAuditStationByAnId(anId);
        //如果只有一个岗位，则直接返回
        if(CollectionUtils.isEmpty(auditStationList) || IConstants.AuditConstant.ONE == auditStationList.size()) {
            return;
        }
        //需要移动的节点
        AuditStation needToMoveStation = null;
        //需要移动的节点的旁边节点
        AuditStation sideStation = null;
        //需要移动节点的序号
        String needToMoveSortNo = "";
        //需要移动节点的旁边节点的序号
        String sideSortNo = "";
        //MOVE_UP 上移
        if(AuditConstant.MOVE_UP == upOrDown) {
            //当需要移动的岗位已经是第一个岗位，则不需要移动
            if(id.equals(auditStationList.get(0).getId())) {
                return;
            }
            //从第二个岗位开始判断
            for(int i = 1, t = 0; i < auditStationList.size(); i++, t++) {
                AuditStation auditStation = auditStationList.get(i);
                //找到需要移动的节点
                if(id.equals(auditStation.getId())) {
                    needToMoveStation = auditStation;
                    sideStation = auditStationList.get(t);
                    //获取需要移动的节点的序号
                    needToMoveSortNo = needToMoveStation.getSortNo();
                    //获取需要移动节点的上一个节点的序号
                    sideSortNo = sideStation.getSortNo();
                    break;
                }
            }
        }else{
            //下移
            //获取审批岗位的个数
            int count = auditStationList.size();
            //如果需要移动的岗位是最后一个岗位，则不需要移动
            if(id.equals(auditStationList.get(count-1).getId())) {
                return;
            }
            //从倒数第二个岗位开始判断
            for(int i = count-2, t = count-1; i >= 0; i--, t--) {
                AuditStation auditStation = auditStationList.get(i);
                //找到需要移动的节点
                if(id.equals(auditStation.getId())) {
                    needToMoveStation = auditStation;
                    sideStation = auditStationList.get(t);
                    //获取需要移动的节点的序号
                    needToMoveSortNo = needToMoveStation.getSortNo();
                    //获取需要移动节点的下一个节点的序号
                    sideSortNo = sideStation.getSortNo();
                    break;
                }
            }
        }
        //更新序号
        needToMoveStation.setSortNo(sideSortNo);
        sideStation.setSortNo(needToMoveSortNo);
        AuditStationDao dao=new AuditStationDao();
        dao.modifyAuditStation(needToMoveStation);
        dao.modifyAuditStation(sideStation);
        
    }

    @Override
    public void judgeAuditStationPrivilege(String arId) throws Exception {
        List<AuditStation> auditStationList = getAuditStationByArId(arId);
        //如果没有审批岗位，则直接返回
        if(CollectionUtils.isEmpty(auditStationList)) {
            return;
        }
        for(AuditStation as : auditStationList) {
            //只要审批权限大于0，则直接返回
            if(as.getAsPrivilege() > 0) {
                return;
            }
        }
        throw new Exception("审批路线下的审批权限不允许全为0，请确认后再操作");
    }

    
    public void setAuditCommonService(IAuditCommonService auditCommonService) {
        this.auditCommonService = auditCommonService;
    }

    
}
