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
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.IRiskBillCheckService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 转入记账Controller
 *
 */
/**
 * @author 李江涛
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/rebuyAccountController")
public class RebuyAccountController extends BaseController {
	/**
	 * 转入记账批次页面
	 * 
	 * @param request   
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=listAccountEntity")
	public ModelAndView accountableApplyList(Page page,RebuySearchBean searchBean) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_batch_acct_entity");
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
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAccountController", "accountableApplyList", null));
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
	 * 待审核票据清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=reauditDetailList")
	public ModelAndView reauditDetailList(Page page,String rebuyId) throws BizAppException{
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_bill_acct");
		page.activeCommand();
		searchBean.setRebuyId(rebuyId);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAccountController", "reauditDetailList", null));
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
	@RequestMapping(params = "method=toReaudit")
	public ModelAndView toAudit(String ids){
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_reaudit");
		mv.addObject("ids", ids);
		return mv;
	}
	
	/**
	 * 票据复核
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=reauditSubmit")
	@ResponseBody
	public AjaxJson reauditSubmit(String ids,String status) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = rebuyService.reauditSubmit(ids,status);
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
	
	/**
	 * 记账提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=accountSubmit")
	public ModelAndView accountSubmit(String ids,String rebuyId) throws BizAppException {
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = rebuyService.accountSubmit(ids,rebuyId);
			if(rs>0){
				mv = this.accountableApplyList(new Page(), new RebuySearchBean());
			}
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("记账失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "记账失败:"+e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 撤销记账批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchRevokableBatch")
	public ModelAndView searchRevokableBatch(Page page,RebuySearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/revoke_rebuy_batch_acct_entity");
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
		searchBean.setAcctOperNo(user.getUserId());
		searchBean.addProperty2TableObj("acctOperNo", "bill");
		searchBean.setAccountDate(DateTimeUtil.getWorkdayString());
		searchBean.addProperty2TableObj("accountDate", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAccountController", "searchRevokableBatch", null));
		} catch (Exception e1) {
			throw new BizAppException("获取状态信息失败");
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
	 * 撤销记账票据清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchRevokableDetail")
	public ModelAndView searchRevokableDetail(Page page,String rebuyId) throws BizAppException{
		page.activeCommand();
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/revoke_rebuy_bill_acct");
		searchBean.setRebuyId(rebuyId);
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setAcctOperNo(user.getUserId());
		searchBean.setAccountDate(DateTimeUtil.getWorkdayString());
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAccountController", "searchRevokableDetail", null));
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
	 * 撤销记账
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=revokeAccount")
	public ModelAndView revokeAccount(String ids,String rebuyId) throws BizAppException{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.revokeAccount(ids,rebuyId);
			mv = this.searchRevokableBatch(new Page(), new RebuySearchBean());
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据撤销记账提交失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销记账提交失败:"+e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 风险票检查
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "method=checkRiskBills")
	@ResponseBody
	public AjaxJson checkRiskBills(String ids){
		AjaxJson aj = new AjaxJson();
		IRiskBillCheckService riskBillCheckService = ServiceFactory.getRiskBillCheckService();
		List<BillInfoDTO> resultList = null;
		try {
			resultList = riskBillCheckService.checkRiskInfo("getBillInfoDTO", "RebuyService", ids);
		} catch (BizAppException e) {
			e.printStackTrace();
			aj.setSuccess(false);
			aj.setMsg("检查风险票据失败"+e.getMessage());
			return aj;
		}
		if(resultList!=null&&resultList.size()>0){
			aj.setSuccess(false);
			aj.setMsg("存在风险票据");
			aj.setObj(resultList);
			return aj;
		}
		aj.setSuccess(true);
		return aj;
	}
	
	/**
	 * 跳转到风险票页面
	 * @param data
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params = "method=toRiskBillsPage")
	public ModelAndView toRiskBillsPage(String ids){
		ModelAndView mv = null;
		mv = new ModelAndView("busi/rebuy/entity/rebuy_riskbill");
		IRiskBillCheckService riskBillCheckService = ServiceFactory.getRiskBillCheckService();
		List<BillInfoDTO> resultList = null;
		try {
			resultList = riskBillCheckService.checkRiskInfo("getBillInfoDTO", "RebuyService", ids);
		} catch (BizAppException e) {
			e.printStackTrace();
			mv.addObject("msg","检查风险票据失败"+e.getMessage());
			mv.addObject("resultList",resultList);
			return mv;
		}
		mv.addObject("msg","存在风险票据");
		mv.addObject("resultList",resultList);
		return mv;
	}
	
	/**
	 * 电票--跳转到电票转入记账批次页面（ljt）
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=listAccountElec")
	public ModelAndView toElectricticketbookingBatch(Page page,RebuySearchBean searchBean) throws Exception{
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/rebuy_acct_batch_elec");  //电票记账批次页面
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
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		searchBean.setOpers(StatusUtils.queryStatus("RebuyAccountController", "accountableApplyList", null));
		mv.addObject("batchList", rebuyService.getRebuyApplyListBySearchBean(page,searchBean));
		mv.addObject("page", page);
		return mv;
		
	}
	
	/**
	 * 电票--转入记账清单页面（ljt）
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecreauditDetailList")
	public ModelAndView elecreauditDetailList(Page page,String rebuyId) throws Exception{
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/rebuy_acct_bill_elec");
		page.activeCommand();
		searchBean.setRebuyId(rebuyId);
		searchBean.setOpers(StatusUtils.queryStatus("RebuyAccountController", "reauditDetailList", null));
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
	 * 电票--转入记账提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecaccountSubmit")
	public ModelAndView elecaccountSubmit(String ids,String rebuyId) throws BizAppException {
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		try {
			rebuyService.checkCancelState(ids);
			rebuyService.elecAccountSubmit(ids,rebuyId);
			mv = this.toElectricticketbookingBatch(new Page(), new RebuySearchBean());
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("记账失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "记账失败:"+e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 电票--跳转到电票记账确认批次页面（ljt）
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=listAccountConfirmElec")
	public ModelAndView listAccountConfirmElec(Page page,RebuySearchBean searchBean)throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/rebuy_acct_confirm_batch");  //电票记账批次页面
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		searchBean.setBillClass(billClass);
		searchBean.setBranchNo(branchNo);
		
		searchBean.setDfaultSrchTbAliasName("apply");
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		searchBean.addProperty2TableObj("opers", "bill");
	
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		try {
			page.activeCommand();
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAccountController", "searchConfirmableListElec", null));
			mv.addObject("batchList", rebuyService.getRebuyApplyListBySearchBean(page,searchBean));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		return mv;

	}
	
	/**
	 * 电票--确认记账票据列表页面（ljt）
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecAccountBillList")
	public ModelAndView elecAccountBillList(Page page,RebuySearchBean searchBean ) throws BizAppException{
		IProductService productService = ServiceFactory.getProductService();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/rebuy_acct_confirm_bill");
		try {
			RebuyApplyInfo batch = rebuyService.getRebuyApplyInfo(searchBean.getRebuyId());
			Product prod = productService.getProductInfoByProdNo(batch.getProdNo());
			page.activeCommand();
			session.beginTransaction();
			searchBean.setOpers(StatusUtils.queryStatus("RebuyAccountController", "searchConfirmableListElec", null));
			List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean, page);
			if(billList.size()!=0){
				String ids = rebuyService.getBillIdsString(billList);
				batch = rebuyService.sumInfo(batch, ids);
			}
			mv.addObject("batch",batch);
			mv.addObject("resultList",billList);
			mv.addObject("prodName", prod.getProdName());
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待记账票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 电票记账确认
	 * @param batch
	 * @param ids
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=accountConfirmSubmitElec")
	public ModelAndView accountConfirmSubmitElec(RebuyApplyInfo batch,String ids) throws BizAppException{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			rebuyService.confirmAccount(batch,ids);
			mv = this.listAccountConfirmElec(new Page(),new RebuySearchBean());
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待记账票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		return mv;
	}
	
	
}
