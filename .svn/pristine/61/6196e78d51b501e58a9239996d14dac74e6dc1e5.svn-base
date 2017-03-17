/********************************************
 * 文件名称: BlackController.java
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
import javax.servlet.http.HttpSession;

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
import com.herongtech.console.core.system.manager.Client;
import com.herongtech.console.core.system.manager.ClientManager;
import com.herongtech.console.core.util.ContextHolderUtils;
import com.herongtech.console.domain.bean.BlackList;
import com.herongtech.console.domain.bean.Dept;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBlackService;
import com.herongtech.console.service.interfaces.IDeptService;
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
@RequestMapping("/blackController")
public class BlackController extends BaseController {

public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=listBlack")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		
		Map<String, String> mapField = new HashMap<String,String>();
		
		String enterprise_name = request.getParameter("enterprise_name");
		String union_bankno = request.getParameter("union_bankno");
		String oper_brch_no = request.getParameter("oper_brch_no");
		String description = request.getParameter("description");
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
		
		HsSqlString dbSql = new HsSqlString("tblack_list");
		if (!StringUtils.isEmpty(enterprise_name)){
			dbSql.setWhere("enterprise_name like '%" + enterprise_name + "%'");
		}
		if (!StringUtils.isEmpty(union_bankno)){
			dbSql.setWhere("union_bankno like '%" + union_bankno + "%'");
		}
		if (!StringUtils.isEmpty(oper_brch_no)){
			dbSql.setWhere("oper_brch_no like '%" + oper_brch_no + "%'");
		}
		if (!StringUtils.isEmpty(description)){
			dbSql.setWhere("description like '%" + description + "%'");
		}
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<BlackList> resultList = new ArrayList<BlackList>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), BlackList.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询黑名单列表失败");
		}
    	
    	mapField.put("enterprise_name", enterprise_name);
    	mapField.put("union_bankno", union_bankno);
    	mapField.put("oper_brch_no", oper_brch_no);
    	mapField.put("description", description);
    	mapField.put("order_by", order_by);
		mv.setViewName("system/blacklist/blacklist_list");
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
		BlackList black = new BlackList();
		String isedit = request.getParameter("isedit");
		if(isedit.equals("0")){
			HttpSession sessions = ContextHolderUtils.getSession();
			Client client = ClientManager.getInstance().getClient(sessions.getId());
			User user=client.getUser();
			String userName=user.getUserName();
			String d= DateUtil.getDate();
			String date=DateUtil.dateTo10(d);
			String t=DateUtil.getTime();
			String time=DateUtil.timeTo8(t);
			black.setCreateDt(date);
			black.setCreateTime(time);
			black.setOperName(userName);
		}else{
			String create_dt = request.getParameter("create_dt");
			String create_time = request.getParameter("create_time");
			String oper_name = request.getParameter("oper_name");
			black.setCreateDt(create_dt);
			black.setCreateTime(create_time);
			black.setOperName(oper_name);
		}
		ModelAndView mv = new ModelAndView();
		
		//取黑名单处理类
		IBlackService blackService = ServiceFactory.getBlackService();
		/*String ids = request.getParameter("id");
		Long id = Long.parseLong(ids);*/
		String enterprise_name = request.getParameter("enterprise_name");
		String union_bankno = request.getParameter("union_bankno");
		//String create_date = request.getParameter("create_date");
		String oper_brch_no = request.getParameter("oper_brch_no");
		String description = request.getParameter("description");
		
		//black.setId(id);
		black.setEnterpriseName(enterprise_name);
		black.setUnionBankno(union_bankno);
		//black.setCreateDate(create_date);
		black.setOperBranchNo(oper_brch_no);
		black.setDescription(description);
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				blackService.modifyParam(black);
			}else{
				blackService.addParam(black);  //插入
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录黑名单失败");
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
		
		mv.setViewName("system/blacklist/blacklist_edit");
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
		
		String sql = "select count(*) from tblack_list where union_bankno = ?";
		
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
		
		String id = request.getParameter("blackid");
		String sql = "select * from tblack_list where union_bankno = ?";
		
		BlackList black = new BlackList();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		black = session.getObject(sql, BlackList.class, id);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/blacklist/blacklist_edit");
		mv.addObject("blackparam", black);
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
		
		String black_ids = request.getParameter("black_ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(black_ids));
		
		String sql = "delete from tblack_list where union_bankno in(" + sqlInParts+  ")";
		
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
