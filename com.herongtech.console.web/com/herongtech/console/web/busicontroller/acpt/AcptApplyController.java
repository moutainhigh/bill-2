package com.herongtech.console.web.busicontroller.acpt;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptQueryCondition;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.acpt.IAcptService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 承兑申请Controller
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/acptApplyController")
public class AcptApplyController extends BaseController {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
   
	/*****************************************************电票系统***************************************************************/
	/************电票接收**********************/
	/**
	 * 银承电票接收页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(Page page, AcptQueryCondition searchBean) throws Exception {

		ModelAndView mv = new ModelAndView();
		page.activeCommand();
		if(searchBean==null){
			searchBean=new  AcptQueryCondition();
		}
	    UserLoginfo user=ResourceUtil.getSessionLoginfo();
	    searchBean.setBranchNo(user.getBranchNo());//取管理员的机构号
		searchBean.setStatus(StatusUtils.queryStatus("AcptApplyController", "transFor034", null)[0]);
		searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
//		searchBean.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
//		searchBean.setAcceptorBankNo(ResourceUtil.getSessionLoginfo().getBrchBankNo());
		// 分页开始位置
		List<AcptBillInfo> resultList = new ArrayList<AcptBillInfo>();
		try {
			resultList=ServiceFactory.getAcptService().getAcptBillListForBatch(page, searchBean);
		} catch (BizAppException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询银承列表失败");
		}
		mv.setViewName("busi/acpt/electronic/ebillAccept_list");
		mv.addObject("resultList", resultList);
		mv.addObject("searchBean",searchBean);
		mv.addObject("page", page);
		return mv;

	}

	// 确认签收,修改票据状态为预签收

	@RequestMapping(params = "method=acceptBill")
	public ModelAndView acceptBill(String acptmx_ids) throws Exception {

        IAcptService acptService=ServiceFactory.getAcptService();
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			acptService.acceptBillForAcptmxId(acptmx_ids);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("接收失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "接收失败:"+e.getMessage());
		}
		return list(new Page(), null);
	}

	// 拒绝签收
	@RequestMapping(params = "method=refuseBill")
	public ModelAndView refuseBill(String acptmx_ids) throws Exception {

		String[] ids = CommUtils.couvertLong(acptmx_ids);
		IAcptService acptService=ServiceFactory.getAcptService();
		IDB session = DBFactory.getDB();// 获取数据库连接
		acptService.refuseBillAcptmxId(acptmx_ids);
		for (int i = 0; i < ids.length; i++) {
					
			String id = ids[i];
			try {
				session.beginTransaction();
				List<AcptBillInfo> list= acptService.refuseBillForAcptmxId(id);
				RgctBill bill=RcServiceFactory.getRcAcptBillService().getRgctBillByBillNo(list.get(0).getBillNo());
				
			    RcServiceFactory.getRcAcptBillService().acptBack(bill);
			    
			    session.endTransaction();
			} catch (SQLException e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list(new Page(), null);
	}
	/************电票接收**********************/
	
	
	/************电票导出**********************/
	/**
	 * 电子银承导出票据页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=exportBill")
	public ModelAndView acptAccount(Page page,AcptQueryCondition query) throws BizAppException, SQLException,Exception {
		ModelAndView mv = new ModelAndView("busi/acpt/electronic/export_bill");
		page.activeCommand();
		IAcptService acptService = ServiceFactory.getAcptService();
		if(query==null){
			query=new AcptQueryCondition();
		}
		try {
		    UserLoginfo user=ResourceUtil.getSessionLoginfo();
		    query.setBranchNo(user.getBranchNo());//取管理员的机构号
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			query.setStatus(StatusUtils.queryStatus("AcptAuditController", "rejectLoanNotification", null)[0]);
			List<AcptApplyInfo> batchList = acptService.getAcptApplyListForCondition(page,query);
			mv.addObject("batchList", batchList);
			mv.addObject("page", page);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("银承记账查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "银承记账查询失败");
		}
		return mv;
	}
	/************电票导出**********************/
}


