package com.herongtech.console.web.busicontroller.into;

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
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.into.bean.IntoApplyInfo;
import com.herongtech.console.domain.into.bean.IntoBillInfo;
import com.herongtech.console.domain.into.bean.IntoSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.into.IIntoService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.IntoCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 存票申请Controller
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping( { "/intoApplyController" })
public class IntoApplyController {
	/***********************************纸票存票 ***********************************************/
	/**
	 * 客户定位，批次管理页面
	 * @param custNo
	 * @param custName
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = { "method=batchList" })
	public ModelAndView batchList(String custNo,String custName) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/into/into_apply_batch_list");
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
		ModelAndView mv = new ModelAndView("busi/into/into_apply_batch_list");
		IIntoService intoService = ServiceFactory.getIntoService();
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		CustInfo custInfo = null;
		custInfo = custInfoService.getParam(query.getCustNo());
		if(custInfo != null){
			custName = custInfo.getCustName();
		}else{
			custName = "";
		}
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		page.activeCommand();
		try {
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(CommonConst.APPLY_STATUS_NEW);
			mv.addObject("resultList", intoService.getIntoApplyListForApply(page, query));
			mv.addObject("page", page);
			mv.addObject("query", query);
			mv.addObject("custName", custName);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		return mv;
	}
	
	/**
	 * 新增批次
	 * 
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=toAddBatch" })
	public ModelAndView toAddBatch(Page page, IntoApplyInfo intoApplyInfo)
			throws SQLException {
		ModelAndView mv = new ModelAndView();
		IProductService productService = ServiceFactory.getProductService();
		List<Product> prodList = null;
		prodList = productService.getProductListForApplyBatch(IntoCodeConst.INTOBILL_PROD_NO);
		intoApplyInfo.setCustManager(null);
		intoApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
		mv.setViewName("busi/into/into_edit_batch");
		mv.addObject("prodList", prodList);
		mv.addObject("page", page);
		mv.addObject("isedit", "0");
		return mv;
	}

	/**
	 * 修改批次信息
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=toEditBatch" })
	public ModelAndView toEditBatch(Page page, String intoId,
			IntoApplyInfo intoApplyInfo, IntoSearchBean query) {
		ModelAndView mv = new ModelAndView();
		IIntoService intoService = ServiceFactory.getIntoService();
		IProductService productService = ServiceFactory.getProductService();
		List<Product> prodList = null;
		try {
			intoApplyInfo = intoService.getIntoApplyInfo(intoId,query);
			prodList = productService.getProductListForApplyBatch(IntoCodeConst.INTOBILL_PROD_NO);
		} catch (BizAppException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.setViewName("busi/into/into_edit_batch");
		mv.addObject("page", page);
		mv.addObject("intoId", intoId);
		mv.addObject("intoApplyInfo", intoApplyInfo);
		mv.addObject("prodList", prodList);
		mv.addObject("isedit", "1");
		return mv;
	}

	/**
	 * 删除批次
	 */
	@RequestMapping(params = { "method=delBatch" })
	@ResponseBody
	public AjaxJson delBatch(Page page, String intoId) throws BizAppException {
		AjaxJson retJson = new AjaxJson();
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			boolean bool = intoService.delApplyInfoForIntoIds(page, StringUtils.split(intoId));
			retJson.setSuccess(bool);
			if (bool) {
				retJson.setMsg("批次信息删除成功！");
				session.endTransaction();
			} else
				retJson.setMsg("包含票据信息的批次不允许删除！");
			session.rollback();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库删除失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}

		return retJson;
	}

	/**
	 * 保存批次
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=save" })
	public ModelAndView into(IntoApplyInfo intoApplyInfo, String isedit)
			throws BizAppException {
		ModelAndView mv = new ModelAndView();
		IIntoService intoService = ServiceFactory.getIntoService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			if (isedit.equals("1")){
				IntoApplyInfo intApplyInfo = intoService.getIntoApplyInfo(intoApplyInfo.getIntoId(),null);
				intoApplyInfo.setBatchType(intApplyInfo.getBatchType());
				intoApplyInfo.setProdNo(intApplyInfo.getProdNo());
				intoApplyInfo.setIsTc(intApplyInfo.getIsTc());
				intoApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
				intoService.modifyIntoApplyInfo(intoApplyInfo);
			}else {
				intoApplyInfo.setIntoId(sequenceService.getPrimaryKeyValue());
				intoApplyInfo.setBatchNo(sequenceService.getIntoApplyNo(user.getBranchNo()));
				intoApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
				intoService.addIntoApplyInfo(intoApplyInfo);
			}
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tinto_apply_info失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
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
	public ModelAndView goBatchDetail(String intoId, IntoSearchBean query) {
		IntoApplyInfo intoApplyInfo = new IntoApplyInfo();
		IIntoService intoService = ServiceFactory.getIntoService();
		try {
			try {
				intoApplyInfo = intoService.getIntoApplyInfo(intoId, query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/into/into_batch_detail");
		mv.addObject("intoApplyInfo", intoApplyInfo);
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
	public ModelAndView billManage(Page page,IntoSearchBean query,String custName) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/into/into_apply_bill_list");
		page.activeCommand();
		try {
			IIntoService intoService = ServiceFactory.getIntoService();
			query.addSqlPropretyMapping("opers", "operStatus");
			query.setOpers(StatusUtils.queryStatus("IntoApplyController", "billManage", null));

			IntoApplyInfo intApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(intApplyInfo.getProdNo());

			IntoApplyInfo intoApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), query);
			String prodName = prod.getProdName();
			mv.addObject("batch", intoApplyInfo);
			mv.addObject("resultList", intoService.getIntoBillListBySearchBean(page, query));
			mv.addObject("prodName", prodName);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("intoId", query.getIntoId());
		mv.addObject("page", page);
		mv.addObject("query", query);
		mv.addObject("custName", custName);
		return mv;
	}

	/**
	 * 新增票据页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=toAddBill" })
	public ModelAndView toAddBill(String batchType, String intoId, String prodNo, String action)
			throws BizAppException {
		ModelAndView mv = null;
		if ("1".equals(batchType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		IIntoService intoService = ServiceFactory.getIntoService();
		try {
			mv.addObject("batch", intoService.getIntoApplyInfo(intoId, null));
			mv.addObject("batchId", intoId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("action", action);
		mv.addObject("billType", batchType);
		mv.addObject("prodNo", prodNo);
		mv.addObject("isedit", "0");
		return mv;
	}

	/**
	 * 修改票据页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=toEditBill" })
	public ModelAndView toEditBill(String batchType, String intomxId, String prodNo, String action) throws BizAppException {
		ModelAndView mv = null;
		if ("1".equals(batchType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		IIntoService intoService = ServiceFactory.getIntoService();
		IntoBillInfo bill = intoService.getIntoBillInfo(intomxId);
		bill.setDraweeAddr(bill.getRemitterAddr());
		bill.setProtocalNo(bill.getAcceptProtocolNo());
		mv.addObject("bill", bill);
		mv.addObject("batchId", bill.getIntoId());
		mv.addObject("billId", intomxId);
		mv.addObject("billType", batchType);
		mv.addObject("prodNo", prodNo);
		mv.addObject("action", action);
		mv.addObject("isedit", "1");
		return mv;
	}

	/**
	 * 删除票据
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=delBill")
	public ModelAndView delBill(Page page, IntoSearchBean query, String ids,String intoId) throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/into/into_apply_bill_list");
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			intoService.delBill(ids);
			query.addSqlPropretyMapping("opers", "operStatus");
			query.setOpers(StatusUtils.queryStatus("IntoApplyController", "billManage", null));
			mv.addObject("batch", intoService.getIntoApplyInfo(query.getIntoId(), query));
			mv.addObject("resultList", intoService.getIntoBillListBySearchBean(page, query));
			mv.addObject("intoId", intoId);
			mv.addObject("query", query);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据删除失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据删除失败");
		}
		return mv;
	}

	/**
	 * 保存票据
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=saveBill" })
	public ModelAndView intobill(String billType, String isedit,
			String batchId, String billId, IntoBillInfo bill)
			throws BizAppException {
		IIntoService intoService = ServiceFactory.getIntoService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		bill.setIntoId(batchId);
		bill.setOperNo(user.getUserId());
		bill.setBranchNo(user.getBranchNo());
		bill.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
		if ("1".equals(billType)) {
			bill.setBillType(IDict.K_BILL_TYPE.K_BANK_BILL);
			bill.setAcceptor(bill.getAcceptorBankName());
			bill.setAcceptorBankNo(bill.getRemitterBankNo());
		} else
			bill.setBillType(IDict.K_BILL_TYPE.K_CORP_BILL);
		IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			IntoApplyInfo intoApplyInfo = intoService.getIntoApplyInfo(batchId,null);
			bill.setIsTc(intoApplyInfo.getIsTc());
			if (isedit.equals("1")) {
				IntoBillInfo billInfo = intoService.getIntoBillInfo(billId);
				billInfo.setIntoId(bill.getIntoId());
				billInfo.setIssueDt(bill.getIssueDt());
				billInfo.setDueDt(bill.getDueDt());
				billInfo.setRemitter(bill.getRemitter());
				billInfo.setPayee(bill.getPayee());
				billInfo.setRemitterAcct(bill.getRemitterAcct());
				billInfo.setPayeeAcct(bill.getPayeeAcct());
				billInfo.setRemitterBankName(bill.getRemitterBankName());
				billInfo.setPayeeBankName(bill.getPayeeBankName());
				billInfo.setBillNo(bill.getBillNo());
				billInfo.setBillMoney(bill.getBillMoney());
				billInfo.setRemitterBankNo(bill.getRemitterBankNo());
				billInfo.setAcceptor(bill.getAcceptor());
				billInfo.setAcceptorBankName(bill.getAcceptorBankName());
				billInfo.setBillBeforeOwner(bill.getBillBeforeOwner());
				billInfo.setRemitterAddr(bill.getDraweeAddr());
				billInfo.setAcceptProtocolNo(bill.getProtocalNo());
				billInfo.setRemark(bill.getRemark());
				intoService.modifyIntoBillInfo(billInfo);// 修改
			} else {
				intoService.addIntoBillInfo(bill);// 增加
			}
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tinto_bill_info失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 去复制录入票据
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=toCopyBill")
	public ModelAndView toCopyBill(String billType, String intoId,String intomxId,String prodNo, IntoApplyInfo intoApplyInfo)
			throws BizAppException {
		ModelAndView mv = null;
		// 1银票 2商票
		if ("1".equals(billType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		IIntoService intoService = ServiceFactory.getIntoService();
		try {
			mv.addObject("batch", intoService.getIntoApplyInfo(intoId, null));
			mv.addObject("bill", intoService.getIntoBillInfo(intomxId));
			mv.addObject("batchId", intoId);
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("action", "intoApplyController.do?method=saveBill");
		mv.addObject("billType", billType);
		mv.addObject("prodNo", prodNo);
		mv.addObject("intoApplyInfo", intoApplyInfo);
		mv.addObject("isedit", "0");
		mv.addObject("copyadd", "1");// 标识为复制录入
		return mv;
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
	public ModelAndView goBillDetail(String intomxId) {
		IntoBillInfo intoBillInfo = new IntoBillInfo();
		IIntoService intoService = ServiceFactory.getIntoService();
		try {
			intoBillInfo = intoService.getIntoBillInfo(intomxId);
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView(
				"busi/into/into_bill_detail");
		mv.addObject("intoBillInfo", intoBillInfo);
		return mv;
	}

	/**
	 * 申请
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=apply" })
	@ResponseBody
	public String apply(String ids) throws BizAppException {
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB();
		int rs = 0;
		try {
			session.beginTransaction();
			rs = intoService.applySubmit(ids);
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
	@RequestMapping(params = "method=cancelApplyBatchList")
	public ModelAndView cancelApplyBatchList(Page page, IntoSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/into/into_cancel_apply_batch_list");
		IIntoService intoService = ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		query.setApplyOperNo(user.getUserId());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus(
					"IntoAuditController", "seachWaitAuditBill",
					null)[0]);
			mv.addObject("batchList", intoService.getIntoApplyListBySearchBean(page, query));
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
	public ModelAndView cancelApplyBillList(Page page, IntoSearchBean query)
			throws BizAppException {
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView(
				"busi/into/into_cancel_apply_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus(
					"IntoAuditController", "seachWaitAuditBill",
					null));
			mv.addObject("batch", intoService.getIntoApplyInfo(query.getIntoId(), query));
			mv.addObject("resultList", intoService.getIntoBillInfo(page, query));
			IntoApplyInfo intApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(intApplyInfo.getProdNo());
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
	public AjaxJson cancelApply(String ids, String intoId)throws BizAppException {
		AjaxJson aj = new AjaxJson();
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = intoService.cancel("IntoApplyController", "cancelApply", ids);
		//	IntoApplyInfoDao applyDao = new IntoApplyInfoDao();
			IntoApplyInfo apply = intoService.getIntoApplyInfo(intoId, null);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			intoService.modiIntoApplyInfo(apply);
			//rs = applyDao.modifyIntoApplyInfo(apply);
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
		aj.setSuccess(rs > 0);
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
	
	/***********************************电票存票 ***********************************************/
	/**
	 * 客户定位，电票管理页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = { "method=elecBillManage" })
	public ModelAndView elecBillManage(String custNo) throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/into/electronic/into_elec_apply_bill_manage");
		mv.addObject("custNo", custNo);
		return mv;
	}

	/**
	 * 查询待签收电票票据
	 * 
	 * @param page
	 * @param custName
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = { "method=searchElecBill" })
	public ModelAndView searchElecBill(Page page, String custName,
			IntoSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/into/electronic/into_elec_apply_bill_manage");
		IIntoService intoService = ServiceFactory.getIntoService();
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IntoSearchBean query1 = new IntoSearchBean();
		CustInfo custInfo = custInfoService.getParam(query.getCustNo());
		query.setOrgCode(custInfo.getOrgCode());
		query.setCustNo(query.getCustNo());
		query.setBranchNo(user.getBranchNo());
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		query1.setOrgCode(custInfo.getOrgCode());
		query1.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		query1.setBranchNo(user.getBranchNo());
		try {
			String[] status = StatusUtils.queryStatus("IntoApplyController", "receiveBill", null);
			query.setOperStatus(status[0]);
			query1.setOperStatus(status[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		page.activeCommand();
		try {
			mv.addObject("resultList", intoService.getElecIntoBillListForReceive(page, query1));
			mv.addObject("page", page);
			mv.addObject("query", query);
			mv.addObject("custName", custName);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		return mv;
	}
	
	/**
	 * 接收检查勾选票据
	 * @param request
	 * @param response
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecCheckBill")
	@ResponseBody
	public AjaxJson elecCheckBill(HttpServletRequest request,HttpServletResponse response)
	        throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		String ids = request.getParameter("ids");
		Map<String, Object> retMap = new HashMap<String, Object>();
		IIntoService intoService = ServiceFactory.getIntoService();
		try {
			List<IntoBillInfo> list=intoService.getElecReceiveForId(ids);
			String billType=list.get(0).getBillType();
			if(list.size()==1){
				retMap.put("billType", billType);
				retJson.setAttributes(retMap);
				retJson.setSuccess(true);
			}else{
				for(int i = 0;i<list.size();i++){
					if(billType.equals(list.get(i).getBillType())){
						retMap.put("billType", billType);
						retJson.setAttributes(retMap);
						retJson.setSuccess(true);
					}else{
						retJson.setMsg("您所选择的票据类型没有统一");
						retJson.setSuccess(false);
						return retJson;
					}
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retJson;
	}
	
	/**
	 * 电票待签收界面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=elecSignBill" })
	public ModelAndView elecSignBill(Page page, IntoSearchBean query,IntoApplyInfo intoApplyInfo,IntoBillInfo intoBillInfo,String ids)
	        throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/into/electronic/into_elec_sign_bill");
		page.activeCommand();
		IIntoService intoService = ServiceFactory.getIntoService();
		IProductService productService = ServiceFactory.getProductService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		List<Product> prodList = null;
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		intoApplyInfo.setIntoId(sequenceService.getSAVE_APPLY_ID());
		intoApplyInfo.setBatchNo(sequenceService.getIntoApplyNo(user.getBranchNo()));
		intoApplyInfo.setBatchType(query.getBillType());
		intoApplyInfo.setCustManager(null);
		try {
			prodList = productService.getProductListForApplyBatch(IntoCodeConst.DERIVATIVE_PROD_NO);
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			mv.addObject("resultList", intoService.getElecReceiveForId(ids));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("batch", intoApplyInfo);
		mv.addObject("intoId", intoApplyInfo.getIntoId());
		mv.addObject("batchNo", intoApplyInfo.getBatchNo());
		mv.addObject("batchType", query.getBillType());
		mv.addObject("ids", ids);
		mv.addObject("page", page);
		mv.addObject("query", query);
		mv.addObject("prodList", prodList);
		return mv;
	}
	
	/**
	 * 保存批次
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecSaveBatch")
	@ResponseBody
	public AjaxJson elecIntoBatch(Page page,String ids,String isedit,IntoApplyInfo intoApplyInfo) throws BizAppException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		IIntoService intoService = ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			intoApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			intoApplyInfo.setProdNo(IntoCodeConst.DERIVATIVE_PROD_NO);
			for (String id : ids.split(",")) {
				IntoBillInfo bill = intoService.getIntoBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(bill.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+bill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}
			}
			if(isedit.equals("1")){
				intoService.modifyIntoApplyInfo(intoApplyInfo);
			}else{
				intoService.addBatchAndSignBill(ids, intoApplyInfo);
				retMap.put("intoId", intoApplyInfo.getIntoId());
				retJson.setAttributes(retMap);
				retJson.setMsg("保存批次成功");
				retJson.setSuccess(true);
			}
			/*for (String id : ids.split(",")) {
				IntoBillInfo bill = intoService.getIntoBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(bill.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+bill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}
				bill.setOperStatus(StatusUtils.handleStatus("IntoApplyController", "elecReceive", null,bill.getOperStatus()));
				bill.setIntoId(intoApplyInfo.getIntoId());
				bill.setSignRemark(CollateCodeConst.SIGN_YES);
				intoService.modiIntoBillInfo(bill);
			}
			intoApplyInfo.setBatchClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			intoService.addIntoApplyInfo(intoApplyInfo);*/
			retJson.setAttributes(retMap);
			retJson.setMsg("保存批次成功");
			retJson.setSuccess(true);
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				retJson.setMsg("保存批次失败");
				retJson.setSuccess(false);
				return retJson;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg("保存批次失败");
			retJson.setSuccess(false);
			return retJson;
		}
		return retJson;
	}
	
	/**
	 * 电票签收保存批次后
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = { "method=elecAfterSaveBatch" })
	public ModelAndView elecAfterIntoBatch(Page page, IntoSearchBean query,IntoApplyInfo intoApplyInfo,IntoBillInfo intoBillInfo,String ids)
	        throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/into/electronic/into_elec_sign_bill");
		page.activeCommand();
		IIntoService intoService = ServiceFactory.getIntoService();
		IProductService productService = ServiceFactory.getProductService();
		List<Product> prodList = null;
		try {
			prodList = productService.getProductListForApplyBatch(IntoCodeConst.DERIVATIVE_PROD_NO);
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			mv.addObject("resultList", intoService.getElecReceiveForId(ids));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("batch", intoApplyInfo);
		mv.addObject("intoId", intoApplyInfo.getIntoId());
		mv.addObject("batchNo", intoApplyInfo.getBatchNo());
		mv.addObject("batchType", query.getBatchType());
		mv.addObject("ids", ids);
		mv.addObject("page", page);
		mv.addObject("query", query);
		mv.addObject("prodList", prodList);
		mv.addObject("isedit", "1");
		return mv;
	}
	
	/**
	 * 签收提交申请
	 * @param ids
	 * @param intoApplyInfo
	 * @param intoBillInfo
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = { "method=elecApply" })
	@ResponseBody
	public AjaxJson elecApply(String ids,IntoApplyInfo intoApplyInfo,IntoBillInfo intoBillInfo,String isedit) throws BizAppException {
		IIntoService intoService = ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			intoApplyInfo.setProdNo(IntoCodeConst.DERIVATIVE_PROD_NO);
			if("".equals(isedit)||isedit==null){
				intoService.addBatchAndSignBill(ids, intoApplyInfo);
			}else{
				intoApplyInfo = intoService.getIntoApplyInfo(intoBillInfo.getIntoId(),null);
			}
			for (String id : ids.split(",")) {
				IntoBillInfo bill = intoService.getIntoBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(bill.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+bill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}
				bill.setOperStatus(StatusUtils.handleStatus("IntoApplyController", "apply", null,bill.getOperStatus()));
				bill.setIntoId(intoApplyInfo.getIntoId());
				bill.setApplyDate(DateTimeUtil.getWorkdayString());
				bill.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				bill.setSignRemark(CollateCodeConst.SIGN_YES);
				UserLoginfo user = ResourceUtil.getSessionLoginfo();
				bill.setApplyOperNo(user.getUserId());
				intoService.modiIntoBillInfo(bill);
			}
			intoApplyInfo.setBatchClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			intoApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
			intoService.modiIntoApplyInfo(intoApplyInfo);
			
			/*for (String id : ids.split(",")) {
				IntoBillInfo bill = intoService.getIntoBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(bill.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+bill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}
				if(isedit.equals("1")){
					bill.setOperStatus(StatusUtils.handleStatus("IntoApplyController", "apply", null,bill.getOperStatus()));
				}else{
					bill.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "elecApply", null,bill.getOperStatus()));
				}
				bill.setIntoId(intoApplyInfo.getIntoId());
				bill.setApplyDate(DateTimeUtil.getWorkdayString());
				bill.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				bill.setSignRemark(CollateCodeConst.SIGN_YES);
				UserLoginfo user = ResourceUtil.getSessionLoginfo();
				bill.setApplyOperNo(user.getUserId());
				intoService.modiIntoBillInfo(bill);
			}
			if("".equals(isedit)||isedit==null){
				intoApplyInfo.setBatchClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
				intoApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
				intoService.addIntoApplyInfo(intoApplyInfo);
			}*/
			retJson.setSuccess(true);
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				retJson.setMsg("提交失败");
				retJson.setSuccess(false);
				return retJson;
			}
		}catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg("提交失败");
			retJson.setSuccess(false);
			return retJson;
		}
		
		return retJson;

	}
	
	/**
	 * 拒收意见界面
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "method=elecRefuseRemark")
	public ModelAndView elecRefuseRemark(String ids){
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_option");
		mv.addObject("ids", ids);
		return mv;
	}
	
	/**
	 * 电票拒接
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecRefuseBill")
	@ResponseBody
	public AjaxJson elecRefuseBill(String ids,String option) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IIntoService intoService = ServiceFactory.getIntoService();
			try {
				List<IntoBillInfo> list= intoService.getElecReceiveForId(ids);
				session.beginTransaction();
				for(int i = 0 ;i<list.size();i++){
					IntoBillInfo intoBillInfo =list.get(i);
					if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(intoBillInfo.getApplyCancelFlag())){
						retJson.setMsg("对方已经撤销申请,票号为"+intoBillInfo.getBillNo());
						retJson.setSuccess(false);
						return retJson;
					}
				    intoBillInfo.setOperStatus(StatusUtils.handleStatus("IntoApplyController", "elecRefuse", null, intoBillInfo.getOperStatus()));
				    intoBillInfo.setOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
				    intoBillInfo.setSignContent(option);
				    intoService.modiIntoBillInfo(intoBillInfo);
				    RgctBill rgctBill=RcServiceFactory.getRcRegBillService().getRgctBillById(list.get(0).getRgctId());
				    RgctBillHist billHist = rgctBill.getHist();
				    billHist.setSignDt(DateTimeUtil.getWorkday());
				    billHist.setToAcctNo("0");
				    
				    billHist.setToBankNo(ResourceUtil.getSessionLoginfo().getBrchBankNo());
				    Branch branch=ServiceFactory.getBranchService().getBranch(ResourceUtil.getSessionLoginfo().getBrchNo());
				    billHist.setToRole(branch.getPartnerType());
					billHist.setToOrgcode(branch.getOrgCode());
					billHist.setSignerSign(CommUtils.getSignerSign(billHist.getToBankNo()));
					billHist.setIsLock(CommonConst.LOCK_NO);
					rgctBill.setHist(billHist);
				    RcServiceFactory.getRcImpawnService().rejectSignImpawn(rgctBill);
				}
				session.endTransaction();
				retJson.setSuccess(true);
			} catch (SQLException e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					retJson.setSuccess(false);
					return retJson;
				}
			} catch (Exception e) {
				e.printStackTrace();
				retJson.setSuccess(false);
				return retJson;
			}
			
		return retJson;
	}
	
	/**
	 * 修改申请岗批次列表
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecModifyApplyBatchList")
	public ModelAndView elecModifyApplyBatchList(Page page, IntoSearchBean query) throws BizAppException {
		page.activeCommand();
		IIntoService intoService = ServiceFactory.getIntoService();
		try {
			query.setOperStatus(StatusUtils.queryStatus("IntoApplyController", "billManage", null)[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		query.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		String batchNo=query.getBatchNo();
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_modify_batch_list");
		try {
			List<IntoApplyInfo> resultList = intoService.getIntoApplyListBySearchBean(page,query);
			mv.addObject("resultList", resultList);
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("batchNo",batchNo);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 修改申请岗清单列表
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecModifyApplyBillList")
	public ModelAndView elecModifyApplyBillList(Page page, IntoSearchBean query) throws BizAppException{
		page.activeCommand();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IProductService productService = ServiceFactory.getProductService();
		IIntoService intoService = ServiceFactory.getIntoService();
	    try {
	    	query.setOperStatus(StatusUtils.queryStatus("IntoApplyController", "billManage", null)[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_modify_bill_list");
		try {
			IntoApplyInfo intoApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(),query);
			Product prod = productService.getProductInfoByProdNo(intoApplyInfo.getProdNo());
			session.beginTransaction();
			mv.addObject("batch",intoApplyInfo);
			mv.addObject("resultList",intoService.getIntoBillInfo(page,query));
			mv.addObject("prodName", prod.getProdName());
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("电票票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("custNo", query.getCustNo());
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 修改申请岗提交申请
	 * @param query
	 * @param intomxIds
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecModifyApplySubmit")
	@ResponseBody
	public AjaxJson elecModifyApplySubmit(IntoBillInfo intoBill,String intomxIds) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		Map<String, Object> retMap = new HashMap<String, Object>();
		IIntoService intoService = ServiceFactory.getIntoService();
		try {
			session.beginTransaction();
			List<IntoBillInfo> list = intoService.getElecReceiveForId(intomxIds);
			for(int i = 0 ;i<list.size();i++){
				IntoBillInfo intoBillInfo=list.get(i);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(intoBillInfo.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+intoBillInfo.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}else{
				    intoBillInfo.setOperStatus(StatusUtils.handleStatus("IntoApplyController", "apply", null, intoBillInfo.getOperStatus()));
				    intoBillInfo.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
				    intoBillInfo.setApplyDate(DateTimeUtil.getWorkdayString());
				    intoBillInfo.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				    intoService.modiIntoBillInfo(intoBillInfo);
				}
		}
			intoBill.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
			intoBill.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			intoBill.setOperStatus(StatusUtils.queryStatus("IntoApplyController", "apply", null)[0]);
			int i = intoService.totalCount(intoBill);
			if(i<=0){
				IntoApplyInfo intoApplyInfo = intoService.getIntoApplyInfo(intoBill.getIntoId(), null);
				intoApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
				intoService.modiIntoApplyInfo(intoApplyInfo);
			}
			session.endTransaction();
			retMap.put("count", String.valueOf(i));
			retJson.setAttributes(retMap);
			retJson.setSuccess(true);
		} catch (SQLException e) {
			try {
				session.rollback();
				retJson.setMsg("提交失败");
				retJson.setSuccess(false);
				return retJson;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg("提交失败");
			retJson.setSuccess(false);
			return retJson;
		}
		return retJson;
	}
	
	/**
	 * 电票增加页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecAddBill")
	public ModelAndView elecAddBill(Page page,IntoSearchBean query,String intoIds) throws BizAppException{
		page.activeCommand();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/into/electronic/into_elec_modify_add_bill");
		IIntoService intoService = ServiceFactory.getIntoService();
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
	    try {
	    	CustInfo custInfo = custInfoService.getParam(query.getCustNo());
	    	query.setOrgCode(custInfo.getOrgCode());
			query.setOperStatus(StatusUtils.queryStatus("IntoApplyController", "receiveBill", null)[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			session.beginTransaction();
			mv.addObject("resultList",intoService.getElecIntoBillListForReceive(page,query));
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("电票票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		mv.addObject("custNo", query.getCustNo());
		mv.addObject("intoIds", intoIds);
		return mv;
	}
	
	/**
	 * 修改申请岗新增票据
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecModifyAddBill")
	@ResponseBody
	public AjaxJson elecModifyAddBill(Page page,IntoSearchBean query) throws BizAppException {	
		AjaxJson retJson = new AjaxJson();
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			List<IntoBillInfo> list = intoService.getElecReceiveForId(query.getIntomxId());
			for(int i = 0 ;i<list.size();i++){
				IntoBillInfo intoBillInfo=list.get(i);
				intoBillInfo.setOperStatus(StatusUtils.handleStatus("IntoApplyController", "elecReceive", null, list.get(i).getOperStatus()));
				intoBillInfo.setIntoId(query.getIntoId());
				intoBillInfo.setCustNo(query.getCustNo());
				intoBillInfo.setSignRemark(CollateCodeConst.SIGN_YES);
				intoBillInfo.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
				intoBillInfo.setApplyDate(DateTimeUtil.getWorkdayString());
			    intoBillInfo.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			    intoService.modiIntoBillInfo(intoBillInfo);
		}
			session.endTransaction();
			retJson.setSuccess(true);
		}catch (SQLException e) {
			try {
				session.rollback();
				retJson.setSuccess(false);
				return retJson;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setSuccess(false);
			return retJson;
		}
		return retJson;
	}
	
	/**
	 * 修改申请岗移除票据
	 * @param query
	 * @param intomxIds
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecDelBill")
	@ResponseBody
	public AjaxJson elecDelBill(IntoBillInfo query,String intomxIds) throws BizAppException{
		AjaxJson retJson = new AjaxJson();                       
		IDB session = DBFactory.getDB(); // 获取数据库连接
		Map<String, Object> retMap = new HashMap<String, Object>();
		IIntoService intoService = ServiceFactory.getIntoService();
		try {
			session.beginTransaction();
			List<IntoBillInfo> list = intoService.getElecReceiveForId(intomxIds);
			for(int i = 0 ;i<list.size();i++){
				IntoBillInfo intoBillInfo=list.get(i);
				intoBillInfo.setIntoId(" ");
				intoBillInfo.setSignRemark(CollateCodeConst.SIGN_NO);
				intoBillInfo.setOperStatus(StatusUtils.handleStatus("IntoApplyController", "elecSignToReceive", null, intoBillInfo.getOperStatus()));
				intoService.modiIntoBillInfo(intoBillInfo);
		    }
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("IntoApplyController", "elecSignToReceive", null)[0]);
			int i = intoService.totalCount(query);
			if(i<=0){
				IntoApplyInfo intoApplyInfo = intoService.getIntoApplyInfo(query.getIntoId(), null);
				intoApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
				intoService.modiIntoApplyInfo(intoApplyInfo);
				retMap.put("count", String.valueOf(i));
				retJson.setAttributes(retMap);
			}
			session.endTransaction();
			retMap.put("count", String.valueOf(i));
			retJson.setAttributes(retMap);
			retJson.setSuccess(true);
		} catch (SQLException e) {
			try {
				session.rollback();
				retJson.setSuccess(false);
				return retJson;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setSuccess(false);
			return retJson;
		}
		return retJson;
		
	}
	
	/**
	 * 电票待撤销申请的批次列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=elecCancelApplyBatchList")
	public ModelAndView elecCancelApplyBatchList(Page page, IntoSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/into/electronic/into_elec_cancel_apply_batch_list");
		IIntoService intoService = ServiceFactory.getIntoService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setApplyOperNo(user.getUserId());
			query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			query.setOperStatus(StatusUtils.queryStatus("IntoApplyController", "cancelApply",null)[0]);
			mv.addObject("batchList", intoService.getIntoApplyListBySearchBean(page, query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("待撤销申请批次列表");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销申请批次列表");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 电票待撤销申请的清单列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=elecCancelApplyBillList")
	public ModelAndView elecCancelApplyBillList(Page page, IntoSearchBean query)
			throws BizAppException {
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView(
				"busi/into/electronic/into_elec_cancel_apply_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus("IntoApplyController", "cancelApply",null));
			mv.addObject("batch", intoService.getIntoApplyInfo(query.getIntoId(), query));
			mv.addObject("resultList", intoService.getIntoBillInfo(page, query));
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
			CommonLog.getCommonLogCache().errorMessage("待撤销申请的清单列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销申请的清单列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 电票撤销申请
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecCancelApply")
	@ResponseBody
	public AjaxJson elecCancelApply(String ids, String intoId)
			throws BizAppException {
		AjaxJson aj = new AjaxJson();
		IIntoService intoService = ServiceFactory.getIntoService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = intoService.cancel(
					"IntoApplyController", "cancelApply", ids);
		//	IntoApplyInfoDao applyDao = new IntoApplyInfoDao();
			IntoApplyInfo apply = intoService.getIntoApplyInfo(
					intoId, null);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			intoService.modiIntoApplyInfo(apply);
		//	rs = applyDao.modifyIntoApplyInfo(apply);
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
		aj.setSuccess(rs > 0);
		return aj;
	}
	

}
