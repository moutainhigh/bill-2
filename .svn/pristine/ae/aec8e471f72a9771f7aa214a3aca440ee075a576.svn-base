package com.herongtech.console.web.syscontroller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.common.bean.TaskPool;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.interfaces.IDayEndSchedulerService;
import com.herongtech.console.service.common.dayend.interfaces.ITaskPoolService;

@Scope("prototype")
@Controller
@RequestMapping("/dayendController")
public class DayEndController extends BaseController {
    
    
    @RequestMapping(params = "method=toTaskMonitor")
    public ModelAndView toTaskMonitor() throws Exception {
        ModelAndView mv = new ModelAndView("system/dayend/dayendMonitor");
        return mv;
    }
    
    /* 
    @RequestMapping(params = "method=taskMonitor")
    public ModelAndView taskMonitor(String taskType) throws Exception {
        ModelAndView mv = new ModelAndView("system/dayend/dayendMonitor");
        ITaskPoolService taskPoolService=ServiceFactory.getTaskPoolService();
        List list=taskPoolService.getActiveTaskpools(taskType);
        mv.addObject("data",dataTranferMap(list));
        return mv;
    }*/
    
    @RequestMapping(params = "method=taskMonitor")
    public ModelAndView taskMonitor(String taskType) throws Exception {
        ModelAndView mv = new ModelAndView("system/dayend/dayendMonitor");
        ITaskPoolService taskPoolService=ServiceFactory.getTaskPoolService();
        List<TaskPool> list=taskPoolService.getActiveTaskpools(taskType);
        mv.addObject("resultList",list);
        return mv;
    }
    
    @RequestMapping(params = "method=executeTask")
    @ResponseBody
    public AjaxJson executeTask(String taskNo,String taskType) throws Exception {
    	AjaxJson json = new AjaxJson();
    	IDayEndSchedulerService scheduleService=ServiceFactory.getDayEndSchedulerService();
        try{
        	scheduleService.runOneTask(taskNo,taskType);
        	json.setSuccess(true);
            json.setMsg("任务执行成功！");
        }catch(Exception e){
        	json.setSuccess(false);
    		json.setMsg(e.getMessage());
        }
        return json;
    }
}
