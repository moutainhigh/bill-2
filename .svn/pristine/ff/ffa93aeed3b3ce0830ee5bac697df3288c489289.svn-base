/********************************************
 * 文件名称: BbspProductController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-21 下午02:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;

/** 
 * 类名称：SignProdController
 * 创建人：dq
 * 创建时间：2016年7月21日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/signProdController")
public class SignProdController extends BaseController{
public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(Page page,SignProd signProd)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		Map<String, String> mapField = new HashMap<String,String>();	
		ISignProdService signProdService = ServiceFactory.getSignProdService();
    	mapField.put("prodNo", signProd.getProdNo());
    	mapField.put("busSettleAcct", signProd.getBusSettleAcct());
    	mapField.put("signStatusCd", signProd.getSignStatusCd());
		mv.setViewName("system/signProd/signProd_list");
		mv.addObject("resultList", signProdService.getSignProdInfoBySignStatusCd(page, signProd));
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		return mv;
		
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(String isedit,SignProd signProd) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		ISignProdService signProdService = ServiceFactory.getSignProdService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				signProdService.modifySignProd(signProd);
			}else{
				signProdService.addSignProd(signProd);  //插入
			}
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.getMessage();
			
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
		mv.setViewName("system/signProd/signProd_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 判断客户账号是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(SignProd signProd) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		ISignProdService signProdService = ServiceFactory.getSignProdService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		Branch branch = ServiceFactory.getBranchService().getBranch(user.getBranchNo());
		if ("1".equals(branch.getBranchLevel())) {
			retJson.setSuccess(false);
			throw new BizAppException("总行柜员不能操作此业务");
		}
    	// ---账号级签约检查开始--------
    	SignProd sign=signProdService.getSignProdByBusAct(signProd.getProdNo(), signProd.getBusSettleAcct());
    	if(sign!=null){
    		retJson.setSuccess(false);
    		throw new BizAppException("账号已在[ "+ sign.getSignOrg() +" ]机构签约成功,请勿重复签约！");
    	}
		return retJson;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(String id) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		ISignProdService signProdService = ServiceFactory.getSignProdService();
		SignProd signProd=signProdService.getSignProdById(id);
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		Branch branch = ServiceFactory.getBranchService().getBranch(user.getBranchNo());
		if ("1".equals(branch.getBranchLevel())) {
			throw new BizAppException("总行柜员不能操作此业务");
		}
		mv.setViewName("system/signProd/signProd_edit");
		mv.addObject("signProd", signProd);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
}
