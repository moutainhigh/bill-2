/********************************************
 * 文件名称: SubcollDraftCallback.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 托收报文回调处理
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-25 下午02:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.service.busiservice.subcoll;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptColltnReg;
import com.herongtech.console.domain.acpt.dao.AcptBillInfoDao;
import com.herongtech.console.domain.acpt.dao.AcptColltnRegDao;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.domain.subcoll.dao.SubcollBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.acpt.IAcptService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.SubcollCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcPresentationService;
import com.herongtech.rc.service.rcservice.ITrigger;

/**
 * 托收报文回调处理
 * @author Administrator
 *
 */

public class SubcollDraftCallback implements ITrigger{

	IRcPresentationService rcService = RcServiceFactory.getRcPresentationService();
	SubcollService subcollService = new SubcollService();
	SubcollBillInfoDao subBillInfoDao = new SubcollBillInfoDao();
	IAcptService acptService= ServiceFactory.getAcptService();
	private AcptBillInfoDao acptBillInfoDao=new AcptBillInfoDao();
	private AcptColltnRegDao acptColltnRegDao=new AcptColltnRegDao();
	/**
	 * 提示付款034转发回调处理
	 * 自动登记委托收款登记簿
	 * @author 
	 *
	 */
	@Override
	public void transFor034execute(RgctBill rgctBill) throws BizAppException {
	    String billType = rgctBill.getInfo().getBillType();
	    RgctBillHist hist = rgctBill.getHist();
	//银承 线下清算 且提示付款人开户行行号为907开头的，付款备注为空或入账行号为'907' 直接拒绝
	if(CommonConst.BILL_TYPE_BANK.equals(billType) && CommonConst.ONLINE_NOT.equals(hist.getIsOnline()) 
			&& hist.getFromBankNo().startsWith("907") && (StringUtils.isEmpty(hist.getInBankNo()) || hist.getInBankNo().startsWith("907"))){
//		xbankService.rejectPayBankElec(rgctBill);
//		LogTool.log_ba("票号：" + rgctBill.getInfo().getBillNo() + "提示付款自动拒付");
		return;
	}
	IAcptService acptService=ServiceFactory.getAcptService();
	AcptColltnReg colltnReg = acptService.collectionRegisterElec(rgctBill);

//	//电子银承收到委托收款后自动付款签收
//	if(BillConst.BANK_BILL.equals(billType)){
//		xbankService.paySignUpBankElec(rgctBill, colltnReg);
//	}
//		
//	LogTool.log_ba("票号：" + rgctBill.getInfo().getBillNo() + "提示付款登记回调处理结束");
}
	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		//只用rgctId查询是否严谨
		//SubcollBillInfo sub = subBillInfoDao.getSubcollBillForRgctId(rgctBill.getInfo().getId());
		SubcollBillInfo sub = new SubcollBillInfo();
		String operStatus = null;
		try {
			sub = subBillInfoDao.getSubcollBillForRgctIdAndOperStatus(rgctBill.getInfo().getId(),SubcollCodeConst.ACPT_SUB_COLL_LIST_324);
			operStatus = StatusUtils.handleStatusNoCheck("SubcollApplyController", "apply", null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(sub == null){
			return;
		}
		if(isSuccess){
			//***************1、更新原始清单发托日期***************
			subcollService.updateSourceCollDate(sub, true, rgctBill.getHist());
			
			//***************2、清单状态变更***************
			sub.setOperStatus(SubcollCodeConst.ACPT_SUB_COLL_LIST_325);
			sub.setStatus(SubcollCodeConst.SUB_STAUTS_ONWAY);
			sub.setCurStatus(RcConstants.CUR_STATUS_SUB_ON_PASSAGE);
			sub.setCollDate(DateTimeUtil.getWorkdayString());
			sub.setPosition(SubcollCodeConst.ELEC_WAIT_031);			
			
			//***************3、发托出库记账***************
			/*IAccountAgentService<SubCollBatch, SubCollList> subCollAccountService = SourceTemplate.getBean("agentSubCollAccountService");
			List<SubCollList> subBillList = new ArrayList<SubCollList>();
			subBillList.add(sub);
			AccountAgentRequest agentRequest = new AccountAgentRequest();
			//UserLogonInfo user = SessionManager.getUserLogonInfoForAjax();
			Branch branch = BranchServiceFactory.getBranchService().getBranchById(sub.getAdscriptionId());
			agentRequest.setBrchNo(branch.getBrchNo());
			agentRequest.setUserNo(sub.getAuditTellerNo());
			agentRequest.setProdNo(AcptCodeConst.PRODUCT_ACPT_TS_DKTS);
			agentRequest.setWorkDay(DateTimeUtil.getWorkday());
			subCollAccountService.account(agentRequest, null, subBillList);*/
		}else{
			sub.setOperStatus(operStatus);
			sub.setStatus(null);
			sub.setCollDate(null);
			sub.setPosition(SubcollCodeConst.OUTSTORE);
			//033失败时，继续锁票，否则重复挑票！
			rcService.lock(rgctBill.getHist().getRgctId());
		}
		try {
			subBillInfoDao.modifySubcollBillInfo(sub);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		//只用rgctId查询是否严谨
		//SubcollBillInfo sub = subBillInfoDao.getSubcollBillForRgctId(rgctBill.getInfo().getId());
		SubcollBillInfo sub = new SubcollBillInfo();
		String operStatus = null;
		try {
			sub = subBillInfoDao.getSubcollBillForRgctIdAndOperStatus(rgctBill.getInfo().getId(),SubcollCodeConst.ACPT_SUB_COLL_LIST_324);
			operStatus = StatusUtils.handleStatusNoCheck("SubcollApplyController", "apply", null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(sub == null){// 网银端客户发托不涉及工作流
			return;
		}
		/*
		 * 撤销成功则退回发托出库岗
		 * 失败则继续等待
		 */
		if (isSuccess) {
			
			//***************3、登记中心锁票***************
			RgctBillHist hist = rgctBill.getHist();
			hist.setIsLock(IDict.K_LOCK.K_LOCK_YES);
			rcService.updateRgctBillHist(hist);
			
			//***************1、清空原始清单发托日期***************
			subcollService.updateSourceCollDate(sub, false, hist);
			
			//***************2、清单状态变更***************
			sub.setOperStatus(operStatus);
			sub.setStatus(null);
			sub.setCollDate(null);		
			sub.setPosition(SubcollCodeConst.OUTSTORE);
			sub.setCurStatus(null);
			
			//***************4、电票发托冲正***************
			/*List<SubcollBillInfo> subBillList = new ArrayList<SubcollBillInfo>();
			IAccountAgentService accountService = SourceTemplate.getBean("agentSubCollAccountService");
			AccountAgentRequest agentRequest = new AccountAgentRequest();
			subBillList.add(sub);
			Branch branch = BranchServiceFactory.getBranchService().getBranchById(sub.getAdscriptionId());
			agentRequest.setBrchNo(branch.getBrchNo());
			agentRequest.setUserNo(sub.getAuditTellerNo());
			agentRequest.setProdNo(AcptCodeConst.PRODUCT_ACPT_TS_DKTS);
			agentRequest.setWorkDay(DateTimeUtil.getWorkday());
			accountService.reverseAccount(agentRequest, subBillList);
			
			sub.setAuditTellerNo(null);*/
		}else{
			sub.setOperStatus(SubcollCodeConst.ACPT_SUB_COLL_LIST_325);
			sub.setPosition(SubcollCodeConst.ELEC_WAIT_031);
		}
		try {
			subBillInfoDao.modifySubcollBillInfo(sub);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void signFor033execute(RgctBill rgctBill, String isSign,
			boolean isSuccess) throws BizAppException {
			if(isSuccess){//031签收成功
				try {
					AcptBillInfo billInfo = acptBillInfoDao.getAcptBillInfoByRgctId(rgctBill.getInfo().getId());
					AcptColltnReg coltzReg = acptColltnRegDao.getAcptColltnReg(billInfo.getColltnId()); 
					acptService.paymentSignUp(rgctBill, coltzReg, billInfo,isSign);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}

	@Override
	public void transFor031execute(RgctBill rgctBill, String isSign)
			throws BizAppException {

		//登记中心 托收退票后的from客户回退到企业
		//只用rgctId查询是否严谨
		//SubcollBillInfo sub = subBillInfoDao.getSubcollBillForRgctId(rgctBill.getInfo().getId());
		SubcollBillInfo sub = new SubcollBillInfo();
		try {
			sub = subBillInfoDao.getSubcollBillForRgctIdAndOperStatus(rgctBill.getInfo().getId(),SubcollCodeConst.ACPT_SUB_COLL_LIST_325);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(sub == null){
			return;
		}
		if(RcConstants.SIGN_YES.equals(isSign)){//走收款登记
//			if(!SubcollCodeConst.ACPT_SUB_COLL_LIST_325.equals(sub.getOperStatus())){
//				//不等于BS325,说明232先回来已经处理过，此次可以不处理
//				return;
//			}
//			if(RcConstants.SETTLEMENTMARK_ZERO.equals(sub.getIsOnline())){//TODO 只处理线下,线上的在232处理 防止并发时互相覆盖--暂时都在031回调处理，待优化
				try {
					Object[] stratus = null;
					stratus = StatusUtils.queryStatus("SubcollStorageController", "revokeSendBillForApply", null);
					String operStatus = stratus[0].toString();
					sub.setOperStatus(operStatus);
					sub.setPosition(SubcollCodeConst.REGISTER);
					subBillInfoDao.modifySubcollBillInfo(sub);
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}
		}else{//走退票登记
			try {
				Object[] stratus = null;
				stratus = StatusUtils.queryStatus("ReceiveMoneyController", "elecRevokeBillRegister", null);
				String operStatus = stratus[0].toString();
				sub.setOperStatus(operStatus);
				sub.setPosition(SubcollCodeConst.ELEC_BACK_REGISTER);
				subBillInfoDao.modifySubcollBillInfo(sub);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		rcService.lock(rgctBill.getInfo().getId());//锁票,防止新增发托将票查出
	}
	/**********提示付款的撤销032 回调*************/
	@Override
	public void transFor032execute(RgctBill rgctBill) throws BizAppException {
		 IAcptService acptService =ServiceFactory.getAcptService();
		    acptService.paymentCancel(rgctBill);
	}

	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		// TODO Auto-generated method stub
		
	}
}
