/********************************************
 * 文件名称: BuybackAuditController.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-11-04 下午10:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.web.busicontroller.buyback;

import java.sql.SQLException;

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
import com.herongtech.console.domain.buyback.bean.BuybackSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.buyback.IBuybackService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.parser.exception.ParseException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 回购审核Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/buybackAuditController")
public class BuybackAuditController extends BaseController {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/*****电票*****/
	/**
	 * 待审核票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditDetailList")
	public ModelAndView auditDetailList(Page page,BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_audit_detail_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(StatusUtils.queryStatus("BuybackAuditController", "buybackAudit", "0")[0]);
			mv.addObject("batch",buybackService.getBuybackApplyInfo(query.getBuybackId(),query));
			mv.addObject("resultList",buybackService.getBuybackBillListForBatch(page,query));
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
		mv.addObject("query", query);
		mv.addObject("page", page);
		return mv;
	}
	/**
	 * 功能描述：待审核批次列表页面
	 * @param request
	 * @return ModelAndView
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditBatchList")
	public ModelAndView auditBatchList(Page page,BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_audit_batch_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("BuybackAuditController", "buybackAudit", "1")[0]);
			query.setBillClass(CommonConst.BILL_CLASS_ELEC);
			session.beginTransaction();
			mv.addObject("batchList", buybackService.getBuybackApplyListForCondition(page,query));
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待审核批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待审核批次列表查询失败");
		}
		mv.addObject("query", query);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 票据审核页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toAudit")
	public ModelAndView toAudit(String ids){
		ModelAndView mv = new ModelAndView("busi/disc/electronic/buyback_audit");
		mv.addObject("ids", ids);
		return mv;
	}
	
	/**
	 * 票据审核
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=audit")
	@ResponseBody
	public AjaxJson audit(String ids,String status,String reason) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IBuybackService BuybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = BuybackService.auditSubmit(ids,status,reason);
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
		aj.setSuccess(rs > 0);
		return aj;
	}
	/*****纸票*****/
	/**
	 * 功能描述：待审核批次列表页面
	 * @param request
	 * @return ModelAndView
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=entityAuditBatchList")
	public ModelAndView entityAuditBatchList(Page page,BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_audit_batch_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "entyBuybackCancelApply", null)[0]);
			query.setBillClass(CommonConst.BILL_CLASS_ENTITY);
			session.beginTransaction();
			mv.addObject("batchList", buybackService.getBuybackApplyListForCondition(page,query));
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待审核批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待审核批次列表查询失败");
		}
		mv.addObject("query", query);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 待审核票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=entityAuditBillList")
	public ModelAndView entityAuditBillList(Page page,BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_audit_bill_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "entyBuybackCancelApply", null)[0]);
			mv.addObject("batch",buybackService.getBuybackApplyInfo(query.getBuybackId(),query));
			mv.addObject("resultList",buybackService.getBuybackBillListForBatch(page,query));
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
		mv.addObject("query", query);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 审核页面
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "method=toAuditForEntity")
	public ModelAndView toAuditForEntity(String ids,String buybackId){
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_audit");
		mv.addObject("ids", ids);
		mv.addObject("buybackId", buybackId);
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
	@RequestMapping(params = "method=entityAudit")
	@ResponseBody
	public String entityAudit(String ids,String status,BuybackSearchBean query) throws BizAppException{
		IBuybackService buybackService=ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs=buybackService.auditSubmitForEntity(ids,status,query);
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
	 * 待撤销审核的批次列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=cancelAuditBatchList")
	public ModelAndView cancelAuditBatchList(Page page, BuybackSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_cancel_audit_batch_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setBillClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus("BuybackAuditController", "buybackCancelAudit",null)[0]);
			mv.addObject("batchList", buybackService.getBuybackApplyListForCondition(page, query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("待撤销审核批次列表");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销审核批次列表");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 待撤销审核的清单列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=cancelAuditBillList")
	public ModelAndView cancelAuditBillList(Page page, BuybackSearchBean query)
			throws BizAppException {
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_cancel_audit_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus("BuybackAuditController", "buybackCancelAudit",null));
			mv.addObject("batch", buybackService.getBuybackApplyInfo(query.getBuybackId(), query));
			mv.addObject("resultList", buybackService.getBuybackBillListForBatch(page, query));
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
	 * 撤销审核(纸票)
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=entityCancelAudit")
	public ModelAndView entityCancelAudit(String ids,String buybackId) throws BizAppException{
		IDB session = DBFactory.getDB(); // 获取数据库连接
//		IBuybackService buybackService=ServiceFactory.getBuybackService();
		try {
			session.beginTransaction();
			ServiceFactory.getBuybackService().cancel("BuybackAuditController","buybackCancelAudit",ids);
			/*BuybackApplyInfo apply = buybackService.getBuybackApplyInfo(buybackId, null);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
			buybackService.modifyBuybackApplyInfo(apply);*/
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
		return cancelAuditBatchList(new Page(),new BuybackSearchBean());
	}
	
}
