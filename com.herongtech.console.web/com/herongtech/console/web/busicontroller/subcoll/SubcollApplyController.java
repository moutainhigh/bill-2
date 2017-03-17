/********************************************
 * 文件名称: SubcollApplyController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-25 下午02:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.busicontroller.subcoll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import com.herongtech.console.domain.subcoll.bean.SubcollApplyInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollQueryCondition;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.subcoll.ISubcollService;
import com.herongtech.console.web.busicontroller.common.SubcollCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.rc.service.rcservice.IRcPresentationService;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 托收申请Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/subcollApplyController")
public class SubcollApplyController extends BaseController {
									/**-------------纸票系统start-------------------*/
	/**
	 * 纸票申请岗管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=billManage")
	public ModelAndView subcollManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/bill_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		if(query == null){
			query = new SubcollQueryCondition();
		}
		String billNo = query.getBillNo();
		String acceptor = query.getAcceptor();
		String billClass = IDict.K_BILL_CLASS.K_ENTY_BILL;
		String busiDate = DateTimeUtil.getWorkdayString();
        String startDay = null;
        String endDay = null;
        String billType = null;
        if(StringUtils.isBlank(query.getBillType())){
        	billType = (IDict.K_BILL_TYPE.K_BANK_BILL);
        	query.setBillType(billType);
		}else{
			billType = query.getBillType();
		}
        if(StringUtils.isBlank(query.getStartDay())){
        	startDay = DateTimeUtil.getDate(busiDate, -10);
        	query.setStartDay(startDay);
        }else{
        	startDay = query.getStartDay();
        }
        if(StringUtils.isBlank(query.getEndDay())){
        	endDay = DateTimeUtil.getDate(busiDate, 10);
        	query.setEndDay(endDay);
        }else {
			endDay = query.getEndDay();
		}
		try {
			query.setBillClass(billClass);
			mv.addObject("resultList",subcollService.getSubcollBillForPrint(page, query));
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("billClass", billClass);
		mv.addObject("billType", billType);
		mv.addObject("startDay", startDay);
		mv.addObject("endDay", endDay);
		mv.addObject("page", page);
		mv.addObject("bigMoney", query.getBigMoney());
		mv.addObject("smallMoney", query.getSmallMoney());
		mv.addObject("acceptor", acceptor);
		mv.addObject("billNo", billNo);
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * 纸票录入批次信息
	 * 1、主要是EMS，其余信息跟着显示
	 * @param ids
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=inputs")
	public ModelAndView inputs(String ids,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/luru");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		IEcdsBankDataService ecdsBankDataService = ServiceFactory.getEcdsBankDataService();
		String [] idss =CommUtils.couvertLong(ids);
		String toBankName = null;
		String toBankNo = null;
		String toBankAddress = null;
		String fromBankName = ResourceUtil.getSessionLoginfo().getBranchName();
		String fromBankNo = ResourceUtil.getSessionLoginfo().getBrchBankNo();
		for (int i = 0; i < idss.length; i++) {
			String id = idss[i];
			RgctBill rgctBill = subcollService.goDetail(id);
			RgctBillInfo info = rgctBill.getInfo();
			toBankName = info.getAcceptorBankName();
			toBankNo = info.getAcceptorBankNo();
			EcdsBankData ebd = ecdsBankDataService.getEcdsBankData(info.getAcceptorBankNo());
			if(ebd==null){
				ebd = new EcdsBankData();
			}
			toBankAddress = ebd.getAddress();
		}
		query.setFromBankName(fromBankName);
		query.setFromBankNo(fromBankNo);
		query.setToBankAddress(toBankAddress);
		query.setToBankName(toBankName);
		query.setToBankNo(toBankNo);
		query.setInAcctNo("0");
		query.setInBankNo(toBankNo);
		mv.addObject("query", query);
		mv.addObject("ids", ids);
		mv.addObject("isedit", "1");
		return mv;
	}
	
	
	/**
	 * 纸票录入批次信息传值跳转
	 * 方法说明：此方法用于纸票申请岗在进入批次信息录入之后，进行用AjaxJson传值,然后跳转到nextView(),避免在本页面进行显示
	 * @param ids
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=valueToJump")
	@ResponseBody
	public AjaxJson valueToJump(String ids,SubcollQueryCondition query) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		aj.setSuccess(true);
		return aj;
	}
	
	/**
	 * 纸票管理页面票据管理
	 * @param ids
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=nextView")
	public ModelAndView nextView(String ids,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/bill_next");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		List<RgctBillInfo> list=new ArrayList<RgctBillInfo>();
		String billClass = null;
		String billType = null;
		Double totalMoney = 0.0;
		String toBankName = null;
		String toBankNo = null;
		String fromBankName = ResourceUtil.getSessionLoginfo().getBranchName();
		String [] idss =CommUtils.couvertLong(ids);
		int totalNum = idss.length;
		for (int i = 0; i < idss.length; i++) {
			String id = idss[i];
			RgctBill rgctBill = subcollService.goDetail(id);
			RgctBillInfo info = rgctBill.getInfo();
			list.add(info);
			billClass = info.getBillClass();
			billType = info.getBillType();
			totalMoney += info.getBillMoney();
			toBankName = info.getAcceptorBankName();
			toBankNo = info.getAcceptorBankNo();
		}
		Page page  = new Page();
		page.setTotalResult(totalNum);
		page.activeCommand();
		query.setFromBankName(fromBankName);
		query.setToBankNo(toBankNo);
		query.setToBankName(toBankName);
		mv.addObject("page",page);
		mv.addObject("ids",ids);
		mv.addObject("resultList",list);
		mv.addObject("billClass", billClass);
		mv.addObject("billType", billType);
		mv.addObject("totalNum", totalNum);
		mv.addObject("totalMoney", totalMoney);
		mv.addObject("fromBankName", fromBankName);
		mv.addObject("toBankName", toBankName);
		mv.addObject("toBankNo", toBankNo);
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * 功能描述：纸票修改岗批次管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=batchManage")
	public ModelAndView batchManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/batch_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		if(query == null){
            query=new SubcollQueryCondition();
        }
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "batchManage", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setDfaultSrchTbAliasName("apply");
			query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
			query.addProperty2TableObj("billClass", "bill");
			mv.addObject("batchList", subcollService.getSubcollApplyInfoListForCondition(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("billClass", query.getBillClass());
		mv.addObject("page", page);
		mv.addObject("totalResult", page.getTotalResult());
		mv.addObject("query", query);
		return mv;
    	
	}
	
	
	/**
	 * 功能描述：纸票撤销发托管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=revokeBatchManage")
	public ModelAndView revokeBatchManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/revoke_batch_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
		String billClass = IDict.K_BILL_CLASS.K_ENTY_BILL;
		if(query == null){
            query=new SubcollQueryCondition();
        }
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "revokeBillForApply", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setDfaultSrchTbAliasName("apply");
			query.setApplyOperNo(userLogonInfo.getUserNo());
			query.addProperty2TableObj("applyOperNo", "bill");
			query.setBillClass(billClass);
			query.addProperty2TableObj("billClass", "bill");
			mv.addObject("batchList", subcollService.getSubcollApplyInfoListForCondition(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("billClass", billClass);
		mv.addObject("page", page);
		mv.addObject("totalResult", page.getTotalResult());
		mv.addObject("query", query);
		return mv;
	}
	
	
	/**托收打印清单前的查询（是否保存）
	 * @throws BizAppException */
	@RequestMapping(params="method=issave")
	@ResponseBody
	public AjaxJson issave(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		aj.setSuccess(true);
		try {
			String[] billids = ids.split(",");
			List<SubcollBillInfo> lists;
			String operStatus = StatusUtils.handleStatusNoCheck("SubcollApplyController", "save", null);
			lists = subcollService.getIssaveListbyidsandoperstatus(ids, operStatus);
			if(lists.size()<=0||lists==null){
				aj.setMsg("请先保存数据");
				aj.setSuccess(false);
				return aj;
			}else if(0<lists.size()&&lists.size()<billids.length){
				aj.setMsg("有未保存的数据，请先保存数据");
				aj.setSuccess(false);
				return aj;
			}
		} catch (Exception e3) {
			CommonLog.getCommonLogCache().errorMessage("查询票据保存状态时失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询票据保存状态时失败");
		}
		return aj;
	}
		/**托收打印清单前的查询（是否保存,根据主subcollmx_id键查询）
	 * @throws BizAppException */
	@RequestMapping(params="method=otherissave")
	@ResponseBody
	public AjaxJson otherissave(String ids) throws BizAppException{
		AjaxJson aj = new AjaxJson();
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		aj.setSuccess(true);
		try {
			String[] billids = ids.split(",");
			List<SubcollBillInfo> lists;
			String operStatus = StatusUtils.handleStatusNoCheck("SubcollApplyController", "save", null);
			lists = subcollService.getIssaveListbysubcollmxidandoperstatus(ids, operStatus);
			if(lists.size()<=0||lists==null){
				aj.setMsg("请先保存数据");
				aj.setSuccess(false);
				return aj;
			}else if(0<lists.size()&&lists.size()<billids.length){
				aj.setMsg("有未保存的数据，请先保存数据");
				aj.setSuccess(false);
				return aj;
			}
		} catch (Exception e3) {
			CommonLog.getCommonLogCache().errorMessage("查询票据保存状态时失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询票据保存状态时失败");
		}
		return aj;
	}
	
								/**-------------纸票系统end-------------------*/


								/**-------------公共方法start-------------------*/
	
	/**
	 * 申请岗票据保存或提交前检查
	 * 1.判断票据信息是否存在,如果存在，无需重复保存
	 * 2.纸票需判断是否为同一承兑行，并且同一批次下只能是相同的承兑行
	 * 3.电票勾选的票据含有行内提示付款的票据，只能选择线下清算方式
	 * 4,判断是否为同一票据类型,如果否，则需选择相同的票据类型
	 * @param ids
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=checkBill")
	@ResponseBody
	public AjaxJson checkBill(String ids)throws BizAppException{
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		AjaxJson retJson = new AjaxJson();
				IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();
				String [] idss =CommUtils.couvertLong(ids);
				String [] acceptorBankNo = new String[idss.length];
				String [] billType = new String[idss.length];
				String operStatus = null;
				String billClass = null;
				try {
					operStatus = StatusUtils.handleStatusNoCheck("SubcollApplyController", "save", null);
				} catch (Exception e) {
					CommonLog.getCommonLogCache().errorMessage("获取状态值失败");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "获取状态值失败");
				}
				for (int i = 0; i < idss.length; i++) {
					String id = idss[i];
					RgctBill rgctBill =rcService.getRgctBillById(id);
					RgctBillInfo rgctBillInfo = rgctBill.getInfo();
					String rgctId = rgctBill.getHist().getRgctId();
					//判断票据信息是否存在
					SubcollBillInfo list = subcollService.getSubcollBillForRgctid(rgctId);
					if(list!=null && operStatus.equals(list.getOperStatus())){
								retJson.setObj("save");//票据信息已保存，无需重复保存
								return retJson;
						}
					acceptorBankNo [i] = rgctBillInfo.getAcceptorBankNo();
					billType [i] = rgctBillInfo.getBillType();
					billClass = rgctBillInfo.getBillClass();
				}
				for (int i = 0; i < acceptorBankNo.length; i++) {
					//纸票需判断是否为同一承兑行，并且同一批次下只能是相同的承兑行
					if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
						 if(!acceptorBankNo[0].equals(acceptorBankNo[i])){
							 retJson.setObj("acceptorBankNo");
							 return retJson;
						 }
					}else{
						//如果勾选的票据含有行内提示付款的票据，只能选择线下清算方式
						if(CommUtils.isSelfBank(acceptorBankNo[i])){
							retJson.setObj("isOnline");
							return retJson;
						}
					}
					//判断是否为同一票据类型
					if(!billType[0].equals(billType[i])){
						 retJson.setObj("billType");
						 return retJson;
					}
				}
		return retJson;
	}
	
	
	/**
	 * 保存
	 * @param ids
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=save")
	public ModelAndView save(String ids,SubcollQueryCondition query) throws BizAppException{
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		if(query == null){
            query=new SubcollQueryCondition();
        }
		String [] idss =CommUtils.couvertLong(ids);
		String billClass = null;
		try {
			query.setOperStatus(StatusUtils.handleStatusNoCheck("SubcollApplyController", "save", null));
			for (int i = 0; i < idss.length; i++) {
				String id = idss[i];
				RgctBill rgctBill =rcService.getRgctBillById(id);
				RgctBillInfo rgctBillInfo = rgctBill.getInfo();
				billClass = rgctBillInfo.getBillClass();
			}
			session.beginTransaction();
			subcollService.addBillInfoAndApplyInfo(ids,query);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new BizAppException("事物回滚失败");
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("保存失败");
			throw new BizAppException(e.getMessage());
		}
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			return nextView(ids,query);
		}else{
			return elecNextView(ids,query,new Page());
		}
	}

	/**
	 * 提交申请岗新增票据与批次
	 * @param ids
	 * @param query
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params = "method=apply")
	public ModelAndView apply(String ids,SubcollQueryCondition query) throws BizAppException{
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		if(query == null){
            query=new SubcollQueryCondition();
        }
		SubcollBillInfo bill = new SubcollBillInfo();
		String [] idss =CommUtils.couvertLong(ids);
		String billClass = null;
		String [] subcollmxIds = new String[idss.length];
		try {
			for (int i = 0; i < idss.length; i++) {
				String id = idss[i];
				RgctBill rgctBill =rcService.getRgctBillById(id);
				RgctBillInfo rgctBillInfo = rgctBill.getInfo();
				billClass = rgctBillInfo.getBillClass();
				//根据rgctId与票据保存时状态判断票据是否已先保存
				String rgctId = rgctBill.getHist().getRgctId();
				bill = subcollService.getSubcollBillForRgctIdAndOperStatus(rgctId,StatusUtils.handleStatusNoCheck("SubcollApplyController", "save", null));
				if(bill!=null){//如果查询到票，则获取主键subcollmxId
					subcollmxIds[i] = bill.getSubcollmxId();
				}
			}
			//如果票据已保存，则修改当前状态进行提交
			if(bill!=null){
				return lastApply(subcollmxIds,query,billClass);
			}
			query.setOperStatus(StatusUtils.handleStatusNoCheck("SubcollApplyController", "apply", null));
			session.beginTransaction();
			subcollService.addBillInfoAndApplyInfo(ids,query);
			session.endTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("提交失败");
			throw new BizAppException(e.getMessage());
		}
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			return subcollManage(new Page(),query);
		}else{
			return elecBillManage(new Page(),query);
		}
	}
	/**
	 * 申请岗先保存后提交
	 * @param subcollmxIds
	 * @param query
	 * @param billClass
	 * @return
	 * @throws BizAppException
	 */
	public ModelAndView lastApply(String [] subcollmxIds,SubcollQueryCondition query,String billClass)throws BizAppException{
		ISubcollService subcollService = ServiceFactory.getSubcollService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            subcollService.lastApply(subcollmxIds);
            session.endTransaction();
        } catch (SQLException e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
   			 	throw new BizAppException(ISysErrorNo.ERR_DBERR, "事物回滚失败");
            }
            e.printStackTrace();
            CommonLog.getCommonLogCache().errorMessage("提交票据失败");
			throw new BizAppException(e.getMessage());
        }
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			return subcollManage(new Page(),query);
		}else{
			return elecBillManage(new Page(),query);
		}
	}
	
	
	/**
	 * 系统主页面方法调用显示快捷方式
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=advancedQuery")
	public ModelAndView advancedQuery(Page page,SubcollQueryCondition query)throws BizAppException{
		ModelAndView mv = new ModelAndView();
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		if(query == null){
            query=new SubcollQueryCondition();
        }
	    //进行格式转换，让前台不用科学计数法显示,利于查询
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String billNo = query.getBillNo();
        Double bigMoney = query.getBigMoney();
        String bm = null;
        String sm = null;
        if(bigMoney != null){
        	bm = nf.format(bigMoney);
        }
        Double smallMoney = query.getSmallMoney();
        if(smallMoney != null){
        	sm = nf.format(smallMoney);
        }
        String billClass = query.getBillClass();
	    String acceptor =query.getAcceptor();
		try {
			mv.addObject("resultList",subcollService.getSubcollBillForPrint(page, query));
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			mv.setViewName("busi/subcoll/entity/bill_manage");
		}else{
			mv.setViewName("busi/subcoll/electronic/elec_bill_manage");
		}
		mv.addObject("billClass", billClass);
        mv.addObject("bigMoney", bm);
        mv.addObject("smallMoney", sm);
        mv.addObject("acceptor", acceptor);
        mv.addObject("billNo", billNo);
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * rc票据查看
	 * @param id
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=goDetail")
	public ModelAndView goDetail(String id)throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/subcoll_bill_detail");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		RgctBill rgctBill = null;
		try {
			rgctBill = subcollService.goDetail(id);
			RgctBillInfo info = rgctBill.getInfo();
			mv.addObject("resultList",info);
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("查询rgctBill信息失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询rgctBill信息失败");
		}
		return mv;
	}
	
	/**
	 * 数据库票据查看
	 * @param subcollmxId
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=goSqlDetail")
	public ModelAndView goSqlDetail(String subcollmxId) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/subcoll_batch_detail");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		SubcollBillInfo subcollBillInfo =new SubcollBillInfo();
		try {
			subcollBillInfo= subcollService.getSubcollBillInfo(subcollmxId);
			mv.addObject("resultList",subcollBillInfo);
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo信息失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo信息失败");
		}
		return mv;
	}
	
	
	/**
	 * 修改岗添加票据主页面
	 * 1、纸票查询票据信息列表，只查询同一承兑行和同一类型的票据信息，(同一批次中，承兑行与类型必须相同)用于为批次新增票
	 * 2、电票中清算方式、是否逾期、逾期原因在同一批次中必须相同且显示当前系统营业日前后十天到期的票据
	 * 3、电票无需在页面进行显示是否逾期等信息，需从批次中查询对应清单信息，然后copy
	 * 4、页面电票和纸票共用同一个就行
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=toAddBill")
	public ModelAndView toAddBill(Page page,SubcollQueryCondition query) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		page.activeCommand();
		mv.setViewName("busi/subcoll/entity/choose_bill");
		String busiDate = DateTimeUtil.getWorkdayString();
		String acceptorBankNo =null;
		String isOverdue = null;
		String isOnline = null;
		if(query == null){
			query = new SubcollQueryCondition();
		}
		try {
			ISubcollService subcollService = ServiceFactory.getSubcollService();
			//查询该批次中的票据信息
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "batchManage", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
			List<SubcollBillInfo> subcollBillList = subcollService.getSubcollBillsBySearchBean(page, query);
			for(int j=0;j<subcollBillList.size();j++){
				SubcollBillInfo subcollBill = subcollBillList.get(j);
				acceptorBankNo = subcollBill.getAcceptorBankNo();
				isOverdue = subcollBill.getIsOverdue();
				isOnline = subcollBill.getIsOnline();
			}
			query.setIsOnline(isOnline);
			query.setIsOverdue(isOverdue);
			//如果是电票，查询票面到期日的系统营业日期前后10天的票据,且不需要相同的承兑行
			if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(query.getBillClass())){
				String startDay = DateTimeUtil.getDate(busiDate, -10);
				String endDay = DateTimeUtil.getDate(busiDate, 10);
				query.setStartDay(startDay);
				query.setEndDay(endDay);
			}else {//纸票需要相同的承兑行
				query.setAcceptorBankNo(acceptorBankNo);
			}
			//查询待提交发托的票据信息
			List<RgctBillData> rgctBillLIst = subcollService.getSubcollBillForPrint(page, query);
			mv.addObject("resultList",rgctBillLIst);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("totalResult", page.getTotalResult());
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * 修改岗添加票据
	 * @param ids
	 * @param subcollId
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=addBill")
	public ModelAndView addBill(String ids,String subcollId,SubcollQueryCondition query) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		IDB session = DBFactory.getDB();
		if(query == null){
            query=new SubcollQueryCondition();
        }
		try {
			session.beginTransaction();
			subcollService.addSubcollBillInfo(ids, subcollId,query);
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new BizAppException("事物回滚失败");
			}
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 修改岗票据管理页面
	 * 方法说明：
	 * 1、overdueReason不能作为查询条件，因为查询条件中用的都是query，清单表中没有这个字段，会报sql异常
	 * 	这是为了打印中先保存之后打印然后页面定住使用的
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=editBillManage")
	public ModelAndView editSubcollManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		String isOnline = null;
		String isOverdue = null;
		String remark = null;
		if(query == null){
            query=new SubcollQueryCondition();
        }
		String overdueReason = query.getOverdueReason();
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "batchManage", null));
			query.setOverdueReason(null);
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		   // query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
		    mv.addObject("batch",subcollService.getSubcollApplyInfo(query));
			mv.addObject("resultList",subcollService.getSubcollBillsBySearchBean(page, query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(e.getMessage());
		}
		List<SubcollBillInfo> billList = subcollService.getSubcollBillsBySearchBean(page, query);
		for(SubcollBillInfo bill:billList){
			isOnline = bill.getIsOnline();
			isOverdue = bill.getIsOverdue();
			remark = bill.getRemark();
		}
		query.setIsOnline(isOnline);
		query.setIsOverdue(isOverdue);
		query.setRemark(remark);
		String billClass = query.getBillClass();
		query.setOverdueReason(overdueReason);
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			mv.setViewName("busi/subcoll/entity/edit_bill_manage");
		}else{
			mv.setViewName("busi/subcoll/electronic/elec_edit_bill_manage");
		}
		mv.addObject("billClass", billClass);
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * 功能描述：修改岗删除票据信息
	 * @param page
	 * @param subcollmxIds
	 * @param billClass
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=delBillForApply")
	public ModelAndView delBillForApply(Page page,String subcollmxIds,String billClass) throws BizAppException{
		 ISubcollService subcollService = ServiceFactory.getSubcollService();
	        IDB session = DBFactory.getDB();
	        try {
	            session.beginTransaction();
	            subcollService.delBillForApply(subcollmxIds);
	            session.endTransaction();
	        } catch (Exception e) {
	            try {
	                session.rollback();
	            } catch (SQLException e1) {
	            	CommonLog.getCommonLogCache().errorMessage("事物回滚失败");
	   			 	throw new BizAppException(ISysErrorNo.ERR_DBERR, "事物回滚失败");
	            }
	            e.printStackTrace();
	            CommonLog.getCommonLogCache().errorMessage("删除票据信息失败");
				throw new BizAppException(e.getMessage());
	        }
	        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
	        	return batchManage(new Page(),new SubcollQueryCondition());
			}else{
				return elecBatchManage(new Page(),new SubcollQueryCondition());
			}
	        
		}
	
	/**
	 * 纸票提交修改岗票据
	 * @param page
	 * @param subcollIds
	 * @param billClass
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=applyBill")
	public ModelAndView applyBill(Page page,String subcollIds) throws BizAppException{
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            subcollService.applyBill(subcollIds);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
            	CommonLog.getCommonLogCache().errorMessage("事物回滚失败");
   			 	throw new BizAppException(ISysErrorNo.ERR_DBERR, "事物回滚失败");
            }
            e.printStackTrace();
            CommonLog.getCommonLogCache().errorMessage("提交票据失败");
			throw new BizAppException(e.getMessage());
        }
        	return batchManage(new Page(),new SubcollQueryCondition());
	}
	
	/**
	 * 电票提交修改岗票据
	 * @param page
	 * @param subcollmxId
	 * @param billClass
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecApplyBill")
	public ModelAndView elecApplyBill(Page page,String subcollmxId,SubcollQueryCondition query) throws BizAppException{
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            subcollService.elecApplyBill(subcollmxId, query);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
            	CommonLog.getCommonLogCache().errorMessage("事物回滚失败");
   			 	throw new BizAppException(ISysErrorNo.ERR_DBERR, "事物回滚失败");
            }
            e.printStackTrace();
            CommonLog.getCommonLogCache().errorMessage("提交票据失败");
			throw new BizAppException(e.getMessage());
        }
			return elecBatchManage(new Page(),new SubcollQueryCondition());
	}
	
	/**
	 * 撤销岗：撤销发托票据信息明细管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=revokeBillDetailsManage")
	public ModelAndView revokeBillDetailsManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		if(query == null){
            query=new SubcollQueryCondition();
        }
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "revokeBillForApply", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
		    mv.addObject("batch",subcollService.getSubcollApplyInfo(query));
			mv.addObject("resultList",subcollService.getSubcollBillsBySearchBean(page, query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(e.getMessage());
		}
		List<SubcollBillInfo> billList = subcollService.getSubcollBillsBySearchBean(page,query);
		for(SubcollBillInfo bill:billList){
			query.setIsOnline(bill.getIsOnline());//增加清算方式
		}
		String billClass = query.getBillClass();
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			mv.setViewName("busi/subcoll/entity/revoke_bill_details");
		}else{
			mv.setViewName("busi/subcoll/electronic/elec_revoke_bill_details");
		}
		mv.addObject("billClass", billClass);
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	/**
	 * 撤销发托
	 * @param page
	 * @param subcollIds
	 * @param billClass
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=revokeBillForApply")
	public ModelAndView revokeBillForApply(Page page,String subcollIds,String billClass) throws BizAppException{
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            subcollService.RevokeBillRegister(subcollIds);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
            	CommonLog.getCommonLogCache().errorMessage("事物回滚失败");
   			 	throw new BizAppException(ISysErrorNo.ERR_DBERR, "事物回滚失败");
            }
            e.printStackTrace();
            CommonLog.getCommonLogCache().errorMessage("撤销发托失败");
			throw new BizAppException(e.getMessage());
        }
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
        	return revokeBatchManage(new Page(),new SubcollQueryCondition());
		}else{
			return elecRevokeBatchManage(new Page(),new SubcollQueryCondition());
		}
        
	}

								/**-------------公共方法end-------------------*/
	
	
	
								/**-------------电票系统start-------------------*/

	/**
	 * 电票申请岗管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecBillManage")
	public ModelAndView elecBillManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/electronic/elec_bill_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		if(query == null){
			query = new SubcollQueryCondition();
		}
		String billNo = query.getBillNo();
		String accptor = query.getAcceptor();
		String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
		String busiDate = DateTimeUtil.getWorkdayString();
	    String startDay = null;
	    String endDay = null;
	    String billType = null;
	    if(StringUtils.isBlank(query.getBillType())){
	    	billType = (IDict.K_BILL_TYPE.K_BANK_BILL);
	    	query.setBillType(billType);
		}else{
			billType = query.getBillType();
		}
	    if(StringUtils.isBlank(query.getStartDay())){
	    	startDay = DateTimeUtil.getDate(busiDate, -10);
	    	query.setStartDay(startDay);
	    }else{
	    	startDay = query.getStartDay();
	    }
	    if(StringUtils.isBlank(query.getEndDay())){
	    	endDay = DateTimeUtil.getDate(busiDate, 10);
	    	query.setEndDay(endDay);
	    }else {
			endDay = query.getEndDay();
		}
		try {
			query.setBillClass(billClass);
			mv.addObject("resultList",subcollService.getSubcollBillForPrint(page, query));
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("billClass", billClass);
		mv.addObject("billType", billType);
		mv.addObject("startDay", startDay);
		mv.addObject("endDay", endDay);
		mv.addObject("page", page);
		mv.addObject("bigMoney", query.getBigMoney());
		mv.addObject("smallMoney", query.getSmallMoney());
		mv.addObject("acceptor", accptor);
		mv.addObject("billNo", billNo);
		mv.addObject("query", query);
		return mv;
	}

	/**
	 * 电票管理页面票据管理
	 * @param ids
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecNextView")
	public ModelAndView elecNextView(String ids,SubcollQueryCondition query,Page page) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/electronic/elec_bill_next");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		List<RgctBillInfo> list=new ArrayList<RgctBillInfo>();
		if(query==null){
			query= new SubcollQueryCondition();
		}
		String [] idss =CommUtils.couvertLong(ids);
		String isOnline = null;
		//首次进入，界面显示默认清算方式为线下
		if(StringUtils.isBlank(query.getIsOnline())){
			isOnline = RcConstants.SETTLEMENTMARK_ZERO;
			query.setIsOnline(isOnline);
		}else{
			isOnline = query.getIsOnline();
		}
		String isOverdue = null;
		//首次进入，界面显示默认是否逾期为未逾期
		if(StringUtils.isBlank(query.getIsOverdue())){
			isOverdue = SubcollCodeConst.IS_OVERDUE_ONE;
			query.setIsOverdue(isOverdue);
		}else{
			isOverdue = query.getIsOverdue();
		}
		for (int i = 0; i < idss.length; i++) {
			String id = idss[i];
			RgctBill rgctBill = subcollService.goDetail(id);
			RgctBillInfo info = rgctBill.getInfo();
			list.add(info);
		}
		page.setTotalResult(list.size());
		page.activeCommand();
		mv.addObject("page",page);
		mv.addObject("id",ids);
		mv.addObject("query",query);
		mv.addObject("resultList",list);
		return mv;
	}
	
	/**
	 * 功能描述：电票修改岗批次管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecBatchManage")
	public ModelAndView elecBatchManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/electronic/elec_batch_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		page.activeCommand();
		if(query == null){
	        query=new SubcollQueryCondition();
	    }
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "batchManage", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setDfaultSrchTbAliasName("apply");
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			query.setBranchNo(user.getBranchNo());
			query.addProperty2TableObj("billClass", "bill");
			mv.addObject("batchList", subcollService.getSubcollApplyInfoListForCondition(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("billClass", query.getBillClass());
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
		
	}

	/**
	 * 电票修改岗保存前检查
	 * 方法说明：主要是检查修改的信息能否进行保存
	 * 1、判断该批次下是否有行内的票据并且选中的的清算方式是否为线上清算，如果是，则不能修改为线上清算进行保存
	 * 2、判断该批次下的票据是否有提交或者已经提示付款申请，但是承兑行没有回复的操作状态(即票据可以回到修改岗)，如果是，则不能修改批次信息进行保存
	 * @param subcollId
	 * @param isOnline
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecCheckBill")
	@ResponseBody
	public AjaxJson elecCheckBill(String subcollId,String isOnline)throws BizAppException{
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		AjaxJson retJson = new AjaxJson();
		List<SubcollBillInfo> billList = subcollService.getSubcollBillForSubcollId(subcollId);//根据subcollId进行查询该批次下的所有票据
		String [] operStatus = new String[billList.size()];
		String [] acceptorBankNo = new String[billList.size()];
		String applyOperStatus = null;
		try {
			//获取提交的操作状态
			applyOperStatus = StatusUtils.handleStatusNoCheck("SubcollStorageController", "revokeSendBillForApply", null);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("获取状态值失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "获取状态值失败");
		}
		for(int i = 0; i < billList.size(); i++){
			SubcollBillInfo bill = billList.get(i);
			operStatus [i] = bill.getOperStatus();;//获得每张票的操作状态放入数组中
			acceptorBankNo [i] = bill.getAcceptorBankNo();//获得每张票的承兑行行号放入数组中
		}
		//判断该批次下是否有行内的票据并且选中的的清算方式是否为线上清算
		for (int i = 0; i < acceptorBankNo.length; i++) {
			if(CommUtils.isSelfBank(acceptorBankNo[i]) && RcConstants.SETTLEMENTMARK_ONE.equals(isOnline)){
				retJson.setObj("notOnline");//如果是，则不能修改为线上清算进行保存
				return retJson;
			}
		}
		//判断该批次下的票据是否有提交或者已经提示付款申请，但是承兑行没有回复的操作状态(即票据可以回到修改岗)
		for (int i = 0; i < operStatus.length; i++) {
			if(applyOperStatus.equals(operStatus[i]) || SubcollCodeConst.ACPT_SUB_COLL_LIST_325.equals(operStatus[i]) ){
				retJson.setObj("notSave");//如果是，则不能修改批次信息进行保存
				return retJson;
			}
		}
		return retJson;
	}
	
	/**
	 * 电票修改岗保存修改信息
	 * 方法说明：保存修改的批次信息和该批次下所有清单的信息
	 * 1、同一个批次下清算方式相同
	 * 2、同一个批次下是否逾期相同
	 * 3、同一批次下逾期原因相同
	 * 4、同一批次下提示付款人备注相同
	 * @param subcollId
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecSave")
	public ModelAndView elecSave(String subcollId,SubcollQueryCondition query) throws BizAppException{
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		if(query == null){
            query=new SubcollQueryCondition();
        }
		IDB session = DBFactory.getDB();
		List<SubcollBillInfo> billList = subcollService.getSubcollBillForSubcollId(subcollId);//根据subcollId进行查询该批次下的所有票据
		try {
			session.beginTransaction();
			for(SubcollBillInfo bill:billList){
				bill.setIsOnline(query.getIsOnline());
				bill.setIsOverdue(query.getIsOverdue());
				bill.setRemark(query.getRemark());
				subcollService.modifySubcollBillInfo(bill);
			}
			SubcollApplyInfo applyInfo = subcollService.getSubcollApplyInfobyid(subcollId);//根据subcollId进行查询该批次
			Boolean bool = SubcollCodeConst.IS_OVERDUE_ONE.equals(query.getIsOverdue());//判断是否逾期，如果bool为true，则未逾期，否则逾期
	        //如果修改为未逾期，则把逾期原因置空,否则写入前台输入的逾期原因
			applyInfo.setOverduereason(bool?null:query.getOverdueReason());
			subcollService.modifySubcollApplyInfo(applyInfo);
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new BizAppException("事物回滚失败");
			}
			e.printStackTrace();
			throw new BizAppException("修改票据信息失败");
		}
		return editSubcollManage(new Page(),query);
	}
	
	
	/**
	 * 电票票据添加前检查
	 * 1.判断该批次下是否有行内的票据,如果有行内的票据，只能选择线下清算方式
	 * 2.判断该批次下的票据是否有提交或者已经提示付款申请，但是承兑行没有回复的操作状态(即票据可以回到修改岗)并且该批次下的清算方式是否为线上清算,如果是，则不能继续加入行内提示付款的票据
	 * @param ids
	 * @param subcollId
	 * @param billClass
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=addCheckBill")
	@ResponseBody
	public AjaxJson addCheckBill(String ids,String subcollId,String billClass)throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		SubcollQueryCondition query = new SubcollQueryCondition();
		query.setSubcollId(subcollId);
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(billClass)){
			ISubcollService subcollService = ServiceFactory.getSubcollService();
			String [] idss =CommUtils.couvertLong(ids);
			String [] acceptorBankNo = new String[idss.length];
			String [] operStatus = new String[idss.length];
			String isOnline = null;
			String applyOperStatus = null;
			try {
				//获取提交的操作状态
				applyOperStatus = StatusUtils.handleStatusNoCheck("SubcollStorageController", "revokeSendBillForApply", null);
			} catch (Exception e) {
				CommonLog.getCommonLogCache().errorMessage("获取状态值失败");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "获取状态值失败");
			}
			//根据subcollId进行查询该批次下的所有票据
			List<SubcollBillInfo> subcollBillList = subcollService.getSubcollBillsBySearchBean(new Page(), query);
			//获得该批次下所有票据的清算方式与承兑行行号和操作状态
			for(int i=0;i<subcollBillList.size();i++){
				SubcollBillInfo subcollBill = subcollBillList.get(i);
				isOnline = subcollBill.getIsOnline();
				acceptorBankNo [i] = subcollBill.getAcceptorBankNo();
				operStatus [i] = subcollBill.getOperStatus();
			}
			//判断该批次下是否有行内的票据
			for (int i = 0; i < acceptorBankNo.length; i++) {
				if(CommUtils.isSelfBank(acceptorBankNo[i])){
					retJson.setObj("isOnline");//如果有行内的票据，只能选择线下清算方式
					return retJson;
				}
			}
			//判断该批次下的票据是否有提交或者已经提示付款申请，但是承兑行没有回复的操作状态(即票据可以回到修改岗)并且该批次下的清算方式是否为线上清算
			for (int i = 0; i < operStatus.length; i++) {
				if((applyOperStatus.equals(operStatus[i]) || SubcollCodeConst.ACPT_SUB_COLL_LIST_325.equals(operStatus[i]))&&RcConstants.SETTLEMENTMARK_ONE.equals(isOnline)){
					retJson.setObj("notAdd");//如果是，则不能继续加入行内提示付款的票据
					return retJson;
				}
			}
		}
		return retJson;
	}
	
	/**
	 * 功能描述：电票撤销发托管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecRevokeBatchManage")
	public ModelAndView elecRevokeBatchManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/electronic/elec_revoke_batch_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
		String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
		if(query == null){
	        query=new SubcollQueryCondition();
	    }
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "revokeBillForApply", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setDfaultSrchTbAliasName("apply");
			query.setApplyOperNo(userLogonInfo.getUserId());
			query.addProperty2TableObj("operNo", "bill");
			query.addProperty2TableObj("applyOperNo", "bill");
			query.setBillClass(billClass);
			query.setBranchNo(userLogonInfo.getBranchNo());
			query.addProperty2TableObj("billClass", "bill");
			mv.addObject("batchList", subcollService.getSubcollApplyInfoListForCondition(page,query));
		} catch (Exception e1) {
			CommonLog.getCommonLogCache().errorMessage("撤销发托失败");
			throw new BizAppException(e1.getMessage());
		}
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("billClass", billClass);
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}

								
								/**-------------电票系统end-------------------*/
}

