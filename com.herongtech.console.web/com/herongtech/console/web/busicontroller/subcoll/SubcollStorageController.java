/********************************************
 * 文件名称: SubcollStorageController.java
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
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
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
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 托收出库Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/subcollStorageController")
public class SubcollStorageController extends BaseController{
			
									/**-------------纸票系统start-------------------*/
	
	/**
	 * 功能描述：纸票发托出库管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=sendBatchManage")
	public ModelAndView sendBatchManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/send_batch_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		String billClass = IDict.K_BILL_CLASS.K_ENTY_BILL;
		if(query == null){
            query=new SubcollQueryCondition();
        }
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "revokeBillForApply", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setDfaultSrchTbAliasName("apply");
			query.setBillClass(billClass);
			query.addProperty2TableObj("billClass", "bill");
			/*query.setOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
			query.addProperty2TableObj("operNo", "bill");*/
			mv.addObject("batchList", subcollService.getSubcollApplyInfoListForCondition(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查询可发托出库的票据信息失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询可发托出库的票据信息失败");
		}
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("billClass", billClass);
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * 功能描述：纸票撤销出库管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=revokeSendBatchManage")
	public ModelAndView revokeBatchSendManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/revoke_send_batch_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		String billClass = IDict.K_BILL_CLASS.K_ENTY_BILL;
		if(query == null){
            query=new SubcollQueryCondition();
        }
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollStorageController", "revokeSendBillForApply", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setDfaultSrchTbAliasName("apply");
			query.setBillClass(billClass);
			query.addProperty2TableObj("billClass", "bill");
			/*query.setOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
			query.addProperty2TableObj("operNo", "bill");*/
			mv.addObject("batchList", subcollService.getSubcollApplyInfoListForCondition(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("可撤销出库票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "可撤销出库票据列表查询失败");
		}
		String msg = page.getPageStr().replace("nextPage(page", "nextPages(page").replace("changeCount(value", "changeRSCount(value");
		page.setPageStr(msg);
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("billClass", billClass);
		mv.addObject("page", page);
		mv.addObject("totalResult", page.getTotalResult());
		mv.addObject("query", query);
		return mv;
	}
	
	
										/**-------------纸票系统end-------------------*/
	
	
	
										/**-------------公共方法start-------------------*/
	
	/**
	 * 发托出库票据明细页面
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
		if(query == null){
            query=new SubcollQueryCondition();
        }
		String billClass = query.getBillClass();
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "revokeBillForApply", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    mv.addObject("batch",subcollService.getSubcollApplyInfo(query));
			mv.addObject("resultList",subcollService.getSubcollBillsBySearchBean(page, query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据信息查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据信息查询失败");
		}
		List<SubcollBillInfo> billList = subcollService.getSubcollBillsBySearchBean(page,query);
		for(SubcollBillInfo bill:billList){
			query.setIsOnline(bill.getIsOnline());//增加清算方式
		}
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			mv.setViewName("busi/subcoll/entity/bill_detail");
		}else{
			mv.setViewName("busi/subcoll/electronic/elec_bill_detail");
		}
		mv.addObject("billClass", billClass);
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("page", page);
		mv.addObject("query", query);
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
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		return mv;
	}
	/**
	 * 批次查看
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=goBatchInfo")
	public ModelAndView goBatchInfo(SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/subcoll_batch_detail");
		SubcollApplyInfo subcollApplyInfo = new SubcollApplyInfo();
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		subcollApplyInfo = subcollService.getSubcollApplyInfo(query);
		mv.addObject("subcollBillInfo", subcollApplyInfo);
		mv.addObject("query", query);
		return mv;
	}
	
	
	/**
	 * 查询
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=search")
	public ModelAndView search(Page page ,SubcollQueryCondition query)throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/bill_detail");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		if(query == null){
            query=new SubcollQueryCondition();
        }
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "batchManage", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    mv.addObject("batchList", subcollService.getSubcollBillsBySearchBean(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
		
	}
	
	/**
	 * 提交出库
	 * @param page
	 * @param query
	 * @param subcollmxIds
	 * @param billClass
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=apply")
	public ModelAndView apply(Page page,String subcollmxIds,SubcollQueryCondition query,String billClass) throws BizAppException{
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        try {
            subcollService.applyWarehouse(subcollmxIds,query);
        } catch (Exception e) {
            e.printStackTrace();
            CommonLog.getCommonLogCache().errorMessage("提交出库失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "提交出库失败");
        }
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
        	return sendBatchManage(new Page(),new SubcollQueryCondition());
		}else{
			return elecSendBatchManage(new Page(),new SubcollQueryCondition());
		}
        
    
	}
	
	
	/**
	 * 撤销出库票据明细页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=revokeSendBillDetail")
	public ModelAndView revokeSendBillDetail(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		page.activeCommand();
		if(query == null){
            query=new SubcollQueryCondition();
        }
		String billClass = query.getBillClass();
		try {
			if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
				query.setStatusArray(StatusUtils.queryStatus("SubcollStorageController", "revokeSendBillForApply", null));
				query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
			    query.addSqlPropretyMapping("statusArray", "operStatus");
			}else {
				query.setOperStatus(SubcollCodeConst.ACPT_SUB_COLL_LIST_325);
			}
			mv.addObject("batch",subcollService.getSubcollApplyInfo(query));
			mv.addObject("resultList",subcollService.getSubcollBillsBySearchBean(page, query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		List<SubcollBillInfo> billList = subcollService.getSubcollBillsBySearchBean(page,query);
		for(SubcollBillInfo bill:billList){
			query.setIsOnline(bill.getIsOnline());//增加清算方式
		}
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			mv.setViewName("busi/subcoll/entity/revoke_send_bill_details");
		}else{
			mv.setViewName("busi/subcoll/electronic/elec_revoke_send_bill_details");
		}
		mv.addObject("billClass", billClass);
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * 撤销出库
	 * @param page
	 * @param subcollIds
	 * @param billClass
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=revokeSendBillForApply")
	public ModelAndView revokeSendBillForApply(Page page,String subcollIds,String billClass) throws BizAppException{
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        try {
            subcollService.revokeWarehouse(subcollIds,billClass);
        } catch (Exception e) {
            CommonLog.getCommonLogCache().errorMessage("撤销出库失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "撤销出库失败");
        }
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
        	return revokeBatchSendManage(new Page(),new SubcollQueryCondition());
		}else{
			return elecRevokeSendBatchManage(new Page(),new SubcollQueryCondition());
		}
        
    
	}
	
									
	
									/**-------------公共方法end-------------------*/
	
	
	
									/**-------------电票系统start-------------------*/
	
	/**
	 * 功能描述：电票托收管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecSendBatchManage")
	public ModelAndView elecSendBatchManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/electronic/elec_send_batch_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		page.activeCommand();
		String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
		if(query == null){
            query=new SubcollQueryCondition();
        }
		try {
			query.setStatusArray(StatusUtils.queryStatus("SubcollApplyController", "revokeBillForApply", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setDfaultSrchTbAliasName("apply");
			query.setBillClass(billClass);
			query.addProperty2TableObj("billClass", "bill");
			query.setBranchNo(user.getBranchNo());
			/*query.setOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
			query.addProperty2TableObj("operNo", "bill");*/
			mv.addObject("batchList", subcollService.getSubcollApplyInfoListForCondition(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("可电票托收票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "可电票托收票据列表查询失败");
		}
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("billClass", billClass);
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	
	/**
	 * 功能描述：电票撤销托收管理页面
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecRevokeSendBatchManage")
	public ModelAndView elecRevokeSendBatchManage(Page page,SubcollQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/revoke_send_batch_manage");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		page.activeCommand();
		String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
		if(query == null){
            query=new SubcollQueryCondition();
        }
		try {
			query.setDfaultSrchTbAliasName("apply");
			query.setBillClass(billClass);
			query.addProperty2TableObj("billClass", "bill");
			query.addProperty2TableObj("applyOperNo", "bill");
			//query.setApplyOperNo(user.getUserId());
			query.setBranchNo(user.getBranchNo());
			query.setOperStatus(SubcollCodeConst.ACPT_SUB_COLL_LIST_325);//电票发托过程中其是中间过度状态，无需再状态机进行配置
			mv.addObject("batchList", subcollService.getSubcollApplyInfoListForCondition(page,query));
		} catch (BizAppException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("可撤销电票托收票据信息查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "可撤销电票托收票据信息查询失败");
		}
		mv.addObject("subcollId", query.getSubcollId());
		mv.addObject("billClass", billClass);
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	
									/**-------------电票系统end-------------------*/
}
