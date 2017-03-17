package com.herongtech.console.web.busicontroller.repurchasedcollateralization;

import java.sql.SQLException;
import java.util.List;

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
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.get.bean.GetApplyInfo;
import com.herongtech.console.domain.get.bean.GetSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.repurchasedcollateralization.IRepurCollateService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 出库Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/repurCollateStorageController")
public class RepurCollateStorageController {
	/** 电票解质押 **/
	/**
	 * 出库批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=elecStorageBatchList")
	public ModelAndView elecStorageBatchList(Page page,GetSearchBean query) throws Exception{
	ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/electronic/repurcollate_elec_storage_batch_list");
	IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
	UserLoginfo user = ResourceUtil.getSessionLoginfo();
	page.activeCommand();
	try {
		query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
		query.setBranchNo(user.getBranchNo());
		query.setOpers(StatusUtils.queryStatus("RepurCollateStorageController", "seachWaitStorageBill", null));
		List<GetApplyInfo> batchList = repurCollateService.getGetApplyListBySearchBean(page,query);
		mv.addObject("batchList", batchList);
	} catch (Exception e) {
		e.printStackTrace();
		CommonLog.getCommonLogCache().errorMessage("出库查询失败");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "出库查询失败");
	}
	return mv;
}

	/**
	 * 出库清单列表
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=elecStorageBillList")
	public ModelAndView elecStorageBillList(Page page,GetSearchBean query) throws Exception{
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/electronic/repurcollate_elec_storage_bill_list");
		IDB session = DBFactory.getDB();
		page.activeCommand();
		
		try {
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("RepurCollateStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",repurCollateService.getGetApplyInfo(query.getGetId(),query));
			mv.addObject("resultList",repurCollateService.getGetBillListBySearchBean(page,query));
			GetApplyInfo geApplyInfo = repurCollateService.getGetApplyInfo(query.getGetId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(geApplyInfo.getProdNo());
			mv.addObject("prodName", prod.getProdName());
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待出库票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待出库票据列表查询失败");
			
		}
		mv.addObject("getId", query.getGetId());
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 出库
	 * @throws Exception 
	 */
	@RequestMapping(params="method=elecStorage")
	@ResponseBody
	public AjaxJson elecStorage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		AjaxJson retJson = new AjaxJson();
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		String getmxIds = request.getParameter("getmxIds");
		String getId = request.getParameter("getId");
		try {
			repurCollateService.accountRepurCollate(getmxIds,getId);
		} catch (Exception e) {
		
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("解质押出库失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "解质押出库失败");
		}
		
		return retJson;
	}
	/** 纸票解质押**/
	/**
	 * 出库批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=storageBatchList")
	public ModelAndView seachWaitStorageApply(Page page,GetSearchBean query) throws Exception{
	ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/entity/repurcollate_storage_batch_list");
	IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
	page.activeCommand();
	UserLoginfo user=ResourceUtil.getSessionLoginfo();
	try {
		query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
		query.setBranchNo(user.getBranchNo());
		query.setOpers(StatusUtils.queryStatus("RepurCollateStorageController", "seachWaitStorageBill", null));
		List<GetApplyInfo> batchList = repurCollateService.getGetApplyListBySearchBean(page,query);
		mv.addObject("batchList", batchList);
	} catch (Exception e) {
		e.printStackTrace();
		CommonLog.getCommonLogCache().errorMessage("出库查询失败");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "出库查询失败");
	}
	return mv;
}

	/**
	 * 出库清单列表
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=storageBillList")
	public ModelAndView seachWaitAuditBill(Page page,GetSearchBean query) throws Exception{
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/entity/repurcollate_storage_bill_list");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		page.activeCommand();
		
		try {
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("RepurCollateStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",repurCollateService.getGetApplyInfo(query.getGetId(),query));
			mv.addObject("resultList",repurCollateService.getGetBillListBySearchBean(page,query));
			GetApplyInfo getApplyInfo = repurCollateService.getGetApplyInfo(query.getGetId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(getApplyInfo.getProdNo());
			mv.addObject("prodName", prod.getProdName());
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待出库票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待出库票据列表查询失败");
			
		}
		mv.addObject("getId", query.getGetId());
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 票据出库
	 * @throws Exception 
	 */
	@RequestMapping(params="method=storage")
	@ResponseBody
	public AjaxJson storage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		AjaxJson retJson = new AjaxJson();
//		IDB session = DBFactory.getDB();
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		String getmxIds = request.getParameter("getmx_ids");
		String getId = request.getParameter("getId");
		try {
//			session.beginTransaction();
			repurCollateService.accountRepurCollate(getmxIds,getId);
//			session.endTransaction();
		} catch (Exception e) {
//			session.rollback();
			e.printStackTrace();
		}
		
		return retJson;
	}
	

}
