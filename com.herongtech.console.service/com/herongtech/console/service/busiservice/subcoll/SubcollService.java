/********************************************
 * 文件名称: SubcollService.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.acct.bean.AccountResponseDTO;
import com.herongtech.console.domain.acct.bean.AcctBalance;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.dao.BuybackBillInfoDao;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.dao.DiscBillInfoDao;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
import com.herongtech.console.domain.save.dao.SaveBillInfoDao;
import com.herongtech.console.domain.subcoll.bean.SubcollApplyInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollQueryCondition;
import com.herongtech.console.domain.subcoll.dao.SubcollApplyInfoDao;
import com.herongtech.console.domain.subcoll.dao.SubcollBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.collateralization.ICollateralizationService;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.IAcctBalanceService;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.console.web.busicontroller.common.SubcollCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctEndoHist;
import com.herongtech.rc.domain.dao.RgctEndoHistDao;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.rc.service.rcservice.IRcPresentationService;
import com.herongtech.rc.service.rcservice.RcBaseService;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 托收服务类Service
 * @author Administrator
 *
 */
public class SubcollService extends RcBaseService implements ISubcollService{
	
	private  SubcollBillInfoDao subcollBillInfoDao = new SubcollBillInfoDao(); 
	private SubcollApplyInfoDao subcollApplyInfoDao = new SubcollApplyInfoDao(); 
	/**
	 * 初始化查询条件
	 * @param query
	 */
	private void initQueryCondition(SubcollQueryCondition query){
		query.addProperty2TableObj("statusArray", "bill");
		query.addProperty2TableObj("operStatus", "bill");
		query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
		query.addSqlPropretyMapping("statusArray", "operStatus");
		query.addProperty2TableObj("subcollmxId", "bill");
	}
	/**
	 * 功能描述：根据批次id查询清单
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	public List<SubcollBillInfo> getSubcollBillsBySearchBean(Page page,SubcollQueryCondition query) throws BizAppException {
		if(!"".equals(query.getBillNo())&&query.getBillNo()!=null){
			query.setBillNo("%"+query.getBillNo()+"%");
		}
		query.setDfaultSrchTbAliasName("bill");
		query.addProperty2TableObj("subcollId", "bill");
		query.addSpecialOpertion("endDay",BaseSearchBean.LESS_AND_EQUAL);
	    query.addSqlPropretyMapping("endDay", "dueDt");
	    query.addSpecialOpertion("startDay",BaseSearchBean.MORE_AND_EQUAL);
        query.addSqlPropretyMapping("startDay", "dueDt");
        query.addSpecialOpertion("bigMoney",BaseSearchBean.LESS_AND_EQUAL);
	    query.addSqlPropretyMapping("bigMoney", "billMoney");
	    query.addSpecialOpertion("smallMoney",BaseSearchBean.MORE_AND_EQUAL);
        query.addSqlPropretyMapping("smallMoney", "billMoney");
        query.addSpecialOpertion("billNo",BaseSearchBean.LIKE);
	    query.addSqlPropretyMapping("billNo", "billNo");
		try {
			return subcollBillInfoDao.getSubcollBillsBySearchBean(page,query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException("清单信息查询失败");
		}
	}


	/**
	 * 功能描述：得到批次详情
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	public SubcollApplyInfo getSubcollApplyInfo(SubcollQueryCondition query) throws BizAppException {
		try {
			return subcollApplyInfoDao.getSubcollApplyInfo(query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}


	/**
	 * 功能描述：根据前台输入获取可托收票据信息
	 * @param page
	 * @param query
	 * @throws BizAppException
	 */
	public List<RgctBillData> getSubcollBillForPrint(Page page,SubcollQueryCondition query) throws BizAppException {
		RcBaseSearchBean rcSb = new RcBaseSearchBean();
		List<RgctBillData> list=new ArrayList<RgctBillData>();
		try {
			if(!"".equals(query.getBillNo())&&query.getBillNo()!=null){
				query.setBillNo("%"+query.getBillNo()+"%");
			}
			if(!"".equals(query.getAcceptor())&&query.getAcceptor()!=null){
				query.setAcceptor("%"+query.getAcceptor()+"%");
			}
			OrderBean order=new OrderBean("dueDt",true);
			rcSb.appendOrder(order);
			rcSb.addSpecialOpertion("billNo",BaseSearchBean.LIKE);
			rcSb.addSqlPropretyMapping("billNo", "billNo");
			rcSb.addSpecialOpertion("acceptor",BaseSearchBean.LIKE);
			rcSb.addSqlPropretyMapping("acceptor", "acceptor");
			rcSb.setMinDueDt(query.getStartDay());
			rcSb.setMaxDueDt(query.getEndDay());
			rcSb.setMinBillMoney(query.getSmallMoney());
			rcSb.setMaxBillMoney(query.getBigMoney());
			rcSb.setBillType(query.getBillType());
			rcSb.setBillClass(query.getBillClass());
			rcSb.setBillNo(query.getBillNo());
			rcSb.setBillMoney(query.getBillMoney());
			rcSb.setAcceptor(query.getAcceptor());
			rcSb.setAcceptorBankNo(query.getAcceptorBankNo());
			rcSb.setAcceptorBankName(query.getAcceptorBankName());
			rcSb.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			rcSb.setHoldBankNo(ResourceUtil.getSessionLoginfo().getBrchBankNo());//获得登陆者行号进行查票
			rcSb.setHoldAcctNo("0");
			return  RcServiceFactory.getRcPresentationService().queryPrecollectBill(rcSb,page);
		} catch (BizAppException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获得SubcollBillInfo bean 通过rgctId
	 * @param rgctId
	 * @throws BizAppException
	 */
	public SubcollBillInfo getSubcollBillForRgctId(String rgctId) throws BizAppException {
		SubcollBillInfo billInfo = new SubcollBillInfo();
		try {
			billInfo = subcollBillInfoDao.getSubcollBillForRgctId(rgctId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		return billInfo;
	}
	
	/**
	 * 获得SubcollBillInfo bean 通过rgctId 和 operstatus
	 * @param rgctId
	 * @param operstatus
	 * @return
	 * @throws BizAppException
	 */
	public SubcollBillInfo getSubcollBillForRgctIdAndOperStatus(String rgctId,String operstatus) throws BizAppException{
		SubcollBillInfo billInfo = new SubcollBillInfo();
		try {
			billInfo = subcollBillInfoDao.getSubcollBillForRgctIdAndOperStatus(rgctId,operstatus);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		return billInfo;
	}
	
	/**
	 * 获得List<SubcollBillInfo>  通过subcollId
	 * @param subcollId
	 * @return
	 * @throws BizAppException
	 */
	public List<SubcollBillInfo> getSubcollBillForSubcollId(String subcollId) throws BizAppException {
		List<SubcollBillInfo> list = new ArrayList<SubcollBillInfo>();
		try {
			list = subcollBillInfoDao.getSubcollBillForSubcollId(subcollId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 获得List<SubcollBillInfo>  通过rgctId
	 * @param rgctId
	 * @return
	 * @throws BizAppException
	 */
	public SubcollBillInfo getSubcollBillForRgctid(String rgctId) throws BizAppException {
		SubcollBillInfo BillInfo = new SubcollBillInfo();
		try {
			BillInfo = subcollBillInfoDao.getSubcollBillForRgctId(rgctId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		return BillInfo;
	}
	
	/**
	 * 更新实体subcollBillInfo信息
	 * @param bill
	 * @throws BizAppException
	 */
	public void modifySubcollBillInfo(SubcollBillInfo bill) throws BizAppException {
		try {
			subcollBillInfoDao.modifySubcollBillInfo(bill);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新实体SubcollApplyInfo信息
	 * @param applyInfo
	 * @throws BizAppException
	 */
	public void modifySubcollApplyInfo(SubcollApplyInfo applyInfo) throws BizAppException {
		try {
			subcollApplyInfoDao.modifySubcollApplyInfo(applyInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能描述：添加票据和批次信息
	 * @param ids
	 * @param query
	 * @throws BizAppException
	 */
	public void addBillInfoAndApplyInfo(String ids , SubcollQueryCondition query)
			throws BizAppException {
		RgctBillInfo rgctBillInfo = new RgctBillInfo();
		RgctBillHist rgctBillHist = new RgctBillHist();
		SubcollBillInfo subcollBillInfo = new SubcollBillInfo();
		SubcollApplyInfo subcollApplyInfo = new SubcollApplyInfo();
		RgctEndoHistDao endoHistDao = new RgctEndoHistDao();
		ISequenceService SequenceService = ServiceFactory.getSequenceService();
		IAcctBalanceService  acctBalanceService = ServiceFactory.getAcctBalanceService();
		IEcdsBankDataService ecdsBankDataService = ServiceFactory.getEcdsBankDataService();
		String subcollId = SequenceService.getSUBCOLL_APPLY_ID();
		String branchNo=ResourceUtil.getSessionLoginfo().getBranchNo();
		if(query==null){
			query = new SubcollQueryCondition();
		}
		String operStatus = query.getOperStatus();
		String [] idss =CommUtils.couvertLong(ids);
		IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();
		try {
			for (int i = 0; i < idss.length; i++) {
				String id = idss[i];
				/*插入清单表*/
				RgctBill rgctBill =rcService.getRgctBillById(id);
				rgctBillInfo = rgctBill.getInfo();
				rgctBillHist = rgctBill.getHist();
				subcollBillInfo.setSubcollmxId(SequenceService.getSUBCOLL_BILL_INFO_ID());;
				subcollBillInfo.setSubcollId(subcollId);
				subcollBillInfo.setOperStatus(operStatus);
				subcollBillInfo.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
				subcollBillInfo.setSubcollno(SequenceService.getSubcollApplyNo(branchNo));//暂时塞入批次号
				subcollBillInfo.setOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
				subcollBillInfo.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
				subcollBillInfo.setPosition(SubcollCodeConst.OUTSTORE);
				subcollBillInfo.setYzSource("0");
				//添加背书次数
				List<RgctEndoHist> list = endoHistDao.getRgctEndoHistList(id);
				subcollBillInfo.setEndorsnum(new Long((long)list.size()));
				//如果是纸票，默认未逾期和线下清算，电票进行前台输入获取
				if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(rgctBillInfo.getBillClass())){
					subcollBillInfo.setIsOverdue(SubcollCodeConst.IS_OVERDUE_ONE);
					subcollBillInfo.setIsOnline(RcConstants.SETTLEMENTMARK_ZERO);
				}else{
					subcollBillInfo.setIsOverdue(query.getIsOverdue());
					subcollBillInfo.setIsOnline(query.getIsOnline());
				}
				//获取票据来源与最近票据来源的清单id
				AcctBalance acctBalance = acctBalanceService.getAcctBalanceByRgctId(rgctBillInfo.getId());
				if(acctBalance==null){
					acctBalance = new AcctBalance();
				}
				String billSource = SubcollCodeConst.statusMap.get(acctBalance.getProdNo());
				if(StringUtils.isBlank(billSource)){
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "获取业务类型时异常! 票号:"+rgctBillInfo.getBillNo());
				}
				subcollBillInfo.setBillSource(billSource);
				subcollBillInfo.setBillId(acctBalance.getInfoId());
				this.addSubcollBillInfoForRgctBill(rgctBill, query,subcollBillInfo);
				this.reSetOriginateInfo(billSource,rgctBillHist,subcollBillInfo);
				this.reSetCollPayeeInfo(subcollBillInfo);
				//加锁
				rcService.lock(rgctBillInfo.getId());
				if (subcollBillInfoDao.addSubcollBillInfo(subcollBillInfo) != 1) {
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加SubcollApplyInfo失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("添加票据信息失败");
			throw new BizAppException(e.getMessage());
		}
		try {
			//插入批次表
			String id = idss[0];
			RgctBill rgctBill =rcService.getRgctBillById(id);
			rgctBillInfo = rgctBill.getInfo();
			subcollApplyInfo.setSubcollId(subcollId);
			subcollApplyInfo.setBillType(rgctBillInfo.getBillType());
			subcollApplyInfo.setBillClass(rgctBillInfo.getBillClass());
			subcollApplyInfo.setBatchId(SequenceService.getSubcollApplyNo(branchNo));
			subcollApplyInfo.setFromBankNo(ResourceUtil.getSessionLoginfo().getBrchBankNo());
			subcollApplyInfo.setFromBankName(ResourceUtil.getSessionLoginfo().getBranchName());
			subcollApplyInfo.setEms(query.getEMS());
			subcollApplyInfo.setInAcctNo(query.getInAcctNo());
			subcollApplyInfo.setInBankNo(query.getInBankNo());
			subcollApplyInfo.setBranchNo(branchNo);
			//纸票中需要加入托收人的信息，电票不需要（为打印做准备）
			if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(rgctBillInfo.getBillClass())){
				subcollApplyInfo.setToBankNo(rgctBillInfo.getAcceptorBankNo());
				subcollApplyInfo.setToBankName(rgctBillInfo.getAcceptorBankName());
				EcdsBankData ebd = ecdsBankDataService.getEcdsBankData(rgctBillInfo.getAcceptorBankNo());
				if(ebd==null){
					ebd = new EcdsBankData();
				}
				subcollApplyInfo.setToBankAddress(ebd.getAddress());
			}
			//如果逾期，说明逾期原因
			subcollApplyInfo.setOverduereason(query.getOverdueReason());
			if (subcollApplyInfoDao.addSubcollApplyInfo(subcollApplyInfo) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加SubcollApplyInfo失败");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("插入记录tsubcoll_apply_info失败");
			throw new BizAppException(e1.getMessage());
		}
	}

	/**
	 * 功能描述：添加票据往清单表中
	 * @param ids
	 * @param subcollId
	 * @param query
	 * @throws BizAppException
	 */
	public void addSubcollBillInfo(String ids,String subcollId,SubcollQueryCondition query) throws BizAppException {
		RgctBillInfo rgctBillInfo = new RgctBillInfo();
		RgctBillHist rgctBillHist = new RgctBillHist();
		SubcollBillInfo subcollBillInfo = new SubcollBillInfo();
		RgctEndoHistDao endoHistDao = new RgctEndoHistDao();
		ISequenceService SequenceService = ServiceFactory.getSequenceService();
		IAcctBalanceService  acctBalanceService = ServiceFactory.getAcctBalanceService();
		if(query==null){
			query = new SubcollQueryCondition();
		}
		String [] idss =CommUtils.couvertLong(ids);
		IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();
		try {
			for (int i = 0; i < idss.length; i++) {
				String id = idss[i];
				RgctBill rgctBill =rcService.getRgctBillById(id);
				rgctBillInfo = rgctBill.getInfo();
				rgctBillHist = rgctBill.getHist();
				subcollBillInfo.setSubcollmxId(SequenceService.getSUBCOLL_BILL_INFO_ID());
				subcollBillInfo.setSubcollId(subcollId);
				subcollBillInfo.setOperStatus(StatusUtils.handleStatusNoCheck("SubcollApplyController", "save", null));
				subcollBillInfo.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
				subcollBillInfo.setSubcollno(SequenceService.getSubcollApplyNo(ResourceUtil.getSessionLoginfo().getBranchNo()));//暂时塞入批次号
				subcollBillInfo.setOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
				subcollBillInfo.setPosition(SubcollCodeConst.APPLY);
				subcollBillInfo.setYzSource("0");
				//添加背书次数
				List<RgctEndoHist> list = endoHistDao.getRgctEndoHistList(id);
				subcollBillInfo.setEndorsnum(new Long((long)list.size()));
				//如果是纸票，默认未逾期和线下，电票进行从前台获取
				if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(rgctBillInfo.getBillClass())){
					subcollBillInfo.setIsOverdue(SubcollCodeConst.IS_OVERDUE_ONE);
					subcollBillInfo.setIsOnline(RcConstants.SETTLEMENTMARK_ZERO);
				}else{
					subcollBillInfo.setIsOverdue(query.getIsOverdue());
					subcollBillInfo.setIsOnline(query.getIsOnline());
				}
				//获取票据来源与最近票据来源的清单id
				AcctBalance acctBalance = acctBalanceService.getAcctBalanceByRgctId(rgctBillInfo.getId());
				if(acctBalance==null){
					acctBalance = new AcctBalance();
				}
				String billSource = SubcollCodeConst.statusMap.get(acctBalance.getProdNo());
				if(StringUtils.isBlank(billSource)){
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "获取业务类型时异常! 票号:"+rgctBillInfo.getBillNo());
				}
				subcollBillInfo.setBillSource(billSource);
				subcollBillInfo.setBillId(acctBalance.getInfoId());
				this.addSubcollBillInfoForRgctBill(rgctBill, query,subcollBillInfo);
				this.reSetOriginateInfo(billSource,rgctBillHist,subcollBillInfo);
				this.reSetCollPayeeInfo(subcollBillInfo);
				//加锁
				rcService.lock(rgctBillInfo.getId());
				if (subcollBillInfoDao.addSubcollBillInfo(subcollBillInfo) != 1) {
				      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加SubcollApplyInfo失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("添加票据失败");
			throw new BizAppException(e.getMessage());
		}
	}
	
	/**
	 * 从RgctBill中copy票面信息
	 * @param rgctBill
	 * @param query
	 * @param subcollBillInfo
	 * @throws BizAppException
	 */
	public void addSubcollBillInfoForRgctBill(RgctBill rgctBill,SubcollQueryCondition query,SubcollBillInfo subcollBillInfo) throws BizAppException {
		RgctBillInfo rgctBillInfo = rgctBill.getInfo();
		RgctBillHist rgctBillHist = rgctBill.getHist();
		subcollBillInfo.setBillNo(rgctBillInfo.getBillNo());
		subcollBillInfo.setBillType(rgctBillInfo.getBillType());
		subcollBillInfo.setBillClass(rgctBillInfo.getBillClass());
		subcollBillInfo.setBuyType(rgctBillHist.getBuyType());
		subcollBillInfo.setIssueDt(rgctBillInfo.getIssueDt());
		subcollBillInfo.setDueDt(rgctBillInfo.getDueDt());
		subcollBillInfo.setBillMoney(rgctBillInfo.getBillMoney());
		subcollBillInfo.setDraweeBankNo(rgctBillInfo.getDraweeBankNo());
		subcollBillInfo.setAcceptor(rgctBillInfo.getAcceptor());
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(rgctBillInfo.getBillClass())){
			subcollBillInfo.setRemark(rgctBillInfo.getRemark());
			subcollBillInfo.setIsOnline(RcConstants.SETTLEMENTMARK_ZERO);
			subcollBillInfo.setIsOverdue(SubcollCodeConst.IS_OVERDUE_ONE);
			if(IDict.K_BILL_TYPE.K_BANK_BILL.equals(rgctBillInfo.getBillType())){
				subcollBillInfo.setAcceptorAcct("0");
			}else{
				subcollBillInfo.setAcceptorAcct(rgctBillInfo.getRemitterAcct());
			}			
		}else{
			subcollBillInfo.setAcceptorAcct(rgctBillInfo.getAcceptorAcct());
			subcollBillInfo.setRemark(query.getRemark());
			subcollBillInfo.setIsOnline(query.getIsOnline());
			subcollBillInfo.setIsOverdue(query.getIsOverdue());
		}
		subcollBillInfo.setAcceptorBankName(rgctBillInfo.getAcceptorBankName());
		subcollBillInfo.setAcceptorBankNo(rgctBillInfo.getAcceptorBankNo());
		subcollBillInfo.setDealBranchNo(rgctBillInfo.getDraweeBranchNo());
		subcollBillInfo.setRgctId(rgctBillInfo.getId());
		subcollBillInfo.setDraweeBankName(rgctBillInfo.getDraweeBankName());
		subcollBillInfo.setDraweeAddr(rgctBillInfo.getDraweeAddr());
		subcollBillInfo.setPayee(rgctBillInfo.getPayeeName());
		subcollBillInfo.setPayeeAcct(rgctBillInfo.getPayeeAcct());
		subcollBillInfo.setPayeeBankName(rgctBillInfo.getPayeeBankName());
		
		subcollBillInfo.setPayeeBankNo(rgctBillInfo.getPayeeBankNo());
		subcollBillInfo.setForbidFlag(rgctBillHist.getForbidFlag());
		subcollBillInfo.setRemitterBankNo(rgctBillInfo.getRemitterBankNo());
		subcollBillInfo.setBillBeforeOwner(rgctBillHist.getBillBeforeOwner());
		subcollBillInfo.setCreateDt(rgctBillInfo.getCreateDt());
		subcollBillInfo.setCreateTime(rgctBillInfo.getCreateTime());
		subcollBillInfo.setAcctBranchNo(rgctBillInfo.getAcctBranchNo());
		subcollBillInfo.setProdAttr(rgctBillHist.getProdAttr());
		subcollBillInfo.setGaleDate(rgctBillHist.getInterestDueDt());
		subcollBillInfo.setCustName(rgctBillHist.getHoldCustName());
		
		subcollBillInfo.setRemitter(rgctBillInfo.getRemitter());
		subcollBillInfo.setRemitterAcct(rgctBillInfo.getRemitterAcct());
		subcollBillInfo.setRemitterBankName(rgctBillInfo.getRemitterBankName());
	}
	
	/**
	 * 提交收款记账
	 * @param ids 托收明细主键
	 * @throws BizAppException
	 */
    public void submitReceiveMoneyAcct(String ids) throws BizAppException {
	        try {
	        	List<SubcollBillInfo> billList= subcollBillInfoDao.getSubcollBillInfoByIds(ids);
	 	        IRcPresentationService rcpService=RcServiceFactory.getRcPresentationService();
	 	        //调用记账方法
	 	        IAccountFacadeService acctService = ServiceFactory.getSubcollAccountService();
	 	        AccountRequestDTO<SubcollApplyInfo,SubcollBillInfo> accReq = new AccountRequestDTO<SubcollApplyInfo, SubcollBillInfo>();
	 	        accReq.setBillList(billList);
	 	        accReq.setUserLogonInfo(ResourceUtil.getSessionLoginfo());
	 	        AccountResponseDTO accResp = new AccountResponseDTO();
	        	accResp = acctService.account(accReq);
	        	//修改SubcollBillInfo信息
	        	for(SubcollBillInfo bill:billList){
	                bill.setGathDate(DateTimeUtil.getWorkdayString());
	                bill.setGathAuditOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
	                bill.setStatus(SubcollCodeConst.SUB_STAUTS_SETTLED);
	                bill.setCurStatus(RcConstants.CUR_STATUS_SUB_COLL_BACK);
	                bill.setPosition(SubcollCodeConst.ACCOUNT_OVER);
	                bill.setAcctFlowNo(accResp.getForeFlowNo());
	                bill.setOperStatus(StatusUtils.handleStatus("ReceiveMoneyController", "submitAccount", null, bill.getOperStatus()));
	                subcollBillInfoDao.modifySubcollBillInfo(bill);
	                updateInstrIncome(bill);
	                RgctBill rcBill=rcpService.getRgctBillById(bill.getRgctId());
	                rcpService.inputGether(rcBill);
	            }
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new BizAppException("提交收款记账失败");
			}
    }
   
    
    /**
	 * 提交撤销记账
	 * @param ids 托收明细主键
	 * @throws Exception
	 */
	public void submitRevokeMoneyAcct(String ids) throws BizAppException {
	       try {
	           List<SubcollBillInfo> billList= subcollBillInfoDao.getSubcollBillInfoByIds(ids);
	           IRcPresentationService rcpService=RcServiceFactory.getRcPresentationService();
	           //修改SubcollBillInfo信息
	           for(SubcollBillInfo bill:billList){
	        	   if(RcConstants.SETTLEMENTMARK_ONE.equals(bill.getIsOnline())){
	   					throw new BizAppException("线上清算业务不允许撤销记账!");
	   				}
	        	   	// 更新状态
		            bill.setStatus(SubcollCodeConst.SUB_STAUTS_ONWAY);// 1，托收在途；2,结清;3,退票
		            bill.setGathDate(null);
		   			bill.setPosition(SubcollCodeConst.ACCOUNT);
		   			bill.setCurStatus(RcConstants.CUR_STATUS_SUB_ON_PASSAGE);
		   			bill.setOperStatus(StatusUtils.handleStatus("ReceiveMoneyController", "submitRevokeAccount", null, bill.getOperStatus()));
		   			subcollBillInfoDao.modifySubcollBillInfo(bill);
			        RgctBill rcBill=rcpService.getRgctBillById(bill.getRgctId());
			        rcpService.cancelSign(rcBill);
			        //冲正
			        IAccountFacadeService acctService = ServiceFactory.getSubcollAccountService();
			        UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
			        AccountRequestDTO<SubcollApplyInfo,SubcollBillInfo> accReq = new AccountRequestDTO<SubcollApplyInfo, SubcollBillInfo>();
			        accReq.setBillList(billList);
			        accReq.setUserLogonInfo(userInfo);
			        acctService.reverseAccount(accReq);
	          }
	        }catch (Exception e) {
	              e.printStackTrace();
	              throw new BizAppException("提交撤销记账失败");
	          }
	       }
    
    /**
	 * 提交收款登记
	 * @param ids 托收明细主键
	 * @throws BizAppException
	 */
	public void submitReceiveMoneyAcctRegister(String ids) throws BizAppException {
		try {
	        List<SubcollBillInfo> billList= subcollBillInfoDao.getSubcollBillInfoByIds(ids);
	        for(SubcollBillInfo bill:billList){
	            bill.setGathDate(DateTimeUtil.getWorkdayString());
	            bill.setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
	            bill.setStatus(SubcollCodeConst.SUB_STAUTS_ONWAY);
				bill.setOperStatus(StatusUtils.handleStatus("ReceiveMoneyController", "submitAccountRegister", null, bill.getOperStatus()));
	           subcollBillInfoDao.modifySubcollBillInfo(bill);
	        }
	       } catch (Exception e) {
	           e.printStackTrace();
	           throw new BizAppException("提交收款登记失败");
	       }
	}

	 	/**
		 * 提交退票登记
		 * 方法说明：此方法用于纸票和电票的提交退票操作
		 * 1、纸票和电票提交退票之后票据的操作状态相同，但是前置状态不同，因为电票有报文回复处理的操作，状态会比纸票多
		 * 2、提交之后会对登记中心票据进行解锁操作，方便在申请岗把票查到可以进行继续操作
		 * @param ids 托收明细主键
		 * @throws BizAppException
		 */
	public void submitRevokeBillRegister(String ids) throws BizAppException {
		try {
	        List<SubcollBillInfo> billList= subcollBillInfoDao.getSubcollBillInfoByIds(ids);
	        IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();
	        for(SubcollBillInfo bill:billList){
	            bill.setGathDate(DateTimeUtil.getWorkdayString());
	            bill.setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
	            bill.setCollDate("");
	            bill.setStatus(SubcollCodeConst.SUB_STAUTS_REFUSEBACK);
	            bill.setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());// 销记柜员
	            bill.setGathAuditOperNo(ResourceUtil.getSessionLoginfo().getUserNo());// 销记复核柜员
	            bill.setGathDate(DateTimeUtil.getWorkdayString());// 销记日期
	            if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getBillClass())){
	            	bill.setPosition(SubcollCodeConst.BACKINSTORE);
					bill.setOperStatus(StatusUtils.handleStatus("ReceiveMoneyController", "revokeBill", null, bill.getOperStatus()));
	            }else{
	            	bill.setPosition(SubcollCodeConst.BACKINSTORE_OVER);
					bill.setOperStatus(StatusUtils.handleStatusNoCheck("ReceiveMoneyController", "revokeBill", null));
	            }
	            subcollBillInfoDao.modifySubcollBillInfo(bill);
	            rcService.unLock(bill.getRgctId());//对登记中心票据进行解锁操作,票据在申请岗可以查到
//	            rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
//	            try {
//					//撤消提示付款背书前,对登记中心票据进行解锁操作
//					//this.reCollectService.unlock(sub.getRgctId(), "", "");
//	            	rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
//					if("2".equals(bill.getIsOverdue())){
//						rcService.cancelOverdue(rgctBill);
//					}else{
//						rcService.cancelPayEndorse(rgctBill);
//					}	
//					//继续锁票
//					/*rgctBill = rcService.getRgctBillById(bill.getRgctId());
//					String rgctId = rgctBill.getHist().getRgctId();
//					rcService.lock(rgctId);*/
//					
//				}catch(Exception e) {
//					e.printStackTrace();
//					throw new BizAppException(e.getMessage());
//				}
	        }
	       } catch (Exception e) {
	           e.printStackTrace();
	           throw new BizAppException("提交退票登记失败");
	       
	       }
	}

	/**
	 * 撤销发托
	 * @param subcollIds 托收明细主键
	 * @throws BizAppException
	 */
	public void RevokeBillRegister(String subcollIds) throws BizAppException {
		try {
			Object[] operStatus = StatusUtils.queryStatus("SubcollApplyController", "revokeBillForApply", null);
	        List<SubcollBillInfo> billList= subcollBillInfoDao.getSubcollBillInfoByIds(subcollIds);
	        for(SubcollBillInfo bill:billList){
	            bill.setGathDate(DateTimeUtil.getWorkdayString());
	            bill.setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
	            bill.setPosition(SubcollCodeConst.APPLY);
				bill.setOperStatus(StatusUtils.handleStatus("SubcollApplyController", "revokeBillForApply", null, bill.getOperStatus()));
	            subcollBillInfoDao.modifySubcollBillInfo(bill);
	        }
	       } catch (Exception e) {
	           e.printStackTrace();
	           throw new BizAppException("撤销发托失败");
	       
	       }
	}

	/**
	 * 提交出库
	 * @param subcollmxIds 
	 * @param query 
	 * @throws BizAppException
	 */
	public void applyWarehouse(String subcollmxIds,SubcollQueryCondition query) throws BizAppException {
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IBranchService ibs = ServiceFactory.getBranchService();
		Map<String,RgctBill> rgctmap = new HashMap<String,RgctBill>();
		IDB session = DBFactory.getDB();
		String rgctids = "";
		Branch branch = ibs.getBranch(user.getBranchNo());
		IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();
		try {
			Object[] operStatus = StatusUtils.queryStatus("SubcollStorageController", "apply", null);
	        List<SubcollBillInfo> billList= subcollBillInfoDao.getSubcollBillInfoByIds(subcollmxIds);
	        if(billList.isEmpty()){
	        	 throw new BizAppException(ISysErrorNo.ERR_DBERR,"票据不支持该处理");
	        }
	        for (int i = 0; i < billList.size(); i++) {
	        	rgctids = rgctids + billList.get(i).getRgctId() + ",";
			}
	        List<RgctBill> rgctlist = rcService.getRgctBillList(rgctids.substring(0,rgctids.length()-1));
	        for (int i = 0; i < rgctlist.size(); i++) {
	        	rgctmap.put(rgctlist.get(i).getInfo().getId(), rgctlist.get(i));
			}
	        SubcollApplyInfo applyInfo = subcollApplyInfoDao.getSubcollApplyInfobyid(billList.get(0).getSubcollId());
	        for(SubcollBillInfo bill:billList){
	        	session.beginTransaction();
	            bill.setGathDate(DateTimeUtil.getWorkdayString());
	            bill.setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
	            bill.setCollDate(DateTimeUtil.getWorkdayString());
	            RgctBill rgctBill =rgctmap.get(bill.getRgctId());
	            if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getBillClass())){
	            	bill.setPosition(SubcollCodeConst.ELEC_WAIT_033_FOR_APPLY);
	            	bill.setOperStatus(SubcollCodeConst.ACPT_SUB_COLL_LIST_324);//电票发托中其是中间过度状态，无需再状态机进行配置
	            }else {
					bill.setStatus(SubcollCodeConst.SUB_STAUTS_ONWAY);
					bill.setCurStatus(RcConstants.CUR_STATUS_SUB_ON_PASSAGE);
					bill.setPosition(SubcollCodeConst.REGISTER);
					bill.setOperStatus(StatusUtils.handleStatus("SubcollStorageController", "apply", null, bill.getOperStatus()));
					//纸票更新来源清单的发托日期
					this.updateSourceCollDate(bill, true, rgctBill.getHist());
				}
	            subcollBillInfoDao.modifySubcollBillInfo(bill);
	            query.setOverdueReason(applyInfo.getOverduereason());
	          //调用初始化数据
				this.initRgctHist(rgctBill, branch, bill, query);
				//判断是否逾期
				if(SubcollCodeConst.IS_OVERDUE_TWO.equals(bill.getIsOverdue())){//逾期发托
					rgctBill.getHist().setOverdueRs(applyInfo.getOverduereason());
					rcService.regOverdue(rgctBill);;
				}else {
					rcService.payEndorse(rgctBill);
				}
				session.endTransaction();
	        }
	       } catch (Exception e) {
	    	   try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	           e.printStackTrace();
	           throw new BizAppException("提交出库失败");
	       
	       }
	}

	/**
	 * 撤销出库
	 * @param subcollIds 
	 * @param billClass 
	 * @throws BizAppException
	 */
	public void revokeWarehouse(String subcollIds,String billClass) throws BizAppException {
		IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();
		String rgctids = "";
		Map<String,RgctBill> rgctmap = new HashMap<String, RgctBill>();
		IDB session = DBFactory.getDB();
		try {
			List<SubcollBillInfo> billList = null;
			if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
//				Object[] operStatus = StatusUtils.queryStatus("SubcollStorageController", "revokeSendBillForApply", null);
		        billList= subcollBillInfoDao.getSubcollBillInfoByIds(subcollIds);
			}else{
				billList= subcollBillInfoDao.getSubcollBillInfoByIds(subcollIds);
			}
			if (billList.isEmpty()) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"票据不支持该处理");
			}
			for (int i = 0; i < billList.size(); i++) {
				rgctids = rgctids + billList.get(i).getRgctId() + ",";
			}
			List<RgctBill> rgctlist = rcService.getRgctBillList(rgctids.substring(0,rgctids.length()-1));
			for (int i = 0; i < rgctlist.size(); i++) {
				rgctmap.put(rgctlist.get(i).getInfo().getId(),rgctlist.get(i));
			}
	        for(SubcollBillInfo bill:billList){
	        	session.beginTransaction();
	            bill.setGathDate(DateTimeUtil.getWorkdayString());
	            bill.setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
	            bill.setStatus(null);
	            bill.setCollDate(null);
	            bill.setPosition(SubcollCodeConst.OUTSTORE);
	            bill.setCurStatus(null);
	            RgctBill rgctBill =rgctmap.get(bill.getRgctId());
	            if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass)){
	            	bill.setOperStatus(StatusUtils.handleStatus("SubcollStorageController", "revokeSendBillForApply", null, bill.getOperStatus()));
	            }else{
	            	bill.setOperStatus(SubcollCodeConst.ACPT_SUB_COLL_LIST_324);
	            }
	            	subcollBillInfoDao.modifySubcollBillInfo(bill);
	 	        //置空托收来源票据的发托日期
	 	        this.updateSourceCollDate(bill, true, rgctBill.getHist());
	 	        //撤消提示付款背书前,对登记中心票据进行解锁操作
	            rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
				if("2".equals(bill.getIsOverdue())){
					rcService.cancelOverdue(rgctBill);
				}else{
					rcService.cancelPayEndorse(rgctBill);
				}	
				//继续锁票
				rgctBill = rgctmap.get(bill.getRgctId());
				String rgctId = rgctBill.getHist().getRgctId();
				rcService.lock(rgctId);
				session.endTransaction();
	        }
	       } catch (Exception e) {
	    	   try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	           e.printStackTrace();
	           throw new BizAppException("撤销出库失败");
	       
	       }
	}

	/**
	 * 删除票据信息
	 * @param subcollmxIds 
	 * @throws BizAppException
	 */
	public void delBillForApply(String subcollmxIds) throws BizAppException {
		IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();	
		try {
			Object[] operStatus = StatusUtils.queryStatus("SubcollApplyController", "delBillForApply", null);
		    List<SubcollBillInfo> billList= subcollBillInfoDao.getSubcollBillInfoByIds(subcollmxIds);
		    for(SubcollBillInfo bill:billList){
		        bill.setGathDate(DateTimeUtil.getWorkdayString());
		        bill.setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
		        RgctBill rgctBill =rcService.getRgctBillById(bill.getRgctId());
				bill.setOperStatus(StatusUtils.handleStatus("SubcollApplyController", "delBillForApply", null, bill.getOperStatus()));
		        subcollBillInfoDao.modifySubcollBillInfo(bill);
		        //解锁
		        String rgctId = rgctBill.getHist().getRgctId();
			    rcService.unLock(rgctId);
		       }
	       } catch (Exception e) {
	           e.printStackTrace();
	           throw new BizAppException("删除票据信息失败");
	       
	       }
		
	}

	/**
	 * 申请岗先保存后提交
	 * @param subcollmxIds
	 * @return
	 * @throws BizAppException
	 */
	public void lastApply(String[]subcollmxIds)throws BizAppException{
		try {	
			for (int i = 0; i < subcollmxIds.length; i++) {
				String subcollmxId = subcollmxIds[i];
	        	List<SubcollBillInfo> bill= subcollBillInfoDao.getSubcollBillInfoByIds(subcollmxId);
	            bill.get(0).setGathDate(DateTimeUtil.getWorkdayString());
	            bill.get(0).setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
	            bill.get(0).setPosition(SubcollCodeConst.OUTSTORE);
				bill.get(0).setOperStatus(StatusUtils.handleStatus("SubcollApplyController", "applyBill", null, bill.get(0).getOperStatus()));
	            subcollBillInfoDao.modifySubcollBillInfo(bill.get(0));
			}
	       } catch (Exception e) {
	           e.printStackTrace();
	           throw new BizAppException("提交票据信息失败");
	       }
	}

	/**
	 * 纸票提交修改岗票据
	 * @param subcollIds 托收明细主键
	 * @throws BizAppException
	 */
	public void applyBill(String subcollIds) throws BizAppException {
		try {
			Object[] operStatus = StatusUtils.queryStatus("SubcollApplyController", "applyBill", null);
	        List<SubcollBillInfo> billList= subcollBillInfoDao.getSubcollBillInfoByIds(subcollIds);
	        for(SubcollBillInfo bill:billList){
	            bill.setGathDate(DateTimeUtil.getWorkdayString());
	            bill.setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
	            bill.setPosition(SubcollCodeConst.OUTSTORE);
				bill.setOperStatus(StatusUtils.handleStatus("SubcollApplyController", "applyBill", null, bill.getOperStatus()));
	            subcollBillInfoDao.modifySubcollBillInfo(bill);
	        }
	       } catch (Exception e) {
	           e.printStackTrace();
	           throw new BizAppException("提交票据信息失败");
	       }
	}
	
	/**
	 * 电票提交修改岗票据
	 * @param subcollmxId 托收明细主键
	 * @throws BizAppException
	 */
	public void elecApplyBill(String subcollmxId, SubcollQueryCondition query) throws BizAppException {
		if(query == null){
			query = new SubcollQueryCondition();
		}
		try {
			Object[] operStatus = StatusUtils.queryStatus("SubcollApplyController", "applyBill", null);
	        List<SubcollBillInfo> billList= subcollBillInfoDao.getSubcollBillInfoByIds(subcollmxId);
	        for(SubcollBillInfo bill:billList){
	            bill.setGathDate(DateTimeUtil.getWorkdayString());
	            bill.setGathOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
	            bill.setPosition(SubcollCodeConst.OUTSTORE);
				bill.setOperStatus(StatusUtils.handleStatus("SubcollApplyController", "applyBill", null, bill.getOperStatus()));
				bill.setIsOnline(query.getIsOnline());
				bill.setIsOverdue(query.getIsOverdue());
				bill.setRemark(query.getRemark());
	            subcollBillInfoDao.modifySubcollBillInfo(bill);
	        }
	        SubcollApplyInfo applyInfo = subcollApplyInfoDao.getSubcollApplyInfobyid(query.getSubcollId());//根据subcollId进行查询该批次
	        Boolean bool = SubcollCodeConst.IS_OVERDUE_ONE.equals(query.getIsOverdue());//判断是否逾期，如果bool为true，则未逾期，否则逾期
	        //如果修改为未逾期，则把逾期原因置空,否则写入前台输入的原因
			applyInfo.setOverduereason(bool?null:query.getOverdueReason());
			subcollApplyInfoDao.modifySubcollApplyInfo(applyInfo);
	       } catch (Exception e) {
	           e.printStackTrace();
	           throw new BizAppException("提交票据信息失败");
	       }
	}

	/**
	 * 从原始清单中抽取发托清单信息
	 * @param billSource
	 * @param hist
	 * @param subcoll
	 */
	private void reSetOriginateInfo(String billSource,RgctBillHist hist,SubcollBillInfo subcoll) throws BizAppException{
		IDiscService discService = ServiceFactory.getDiscService();
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		ICollateralizationService saveService = ServiceFactory.getCollateralizationService();
		String billTrack = hist.getBillTrack();
		//仅贴现、转贴买入时设置原始产品
		if(SubcollCodeConst.isDiscount(billSource, billTrack)){
			DiscBillInfo disc = new DiscBillInfo();
			try {
				disc = discService.getDiscBillInfoByRgctId(hist.getRgctId());
			} catch (SQLException e) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"获得贴现票据信息失败");
			}
			if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(disc.getBillClass()) && !ResourceUtil.getSessionLoginfo().getBranchNo().equals(disc.getBranchNo())){
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"票号:"+disc.getBillNo()+"当前操作机构["+ResourceUtil.getSessionLoginfo().getBranchNo()+"]和原始清单的账务机构["+disc.getBranchNo()+"]不一致!");
			}
			subcoll.setSourceProjectNo(disc.getProductId());
			subcoll.setDiscDt(disc.getAccountDate());
			subcoll.setRedeemOpenDt(disc.getRedeemOpenDt());
			subcoll.setRedeemEndDt(disc.getRedeemEndDate());
			subcoll.setInterestDays(disc.getInterestDays());
			subcoll.setRate(disc.getRate());
			subcoll.setPayMoney(disc.getPayMoney());
			subcoll.setInterest(disc.getInterest());
			subcoll.setFileNo(disc.getFileNo());
			subcoll.setBillBeforeOwner(disc.getBillBeforeOwner());
			subcoll.setProdAttr(disc.getBankProdNo());
		}//代保管、票据池、质押来源时需要设置 票据管家客户号
		else if("3".equals(billSource)||"4".equals(billSource)){
			SaveBillInfo save = new SaveBillInfo();
			SaveApplyInfo saveApplyInfo = new SaveApplyInfo();
			try {
				save = saveService.getSaveBillInfoByRgctId(hist.getRgctId());
				saveApplyInfo = saveService.getSaveApplyInfo(save.getSaveId(),new SaveSearchBean());
			} catch (SQLException e) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"获得质押票据信息失败");
			}
			if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(save.getBillClass()) && !ResourceUtil.getSessionLoginfo().getBranchNo().equals(save.getBranchNo())){
				throw new BizAppException("票号:"+save.getBillNo()+"当前操作机构["+ResourceUtil.getSessionLoginfo().getBranchNo()+"]和原始清单的账务机构["+save.getBranchNo()+"]不一致!");
			}
			subcoll.setBelongCustNo(save.getCustNo()); 
            subcoll.setCustName(save.getCustName());
            subcoll.setImpawnBailAccount(saveApplyInfo.getImpawnBailAccount());
            subcoll.setCollPayeeAcct(saveApplyInfo.getImpawnBailAccount());
		}else if(SubcollCodeConst.isRpdiscount(billSource, billTrack)){
			RebuyBillInfo rebuy = new RebuyBillInfo();
			RebuyApplyInfo apply = new RebuyApplyInfo();
			try {
				rebuy = rebuyService.getRebuyBillInfoByRgctId(hist.getRgctId());
				apply = rebuyService.getRebuyApplyInfo(rebuy.getRebuyId());
			} catch (SQLException e) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"获得转入票据信息失败");
			}
			if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(rebuy.getBillClass()) && !ResourceUtil.getSessionLoginfo().getBranchNo().equals(rebuy.getBranchNo())){
				throw new BizAppException("票号:"+rebuy.getBillNo()+"当前操作机构["+ResourceUtil.getSessionLoginfo().getBranchNo()+"]和原始清单的账务机构["+rebuy.getBranchNo()+"]不一致!");
			}
			subcoll.setSourceProjectNo(apply.getProdNo());
			subcoll.setDiscDt(rebuy.getAccountDate());
			subcoll.setRedeemOpenDt(rebuy.getResaleStaDt());
			subcoll.setRedeemEndDt(rebuy.getResaleDueDt());
			subcoll.setInterestDays(rebuy.getInterestDays());
			subcoll.setRate(rebuy.getRate());
			subcoll.setPayMoney(rebuy.getPayMoney());
			subcoll.setInterest(rebuy.getInterest());
			subcoll.setProdAttr(apply.getProdAttr());
		}
	}
	
	/**
	 * 根据票据来源设置 托收收款人相关信息
	 * @param subcollBillInfo
	 * @throws BizAppException
	 */
	public void reSetCollPayeeInfo(SubcollBillInfo subcollBillInfo) throws BizAppException{		
		//(票据池)
		if(SubcollCodeConst.SUB_FOUR.equals(subcollBillInfo.getBillSource())){
			ISignProdService sign = ServiceFactory.getSignProdService();
			SignProd signProd =sign.getSignProdInfoByCust(IConstants.BILLPOOL_SINGPROD, subcollBillInfo.getBelongCustNo());
			if(signProd==null){
				throw new BizAppException("客户签约信息不存在");
			}
			IBranchService bs = ServiceFactory.getBranchService();
			Branch branch = bs.getBranch(signProd.getSignOrg());
			subcollBillInfo.setCollPayeeBank(branch.getBranchName());
			subcollBillInfo.setCollPayeeBankNo(branch.getBankNo());
			subcollBillInfo.setCollPayee(signProd.getCustName());
			if(!SubcollCodeConst.SIGN_PROD_CMS_OPEN.equals(signProd.getCmsFlag())){
				throw new BizAppException("客户未开通票据池衍生业务");
			}
			subcollBillInfo.setCollPayeeAcct(signProd.getMarginAccount());		
		}//质押类型由前台手工录入
		else if(SubcollCodeConst.SUB_THREE.equals(subcollBillInfo.getBillSource())){	
			
		}//贴现、转入
		else{
			//2012-09-10以上类型回款入账账号应该为空，确认不显示!
			//2012-09-12托收试图需显示(前台JSP不显示)
			//InnerAccount acct = innerAccountDao.queryContzAccount(ResourceUtil.getSessionLoginfo().getBrchNo());
			subcollBillInfo.setCollPayee(ResourceUtil.getSessionLoginfo().getBranchName());
			//sub.setCollPayeeAcct(acct.getAccountNo());	
			subcollBillInfo.setCollPayeeBank(ResourceUtil.getSessionLoginfo().getBranchName());
			subcollBillInfo.setCollPayeeBankNo(ResourceUtil.getSessionLoginfo().getBrchBankNo());
		}
	}

		/**
	    * 更新票据来源清单信息
	    * @param bill
	    * @throws BizAppException
	    */
	    private void updateInstrIncome(SubcollBillInfo bill) throws BizAppException{
	        IRcPresentationService rcpService=RcServiceFactory.getRcPresentationService();
	        RebuyBillInfoDao rebuyDao = new RebuyBillInfoDao();
	        DiscBillInfoDao discDao = new DiscBillInfoDao();
	        SaveBillInfoDao saveDao = new SaveBillInfoDao();
	        BuybackBillInfoDao billDao = new BuybackBillInfoDao();
			String billSource = bill.getBillSource();
			RgctBill rgctBill = new RgctBill();
			try {
				rgctBill = rcpService.getRgctBillById(bill.getRgctId());
			} catch (BizAppException e) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"获得RgctBill信息失败");
			}
			String billTrack = rgctBill.getHist().getBillTrack();
			if (SubcollCodeConst.isRpdiscount(billSource, billTrack)) {// 转入时更新转入清单
				RebuyBillInfo rebuyBill = new RebuyBillInfo();
				try {
					rebuyBill = rebuyDao.getRebuyBillInfo(bill.getBillId());
					rebuyBill.setGaleDate(bill.getGathDate());//票款收回日期
					rebuyBill.setGathType(RcConstants.SETTLEMENT);//票款收回类型：1正常(默认)2转卖3托收(结清)4赎回
					rebuyBill.setTransId(bill.getSubcollmxId());//流转ID
					rebuyBill.setTransType(RcConstants.SETTLEMENT);//流转类型
					rebuyBill.setCurStatus(RcConstants.CUR_STATUS_SUB_COLL_BACK);//当前状态
					rebuyDao.modifyRebuyBillInfo(rebuyBill);
				} catch (SQLException e) {
					throw new BizAppException(ISysErrorNo.ERR_DBERR,"更新转入来源清单信息失败");
				}
			} else if (SubcollCodeConst.isDiscount(billSource, billTrack)) {// 贴现
				DiscBillInfo discBill = new DiscBillInfo();
				try {
					discBill = discDao.getDiscBillInfo(bill.getBillId());
					discBill.setGathMneyDate(bill.getGathDate());//收回票款日
					discBill.setGathMneyType(RcConstants.SETTLEMENT); //收回票款类型
					discBill.setTransId(bill.getSubcollmxId());//流转ID
					discBill.setTransType(RcConstants.SETTLEMENT);//流转类型
					discBill.setCurStatus(RcConstants.CUR_STATUS_SUB_COLL_BACK);//当前状态
					discDao.modifyDiscBillInfo(discBill);
				} catch (SQLException e) {
					throw new BizAppException(ISysErrorNo.ERR_DBERR,"更新贴现来源清单信息失败");
				}
			} else if(SubcollCodeConst.isSaveBill(billSource)){
				SaveBillInfo saveBill = new SaveBillInfo();
				try {
					saveBill = saveDao.getSaveBillInfo(bill.getBillId());
					saveBill.setGathType(RcConstants.GATH_TYPE_SUB);
					saveBill.setGathDate(bill.getGathDate());
					saveDao.modifySaveBillInfo(saveBill);
				} catch (SQLException e) {
					throw new BizAppException(ISysErrorNo.ERR_DBERR,"更新质押来源清单信息失败");
				}
			}else if(SubcollCodeConst.isBuybackBill(billSource)){
				try {
					BuybackBillInfo buybackBill = new BuybackBillInfo();
					buybackBill=billDao.getBuybackBillInfoForId(bill.getBillId());
					buybackBill.setCurStatus(RcConstants.CUR_STATUS_SUB_COLL_BACK);//当前状态
					billDao.modifyBuybackBillInfo(buybackBill);
				} catch (SQLException e) {
					throw new BizAppException(ISysErrorNo.ERR_DBERR,"更新回购来源清单信息失败");
				}
			}
		}
	
	/**
	 * 更新托收来源票据的发托日期
	 * 纸票 撤销发托出库置空， 实物出库置为托收日期
	 * 电票 撤销提示付款背书的033回调置空, 提示付款背书033置为托收日期
	 * @param bill
	 * @param flag
	 * @param hist
	 * @throws BizAppException
	 */
	public void updateSourceCollDate(SubcollBillInfo bill, boolean flag, RgctBillHist hist) throws BizAppException{
		//flag true 记账 false 撤销记账
		String date = flag ? DateTimeUtil.getWorkdayString() : null;
		String billSource = bill.getBillSource();
		String billTrack = hist.getBillTrack();
		RebuyBillInfoDao rebuyDao = new RebuyBillInfoDao();
        DiscBillInfoDao discDao = new DiscBillInfoDao();
        SaveBillInfoDao saveDao = new SaveBillInfoDao();
		if(SubcollCodeConst.isDiscount(billSource, billTrack)){//贴现
			DiscBillInfo discBill = new DiscBillInfo();
			try {
				discBill = discDao.getDiscBillInfo(bill.getBillId());
				if(flag){
					discBill.setCollDt(bill.getCollDate());//发托日期
					discBill.setTransId(bill.getSubcollmxId());//流转ID
					discBill.setTransType(RcConstants.SUB_ON_WAY);//流转类型
					discBill.setCurStatus(RcConstants.CUR_STATUS_SUB_ON_PASSAGE);//当前状态
				}else{
					discBill.setCollDt(null);//收回日期
					if(discBill.getBuybackId()!=null){//最后一手回购到期的场景
						discBill.setTransId(discBill.getBuybackId());//票据流转id
						discBill.setTransType(RcConstants.BUYBACK);//流转类型
					}else{
						discBill.setTransId(null);
						discBill.setTransType(RcConstants.NORMAL);
					}
					discBill.setCurStatus(RcConstants.CUR_STATUS_DISC);//当前状态
				}
				discDao.modifyDiscBillInfo(discBill);
			} catch (SQLException e) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"贴现来源票据信息更新失败");
			}
		}else if(SubcollCodeConst.isRpdiscount(billSource, billTrack)){//转入时更新转入清单
			RebuyBillInfo rebuyBill = new RebuyBillInfo();
			try {
				rebuyBill = rebuyDao.getRebuyBillInfo(bill.getBillId());
				rebuyBill.setCollDate(date);
				if(flag){
					rebuyBill.setCollDate(bill.getCollDate());//发托日期
					rebuyBill.setTransId(bill.getSubcollmxId());//流转ID
					rebuyBill.setTransType(RcConstants.SUB_ON_WAY);//流转类型
					rebuyBill.setCurStatus(RcConstants.CUR_STATUS_SUB_ON_PASSAGE);//当前状态
				}else{
					rebuyBill.setCollDate(null);//收回日期
					if(rebuyBill.getBuybackId()!=null){
						rebuyBill.setTransId(rebuyBill.getBuybackId());
						rebuyBill.setTransType(RcConstants.BUYBACK);
					}else {
						rebuyBill.setTransId(null);
						rebuyBill.setTransType(RcConstants.NORMAL);
					}
					rebuyBill.setCurStatus(RcConstants.CUR_STATUS_REBUY);//当前状态
				}
				rebuyDao.modifyRebuyBillInfo(rebuyBill);
			} catch (SQLException e) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"转入来源票据信息更新失败");
			}
		}else if(SubcollCodeConst.isSaveBill(billSource)){
			SaveBillInfo save = new SaveBillInfo();
			try {
				save = saveDao.getSaveBillInfo(bill.getBillId());
				save.setCollDate(date);
				saveDao.modifySaveBillInfo(save);
			} catch (SQLException e) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"更新质押来源票据信息失败");
			}
		}
	}
	
	/**
	 * 初始化报文数据
	 * @param rgctBill
	 * @param banch
	 * @param bill
	 * @param query
	 * @return rgctBill
	 * @throws BizAppException
	 */
	public RgctBill initRgctHist(RgctBill rgctBill,Branch banch,SubcollBillInfo bill,SubcollQueryCondition query) throws BizAppException {
			//提示付款背书前,先对登记中心票据进行解锁操作
			RgctBillHist hist = rgctBill.getHist();
			hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			//进行发出托收记账处理
			//实物票托收出库记账，电票回调后记账
			if (IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getBillClass())) {
				if (CommUtils.isSelfBank(rgctBill.getInfo().getAcceptorBankNo()) && RcConstants.SETTLEMENTMARK_ONE.equals(bill.getIsOnline())) {
					throw new BizAppException(IErrorNo.BBSP0074);
				}
				hist.setFromSign(CommUtils.getSignerSign(hist.getHoldBankNo()));// 电子签名
				hist.setIsOnline(bill.getIsOnline());//线上线下
				hist.setFromRemark(query.getOverdueReason());//放入逾期原因
			}
			hist.setChannel("2"); // 发起来源 1网银 2银行
			hist.setEndorseDt(DateTimeUtil.getWorkdayString());// 获取当前营业日
			hist.setFromName(hist.getHoldCustName());
			hist.setFromBankNo(hist.getHoldBankNo());
			hist.setToBankNo(rgctBill.getInfo().getAcceptorBankNo());
			hist.setToAcctNo(rgctBill.getInfo().getAcceptorAcct());
			hist.setToName(rgctBill.getInfo().getAcceptor());
			hist.setToOrgcode(rgctBill.getInfo().getAcceptorOrgCode());
			hist.setFromOrgcode(banch.getOrgCode());
			hist.setFromRole(banch.getPartnerType());
			hist.setFromAcctNo(hist.getHoldAcctNo());
			return rgctBill;
	}


	/**
	 *  RC票据查看
	 * @param id
	 * @return rgctBill
	 * @throws BizAppException
	 */
	public RgctBill goDetail(String id) throws BizAppException {
		IRcPresentationService rcService=RcServiceFactory.getRcPresentationService();
		RgctBill rgctBill = rcService.getRgctBillById(id);
		return rgctBill;
	}
	
	/**
	 * 通过主键查询票据详细信息（单张票）
	 * @param subcollmxId
	 * @return subcollBillInfo
	 * @throws BizAppException
	 */
	public SubcollBillInfo getSubcollBillInfo(String subcollmxId) throws BizAppException{
		List<SubcollBillInfo> subcollBillInfo = new ArrayList<SubcollBillInfo>();
		try {
			subcollBillInfo= subcollBillInfoDao.getSubcollBillInfoByIds(subcollmxId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subcollBillInfo.get(0);
	}
	/**
	 * 根据条件查询批次信息
	 * @param page
	 * @param query
	 * @return List<SubcollApplyInfo>
	 * @throws BizAppException
	 */
	public List<SubcollApplyInfo> getSubcollApplyInfoListForCondition(Page page,SubcollQueryCondition query) throws BizAppException {
		initQueryCondition(query);
		OrderBean order=new OrderBean("subcollId",false);
		query.appendOrder(order);
		query.addProperty2TableObj("branchNo", "bill");
		query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
	    try {
			return subcollApplyInfoDao.getSubcollApplyInfoListForCondition(page, query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException("查询批次信息失败");
		}
	}
	
	/**根据清单id查询发拖请单*/
	public SubcollBillInfo getSubcollBillInfobyid(String id)throws BizAppException {
		SubcollBillInfoDao dao = new SubcollBillInfoDao();
		List<SubcollBillInfo> subcollBillInfo = new ArrayList<SubcollBillInfo>();
		try {
			subcollBillInfo = dao.getSubcollBillInfoByIds(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		return subcollBillInfo.get(0);
	}
	/**根据清单rgct_id查询发拖请单*/
	public SubcollBillInfo getSubcollBillInfobyrgctid(String rgctid)throws BizAppException {
		SubcollBillInfoDao dao = new SubcollBillInfoDao();
		SubcollBillInfo SubcollBillinfo=null;
		try {
			SubcollBillinfo = dao.getSubcollBillForRgctId(rgctid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		return SubcollBillinfo;
	}
	
	/**根据批次id查询批次*/
	public SubcollApplyInfo getSubcollApplyInfobyid(String id)throws BizAppException{
		SubcollApplyInfoDao dao = new SubcollApplyInfoDao();
		SubcollApplyInfo subapplyinfo=null;
		try {
			subapplyinfo = dao.getSubcollApplyInfobyid(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subapplyinfo;
	}
//	/**根据批次id查询清单list*/
//	public List<SubcollBillInfo> getSubcollBillInfolistbypiciid(String id)throws BizAppException{
//		List<SubcollBillInfo> list=null;
//		SubcollBillInfoDao dao = new SubcollBillInfoDao();
//		String operstatu = "BS310";
//		list = dao.getSubcollBillForRgctIdAndOperStatus(id,operstatu);
//		return list;
//	}
	/**根据票据类型的标识获得字符串*/
	@Override
	public String getBillClassStringbybillclass(String billclass)
			throws BizAppException {
		SubcollBillInfoDao dao = new SubcollBillInfoDao();
		String keyNo="K_BILL_CLASS";
		String BillClassstring = dao.getBillSourceStringbyItemCode(billclass,keyNo);
		return BillClassstring;
	}
	/**根据票据品种的标识获得字符串*/
	@Override
	public String getBillTypeStringbybilltype(String billclass)
			throws BizAppException {
		SubcollBillInfoDao dao = new SubcollBillInfoDao();
		String keyNo="K_BILL_TYPE";
		String BillTypestring = dao.getBillSourceStringbyItemCode(billclass,keyNo);
		return BillTypestring;
	}
	/**票据来源字符串  1:代保管，2票据池,3质押,4贴现；5转入，6理财，7分行带总行代保管 */
	@Override
	public String getBillSourceStringbybillsource(String billsource)
			throws BizAppException {
		SubcollBillInfoDao dao = new SubcollBillInfoDao();
		String keyNo="K_BILL_SOURCE";
		String BillSourcestring = dao.getBillSourceStringbyItemCode(billsource,keyNo);
		return BillSourcestring;
	}
	
//	/**根据清单ids(逗号分隔开的清单id)和OPER_STATUS=BS310查出清单list  （该list集合是托收增加保存完（OPER_STATUS=BS310）之后的清单）*/
//	@Override
//	public List<SubcollBillInfo> getSubcollBillInfolistbyqingdanids(String ids)
//			throws BizAppException {
//		SubcollBillInfoDao dao = new SubcollBillInfoDao();
//		List<SubcollBillInfo>  list= dao.getSubcollBillInfolistbyqingdanids(ids);
//		return list;
//	}
	
	/**根据批次号查询出批次id*/
	public String getpiciidbypicihao(String picihao)throws BizAppException {
		SubcollApplyInfoDao dao = new SubcollApplyInfoDao();
		String id = dao.getpiciidbypicihao(picihao);
		return id;
	}
	
	/**
	 * 1.托收打印时判断 票据是否保存所用
	 * 2.根据ids字符串和前置状态（保存状态BS310）查询SubcollBillInfo集合（集合不为空说明这批ids中有保存完的票）*/
	public List<SubcollBillInfo> getIssaveListbyidsandoperstatus(String ids,String operstatus)
			throws BizAppException {
		SubcollBillInfoDao dao = new SubcollBillInfoDao();
		List<SubcollBillInfo> list=null;
		try {
			list = dao.getIssaveListbyidsandoperstatus(ids, operstatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 1.托收打印时判断 票据是否保存所用
	 * 2.根据主键subcollmx_id字符串和前置状态（保存状态BS310）查询SubcollBillInfo集合（集合不为空说明这批subcollmxid中有保存完的票）*/
	public List<SubcollBillInfo> getIssaveListbysubcollmxidandoperstatus(String subcollmxid,String operstatus)
			throws BizAppException {
		SubcollBillInfoDao dao = new SubcollBillInfoDao();
		List<SubcollBillInfo> list=null;
		try {
			list = dao.getIssaveListbysubcollmxidandoperstatus(subcollmxid, operstatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}