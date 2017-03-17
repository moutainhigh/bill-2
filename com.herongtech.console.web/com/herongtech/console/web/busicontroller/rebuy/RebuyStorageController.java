package com.herongtech.console.web.busicontroller.rebuy;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 转入入库Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/rebuyStorageController")
public class RebuyStorageController extends BaseController {

	/**
	 * 待入库批次列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=listStorageEntity")
	public ModelAndView storableApplyList(Page page,RebuySearchBean searchBean) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_batch_storage_entity");
		page.activeCommand();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		AjaxJson retJson = new AjaxJson();
		if(searchBean==null){
			searchBean = new RebuySearchBean();
		}
		//设置查询条件
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setDfaultSrchTbAliasName("apply");
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setBillClass(CommonConst.BILL_CLASS_ENTITY);//纸票
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		searchBean.addProperty2TableObj("opers", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyStorageController", "storableApplyList", null));
		} catch (Exception e1) {
			throw new BizAppException("获取状态信息失败");
		}
		try {
			mv.addObject("batchList", rebuyService.getRebuyApplyListBySearchBean(page,searchBean));
		} catch (SQLException e) {
			retJson.setSuccess(false);
			CommonLog.getCommonLogCache().errorMessage("批次列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "批次列表查询失败");
		}
		
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 待入库票据清单列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=storageDetailList")
	public ModelAndView storageDetailList(Page page,String rebuyId) throws BizAppException{
		page.activeCommand();
		RebuySearchBean searchBean = new RebuySearchBean();
		ModelAndView mv = new ModelAndView("busi/rebuy/entity/rebuy_bill_storage");
		searchBean.setRebuyId(rebuyId);
		try {
			searchBean.setOpers(StatusUtils.queryStatus("RebuyStorageController", "storageDetailList", null));
		} catch (Exception e1) {
			CommonLog.getCommonLogCache().errorMessage("查询票据状态失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询票据状态失败");
		}
		try {
			IRebuyService rebuyService = ServiceFactory.getRebuyService();
			mv.addObject("batch",rebuyService.getApplyInfoById(rebuyId,searchBean));
			mv.addObject("resultList",rebuyService.getRebuyBillListBySearchBean(searchBean,page));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("rebuyId", rebuyId);
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 入库提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=storageSubmit")
	public ModelAndView storageSubmit(String ids,String rebuyId) throws BizAppException{
		ModelAndView mv = null;
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			rebuyService.storageSubmit(ids,rebuyId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据申请提交失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据申请提交失败:"+e.getMessage());
		}
		mv = this.storableApplyList(new Page(),new RebuySearchBean());
		return mv;
	}
}
