package com.herongtech.console.web.syscontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.draft.DraftService;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.service.RcServiceFactory;

/**
 * @author 李江涛
 * 自由格式报文发送
 *
 */
@Scope("prototype")
@RequestMapping("/sysFreeFormatController")
@Controller
public class SysFreeFormatController {
	/**页面跳转*/
	@RequestMapping(params="method=main")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ModelAndView mv = new ModelAndView();
		String branchbankno = ResourceUtil.getSessionLoginfo().getBrchBankNo();
		Map<String,String> map = new HashMap<String,String>();
		map.put("branchbankno",branchbankno);
		mv.addObject("map", map);
		mv.setViewName("system/freeformatdraftsend/freeformatdraftsend");
		return mv;
	}
	
	
	/**
	 * 发送039自由格式报文
	 * @param page
	 * @param request
	 * @param response
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(params="method=send")
	@ResponseBody
	public String send(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		String BorderLevel = request.getParameter("recevieType");
		String contents = request.getParameter("drafttext");
		String SendBankNo = request.getParameter("bankNo");
		String receiveBankNo = request.getParameter("meetcode");

		
		SysMgrInfoVo vo = new SysMgrInfoVo();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		if(user.getBrchBankNo().equals(SendBankNo)){
			String msgid = MsgUtil.getMsgId("039", user.getBrchBankNo());
			vo.setReqMsgId(msgid);
			vo.setBorderLevel(BorderLevel);
			vo.setContents(contents);
			vo.setSendBankNo(SendBankNo);
			vo.setFromBankNo(SendBankNo);
			if("BC00".equals(BorderLevel)){
				vo.setReceiveBankNo(receiveBankNo);
			}
			
			RgctMethod method = new RgctMethod();
			method.setId(Long.parseLong("1039"));
			DraftService ds = RcServiceFactory.getDraftService();
			String result = ds.buildAndSend(vo, method);   //组装并发送自由格式报文
			return "发送成功";
		}
		
		return "登陆用户有误";
	}
	
	
}
