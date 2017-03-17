package com.herongtech.console.web.syscontroller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.machinestatus.CreateStatusDigrph;
import com.herongtech.console.service.tailAfterSearch.ITailAfterSearchService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.parser.exception.ParseException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 跟踪查询Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tailAfterSearchController")
public class TailAfterSearchController extends BaseController {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20161015 @describe ";
	/**
	 * 跟踪查询页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=tailAfterSearch")
	public ModelAndView tailAfterSearch() throws BizAppException{
		ModelAndView mv = new ModelAndView("system/tailAfterSearch/tailAfter_search");
		return mv;
	}
	
	/**
	 * 跟踪查询
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=search")
	public ModelAndView search(Page page,RgctBillInfo query) throws BizAppException{
		ModelAndView mv = new ModelAndView("system/tailAfterSearch/tailAfter_search");
		ITailAfterSearchService tailAfterService=ServiceFactory.getTailAfterSearchService();
		page.activeCommand();
		try {
			//查询票据信息
			 RgctBill rgctBill=tailAfterService.getTailAfterServiceCondition(query.getBillNo());			
			 //查询票据当前操作状态和票据所在流程图节点列表
			 String[]  result=tailAfterService.getModelCodeAndOperStatus(rgctBill);
			List statusDictList= tailAfterService.getStatusDictListByRgctBillInfo(rgctBill, result[0]);
			if(statusDictList==null || statusDictList.isEmpty()){
				throw new BizAppException("流程图节点列表为空！");
			}
			CreateStatusDigrph createDigraph = new CreateStatusDigrph(statusDictList);		
			String myscript=createDigraph.createDigrh(result[1]);//result[1]为状态操作码
			mv.addObject("curCode",result);//当前操作码传入前台，判断指定的才弹出框显示
			mv.addObject("result",rgctBill.getInfo());
			mv.addObject("statusDictList",statusDictList);
			mv.addObject("myscript",myscript);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage(" 票据追踪查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
}
