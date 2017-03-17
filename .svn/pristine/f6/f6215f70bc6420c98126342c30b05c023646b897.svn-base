package com.herongtech.console.web.syscontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.rc.domain.bean.EcdsApData;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.EcdsApDataDao;
import com.herongtech.rc.draft.DraftService;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.service.RcServiceFactory;
@Scope("prototype")
@Controller
@RequestMapping("/manpowerLogonController")
public class ManpowerLogonController {

	/**人工登出*/
	@RequestMapping(params="method=logon")
	@ResponseBody
	public AjaxJson Logon(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		AjaxJson aj = new AjaxJson();
		System.out.println(request.getParameter("type"));
		EcdsApDataDao ead = new EcdsApDataDao();
		String actntpmk = request.getParameter("type");
		SysMgrInfoVo vo = new SysMgrInfoVo();
		String meetCode = "3051000001";//接入点号
		EcdsApData  ecdsapdata = ead.getEcdsApData(meetCode);
		System.out.println("result==="+ecdsapdata.getInureDate());
		vo.setActnTpMk(actntpmk);    //登陆登出类型TM00(登入)/TM01(登出)
		vo.setOrgnlIdCd(ecdsapdata.getEnterIdentify());   //原识别码
		vo.setIdCd("");        //新识别码
		
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		vo.setFromBankNo(user.getBrchBankNo());
		System.out.println("user.getBankNo()=="+user.getBankNo());
		String msgid = MsgUtil.getMsgId("051", user.getBrchBankNo());  //报文编号    当前登录人的行号(暂时行号写死)
		//String method = MsgUtil.getMethodName(busiType, isRegress); 
		RgctMethod method = new RgctMethod();
		vo.setReqMsgId(msgid);   //报文标识
		method.setId(Long.parseLong("1051"));
		DraftService ds = RcServiceFactory.getDraftService();
		
		
		String result = ds.buildAndSend(vo, method);
		if("".equals(result)||null==result){
			aj.setMsg("人工退出失败");
		}else{
			aj.setMsg("人工退出成功");			
		}
		
		return aj;
	}
}
