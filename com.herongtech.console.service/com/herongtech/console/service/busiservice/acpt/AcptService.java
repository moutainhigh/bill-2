/********************************************
 * 文件名称: AcptService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz,wzc
 * 开发时间: 2016-9-19
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.busiservice.acpt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.herongtech.console.cache.SysParamCache;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.core.util.ReflectionUtils;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptColltnReg;
import com.herongtech.console.domain.acpt.bean.AcptQueryCondition;
import com.herongtech.console.domain.acpt.bean.AcptSspdReg;
import com.herongtech.console.domain.acpt.dao.AcptApplyInfoDao;
import com.herongtech.console.domain.acpt.dao.AcptBillInfoDao;
import com.herongtech.console.domain.acpt.dao.AcptColltnRegDao;
import com.herongtech.console.domain.acpt.dao.AcptSspdRegDao;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.domain.dao.CustInfoAcctDao;
import com.herongtech.console.domain.dao.CustInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.sequence.SequenceService;
import com.herongtech.console.web.busicontroller.common.AcptCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.rc.service.rcservice.IRcAcptBillService;
import com.herongtech.rc.service.rcservice.IRcPresentationService;
import com.herongtech.sysconstant.ISysErrorNo;
import com.herongtech.xmlchannel.pkg.TransInfo;

public class AcptService implements IAcptService{
	private IEcdsBankDataService ecdsBankService;
	private AcptBillInfoDao acptBillInfoDao=new AcptBillInfoDao();
	private CustInfoAcctDao custInfoAcctDao=new CustInfoAcctDao();
	private CustInfoDao custInfoDao=new CustInfoDao();
	private AcptApplyInfoDao acptApplyDao=new AcptApplyInfoDao();
	private AcptBillInfoDao acptBillDao = new AcptBillInfoDao();
	private AcptSspdRegDao acptSspdRegDao=new AcptSspdRegDao();
	private AcptColltnRegDao acptColltnRegDao=new AcptColltnRegDao();
	/*****************************************************电票系统***************************************************************/
	/***************************报文处理**************************/
	/**********201001信贷放款*************/
	/**
	 * 信贷放款,放款处理T201001Service
	 */
	public void loanNotification(AcptApplyBean batchBean,List<AcptBillInfoBean> billList)throws BizAppException{
		AcptApplyInfo batchInfo = new AcptApplyInfo();
		AcptApplyInfo oldBatchInfo = new AcptApplyInfo();
		AcptApplyInfoDao applyDao=new AcptApplyInfoDao();
		try{
			AcptApplyInfo oldBatch=applyDao.getAcptApplyInfoByProtocalNoAndBrchNo(batchBean.getProtocalNo(), batchBean.getBranchNo());
			if(oldBatch != null){
				throw new BizAppException("同一签发机构下银承协议编号必须唯一:"+batchBean.getProtocalNo());
			}
			if (IDict.K_BILL_CLASS.K_ELEC_BILL.equals(batchBean.getBillClass())) {
				String batchNo = batchBean.getBatchNo();
				AcptApplyInfo apply=applyDao.getAcptApplyInfoByBatchNo(batchNo);
				if (apply==null) {
					throw new BizAppException("根据批次号["+batchNo+"]未能找到对应的银承协议信息");
				}
				oldBatchInfo = apply;
				if(CommUtils.isSelfBank(oldBatchInfo.getRemitterBankNo())){
					if(!oldBatchInfo.getRemitterAcct().equals(batchBean.getRemitterAcct())){
						throw new BizAppException("当前输入的出票人账号["+batchBean.getRemitterAcct()+"与对应批次电票的出票人账号["+oldBatchInfo.getRemitterAcct()+"]不一致");
					}
					if(!oldBatchInfo.getRemitterCustNo().equals(batchBean.getRemitterCustNo())){
						throw new BizAppException("当前输入的出票人客户号["+batchBean.getRemitterCustNo()+"]与对应批次电票的出票人客户号["+oldBatchInfo.getRemitterCustNo()+"]不一致");
					}
				}
			}
			
			batchBean.setBatchStatus("0");

			// 出票人信息
			processIuuseInfo(batchBean, batchBean.getProcessOperNo(), batchBean.getBranchNo());
			// 还款帐号信息处理
			processRepaymentAcct(batchBean);
			BeanUtils.copyProperties(batchInfo, batchBean);
			batchInfo.setProcessTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
			batchInfo.setProcessDt(DateTimeUtil.getWorkdayString());//保证金变化时间:1、放款通知2、未用退回3、到期扣款4、银承维护
			batchInfo.setRemitterBankNo(oldBatchInfo.getRemitterBankNo());
			AcptBillInfoDao billDao=new AcptBillInfoDao();
			if (IDict.K_BILL_CLASS.K_ENTY_BILL.equals(batchBean.getBillClass())) {
				batchInfo.setAcptId(ServiceFactory.getSequenceService().getACPT_APPLY_ID());
//				batchInfo.setBatchNo(FlowNoUtil.getTimeFlowNo());
				batchInfo.setProdNo(AcptCodeConst.PROD_NO_ENTITY);
				applyDao.addAcptApplyInfo(batchInfo);
				// 处理纸质票据清单明细
//				processEntityBillInfo(list, batchInfo);
//				final Long batchId = batchInfo.getId();
				Double totalAmt = 0D;
				for (int i = 0; i < billList.size(); i++) {
					AcptBillInfo billInfo = new AcptBillInfo();
					billInfo.setAcptmxId(ServiceFactory.getSequenceService().getACPT_BILL_INFO_ID());
					AcptBillInfoBean billInfoBean = billList.get(i);
					BeanUtils.copyProperties(billInfo, batchInfo);
//					BeanCoper.copyProperties(batchInfo, billInfo, null, null);
					billInfo.setAcptId(batchInfo.getAcptId());
					billInfo.setBillStatus(AcptCodeConst.BILL_STATUS_ACCPTNC);
					billInfo.setPrintStatus("0");
					// 明细增加批次内部序号
					billInfo.setEsno(Long.valueOf(i + 1));
					billInfo.setCurrencyCategory(billInfoBean.getCurrencyCategory());
					billInfo.setBillMoney(Double.valueOf(billInfoBean.getBillMoney()));
					billInfo.setPayeeAcct(billInfoBean.getPayeeAcct());
					billInfo.setPayee(billInfoBean.getPayee());
					billInfo.setPayeeBankName(billInfoBean.getPayeeBankName());
					billInfo.setBillNo(billInfoBean.getBillNo());
					billInfo.setBillType(batchInfo.getBillType());
					billInfo.setStatus(StatusUtils.handleStatusNoCheck("AcptAuditController", "extendLoanNotification", null));
					billInfo.setBillClass(batchInfo.getBillClass());
					billInfo.setVoucherType(AcptCodeConst.VOUCHER_TYPE_EC01);
					//付款日为到期日
					billInfo.setPaymentDt(batchInfo.getDueDt());
					billDao.addAcptBillInfo(billInfo);
//					totalAmt += billInfo.getBillAmt();
					totalAmt = MathScaleUtil.add(totalAmt, billInfo.getBillMoney());
				}
				//生成批次号
				batchInfo.setBatchNo(ServiceFactory.getSequenceService().getAcptApplyNo(batchInfo.getBranchNo()));
				//生成记账日期为当天营业日
				batchInfo.setAccountDt(DateTimeUtil.get_YYYY_MM_DD_Date());
				batchInfo.setTotalAmt(totalAmt);
				batchInfo.setTotalAmtForDeduct(totalAmt);
				batchInfo.setTotalCount(Long.valueOf(billList.size()));
				Double grantRatio = MathScaleUtil.divide(
						batchInfo.getFreezeTotalGrantAmt(),
						batchInfo.getTotalAmtForDeduct());
				batchInfo.setGrantRatio(grantRatio);
				applyDao.modifyAcptApplyInfo(batchInfo);
				
			} else {
				batchInfo.setAcptId(oldBatchInfo.getAcptId());
				batchInfo.setBillClass(oldBatchInfo.getBillClass());
				batchInfo.setBillType(oldBatchInfo.getBillType());
				batchInfo.setIssueDt(oldBatchInfo.getIssueDt());
				batchInfo.setDueDt(oldBatchInfo.getDueDt());
				batchInfo.setTotalAmt(oldBatchInfo.getTotalAmt());
				batchInfo.setTotalAmtForDeduct(oldBatchInfo.getTotalAmtForDeduct());
				batchInfo.setTotalCount(oldBatchInfo.getTotalCount());
				Double grantRatio = MathScaleUtil.divide(
						batchInfo.getFreezeTotalGrantAmt(),
						batchInfo.getTotalAmtForDeduct());
				batchInfo.setGrantRatio(grantRatio);
				batchInfo.setProdNo(AcptCodeConst.PROD_NO_ELEC);
				applyDao.modifyAcptApplyInfo(batchInfo);
				
				billDao.modifyAcptBillInfo(batchInfo);
			
			}
		}catch (Exception e) {
			throw new BizAppException(e.getMessage());
		}
		
	}
	/**********201001信贷放款*************/
	
	/**********031签收-033的回调*************/
	/**
	 * 电子银承记账在031签收-033的回调时调用，用以处理报文，拒绝签收时调用
	 * @param rgctBill
	 * @throws SQLException 
	 * @throws Exception 
	 * @throws ServiceException
	 */
	public void acceptanceAccountElec2(RgctBill rgctBill) throws BizAppException,Exception{
		AcptBillInfo bill = acptBillInfoDao.getAcptBillInfoByRgctId(rgctBill.getInfo().getId());
		bill.setBillStatus(AcptCodeConst.BILL_STATUS_DEFAULT);//拒绝签发改成默认值
		bill.setStatus(StatusUtils.handleStatusNoCheck("AcptApplyController", "rejectElecBill", null));//塞电票拒绝成功删票的承兑状态，BS400
		
		acptBillInfoDao.modifyAcptBillInfo(bill);
		
	}
	
	/**
	 * 电子银承记账在031签收-033的回调时调用，用以处理报文，拒绝签收失败时调用
	 * @param rgctBill
	 * @throws SQLException 
	 * @throws Exception 
	 * @throws ServiceException
	 */
	public void acceptanceAccountElec3(RgctBill rgctBill) throws BizAppException,Exception{
		AcptBillInfo bill = acptBillInfoDao.getAcptBillInfoByRgctId(rgctBill.getInfo().getId());
		bill.setBillStatus(AcptCodeConst.BILL_STATUS_DEFAULT);//拒绝签发改成默认值
        bill.setStatus(StatusUtils.handleStatusNoCheck("AcptApplyController", "rejectElecBillFailure", null));//塞电票拒绝失败的承兑状态，BS410
		
		acptBillInfoDao.modifyAcptBillInfo(bill);
	}
	
	/**
	 * 电子银承记账在031签收-033的回调时调用，用以处理报文，签收成功时调用
	 * @param rgctBill
	 * @throws Exception 
	 * @throws ServiceException
	 */
	public void acceptanceAccountElec(RgctBill rgctBill) throws Exception{
		AcptBillInfo bill = acptBillInfoDao.getAcptBillInfoByRgctId(rgctBill.getInfo().getId());
		bill.setBillStatus(AcptCodeConst.BILL_STATUS_ISSUE);
		String workday = DateTimeUtil.getWorkdayString();
		bill.setAcptDt(workday);
		bill.setStatus(StatusUtils.handleStatusNoCheck("AcptAccountController", "elecBillAccountSucceed", null));//塞签发记账成功的承兑状态，BS436
		
		acptBillInfoDao.modifyAcptBillInfo(bill);
		
		// 如果批次票据都签发完成，更改批次状态
				List<AcptBillInfo> billList = acptBillInfoDao.queryBillByStatus(bill.getAcptId(), AcptCodeConst.BILL_STATUS_ACCPTNC);//根据billStatus查询acptId批次下该状态的票据
				AcptApplyInfo batch = acptApplyDao.queryApplyByAcptId(bill.getAcptId());//根据acptId查询批次信息
				if (billList.size() == 0) {
					batch.setBatchStatus(AcptCodeConst.BATCH_STATUS_ISSUE);
					//batch.setChangeDt(workday);保证金变化时间
					acptApplyDao.modifyAcptApplyInfo(batch);
				}
//		// 银承表外//表外指银行商业银行从事的不列入资产负债表，但能影响银行当期损益的经营活动
//				if(isDMBranch(bill.getBranchNo())){
//					AccountAgentRequest request = new AccountAgentRequest();
//					request.setAcptType(AcptConst.ACPT_SCENE);
//					request.setBranchNo(batch.getBranchNo());//签发机构
//					request.setUserNo(batch.getAcctUserNo());//签发记账柜员
//					request.setWorkDay(workday);//交易日期
//					request.setBachForeFlowNo(FlowNoUtil.getTimeFlowNo());// 计提摊销：批次前台流水号
//					accountService.account(request, null, addToList(bill));
				
	}
	
	
	
	/**********031签收-033的回调*************/
	
	
	/**********提示付款034处理*************/
	/**
	 * 电子票据委托收款登记(提示付款034处理）
	 * 
	 * @param bill
	 * @throws BizAppException 
	 * @throws ServiceException
	 */

	public AcptColltnReg collectionRegisterElec(RgctBill bill) throws BizAppException   {
		String rgctId = bill.getInfo().getId();
		RgctBillHist hist = bill.getHist();
		RgctBillInfo info = bill.getInfo();
//		UserLoginfo user=ResourceUtil.getSessionLoginfo();//取用户信息
		String billType = bill.getInfo().getBillType();
		SequenceService se=new SequenceService();//流水号处理类
		String colltnId  = se.getACPT_COLLTN_REG_ID();
		BranchDao branchDao=new BranchDao();
		Branch branch = null;
			try {
				branch = branchDao.getBranchByBankNo(info.getAcceptorBankNo());//承兑人开户行行号
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		// 新增委托收款登记簿
		AcptColltnReg coltzReg = new AcptColltnReg();
		coltzReg.setId(colltnId);//生成主键id
		coltzReg.setRegDt(hist.getEndorseDt());
		coltzReg.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_REG);
//		coltzReg.setOperNo(user.getUserNo());//用户操作员号给经办柜员
		coltzReg.setOperTime(DateTimeUtil.get_hhmmss_time());
		//客户附言  提示付款人备注
		coltzReg.setCustRemark(bill.getHist().getFromRemark());
		//银行附言 电子银承+票号后9位
		coltzReg.setBankRemark((CommonConst.BILL_TYPE_BANK.equals(billType) ? "电子银承" : "电子商承") + StringUtils.right(info.getBillNo(), 9));
		
	    
	if(CommUtils.isSelfBank(hist.getFromBankNo()) && "0".equals(hist.getFromAcctNo())){//系统内转发托机构内部户
		IBranchService branchService = ServiceFactory.getBranchService();
		Branch collBrch;
		try {
			collBrch = branchService.getBranchByBankNo(hist.getFromBankNo());
			coltzReg.setPayeeBankName(collBrch.getBranchName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		InnerAccount account = InnerAccountsFactory.getInnerAccountService().queryInnerAcctByBrchNo(collBrch.getBrchNo(), InnerAccount.INNERACCOUNT_TYPE_SUBCOLL);
		coltzReg.setPayeeAcct(hist.getFromAcctNo());
		coltzReg.setPayeeBankNo(hist.getFromBankNo());

	}else{//行外或本行客户
		if(StringUtils.isNotEmpty(hist.getInBankNo()) && StringUtils.isNotEmpty(hist.getInAcctNo())){//备注信息为#行号@帐号#的特殊处理
			coltzReg.setPayeeAcct(hist.getInAcctNo());
			coltzReg.setPayeeBankNo(hist.getInBankNo());
		}else{
			coltzReg.setPayeeAcct(hist.getFromAcctNo());
			coltzReg.setPayeeBankNo(hist.getFromBankNo());
		}
		
		IEcdsBankDataService ecdsBankDataService = ServiceFactory.getEcdsBankDataService();
		EcdsBankData bankInfo = ecdsBankDataService.getEcdsBankData(coltzReg.getPayeeBankNo());
		if (bankInfo != null) {
			coltzReg.setPayeeBankName(bankInfo.getActorFullCall());
		 }
	   }
		coltzReg.setPayeeName(hist.getFromName());
		coltzReg.setBranchNo(branch.getBranchNo());//签发机构
		coltzReg.setColltnAmt(bill.getInfo().getBillMoney());
		
		try {
			AcptBillInfo acptBill=acptBillInfoDao.getAcptBillInfoByRgctId(info.getId());
			acptBill.setColltnId(colltnId);//给承兑清单表塞入最新的委托收款id
			acptBill.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_REG);
			acptBillDao.modifyAcptBillInfo(acptBill);
			coltzReg.setAcptId(acptBill.getAcptId());//对于委托收款表的外键，acptId特殊处理查出来在同一添加
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		coltzReg.setBillNo(info.getBillNo());
		coltzReg.setBillType(info.getBillType());
		coltzReg.setBillClass(info.getBillClass());
//		coltzReg.setRegFlowNo(SequenceService.getRcLogSerialNo());//委托登记流水号
		coltzReg.setBusiType(AcptCodeConst.COLTZN_BUSY_TYPE_ACCPTNC);//我行承兑
		coltzReg.setSettleMark(CommonConst.ONLINE_NOT.equals(hist.getIsOnline()) ? AcptCodeConst.SM01 : AcptCodeConst.SM00);//线上清算标识
		coltzReg.setDelayReason(hist.getOverdueRs());//延期原因
		coltzReg.setIsDelay(StringUtils.isBlank(hist.getOverdueRs()) ? AcptCodeConst.COLTZN_DELAY_NO : AcptCodeConst.COLTZN_DELAY_YES);//逾期原因说明
		coltzReg.setFund(AcptCodeConst.PAYMENT_ORDER);
		// 线上清算或收款人行号为系统外, 资金通道默认为大额
		try {
			if (AcptCodeConst.ONLINE.equals(hist.getIsOnline()) || !MsgUtil.isSelfBank(coltzReg.getPayeeBankNo())) {
//		        coltzReg.setIsPosthaste(AcptCodeConst.URGENT_FLAG_1);//没有是否加急
				coltzReg.setPaymentPath(AcptCodeConst.HVPS);//汇路，资金通道
			} else {// 行内资金通道走系统内汇划(如果备注中收款人开户行为我行的话，付款通道默认为0000-系统内)
				coltzReg.setPaymentPath(AcptCodeConst.INNER_PATH);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coltzReg.setYzSource("0");//移植来源
		try {
			acptColltnRegDao.addAcptColltnReg(coltzReg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
//		if (BillConst.BANK_BILL.equals(billType)) {
//			AcptBillInfo billInfo = xbankDao.getAcptBillInfoByRgctId(rgctId);
//			// 银承主表
//			billInfo.setColltnId(coltzReg.getId());
//			billInfo.setColltnStatus(AcptConst.COLTZN_STUTUS_REG);
//			billInfo.setStatus(AcptConst.BILL_LOCAL_STATE_COLTZN_REG);
//			billInfo.setPrsnttnAcctNo(coltzReg.getPayeeAcctNo());
//			billInfo.setPrsnttnBankName(coltzReg.getPayeeBankName());
//			billInfo.setPrsnttnBankNo(coltzReg.getPayeeBankNo());
//			billInfo.setPrsnttnName(coltzReg.getPayeeName());
//			billInfo.setRequestMsgId(hist.getRespDraftId());
//			billInfo.setPaymentPath(coltzReg.getPaymentPath());
//			billInfo.setCustomerRemark(coltzReg.getCustomerRemark());
//			xbankDao.updateEntity(billInfo);
//
//			coltzReg.setAcptId(billInfo.getId());
//			xbankDao.updateEntity(coltzReg);
//			
//			if(isDMBranch(coltzReg.getBrchNo())){
//				this.accountService.account(request, coltzReg, addToList(billInfo));
//			}
//		} else {
//			CorpBillInfo corpBill = xbankDao.queryCorpBillByBillNo(bill.getInfo().getBillNo());
//			corpBill.setColltnId(coltzReg.getId());
//			corpBill.setColltnStatus(AcptConst.COLTZN_STUTUS_REG);
//			corpBill.setStatus(AcptConst.BILL_LOCAL_STATE_COLTZN_REG);
//			corpBill.setPrsnttnAcctNo(coltzReg.getPayeeAcctNo());
//			corpBill.setPrsnttnBankName(coltzReg.getPayeeBankName());
//			corpBill.setPrsnttnBankNo(coltzReg.getPayeeBankNo());
//			corpBill.setPrsnttnName(coltzReg.getPayeeName());
//			corpBill.setCustomerRemark(coltzReg.getCustomerRemark());
//			corpBill.setDeductFlag(AcptConst.DEDUCT_DEFAULT);
//			corpBill.setPayWaitOrder(null);
//			xbankDao.updateEntity(corpBill);
//		}
		return coltzReg;
	}
	/**********提示付款034处理*************/
	
	/**********提示付款的撤销032 回调*************/

	public void paymentCancel(RgctBill bill) throws BizAppException {
		RgctBillInfo info = bill.getInfo();
		final String billType = info.getBillType();
		final String billNo = info.getBillNo();
		if (CommonConst.BILL_TYPE_BANK.equals(billType)) {
			AcptBillInfo billInfo;
			try {
				billInfo = acptBillInfoDao.getAcptBillInfoByRgctId(info.getId());
				billInfo.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_CANCEL);
				acptBillInfoDao.modifyAcptBillInfo(billInfo);
				AcptColltnReg coltzReg = acptColltnRegDao.getAcptColltnReg(billInfo.getColltnId());
				coltzReg.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_CANCEL);
				acptColltnRegDao.modifyAcptColltnReg(coltzReg);
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		
			
			
//			if(isDMBranch(coltzReg.getBrchNo())){
//				// 销记收到托收表外账
//				UserLogonInfo userInfo = (UserLogonInfo) CacheInitServlet.initInfo.get("userInfo");
//				AccountAgentRequest request = new AccountAgentRequest();
//				request.setAcptType(AcptConst.REJECT_SCENE);
//				request.setBrchNo(coltzReg.getBrchNo());
//				request.setUserNo(userInfo.getUserNo());
//				request.setWorkDay(DateTimeUtil.getWorkday());
//				request.setBachForeFlowNo(FlowNoUtil.getTimeFlowNo());
//				accountService.account(request, coltzReg, addToList(billInfo));
//			}
		} 
//			else {
//			CorpBillInfo corpBill = xbankDao.queryCorpBillByBillNo(billNo);
//			if(corpBill.getPayWaitOrder() != null){
//				deductService.refundToIssuer(corpBill);
//			}
//			corpBill.setColltnStatus(AcptConst.COLTZN_STUTUS_CANCEL);
//			xbankDao.updateEntity(corpBill);
//			AcptColltnReg coltzReg = xbankDao.getEntity(AcptColltnReg.class, corpBill.getColltnId());
//			coltzReg.setColltnStatus(AcptConst.COLTZN_STUTUS_CANCEL);
//			xbankDao.updateEntity(coltzReg);
//		}
				
	}
	/**********提示付款的撤销032 回调*************/
	
	
	/**********提示付款签收的033（提示付款回复） 回调*************/
	/**
	 * 电子票据提示付款签收
	 * @param bill
	 * @param coltzReg
	 * @param billInfo
	 * @param transInfo
	 * @param isSign
	 * @throws ServiceException
	 */
	public void paymentSignUp(RgctBill bill, AcptColltnReg coltzReg, AcptBillInfo billInfo,  String isSign) throws BizAppException{
		String signDt = DateTimeUtil.getWorkdayString();
		if (CommonConst.SIGN_YES.equals(isSign)) {// 签收处理逻辑
			try {
				billInfo.setBillStatus(AcptCodeConst.BILL_STATUS_PAYMENT);
				billInfo.setPaymentDt(signDt);
				billInfo.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_LOGONOUT);
				billInfo.setCustRemark(coltzReg.getBankRemark());//电票调整为银行附言，防止他行过来的提示付款备注会导致大额报文字段超长
				billInfo.setBankRemark(coltzReg.getBankRemark());
				billInfo.setPrsnttnAcctNo(coltzReg.getPayeeAcct());
				billInfo.setPrsnttnBankName(coltzReg.getPayeeBankName());
				billInfo.setPrsnttnBankNo(coltzReg.getPayeeBankNo());
				billInfo.setPrsnttnName(coltzReg.getPayeeName());
				acptBillInfoDao.modifyAcptBillInfo(billInfo);
				
				coltzReg.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_LOGONOUT);
				coltzReg.setAccountDt(signDt);
				coltzReg.setRejectReason(bill.getHist().getRejectReason());
				acptColltnRegDao.modifyAcptColltnReg(coltzReg);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {// 拒绝处理逻辑
			try {
				billInfo.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_REJECT);
				acptBillInfoDao.modifyAcptBillInfo(billInfo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 委托收款登记簿
			final String rejectCode = bill.getHist().getRejectCode();
			final String rejectReason = bill.getHist().getRejectReason();
			coltzRefuseReg(signDt, rejectCode, rejectReason, coltzReg);

		}
		

	}
	
	
	private void coltzRefuseReg(String rejectDt, String rejectCode, String rejectReason, AcptColltnReg coltzReg) {
		try {
			coltzReg.setRejectCode(rejectCode);
			coltzReg.setRejectReason(rejectReason);
			coltzReg.setRejectDt(rejectDt);
			coltzReg.setAuditTime(DateTimeUtil.get_hhmmss_time());
			coltzReg.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_REJECT);
			acptColltnRegDao.modifyAcptColltnReg(coltzReg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**********提示付款签收的033 回调*************/
	

		
	
	/***************************报文处理**************************/
	
	
	/***************************电票接收**************************/
	/**
	 * 点击确认接收时调用状态机，修改Status状态为BS415
	 */
	public void acceptBillForAcptmxId(String ids) throws Exception{
		String totalAmt="0.00";
		UserLoginfo  user=ResourceUtil.getSessionLoginfo();
		String[] billIds=CommUtils.couvertLong(ids);//分割逗号
		IRcAcptBillService rcService=RcServiceFactory.getRcAcptBillService();
		RgctBill rbtmp = null;
		String id=ServiceFactory.getSequenceService().getACPT_APPLY_ID();
		
		for (int i = 0; i < billIds.length; i++) {
			AcptBillInfo info=acptBillInfoDao.getAcptBillInfo(billIds[i]);
			RgctBill rcBill=rcService.getRgctBillById(info.getRgctId());
			rcService.lock(info.getRgctId());
			totalAmt = MathScaleUtil.add(totalAmt, String.valueOf(rcBill.getInfo().getBillMoney()));
			if(rbtmp==null){
				rbtmp=rcBill;
			}
			
			
			info.setStatus(StatusUtils.handleStatus("AcptApplyController", "receiveElecBill", null,info.getStatus()));
//			info.setBillStatus();
			info.setAcptId(id);
			acptBillInfoDao.modifyAcptBillInfo(info);
		}
		AcptApplyInfo apply = new AcptApplyInfo();//
		apply.setAcptId(id);
		//银承批次号
		apply.setBatchNo(ServiceFactory.getSequenceService().getAcptApplyNo(user.getBranchNo()));
		//签发机构
		apply.setBranchNo(user.getBranchNo());
		// 出票方全称
		apply.setRemitter(rbtmp.getInfo().getRemitter());
		//出票方开户行行号
		apply.setRemitterBankNo(rbtmp.getInfo().getRemitterBankNo());
		//出票方开户行名称
		apply.setRemitterBankName(rbtmp.getInfo().getRemitterBankName());
		//出票方开户机构(只有本行机构出的票才执行以下判断)
		if(StringUtils.isNotEmpty(rbtmp.getInfo().getRemitterBankNo())&&CommUtils.isSelfBank(rbtmp.getInfo().getRemitterBankNo())){
			//出票人客户号
			apply.setRemitterCustNo(rbtmp.getInfo().getRemitterCustNo());
			// 出票方帐号
			apply.setRemitterAcct(rbtmp.getInfo().getRemitterAcct());
			BranchDao brchdDao=new BranchDao();
	    	Branch brch=null;
			try {
				brch = brchdDao.getBranchByBrchBankNo(rbtmp.getInfo().getRemitterBankNo());
				apply.setIssueBranchNo(brch.getBranchNo());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BizAppException(e.getMessage());
			}
		}
		//付款行行号
		apply.setDraweeBankNo(rbtmp.getInfo().getDraweeBankNo());
		//付款行全称
		apply.setDraweeBankName(rbtmp.getInfo().getDraweeBankName());
		//付款行地址
		apply.setDraweeAddr(rbtmp.getInfo().getDraweeAddr());
		//出票日
		apply.setIssueDt(rbtmp.getInfo().getIssueDt());
		//到期日
		apply.setDueDt(rbtmp.getInfo().getDueDt());
		//票据形态
		apply.setBillClass(rbtmp.getInfo().getBillClass());
		//票据种类
		apply.setBillType(rbtmp.getInfo().getBillType());			
		//批次状态: -1 默认值
		apply.setBatchStatus(AcptCodeConst.BILL_STATUS_DEFAULT);
		//产品编号，固定为030401000000000000
		apply.setProdNo(AcptCodeConst.PROD_NO_ELEC);
		//生成批次柜员号
		apply.setProcessOperNo(user.getUserNo());
		//生成批次时间
		apply.setProcessTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
		apply.setTotalCount(new Long(billIds.length));
		apply.setTotalAmt(Double.valueOf(totalAmt));
		apply.setTotalAmtForDeduct(Double.valueOf(totalAmt));
		//apply.setLoanSystem(AcptConst.LOAN_SYSTEM);
		apply.setGrantRatio(0.0);
		apply.setFreezeTotalGrantAmt(0.0);
		//记账日期
		apply.setAccountDt(DateTimeUtil.get_YYYY_MM_DD_Date());
		new AcptApplyInfoDao().addAcptApplyInfo(apply);
	}
	/**
	 * 点击拒绝接收时调用状态机，修改status状态为BS411中间状态
	 */
	public int refuseBillAcptmxId(String ids) throws Exception{
		return acptBillInfoDao.updateAcptBillInfoStatus(ids,StatusUtils.queryStatus("AcptApplyController", "rejectElecBill", null),StatusUtils.handleStatusNoCheck("AcptApplyController", "rejectElecBill", null),null,null);
	}
	/***************************电票接收**************************/
	
	/***********************电票承兑记账模块***********************/
	/**
	 * 在承兑记账页面点击提交时调用状态机，修改status状态为BS431中间状态，电票签发记账（报文发送中）
	 */
		public int accountBillAcptmxId(String ids) throws Exception{
			return acptBillInfoDao.updateAcptBillInfoStatus(ids,StatusUtils.queryStatus("AcptAccountController", "elecBillAccount", null),StatusUtils.handleStatusNoCheck("AcptAccountController", "elecBillAccount", null),null, null);
		}
		
	/**
		 * 电子承兑记账
		 * @param acptId
		 * @param user
		 * @throws BizAppException
	*/
		public void acptAccountSubmitByElec(String acptId,UserLoginfo user)throws BizAppException {
			try {
				AcptApplyInfo apply=acptApplyDao.getAcptApplyInfo(acptId);
				List<AcptBillInfo> billList=acptBillInfoDao.getBillListByAcptId(acptId,StatusUtils.queryStatus("AcptAccountController", "elecBillAccount", null)[0]);
				IRcAcptBillService acptService=RcServiceFactory.getRcAcptBillService();
				BranchDao brchDao=new BranchDao();
				String status=StatusUtils.handleStatusNoCheck("AcptAccountController", "elecBillAccount", null);
				for (int i = 0; i < billList.size(); i++) {
					AcptBillInfo bill = billList.get(i);
					RgctBill rcBill = acptService.getRgctBillById(bill.getRgctId());
					RgctBillHist hist = rcBill.getHist();
					RgctBillInfo info = rcBill.getInfo();
					Branch brch = brchDao.getBranchByBrchBankNo(info.getAcceptorBankNo());
					hist.setToRole(brch.getPartnerType());
					hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
					hist.setToOrgcode(brch.getOrgCode());
					hist.setSignDt(DateTimeUtil.getEcds_YYYY_MM_DD_Date());
					hist.setSignerSign(CommUtils.getSignerSign(info.getAcceptorBankNo()));
					acptService.acptSign(rcBill);
					bill.setStatus(status);
					acptBillInfoDao.modifyAcptBillInfo(bill);
				}
				apply.setAcctOperNo(user.getUserNo());
				//batch.setBrchNo(transInfo.getBranch());
				apply.setAccountDt(DateTimeUtil.getWorkdayString());
				apply.setAccountTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
//				apply.setAcctFlowNo);
				acptApplyDao.modifyAcptApplyInfo(apply);
			} catch (Exception e) {
				throw new BizAppException(e.getMessage());
			}
			
		}
		
		
		
		/***********************电票承兑记账模块***********************/
		
		
		
		
		/***********************电票承兑到期付款模块***********************/
		/**
		 * 电票到期付款，付款登记保存表时调用发送提示付款回复报文
		 */
		
		public void presentationPayment(String acptmx_ids) throws BizAppException{
			IRcPresentationService rcpService=RcServiceFactory.getRcPresentationService();
			IBranchService ibs = ServiceFactory.getBranchService();
			AcptColltnReg coltzReg = new AcptColltnReg();
			AcptBillInfo bill = null;
			Branch brch = null;
			AcptApplyInfo batch = null;
			try {
				bill = acptBillInfoDao.getAcptBillInfo(acptmx_ids);
				coltzReg = acptColltnRegDao.getAcptColltnReg(bill.getColltnId());//根据主键来查
				brch = ibs.getBranchByBankNo(bill.getAcceptorBankNo());
				batch = acptApplyDao.getAcptApplyInfo(bill.getAcptId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (AcptCodeConst.BILL_STATUS_PAYMENT.equals(bill.getBillStatus()) || AcptCodeConst.BILL_STATUS_DRAWBACK.equals(bill.getBillStatus())) {
				throw new BizAppException(IErrorNo.BBSP0073, new String (AcptCodeConst.getBillStatusString(bill.getBillStatus())));
			}

			if (StringUtils.isEmpty(batch.getOrderId())) {
				throw new BizAppException("票据状态有误，银承尚未扣款或已付款!");
			}
			String workday = DateTimeUtil.getWorkdayString();
			RgctBill rcBill = rcpService.getRgctBillById(bill.getRgctId());
			RgctBillHist hist = rcBill.getHist();
			String curStatus = hist.getCurStatus();
			if(!(curStatus.equals("R_08") || curStatus.equals("S_08"))){
				throw new BizAppException("票据" + bill.getBillNo() + "尚未收到提示付款申请，禁止做付款签收操作！");
			}
			hist.setToRole(brch.getPartnerType());
			hist.setToOrgcode(brch.getOrgCode());
			hist.setSignDt(workday);
			hist.setSignerSign(CommUtils.getSignerSign(bill.getAcceptorBankNo()));
			if (AcptCodeConst.COLTZN_DELAY_NO.equals(coltzReg.getIsDelay())) {
				rcpService.collectSign(rcBill);
			} else {
				rcpService.signOverdue(rcBill);
			}
		}
		
		
		
		
		/**
		 * 电票到期付款，拒付登记保存表时调用发送提示付款回复报文
		 */
		public void collectionRefuse(String acptmx_ids,AcptQueryCondition query) throws BizAppException{
			IRcPresentationService rcpService=RcServiceFactory.getRcPresentationService();
			AcptColltnReg coltzReg = new AcptColltnReg();
			AcptBillInfo bill = new AcptBillInfo();
			Branch brch = new Branch();
			IBranchService ibs = ServiceFactory.getBranchService();
			try {
				bill = acptBillInfoDao.getAcptBillInfo(acptmx_ids);
				brch = ibs.getBranchByBankNo(bill.getAcceptorBankNo());
				coltzReg = acptColltnRegDao.getAcptColltnReg(bill.getColltnId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//Assert.notNull(coltzReg, "未找到对应的委托收款登记信息, 委托收款编号:" + bill.getAcptId());
			if(coltzReg == null){
				throw new BizAppException("未找到对应的委托收款登记信息, 委托收款编号:" + bill.getColltnId());
			}
			String workday = DateTimeUtil.getWorkdayString();
			
	        //票据委托收款状态必须为收到
	        if(!AcptCodeConst.COLTZN_STUTUS_REG.equals(coltzReg.getColltnStatus())){
	        	throw new BizAppException(IErrorNo.BBSP0081, new String(AcptCodeConst.getColltnStatusString(coltzReg.getColltnStatus())));
	        }
	        
			if (IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getBillClass())) {
				if (AcptCodeConst.COLTZN_STUTUS_CANCEL.equals(coltzReg.getColltnStatus())) {
					throw new BizAppException("提示付款人已经撤销申请，请核实后进行操作！");
				}
				// RC状态切换，电子票据发拒绝报文
				RgctBill rcBill = rcpService.getRgctBillById(bill.getRgctId());
				RgctBillHist hist = rcBill.getHist();
				hist.setRejectCode(query.getRejectCode());
				hist.setRejectReason(query.getRejectReason());
				hist.setToRole(brch.getPartnerType());
				hist.setToOrgcode(brch.getOrgCode());
				hist.setSignDt(workday);
				hist.setSignerSign(CommonConst.SIGN_NO);
				if (AcptCodeConst.COLTZN_DELAY_NO.equals(coltzReg.getIsDelay())) {
					rcpService.rejectCollect(rcBill);
				} else {
					rcpService.overduePresentationNo(rcBill);
				}
			} 
		}
		
		
		
		/**
		 * 电票到期付款，付款登记页面调用，用于根据colltnId来获取委托收款登记表
		 */
		
		public AcptColltnReg getAcptColltnRegForColltnId(String colltnId) throws BizAppException{
			AcptColltnReg acptColltnReg = new AcptColltnReg();
			try {
				acptColltnReg = acptColltnRegDao.getAcptColltnReg(colltnId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return acptColltnReg;
		}
		
		/**
		 * 电票到期付款，电票到期付款保存调用，用于更新委托收款登记表
		 * @throws SQLException 
		 */
		
		
		public int modifyAcptColltnRegForColltnId(AcptColltnReg obj) throws BizAppException, SQLException{
			return acptColltnRegDao.modifyAcptColltnReg(obj) ;
		}
		
		/***********************电票承兑到期付款模块***********************/
		
		/*****************************************************电票系统***************************************************************/
		
	
		/*****************************************************纸票系统***************************************************************/	
		
		/***************纸票承兑记账模块******************************/
		/**
		 * 功能描述：票据记账
		 * @param ids
		 * @return 
		 * @return
		 */
		
		
		public int doAccount(String ids) throws Exception{
			String billStatus=AcptCodeConst.BILL_STATUS_ISSUE;
			return acptBillInfoDao.updateAcptBillInfoStatus(ids,StatusUtils.queryStatus("AcptAccountController", "entityAccountSucceed", null),StatusUtils.handleStatusNoCheck("AcptAccountController", "entityAccountSucceed", null),null,billStatus);
		}
		/**
		 * 交易凭证填写
		 */
		public int cumentsSubmitted(String ids,String billNo) throws Exception{
			
			return acptBillInfoDao.updateProofOfTrading(ids,billNo);
		}
		/***************纸票承兑记账模块******************************/
		
		
		/***************纸票承兑未用退回模块***************************/
		/**
		 * 在纸票未用退回页面点击退回时调用状态机，修改status状态为BS437，修改bill_status票据状态为2
		 * @param ids
		 * @return
		 * @throws Exception
		 */
		public void notUseReturnForAcptmxId(String ids) throws Exception{
			String[] arr=CommUtils.couvertLong(ids);
			for(int i=0;i<CommUtils.couvertLong(ids).length;i++){
				String ay=arr[i];
				AcptBillInfo billInfo = acptBillInfoDao.getAcptBillInfo(ay);
				String curStatus=billInfo.getStatus();
				billInfo.setStatus(StatusUtils.handleStatus("AcptAccountController", "notUseReturn", null, curStatus));
				billInfo.setBillStatus("2");
				billInfo.setDrwgBckDt(DateTimeUtil.get_YYYY_MM_DD_Date());//未用退回日期
				billInfo.setDrwgBckTime(DateTimeUtil.get_hhmmss_time());//未用退回时间
				acptBillInfoDao.modifyAcptBillInfo(billInfo, ay);				
		}
	}
		/***************纸票承兑未用退回模块***************************/
		
		/******************纸票承兑挂失止付模块************************/
		/**
		 * 新增挂失表信息
		 * @param saleId
		 * @return
		 */
		public int addAcptSspdReg(AcptSspdReg obj) throws SQLException{
			return acptSspdRegDao.addAcptSspdReg(obj) ;
		}
		
		/**
		 * 挂失时保存表时，修改对应票的sspd_status挂失状态为1,状态0是未挂失，状态1是已挂失
		 * @param saleId
		 * @return
		 * @throws BizAppException 
		 */
		public void modifySspdStatus0(AcptQueryCondition query) throws SQLException, BizAppException{
			
			AcptBillInfo billInfo = acptBillInfoDao.getAcptBillInfo(query.getAcptmxId());
			billInfo.setAcptmxId(query.getAcptmxId());
			String jy=billInfo.getSspdStatus();
			if(jy.equals("0")){
				billInfo.setSspdStatus("1");
			}else{
				CommonLog.getCommonLogCache().errorMessage("此票已经挂失，不要重复记录");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "此票已经挂失，不要重复记录");
			}
				

			acptBillInfoDao.modifyAcptBillInfo(billInfo);
		}	
		/**
		 * 解挂时保存表时，修改对应票的sspd_status挂失状态为0,状态0是未挂失，状态1是已挂失
		 * @param saleId
		 * @return
		 * @throws BizAppException 
		 */
		public void modifySspdStatus1(AcptQueryCondition query) throws SQLException, BizAppException{
			
			AcptBillInfo billInfo = acptBillInfoDao.getAcptBillInfo(query.getAcptmxId());
			billInfo.setAcptmxId(query.getAcptmxId());
			String jy=billInfo.getSspdStatus();
			if(jy.equals("1")){
				billInfo.setSspdStatus("0");
			}else{
				CommonLog.getCommonLogCache().errorMessage("此票已经解挂，不要重复记录");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "此票已经解挂，不要重复记录");
			}
				

			acptBillInfoDao.modifyAcptBillInfo(billInfo);
		}
		/******************纸票承兑挂失止付模块************************/
		
		/*****************************************************纸票系统***************************************************************/

		/*****************************************************公共部分***************************************************************/
		/**
		    * 根据主键acptmx_id查询票据清单的方法
			*/		
			public List<AcptBillInfo> refuseBillForAcptmxId(String ids) throws Exception{
				return acptBillInfoDao.getElectricReceiveForAcptmxId(ids);
			}
			/**
			 * 根据主键查询票据清单的方法
			 */
			public AcptBillInfo getacptBillForAcptmxId(String ids) throws Exception{
				return acptBillInfoDao.getAcptBillInfo(ids);
			}
			/**
			 * 功能描述：得到批次详情
			 * @param acptId
			 * @return
			 * @throws SQLException
			 */
			public AcptApplyInfo getAcptApplyInfo(String acptId) throws SQLException{
				return acptApplyDao.getAcptApplyInfo(acptId);
			}
			/**
			 * 功能描述：根据条件查询清单
			 * @param acptId
			 * @return
			 * @throws SQLException
			 */
			public List<AcptBillInfo> getAcptBillListForBatch(Page page,AcptQueryCondition query) throws Exception{
				initQueryCondition(query);
				OrderBean order=new OrderBean("acptmxId",true);
			    query.appendOrder(order);
				return acptBillDao.getAcptBillListForBatch(page,query);
			}
			/**
			 * 初始化查询条件
			 * @param query
			 */
			private void initQueryCondition(AcptQueryCondition query){
				query.setDfaultSrchTbAliasName("bill");
			}
			/**
			 * 功能描述：根据条件查询批次列表
			 * @param page
			 * @param query
			 * @return
			 * @throws SQLException
			 */
			public List<AcptApplyInfo> getAcptApplyListForCondition(Page page,AcptQueryCondition query) throws SQLException{
				query.setDfaultSrchTbAliasName("apply");
				query.addProperty2TableObj("billStatus", "bill");
				query.addProperty2TableObj("status", "bill");
				query.addProperty2TableObj("dueDt", "bill");
				OrderBean order=new OrderBean("batchNo",false);//排序方法
				
				query.appendOrder(order);
			    return acptApplyDao.getAcptApplyListForCondition(page,query);
			}
			/**
			 * 功能描述：票号非空校验
			 * @param ids
			 * @return 
			 * @return
			 */
			@SuppressWarnings("unused")
			public boolean check(String ids) throws Exception{
				List<AcptBillInfo> billList=acptBillInfoDao.getElectricReceiveForAcptmxId(ids);
				
				for(int i=0;i<billList.size();i++){
					if("".equals(billList.get(i).getBillNo())||billList.get(i).getBillNo()==null){
						return false;
				}
				}
				return true;
				
			}	
			/**
			 * 根据主键查询批次信息
			 * @param 
			 * @return
			 */
			public AcptApplyInfo getAcptApplyForAcptID(AcptQueryCondition query) throws SQLException{
				
				query.setDfaultSrchTbAliasName("bill");
				query.addSqlPropretyMapping("statusArray", "operStatus");
				return acptApplyDao.getAcptApplyInfo(query);
			}
			/**
			 * 纸票电票到期扣款，把bill_Status票据状态从1改为3
			 * @param ids
			 * @return
			 * @throws Exception
			 */
			public void dueChargeBillForAcptmxId(AcptQueryCondition query) throws Exception{
				List<AcptBillInfo> ac=acptBillInfoDao.getBillListAcptId(query.getAcptId());
				
				for(int i=0;i<ac.size();i++){
					AcptBillInfo billInfo = acptBillInfoDao.getAcptBillInfo(ac.get(i).getAcptmxId());
					billInfo.setBillStatus(AcptCodeConst.BILL_STATUS_DEDUCT);
				
					acptBillInfoDao.modifyAcptBillInfo(billInfo);
				}	
			}	
			
			/**
			 * 纸票电票到期转垫款，把bill_Status票据状态从1改为4
			 * @param ids
			 * @return
			 * @throws Exception
			 */
			public void dueAdvanceBillForAcptmxId(AcptQueryCondition query) throws Exception{
				List<AcptBillInfo> ac=acptBillInfoDao.getBillListAcptId(query.getAcptId());
				
				for(int i=0;i<ac.size();i++){
					AcptBillInfo billInfo = acptBillInfoDao.getAcptBillInfo(ac.get(i).getAcptmxId());
					billInfo.setBillStatus(AcptCodeConst.BILL_STATUS_ADVANCE);
				
					acptBillInfoDao.modifyAcptBillInfo(billInfo);
				}	
			}	
			
				
			/**
			 * 纸票到期付款，添加付款拒付信息;
			 * @param ids
			 * @return
			 * @throws Exception
			 */
		
			
			public int addAcptColltnReg(AcptColltnReg obj) throws Exception{
				return acptColltnRegDao.addAcptColltnReg(obj) ;

				}	
			
			/**
			 * 纸票到期付款,付款登记保存表时，修改对应票清单的bill_status状态为5。加入委托id，提示付款人信息；银行，客户附言
			 * @param saleId
			 * @return
			 * @throws BizAppException 
			 */
			public void modifyAcptBillInfoAboutPaymentSave(AcptBillInfo	billInfo) throws BizAppException{
				try {

					acptBillInfoDao.modifyAcptBillInfo(billInfo);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			/**
			 * 纸票到期付款，拒付登记保存表时，修改对应票清单的colltn_status委托收款状态，
			 * @param saleId
			 * @return
			 * @throws BizAppException 
			 */
			public void modifyAcptBillInfoAboutPaymentSave1(AcptQueryCondition query) throws SQLException, BizAppException{
				String acptmx_ids=query.getAcptmxId();
				AcptBillInfo billInfo = acptBillInfoDao.getAcptBillInfo(acptmx_ids);	
//				billInfo.setAcptmxId(acptmx_ids);

				billInfo.setColltnStatus(AcptCodeConst.COLTZN_STUTUS_REJECT);
				billInfo.setColltnId(query.getColltnId());
				acptBillInfoDao.modifyAcptBillInfo(billInfo);
			}		
	/**
	 * 处理出票人信息
	 * 
	 * @param batchBean
	 * @param userInfo
	 * @throws SQLException 
	 */
	private void processIuuseInfo(AcptApplyBean batchBean, String teller,
			String brchNo) throws BizAppException {
		CustInfoAcctDao acctDao=new CustInfoAcctDao();
		CustInfoAcct acct;
		try {
			acct = acctDao.getCustInfoAcct(batchBean.getRemitterAcct());
			batchBean.setRemitterAcct(StringUtils.trim(batchBean.getRemitterAcct()));
			//出票人账户锁状态检查
//			checkAcctLock(issueRet);
			
			/*String acctType = CacheInitServlet.ISSUE_ACCT_TYPE.get(issueRet.getProductId());
			if (acctType == null) {
				throw new ServiceException(new String[]{ErrorConst.BBSP0010});
			}*/
//			batchBean.setIssueBrchNo(acct.getAcctBranchNo());
			CustInfoDao custDao=new CustInfoDao();
			CustInfo cust=custDao.getCustInfo(acct.getCustNo());
			batchBean.setRemitter(cust.getCustName());
			BranchDao brchDao=new BranchDao();
			
			Branch brch = brchDao.getBranch(acct.getAcctBranchNo());
			if(brch==null){
				throw new BizAppException("账户["+batchBean.getRemitterAcct()+"]对应的开户机构["+acct.getAcctBranchNo()+"不存在");
			}
			batchBean.setRemitterBankName(brch.getBranchName());
			batchBean.setRemitterBankNo(brch.getBankNo());
			brchNo= batchBean.getBranchNo();
			Branch drwrBrch = brchDao.getBranch(brchNo);
//			batchBean.setDraweeBankAddr("");
			batchBean.setDraweeBankName(drwrBrch.getBranchName());
			batchBean.setDraweeBankNo(drwrBrch.getBankNo());
		} catch (SQLException e) {
			throw new BizAppException(e.getMessage());
		}
		
	}
	
	/**
	 * 处理还款帐号信息
	 * 
	 * @param batchBean
	 * @param info
	 * @return
	 * @throws BizAppException 
	 */
	protected void processRepaymentAcct(AcptApplyBean batchBean) throws BizAppException {
//		INPSProxyService npsService = (INPSProxyService) AgentFactory.getIntance("npsAgentProxy");
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String totalGrantAmt = "0.00";
		boolean acctFlag = false; //必须有一个公共活期结算户
		try {
			for (int i = 1; i < 6; i++) {
				String acctNo = StringUtils.trim((String) ReflectionUtils.getFieldValue(batchBean, "accountNo" + i));
				if (StringUtils.isBlank(acctNo)) {
					ReflectionUtils.setFieldValue(batchBean, "freezeGrantAmt"+i, 0.00);
					ReflectionUtils.setFieldValue(batchBean, "grantAmt"+i, 0.00);
					continue;
				}
	//			QueryAcptAccountInfoRet ret = npsService.queryAcptAccountInfo(
	//					acctNo, FlowNoUtil.getTimeFlowNo(), info.getTeller(), info.getBranch());
				
				//账户锁状态检查
	//			checkAcctLock(ret);
				CustInfoAcctDao acctDao=new CustInfoAcctDao();
				CustInfoAcct acct;
		
				acct = acctDao.getCustInfoAcct(acctNo);
				
				String brchNo =acct.getAcctBranchNo();
				CustInfoDao custDao=new CustInfoDao();
				
				ReflectionUtils.setFieldValue(batchBean, "accountBranchNo" + i,brchNo);
				ReflectionUtils.setFieldValue(batchBean, "accountName" + i,custDao.getCustInfo(acct.getCustNo()).getCustName());
				BranchDao brchDao=new BranchDao();
				Branch brch = brchDao.getBranch(brchNo);
				if (brch == null) {
					throw new BizAppException("账户["+acctNo+"]对应的开户机构["+brchNo+"不存在");
				}
				ReflectionUtils.setFieldValue(batchBean, "accountBranchName" + i, brch.getBranchName());
	
				ReflectionUtils.setFieldValue(batchBean, "accountType" + i, acct.getAcctType());
				
				if (AcptCodeConst.isCurtAcct(acct.getAcctType())) {//结算帐号的保证金限额必须为0
					System.out.println( ReflectionUtils.getFieldValue(batchBean, "grantAmt" + i));
					String grantAmt = String.valueOf( ReflectionUtils.getFieldValue(batchBean, "grantAmt" + i));
					
					if(!MathScaleUtil.compareWithZero(grantAmt)){
						throw new BizAppException("结算帐号的保证金限额必须为0");
					}
					//一个银承协议编号项下最少保留一个还款账户，而且这个账户必须为对公活期结算账号。
					if(AcptCodeConst.CORP_CURT.equals(acct.getAcctType())){
						acctFlag = true;
					}
					ReflectionUtils.setFieldValue(batchBean, "freezeGrantAmt"+i, 0.00);
					ReflectionUtils.setFieldValue(batchBean, "grantAmt"+i, 0.00);
					ReflectionUtils.setFieldValue(batchBean, "grantNo"+i, null);
				} else {// 如果帐号类型为非结算户
					String grantAmt = String.valueOf(ReflectionUtils.getFieldValue(batchBean, "grantAmt" + i));
					if (MathScaleUtil.isMoreThanOne(grantAmt)) {//保证金限额为0 则不调用PE服务进行圈存
						Map<String, String> map =new HashMap<String, String>();
	//					AcctLoopBean bean = new AcctLoopBean();
						map.put("grantAmt", grantAmt);
						map.put("acctNo", acctNo);
						map.put("Seq", String.valueOf(i));
						/*bean.setLoopMoney(grantAmt);
						bean.setAcctNo(acctNo);
						bean.setSeq(String.valueOf(i));*/
						list.add(map);
						// 汇总保证金限额
						totalGrantAmt = MathScaleUtil.add(totalGrantAmt, grantAmt);
						ReflectionUtils.setFieldValue(batchBean, "freezeGrantAmt" + i, grantAmt);
					}else{
						ReflectionUtils.setFieldValue(batchBean, "freezeGrantAmt"+i, 0.00);
						ReflectionUtils.setFieldValue(batchBean, "grantAmt"+i, 0.00);
					}
				}
			}
			if(!acctFlag){
				throw new BizAppException("银承协议项下还款账号必须至少一个对公活期结算户!");
			}
			batchBean.setTotalGrantAmt(Double.valueOf(totalGrantAmt));
			// 汇总实际圈存金额
			ReflectionUtils.setFieldValue(batchBean, "freezeTotalGrantAmt", Double.valueOf(totalGrantAmt));
	
			// 批量圈存，保存圈存金额和圈存帐号
	//		batchBlockCredit(batchBean, info, list);
		/*	String freezeTotalGrantAmt = "0";
			if (!list.isEmpty()) {
				PesInputBean inputBean = new PesInputBean(transInfo);
				List<AcctLoopBean> responseList = pesService.acctBatAuthSyn(inputBean, list);
				for (int i = 0; i < responseList.size(); i++) {
					AcctLoopBean resp = responseList.get(i);
					AcctLoopBean req = list.get(i);
					String seq = req.getSeq();
					//ReflectionUtils.setFieldValue(batchBean, "grantAmt" + seq, resp.getLoopMoney());
					ReflectionUtils.setFieldValue(batchBean, "grantNo" + seq, resp.getLoopNumber());
					ReflectionUtils.setFieldValue(batchBean, "freezeGrantAmt" + seq, resp.getLoopMoney());
					freezeTotalGrantAmt = MathScaleUtil.add(freezeTotalGrantAmt, resp.getLoopMoney());
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
    /**
     * 取缓存中的提醒日期常量
     * @throws Exception
     */
    public String isRemindDays() throws Exception {
        String  remindDaysFlag= SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.REMIND_DAYS);
        return remindDaysFlag;
    }
	
    /*****************************************************公共部分***************************************************************/
}	
	
	

