/********************************************
 * 文件名称: ReceiveMoneyController.java
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

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollQueryCondition;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.subcoll.ISubcollService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 托收记账与退票 Controller
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/receiveMoneyController")
public class ReceiveMoneyController extends BaseController {
    
									/**-------------纸票系统start-------------------*/
	
	/**
     * 纸票收款登记管理
     * @param page
     * @param query
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=receiveMoneyRegister")
    public ModelAndView receiveMoneyRegister(Page page,SubcollQueryCondition query) throws BizAppException{
    	
        ModelAndView mv = new ModelAndView("busi/subcoll/entity/receivemoney/receiveMoneyRegisterMain");
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        page.activeCommand();
        if(query==null){
        	query = new SubcollQueryCondition();
        }
        String billNo = query.getBillNo();
        String billClass = IDict.K_BILL_CLASS.K_ENTY_BILL;
        //进行格式转换，让前台不用科学计数法显示,利于查询
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
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
        String billType = query.getBillType();
        String busiDate = DateTimeUtil.getWorkdayString();
        String startDay = null;
        String endDay = null;
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
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "receiveMoneyRegister", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setBillClass(billClass);
		    query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
		    query.setIds(null);
            mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(page, query));
        } catch (Exception e) {
        	e.printStackTrace();
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        mv.addObject("billNo", billNo);
        mv.addObject("bigMoney", bm);
        mv.addObject("smallMoney", sm);
        mv.addObject("billType", billType);
        mv.addObject("billClass", billClass);
        mv.addObject("startDay", startDay);
		mv.addObject("endDay", endDay);
        mv.addObject("subcollId", query.getSubcollId());
        mv.addObject("page", page);
        return mv;
    }
	
	/**
     * 纸票收回记账管理
     * @param page
     * @param query
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=searchBillList")
    public ModelAndView searchBillList(Page page,SubcollQueryCondition query) throws BizAppException{
        ModelAndView mv = new ModelAndView("busi/subcoll/entity/receivemoney/receiveMoneyMain");
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        page.activeCommand();
        String billClass = IDict.K_BILL_CLASS.K_ENTY_BILL;
        if(query == null){
            query=new SubcollQueryCondition();
        }
        try {
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "searchBillList", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setBillClass(billClass);
		    query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
            mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(page, query));
        } catch (Exception e) {
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        mv.addObject("subcollId", query.getSubcollId());
        mv.addObject("billClass", billClass);
        mv.addObject("page", page);
        return mv;
    }
    
    /**
     * 纸票撤销记账管理
     * @param page
     * @param query
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=revokeBillList")
    public ModelAndView revokeBillList(Page page,SubcollQueryCondition query) throws BizAppException{
        ModelAndView mv = new ModelAndView("busi/subcoll/entity/receivemoney/revokeMoneyMain");
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        page.activeCommand();
        String billClass = IDict.K_BILL_CLASS.K_ENTY_BILL;
        if(query == null){
            query=new SubcollQueryCondition();
        }
        try {
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "revokeBillList", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
	        query.addSqlPropretyMapping("statusArray", "operStatus");
	        query.setBillClass(billClass);
	        query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
	        query.setGathDate(DateTimeUtil.getWorkdayString());//查询出当前营业日的记账票据
            mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(page, query));
        } catch (Exception e) {
        	e.printStackTrace();
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        mv.addObject("subcollId", query.getSubcollId());
        mv.addObject("billClass", billClass);
        mv.addObject("page", page);
        return mv;
    }
    
    /**
     * 纸票退票登记管理
     * @param page
     * @param query
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=revokeBillRegister")
    public ModelAndView revokeBillRegister(Page page,SubcollQueryCondition query) throws BizAppException{
        ModelAndView mv = new ModelAndView("busi/subcoll/entity/receivemoney/revokeBillRegisterMain");
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        page.activeCommand();
        if(query == null){
            query=new SubcollQueryCondition();
        }
        String billClass = IDict.K_BILL_CLASS.K_ENTY_BILL;
        try {
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "revokeBillRegister", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
	        query.addSqlPropretyMapping("statusArray", "operStatus");
	        query.setBillClass(billClass);
	        query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
            mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(page, query));
        } catch (Exception e) {
        	e.printStackTrace();
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        mv.addObject("subcollId", query.getSubcollId());
        mv.addObject("billClass", billClass);
        mv.addObject("page", page);
        return mv;
    }
   
    
    								/**-------------纸票系统end-------------------*/
    
    
    
    								/**-------------公共方法start-------------------*/
    
    
   /**
    * 提交收回记账管理
    * @param page
    * @param ids
    * @param billClass
    * @return
    * @throws BizAppException
    */
    @RequestMapping(params = "method=toSubmitAccount")
    public ModelAndView toSubmitAccount(Page page,String ids,String billClass) throws BizAppException{
        ModelAndView mv = new ModelAndView();
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        mv.addObject("ids",ids);
        SubcollQueryCondition  query=new SubcollQueryCondition();
        try {
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "searchBillList", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
	        query.addSqlPropretyMapping("statusArray", "operStatus");
	        query.setIds(ids.split(","));
	        query.addSpecialOpertion("ids", BaseSearchBean.IN);
	        query.addSqlPropretyMapping("ids", "subcollmxId");
            mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(new Page(), query));
        } catch (Exception e) {
        	e.printStackTrace();
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			mv.setViewName("busi/subcoll/entity/receivemoney/receiveMoneyConfirm");
		}else{
			mv.setViewName("busi/subcoll/electronic/elecReceivemoney/elecReceiveMoneyConfirm");
		}
        mv.addObject("billClass", billClass);
        mv.addObject("page", page);
        return mv;
    }
    /**
     * 提交收回记账
     * @param ids
     * @param billClass
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=submitAccount")
    public ModelAndView submitAccount(String ids,String billClass) throws BizAppException{
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            subcollService.submitReceiveMoneyAcct(ids);
            session.endTransaction();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
                session.rollback();
            } catch (SQLException e1) {
            	CommonLog.getCommonLogCache().errorMessage("事物回滚失败");
   			 	throw new BizAppException(ISysErrorNo.ERR_DBERR, "事物回滚失败");
            }
            CommonLog.getCommonLogCache().errorMessage("提交收回记账失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "提交收回记账失败");
        }
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
        	return searchBillList(new Page(), null);
		}else{
			return elecSearchBillList(new Page(), null);
		}
        
    }
    /**
    * 提交撤销记账管理
    * @param page
    * @param ids
    * @param billClass
    * @return
    * @throws BizAppException
    */
    @RequestMapping(params = "method=toRevokeAccount")
    public ModelAndView toRevokeAccount(Page page,String ids,String billClass) throws BizAppException{
        ModelAndView mv = new ModelAndView();
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        SubcollQueryCondition  query=new SubcollQueryCondition();
        try {
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "revokeBillList", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setIds(ids.split(","));
		    query.addSpecialOpertion("ids", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("ids", "subcollmxId");
       	 	mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(new Page(), query));
        } catch (Exception e) {
        	e.printStackTrace();
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
		mv.setViewName("busi/subcoll/entity/receivemoney/revokeMoneyConfirm");
		mv.addObject("ids",ids);
        mv.addObject("billClass", billClass);
        mv.addObject("page", page);
        return mv;
    }
    
    /**
     * 提交撤销记账
     * @param ids
     * @param billClass
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=submitRevokeAccount")
    public ModelAndView submitRevokeAccount(String ids,String billClass) throws BizAppException{
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            subcollService.submitRevokeMoneyAcct(ids);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
            	CommonLog.getCommonLogCache().errorMessage("事物回滚失败");
   			 	throw new BizAppException(ISysErrorNo.ERR_DBERR, "事物回滚失败");
            }
            e.printStackTrace();
            CommonLog.getCommonLogCache().errorMessage("提交撤销记账失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "提交撤销记账失败");
        }
        	return revokeBillList(new Page(), null);
    }
    /**
     * 提交登记管理
     * @param page
     * @param ids
     * @param query
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=toSubmitAccountRegister")
    public ModelAndView toSubmitAccountRegister(Page page,String ids,SubcollQueryCondition query) throws BizAppException{
        ModelAndView mv = new ModelAndView();
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        if(query==null){
        	query=new SubcollQueryCondition();
        }
        String billNo = query.getBillNo();//防止点击返回按钮回显时票号为模糊查询状态，例如输入票号为2，但是回显为%2%
        try {
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "receiveMoneyRegister", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setIds(ids.split(","));
		    query.addSpecialOpertion("ids", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("ids", "subcollmxId");
            mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(new Page(), query));
        } catch (Exception e) {
        	e.printStackTrace();
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(query.getBillClass())){
			mv.setViewName("busi/subcoll/entity/receivemoney/receiveMoneyRegisterConfirm");
		}else{
			mv.setViewName("busi/subcoll/electronic/elecReceivemoney/elecReceiveMoneyRegisterConfirm");
		}
        mv.addObject("ids",ids);
        mv.addObject("billClass",query.getBillClass());
        mv.addObject("billNo",billNo);//防止点击返回按钮回显时票号为模糊查询状态，例如输入票号为2，但是回显为%2%
        mv.addObject("query",query);
        mv.addObject("page",page);
        return mv;
    }
    
    /**
     * 提交登记
     * @param ids
     * @param billClass
     * @param query
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=submitAccountRegister")
    public ModelAndView submitAccountRegister(String ids,String billClass ,SubcollQueryCondition query) throws BizAppException{
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            subcollService.submitReceiveMoneyAcctRegister(ids);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
            	CommonLog.getCommonLogCache().errorMessage("事物回滚失败");
   			 	throw new BizAppException(ISysErrorNo.ERR_DBERR, "事物回滚失败");
            }
            e.printStackTrace();
            CommonLog.getCommonLogCache().errorMessage("提交登记失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "提交登记失败");
        }
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
        	return receiveMoneyRegister(new Page(), query);
		}else{
			return elecReceiveMoneyRegister(new Page(), query);
		}
    }

    /**
	 * 数据库票据查看
	 * @param subcollmxId
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=goSqlDetail")
	public ModelAndView goSqlDetail(String subcollmxId)  throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/subcoll/entity/subcoll_batch_detail");
		ISubcollService subcollService = ServiceFactory.getSubcollService();
		SubcollBillInfo subcollBillInfo =new SubcollBillInfo();
		try {
			subcollBillInfo= subcollService.getSubcollBillInfo(subcollmxId);
			mv.addObject("resultList",subcollBillInfo);
		} catch (BizAppException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
		return mv;
	}
	
	/**
	 * 提交退票登记管理
	 * @param page
	 * @param ids
	 * @param billClass
	 * @return
	 * @throws BizAppException
	 */
    @RequestMapping(params = "method=toSubmitrevokeBillRegister")
    public ModelAndView toSubmitrevokeBillRegister(Page page,String ids,String billClass) throws BizAppException{
        ModelAndView mv = new ModelAndView();
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        SubcollQueryCondition  query=new SubcollQueryCondition();
        try {
        	if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
            	query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "revokeBillRegister", null));
            }else{
            	query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "elecRevokeBillRegister", null));
            }
            query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
            query.addSqlPropretyMapping("statusArray", "operStatus");
            query.setIds(ids.split(","));
            query.addSpecialOpertion("ids", BaseSearchBean.IN);
            query.addSqlPropretyMapping("ids", "subcollmxId");
            mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(new Page(), query));
        } catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
			mv.setViewName("busi/subcoll/entity/receivemoney/submitrevokeBillRegister");
		}else{
			mv.setViewName("busi/subcoll/electronic/elecReceivemoney/elecSubmitrevokeBillRegister");
		}
        mv.addObject("ids",ids);
        mv.addObject("billClass",billClass);
        mv.addObject("page",page);
        return mv;
    }
	
    /**
     * 提交退票
     * @param ids
     * @param billClass
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=revokeBill")
    public ModelAndView revokeBill(String ids,String billClass) throws BizAppException{
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            subcollService.submitRevokeBillRegister(ids);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
            	CommonLog.getCommonLogCache().errorMessage("事物回滚失败");
   			 	throw new BizAppException(ISysErrorNo.ERR_DBERR, "事物回滚失败");
            }
            e.printStackTrace();
            CommonLog.getCommonLogCache().errorMessage("退票登记失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "退票登记失败");
        }
        if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
        	return revokeBillRegister(new Page(), null);
		}else{
			return elecRevokeBillRegister(new Page(), null);
		}
        
    }
    
    
    										
    										/**-------------公共方法end-------------------*/
    
    
    										/**-------------电票系统start-------------------*/
    
    /**
     * 电票收款登记管理
     * @param page
     * @param query
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=elecReceiveMoneyRegister")
    public ModelAndView elecReceiveMoneyRegister(Page page,SubcollQueryCondition query) throws BizAppException{
    	
        ModelAndView mv = new ModelAndView("busi/subcoll/electronic/elecReceivemoney/elecReceiveMoneyRegisterMain");
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        UserLoginfo user = ResourceUtil.getSessionLoginfo();
        page.activeCommand();
        if(query == null){
            query=new SubcollQueryCondition();
        }
        String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
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
        if(bigMoney != null){
        	sm = nf.format(smallMoney);
        }
        String billType = query.getBillType();
        String busiDate = DateTimeUtil.getWorkdayString();
        String startDay = null;
        String endDay = null;
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
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "receiveMoneyRegister", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
	        query.addSqlPropretyMapping("statusArray", "operStatus");
	        query.setBillClass(billClass);
	        query.setBranchNo(user.getBranchNo());
	        query.setIds(null);
            mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(page, query));
        } catch (Exception e) {
        	e.printStackTrace();
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        mv.addObject("billNo", billNo);
        mv.addObject("bigMoney", bm);
        mv.addObject("smallMoney", sm);
        mv.addObject("billType", billType);
        mv.addObject("billClass", billClass);
        mv.addObject("startDay", startDay);
		mv.addObject("endDay", endDay);
		mv.addObject("branchNo", user.getBranchNo());
        mv.addObject("subcollId", query.getSubcollId());
        mv.addObject("page", page);
        return mv;
    }
    
    /**
     * 电票收回记账管理
     * @param page
     * @param query
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=elecSearchBillList")
    public ModelAndView elecSearchBillList(Page page,SubcollQueryCondition query) throws BizAppException{
        ModelAndView mv = new ModelAndView("busi/subcoll/electronic/elecReceivemoney/elecReceiveMoneyMain");
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        page.activeCommand();
        String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
        if(query == null){
            query=new SubcollQueryCondition();
        }
        try {
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "searchBillList", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
	        query.addSqlPropretyMapping("statusArray", "operStatus");
	        query.setBillClass(billClass);
	        query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
            mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(page, query));
        } catch (Exception e) {
        	e.printStackTrace();
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        mv.addObject("subcollId", query.getSubcollId());
        mv.addObject("billClass", billClass);
        mv.addObject("page", page);
        return mv;
    }
    
    /**
     * 电票退票登记管理
     * @param page
     * @param query
     * @return
     * @throws BizAppException
     */
    @RequestMapping(params = "method=elecRevokeBillRegister")
    public ModelAndView elecRevokeBillRegister(Page page,SubcollQueryCondition query) throws BizAppException{
        ModelAndView mv = new ModelAndView("busi/subcoll/electronic/elecReceivemoney/elecRevokeBillRegisterMain");
        ISubcollService subcollService = ServiceFactory.getSubcollService();
        page.activeCommand();
        if(query == null){
            query=new SubcollQueryCondition();
        }
        String billClass = IDict.K_BILL_CLASS.K_ELEC_BILL;
        try {
			query.setStatusArray(StatusUtils.queryStatus("ReceiveMoneyController", "elecRevokeBillRegister", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		    query.addSqlPropretyMapping("statusArray", "operStatus");
		    query.setBillClass(billClass);
		    query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
       	 	mv.addObject("billList",subcollService.getSubcollBillsBySearchBean(page, query));
        } catch (Exception e) {
        	e.printStackTrace();
        	CommonLog.getCommonLogCache().errorMessage("查询subcollBillInfo失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询subcollBillInfo失败");
		}
        mv.addObject("subcollId", query.getSubcollId());
        mv.addObject("billClass", billClass);
        mv.addObject("page", page);
        return mv;
    }								
    										
    
    										/**-------------电票系统end-------------------*/
}
