/********************************************
 * 文件名称: SerialnoController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2017-7-11 下午01:48:52
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
import com.herongtech.console.domain.bean.Param;
import com.herongtech.console.domain.bean.Serialno;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IParamService;
import com.herongtech.console.service.interfaces.ISerialnoService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysConstant;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：SerialnoController
 * 创建人：wzc
 * 创建时间：2014年9月2日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/serialnoController")
public class SerialnoController extends BaseController {
	
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
		
		String key_no = request.getParameter("key_no");
		String curr_value = request.getParameter("curr_value");
		String inc = request.getParameter("inc");
		String auto_reset = request.getParameter("auto_reset");
		String max_value = request.getParameter("max_value");
		String min_value = request.getParameter("min_value");
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
		
		HsSqlString dbSql = new HsSqlString("tserialno");
		if (!StringUtils.isEmpty(key_no)){
			dbSql.setWhere("key_no like '%" + key_no + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<Serialno> resultList = new ArrayList<Serialno>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), Serialno.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}
    	
    	mapField.put("key_no", key_no);
    	mapField.put("curr_value", curr_value);
    	mapField.put("inc", inc);
    	mapField.put("auto_reset", auto_reset);
    	mapField.put("max_value", max_value);
    	mapField.put("min_value", min_value);
    	mapField.put("order_by", order_by);
    	
		mv.setViewName("system/serialno/serialno_list");
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
        ISerialnoService serialnoService = ServiceFactory.getSerialnoService();
		
        Serialno serialno = new Serialno();
		String key_no = request.getParameter("key_no");
		String curr_value = request.getParameter("curr_value");
		String inc = request.getParameter("inc");
		String auto_reset = request.getParameter("auto_reset");
		String max_value = request.getParameter("max_value");
		String min_value = request.getParameter("min_value");
		String isedit = request.getParameter("isedit");
		
		serialno.setKeyNo(key_no);
		serialno.setCurrValue(Integer.parseInt(curr_value));
		serialno.setInc(Integer.parseInt(inc));
		serialno.setAutoReset(Integer.parseInt(auto_reset));
		serialno.setMaxValue(Integer.parseInt(max_value));
		serialno.setMinValue(Integer.parseInt(min_value));
		
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				serialnoService.modifySerialno(serialno);
			}else{
				serialnoService.addSerialno(serialno);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tserialno失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		
		mv.addObject("msg","success");
		mv.addObject("serialno", serialno);
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(params="method=toAdd")
	public ModelAndView toAdd(Page page){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("system/serialno/serialno_edit");
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
		
		String key_no = request.getParameter("key_no");
		
		String sql = "select count(*) from tserialno where key_no = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, key_no);
        	
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
		
		String key_no = request.getParameter("key_no");
		String sql = "select * from tserialno where key_no = ?";
		
		Serialno serialno = new Serialno();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		serialno = session.getObject(sql, Serialno.class, key_no);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/serialno/serialno_edit");
		mv.addObject("serialno", serialno);
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
		
		String key_nos = request.getParameter("key_nos");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(key_nos));
		
		String sql = "delete from tserialno where key_no in(" + sqlInParts+  ")";
		
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
