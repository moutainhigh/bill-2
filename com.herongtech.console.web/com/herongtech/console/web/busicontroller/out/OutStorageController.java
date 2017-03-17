package com.herongtech.console.web.busicontroller.out;

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
import com.herongtech.console.domain.out.bean.OutApplyInfo;
import com.herongtech.console.domain.out.bean.OutSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.out.IOutService;
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
@RequestMapping("/outStorageController")
public class OutStorageController extends BaseController {
	/** 纸票取票 **/
	/**
	 * 出库批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=storageBatchList")
	public ModelAndView seachWaitStorageApply(Page page,OutSearchBean query) throws Exception{
	ModelAndView mv = new ModelAndView("busi/out/out_storage_batch_list");
	IOutService outService=ServiceFactory.getOutService();
	page.activeCommand();
	UserLoginfo user=ResourceUtil.getSessionLoginfo();
	try {
		query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
		query.setBranchNo(user.getBranchNo());
		query.setOpers(StatusUtils.queryStatus("OutStorageController", "seachWaitStorageBill", null));
		List<OutApplyInfo> batchList = outService.getOutApplyListBySearchBean(page,query);
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
	public ModelAndView seachWaitAuditBill(Page page,OutSearchBean query) throws Exception{
		IOutService outService=ServiceFactory.getOutService();
		ModelAndView mv = new ModelAndView("busi/out/out_storage_bill_list");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		page.activeCommand();
		
		try {
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("OutStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",outService.getOutApplyInfo(query.getOutId(),query));
			mv.addObject("resultList",outService.getOutBillListBySearchBean(page,query));
			OutApplyInfo outApplyInfo = outService.getOutApplyInfo(query.getOutId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(outApplyInfo.getProdNo());
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
		mv.addObject("outId", query.getOutId());
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
		IDB session = DBFactory.getDB();
		IOutService outService=ServiceFactory.getOutService();
		String outmxIds = request.getParameter("outmx_ids");
		String outId = request.getParameter("outId");
		try {
			session.beginTransaction();
			outService.accountOutBill(outmxIds,outId);
			session.endTransaction();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
		
		return retJson;
	}
	
	/** 电票取票 **/
	/**
	 * 出库批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=elecStorageBatchList")
	public ModelAndView elecStorageBatchList(Page page,OutSearchBean query) throws Exception{
	ModelAndView mv = new ModelAndView("busi/out/electronic/out_elec_storage_batch_list");
	IOutService outService=ServiceFactory.getOutService();
	page.activeCommand();
	UserLoginfo user=ResourceUtil.getSessionLoginfo();
	try {
		query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
		query.setBranchNo(user.getBranchNo());
		query.setOpers(StatusUtils.queryStatus("OutStorageController", "seachWaitStorageBill", null));
		List<OutApplyInfo> batchList = outService.getOutApplyListBySearchBean(page,query);
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
	public ModelAndView elecStorageBillList(Page page,OutSearchBean query) throws Exception{
		IOutService outService=ServiceFactory.getOutService();
		ModelAndView mv = new ModelAndView("busi/out/electronic/out_elec_storage_bill_list");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		page.activeCommand();
		
		try {
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("OutStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",outService.getOutApplyInfo(query.getOutId(),query));
			mv.addObject("resultList",outService.getOutBillListBySearchBean(page,query));
			OutApplyInfo ouApplyInfo = outService.getOutApplyInfo(query.getOutId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(ouApplyInfo.getProdNo());
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
		mv.addObject("outId", query.getOutId());
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
		IOutService outService=ServiceFactory.getOutService();
		String outmxIds = request.getParameter("outmx_ids");
		String outId = request.getParameter("outId");
		IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			outService.accountOutBill(outmxIds,outId);
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
