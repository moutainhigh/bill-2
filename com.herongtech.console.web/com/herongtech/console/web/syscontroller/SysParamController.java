/********************************************
 * 文件名称: SysParamController.java
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sun.awt.geom.AreaOp.AddOp;



import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.Param;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IParamService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：DictionariesController
 * 创建人：FH
 * 创建时间：2014年9月2日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/sysParamController")
public class SysParamController extends BaseController {
	
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
		
		String param_id = request.getParameter("param_id");
		String belong_type = request.getParameter("belong_type");
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
		
		HsSqlString dbSql = new HsSqlString("tsysparam");
		if (!StringUtils.isEmpty(param_id)){
			dbSql.setWhere("param_id like '%" + param_id + "%'");
		}
		if (!StringUtils.isEmpty(belong_type)){
			dbSql.setWhere("belong_type like '%" + belong_type + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<Param> resultList = new ArrayList<Param>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), Param.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}
    	
    	mapField.put("param_id", param_id);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/sysparam/sysparam_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		
		return mv;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(Page page,HttpServletRequest request,
			HttpServletResponse response) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		
		//取用户处理类
        IParamService paramService = ServiceFactory.getParamService();
		
		Param param = new Param();
		String param_id = request.getParameter("param_id");
		String param_name = request.getParameter("param_name");
		String param_value = request.getParameter("param_value");
		String value_name = request.getParameter("value_name");
		String isedit = request.getParameter("isedit");
		String belong_type=request.getParameter("belong_type");
		param.setBankNo(IConstants.DEFAULT_BANK_NO);
		param.setTaCode(IConstants.DEFAULT_PARAM_CODE);
		param.setParamId(param_id);
		param.setParamName(param_name);
		param.setParamValue(param_value);
		param.setValueName(value_name);
		param.setBelongType(belong_type);
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				paramService.modifyParam(param);
			}else{
				paramService.addParam(param);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tsysparam失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		mv.addObject("param_id",param_id);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(params="method=toAdd")
	public ModelAndView toAdd(Page page){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("system/sysparam/sysparam_edit");
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
		
		String param_id = request.getParameter("param_id");
		
		String sql = "select count(*) from tsysparam where param_id = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, param_id);
        	
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
		
		String param_id = request.getParameter("param_id");
		String sql = "select * from tsysparam where param_id = ?";
		
		Param param = new Param();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		param = session.getObject(sql, Param.class, param_id);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/sysparam/sysparam_edit");
		mv.addObject("sysparam", param);
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
		
		String param_ids = request.getParameter("param_ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(param_ids));
		
		String sql = "delete from tsysparam where param_id in(" + sqlInParts+  ")";
		
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
