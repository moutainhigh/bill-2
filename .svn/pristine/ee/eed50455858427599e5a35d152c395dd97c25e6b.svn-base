/********************************************
 * 文件名称: BuybackAccountController.java
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
 * 回购记账Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/buybackAccountController")
public class BuybackAccountController extends BaseController {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/*********电票**********/
	/**
	 * 回购申请记账票据列表页面
	 * @param page	
	 * @param query	
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=buybackApplyAccountDetailList")
	public ModelAndView buybackApplyAccountDetailList(Page page,BuybackSearchBean query) throws BizAppException{
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_review_account_detail_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(StatusUtils.queryStatus("BuybackAccountController", "buybackApplyAccount", null)[0]);
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
			CommonLog.getCommonLogCache().errorMessage("回购记账票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "回购记账票据列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	/**
	 * 功能描述：回购申请记账批次列表页面
	 * @param page
	 * @param query
	 * @return ModelAndView
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=buybackApplyAccountList")
	public ModelAndView buybackApplyAccountList(Page page,BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_review_account_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("BuybackAccountController", "buybackApplyAccount", null)[0]);
			query.setBillClass(CommonConst.BILL_CLASS_ELEC);
			mv.addObject("batchList", buybackService.getBuybackApplyListForCondition(page,query));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("回购记账批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "回购记账批次列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}
	
	
	/**
	 * 功能描述：回购申请记账
	 * @param page
	 * @param query
	 * @return ModelAndView
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=applyAccount")
	public ModelAndView applyAccount(String buybackId,String ids) throws BizAppException{
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		try {
			buybackService.applyAccount(buybackId, ids);	
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("回购申请记账失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "回购申请记账失败");
		}
		return buybackApplyAccountList(new Page(),new BuybackSearchBean());
	}
	
	/**
	 * 回购确认记账票据列表页面
	 * @param page	
	 * @param query	
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=buybackConfirmAccountDetailList")
	public ModelAndView buybackConfirmAccountDetailList(Page page,BuybackSearchBean query) throws BizAppException{
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_confirm_account_detail_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(StatusUtils.queryStatus("BuybackAccountController", "buybackDoAccount", null)[0]);
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
			CommonLog.getCommonLogCache().errorMessage("回购确认记账票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "回购确认记账票据列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	/**
	 * 功能描述：回购确认记账批次列表页面
	 * @param page
	 * @param query
	 * @return ModelAndView
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=buybackConfirmAccountList")
	public ModelAndView buybackConfirmAccountList(Page page,BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_confirm_account_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("BuybackAccountController", "buybackDoAccount", null)[0]);
			query.setBillClass(CommonConst.BILL_CLASS_ELEC);
			mv.addObject("batchList", buybackService.getBuybackApplyListForCondition(page,query));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("回购确认记账批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "回购确认记账批次列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}
	
	
	/**
	 * 功能描述：回购确认记账
	 * @param page
	 * @param query
	 * @return ModelAndView
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=confirmAccount")
	public ModelAndView confirmAccount(String buybackId,String ids) throws BizAppException{
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			buybackService.confirmAccount(buybackId, ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("回购确认记账失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "回购确认记账失败");
		}
		return buybackConfirmAccountList(new Page(),new BuybackSearchBean());
	}
	
	/*********纸票**********/
	/**
	 * 待记账的批次列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=entityAccountBatchList")
	public ModelAndView entityAccountBatchList(Page page, BuybackSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_account_batch_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			/*query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setOperNo(user.getUserId());*/
			query.setBranchNo(user.getBranchNo());
			query.setBillClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus("BuybackAccountController", "buybackDoAccount",null)[0]);
			mv.addObject("batchList", buybackService.getBuybackApplyListForCondition(page, query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("待记账批次列表");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待记账批次列表");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 待记账的清单列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=accountBillList")
	public ModelAndView accountBillList(Page page, BuybackSearchBean query)
			throws BizAppException {
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_account_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("BuybackAccountController", "buybackDoAccount",null));
			mv.addObject("batch", buybackService.getBuybackApplyInfo(query.getBuybackId(), query));
			mv.addObject("resultList", buybackService.getBuybackBillListForBatch(page, query));
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("待记账的清单列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待记账的清单列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 记账(纸票)
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=entityAccount")
	public ModelAndView entityAccount(String ids,String buybackId) throws BizAppException{
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			ServiceFactory.getBuybackService().accountForEntity(ids,buybackId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据记账失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据记账失败");
		}
		return entityAccountBatchList(new Page(),new BuybackSearchBean());
	}
}
