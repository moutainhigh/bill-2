/********************************************
 * 文件名称: TradeBankRootController.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-2 下午15:45:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
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
import com.herongtech.console.domain.bean.TradeBankRoot;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ITradeBankRootService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：TradeBankRootController
 * 创建人：dq
 * 创建时间：2016年8月2日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/tradeBankRootController")
public class TradeBankRootController {
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
		
		String bankName = request.getParameter("bankName");
		String bankNo = request.getParameter("bankNo");
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
		
		HsSqlString dbSql = new HsSqlString("ttradebank_root");
		if (!StringUtils.isEmpty(bankName)){
			dbSql.setWhere("bank_name like '%" + bankName + "%'");
		}
		
		if (!StringUtils.isEmpty(bankNo)){
			dbSql.setWhere("bank_no like '%" + bankNo + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<TradeBankRoot> resultList = new ArrayList<TradeBankRoot>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), TradeBankRoot.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}
    	
    	mapField.put("bankName", bankName);
    	mapField.put("bankNo", bankNo);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/tradeBankRoot/tradeBankRoot_list");
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
		ITradeBankRootService tradeBankRootService = ServiceFactory.getTradeBankRootService();
		
		TradeBankRoot tradeBankRoot = new TradeBankRoot();
		String leftThreeBankNo = request.getParameter("bankNo");
		String s = leftThreeBankNo.substring(0,3);
		String bankName = request.getParameter("bankName");
		String bankNo = request.getParameter("bankNo");
		String isedit = request.getParameter("isedit");
		
		tradeBankRoot.setLeftThreeBankNo(s);
		tradeBankRoot.setBankName(bankName);
		tradeBankRoot.setBankNo(bankNo);
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				tradeBankRootService.modifyTradeBankRoot(tradeBankRoot);
			}else{
				tradeBankRootService.addTradeBankRoot(tradeBankRoot);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录ttradebank_root失败");
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
		
		mv.setViewName("system/tradeBankRoot/tradeBankRoot_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 判断行号是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String pBankNo = request.getParameter("bankNo");
		String bankNo = request.getParameter("bankNo");
		
		String sql = "select count(*) from tecds_bank_data where bank_no = ?";
		String sqls = "select count(*) from ttradebank_root where bank_no = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, pBankNo);
    		int counts = session.account(sqls, bankNo);
    		if (count < 1){
    			retJson.setObj("pBankNo");
    		}
    		if (counts >= 1){
    			retJson.setObj("bankNo");
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
		
		String bankNo = request.getParameter("bankNo");
		String sql = "select * from ttradebank_root where bank_no = ?";
		
		TradeBankRoot tradeBankRoot = new TradeBankRoot();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		tradeBankRoot = session.getObject(sql, TradeBankRoot.class, bankNo); 
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/tradeBankRoot/tradeBankRoot_edit");
		mv.addObject("tradeBankRoot", tradeBankRoot);
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
		
		String tradeBankRoot_ids = request.getParameter("tradeBankRoot_ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(tradeBankRoot_ids));
		
		String sql = "delete from ttradebank_root where bank_no in(" + sqlInParts+  ")";
		
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
