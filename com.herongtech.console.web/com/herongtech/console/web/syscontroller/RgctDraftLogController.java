/********************************************
 * 文件名称: RgctDraftLogController.java
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
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：DictionariesController
 * 创建人：FH
 * 创建时间：2014年9月2日
 * @version
 */
/**
 * @author 李江涛
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/rgctDraftLogController")
public class RgctDraftLogController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		page.activeCommand();
		Map<String, String> mapField = new HashMap<String,String>();
		
		String log_id = request.getParameter("log_id");
		String rgct_id = request.getParameter("rgct_id");
		String bill_no = request.getParameter("bill_no");
		String req_sid = request.getParameter("req_sid");
		String draft_no_req = request.getParameter("draft_no_req");
	//	String req_dt = request.getParameter("req_dt");
		String startDay = request.getParameter("startDay");
		String endDay = request.getParameter("endDay");
		String resp_sid = request.getParameter("resp_sid");
		String draft_no_resp = request.getParameter("draft_no_resp");
		String resp_dt = request.getParameter("resp_dt");
		String process_flag = request.getParameter("status");
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
		
		HsSqlString dbSql = new HsSqlString("trgct_draft_log");
		
		if (!StringUtils.isEmpty(log_id)){
			dbSql.setWhere("log_id like '%" + log_id + "%'");
		}
		
		if (!StringUtils.isEmpty(rgct_id)){
			dbSql.setWhere("rgct_id like '%" + rgct_id + "%'");
		}
		
		if (!StringUtils.isEmpty(bill_no)){
			dbSql.setWhere("bill_no like '%" + bill_no + "%'");
		}
		
		if (!StringUtils.isEmpty(req_sid)){
			dbSql.setWhere("req_sid like '%" + req_sid + "%'");
		}
		
		if (!StringUtils.isEmpty(draft_no_req)){
			dbSql.setWhere("draft_no_req like '%" + draft_no_req + "%'");
		}
		
		/*if (!StringUtils.isEmpty(req_dt)){
			dbSql.setWhere("req_dt like '%" + req_dt + "%'");
		}*/
		
		if (!StringUtils.isEmpty(endDay)&&!StringUtils.isEmpty(startDay)){
			dbSql.setWhere("op_date >= '" + startDay + "' and op_date <= '" + endDay + "'");
		}
		
		if (!StringUtils.isEmpty(endDay)&&StringUtils.isEmpty(startDay)){
			dbSql.setWhere("op_date <= '" + endDay + "'");
		}
		
		if (!StringUtils.isEmpty(startDay)&&StringUtils.isEmpty(endDay)){
			dbSql.setWhere("op_date >= '" + startDay + "'");
		}
		
		if (!StringUtils.isEmpty(resp_sid)){
			dbSql.setWhere("resp_sid like '%" + resp_sid + "%'");
		}
		
		if (!StringUtils.isEmpty(draft_no_resp)){
			dbSql.setWhere("draft_no_resp like '%" + draft_no_resp + "%'");
		}
		
		if (!StringUtils.isEmpty(resp_dt)){
			dbSql.setWhere("resp_dt like '%" + resp_dt + "%'");
		}
		
		if (!StringUtils.isEmpty(process_flag)){
			dbSql.setWhere("process_flag like '%" + process_flag + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}else{
			//dbSql.setWhere("order by log_id desc");
			dbSql.setOrder("log_id desc");
		}
		
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<RgctDraftLog> resultList = new ArrayList<RgctDraftLog>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), RgctDraftLog.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询报文日志信息失败");
		}
		List<RgctDraftLog> resultListend = new ArrayList<RgctDraftLog>(resultList.size());
		for (int i = resultList.size()-1; i >= 0; i--) {
			resultListend.add(resultList.get(i));
		}
		
		
    	mapField.put("log_id", log_id);
    	mapField.put("rgct_id", rgct_id);
    	mapField.put("bill_no", bill_no);
    	mapField.put("req_sid", req_sid);
    	mapField.put("draft_no_req", draft_no_req);
    //	mapField.put("req_dt", req_dt);
    	mapField.put("startDay", startDay);
    	mapField.put("endDay", endDay);
    	mapField.put("resp_sid", resp_sid);
    	mapField.put("draft_no_resp", draft_no_resp);
    	mapField.put("resp_dt", resp_dt);
    	mapField.put("process_flag", process_flag);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/rgctdraftlog/rgctdraftlog_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
	/**
	 * 查看报文日志详情
	 * @param logId
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=detail")
	public ModelAndView detail(String logId) throws BizAppException{
		ModelAndView mv =new ModelAndView();
		IRgctDraftLogService service = RcServiceFactory.getRgctDraftLogService();
		RgctDraftLog rgctdraftlog = new RgctDraftLog();
		try {
			rgctdraftlog = service.getRgctDraftLog(logId);
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("报文日志列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "报文日志列表查询失败");
		}		
		
		mv.setViewName("system/rgctdraftlog/rgct_draft_log_detail");
		mv.addObject("rgctdraftlog",rgctdraftlog);
		return mv;
	}
	
}