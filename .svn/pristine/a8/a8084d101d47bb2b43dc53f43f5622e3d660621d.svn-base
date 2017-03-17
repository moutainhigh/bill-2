package com.herongtech.console.service.busiservice.out;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.domain.get.bean.GetApplyInfo;
import com.herongtech.console.domain.get.bean.GetBillInfo;
import com.herongtech.console.domain.into.bean.IntoApplyInfo;
import com.herongtech.console.domain.into.bean.IntoBillInfo;
import com.herongtech.console.domain.into.dao.IntoBillInfoDao;
import com.herongtech.console.domain.out.bean.OutApplyInfo;
import com.herongtech.console.domain.out.bean.OutBillInfo;
import com.herongtech.console.domain.out.bean.OutSearchBean;
import com.herongtech.console.domain.out.dao.OutApplyInfoDao;
import com.herongtech.console.domain.out.dao.OutBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.into.IIntoService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.IntoCodeConst;
import com.herongtech.console.web.busicontroller.common.OutCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.dao.RgctBillHistDao;
import com.herongtech.rc.domain.dao.RgctBillInfoDao;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcGetBillService;
import com.herongtech.sysconstant.ISysErrorNo;


public class OutService implements IOutService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	private OutApplyInfoDao outApplyDao = new OutApplyInfoDao();	
	private OutBillInfoDao outBillDao = new OutBillInfoDao();
	
	/**
	 * 初始化查询条件
	 * @param query
	 */
	private void initQueryCondition(OutSearchBean query){
		query.setDfaultSrchTbAliasName("bill");
	    query.addSpecialOpertion("endDay",BaseSearchBean.LESS_AND_EQUAL);
	    query.addSqlPropretyMapping("endDay", "dueDt");
	    query.addSpecialOpertion("startDay",BaseSearchBean.LESS_AND_EQUAL);
        query.addSqlPropretyMapping("startDay", "dueDt");

		query.addSpecialOpertion("opers",BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		query.addSqlPropretyMapping("operStatus", "operStatus");
		query.addSqlPropretyMapping("custNo", "custNo");
	}
	
	/**
	 * 批次列表(申请岗)
	 */
  public List<OutApplyInfo> getOutApplyListForApply(Page page, OutSearchBean query)
    throws Exception
  {
    query.setDfaultSrchTbAliasName("ta");
    query.addSqlPropretyMapping("custNo", "custNo");
    query.addSqlPropretyMapping("operStatus", "applyStatus");
    OrderBean order = new OrderBean("outId", false);
    query.appendOrder(order);
    return outApplyDao.getOutApplyListForApply(page, query);
  }
  
	  /**
		 * 批次列表(审核 入库岗)
		 */
	public List<OutApplyInfo> getOutApplyListBySearchBean(Page page, OutSearchBean query)
	throws SQLException
	{
		  if(!"".equals(query.getBatchNo())&&query.getBatchNo()!=null){
				query.setBatchNo("%"+query.getBatchNo()+"%");
			}  
	initQueryCondition(query);
	query.setDfaultSrchTbAliasName("bill");
	query.addProperty2TableObj("outId", "apply");
//	query.addProperty2TableObj("branchNo", "apply");
	query.addProperty2TableObj("batchClass", "apply");
	query.addProperty2TableObj("batchNo", "apply");
	query.addProperty2TableObj("applyStatus", "apply");
	query.addSpecialOpertion("batchNo", BaseSearchBean.LIKE);
	query.addSqlPropretyMapping("batchNo", "batchNo");
	OrderBean order = new OrderBean("outId", false);
	query.appendOrder(order);
	return outApplyDao.getOutApplyListBySearchBean(page, query);
	}
	
	/**
		 * 清单列表(申请 审核 入库)
		 */
	public List<OutBillInfo> getOutBillListBySearchBean(Page page, OutSearchBean query)
	throws Exception
	{
	initQueryCondition(query);
	query.setDfaultSrchTbAliasName("bill");
	query.addProperty2TableObj("outmxId", "bill");
	OrderBean order = new OrderBean("outmxId", false);
	query.appendOrder(order);
	return outBillDao.getOutBillListBySearchBean(page, query);
	}

	/**
	 * 得到批次详情
	 * @param outId
	 * @return
	 * @throws SQLException
	 */
	public OutApplyInfo getOutApplyInfo(String outId,OutSearchBean query) throws SQLException{
		return outApplyDao.getOutApplyInfo(outId,query);
	}
	
	/**
	 * 得到票据详情
	 * @param outId
	 * @return
	 * @throws SQLException
	 */
	public List<OutBillInfo> getOutBillInfo(Page page,OutSearchBean query) throws Exception{
		initQueryCondition(query);
		OrderBean order=new OrderBean("outmxId",false);
	    query.appendOrder(order);
		return outBillDao.getOutBillListBySearchBean(page,query);
	}

		/**
		 * 添加批次
		 */
	public void addOutApplyInfo(OutApplyInfo outApplyInfo)
	throws BizAppException
	{
	UserLoginfo user = ResourceUtil.getSessionLoginfo();
	//ISequenceService sequenceService=ServiceFactory.getSequenceService();
	//saveApplyInfo.setIntoId(sequenceService.getSAVE_APPLY_ID());
	//saveApplyInfo.setBatchNo(sequenceService.getSaveApplyNo(user.getBranchNo()));
	//saveApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
	//outApplyInfo.setOperNo(user.getUserId());
	outApplyInfo.setCreateDt(DateTimeUtil.getWorkdayString());
	outApplyInfo.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
	outApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
	//outApplyInfo.setBranchNo(user.getBranchNo());
	try {
	  if (outApplyDao.addOutApplyInfo(outApplyInfo) != 1)
	    throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加OutApplyInfo失败");
	}
	catch (SQLException e) {
	  throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
	}
	}

	/**
	 * 修改批次
	 */
	 public void modiOutApplyInfo(OutApplyInfo outApplyInfo)throws BizAppException{
	  try {
	    if (outApplyDao.modifyOutApplyInfo(outApplyInfo) != 1)
	      throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改OutApplyInfo失败");
	  }
	  catch (SQLException e) {
	    throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
	  }
	}

	/**
		 * 修改批次 批次状态修改
		 */
	public void modifyOutApplyInfo(OutApplyInfo outApplyInfo)
	throws BizAppException
	{
	UserLoginfo user = ResourceUtil.getSessionLoginfo();
	outApplyInfo.setCreateDt(DateTimeUtil.getWorkdayString());
	outApplyInfo.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
	outApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
	//outApplyInfo.setOperNo(user.getUserId());
	//outApplyInfo.setBranchNo(user.getBranchNo());
	try {
	  if (outApplyDao.modifyOutApplyInfo(outApplyInfo) != 1)
	    throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改OutApplyInfo失败");
	}
	catch (SQLException e) {
	  throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
	}
	}

/**
	 * 删除批次
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	public boolean delApplyInfoForOutIds(Page page, String[] outIds)throws Exception {
		OutSearchBean query = new OutSearchBean();
		boolean bool = true;
		for(int i=0;i<outIds.length;i++){
			query.setOutId(outIds[i]);
				List<OutBillInfo> billList = getOutBillInfo(page, query);
				if(billList==null || billList.size()<=0){
					OutApplyInfo outApplyInfo = new OutApplyInfo();
					outApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
					outApplyDao.deleteOutApplyInfo(outApplyInfo,outIds[i]);}
				else{
					bool = false;
				}
		}
		//session.endTransaction();
		return bool;
	}
	
	/**
	 * 获取清单详情
	 */
	public OutBillInfo getOutBillInfo(String outmxId)
	  throws BizAppException
	{
	  try {
	    return outBillDao.getOutBillInfo(outmxId); } catch (SQLException e) {
	  }
	  throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
	}
	
	/**
	 * 修改清单
	 * @param OutBillInfo
	 * @return
	 * @throws BizAppException
	 */
	public void modifyOutBillInfo(OutBillInfo outBillInfo) throws BizAppException{
		
		try {
			if (outBillDao.modifyOutBillInfo(outBillInfo) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改OutBillInfo失败");
			}
		} catch (SQLException e) {
	         throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 申请提交
	 */
	public int applySubmit(String ids,OutApplyInfo outApplyInfo) throws Exception {
		int rs = 0;
		if (StringUtils.isBlank(ids))
			return rs;
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		ICustManageService custManageService = ServiceFactory.getCustManageService();
		IIntoService intoService = ServiceFactory.getIntoService();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		CustInfo custInfo = custInfoService.getParam(outApplyInfo.getCustNo());
		CustManage custManage=custManageService.getCustManage(outApplyInfo.getCustManager());
		outApplyInfo.setOutId(sequenceService.getPrimaryKeyValue());
		outApplyInfo.setCustName(custInfo.getCustName());
		outApplyInfo.setCustManagerName(custManage.getCustManagerName());
		outApplyInfo.setDeptName(custManage.getDeptName());
		this.addOutApplyInfo(outApplyInfo);
		for (String id : ids.split(",")) {
			OutBillInfo outBill = new OutBillInfo();
			IntoBillInfo intoBill = intoService.getIntoBillInfo(id);
			intoBill.setPosition(IntoCodeConst.SAVE_TO_GET);
			intoService.modiIntoBillInfo(intoBill);
			if(intoBill.getBillClass().equals(CommonConst.BILL_CLASS_ELEC)){
				outBill.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
				outBill.setProdNo(OutCodeConst.OUTBILL_PROD_NO);
			}else{
				outBill.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
				if(intoBill.getProdNo().equals(IntoCodeConst.BASIC_PROD_NO)){
					outBill.setProdNo(OutCodeConst.BASIC_PROD_NO);
				}else if(intoBill.getProdNo().equals(IntoCodeConst.DERIVATIVE_PROD_NO)){
					outBill.setProdNo(OutCodeConst.DERIVATIVE_PROD_NO);
				}else{
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "无产品编号");
				}
			}
			outBill.setOutmxId(sequenceService.getPrimaryKeyValue());
			outBill.setOutId(outApplyInfo.getOutId());
			outBill.setRgctId(intoBill.getRgctId());
			outBill.setIntobillRelaId(intoBill.getIntomxId());
			outBill.setIsTc(intoBill.getIsTc());
			outBill.setOperStatus(StatusUtils.queryStatus(
						"OutAuditController", "seachWaitAuditBill",null)[0]);
//			outBill.setPosition(OutCodeConst.GET_CHECK);
			outBill.setCustNo(outApplyInfo.getCustNo());
			outBill.setCustName(custInfo.getCustName());
			outBill.setOperNo(user.getUserNo());
			outBill.setBillType(intoBill.getBillType());
			outBill.setBillNo(intoBill.getBillNo());
			outBill.setBillMoney(intoBill.getBillMoney());
			outBill.setIssueDt(intoBill.getIssueDt());
			outBill.setDueDt(intoBill.getDueDt());
			outBill.setRemitter(intoBill.getRemitter());
			outBill.setRemitterAcct(intoBill.getRemitterAcct());
			outBill.setRemitterBankName(intoBill.getRemitterBankName());
			outBill.setRemitterBankNo(intoBill.getRemitterBankNo());
			outBill.setAcceptor(intoBill.getAcceptor());
			outBill.setPayeeAcct(intoBill.getPayeeAcct());
			outBill.setPayeeBankName(intoBill.getPayeeBankName());
			outBill.setBillBeforeOwner(intoBill.getBillBeforeOwner());
			outBill.setBillSource(intoBill.getBillSource());
			outBill.setApplyOperNo(user.getUserId());
			outBill.setApplyDate(DateTimeUtil.getWorkdayString());
			outBill.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			outBill.setCreateDt(intoBill.getCreateDt());
			outBill.setCreateTime(intoBill.getCreateTime());
			outBill.setBranchNo(user.getBranchNo());//机构号
			rs = outBillDao.addOutBillInfo(outBill);
			if (rs <= 0) {
				return rs;
			}
		}
		return rs;
	}
	
	/**
	 * 审核提交
	 */
	public int auditSubmit(String ids, String status, String option,
			OutSearchBean query) throws Exception {
		int rs = 0;
		if (StringUtils.isBlank(ids))
			return rs;
		if ("0".equals(status) || status == OutCodeConst.PASS_NO) {
			OutApplyInfo apply = this.getOutApplyInfo(query.getOutId(),
					query);
			apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			rs = outApplyDao.modifyOutApplyInfo(apply);

		}
		for (String id : ids.split(",")) {
			OutBillInfo bill = this.getOutBillInfo(id);
			bill.setOperStatus(StatusUtils.handleStatus("OutAuditController",
					"audit", status, bill.getOperStatus()));
			bill.setAuditRemark(option);
			// bill.setCheckDate(DateTimeUtil.getWorkdayString());
			// bill.setCheckTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			UserLoginfo user = ResourceUtil.getSessionLoginfo();
			bill.setCheckOperNo(user.getUserId());
			rs = outBillDao.modifyOutBillInfo(bill);
			if (rs <= 0) {
				// 如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;

	}
	
	/**
	 * 出库
	 * 
	 * @return
	 */
	public int accountOutBill(String ids, String outId) throws Exception {
		IntoBillInfoDao intoBillDao = new IntoBillInfoDao();
		BranchDao branchDao = new BranchDao();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		Branch brch = branchDao.getBranch(user.getBrchNo());
		OutApplyInfo outApplyInfo = outApplyDao.getOutApplyInfo(outId);
		List<OutBillInfo> BillList = new ArrayList<OutBillInfo>();
		String idArr[] = ids.split(",");
		int rs = 0;
		int rs1 = 0;
		/*for (int n = 0; n < idArr.length; n++) {
			OutBillInfo outBill = this.getOutBillInfo(idArr[n]);
			IntoBillInfo intoBill = intoBillDao.getIntoBillInfo(outBill.getIntobillRelaId());
			if(CommonConst.BILL_CLASS_ENTITY.equals(outApplyInfo.getBatchClass())){
				outBill.setOperStatus(StatusUtils.handleStatusNoCheck("OutStorageController", "storage", null));
				outBillDao.modifyOutBillInfo(outBill);
				billList.add(outBill);
				if (OutCodeConst.PASS_YES.equals(intoBill.getIsAmount()) &&!OutCodeConst.ONE.equals(intoBill.getYzSource())){//迁移数据不释放第三方额度
					list.add(intoBill);
				}
				if(OutCodeConst.PASS_YES.equals(intoBill.getIsAmount())
						&& OutCodeConst.ONE.equals(intoBill.getIsFac())
						&& OutCodeConst.ONE.equals(intoBill.getQueryStatus().trim())){//票据池额度处理
					idArr[n] = outBill.getOutmxId();					    
				}
			}else{
				if(OutCodeConst.PASS_YES.equals(intoBill.getIsAmount())
						&& OutCodeConst.ONE.equals(intoBill.getIsFac())){//票据池额度处理
					idArr[n] = outBill.getOutmxId();					    
				}
			}
		}*/
		if(CommonConst.BILL_CLASS_ENTITY.equals(outApplyInfo.getBatchClass())){
			for (int n = 0; n < idArr.length; n++) {
				OutBillInfo outBill = this.getOutBillInfo(idArr[n]);
				outBill.setOperStatus(StatusUtils.handleStatusNoCheck("OutStorageController", "storage", null));// 取票记账完成
				outBill.setAccountOperNo(user.getUserNo());
				outBill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				outBill.setPosition(OutCodeConst.GET_ACCOUNT_OVER);
				rs = outBillDao.modifyOutBillInfo(outBill);
				
				IntoBillInfo intoBill = intoBillDao.getIntoBillInfo(outBill.getIntobillRelaId());
				intoBill.setIsFac("0");
				intoBill.setGathType(OutCodeConst.GATH_TYPE_SALE);
				intoBill.setGathDate(DateTimeUtil.getWorkdayString());
				rs1 = intoBillDao.modifyIntoBillInfo(intoBill);
				
				RgctBill rgctBill = RcServiceFactory.getRcGetBillService().getRgctBillById(outBill.getRgctId());
				rgctBill.getHist().setIsLock(CommonConst.LOCK_NO);
				RcServiceFactory.getRcGetBillService().updateRgctBillHist(rgctBill.getHist());
				if (rs <= 0||rs1 <= 0) {
					// 如果有执行失败的 直接返回失败
					return 0;
				}
				// 出池出库记账调用登记中心
				changeRgctBillOutPool(outBill, user);
				BillList.add(outBill);
			}
		}else{
			//电子出池处理
			for (int n = 0; n < idArr.length; n++) {
				OutBillInfo outBill = this.getOutBillInfo(idArr[n]);
				outBill.setOperStatus(StatusUtils.handleStatusNoCheck("OutStorageController", "elecStorage", null));
				outBill.setAccountOperNo(user.getUserNo());
				outBill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
//				outBill.setPosition(OutCodeConst.GET_ACCOUNT_OVER);
				rs = outBillDao.modifyOutBillInfo(outBill);
				if (rs <= 0) {
					// 如果有执行失败的 直接返回失败
					return rs;
				}
				processRcStatus(outBill, brch);
				BillList.add(outBill);
			}
		}
		IAccountFacadeService acctService = ServiceFactory.getOutAccountService();
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		OutApplyInfo apply = outApplyDao.getOutApplyInfo(outId, null);
//		List<OutBillInfo> billLis = outBillDao.getOutBillListByMxids(ids.split(","));
		AccountRequestDTO<OutApplyInfo,OutBillInfo> accountReq = new AccountRequestDTO<OutApplyInfo,OutBillInfo>();
		accountReq.setApply(apply);
		accountReq.setBillList(BillList);
		accountReq.setUserLogonInfo(userInfo);
		acctService.account(accountReq);
		return rs;
	}
	
	private void changeRgctBillOutPool(OutBillInfo outBill, UserLoginfo user) {
		RgctBill rb;
		try {
			rb = RcServiceFactory.getRcGetBillService().getRgctBillById(outBill.getRgctId());
			rb.getHist().setFromAcctNo(rb.getHist().getToAcctNo());
			rb.getHist().setToBankNo(rb.getHist().getFromBankNo());
			rb.getHist().setToAcctNo(null);
			rb.getHist().setFromBankNo(rb.getHist().getToBankNo());
			rb.getHist().setEndorseDt(DateTimeUtil.getWorkday());
			rb.getHist().setFromName(user.getBranchName());
			rb.getHist().setToName(outBill.getCustName());
			rb.getHist().setToCustNo(outBill.getCustNo());
			rb.getHist().setHoldCustName(outBill.getCustName());
			rb.getHist().setHoldCustNo(outBill.getCustNo());
			if (OutCodeConst.outPoolProd_no.equals(outBill.getProdNo())) {
				// 出池出库记账调用登记中心
				///	RcServiceFactory.getRcOutpoolService().outpoolAccount(rb);
			} else {
				// 取票记账调用登记中心
				RcServiceFactory.getRcGetBillService().getOutpool(rb);
			}
		} catch (BizAppException e) {
			e.printStackTrace();
		}
	}
	
	private void processRcStatus(OutBillInfo outBill, Branch brch) {
		try {
			RgctBill rb = RcServiceFactory.getRcUnimpawnService().getRgctBillById(outBill.getRgctId());
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
		for (String outmxId : idArr) {
			OutBillInfo bill = this.getOutBillInfo(outmxId);
			bill.setOperStatus(StatusUtils.handleStatus(conName, methodName,
					null, bill.getOperStatus()));
			rs = outBillDao.modifyOutBillInfo(bill);
			if (rs <= 0) {
				// 如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}
	
	/**
	 * 功能描述：剩余票据总数量
	 * @param bill
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int totalCount(OutBillInfo bill) throws SQLException {
		return outBillDao.totalCount(bill);
	}
	
	

}
