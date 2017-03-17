package com.herongtech.console.web.busicontroller.into;

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
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.into.bean.IntoApplyInfo;
import com.herongtech.console.domain.into.bean.IntoSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.into.IIntoService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 入库Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/intoStorageController")
public class IntoStorageController extends BaseController {
	/**
	 * 入库批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=storageBatchList")
	public ModelAndView seachWaitStorageApply(Page page,IntoSearchBean query) throws Exception{
	ModelAndView mv = new ModelAndView("busi/into/into_storage_batch_list");
	IIntoService intoService=ServiceFactory.getIntoService();
	page.activeCommand();
	UserLoginfo user=ResourceUtil.getSessionLoginfo();
	try {
		query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
		query.setBranchNo(user.getBranchNo());
		query.setOpers(StatusUtils.queryStatus("IntoStorageController", "seachWaitStorageBill", null));
		List<IntoApplyInfo> batchList = intoService.getIntoApplyListBySearchBean(page,query);
		mv.addObject("batchList", batchList);
	} catch (Exception e) {
		e.printStackTrace();
		CommonLog.getCommonLogCache().errorMessage("质押入库查询失败");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "质押入库查询失败");
	}
	return mv;
}

	/**
	 * 入库清单列表
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=storageBillList")
	public ModelAndView seachWaitAuditBill(Page page,IntoSearchBean query) throws Exception{
		IIntoService intoService=ServiceFactory.getIntoService();
		ModelAndView mv = new ModelAndView("busi/into/into_storage_bill_list");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		page.activeCommand();
		
		try {
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("IntoStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",intoService.getIntoApplyInfo(query.getIntoId(),query));
			mv.addObject("resultList",intoService.getIntoBillListBySearchBean(page,query));
			IntoApplyInfo intoApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(intoApplyInfo.getProdNo());
			mv.addObject("prodName", prod.getProdName());
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待入库票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待入库票据列表查询失败");
			
		}
		mv.addObject("intoId", query.getIntoId());
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 票据入库
	 * @throws Exception 
	 */
	@RequestMapping(params="method=storage")
	@ResponseBody
	public AjaxJson storage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		AjaxJson retJson = new AjaxJson();
		IIntoService intoService=ServiceFactory.getIntoService();
		String intomxIds = request.getParameter("intomx_ids");
		String intoId = request.getParameter("intoId");
		try {
			intoService.accountIntoBill(intomxIds,intoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retJson;
	}
	
	/*********************************************************电票存票*******************************************************/
	/**
	 * 入库批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=elecStorageBatchList")
	public ModelAndView elecStorageBatchList(Page page,IntoSearchBean query) throws Exception{
	ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_storage_batch_list");
	IIntoService intoService=ServiceFactory.getIntoService();
	page.activeCommand();
	UserLoginfo user=ResourceUtil.getSessionLoginfo();
	
	try {
		query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
		query.setBranchNo(user.getBranchNo());
		query.setOpers(StatusUtils.queryStatus("IntoStorageController", "seachWaitStorageBill", null));
		List<IntoApplyInfo> batchList = intoService.getIntoApplyListBySearchBean(page,query);
		mv.addObject("batchList", batchList);
	} catch (Exception e) {
		e.printStackTrace();
		CommonLog.getCommonLogCache().errorMessage("质押入库查询失败");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "质押入库查询失败");
	}
	return mv;
}

	/**
	 * 入库清单列表
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=elecStorageBillList")
	public ModelAndView elecStorageBillList(Page page,IntoSearchBean query) throws Exception{
		IIntoService intoService=ServiceFactory.getIntoService();
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_storage_bill_list");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		page.activeCommand();
		
		try {
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("IntoStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",intoService.getIntoApplyInfo(query.getIntoId(),query));
			mv.addObject("resultList",intoService.getIntoBillListBySearchBean(page,query));
			IntoApplyInfo intoApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(intoApplyInfo.getProdNo());
			mv.addObject("prodName", prod.getProdName());
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待入库票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待入库票据列表查询失败");
			
		}
		mv.addObject("intoId", query.getIntoId());
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 入库
	 * @throws Exception 
	 */
	@RequestMapping(params="method=elecStorage")
	@ResponseBody
	public AjaxJson elecStorage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		AjaxJson retJson = new AjaxJson();
		IIntoService intoService=ServiceFactory.getIntoService();
		String intomxIds = request.getParameter("intomx_ids");
		String intoId = request.getParameter("intoId");
		IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			intoService.accountIntoBill(intomxIds,intoId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return retJson;
	}
	
}
