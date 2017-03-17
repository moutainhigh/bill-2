/********************************************
 * 文件名称: DiscApplyController.java
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
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.FacDealResult;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.console.domain.disc.dao.DiscBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.common.ICommonService;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.DiscCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 贴现申请Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/discApplyController")
public class DiscApplyController extends BaseController {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**-------------电票系统start-------------------*/

	/**
	 * 功能描述：客户定位，电票管理页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecBillManage")
	public ModelAndView elecBillManage(String acctNo) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_apply_receive_bill");
		mv.addObject("acctNo",acctNo);
		return mv;
	}
	

	/**
	 * 付款行行号校验
	 * @param request
	 * @param user_password
	 * @param newPassword
	 * @param user_id
	 * @return AjaxJson
	 * @throws BizAppException
	 * @throws SQLException
	 */
	@RequestMapping(params="method=checkDraweeBankNo")
	@ResponseBody
	public AjaxJson checkDraweeBankNo(String draweeBankNo)throws BizAppException, SQLException {
		AjaxJson aj = new AjaxJson();
		EcdsBankData ecds;
		IDB session = DBFactory.getDB();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			session.beginTransaction();
			ecds = RcServiceFactory.getEcdsBankDataService().getEcdsBankData(draweeBankNo);
			if(ecds!=null){
				aj.setSuccess(true);
				map.put("ActorFullCall", ecds.getActorFullCall());
				map.put("Address", ecds.getAddress());
				aj.setAttributes(map);		
			}else{
				aj.setSuccess(false);
				aj.setMsg("行号不存在");
			}
			session.endTransaction();
		} catch (Exception e) {
			session.rollback();
			aj.setMsg("行号查询失败");
			aj.setSuccess(false);
			return aj;
		}
		return aj;
	}

	/**
	 * 电票接收
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=receiveAddBatch")
	public ModelAndView receiveAddBatch(String ids,DiscBillInfo bill,String custAcctNo,DiscSearchBean query) throws BizAppException{
		IProductService productService = ServiceFactory.getProductService();
		DiscApplyInfo discApply = new DiscApplyInfo();
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_apply_add_batch");
		IDiscService discService = ServiceFactory.getDiscService();
		ICommonService commonService = ServiceFactory.getCommonService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		List<Product> prodList = null;
		try {
			prodList = productService.getProductListForApplyBatch(DiscCodeConst.PARENT_PROD_NO, bill.getBillType());
			session.beginTransaction();
			mv.addObject("resultList",discService.getElectricReceiveForId(ids));
			mv.addObject("professionList",commonService.getAllProfessionInvestDirection());
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(IDict.K_BILL_TYPE.K_BANK_BILL.equals(bill.getBillType())||bill.getBillType()==IDict.K_BILL_TYPE.K_BANK_BILL){
			discApply.setProdNo("001001003");
		}else{
			discApply.setProdNo("001001006");
		}
		discApply.setPayAccountType("7");
		discApply.setCustManage(null);
		discApply.setPayAccount(null);
		mv.addObject("discApplyInfo", discApply);
		mv.addObject("prodList", prodList);
		mv.addObject("ids", ids);
		mv.addObject("orgCode", query.getOrgCode());
		mv.addObject("acctNo", custAcctNo);
		mv.addObject("rate", bill.getRate());
		mv.addObject("discDt", bill.getDiscDt());
		mv.addObject("billType", bill.getBillType());
		mv.addObject("billClass", IDict.K_BILL_CLASS.K_ELEC_BILL);
		return mv;
	}
	
	/**
	 * 电票拒接
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=noReceive")
	@ResponseBody
	public AjaxJson noReceive(String discNos) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		IDiscService discService = ServiceFactory.getDiscService();
			try {
				
				discService.ElecNoReceive(discNos);
				retJson.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				retJson.setSuccess(false);
				return retJson;
			}
			
		return retJson;
	}
	
	/**
	 * 电票待接受页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=electricReceive")
	public ModelAndView electricReceive(Page page,DiscSearchBean query,String custName,String custAcctNo,String custNos) throws BizAppException{
		page.activeCommand();
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		try {
			String[] status = StatusUtils.queryStatus("DiscApplyController", "electricReceive", null);
			query.setOperStatus(status[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query.setOperBankNo(ResourceUtil.getSessionLoginfo().getBrchBankNo());
		query.setBillClass(billClass);
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_apply_receive_bill");
		try {
			IDiscService discService = ServiceFactory.getDiscService();
			mv.addObject("resultList",discService.getElectricReceive(page,query));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("票据清单列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("acctNo", custAcctNo);
		mv.addObject("custNo", custNos);
		mv.addObject("custName", custName);
		mv.addObject("orgCode", query.getOrgCode());
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
	@RequestMapping(params = "method=saveElecBatch")
	@ResponseBody
	public AjaxJson saveElecBatch(Page page,DiscSearchBean query,DiscApplyInfo discApplyInfo,String ids,String isedit,String discIds,String batchNos) throws BizAppException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		AjaxJson retJson = new AjaxJson();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		String discId = sequenceService.getDISC_APPLY_ID();
		String batchNo = sequenceService.getDiscountApplyNo(ResourceUtil.getSessionLoginfo().getBranchNo());
		String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
		IDiscService discService = ServiceFactory.getDiscService();
        IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
				discApplyInfo.setBillClass(billClass);
				discApplyInfo.setDiscId(discId);
				discApplyInfo.setBatchNo(batchNo);
				discService.saveConditionForAddDiscApplyInfoAndModifyDiscBillInfo(ids, discApplyInfo);
				retMap.put("discId", discId);
				retMap.put("batchNo", batchNo);
				retJson.setAttributes(retMap);
				retJson.setMsg("恭喜您保存批次成功");
				retJson.setSuccess(true);
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
				retJson.setMsg("生成批次失败");
				retJson.setSuccess(false);
				return retJson;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg("生成批次失败");
			retJson.setSuccess(false);
			return retJson;
		}
		return retJson;
	}
	
	/**
	 * 保存票据后停留在原页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=saveAfterPage")
	public ModelAndView saveAfterPage(DiscSearchBean query,DiscApplyInfo discApplyInfo,String ids,String discIds,String batchNos) throws BizAppException{
		IProductService productService = ServiceFactory.getProductService();
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_apply_submit_batch");
		try {
			Product prod = productService.getProductInfoByProdNo(discApplyInfo.getProdNo());
			IDiscService discService = ServiceFactory.getDiscService();
			mv.addObject("prodName", prod.getProdName());
			mv.addObject("batch",discService.getDiscApplyInfo(discIds, query));
			mv.addObject("resultList",discService.getElectricReceiveForId(ids));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("票据清单列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		discApplyInfo.setBatchNo(batchNos);
		mv.addObject("discDt", discApplyInfo.getDiscDt());
		mv.addObject("billType", discApplyInfo.getBillType());
		mv.addObject("billClass", discApplyInfo.getBillClass());
		mv.addObject("acctNo", discApplyInfo.getCustAccount());
		mv.addObject("rate", discApplyInfo.getRate());
		mv.addObject("ids", ids);
		mv.addObject("discIds", discIds);
		mv.addObject("batchNos", batchNos);
		mv.addObject("discApplyInfo", discApplyInfo);
		mv.addObject("prodNo", discApplyInfo.getProdNo());
		return mv;
	} 
	
	/**
	 * 电票票据管理列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecBillList")
	public ModelAndView elecBillList(Page page,DiscSearchBean query) throws BizAppException{
		ICommonService commonService = ServiceFactory.getCommonService();
		List<Product> prodList = null;
		IProductService productService = ServiceFactory.getProductService();
		String acctNo = null;
		page.activeCommand();
	    try {
			String[] status = StatusUtils.queryStatus("DiscApplyController", "modifyElecBillSubmit", null);
			query.setOperStatus(status[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_apply_bill_list");
		try {
			DiscApplyInfo applyInfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			acctNo = applyInfo.getCustAccount();
			prodList = productService.getProductListForApplyBatch(DiscCodeConst.PARENT_PROD_NO, applyInfo.getBillType());
			session.beginTransaction();
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
			mv.addObject("professionList",commonService.getAllProfessionInvestDirection());
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
		mv.addObject("prodList", prodList);
		mv.addObject("acctNo", acctNo);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 显示批次列表页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecBatchList")
	public ModelAndView elecBatchList(Page page,DiscSearchBean query) throws BizAppException {
		String temp = query.getBatchNo();
		page.activeCommand();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		try {
			String[] status = StatusUtils.queryStatus("DiscApplyController", "modifyElecBillSubmit", null);
			query.setOperStatus(status[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query.setBranchNo(branchNo);
		query.setBillClass(billClass);
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_apply_batch_list");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			List<DiscApplyInfo> batchList = discService.getDiscApplyListForCondition(page,query);
			mv.addObject("batchList", batchList);
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		mv.addObject("batchNo",temp);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 修改申请岗--电票提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params="method=elecBillSubmit")
	@ResponseBody
	public AjaxJson elecBillSubmit(DiscApplyInfo applyInfo,DiscBillInfo query,String discmxIds) throws BizAppException{
		Double auditAmt = 0.0;
		AjaxJson retJson = new AjaxJson();                       
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IDiscService discService = ServiceFactory.getDiscService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		try {
			session.beginTransaction();
			DiscApplyInfo discApplyInfo = discService.getDiscApplyInfo(query.getDiscId(), null);
			List<DiscBillInfo> list = discService.getElectricReceiveForId(discmxIds);
			//额度扣减
			FacDealResult result = ServiceFactory.getDiscFacService().dealFac(discApplyInfo, list, user, CommonConst.FAC_OPER_OCCUPY);
			result.checkFacResult(CommonConst.FAC_OPER_OCCUPY);
//			ServiceFactory.getFmsAgentService().acceptorBankCredit(discApplyInfo, CommonConst.FAC_SOURCE_DISC, list, user);
			//更新清单信息
			for(int i = 0 ;i<list.size();i++){
				DiscBillInfo discBill=list.get(i);
				if(DiscCodeConst.IS_CANCEL_TRUE.equals(discBill.getIsCancel())){
					retJson.setMsg("对方已经撤销申请,票号为"+discBill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}else if(" ".equals(discBill.getGaleDate())||discBill.getGaleDate()==null){
					retJson.setMsg("请先进行计算利息再进行提交");
					retJson.setSuccess(false);
					return retJson;
				}else{
					auditAmt += discBill.getBillMoney();
				    String status= discBill.getOperStatus();
				    String afterStatus= StatusUtils.handleStatus("DiscApplyController", "modifyElecBillSubmit", null, status);
				    discBill.setOperStatus(afterStatus);
				    discBill.setApplyOperNo(user.getUserNo());
				    discBill.setApplyCommitDate(DateTimeUtil.getWorkdayString());
				    discBill.setApplyCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				    discService.modDiscBillInfo(discBill);
				    discService.getCheckApprove(discmxIds, auditAmt);
				}
			}
			//更新批次信息
			discApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
			discService.modifyElecDiscApplyInfo(discApplyInfo);
			session.endTransaction();
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
	 * 提交批次--电票贴现申请提交
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=submitElecBatch")
	@ResponseBody
	public AjaxJson submitElecBatch(Page page,DiscSearchBean query,DiscApplyInfo discApplyInfo,String ids,String isedit,String discIds) throws BizAppException {	
		Map<String, Object> retMap = new HashMap<String, Object>();
		AjaxJson retJson = new AjaxJson();
		String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
		discApplyInfo.setBillClass(billClass);
		Double auditAmt = 0.0;
		IDiscService discService = ServiceFactory.getDiscService();
        IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			UserLoginfo user = ResourceUtil.getSessionLoginfo();
			List<DiscBillInfo> list=discService.getElectricReceiveForId(ids);
			for(int i = 0 ;i<list.size();i++){
				DiscBillInfo discBill=list.get(i);
				if(DiscCodeConst.IS_CANCEL_TRUE.equals(discBill.getIsCancel())||discBill.getIsCancel()==DiscCodeConst.IS_CANCEL_TRUE){
					retJson.setMsg("对方已经撤销申请,票号为"+discBill.getBillNo());
					retJson.setSuccess(false);
					return retJson;
				}else if(" ".equals(discBill.getGaleDate())||discBill.getGaleDate()==null){
					retJson.setMsg("请先进行计算利息再进行提交");
					retJson.setSuccess(false);
					return retJson;
				}
			}
			DiscApplyInfo ApplyInfo = discService.getDiscApplyInfo(discIds, null);
			//额度扣减
			FacDealResult result = ServiceFactory.getDiscFacService().dealFac(ApplyInfo, list, user, CommonConst.FAC_OPER_OCCUPY);
			result.checkFacResult(CommonConst.FAC_OPER_OCCUPY);
			//更新清单信息
			for(int i = 0 ;i<list.size();i++){
				DiscBillInfo discBill=list.get(i);
				auditAmt += discBill.getBillMoney();
				String status= discBill.getOperStatus();
				String afterStatus= StatusUtils.handleStatus("DiscApplyController", "modifyElecBillSubmit", null, status);
				discBill.setOperStatus(afterStatus);
				discBill.setApplyOperNo(user.getUserNo());
				discBill.setApplyCommitDate(DateTimeUtil.getWorkdayString());
				discBill.setApplyCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				discService.modDiscBillInfo(discBill);
				discService.getCheckApprove(ids, auditAmt);
				retMap.put("orgCode", discBill.getOrgCode());
			}
			ApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
			discService.modifyElecDiscApplyInfo(ApplyInfo);
			retJson.setAttributes(retMap);
			retJson.setSuccess(true);
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
				retJson.setMsg("生成批次失败");
				retJson.setSuccess(false);
				return retJson;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg(e.getMessage());
			retJson.setSuccess(false);
			return retJson;
		}
		return retJson;
	}
	
	/**
	 * 电票删除
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params="method=delElecBill")
	@ResponseBody
	public AjaxJson delElecBill(DiscBillInfo query,String discmxIds) throws BizAppException{
		Map<String, Object> retMap = new HashMap<String, Object>();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo(); 
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		AjaxJson retJson = new AjaxJson();                       
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			String curStatus=StatusUtils.queryStatus("DiscApplyController", "delElecBill", null)[0];
			session.beginTransaction();
			List<DiscBillInfo> list = discService.getElectricReceiveForId(discmxIds);
			for(int i = 0 ;i<list.size();i++){
				DiscBillInfo discBill=list.get(i);
				String status= discBill.getOperStatus();
				String afterStatus= StatusUtils.handleStatus("DiscApplyController", "delElecBill", null, status);
				discBill.setOperStatus(afterStatus);
				discBill.setGaleDate(null);
				discBill.setLocalPayMoney(0);
				discBill.setInterestDays(null);
				discBill.setInterest(0);
				discService.modDiscBillInfo(discBill);
		    }
			query.setBranchNo(branchNo);
			query.setBillClass(billClass);
			query.setOperStatus(curStatus);
			int i = discService.totalCount(query);
			if(i<=0){
				DiscApplyInfo discApplyInfo = discService.getDiscApplyInfo(query.getDiscId(), null);
				discApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
				discService.modifyElecDiscApplyInfo(discApplyInfo);
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
	 * 电票增加页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=addElecBill")
	public ModelAndView addElecBill(Page page,DiscSearchBean query,String discIds,String custAccount) throws BizAppException{
		page.activeCommand();
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		ICustInfoAcctService custInfoAcctService = ServiceFactory.getCustInfoAcctService();
		String operBankNo= ResourceUtil.getSessionLoginfo().getBrchBankNo();
		query.setOperBankNo(operBankNo);
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		query.setBillClass(billClass);
	    try {
	    	CustInfoAcct custInfoAcct = custInfoAcctService.getParam(custAccount);
	    	CustInfo custInfo = custInfoService.getParam(custInfoAcct.getCustNo());
	    	//query.setCustNo(custInfoAcct.getCustNo());
	    	query.setOrgCode(custInfo.getOrgCode());
			String[] status = StatusUtils.queryStatus("DiscApplyController", "electricReceive", null);
			query.setOperStatus(status[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_apply_add_bill");
		try {
			session.beginTransaction();
			mv.addObject("resultList",discService.getDiscElecBillListForBatch(page,query));
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
		mv.addObject("custAccount", custAccount);
		mv.addObject("query", query);
		mv.addObject("discIds", discIds);
		return mv;
	}
	
	/**
	 * 在一个批次里新增电票提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params="method=doFinalAdd")
	@ResponseBody
	public AjaxJson doFinalAdd(HttpServletRequest request,
			HttpServletResponse response,DiscBillInfo discBill) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		String discId = request.getParameter("discId");
		String bill_ids = request.getParameter("bill_ids");
		
		String[] ids = CommUtils.couvertLong(bill_ids);
		IDB session = DBFactory.getDB(); // 获取数据库连接
		for(String s : ids){
			IDiscService discService = ServiceFactory.getDiscService();
			try {
				session.beginTransaction();
				List<DiscBillInfo> list= discService.getElectricReceiveForId(s);
				String status= list.get(0).getOperStatus();
				String afterStatus= StatusUtils.handleStatus("DiscApplyController", "receiveAddBatch", null, status);
				discBill.setDiscId(discId);
				discBill.setDiscmxId(s);
				discBill.setOperStatus(afterStatus);
				discService.modDiscBillInfo(discBill);
				session.endTransaction();
			} catch (SQLException e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				CommonLog.getCommonLogCache().errorMessage("电票新增失败");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return retJson;
	}
	
	/**
	 * 校验接收时所选的票据是否统一利率,类型和贴现日
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params="method=checkElecBill")
	@ResponseBody
	public AjaxJson checkElecBill(HttpServletRequest request,
			HttpServletResponse response,DiscBillInfo discBill) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		String ids = request.getParameter("ids");
		Map<String, Object> retMap = new HashMap<String, Object>();
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			List<DiscBillInfo> list=discService.getElectricReceiveForId(ids);
			Double rate=list.get(0).getRate();
			String discDt=list.get(0).getDiscDt();
			String billType=list.get(0).getBillType();
			if(list.size()==1){
				retMap.put("rate", rate);
				retMap.put("discDt", discDt);
				retMap.put("billType", billType);
				retMap.put("orgCode", list.get(0).getOrgCode());
				retJson.setAttributes(retMap);
				retJson.setSuccess(true);
			}else{
				for(int i = 0;i<list.size();i++){
					if(rate.equals(list.get(i).getRate())&&discDt.equals(list.get(i).getDiscDt())&&billType.equals(list.get(i).getBillType())){
						retMap.put("rate", rate);
						retMap.put("discDt", discDt);
						retMap.put("billType", billType);
						retMap.put("orgCode", list.get(0).getOrgCode());
						retJson.setAttributes(retMap);
						retJson.setSuccess(true);
					}else if(!rate.equals(list.get(i).getRate())&&!discDt.equals(list.get(i).getDiscDt())&&!billType.equals(list.get(i).getBillType())){
						retJson.setMsg("请选择贴现日,利率和票据类型都要统一的电票");
						retJson.setSuccess(false);
						return retJson;
						
					}else if(!rate.equals(list.get(i).getRate())&&discDt.equals(list.get(i).getDiscDt())&&billType.equals(list.get(i).getBillType())){
						retJson.setMsg("您所选择的电票利率没有统一");
						retJson.setSuccess(false);
						return retJson;
					}else if(!rate.equals(list.get(i).getRate())&&!discDt.equals(list.get(i).getDiscDt())&&billType.equals(list.get(i).getBillType())){
						retJson.setMsg("您所选择的电票利率和贴现日没有统一");
						retJson.setSuccess(false);
						return retJson;
					}else if(rate.equals(list.get(i).getRate())&&!discDt.equals(list.get(i).getDiscDt())&&billType.equals(list.get(i).getBillType())){
						retJson.setMsg("您所选择的电票贴现日没有统一");
						retJson.setSuccess(false);
						return retJson;
					}else if(rate.equals(list.get(i).getRate())&&!discDt.equals(list.get(i).getDiscDt())&&!billType.equals(list.get(i).getBillType())){
						retJson.setMsg("您所选择的电票贴现日和票据类型没有统一");
						retJson.setSuccess(false);
						return retJson;
					}else if(!rate.equals(list.get(i).getRate())&&discDt.equals(list.get(i).getDiscDt())&&!billType.equals(list.get(i).getBillType())){
						retJson.setMsg("您所选择的电票利率和票据类型没有统一");
						retJson.setSuccess(false);
						return retJson;
					}else{
						retJson.setMsg("您所选择的票据类型没有统一");
						retJson.setSuccess(false);
						return retJson;
					}
					
				}
			}
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询电票失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		
		return retJson;
	}
	
	/**
	 * 新增票据
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=saveAddElecBill")
	@ResponseBody
	public AjaxJson saveAddElecBill(Page page,DiscSearchBean query) throws BizAppException {	
		AjaxJson retJson = new AjaxJson();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		ICustInfoAcctService custInfoAcctService = ServiceFactory.getCustInfoAcctService();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			CustInfoAcct custInfoAcct = custInfoAcctService.getParam(query.getAcctNo());
			List<DiscBillInfo> list = discService.getElectricReceiveForId(query.getDiscmxId());
			for(int i = 0 ;i<list.size();i++){
				DiscBillInfo discBill=list.get(i);
				String status= list.get(i).getOperStatus();
				String afterStatus= StatusUtils.handleStatus("DiscApplyController", "addBill", null, status);
				discBill.setOperStatus(afterStatus);
				discBill.setDiscId(query.getDiscId());
				discBill.setCustAccount(query.getAcctNo());
				discBill.setCustNo(custInfoAcct.getCustNo());
				discBill.setBranchNo(branchNo);
				discBill.setApplyCommitDate(DateTimeUtil.getWorkdayString());
				discBill.setApplyCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				discService.modDiscBillInfo(discBill);
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
	 * 待撤销申请批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelElecBatch")
	public ModelAndView cancelElecBatch(Page page,DiscSearchBean query) throws BizAppException{
		String temp = query.getBatchNo();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		query.setBillClass(billClass);
		query.setBranchNo(branchNo);
		//query.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
		query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_cancel_apply_batch_list");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			page.activeCommand();
			query.setOperStatus(StatusUtils.queryStatus("DiscApplyController", "cancelElecApply", null)[0]);
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
	 * 待撤销票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelBillList")
	public ModelAndView cancelBillList(Page page,DiscSearchBean query) throws BizAppException{
		IProductService productService = ServiceFactory.getProductService();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_cancel_apply_bill_list");
		try {
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			DiscApplyInfo applyInfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			Product prod = productService.getProductInfoByProdNo(applyInfo.getProdNo());
			page.activeCommand();
			session.beginTransaction();
			query.setOperStatus(StatusUtils.queryStatus("DiscApplyController", "cancelElecApply", null)[0]);
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
	 * 电票贴现申请撤销
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params="method=doCancelApply")
	@ResponseBody
	public AjaxJson doCancelApply(DiscBillInfo query,String discmxIds) throws BizAppException{
		Map<String, Object> retMap = new HashMap<String, Object>();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo(); 
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		AjaxJson retJson = new AjaxJson();                       
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IDiscService discService = ServiceFactory.getDiscService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		try {
			String curStatus=StatusUtils.queryStatus("DiscApplyController", "cancelElecApply", null)[0];
			session.beginTransaction();
			List<DiscBillInfo> list = discService.getElectricReceiveForId(discmxIds);
			DiscApplyInfo discApplyInfo = discService.getDiscApplyInfo(query.getDiscId(), null);
			//额度释放
			FacDealResult result = ServiceFactory.getDiscFacService().dealFac(discApplyInfo, list, user, CommonConst.FAC_OPER_RELEASE);
			result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
//			ServiceFactory.getFmsAgentService().facReleaseByBillBatch(discApplyInfo, list, user);
			//更新清单信息
			for(int i = 0 ;i<list.size();i++){
				DiscBillInfo discBill=list.get(i);
				String status= discBill.getOperStatus();
				String afterStatus= StatusUtils.handleStatus("DiscApplyController", "cancelElecApply", null, status);
				discBill.setOperStatus(afterStatus);
				discBill.setApplyOperNo(user.getUserNo());
				discService.modDiscBillInfo(discBill);
			}
			query.setBranchNo(branchNo);
			query.setBillClass(billClass);
			query.setOperStatus(curStatus);
			int i = discService.totalCount(query);
			if(i<=0){
				discApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
				discService.modifyElecDiscApplyInfo(discApplyInfo);
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
	 * 去电票利息试算页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toElecInterestTrial")
	public ModelAndView toElecInterestTrial(String ids,String discId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("busi/disc/electronic/disc_elec_interest_trial");
		mv.addObject("ids", ids);
		mv.addObject("discId", discId);
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
	@RequestMapping(params = "method=elecInterestTrial")
	public ModelAndView elecInterestTrial(DiscBillInfo discBill,String ids) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			if("".equals(ids)||ids == null){
				rs = discService.elecSubmitInterestTrial(discBill);
			}else{
				rs = discService.interestTrial(discBill,ids);
			}
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
	 * 修改岗翻页与利息符合后反显值
	 * @param page,query,batch,batchNos
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=afterPage")
	public ModelAndView afterPage(Page page,DiscSearchBean query,DiscApplyInfo batch) throws BizAppException{
		ICommonService commonService = ServiceFactory.getCommonService();
		List<Product> prodList = null;
		IProductService productService = ServiceFactory.getProductService();
		String acctNo = null;
		page.activeCommand();
	    try {
			String[] status = StatusUtils.queryStatus("DiscApplyController", "modifyElecBillSubmit", null);
			query.setOperStatus(status[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/electronic/disc_elec_apply_bill_list");
		try {
			DiscApplyInfo applyInfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			acctNo = applyInfo.getCustAccount();
			prodList = productService.getProductListForApplyBatch(DiscCodeConst.PARENT_PROD_NO, applyInfo.getBillType());
			session.beginTransaction();
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
			mv.addObject("professionList",commonService.getAllProfessionInvestDirection());
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
		mv.addObject("prodList", prodList);
		mv.addObject("acctNo", acctNo);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 电票修改岗利息复核修改批次信息
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params="method=trialUpdateBatch")
	@ResponseBody
	public AjaxJson trialUpdateBatch(DiscApplyInfo applyInfo,DiscBillInfo query) throws BizAppException{
		AjaxJson retJson = new AjaxJson();                       
		IDB session = DBFactory.getDB(); // 获取数据库连接
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			session.beginTransaction();
			DiscApplyInfo apply = discService.getDiscApplyInfo(query.getDiscId(), null);
			if(DiscCodeConst.PAY_TYPE_BUYER.equals(applyInfo.getPayType())||applyInfo.getPayType()==DiscCodeConst.PAY_TYPE_BUYER){
				apply.setPayAccountType(applyInfo.getPayAccountType());
				apply.setPayAccount(applyInfo.getPayAccount());
				apply.setPayCustName(applyInfo.getPayCustName());
				apply.setBuyPayRate(0);
			}else if(DiscCodeConst.PAY_TYPE_AGREEMENT.equals(applyInfo.getPayType())||applyInfo.getPayType()==DiscCodeConst.PAY_TYPE_AGREEMENT){
				apply.setPayAccountType(applyInfo.getPayAccountType());
				apply.setPayAccount(applyInfo.getPayAccount());
				apply.setPayCustName(applyInfo.getPayCustName());
				apply.setBuyPayRate(applyInfo.getBuyPayRate());
			}else{
				apply.setPayAccountType(null);
				apply.setPayAccount(null);
				apply.setPayCustName(null);
				apply.setBuyPayRate(0);
			}
			apply.setPayType(applyInfo.getPayType());
			apply.setProdNo(applyInfo.getProdNo());
			apply.setCbRate(applyInfo.getCbRate());
			apply.setProfessionName(applyInfo.getProfessionName());
			apply.setCustManage(applyInfo.getCustManage());
			apply.setCustManagerName(applyInfo.getCustManagerName());
			apply.setDeptName(applyInfo.getDeptName());
			discService.modifyElecDiscApplyInfo(apply);
			session.endTransaction();
			retJson.setSuccess(true);
		} catch (SQLException e) {
			try {
				session.rollback();
				retJson.setMsg("修改批次失败");
				retJson.setSuccess(false);
				return retJson;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retJson.setMsg("修改批次失败");
			retJson.setSuccess(false);
			return retJson;
		}
		return retJson;
	}
	/**-------------电票系统end-------------------*/
	
	
	/**-------------纸票系统start-------------------*/
	
	/**
	 * 去利息试算页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toInterestTrial")
	public ModelAndView toInterestTrial(String ids,String discId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("busi/disc/entity/disc_interest_trial");
		mv.addObject("ids", ids);
		mv.addObject("discId", discId);
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
	public ModelAndView interestTrial(DiscBillInfo discBill,String ids) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			int rs = discService.interestTrial(discBill,ids);
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
	 * 删除票据
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=delBill")
	public ModelAndView delBill(Page page,DiscSearchBean query,String ids) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_apply_detail_list");
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			discService.delBill(ids);
			query.setOpers(StatusUtils.queryStatus("DiscApplyController", "billManage", null));
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
			mv.addObject("query",query);
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
	 * 新增票据页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=saveBill")
	public ModelAndView saveBill(DiscBillInfo bill,String billId,String isedit,String batchId) throws BizAppException{
		IDiscService discService = ServiceFactory.getDiscService();
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			bill.setDiscId(batchId);
		
			if (isedit.equals("1")){   //编辑操作
				DiscBillInfo discbillinfo = discService.getDiscBillInfo(billId);
				discbillinfo.setIssueDt(bill.getIssueDt());
				discbillinfo.setBillNo(bill.getBillNo());
				discbillinfo.setRemitter(bill.getRemitter());
				discbillinfo.setPayee(bill.getPayee());
				discbillinfo.setRemitterAcct(bill.getRemitterAcct());
				discbillinfo.setPayeeAcct(bill.getPayeeAcct());
				discbillinfo.setRemitterBankName(bill.getRemitterBankName());
				discbillinfo.setPayeeBankName(bill.getPayeeBankName());
				discbillinfo.setBillMoney(bill.getBillMoney());
				discbillinfo.setDueDt(bill.getDueDt());
				discbillinfo.setRemitterBankNo(bill.getRemitterBankNo());
				discbillinfo.setAcceptorBankNo(bill.getRemitterBankNo());
				discbillinfo.setDraweeBankNo(bill.getRemitterBankNo());
				discbillinfo.setProtocalNo(bill.getProtocalNo());
				discbillinfo.setDraweeAddr(bill.getDraweeAddr());
				discbillinfo.setAcceptorBankName(bill.getAcceptorBankName());
				discbillinfo.setIsSameCity(bill.getIsSameCity());
				discbillinfo.setRemark(bill.getRemark());
				discbillinfo.setBillBeforeOwner(bill.getBillBeforeOwner());
				discbillinfo.setDiscId(batchId);
				discbillinfo.setGaleDate(" ");
				discbillinfo.setPayMoney(0.0);
				discbillinfo.setInterestDays(0l);
				discbillinfo.setSalerInterest(0.0);
				discbillinfo.setDelayType(" ");
				discbillinfo.setDelayDays(0l);
				discbillinfo.setBuyerInterest(0.0);
				discbillinfo.setTotalIntrstPayment(0.0);
				discService.modDiscBillInfo(discbillinfo);
			}else{
				discService.addDiscBillInfo(bill); //插入
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
	 * 撤销申请
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelApply")
	@ResponseBody
	public AjaxJson cancelApply(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = discService.cancel("DiscApplyController","cancelApply",ids);
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
	 * 待撤销申请的清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelApplyDetailList")
	public ModelAndView cancelApplyDetailList(Page page,DiscSearchBean query) throws BizAppException{
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_cancel_apply_detail_list");
		try {
			page.activeCommand();
			session.beginTransaction();
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setOpers(StatusUtils.queryStatus("DiscApplyController", "cancelApply", null));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
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
			CommonLog.getCommonLogCache().errorMessage("待撤销申请的清单列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销申请的清单列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}
	/**
	 * 待撤销申请的批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelApplyList")
	public ModelAndView cancelApplyList(Page page,DiscSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_cancel_apply_list");
		IDiscService discService = ServiceFactory.getDiscService();
		AjaxJson retJson = new AjaxJson();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());
			query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			String BatchNo=query.getBatchNo();
			query.setOperStatus(StatusUtils.queryStatus("DiscApplyController", "cancelApply", null)[0]);
			query.setBillClass(DiscCodeConst.BILL_CLASS_ENTITY);
			mv.addObject("batchList", discService.getDiscApplyListForCondition(page,query));
			query.setBatchNo(BatchNo);
		} catch (Exception e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("待撤销申请批次列表");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待撤销申请批次列表");
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	/**
	 * 票据提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=apply")
	public ModelAndView apply(String ids, Page page, DiscSearchBean query, String custName) throws BizAppException{
		ModelAndView mv = null;
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		int rs = 0;
		try {
			session.beginTransaction();
			rs = discService.applySubmit(ids);
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
			mv=this.searchBatch(page, query, custName);
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
	public ModelAndView toAddBill(String billType,String discId,String action) throws BizAppException{
		ModelAndView mv = null;
		//1银票 2商票
		if("1".equals(billType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			mv.addObject("batch",discService.getDiscApplyInfo(discId,null));
			mv.addObject("batchId",discId);
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("action",action);
		
		mv.addObject("billType",billType);
		mv.addObject("isedit","0");
		return mv;
	}
	/**
	 * 修改批次信息判断是否能修改
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toEditBatchCheck")
	@ResponseBody
	public AjaxJson toEditBatchCheck(Page page,DiscSearchBean query) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB();
		try{
			query.setOpers(StatusUtils.queryStatus("DiscApplyController", "toEditBatch", null));
			List<DiscBillInfo> billList=discService.getDiscBillListForBatchStatus(page, query);
			if(billList==null || billList.size()<=0){
				retJson.setSuccess(true);
				return retJson;
			}else{
				retJson.setSuccess(false);
				session.rollback();
			}
			
		} catch (Exception e){
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库查询失败");
		}
		return retJson;
	}
	/**
	 * 修改票据页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=toEditBill")
	public ModelAndView toEditBill(String billType,String discmxId,String action) throws BizAppException{
		ModelAndView mv = null;
		//1银票 2商票
		if("1".equals(billType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		IDiscService discService = ServiceFactory.getDiscService();
		DiscBillInfo bill = discService.getDiscBillInfo(discmxId);
		mv.addObject("bill",bill);
		mv.addObject("batchId",bill.getDiscId());
		mv.addObject("action",action);
		mv.addObject("billId",discmxId);
		mv.addObject("isedit","1");
		return mv;
	}
	/**
	 * 清空票据利息等信息
	 * @param 
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=updateBillInfo")
	@ResponseBody
	public AjaxJson updateBillInfo(DiscSearchBean query)throws BizAppException{
		IDiscService discService = ServiceFactory.getDiscService();
		AjaxJson retJson = new AjaxJson();
		try {
			IDB session = DBFactory.getDB();
			query.setOpers(StatusUtils.queryStatus("DiscApplyController", "billManage", null));
			session.beginTransaction();
			discService.modifyBill(query);
			session.endTransaction();
		}catch (Exception e1){
			retJson.setSuccess(false);
			
			  CommonLog.getCommonLogCache().errorMessage("票据信息更新失败");
			  throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据信息更新失败");
		}
		return retJson;
	}

	
	/**
	 * 票据管理页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=billManage")
	public ModelAndView billManage(Page page,DiscSearchBean query,String custName) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_apply_detail_list");
		try {
			page.activeCommand();
			IDiscService discService = ServiceFactory.getDiscService();
			query.setOpers(StatusUtils.queryStatus("DiscApplyController", "billManage", null));
			DiscApplyInfo disapplyinfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(disapplyinfo.getProdNo());
			DiscApplyInfo  discapplyinfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			List<DiscBillInfo> discbillinfo= discService.getDiscBillListForBatch(page,query);
			String ProdName = prod.getProdName();
			mv.addObject("batch",discapplyinfo);
			mv.addObject("resultList",discbillinfo);
			mv.addObject("ProdName",ProdName);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		mv.addObject("custName", custName);
		return mv;
	}
	/**
	 * 修改批次信息
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toEditBatch")
	public ModelAndView toEditBatch(String discId,Page page,DiscSearchBean query,String custAcctNo) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		DiscApplyInfo discApplyInfo = new DiscApplyInfo();
		IDiscService discService = ServiceFactory.getDiscService();
		IProductService productService = ServiceFactory.getProductService();
		List<Product> prodList = null;
		try {
			query.setDiscId(discId);
			query.setOpers(StatusUtils.queryStatus("DiscApplyController", "toEditBatch", null));
			List<DiscBillInfo> billList=discService.getDiscBillListForBatchStatus(page, query);
			if(billList==null || billList.size()<=0){
				discApplyInfo = discService.getDiscApplyInfo(discId,query);
			}else{
					throw new Exception("该批次还有票据没有处理，不能修改");
			}
			
		}catch (Exception e) {
			CommonLog.getCommonLogCache().infoMessage("该批次还有票据没有处理，不能修改");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "该批次还有票据没有处理，不能修改");
		}
		ICommonService CommonService = ServiceFactory.getCommonService();
		try {
			mv.addObject("resultList",CommonService.getAllProfessionInvestDirection());
			prodList = productService.getProductListForApplyBatch(DiscCodeConst.PARENT_PROD_NO,discApplyInfo.getBillType());
			mv.addObject("prodList", prodList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.setViewName("busi/disc/entity/disc_editBatch");
		mv.addObject("discApplyInfo", discApplyInfo);
		mv.addObject("query", query);
		mv.addObject("custAccount", custAcctNo);
		mv.addObject("isedit", "1");
		return mv;
	}
	
	/**
	 * 新增批次
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=toAddBatch")
	public ModelAndView toAddBatch(HttpServletRequest request,
			HttpServletResponse response,String billType,String custAcctNo) {
		IProductService productService = ServiceFactory.getProductService();
		List<Product> prodList;
		ModelAndView mv = new ModelAndView();
		try {
			String date= DateTimeUtil.getWorkdayString();
			prodList = productService.getProductListForApplyBatch(DiscCodeConst.PARENT_PROD_NO);
			mv.addObject("prodList", prodList);
			mv.addObject("date", date);
			mv.addObject("custAccount",custAcctNo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ICommonService CommonService = ServiceFactory.getCommonService();
		try {
			
			mv.addObject("resultList",CommonService.getAllProfessionInvestDirection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.setViewName("busi/disc/entity/disc_editBatch");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	
	/**
	 * 功能描述：客户定位，批次管理页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=batchManage")
	public ModelAndView batchManage(String acctNo) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_apply_batch_list");
		mv.addObject("acctNo",acctNo);
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
	@RequestMapping(params = "method=searchBatch")
	public ModelAndView searchBatch(Page page,DiscSearchBean query,String custName) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_apply_batch_list");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			page.activeCommand();
			mv.addObject("resultList",discService.getDiscApplyListForApply(page,query));
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
	 * 保存批次
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=saveBatch")
	public ModelAndView saveBatch(DiscApplyInfo discApplyInfo,String isedit) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		IDiscService discService = ServiceFactory.getDiscService();
        IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				discService.modifyDiscApplyInfo(discApplyInfo);
			}else{
				discService.addDiscApplyInfo(discApplyInfo);  //插入
			}
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	
	/**
	 * 删除批次
	 */
	@RequestMapping(params="method=delBatch")
	@ResponseBody
	public AjaxJson delBatch(Page page,String discId) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		IDiscService discService = ServiceFactory.getDiscService();
		IDB session = DBFactory.getDB();
		try{
			session.beginTransaction();
			boolean bool = discService.delApplyInfoForDiscIds(page,StringUtils.split(discId));
			retJson.setSuccess(bool);
			if(bool){
				retJson.setMsg("批次信息删除成功！");
				session.endTransaction();
			}else{
				retJson.setMsg("包含票据信息的批次不允许删除！");
				session.rollback();
			}
		} catch (Exception e){
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
	public AjaxJson searchProdinfo(String billType){
		AjaxJson retJson = new AjaxJson();
		// 获取产品信息
		IProductService productService = ServiceFactory.getProductService();
		try {
			retJson.setObj(productService.getProductListForApplyBatch(DiscCodeConst.PARENT_PROD_NO, billType));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retJson;
	}
	/**
	 * 查询行业投向信息
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=searchProfidinfo")
	@ResponseBody
	public ModelAndView searchProfessionInvestDirectioninfo(){
		ModelAndView mv = new ModelAndView();
		// 获取产品信息
		ICommonService CommonService = ServiceFactory.getCommonService();
		try {
			mv.addObject("resultList",CommonService.getAllProfessionInvestDirection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 批次查看
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=goBatchInfo")
	public ModelAndView goBatchInfo(String discId,DiscSearchBean query){
		DiscApplyInfo discApplyInfo = new DiscApplyInfo();
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			discApplyInfo = discService.getDiscApplyInfo(discId,query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_batch_detail");
		mv.addObject("discApplyInfo", discApplyInfo);
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * 跳转到复制录入页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=toCopyBill")
	public ModelAndView toCopyBill(String billType,String discId,String discmxId) throws BizAppException{
		ModelAndView mv = null;
		//1银票 2商票
		if("1".equals(billType))
			mv = new ModelAndView("busi/common/bank-fund");
		else
			mv = new ModelAndView("busi/common/commerce-fund");
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			mv.addObject("batch",discService.getDiscApplyInfo(discId,null));
			mv.addObject("bill",discService.getDiscBillInfo(discmxId));
			mv.addObject("batchId",discId);
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("action","discApplyController.do?method=saveBill");
		
		mv.addObject("billType",billType);
		mv.addObject("copyadd","1");//标识为复制录入
		return mv;
	}
	/**是否已经计算利息 没计算返回false
	 * @throws BizAppException */
	@RequestMapping(params="method=isInterestTrial")
	@ResponseBody
	public AjaxJson isInterestTrial(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		DiscBillInfoDao discBillDao = new DiscBillInfoDao();
		try {
			if(!discBillDao.isInterestTrial(ids)){
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
	
}
