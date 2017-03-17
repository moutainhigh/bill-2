/********************************************
 * 文件名称: CustInfoAcctController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: cgc
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.console.service.interfaces.ICustInfoService;
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
@RequestMapping("/custInfoAcctController")

public class CustInfoAcctController extends BaseController{

public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=listCustInfoAcct")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		
		Map<String, String> mapField = new HashMap<String,String>();
		
		String cust_no = request.getParameter("cust_no");
		String acct_no = request.getParameter("acct_no");
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
		
		HsSqlString dbSql = new HsSqlString("tcust_info_acct");
		if (!StringUtils.isEmpty(cust_no)){
			dbSql.setWhere("cust_no like '%" + cust_no + "%'");
		}
		if (!StringUtils.isEmpty(acct_no)){
			dbSql.setWhere("acct_no like '%" + acct_no + "%'");
		}
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<CustInfoAcct> resultList = new ArrayList<CustInfoAcct>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), CustInfoAcct.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询客户帐号信息列表失败");
		}
    	
    	mapField.put("acct_no", acct_no);
    	mapField.put("cust_no", cust_no);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/custinfoacct/custinfoacct_list");
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
		CustInfoAcct custIA = new CustInfoAcct();
		String isedit = request.getParameter("isedit");
		if(isedit.equals("0")){
			/*HttpSession sessions = ContextHolderUtils.getSession();
			Client client = ClientManager.getInstance().getClient(sessions.getId());
			User user=client.getUser();
			String userName=user.getUserName();*/
			String d= DateUtil.getDate();
			String date=DateUtil.dateTo10(d);
			String t=DateUtil.getTime();
			String time=DateUtil.timeTo8(t);
			custIA.setOpenDate(date);
			custIA.setOpenTime(time);
		}else{
			String open_date = request.getParameter("open_date");
			String open_time = request.getParameter("open_time");
			custIA.setOpenDate(open_date);
			custIA.setOpenTime(open_time);
		}
		ModelAndView mv = new ModelAndView();
		
		//取客户信息处理类
		ICustInfoAcctService custService = ServiceFactory.getCustInfoAcctService();
		String cust_no = request.getParameter("cust_no");
		String acct_no = request.getParameter("acct_no");
		String acct_type = request.getParameter("acct_type");
		String acct_branch_no = request.getParameter("acct_branch_no");
		String acct_branch_name = request.getParameter("acct_branch_name");
		String acct_bank_no = request.getParameter("acct_bank_no");
		custIA.setCustNo(cust_no);
		custIA.setAcctNo(acct_no);
		custIA.setAcctType(acct_type);
		custIA.setAcctBranchNo(acct_branch_no);
		custIA.setAcctBranchName(acct_branch_name);
		custIA.setAcctBankNo(acct_bank_no);
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				custService.modifyParam(custIA);;
			}else{
				custService.addParam(custIA);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录客户帐号信息失败");
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
		
		mv.setViewName("system/custinfoacct/custinfoacct_edit");
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
		Map<String, Object> retMap = new HashMap<String, Object>();
		AjaxJson retJson = new AjaxJson();
		String id = request.getParameter("id");
		String ids = request.getParameter("ids");
		String sql = "select count(*) from tcust_info_acct where acct_no = ? and cust_no=?";
		String custInfoSql = "select count(*) from tcust_info where cust_no = ?";
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql,id,ids);
    		int counts = session.account(custInfoSql,ids);
        	
    		if (count >= 1){
				retMap.put("checkCustAcctNo", "1");
				retJson.setAttributes(retMap);
    			retJson.setSuccess(false);
    		}
    		if (counts == 0){
    			retMap.put("checkCustNo", "1");
				retJson.setAttributes(retMap);
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
		
		String id = request.getParameter("custacctid");
		String sql = "select * from tcust_info_acct where acct_no = ?";
		
		CustInfoAcct custI=new CustInfoAcct();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		custI = session.getObject(sql, CustInfoAcct.class, id);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/custinfoacct/custinfoacct_edit");
		mv.addObject("custinfoacctparam", custI);
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
		
		String cust_ids = request.getParameter("custacct_ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(cust_ids));
		
		String sql = "delete from tcust_info_acct where acct_no in(" + sqlInParts+  ")";
		
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
