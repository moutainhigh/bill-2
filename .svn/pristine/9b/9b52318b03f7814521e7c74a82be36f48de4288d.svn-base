package com.herongtech.console.service.busiservice.repurchasedcollateralization;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.domain.get.bean.GetApplyInfo;
import com.herongtech.console.domain.get.bean.GetBillInfo;
import com.herongtech.console.domain.get.bean.GetSearchBean;
import com.herongtech.console.domain.get.dao.GetApplyInfoDao;
import com.herongtech.console.domain.get.dao.GetBillInfoDao;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.dao.SaveBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.collateralization.ICollateralizationService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.RepurCollateCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.sysconstant.ISysErrorNo;

public class RepurCollateService implements IRepurCollateService {
	public static final String HUNDSUN_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	private GetApplyInfoDao getApplyDao = new GetApplyInfoDao();
	private GetBillInfoDao getBillDao = new GetBillInfoDao();

	/**
	 * 初始化查询条件
	 * 
	 * @param query
	 */
	private void initQueryCondition(GetSearchBean query) {
		query.setDfaultSrchTbAliasName("bill");
		query.addSpecialOpertion("endDay", BaseSearchBean.LESS_AND_EQUAL);
		query.addSqlPropretyMapping("endDay", "dueDt");
		query.addSpecialOpertion("startDay", BaseSearchBean.LESS_AND_EQUAL);
		query.addSqlPropretyMapping("startDay", "dueDt");

		query.addSpecialOpertion("opers", BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		query.addSqlPropretyMapping("operStatus", "operStatus");
		query.addSqlPropretyMapping("custNo", "custNo");
	}

	/**
	 * 批次列表(申请岗)
	 */
	public List<GetApplyInfo> getGetApplyListForApply(Page page,
			GetSearchBean query) throws Exception {
		query.setDfaultSrchTbAliasName("ta");
		query.addSqlPropretyMapping("custNo", "custNo");
		query.addSqlPropretyMapping("operStatus", "applyStatus");
		OrderBean order = new OrderBean("getId", false);
		query.appendOrder(order);
		return getApplyDao.getGetApplyListForApply(page, query);
	}

	/**
	 * 批次列表(审核 入库岗)
	 */
	public List<GetApplyInfo> getGetApplyListBySearchBean(Page page,
			GetSearchBean query) throws SQLException {
		if (!"".equals(query.getBatchNo()) && query.getBatchNo() != null) {
			query.setBatchNo("%" + query.getBatchNo() + "%");
		}
		initQueryCondition(query);
		query.setDfaultSrchTbAliasName("bill");
		query.addProperty2TableObj("getId", "apply");
		query.addProperty2TableObj("batchClass", "apply");
		query.addProperty2TableObj("batchNo", "apply");
		query.addProperty2TableObj("applyStatus", "apply");
		query.addSpecialOpertion("batchNo", BaseSearchBean.LIKE);
		query.addSqlPropretyMapping("batchNo", "batchNo");
		OrderBean order = new OrderBean("getId", false);
		query.appendOrder(order);
		return getApplyDao.getGetApplyListBySearchBean(page, query);
	}

	/**
	 * 清单列表(申请 审核 入库)
	 */
	public List<GetBillInfo> getGetBillListBySearchBean(Page page,
			GetSearchBean query) throws Exception {
		initQueryCondition(query);
		query.setDfaultSrchTbAliasName("bill");
		query.addProperty2TableObj("getmxId", "bill");
		OrderBean order = new OrderBean("getmxId", false);
		query.appendOrder(order);
		return getBillDao.getGetBillListBySearchBean(page, query);
	}

	/**
	 * 得到批次详情
	 * 
	 * @param getId
	 * @return
	 * @throws SQLException
	 */
	public GetApplyInfo getGetApplyInfo(String getId, GetSearchBean query)
			throws SQLException {
		return getApplyDao.getGetApplyInfo(getId, query);
	}

	/**
	 * 得到票据详情
	 * 
	 * @param getId
	 * @return
	 * @throws SQLException
	 */
	public List<GetBillInfo> getGetBillInfo(Page page, GetSearchBean query)
			throws Exception {
		initQueryCondition(query);
		OrderBean order = new OrderBean("getmxId", false);
		query.appendOrder(order);
		return getBillDao.getGetBillListBySearchBean(page, query);
	}

	/**
	 * 添加批次
	 */
	public void addGetApplyInfo(GetApplyInfo getApplyInfo)
			throws BizAppException {
		getApplyInfo.setCreateDt(DateTimeUtil.getWorkdayString());
		getApplyInfo.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		getApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
		try {
			if (getApplyDao.addGetApplyInfo(getApplyInfo) != 1)
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"增加GetApplyInfo失败");
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 修改批次
	 */
	public void modiGetApplyInfo(GetApplyInfo getApplyInfo)
			throws BizAppException {
		try {
			if (getApplyDao.modifyGetApplyInfo(getApplyInfo) != 1)
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"修改GetApplyInfo失败");
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 修改批次 批次状态修改
	 */
	public void modifyGetApplyInfo(GetApplyInfo getApplyInfo)
			throws BizAppException {
		getApplyInfo.setCreateDt(DateTimeUtil.getWorkdayString());
		getApplyInfo.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		getApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		try {
			if (getApplyDao.modifyGetApplyInfo(getApplyInfo) != 1)
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"修改GetApplyInfo失败");
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 删除批次
	 * 
	 * @param
	 * @return
	 * @throws SQLException
	 */
	public boolean delApplyInfoForGetIds(Page page, String[] getIds)
			throws Exception {
		GetSearchBean query = new GetSearchBean();
		boolean bool = true;
		for (int i = 0; i < getIds.length; i++) {
			query.setGetId(getIds[i]);
			List<GetBillInfo> billList = getGetBillInfo(page, query);
			if (billList == null || billList.size() <= 0) {
				GetApplyInfo getApplyInfo = new GetApplyInfo();
				getApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
				getApplyDao.deleteGetApplyInfo(getApplyInfo, getIds[i]);
			} else {
				bool = false;
			}
		}
		return bool;
	}

	/**
	 * 获取清单详情
	 */
	public GetBillInfo getGetBillInfo(String getmxId) throws BizAppException {
		try {
			return getBillDao.getGetBillInfo(getmxId);
		} catch (SQLException e) {
		}
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
	}
	public List<GetBillInfo> getGetBillListByMxids (String[] ids) throws SQLException{
		return getBillDao.getGetBillListByMxids(ids);
	}

	/**
	 * 修改清单
	 * 
	 * @param GetBillInfo
	 * @return
	 * @throws BizAppException
	 */
	public void modifyGetBillInfo(GetBillInfo getBillInfo)
			throws BizAppException {

		try {
			if (getBillDao.modifyGetBillInfo(getBillInfo) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"修改GetBillInfo失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 申请提交
	 */
	public int applySubmit(String ids, GetApplyInfo getApplyInfo)throws Exception {
		int rs = 0;
		if (StringUtils.isBlank(ids)){
			return rs;
		}
		IRcBaseService rcBaseService = RcServiceFactory.getRcBaseService();
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		ICustManageService custManageService = ServiceFactory.getCustManageService();
		ICollateralizationService collateService = ServiceFactory.getCollateralizationService();
		ISequenceService sequenceService = ServiceFactory.getSequenceService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		CustInfo custInfo = custInfoService.getParam(getApplyInfo.getCustNo());
		CustManage custManage = custManageService.getCustManage(getApplyInfo.getCustManager());
		getApplyInfo.setGetId(sequenceService.getPrimaryKeyValue());
		getApplyInfo.setCustName(custInfo.getCustName());
		getApplyInfo.setCustManagerName(custManage.getCustManagerName());
		getApplyInfo.setDeptName(custManage.getDeptName());
		this.addGetApplyInfo(getApplyInfo);
		List<SaveBillInfo> saveBillList = collateService.getSaveBillListByMxids(ids.split(","));
		String rgctIds = "";
		for (SaveBillInfo saveBill : saveBillList) {
			rgctIds += saveBill.getRgctId()+",";
			GetBillInfo getBill = new GetBillInfo();
			saveBill.setPosition(CollateCodeConst.COLLZTN_TO_REPURCOLLZTN);
			collateService.modiSaveBillInfo(saveBill);
			if (saveBill.getBillClass().equals(CommonConst.BILL_CLASS_ELEC)) {
				getBill.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			} else {
				getBill.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
			}
			getBill.setProdNo(RepurCollateCodeConst.REPURCOLLATE_PROD_NO);
			getBill.setGetmxId(sequenceService.getPrimaryKeyValue());
			getBill.setGetId(getApplyInfo.getGetId());
			getBill.setRgctId(saveBill.getRgctId());
			getBill.setSavebillRelaId(saveBill.getSavemxId());
			getBill.setIsTc(saveBill.getIsTc());
			getBill.setOperStatus(StatusUtils.queryStatus(
					"RepurCollateStorageController", "seachWaitStorageBill",
					null)[0]);
			getBill.setCustNo(getApplyInfo.getCustNo());
			getBill.setCustName(custInfo.getCustName());
			getBill.setOperNo(user.getUserNo());
			getBill.setBillType(saveBill.getBillType());
			getBill.setBillNo(saveBill.getBillNo());
			getBill.setBillMoney(saveBill.getBillMoney());
			getBill.setIssueDt(saveBill.getIssueDt());
			getBill.setDueDt(saveBill.getDueDt());
			getBill.setRemitter(saveBill.getRemitter());
			getBill.setRemitterAcct(saveBill.getRemitterAcct());
			getBill.setRemitterBankName(saveBill.getRemitterBankName());
			getBill.setRemitterBankNo(saveBill.getRemitterBankNo());
			getBill.setAcceptor(saveBill.getAcceptor());
			getBill.setPayeeAcct(saveBill.getPayeeAcct());
			getBill.setPayeeBankName(saveBill.getPayeeBankName());
			getBill.setBillBeforeOwner(saveBill.getBillBeforeOwner());
			getBill.setBillSource(saveBill.getBillSource());
			getBill.setApplyOperNo(user.getUserId());
			getBill.setApplyDate(DateTimeUtil.getWorkdayString());
			getBill.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			getBill.setCreateDt(saveBill.getCreateDt());
			getBill.setCreateTime(saveBill.getCreateTime());
			getBill.setBranchNo(user.getBranchNo());// 机构号
			rs = getBillDao.addGetBillInfo(getBill);
			if (rs <= 0) {
				return rs;
			}
		}
		rgctIds = rgctIds.substring(0, rgctIds.length()-1);
		List<RgctBill> rgctBillList = rcBaseService.getRgctBillList(rgctIds);
		for(RgctBill rgctBill : rgctBillList){
			RgctBillHist hist = rgctBill.getHist();
			hist.setIsLock(IDict.K_LOCK.K_LOCK_YES);
			rcBaseService.updateRgctBillHist(hist);
		}
		return rs;
	}

	/**
	 * 出库
	 * 
	 * @return
	 */
	public void accountRepurCollate(String ids, String getId) throws Exception {
		SaveBillInfoDao saveBillDao = new SaveBillInfoDao();
		BranchDao branchDao = new BranchDao();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		List<GetBillInfo> getBillList = new ArrayList<GetBillInfo>();
		List<SaveBillInfo> saveBillList = new ArrayList<SaveBillInfo>();
		Map<String,SaveBillInfo> savemap = new HashMap<String,SaveBillInfo>();
		IDB session = DBFactory.getDB();
		SaveApplyInfo apply = null;
		Branch brch = branchDao.getBranch(user.getBrchNo());
		List<GetBillInfo> getbillinmfo = getBillDao.getGetBillInfolist(ids);	
		String savemxids[] = new String[getbillinmfo.size()];
		if(getbillinmfo.isEmpty()){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持该操作");
		}
		for (int i = 0; i < getbillinmfo.size(); i++) {
			savemxids[i]=getbillinmfo.get(i).getSavebillRelaId();
		}
		List<SaveBillInfo> savebilllist = saveBillDao.getSaveBillListByMxids(savemxids);
		if(savebilllist.isEmpty()){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持该操作");
		}
		for (int i = 0; i < savebilllist.size(); i++) {
			savemap.put(savebilllist.get(i).getSavemxId(), savebilllist.get(i));
		}
		for (int n = 0; n < getbillinmfo.size(); n++) {
			try {
				session.beginTransaction();
				GetBillInfo getBill = getbillinmfo.get(n);
				SaveBillInfo saveBill = savemap.get(getBill.getSavebillRelaId());
				saveBillList.add(saveBill);
				if (apply == null) {
					apply = ServiceFactory.getCollateralizationService().getSaveApplyInfo(saveBill.getSaveId(), null);
				}
				if (CommonConst.BILL_CLASS_ENTITY.equals(getBill.getBillClass())) {
					getBill.setOperStatus(StatusUtils.handleStatusNoCheck("RepurCollateStorageController", "storage", null));
					getBill.setPosition(RepurCollateCodeConst.RPDCOLLZTN_ACCOUNT_OVER);
					getBillList.add(getBill);
					saveBill.setGathType(CollateCodeConst.GATH_TYPE_SALE);
					saveBill.setGathDate(DateTimeUtil.getWorkdayString());
					saveBillDao.modifySaveBillInfo(saveBill);
				} else {
					getBill.setOperStatus(StatusUtils.handleStatusNoCheck(
							"RepurCollateStorageController", "elecStorage", null));// 电票，等待人行回复回调工作流处理
				}
				getBill.setAccountOperNo(user.getUserNo());
				getBill.setAccountDate(DateTimeUtil.getWorkdayString());
				this.modifyGetBillInfo(getBill);
				/* 调用登记中心 */
				processRcStatus(getBill, brch);
				session.endTransaction();
			} catch (Exception e) {
				try {
					session.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("解质押出库处理失败:"+e.getMessage());
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "解质押出库处理失败:"+e.getMessage());
			}
		}
		IAccountFacadeService acctService = ServiceFactory.getGetAccountService();
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		GetApplyInfo apply1 = getApplyDao.getGetApplyInfo(getId, null);
		AccountRequestDTO<GetApplyInfo, GetBillInfo> accountReq = new AccountRequestDTO<GetApplyInfo, GetBillInfo>();
		accountReq.setApply(apply1);
		accountReq.setBillList(getBillList);
		accountReq.setUserLogonInfo(userInfo);
		acctService.account(accountReq);
	}

	private void processRcStatus(GetBillInfo getbillinfo, Branch brch) {
		RgctBill rb;
		try {
			rb = RcServiceFactory.getRcUnimpawnService().getRgctBillById(
					getbillinfo.getRgctId());
			if (CommonConst.LOCK_YES.equals(rb.getHist().getIsLock())) {
				rb.getHist().setIsLock("0");
			}
			String fromAcctNo = rb.getHist().getToAcctNo();
			String fromBankNo = rb.getHist().getToBankNo();
			String fromName = rb.getHist().getToName();
			String toName = rb.getHist().getFromName();
			rb.getHist().setFromName(fromName);
			rb.getHist().setToName(toName);
			String toAcctNo = rb.getHist().getFromAcctNo();
			String toBankNo = rb.getHist().getFromBankNo();
			rb.getHist().setFromAcctNo(fromAcctNo);
			rb.getHist().setToBankNo(toBankNo);
			rb.getHist().setToAcctNo(toAcctNo);
			rb.getHist().setFromBankNo(fromBankNo);
			rb.getHist().setEndorseDt(DateTimeUtil.getWorkday());
			rb.getHist().setFromSign("0");// 电子签名 银行端直接写死"0" by zcz
			rb.getHist().setFromRole(brch.getPartnerType());
			rb.getHist().setFromOrgcode(brch.getOrgCode());
			RcServiceFactory.getRcUnimpawnService().submitUnimpawnApply(rb);
		} catch (BizAppException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 功能描述:撤销票据状态到上一个操作状态
	 * 
	 * @param ids
	 * @return
	 */
	public int cancel(String conName, String methodName, String ids)
			throws Exception {
		if (StringUtils.isBlank(ids))
			throw new BizAppException(
					ISysErrorNo.PARAM_VALIDATE_EXCEPTION_CODE, "请选择撤销票据");
		String idArr[] = ids.split(",");
		int rs = 0;
		for (String getmxId : idArr) {
			GetBillInfo bill = this.getGetBillInfo(getmxId);
			bill.setOperStatus(StatusUtils.handleStatus(conName, methodName,
					null, bill.getOperStatus()));
			rs = getBillDao.modifyGetBillInfo(bill);
			if (rs <= 0) {
				// 如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}

	/**
	 * 功能描述：剩余票据总数量
	 * 
	 * @param bill
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int totalCount(GetBillInfo bill) throws SQLException {
		return getBillDao.totalCount(bill);
	}

}
