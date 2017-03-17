package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.common.bean.TaskSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.interfaces.ITaskLogService;
import com.herongtech.exception.impl.BizAppException;
@Scope("prototype")
@Controller
@RequestMapping("/taskLogController")
public class TaskLogController extends BaseController {

	@RequestMapping(params = "method=search")
	public ModelAndView search(TaskSearchBean bean,Page page) throws BizAppException{
		page.activeCommand();
		ModelAndView mv = new ModelAndView("system/dayend/taskLog");
		ITaskLogService logService = ServiceFactory.getTaskLogService();
		String taskName = bean.getTaskName();
		try {
			/*
			if(StringUtils.isNotBlank(bean.getMinWorkday())){
				bean.setMinWorkday(bean.getMinWorkday().replace("-", ""));
			}
			if(StringUtils.isNotBlank(bean.getMaxWorkday())){
				bean.setMaxWorkday(bean.getMaxWorkday().replace("-", ""));
			}*/
			if(StringUtils.isNotBlank(bean.getTaskName())){
				bean.setTaskName("%"+taskName+"%");
			}
			OrderBean order=new OrderBean("workday",false);//构造方法指定升序降序
	        bean.appendOrder(order);
			bean.setDfaultSrchTbAliasName("log");
			bean.addSqlPropretyMapping("minWorkday", "workday");
			bean.addSpecialOpertion("minWorkday", BaseSearchBean.MORE_AND_EQUAL);
			bean.addSqlPropretyMapping("maxWorkday", "workday");
			bean.addSpecialOpertion("maxWorkday", BaseSearchBean.LESS_AND_EQUAL);
			bean.addSpecialOpertion("taskName", BaseSearchBean.LIKE);
			mv.addObject("resultList", logService.getTaskLogList(bean, page));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException("日终日志查询失败，"+e.getMessage());
		}
		bean.setTaskName(taskName);
		mv.addObject("bean", bean);
		return mv;
	}
}
