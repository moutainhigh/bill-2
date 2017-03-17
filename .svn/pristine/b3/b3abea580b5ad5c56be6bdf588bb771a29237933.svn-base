package com.herongtech.console.web.busicontroller.into;

import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

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
 * 审核Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/intoAuditController")
public class IntoAuditController extends BaseController {
	/**
	 * 审核批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=auditBatchList")
	public ModelAndView seachWaitAuditApply(Page page,IntoSearchBean query) throws Exception{
		ModelAndView mv = new ModelAndView("busi/into/into_audit_batch_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus("IntoAuditController", "seachWaitAuditBill", null)[0]);
			mv.addObject("batchList", intoService.getIntoApplyListBySearchBean(page,query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
		
	}
	

	/**
	 * 审核清单列表
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=auditBillList")
	public ModelAndView seachWaitAuditBill(Page page,HttpServletRequest request,IntoSearchBean query) throws Exception{
		ModelAndView mv = new ModelAndView("busi/into/into_audit_bill_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB();
		
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("IntoAuditController", "seachWaitAuditBill", null));
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
			CommonLog.getCommonLogCache().errorMessage("待审核票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待审核票据列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
		
	}
	/**
	 * 审核页面
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "method=toAudit")
	public ModelAndView toAudit(String ids,String intoId){
		ModelAndView mv = new ModelAndView("busi/into/into_audit");
		mv.addObject("ids", ids);
		mv.addObject("intoId", intoId);
		return mv;
	}
	/**
	 * 审核
	 * @param ids
	 * @param status
	 * @param option
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=audit")
	@ResponseBody
	public String audit(String ids,String status,String option,IntoSearchBean query) throws BizAppException{
		IIntoService intoService=ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = intoService.auditSubmit(ids,status,option,query);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据审核提交失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据审核提交失败");
		}
		return rs > 0 ? "yes":"no";
	}
	
	/**
	 * 待撤销申请的批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelAuditBatchList")
	public ModelAndView cancelAuditBatchList(Page page,IntoSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/into/into_cancel_audit_batch_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setCheckOperNo(user.getUserId());
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus("IntoStorageController", "seachWaitStorageBill", null)[0]);
			mv.addObject("batchList", intoService.getIntoApplyListBySearchBean(page,query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("待撤销审核批次列表");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销审核批次列表");
		}
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 待撤销申请的清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelAuditBillList")
	public ModelAndView cancelAuditBillList(Page page,IntoSearchBean query) throws BizAppException{
		IIntoService intoService=ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/into/into_cancel_audit_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setCheckOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus("IntoStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",intoService.getIntoApplyInfo(query.getIntoId(),query));
			mv.addObject("resultList",intoService.getIntoBillInfo(page,query));
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
			CommonLog.getCommonLogCache().errorMessage("待撤销审核的清单列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销审核的清单列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}
	/**
	 * 撤销审核
	 * @param request					
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelAudit")
	@ResponseBody
	public AjaxJson cancelAudit(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IIntoService intoService=ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = intoService.cancel("IntoAuditController","cancelAudit",ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据撤销审核失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销审核失败");
		}
		aj.setSuccess(rs > 0);
		return aj;
	}
	
	/***************************************************电票入池****************************************/
	/**
	 * 审核批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=elecAuditBatchList")
	public ModelAndView elecAuditBatchList(Page page,IntoSearchBean query) throws Exception{
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_audit_batch_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			query.setOperStatus(StatusUtils.queryStatus("IntoAuditController", "seachWaitAuditBill", null)[0]);
			mv.addObject("batchList", intoService.getIntoApplyListBySearchBean(page,query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
		
	}
	

	/**
	 * 审核清单列表
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=elecAuditBillList")
	public ModelAndView elecAuditBillList(Page page,HttpServletRequest request,IntoSearchBean query) throws Exception{
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_audit_bill_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB();
		
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("IntoAuditController", "seachWaitAuditBill", null));
			mv.addObject("batch",intoService.getIntoApplyInfo(query.getIntoId(),query));
			mv.addObject("resultList",intoService.getIntoBillListBySearchBean(page,query));
			IntoApplyInfo savApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), query);
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
			CommonLog.getCommonLogCache().errorMessage("待审核票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待审核票据列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
		
	}
	/**
	 * 审核页面
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "method=toElecAudit")
	public ModelAndView toElecAudit(String ids,String intoId){
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_audit");
		mv.addObject("ids", ids);
		mv.addObject("intoId", intoId);
		return mv;
	}
	/**
	 * 审核
	 * @param ids
	 * @param status
	 * @param option
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecAudit")
	@ResponseBody
	public String elecAudit(String ids,String status,String option,IntoSearchBean query) throws BizAppException{
		IIntoService intoService=ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = intoService.auditSubmit(ids,status,option,query);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据审核提交失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据审核提交失败");
		}
		return rs > 0 ? "yes":"no";
	}
	
	/**
	 * 待撤销申请的批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecCancelAuditBatchList")
	public ModelAndView elecCancelAuditBatchList(Page page,IntoSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_cancel_audit_batch_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setCheckOperNo(user.getUserId());
			query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			query.setOperStatus(StatusUtils.queryStatus("IntoStorageController", "seachWaitStorageBill", null)[0]);
			mv.addObject("batchList", intoService.getIntoApplyListBySearchBean(page,query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("待撤销审核批次列表");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销审核批次列表");
		}
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 待撤销申请的清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecCancelAuditBillList")
	public ModelAndView elecCancelAuditBillList(Page page,IntoSearchBean query) throws BizAppException{
		IIntoService intoService=ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_cancel_audit_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setCheckOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus("IntoStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",intoService.getIntoApplyInfo(query.getIntoId(),query));
			mv.addObject("resultList",intoService.getIntoBillInfo(page,query));
			IntoApplyInfo savApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), query);
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
			CommonLog.getCommonLogCache().errorMessage("待撤销审核的清单列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销审核的清单列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 撤销审核
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecCancelAudit")
	@ResponseBody
	public AjaxJson elecCancelAudit(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IIntoService intoService=ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = intoService.cancel("IntoAuditController","cancelAudit",ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据撤销审核失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销审核失败");
		}
		aj.setSuccess(rs > 0);
		return aj;
	}
	
}
