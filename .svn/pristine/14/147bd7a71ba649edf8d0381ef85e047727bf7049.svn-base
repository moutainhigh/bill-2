 /********************************************
 * 文件名称: DelayRuleController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-12
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
import com.herongtech.console.domain.bean.DelayRule;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IDelayRuleService;
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
@RequestMapping("/delayRuleController")
public class DelayRuleController extends BaseController {
	
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
		
		String id = request.getParameter("id");
		String prod_no = request.getParameter("prod_no");
		String bill_class = request.getParameter("bill_class");
		String bill_type = request.getParameter("bill_type");
		String delay_type = request.getParameter("delay_type");
		String delay_days = request.getParameter("delay_days");
		String oper_type = request.getParameter("oper_type");
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
		
		HsSqlString dbSql = new HsSqlString("tdelay_rule");
		
		if (!StringUtils.isEmpty(id)){
			dbSql.setWhere("id like '%" + id + "%'");
		}
		
		if (!StringUtils.isEmpty(prod_no)){
			dbSql.setWhere("prod_no like '%" + prod_no + "%'");
		}
		
		if (!StringUtils.isEmpty(bill_class)){
			dbSql.setWhere("bill_class like '%" + bill_class + "%'");
		}
		
		if (!StringUtils.isEmpty(bill_type)){
			dbSql.setWhere("bill_type like '%" + bill_type + "%'");
		}
		
		if (!StringUtils.isEmpty(delay_type)){
			dbSql.setWhere("delay_type like '%" + delay_type + "%'");
		}
		
		if (!StringUtils.isEmpty(delay_days)){
			dbSql.setWhere("delay_days like '%" + delay_days + "%'");
		}
		
		if (!StringUtils.isEmpty(oper_type)){
			dbSql.setWhere("oper_type like '%" + oper_type + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<DelayRule> resultList = new ArrayList<DelayRule>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), DelayRule.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询顺延规则表失败");
		}
    	
    	mapField.put("id", id);
    	mapField.put("prod_no", prod_no);
    	mapField.put("bill_class", bill_class);
    	mapField.put("bill_type", bill_type);
    	mapField.put("delay_type", delay_type);
    	mapField.put("delay_days", delay_days);
    	mapField.put("oper_type", oper_type);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/delayrule/delayrule_list");
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
        IDelayRuleService delayruleService = ServiceFactory.getDelayRuleService();
		
        DelayRule delayrule = new DelayRule();

		String id = request.getParameter("id");
		String prod_no = request.getParameter("prod_no");
		String bill_class = request.getParameter("bill_class");
		String bill_type = request.getParameter("bill_type");
		String delay_type = request.getParameter("delay_type");
		String delay_days = request.getParameter("delay_days");
		String oper_type = request.getParameter("oper_type");
		String isedit = request.getParameter("isedit");
		
		if(!StringUtils.isEmpty(id)){
			delayrule.setId(Long.parseLong(id));
	    }
		delayrule.setProdNo(prod_no);
		delayrule.setBillClass(bill_class);
		delayrule.setBillType(bill_type);
		delayrule.setDelayType(delay_type);
		if(!StringUtils.isEmpty(delay_days)){
			delayrule.setDelayDays(Long.parseLong(delay_days));
	    }
		delayrule.setOperType(oper_type);
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				delayruleService.modifyDelayRule(delayrule);
			}else{
				delayruleService.addDelayRule(delayrule);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tdelay_rule失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
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
		
		String ids = request.getParameter("ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(ids));
		
		String sql = "delete from tdelay_rule where id in(" + sqlInParts+  ")";
		
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
	 * 新增页面
	 */
	@RequestMapping(params="method=toAdd")
	public ModelAndView toAdd(Page page){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("system/delayrule/delayrule_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 编辑页面
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		
		String id = request.getParameter("id");
		String sql = "select * from tdelay_rule where id = ?";
		
		DelayRule delayrule = new DelayRule();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		delayrule = session.getObject(sql, DelayRule.class, id);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/delayrule/delayrule_edit");
		mv.addObject("delayrule", delayrule);
		mv.addObject("isedit", "1");
		
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
		
		String id = request.getParameter("id");
		
		String sql = "select count(*) from tdelay_rule where id = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, id);
        	
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
	
}
