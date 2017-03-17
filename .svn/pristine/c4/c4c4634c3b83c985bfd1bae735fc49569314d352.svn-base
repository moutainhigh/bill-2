/********************************************
 * 文件名称:  BusiDateController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
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
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.BusiDate;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBusiDateService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：busiDateController
 * 创建人：FH
 * 创建时间：2014年9月2日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/busiDateController")
public class BusiDateController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();

		Map<String, String> mapField = new HashMap<String,String>();
		
		String sys_bank_no = request.getParameter("sys_bank_no");
		
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
		
		HsSqlString dbSql = new HsSqlString("tbusi_date");
		if (!StringUtils.isEmpty(sys_bank_no)){
			dbSql.setWhere("sys_bank_no", sys_bank_no);
		}
		
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<BusiDate> resultList = new ArrayList<BusiDate>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), BusiDate.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	page.activeCommand();
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询失败");
		}
    	
    	mapField.put("sys_bank_no", sys_bank_no);
		mv.setViewName("system/busidate/busidate_list");
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
        IBusiDateService   busidateService = ServiceFactory.getBusiDateService();
		
        BusiDate busidate = new BusiDate();
        String sys_bank_no = request.getParameter("sys_bank_no");
		String workday = request.getParameter("workday");
		String host_check_date = request.getParameter("host_check_date");
		String sys_status = request.getParameter("sys_status");
		String isedit = request.getParameter("isedit");
			
		busidate.setSysBankNo(sys_bank_no);
		busidate.setWorkday(workday);
		busidate.setHostCheckDate(host_check_date);
		busidate.setSysStatus(sys_status);
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				busidateService.modifyBusiDate(busidate);
			}else{
				busidateService.addBusiDate(busidate);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tbusi_date失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		 
		String sys_bank_no = request.getParameter("sys_bank_no");
		String sql = "select * from tbusi_date where sys_bank_no = ?";
		
		BusiDate busidate = new BusiDate();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		busidate = session.getObject(sql, BusiDate.class, sys_bank_no);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/busidate/busidate_edit");
		mv.addObject("busidate", busidate);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
}
