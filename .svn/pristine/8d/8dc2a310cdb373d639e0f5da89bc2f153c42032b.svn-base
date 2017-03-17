/********************************************
 * 文件名称: RgctExceptionDraftController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-20
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
import com.herongtech.console.domain.bean.RgctExceptionDraft;
import com.herongtech.console.service.ServiceFactory;
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
@RequestMapping("/rgctExceptionDraftController")
public class RgctExceptionDraftController extends BaseController {
	
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
		
		String log_id = request.getParameter("log_id");
		String msg_sid = request.getParameter("msg_sid");
		String draft_dt = request.getParameter("draft_dt");
		String draft_no = request.getParameter("draft_no");
		String orgnl_msg_sid = request.getParameter("orgnl_msg_sid");
		String orgnl_draft_dt = request.getParameter("orgnl_draft_dt");
		String orgnl_draft_no = request.getParameter("orgnl_draft_no");
		String bill_no = request.getParameter("bill_no");
		String in_out = request.getParameter("in_out");
		String status = request.getParameter("status");
		String err_count = request.getParameter("err_count");
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
		
		HsSqlString dbSql = new HsSqlString("trgct_exception_draft");
		
		if (!StringUtils.isEmpty(log_id)){
			dbSql.setWhere("log_id like '%" + log_id + "%'");
		}
		
		if (!StringUtils.isEmpty(msg_sid)){
			dbSql.setWhere("msg_sid like '%" + msg_sid + "%'");
		}
		
		if (!StringUtils.isEmpty(draft_dt)){
			dbSql.setWhere("draft_dt like '%" + draft_dt + "%'");
		}
		
		if (!StringUtils.isEmpty(draft_no)){
			dbSql.setWhere("draft_no like '%" + draft_no + "%'");
		}
		
		if (!StringUtils.isEmpty(orgnl_msg_sid)){
			dbSql.setWhere("orgnl_msg_sid like '%" + orgnl_msg_sid + "%'");
		}
		
		if (!StringUtils.isEmpty(orgnl_draft_dt)){
			dbSql.setWhere("orgnl_draft_dt like '%" + orgnl_draft_dt + "%'");
		}
		
		if (!StringUtils.isEmpty(orgnl_draft_no)){
			dbSql.setWhere("orgnl_draft_no like '%" + orgnl_draft_no + "%'");
		}
		
		if (!StringUtils.isEmpty(bill_no)){
			dbSql.setWhere("bill_no like '%" + bill_no + "%'");
		}
		
		if (!StringUtils.isEmpty(in_out)){
			dbSql.setWhere("in_out like '%" + in_out + "%'");
		}
		
		if (!StringUtils.isEmpty(status)){
			dbSql.setWhere("status like '%" + status + "%'");
		}
		
		if (!StringUtils.isEmpty(err_count)){
			dbSql.setWhere("err_count like '%" + err_count + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<RgctExceptionDraft> resultList = new ArrayList<RgctExceptionDraft>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), RgctExceptionDraft.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询异常报文失败");
		}
    	
    	mapField.put("log_id", log_id);
    	mapField.put("msg_sid", msg_sid);
    	mapField.put("draft_dt", draft_dt);
    	mapField.put("draft_no", draft_no);
    	mapField.put("orgnl_msg_sid", orgnl_msg_sid);
    	mapField.put("orgnl_draft_dt", orgnl_draft_dt);
    	mapField.put("orgnl_draft_no", orgnl_draft_no);
    	mapField.put("bill_no", bill_no);
    	mapField.put("in_out", in_out);
    	mapField.put("status", status);
    	mapField.put("err_count", err_count);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/rgctexceptiondraft/rgctexceptiondraft_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
	/**
	 * 处理状态
	 */
	@RequestMapping(params="method=reh")
	@ResponseBody
	public AjaxJson reh(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String msg_sids = request.getParameter("msg_sids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(msg_sids));
		
		String sql = "update TRGCT_EXCEPTION_DRAFT set status='1' where msg_sid in(" + sqlInParts+  ")";
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
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库修改失败");
		}
		
		return retJson;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(params="method=del")
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String msg_sids = request.getParameter("msg_sids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(msg_sids));
		
		String sql = "delete from trgct_exception_draft where msg_sid in(" + sqlInParts+  ")";
		
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

