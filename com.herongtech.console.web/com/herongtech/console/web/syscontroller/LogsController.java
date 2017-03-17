/********************************************
 * 文件名称: LogsController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-11
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
import com.herongtech.console.domain.bean.Logs;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ILogsService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysConstant;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：DictionariesController
 * 创建人：
 * 创建时间：2016年7月11日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/logsController")
public class LogsController extends BaseController {
	
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
		
		String log_serial = request.getParameter("log_serial");
		String user_id = request.getParameter("user_id");
//		String op_date = request.getParameter("op_date");
		String auth_user = request.getParameter("auth_user");
		String startDay = request.getParameter("startDay");
		String endDay = request.getParameter("endDay");
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
		
		HsSqlString dbSql = new HsSqlString("tlogs");
		if (!StringUtils.isEmpty(log_serial)){
			dbSql.setWhere("log_serial like '%" + log_serial + "%'");
		}
		
		if (!StringUtils.isEmpty(user_id)){
			dbSql.setWhere("user_id like '%" + user_id + "%'");
		}
		
//		if (!StringUtils.isEmpty(op_date)){
//			dbSql.setWhere("op_date like '%" + op_date + "%'");
//		}
		
		if (!StringUtils.isEmpty(auth_user)){
			dbSql.setWhere("auth_user like '%" + auth_user + "%'");
		}
		
		if (!StringUtils.isEmpty(endDay)&&!StringUtils.isEmpty(startDay)){
			dbSql.setWhere("op_date >= '" + startDay + "' and op_date <= '" + endDay + "'");
		}
		
		if (!StringUtils.isEmpty(endDay)&&StringUtils.isEmpty(startDay)){
			dbSql.setWhere("op_date <= '" + endDay + "'");
		}
		
		if (!StringUtils.isEmpty(startDay)&&StringUtils.isEmpty(endDay)){
			dbSql.setWhere("op_date >= '" + startDay + "'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}	
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<Logs> resultList = new ArrayList<Logs>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), Logs.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询日志列表失败");
		}
    	
    	mapField.put("log_serial", log_serial);
    	mapField.put("user_id", user_id);
    	//mapField.put("op_date", op_date);
    	mapField.put("auth_user", auth_user);
    	mapField.put("startDay", startDay);
    	mapField.put("endDay", endDay);
    	mapField.put("order_by", order_by);	
		
		mv.setViewName("system/logs/logs_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(params="method=toAdd")
	public ModelAndView toAdd(Page page){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("system/logs/logs_list");
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
		
		String log_serial = request.getParameter("log_serial");
		
		String sql = "select count(*) from tlogs where log_serial = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, log_serial);
        	
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
		
		String log_serial = request.getParameter("log_serial");
		String sql = "select * from tlogs where log_serial = ?";
		
		Logs logs = new Logs();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		logs = session.getObject(sql, Logs.class, log_serial);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/logs/logs_list");
		mv.addObject("logs", logs);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
}
