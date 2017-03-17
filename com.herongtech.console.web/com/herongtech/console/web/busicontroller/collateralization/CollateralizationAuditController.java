package com.herongtech.console.web.busicontroller.collateralization;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

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
 * 质押审核Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/collateralizationAuditController")
public class CollateralizationAuditController extends BaseController {
	/** 电票质押 **/
	/**
	 * 审核批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=elecAuditBatchList")
	public ModelAndView elecAuditBatchList(Page page,SaveSearchBean query) throws Exception{
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_audit_batch_list");
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			query.setOperStatus(StatusUtils.queryStatus("CollateralizationAuditController", "seachWaitAuditBill", null)[0]);
			mv.addObject("batchList", saveService.getSaveApplyListBySearchBean(page,query));
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
	public ModelAndView elecAuditBillList(Page page,HttpServletRequest request,SaveSearchBean query) throws Exception{
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_audit_bill_list");
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB();
		
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("CollateralizationAuditController", "seachWaitAuditBill", null));
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
	public ModelAndView toElecAudit(String ids,String saveId){
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_audit");
		mv.addObject("ids", ids);
		mv.addObject("saveId", saveId);
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
	public String elecAudit(String ids,String status,String option,SaveSearchBean query) throws BizAppException{
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = saveService.auditSubmit(ids,status,option,query);
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
	public ModelAndView elecCancelAuditBatchList(Page page,SaveSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_cancel_audit_batch_list");
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			//query.setOperNo(user.getUserId());
			query.setCheckOperNo(user.getUserId());
			query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			query.setOperStatus(StatusUtils.queryStatus("CollateralizationStorageController", "seachWaitStorageBill", null)[0]);
			mv.addObject("batchList", collateralizationService.getSaveApplyListBySearchBean(page,query));
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
	public ModelAndView elecCancelAuditBillList(Page page,SaveSearchBean query) throws BizAppException{
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_cancel_audit_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("CollateralizationStorageController", "seachWaitStorageBill", null));
			query.setCheckOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			mv.addObject("batch",collateralizationService.getSaveApplyInfo(query.getSaveId(),query));
			mv.addObject("resultList",collateralizationService.getSaveBillInfo(page,query));
			SaveApplyInfo savApplyInfo = collateralizationService.getSaveApplyInfo(query.getSaveId(), query);
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
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = collateralizationService.cancel("CollateralizationAuditController","cancelAudit",ids);
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
	
	/** 纸票质押 **/
	/**
	 * 审核批次列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=auditBatchList")
	public ModelAndView seachWaitAuditApply(Page page,SaveSearchBean query) throws Exception{
		ModelAndView mv = new ModelAndView("busi/collateralization/entity/collate_audit_batch_list");
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus("CollateralizationAuditController", "seachWaitAuditBill", null)[0]);
			mv.addObject("batchList", saveService.getSaveApplyListBySearchBean(page,query));
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
	public ModelAndView seachWaitAuditBill(Page page,HttpServletRequest request,SaveSearchBean query) throws Exception{
		ModelAndView mv = new ModelAndView("busi/collateralization/entity/collate_audit_bill_list");
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB();
		
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("CollateralizationAuditController", "seachWaitAuditBill", null));
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
	public ModelAndView toAudit(String ids,String saveId){
		ModelAndView mv = new ModelAndView("busi/collateralization/entity/collate_audit");
		mv.addObject("ids", ids);
		mv.addObject("saveId", saveId);
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
	public String audit(String ids,String status,String option,SaveSearchBean query) throws BizAppException{
		ICollateralizationService saveService=ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = saveService.auditSubmit(ids,status,option,query);
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
	public ModelAndView cancelAuditBatchList(Page page,SaveSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/collateralization/entity/collate_cancel_audit_batch_list");
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setCheckOperNo(user.getUserId());
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus("CollateralizationStorageController", "seachWaitStorageBill", null)[0]);
			mv.addObject("batchList", collateralizationService.getSaveApplyListBySearchBean(page,query));
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
	public ModelAndView cancelAuditBillList(Page page,SaveSearchBean query) throws BizAppException{
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/collateralization/entity/collate_cancel_audit_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setCheckOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus("CollateralizationStorageController", "seachWaitStorageBill", null));
			mv.addObject("batch",collateralizationService.getSaveApplyInfo(query.getSaveId(),query));
			mv.addObject("resultList",collateralizationService.getSaveBillInfo(page,query));
			SaveApplyInfo savApplyInfo = collateralizationService.getSaveApplyInfo(query.getSaveId(), query);
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
	@RequestMapping(params = "method=cancelAudit")
	@ResponseBody
	public AjaxJson cancelAudit(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = collateralizationService.cancel("CollateralizationAuditController","cancelAudit",ids);
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
