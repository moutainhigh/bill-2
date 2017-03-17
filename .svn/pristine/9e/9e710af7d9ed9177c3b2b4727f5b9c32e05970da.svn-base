/********************************************
 * 文件名称: DiscAccountController.java
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
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.DiscCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.sysconstant.ISysErrorNo;


/**
 * 贴现记账Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/discAccountController")
public class DiscAccountController extends BaseController {
	
	/**
	 * 撤销贴现记账
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelAccount")
	@ResponseBody
	public AjaxJson cancelAccount(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = discService.cancel("DiscAccountController","cancelAccount",ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据撤销贴现记账失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据撤销贴现记账失败");
		}
		aj.setSuccess(rs>0);
		return aj;
	}
	
	/**
	 * 待撤销记账票据详情列表页面
	 * @param Page page,DiscQueryCondition query
	 * @return ModelAndView
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelAccountDetailList")
	public ModelAndView cancelAccountDetailList(Page page,DiscSearchBean query) throws BizAppException{//首次登陆
		IDiscService discService = ServiceFactory.getDiscService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_cancel_account_detail_list");
		page.activeCommand();
		try {
			session.beginTransaction();
			query.setBranchNo(user.getBranchNo());
			query.setAccountDate(DateTimeUtil.getWorkdayString());
			query.setOpers(StatusUtils.queryStatus("DiscStorageController", "doStorage", null));
			DiscApplyInfo disapplyinfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(disapplyinfo.getProdNo());
			String ProdName = prod.getProdName();
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
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待撤销记账票据详情列表页面查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销记账票据详情列表页面查询失败");
			
		}
		mv.addObject("page", page);
		return mv;
	}
	/**
	 * 待撤销记账批次列表
	 * @param Page page,DiscQueryCondition query
	 * @return ModelAndView
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelAccountList")
	public ModelAndView cancelAccountList(Page page,DiscSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_cancel_account_list");
		IDiscService discService = ServiceFactory.getDiscService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		try {
			page.activeCommand();
			query.addProperty2TableObj("branchNo", "apply");
			query.setBranchNo(user.getBranchNo());
			query.setAccountDate(DateTimeUtil.getWorkdayString());
			query.setOpers(StatusUtils.queryStatus("DiscStorageController", "doStorage", null));
			query.setAcctOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setBillClass(DiscCodeConst.BILL_CLASS_ENTITY);
			List<DiscApplyInfo> batchList = discService.getDiscApplyListForCondition(page,query);
			mv.addObject("batchList", batchList);
			mv.addObject("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage(" 待撤销记账批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, " 待撤销记账批次列表查询失败");
		}
		return mv;
	}
	/**
	 * 贴现记账列表
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=reviewAccountList")
	public ModelAndView reviewAccountList(Page page,DiscSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_review_account_list");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());
			query.setOperStatus(StatusUtils.queryStatus("DiscAccountController", "reviewDetailList", null)[0]);
			query.setBillClass(DiscCodeConst.BILL_CLASS_ENTITY);
			List<DiscApplyInfo> batchList = discService.getDiscApplyListForCondition(page,query);
			mv.addObject("batchList", batchList);
			mv.addObject("page", page);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("贴现记账查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 复核票据列表清单页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=reviewDetailList")
	public ModelAndView reviewDetailList(Page page,DiscSearchBean query) throws BizAppException{
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_review_account_detail_list");
		try {
			session.beginTransaction();
			page.activeCommand();
			query.setOpers(StatusUtils.queryStatus("DiscAccountController", "reviewDetailList", null));
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
			DiscApplyInfo disapplyinfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(disapplyinfo.getProdNo());
			String ProdName = prod.getProdName();
			mv.addObject("ProdName",ProdName);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("复核票据列表清单页面查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "复核票据列表清单页面查询失败");
		}
		mv.addObject("query",query);
		mv.addObject("page", page);
		return mv;
	}
	/**
	 * 票据复核
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=review")
	public ModelAndView review(String billNo,String discmxId){
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_review");
		mv.addObject("discNos", billNo);
		return mv;
	}
	
	/**
	 * 电子贴现复核记账批次列表
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecAccount")
	public ModelAndView elecAccount(Page page,DiscSearchBean query) throws BizAppException {
		String temp = query.getBatchNo();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		query.setBillClass(billClass);
		query.setBranchNo(branchNo);
		//query.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_review_batch_list");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			page.activeCommand();
			query.setOperStatus(StatusUtils.queryStatus("DiscAccountController", "elecAccount", null)[0]);
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
	 * 电票票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecReviewList")
	public ModelAndView elecReviewList(Page page,DiscSearchBean query) throws BizAppException{
		IProductService productService = ServiceFactory.getProductService();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_review_bill_list");
		try {
			DiscApplyInfo applyInfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			Product prod = productService.getProductInfoByProdNo(applyInfo.getProdNo());
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(StatusUtils.queryStatus("DiscAccountController", "elecReviewList", null)[0]);
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
			CommonLog.getCommonLogCache().errorMessage("待复核票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		return mv;
	}
	

	/**
	 * 电票提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=doElecAccount")
	public ModelAndView doElecAccount(DiscBillInfo query,String discmxIds) throws BizAppException{
		ModelAndView mv = null;
		IDiscService discService = ServiceFactory.getDiscService();
			try {
				discService.discElecReviewBillListDoAccount(discmxIds,query.getDiscId());
				int count=discService.discElecReviewBillForTotalCount(query);
				if(count == 0){
						   mv = this.elecAccount(new Page(), new DiscSearchBean());
					   }else{
						   DiscSearchBean  searchBean= new DiscSearchBean();
						   searchBean.setAcctNo(query.getCustAccount());
						   searchBean.setDiscId(query.getDiscId());
						   mv = this.elecReviewList(new Page(), searchBean);
					   }
			} catch (Exception e) {
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("提交失败:"+e.getMessage());
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "提交失败:"+e.getMessage());
			}
			return mv;
	}
	
	/**
	 * 电票确认记账批次页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=finalAccount")
	public ModelAndView finalAccount(Page page,DiscSearchBean query) throws BizAppException{
		String temp = query.getBatchNo();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		query.setBillClass(billClass);
		query.setBranchNo(branchNo);
		query.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_account_batch_list");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			page.activeCommand();
			query.setOperStatus(StatusUtils.queryStatus("DiscAccountController", "doFinalAccount", null)[0]);
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
	 * 电票确认记账票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecAccountBillList")
	public ModelAndView elecAccountBillList(Page page,DiscSearchBean query) throws BizAppException{
		IProductService productService = ServiceFactory.getProductService();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_account_bill_list");
		try {
			DiscApplyInfo applyInfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			Product prod = productService.getProductInfoByProdNo(applyInfo.getProdNo());
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(StatusUtils.queryStatus("DiscAccountController", "doFinalAccount", null)[0]);
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
			CommonLog.getCommonLogCache().errorMessage("待记账票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 电票确认记账
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params="method=doFinalAccount")
	@ResponseBody
	public AjaxJson doFinalAccount(DiscBillInfo query,String discmxIds) throws BizAppException{
		Map<String, Object> retMap = new HashMap<String, Object>();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo(); 
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IDiscService discService = ServiceFactory.getDiscService();
		IAccountFacadeService acctService = ServiceFactory.getDiscAccountService();
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
			try {
				DiscApplyInfo apply = discService.getDiscApplyInfo(query.getDiscId(), null);
				String curStatus=StatusUtils.queryStatus("DiscAccountController", "doFinalAccount", null)[0];
				session.beginTransaction();
				List<DiscBillInfo> list= discService.getElectricReceiveForId(discmxIds);
				for(int i = 0 ;i<list.size();i++){
				    DiscBillInfo discBill = list.get(i);
				    RgctBill rgctBill = RcServiceFactory.getRcDiscService().getRgctBillById(discBill.getRgctId());
				    rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
				    rgctBill.getInfo().setBillSource(RcConstants.BILL_SOURCE_DISC);
				    RcServiceFactory.getRcDiscService().updateRgctBillHist(rgctBill.getHist());
				    RcServiceFactory.getRcDiscService().updateRgctBillInfo(rgctBill.getInfo());
				    String status= discBill.getOperStatus();
				    String afterStatus= StatusUtils.handleStatus("DiscAccountController", "doFinalAccount", null, status);
				    discBill.setOperStatus(afterStatus);
				    discBill.setAcctOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
				    discBill.setAccountDate(DateTimeUtil.getWorkdayString());
				    discBill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				    discBill.doDiscAccount();
				    discService.modDiscBillInfo(discBill);
					
				}
				AccountRequestDTO<DiscApplyInfo,DiscBillInfo> accountReq = new AccountRequestDTO<DiscApplyInfo,DiscBillInfo>();
				accountReq.setApply(apply);
				accountReq.setBillList(list);
				accountReq.setUserLogonInfo(userInfo);
				acctService.account(accountReq);
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
					retJson.setMsg("记账失败");
					retJson.setSuccess(false);
					return retJson;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				retJson.setMsg("记账失败");
				retJson.setSuccess(false);
				return retJson;
			}
			return retJson;
	}
	
	/**
	 * 贴现记账
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=doAccount")
	@ResponseBody
	public String doAccount(String ids,String discId) throws BizAppException{
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = discService.doAccount(discId,ids);
			session.endTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("票据申请提交失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据申请提交失败");
		}
		return rs > 0 ? "yes":"no";
	}
}
