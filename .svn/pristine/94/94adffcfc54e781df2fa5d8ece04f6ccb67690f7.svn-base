/********************************************
 * 文件名称: TradeBankController.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-1 下午15:45:52
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

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.TradeBank;
import com.herongtech.console.domain.bean.TradeBankRoot;
import com.herongtech.console.domain.dao.TradeBankRootDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ITradeBankService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：TradeBankController
 * 创建人：dq
 * 创建时间：2016年8月1日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/tradeBankController")
public class TradeBankController {
	
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
		
		
		
		
		
		String childBankno = request.getParameter("childBankno");
		String childBankname = request.getParameter("childBankname");
		String parentBankno = request.getParameter("parentBankno");
		String parentBankname = request.getParameter("parentBankname");
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
		
		HsSqlString dbSql = new HsSqlString("ttradebank");
		if (!StringUtils.isEmpty(childBankno)){
			dbSql.setWhere("child_bankno like '%" + childBankno + "%'");
		}
		
		if (!StringUtils.isEmpty(childBankname)){
			dbSql.setWhere("child_bankname like '%" + childBankname + "%'");
		}
		
		if (!StringUtils.isEmpty(parentBankno)){
			dbSql.setWhere("parent_bankno like '%" + parentBankno + "%'");
		}
		
		if (!StringUtils.isEmpty(parentBankname)){
			dbSql.setWhere("parent_bankname like '%" + parentBankname + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<TradeBank> resultList = new ArrayList<TradeBank>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), TradeBank.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}
    	
    	mapField.put("childBankno", childBankno);
    	mapField.put("childBankname", childBankname);
    	mapField.put("parentBankno", parentBankno);
    	mapField.put("parentBankname", parentBankname);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/tradeBank/tradeBank_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
	/**
	 * 保存
	 * @throws SQLException 
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException, SQLException {
		ModelAndView mv = new ModelAndView();
		
		//取用户处理类
		ITradeBankService tradeBankService = ServiceFactory.getTradeBankService();
		
		TradeBank tradeBank = new TradeBank();
		
		TradeBankRootDao tradeBankRootDao = new TradeBankRootDao();
		TradeBankRoot tradeBankRoot = new TradeBankRoot();
		
		String childBankno = request.getParameter("childBankno");
		String childBankname = request.getParameter("childBankname");
		String parentBankno = request.getParameter("parentBankno");
		
		//tradeBankRoot = tradeBankRootDao.getTradeBankRoot(parentBankno);
		//String parentBankname=tradeBankRoot.getBankName();
		String parentBankname = request.getParameter("parentBankname");
		String custName = request.getParameter("custName");
		String d= DateUtil.getDate();
		String date=DateUtil.dateTo10(d);
		//String createDate = request.getParameter("createDate");
		String t=DateUtil.getTime();
		String time=DateUtil.timeTo8(t);
		//String createTime = request.getParameter("createTime");
		String orgCode = request.getParameter("orgCode");
		String partnerType = request.getParameter("partnerType");
		String isedit = request.getParameter("isedit");
		
		tradeBank.setChildBankno(childBankno);
		tradeBank.setChildBankname(childBankname);
		tradeBank.setParentBankno(parentBankno);
		tradeBank.setParentBankname(parentBankname);
		tradeBank.setCustName(custName);
		tradeBank.setCreateDate(date);
		tradeBank.setCreateTime(time);
		tradeBank.setOrgCode(orgCode);
		tradeBank.setPartnerType(partnerType);
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				tradeBankService.modifyTradeBank(tradeBank);
			}else{
				tradeBankService.addTradeBank(tradeBank);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录ttradebank失败");
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
		
		mv.setViewName("system/tradeBank/tradeBank_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	
	/**
	 * 判断总行行号与分支行行号是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String parentBankno = request.getParameter("parentBankno");
		String childBankno = request.getParameter("childBankno");
		String pChildBankno = request.getParameter("childBankno");
		
		String sql = "select count(*) from ttradebank_root where bank_no = ?";
		String sqls = "select count(*) from ttradebank where child_bankno = ?";
		String psql = "select count(*) from tecds_bank_data where row_number = ?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, parentBankno);
    		int counts = session.account(sqls, childBankno);
    		int pcount = session.account(psql, pChildBankno);
    		
    		if (count < 1){
    			retJson.setObj("parentBankno");
    		}
    		if(counts >= 1){
    			retJson.setObj("childBankno");
    		}
    		if(pcount < 1){
    			retJson.setObj("pChildBankno");
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
		
		String childBankno = request.getParameter("childBankno");
		String sql = "select * from ttradebank where child_bankno = ?";
		
		TradeBank tradeBank = new TradeBank();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		tradeBank = session.getObject(sql, TradeBank.class, childBankno); 
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/tradeBank/tradeBank_edit");
		mv.addObject("tradeBank", tradeBank);
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
		
		String tradeBank_ids = request.getParameter("tradeBank_ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(tradeBank_ids));
		
		String sql = "delete from ttradebank where child_bankno in(" + sqlInParts+  ")";
		
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
