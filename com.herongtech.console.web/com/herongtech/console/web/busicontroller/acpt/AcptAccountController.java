package com.herongtech.console.web.busicontroller.acpt;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptColltnReg;
import com.herongtech.console.domain.acpt.bean.AcptQueryCondition;
import com.herongtech.console.domain.acpt.bean.AcptSspdReg;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.acpt.IAcptService;
import com.herongtech.console.service.sequence.SequenceService;
import com.herongtech.console.web.busicontroller.common.AcptCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 承兑记账ControllerController
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/acptAccountController")
public class AcptAccountController extends BaseController {
	
	/*****************************************************纸票系统***************************************************************/
	
	/***************纸票承兑记账模块***************/
	/**
	 * 纸票承兑记账列表
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=entityAccountList")
	public ModelAndView entityAccountList(Page page,AcptQueryCondition query) throws BizAppException, SQLException,Exception {
		ModelAndView mv = new ModelAndView("busi/acpt/entity/batch_manage");
		String billClass=IDict.K_BILL_CLASS.K_ENTY_BILL;
		
		IAcptService acptService = ServiceFactory.getAcptService();
		try {
			page.activeCommand();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());//取管理员的机构号
			query.setStatus(StatusUtils.queryStatus("AcptAccountController", "entityAccountSucceed", null)[0]);
			query.setBillClass(billClass);
			query.setBillStatus(AcptCodeConst.BILL_STATUS_ACCPTNC);
			List<AcptApplyInfo> batchList = acptService.getAcptApplyListForCondition(page,query);
			mv.addObject("batchList", batchList);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("银承纸票记账查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "银承纸票记账查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}
	
	/**
	 * 纸票承兑记账清单页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=entitybill")
	public ModelAndView entitybill(Page page,AcptQueryCondition query) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/acpt/entity/bill_manage");
		String billClass=IDict.K_BILL_CLASS.K_ENTY_BILL;
		try {
			page.activeCommand();
			IAcptService acptService = ServiceFactory.getAcptService();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());//取管理员的机构号
		    query.setStatus(StatusUtils.queryStatus("AcptAccountController", "entityAccountSucceed", null)[0]);
			query.setBillClass(billClass);
			mv.addObject("batch",acptService.getAcptApplyForAcptID(query));
			mv.addObject("resultList",acptService.getAcptBillListForBatch(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("page", page);
		/*mv.addObject("page", page);
		*//*mv.addObject("query", query);*/
		return mv;
	}
	

	/**
	 * 承兑记账提交
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=doAccount")
	@ResponseBody
	public AjaxJson doAccount(String ids) throws BizAppException{
		IAcptService acptService = ServiceFactory.getAcptService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		AjaxJson retJson = new AjaxJson();
		try {
			boolean b= false;
			if(b=acptService.check(ids)){
				session.beginTransaction();
				acptService.doAccount(ids);
				session.endTransaction();
			}else{
				retJson.setSuccess(false);
				retJson.setMsg("请添加票号");
				return retJson;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			retJson.setMsg("服务器异常");
			retJson.setSuccess(false);
			return retJson;
		
		}
		retJson.setSuccess(true);
		return retJson;
	}
	/**
	 * 交易凭证填写页面
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "method=doCuments")
	public ModelAndView toAudit(String ids){
		ModelAndView mv = new ModelAndView("busi/acpt/entity/documents_submitted");
		mv.addObject("ids", ids);
		return mv;
	}
	
	/**
	 * 交易凭证填写
	 * @param ids
	 * @param status
	 * @param option
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=doCumentsSubmitted")
	@ResponseBody
	public AjaxJson doCumentsSubmitted(String ids,String billNo) throws BizAppException{
		IAcptService acptService = ServiceFactory.getAcptService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		AjaxJson retJson = new AjaxJson();
		
			boolean rs= false;
		    String regEx = "^[A-Za-z0-9]{16}$";
		    Pattern pattern = Pattern.compile(regEx);
		    Matcher matcher = pattern.matcher(billNo);
		    rs = matcher.matches();
			if(rs==false){
				retJson.setSuccess(false);
				retJson.setMsg("交易凭证只能填写16位数字或字母");
				return retJson;
			}
		
		try {
			session.beginTransaction();
			acptService.cumentsSubmitted(ids,billNo);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			retJson.setSuccess(false);
			retJson.setMsg("票据交易凭证提交失败");
			return retJson;
		}
		retJson.setSuccess(true);
		return retJson;
		
	}
	/***************纸票承兑记账模块***************/
	
	/***************纸票承兑未用退回模块***************/

	/**
	 * 纸票未用退回页面
	 * 
	 */
	@RequestMapping(params = "method=notUseReturnList")
	public ModelAndView notUseReturnList(Page page,AcptQueryCondition query) throws BizAppException, SQLException,Exception {
		ModelAndView mv = new ModelAndView("busi/acpt/entity/notUseReturn_list");
		page.activeCommand();
		if(query==null){
			query=new AcptQueryCondition();
		}
		List<AcptBillInfo> resultList = new ArrayList<AcptBillInfo>();
		try {
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());//取管理员的机构号
			query.setStatus(StatusUtils.queryStatus("AcptAccountController", "notUseReturn", null)[0]);
//			query.setBranchNo();
			query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
			resultList =ServiceFactory.getAcptService().getAcptBillListForBatch(page, query);
			mv.addObject("resultList", resultList);
			mv.addObject("page", page);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("银承清单查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "银承清单查询失败");
		}
		return mv;
	}

	/**
	 * 退回按钮操作
	 * 
	 */
		@RequestMapping(params = "method=notUseReturn")
		public ModelAndView notUseReturn(String ids) throws Exception {

	        IAcptService acptService=ServiceFactory.getAcptService();
			
			IDB session = DBFactory.getDB(); // 获取数据库连接
			try {
				session.beginTransaction();
				acptService.notUseReturnForAcptmxId(ids);
				session.endTransaction();
			} catch (Exception e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return notUseReturnList(new Page(), null);
		}

		/***************纸票承兑未用退回模块***************/
	
	
	
		/***************纸票承兑挂失止付模块***************/
		/**
		 * 挂失止付页面
		 * 
		 */
		@RequestMapping(params = "method=reportOfLossAndSp")
		public ModelAndView reportOfLossAndSp(Page page, AcptQueryCondition searchBean) throws Exception {

			ModelAndView mv = new ModelAndView();
			page.activeCommand();
			if(searchBean==null){
				searchBean=new  AcptQueryCondition();
			}
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			searchBean.setBranchNo(user.getBranchNo());//取管理员的机构号
			searchBean.setBillStatus(AcptCodeConst.BILL_STATUS_ISSUE);
			searchBean.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
			// 分页开始位置
			List<AcptBillInfo> resultList = new ArrayList<AcptBillInfo>();
			try {
				resultList=ServiceFactory.getAcptService().getAcptBillListForBatch(page,searchBean);
			} catch (BizAppException e) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询挂失列表失败");
			}
			mv.setViewName("busi/acpt/entity/reportOfLossAndSp");
			mv.addObject("resultList", resultList);
			mv.addObject("searchBean",searchBean);
			mv.addObject("page", page);
			return mv;
		}
		
		
		
		/**
		 * 挂失，去修改页面
		 * 
		 */
		@RequestMapping(params = "method=reportOfLoss")
		public ModelAndView reportOfLoss(String acptmx_ids,AcptQueryCondition query) throws Exception {
				ModelAndView mv = new ModelAndView();
			
				try {
					IAcptService acptService = ServiceFactory.getAcptService();
						mv.addObject("query",acptService.getacptBillForAcptmxId(acptmx_ids));
				} catch (Exception e) {
					e.printStackTrace();
					CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
				}
				mv.setViewName("busi/acpt/entity/reportOfLossAndSp_edit");
				mv.addObject("isedit", "0");
				return mv;
			}
	
		/**
		 * 解挂，去修改页面
		 * 
		 */
		@RequestMapping(params = "method=remove1")
		public ModelAndView remove1(String acptmx_ids,AcptQueryCondition query) throws Exception {
				ModelAndView mv = new ModelAndView();
				
				try {
					IAcptService acptService = ServiceFactory.getAcptService();
					mv.addObject("query",acptService.getacptBillForAcptmxId(acptmx_ids));
				} catch (Exception e) {
					e.printStackTrace();
					CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
				}

				mv.setViewName("busi/acpt/entity/reportOfLossAndSp_edit");
				mv.addObject("isedit", "1");
				return mv;
			}
		
		/**
		 * 挂失解挂保存
		 * @throws Exception 
		 * 
		 */
		@RequestMapping(params="method=save")
		public ModelAndView save(AcptQueryCondition query,AcptSspdReg obj) throws Exception {
			ModelAndView mv=new ModelAndView();
			IAcptService acptService = ServiceFactory.getAcptService();
			SequenceService se=new SequenceService();
			obj.setBillNo(query.getBillNo());
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			obj.setSspdBranchNo(user.getBranchNo());//挂失机构号
			
			if(obj==null){
				obj=new AcptSspdReg();				
			}
			obj.setId(se.getACPT_SSPD_REG_ID());			
			int rs=0;
			IDB session = DBFactory.getDB(); // 获取数据库连接	
			String isedit= query.getIsedit();	
			try {
				session.beginTransaction();
				
				if (isedit.equals("0")){		
					rs=acptService.addAcptSspdReg(obj);
					if(rs!=0){
					acptService.modifySspdStatus0(query);
					}
				}else{
					rs=acptService.addAcptSspdReg(obj);
					if(rs!=0){
					acptService.modifySspdStatus1(query);
					}
				}
				session.endTransaction();
			} catch (SQLException e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				CommonLog.getCommonLogCache().errorMessage("插入记录tacpt_sspd_reg失败");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
			}
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		}
		/***************纸票承兑挂失止付模块***************/
		
		
		/***************纸票承兑到期扣款模块***************/
		/**
		 * 纸票到期扣款页面
		 * @throws Exception 
		 * 
		 */
		@RequestMapping(params = "method=dueCharge")
		public ModelAndView dueCharge(Page page,AcptQueryCondition query) throws BizAppException, SQLException,Exception {
			ModelAndView mv = new ModelAndView("busi/acpt/entity/dueCharge_batch");
			page.activeCommand();
			IAcptService acptService = ServiceFactory.getAcptService();
			if(query==null){
				query=new AcptQueryCondition();
			}
			try {
				UserLoginfo user=ResourceUtil.getSessionLoginfo();
				query.setBranchNo(user.getBranchNo());//取管理员的机构号
				String dt=DateTimeUtil.getWorkdayString();//获得当前营业日
				query.setWorkday(dt);
			//	query.addSpecialOpertion("workday",BaseSearchBean.LESS_AND_EQUAL);
				query.addSqlPropretyMapping("workday", "dueDt");
				
				query.setBillStatus(AcptCodeConst.BILL_STATUS_ISSUE);
				query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
				List<AcptApplyInfo> batchList = acptService.getAcptApplyListForCondition(page,query);
				mv.addObject("batchList", batchList);
				mv.addObject("page", page);
			} catch (Exception e) {
				CommonLog.getCommonLogCache().errorMessage("承兑到期扣款查询失败");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "承兑到期扣款查询失败");
			}
			return mv;
		}
	
		/**
		 * 纸票到期扣款票据管理页面
		 * @param request
		 * @return
		 * @throws BizAppException
		 */
		@RequestMapping(params = "method=dueChargeBill")
		public ModelAndView dueChargeBill(Page page,AcptQueryCondition query) throws BizAppException{
			page.activeCommand();
			ModelAndView mv = new ModelAndView("busi/acpt/entity/dueCharge_bill");
			try {
				UserLoginfo user=ResourceUtil.getSessionLoginfo();
				query.setBranchNo(user.getBranchNo());//取管理员的机构号
				IAcptService acptService = ServiceFactory.getAcptService();
//				mv.addObject("batch",acptService.getAcptApplyInfo(query.getAcptId()));
				query.setBillStatus(AcptCodeConst.BILL_STATUS_ISSUE);
				mv.addObject("batch",acptService.getAcptApplyForAcptID(query));//票据管理页面的批次信息
				mv.addObject("resultList",acptService.getAcptBillListForBatch(page,query));
			} catch (Exception e) {
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
			}
			mv.addObject("page", page);
			return mv;
		}
		
		/**
		 * 纸票到期扣款按钮控制
		 * @param request
		 * @return
		 * @throws BizAppException
		 */

		@RequestMapping(params = "method=doCharge")
		public ModelAndView doCharge(AcptQueryCondition query) throws Exception {
	        IAcptService acptService=ServiceFactory.getAcptService();
			
			IDB session = DBFactory.getDB(); // 获取数据库连接
			try {
				session.beginTransaction();
				acptService.dueChargeBillForAcptmxId(query);
				session.endTransaction();
			} catch (Exception e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			return dueCharge(new Page(), null);
		}
		
		
		/**
		 * 纸票到期转垫款按钮控制
		 * @param request
		 * @return
		 * @throws BizAppException
		 */

		@RequestMapping(params = "method=doAdvance")
		public ModelAndView doAdvance(AcptQueryCondition query) throws Exception {
	        IAcptService acptService=ServiceFactory.getAcptService();
			
			IDB session = DBFactory.getDB(); // 获取数据库连接
			try {
				session.beginTransaction();
				acptService.dueAdvanceBillForAcptmxId(query);
				session.endTransaction();
			} catch (Exception e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			return dueCharge(new Page(), null);
		}
		
		
		/***************纸票承兑到期扣款模块***************/
		
		
	
		/***************纸票承兑到期付款模块***************/
		
		/**
		 * 纸票到期付款页面
		 * @throws Exception 
		 * 
		 */
		@RequestMapping(params = "method=duePayment")
		public ModelAndView duePayment(Page page,AcptQueryCondition query) throws BizAppException, SQLException,Exception {
			ModelAndView mv = new ModelAndView("busi/acpt/entity/duePayment");
			page.activeCommand();
			IAcptService acptService = ServiceFactory.getAcptService();
			if(query==null){
				query=new AcptQueryCondition();
			}
			try {
				UserLoginfo user=ResourceUtil.getSessionLoginfo();
				query.setBranchNo(user.getBranchNo());//取管理员的机构号
				String dt=DateTimeUtil.getWorkdayString();//获得当前营业日
				query.setDfaultSrchTbAliasName("bill");
				String[] billStatusArray=new String[]{"3","4"};
				query.setBillStatusArray(billStatusArray);
				query.addSpecialOpertion("billStatusArray",BaseSearchBean.IN);
				query.addSqlPropretyMapping("billStatusArray", "billStatus");
				
				
				query.setWorkday(dt);
				query.addSpecialOpertion("workday",BaseSearchBean.LESS_AND_EQUAL);
				query.addSqlPropretyMapping("workday", "dueDt");
				
				query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
				List<AcptBillInfo> resultList = acptService.getAcptBillListForBatch(page,query);
				mv.addObject("resultList", resultList);
				mv.addObject("page", page);
			} catch (Exception e) {
				CommonLog.getCommonLogCache().errorMessage("承兑到期扣款查询失败");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "承兑到期扣款查询失败");
			}
			return mv;
		}	
		
		
		
		/**
		 * 纸票到期付款，付款登记页面
		 * 
		 */
		@RequestMapping(params = "method=paymentRegister")
		public ModelAndView paymentRegister(String acptmx_ids,AcptQueryCondition query) throws Exception {
				ModelAndView mv = new ModelAndView();
			
				try {
					IAcptService acptService = ServiceFactory.getAcptService();
					mv.addObject("query",acptService.getacptBillForAcptmxId(acptmx_ids));
				} catch (Exception e) {
					e.printStackTrace();
					CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
				}
				mv.setViewName("busi/acpt/entity/duePayment_edit");
				mv.addObject("isedit", "0");
				return mv;
			}	
		
		/**
		 * 纸票到期付款，拒付登记页面
		 * 
		 */
		@RequestMapping(params = "method=noPayment")
		public ModelAndView noPayment(String acptmx_ids,AcptQueryCondition query) throws Exception {
				ModelAndView mv = new ModelAndView();
				
				try {
					IAcptService acptService = ServiceFactory.getAcptService();
					mv.addObject("query",acptService.getacptBillForAcptmxId(acptmx_ids));
				} catch (Exception e) {
					e.printStackTrace();
					CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
				}

				mv.setViewName("busi/acpt/entity/duePayment_edit");
				mv.addObject("isedit", "1");
				return mv;
			}
		
		
		
		/**
		 * 纸票到期付款保存
		 * @throws Exception 
		 * 
		 */
		@RequestMapping(params="method=paymentSave")
		public ModelAndView paymentSave(AcptQueryCondition query,AcptColltnReg obj) throws Exception {
			ModelAndView mv=new ModelAndView();
			IAcptService acptService = ServiceFactory.getAcptService();
			SequenceService se=new SequenceService();
			
			if(obj==null){
				obj=new AcptColltnReg();
				
			}
			
			String colltnId=se.getACPT_COLLTN_REG_ID();
			obj.setId(colltnId);
         	query.setColltnId(colltnId);
			int rs=0;
			IDB session = DBFactory.getDB(); // 获取数据库连接
			String isedit= query.getIsedit();
			try {
				session.beginTransaction();
				AcptBillInfo billInfo=acptService.getacptBillForAcptmxId(query.getAcptmxId());
                obj.setAcptId(billInfo.getAcptId());
                obj.setBranchNo(billInfo.getBranchNo());
                obj.setBillClass(billInfo.getBillClass());
                obj.setBillType(billInfo.getBillType());
                UserLoginfo user=ResourceUtil.getSessionLoginfo();//取用户信息
                obj.setOperNo(user.getUserNo());//用户操作员号给经办柜员
                obj.setOperTime(DateTimeUtil.get_hhmmss_time());            
                obj.setBranchNo(user.getBranchNo());//签发机构
                obj.setBusiType(AcptCodeConst.COLTZN_BUSY_TYPE_ACCPTNC);//我行承兑
                obj.setFund(AcptCodeConst.PAYMENT_ORDER);
                
				if (isedit.equals("0")){
					obj.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_LOGONOUT);
					obj.setAccountDt(DateTimeUtil.getWorkdayString());
					obj.setRegDt(DateTimeUtil.getWorkdayString());
					rs=acptService.addAcptColltnReg(obj);
					if(rs!=0){
						billInfo.setBillStatus(AcptCodeConst.BILL_STATUS_PAYMENT);
						billInfo.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_LOGONOUT);
						billInfo.setColltnId(query.getColltnId());
						billInfo.setPaymentDt(DateTimeUtil.getWorkdayString());
						billInfo.setCustRemark(obj.getBankRemark());//电票调整为银行附言，防止他行过来的提示付款备注会导致大额报文字段超长
						billInfo.setBankRemark(obj.getBankRemark());
						billInfo.setPrsnttnAcctNo(obj.getPayeeAcct());
						billInfo.setPrsnttnBankName(obj.getPayeeBankName());
						billInfo.setPrsnttnBankNo(obj.getPayeeBankNo());
						billInfo.setPrsnttnName(obj.getPayeeName());
					    acptService.modifyAcptBillInfoAboutPaymentSave(billInfo);
					}
				}else{
					obj.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_REJECT);
					obj.setAuditTime(DateTimeUtil.get_hhmmss_time());
					obj.setRejectDt(DateTimeUtil.getWorkdayString());
					obj.setRegDt(DateTimeUtil.getWorkdayString());
					rs=acptService.addAcptColltnReg(obj);
					if(rs!=0){
					acptService.modifyAcptBillInfoAboutPaymentSave1(query);;
				}
				
			}
				session.endTransaction();
			}catch (SQLException e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				CommonLog.getCommonLogCache().errorMessage("插入记录tacpt_sspd_reg失败");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
			}
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		
		}
		/***************纸票承兑到期付款模块***************/
	
		/*****************************************************纸票系统***************************************************************/
	
	
	
	
	
		/*****************************************************电票系统***************************************************************/
		/***************电票承兑记账模块***************/
	/**
	 * 电子承兑记账批次查询页面
	 * 
	 * @param request
	 * @return
	 * @throws BizAppException
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=batchManage")
	public ModelAndView batchManage(Page page,AcptQueryCondition query) throws BizAppException, SQLException,Exception {
		ModelAndView mv = new ModelAndView("busi/acpt/electronic/batch_manage");
		page.activeCommand();
		IAcptService acptService = ServiceFactory.getAcptService();
		if(query==null){
			query=new AcptQueryCondition();
		}
		try {
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());//取管理员的机构号
			query.setStatus(StatusUtils.queryStatus("AcptAccountController", "elecBillAccount", null)[0]);
//			query.setBranchNo();
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			List<AcptApplyInfo> batchList = acptService.getAcptApplyListForCondition(page,query);
			mv.addObject("batchList", batchList);
			mv.addObject("page", page);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("银承记账查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "银承记账查询失败");
		}
		return mv;
	}


	/**
	 * 票据管理页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=billManage")
	public ModelAndView billManage(Page page,AcptQueryCondition query) throws BizAppException{
		page.activeCommand();
		ModelAndView mv = new ModelAndView("busi/acpt/electronic/bill_manage");
		try {
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());//取管理员的机构号
			IAcptService acptService = ServiceFactory.getAcptService();
			mv.addObject("batch",acptService.getAcptApplyForAcptID(query));//票据管理页面的批次信息
//			mv.addObject("batch",acptService.getAcptApplyInfo(query.getAcptId()));
			query.setStatus(StatusUtils.queryStatus("AcptAccountController", "elecBillAccount", null)[0]);
			mv.addObject("resultList",acptService.getAcptBillListForBatch(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 电子票据记账
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	@RequestMapping(params = "method=doElecAccount")
	public ModelAndView doElecAccount(String acptId) throws Exception {
		UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
		IAcptService acptService = ServiceFactory.getAcptService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            acptService.acptAccountSubmitByElec(acptId, userLogonInfo);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw e;
        }
		return batchManage(new Page(),new AcptQueryCondition());
	}
	/***************电票承兑记账模块***************/
	
	/***************电票承兑到期扣款模块***************/
	
	/**
	 * 电票到期扣款页面
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(params = "method=elecDueCharge")
	public ModelAndView elecDueCharge(Page page,AcptQueryCondition query) throws BizAppException, SQLException,Exception {
		ModelAndView mv = new ModelAndView("busi/acpt/electronic/elecDueCharge_batch");
		page.activeCommand();
		IAcptService acptService = ServiceFactory.getAcptService();
		if(query==null){
			query=new AcptQueryCondition();
		}
		try {
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());//取管理员的机构号
			String dt=DateTimeUtil.getWorkdayString();//获得当前营业日
			query.setWorkday(dt);
		//	query.addSpecialOpertion("workday",BaseSearchBean.LESS_AND_EQUAL);
			query.addSqlPropretyMapping("workday", "dueDt");
			
			
			query.setBillStatus(AcptCodeConst.BILL_STATUS_ISSUE);
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			List<AcptApplyInfo> batchList = acptService.getAcptApplyListForCondition(page,query);
			mv.addObject("batchList", batchList);
			mv.addObject("page", page);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("承兑到期扣款查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "承兑到期扣款查询失败");
		}
		return mv;
	}
	/**
	 * 电票到期扣款票据管理页面
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=elecDueChargeBill")
	public ModelAndView elecDueChargeBill(Page page,AcptQueryCondition query) throws BizAppException{
		page.activeCommand();
		ModelAndView mv = new ModelAndView("busi/acpt/electronic/elecDueCharge_bill");
		try {
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());//取管理员的机构号
			IAcptService acptService = ServiceFactory.getAcptService();
			mv.addObject("batch",acptService.getAcptApplyForAcptID(query));//票据管理页面的批次信息
//			mv.addObject("batch",acptService.getAcptApplyInfo(query.getAcptId()));
			query.setBillStatus(AcptCodeConst.BILL_STATUS_ISSUE);
			mv.addObject("resultList",acptService.getAcptBillListForBatch(page,query));
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
		}
		mv.addObject("page", page);
		return mv;
	}
	/**
	 * 电票到期扣款按钮控制
	 * @param request
	 * @return
	 * @throws BizAppException
	 */

	@RequestMapping(params = "method=doElecCharge")
	public ModelAndView doElecCharge(AcptQueryCondition query) throws Exception {
        IAcptService acptService=ServiceFactory.getAcptService();
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			acptService.dueChargeBillForAcptmxId(query);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return elecDueCharge(new Page(), null);
	}
	
	
	/**
	 * 电票到期转垫款按钮控制
	 * @param request
	 * @return
	 * @throws BizAppException
	 */

	@RequestMapping(params = "method=doElecAdvance")
	public ModelAndView doElecAdvance(AcptQueryCondition query) throws Exception {
        IAcptService acptService=ServiceFactory.getAcptService();
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			acptService.dueAdvanceBillForAcptmxId(query);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return elecDueCharge(new Page(), null);
	}
	
		
	/***************电票承兑到期扣款模块***************/	
		
	
	/***************电票承兑到期付款模块***************/
	
	/**
	 * 电票到期付款页面
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(params = "method=elecDuePayment")
	public ModelAndView elecDuePayment(Page page,AcptQueryCondition query) throws BizAppException, SQLException,Exception {
		ModelAndView mv = new ModelAndView("busi/acpt/electronic/elecDuePayment");
		page.activeCommand();
		IAcptService acptService = ServiceFactory.getAcptService();
		if(query==null){
			query=new AcptQueryCondition();
		}
		try {
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			query.setBranchNo(user.getBranchNo());//取管理员的机构号
			String dt=DateTimeUtil.getWorkdayString();//获得当前营业日
			query.setDfaultSrchTbAliasName("bill");
			String[] billStatusArray=new String[]{"3","4"};
			query.setBillStatusArray(billStatusArray);
			query.addSpecialOpertion("billStatusArray",BaseSearchBean.IN);
			query.addSqlPropretyMapping("billStatusArray", "billStatus");
			
			
			query.setWorkday(dt);
			query.addSpecialOpertion("workday",BaseSearchBean.LESS_AND_EQUAL);
			query.addSqlPropretyMapping("workday", "dueDt");
			
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			List<AcptBillInfo> resultList = acptService.getAcptBillListForBatch(page,query);
			mv.addObject("resultList", resultList);
			mv.addObject("page", page);
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("承兑到期付款查询失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "承兑到期付款查询失败");
		}
		return mv;
	}	
	
	
	
	/**
	 * 电票到期付款，付款登记页面
	 * 
	 */
	@RequestMapping(params = "method=elecPaymentRegister")
	public ModelAndView elecPaymentRegister(String acptmx_ids,AcptQueryCondition query) throws Exception {
			ModelAndView mv = new ModelAndView();
			IAcptService acptService = ServiceFactory.getAcptService();
			try {
				mv.addObject("query",acptService.getacptBillForAcptmxId(acptmx_ids));
			} catch (Exception e) {
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
			}
			AcptBillInfo bill =  acptService.getacptBillForAcptmxId(acptmx_ids);
			mv.addObject("obj", acptService.getAcptColltnRegForColltnId(bill.getColltnId()));
			mv.setViewName("busi/acpt/electronic/elecDuePayment_edit");
			mv.addObject("acptmx_ids", acptmx_ids);
			mv.addObject("isedit", "0");
			return mv;
		}	
	
	/**
	 * 电票到期付款，拒付登记页面
	 * 
	 */
	@RequestMapping(params = "method=elecNoPayment")
	public ModelAndView elecNoPayment(String acptmx_ids,AcptQueryCondition query) throws Exception {
			ModelAndView mv = new ModelAndView();
			IAcptService acptService = ServiceFactory.getAcptService();
			try {

				mv.addObject("query",acptService.getacptBillForAcptmxId(acptmx_ids));
			} catch (Exception e) {
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("票据列表查询失败");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据列表查询失败");
			}
			AcptBillInfo bill =  acptService.getacptBillForAcptmxId(acptmx_ids);
			mv.addObject("obj", acptService.getAcptColltnRegForColltnId(bill.getColltnId()));
			mv.setViewName("busi/acpt/electronic/elecDuePayment_edit");
			mv.addObject("acptmx_ids", acptmx_ids);
			mv.addObject("isedit", "1");
			return mv;
		}
	
	
	
	/**
	 * 电票到期付款保存
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(params="method=elecPaymentSave")
	public ModelAndView elecPaymentSave(String acptmx_ids,AcptQueryCondition query) throws Exception {
		ModelAndView mv=new ModelAndView();
		IAcptService acptService = ServiceFactory.getAcptService();
		AcptBillInfo billInfo=acptService.getacptBillForAcptmxId(acptmx_ids);
		String colltnId=billInfo.getColltnId();
		AcptColltnReg acptColltnReg = new AcptColltnReg();
		acptColltnReg=acptService.getAcptColltnRegForColltnId(colltnId);
		if(query==null){
			query=new AcptQueryCondition();
		}
		acptColltnReg.setCustRemark(query.getCustRemark());
		acptColltnReg.setBankRemark(query.getBankRemark());
		IDB session = DBFactory.getDB(); // 获取数据库连接
		String isedit= query.getIsedit();
		
		try {
			session.beginTransaction();
			if (isedit.equals("0")){
				acptService.modifyAcptColltnRegForColltnId(acptColltnReg);
				acptService.presentationPayment(acptmx_ids);
				
			}else{
				acptColltnReg.setRejectReason(query.getRejectReason());
				acptColltnReg.setRejectCode(query.getRejectCode());
				acptColltnReg.setRejectDt(DateTimeUtil.getWorkdayString());
				acptService.modifyAcptColltnRegForColltnId(acptColltnReg);
				acptService.collectionRefuse(acptmx_ids,query);
		}
			session.endTransaction();
		}catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tacpt_sspd_reg失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	
	}
	/***************电票承兑到期付款模块***************/
		
		

}

		

