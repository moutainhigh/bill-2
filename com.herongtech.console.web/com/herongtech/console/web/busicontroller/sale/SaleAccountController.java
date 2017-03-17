package com.herongtech.console.web.busicontroller.sale;

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
import com.herongtech.console.core.util.DateTimeUtil;
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
import com.herongtech.parser.exception.ParseException;

/********************************************
 * 文件名称: SaleAccountController.java
 * 系统名称: 基础技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 转卖记账处理类
 * 系统版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-8-15
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期     修改人员        修改说明
 * 20160815-01   fqz  创建  
 *********************************************/
@Scope("prototype")
@Controller
@RequestMapping("/saleAccountController")
public class SaleAccountController extends BaseController {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
    /**
     * 查询待记账的批次列表
     * @param page
     * @param bean 筛选条件
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=queryApplyForSaleAcct")
    public ModelAndView queryApplyForSaleAcct(Page page,SaleSearchBean bean) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/entity/account/sale_account_batch_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        if(bean == null){
            bean=new SaleSearchBean();
        }
        //bean.setSaleType(RcConstants.REGRESS_NO);
        bean.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "queryApplyForSaleAcct", null));
        bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        bean.addSqlPropretyMapping("statusArray", "operStatus");
        bean.addProperty2TableObj("statusArray", "bill");
        UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
//        bean.setBranchNo(userLogonInfo.getBranchNo());
        bean.setBillStorageBrchno(userLogonInfo.getBranchNo());
        bean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
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
     * 查询批次下的待记账清单列表
     * @param batchId 批次ID
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toSubmitSaleAccount")
    public ModelAndView toSubmitSaleAccount(String batchId,Page page) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/entity/account/sale_account_bill_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        SaleSearchBean  query=new SaleSearchBean();
        query.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "queryApplyForSaleAcct", null));
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
     * 转卖记账
     * @param ids
     * @throws Exception
     */
    @RequestMapping(params = "method=submitSaleAccount")
    public ModelAndView submitSaleAccount(String ids,String batchId) throws Exception{
        ISaleService saleService = ServiceFactory.getISaleService();
        UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            saleService.submitSaleAccount(batchId,ids.split(","),userLogonInfo);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw e;
        }
        return queryApplyForSaleAcct(new Page(),new SaleSearchBean());
    }
    /*****撤销记账 begin**********************************************************************************************************************/
    /**
	 * 获取待撤销记账的批次列表
	 * @param page
	 * @param searchBean
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=cancelAccountBatchList")
	public ModelAndView cancelAccountBatch(Page page,SaleSearchBean bean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/entity/account/sale_cancel_account_batch_list");
		ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        if(bean == null){
            bean=new SaleSearchBean();
        }
        
        try {
        	bean.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "cancelAccountList", null));
        	bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        	bean.addSqlPropretyMapping("statusArray", "operStatus");
        	bean.addProperty2TableObj("statusArray", "bill");
        	UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
        	bean.addProperty2TableObj("acctOperNo", "bill");
        	bean.addProperty2TableObj("accountDate", "bill");
        	bean.setAcctOperNo(userLogonInfo.getUserNo());
        	bean.setAccountDate(DateTimeUtil.getWorkdayString());
//        	bean.setBranchNo(userLogonInfo.getBranchNo());
        	bean.setBillStorageBrchno(userLogonInfo.getBranchNo());
        	bean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
        	bean.setDfaultSrchTbAliasName("apply");
            List<SaleApplyInfo> batchList=saleService.getSaleApplyListForCondition(page, bean);
            mv.addObject("batchList",batchList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizAppException("查询待撤销批次列表失败，"+e.getMessage());
        }
        
        mv.addObject("page", page);
        return mv;
	}
	/**
	 * 撤销记账：去批次清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelAccountBillManage")
	public ModelAndView cancelAccountBillManage(Page page,String batchId) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/entity/account/sale_cancel_account_bill_list");
		page.activeCommand();
		ISaleService saleService = ServiceFactory.getISaleService();
		SaleSearchBean query = new SaleSearchBean();
		try {
			query.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "cancelAccountList", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
			query.addSqlPropretyMapping("statusArray", "operStatus");
			query.setSaleId(batchId);
			query.setDfaultSrchTbAliasName("bill");
			query.setAccountDate(DateTimeUtil.getWorkdayString());
			SaleApplyInfo saleApply= saleService.getSaleApplyInfo(query);
			List<SaleBillInfo> billList=saleService.getSaleBillListBySearchBeanForPage(page, query);
			mv.addObject("batch",saleApply);
			mv.addObject("billList",billList);
		}catch(Exception e){
			CommonLog.getCommonLogCache().errorMessage("批次清单列表查询失败");
			throw new BizAppException("批次清单列表查询失败，"+e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("batchId", batchId);
		return mv;
	}
	/**
	 * 撤销记账
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelAccount")
	public ModelAndView cancelAccount(String batchId,String ids) throws BizAppException{
		ISaleService saleService = ServiceFactory.getISaleService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			saleService.cancelAccount(batchId, ids);
			session.endTransaction();
			return cancelAccountBatch(new Page(), new SaleSearchBean());
		}  catch (Exception e) {
			e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("撤销票据转卖申请失败");
			throw new BizAppException("撤销票据转卖申请失败,"+e.getMessage());
		}
	}
	/***撤回  begin****************************************************************************************************/
	/**
	 * 撤回：多岗的情况下撤回审核，将票撤销到提交完成状态
	 * @param batchId
	 * @param ids
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params = "method=cancel")
	public ModelAndView cancel(String batchId,String ids) throws BizAppException{
		ISaleService saleService = ServiceFactory.getISaleService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			saleService.cancelAccount(batchId, ids);
			session.endTransaction();
			return cancelAccountBatch(new Page(), new SaleSearchBean());
		}  catch (Exception e) {
			e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("撤回失败");
			throw new BizAppException("撤回失败,"+e.getMessage());
		}
	}
	
	/***电票记账  begin****************************************************************************************************/
	
	/**
     * 查询待记账的批次列表
     * @param page
     * @param bean 筛选条件
     * @return
     * @throws Exception
     */
    @RequestMapping(params ="method=elecQueryApplyForSaleAcct")
    public ModelAndView elecQueryApplyForSaleAcct(Page page,SaleSearchBean bean) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/electronic/account/sale_account_batch_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        if(bean == null){
            bean=new SaleSearchBean();
        }
        bean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        bean.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "queryApplyForElecSaleAccount", null));
        bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        bean.addSqlPropretyMapping("statusArray", "operStatus");
        bean.addProperty2TableObj("statusArray", "bill");
        UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
//        bean.setBranchNo(userLogonInfo.getBranchNo());
        bean.setBillStorageBrchno(userLogonInfo.getBranchNo());
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
     * 查询批次下的待记账清单列表
     * @param batchId 批次ID
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toSubmitElecSaleAccount")
    public ModelAndView toSubmitElecSaleAccount(String batchId,Page page) throws Exception{
        ModelAndView mv = new ModelAndView("busi/sale/electronic/account/sale_account_bill_list");
        ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        SaleSearchBean  query=new SaleSearchBean();
        query.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "queryApplyForElecSaleAccount", null));
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
     * 电票转卖记账
     * @param ids
     * @throws Exception
     */
    @RequestMapping(params = "method=submitElecSaleAccount")
    public ModelAndView submitElecSaleAccount(String ids,String batchId) throws Exception{
        ISaleService saleService = ServiceFactory.getISaleService();
        UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            saleService.submitSaleAccount(batchId,ids.split(","),userLogonInfo);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw e;
        }
        return elecQueryApplyForSaleAcct(new Page(),new SaleSearchBean());
    }
    
    
	//电票撤销记账废弃
	 /*****撤销记账 begin**********************************************************************************************************************/
    /**
	 * 获取待撤销记账的批次列表
	 * @param page
	 * @param searchBean
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecQueryApplyForCancelSaleAcct")
	public ModelAndView elecQueryApplyForCancelSaleAcct(Page page,SaleSearchBean bean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/electronic/account/sale_cancel_account_batch_list");
		ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        if(bean == null){
            bean=new SaleSearchBean();
        }
        
        try {
        	bean.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "queryApplyForCancelElecSaleAccount", null));
        	bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        	bean.addSqlPropretyMapping("statusArray", "operStatus");
        	bean.addProperty2TableObj("statusArray", "bill");
        	UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
        	bean.addProperty2TableObj("acctOperNo", "bill");
        	bean.setAcctOperNo(userLogonInfo.getUserNo());
//        	bean.setBranchNo(userLogonInfo.getBranchNo());
        	bean.setBillStorageBrchno(userLogonInfo.getBranchNo());
        	bean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        	bean.setDfaultSrchTbAliasName("apply");
            List<SaleApplyInfo> batchList=saleService.getSaleApplyListForCondition(page, bean);
            mv.addObject("batchList",batchList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizAppException("查询待撤销批次列表失败，"+e.getMessage());
        }
        
        mv.addObject("page", page);
        return mv;
	}
	/**
	 * 撤销记账：去批次清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelAccountElecBillManage")
	public ModelAndView cancelAccountElecBillManage(Page page,String batchId) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/electronic/account/sale_cancel_account_bill_list");
		page.activeCommand();
		ISaleService saleService = ServiceFactory.getISaleService();
		SaleSearchBean query = new SaleSearchBean();
		try {
			query.setStatusArray(StatusUtils.queryStatus("SaleAccountController", "queryApplyForCancelElecSaleAccount", null));
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
			query.addSqlPropretyMapping("statusArray", "operStatus");
			query.setSaleId(batchId);
			query.setAcctOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			query.setDfaultSrchTbAliasName("bill");
			SaleApplyInfo saleApply= saleService.getSaleApplyInfo(query);
			List<SaleBillInfo> billList=saleService.getSaleBillListBySearchBeanForPage(page, query);
			mv.addObject("batch",saleApply);
			mv.addObject("billList",billList);
		}catch(Exception e){
			CommonLog.getCommonLogCache().errorMessage("批次清单列表查询失败");
			throw new BizAppException("批次清单列表查询失败，"+e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("batchId", batchId);
		return mv;
	}
	/**
	 * 电票撤销记账
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=elecCancelAccount")
	public ModelAndView elecCancelAccount(String batchId,String ids) throws BizAppException{
		ISaleService saleService = ServiceFactory.getISaleService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			saleService.cancelAccount(batchId, ids);
			session.endTransaction();
			return elecQueryApplyForCancelSaleAcct(new Page(), new SaleSearchBean());
		}  catch (Exception e) {
			e.printStackTrace();
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("撤销票据转卖申请失败");
			throw new BizAppException("撤销票据转卖申请失败,"+e.getMessage());
		}
	}
}
