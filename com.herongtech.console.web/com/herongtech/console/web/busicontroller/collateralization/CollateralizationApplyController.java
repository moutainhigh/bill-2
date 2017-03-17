package com.herongtech.console.web.busicontroller.collateralization;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.FacDealResult;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.collateralization.ICollateralizationService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 质押申请Controller
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping( { "/collateralizationApplyController" })
public class CollateralizationApplyController extends BaseController {
	/** 电票质押 **/
	/**
	 * 客户定位，电票管理页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecBillManage")
	public ModelAndView elecBillManage(String custNo) throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/electronic/collate_elec_apply_bill_manage");
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
	@RequestMapping(params = "method=searchElecBill")
	public ModelAndView searchElecBill(Page page, String custName,
			SaveSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/electronic/collate_elec_apply_bill_manage");
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		SaveSearchBean query1 = new SaveSearchBean();
		CustInfo custInfo = custInfoService.getParam(query.getCustNo());
		query.setOrgCode(custInfo.getOrgCode());
		query.setCustNo(query.getCustNo());
		query.setBranchNo(user.getBranchNo());
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		query1.setOrgCode(custInfo.getOrgCode());
		query1.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		query1.setBranchNo(user.getBranchNo());
		try {
			String[] status = StatusUtils.queryStatus("CollateralizationApplyController", "receiveBill", null);
			query.setOperStatus(status[0]);
			query1.setOperStatus(status[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		page.activeCommand();
		try {
			mv.addObject("resultList", collateralizationService.getElecSaveBillListForReceive(page, query1));
			mv.addObject("page", page);
			mv.addObject("query", query);
			mv.addObject("custName", custInfo.getCustName());
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
	@RequestMapping(params = "method=elecCheckBill")
	@ResponseBody
	public AjaxJson elecCheckBill(HttpServletRequest request,HttpServletResponse response)
	        throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		String ids = request.getParameter("ids");
		Map<String, Object> retMap = new HashMap<String, Object>();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		try {
			List<SaveBillInfo> list=collateralizationService.getElecReceiveForId(ids);
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
						retJson.setMsg("您所选择的票据类型没有统一!");
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
	@RequestMapping(params =  "method=elecSignBill")
	public ModelAndView elecSignBill(Page page, SaveSearchBean query,SaveApplyInfo saveApplyInfo,SaveBillInfo saveBillInfo,String ids)
	        throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/electronic/collate_elec_sign_bill");
		page.activeCommand();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		IProductService productService = ServiceFactory.getProductService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		List<Product> prodList = null;
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		saveApplyInfo.setSaveId(sequenceService.getSAVE_APPLY_ID());
		saveApplyInfo.setBatchNo(sequenceService.getSaveApplyNo(user.getBranchNo()));
		saveApplyInfo.setBatchType(query.getBillType());
		saveApplyInfo.setCustManager(null);
		try {
			prodList = productService.getProductListForApplyBatch(CollateCodeConst.PARENT_PROD_NO);
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			mv.addObject("resultList", collateralizationService.getElecReceiveForId(ids));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("batch", saveApplyInfo);
		mv.addObject("saveId", saveApplyInfo.getSaveId());
		mv.addObject("batchNo", saveApplyInfo.getBatchNo());
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
	public AjaxJson elecSaveBatch(Page page,String ids,String isedit,SaveApplyInfo saveApplyInfo) throws BizAppException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			saveApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			for (String id : ids.split(",")) {
				SaveBillInfo bill = collateralizationService.getSaveBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(bill.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+bill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}
			}
			if(isedit.equals("1")){
				collateralizationService.modifySaveApplyInfo(saveApplyInfo);
			}else{
				collateralizationService.addBatchAndSignBill(ids, saveApplyInfo);
				retMap.put("saveId", saveApplyInfo.getSaveId());
				retJson.setAttributes(retMap);
				retJson.setMsg("保存批次成功!");
				retJson.setSuccess(true);
			}
			/*for (String id : ids.split(",")) {
				SaveBillInfo bill = collateralizationService.getSaveBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(bill.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+bill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}
				bill.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "elecReceive", null,bill.getOperStatus()));
				bill.setSaveId(saveApplyInfo.getSaveId());
				bill.setSignRemark(CollateCodeConst.SIGN_YES);
				collateralizationService.modiSaveBillInfo(bill);
			}
			saveApplyInfo.setBatchClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			collateralizationService.addSaveApplyInfo(saveApplyInfo);*/
			retJson.setAttributes(retMap);
			retJson.setMsg("保存批次成功!");
			retJson.setSuccess(true);
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				retJson.setMsg("保存批次失败!");
				retJson.setSuccess(false);
				return retJson;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg("保存批次失败!");
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
	@RequestMapping(params =  "method=elecAfterSaveBatch" )
	public ModelAndView elecAfterSaveBatch(Page page, SaveSearchBean query,SaveApplyInfo saveApplyInfo,SaveBillInfo saveBillInfo,String ids)
	        throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/electronic/collate_elec_sign_bill");
		page.activeCommand();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		IProductService productService = ServiceFactory.getProductService();
		List<Product> prodList = null;
		try {
			prodList = productService.getProductListForApplyBatch(CollateCodeConst.PARENT_PROD_NO);
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			mv.addObject("resultList", collateralizationService.getElecReceiveForId(ids));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("batch", saveApplyInfo);
		mv.addObject("saveId", saveApplyInfo.getSaveId());
		mv.addObject("batchNo", saveApplyInfo.getBatchNo());
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
	 * @param saveApplyInfo
	 * @param saveBillInfo
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecApply")
	@ResponseBody
	public AjaxJson elecApply(String ids,SaveApplyInfo saveApplyInfo,SaveBillInfo saveBillInfo,String isedit) throws BizAppException {
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if("".equals(isedit)||isedit==null){
				collateralizationService.addBatchAndSignBill(ids, saveApplyInfo);
			}else{
				saveApplyInfo = collateralizationService.getSaveApplyInfo(saveBillInfo.getSaveId(),null);
			}
			List<SaveBillInfo> bills = new ArrayList<SaveBillInfo>();
			UserLoginfo user = ResourceUtil.getSessionLoginfo();
			for (String id : ids.split(",")) {
				SaveBillInfo bill = collateralizationService.getSaveBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(bill.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+bill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}
				bills.add(bill);
			}
			saveApplyInfo.setBatchClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			saveApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
			collateralizationService.modiSaveApplyInfo(saveApplyInfo);
			//额度扣减
			FacDealResult result = ServiceFactory.getCollateFacService().dealFac(saveApplyInfo, bills,user, CommonConst.FAC_OPER_OCCUPY);
			result.checkFacResult(CommonConst.FAC_OPER_OCCUPY);
			//更新清单信息
			for(SaveBillInfo bill:bills){
				bill.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "apply", null,bill.getOperStatus()));
				bill.setSaveId(saveApplyInfo.getSaveId());
				bill.setApplyDate(DateTimeUtil.getWorkdayString());
				bill.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				bill.setSignRemark(CollateCodeConst.SIGN_YES);
				bill.setApplyOperNo(user.getUserId());
				collateralizationService.modiSaveBillInfo(bill);
			}
			/*for (String id : ids.split(",")) {
				SaveBillInfo bill = collateralizationService.getSaveBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(bill.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+bill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}
				if(isedit.equals("1")){
					bill.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "apply", null,bill.getOperStatus()));
				}else{
					bill.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "elecApply", null,bill.getOperStatus()));
				}
				bill.setSaveId(saveApplyInfo.getSaveId());
				bill.setApplyDate(DateTimeUtil.getWorkdayString());
				bill.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				bill.setSignRemark(CollateCodeConst.SIGN_YES);
				UserLoginfo user = ResourceUtil.getSessionLoginfo();
				bill.setApplyOperNo(user.getUserId());
				collateralizationService.modiSaveBillInfo(bill);
			}
			if("".equals(isedit)||isedit==null){
				saveApplyInfo.setBatchClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
				saveApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
				collateralizationService.addSaveApplyInfo(saveApplyInfo);
			}*/
			retJson.setSuccess(true);
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				retJson.setMsg("提交申请失败!");
				retJson.setSuccess(false);
				return retJson;
			}
		}catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg("提交申请失败!");
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
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_option");
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
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
			try {
				List<SaveBillInfo> list= collateralizationService.getElecReceiveForId(ids);
				session.beginTransaction();
				for(int i = 0 ;i<list.size();i++){
					SaveBillInfo saveBillInfo =list.get(i);
					if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(saveBillInfo.getApplyCancelFlag())){
						retJson.setMsg("对方已经撤销申请,票号为"+saveBillInfo.getBillNo());
						retJson.setSuccess(false);
						return retJson;
					}
				    saveBillInfo.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "elecRefuse", null, saveBillInfo.getOperStatus()));
				    saveBillInfo.setOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
				    saveBillInfo.setSignContent(option);
				    collateralizationService.modiSaveBillInfo(saveBillInfo);
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
	public ModelAndView elecModifyApplyBatchList(Page page, SaveSearchBean query) throws BizAppException {
		page.activeCommand();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		try {
			query.setOperStatus(StatusUtils.queryStatus("CollateralizationApplyController", "billManage", null)[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		query.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		String batchNo=query.getBatchNo();
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_modify_batch_list");
		try {
			List<SaveApplyInfo> resultList = collateralizationService.getSaveApplyListBySearchBean(page,query);
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
	public ModelAndView elecModifyApplyBillList(Page page, SaveSearchBean query ,String custNo1) throws BizAppException{
		page.activeCommand();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IProductService productService = ServiceFactory.getProductService();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
	    try {
	    	query.setOperStatus(StatusUtils.queryStatus("CollateralizationApplyController", "billManage", null)[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_modify_bill_list");
		try {
			SaveApplyInfo saveApplyInfo = collateralizationService.getSaveApplyInfo(query.getSaveId(),query);
			Product prod = productService.getProductInfoByProdNo(saveApplyInfo.getProdNo());
			session.beginTransaction();
			mv.addObject("batch",saveApplyInfo);
			mv.addObject("resultList",collateralizationService.getSaveBillInfo(page,query));
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
		mv.addObject("custNo", custNo1);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 修改申请岗提交申请
	 * @param query
	 * @param savemxIds
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecModifyApplySubmit")
	@ResponseBody
	public AjaxJson elecModifyApplySubmit(SaveBillInfo saveBill,String savemxIds) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		Map<String, Object> retMap = new HashMap<String, Object>();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		try {
			session.beginTransaction();
			List<SaveBillInfo> list = collateralizationService.getElecReceiveForId(savemxIds);
			for(int i = 0 ;i<list.size();i++){
				SaveBillInfo saveBillInfo=list.get(i);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(saveBillInfo.getApplyCancelFlag())){
					retJson.setMsg("对方已经撤销申请,票号为"+saveBillInfo.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}
			}
			saveBill.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
			saveBill.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			saveBill.setOperStatus(StatusUtils.queryStatus("CollateralizationApplyController", "apply", null)[0]);
			int i = collateralizationService.totalCount(saveBill);
			SaveApplyInfo saveApplyInfo = collateralizationService.getSaveApplyInfo(saveBill.getSaveId(), null);
			if(i<=0){
				saveApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
				collateralizationService.modiSaveApplyInfo(saveApplyInfo);
			}
			//额度扣减
			FacDealResult result = ServiceFactory.getCollateFacService().dealFac(saveApplyInfo, list, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_OCCUPY);
			result.checkFacResult(CommonConst.FAC_OPER_OCCUPY);
			//更新清单信息
			for(SaveBillInfo saveBillInfo:list){
				saveBillInfo.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "apply", null, saveBillInfo.getOperStatus()));
			    saveBillInfo.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
			    saveBillInfo.setApplyDate(DateTimeUtil.getWorkdayString());
			    saveBillInfo.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			    collateralizationService.modiSaveBillInfo(saveBillInfo);
			}
			session.endTransaction();
			retMap.put("count", String.valueOf(i));
			retJson.setAttributes(retMap);
			retJson.setSuccess(true);
		} catch (SQLException e) {
			try {
				session.rollback();
				retJson.setMsg("提交申请失败!");
				retJson.setSuccess(false);
				return retJson;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg("提交申请失败!");
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
	public ModelAndView elecAddBill(Page page,SaveSearchBean query,String saveIds,String custNo1) throws BizAppException{
		page.activeCommand();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/collateralization/electronic/collate_elec_modify_add_bill");
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		CustInfo custInfo = custInfoService.getParam(custNo1);
		SaveSearchBean query1 = new SaveSearchBean();
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		query1.setOrgCode(custInfo.getOrgCode());
		query1.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
	    try {
			query1.setOperStatus(StatusUtils.queryStatus("CollateralizationApplyController", "receiveBill", null)[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			session.beginTransaction();
			mv.addObject("resultList",collateralizationService.getElecSaveBillListForReceive(page,query1));
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
		mv.addObject("custNo", custNo1);
		mv.addObject("saveIds", saveIds);
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
	public AjaxJson elecModifyAddBill(Page page,SaveSearchBean query) throws BizAppException {	
		AjaxJson retJson = new AjaxJson();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			List<SaveBillInfo> list = collateralizationService.getElecReceiveForId(query.getSavemxId());
			for(int i = 0 ;i<list.size();i++){
				SaveBillInfo saveBillInfo=list.get(i);
				saveBillInfo.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "elecReceive", null, list.get(i).getOperStatus()));
				saveBillInfo.setSaveId(query.getSaveId());
				saveBillInfo.setCustNo(query.getCustNo());
				saveBillInfo.setSignRemark(CollateCodeConst.SIGN_YES);
				saveBillInfo.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
				saveBillInfo.setApplyDate(DateTimeUtil.getWorkdayString());
			    saveBillInfo.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			    collateralizationService.modiSaveBillInfo(saveBillInfo);
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
	 * @param savemxIds
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecDelBill")
	@ResponseBody
	public AjaxJson elecDelBill(SaveBillInfo query,String savemxIds) throws BizAppException{
		AjaxJson retJson = new AjaxJson();                       
		IDB session = DBFactory.getDB(); // 获取数据库连接
		Map<String, Object> retMap = new HashMap<String, Object>();
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		try {
			session.beginTransaction();
			List<SaveBillInfo> list = collateralizationService.getElecReceiveForId(savemxIds);
			for(int i = 0 ;i<list.size();i++){
				SaveBillInfo saveBillInfo=list.get(i);
				saveBillInfo.setSaveId(" ");
				saveBillInfo.setSignRemark(CollateCodeConst.SIGN_NO);
				saveBillInfo.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "elecSignToReceive", null, saveBillInfo.getOperStatus()));
				collateralizationService.modiSaveBillInfo(saveBillInfo);
		    }
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("CollateralizationApplyController", "elecSignToReceive", null)[0]);
			int i = collateralizationService.totalCount(query);
			if(i<=0){
				SaveApplyInfo saveApplyInfo = collateralizationService.getSaveApplyInfo(query.getSaveId(), null);
				saveApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
				collateralizationService.modiSaveApplyInfo(saveApplyInfo);
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
	public ModelAndView elecCancelApplyBatchList(Page page, SaveSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/electronic/collate_elec_cancel_apply_batch_list");
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setOperNo(user.getUserId());
			query.setApplyOperNo(user.getUserId());
			query.setBatchClass(CommonConst.BILL_CLASS_ELEC);
			query.setOperStatus(StatusUtils.queryStatus("CollateralizationAuditController", "seachWaitAuditBill",null)[0]);
			mv.addObject("batchList", collateralizationService.getSaveApplyListBySearchBean(page, query));
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
	public ModelAndView elecCancelApplyBillList(Page page, SaveSearchBean query)
			throws BizAppException {
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/electronic/collate_elec_cancel_apply_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus("CollateralizationAuditController", "seachWaitAuditBill",null));
			mv.addObject("batch", collateralizationService.getSaveApplyInfo(query.getSaveId(), query));
			mv.addObject("resultList", collateralizationService.getSaveBillInfo(page, query));
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
	public AjaxJson elecCancelApply(String ids, String saveId)
			throws BizAppException {
		AjaxJson aj = new AjaxJson();
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			//释放额度
			List<SaveBillInfo> bills = new ArrayList<SaveBillInfo>();
			String idArr[] = ids.split(",");
			for (String savemxId : idArr) {
				SaveBillInfo bill = collateralizationService.getSaveBillInfo(savemxId);
				bills.add(bill);
			}
			SaveApplyInfo apply = collateralizationService.getSaveApplyInfo(saveId, null);
			FacDealResult result = ServiceFactory.getCollateFacService().dealFac(apply, bills, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_RELEASE);
			result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
			
			//更新清单信息
			rs = collateralizationService.cancel(
					"CollateralizationApplyController", "cancelApply", ids);
		//	SaveApplyInfoDao applyDao = new SaveApplyInfoDao();
			
			apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			collateralizationService.modiSaveApplyInfo(apply);
		//	rs = applyDao.modifySaveApplyInfo(apply);
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
	
	
	/** 纸票质押 **/
	/**
	 * 客户定位，批次管理页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params =  "method=batchManage" )
	public ModelAndView batchManage(String custNo,String custName) throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/entity/collate_apply_batch_list");
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
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params =  "method=searchBatch" )
	public ModelAndView searchBatch(Page page, String custName,SaveSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/collateralization/entity/collate_apply_batch_list");
		ICollateralizationService collateralizationService = ServiceFactory.getCollateralizationService();
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
			mv.addObject("resultList", collateralizationService
					.getSaveApplyListForApply(page, query));
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
	@RequestMapping(params =  "method=toAddBatch")
	public ModelAndView toAddBatch(Page page, SaveApplyInfo saveApplyInfo)
			throws SQLException {
		ModelAndView mv = new ModelAndView();
		IProductService productService = ServiceFactory.getProductService();
		List<Product> prodList = null;
		prodList = productService.getProductListForApplyBatch(CollateCodeConst.PARENT_PROD_NO);
	/*	ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		CustInfo custInfo = null;
		try {
			custInfo = custInfoService.getParam(saveApplyInfo.getCustNo());
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		saveApplyInfo.setCustName(custInfo.getCustName());*/
		saveApplyInfo.setCustManager(null);
		saveApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
		mv.setViewName("busi/collateralization/entity/collate_apply_edit_batch");
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
	@RequestMapping(params = "method=toEditBatch")
	public ModelAndView toEditBatch(Page page, String saveId,
			SaveApplyInfo saveApplyInfo, SaveSearchBean query) {
		ModelAndView mv = new ModelAndView();
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		IProductService productService = ServiceFactory.getProductService();
		List<Product> prodList = null;
		try {
			saveApplyInfo = collateralizationService.getSaveApplyInfo(saveId,
					query);
			prodList = productService
					.getProductListForApplyBatch(CollateCodeConst.PARENT_PROD_NO);
		} catch (BizAppException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.setViewName("busi/collateralization/entity/collate_apply_edit_batch");
		mv.addObject("page", page);
		mv.addObject("saveId", saveId);
		mv.addObject("saveApplyInfo", saveApplyInfo);
		mv.addObject("prodList", prodList);
		mv.addObject("isedit", "1");
		return mv;
	}

	/**
	 * 删除批次
	 */
	@RequestMapping(params = "method=delBatch")
	@ResponseBody
	public AjaxJson delBatch(Page page, String saveId) throws BizAppException {
		AjaxJson retJson = new AjaxJson();
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			boolean bool = collateralizationService.delApplyInfoForSaveIds(
					page, StringUtils.split(saveId));
			retJson.setSuccess(bool);
			if (bool) {
				retJson.setMsg("批次信息删除成功!");
				session.endTransaction();
			} else
				retJson.setMsg("包含票据信息的批次不允许删除!");
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
	@RequestMapping(params = "method=save")
	public ModelAndView save(SaveApplyInfo saveApplyInfo, String isedit)
			throws BizAppException {
		ModelAndView mv = new ModelAndView();
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			if (isedit.equals("1")){
				SaveApplyInfo savApplyInfo = collateralizationService.getSaveApplyInfo(saveApplyInfo.getSaveId(),null);
				saveApplyInfo.setBatchType(savApplyInfo.getBatchType());
				saveApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
				collateralizationService.modifySaveApplyInfo(saveApplyInfo);
			}else {
				saveApplyInfo.setSaveId(sequenceService.getSAVE_APPLY_ID());
			    saveApplyInfo.setBatchNo(sequenceService.getSaveApplyNo(user.getBranchNo()));
			    saveApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
				collateralizationService.addSaveApplyInfo(saveApplyInfo);
			}
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tsave_apply_info失败");
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
	@RequestMapping(params = "method=goBatchDetail")
	public ModelAndView goBatchDetail(String saveId, SaveSearchBean query) {
		SaveApplyInfo saveApplyInfo = new SaveApplyInfo();
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		try {
			try {
				saveApplyInfo = collateralizationService.getSaveApplyInfo(
						saveId, query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/entity/collate_batch_detail");
		mv.addObject("saveApplyInfo", saveApplyInfo);
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
	@RequestMapping(params = "method=billManage")
	public ModelAndView billManage(Page page, SaveSearchBean query,
			String custName) throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/entity/collate_apply_bill_list");
		page.activeCommand();
		try {
			ICollateralizationService collateralizationService = ServiceFactory
					.getCollateralizationService();
			query.addSqlPropretyMapping("opers", "operStatus");
			query.setOpers(StatusUtils.queryStatus(
					"CollateralizationApplyController", "billManage", null));

			SaveApplyInfo savApplyInfo = collateralizationService
					.getSaveApplyInfo(query.getSaveId(), query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(savApplyInfo.getProdNo());

			SaveApplyInfo saveApplyInfo = collateralizationService
					.getSaveApplyInfo(query.getSaveId(), query);
			String prodName = prod.getProdName();
			mv.addObject("batch", saveApplyInfo);
			mv.addObject("resultList", collateralizationService
					.getSaveBillListBySearchBean(page, query));
			mv.addObject("prodName", prodName);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("saveId", query.getSaveId());
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
	@RequestMapping(params = "method=toAddBill")
	public ModelAndView toAddBill(String batchType, String saveId, String action)
			throws BizAppException {
		ModelAndView mv = null;
		if ("1".equals(batchType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		try {
			mv.addObject("batch", collateralizationService.getSaveApplyInfo(
					saveId, null));
			mv.addObject("batchId", saveId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("action", action);
		mv.addObject("billType", batchType);
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
	@RequestMapping(params = "method=toEditBill")
	public ModelAndView toEditBill(String batchType, String savemxId,
			String action) throws BizAppException {
		ModelAndView mv = null;
		if ("1".equals(batchType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		SaveBillInfo bill = collateralizationService.getSaveBillInfo(savemxId);
		bill.setDraweeAddr(bill.getRemitterAddr());
		bill.setProtocalNo(bill.getAcceptProtocolNo());
		mv.addObject("bill", bill);
		mv.addObject("batchId", bill.getSaveId());
		mv.addObject("billId", savemxId);
		mv.addObject("billType", batchType);
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
	public ModelAndView delBill(Page page, SaveSearchBean query, String ids,
			String saveId) throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/entity/collate_apply_bill_list");
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			collateralizationService.delBill(ids);
			query.addSqlPropretyMapping("opers", "operStatus");
			query.setOpers(StatusUtils.queryStatus(
					"CollateralizationApplyController", "billManage", null));
			mv.addObject("batch", collateralizationService.getSaveApplyInfo(
					query.getSaveId(), query));
			mv.addObject("resultList", collateralizationService
					.getSaveBillListBySearchBean(page, query));
			mv.addObject("saveId", saveId);
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
	@RequestMapping(params = "method=saveBill")
	public ModelAndView savebill(String billType, String isedit,
			String batchId, String billId, SaveBillInfo bill)
			throws BizAppException {
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		bill.setSaveId(batchId);
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
			if (isedit.equals("1")) {
				SaveBillInfo billInfo = collateralizationService
						.getSaveBillInfo(billId);
				billInfo.setSaveId(bill.getSaveId());
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
				collateralizationService.modifySaveBillInfo(billInfo);// 修改
			} else {
				collateralizationService.addSaveBillInfo(bill);// 增加
			}
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tsave_bill_info失败");
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
	public ModelAndView toCopyBill(String billType, String saveId,
			String savemxId, SaveApplyInfo saveApplyInfo)
			throws BizAppException {
		ModelAndView mv = null;
		// 1银票 2商票
		if ("1".equals(billType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		try {
			mv.addObject("batch", collateralizationService.getSaveApplyInfo(
					saveId, null));
			mv.addObject("bill", collateralizationService
					.getSaveBillInfo(savemxId));
			mv.addObject("batchId", saveId);
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("action", "collateralizationApplyController.do?method=saveBill");
		mv.addObject("billType", billType);
		mv.addObject("saveApplyInfo", saveApplyInfo);
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
	@RequestMapping(params = "method=goBillDetail")
	public ModelAndView goBillDetail(String savemxId) {
		SaveBillInfo saveBillInfo = new SaveBillInfo();
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		try {
			saveBillInfo = collateralizationService.getSaveBillInfo(savemxId);
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/entity/collate_bill_detail");
		mv.addObject("saveBillInfo", saveBillInfo);
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
	@RequestMapping(params = "method=apply")
	@ResponseBody
	public String apply(String ids) throws BizAppException {
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		IDB session = DBFactory.getDB();
		int rs = 0;
		try {
			session.beginTransaction();
			rs = collateralizationService.applySubmit(ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
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
	public ModelAndView cancelApplyBatchList(Page page, SaveSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/entity/collate_cancel_apply_batch_list");
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setApplyOperNo(user.getUserId());
			query.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus(
					"CollateralizationAuditController", "seachWaitAuditBill",
					null)[0]);
			mv.addObject("batchList", collateralizationService
					.getSaveApplyListBySearchBean(page, query));
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
	public ModelAndView cancelApplyBillList(Page page, SaveSearchBean query)
			throws BizAppException {
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView(
				"busi/collateralization/entity/collate_cancel_apply_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus(
					"CollateralizationAuditController", "seachWaitAuditBill",
					null));
			mv.addObject("batch", collateralizationService.getSaveApplyInfo(
					query.getSaveId(), query));
			mv.addObject("resultList", collateralizationService
					.getSaveBillInfo(page, query));
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
	public AjaxJson cancelApply(String ids, String saveId)
			throws BizAppException {
		AjaxJson aj = new AjaxJson();
		ICollateralizationService collateralizationService = ServiceFactory
				.getCollateralizationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			//释放额度
			String idArr[] = ids.split(",");
			List<SaveBillInfo> bills = collateralizationService.getSaveBillListByMxids(idArr);
			SaveApplyInfo apply = collateralizationService.getSaveApplyInfo(saveId, null);
			FacDealResult result = ServiceFactory.getCollateFacService().dealFac(apply, bills, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_RELEASE);
			result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
			//更新清单信息
			rs = collateralizationService.cancel(
					"CollateralizationApplyController", "cancelApply", ids);
			
			apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			collateralizationService.modiSaveApplyInfo(apply);
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
	 * 
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=custInfo")
	@ResponseBody
	public AjaxJson checkuser(HttpServletRequest req,
			HttpServletResponse response) {

		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();

		String custNo = req.getParameter("custNo");
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
}