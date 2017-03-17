package com.herongtech.console.web.busicontroller.saleback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.saleback.bean.SaleBackSearchBean;
import com.herongtech.console.domain.saleback.bean.SalebackApplyInfo;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.sale.ISaleService;
import com.herongtech.console.service.interfaces.ISaleBackService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.parser.exception.ParseException;
@Scope("prototype")
@Controller         
@RequestMapping("/SaleBackAuditController")
public class SaleBackAuditController extends BaseController{
	
	private ISaleBackService service = ServiceFactory.getSaleBackService();
	/**
	 * 审核批次页面
	 * @param bean
	 * @param page
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=entityAuditSalebackBatch")
	public ModelAndView entityAuditSalebackBatch(SaleBackSearchBean bean,Page page) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/saleback/entity/saleback_entity_audit_apply_batch");
		page.activeCommand();
		List<SalebackApplyInfo> batch = new ArrayList<SalebackApplyInfo>();
		bean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
		try {
			batch = service.getSaleBackAuditForCondition(page, bean);//多状态
		}catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("查询批次失败");
			throw new BizAppException("查询批次失败,"+e.getMessage());
		}

		mv.addObject("batchList",batch);
		mv.addObject("page",page);
		return mv;
	}
	
	
	/**
	 * 审核清单页面
	 * @param bean
	 * @param page
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=auditbilllist")
	public ModelAndView auditbilllist(SaleBackSearchBean bean,Page page) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		page.activeCommand();
		bean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
		List<SalebackBillInfo> billlist;
		List<SalebackApplyInfo> applylist;
		SalebackApplyInfo apply;
		try {
			String[] opers = StatusUtils.queryStatus("SaleBackAuditController", "auditselect", null);
			bean.setOpers(opers);
			billlist = service.getauditbilllist(bean, page);
			if(billlist.size()==0){
				return entityAuditSalebackBatch(new SaleBackSearchBean(),new Page());
			}
			applylist = service.getSaleBackAuditForCondition(page,bean);
			apply = applylist.get(0);
		}catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("查询清单失败");
			throw new BizAppException("查询清单失败,"+e.getMessage());
		}
		mv.setViewName("busi/saleback/entity/saleback_entity_audit_bill");
		mv.addObject("batch",apply);
		mv.addObject("resultList",billlist);
		mv.addObject("page",page);
		
		
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
		ModelAndView mv = new ModelAndView("busi/saleback/entity/saleback_audit");
		mv.addObject("ids", ids);
		return mv;
	}
	
	
	/**
	 * 审核提交
	 * @param bean
	 * @param page
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=auditapply")
	@ResponseBody
	public AjaxJson auditapply(String ids,String status,String option) throws BizAppException{
		AjaxJson json = new AjaxJson();
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			service.submitSaleAudit(CommUtils.couvertLong(ids),status,option);
			session.endTransaction();
			json.setSuccess(true);
			json.setMsg("提交审核成功");
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据审核提交失败");
			throw new BizAppException("票据审核提交失败,"+e.getMessage());
		}
		return json;
	}
	
	
	/**
	 * 撤销审核批次页面
	 * @param bean
	 * @param page
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=entityrevokeAuditSalebackBatch")
	public ModelAndView entityrevokeAuditSalebackBatch(SaleBackSearchBean bean,Page page) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/saleback/entity/saleback_entity_cancel_audit_batch");
		page.activeCommand();
		List<SalebackApplyInfo> batch = new ArrayList<SalebackApplyInfo>();
		bean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
		bean.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserId());
		try {
			batch = service.getcancelauditbatchlist(bean, page);
		}catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("查询批次失败");
			throw new BizAppException("查询批次失败,"+e.getMessage());
		}

		mv.addObject("batchList",batch);
		mv.addObject("page",page);
		return mv;

	}
	
	/**
	 * 撤销审核清单页面
	 * @param bean
	 * @param page
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=entityrevokeAuditSalebackBill")
	public ModelAndView entityrevokeAuditSalebackBill(SaleBackSearchBean bean,Page page) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		page.activeCommand();
		bean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
		List<SalebackBillInfo> billlist;
		List<SalebackApplyInfo> applylist;
		SalebackApplyInfo apply;
		try {
			String[] opers = StatusUtils.queryStatus("SaleBackAuditController", "cancelaudit", null);
			bean.setOpers(opers);
			bean.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			billlist = service.getauditbilllist(bean, page);
			if(billlist.size()==0){
				return entityrevokeAuditSalebackBatch(new SaleBackSearchBean(),new Page());
			}
			applylist = service.getcancelauditbatchlist(bean,page);
			apply = applylist.get(0);
		}catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("查询清单失败");
			throw new BizAppException("查询清单失败,"+e.getMessage());
		}
		mv.setViewName("busi/saleback/entity/saleback_entity_cancel_audit_bill");
		mv.addObject("batch",apply);
		mv.addObject("resultList",billlist);
		return mv;
		
	}
	
	
	/**
	 * 审核撤销
	 * @param salebackId
	 * @param salebackmxId
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=revokeauditsubmit")
	public ModelAndView revokeauditsubmit(SaleBackSearchBean bean,Page page,String salebackmxId) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB();
		
		try {
			session.beginTransaction();
			service.cancelaudit(bean,page,salebackmxId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("撤销审核失败");
			throw new BizAppException("撤销审核失败,"+e.getMessage());
		}
		
		return entityrevokeAuditSalebackBill(bean,page);
	}
	
}
