package com.herongtech.console.web.busicontroller.rebuy;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.ProdLimitType;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.console.service.interfaces.IRiskBillCheckService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 转入申请Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/rebuyApplyController")
public class RebuyApplyController extends BaseController {

	/**
	 * 功能描述：转贴现转入申请页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=apply")
	public ModelAndView apply(String custType,String custBankNo) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		RebuySearchBean searchBean = new RebuySearchBean();
		if("1".equals(custType)){
			mv = new ModelAndView("busi/rebuy/entity/rebuy_batch_apply_entity_outer");
			searchBean.setCustBankNo(custBankNo);
			searchBean.setIsInner(RebuyCodeConst.IS_INNER_FALSE);
			mv.addObject("searchBean",searchBean);
		}else if("2".equals(custType)){
			mv = new ModelAndView("busi/rebuy/entity/rebuy_batch_apply_entity_inner");
			searchBean.setIsInner(RebuyCodeConst.IS_INNER_TRUE);
			mv.addObject("searchBean",searchBean);
		}else{
			mv = new ModelAndView("busi/rebuy/entity/rebuy_batch_apply_entity");
		}
		return mv;
	}
	
	/**
	 * 根据行号查询银行信息
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=bankInfo")
	@ResponseBody
	public AjaxJson queryBankInfo(String custBankNo) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();
		//是否系统内行号判断
		if(CommUtils.isSelfBank(custBankNo)){
			retJson.setMsg("该行号信息为系统内行号，不允许做同业交易");
			retJson.setSuccess(false);
			return retJson;
		}
		// 获取客户信息和账号处理类
		IEcdsBankDataService bankService = RcServiceFactory.getEcdsBankDataService();
		EcdsBankData bankData;
		try {
			bankData = bankService.getEcdsBankData(custBankNo);
		} catch (BizAppException e) {
			retJson.setMsg("查询数据库信息失败:"+e.getMessage());
			retJson.setSuccess(false);
			return retJson;
		}
		if(bankData==null){
			retJson.setMsg("根据行号无法找到对应的银行信息，请检查行号信息是否有误");
			retJson.setSuccess(false);
			return retJson;
		}
		
		retMap.put("custBankNo", bankData.getRowNumber());
		retMap.put("custBankName", bankData.getActorFullCall());
		retJson.setAttributes(retMap);
		retJson.setSuccess(true);
		
		return retJson;
	}
	
	/**
	 * 批次查询
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchBatch")
	public ModelAndView searchApplyBatch(Page page,RebuySearchBean searchBean) throws Exception{
		ModelAndView mv = new ModelAndView();
		if(searchBean==null){
			searchBean=new RebuySearchBean();
		}
		if(RebuyCodeConst.IS_INNER_FALSE.equals(searchBean.getIsInner())){
			mv = new ModelAndView("busi/rebuy/entity/rebuy_batch_apply_entity_outer");
			mv.addObject("custBankNo",searchBean.getCustBankNo());
		}else if(RebuyCodeConst.IS_INNER_TRUE.equals(searchBean.getIsInner())){
			mv = new ModelAndView("busi/rebuy/entity/rebuy_batch_apply_entity_inner");
		}
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		List<RebuyApplyInfo> batchList = rebuyService.getRebuyApplyListBySearchBeanOnlyForApply(page, searchBean);
		mv.addObject("batchList",batchList);
		mv.addObject("searchBean",searchBean);
		mv.addObject("page", page);
		return mv;
	} 
	
	
	/**
	 * 新增批次页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toAddBatch")
	public ModelAndView toAddBatch(String bankNo) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		RebuyApplyInfo apply = new RebuyApplyInfo();
		IEcdsBankDataService bankService = RcServiceFactory.getEcdsBankDataService();
		EcdsBankData bankData = new EcdsBankData();
		bankData = bankService.getEcdsBankData(bankNo);
		apply.setCustBankNo(bankNo);
		apply.setCustBankName(bankData.getActorFullCall());
		
		apply.setBillClass(CommonConst.BILL_CLASS_ENTITY);
		apply.setTradeAcctType(CommonConst.TRADE_ACCT_TYPE_SETTLE);
		apply.setRebuyDt("");
		apply.setResaleDueDt("");
		apply.setResaleStaDt("");
		List<Product> prodList = null;
		try {
			prodList=ServiceFactory.getProductService().getProductListForApplyBatch(RebuyCodeConst.PRODUCT_ID, CommonConst.BILL_CLASS_ENTITY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.setViewName("busi/rebuy/entity/rebuy_editBatch");
		mv.addObject("apply",apply);
		mv.addObject("isedit", "0");
		mv.addObject("prodList",prodList);
		mv.addObject("workday",DateTimeUtil.getWorkdayString());
		mv.addObject("resaleStaDt",DateTimeUtil.getNextWorkdayString());
		return mv;
	}
	
	/**
	 * 修改批次信息页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toEditBatch")
	public ModelAndView toEditBatch(String rebuyId) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		RebuyApplyInfo apply = new RebuyApplyInfo();
		IRebuyService service = ServiceFactory.getRebuyService();
		RebuySearchBean searchBean = new RebuySearchBean();
		searchBean.setDfaultSrchTbAliasName("bill");
		searchBean.setRebuyId(rebuyId);
		String[] opers;
		List<Product> prodList=new ArrayList<Product>();
		try {
			opers = StatusUtils.queryStatus("RebuyApplyController", "toEditBatch", null);
		} catch (Exception e2) {
			CommonLog.getCommonLogCache().errorMessage("查询状态机信息失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询状态机信息失败");
		}
		searchBean.setOpers(opers);
		searchBean.addSpecialOpertion("opers", BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		int count=0;
		try {
			count = service.getRebuyBillCountBySearchBean(searchBean);
		} catch (SQLException e1) {
			throw new BizAppException("查询票据清单异常:"+e1.getMessage());
		}
		if(count>0){
			throw new BizAppException("该批次下含有已提交的票据，不允许修改");
		}
		try {
			apply = service.getApplyInfoById(rebuyId);
			prodList=ServiceFactory.getProductService().getProductListForApplyBatch(RebuyCodeConst.PRODUCT_ID, apply.getBillType(), RebuyCodeConst.IS_INNER_FALSE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (ServiceFactory.getProductService().getProdLimitTypeByProdNo(apply.getProdNo()).equals("1")) {
				mv.addObject("type","1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.setViewName("busi/rebuy/entity/rebuy_editBatch");
		mv.addObject("apply",apply);
		mv.addObject("isedit", "1");
		mv.addObject("prodList", prodList);
		mv.addObject("workday",DateTimeUtil.getWorkdayString());
		mv.addObject("resaleStaDt",DateTimeUtil.getNextWorkdayString());
		return mv;
	}
	
	/**
	 * 根据billType获取产品列表
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=getProdType")
	@ResponseBody
	public AjaxJson getProdType(String billType){
		AjaxJson retJson = new AjaxJson();
		IProductService prodService = ServiceFactory.getProductService();
		List<Product> prodList = new ArrayList<Product>();
		try {
			prodList = prodService.getProductListForApplyBatch(RebuyCodeConst.PRODUCT_ID, billType, RebuyCodeConst.IS_INNER_FALSE);
		} catch (SQLException e) {
			retJson.setSuccess(false);
			retJson.setMsg("查询产品信息失败:"+e.getMessage());
		}
		retJson.setObj(prodList);
		retJson.setSuccess(true);
		return retJson;
	} 
	
	/**
	 * 根据prodNo获取产品列表
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=getProdName")
	@ResponseBody
	public AjaxJson getProdName(String prodNo){
		AjaxJson retJson = new AjaxJson();
		IProductService prodService = ServiceFactory.getProductService();
		Product prod = new Product();
		try {
			prod = prodService.getProductInfoByProdNo(prodNo);
		} catch (SQLException e) {
			retJson.setSuccess(false);
			retJson.setMsg("查询产品信息失败:"+e.getMessage());
		}
		retJson.setObj(prod.getProdName());
		retJson.setSuccess(true);
		return retJson;
	} 
	
	/**
	 * 根据prodNo获取买入类型
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=getBuyType")
	@ResponseBody
	public AjaxJson getBuyType(String prodNo) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		IProductService prodService = ServiceFactory.getProductService();
		ProdLimitType prod = new ProdLimitType();
		try {
			prod = prodService.getProdLimitTypeByProdNo(prodNo);
		} catch (SQLException e) {
			retJson.setSuccess(false);
			retJson.setMsg("查询产品信息失败");
			CommonLog.getCommonLogCache().errorMessage("查询产品信息失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询产品信息失败");
		}
		retJson.setObj(prod.getBuyType());
		retJson.setSuccess(true);
		return retJson;
	} 
	
	/**
	 * 新增或修改批次信息
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=saveBatch")
	public ModelAndView saveBatch(RebuyApplyInfo apply, String isedit) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IRebuyService service = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if("0".equals(isedit)){//新增
				service.addBatch(apply);
			}else{//编辑
				service.modifyBatch(apply);
			}
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("插入票据清单失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入票据清单失败");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 票据管理页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=billManage")
	public ModelAndView billManage(Page page,RebuySearchBean searchBean,String rebuyId) throws BizAppException{
		ModelAndView mv;
		if(searchBean==null){
			searchBean = new RebuySearchBean();
		}
		if(RebuyCodeConst.IS_INNER_FALSE.equals(searchBean.getIsInner())){
			mv = new ModelAndView("busi/rebuy/entity/rebuy_bill_apply_outer");
		}else{
			mv = new ModelAndView("busi/rebuy/entity/rebuy_bill_apply_inner");
		}
		page.activeCommand();
		searchBean.setRebuyId(rebuyId);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "billManage", null));
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
		mv.addObject("page", page);
		mv.addObject("rebuyId", rebuyId);
		mv.addObject("searchBean",searchBean);
		return mv;
	}
	
	/**
	 * 删除批次信息
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=delBatch")
	@ResponseBody
	public AjaxJson delBatch(String rebuyIds){

		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		String[] ids = rebuyIds.split(",");
		IRebuyService service = ServiceFactory.getRebuyService();
		try{
			for(String id : ids){
				int count = service.getBillCountByRebuyId(id);
				if(count>0){
					retJson.setMsg("批次下含有未处理的票据，不允许删除");
					retJson.setSuccess(false);
					return retJson;
				}
			}
		} catch (Exception e) {
			retJson.setMsg("查询数据库失败");
			retJson.setSuccess(false);
			return retJson;
		}
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			service.deleteBatchesByRebuyId(ids,CommonConst.APPLY_STATUS_DELETE);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			retJson.setMsg("删除数据库信息失败"+e.getMessage());
			retJson.setSuccess(false);
			return retJson;
		}
		retJson.setAttributes(retMap);
		retJson.setSuccess(true);
		
		return retJson;
	}
	
	/**
	 * 删除清单信息
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=delBill")
	@ResponseBody
	public AjaxJson delBill(String ids) throws BizAppException{

		AjaxJson retJson = new AjaxJson();
		IRebuyService service = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			service.deleteBillsByRebuymxId(ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			retJson.setMsg("删除数据库信息失败:"+e.getMessage());
			retJson.setSuccess(false);
			return retJson;
		}
		retJson.setSuccess(true);
		return retJson;
	}
	
	/**
	 * 票据明细展示页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=goDetail")
	public ModelAndView goDetail(String rebuymxId,String rebuyId) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_bill_detail");
		RebuyBillInfo info = new RebuyBillInfo();
		RebuyApplyInfo apply = new RebuyApplyInfo();
		IRebuyService service = ServiceFactory.getRebuyService();
		try {
			info = service.getBillInfoByRebuymxId(rebuymxId);
			apply = service.getApplyInfoById(rebuyId);
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("票据清单查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据清单查询失败");
		}
		
		mv.addObject("bill",info);
		mv.addObject("apply",apply);
		return mv;
	}

	/**
	 * 票据提交
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=applySubmit")
	public ModelAndView applySubmit(String ids, String rebuyId, Page page, RebuySearchBean searchBean) throws Exception{
		ModelAndView mv = new ModelAndView();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = rebuyService.applySubmit(ids,rebuyId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据申请提交失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据申请提交失败:"+e.getMessage());
		}
		if(rs>0){
			mv = this.searchApplyBatch(page, searchBean);
		}else{
			CommonLog.getCommonLogCache().errorMessage("票据申请提交失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据申请提交失败");
		}
		return mv;
	}
	
	/**
	 * 跳转到新增票据页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=toAddBill")
	public ModelAndView toAddBill(String billType,String rebuyId,String action) throws BizAppException{
		ModelAndView mv = null;
		//1银票 2商票
		if("1".equals(billType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		try {
			mv.addObject("batch",rebuyService.getRebuyApplyInfo(rebuyId));
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("action",action);
		mv.addObject("batchId",rebuyId);
		mv.addObject("billType",billType);
		mv.addObject("isedit","0");
		return mv;
	}
	
	/**
	 * 跳转到复制录入票据页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=toCopyBill")
	public ModelAndView toCopyBill(String billType,String rebuyId,String action,String rebuymxId) throws BizAppException{
		ModelAndView mv = null;
		//1银票 2商票
		if("1".equals(billType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		try {
			mv.addObject("batch",rebuyService.getRebuyApplyInfo(rebuyId));
			mv.addObject("bill",rebuyService.getRebuyBillInfo(rebuymxId));
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("action",action);
		mv.addObject("batchId",rebuyId);
		mv.addObject("billType",billType);
		mv.addObject("isedit","0");
		mv.addObject("copyadd","1");//标识为复制录入
		return mv;
	}
	
	/**
	 * 跳转到修改票据页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=toEditBill")
	public ModelAndView toEditBill(String action,String rebuymxId) throws BizAppException{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuyBillInfo info = new RebuyBillInfo();
		try {
			info = rebuyService.getRebuyBillInfo(rebuymxId);
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		String billType = info.getBillType();
		if(CommonConst.BILL_TYPE_BANK.equals(billType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		mv.addObject("bill",info);
		mv.addObject("billId",rebuymxId);
		mv.addObject("action",action);
		mv.addObject("batchId",info.getRebuyId());
		mv.addObject("billType",billType);
		mv.addObject("isedit","1");
		return mv;
	}
	
	/**
	 * 新增/修改票据
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=saveBill")
	public ModelAndView saveBill(RebuyBillInfo bill,String isedit,String batchId,String billId) throws BizAppException{
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				rebuyService.modRebuyBillInfo(billId,bill);
			}else{//新增
				
				rebuyService.addRebuyBillInfo(bill,batchId); //插入
			}
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("插入票据清单失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入票据清单失败");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 根据客户经理编号查询客户经理信息
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=custManagerInfo")
	@ResponseBody
	public AjaxJson custManagerInfo(String custManagerNo){

		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();
		ICustManageService custManageService = ServiceFactory.getCustManageService();
		custManagerNo=custManagerNo.trim();
		// 取客户经理基本信息
		CustManage custManage;
			try {
				custManage=custManageService.getCustManage(custManagerNo);
			} catch (BizAppException e) {
				e.printStackTrace();
				retJson.setMsg("查询数据库信息失败");
				retJson.setSuccess(false);
				e.printStackTrace();
				return retJson;
			}
	
		if(custManage==null){
			retJson.setMsg("根据客户无法找到对应的客户经理信息");
			retJson.setSuccess(false);
			return retJson;
		}
		
		retMap.put("custManage",custManagerNo);
		retMap.put("custManagerName",custManage.getCustManagerName());
		retMap.put("deptName",custManage.getDeptName());
		retJson.setAttributes(retMap);
		retJson.setSuccess(true);
		
		return retJson;
	}
	
	/**
	 * 去利息试算页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toCalculateInterestPage")
	public ModelAndView toCalculateInterestPage(String ids) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("busi/rebuy/entity/rebuy_calculate_interest");
		mv.addObject("ids", ids);
		return mv;
	}
	
	/**
	 * 利息计算
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=calculateInterest")
	public ModelAndView calculateInterest(String ids, String isSameCity, String delayType, String delayDays) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		try {
			session.beginTransaction();
			rebuyService.calculateInterest(ids.split(","),isSameCity,delayType,delayDays);
			session.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("利息试算失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "利息试算失败");
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		
		return mv;
	}

	/**
	 * 批次查看
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=goBatchDetail")
	public ModelAndView goBatchDetail(String rebuyId){
		RebuyApplyInfo rebuyApplyInfo = new RebuyApplyInfo();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		try {
			rebuyApplyInfo = rebuyService.getRebuyApplyInfo(rebuyId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_batch_detail");
		mv.addObject("rebuyApplyInfo", rebuyApplyInfo);
		return mv;
	}
	
	/**
	 * 待撤销申请批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchRevokableBatch")
	public ModelAndView auditableApplyList(Page page,RebuySearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/revoke_rebuy_batch_apply_entity");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
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
		searchBean.setApplyOperNo(user.getUserId());
		searchBean.addProperty2TableObj("applyOperNo", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "searchRevokableBatch", null));
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
	 * 撤销申请票据清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchRevokableDetail")
	public ModelAndView searchRevokableDetail(Page page,String rebuyId) throws BizAppException{
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/revoke_rebuy_bill_apply");
		page.activeCommand();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setRebuyId(rebuyId);
		searchBean.setApplyOperNo(user.getUserId());
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "searchRevokableDetail", null));
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
	 * 撤销申请
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=revokeApply")
	public ModelAndView revokeApply(String ids,String rebuyId) throws BizAppException{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.revokeApply(ids,rebuyId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据撤销申请提交失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销申请提交失败:"+e.getMessage());
		}
		mv = this.auditableApplyList(new Page(), new RebuySearchBean());
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
	
	/**是否计算过利息
	 * @throws BizAppException 
	 * @throws Exception */
	@RequestMapping(params="method=isRate")
	@ResponseBody
	public AjaxJson isRate(String ids, String rebuyId) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		try {
			aj.setSuccess(rebuyService.israte(ids));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("查询利息试算结果失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询利息试算结果失败");
		}
		
		return aj;
	}
	
	
	
	/**
	 * 功能描述：转贴现转入申请页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=applyMainElec")
	public ModelAndView applyMainElec(String custType,String custBankNo) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		RebuySearchBean searchBean = new RebuySearchBean();
		if("1".equals(custType)){
			mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_receive_elec_outer");
			searchBean.setCustBankNo(custBankNo);
			searchBean.setIsInner(RebuyCodeConst.IS_INNER_FALSE);
			mv.addObject("searchBean",searchBean);
		}else if("2".equals(custType)){
			mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_receive_elec_inner");
			searchBean.setIsInner(RebuyCodeConst.IS_INNER_TRUE);
			mv.addObject("searchBean",searchBean);
		}else{
			mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_receive_elec");
		}
		return mv;
	}
	
	
	/**
	 * 查询同业转入待签收的电票
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchReceivableBill")
	public ModelAndView searchReceivableBill(Page page,RebuySearchBean searchBean) throws Exception{
		ModelAndView mv = new ModelAndView();
		if(searchBean==null){
			searchBean=new RebuySearchBean();
		}
		mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_receive_elec_outer");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		String custBankName = searchBean.getCustBankName();
		searchBean.setCustBankName(null);
		searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "searchReceivableBill", null));
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setApplyCancelFlag(IConstants.NO);
		searchBean.setBillClass(CommonConst.BILL_CLASS_ELEC);
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean, page);
		searchBean.setCustBankName(custBankName);
		mv.addObject("billList",billList);
		mv.addObject("searchBean",searchBean);
		mv.addObject("page", page);
		return mv;
	} 
	
	/**
	 * 查询系统内转入待签收的电票
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchInnerReceivableBill")
	public ModelAndView searchInnerReceivableBill(Page page, String brchNo) throws Exception{
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_receive_elec_inner");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		RebuySearchBean searchBean = new RebuySearchBean();
		if((brchNo!=null)&&(!"".equals(brchNo))){
			Branch branch = ServiceFactory.getBranchService().getBranch(brchNo);
			searchBean.setCustBankNo(branch.getPayBankNo());
		}
		searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "searchReceivableBill", null));
		searchBean.setIsInner(RebuyCodeConst.IS_INNER_TRUE);
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setApplyCancelFlag(IConstants.NO);
		searchBean.setBillClass(CommonConst.BILL_CLASS_ELEC);
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean, page);
		mv.addObject("billList",billList);
		mv.addObject("brchNo",brchNo);
		mv.addObject("page", page);
		return mv;
	} 
	
	/**
	 * 电票签收拒绝
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="method=rejectApply")
	public ModelAndView rejectApply(String ids,RebuySearchBean searchBean) throws Exception{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.rejectApplyElec(ids);
			mv = this.searchReceivableBill(new Page(), searchBean);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据签收拒绝失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据签收拒绝失败:"+e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 系统内电票签收拒绝
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params="method=rejectInnerApply")
	public ModelAndView rejectInnerApply(String ids,String brchNo) throws Exception{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.rejectApplyElec(ids);
			mv = this.searchInnerReceivableBill(new Page(), brchNo);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据签收拒绝失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据签收拒绝失败:"+e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 校验接收时所选的票据是否一致
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params="method=checkElecBill")
	@ResponseBody
	public AjaxJson checkElecBill(String ids){
		AjaxJson retJson = new AjaxJson();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		try {
			List<RebuyBillInfo> list = rebuyService.getRebuyBillListByIds(ids);
			RebuyBillInfo baseBill = list.get(0);
			if(list.size()==1){
				retJson.setSuccess(true);
			}else{
				for(int i = 1;i<list.size();i++){
					RebuyBillInfo bill = list.get(i);
					//票据类型
		    		if(!StringUtils.equals(baseBill.getBillType(), bill.getBillType())){
		    			retJson.setMsg("票据类型不统一,应全部为银票或商票");
						retJson.setSuccess(false);
						return retJson;
		    		}
	    			//转贴现类型
	    			if(!StringUtils.equals(baseBill.getIsRegress(), bill.getIsRegress())){
		    			retJson.setSuccess(false);
		    			retJson.setMsg("票据转贴现类型不统一,应全部为卖断类或回购类");
		    			return retJson;
		    		}
	    			//转入日
	    			if(!StringUtils.equals(baseBill.getRebuyDt(), bill.getRebuyDt())){
		    			retJson.setSuccess(false);
		    			retJson.setMsg("转入日不统一");
		    			return retJson;
		    		}
	    			//利率 
	    			if(!StringUtils.equals(MoneyUtils.rate2String(baseBill.getRate()),MoneyUtils.rate2String(bill.getRate()))){
		    			retJson.setSuccess(false);
		    			retJson.setMsg("票据转贴现利率不统一");
		    			return retJson;
		    		}
	    			//是否央行卖票
	    			if(!StringUtils.equals(baseBill.getIsRediscCenter(), bill.getIsRediscCenter())){
		    			retJson.setSuccess(false);
		    			retJson.setMsg("票据央行卖票标记不统一");
		    			return retJson;
		    		}
	    			//清算方式
	    			if(!StringUtils.equals(baseBill.getIsOnline(),bill.getIsOnline())){
	    				retJson.setSuccess(false);
	    				retJson.setMsg("清算方式不统一,应全部为线上清算或线下清算");
	    				return retJson;
	    			}
	    			//交易对手
		    		if(!StringUtils.equals(baseBill.getCustBankNo(), bill.getCustBankNo())){
		    			retJson.setMsg("交易对手行不统一");
						retJson.setSuccess(false);
						return retJson;
		    		}
				}
				//重复票校验
				try{
					checkReapBills(list);
				}catch(BizAppException e){
					retJson.setSuccess(false);
    				retJson.setMsg(e.getMessage());
    				return retJson;
				}
			}
			return retJson;
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg("查询票据清单失败："+e.getMessage());
			retJson.setSuccess(false);
			return retJson;
		}
	}
	
	/**
	 * 重复性校验
	 * @param list
	 * @throws BizAppException
	 */
	private void checkReapBills(List<RebuyBillInfo> list) throws BizAppException{
		Map<String,String> map = new HashMap<String,String>(list.size());
		String existNos = "";
		for(RebuyBillInfo bill:list){
			if(!map.containsKey(bill.getBillNo())){
				map.put(bill.getBillNo(), bill.getAcceptor());
			}else{
				existNos += bill.getBillNo()+","; 
			}
		}
		map=null;
		if(existNos.length()>0){
			throw new BizAppException("票号："+Arrays.toString(existNos.split(","))+"的票据已经在本批次内存在多张，请核实");
		}
	}
	
	/**
	 * 跳转到电票接收页面
	 * @param ids
	 * @param searchBean
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(params="method=toReceivePage")
	public ModelAndView toReceivePage(Page page,String ids,RebuySearchBean searchBean) throws Exception{
		ModelAndView mv = null;
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuyApplyInfo batch = new RebuyApplyInfo();
		String custBankName = searchBean.getCustBankName();
		searchBean.setCustBankName(null);
		searchBean.setMxIds(ids.split(","));
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean, page);
		RebuyBillInfo bill = billList.get(0);
		batch.setBillType(bill.getBillType());
		batch.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		batch.setDiscType(bill.getIsRegress());
		batch.setRebuyDt(bill.getRebuyDt());
		batch.setIsRediscCenter(bill.getIsRediscCenter());
		batch.setRate(bill.getRate());
		batch.setIsOnline(bill.getIsOnline());
		batch.setIsInner(bill.getIsInner());
		batch.setCustBankNo(searchBean.getCustBankNo());
		batch.setCustBankName(custBankName);
		batch.setResaleStaDt(bill.getResaleStaDt());
		batch.setResaleDueDt(bill.getResaleDueDt());
//		if(RebuyCodeConst.IS_INNER_FALSE.equals(searchBean.getIsInner())){
			mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_save_elec_outer");
//		}else if(RebuyCodeConst.IS_INNER_TRUE.equals(searchBean.getIsInner())){
//			mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_save_elec");
//		}
		IProductService prodService = ServiceFactory.getProductService();
		Product product = prodService.getProductListForApplyBatch(RebuyCodeConst.PRODUCT_ID, bill.getBillType(), bill.getIsInner(),bill.getIsRegress());
		batch.setProdNo(product.getProdNo());
		mv.addObject("billList",billList);
		mv.addObject("batch",batch);
		mv.addObject("ids",ids);
		mv.addObject("prodName",product.getProdName());
		mv.addObject("workday",DateTimeUtil.getWorkdayString());
		mv.addObject("page",page);
		return mv;
	}
	
	/**
	 * 跳转到电票接收页面
	 * @param ids
	 * @param searchBean
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(params="method=toInnerReceivePage")
	public ModelAndView toInnerReceivePage(Page page,String ids,String brchNo) throws Exception{
		ModelAndView mv = null;
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuyApplyInfo batch = new RebuyApplyInfo();
		RebuySearchBean searchBean = new RebuySearchBean();
		if((brchNo!=null)&&(!"".equals(brchNo))){
			Branch branch = ServiceFactory.getBranchService().getBranch(brchNo);
			searchBean.setCustBankNo(branch.getPayBankNo());
		}
		searchBean.setIsInner(RebuyCodeConst.IS_INNER_TRUE);
		searchBean.setMxIds(ids.split(","));
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean, page);
		RebuyBillInfo bill = billList.get(0);
		batch.setBillType(bill.getBillType());
		batch.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		batch.setDiscType(bill.getIsRegress());
		batch.setRebuyDt(bill.getRebuyDt());
		batch.setIsRediscCenter(bill.getIsRediscCenter());
		batch.setRate(bill.getRate());
		batch.setIsOnline(bill.getIsOnline());
		batch.setIsInner(bill.getIsInner());
		batch.setCustBankNo(bill.getCustBankNo());
		batch.setCustBankName(bill.getCustBankName());
		batch.setResaleStaDt(bill.getResaleStaDt());
		batch.setResaleDueDt(bill.getResaleDueDt());
//		if(RebuyCodeConst.IS_INNER_FALSE.equals(searchBean.getIsInner())){
			mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_save_elec_outer");
//		}else if(RebuyCodeConst.IS_INNER_TRUE.equals(searchBean.getIsInner())){
//			mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_save_elec");
//		}
		IProductService prodService = ServiceFactory.getProductService();
		Product product = prodService.getProductListForApplyBatch(RebuyCodeConst.PRODUCT_ID, bill.getBillType(), bill.getIsInner(),bill.getIsRegress());
		batch.setProdNo(product.getProdNo());
		mv.addObject("billList",billList);
		mv.addObject("batch",batch);
		mv.addObject("ids",ids);
		mv.addObject("prodName",product.getProdName());
		mv.addObject("workday",DateTimeUtil.getWorkdayString());
		mv.addObject("page",page);
		mv.addObject("brchNo",brchNo);
		return mv;
	}
	
	/**
	 * 电票接收保存
	 * @param ids
	 * @param batch
	 * @return
	 * @throws BizAppException 
	 * @throws SQLException 
	 */
	@RequestMapping(params="method=saveElecBill")
	public ModelAndView saveElecBill(String ids, RebuyApplyInfo batch, String isEdit, String brchNo) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_submit_elec_outer");
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if(IConstants.NO.equals(isEdit)){
				batch = rebuyService.saveElecBill(ids,batch);
			}else{
				batch = rebuyService.modifyElecBill(ids,batch);
			}
			mv = this.toSubmitPage(new Page(), batch.getRebuyId(), isEdit, brchNo);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("保存失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "保存失败:"+e.getMessage());
		}
		mv.addObject("isEdit",isEdit);
		return mv;
	}
	
	/**
	 * 跳转到提交申请页面
	 * @param page
	 * @param batch
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(params="method=toSubmitPage")
	public ModelAndView toSubmitPage(Page page, String rebuyId, String isEdit, String brchNo) throws Exception{
		ModelAndView mv = null;
		page.activeCommand();
		IProductService prodService = ServiceFactory.getProductService();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuyApplyInfo batch = rebuyService.getApplyInfoById(rebuyId);
		RebuySearchBean searchBean = new RebuySearchBean();
		searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "applySubmitElec", null));
		searchBean.setRebuyId(rebuyId);
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean, page);
		String ids = rebuyService.getBillIdsString(billList);
		batch = rebuyService.sumInfo(batch, ids);
//		if(RebuyCodeConst.IS_INNER_FALSE.equals(batch.getIsInner())){
			mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_submit_elec_outer");
//		}else if(RebuyCodeConst.IS_INNER_TRUE.equals(batch.getIsInner())){
//			mv = new ModelAndView("busi/rebuy/electronic/rebuy_apply_submit_elec");
//		}
		mv.addObject("billList",billList);
		mv.addObject("prodName",prodService.getProductInfoByProdNo(batch.getProdNo()).getProdName());
		mv.addObject("batch",batch);
		mv.addObject("page",page);
		mv.addObject("isEdit",isEdit);
		mv.addObject("workday",DateTimeUtil.getWorkdayString());
		mv.addObject("brchNo",brchNo);
		return mv;
	}
	
	/**
	 * 去电票利息试算页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toCalculateInterestElecPage")
	public ModelAndView toCalculateInterestElecPage(String ids) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("busi/rebuy/electronic/rebuy_calculate_interest_elec");
		mv.addObject("ids", ids);
		mv.addObject("isSameCity",IConstants.YES);//电票默认同城
		return mv;
	}
	
	/**
	 * 电票利息试算
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=calculateInterestElec")
	public ModelAndView calculateInterestElec(String ids, String isSameCity, String delayType, String delayDays) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		try {
			session.beginTransaction();
			rebuyService.calculateInterestElec(ids.split(","),isSameCity,delayType,delayDays);
			session.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("利息试算失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "利息试算失败");
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		
		return mv;
	}
	
	/**
	 * 电票申请提交
	 * @param ids
	 * @param batch
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=applySubmitElec")
	public ModelAndView applySubmitElec(String ids, RebuyApplyInfo batch, String isEdit, String brchNo) throws BizAppException{
		ModelAndView mv = null;
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuySearchBean searchBean = new RebuySearchBean();
		searchBean.setCustBankNo(batch.getCustBankNo());
		searchBean.setCustBankName(batch.getCustBankName());
		searchBean.setIsInner(batch.getIsInner());
		try {
			session.beginTransaction();
			 List<RebuyBillInfo> cancelResults = rebuyService.checkCancelState(ids);
			if(cancelResults==null){
				rebuyService.applySubmitElec(batch,ids);
			}
			if(IConstants.NO.equals(isEdit)){
				if(RebuyCodeConst.IS_INNER_FALSE.equals(batch.getIsInner())){
					mv = this.searchReceivableBill(new Page(), searchBean);
				}else if(RebuyCodeConst.IS_INNER_TRUE.equals(batch.getIsInner())){
					mv = this.searchInnerReceivableBill(new Page(), brchNo);
				}
			}else if(IConstants.YES.equals(isEdit)){
				mv = this.searchEditableApplyBatchElec(new Page());
			}
			session.endTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("转入申请提交失败："+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "转入申请提交失败："+e.getMessage());
		} 
		return mv;
	}
	
	/**
	 * 待撤销申请票据批次列表查询
	 * @param page
	 * @return
	 */
	@RequestMapping(params="method=searchRevokableApplyBatchElec")
	public ModelAndView searchRevokableApplyBatchElec(Page page) throws Exception{
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/revoke_rebuy_apply_batch_elec");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuySearchBean searchBean = new RebuySearchBean();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setDfaultSrchTbAliasName("apply");
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);//电票
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		searchBean.addProperty2TableObj("opers", "bill");
		searchBean.setApplyOperNo(user.getUserId());
		searchBean.addProperty2TableObj("applyOperNo", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "revokeApplyElec", null));
		List<RebuyApplyInfo> batchList = rebuyService.getRebuyApplyListBySearchBean(page, searchBean);
		mv.addObject("batchList",batchList);
		mv.addObject("page",page);
		return mv;
	}

	
	/**
	 * 撤销申请票据清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=searchRevokableApplyDetailElec")
	public ModelAndView searchRevokableApplyDetailElec(Page page,String rebuyId) throws Exception{
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/revoke_rebuy_apply_bill_elec");
		page.activeCommand();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setRebuyId(rebuyId);
		searchBean.setApplyOperNo(user.getUserId());
		searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "revokeApplyElec", null));
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuyApplyInfo batch = rebuyService.getApplyInfoById(rebuyId,searchBean);
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean,page);
		IProductService prodService = ServiceFactory.getProductService();
		Product product = prodService.getProductInfoByProdNo(batch.getProdNo());
		mv.addObject("batch",batch);
		mv.addObject("resultList",billList);
		mv.addObject("prodName",product.getProdName());
		mv.addObject("rebuyId", rebuyId);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 撤销申请
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=revokeApplyElec")
	public ModelAndView revokeApplyElec(String ids) throws Exception{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.revokeApplyElec(ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据撤销申请提交失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销申请提交失败:"+e.getMessage());
		}
		mv = this.searchRevokableApplyBatchElec(new Page());
		return mv;
	}
	
	
	/**
	 * 待修改申请票据批次列表查询
	 * @param page
	 * @return
	 */
	@RequestMapping(params="method=searchEditableApplyBatchElec")
	public ModelAndView searchEditableApplyBatchElec(Page page) throws Exception{
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/modify_rebuy_apply_batch_elec");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuySearchBean searchBean = new RebuySearchBean();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setDfaultSrchTbAliasName("apply");
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);//电票
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		searchBean.addProperty2TableObj("opers", "bill");
		searchBean.setApplyOperNo(user.getUserId());
		searchBean.addProperty2TableObj("applyOperNo", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "searchEditableBillElec", null));
		List<RebuyApplyInfo> batchList = rebuyService.getRebuyApplyListBySearchBean(page, searchBean);
		mv.addObject("batchList",batchList);
		mv.addObject("page",page);
		return mv;
	}
	
	/**
	 * 跳转到修改申请明细页面
	 * @param ids
	 * @param searchBean
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(params="method=toModifyPage")
	public ModelAndView toModifyPage(Page page,String rebuyId) throws Exception{
		ModelAndView mv = null;
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuyApplyInfo batch = rebuyService.getRebuyApplyInfo(rebuyId);
		RebuySearchBean searchBean = new RebuySearchBean();
		searchBean.setDfaultSrchTbAliasName("bill");
		searchBean.setRebuyId(rebuyId);
		String[] opers = StatusUtils.queryStatus("RebuyApplyController", "searchEditableBillElec", null);
		searchBean.setOpers(opers);
		searchBean.addSpecialOpertion("opers", BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean, page);
		String ids="";
		if(billList.size()!=0){
			billList = rebuyService.clearInterest(billList);
			ids = rebuyService.getBillIdsString(billList);
			batch = rebuyService.sumInfo(batch, ids);
		}
		mv = new ModelAndView("busi/rebuy/electronic/modify_rebuy_apply_save_elec");
		IProductService prodService = ServiceFactory.getProductService();
		Product product = prodService.getProductInfoByProdNo(batch.getProdNo());
		boolean isSameStatus = rebuyService.validStatus(rebuyId);
		if(isSameStatus){
			mv.addObject("isReadonly","0");
		}else{
			mv.addObject("isReadonly","1");
		}
		mv.addObject("billList",billList);
		mv.addObject("batch",batch);
		mv.addObject("ids",ids);
		mv.addObject("prodName",product.getProdName());
		mv.addObject("workday",DateTimeUtil.getWorkdayString());
		mv.addObject("page",page);
		return mv;
	}
	
	/**
	 * 跳转到新增电票页面
	 * @throws SQLException 
	 */
	@RequestMapping(params="method=toAddBillPage")
	public ModelAndView toAddBillPage(Page page, String rebuyId) throws Exception{
		ModelAndView mv = new ModelAndView("busi/rebuy/electronic/modify_rebuy_apply_add_bill_elec");
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		RebuyApplyInfo batch = rebuyService.getApplyInfoById(rebuyId);
		page.activeCommand();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		RebuySearchBean searchBean = new RebuySearchBean();
		searchBean.setIsInner(batch.getIsInner());
		searchBean.setCustBankNo(batch.getCustBankNo());
		searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "searchReceivableBill", null));
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setApplyCancelFlag(IConstants.NO);
		searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		searchBean.setIsOnline(batch.getIsOnline());
		searchBean.setRate(Double.toString(batch.getRate()));
		searchBean.setIsRegress(batch.getDiscType());
		searchBean.setRebuyDt(batch.getRebuyDt());
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListBySearchBean(searchBean, page);
		String rebuymxIds = rebuyService.getBillIdsString(rebuyId);
		mv.addObject("rebuymxIds",rebuymxIds);
		mv.addObject("billList",billList);
		mv.addObject("rebuyId",rebuyId);
		return mv;
	}
	
	/**
	 * 在某批次电票下新增电票
	 * @param ids
	 * @param rebuyId
	 * @return
	 */
	@RequestMapping(params="method=addElecBill")
	@ResponseBody
	public AjaxJson addElecBill(String ids, String rebuyId){
		AjaxJson aj = new AjaxJson();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.addElecBill(ids,rebuyId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			aj.setSuccess(false);
			aj.setMsg("新增票据失败："+e.getMessage());
			return aj;
		}
		aj.setSuccess(true);
		return aj;
	}
	
	/**
	 * 电票修改申请移除票据
	 * @param page
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=removeElecBill")
	public ModelAndView removeElecBill(Page page, String ids) throws Exception{
		ModelAndView mv = null;
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			IRebuyService rebuyService = ServiceFactory.getRebuyService();
			List<RebuyBillInfo> billList = rebuyService.getRebuyBillListByIds(ids);
			String rebuyId = billList.get(0).getRebuyId();
			rebuyService.removeElecBill(billList);
			mv = this.toModifyPage(page, rebuyId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("移除票据失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "移除票据失败:"+e.getMessage());
		}
		return mv;
	}
}
