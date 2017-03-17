/********************************************
 * 文件名称: SysErrmsgController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-11 下午02:48:52
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
import com.herongtech.console.domain.bean.Errmsg;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IErrmsgService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：SysErrmsgController
 * 创建人：dq
 * 创建时间：2016年7月11日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/sysErrmsgController")
public class SysErrmsgController extends BaseController{
	
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
		
		String errCode = request.getParameter("errCode");
		String errMsg = request.getParameter("errMsg");
		String order_by = request.getParameter("order_by");
		
		String currentPage = request.getParameter("page.currentPage");
		if(!StringUtils.isEmpty(currentPage)){
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		
		String showCount = request.getParameter("page.showCount");
		if(!StringUtils.isEmpty(showCount)){
			page.setShowCount(Integer.parseInt(showCount));
		}
		
		HsSqlString dbSql = new HsSqlString("terrmsg");
		if (!StringUtils.isEmpty(errCode)){
			dbSql.setWhere("err_Code like '%" + errCode + "%'");
		}
		
		if (!StringUtils.isEmpty(errMsg)){
			dbSql.setWhere("err_Msg like '%" + errMsg + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<Errmsg> resultList = new ArrayList<Errmsg>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), Errmsg.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}
    	
    	mapField.put("errCode", errCode);
    	mapField.put("errMsg", errMsg);
		
		mv.setViewName("system/syserrmsg/syserrmsg_list");
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
		IErrmsgService errmsgService = ServiceFactory.getErrmsgService();
		
		Errmsg errmsg = new Errmsg();
		String errCode = request.getParameter("errCode");
		String errMsg = request.getParameter("errMsg");
		String isedit = request.getParameter("isedit");
		
		errmsg.setErrCode(errCode);
		errmsg.setErrMsg(errMsg);
		
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				errmsgService.modifyErrmsg(errmsg);
			}else{
				errmsgService.addErrmsg(errmsg);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录terrmsg失败");
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
		
		mv.setViewName("system/syserrmsg/syserrmsg_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 判断错误代码是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String errCode = request.getParameter("errCode");
		
		String sql = "select count(*) from terrmsg where err_Code = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, errCode);
        	
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
		
		String errCode = request.getParameter("errCode");
		String sql = "select * from terrmsg where err_Code = ?";
		
		Errmsg errmsg = new Errmsg();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		errmsg = session.getObject(sql, Errmsg.class, errCode);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/syserrmsg/syserrmsg_edit");
		mv.addObject("syserrmsg", errmsg);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(params="method=del")
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String errmsg_ids = request.getParameter("errmsg_ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(errmsg_ids));
		
		String sql = "delete from terrmsg where err_Code in(" + sqlInParts+  ")";
		
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
