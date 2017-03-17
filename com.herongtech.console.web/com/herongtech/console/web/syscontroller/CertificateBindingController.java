package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.serviceloader.ServiceFactoryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ICertificateBindingService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.EcdsApDataDao;
import com.herongtech.rc.domain.dao.EcdsBankDataDao;
import com.herongtech.rc.draft.DraftService;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.service.RcServiceFactory;

/**数字证书行号绑定关系变更申请报文065*/
@Scope("prototype")
@Controller
@RequestMapping("/certificateBindingController")
public class CertificateBindingController extends BaseController{

	/**页面跳转*/
	@RequestMapping(params="method=list")
	public ModelAndView ShowCertificateBinding(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/certificatebinding/certificatebinding");
	
		return mv;
	}
	
	/**查询数据*/
	@RequestMapping(params="method=select")
	public ModelAndView SelectCertificateBinding(Page page, String row_number,String certBindingStatus)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		String branchbankno = user.getBrchBankNo();
		
		List<EcdsBankData> ecdsbankDatas = new ArrayList<EcdsBankData>();
		//分页开始位置
		int startIndex = page.getCurrentResult();
		page.setCurrentResult(startIndex);
		ICertificateBindingService cbs=ServiceFactory.getCertificateBindingService();
		IDB session =  DBFactory.getDB();
		try {
			session.beginTransaction();
			ecdsbankDatas = cbs.getEcdsBankList(page, branchbankno, certBindingStatus,row_number);
			session.endTransaction();
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
		}

		mv.setViewName("system/certificatebinding/certificatebinding");
		mv.addObject("ecdsbankDatas",ecdsbankDatas);
		mv.addObject("certBindingStatus",certBindingStatus);
		mv.addObject("row_number", row_number);
		mv.addObject("page",page);
		return mv;
	}
	
	
	
	/**证书邦定*/
	@RequestMapping(params="method=update")
	@ResponseBody
	public AjaxJson BindingRequestUpdate(Page page, HttpServletRequest request,
	HttpServletResponse response)throws Exception{
		AjaxJson aj = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		String msgid = MsgUtil.getMsgId("065", user.getBrchBankNo());//传入报文编号和登陆者行号得到报文标识号
		SysMgrInfoVo vo = new SysMgrInfoVo();
		String bankno = request.getParameter("row_number");//行号逗号分开的字符串
		String[] ptcpts = bankno.split(","); //行号
		String altrnTp = request.getParameter("operationType");
		List<String> ptcpt = new ArrayList<String>();
		for (int i = 0; i < ptcpts.length; i++) {
			ptcpt.add(ptcpts[i]);
		}
		vo.setReqMsgId(msgid);//报文标识号
		vo.setFromBankNo(user.getBrchBankNo());//发起人的行号
		vo.setPtcpt(ptcpt);
		vo.setAltrnTp(altrnTp);
		
		
		RgctMethod method = new RgctMethod();
		method.setId(Long.parseLong("1065"));
		
		DraftService ds = RcServiceFactory.getDraftService();
		String result = ds.buildAndSend(vo, method);
		aj.setMsg(result);
		return aj;
	}
}
