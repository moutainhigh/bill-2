package com.herongtech.console.web.busicontroller.common;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.disc.bean.BillAllInfoBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.exception.impl.BizAppException;


/**
 * 电票详情界面Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/elecBillDetailController")
public class ElecBillDetailController extends BaseController{

	/**
	 * 票据查看
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=goDetail")
	public ModelAndView goDetail(String rgctId){
		IDiscService discService = ServiceFactory.getDiscService();
		BillAllInfoBean bean = null;
		try {
			bean = discService.getElecBillDetail(rgctId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/common/elecBillDetail");
		mv.addObject("elecBillBefore", bean.getFrontBean());
		mv.addObject("elecBillBack", bean.getBackBean().getEndoListBean());
		mv.addObject("assuBillList", bean.getGuarnteeList());
		return mv;
	}
}
