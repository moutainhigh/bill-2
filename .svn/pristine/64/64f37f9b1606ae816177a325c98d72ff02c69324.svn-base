package com.herongtech.console.web.syscontroller.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditProcess;
import com.herongtech.console.domain.common.audit.bean.AuditProcessDto;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.service.ServiceFactory;

/**
 * 产品审批管理
 */
@Scope("prototype")
@Controller
@RequestMapping("/auditProcessController")
public class AuditProcessController extends BaseController {

    @RequestMapping(params = "method=searchAuditProcess")
    public ModelAndView searchAuditProcess(Page page,String apCommitDate) throws Exception{
        ModelAndView mv = new ModelAndView("system/audit/product_audit_manager_main");
        page.activeCommand();
        List<AuditProcess> processList=ServiceFactory.getAuditProcessService().searchAuditProcessByUserNo(page, ResourceUtil.getSessionLoginfo(), null, apCommitDate);
        
        List<AuditProcessDto> dto = new ArrayList<AuditProcessDto>();
        if(processList != null && processList.size()>0){
            for(AuditProcess ap :processList) {
                AuditProcessDto apd = new AuditProcessDto();
                BeanUtils.copyProperties(apd, ap);
                //获取审批任务详细信息
                AuditTask at = ServiceFactory.getAuditTasksService().queryAuditTaskInfo(ap.getAtId());
                //任务编号
                apd.setAtId(ap.getAtId());
                //任务分类id
                apd.setProdNo(at.getProdNo());
                //任务分类名称
                apd.setProdName(at.getProdNo());
                //批次号
                apd.setBatchNo(at.getBatchNo());
                //任务提交人id
                apd.setAtAuthorId(at.getAtAuthorId());
                //任务提交人名称
                apd.setAtAuthorName(at.getAtAuthorName());
                //任务提交人所在机构id
                apd.setBrchNo(at.getBrchNo());
                //任务提交人所在机构名称
                apd.setBrchName(at.getBrchNo());
                apd.setApCommitBrchName(at.getBrchNo());
                apd.setBatchId(at.getBatchId());
                dto.add(apd);
            }
        }
        mv.addObject("processList", dto);
        mv.addObject("page", page);
        return mv;
    }
    
    
    @RequestMapping(params = "method=toAudit")
    public ModelAndView toAudit(String apId,String atId,String asId) throws Exception{
        ModelAndView mv = new ModelAndView("system/audit/audit_detail");
        AuditTask task=ServiceFactory.getAuditTasksService().queryAuditTaskInfo(atId);
        String dataUrl=task.getEntityName()+"&"+task.getEntityService()+"="+task.getBatchId()+"&isReadonly=1";
        List<AuditProcess> processList=ServiceFactory.getAuditProcessService().getAuditProcessByAtId(atId);
        mv.addObject("processList", processList);
        mv.addObject("dataUrl", dataUrl);
        mv.addObject("apId", apId);
        mv.addObject("asId", asId);
        mv.addObject("atId", atId);
        
        return mv;
    }
    
    @RequestMapping(params = "method=submitAuditResult")
    public ModelAndView submitAuditResult(String apId,String asId,String apExecResult,String apExecRemark) throws Exception{
        UserLoginfo user=ResourceUtil.getSessionLoginfo();
        AuditProcess auditProcess=new AuditProcess();
        auditProcess.setApExecStationId(asId);
        auditProcess.setId(apId);
        auditProcess.setApExecResult(apExecResult);
        auditProcess.setApExecRemark(apExecRemark);
        auditProcess.setApExecPersonId(user.getUserId());
        auditProcess.setApExecPersonName(user.getUserName());
        auditProcess.setApExecBrchNo(user.getBranchNo());
        ServiceFactory.getAuditProcessService().addAuditProcess(auditProcess);
        
        return searchAuditProcess(new Page(), null);
    }
    
    
}
