package com.herongtech.console.web.syscontroller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.util.SendEventClient;
import com.herongtech.mqchannel.server.MqTask;

/**
 * 报文补发和补收
 * @author fqz
 *
 */
@Scope("prototype")
@RequestMapping("/draftLaunchAndInceptController")
@Controller
public class DraftLaunchAndInceptController {
	/**页面跳转*/
	@RequestMapping(params="method=main")
	public ModelAndView main(){
		ModelAndView mv = new ModelAndView("system/draftLaunchAndIncept/draftLaunchAndIncept");
		return mv;
	}
	/**
	 * 补发报文
	 * @return
	 */
	@RequestMapping(params="method=send")
	@ResponseBody
	public AjaxJson send(String drafttext){
		AjaxJson json = new AjaxJson();
		String isRealTime = "0";
		try {
			String draftNo = drafttext.substring(87,90);
			if("039".equals(draftNo) || "051".equals(draftNo) || "065".equals(draftNo)){
				isRealTime = "1";
			}
			SendEventClient.send("ECDS"+isRealTime+draftNo, drafttext);
			json.setSuccess(true);
			json.setMsg("报文发送完成！");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("补发异常，报文发送失败！");
		}
		return json;
	}
	/**
	 * 补收报文
	 * @return
	 */
	@RequestMapping(params="method=receive")
	@ResponseBody
	public AjaxJson receive(String drafttext){
		AjaxJson json = new AjaxJson();
		MqTask mqTask = new MqTask();
		try {
			mqTask.setRequestMsg(drafttext);
			//处理请求信息
			mqTask.dealRequest();
			json.setSuccess(true);
			json.setMsg("报文接收成功！");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("补收异常，报文接收失败！");
		}
		return json;
	}
}
