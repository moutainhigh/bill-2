/********************************************
 * 文件名称: DiscAuditController.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-08-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.web.busicontroller.disc;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.web.busicontroller.common.DiscCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 贴现入库Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/discStorageController")
public class DiscStorageController extends BaseController {

	/**
	 * 待入库批次列表
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=storageBatchList")
	public ModelAndView storageBatchList(Page page,DiscSearchBean query) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_storage_batch_list");
		IDiscService discService = ServiceFactory.getDiscService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		try {
			page.activeCommand();
			query.addProperty2TableObj("branchNo", "apply");
			query.setBranchNo(user.getBranchNo());
			query.setBillClass(DiscCodeConst.BILL_CLASS_ENTITY);
			query.setOpers(StatusUtils.queryStatus("DiscStorageController", "doStorage", null));
			List<DiscApplyInfo> batchList = discService.getDiscApplyListForCondition(page,query);
			mv.addObject("page", page);
			mv.addObject("batchList", batchList);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("贴现记账查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 入库票据列表页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=storageDetailList")
	public ModelAndView storageDetailList(Page page,DiscSearchBean query) throws BizAppException{
		IDiscService discService = ServiceFactory.getDiscService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		ModelAndView mv = new ModelAndView("busi/disc/entity/disc_storage_detail_list");
		try {
			session.beginTransaction();
			page.activeCommand();
			query.setBranchNo(user.getBranchNo());
			query.setOpers(StatusUtils.queryStatus("DiscStorageController", "doStorage", null));
			mv.addObject("resultList",discService.getDiscBillListForBatch(page,query));
			mv.addObject("batch",discService.getDiscApplyInfo(query.getDiscId(),query));
			DiscApplyInfo disapplyinfo = discService.getDiscApplyInfo(query.getDiscId(),query);
			IProductService productservice = ServiceFactory.getProductService();
			Product prod = productservice.getProductInfoByProdNo(disapplyinfo.getProdNo());
			String ProdName = prod.getProdName();
			mv.addObject("ProdName",ProdName);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("待入库票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "待入库票据列表查询失败");
			
		}
		mv.addObject("page", page);
		mv.addObject("query", query);
		return mv;
	}
	
	
	/**
	 * 处理状态
	 * @throws Exception 
	 */
	@RequestMapping(params="method=doStorage")
	@ResponseBody
	public AjaxJson doStorage(String discmx_ids) throws Exception{
		AjaxJson retJson = new AjaxJson();
		IDiscService discService = ServiceFactory.getDiscService();
		int rs = 0;
		try {
			rs = discService.discStorageForDiscmxId(discmx_ids);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		retJson.setSuccess(rs>0);
		return retJson;
	}

	
	

}
