package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ExportExcel;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.ExportInfoBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.balanceSearch.IBalanceSearchService;
import com.herongtech.console.service.common.export.IExportService;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.SubcollCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.BillSearchBean;

/**
 * 余额类查询Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/balanceSearchController")
public class BalanceSearchController extends BaseController {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20161015 @describe ";
	
	/**
	 * 去余额查询页面
	 * @param page
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=toSearch")
	public ModelAndView toSearch(String branchNo) throws BizAppException{
		ModelAndView mv = new ModelAndView("system/balanceSearch/balance_search");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		branchNo=user.getBranchNo();
		mv.addObject("branchNo", branchNo);
		return mv;
	}
	
	/**
	 * 查询余额
	 * @param serviceType
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=searchBalance")
	public ModelAndView searchBalance(Page page,String serviceType,String billNo,String branchNo) throws BizAppException{
		ModelAndView mv = new ModelAndView("system/balanceSearch/balance_search");
		page.activeCommand();
		IBalanceSearchService balService = ServiceFactory.getIBalanceSearchService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		BillSearchBean bean = new BillSearchBean();
		List list = null;
		try {
			bean.setBranchNo(branchNo);
			bean.setBillNo(billNo);
			list = balService.getBalanceByType(serviceType, bean, page);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查询失败");
			throw new BizAppException("查询失败,"+e.getMessage());
		}
		IBranchService branchService = ServiceFactory.getBranchService();
		// 取客户经理基本信息
		Branch branch = branchService.getBranch(branchNo);
		branchNo=user.getBranchNo();
		mv.addObject("serviceType", serviceType);
		mv.addObject("billNo", billNo);
		mv.addObject("branchNo", branchNo);
		mv.addObject("branch", branch);
		mv.addObject("page", page);
		mv.addObject("resultList", list);
		return mv;
	}
	/**
	 * 去余额详情页面
	 * @param xxxmxId 指对应业务的billInfo表的ID，例如贴现DiscBillInfo表的主键discmxId
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params = "method=goBalanceDetail")
	public ModelAndView goBalanceDetail(String serviceType,String xxxmxId,String billNo) throws BizAppException{
		ModelAndView mv = new ModelAndView("system/balanceSearch/balance_detail");
		IBalanceSearchService balService = ServiceFactory.getIBalanceSearchService();
		try {
			mv.addObject("obj", balService.getBalanceDetailByXxxmxId(serviceType, xxxmxId));
			mv.addObject("billNo", billNo);
			mv.addObject("serviceType", serviceType);
			mv.addObject("xxxmxId", xxxmxId);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查看详情失败");
			throw new BizAppException("查看详情失败，"+e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 去余额详情页面
	 * @param xxxmxId 指对应业务的billInfo表的ID，例如贴现DiscBillInfo表的主键discmxId
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params = "method=doExport")
	public void doExport(String tableName,String billNo,String leftList,String rightList,HttpServletResponse response,HttpServletRequest request) throws BizAppException{
		IExportService exService = ServiceFactory.getExportService();
		BillSearchBean bean = new BillSearchBean();
		try {
			bean.setBillNo(billNo);
			bean.addSqlPropretyMapping("statusArray", "operStatus");
			bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
			bean.addProperty2TableObj("statusArray", "bill");
			bean.addProperty2TableObj("prodNo", "bill");
			bean.addProperty2TableObj("billNo", "bill");
			bean.addProperty2TableObj("billSource", "bill");
			QueryCondition qc = new QueryCondition();
			String excelName = "贴现余额表";
			if("VW_DISC_BALANCE".equals(tableName)){//贴现余额
				//入库、记账成功
				String[] opers = {StatusUtils.handleStatusNoCheck("DiscAccountController", "doAccount", null),
						StatusUtils.handleStatusNoCheck("DiscStorageController", "doStorage", null)};
				bean.setStatusArray(opers);
				qc.add("bill.gath_Mney_Type=:gathMneyType", RcConstants.GATH_TYPE_COMMON);
				excelName = "贴现余额表";
			}else if("VW_REBUY_BALANCE".equals(tableName)){//转贴现转入余额
				//记账通过、入库、返售逾期
				String[] opers = {StatusUtils.handleStatusNoCheck("RebuyAccountController", "accountSubmit", null),
						StatusUtils.handleStatusNoCheck("RebuyStorageController", "storageSubmit", null)};
				bean.setStatusArray(opers);
				qc.add("bill.gath_Type=:gathType", RcConstants.GATH_TYPE_COMMON);
				tableName = "VW_REBUY_BALANCE";
				excelName = "转贴现转入余额表";
			}else if("VW_SAVE_BALANCE".equals(tableName)){//质押票据
				bean.setBillSource(RcConstants.BILL_SOURCE_COLLATERALIZATION);
				bean.setProdNo(CollateCodeConst.impawnBillProd_no);
				tableName = "VW_SAVE_BALANCE";
				excelName = "质押票据表";
			}else if("VW_SUBCOLL_BALANCE".equals(tableName)){//托收在途
				bean.setStatus(null);
				qc.add("bill.status=:status", SubcollCodeConst.SUB_STAUTS_ONWAY);
				tableName = "VW_SUBCOLL_BALANCE";
				excelName = "托收在途表";
			}else{
				throw new BizAppException("业务类型选择错误！");
			}
			//保存导出格式信息
			ExportController.updatexportInfo(tableName, leftList, rightList);
			List<ExportInfoBean> rsList = exService.exportInfoList(tableName);
			List<String> list = Lists.newArrayList();
			List<ExportInfoBean> exportlist = Lists.newArrayList();
			for (ExportInfoBean eBean : rsList) {
				if("1".equals(eBean.getIsExport())){
					list.add(eBean.getExFieldName());
					exportlist.add(eBean);
				}
			}
			ExportExcel.exportData(request,response,excelName,exportlist,list,exService.getHeaderListAndResultSet(tableName, bean, qc,"bill"));
		} catch (Throwable e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查询失败");
			throw new BizAppException("查询失败,"+e.getMessage());
		}
	}
}
