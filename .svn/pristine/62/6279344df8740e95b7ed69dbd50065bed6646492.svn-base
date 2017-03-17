package com.herongtech.console.web.syscontroller.audit;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditProcess;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.audit.bean.AuditTaskSearchBean;
import com.herongtech.console.service.ServiceFactory;
@Scope("prototype")
@Controller
@RequestMapping("/submittedAuditController")
public class SubmittedAuditController extends BaseController {

	@RequestMapping(params = "method=search")
	public ModelAndView search(Page page,AuditTaskSearchBean searchBean)throws Exception{
		ModelAndView mv = new ModelAndView("system/audit/submitted_audit_list");
		page.activeCommand();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setAtAuthorId(user.getUserId());
		searchBean.addSqlPropretyMapping("minAtCreateDt", "atCreateDt");
		searchBean.addSpecialOpertion("minAtCreateDt", BaseSearchBean.MORE_AND_EQUAL);
		searchBean.addSqlPropretyMapping("maxAtCreateDt", "atCreateDt");
		searchBean.addSpecialOpertion("maxAtCreateDt", BaseSearchBean.LESS_AND_EQUAL);
		searchBean.addSqlPropretyMapping("minAtDoneDt", "atDoneDt");
		searchBean.addSpecialOpertion("minAtDoneDt", BaseSearchBean.MORE_AND_EQUAL);
		searchBean.addSqlPropretyMapping("maxAtDoneDt", "atDoneDt");
		searchBean.addSpecialOpertion("maxAtDoneDt", BaseSearchBean.LESS_AND_EQUAL);
		mv.addObject("resultList", ServiceFactory.getAuditTasksService().getAuditTaskListBySearchBean(page, searchBean));
		mv.addObject("bean", searchBean);
		if(StringUtils.isNotBlank(searchBean.getProdNo())){
			mv.addObject("prodName", ServiceFactory.getProductService().getProductInfoByProdNo(searchBean.getProdNo()).getProdName());
		}else{
			mv.addObject("prodName", "");
		}
		return mv;
	}
	@RequestMapping(params = "method=goDetailInfo")
	public ModelAndView goDetailInfo(String id,String checkFlag) throws Exception{
		ModelAndView mv = new ModelAndView("system/audit/audit_detail");
        AuditTask task=ServiceFactory.getAuditTasksService().queryAuditTaskInfo(id);
        String dataUrl=task.getEntityName()+"&"+task.getEntityService()+"="+task.getBatchId()+"&isReadonly=1";
        List<AuditProcess> processList=ServiceFactory.getAuditProcessService().getAuditProcessByAtId(id);
        mv.addObject("processList", processList);
        mv.addObject("dataUrl", dataUrl);
        mv.addObject("atId", id);
        mv.addObject("checkFlag", checkFlag);
        return mv;
	}
}
