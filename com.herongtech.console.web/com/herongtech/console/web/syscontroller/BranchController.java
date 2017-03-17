/********************************************
 * 文件名称: BranchController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-12 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.text.ParseException;
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
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：BranchController
 * 创建人：fqz
 * 创建时间：2016年7月12日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/branchController")
public class BranchController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		return new ModelAndView("system/branch/branch_list");
	}
	/**
	 * branch_list.jsp中使用createListTree方法异步获取机构信息
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=listTree")
	@ResponseBody
	public List<Branch> listTree(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		String branch_no = request.getParameter("branch_no");
		String branch_name = request.getParameter("branch_name");
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
		HsSqlString dbSql = new HsSqlString("tbranch");
		if (!StringUtils.isEmpty(branch_no)){
			dbSql.setWhere("branch_no like '%" + branch_no + "%'");
		}
		
		if (!StringUtils.isEmpty(branch_name)){
			dbSql.setWhere("branch_name like '%" + branch_name + "%'");
		}
		dbSql.setOrder("branch_Level");
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<Branch> resultList = new ArrayList<Branch>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		List<Branch> list = session.getObjectListByListForPage(dbSql.getSqlString(), Branch.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	String parentBrchCode = "0";
        	if( list!=null && list.size() > 0){
        		parentBrchCode = list.get(0).getParentBrchCode();
				//mv.addObject("level", list.get(0).getBranchLevel());
			}
			sortList(resultList, list,parentBrchCode,true);
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	page.activeCommand();
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询机构信息失败");
		}
    	
		return resultList;
		
	}
	/**
	 * 通过点击页面左侧的组织机构树，在右侧显示相应的机构列表信息
	 */
	@RequestMapping(params="method=treeNodeList")
	public ModelAndView treeNodeList(String id)throws Exception{
		IBranchService branchService = ServiceFactory.getBranchService();
		List<Branch> list = new ArrayList<Branch>();
		if (!StringUtils.isBlank(id)) list.add(branchService.getBranch(id));
		List<Branch> resultList = new ArrayList<Branch>();
		
		list.addAll(branchService.getAllBranchByParentBrchCode(id));
		ModelAndView mv = new ModelAndView();
		String parentBrchCode = "0";
    	if( list!=null && list.size() > 0){
    		parentBrchCode = list.get(0).getParentBrchCode();
			mv.addObject("level", list.get(0).getBranchLevel());
		}
		sortList(resultList, list,parentBrchCode,true);
		mv.addObject("resultList",resultList);
		mv.setViewName("system/branch/branchTreeTable");
		return mv;
		
	}
	/**
	 * 保存
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(Branch branch,String isedit) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		
		//取机构信息处理类
        IBranchService branchService = ServiceFactory.getBranchService();
		
		/*Branch branch = new Branch();
		String branch_no = request.getParameter("branch_no");
		String branch_name = request.getParameter("branch_name");
		String short_name = request.getParameter("short_name");
		String branch_path = request.getParameter("branch_path");
		String branch_level = request.getParameter("branch_level");
		String parent_brch_code = request.getParameter("parent_brch_code");
		String pay_bank_no = request.getParameter("pay_bank_no");
		String elec_auth = request.getParameter("elec_auth");
		String if_effective = request.getParameter("if_effective");
		String remark1 = request.getParameter("remark1");
		String remark2 = request.getParameter("remark2");
		String remark3 = request.getParameter("remark3");
		String isedit = request.getParameter("isedit");
		
		branch.setBranchNo(branch_no);
		branch.setBranchName(branch_name);
		branch.setShortName(short_name);
		branch.setBranchPath(branch_path);
		branch.setBranchLevel(branch_level);
		branch.setParentBrchCode(parent_brch_code);
		branch.setPayBankNo(pay_bank_no);
		branch.setElecAuth(elec_auth);
		branch.setIfEffective(if_effective);
		branch.setRemark1(remark1);
		branch.setRemark2(remark2);
		branch.setRemark3(remark3);*/
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				branchService.modifyBranch(branch);
			}else{
				Branch parentBrch = branchService.getBranch(branch.getParentBrchCode());
				branch.setBranchLevel((Integer.valueOf(parentBrch.getBranchLevel())+1)+"");
				branchService.addBranch(branch);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tbranch失败");
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
	public ModelAndView toAdd(String branchNo) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		IBranchService branchService = ServiceFactory.getBranchService();
		try {
			Branch parentBrch = branchService.getBranch(branchNo);
			mv.addObject("edit", "0");
			if( parentBrch != null ){
				Branch b = new Branch();
				b.setParentBrchCode(branchNo);
				b.setParentName(parentBrch.getBranchName());
				b.setBranchLevel((Integer.valueOf(parentBrch.getBranchLevel())+1)+"");
				mv.addObject("parentBranchName",parentBrch.getBranchName());
				mv.addObject("branch", b);
				mv.addObject("edit", "1");
			}
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("查询记录tbranch失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询记录失败");
		}
		
		mv.setViewName("system/branch/branch_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 判断编码是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(String branchNo) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		String sql = "select count(*) from tbranch where branch_no = ?";
    	try{
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, branchNo);
    		if (count >= 1){
    			retJson.setSuccess(false);
    		}
		} catch (SQLException e){
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
		String branchNo = request.getParameter("branch_no");
		IBranchService branchService = ServiceFactory.getBranchService();
		Branch branch = branchService.getBranch(branchNo);
		mv.addObject("parentName", "1");
		if(branchService.getBranch(branch.getParentBrchCode())!=null){
		mv.addObject("parentBranchName", branchService.getBranch(branch.getParentBrchCode()).getBranchName());
		mv.addObject("parentName", "0");
		}
		mv.setViewName("system/branch/branch_edit");
		mv.addObject("branch", branch);
		mv.addObject("edit", "1");
		mv.addObject("isedit", "1");
		return mv;
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(params="method=del")
	@ResponseBody
	public AjaxJson del(String branchNo) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		
		try{
			session.beginTransaction();
			IBranchService branchService = ServiceFactory.getBranchService();
			if( branchService.isExists(branchNo) == 0 ){
				branchService.deleteBranch(branchNo);
				retJson.setSuccess(true);
			}else{
				retJson.setSuccess(false);
			}
			session.endTransaction();
		} catch (SQLException e){
			//e.printStackTrace();	
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库错误");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		return retJson;
	}
	@ResponseBody
	@RequestMapping(params = "method=treeData")
	public List<Map<String, Object>> treeData(HttpServletRequest request, HttpServletResponse response) throws BizAppException {
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		String sql = "select * from tbranch";
		List<Branch> list = null;
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		list = session.getObjectList(sql, Branch.class);
        	
    		for (int i=0; i<list.size(); i++){
    			Branch e = list.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", e.getBranchNo());
				map.put("pId", e.getParentBrchCode());
				map.put("name", e.getBranchName());
				mapList.add(map);
    		}
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		return mapList;
	}
	public static void sortList(List<Branch> list, List<Branch> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			Branch e = sourcelist.get(i);
			if (e.getParentBrchCode()!=null && e.getParentBrchCode().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						Branch child = sourcelist.get(j);
						if (child.getParentBrchCode()!=null && child.getParentBrchCode().equals(e.getBranchNo())){
							sortList(list, sourcelist, e.getBranchNo(), true);
							break;
						}
					}
				}
			}
		}
	}
	/**
	 * 根据机构号查询机构信息
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=branchInfo")
	@ResponseBody
	public AjaxJson branchInfo(String branchNo){
		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();
		IBranchService branchService = ServiceFactory.getBranchService();
		// 取客户经理基本信息
		Branch branch;
			try {
				branch=branchService.getBranch(branchNo);
			} catch (BizAppException e) {
				e.printStackTrace();
				retJson.setMsg("查询数据库信息失败");
				retJson.setSuccess(false);
				e.printStackTrace();
				return retJson;
			}	
		retMap.put("branchName",branch.getBranchName());
		retMap.put("bankNo",branch.getPayBankNo());
		retJson.setAttributes(retMap);
		retJson.setSuccess(true);
		
		return retJson;
	}
	/**
	 * 判断机构是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkBrchNo")
	@ResponseBody
	public AjaxJson checkBrchNo(String branchNo) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		IBranchService branchService = ServiceFactory.getBranchService();
    	try{
    		if (branchService.getBranch(branchNo)==null){
    			retJson.setSuccess(false);
    			retJson.setMsg("不存在本机构，请重新输入！");
    		}
		} catch (Exception e){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		
		return retJson;
	}
}
