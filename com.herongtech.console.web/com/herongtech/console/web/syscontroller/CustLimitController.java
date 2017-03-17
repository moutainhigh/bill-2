/********************************************
 * 文件名称: CustLimitController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-10-25 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustLimit;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ICustLimitService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：CustLimitController
 * 创建人：songzx
 * 创建时间：2016年10月25日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/custLimitController")
public class CustLimitController extends BaseController{
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 新增客户余额
	 */
	@RequestMapping(params="method=doSave")
	public ModelAndView doSave(String isedit,CustLimit cb) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			ICustLimitService service = ServiceFactory.getCustLimitService();
			session.beginTransaction();
			if ("1".equals(isedit)) { // 编辑操作
				service.modCustLimit(cb);
			} else {
				service.addCustLimit(cb); // 插入
			}
			session.endTransaction();
			mv.addObject("msg", "success");
			mv.setViewName("save_result");
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tMenu失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		return mv;
	}
	
	/**
	 * 新增客户余额
	 */
	@RequestMapping(params="method=toAdd")
	public ModelAndView toAdd(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/custLimit/cust_limit_edit");
		mv.addObject("isedit", "0");
		mv.addObject("cb",new CustLimit());
		return mv;
	}
	
	/**
	 * 修改客户余额
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(String custNo) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		ICustLimitService service = ServiceFactory.getCustLimitService();
		try {
			mv.addObject("cb", service.getCustLimit(custNo));
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库查找错误");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库查找错误");
		}
		mv.setViewName("system/custLimit/cust_limit_edit");
		mv.addObject("isedit", "1");
		return mv;
	}
		
	/**
	 * 功能描述：查询列表页
	 * @return
	 */
	@RequestMapping(params="method=cbList")
	public ModelAndView cbList(Page page){
		ModelAndView mv = new ModelAndView();
		ICustLimitService service = ServiceFactory.getCustLimitService();
		page.activeCommand();
		try {
			mv.addObject("rsList",service.getList(page));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.setViewName("system/custLimit/cust_limit_list");
		return mv;
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
	@RequestMapping(params = "method=getCustInfo")
	@ResponseBody
	public AjaxJson getCustInfo(String custNo){
		AjaxJson retJson = new AjaxJson();
		// 获取客户信息和账号处理类
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		CustInfo custInfo;
		try {
			custInfo = custInfoService.getParam(custNo);
		} catch (BizAppException e) {
			retJson.setMsg("查询数据库信息失败");
			retJson.setSuccess(false);
			return retJson;
		}
		if(custInfo==null){
			// 获取客户信息和账号处理类
			IEcdsBankDataService bankService = RcServiceFactory.getEcdsBankDataService();
			EcdsBankData bankData;
			try {
				bankData = bankService.getEcdsBankData(custNo);
			} catch (BizAppException e) {
				retJson.setMsg("查询数据库信息失败:"+e.getMessage());
				retJson.setSuccess(false);
				return retJson;
			}
			if(bankData==null){
				retJson.setMsg("无法找到对应的客户信息，请检查客户号信息是否有误");
				retJson.setSuccess(false);
				return retJson;
			}else{
				custInfo = new CustInfo();
				custInfo.setCustName(bankData.getActorFullCall());
				custInfo.setCustType("1");//同业客户
			}
		}
		//企业客户
		custInfo.setCustType("2");
		retJson.setObj(custInfo);
		retJson.setSuccess(true);
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
	@RequestMapping(params = "method=checkExist")
	@ResponseBody
	public AjaxJson checkExist(String custNo) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		ICustLimitService service = ServiceFactory.getCustLimitService();
		try {
			CustLimit cb = service.getCustLimit(custNo);
			
			if(cb!=null){
				retJson.setSuccess(true);
				retJson.setMsg("客户余额已经存在！");
			}else{
				retJson.setSuccess(false);
			}
		} catch (SQLException e) {
			retJson.setSuccess(true);
			retJson.setMsg("客户余额查询失败！");
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库查找错误");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库查找错误");
		}
		
		return retJson;
	}
}
