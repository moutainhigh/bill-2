/********************************************
 * 文件名称: RgctEcdsStatusController.java
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
import com.herongtech.rc.domain.bean.RgctEcdsStatus;
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
@RequestMapping("/rgctEcdsStatusController")
public class RgctEcdsStatusController extends BaseController {
	
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
		
		String pname = request.getParameter("pname");
		String pname_cn = request.getParameter("pname_cn");
		String pvalue_cn = request.getParameter("pvalue_cn");
		//String order_by = request.getParameter("order_by");
		
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
		
		HsSqlString dbSql = new HsSqlString("trgct_ecds_status");
		
		if (!StringUtils.isEmpty(pname)){
			dbSql.setWhere("pname like '%" + pname + "%'");
		}
		
		if (!StringUtils.isEmpty(pname_cn)){
			dbSql.setWhere("pname_cn like '%" + pname_cn + "%'");
		}
		
		if (!StringUtils.isEmpty(pvalue_cn)){
			dbSql.setWhere("pvalue_cn like '%" + pvalue_cn + "%'");
		}
		
		/*if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
			
		}*/
		
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<RgctEcdsStatus> resultList = new ArrayList<RgctEcdsStatus>();
    	try{
    		dbSql.setOrder("pname");
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), RgctEcdsStatus.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询系统运营状态失败");
		}
    	
    	mapField.put("pname", pname);
    	mapField.put("pname_cn", pname_cn);
    	mapField.put("pvalue_cn", pvalue_cn);
    	//mapField.put("order_by", order_by);
		mv.setViewName("system/rgctecdsstatus/rgctecdsstatus_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
}
