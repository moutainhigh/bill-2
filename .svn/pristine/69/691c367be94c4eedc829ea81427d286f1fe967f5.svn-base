package com.herongtech.console.web.busicontroller.sale;

import java.sql.SQLException;
import java.util.List;

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
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.bean.SaleSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.sale.ISaleService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 转出电票背书Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/saleEndorseController")
public class SaleEndorseController extends BaseController {
/***电票转卖背书  begin******************************************************************************************************/
	/**
     * 查询查询待背书批次列表
     * @param page
     * @param bean 筛选条件
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=queryForSaleEndorse")
    public ModelAndView queryForSaleEndorse(Page page,SaleSearchBean bean) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/electronic/endorse/sale_endorse_batch_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        if(bean == null){
            bean=new SaleSearchBean();
        }
        
        bean.setStatusArray(StatusUtils.queryStatus("SaleEndorseController", "queryForSaleEndorse", null));
        bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        bean.addSqlPropretyMapping("statusArray", "operStatus");
        bean.addProperty2TableObj("statusArray", "bill");
        UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
        bean.setBillStorageBrchno(userLogonInfo.getBranchNo());
        bean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        bean.setDfaultSrchTbAliasName("apply");
        try {
            List<SaleApplyInfo> batchList=saleService.getSaleApplyListForCondition(page, bean);
            mv.addObject("batchList",batchList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mv.addObject("page", page);
        return mv;
    }
    
    /**
     * 查询批次下的待背书清单列表
     * @param batchId 批次ID
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=queryBillList")
    public ModelAndView queryBillList(String saleId,Page page) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/electronic/endorse/sale_endorse_bill_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        SaleSearchBean  query=new SaleSearchBean();
        query.setStatusArray(StatusUtils.queryStatus("SaleEndorseController", "queryForSaleEndorse", null));
        query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        query.addSqlPropretyMapping("statusArray", "operStatus");
        query.setSaleId(saleId);
        query.setDfaultSrchTbAliasName("bill");
        try {
            SaleApplyInfo saleApply= saleService.getSaleApplyInfo(query);
            
            List<SaleBillInfo> billList=saleService.getSaleBillListBySearchBeanForPage(page, query);
            mv.addObject("batch",saleApply);
            mv.addObject("billList",billList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("batchId",saleId);
        return mv;
    }
    
    
    /**
     * 转卖背书
     * @param ids
     * @throws Exception
     */
    @RequestMapping(params = "method=submitSaleEndorse")
    public ModelAndView submitSaleEndorse(String ids,String batchId) throws Exception{
        ISaleService saleService = ServiceFactory.getISaleService();
        UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
        try {
            saleService.submitSaleEndorse(batchId,ids.split(","),userLogonInfo);
        } catch (Exception e) {
        	CommonLog.getCommonLogCache().errorMessage("背书:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "背书失败:"+e.getMessage());
        }
        return queryForSaleEndorse(new Page(),new SaleSearchBean());
    }
    
    
    /***撤销电票转卖背书  begin******************************************************************************************************/
    /**
     * 查询待撤销背书批次列表
     * @param page
     * @param bean 筛选条件
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=queryForCancelSaleEndorse")
    public ModelAndView queryForCancelSaleEndorse(Page page,SaleSearchBean bean) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/electronic/endorse/sale_cancel_endorse_batch_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        if(bean == null){
            bean=new SaleSearchBean();
        }
        
        bean.setStatusArray(StatusUtils.queryStatus("SaleEndorseController", "queryForCancelSaleEndorse", null));
        bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        bean.addSqlPropretyMapping("statusArray", "operStatus");
        bean.addProperty2TableObj("statusArray", "bill");
        UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
        bean.setBillStorageBrchno(userLogonInfo.getBranchNo());
        bean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        bean.setDfaultSrchTbAliasName("apply");
        try {
            List<SaleApplyInfo> batchList=saleService.getSaleApplyListForCondition(page, bean);
            mv.addObject("batchList",batchList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mv.addObject("page", page);
        return mv;
    }
    
    /**
     * 查询批次下的待撤销背书清单列表
     * @param batchId 批次ID
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=queryCancelBillList")
    public ModelAndView queryCancelBillList(String batchId,Page page) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/electronic/endorse/sale_cancel_endorse_bill_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        SaleSearchBean  query=new SaleSearchBean();
        query.setStatusArray(StatusUtils.queryStatus("SaleEndorseController", "queryForCancelSaleEndorse", null));
        query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        query.addSqlPropretyMapping("statusArray", "operStatus");
        query.setSaleId(batchId);
        query.setDfaultSrchTbAliasName("bill");
        
        try {
            SaleApplyInfo saleApply= saleService.getSaleApplyInfo(query);
            
            List<SaleBillInfo> billList=saleService.getSaleBillListBySearchBeanForPage(page, query);
            mv.addObject("batch",saleApply);
            mv.addObject("resultList",billList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("batchId",batchId);
        return mv;
    }
    
    
    /**
     * 撤销转卖背书
     * @param ids
     * @throws Exception
     */
    @RequestMapping(params = "method=cancelElecSaleEndorse")
    public ModelAndView cancelElecSaleEndorse(String ids,String batchId) throws Exception{
        ISaleService saleService = ServiceFactory.getISaleService();
        try {
            saleService.cancelElecSaleEndorse(batchId,ids.split(","));
        } catch (Exception e) {
        	e.printStackTrace();
			throw new BizAppException("撤销转卖背书失败，"+e.getMessage());
        }
        return queryForCancelSaleEndorse(new Page(),new SaleSearchBean());
    }
    /***确认背书  begin******************************************************************************************************/
    /**
     * 查询待背书确认签收批次列表
     * @param page
     * @param bean 筛选条件
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=queryForConfirmSaleEndorse")
    public ModelAndView queryForConfirmSaleEndorse(Page page,SaleSearchBean bean) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/electronic/endorse/sale_endorse_confirm_batch_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        if(bean == null){
            bean=new SaleSearchBean();
        }
        bean.setIsInner(RcConstants.INNER_NO);
        bean.setStatusArray(StatusUtils.queryStatus("SaleEndorseController", "queryForConfirmSign", null));
        bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        bean.addSqlPropretyMapping("statusArray", "operStatus");
        bean.addProperty2TableObj("statusArray", "bill");
        UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
        bean.setBillStorageBrchno(userLogonInfo.getBranchNo());
        bean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        bean.setDfaultSrchTbAliasName("apply");
        try {
            List<SaleApplyInfo> batchList=saleService.getSaleApplyListForCondition(page, bean);
            mv.addObject("batchList",batchList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mv.addObject("page", page);
        return mv;
    }
    
    /**
     * 查询批次下的待背书确认签收清单列表
     * @param batchId 批次ID
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=queryConfirmEndorseBillList")
    public ModelAndView queryConfirmEndorseBillList(String batchId,Page page) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/electronic/endorse/sale_endorse_confirm_bill_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        SaleSearchBean  query=new SaleSearchBean();
        query.setStatusArray(StatusUtils.queryStatus("SaleEndorseController", "queryForConfirmSign", null));
        query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        query.addSqlPropretyMapping("statusArray", "operStatus");
        query.setSaleId(batchId);
        query.setDfaultSrchTbAliasName("bill");
        
        try {
            SaleApplyInfo saleApply= saleService.getSaleApplyInfo(query);
            
            List<SaleBillInfo> billList=saleService.getSaleBillListBySearchBeanForPage(page, query);
            mv.addObject("batch",saleApply);
            mv.addObject("billList",billList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("batchId",batchId);
        return mv;
    }
    
    
    /**
     * 背书确认签收
     * @param ids
     * @throws Exception
     */
    @RequestMapping(params = "method=confirmEndorse")
    @ResponseBody
    public AjaxJson confirmEndorse(String ids,String batchId) throws Exception{
    	AjaxJson retJson = new AjaxJson();
        ISaleService saleService = ServiceFactory.getISaleService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            boolean bool=saleService.confirmEndorse(batchId,ids.split(","));
            session.endTransaction();
            if(bool){
            	retJson.setSuccess(true);
            	retJson.setMsg("签收成功");
            }else{
            	retJson.setSuccess(false);
            	retJson.setMsg("对方还没有对信息进行处理，不能进行签收，请等待对方回复！");
            }
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw e;
        }
        return retJson;
    }
    
}
