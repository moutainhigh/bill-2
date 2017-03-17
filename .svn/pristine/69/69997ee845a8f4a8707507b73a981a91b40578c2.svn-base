/********************************************
 * 文件名称: BbspProductController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-12 下午02:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.ArrayList;
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

import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.BbspProduct;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBbspProductService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：BbspProductController
 * 创建人：dq
 * 创建时间：2016年7月12日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/bbspProductController")
public class BbspProductController extends BaseController{
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		Map<String, String> mapField = new HashMap<String,String>();
		
		String prodType = request.getParameter("prodType");
		String prodName = request.getParameter("prodName");
		String prodStatus = request.getParameter("prodStatus");
		String order_by = request.getParameter("order_by");
		
		String currentPage = request.getParameter("page.currentPage");
		if(!StringUtils.isEmpty(currentPage)){
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		
		String showCount = request.getParameter("page.showCount");
		if(!StringUtils.isEmpty(showCount)){
			page.setShowCount(Integer.parseInt(showCount));
		}
		
		String totalResult = request.getParameter("page.totalResult");
		if(!StringUtils.isEmpty(totalResult)){
			page.setTotalResult(Integer.parseInt(totalResult));
		}
		
		HsSqlString dbSql = new HsSqlString("tBBSP_PRODUCT");
		if (!StringUtils.isEmpty(prodType)){
			dbSql.setWhere("prod_type like '%" + prodType + "%'");
		}
		
		if (!StringUtils.isEmpty(prodName)){
			dbSql.setWhere("prod_name like '%" + prodName + "%'");
		}
		
		if (!StringUtils.isEmpty(prodStatus)){
			dbSql.setWhere("prod_status like '%" + prodStatus + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<BbspProduct> resultList = new ArrayList<BbspProduct>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), BbspProduct.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	page.activeCommand();
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}
    	
    	mapField.put("prodType", prodType);
    	mapField.put("prodName", prodName);
    	mapField.put("prodStatus", prodStatus);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/bbspProduct/bbspProduct_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		
		//取用户处理类
		IBbspProductService bbspProductService = ServiceFactory.getBbspProductService();
		
        BbspProduct bbspProduct = new BbspProduct();
		String prodName = request.getParameter("prodName");
		String prodNo = request.getParameter("prodNo");
		String prodType = request.getParameter("prodType");
		String prodStatus = request.getParameter("prodStatus");
		String createDate = request.getParameter("createDate");
		String createUserName = request.getParameter("createUserName");
		String createUserNo = request.getParameter("createUserNo");
		String prodCode = request.getParameter("prodCode");
		String branchsPower = request.getParameter("branchsPower");
		String isedit = request.getParameter("isedit");
		
		bbspProduct.setProdName(prodName);
		bbspProduct.setProdNo(prodNo);
		bbspProduct.setProdType(prodType);
		bbspProduct.setProdStatus(prodStatus);
		bbspProduct.setCreateDate(createDate);
		bbspProduct.setCreateUserName(createUserName);
		bbspProduct.setCreateUserNo(createUserNo);
		bbspProduct.setProdCode(prodCode);
		bbspProduct.setBranchsPower(branchsPower);
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				bbspProductService.modifyBbspProduct(bbspProduct);
			}else{
				bbspProductService.addBbspProduct(bbspProduct);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tBBSP_PRODUCT失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(params="method=toAdd")
	public ModelAndView toAdd(Page page){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("system/bbspProduct/bbspProduct_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 判断产品编号是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String prodNo = request.getParameter("prodNo");
		
		String sql = "select count(*) from tBBSP_PRODUCT where prod_no = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, prodNo);
    		if (count >= 1){
    			retJson.setSuccess(false);
    		}
    		
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		
		return retJson;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		
		String prodNo = request.getParameter("prodNo");
		String sql = "select * from tBBSP_PRODUCT where prod_no = ?";
		
		BbspProduct bbspProduct = new BbspProduct();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		bbspProduct = session.getObject(sql, BbspProduct.class, prodNo); 
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/bbspProduct/bbspProduct_edit");
		mv.addObject("bbspProduct", bbspProduct);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(params="method=del")
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String bbspProduct_ids = request.getParameter("bbspProduct_ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(bbspProduct_ids));
		
		String sql = "delete from tBBSP_PRODUCT where prod_no in(" + sqlInParts+  ")";
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		
		try{
			session.beginTransaction();
			
    		session.execute(sql);
    		
    		session.endTransaction();
    		
		} catch (SQLException e){
			//e.printStackTrace();	
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		
		return retJson;
	}
}
