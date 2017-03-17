package com.herongtech.console.service.common.entitydraftregister;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptRegAndBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptSspdReg;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.domain.dao.CustInfoAcctDao;
import com.herongtech.console.domain.dao.CustInfoDao;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.entityregister.bean.EntityRegisterInfo;
import com.herongtech.console.domain.entityregister.dao.EntityRegisterInfoDao;
import com.herongtech.console.domain.get.bean.GetBillInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.entityRegisterCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.EcdsBankDataDao;
import com.herongtech.rc.draft.DraftService;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctBillInfoService;

public class EntityDraftRegisterService implements IEntityDraftRegisterService {

	EntityRegisterInfoDao dao = new EntityRegisterInfoDao();
	ISequenceService sequenceService = ServiceFactory.getSequenceService();
	IBranchService branchService = ServiceFactory.getBranchService();
	IRgctBillInfoService rgct = RcServiceFactory.getRgctBillInfoService();
	CustInfoAcctDao custAcctDao = new CustInfoAcctDao();
	EcdsBankDataDao ecdsBankData = new EcdsBankDataDao();
	CustInfoDao custDao = new CustInfoDao();
	/***
	 * 1:纸票承兑登记<br>
	 * 2.纸票未用退回登记<br>
	 * 3.纸票贴现登记<br>
	 * 4.纸票转贴现登记<br>
	 * 5.纸票再贴现登记 (待定)<br>
	 * 6:纸票质押登记<br>
	 * 7:纸票质押解除登记<br>
	 * 8:纸票委托收款登记<br>
	 * 9:纸票结清登记<br>
	 * 10:纸票拒付登记<br>
	 * 11:纸票挂失止付及公示催告登记<br>
	 * 12:纸票止付解除登记<br>
	 * @throws BizAppException
	 */
	@Override
	public void entityRegister() throws BizAppException {
		IDB session = DBFactory.getDB();
		this.entityRegisterOfAcceptance(session);
		this.entityRegisterOfDrawingBack(session);
		this.entityRegisterOfDiscount(session);
		this.entityRegisterOfRediscount(session);
		this.entityRegisterOfCollateralization(session);
		this.entityRegisterOfRepurchasedCollateralization(session);
		this.entityRegisterOfCollection(session);
		this.entityRegisterOfSettlement(session);
		this.entityRegisterOfPaymentRefused(session);
		this.entityRegisterOfSuspendingPayment(session);
		this.entityRegisterOfAnnulingSuspendingPayment(session);

	}

	public void entityRegisterOfAcceptance(IDB session){
		try {
			String sql = "select acpt.* from tacpt_bill_info as acpt where acpt.bill_status in ('1','2') and acpt.bill_class = '1' and acpt.bill_type = '1' and acpt.acpt_dt = '"+DateTimeUtil.convertToyyyyMMdd(DateTimeUtil.getWorkdayString())+"' order by acpt.acptmx_id";
			List<AcptBillInfo> list = session.getObjectList(sql, AcptBillInfo.class);
			if(list!=null&&list.size()>0){
				for (AcptBillInfo acptBillInfo : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(acptBillInfo.getBranchNo());
					RgctBillInfo bill = new RgctBillInfo();
					bill.setBillType(acptBillInfo.getBillType());
					bill.setBillNo(acptBillInfo.getBillNo());
					bill.setBillMoney(acptBillInfo.getBillMoney());
					bill.setIssueDt(acptBillInfo.getIssueDt());
					bill.setDueDt(acptBillInfo.getDueDt());
					bill.setAcceptor(acptBillInfo.getAcceptor());
					bill.setAcceptorBankNo(acptBillInfo.getAcceptorBankNo());
					bill.setDraweeBankNo(acptBillInfo.getDraweeBankNo());
					bill.setAcceptorDate(acptBillInfo.getAcptDt());
					bill.setRemitter(acptBillInfo.getRemitter());
					bill.setPayeeName(acptBillInfo.getPayee());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_100);
					entityRegisterInfo.setBillType(acptBillInfo.getBillType());
					entityRegisterInfo.setBillNo(acptBillInfo.getBillNo());
					entityRegisterInfo.setBillMoney(acptBillInfo.getBillMoney());
					entityRegisterInfo.setIssueDt(acptBillInfo.getIssueDt());
					entityRegisterInfo.setDueDt(acptBillInfo.getDueDt());
					entityRegisterInfo.setAcceptDt(acptBillInfo.getAcptDt());
					entityRegisterInfo.setAcceptor(acptBillInfo.getAcceptor());
					entityRegisterInfo.setAcceptorBankNo(acptBillInfo.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(acptBillInfo.getRemitter());
					entityRegisterInfo.setPayeeName(acptBillInfo.getPayee());
					entityRegisterInfo.setContractNo(acptBillInfo.getConferNo());
					entityRegisterInfo.setInvoiceNo(acptBillInfo.getInvcNb());
					entityRegisterInfo.setAgreeNo(acptBillInfo.getProtocalNo());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("101", branch.getPayBankNo()));
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(acptBillInfo.getBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("101", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setTxlCtrctNb(acptBillInfo.getTxlCtrctNb());
					vo.setInvcNb(acptBillInfo.getInvcNb());
					vo.setAccptncAgrmtNb(acptBillInfo.getProtocalNo());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1101"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票承兑登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfDrawingBack(IDB session){
		try {
			String sql = "select acpt.* from tacpt_bill_info as acpt where acpt.bill_class = '1' and acpt.bill_type = '1' and acpt.drwg_bck_dt = '"+DateTimeUtil.getWorkdayString()+"' and acpt.bill_status = '2' order by acpt.acptmx_id";
			List<AcptBillInfo> list = session.getObjectList(sql, AcptBillInfo.class);
			if(list!=null&&list.size()>0){
				for (AcptBillInfo acptBillInfo : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(acptBillInfo.getBranchNo());
					RgctBillInfo bill = new RgctBillInfo();
					bill.setBillType(acptBillInfo.getBillType());
					bill.setBillNo(acptBillInfo.getBillNo());
					bill.setBillMoney(acptBillInfo.getBillMoney());
					bill.setIssueDt(acptBillInfo.getIssueDt());
					bill.setDueDt(acptBillInfo.getDueDt());
					bill.setAcceptor(acptBillInfo.getAcceptor());
					bill.setAcceptorBankNo(acptBillInfo.getAcceptorBankNo());
					bill.setDraweeBankNo(acptBillInfo.getDraweeBankNo());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_110);
					entityRegisterInfo.setBillType(acptBillInfo.getBillType());
					entityRegisterInfo.setBillNo(acptBillInfo.getBillNo());
					entityRegisterInfo.setBillMoney(acptBillInfo.getBillMoney());
					entityRegisterInfo.setIssueDt(acptBillInfo.getIssueDt());
					entityRegisterInfo.setDueDt(acptBillInfo.getDueDt());
					entityRegisterInfo.setAcceptDt(acptBillInfo.getAcptDt());
					entityRegisterInfo.setAcceptor(acptBillInfo.getAcceptor());
					entityRegisterInfo.setAcceptorBankNo(acptBillInfo.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(acptBillInfo.getRemitter());
					entityRegisterInfo.setPayeeName(acptBillInfo.getPayee());
					entityRegisterInfo.setSignCancelDt(acptBillInfo.getDrwgBckDt());
					entityRegisterInfo.setSignCancelTime(acptBillInfo.getDrwgBckTime());
					entityRegisterInfo.setContractNo(acptBillInfo.getConferNo());
					entityRegisterInfo.setInvoiceNo(acptBillInfo.getInvcNb());
					entityRegisterInfo.setAgreeNo(acptBillInfo.getProtocalNo());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("125", branch.getPayBankNo()));
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(acptBillInfo.getBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("125", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setDrwgBckDt(acptBillInfo.getDrwgBckDt());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1125"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票未用退回登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfDiscount(IDB session){
		try {
			String sql = "select disc.* from tdisc_bill_info as disc where disc.account_date = '"+DateTimeUtil.getWorkdayString()+"' and disc.bill_class = '1' and disc.oper_status in ('BS036','BS041') order by disc.discmx_id";
			List<DiscBillInfo> list = session.getObjectList(sql, DiscBillInfo.class);
			if(list!=null&&list.size()>0){
				for (DiscBillInfo discBillInfo : list) {
					session.beginTransaction();
					CustInfo custInfo = custDao.getCustInfo(discBillInfo.getCustNo());
					CustInfoAcct custInfoAcct = custAcctDao.getCustInfoAcct(discBillInfo.getCustNo(), discBillInfo.getCustAccount());
					EcdsBankData bankData = ecdsBankData.getEcdsBankData(discBillInfo.getOperBankNo());
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(discBillInfo.getBranchNo());
					RgctBillInfo bill = rgct.getRgctBillInfo(discBillInfo.getRgctId());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_120);
					entityRegisterInfo.setBillType(discBillInfo.getBillType());
					entityRegisterInfo.setBillNo(discBillInfo.getBillNo());
					entityRegisterInfo.setBillMoney(discBillInfo.getBillMoney());
					entityRegisterInfo.setIssueDt(discBillInfo.getIssueDt());
					entityRegisterInfo.setDueDt(discBillInfo.getDueDt());
					entityRegisterInfo.setAcceptor(discBillInfo.getAcceptor());
					entityRegisterInfo.setAcceptorBankNo(discBillInfo.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(discBillInfo.getRemitter());
					entityRegisterInfo.setPayeeName(discBillInfo.getPayee());
					entityRegisterInfo.setDscntDt(discBillInfo.getDiscDt());
					entityRegisterInfo.setDscntPropsrNm(custInfoAcct.getAcctBranchName());
					entityRegisterInfo.setDscntPropsrBankNo(custInfoAcct.getAcctBankNo());
					entityRegisterInfo.setDscntBkNm(bankData.getActorFullCall());
					entityRegisterInfo.setDscntBkBankNo(discBillInfo.getOperBankNo());
					entityRegisterInfo.setContractNo(discBillInfo.getConferNo());
					entityRegisterInfo.setInvoiceNo(discBillInfo.getInvoiceNo());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("102", branch.getPayBankNo()));
					entityRegisterInfo.setIntrstrate1(discBillInfo.getRate());
					entityRegisterInfo.setDscntagreeno(discBillInfo.getProtocalNo());
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(discBillInfo.getBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("102", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setDiscDt(discBillInfo.getDiscDt());
					vo.setIntrstRate(discBillInfo.getRate());
					vo.setTxlCtrctNb(discBillInfo.getConferNo());
					vo.setInvcNb(discBillInfo.getInvoiceNo());
					vo.setDscntAgrmtNb(discBillInfo.getProtocalNo());
					vo.setFromName(custInfo.getCustName());
					vo.setReceiveName(bankData.getActorFullCall());
					vo.setInBankNo(discBillInfo.getOperBankNo());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1102"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票贴现登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfRediscount(IDB session){
		try {
			String sql = "select rebuy.* from trebuy_bill_info as rebuy where rebuy.bill_class = '1' and rebuy.account_date = '"+DateTimeUtil.getWorkdayString()+"' and rebuy.oper_status in ('BS136','BS140') order by rebuy.rebuymx_id";
			List<RebuyBillInfo> list = session.getObjectList(sql, RebuyBillInfo.class);
			if(list!=null&&list.size()>0){
				for (RebuyBillInfo rebuyBillInfo : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(rebuyBillInfo.getBranchNo());
					RgctBillInfo bill = rgct.getRgctBillInfo(rebuyBillInfo.getRgctId());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_130);
					entityRegisterInfo.setBillType(rebuyBillInfo.getBillType());
					entityRegisterInfo.setBillNo(rebuyBillInfo.getBillNo());
					entityRegisterInfo.setBillMoney(rebuyBillInfo.getBillMoney());
					entityRegisterInfo.setIssueDt(rebuyBillInfo.getIssueDt());
					entityRegisterInfo.setDueDt(rebuyBillInfo.getDueDt());
					entityRegisterInfo.setAcceptor(rebuyBillInfo.getAcceptor());
					entityRegisterInfo.setAcceptorBankNo(rebuyBillInfo.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(rebuyBillInfo.getRemitter());
					entityRegisterInfo.setPayeeName(rebuyBillInfo.getPayee());
					entityRegisterInfo.setRdscntWthcomrclbkDt(rebuyBillInfo.getRebuyDt());
					entityRegisterInfo.setContractNo(rebuyBillInfo.getConferNo());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("103", branch.getPayBankNo()));
					entityRegisterInfo.setIntrstrate2(rebuyBillInfo.getRate());
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(rebuyBillInfo.getBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("103", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setReDiscDt(rebuyBillInfo.getRebuyDt());
					vo.setIntrstRate(rebuyBillInfo.getRate());
					vo.setFromName(rebuyBillInfo.getCustBankName());
					vo.setReceiveName(branch.getBranchName());
					vo.setInBankNo(branch.getPayBankNo());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1103"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票转贴现登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfCollateralization(IDB session){
		try {
			String sql = "select sbi.* from tsave_bill_info as sbi where sbi.bill_class = '1' and sbi.prod_no = '001004' and sbi.oper_status = 'BS736' and sbi.account_date = '"+DateTimeUtil.getWorkdayString()+"' order by sbi.savemx_id";
			List<SaveBillInfo> list = session.getObjectList(sql, SaveBillInfo.class);
			if(list!=null&&list.size()>0){
				for (SaveBillInfo saveBillInfo : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(saveBillInfo.getBranchNo());
					RgctBillInfo bill = rgct.getRgctBillInfo(saveBillInfo.getRgctId());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_150);
					entityRegisterInfo.setBillType(saveBillInfo.getBillType());
					entityRegisterInfo.setBillNo(saveBillInfo.getBillNo());
					entityRegisterInfo.setBillMoney(saveBillInfo.getBillMoney());
					entityRegisterInfo.setIssueDt(saveBillInfo.getIssueDt());
					entityRegisterInfo.setDueDt(saveBillInfo.getDueDt());
					entityRegisterInfo.setAcceptor(saveBillInfo.getAcceptor());
					entityRegisterInfo.setAcceptorBankNo(saveBillInfo.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(saveBillInfo.getRemitter());
					entityRegisterInfo.setPayeeName(saveBillInfo.getPayee());
					entityRegisterInfo.setCollztnDt(saveBillInfo.getAccountDate());
					entityRegisterInfo.setCollztnPropsrNm(saveBillInfo.getCustName());
					entityRegisterInfo.setCollztnBkNm(branch.getBranchName());
					entityRegisterInfo.setCollztnBankNo(branch.getPayBankNo());
					entityRegisterInfo.setContractNo(saveBillInfo.getConferNo());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("105", branch.getPayBankNo()));
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(saveBillInfo.getBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("105", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setCollDt(saveBillInfo.getAccountDate());
					vo.setFromName(saveBillInfo.getCustName());
					vo.setReceiveName(branch.getBranchName());
					vo.setInBankNo(branch.getPayBankNo());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1105"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票质押登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfRepurchasedCollateralization(IDB session){
		try {
			String sql = "select gbi.* from tget_bill_info as gbi,tbranch as brch where gbi.bill_class = '1' and gbi.prod_no = '001005' and gbi.oper_status = 'BS836' and gbi.branch_no = brch.branch_no and gbi.account_date = '"+DateTimeUtil.getWorkdayString()+"' order by gbi.getmx_id";
			List<GetBillInfo> list = session.getObjectList(sql, GetBillInfo.class);
			if(list!=null&&list.size()>0){
				for (GetBillInfo getBillInfo : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(getBillInfo.getBranchNo());
					RgctBillInfo bill = rgct.getRgctBillInfo(getBillInfo.getRgctId());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_160);
					entityRegisterInfo.setBillType(getBillInfo.getBillType());
					entityRegisterInfo.setBillNo(getBillInfo.getBillNo());
					entityRegisterInfo.setBillMoney(getBillInfo.getBillMoney());
					entityRegisterInfo.setIssueDt(getBillInfo.getIssueDt());
					entityRegisterInfo.setDueDt(getBillInfo.getDueDt());
					entityRegisterInfo.setAcceptor(getBillInfo.getAcceptor());
					entityRegisterInfo.setAcceptorBankNo(getBillInfo.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(getBillInfo.getRemitter());
					entityRegisterInfo.setPayeeName(getBillInfo.getPayee());
					entityRegisterInfo.setUnchainCollztnDt(getBillInfo.getAccountDate());
					entityRegisterInfo.setUnchainCollztnTime(getBillInfo.getAccountTime());
					entityRegisterInfo.setFormerCollztnPropsrNm(getBillInfo.getCustName());
					entityRegisterInfo.setFormerCollztnBkNm(branch.getBranchName());
					entityRegisterInfo.setFormerCollztnBankNo(branch.getPayBankNo());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("106", branch.getPayBankNo()));
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(getBillInfo.getBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("106", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setRepCollDt(getBillInfo.getAccountDate());
					vo.setFromName(getBillInfo.getCustName());
					vo.setReceiveName(branch.getBranchName());
					vo.setInBankNo(branch.getPayBankNo());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1106"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票质押解除登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfCollection(IDB session){
		try {
			String sql = "select coll.* from tsubcoll_bill_info as coll,tbranch as brch where coll.bill_class = '1' and coll.acct_branch_no = brch.branch_no and coll.oper_status in ('BS326','BS336','BS340','BS330') and coll.coll_date = '"+DateTimeUtil.getWorkdayString()+"' order by coll.subcollmx_id";
			List<SubcollBillInfo> list = session.getObjectList(sql, SubcollBillInfo.class);
			if(list!=null&&list.size()>0){
				for (SubcollBillInfo subcollBillInfo : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(subcollBillInfo.getBranchNo());
					RgctBillInfo bill = rgct.getRgctBillInfo(subcollBillInfo.getRgctId());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_170);
					entityRegisterInfo.setBillType(subcollBillInfo.getBillType());
					entityRegisterInfo.setBillNo(subcollBillInfo.getBillNo());
					entityRegisterInfo.setBillMoney(subcollBillInfo.getBillMoney());
					entityRegisterInfo.setIssueDt(subcollBillInfo.getIssueDt());
					entityRegisterInfo.setDueDt(subcollBillInfo.getDueDt());
					entityRegisterInfo.setAcceptor(subcollBillInfo.getAcceptor());
					entityRegisterInfo.setAcceptorBankNo(subcollBillInfo.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(subcollBillInfo.getRemitter());
					entityRegisterInfo.setPayeeName(subcollBillInfo.getPayee());
					entityRegisterInfo.setColltnDt(subcollBillInfo.getCollDate());
					entityRegisterInfo.setColltnCount(subcollBillInfo.getEndorsnum());
					entityRegisterInfo.setColltnBankNm(subcollBillInfo.getCollPayeeBank());
					entityRegisterInfo.setColltnBankNo(subcollBillInfo.getCollPayeeBankNo());
					entityRegisterInfo.setPrncplNm(subcollBillInfo.getBillBeforeOwner());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("107", branch.getPayBankNo()));
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(subcollBillInfo.getAcctBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("107", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setSubCollDt(subcollBillInfo.getCollDate());
					vo.setEndorNum(Long.toString(subcollBillInfo.getEndorsnum()));
					vo.setFromName(subcollBillInfo.getCustName());
					vo.setReceiveName(branch.getBranchName());
					vo.setInBankNo(branch.getPayBankNo());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1107"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票委托收款登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfSettlement(IDB session){
		try {
			String sql = "select acpt.* from tacpt_bill_info as acpt,tacpt_colltn_reg as reg where acpt.bill_class = '1' and acpt.bill_type = '1' and acpt.bill_status = '5' and reg.colltn_status = '2' and reg.acpt_id=acpt.acpt_id and reg.bill_no=acpt.bill_no and acpt.payment_dt = '"+DateTimeUtil.getWorkdayString()+"' order by acpt.acptmx_id";
			List<AcptBillInfo> list = session.getObjectList(sql, AcptBillInfo.class);
			if(list!=null&&list.size()>0){
				for (AcptBillInfo acptBillInfo : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(acptBillInfo.getBranchNo());
					RgctBillInfo bill = rgct.getRgctBillInfo(acptBillInfo.getRgctId());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_180);
					entityRegisterInfo.setBillType(acptBillInfo.getBillType());
					entityRegisterInfo.setBillNo(acptBillInfo.getBillNo());
					entityRegisterInfo.setBillMoney(acptBillInfo.getBillMoney());
					entityRegisterInfo.setIssueDt(acptBillInfo.getIssueDt());
					entityRegisterInfo.setDueDt(acptBillInfo.getDueDt());
					entityRegisterInfo.setSttlmPayDt(acptBillInfo.getPaymentDt());
					entityRegisterInfo.setAcceptor(acptBillInfo.getAcceptor());
					entityRegisterInfo.setAcceptorBankNo(acptBillInfo.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(acptBillInfo.getRemitter());
					entityRegisterInfo.setPayeeName(acptBillInfo.getPayee());
					entityRegisterInfo.setContractNo(acptBillInfo.getConferNo());
					entityRegisterInfo.setInvoiceNo(acptBillInfo.getInvcNb());
					entityRegisterInfo.setAgreeNo(acptBillInfo.getProtocalNo());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("108", branch.getPayBankNo()));
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(acptBillInfo.getBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("108", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setSettleDt(acptBillInfo.getPaymentDt());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setFromName(acptBillInfo.getPayee());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1108"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票结清登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfPaymentRefused(IDB session){
		try {
			String sql = "select reg.* from tacpt_bill_info as acpt,tacpt_colltn_reg as reg where acpt.bill_class = '1' and acpt.bill_type = '1' and reg.colltn_status = '1' and reg.acpt_id=acpt.acpt_id and reg.bill_no=acpt.bill_no and reg.reject_dt = '"+DateTimeUtil.getWorkdayString()+"' order by reg.id";
			List<AcptRegAndBillInfo> list = session.getObjectList(sql, AcptRegAndBillInfo.class);
			if(list!=null&&list.size()>0){
				for (AcptRegAndBillInfo acptColltnReg : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					RgctBillInfo bill = new RgctBillInfo();
					bill.setBillType(acptColltnReg.getBillType());
					bill.setBillNo(acptColltnReg.getBillNo());
					bill.setBillMoney(acptColltnReg.getBillMoney());
					bill.setIssueDt(acptColltnReg.getIssueDt());
					bill.setDueDt(acptColltnReg.getDueDt());
					bill.setAcceptor(acptColltnReg.getAcceptor());
//					bill.setRemark(acptColltnReg.getRemark());
					bill.setAcceptorBankNo(acptColltnReg.getAcceptorBankNo());
					bill.setDraweeBankNo(acptColltnReg.getDraweeBankNo());
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(acptColltnReg.getBranchNo());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_190);
					entityRegisterInfo.setBillType(acptColltnReg.getBillType());
					entityRegisterInfo.setBillNo(acptColltnReg.getBillNo());
					entityRegisterInfo.setBillMoney(acptColltnReg.getBillMoney());
					entityRegisterInfo.setIssueDt(acptColltnReg.getIssueDt());
					entityRegisterInfo.setDueDt(acptColltnReg.getDueDt());
					entityRegisterInfo.setPmtrfsedDt(acptColltnReg.getRejectDt());
					entityRegisterInfo.setDshnrCd(acptColltnReg.getRejectCode());
					entityRegisterInfo.setDshnrRsn(acptColltnReg.getRejectReason());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("109", branch.getPayBankNo()));
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(acptColltnReg.getBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("109", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setPayRefuseDt(acptColltnReg.getRejectDt());
					vo.setPayRefuseReason(acptColltnReg.getRejectReason());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setFromName(acptColltnReg.getPayee());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1109"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票拒付登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfSuspendingPayment(IDB session){
		try {
			String sql = "select reg.*,acpt.drawee_bank_no,acpt.acceptor from tacpt_bill_info as acpt,tacpt_sspd_reg as reg where reg.acpt_id=acpt.acpt_id and reg.sspd_dt = '"+DateTimeUtil.getWorkdayString()+"' order by reg.id";
			List<AcptSspdReg> list = session.getObjectList(sql, AcptSspdReg.class);
			if(list!=null&&list.size()>0){
				for (AcptSspdReg acptSspdReg : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					RgctBillInfo bill = new RgctBillInfo();
					bill.setBillType(acptSspdReg.getBillType());
					bill.setBillNo(acptSspdReg.getBillNo());
					bill.setBillMoney(acptSspdReg.getBillMoney());
					bill.setIssueDt(acptSspdReg.getIssueDt());
					bill.setDueDt(acptSspdReg.getDueDt());
					bill.setAcceptor(acptSspdReg.getAcceptor());
					bill.setAcceptorBankNo(acptSspdReg.getAcceptorBankNo());
					bill.setRemark(acptSspdReg.getRemark());
					bill.setDraweeBankNo(acptSspdReg.getDraweeBankNo());
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(acptSspdReg.getSspdBranchNo());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_200);
					entityRegisterInfo.setBillType(acptSspdReg.getBillType());
					entityRegisterInfo.setBillNo(acptSspdReg.getBillNo());
					entityRegisterInfo.setBillMoney(acptSspdReg.getBillMoney());
					entityRegisterInfo.setIssueDt(acptSspdReg.getIssueDt());
					entityRegisterInfo.setDueDt(acptSspdReg.getDueDt());
					entityRegisterInfo.setAcceptorBankNo(acptSspdReg.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(acptSspdReg.getRemitter());
					entityRegisterInfo.setSspdgPmtTp("0".equals(acptSspdReg.getIsPubExhort())||StringUtils.isBlank(acptSspdReg.getIsPubExhort())? "SP00" : "SP01");//止付类型
					entityRegisterInfo.setSspdgPmtDt(acptSspdReg.getSspdDt());
					entityRegisterInfo.setSspdgPmtTime(acptSspdReg.getSspdTime());
					entityRegisterInfo.setPropsrNm(acptSspdReg.getSspdName());
					entityRegisterInfo.setOprtrNm(acptSspdReg.getSspdOperName());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("110", branch.getPayBankNo()));
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(acptSspdReg.getSspdBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("110", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setSuspendPayType("0".equals(acptSspdReg.getIsPubExhort())||StringUtils.isBlank(acptSspdReg.getIsPubExhort())? "SP00" : "SP01");
					vo.setSuspendPayDt(acptSspdReg.getSspdDt());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1110"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票挂失止付及公示催告登记异常|"+e.getMessage());
		}
	}
	
	public void entityRegisterOfAnnulingSuspendingPayment(IDB session){
		try {
			String sql = "select reg.*,acpt.drawee_bank_no,acpt.acceptor from tacpt_bill_info as acpt,tacpt_sspd_reg as reg where reg.acpt_id=acpt.acpt_id and reg.anlg_sspd_dt = '"+DateTimeUtil.getWorkdayString()+"' order by reg.id";
			List<AcptSspdReg> list = session.getObjectList(sql, AcptSspdReg.class);
			if(list!=null&&list.size()>0){
				for (AcptSspdReg acptSspdReg : list) {
					session.beginTransaction();
					EntityRegisterInfo entityRegisterInfo = new EntityRegisterInfo();
					RgctBillInfo bill = new RgctBillInfo();
					bill.setBillType(acptSspdReg.getBillType());
					bill.setBillNo(acptSspdReg.getBillNo());
					bill.setBillMoney(acptSspdReg.getBillMoney());
					bill.setIssueDt(acptSspdReg.getIssueDt());
					bill.setDueDt(acptSspdReg.getDueDt());
					bill.setAcceptor(acptSspdReg.getAcceptor());
					bill.setAcceptorBankNo(acptSspdReg.getAcceptorBankNo());
					bill.setRemark(acptSspdReg.getRemark());
					bill.setDraweeBankNo(acptSspdReg.getDraweeBankNo());
					DraftInfoVo vo = new DraftInfoVo();
					Branch branch = branchService.getBranch(acptSspdReg.getSspdBranchNo());
					entityRegisterInfo.setId(sequenceService.getENTITY_REGISTER_ID());
					entityRegisterInfo.setRegisterDate(DateTimeUtil.getWorkdayString());
					entityRegisterInfo.setRegisterTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
					entityRegisterInfo.setOperNo(ResourceUtil.getVirtualUserLoginfo().getUserId());
					entityRegisterInfo.setRegisterType(entityRegisterCodeConst.ENTITY_REGISTER_TYPE_210);
					entityRegisterInfo.setBillType(acptSspdReg.getBillType());
					entityRegisterInfo.setBillNo(acptSspdReg.getBillNo());
					entityRegisterInfo.setBillMoney(acptSspdReg.getBillMoney());
					entityRegisterInfo.setIssueDt(acptSspdReg.getIssueDt());
					entityRegisterInfo.setDueDt(acptSspdReg.getDueDt());
					entityRegisterInfo.setAcceptorBankNo(acptSspdReg.getAcceptorBankNo());
					entityRegisterInfo.setRemitter(acptSspdReg.getRemitter());
					entityRegisterInfo.setAnlgSspdgPmtTp("0".equals(acptSspdReg.getIsPubExhort())||StringUtils.isBlank(acptSspdReg.getIsPubExhort())? "AS00" : "AS01");//止付解除类型
					entityRegisterInfo.setAnlgSspdgPmtDt(acptSspdReg.getAnlgSspdDt());
					entityRegisterInfo.setAnlgSspdgPmtTime(acptSspdReg.getAnlgSspdTime());
					entityRegisterInfo.setDispelPropsrNm(acptSspdReg.getAnlgSspdName());
					entityRegisterInfo.setDispelOprtrNm(acptSspdReg.getAnlgSspdOperName());
					entityRegisterInfo.setReplyFlag("0");
					entityRegisterInfo.setMsgId(MsgUtil.getMsgId("111", branch.getPayBankNo()));
					entityRegisterInfo.setOperBranchNo(ResourceUtil.getVirtualUserLoginfo().getBrchNo());
					entityRegisterInfo.setRegBranchNo(acptSspdReg.getSspdBranchNo());
					dao.addEntityRegisterInfo(entityRegisterInfo);
					vo.setReqMsgId(MsgUtil.getMsgId("111", branch.getPayBankNo()));
					vo.setBill(bill);
					vo.setAnnulSuspendPayType("0".equals(acptSspdReg.getIsPubExhort())||StringUtils.isBlank(acptSspdReg.getIsPubExhort())? "AS00" : "AS01");
					vo.setAnnulSuspendPayDt(acptSspdReg.getAnlgSspdDt());
					vo.setFromBankNo(branch.getPayBankNo());
					vo.setReceiveBankNo("9968");
					RgctMethod method = new RgctMethod();
					method.setId(Long.parseLong("1111"));
					DraftService ds = RcServiceFactory.getDraftService();
					ds.buildAndSend(vo, method);
					session.endTransaction();
				}
			}else{
				return;
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().infoMessage("纸票止付解除登记异常|"+e.getMessage());
		}
	}
}
