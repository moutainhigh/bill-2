package com.herongtech.console.web.busicontroller.rebuy;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 转入审核Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/rebuyAuditController")
public class RebuyAuditController extends BaseController {

	/**
	 * 待审核批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=listAuditEntity")
	public ModelAndView auditableApplyList(Page page,RebuySearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_batch_audit_entity");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		AjaxJson retJson = new AjaxJson();
		if(searchBean==null){
			searchBean = new RebuySearchBean();
		}
		//设置查询条件
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setDfaultSrchTbAliasName("apply");
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setBillClass(CommonConst.BILL_CLASS_ENTITY);//纸票
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		searchBean.addProperty2TableObj("opers", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAuditController", "auditableApplyList", null));
		} catch (Exception e1) {
			throw new BizAppException("获取"+"状态信息失败");
		}
		try {
			mv.addObject("batchList", rebuyService.getRebuyApplyListBySearchBean(page,searchBean));
		} catch (SQLException e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 待审核票据清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditDetailList")
	public ModelAndView auditDetailList(Page page,String rebuyId,String isReadonly) throws BizAppException{
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_bill_audit");
		if(!"".equals(isReadonly)&&"1".equals(isReadonly)){
			mv = new ModelAndView("busi/rebuy/entity/rebuy_bill_audit_readonly");
		}else{
			mv = new ModelAndView("busi/rebuy/entity/rebuy_bill_audit");
		}
		page.activeCommand();
		searchBean.setRebuyId(rebuyId);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAuditController", "auditDetailList", null));
		} catch (Exception e1) {
			CommonLog.getCommonLogCache().errorMessage("查询票据状态失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询票据状态失败");
		}
		try {
			IRebuyService rebuyService = ServiceFactory.getRebuyService();
			mv.addObject("batch",rebuyService.getApplyInfoById(rebuyId,searchBean));
			mv.addObject("resultList",rebuyService.getRebuyBillListBySearchBean(searchBean,page));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("rebuyId", rebuyId);
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
	public ModelAndView toAudit(String ids,String rebuyId,String billClass){
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_audit");
		mv.addObject("ids",ids);
		mv.addObject("rebuyId",rebuyId);
		mv.addObject("billClass",billClass);
		return mv;
	}
	
	/**
	 * 票据审核
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditSubmit")
	@ResponseBody
	public AjaxJson auditSubmit(String ids, String rebuyId, String status, String reason) throws BizAppException{
		AjaxJson json = new AjaxJson();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.auditSubmit(ids,rebuyId,status,reason);
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
			CommonLog.getCommonLogCache().errorMessage("审核失败:"+e.getMessage());
			throw new BizAppException("审核失败:"+e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 撤销审核批次页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchRevokableBatch")
	public ModelAndView accountableApplyList(Page page,RebuySearchBean searchBean) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/revoke_rebuy_batch_audit_entity");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		//设置查询条件
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		if(searchBean==null){
			searchBean = new RebuySearchBean();
		}
		searchBean.setDfaultSrchTbAliasName("apply");
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setBillClass(CommonConst.BILL_CLASS_ENTITY);//纸票
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		searchBean.addProperty2TableObj("opers", "bill");
		searchBean.setAuditOperNo(user.getUserId());
		searchBean.addProperty2TableObj("auditOperNo", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAuditController", "searchRevokableBatch", null));
		} catch (Exception e1) {
			throw new BizAppException("获取"+"状态信息失败");
		}
		try {
			mv.addObject("batchList", rebuyService.getRebuyApplyListBySearchBean(page,searchBean));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 撤销审核票据清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchRevokableDetail")
	public ModelAndView searchRevokableDetail(Page page,String rebuyId) throws BizAppException{
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/revoke_rebuy_bill_audit");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setRebuyId(rebuyId);
		searchBean.setAuditOperNo(user.getUserId());
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAuditController", "searchRevokableDetail", null));
		} catch (Exception e1) {
			CommonLog.getCommonLogCache().errorMessage("查询票据状态失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询票据状态失败");
		}
		try {
			IRebuyService rebuyService = ServiceFactory.getRebuyService();
			mv.addObject("batch",rebuyService.getApplyInfoById(rebuyId,searchBean));
			mv.addObject("resultList",rebuyService.getRebuyBillListBySearchBean(searchBean,page));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("rebuyId", rebuyId);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 撤销审核
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=revokeAudit")
	public ModelAndView revokeAudit(String ids, Page page, RebuySearchBean searchBean) throws BizAppException{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.revokeAudit(ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据撤销审核提交失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销审核提交失败:"+e.getMessage());
		}
		mv = this.accountableApplyList(page, searchBean);
		return mv;
	}
	
	
	/**电票--转入审核--跳转到批次列表页面（ljt）*/
	@RequestMapping(params = "method=listAuditElec")
	public ModelAndView elecauditableApplyList(Page page,RebuySearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/rebuy_audit_batch_elec");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		if(searchBean==null){
			searchBean = new RebuySearchBean();
		}
		//设置查询条件
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setDfaultSrchTbAliasName("apply");
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setBillClass(CommonConst.BILL_CLASS_ELEC);//电票
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		searchBean.addProperty2TableObj("opers", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAuditController", "searchAuditableListElec", null));
		} catch (Exception e1) {
			throw new BizAppException("获取"+"状态信息失败");
		}
		try {
			mv.addObject("batchList", rebuyService.getRebuyApplyListBySearchBean(page,searchBean));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 电票--待审核票据清单列表页面（ljt）
	 */
	@RequestMapping(params = "method=elecauditDetailList")
	public ModelAndView elecauditDetailList(Page page,String rebuyId,String isReadonly) throws Exception{
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/rebuy_audit_bill_elec");
		if(!"".equals(isReadonly)&&"1".equals(isReadonly)){
			mv = new ModelAndView("busi/rebuy/electronic/rebuy_audit_bill_elec_readonly");
		}else{
			mv = new ModelAndView("busi/rebuy/electronic/rebuy_audit_bill_elec");
		}
		page.activeCommand();
		searchBean.setRebuyId(rebuyId);
		searchBean.setOpers(StatusUtils.queryStatus("RebuyAuditController", "searchAuditableListElec", null));
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuyApplyInfo batch = rebuyService.getApplyInfoById(rebuyId,searchBean);
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean,page);
		IProductService prodService = ServiceFactory.getProductService();
		Product product = prodService.getProductInfoByProdNo(batch.getProdNo());
		mv.addObject("prodName",product.getProdName());
		mv.addObject("batch",batch);
		mv.addObject("resultList",billList);
		mv.addObject("rebuyId", rebuyId);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 电票--转入票据审核（ljt）
	 */
	@RequestMapping(params = "method=elecauditSubmit")
	@ResponseBody
	public AjaxJson elecauditSubmit(Page page,String ids, String rebuyId, String status, String reason) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.checkCancelState(ids);
			rebuyService.elecauditSubmit(ids,rebuyId,status,reason);//调用电票转入审核的方法
			session.endTransaction();
			aj.setSuccess(true);
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("审核失败:"+e.getMessage());
			throw new BizAppException("审核失败:"+e.getMessage());
		}
		return aj;
	}
	
	/**
	 * 电票--转入撤销审核批次页面（ljt）
	 */
	@RequestMapping(params = "method=searchRevokableAuditBatchElec")
	public ModelAndView searchRevokableAuditBatchElec(Page page,RebuySearchBean searchBean) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/revoke_rebuy_audit_batch_elec");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		//设置查询条件
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		if(searchBean==null){
			searchBean = new RebuySearchBean();
		}
		searchBean.setDfaultSrchTbAliasName("apply");
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setBillClass(CommonConst.BILL_CLASS_ELEC);//电票
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		searchBean.addProperty2TableObj("opers", "bill");
		searchBean.setAuditOperNo(user.getUserId());
		searchBean.addProperty2TableObj("auditOperNo", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAuditController", "revokeAuditElec", null));
		} catch (Exception e1) {
			throw new BizAppException("获取"+"状态信息失败");
		}
		try {
			mv.addObject("batchList", rebuyService.getRebuyApplyListBySearchBean(page,searchBean));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 电票--转入撤销审核票据清单列表页面（ljt）
	 */
	@RequestMapping(params = "method=elecsearchRevokableDetail")
	public ModelAndView elecsearchRevokableDetail(Page page,String rebuyId) throws Exception{
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/revoke_rebuy_audit_bill_elec");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setRebuyId(rebuyId);
		searchBean.setAuditOperNo(user.getUserId());
		searchBean.setOpers(StatusUtils.queryStatus("RebuyAuditController", "revokeAuditElec", null));
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuyApplyInfo batch = rebuyService.getApplyInfoById(rebuyId,searchBean);
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean,page);
		IProductService prodService = ServiceFactory.getProductService();
		Product product = prodService.getProductInfoByProdNo(batch.getProdNo());
		mv.addObject("prodName",product.getProdName());
		mv.addObject("batch",batch);
		mv.addObject("resultList",billList);
		mv.addObject("rebuyId", rebuyId);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 电票--转入撤销审核（ljt）
	 */
	@RequestMapping(params = "method=elecrevokeAudit")
	public ModelAndView elecrevokeAudit(String ids, Page page, RebuySearchBean searchBean) throws BizAppException{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.elecrevokeAudit(ids);
			mv = this.searchRevokableAuditBatchElec(page, searchBean);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据撤销审核提交失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销审核提交失败:"+e.getMessage());
		}
		return mv;
	}
	
}
