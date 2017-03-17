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
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.sale.ISaleService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.parser.exception.ParseException;
/********************************************
 * 文件名称: SaleAuditController.java
 * 系统名称: 基础技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 转卖审核处理类
 * 系统版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-8-15
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期     修改人员        修改说明
 *********************************************/
@Scope("prototype")
@Controller
@RequestMapping("/saleAuditController")
public class SaleAuditController extends BaseController {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 待审核批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=auditList")
	public ModelAndView auditList(Page page,SaleSearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/entity/audit/sale_audit_batch_list");
		page.activeCommand();
		ISaleService saleService = ServiceFactory.getISaleService();
		if(searchBean==null){
			searchBean = new SaleSearchBean();
		}
		try {
			searchBean.setDfaultSrchTbAliasName("apply");
			searchBean.setOperStatus(StatusUtils.queryStatus("SaleAuditController", "auditList", null)[0]);
			searchBean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
			mv.addObject("batchList", saleService.getSaleApplyListForCondition(page,searchBean));
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException("批次列表查询失败,"+e.getMessage());
		}
		
		mv.addObject("page", page);
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
	public ModelAndView auditDetailList(Page page,SaleSearchBean query,String flag) throws BizAppException{
		ISaleService saleService = ServiceFactory.getISaleService();
		page.activeCommand();
		ModelAndView mv = new ModelAndView("busi/sale/entity/audit/sale_audit_bill_list");
		try {
			mv.addObject("batch",saleService.getSaleApplyInfo(query));
			query.setDfaultSrchTbAliasName("bill");
			query.addSqlPropretyMapping("statusArray", "operStatus");
			query.addSpecialOpertion("statusArray", BaseSearchBean.IN);
			query.setStatusArray(StatusUtils.queryStatus("SaleAuditController", "auditDetailList", null));
			mv.addObject("resultList",saleService.getSaleBillListBySearchBeanForPage(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待审核票据列表查询失败");
			throw new BizAppException("待审核票据列表查询失败,"+e.getMessage());
		}
		mv.addObject("flag", flag);
		mv.addObject("page", page);
		return mv;
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
		ModelAndView mv = new ModelAndView("busi/sale/entity/audit/sale_audit");
		mv.addObject("ids", ids);
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
	public AjaxJson audit(String ids,String status,String option) throws BizAppException{
		AjaxJson json = new AjaxJson();
		ISaleService saleService = ServiceFactory.getISaleService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			saleService.submitSaleAudit(CommUtils.couvertLong(ids),status,option);
			session.endTransaction();
			json.setSuccess(true);
			json.setMsg("提交审核成功");
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据审核提交失败");
			throw new BizAppException("票据审核提交失败,"+e.getMessage());
		}
		
		return json;
	}
	
	
	/**
	 * 票据查看
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=goDetail")
	public ModelAndView goDetail(String billNo,String salemxId){
		ModelAndView mv = new ModelAndView("busi/sale/common/bill_detail");
		mv.addObject("saleNos", billNo);
		return mv;
	}
	/*****撤销审核 begin**********************************************************************************************************************/
	/**
	 * 获取待撤销审核的批次列表
	 * @param page
	 * @param searchBean
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=cancelAuditBatchList")
	public ModelAndView cancelAuditBatch(Page page,SaleSearchBean bean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/entity/audit/sale_cancel_audit_batch_list");
		ISaleService saleService = ServiceFactory.getISaleService();
        page.activeCommand();
        if(bean == null){
            bean=new SaleSearchBean();
        }
        
        try {
        	bean.setStatusArray(StatusUtils.queryStatus("SaleAuditController", "cancelAuditList", null));
        	bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
        	bean.addSqlPropretyMapping("statusArray", "operStatus");
        	bean.addProperty2TableObj("statusArray", "bill");
        	UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
        	bean.setAuditOperNo(userLogonInfo.getUserNo());
        	bean.addProperty2TableObj("auditOperNo", "bill");
//        	bean.setBranchNo(userLogonInfo.getBranchNo());
        	bean.setBillStorageBrchno(userLogonInfo.getBranchNo());
        	bean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
        	bean.setDfaultSrchTbAliasName("apply");
            List<SaleApplyInfo> batchList=saleService.getSaleApplyListForCondition(page, bean);
            mv.addObject("batchList",batchList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mv.addObject("page", page);
        return mv;
	}
	/**
	 * 撤销审核：去批次清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=cancelAuditBillManage")
	public ModelAndView cancelAuditBillManage(Page page,SaleSearchBean query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/sale/entity/audit/sale_cancel_audit_bill_list");
		page.activeCommand();
		ISaleService saleService = ServiceFactory.getISaleService();
		try {
			query.setDfaultSrchTbAliasName("bill");
			query.setOperStatus(StatusUtils.queryStatus("SaleAuditController", "cancelAuditList", null)[0]);
			mv.addObject("batch",saleService.getSaleApplyInfo(query));
			mv.addObject("resultList",saleService.getSaleBillListBySearchBeanForPage(page,query));
		}catch(Exception e){
			CommonLog.getCommonLogCache().errorMessage("批次清单列表查询失败");
			throw new BizAppException("批次清单列表查询失败，"+e.getMessage());
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	/**
	 * 撤销审核
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=cancelAudit")
	public ModelAndView cancelAudit(String ids,String saleId) throws BizAppException{
		ISaleService saleService = ServiceFactory.getISaleService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			saleService.cancelAudit(ids,saleId);
			session.endTransaction();
			return cancelAuditBatch(new Page(), new SaleSearchBean());
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
}
