/********************************************
 * 文件名称: RiskBillController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2016-7-11 下午01:48:52
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
import com.herongtech.console.core.util.DateTimeUtil;

import com.herongtech.console.domain.bean.RiskBill;

import com.herongtech.console.service.ServiceFactory;

import com.herongtech.console.service.interfaces.IRiskBillService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.service.sequence.SequenceService;


import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;

import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：RiskBillController
 * 创建人：wzc
 * 创建时间：2016年7月13日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/riskBillController")
public class RiskBillController extends BaseController {
	
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
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
	
		
//		String id =sequenceService.getRiskBillId();//risk_bill_id;
		
		String bill_no = request.getParameter("bill_no");
		String msg_id = request.getParameter("msg_id");
		String bill_type = request.getParameter("bill_type");
		String acceptor = request.getParameter("acceptor");
		String acceptor_bank_no = request.getParameter("acceptor_bank_no");
		String order_by = request.getParameter("order_by");
		
		String bill_money = request.getParameter("bill_money");
		//String bill_start_date = request.getParameter("bill_start_date");
		String due_dt = request.getParameter("due_dt");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String bill1 = request.getParameter("bill1");
		
		String bill2 = request.getParameter("bill2");
		String postdate = request.getParameter("postdate");
		
		String enterdate = request.getParameter("enterdate");
		
		String remitter = request.getParameter("remitter");
		
		String urgeapplyname = request.getParameter("urgeapplyname");
		String courtname = request.getParameter("courtname");	
		String matchfileno = request.getParameter("matchfileno");
		String issue_dt = request.getParameter("issue_dt");
		
		
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
	
		
		HsSqlString dbSql = new HsSqlString("trisk_bill");
//		if (!StringUtils.isEmpty(id)){
//			dbSql.setWhere("id like '%" + id + "%'");
//		}
		
		if (!StringUtils.isEmpty(bill_no)){
			dbSql.setWhere("bill_no like '%" + bill_no + "%'");
		}
		
		if (!StringUtils.isEmpty(msg_id)){
			dbSql.setWhere("msg_id '%" +msg_id + "%'");
		}
		if (!StringUtils.isEmpty(bill_type)){
			dbSql.setWhere("bill_type like '%" + bill_type + "%'");
		}
		if (!StringUtils.isEmpty(acceptor)){
			dbSql.setWhere("acceptor like '%" + acceptor + "%'");
		}
		if (!StringUtils.isEmpty(acceptor_bank_no)){
			dbSql.setWhere("acceptor_bank_no like '%" + acceptor_bank_no + "%'");
		}
		
		
		
		if (!StringUtils.isEmpty(bill_money)){
			dbSql.setWhere("bill_money like '%" + bill_money + "%'");
		}
		
//		if (!StringUtils.isEmpty(bill_start_date)){
//			dbSql.setWhere("bill_start_date like '%" + bill_start_date + "%'");
//		}
		
		if (!StringUtils.isEmpty(due_dt)){
			dbSql.setWhere("due_dt '%" +due_dt + "%'");
		}
		if (!StringUtils.isEmpty(description)){
			dbSql.setWhere("description like '%" + description + "%'");
		}
		if (!StringUtils.isEmpty(status)){
			dbSql.setWhere("status like '%" + status + "%'");
		}
		if (!StringUtils.isEmpty(bill1)){
			dbSql.setWhere("bill1 like '%" + bill1 + "%'");
		}
		
		
		if (!StringUtils.isEmpty(bill2)){
			dbSql.setWhere("bill2 like '%" + bill2 + "%'");
		}
		
		if (!StringUtils.isEmpty(postdate)){
			dbSql.setWhere("postdate like '%" + postdate + "%'");
		}
		
		if (!StringUtils.isEmpty(enterdate)){
			dbSql.setWhere("enterdate '%" +enterdate + "%'");
		}
		if (!StringUtils.isEmpty(remitter)){
			dbSql.setWhere("outbillname like '%" + remitter + "%'");
		}
		if (!StringUtils.isEmpty(urgeapplyname)){
			dbSql.setWhere("urgeapplyname like '%" + urgeapplyname + "%'");
		}
		if (!StringUtils.isEmpty(courtname)){
			dbSql.setWhere("courtname like '%" + courtname + "%'");
		}
		
		
		if (!StringUtils.isEmpty(matchfileno)){
			dbSql.setWhere("matchfileno like '%" + matchfileno + "%'");
		}
		if (!StringUtils.isEmpty(issue_dt)){
			dbSql.setWhere("issue_dt like '%" + issue_dt + "%'");
		}
		
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<RiskBill> resultList = new ArrayList<RiskBill>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), RiskBill.class, startIndex, page.getShowCount(), dbSql.getParamList());
    		
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}
    	
//    	mapField.put("id", id);
    	mapField.put("bill_no", bill_no);
    	mapField.put("msg_id", msg_id);
    	mapField.put("bill_type", bill_type);
    	mapField.put("acceptor", acceptor);
    	mapField.put("acceptor_bank_no", acceptor_bank_no);
    	
    	mapField.put("bill_money", bill_money);
//    	mapField.put("bill_start_date", bill_start_date);
    	mapField.put("due_dt", due_dt);
    	mapField.put("description", description);
    	mapField.put("status", status);
    	mapField.put("bill1", bill1);
    	
    	
    	mapField.put("bill2", bill2);
    	mapField.put("postdate", postdate);
    	mapField.put("enterdate", enterdate);
    	mapField.put("remitter", remitter);
    	mapField.put("urgeapplyname", urgeapplyname);
    	mapField.put("courtname", courtname);
    	
    	mapField.put("matchfileno", matchfileno);
    	mapField.put("issue_dt", issue_dt);
    	mapField.put("order_by", order_by);
		
		mv.setViewName("system/riskbill/riskbill_list");
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
		
	
		String dt=DateTimeUtil.getWorkdayString();//获得当前营业日
		
	
		//取用户处理类
        IRiskBillService riskbillService = ServiceFactory.getRiskBillService();
		
        RiskBill riskbill = new RiskBill();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		
        String id =sequenceService.getRiskBillId();
        
        String ids = request.getParameter("id");
		String bill_no = request.getParameter("bill_no");
		String msg_id = request.getParameter("msg_id");
		String bill_type = request.getParameter("bill_type");
		String acceptor = request.getParameter("acceptor");
		String acceptor_bank_no= request.getParameter("acceptor_bank_no");
		
		String bill_money = request.getParameter("bill_money");
//		String bill_start_date = request.getParameter("bill_start_date");
		String due_dt = request.getParameter("due_dt");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String bill1 = request.getParameter("bill1");
		
		String bill2 = request.getParameter("bill2");
		String postdate = request.getParameter("postdate");
//		String enterdate = request.getParameter("enterdate");
		String remitter = request.getParameter("remitter");
		String urgeapplyname = request.getParameter("urgeapplyname");
		String courtname = request.getParameter("courtname");
		
		
		String matchfileno = request.getParameter("matchfileno");
		String issue_dt = request.getParameter("issue_dt");
		String isedit = request.getParameter("isedit");
		
		
		riskbill.setBillNo(bill_no);
		riskbill.setMsgId(msg_id);
		riskbill.setBillType(bill_type);
		riskbill.setAcceptor(acceptor);
		riskbill.setAcceptorBankNo(acceptor_bank_no);
		riskbill.setBillMoney(StringUtils.isBlank(bill_money)?0.00d:Double.parseDouble(bill_money));

		riskbill.setDueDt(due_dt);
		riskbill.setDescription(description);
		riskbill.setStatus(status);
		riskbill.setBill1(bill1);
		
		riskbill.setBill2(bill2);
		riskbill.setPostdate(postdate);
		riskbill.setEnterdate(dt);
		riskbill.setRemitter(remitter);
		riskbill.setUrgeapplyname(urgeapplyname);
		riskbill.setCourtname(courtname);
		
		
		riskbill.setMatchfileno(matchfileno);
		riskbill.setIssueDt(issue_dt);
		
		
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //修改操作
				riskbill.setId(ids);
				riskbillService.modifyRiskBill(riskbill);
			}else{
				riskbill.setId(id);
				riskbillService.addRiskBill(riskbill);  //新增
			}
			
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录trisk_bill失败");
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
		String dt;
		try {
			dt = DateTimeUtil.getWorkdayString();
			mv.addObject("dt", dt);
		} catch (BizAppException e) {

			e.printStackTrace();
		}//获得当前营业日
		
		mv.setViewName("system/riskbill/riskbill_edit");
		
		mv.addObject("isedit", "0");
		
		return mv;
	}
	

	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		
		
		
		String id = request.getParameter("id");
		String sql = "select * from trisk_bill where id = ?";
		
		RiskBill riskbill = new RiskBill();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		riskbill = session.getObject(sql, RiskBill.class, id);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/riskbill/riskbill_edit");
		mv.addObject("riskbill", riskbill);
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
		
		String ids = request.getParameter("ids");
		
		String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(ids));
		
		String sql = "delete from trisk_bill where id in(" + sqlInParts+  ")";
		
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
