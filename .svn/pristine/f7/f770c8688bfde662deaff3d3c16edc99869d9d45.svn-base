package com.herongtech.console.web.syscontroller.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
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
import com.herongtech.console.domain.common.audit.bean.AuditProcessDto;
import com.herongtech.console.domain.common.audit.bean.AuditProcessSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.product.IProductService;
@Scope("prototype")
@Controller
@RequestMapping("/participantAuditController")
public class ParticipantAuditController extends BaseController {

	@RequestMapping(params = "method=search")
	public ModelAndView search(Page page,AuditProcessSearchBean  bean)throws Exception{
		ModelAndView mv = new ModelAndView("system/audit/participant_audit_list");
		page.activeCommand();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		bean.setApExecPersonId(user.getUserId());
		bean.addSqlPropretyMapping("minApDoneDt", "apDoneDt");
		bean.addSpecialOpertion("minApDoneDt", BaseSearchBean.MORE_AND_EQUAL);
		bean.addSqlPropretyMapping("maxApDoneDt", "apDoneDt");
		bean.addSpecialOpertion("maxApDoneDt", BaseSearchBean.LESS_AND_EQUAL);
		List<AuditProcess> processList = ServiceFactory.getAuditProcessService().getAuditProcessBySearchBean(bean, page);
		List<AuditProcessDto> dtoList = new ArrayList<AuditProcessDto>();
		IProductService prodService = ServiceFactory.getProductService();
		for(AuditProcess ap : processList){
			AuditTask at = ServiceFactory.getAuditTasksService().queryAuditTaskInfo(ap.getAtId());
			AuditProcessDto apDto = new AuditProcessDto();
			BeanUtils.copyProperties(apDto, ap);
			apDto.setProdNo(at.getProdNo());
			apDto.setProdName(prodService.getProductInfoByProdNo(at.getProdNo()).getProdName());
			apDto.setWaitAuditAmt(at.getWaitAuditAmt());
			apDto.setAtCreateDt(at.getAtCreateDt());
			apDto.setAtCreateTime(at.getAtCreateTime());
			apDto.setAtDoneDt(at.getAtDoneDt());
			apDto.setAtDoneTime(at.getAtDoneTime());
			dtoList.add(apDto);
		}
		mv.addObject("resultList", dtoList);
		mv.addObject("bean", bean);
		return mv;
	}
}
