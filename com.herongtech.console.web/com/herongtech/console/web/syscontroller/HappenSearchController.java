/********************************************
 * 文件名称: HappenSearchController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-9-13 下午02:50:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.HappenSearchBean;
import com.herongtech.console.domain.disc.bean.DiscInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyInfo;
import com.herongtech.console.domain.sale.bean.SaleInfo;
import com.herongtech.console.domain.save.bean.SaveInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.happensearch.IHappenSearchService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：HappenSearchController
 * 创建人：FQQ
 * 创建时间：2016年9月12日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/happenSearchController")
public class HappenSearchController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 去查询页面
	 */
	@RequestMapping(params="method=toSearch")
	public ModelAndView toSearch()throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/happensearch/happensearch_list");
		
		return mv;
	}
	
	
	/**
	 * 发生查询
	 * @param serviceType
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=happenSearch")
	public ModelAndView happenSearch(Page page,String serviceType,HappenSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("system/happensearch/happensearch_list");
		page.activeCommand();
//		IHappenSearchService balService = ServiceFactory.getHappenSearchService();
		List list = null;
		try {
			if("1".equals(serviceType)){//贴现
				list=searchDiscList(page,query);
				//return searchDiscList(page,query);
			}else if("2".equals(serviceType)){//转贴现转入
				list=searchRebuyList(page,query);
			}else if("3".equals(serviceType)){//转出
				list=searchSaleList(page,query);
			}else if("4".equals(serviceType)){//质押
				list=searchSaveList(page,query);
			}else if("5".equals(serviceType)){//托收
				list=searchSubcollList(page,query);
			}else{
				throw new BizAppException("业务类型选择错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查询失败");
			throw new BizAppException("查询失败,"+e.getMessage());
		}
		mv.addObject("resultList", list);
		mv.addObject("query",query);
		//mv.addObject("OperationName",1);//将操作值传如前台
		mv.addObject("serviceType",serviceType);
		mv.addObject("page", page);
		return mv;
	}
	
	
	/**
	 * 查询贴现发生列表
	 */
	@RequestMapping(params="method=searchDiscList")
	public List searchDiscList(Page page,HappenSearchBean query)throws Exception{
		IHappenSearchService happenSearchService=ServiceFactory.getHappenSearchService();
		query.setOpers(StatusUtils.queryStatus("HappenSearchController", "searchDiscList", null));
		List<DiscInfo> discInfo;
    	try{
//    		page.activeCommand();
    		discInfo =happenSearchService.getDiscBillInfoByStatus(page, query);
        	
		} catch (SQLException e){
			e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误  ");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询缓存数据列表失败");
		}
    	//mv.setViewName("system/happensearch/happensearch_list");
		
		return discInfo;
		
	}
	
	
	/**
	 * 查询转入发生列表
	 */
	@RequestMapping(params="method=searchRebuyList")
	public List searchRebuyList(Page page,HappenSearchBean query)throws Exception{
		IHappenSearchService happenSearchService=ServiceFactory.getHappenSearchService();
		query.setOpers(StatusUtils.queryStatus("HappenSearchController", "searchRebuyList", null));
		List<RebuyInfo> rebuyInfo;
    	try{
//    		page.activeCommand();
    		rebuyInfo =happenSearchService.getRebuyBillInfoByStatus(page, query);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误  ");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询缓存数据列表失败");
		}
		
		return rebuyInfo;
		
	}
	
	
	/**
	 * 查询转卖发生列表
	 */
	@RequestMapping(params="method=searchSaleList")
	public List searchSaleList(Page page,HappenSearchBean query)throws Exception{
		IHappenSearchService happenSearchService=ServiceFactory.getHappenSearchService();
		query.setOpers(StatusUtils.queryStatus("HappenSearchController", "searchSaleList", null));
		List<SaleInfo> saleInfo;
    	try{
//    		page.activeCommand();
    		saleInfo =happenSearchService.getSaleBillInfoByStatus(page, query);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误  ");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询缓存数据列表失败");
		}
    	return saleInfo;
		
	}
	
	
	
	/**
	 * 查询质押发生列表
	 */
	@RequestMapping(params="method=searchSaveList")
	public List searchSaveList(Page page,HappenSearchBean query)throws Exception{
		IHappenSearchService happenSearchService=ServiceFactory.getHappenSearchService();
		query.setOpers(StatusUtils.queryStatus("HappenSearchController", "searchSaveList", null));
		List<SaveInfo> saveInfo;
    	try{
//    		page.activeCommand();
    		saveInfo =happenSearchService.getSaveBillInfoByStatus(page, query);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误  ");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询缓存数据列表失败");
		}
    	return saveInfo;
		
	}
	/**
	 * 查询委托收款发生列表
	 */
	@RequestMapping(params="method=searchSubcollList")
	public List searchSubcollList(Page page,HappenSearchBean query)throws Exception{
		IHappenSearchService happenSearchService=ServiceFactory.getHappenSearchService();
		List<SubcollInfo> subcollInfo;
    	try{
//    		page.activeCommand();
    		subcollInfo =happenSearchService.getSubcollBillInfoByStatus(page, query);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误  ");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询缓存数据列表失败");
		}
    	return subcollInfo;
		
	}
}
