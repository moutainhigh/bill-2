/********************************************
 * 文件名称: DiscAuditController.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-08-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.web.busicontroller.disc;

import java.sql.SQLException;
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
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.DiscCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.parser.exception.ParseException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 贴现审核Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/discAuditController")
public class DiscAuditController extends BaseController {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 票据查看
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=goDetail")
	public ModelAndView goDetail(String discmxId,String discId,DiscSearchBean query){
		IDiscService discService = ServiceFactory.getDiscService();
		DiscBillInfo discBillInfo = new DiscBillInfo();
		DiscApplyInfo discApplyInfo = new DiscApplyInfo();
			try {
				try {
					discApplyInfo=discService.getDiscApplyInfo(discId, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				discBillInfo = discService.getDiscBillInfo(discmxId);
			} catch (BizAppException e) {
				e.printStackTrace();
			}
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_bill_detail");
		mv.addObject("applylist",discApplyInfo);
		mv.addObject("list", discBillInfo);
		return mv;
	}
	
	
	/**
	 * 票据审核
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=audit")
	@ResponseBody
	public AjaxJson audit(String ids,String status,String reason) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = discService.auditSubmit(ids,status,reason);
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
	 * 票据审核页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toAudit")
	public ModelAndView toAudit(String ids){
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_audit");
		mv.addObject("ids", ids);
		return mv;
	}
	/**
	 * 待审核票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditDetailList")
	public ModelAndView auditDetailList(Page page,DiscSearchBean query,String isReadonly) throws BizAppException{
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv;
		if(!"".equals(isReadonly)&&"1".equals(isReadonly)){
			mv = new ModelAndView("busi/disc/entity/disc_audit_detail_list_readonly");
		}else{
			mv = new ModelAndView("busi/disc/entity/disc_audit_detail_list");
		}
		try {
			page.activeCommand();
			session.beginTransaction();
			DiscApplyInfo disapplyinfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(disapplyinfo.getProdNo());
			String ProdName="" ;
			if(prod!=null){
				ProdName = prod.getProdName();
			}
			query.setOpers(StatusUtils.queryStatus("DiscAuditController", "auditDetailList", null));
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
			mv.addObject("ProdName",ProdName);
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
	 * 待审核批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditList")
	public ModelAndView auditList(Page page,DiscSearchBean query) throws BizAppException{
		String temp = query.getBatchNo();
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_audit_batch_list");
		IDiscService discService = ServiceFactory.getDiscService();
		AjaxJson retJson = new AjaxJson();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());
			query.setOpers(StatusUtils.queryStatus("DiscAuditController", "auditDetailList", null));
			query.setBillClass(DiscCodeConst.BILL_CLASS_ENTITY);
			mv.addObject("batchList", discService.getDiscApplyListForCondition(page,query));
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("batchNo", temp);
		return mv;
	}
	
	/**
	 * 待撤销审核列表
	 * 
	 * @param Page page,DiscQueryCondition query
	 * @return ModelAndView
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelAuditList")
	public ModelAndView account(Page page,DiscSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_cancel_audit_list");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());
			query.setAuditOperNo(user.getUserId());
			query.setOperStatus(StatusUtils.queryStatus("DiscAccountController", "reviewDetailList", null)[0]);
			query.setBillClass(DiscCodeConst.BILL_CLASS_ENTITY);
			List<DiscApplyInfo> batchList = discService.getDiscApplyListForCondition(page,query);
			mv.addObject("batchList", batchList);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("贴现记账查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "贴现记账查询失败");
		}
		return mv;
	}
	
	/**
	 * 待撤销审核票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelAuditDetailList")
	public ModelAndView cancelAuditDetailList(Page page,DiscSearchBean query) throws BizAppException{
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_cancel_audit_detail_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus("DiscAccountController", "reviewDetailList", null));
			DiscApplyInfo disapplyinfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(disapplyinfo.getProdNo());
			String ProdName="" ;
			if(prod!=null){
				ProdName = prod.getProdName();
			}
			mv.addObject("ProdName",ProdName);
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("待审核票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待审核票据列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("query",query);
		return mv;
	}
	
	/**
	 * 撤销审核
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelAudit")
	@ResponseBody
	public AjaxJson cancelAudit(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = discService.cancel("DiscAuditController","cancelAudit",ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据撤销审核失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销审核失败");
		}
		aj.setSuccess(rs > 0);
		return aj;
	}
	
	
	/**-------------电票系统start-------------------*/
	
	
	
	/**
	 * 票据审核
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditElec")
	@ResponseBody
	public AjaxJson auditElec(String ids,String status,String reason) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = discService.auditSubmit(ids, status, reason);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据审核提交失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		aj.setSuccess(rs > 0);
		return aj;
	}
	/**
	 * 票据审核页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toAuditElec")
	public ModelAndView toAuditElec(String ids){
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_audit");
		mv.addObject("ids", ids);
		return mv;
	}
	/**
	 * 待审核票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditBillList")
	public ModelAndView auditBillList(Page page,DiscSearchBean query) throws BizAppException{
		IProductService productService = ServiceFactory.getProductService();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_audit_bill_list");
		try {
			DiscApplyInfo applyInfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			Product prod = productService.getProductInfoByProdNo(applyInfo.getProdNo());
			page.activeCommand();
			session.beginTransaction();
			query.setOpers(StatusUtils.queryStatus("DiscAuditController", "auditDetailList", null));
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
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
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		return mv;
	}
	/**
	 * 待审核批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditListElec")
	public ModelAndView auditListElec(Page page,DiscSearchBean query) throws BizAppException{
		String temp = query.getBatchNo();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		query.setBillClass(billClass);
		query.setBranchNo(branchNo);
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_audit_batch_list");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			page.activeCommand();
			query.setOpers(StatusUtils.queryStatus("DiscAuditController", "auditDetailList", null));
			mv.addObject("batchList", discService.getDiscApplyListForCondition(page,query));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("batchNo", temp);
		return mv;
	}
	
	/**
	 * 待撤销审核批次列表页面
	 * 
	 * @param Page page,DiscQueryCondition query
	 * @return ModelAndView
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelAuditElecBatchList")
	public ModelAndView cancelAuditElecBatchList(Page page,DiscSearchBean query) throws BizAppException {
		String temp = query.getBatchNo();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		query.setBillClass(billClass);
		query.setBranchNo(branchNo);
		query.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
		query.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_cancel_audit_batch_list");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			page.activeCommand();
			query.setOperStatus(StatusUtils.queryStatus("DiscAuditController", "cancelElecAudit", null)[0]);
			mv.addObject("batchList", discService.getDiscApplyListForCondition(page,query));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("batchNo", temp);
		return mv;
	}
	
	/**
	 * 待撤销审核票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelAuditElecBillList")
	public ModelAndView cancelAuditElecBillList(Page page,DiscSearchBean query) throws BizAppException{
		IProductService productService = ServiceFactory.getProductService();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_cancel_audit_bill_list");
		try {
			query.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			DiscApplyInfo applyInfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			Product prod = productService.getProductInfoByProdNo(applyInfo.getProdNo());
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(StatusUtils.queryStatus("DiscAuditController", "cancelElecAudit", null)[0]);
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
			mv.addObject("prodName", prod.getProdName());
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待撤销票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 撤销审核
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelAuditElec")
	@ResponseBody
	public AjaxJson cancelAuditElec(DiscBillInfo query,String discmxIds) throws BizAppException{
		Map<String, Object> retMap = new HashMap<String, Object>();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo(); 
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		AjaxJson retJson = new AjaxJson();                       
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			String curStatus=StatusUtils.queryStatus("DiscAuditController", "cancelElecAudit", null)[0];
			session.beginTransaction();
			List<DiscBillInfo> list = discService.getElectricReceiveForId(discmxIds);
			for(int i = 0 ;i<list.size();i++){
				DiscBillInfo discBill=list.get(i);
				String status= discBill.getOperStatus();
				String afterStatus= StatusUtils.handleStatus("DiscAuditController", "cancelElecAudit", null, status);
				discBill.setOperStatus(afterStatus);
				discBill.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
				discService.modDiscBillInfo(discBill);
		}
			query.setBranchNo(branchNo);
			query.setBillClass(billClass);
			query.setOperStatus(curStatus);
			int i = discService.totalCount(query);
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
	 * 校验审核是否被撤销
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=checkAudit")
	@ResponseBody
	public AjaxJson checkAudit(String ids) throws BizAppException{
		AjaxJson retJson = new AjaxJson();                       
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			session.beginTransaction();
			List<DiscBillInfo> list = discService.getElectricReceiveForId(ids);
			for(int i = 0 ;i<list.size();i++){
				DiscBillInfo discBill=list.get(i);
				if(DiscCodeConst.IS_CANCEL_TRUE.equals(discBill.getIsCancel())||discBill.getIsCancel()==DiscCodeConst.IS_CANCEL_TRUE){
					retJson.setMsg("对方已经撤销审核,票号为"+discBill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}else{
					retJson.setSuccess(true);
				}
		}
			
		} catch (SQLException e) {
			try {
				session.rollback();
				retJson.setMsg("查询相关票据信息失败");
				retJson.setSuccess(false);
				return retJson;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
		return retJson;
	}
}
