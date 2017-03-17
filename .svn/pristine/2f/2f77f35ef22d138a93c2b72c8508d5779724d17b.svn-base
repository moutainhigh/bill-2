/********************************************
 * 文件名称: CustInfoController.java
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
import java.text.ParseException;
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
@RequestMapping("/custInfoController")

public class CustInfoController extends BaseController{
	
public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=listCustInfo")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		
		Map<String, String> mapField = new HashMap<String,String>();
		
		String cust_name = request.getParameter("cust_name");
		String cust_no = request.getParameter("cust_no");
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
		
		HsSqlString dbSql = new HsSqlString("tcust_info");
		dbSql.setWhere("cust_type = '2' ");
		if (!StringUtils.isEmpty(cust_name)){
			dbSql.setWhere("cust_name like '%" + cust_name + "%'");
		}
		if (!StringUtils.isEmpty(cust_no)){
			dbSql.setWhere("cust_no like '%" + cust_no + "%'");
		}
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<CustInfo> resultList = new ArrayList<CustInfo>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), CustInfo.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询客户信息列表失败");
		}
    	
    	mapField.put("cust_name", cust_name);
    	mapField.put("cust_no", cust_no);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/custinfo/custinfo_list");
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
		CustInfo custI = new CustInfo();
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
			custI.setCreateDt(date);
			custI.setCreateTime(time);
			custI.setUpdateDt(date);
			custI.setUpdateTime(time);
		}else{
			String e= DateUtil.getDate();
			String uDate=DateUtil.dateTo10(e);
			String i=DateUtil.getTime();
			String uTime=DateUtil.timeTo8(i);
			String create_dt = request.getParameter("create_dt");
			String create_time = request.getParameter("create_time");
			custI.setCreateDt(create_dt);
			custI.setCreateTime(create_time);
			custI.setUpdateDt(uDate);
			custI.setUpdateTime(uTime);
		}
		ModelAndView mv = new ModelAndView();
		
		//取客户信息处理类
		ICustInfoService custService = ServiceFactory.getCustInfoService();
		String cust_name = request.getParameter("cust_name");
		String cust_type = request.getParameter("cust_type");
		String cust_no = request.getParameter("cust_no");
		String loan_no = request.getParameter("loan_no");
		String org_code = request.getParameter("org_code");
		String flag = request.getParameter("flag");
		String partner_type = request.getParameter("partner_type");
		String address = request.getParameter("address");
		String credit_agency = request.getParameter("credit_agency");
		String credit_duedt = request.getParameter("credit_duedt");
		String credit_rate = request.getParameter("credit_rate");
		custI.setCustName(cust_name);
		custI.setCustType(cust_type);
		custI.setCustNo(cust_no);
		custI.setLoanNo(loan_no);
		custI.setOrgCode(org_code);
		custI.setFlag(flag);
		custI.setPartnerType(partner_type);
		custI.setAddress(address);
		custI.setCreditAgency(credit_agency);
		custI.setCreditDuedt(credit_duedt);
		custI.setCreditRate(credit_rate);
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				custService.modifyParam(custI);
			}else{
				custService.addParam(custI);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录客户信息失败");
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
		mv.setViewName("system/custinfo/custinfo_edit");
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
		
		String id = request.getParameter("id");
		
		String sql = "select count(*) from tcust_info where cust_no = ?";
		
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
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		
		String id = request.getParameter("custid");
		String sql = "select * from tcust_info where cust_no = ?";
		
		CustInfo custI=new CustInfo();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		custI = session.getObject(sql, CustInfo.class, id);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/custinfo/custinfo_edit");
		mv.addObject("custinfoparam", custI);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
	/**
	 * 查看账号
	 */
	@RequestMapping(params="method=toSearth")
	public ModelAndView toSearth(Page page,String custid) throws BizAppException{
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		String custName=custInfoService.getParam(custid).getCustName();
		ModelAndView mv = new ModelAndView();
		ICustInfoAcctService custService = ServiceFactory.getCustInfoAcctService();
		List<CustInfoAcct> resultList=custService.resultList1(custid);
		mv.setViewName("system/custinfo/custinfo_searth");
		mv.addObject("resultList", resultList);
		mv.addObject("custName", custName);
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
		
		String cust_ids = request.getParameter("cust_ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(cust_ids));
		
		String sql = "delete from tcust_info where cust_no in(" + sqlInParts+  ")";
		
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
	 * 根据客户账号查询客户信息
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=custInfo")
	@ResponseBody
	public AjaxJson checkuser(String custAcctNo){
		custAcctNo=custAcctNo.trim();
		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();
		// 获取客户信息和账号处理类
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		ICustInfoAcctService custInfoAcctService = ServiceFactory.getCustInfoAcctService();
		// 取用户基本信息
		CustInfoAcct custInfoAcct;
		try {
			custInfoAcct = custInfoAcctService.getParam(custAcctNo);
		} catch (BizAppException e) {
			retJson.setMsg("查询数据库信息失败");
			retJson.setSuccess(false);
			return retJson;
		}
		if(custInfoAcct==null){
			retJson.setMsg("根据客户账号无法找到对应的客户信息");
			retJson.setSuccess(false);
			return retJson;
		}
		if("".equals(custInfoAcct.getAcctType())){
			retJson.setMsg("根据客户账号无法找到对应的客户信息");
			retJson.setSuccess(false);
			return retJson;
		}
		CustInfo custInfo;
		try {
			custInfo = custInfoService.getParam(custInfoAcct.getCustNo());
		} catch (BizAppException e) {
			retJson.setMsg("查询数据库信息失败");
			retJson.setSuccess(false);
			return retJson;
		}
		if(custInfo==null){
			retJson.setMsg("无法找到对应的客户信息");
			retJson.setSuccess(false);
			return retJson;
		}
		String acctType = custInfoAcct.getAcctType();
		if(acctType.length()>3&&acctType.substring(1, 3).equals("04")){
			retJson.setMsg("不能为保证金账号");
			retJson.setSuccess(false);
			return retJson;
		}
		retMap.put("custAcctNo", custAcctNo);
		retMap.put("custNo", custInfo.getCustNo());
		retMap.put("custName", custInfo.getCustName());
		retMap.put("orgCode", custInfo.getOrgCode());
		retJson.setAttributes(retMap);
		retJson.setSuccess(true);
		
		return retJson;
	}
	
}
