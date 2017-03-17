/********************************************
 * 文件名称: OperationController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-7-12 下午02:50:52
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
import com.herongtech.console.domain.bean.TmonthControlle;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;
@Scope("prototype")
@Controller
@RequestMapping("/tmonthCheck")
public class TmonthCheckController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		Map<String, String> mapField = new HashMap<String,String>();
		String checkDate = request.getParameter("checkDate");
		String billNo = request.getParameter("billNo");
		String draftLogId = request.getParameter("draftLogId");
		String errorCode = request.getParameter("errorCode");
		String order_by = request.getParameter("order_by");
		page.activeCommand();
		HsSqlString dbSql = new HsSqlString("tmonth_check_data");
		if (!StringUtils.isEmpty(checkDate)){
			dbSql.setWhere("check_date like '%" + checkDate + "%'");
		}
		
		if (!StringUtils.isEmpty(billNo)){
			dbSql.setWhere("bill_no like '%" + billNo + "%'");
		}
		
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<TmonthControlle> resultList = new ArrayList<TmonthControlle>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), TmonthControlle.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	} catch (SQLException e){
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询功能菜单列表失败");
		}
    	
    	mapField.put("check_date", checkDate);
    	mapField.put("bill_no", billNo);
    	mapField.put("order_by", order_by);
		
		mv.setViewName("system/tmonthCheck/tmonthCheck_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
	
}
