package com.herongtech.console.web.busicontroller.out;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.into.bean.IntoApplyInfo;
import com.herongtech.console.domain.into.bean.IntoBillInfo;
import com.herongtech.console.domain.into.bean.IntoSearchBean;
import com.herongtech.console.domain.out.bean.OutApplyInfo;
import com.herongtech.console.domain.out.bean.OutBillInfo;
import com.herongtech.console.domain.out.bean.OutSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.into.IIntoService;
import com.herongtech.console.service.busiservice.out.IOutService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.IntoCodeConst;
import com.herongtech.console.web.busicontroller.common.OutCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcGetBillService;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 取票申请Controller
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping( { "/outApplyController" })
public class OutApplyController {
	/** 纸票取票 **/
	/**
	 * 客户定位，批次管理页面
	 * @param custNo
	 * @param custName
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = { "method=batchList" })
	public ModelAndView batchList(String custNo,String custName) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/out/out_apply_batch_list");
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		CustInfo custInfo = null;
		custInfo = custInfoService.getParam(custNo);
		if(custInfo != null){
			custName = custInfo.getCustName();
		}else{
			custName = "";
		}
		mv.addObject("custNo", custNo);
		mv.addObject("custName", custName);
		return mv;
	}
	
	/**
	 * 批次查询
	 * @param page
	 * @param custName
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = { "method=searchBatch" })
	public ModelAndView searchBatch(Page page, String custName,IntoSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/out/out_apply_batch_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("position", "bill");
			query.setPosition(IntoCodeConst.SAVE_ACCOUNT_OVER);
			//query.addSqlPropretyMapping("position", "position");
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(IntoCodeConst.ACCOUNT_STATUS);
			mv.addObject("resultList", intoService.getIntoApplyListBySearchBean(page,query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		mv.addObject("custName", custName);
		return mv;
	}
	
	/**
	 * 批次详情
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=goBatchDetail" })
	public ModelAndView goBatchDetail(String outId, OutSearchBean query) {
		OutApplyInfo outApplyInfo = new OutApplyInfo();
		IOutService outService = ServiceFactory.getOutService();
		try {
			try {
				outApplyInfo = outService.getOutApplyInfo(outId, query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/out/out_batch_detail");
		mv.addObject("outApplyInfo", outApplyInfo);
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * 票据管理页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=billManage" })
	public ModelAndView billManage(Page page,IntoSearchBean query,String custName,String intoId) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/out/out_apply_bill_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		IProductService productservice = ServiceFactory.getProductService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(IntoCodeConst.ACCOUNT_STATUS);
			query.addProperty2TableObj("position", "bill");
			query.setPosition(IntoCodeConst.SAVE_ACCOUNT_OVER);
			IntoApplyInfo intoApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), query);
			OutApplyInfo outApplyInfo = new OutApplyInfo();
//			outApplyInfo.setOutId(sequenceService.getPrimaryKeyValue());
			outApplyInfo.setBatchNo(sequenceService.getOutApplyNo(user.getBranchNo()));
			outApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			outApplyInfo.setBatchType(intoApplyInfo.getBatchType());
			outApplyInfo.setCustNo(intoApplyInfo.getCustNo());
			outApplyInfo.setCustName(intoApplyInfo.getCustName());
			outApplyInfo.setTotalNum(intoApplyInfo.getTotalNum());
			outApplyInfo.setTotalMoney(intoApplyInfo.getTotalMoney());
			outApplyInfo.setIsTc(intoApplyInfo.getIsTc());
			outApplyInfo.setCustManager(null);
			if(intoApplyInfo.getProdNo().equals(IntoCodeConst.BASIC_PROD_NO)){
				Product prod = productservice.getProductInfoByProdNo(OutCodeConst.BASIC_PROD_NO);
				mv.addObject("prodName", prod.getProdName());
				outApplyInfo.setProdNo(OutCodeConst.BASIC_PROD_NO);
			}else if(intoApplyInfo.getProdNo().equals(IntoCodeConst.DERIVATIVE_PROD_NO)){
				Product prod = productservice.getProductInfoByProdNo(OutCodeConst.DERIVATIVE_PROD_NO);
				mv.addObject("prodName", prod.getProdName());
				outApplyInfo.setProdNo(OutCodeConst.DERIVATIVE_PROD_NO);
			}else{
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "产品编号查询失败");
			}
			mv.addObject("batch",outApplyInfo);
			mv.addObject("resultList",intoService.getIntoBillListBySearchBean(page,query));
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
	 * 提交申请
	 * @param query
	 * @param outmxIds
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=apply")
	@ResponseBody
	public String apply(String ids,OutApplyInfo outApplyInfo) throws BizAppException {
		IOutService outService = ServiceFactory.getOutService();
		IDB session = DBFactory.getDB();
		int rs = 0;
		try {
			session.beginTransaction();
			rs = outService.applySubmit(ids,outApplyInfo);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据申请提交失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据申请提交失败");
		}
		return rs > 0 ? "yes" : "no";

	}
	
	/**
	 * 票据详情
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=goBillDetail" })
	public ModelAndView goBillDetail(String outmxId) {
		OutBillInfo outBillInfo = new OutBillInfo();
		IOutService outService = ServiceFactory.getOutService();
		try {
			outBillInfo = outService.getOutBillInfo(outmxId);
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView(
				"busi/out/out_bill_detail");
		mv.addObject("outBillInfo", outBillInfo);
		return mv;
	}
	
	/**
	 * 待撤销申请的批次列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=cancelApplyBatchList")
	public ModelAndView cancelApplyBatchList(Page page, OutSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/out/out_cancel_apply_batch_list");
		IOutService outService = ServiceFactory.getOutService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		try {
			page.activeCommand();
			query.setBranchNo(user.getBranchNo());
			query.addProperty2TableObj("operNo", "bill");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setApplyOperNo(user.getUserId());
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus(
					"OutAuditController", "seachWaitAuditBill",
					null)[0]);
			mv.addObject("batchList", outService.getOutApplyListBySearchBean(page, query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("待撤销申请批次列表");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销申请批次列表");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 待撤销申请的清单列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=cancelApplyBillList")
	public ModelAndView cancelApplyBillList(Page page, OutSearchBean query)
			throws BizAppException {
		IOutService outService = ServiceFactory.getOutService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		ModelAndView mv = new ModelAndView(
				"busi/out/out_cancel_apply_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus(
					"OutAuditController", "seachWaitAuditBill",
					null));
			query.setBranchNo(user.getBranchNo());
			query.setApplyOperNo(user.getUserId());
			mv.addObject("batch", outService.getOutApplyInfo(query.getOutId(), query));
			mv.addObject("resultList", outService.getOutBillInfo(page, query));
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
			CommonLog.getCommonLogCache().errorMessage("待撤销申请的清单列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销申请的清单列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 撤销申请
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelApply")
	@ResponseBody
	public AjaxJson cancelApply(String ids, String outId)throws BizAppException {
		AjaxJson aj = new AjaxJson();
		IOutService outService = ServiceFactory.getOutService();
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			for (String id : ids.split(",")) {
				OutBillInfo outbill = outService.getOutBillInfo(id);
				IntoBillInfo intoBill = intoService.getIntoBillInfo(outbill.getIntobillRelaId());
				intoBill.setPosition(IntoCodeConst.SAVE_ACCOUNT_OVER);
				intoService.modiIntoBillInfo(intoBill);
				outbill.setOperStatus(OutCodeConst.DELETE_STATUS);
				outService.modifyOutBillInfo(outbill);
			}
			OutApplyInfo apply = outService.getOutApplyInfo(outId, null);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
			outService.modiOutApplyInfo(apply);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据撤销申请失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销申请失败");
		}
		aj.setSuccess(true);
		return aj;
	}
	
	/**
	 * 根据客户号查询客户信息
	 * @param custNo
	 * @return
	 */
	@RequestMapping(params = "method=custInfo")
	@ResponseBody
	public AjaxJson checkuser(String custNo) {
		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();
//		String custNo = req.getParameter("custNo");
		// 获取客户信息和账号处理类
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		ISignProdService signProdService = ServiceFactory.getSignProdService();
		// 取用户基本信息
		CustInfo custInfo;
		SignProd signProd;
		try {
			custInfo = custInfoService.getParam(custNo);
			signProd = signProdService.getSignProdInfoByCust(IConstants.BILLPOOL_SINGPROD, custNo);
		} catch (BizAppException e) {
			retJson.setMsg("查询数据库信息失败");
			retJson.setSuccess(false);
			return retJson;
		}
		if (custInfo == null) {
			retJson.setMsg("根据客户号无法找到对应的客户信息");
			retJson.setSuccess(false);
			return retJson;
		}
		if ("".equals(custInfo.getCustType())) {
			retJson.setMsg("根据客户号无法找到对应的客户信息");
			retJson.setSuccess(false);
			return retJson;
		}
		if (signProd == null) {
			retJson.setMsg("该客户未进行产品签约");
			retJson.setSuccess(false);
			return retJson;
		}
		String custType = custInfo.getCustType();
		if (custType.length() > 3 && custType.substring(1, 3).equals("04")) {
			retJson.setMsg("不能为保证金账号");
			retJson.setSuccess(false);
			return retJson;
		}
		retMap.put("custNo", custNo);
		retMap.put("custName", custInfo.getCustName());

		retJson.setAttributes(retMap);
		retJson.setSuccess(true);

		return retJson;
	}
	
	/** 电票取票 **/
	/**
	 * 客户定位，批次管理页面
	 * @param custNo
	 * @param custName
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = { "method=elecBatchList" })
	public ModelAndView elecBatchList(String custNo,String custName) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/out/electronic/out_elec_apply_batch_list");
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		CustInfo custInfo = null;
		custInfo = custInfoService.getParam(custNo);
		if(custInfo != null){
			custName = custInfo.getCustName();
		}else{
			custName = "";
		}
		mv.addObject("custNo", custNo);
		mv.addObject("custName", custName);
		return mv;
	}
	
	/**
	 * 批次查询
	 * @param page
	 * @param custName
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = { "method=elecSearchBatch" })
	public ModelAndView elecSearchBatch(Page page, String custName,IntoSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/out/electronic/out_elec_apply_batch_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("position", "bill");
			query.setPosition(IntoCodeConst.SAVE_ACCOUNT_OVER);
			query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			query.setOperStatus(IntoCodeConst.ACCOUNT_STATUS);
			mv.addObject("resultList", intoService.getIntoApplyListBySearchBean(page,query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		mv.addObject("custName", custName);
		return mv;
	}
	
	/**
	 * 票据管理页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=elecBillManage" })
	public ModelAndView elecBillManage(Page page,IntoSearchBean query,String custName,String intoId) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/out/electronic/out_elec_apply_bill_list");
		IIntoService intoService=ServiceFactory.getIntoService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		try {
			page.activeCommand();
			session.beginTransaction();
			query.addProperty2TableObj("position", "bill");
			query.setPosition(IntoCodeConst.SAVE_ACCOUNT_OVER);
			query.setOperStatus(IntoCodeConst.ACCOUNT_STATUS);
			IntoApplyInfo intoApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), query);
			OutApplyInfo outApplyInfo = new OutApplyInfo();
			outApplyInfo.setBatchNo(sequenceService.getOutApplyNo(user.getBranchNo()));
			outApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			outApplyInfo.setBatchType(intoApplyInfo.getBatchType());
			outApplyInfo.setCustNo(intoApplyInfo.getCustNo());
			outApplyInfo.setCustName(intoApplyInfo.getCustName());
			outApplyInfo.setTotalNum(intoApplyInfo.getTotalNum());
			outApplyInfo.setTotalMoney(intoApplyInfo.getTotalMoney());
			outApplyInfo.setIsTc(intoApplyInfo.getIsTc());
			outApplyInfo.setCustManager(null);
			mv.addObject("batch",outApplyInfo);
			mv.addObject("resultList",intoService.getIntoBillListBySearchBean(page,query));
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(OutCodeConst.DERIVATIVE_PROD_NO);
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
	 * 提交申请
	 * @param query
	 * @param outmxIds
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecApply")
	@ResponseBody
	public String elecApply(String ids,OutApplyInfo outApplyInfo) throws BizAppException {
		IOutService outService = ServiceFactory.getOutService();
		IDB session = DBFactory.getDB();
		int rs = 0;
		try {
			session.beginTransaction();
			outApplyInfo.setProdNo(OutCodeConst.DERIVATIVE_PROD_NO);
			rs = outService.applySubmit(ids,outApplyInfo);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据申请提交失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据申请提交失败");
		}
		return rs > 0 ? "yes" : "no";

	}
	
	/**
	 * 待撤销申请的批次列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=elecCancelApplyBatchList")
	public ModelAndView elecCancelApplyBatchList(Page page, OutSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/out/electronic/out_elec_cancel_apply_batch_list");
		IOutService outService = ServiceFactory.getOutService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "bill");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setApplyOperNo(user.getUserId());
			query.setBranchNo(user.getBranchNo());
			query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			query.setOperStatus(StatusUtils.queryStatus(
					"OutAuditController", "seachWaitAuditBill",
					null)[0]);
			mv.addObject("batchList", outService.getOutApplyListBySearchBean(page, query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("待撤销申请批次列表");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销申请批次列表");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 待撤销申请的清单列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=elecCancelApplyBillList")
	public ModelAndView elecCancelApplyBillList(Page page, OutSearchBean query)
			throws BizAppException {
		IOutService outService = ServiceFactory.getOutService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView(
				"busi/out/electronic/out_elec_cancel_apply_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus(
					"OutAuditController", "seachWaitAuditBill",
					null));
			mv.addObject("batch", outService.getOutApplyInfo(query.getOutId(), query));
			mv.addObject("resultList", outService.getOutBillInfo(page, query));
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
			CommonLog.getCommonLogCache().errorMessage("待撤销申请的清单列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销申请的清单列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 撤销申请
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecCancelApply")
	@ResponseBody
	public AjaxJson elecCancelApply(String ids, String outId)throws BizAppException {
		AjaxJson aj = new AjaxJson();
		IOutService outService = ServiceFactory.getOutService();
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			for (String id : ids.split(",")) {
				OutBillInfo outbill = outService.getOutBillInfo(id);
				IntoBillInfo intoBill = intoService.getIntoBillInfo(outbill.getIntobillRelaId());
				intoBill.setPosition(IntoCodeConst.SAVE_ACCOUNT_OVER);
				intoService.modiIntoBillInfo(intoBill);
				outbill.setOperStatus(OutCodeConst.DELETE_STATUS);
				outService.modifyOutBillInfo(outbill);
			}
			OutApplyInfo apply = outService.getOutApplyInfo(outId, null);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
			outService.modiOutApplyInfo(apply);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据撤销申请失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销申请失败");
		}
		aj.setSuccess(true);
		return aj;
	}
	
}
