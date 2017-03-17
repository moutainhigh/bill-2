/********************************************
 * 文件名称: BuybackApplyController.java
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
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.buyback.bean.BuybackApplyInfo;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.bean.BuybackSearchBean;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.buyback.IBuybackService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.parser.exception.ParseException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 回购申请Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/buybackApplyController")
public class BuybackApplyController extends BaseController {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/*********电票**********/
	/**
	 * 功能描述：待申请批次列表页面
	 * @param request
	 * @return ModelAndView
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=buybackApplyList")
	public ModelAndView buybackApplyList(Page page,BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_apply_batch_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
//			query.setOpenDate(DateTimeUtil.getWorkdayString());
			query.setBranchNo(user.getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "buybackApply", "1")[0]);
			query.setBillClass(CommonConst.BILL_CLASS_ELEC);
			query.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
			mv.addObject("batchList", buybackService.getSaleApplyListBySearchBean(page,query));
//			mv.addObject("batchList", buybackService.getBuybackApplyListForCondition(page,query));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("回购批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "回购批次列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 待申请票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=buybackApplyDetailList")
	public ModelAndView buybackApplyDetailList(Page page,BuybackSearchBean query) throws BizAppException{
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_apply_detail_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			BuybackApplyInfo buybackApply=new BuybackApplyInfo();
			if(query.getBuybackId()==null){
				buybackApply.setBuybackId(sequenceService.getPrimaryKeyValue());
				buybackApply.setBatchNo(sequenceService.getBuybackApplyNo(user.getBranchNo()));
			}else{
				BuybackApplyInfo buybackApply2 = buybackService.getBuybackApplyInfo(query.getBuybackId(), null);
				buybackApply.setBuybackId(query.getBuybackId());
				buybackApply.setBatchNo(buybackApply2.getBatchNo());
			}
			query.setBuybackId(null);
			query.setBillClass(CommonConst.BILL_CLASS_ELEC);
			query.setBranchNo(user.getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "buybackApply", "1")[0]);
			List<BuybackBillInfo> billList=buybackService.getBuybackBillListForBatch(page,query);
			String ids=buybackService.getBillIdsString(billList);
			mv.addObject("resultList",billList);
			mv.addObject("ids",ids);
			query.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
			RgctBill rgctBill=RcServiceFactory.getRcRegBillService().getRgctBillById(billList.get(0).getRgctId());
			SaleApplyInfo saleApply=buybackService.getSaleApplyListBySearchBean(page,query).get(0);
			buybackApply.setBillClass(CommonConst.BILL_CLASS_ELEC);
			buybackApply.setBillType(saleApply.getBillType());
			buybackApply.setSaleId(saleApply.getSaleId());
			buybackApply.setCustName(saleApply.getCustName());
			buybackApply.setTotalNum(saleApply.getTotalSize().toString());
			buybackApply.setTotalMoney(saleApply.getSumMoney().toString());
			buybackApply.setTotalInterest(saleApply.getSumInterest().toString());
			buybackApply.setTotalDraweeMoney(saleApply.getSumReceiveMoney().toString());
			buybackApply.setIsOnline(rgctBill.getHist().getIsOnline());
			buybackApply.setBuybackDt(rgctBill.getHist().getRegressDt());
			buybackApply.setBuybackRate(rgctBill.getHist().getBackRate()*100);
			mv.addObject("batch",buybackApply);
//			mv.addObject("batch",buybackService.getBuybackApplyInfo(query.getBuybackId(),query));
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待申请票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待申请票据列表查询失败");
		}
		mv.addObject("query", query);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 清空票据利息等信息
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=updateBillInfo")
	public ModelAndView updateBillInfo(BuybackSearchBean query)throws BizAppException{
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		try {
			IDB session = DBFactory.getDB();
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "buybackApply", "0")[0]);
			session.beginTransaction();
			buybackService.clearInterestTrialInfo(query);
			session.endTransaction();
		}catch (Exception e1){
			CommonLog.getCommonLogCache().errorMessage("票据利息更新失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据利息更新失败");
		}
		return buybackApplyDetailList(new Page(), query);
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
	public ModelAndView toInterestTrial(String ids,BuybackApplyInfo buybackApply ,String saleId,String buybackRate) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("busi/buyback/electronic/buyback_interest_trial");
		mv.addObject("ids", ids);
		mv.addObject("saleId", saleId);
		mv.addObject("buybackRate", buybackRate);
		mv.addObject("batch", buybackApply);
		return mv;
	}
	
	/**
	 * 利息试算
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=interestTrial")
	public ModelAndView interestTrial(BuybackBillInfo buyback,BuybackApplyInfo buybackApply ,String ids,String buybackRate) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			int rs = buybackService.interestTrial(buyback,ids,buybackRate,buybackApply);
			if( rs > 0 ){
				mv.addObject("msg","success");
				session.endTransaction();
			}else{
				session.rollback();
				mv.addObject("msg","failed");
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("利息试算失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "利息试算失败");
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	/**是否已经计算利息 没计算返回false
	 * @throws BizAppException 
	 *
	 */
	@RequestMapping(params="method=isInterestTrial")
	@ResponseBody
	public AjaxJson isInterestTrial(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		try {
			if(!ServiceFactory.getBuybackService().isInterestTrial(ids)){
				aj.setSuccess(false);		
			}else{
				aj.setSuccess(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查询是否计算利息失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询是否计算利息失败");
		}
		
		return aj;
	}
	
	/**
	 * 票据提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=apply")
	public ModelAndView apply(String ids, Page page, BuybackSearchBean query,String buybackRate) throws BizAppException{
		ModelAndView mv = null;
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = ServiceFactory.getBuybackService().applySubmit(ids,query,buybackRate);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据申请提交失败"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据申请提交失败"+e.getMessage());
		}
		//清单没有试算利息
		if(rs == -999){
			throw new BizAppException("请先计算利息");
		} 
		//网银端已撤销
		if(rs == -998){
			throw new BizAppException("网银端已撤销");
		}
		if(rs>0){
			BuybackSearchBean query1=new BuybackSearchBean();
			mv=this.buybackApplyList(page, query1);
		}
		return mv;
	}
	
	/**
	 * 电票拒接
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=noReceive")
	public ModelAndView noReceive(String ids,BuybackSearchBean query) throws BizAppException{
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			ServiceFactory.getBuybackService().noReceive(ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据申请提交失败"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据申请提交失败"+e.getMessage());
		}
		return buybackApplyList(new Page(), query);
	}
	
	/**
	 * 功能描述：撤销申请批次列表页面
	 * @param query
	 * @param page
	 * @return ModelAndView
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=buybackApplyCancelList")
	public ModelAndView buybackApplyCancelList(Page page,BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_cancel_apply_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());
			query.setApplyOperNo(user.getUserId());
			query.setOperStatus(StatusUtils.queryStatus("BuybackAccountController", "buybackApplyAccount", null)[0]);
			query.setBillClass(CommonConst.BILL_CLASS_ELEC);
			mv.addObject("batchList", buybackService.getBuybackApplyListForCondition(page,query));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		mv.addObject("query", query);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 功能描述：撤销申请票据列表页面
	 * @param page
	 * @param query
	 * @return ModelAndView
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelApplyDetailList")
	public ModelAndView cancelApplyDetailList(Page page,BuybackSearchBean query) throws BizAppException{
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_cancel_apply_detail_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOperStatus(StatusUtils.queryStatus("BuybackAccountController", "buybackApplyAccount",null)[0]);
			mv.addObject("batch",buybackService.getBuybackApplyInfo(query.getBuybackId(),query));
			List<BuybackBillInfo> billList=buybackService.getBuybackBillListForBatch(page,query);
			String ids=buybackService.getBillIdsString(billList);
			mv.addObject("resultList",billList);
			mv.addObject("ids",ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("撤销申请票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "撤销申请票据列表查询失败");
		}
		mv.addObject("query", query);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 撤销申请
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelApply")
	public ModelAndView cancelApply(String ids,String buybackId) throws BizAppException{
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			ServiceFactory.getBuybackService().cancel("BuybackApplyController","buybackApply","1",ids,buybackId);
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
		return buybackApplyCancelList(new Page(),new BuybackSearchBean());
	}
	
	/**
	 * 批次查看
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=goBatchInfo")
	public ModelAndView goBatchInfo(String buybackId){
		ModelAndView mv = new ModelAndView("busi/buyback/electronic/buyback_batch_detail");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		try {
			mv.addObject("info", buybackService.getBuybackApplyInfo(buybackId,null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	/*********纸票**********/
	/**
	 * 申请批次页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = { "method=toEntityBuybackApplyList" })
	public ModelAndView toEntityBuybackApplyList() throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_apply_batch_list");
		return mv;
	}
	
	/**
	 * 功能描述：待申请批次列表页面
	 * @param request
	 * @return ModelAndView
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=entityBuybackApplyList")
	public ModelAndView entityBuybackApplyList(Page page,BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_apply_batch_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setOpenDate(DateTimeUtil.getWorkdayString());
			query.setBranchNo(user.getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "entyBuybackApply", null)[0]);
			query.setBillClass(CommonConst.BILL_CLASS_ENTITY);
			query.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
			mv.addObject("batchList", buybackService.getSaleApplyListBySearchBean(page,query));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("回购转卖批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "回购转卖批次列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("isInner",query.getIsInner());
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
	@RequestMapping(params = { "method=entityBuybackBillList" })
	public ModelAndView entityBuybackBillList(Page page,BuybackSearchBean query,String buybackDt,String buybackRate) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_apply_bill_list");
		IBuybackService buybackService=ServiceFactory.getBuybackService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB();
		try {
			page.activeCommand();
			session.beginTransaction();
			BuybackApplyInfo buybackApply=new BuybackApplyInfo();
			if(query.getBuybackId()==null){
				buybackApply.setBuybackId(sequenceService.getPrimaryKeyValue());
				buybackApply.setBatchNo(sequenceService.getBuybackApplyNo(user.getBranchNo()));
			}else{
				BuybackApplyInfo buybackApply2 = buybackService.getBuybackApplyInfo(query.getBuybackId(), null);
				buybackApply.setBuybackId(query.getBuybackId());
				buybackApply.setBatchNo(buybackApply2.getBatchNo());
				buybackRate=String.valueOf(buybackApply2.getBuybackRate());
				buybackDt=buybackApply2.getCreateDt();
			}
			query.setBuybackId(null);
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "entyBuybackApply", null)[0]);
			query.setBranchNo(user.getBranchNo());
			query.setBillClass(CommonConst.BILL_CLASS_ENTITY);
			mv.addObject("resultList",buybackService.getBuybackBillListForBatch(page,query));
			query.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
			query.setOpenDate(DateTimeUtil.getWorkdayString());
			SaleApplyInfo saleApply=buybackService.getSaleApplyListBySearchBean(page,query).get(0);
			buybackApply.setBillClass(CommonConst.BILL_CLASS_ENTITY);
			buybackApply.setBillType(saleApply.getBillType());
			buybackApply.setSaleId(saleApply.getSaleId());
			buybackApply.setCustName(saleApply.getCustName());
			buybackApply.setTotalNum(saleApply.getTotalSize().toString());
			buybackApply.setTotalMoney(saleApply.getSumMoney().toString());
			buybackApply.setTotalInterest(saleApply.getSumInterest().toString());
			buybackApply.setTotalDraweeMoney(saleApply.getSumReceiveMoney().toString());
			mv.addObject("batch",buybackApply);
			if(("").equals(buybackDt)||buybackDt==null){
				buybackDt=DateTimeUtil.getWorkdayString();
			}
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待申请票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待申请票据列表查询失败");
		}
		mv.addObject("buybackDt", buybackDt);
		mv.addObject("buybackRate", buybackRate);
		mv.addObject("isInner", query.getIsInner());
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 清空票据利息等信息
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=updateBillInfoForEntity")
	public ModelAndView updateBillInfoForEntity(BuybackSearchBean query)throws BizAppException{
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		try {
			IDB session = DBFactory.getDB();
			session.beginTransaction();
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "entyBuybackApply", null)[0]);
			buybackService.clearInterestTrialInfoForEntity(query);
			session.endTransaction();
		}catch (Exception e1){
			CommonLog.getCommonLogCache().errorMessage("票据利息更新失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据利息更新失败");
		}
		return entityBuybackBillList(new Page(), query,null,null);
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
	public ModelAndView goBatchDetail(String buybackId, BuybackSearchBean query) {
		BuybackApplyInfo buybackApply = new BuybackApplyInfo();
		IBuybackService buybackService=ServiceFactory.getBuybackService();
		try {
			buybackApply = buybackService.getBuybackApplyInfo(buybackId, query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_batch_detail");
		mv.addObject("buybackApply", buybackApply);
		mv.addObject("query", query);
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
	public ModelAndView goBillDetail(String buybackmxId) {
		BuybackBillInfo buybackBill = new BuybackBillInfo();
		IBuybackService buybackService=ServiceFactory.getBuybackService();
		try {
			buybackBill = buybackService.getBuybackBillInfo(buybackmxId);
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_bill_detail");
		mv.addObject("buybackBill", buybackBill);
		return mv;
	}
	
	/**
	 * 去利息试算页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toInterestTrialForEntity")
	public ModelAndView toInterestTrialForEntity(String ids,String saleId,BuybackApplyInfo buybackApply ,String buybackDt,String buybackRate) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("busi/buyback/entity/buyback_interest_trial");
		mv.addObject("ids", ids);
		mv.addObject("saleId", saleId);
		mv.addObject("buybackDt", buybackDt);
		mv.addObject("buybackRate", buybackRate);
		mv.addObject("batch", buybackApply);
		return mv;
	}
	
	/**
	 * 利息试算
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=interestTrialForEntity")
	public ModelAndView interestTrialForEntity(BuybackBillInfo buybackBill,String ids,BuybackApplyInfo buybackApply,String buybackDt,String buybackRate,String isSameCity) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		BuybackSearchBean query=new BuybackSearchBean();
		try {
			session.beginTransaction();
			query.setSaleId(buybackBill.getSaleId());
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "entyBuybackApply", null)[0]);
			buybackService.clearInterestTrialInfoForEntity(query);
			int rs = buybackService.interestTrialForEntity(buybackBill,ids,buybackApply,buybackDt,buybackRate,isSameCity);
			if( rs > 0 ){
				mv.addObject("msg","success");
				session.endTransaction();
			}else{
				session.rollback();
				mv.addObject("msg","failed");
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("利息试算失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "利息试算失败");
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 调整利息页面
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "method=toAdjustInterest")
	public ModelAndView toAdjustInterest(String id){
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_adjust_interest");
		mv.addObject("id", id);
		return mv;
	}
	
	/**
	 * 调整利息
	 * @param id
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=interestAdjust")
	@ResponseBody
	public int interestAdjust(String id,String adjustMoney) throws BizAppException{
		IBuybackService buybackService=ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs=buybackService.interestAdjust(id,adjustMoney);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("调整利息失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "调整利息失败");
		}
		//清单没有试算利息
		if(rs == -999){
			throw new BizAppException("请先计算利息");
		}
		return rs;
	}
	
	/**
	 * 票据提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=entityApply")
	public ModelAndView entityApply(String ids, Page page, BuybackSearchBean query) throws BizAppException{
		ModelAndView mv = null;
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = ServiceFactory.getBuybackService().applySubmitForEntity(ids,query);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据申请提交失败"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据申请提交失败"+e.getMessage());
		}
		//清单没有试算利息
		if(rs == -999){
			throw new BizAppException("请先计算利息");
		} 
		if(rs>0){
			/*query.setBuybackId(null);
			query.setBatchNo(null);
			query.setSaleId(null);*/
			mv=this.toEntityBuybackApplyList();
		}
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
	public ModelAndView cancelApplyBatchList(Page page, BuybackSearchBean query)
			throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_cancel_apply_batch_list");
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		AjaxJson retJson = new AjaxJson();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		try {
			page.activeCommand();
			query.addProperty2TableObj("operNo", "apply");
			query.addSqlPropretyMapping("operNo", "operNo");
			query.setApplyOperNo(user.getUserId());
			query.setBillClass(CommonConst.BILL_CLASS_ENTITY);
			query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "entyBuybackCancelApply",null)[0]);
			mv.addObject("batchList", buybackService.getBuybackApplyListForCondition(page, query));
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
	public ModelAndView cancelApplyBillList(Page page, BuybackSearchBean query)
			throws BizAppException {
		IBuybackService buybackService = ServiceFactory.getBuybackService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/buyback/entity/buyback_cancel_apply_bill_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus("BuybackApplyController", "entyBuybackCancelApply",null));
			mv.addObject("batch", buybackService.getBuybackApplyInfo(query.getBuybackId(), query));
			mv.addObject("resultList", buybackService.getBuybackBillListForBatch(page, query));
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
	 * 撤销申请(纸票)
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=entityCancelApply")
	public ModelAndView entityCancelApply(String ids,String buybackId) throws BizAppException{
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IBuybackService buybackService=ServiceFactory.getBuybackService();
		try {
			session.beginTransaction();
			ServiceFactory.getBuybackService().cancel("BuybackApplyController","entyBuybackCancelApply",ids);
			BuybackApplyInfo apply = buybackService.getBuybackApplyInfo(buybackId, null);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
			buybackService.modifyBuybackApplyInfo(apply);
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
		return cancelApplyBatchList(new Page(),new BuybackSearchBean());
	}
	
}
