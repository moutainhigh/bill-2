/********************************************
 * 文件名称: AssuApplyController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-11-18 下午02:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.busicontroller.assu;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.domain.assu.bean.AssuSearchBean;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.assu.IAssuService;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.GuarCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;

/**
 * 保证Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/assuApplyController")
public class AssuApplyController extends BaseController{
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 客户定位，电票管理页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params =  "method=elecBillManage" )
	public ModelAndView elecBillManage(String custNo) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/assu/electronic/assu_elec_apply_billinfo_manage");
		mv.addObject("custNo", custNo);
		return mv;
	}
	
	/**
	 * 检查客户信息是否存在
	 * @param custNo
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=checkuser")
	@ResponseBody
	public AjaxJson checkuser (String custNo)throws BizAppException {

		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();
		// 获取客户信息和账号处理类
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		// 取用户基本信息
		CustInfo custInfo = custInfoService.getParam(custNo);
		if (custInfo == null) {
			retJson.setMsg("根据客户号无法找到对应的客户信息");
			retJson.setSuccess(false);
			return retJson;
		}
		if ("".equals(custInfo.getCustType())) {
			retJson.setMsg("根据客户号无法找到对应的客户信息");
			retJson.setSuccess(false);
			return retJson;
		}
		retMap.put("custNo", custNo);
		retMap.put("custName", custInfo.getCustName());

		retJson.setAttributes(retMap);
		retJson.setSuccess(true);

		return retJson;
	}
	
	/**
	 * 可保证票据查询管理页面
	 * @param page
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=searchElecBill")
	public ModelAndView searchElecBill (Page page,String custNo) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/assu/electronic/assu_elec_apply_billinfo_manage");
		IAssuService assuService = ServiceFactory.getAssuService();
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		CustInfo custInfo = custInfoService.getParam(custNo);
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		page.activeCommand();
		mv.addObject("resultList",assuService.getAssuBillInfo(page, GuarCodeConst.ASSU_STATUS_11 , IDict.K_BILL_CLASS.K_ELEC_BILL, custInfo.getOrgCode(), user.getBrchBankNo()));
		mv.addObject("page", page);
		mv.addObject("custName", custInfo.getCustName());
		mv.addObject("custNo", custNo);
		return mv;
	}
	
	/**
	 * 接收检查勾选票据
	 * @param ids
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecCheckBill")
	@ResponseBody
	public AjaxJson elecCheckBill(String ids) throws BizAppException{
		AjaxJson retJson = new AjaxJson();
		Map<String, Object> retMap = new HashMap<String, Object>();
		IAssuService assuService = ServiceFactory.getAssuService();
		try {
			List<AssuBillInfo> list=assuService.getAssuBillInfoForId(ids);
			String billType=list.get(0).getBillType();
			if(list.size()==1){
				retMap.put("billType", billType);
				retJson.setAttributes(retMap);
				retJson.setSuccess(true);
			}else{
				for(int i = 0;i<list.size();i++){
					if(billType.equals(list.get(i).getBillType())){
						retMap.put("billType", billType);
						retJson.setAttributes(retMap);
						retJson.setSuccess(true);
					}else{
						retJson.setMsg("您所选择的票据类型没有统一!");
						retJson.setSuccess(false);
						return retJson;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		return retJson;
	}
	
	/**
	 * 电票拒接
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=elecRefuseBill")
	public ModelAndView elecRefuseBill(String ids,String custNo) throws BizAppException{
		IAssuService assuService = ServiceFactory.getAssuService();
		try {
			assuService.ElecRefuseBill(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException("拒绝失败");
		}
		return searchElecBill(new Page(),custNo);
	}
	
	/**
	 * 电票待签收界面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=elecSignBill")
	public ModelAndView elecSignBill(String ids, String custNo, String custName) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/assu/electronic/assu_elec_sign_bill");
		IAssuService assuService = ServiceFactory.getAssuService();
		List<AssuBillInfo> list = assuService.getAssuBillInfoForId(ids);
		Double totalMoney = 0.0;
		for (int i = 0; i < list.size(); i++) {
			totalMoney += list.get(i).getBillMoney();
		}
		Page page = new Page();
		page.setTotalResult(list.size());
		page.activeCommand();
		mv.addObject("ids", ids);
		mv.addObject("custNo", custNo);
		mv.addObject("custName", custName);
		mv.addObject("batchId", ServiceFactory.getSequenceService().getAssuApplyNo(ResourceUtil.getSessionLoginfo().getBranchNo()));
		mv.addObject("totalMoney", totalMoney);
		mv.addObject("totalNum", list.size());
		mv.addObject("createDt", DateTimeUtil.getWorkdayString());
		mv.addObject("page", page);
		mv.addObject("resultList", list);
		return mv;
	}
	
	/**
	 * 电票保证签收
	 * @param ids
	 * @param asb
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params =  "method=elecSignBills")
	public ModelAndView elecSignBills(String ids, AssuSearchBean asb) throws BizAppException {
		IAssuService assuService = ServiceFactory.getAssuService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		if(asb == null){
			asb = new AssuSearchBean();
		}
		try {
			session.beginTransaction();
			assuService.ElecAssuSign(ids,asb);
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new BizAppException("事物回滚失败");
			}
			e.printStackTrace();
			throw new BizAppException("签收失败");
		}
			return searchElecBill(new Page(),asb.getCustNo());
	}
}
