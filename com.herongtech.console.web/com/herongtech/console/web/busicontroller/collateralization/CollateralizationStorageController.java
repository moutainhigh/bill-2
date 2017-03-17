package com.herongtech.console.web.busicontroller.collateralization;

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
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.collateralization.ICollateralizationService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 质押入库Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/collateralizationStorageController")
public class CollateralizationStorageController extends BaseController {
	/** 电票质押 **/
	/**
	 * 入库批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=elecStorageBatchList")
	public ModelAndView elecStorageBatchList(Page page,SaveSearchBean query) throws Exception{
	ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_storage_batch_list");
	ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
	page.activeCommand();
	UserLoginfo user=ResourceUtil.getSessionLoginfo();
	
	try {
		query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
		query.setBranchNo(user.getBranchNo());
		query.setOpers(StatusUtils.queryStatus("CollateralizationStorageController", "seachWaitStorageBill", null));
		List<SaveApplyInfo> batchList = saveService.getSaveApplyListBySearchBean(page,query);
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
	public ModelAndView elecStorageBillList(Page page,SaveSearchBean query) throws Exception{
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_storage_bill_list");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		page.activeCommand();
		
		try {
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("CollateralizationStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",saveService.getSaveApplyInfo(query.getSaveId(),query));
			mv.addObject("resultList",saveService.getSaveBillListBySearchBean(page,query));
			SaveApplyInfo savApplyInfo = saveService.getSaveApplyInfo(query.getSaveId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(savApplyInfo.getProdNo());
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
		mv.addObject("saveId", query.getSaveId());
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 质押入库
	 * @throws Exception 
	 */
	@RequestMapping(params="method=elecStorage")
	@ResponseBody
	public AjaxJson elecStorage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		AjaxJson retJson = new AjaxJson();
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		String savemxIds = request.getParameter("savemx_ids");
		String saveId = request.getParameter("saveId");
		try {
			saveService.accountCollateralizationBill(savemxIds,saveId);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("质押入库失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "质押入库失败");
		}
		
		return retJson;
	}
	
	/** 纸票质押 **/
	/**
	 * 入库批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=storageBatchList")
	public ModelAndView seachWaitStorageApply(Page page,SaveSearchBean query) throws Exception{
	ModelAndView mv = new ModelAndView("busi/collateralization/entity/collate_storage_batch_list");
	ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
	page.activeCommand();
	UserLoginfo user=ResourceUtil.getSessionLoginfo();
	
	try {
		query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
		query.setBranchNo(user.getBranchNo());
		query.setOpers(StatusUtils.queryStatus("CollateralizationStorageController", "seachWaitStorageBill", null));
		List<SaveApplyInfo> batchList = saveService.getSaveApplyListBySearchBean(page,query);
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
	public ModelAndView seachWaitAuditBill(Page page,SaveSearchBean query) throws Exception{
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		ModelAndView mv = new ModelAndView("busi/collateralization/entity/collate_storage_bill_list");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		page.activeCommand();
		
		try {
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("CollateralizationStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",saveService.getSaveApplyInfo(query.getSaveId(),query));
			mv.addObject("resultList",saveService.getSaveBillListBySearchBean(page,query));
			SaveApplyInfo savApplyInfo = saveService.getSaveApplyInfo(query.getSaveId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(savApplyInfo.getProdNo());
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
		mv.addObject("saveId", query.getSaveId());
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 质押入库
	 * @throws Exception 
	 */
	@RequestMapping(params="method=storage")
	@ResponseBody
	public AjaxJson storage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		AjaxJson retJson = new AjaxJson();
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		String savemxIds = request.getParameter("savemx_ids");
		String saveId = request.getParameter("saveId");
		try {
			saveService.accountCollateralizationBill(savemxIds,saveId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retJson;
	}
}
