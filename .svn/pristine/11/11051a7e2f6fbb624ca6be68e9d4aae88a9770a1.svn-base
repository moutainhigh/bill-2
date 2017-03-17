package com.herongtech.console.service.busiservice.into;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.bean.ProdLimitType;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.domain.into.bean.IntoApplyInfo;
import com.herongtech.console.domain.into.bean.IntoBillInfo;
import com.herongtech.console.domain.into.bean.IntoSearchBean;
import com.herongtech.console.domain.into.dao.IntoApplyInfoDao;
import com.herongtech.console.domain.into.dao.IntoBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.IntoCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.dao.RgctBillInfoDao;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcSaveBillService;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 存票服务
 * 
 */
public class IntoService implements IIntoService {
	public static final String HUNDSUN_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	private IntoApplyInfoDao intoApplyDao = new IntoApplyInfoDao();
	private IntoBillInfoDao intoBillDao = new IntoBillInfoDao();

	/**
	 * 初始化查询条件
	 * 
	 * @param query
	 */
	private void initQueryCondition(IntoSearchBean query) {
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
	public List<IntoApplyInfo> getIntoApplyListForApply(Page page,
			IntoSearchBean query) throws Exception {
		query.setDfaultSrchTbAliasName("ta");
		query.addSqlPropretyMapping("custNo", "custNo");
		query.addSqlPropretyMapping("operStatus", "applyStatus");
		OrderBean order = new OrderBean("intoId", false);
		query.appendOrder(order);
		return intoApplyDao.getIntoApplyListForApply(page, query);
	}

	/**
	 * 批次列表(审核 入库岗)
	 */
	public List<IntoApplyInfo> getIntoApplyListBySearchBean(Page page,
			IntoSearchBean query) throws SQLException {
		if (!"".equals(query.getBatchNo()) && query.getBatchNo() != null) {
			query.setBatchNo("%" + query.getBatchNo() + "%");
		}
		initQueryCondition(query);
		query.setDfaultSrchTbAliasName("bill");
		query.addProperty2TableObj("intoId", "apply");
		query.addProperty2TableObj("branchNo", "apply");
		query.addProperty2TableObj("batchClass", "apply");
		query.addProperty2TableObj("batchNo", "apply");
		query.addProperty2TableObj("applyStatus", "apply");
		query.addSpecialOpertion("batchNo", BaseSearchBean.LIKE);
		query.addSqlPropretyMapping("batchNo", "batchNo");
		OrderBean order = new OrderBean("intoId", false);
		query.appendOrder(order);
		return intoApplyDao.getIntoApplyListBySearchBean(page, query);
	}

	/**
	 * 清单列表(申请 审核 入库)
	 */
	public List<IntoBillInfo> getIntoBillListBySearchBean(Page page,
			IntoSearchBean query) throws Exception {
		initQueryCondition(query);
		query.setDfaultSrchTbAliasName("bill");
		query.addProperty2TableObj("intomxId", "bill");
		OrderBean order = new OrderBean("intomxId", false);
		query.appendOrder(order);
		return intoBillDao.getIntoBillListBySearchBean(page, query);
	}

	/**
	 * 得到批次详情
	 * 
	 * @param intoId
	 * @return
	 * @throws SQLException
	 */
	public IntoApplyInfo getIntoApplyInfo(String intoId, IntoSearchBean query)
			throws SQLException {
		return intoApplyDao.getIntoApplyInfo(intoId, query);
	}

	/**
	 * 得到票据详情
	 * 
	 * @param intoId
	 * @return
	 * @throws SQLException
	 */
	public List<IntoBillInfo> getIntoBillInfo(Page page, IntoSearchBean query)
			throws Exception {
		initQueryCondition(query);
		OrderBean order = new OrderBean("intomxId", false);
		query.appendOrder(order);
		return intoBillDao.getIntoBillListBySearchBean(page, query);
	}

	/**
	 * 添加批次
	 */
	public void addIntoApplyInfo(IntoApplyInfo intoApplyInfo)
			throws BizAppException {
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		// ISequenceService sequenceService=ServiceFactory.getSequenceService();
		// saveApplyInfo.setIntoId(sequenceService.getSAVE_APPLY_ID());
		// saveApplyInfo.setBatchNo(sequenceService.getSaveApplyNo(user.getBranchNo()));
		// saveApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
		intoApplyInfo.setOperNo(user.getUserId());
		intoApplyInfo.setCreateDate(DateTimeUtil.getWorkdayString());
		intoApplyInfo
				.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		intoApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		intoApplyInfo.setBranchNo(user.getBranchNo());
		try {
			if (intoApplyDao.addIntoApplyInfo(intoApplyInfo) != 1)
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"增加IntoApplyInfo失败");
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 修改批次
	 */
	public void modiIntoApplyInfo(IntoApplyInfo intoApplyInfo)
			throws BizAppException {
		try {
			if (intoApplyDao.modifyIntoApplyInfo(intoApplyInfo) != 1)
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"修改IntoApplyInfo失败");
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 修改批次 批次状态修改
	 */
	public void modifyIntoApplyInfo(IntoApplyInfo intoApplyInfo)
			throws BizAppException {
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		intoApplyInfo.setCreateDate(DateTimeUtil.getWorkdayString());
		intoApplyInfo
				.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		intoApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		intoApplyInfo.setOperNo(user.getUserId());
		intoApplyInfo.setBranchNo(user.getBranchNo());
		try {
			if (intoApplyDao.modifyIntoApplyInfo(intoApplyInfo) != 1)
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"修改IntoApplyInfo失败");
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
	public boolean delApplyInfoForIntoIds(Page page, String[] intoIds)
			throws Exception {
		IntoSearchBean query = new IntoSearchBean();
		boolean bool = true;
		for (int i = 0; i < intoIds.length; i++) {
			query.setIntoId(intoIds[i]);
			query.setOperStatus(IntoCodeConst.INPUT_STATUS);
			List<IntoBillInfo> billList = getIntoBillInfo(page, query);
			if (billList == null || billList.size() <= 0) {
				IntoApplyInfo intoApplyInfo = new IntoApplyInfo();
				intoApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
				intoApplyDao.deleteIntoApplyInfo(intoApplyInfo, intoIds[i]);
			} else {
				bool = false;
			}
		}
		// session.endTransaction();
		return bool;
	}

	/**
	 * 获取清单详情
	 */
	public IntoBillInfo getIntoBillInfo(String intoId) throws BizAppException {
		try {
			return intoBillDao.getIntoBillInfo(intoId);
		} catch (SQLException e) {
		}
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
	}
	
	/**
	 * 获取清单详情
	 * @param rgctId
	 * @return
	 * @throws SQLException
	 */
	public IntoBillInfo getIntoBillInfoByRgctId(String rgctId)
			throws SQLException {
		return intoBillDao.getIntoBillInfoByRgctId(rgctId);
	}

	/**
	 * 添加清单
	 * 
	 * @param intoId
	 * @return
	 * @throws SQLException
	 */
	public void addIntoBillInfo(IntoBillInfo bill) throws BizAppException {
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		IRcSaveBillService rcSaveBillService = RcServiceFactory
				.getRcSaveBillService();
		ISequenceService seqService = ServiceFactory.getSequenceService();
		bill.setIntomxId(seqService.getPrimaryKeyValue());
		bill.setCreateDt(DateTimeUtil.getWorkdayString());
		bill.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		try {
			bill.setOperStatus(StatusUtils.handleStatusNoCheck(
					"IntoApplyController", "saveBill", null));
		} catch (Exception e1) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,
					"增加IntoBillInfo时状态机查询失败");
		}
		// bill.setIssueDt(DateTimeUtil.getWorkdayString());
		bill.setYzSource("0");

		RgctBill rcBill = new RgctBill();
		RgctBillInfo info = new RgctBillInfo();
		info.setBillSource(RcConstants.BILL_SOURCE_COLLATERALIZATION);
		rcBill.setInfo(info);

		RgctBillHist hist = new RgctBillHist();
		hist.setFromName(bill.getCustName());
		hist.setFromAcctNo(null);
		hist.setFromCustNo(bill.getCustNo());
		hist.setToAcctNo("0");// 银行账号为0
		hist.setToBankNo(userInfo.getBrchBankNo());
		hist.setToName(userInfo.getBranchName());
		hist.setToBranchNo(userInfo.getBranchId());
		hist.setChannel(RcConstants.COMES_FROM_SYS);// 渠道
		hist.setHoldCustName(bill.getCustName());
		rcBill.setHist(hist);

		bill.setAcceptProtocolNo(bill.getProtocalNo());// 银承交易号
		bill.setRemitterAddr(bill.getDraweeAddr());// 前台付款行地址传到出票行地址
		bill.setBillSource(IntoCodeConst.bill_sourceBank);// 设置票据来源
		bill.setOperStatus(IntoCodeConst.INPUT_STATUS);// 设置初始状态
//		bill.setProdNo(IntoCodeConst.INTOBILL_PROD_NO);// 设置质押种类
		// savebillinfo.setIfBackQuery(SavebillConstantS.if_back_query);
		bill.setYzSource(IntoCodeConst.IF_YZ_SOURCE_NO);
		/* 对象转换 */
		try {
			intoBillInfoToRgctBillInfo(info, bill);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/* 录入存票 */
		rcBill = rcSaveBillService.inputSaveBill(rcBill);
		bill.setRgctId(rcBill.getInfo().getId());

		try {
			if (intoBillDao.addIntoBillInfo(bill) != 1)
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"增加IntoBillInfo失败");
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 对象转换
	 * 
	 * @param info
	 * @param bill
	 * @throws ServiceException
	 */
	public void intoBillInfoToRgctBillInfo(RgctBillInfo info, IntoBillInfo bill)
			throws Exception {
		info.setAcceptor(bill.getAcceptor());
		info.setIssueDt(bill.getIssueDt());// bill.getOutBillDate());
		info.setBillClass(bill.getBillClass());
		info.setBillType(bill.getBillType());
		info.setBillNo(bill.getBillNo());
		info.setRemitter(bill.getRemitter());
		info.setRemitterAcct(bill.getRemitterAcct());
		info.setRemitterBankNo(bill.getRemitterBankNo());
		// info.setIsAccp(bill.getIsAccp());
		info.setDraweeBankName(bill.getRemitterBankName());// 付款行=出票行
		info.setRemitterBankName(bill.getRemitterBankName());
		info.setDraweeBankNo(bill.getRemitterBankNo());// 付款行号=出票行号
		info.setAcceptorBankNo(bill.getRemitterBankNo());// 承兑行号=出票行号
		info.setDraweeAddr(bill.getRemitterAddr());// 付款行地址=出票行地址
		info.setPayeeName(bill.getPayee());
		info.setPayeeAcct(bill.getPayeeAcct());
		info.setPayeeBankName(bill.getPayeeBankName());
		info.setBillMoney(bill.getBillMoney());
		info.setDueDt(bill.getDueDt());
		info.setCreateTime(DateTimeUtil.getWorkdayString());// private Date
		// createTime;// 创建时间
	}

	/**
	 * 修改清单
	 * 
	 * @param IntoBillInfo
	 * @return
	 * @throws BizAppException
	 */
	public void modiIntoBillInfo(IntoBillInfo intoBillInfo)
			throws BizAppException {

		try {
			if (intoBillDao.modifyIntoBillInfo(intoBillInfo) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"修改IntoBillInfo失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 修改清单(调rc)
	 */
	public void modifyIntoBillInfo(IntoBillInfo intobillinfo)
			throws BizAppException {
		try {
			if (intoBillDao.modifyIntoBillInfo(intobillinfo) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR,
						"修改IntoBillInfo失败");
			}
			RgctBillInfoDao rgctBillInfoDao = new RgctBillInfoDao();
			IRcSaveBillService rcSaveBillService = RcServiceFactory
					.getRcSaveBillService();
			RgctBillInfo rgctBillInfo = rcSaveBillService.getRgctBillById(
					intobillinfo.getRgctId()).getInfo();
			try {
				intoBillInfoToRgctBillInfo(rgctBillInfo, intobillinfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			rgctBillInfoDao.modifyRgctBillInfo(rgctBillInfo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 删除票据
	 */
	public int delBill(String ids) throws Exception {
		int rs = 0;
		if (StringUtils.isBlank(ids))
			return rs;
		for (String id : ids.split(",")) {
			IntoBillInfo bill = this.getIntoBillInfo(id);
			bill.setOperStatus(StatusUtils.handleStatus("IntoApplyController",
					"delBill", null, bill.getOperStatus()));
			rs = intoBillDao.modifyIntoBillInfo(bill);
			if (rs <= 0) {
				// 如果有执行失败的 直接返回失败
				return rs;
			}
			RgctBillInfoDao rgctBillInfoDao = new RgctBillInfoDao();
			IRcSaveBillService rcSaveBillService = RcServiceFactory
					.getRcSaveBillService();
			RgctBillInfo rgctBillInfo = rcSaveBillService.getRgctBillById(
					bill.getRgctId()).getInfo();
			rgctBillInfo.setDelFlag(IntoCodeConst.DEL_YES);
			rgctBillInfoDao.modifyRgctBillInfo(rgctBillInfo);
		}
		return rs;
	}

	/**
	 * 申请提交
	 */
	public int applySubmit(String ids) throws Exception {
		int rs = 0;
		double auditAmt = 0.0;
		if (StringUtils.isBlank(ids))
			return rs;
		for (String id : ids.split(",")) {
			IntoBillInfo bill = this.getIntoBillInfo(id);
			IntoApplyInfo apply = this.getIntoApplyInfo(bill.getIntoId(), null);
			bill.setIsTc(apply.getIsTc());
			bill.setOperStatus(StatusUtils.handleStatus("IntoApplyController",
					"apply", null, bill.getOperStatus()));
			bill.setApplyDate(DateTimeUtil.getWorkdayString());
			bill.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			UserLoginfo user = ResourceUtil.getSessionLoginfo();
			bill.setApplyOperNo(user.getUserId());
			auditAmt += bill.getBillMoney();
			rs = intoBillDao.modifyIntoBillInfo(bill);
			if (rs <= 0) {
				return rs;
			}
		}
		if (rs > 0) {
			String intoId = intoBillDao.getIntoBillInfo(ids.split(",")[0])
					.getIntoId();
			// 查询批次下是否存在没有提交的清单
			List<IntoBillInfo> list = intoBillDao
					.getIntoBillListForIntoIdStatus(intoId, StatusUtils
							.queryStatus("IntoApplyController", "billManage",
									null)[0]);
			// 为修改查询批次，所以不用插叙条件
			IntoApplyInfo applyinfo = intoApplyDao.getIntoApplyInfo(intoId,
					null);
			UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
			AuditTask auditTask = new AuditTask();
			auditTask.setWaitAuditAmt(auditAmt);
			auditTask.setAtAuthorName(userInfo.getUserName());
			auditTask.setAtAuthorId(userInfo.getUserId());
			auditTask.setBrchNo(userInfo.getBrchNo());
			auditTask.setAtCreateDt(DateTimeUtil.getWorkdayString());
			auditTask.setAtCreateTime(DateTimeUtil.getTime());
			auditTask.setProdNo(applyinfo.getProdNo());
			auditTask.setBatchNo(applyinfo.getBatchNo());
			auditTask.setBatchId(intoId);
			auditTask.setEntityName("intoAuditController.do?method=auditBillList");
			auditTask.setEntityService("intoId");
			ServiceFactory.getAuditTasksService().addAuditTask(auditTask);

			// 更新批次状态为已提交
			if (list == null || list.size() == 0) {
				// 为修改查询批次，所以不用插叙条件
				applyinfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
				rs = intoApplyDao.modifyIntoApplyInfo(applyinfo);
			}
		}
		return rs;

	}

	/**
	 * 审核提交
	 */
	public int auditSubmit(String ids, String status, String option,
			IntoSearchBean query) throws Exception {
		int rs = 0;
		if (StringUtils.isBlank(ids))
			return rs;
		if ("0".equals(status) || status == IntoCodeConst.PASS_NO) {
			IntoApplyInfo apply = this.getIntoApplyInfo(query.getIntoId(),
					query);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			rs = intoApplyDao.modifyIntoApplyInfo(apply);

		}
		for (String id : ids.split(",")) {
			IntoBillInfo bill = this.getIntoBillInfo(id);
			bill.setOperStatus(StatusUtils.handleStatus("IntoAuditController",
					"audit", status, bill.getOperStatus()));
			bill.setAuditRemark(option);
			// bill.setCheckDate(DateTimeUtil.getWorkdayString());
			// bill.setCheckTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			UserLoginfo user = ResourceUtil.getSessionLoginfo();
			bill.setCheckOperNo(user.getUserId());
			rs = intoBillDao.modifyIntoBillInfo(bill);
			if (rs <= 0) {
				// 如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;

	}

	/**
	 * 入库
	 * 
	 * @return
	 */
	public int accountIntoBill(String ids, String intoId) throws Exception {
		// IntoBillInfo intoBill = null;
		IRcSaveBillService rcSaveBillService = RcServiceFactory
				.getRcSaveBillService();
		// / IRcInpoolService rcInpoolService =
		// RcServiceFactory.getInpoolService();
		IProductService productService = ServiceFactory.getProductService();
		IntoApplyInfo batch = intoApplyDao.getIntoApplyInf(intoId);
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		BranchDao branchDao = new BranchDao();
		Branch brch = branchDao.getBranch(user.getBrchNo());
		List<IntoBillInfo> billList = new ArrayList<IntoBillInfo>();
		List rgctList = new ArrayList();
		String idArr[] = ids.split(",");
		int poolListSize = 0;
		int rs = 0;
		for (int n = 0; n < idArr.length; n++) {
			IntoBillInfo intoBill = this.getIntoBillInfo(idArr[n]);
			RgctBill rb = rcSaveBillService.getRgctBillById(intoBill
					.getRgctId());
			// 买入类型
			ProdLimitType plt = productService.getProdLimitTypeByProdNo(batch
					.getProdNo());
			if (plt != null) {
				rb.getHist().setBuyType(plt.getBuyIntoType());
			}
			intoBill.setAccountOperNo(user.getUserNo());
			intoBill.setAccountDate(DateTimeUtil.getWorkdayString());
			intoBill
					.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			if (CommonConst.BILL_CLASS_ENTITY.equals(intoBill.getBillClass())) {
				// 记账调用登记中心
				rb.getHist().setEndorseDt(DateTimeUtil.getWorkday());
				rb.getHist().setStorageBranchNo(user.getBrchNo());
				rb.getHist().setStorageBranchName(user.getBranchName());
				rb.getHist().setAcctBranchNo(user.getBrchNo());
				rb.getHist().setBillBeforeOwner(intoBill.getCustName());
				rgctList.add(rb.getInfo());
				if (IntoCodeConst.poolProd_no.equals(intoBill.getProdNo())) {
					intoBill.setIsFac("1");
					// TODO rcInpoolService.inpoolAccount(rb);
					poolListSize++;
				} else {
					rcSaveBillService.saveInpool(rb);
				}
				// 入库记账改变状态
				intoBill.setGathType(IntoCodeConst.GATH_TYPE_COMMON);
				intoBill.setGathDate(null);
				intoBill.setPosition(IntoCodeConst.SAVE_ACCOUNT_OVER);
				intoBill.setOperStatus(StatusUtils.handleStatusNoCheck(
							"IntoStorageController", "storage", null));
				billList.add(intoBill);
			} else {
				rb.getHist().setToOrgcode(brch.getOrgCode());
				rb.getHist().setToRole(brch.getPartnerType());
				rb.getHist().setSignDt(DateTimeUtil.getWorkdayString());
				rb.getHist().setToAcctNo("0");
				rb.getHist().setSignerSign(
						CommUtils.getSignerSign(rb.getHist().getToBankNo()));
				rb.getHist().setIsLock("0");
				rb.getHist().setStorageBranchNo(user.getBrchNo());
				rb.getHist().setStorageBranchName(user.getBranchName());
				rb.getHist().setAcctBranchNo(user.getBrchNo());
				rb.getHist().setBillBeforeOwner(intoBill.getCustName());
				rb.getHist().setToBranchNo(user.getBranchId());
				intoBill.setOperStatus(StatusUtils.handleStatusNoCheck(
						"IntoStorageController", "elecStorage", null));
				RcServiceFactory.getRcImpawnService().impawnSign(rb);
				// intoBill.setPosition(IntoCodeConst.SAVE_WAIT_033_FOR_APPLY);
			}
			rs = intoBillDao.modifyIntoBillInfo(intoBill);
			if (rs <= 0) {
				// 如果有执行失败的 直接返回失败
				return rs;
			}
		}
		// TODO
		
		 IAccountFacadeService acctService = ServiceFactory.getIntoAccountService();
		 UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		 IntoApplyInfo apply = intoApplyDao.getIntoApplyInfo(intoId, null);
		 List<IntoBillInfo>billLis = intoBillDao.getIntoBillListByMxids(ids.split(","));
		 AccountRequestDTO<IntoApplyInfo,IntoBillInfo> accountReq = new AccountRequestDTO<IntoApplyInfo,IntoBillInfo>();
		 accountReq.setApply(apply);
		 accountReq.setBillList(billLis);
		 accountReq.setUserLogonInfo(userInfo);
		 acctService.account(accountReq);
	
		return rs;
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
		for (String intomxId : idArr) {
			IntoBillInfo bill = this.getIntoBillInfo(intomxId);
			bill.setOperStatus(StatusUtils.handleStatus(conName, methodName,
					null, bill.getOperStatus()));
			rs = intoBillDao.modifyIntoBillInfo(bill);
			if (rs <= 0) {
				// 如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}
	/**
	 * 电票未签收清单列表
	 */
	public List<IntoBillInfo> getElecIntoBillListForReceive(Page page, IntoSearchBean query)
	throws Exception
	{
		IntoBillInfoDao billDao = new IntoBillInfoDao();
		initQueryCondition(query);
		query.setDfaultSrchTbAliasName("bill");
		query.addProperty2TableObj("operStatus", "bill");
		query.addProperty2TableObj("billClass", "bill");
		OrderBean order = new OrderBean("intomxId", false);
		query.appendOrder(order);
		return billDao.getIntoBillListBySearchBean(page, query);
	}
	/**
	 * 根据id得到待接受电票
	 * @return
	 * @throws SQLException
	 */
	public List<IntoBillInfo> getElecReceiveForId(String ids) throws SQLException{
		return intoBillDao.getElecReceiveForId(ids);
	}
	/**
	 * 功能描述：添加批次并签收票据
	 * @param 
	 * @return
	 * @throws BizAppException
	 */
	@Override
	public void addBatchAndSignBill(String ids,IntoApplyInfo intoApplyInfo) throws BizAppException {
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
	    intoApplyInfo.setOperNo(user.getUserId());
	    intoApplyInfo.setCreateDate(DateTimeUtil.getWorkdayString());
	    intoApplyInfo.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
	    intoApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
	    intoApplyInfo.setBranchNo(user.getBranchNo());
		try {
			if (intoApplyDao.addIntoApplyInfo(intoApplyInfo)!= 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加IntoApplyInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		try{
			for (String id : ids.split(",")) {
				IntoBillInfo intoBillInfo = this.getIntoBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(intoBillInfo.getApplyCancelFlag())){
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "对方已经撤销申请");
				}
				intoBillInfo.setOperStatus(StatusUtils.handleStatus("IntoApplyController", "elecReceive", null,intoBillInfo.getOperStatus()));
				intoBillInfo.setIntoId(intoApplyInfo.getIntoId());
				intoBillInfo.setBranchNo(user.getBranchNo());
				intoBillInfo.setOperNo(user.getUserId());
				intoBillInfo.setSignRemark(CollateCodeConst.SIGN_YES);
				if(intoBillDao.modifyIntoBillInfo(intoBillInfo)!=1){
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改intoBillInfo失败");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 功能描述：剩余票据总数量
	 * @param bill
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int totalCount(IntoBillInfo bill) throws SQLException {
		return intoBillDao.totalCount(bill);
	}
}
