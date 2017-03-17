package com.herongtech.console.web.syscontroller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.acct.bean.AcctFlowSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.accountFlowSearch.IAccountFlowSearchService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.parser.exception.ParseException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 账务流水类查询Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/accountFlowSearchController")
public class AccountFlowSearchController extends BaseController {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20161015 @describe ";
	/**
	 * 账务流水批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=accountFlowSearch")
	public ModelAndView accountFlowSearch(Page page,AcctFlowSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("system/accountFlowSearch/accountFlow_search");
		IAccountFlowSearchService acctService = ServiceFactory.getAccountFlowSearchService();
		page.activeCommand();
		try {
			OrderBean order=new OrderBean("transDt",false);//构造方法指定升序降序
			String prodNo=query.getProdNo();
			query.appendOrder(order);
			query.addSqlPropretyMapping("minTransDt", "transDt");
			query.addSpecialOpertion("minTransDt", BaseSearchBean.MORE_AND_EQUAL);
			query.addSqlPropretyMapping("maxTransDt", "transDt");
			query.addSpecialOpertion("maxTransDt", BaseSearchBean.LESS_AND_EQUAL);
			query.setDfaultSrchTbAliasName("acct");
			mv.addObject("batchList", acctService.getAccountFlowCondition(page,query));
			mv.addObject("prodNo", prodNo);//查询时回显产品编号
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	
	
	/**
	 * 账务流水列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=acctBillList")
	public ModelAndView acctBillList(Page page,AcctFlowSearchBean query) throws BizAppException{
		page.activeCommand();
		ModelAndView mv = new ModelAndView("system/accountFlowSearch/accountFlow_info_search");
		IAccountFlowSearchService acctService = ServiceFactory.getAccountFlowSearchService();
		try {
			mv.addObject("batch",acctService.getAcctFlowForAfID(query));
			mv.addObject("resultList",acctService .getAcctFlowInfoListForBatch(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}
	
	
	
	
	
	
	
}
