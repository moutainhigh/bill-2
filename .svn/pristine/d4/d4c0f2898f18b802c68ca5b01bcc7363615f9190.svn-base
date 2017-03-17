/********************************************
 * 文件名称: CustManagerController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-7-12 下午02:50:52
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
import java.util.Date;
import java.text.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.herongtech.console.core.util.ContextHolderUtils;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.CustManage;

import com.herongtech.console.domain.bean.User;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.console.service.interfaces.IDeptService;
import com.herongtech.console.service.interfaces.IUserService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：CustManageController
 * 创建人：FQQ
 * 创建时间：2016年7月12日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/custManagerController")
public class CustManagerController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		Map<String, String> mapField = new HashMap<String,String>();
		page.activeCommand();
		String custManagerName = request.getParameter("custManagerName");
		String custManagerNo = request.getParameter("custManagerNo");
		String deptNo = request.getParameter("deptNo");
		String status = request.getParameter("status");
		String order_by = request.getParameter("order_by");
		HsSqlString dbSql = new HsSqlString("tcust_manage");
		if (!StringUtils.isEmpty(custManagerName)){
			dbSql.setWhere("cust_Manager_Name like '%" + custManagerName + "%'");
		}
		
		if (!StringUtils.isEmpty(custManagerNo)){
			dbSql.setWhere("cust_Manager_No like '%" + custManagerNo + "%'");
		}
		
		if (!StringUtils.isEmpty(deptNo)){
			dbSql.setWhere("dept_No like '%" + deptNo + "%'");
		}
		if (!StringUtils.isEmpty(status)){
			dbSql.setWhere("status = '" + status + "'");
			
		}
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<CustManage> resultList = new ArrayList<CustManage>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), CustManage.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询经理列表失败");
		}
    	
    	mapField.put("custManagerName", custManagerName);
    	mapField.put("custManagerNo", custManagerNo);
    	mapField.put("deptNo", deptNo);
    	mapField.put("status", status);
    	mapField.put("order_by", order_by);
		
		mv.setViewName("system/custmanager/custManager_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(CustManage custmanager,String isedit) throws BizAppException {
		ModelAndView mv = new ModelAndView();
        ICustManageService custManagerService = ServiceFactory.getCustManageService();
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				CustManage custM=custManagerService.getCustManage(custmanager.getCustManagerNo());
				custM.setCustManagerName(custmanager.getCustManagerName());
				custM.setStatus(custmanager.getStatus());
				custM.setDeptNo(custmanager.getDeptNo());
				IDeptService dept=ServiceFactory.getDeptService();
				User user = ResourceUtil.getSessionUser(); //取用户处理类
				String createOperName = user.getUserName();
				//custmanager.setId(Long.parseLong(id));
				custM.setCreateOperName(createOperName);
				custM.setBranchNo(custmanager.getBranchNo());
				custM.setDeptName(dept.getParam(custmanager.getDeptNo().toString()).getDepName());
				custManagerService.modifyCustManage(custM);
			}else{
				custManagerService.addCustManage(custmanager);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tcustmanager失败");
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
		
		mv.setViewName("system/custmanager/custManager_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 判断编码是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String custManagerNo = request.getParameter("custManagerNo");
		
		String sql = "select count(*) from tcust_manage where cust_manager_no = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, custManagerNo);
        	
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
		
		String custManagerNo = request.getParameter("custManagerNo");
		String sql = "select * from tcust_manage where cust_manager_no = ?";
		IBranchService branchService = ServiceFactory.getBranchService();
		CustManage custmanager = new CustManage();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		custmanager = session.getObject(sql, CustManage.class, custManagerNo);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/custmanager/custManager_edit");
		mv.addObject("custmanager", custmanager);
		mv.addObject("branchName", branchService.getBranch(custmanager.getBranchNo()).getBranchName());
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
		
		String custManagerNos = request.getParameter("custManagerNos");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(custManagerNos));
		
		String sql = "delete from tcust_manage where cust_manager_no in(" + sqlInParts+  ")";
		
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
	/**
	 * 根据客户账号查询客户信息
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=custMagInfo")
	@ResponseBody
	public AjaxJson custMagInfo(String custManagerNo){
		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();
		ICustManageService custManageService = ServiceFactory.getCustManageService();
		// 取客户经理基本信息
		CustManage custManage;
			try {
				custManage=custManageService.getCustManage(custManagerNo);
				if(custManage== null || custManage.getStatus().equals("0")){
					retJson.setMsg("找不到客户经理信息或客户经理不可用");
					retJson.setSuccess(false);
					return retJson;
				}
			
			} catch (BizAppException e) {
				e.printStackTrace();
				retJson.setMsg("查询数据库信息失败");
				retJson.setSuccess(false);
				e.printStackTrace();
				return retJson;
			}
		retMap.put("deptName",custManage.getDeptName());
		retMap.put("custManagerName",custManage.getCustManagerName());
		retJson.setAttributes(retMap);
		retJson.setSuccess(true);
		
		return retJson;
	}
}
