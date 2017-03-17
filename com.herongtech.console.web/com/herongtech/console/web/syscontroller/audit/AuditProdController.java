package com.herongtech.console.web.syscontroller.audit;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.common.audit.bean.ReArProd;
import com.herongtech.console.domain.common.audit.bean.ReArProdSearchBean;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IReArProdService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 审批机构产品管理
 */
@Scope("prototype")
@Controller
@RequestMapping("/auditProdController")
public class AuditProdController extends BaseController {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView auditProdList(Page page, ReArProdSearchBean searchBean)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		Map<String, String> mapField = new HashMap<String,String>();
		IReArProdService reArProdService=ServiceFactory.getReArProdService();
		IBranchService branchService=ServiceFactory.getBranchService();
		IProductService productservice = ServiceFactory.getProductService();
		List<ReArProd> resultList = new ArrayList<ReArProd>();
    	try{
    		resultList=reArProdService.queryReBrchProd(page,searchBean);
    		for(ReArProd reArProd:resultList){
    			Branch  branch = branchService.getBranch(reArProd.getBranchNo());
    			Product prod = productservice.getProductInfoByProdNo(reArProd.getProdNo());
    			reArProd.setProduct(prod);
    			reArProd.setBranch(branch);
    		}
    		
    		
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询机构信息失败");
		}
		mv.setViewName("system/auditProd/auditProd_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		mv.addObject("searchBean", searchBean);
		
		return mv;
	}
	/**
	 * 保存
	 */
	@RequestMapping(params="method=saveAuditProd")
	public ModelAndView saveAuditProd(ReArProd reArProd,String isedit) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		
		//取审批机构产品信息处理类
		IReArProdService reArProdService=ServiceFactory.getReArProdService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
			try {
				session.beginTransaction();
				if (isedit.equals("1")){   //编辑操作
					reArProdService.editReBrchProd(reArProd);
				}else{
					reArProdService.addReBrchProd(reArProd);  //插入
				}
				session.endTransaction();
			  } catch (Exception e) {
				  throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
			}
			
		  mv.addObject("msg","success");
		  mv.setViewName("save_result");
		  return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(params="method=toAddAuditProd")
	public ModelAndView toAddAuditProd(Page page){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/auditProd/auditProd_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(params="method=toEditAuditProd")
	public ModelAndView toEditAuditProd(String id) throws BizAppException, SQLException{
		ModelAndView mv = new ModelAndView();
		IReArProdService reArProdService=ServiceFactory.getReArProdService();
		ReArProd reArProd=new ReArProd();
		try {
				reArProd = reArProdService.getReBrchProdById(id);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("数据库错误");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		mv.setViewName("system/auditProd/auditProd_edit");
		mv.addObject("reArProd", reArProd);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(params="method=delAuditProd")
	@ResponseBody
	public AjaxJson delAuditProd(String id) throws BizAppException{
		IReArProdService reArProdService=ServiceFactory.getReArProdService();
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			reArProdService.deleteReBrchProd(id);
			session.endTransaction();
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("数据库错误");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		return retJson;
	}
}
