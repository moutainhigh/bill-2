package com.herongtech.console.web.busicontroller.sale;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.bean.SaleSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.sale.ISaleService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.SaleCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.rc.service.rcservice.IRcSaleService;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 转卖申请Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/saleApplyController")
public class SaleApplyController extends BaseController {
	/***纸票   begin********************************************************************************/
	/***转卖申请   begin********************************************************************************/
	/**
	 * 功能描述：批次管理页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=batch")
	public ModelAndView batchManage(Page page,SaleSearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/entity/apply/sale_apply_batch_list");
		ISaleService saleService = ServiceFactory.getISaleService();
		page.activeCommand();
		if(searchBean == null){
			searchBean = new SaleSearchBean();
		}
		try {
			searchBean.setDfaultSrchTbAliasName("apply");
			searchBean.setApplyStatus(CommonConst.APPLY_STATUS_NEW);//票据状态
			searchBean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);//票据品种--纸票
			mv.addObject("batchList", saleService.getSaleApplyListBySearchBeanForApply(page, searchBean));
		}catch (Exception e){
			e.printStackTrace();
			throw new BizAppException("获取批次列表失败："+e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("totalResult", page.getTotalResult());
		mv.addObject("query", searchBean);
		return mv;
	}
	
	/*****撤销申请 begin**********************************************************************************************************************/
	/**
	 * 获取待撤销申请的批次列表
	 * @param page
	 * @param searchBean
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=cancelApplyBatchList")
	public ModelAndView cancelApplyBatch(Page page,SaleSearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/entity/apply/sale_cancel_apply_batch_list");
		page.activeCommand();
		ISaleService saleService = ServiceFactory.getISaleService();
		UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
		if(searchBean==null){
			searchBean = new SaleSearchBean();
		}
		try {
			searchBean.setDfaultSrchTbAliasName("apply");
			searchBean.setOperStatus(StatusUtils.queryStatus("SaleApplyController", "cancelApplyList", null)[0]);
			searchBean.setApplyOperNo(userLogonInfo.getUserNo());
			searchBean.addProperty2TableObj("applyOperNo", "bill");
			searchBean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
			mv.addObject("batchList", saleService.getSaleApplyListForCondition(page,searchBean));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		
		mv.addObject("page", page);
		return mv;
	}
	
	/***纸票   end********************************************************************************/
	
	/***电票   begin********************************************************************************/
	/***转卖申请  begin******************************************************************************************************/
	/**
	 * 功能描述：批次管理页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecBatch")
	public ModelAndView elecBatch(Page page,SaleSearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/electronic/apply/sale_apply_batch_list");
		ISaleService saleService = ServiceFactory.getISaleService();
		page.activeCommand();
		if(searchBean == null){
			searchBean = new SaleSearchBean();
		}
		try {
			searchBean.setDfaultSrchTbAliasName("apply");
			searchBean.setApplyStatus(CommonConst.APPLY_STATUS_NEW);//票据状态
			searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);//票据品种--电票
			mv.addObject("batchList", saleService.getSaleApplyListBySearchBeanForApply(page, searchBean));
		}catch (Exception e){
			e.printStackTrace();
			throw new BizAppException("获取批次列表失败："+e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("totalResult", page.getTotalResult());
		mv.addObject("query", searchBean);
		return mv;
	}
	
	/***撤销申请  begin******************************************************************************************************/
	/**
	 * 获取待撤销申请的批次列表
	 * @param page
	 * @param searchBean
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=cancelElecBatch")
	public ModelAndView cancelElecBatch(Page page,SaleSearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/electronic/apply/sale_cancel_apply_batch_list");
		page.activeCommand();
		ISaleService saleService = ServiceFactory.getISaleService();
		UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
		if(searchBean==null){
			searchBean = new SaleSearchBean();
		}
		try {
			searchBean.setDfaultSrchTbAliasName("apply");
			searchBean.setOperStatus(StatusUtils.queryStatus("SaleApplyController", "cancelApplyList", null)[0]);
			searchBean.setApplyOperNo(userLogonInfo.getUserNo());
			searchBean.addProperty2TableObj("applyOperNo", "bill");
			searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			mv.addObject("batchList", saleService.getSaleApplyListForCondition(page,searchBean));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		
		mv.addObject("page", page);
		return mv;
	}
	/***电票   end********************************************************************************/
	
	/***公共   begin********************************************************************************/
	/**
	 * 去新增批次页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toAddBatch")
	public ModelAndView toAddBatch(String billClass) {
		ModelAndView mv = new ModelAndView();
		IProductService prodService = ServiceFactory.getProductService();
		try {
			//查询转卖产品信息
			List<Product> prodList = prodService.getProductListForApplyBatch(SaleCodeConst.PARENT_PROD_NO);
			mv.addObject("prodList", prodList);
			//获取登陆用户所在机构
			mv.addObject("userBranchNo", ResourceUtil.getSessionLoginfo().getBranchNo());
			String dateTime = DateTimeUtil.getWorkdayString();//当前营业日
			String nextDateTime=DateTimeUtil.getNextWorkdayString();//下一个营业日
			mv.addObject("dateTime",dateTime);
			mv.addObject("nextDateTime",nextDateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(billClass)){
			mv.setViewName("busi/sale/electronic/apply/sale_apply_batch_edit");
		}else{
			mv.setViewName("busi/sale/entity/apply/sale_apply_batch_edit");
		}
		mv.addObject("isedit", "0");
		return mv;
	}
	
	
	/**
	 * 去修改批次页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toEditBatch")
	public ModelAndView toEditBatch(String saleId,String billClass) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		ISaleService saleService = ServiceFactory.getISaleService();
		IProductService productService = ServiceFactory.getProductService();
		SaleApplyInfo saleApplyInfo = null;
		List<Product> prodList = null;
    	try{
    		SaleSearchBean bean = new SaleSearchBean();
    		bean.setSaleId(saleId);
    		saleApplyInfo = saleService.getSaleApplyInfo(bean);
    		prodList = productService.getProductListForPidAndIsInner(SaleCodeConst.PARENT_PROD_NO,saleApplyInfo.getIsInner());
    		String dateTime = DateTimeUtil.getWorkdayString();
    		String nextDateTime=DateTimeUtil.getNextWorkdayString();
    		mv.addObject("dateTime",dateTime);
    		mv.addObject("nextDateTime",nextDateTime);
		} catch (SQLException e){
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败");
			throw new BizAppException( "查询数据库失败,"+e.getMessage());
		}
    	if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(billClass)){
    		mv.setViewName("busi/sale/electronic/apply/sale_apply_batch_edit");
    	}else{
    		mv.setViewName("busi/sale/entity/apply/sale_apply_batch_edit");
    	}
		mv.addObject("saleApplyInfo", saleApplyInfo);
		mv.addObject("isedit", "1");
		mv.addObject("prodList", prodList);
		return mv;
	}
	/**
	 * “票据管理”页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=billManage")
	public ModelAndView billManage(Page page,SaleSearchBean searchBean,String flag) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(searchBean.getBillClass())){
			mv.setViewName("busi/sale/electronic/apply/sale_apply_bill_list");
		}else{
			mv.setViewName("busi/sale/entity/apply/sale_apply_bill_list");
		}
		page.activeCommand();
		try {
			ISaleService saleService = ServiceFactory.getISaleService();
			searchBean.setDfaultSrchTbAliasName("bill");
			searchBean.addSqlPropretyMapping("statusArray", "operStatus");
			searchBean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
			searchBean.setStatusArray(StatusUtils.queryStatus("SaleApplyController", "billManege", null));
			mv.addObject("batch",saleService.getSaleApplyInfo(searchBean));//获取批次信息
			List<SaleBillInfo> saleBillList = saleService.getSaleBillListBySearchBeanForPage(page,searchBean);
			//将已经算过利息的状态维提交的票清单的利息设置为空
			if("1".equals(flag)){
				for(SaleBillInfo saleBill : saleBillList){
					saleBill.setInterest(0.0);
					saleBill.setReceiveMoney(0.0);
					saleBill.setDelayDays(0L);
					saleBill.setDelayType(null);
					saleBill.setGaleDate(null);
					saleBill.setIsSameCity(null);
					saleBill.setInterestDays(0L);
					saleBill.setRate(0);
					saleBill.setRateType(null);
					saleService.modifySaleBill(saleBill);
				}
			}
			mv.addObject("resultList",saleBillList);//获取批次下清单列表信息
		}catch(Exception e){
			CommonLog.getCommonLogCache().errorMessage("批次清单列表查询失败");
			throw new BizAppException("批次清单列表查询失败，"+e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("query", searchBean);
		return mv;
	}
	/**
	 * 编辑批次信息：新增或者修改
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(SaleApplyInfo saleApplyInfo,String isedit) throws BizAppException {
		ModelAndView mv = new ModelAndView();
        ISaleService saleService = ServiceFactory.getISaleService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if ("1".equals(isedit)){   //编辑操作
				saleService.modifySaleApply(saleApplyInfo);//修改
			}else{
				saleService.addSaleApply(saleApplyInfo);  //插入
			}
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("插入转卖批次失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入转卖批次失败");
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 删除
	 */
	@RequestMapping(params="method=del")
	@ResponseBody
	public AjaxJson del(String saleId) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		ISaleService saleService = ServiceFactory.getISaleService();
		IDB session = DBFactory.getDB();
		try{
			session.beginTransaction();
			boolean bool = saleService.delApplyInfoForSaleIds(CommUtils.couvertLong(saleId));
			session.endTransaction();
			retJson.setSuccess(true);
			if(bool){
				retJson.setMsg("批次信息删除成功！");
			}else{
				retJson.setMsg("包含票据信息的批次不允许删除！");
			}
		} catch (Exception e){
			//e.printStackTrace();	
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库删除失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		
		return retJson;
	}
	
	/**
	 * 检查是否允许进行批次修改
	 * @param ids
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=checkBillsStatusForEditApply")
	@ResponseBody
	public AjaxJson checkBillsStatusForEditApply(SaleSearchBean searchBean) throws BizAppException{
		ISaleService saleService = ServiceFactory.getISaleService();
		AjaxJson retJson = new AjaxJson();
		try {
			//当修改批次时，要查询下该批次下是否有提交到下一岗的票，如果有则不允许修改
			searchBean.setDfaultSrchTbAliasName("bill");
			List<SaleBillInfo> allSaleBillList = saleService.getSaleBillListBySearchBean(searchBean);
			searchBean.setStatusArray(StatusUtils.queryStatus("SaleApplyController", "updateBatch", null));
			searchBean.addSqlPropretyMapping("statusArray", "operStatus");
			searchBean.addSpecialOpertion("statusArray",BaseSearchBean.IN);
			List<SaleBillInfo> allowSaleBillList = saleService.getSaleBillListBySearchBean(searchBean);
			if((allSaleBillList.size()-allowSaleBillList.size())>0){
				retJson.setSuccess(false);
				CommonLog.getCommonLogCache().infoMessage("该批次下存在不符合规定状态的票据，不允许修改批次信息");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "该批次下存在不符合规定状态的票据，不允许修改批次信息");
			}else{
				retJson.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException("检查是否允许进行批次修改失败,"+e.getMessage());
		}
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
	@RequestMapping(params = "method=toInterestTrial")
	public ModelAndView toInterestTrial(String ids,String billClass) {
		ModelAndView mv = new ModelAndView();
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(billClass)){
			mv.setViewName("busi/sale/electronic/apply/interest_trial");
		}else{
			mv.setViewName("busi/sale/common/interest_trial");
		}
		mv.addObject("salemxIds", ids);
		if(StringUtils.isBlank(billClass)){
			billClass = IDict.K_BILL_CLASS.K_ENTY_BILL;
		}
		mv.addObject("billClass", billClass);
		return mv;
	}
	/**
	 * 利息试算---批量试算
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=interestTrial")
	public ModelAndView interestTrial(SaleSearchBean bean) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ISaleService saleService = ServiceFactory.getISaleService();
		try {
			session.beginTransaction();
			saleService.interestTrial(bean);
			session.endTransaction();
		}catch (Exception e) {
			//e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("利息试算失败");
			throw new BizAppException("利息试算失败，"+e.getMessage());
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		
		return mv;
	}
	/**
	 * 去利息调整页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toInterestAdjust")
	public ModelAndView toInterestAdjust(String salemxId) {
		ModelAndView mv = new ModelAndView();
		ISaleService saleService = ServiceFactory.getISaleService();
		String[] ids = {salemxId};
		try {
			if(saleService.checkBillsHasCalcInterest(ids)){
				mv.setViewName("busi/sale/common/interest_adjust");
				mv.addObject("salemxId", salemxId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 利息调整
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=interestAdjust")
	public ModelAndView interestAdjust(HttpServletRequest req, HttpServletResponse response) throws BizAppException{

		ModelAndView mv = new ModelAndView();
		String salemxId = req.getParameter("salemxId");
		String adjustMoney = req.getParameter("adjustMoney");
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ISaleService saleService = ServiceFactory.getISaleService();
		try {
			session.beginTransaction();
			saleService.interestAdjust(salemxId, adjustMoney);
			session.endTransaction();
			
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("利息调整失败,"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "利息调整失败,"+e.getMessage());
		} 
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		
		return mv;
	}
	/**
	 * 检查是否进行过利息试算
	 * @param ids
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=checkBillsHasCalcInterest")
	@ResponseBody
	public AjaxJson checkBillsHasCalcInterest(String ids) throws BizAppException{
		ISaleService saleService = ServiceFactory.getISaleService();
		AjaxJson retJson = new AjaxJson();
		boolean bool = false;
		try {
			bool = saleService.checkBillsHasCalcInterest(CommUtils.couvertLong(ids));
			if(bool){
				retJson.setSuccess(true);
			}else{
				retJson.setSuccess(false);
				retJson.setMsg("请先进行利息试算");
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("检查利息试算失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "检查利息试算失败");
		}
		return retJson;
	}
	/**
	 * 去添加票据页面
	 * 查询票据信息列表，用于为批次新增票
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toAddBill")
	public ModelAndView toAddBill(Page page,SaleSearchBean searchBean) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		page.activeCommand();
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(searchBean.getBillClass())){
			mv.setViewName("busi/sale/electronic/apply/sale_apply_choose_bill");
		}else{
			searchBean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
			mv.setViewName("busi/sale/entity/apply/sale_apply_choose_bill");
		}
		try {
			ISaleService saleService = ServiceFactory.getISaleService();
			//查询待转出贴现票据信息
			//纸票
			//电票
			/*TODO searchBean.setBuyType(BillConst.BUY_ELEC_DEPOSIT);
			searchBean.addSpecialOpertion("buyType",BaseSearchBean.NOT_EQUAL);--买入类型不是电票托管*/
			mv.addObject("resultList",saleService.getRgctBillInfo(page,searchBean));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException("票据列表查询失败,"+e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("totalResult", page.getTotalResult());
		mv.addObject("query", searchBean);
		return mv;
	}
	/**
	 * 加票
	 */
	@RequestMapping(params="method=addBill")
	public ModelAndView addBill(SaleSearchBean bean) throws BizAppException {
		ModelAndView mv = new ModelAndView();
        ISaleService saleService = ServiceFactory.getISaleService();
		String ids = bean.getRgctIds();
		String saleId = bean.getSaleId();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			saleService.addSaleBillInfo(ids,saleId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("为转卖批次添加票据信息失败");
			throw new BizAppException("为转卖批次添加票据信息失败,"+e.getMessage());
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 为批次删除票据清单
	 */
	@RequestMapping(params="method=delBill")
	@ResponseBody
	public AjaxJson delBill(Page page,String ids) throws BizAppException{
		page.activeCommand();
		AjaxJson retJson = new AjaxJson();
		ISaleService saleService = ServiceFactory.getISaleService();
		
		IDB session = DBFactory.getDB();
		try{
			session.beginTransaction();
			int rs = saleService.delBillInfoForSalemxIds(CommUtils.couvertLong(ids));
			session.endTransaction();
			retJson.setSuccess(true);
			if(rs>0){
				retJson.setMsg("清单信息删除成功！");
			}else{
				retJson.setMsg("清单信息删除失败！");
			}
		} catch (Exception e){
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库删除失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		
		return retJson;
	}
	
	/**
	 * 去高级查询页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toHighSearch")
	public ModelAndView toHighSearch(String ids) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("busi/sale/common/high_search");
		mv.addObject("salemxIds", ids);
		return mv;
	}
	/**信息查询，批次及票据详情查看       begin**************************************************************************************/
	/**
	 * 根据交易对手开户行号填充交易对手开户行行名
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=custBankInfo")
	@ResponseBody
	public AjaxJson custBankInfo(String bankNo){

		AjaxJson retJson = new AjaxJson();
		IEcdsBankDataService ecdsBankDataService  = RcServiceFactory.getEcdsBankDataService();
		EcdsBankData ecdsBankData = null;
		try {
			ecdsBankData = ecdsBankDataService.getEcdsBankData(bankNo);
		} catch (BizAppException e) {
			e.printStackTrace();
			retJson.setMsg("查询数据库信息失败");
			retJson.setSuccess(false);
			e.printStackTrace();
			return retJson;
		}
		if(ecdsBankData==null){
			retJson.setMsg("根据交易对手开户行号无法找到对应的交易对手信息");
			retJson.setSuccess(false);
			return retJson;
		}
		retJson.setObj(ecdsBankData);
		retJson.setSuccess(true);
		
		return retJson;
	}
	/**
	 * 根据票据类型查询产品名称
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=searchProdinfo")
	@ResponseBody
	public AjaxJson searchProdinfo(String custType){
		AjaxJson retJson = new AjaxJson();
		// 获取产品信息
		IProductService productService = ServiceFactory.getProductService();
		List<Product> prodList = new ArrayList<Product>();
		String isInner = "";
		if("1".equals(custType)){
			isInner = RcConstants.INNER_NO;
		}else if("2".equals(custType)){
			isInner = RcConstants.INNER_YES;
		}
		try {
			prodList = productService.getProductListForPidAndIsInner(SaleCodeConst.PARENT_PROD_NO, isInner);
		} catch (SQLException e) {
			retJson.setSuccess(false);
			retJson.setMsg("查询产品信息失败:"+e.getMessage());
			e.printStackTrace();
		}
		retJson.setObj(prodList);
		retJson.setSuccess(true);
		return retJson;
	}
	/**
	 * 票据详情查看
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=goBillInfo")
	public ModelAndView goBillInfo(String salemxId){
		ModelAndView mv = new ModelAndView("busi/sale/common/bill_detail");
		ISaleService saleService = ServiceFactory.getISaleService();
		SaleBillInfo saleBillInfo = null;
		try {
			saleBillInfo = saleService.getSaleBillForSalemxId(salemxId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("bill", saleBillInfo);
		mv.addObject("salemxId", salemxId);
		return mv;
	}
	/**
	 * 票据详情查看--根据rgctId查询
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=goRgctBillByRgctId")
	public ModelAndView goRgctBillByRgctId(String rgctId){
		ModelAndView mv = new ModelAndView("busi/sale/common/rgctbill_detail");
		IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
		RgctBill bill = null;
		try {
			bill = rcSaleService.getRgctBillById(rgctId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("bill", bill);
		mv.addObject("rgctId", rgctId);
		return mv;
	}
	
	/**
	 * 批次详情查看
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=goApplyInfo")
	public ModelAndView goApplyInfo(SaleSearchBean query,String flag){
		SaleApplyInfo saleApplyInfo = new SaleApplyInfo();
		ISaleService saleService = ServiceFactory.getISaleService();
		try {
			if("apply".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleApplyController", "batchManege", null));
			}else if("cancelApply".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleApplyController", "cancelApplyList", null));
			}else if("audit".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleAuditController", "auditList", null));
			}else if("cancelAudit".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleAuditController", "cancelAuditList", null));
			}else if("account".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "queryApplyForSaleAcct", null));
			}else if("cancelAccount".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "cancelAccountList", null));
			}else if("elecApply".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleApplyController", "batchManege", null));
			}else if("elecCancelApply".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleApplyController", "cancelApplyList", null));
			}else if("endorse".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleEndorseController", "queryForSaleEndorse", null));
			}else if("cancelEndorse".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleEndorseController", "queryForCancelSaleEndorse", null));
			}else if("confirmEndorse".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleEndorseController", "queryForConfirmSign", null));
			}else if("elecAccount".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "queryApplyForElecSaleAccount", null));
			}else if("elecCancelAccount".equals(flag)){
				query.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "queryApplyForCancelElecSaleAccount", null));
			}
			
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
			query.addSqlPropretyMapping("statusArray", "operStatus");
			query.addProperty2TableObj("statusArray", "bill");
			saleApplyInfo = saleService.getSaleApplyInfo(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/sale/common/batch_detail");
		mv.addObject("saleApplyInfo", saleApplyInfo);
		mv.addObject("query", query);
		return mv;
	}
	/**
	 * 票据转卖申请提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=submitApply")
	public ModelAndView submitApply(String saleId,String ids,String billClass) throws BizAppException{
		ISaleService saleService = ServiceFactory.getISaleService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			saleService.submitSaleApply(saleId,CommUtils.couvertLong(ids));
			session.endTransaction();
			if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(billClass)){
				return elecBatch(new Page(), new SaleSearchBean());
			}else{
				return batchManage(new Page(), new SaleSearchBean());
			}
		}catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据转卖申请提交失败");
			throw new BizAppException("票据转卖申请提交失败:"+e.getMessage());
		}
	}
	/**
	 * 撤销申请
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelApply")
	public ModelAndView cancelApply(String saleId,String ids,String billClass) throws BizAppException{
		ISaleService saleService = ServiceFactory.getISaleService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			saleService.cancelApply(saleId,ids);
			session.endTransaction();
			if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(billClass)){
				return cancelElecBatch(new Page(), new SaleSearchBean());
			}else{
				return cancelApplyBatch(new Page(), new SaleSearchBean());
			}
		}  catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("撤销票据转卖申请失败");
			throw new BizAppException(e.getMessage());
		}
	}
	/**
	 * 撤销申请：去批次清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelApplyBillManage")
	public ModelAndView cancelApplyBillManage(Page page,SaleSearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(searchBean.getBillClass())){
			mv.setViewName("busi/sale/electronic/apply/sale_cancel_apply_bill_list");
		}else{
			mv.setViewName("busi/sale/entity/apply/sale_cancel_apply_bill_list");
		}
		page.activeCommand();
		try {
			ISaleService saleService = ServiceFactory.getISaleService();
			searchBean.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			searchBean.setDfaultSrchTbAliasName("bill");
			searchBean.addSqlPropretyMapping("statusArray", "operStatus");
			searchBean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
			searchBean.setStatusArray(StatusUtils.queryStatus("SaleApplyController", "cancelApplyList", null));
			mv.addObject("batch",saleService.getSaleApplyInfo(searchBean));//获取批次信息
			mv.addObject("resultList",saleService.getSaleBillListBySearchBeanForPage(page,searchBean));//获取批次下清单列表信息
		}catch(Exception e){
			CommonLog.getCommonLogCache().errorMessage("批次清单列表查询失败");
			throw new BizAppException("批次清单列表查询失败，"+e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("query", searchBean);
		return mv;
	}
	
	/***公共   end********************************************************************************/
}
