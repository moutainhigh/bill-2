package com.herongtech.console.web.busicontroller.repurchasedcollateralization;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.get.bean.GetApplyInfo;
import com.herongtech.console.domain.get.bean.GetBillInfo;
import com.herongtech.console.domain.get.bean.GetSearchBean;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.collateralization.ICollateralizationService;
import com.herongtech.console.service.busiservice.repurchasedcollateralization.IRepurCollateService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.RepurCollateCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 解质押申请Controller
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping( { "/repurCollateApplyController" })
public class RepurCollateApplyController {
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
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/electronic/repurcollate_elec_apply_batch_list");
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
	public ModelAndView elecSearchBatch(Page page, String custName,SaveSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/electronic/repurcollate_elec_apply_batch_list");
		ICollateralizationService collateService=ServiceFactory.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("position", "bill");
			query.setPosition(CollateCodeConst.COLLZTN_ACCOUNT_OVER);
			query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			query.setOperStatus(CollateCodeConst.ACCOUNT_STATUS);
			mv.addObject("resultList", collateService.getSaveApplyListBySearchBean(page,query));
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
	public ModelAndView goBatchDetail(String getId, GetSearchBean query) {
		GetApplyInfo getApplyInfo = new GetApplyInfo();
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		try {
			try {
				getApplyInfo = repurCollateService.getGetApplyInfo(getId, query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/electronic/repurcollate_batch_detail");
		mv.addObject("getApplyInfo", getApplyInfo);
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
	@RequestMapping(params = { "method=elecBillManage" })
	public ModelAndView elecBillManage(Page page,SaveSearchBean query,String custName,String saveId) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/electronic/repurcollate_elec_apply_bill_list");
		ICollateralizationService collateService=ServiceFactory.getCollateralizationService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		try {
			page.activeCommand();
			session.beginTransaction();
			query.addProperty2TableObj("position", "bill");
			query.setPosition(CollateCodeConst.COLLZTN_ACCOUNT_OVER);
			query.setOperStatus(CollateCodeConst.ACCOUNT_STATUS);
			SaveApplyInfo saveApplyInfo = collateService.getSaveApplyInfo(query.getSaveId(), query);
			GetApplyInfo getApplyInfo = new GetApplyInfo();
			getApplyInfo.setBatchNo(sequenceService.getGetApplyNo(user.getBranchNo()));
			getApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			getApplyInfo.setBatchType(saveApplyInfo.getBatchType());
			getApplyInfo.setCustNo(saveApplyInfo.getCustNo());
			getApplyInfo.setCustName(saveApplyInfo.getCustName());
			getApplyInfo.setTotalNum(saveApplyInfo.getTotalNum());
			getApplyInfo.setTotalMoney(saveApplyInfo.getTotalMoney());
			getApplyInfo.setIsTc(saveApplyInfo.getIsTc());
			getApplyInfo.setCustManager(null);
			mv.addObject("batch",getApplyInfo);
			mv.addObject("resultList",collateService.getSaveBillListBySearchBean(page,query));
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(RepurCollateCodeConst.REPURCOLLATE_PROD_NO);
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
	 * @param getmxIds
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecApply")
	@ResponseBody
	public String elecApply(String ids,GetApplyInfo getApplyInfo) throws BizAppException {
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		IDB session = DBFactory.getDB();
		int rs = 0;
		try {
			session.beginTransaction();
			getApplyInfo.setProdNo(RepurCollateCodeConst.REPURCOLLATE_PROD_NO);
			rs = repurCollateService.applySubmit(ids,getApplyInfo);
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
	public ModelAndView elecCancelApplyBatchList(Page page, GetSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/repurchasedcollateralization/electronic/repurcollate_elec_cancel_apply_batch_list");
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
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
					"RepurCollateStorageController", "seachWaitStorageBill",
					null)[0]);
			mv.addObject("batchList", repurCollateService.getGetApplyListBySearchBean(page, query));
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
	public ModelAndView elecCancelApplyBillList(Page page, GetSearchBean query)
			throws BizAppException {
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/electronic/repurcollate_elec_cancel_apply_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus(
					"RepurCollateStorageController", "seachWaitStorageBill",null));
			mv.addObject("batch", repurCollateService.getGetApplyInfo(query.getGetId(), query));
			mv.addObject("resultList", repurCollateService.getGetBillInfo(page, query));
			GetApplyInfo geApplyInfo = repurCollateService.getGetApplyInfo(query.getGetId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(geApplyInfo.getProdNo());
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
	public AjaxJson elecCancelApply(String ids, String getId)throws BizAppException {
		AjaxJson aj = new AjaxJson();
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		ICollateralizationService collateService=ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			List<GetBillInfo> getBillList = repurCollateService.getGetBillListByMxids(ids.split(","));
			String rgctIds = "";
			for (GetBillInfo getbill : getBillList) {
				SaveBillInfo saveBill = collateService.getSaveBillInfo(getbill.getSavebillRelaId());
				rgctIds += saveBill.getRgctId()+",";
				saveBill.setPosition(CollateCodeConst.COLLZTN_ACCOUNT_OVER);
				collateService.modiSaveBillInfo(saveBill);
				getbill.setOperStatus(RepurCollateCodeConst.DELETE_STATUS);
				repurCollateService.modifyGetBillInfo(getbill);
			}
			IRcBaseService rcBaseService = RcServiceFactory.getRcBaseService();
			List<RgctBill> rgctBillList = rcBaseService.getRgctBillList(rgctIds.substring(0, rgctIds.length()-1));
			for(RgctBill rgctBill:rgctBillList){
				RgctBillHist hist = rgctBill.getHist();
				hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
				rcBaseService.updateRgctBillHist(hist);
			}
			GetApplyInfo apply = repurCollateService.getGetApplyInfo(getId, null);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
			repurCollateService.modiGetApplyInfo(apply);
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
		// 获取客户信息和账号处理类
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		// 取用户基本信息
		CustInfo custInfo;
		try {
			custInfo = custInfoService.getParam(custNo);
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
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/entity/repurcollate_apply_batch_list");
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
	public ModelAndView searchBatch(Page page, String custName,SaveSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/entity/repurcollate_apply_batch_list");
		ICollateralizationService collateService=ServiceFactory.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("position", "bill");
			query.setPosition(CollateCodeConst.COLLZTN_ACCOUNT_OVER);
			//query.addSqlPropretyMapping("position", "position");
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(CollateCodeConst.ACCOUNT_STATUS);
			mv.addObject("resultList", collateService.getSaveApplyListBySearchBean(page,query));
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
	@RequestMapping(params = { "method=billManage" })
	public ModelAndView billManage(Page page,SaveSearchBean query,String custName,String saveId) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/repurchasedcollateralization/entity/repurcollate_apply_bill_list");
		ICollateralizationService collateService=ServiceFactory.getCollateralizationService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		IProductService productservice = ServiceFactory.getProductService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(CollateCodeConst.ACCOUNT_STATUS);
			query.addProperty2TableObj("position", "bill");
			query.setPosition(CollateCodeConst.COLLZTN_ACCOUNT_OVER);
			SaveApplyInfo saveApplyInfo = collateService.getSaveApplyInfo(query.getSaveId(), query);
			GetApplyInfo getApplyInfo = new GetApplyInfo();
			getApplyInfo.setBatchNo(sequenceService.getGetApplyNo(user.getBranchNo()));
			getApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			getApplyInfo.setBatchType(saveApplyInfo.getBatchType());
			getApplyInfo.setCustNo(saveApplyInfo.getCustNo());
			getApplyInfo.setCustName(saveApplyInfo.getCustName());
			getApplyInfo.setTotalNum(saveApplyInfo.getTotalNum());
			getApplyInfo.setTotalMoney(saveApplyInfo.getTotalMoney());
			getApplyInfo.setIsTc(saveApplyInfo.getIsTc());
			getApplyInfo.setCustManager(null);
			Product prod = productservice.getProductInfoByProdNo(RepurCollateCodeConst.REPURCOLLATE_PROD_NO);
			mv.addObject("prodName", prod.getProdName());
			getApplyInfo.setProdNo(RepurCollateCodeConst.REPURCOLLATE_PROD_NO);
			mv.addObject("batch",getApplyInfo);
			mv.addObject("resultList",collateService.getSaveBillListBySearchBean(page,query));
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
	 * @param getmxIds
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=apply")
	@ResponseBody
	public String apply(String ids,GetApplyInfo getApplyInfo) throws BizAppException {
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		IDB session = DBFactory.getDB();
		int rs = 0;
		try {
			session.beginTransaction();
			rs = repurCollateService.applySubmit(ids,getApplyInfo);
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
	public ModelAndView goBillDetail(String getmxId) {
		GetBillInfo getBillInfo = new GetBillInfo();
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		try {
			getBillInfo = repurCollateService.getGetBillInfo(getmxId);
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView(
				"busi/repurchasedcollateralization/entity/repurcollate_bill_detail");
		mv.addObject("getBillInfo", getBillInfo);
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
	public ModelAndView cancelApplyBatchList(Page page, GetSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/repurchasedcollateralization/entity/repurcollate_cancel_apply_batch_list");
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "bill");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setApplyOperNo(user.getUserId());
			query.setBranchNo(user.getBranchNo());
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus(
					"RepurCollateStorageController", "seachWaitStorageBill",
					null)[0]);
			mv.addObject("batchList", repurCollateService.getGetApplyListBySearchBean(page, query));
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
	public ModelAndView cancelApplyBillList(Page page, GetSearchBean query)
			throws BizAppException {
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView(
				"busi/repurchasedcollateralization/entity/repurcollate_cancel_apply_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus(
					"RepurCollateStorageController", "seachWaitStorageBill",null));
			mv.addObject("batch", repurCollateService.getGetApplyInfo(query.getGetId(), query));
			mv.addObject("resultList", repurCollateService.getGetBillInfo(page, query));
			GetApplyInfo getApplyInfo = repurCollateService.getGetApplyInfo(query.getGetId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(getApplyInfo.getProdNo());
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
	public AjaxJson cancelApply(String ids, String getId)throws BizAppException {
		AjaxJson aj = new AjaxJson();
		IRepurCollateService repurCollateService = ServiceFactory.getRepurCollateService();
		ICollateralizationService collateService=ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			List<GetBillInfo> getBillList = repurCollateService.getGetBillListByMxids(ids.split(","));
			String rgctIds = "";
			for (GetBillInfo getbill : getBillList) {
				SaveBillInfo saveBill = collateService.getSaveBillInfo(getbill.getSavebillRelaId());
				rgctIds += saveBill.getRgctId()+",";
				saveBill.setPosition(CollateCodeConst.COLLZTN_ACCOUNT_OVER);
				collateService.modiSaveBillInfo(saveBill);
				getbill.setOperStatus(RepurCollateCodeConst.DELETE_STATUS);
				repurCollateService.modifyGetBillInfo(getbill);
			}
			IRcBaseService rcBaseService = RcServiceFactory.getRcBaseService();
			List<RgctBill> rgctBillList = rcBaseService.getRgctBillList(rgctIds.substring(0, rgctIds.length()-1));
			for(RgctBill rgctBill:rgctBillList){
				RgctBillHist hist = rgctBill.getHist();
				hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
				rcBaseService.updateRgctBillHist(hist);
			}
			GetApplyInfo apply = repurCollateService.getGetApplyInfo(getId, null);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
			repurCollateService.modiGetApplyInfo(apply);
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
