/********************************************
 * 文件名称: DeptController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-6-15 下午01:48:52
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
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.domain.bean.Dept;
import com.herongtech.console.domain.bean.Param;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.console.service.interfaces.IDeptService;
import com.herongtech.console.service.interfaces.IParamService;
import com.herongtech.console.service.interfaces.IUserService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysConstant;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：DictionariesController
 * 创建人：FH
 * 创建时间：2014年9月2日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/deptController")
public class DeptController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=listDept")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		
		Map<String, String> mapField = new HashMap<String,String>();
		
		String dep_name = request.getParameter("dep_name");
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
		
		HsSqlString dbSql = new HsSqlString("tdept");
		if (!StringUtils.isEmpty(dep_name)){
			dbSql.setWhere("dep_name like '%" + dep_name + "%'");
		}
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<Dept> resultList = new ArrayList<Dept>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), Dept.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询部门列表失败");
		}
    	
    	mapField.put("dep_name", dep_name);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/department/department_list");
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
		
		//取部门处理类
		IDeptService deptService = ServiceFactory.getDeptService();
		Dept dept = new Dept();
		String dep_code = request.getParameter("dep_code");
		String dep_name = request.getParameter("dep_name");
		String dep_sort = request.getParameter("dep_sort");
		String dep_remark = request.getParameter("dep_remark");
		String isedit = request.getParameter("isedit");
		
		dept.setDepCode(dep_code);
		dept.setDepName(dep_name);
		dept.setShortName(dep_sort);
		dept.setRemark(dep_remark);
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				deptService.modifyParam(dept);
			}else{
				deptService.addParam(dept);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录部门失败");
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
		
		mv.setViewName("system/department/department_edit");
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
		
		String dep_code = request.getParameter("dep_code");
		
		String sql = "select count(*) from tdept where dep_code = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, dep_code);
        	
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
		
		String dep_code = request.getParameter("depCode");
		String sql = "select * from tdept where dep_code = ?";
		
		Dept dept = new Dept();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		dept = session.getObject(sql, Dept.class, dep_code);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/department/department_edit");
		mv.addObject("deptparam", dept);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
	/**
	 * 删除部门
	 */
	@RequestMapping(params="method=del")
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		String dept_ids = request.getParameter("dept_ids");
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try{
			String [] ids =CommUtils.couvertLong(dept_ids);
			for (int i = 0; i < ids.length; i++) {
				ICustManageService custM=ServiceFactory.getCustManageService();
				IUserService user=ServiceFactory.getUserService();
				if(custM.getCustManageByDeptNo(ids[i])==null&&user.getUserByDeptNo(ids[i])==null){
					String sql = "delete from tdept where dep_code='"+ ids[i]+"'";
					session.beginTransaction();
		    		session.execute(sql);
		    		session.endTransaction();
		    		retJson.setMsg("删除部门编号为"+ids[i]+"的部门成功");
		    		
				}else{
					retJson.setMsg("部门编号为"+ids[i]+"的部门还有本部门的客户经理和用户，不能删除，请确认该部门没有人员!");
				}
			}
		} catch (SQLException e){
			//e.printStackTrace();	
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库错误   ");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		
		return retJson;
	}
}

