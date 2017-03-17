package com.herongtech.console.service.busiservice.collateralization;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.herongtech.console.domain.common.bean.FacDealResult;
import com.herongtech.console.domain.common.bean.ProdLimitType;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
import com.herongtech.console.domain.save.dao.SaveApplyInfoDao;
import com.herongtech.console.domain.save.dao.SaveBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.dao.RgctBillInfoDao;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcImpawnService;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 质押服务
 *
 */
public class CollateralizationService implements ICollateralizationService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 初始化查询条件
	 * @param query
	 */
	private void initQueryCondition(SaveSearchBean query){
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
	 * 根据id得到待接受电票
	 * @return
	 * @throws SQLException
	 */
	public List<SaveBillInfo> getElecReceiveForId(String ids) throws SQLException{
		return saveBillDao.getElecReceiveForId(ids);
	}
	
	/**
	 * 电票未签收清单列表
	 */
	public List<SaveBillInfo> getElecSaveBillListForReceive(Page page, SaveSearchBean query)
	throws Exception
	{
		SaveBillInfoDao billDao = new SaveBillInfoDao();
		initQueryCondition(query);
		query.setDfaultSrchTbAliasName("bill");
		query.addProperty2TableObj("operStatus", "bill");
		query.addProperty2TableObj("billClass", "bill");
		OrderBean order = new OrderBean("savemxId", false);
		query.appendOrder(order);
		return billDao.getSaveBillListBySearchBean(page, query);
	}
	
	/**
	 * 功能描述：添加批次并签收票据
	 * @param 
	 * @return
	 * @throws BizAppException
	 */
	@Override
	public void addBatchAndSignBill(String ids,SaveApplyInfo saveApplyInfo) throws BizAppException {
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
//	    ISequenceService sequenceService=ServiceFactory.getSequenceService();
//	    saveApplyInfo.setSaveId(sequenceService.getSAVE_APPLY_ID());
//	    saveApplyInfo.setBatchNo(sequenceService.getSaveApplyNo(user.getBranchNo()));
//	    saveApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
	    saveApplyInfo.setOperNo(user.getUserId());
	    saveApplyInfo.setCreateDate(DateTimeUtil.getWorkdayString());
	    saveApplyInfo.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
	    saveApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
	    saveApplyInfo.setBranchNo(user.getBranchNo());
		try {
			if (saveApplyDao.addSaveApplyInfo(saveApplyInfo)!= 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加SaveApplyInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		try{
			for (String id : ids.split(",")) {
				SaveBillInfo saveBillInfo = this.getSaveBillInfo(id);
				if(CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(saveBillInfo.getApplyCancelFlag())){
				//	retJson.setMsg("对方已经撤销申请,票号为"+saveBillInfo.getBillNo());
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "对方已经撤销申请");
				}
				saveBillInfo.setCustNo(saveApplyInfo.getCustNo());
				saveBillInfo.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "elecReceive", null,saveBillInfo.getOperStatus()));
				saveBillInfo.setSaveId(saveApplyInfo.getSaveId());
				saveBillInfo.setBranchNo(user.getBranchNo());
				saveBillInfo.setOperNo(user.getUserId());
				saveBillInfo.setSignRemark(CollateCodeConst.SIGN_YES);
				if(saveBillDao.modifySaveBillInfo(saveBillInfo)!=1){
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改saveBillInfo失败");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 批次列表(申请岗)
	 */
  public List<SaveApplyInfo> getSaveApplyListForApply(Page page, SaveSearchBean query)
    throws Exception
  {
    SaveBillInfoDao billDao = new SaveBillInfoDao();
    query.setDfaultSrchTbAliasName("ta");
    query.addSqlPropretyMapping("custNo", "custNo");
    query.addSqlPropretyMapping("operStatus", "applyStatus");
    OrderBean order = new OrderBean("saveId", false);
    query.appendOrder(order);
    return billDao.getSaveApplyListForApply(page, query);
  }

  /**
	 * 批次列表(审核 入库岗)
	 */
  public List<SaveApplyInfo> getSaveApplyListBySearchBean(Page page, SaveSearchBean query)
    throws SQLException
  {
	  if(!"".equals(query.getBatchNo())&&query.getBatchNo()!=null){
			query.setBatchNo("%"+query.getBatchNo()+"%");
		}  
    SaveBillInfoDao billDao = new SaveBillInfoDao();
    initQueryCondition(query);
    query.setDfaultSrchTbAliasName("bill");
    query.addProperty2TableObj("saveId", "apply");
    query.addProperty2TableObj("branchNo", "apply");
    query.addProperty2TableObj("batchClass", "apply");
    query.addProperty2TableObj("batchNo", "apply");
    query.addProperty2TableObj("applyStatus", "apply");
    query.addSpecialOpertion("batchNo", BaseSearchBean.LIKE);
    query.addSqlPropretyMapping("batchNo", "batchNo");
    OrderBean order = new OrderBean("saveId", false);
    query.appendOrder(order);
    return billDao.getSaveApplyListBySearchBean(page, query);
  }

  /**
	 * 清单列表(申请 审核 入库)
	 */
  public List<SaveBillInfo> getSaveBillListBySearchBean(Page page, SaveSearchBean query)
    throws Exception
  {
    SaveBillInfoDao billDao = new SaveBillInfoDao();
    initQueryCondition(query);
    query.setDfaultSrchTbAliasName("bill");
    query.addProperty2TableObj("savemxId", "bill");
    OrderBean order = new OrderBean("savemxId", false);
    query.appendOrder(order);
    return billDao.getSaveBillListBySearchBean(page, query);
  }
  
     /**
	 * 得到批次详情
	 * @param saveId
	 * @return
	 * @throws SQLException
	 */
	public SaveApplyInfo getSaveApplyInfo(String saveId,SaveSearchBean query) throws SQLException{
		SaveApplyInfoDao dao = new SaveApplyInfoDao();
		return dao.getSaveApplyInfo(saveId,query);
	}
	
	/**
	 * 得到票据详情
	 * @param saveId
	 * @return
	 * @throws SQLException
	 */
	public List<SaveBillInfo> getSaveBillInfo(Page page,SaveSearchBean query) throws Exception{
		initQueryCondition(query);
		OrderBean order=new OrderBean("savemxId",false);
	    query.appendOrder(order);
		return saveBillDao.getSaveBillListBySearchBean(page,query);
	}

  /**
	 * 添加批次
	 */
  public void addSaveApplyInfo(SaveApplyInfo saveApplyInfo)
    throws BizAppException
  {
    SaveApplyInfoDao dao = new SaveApplyInfoDao();
    UserLoginfo user = ResourceUtil.getSessionLoginfo();
//    ISequenceService sequenceService=ServiceFactory.getSequenceService();
//    saveApplyInfo.setSaveId(sequenceService.getSAVE_APPLY_ID());
//    saveApplyInfo.setBatchNo(sequenceService.getSaveApplyNo(user.getBranchNo()));
//    saveApplyInfo.setBatchClass(CommonConst.BILL_CLASS_ENTITY);
    saveApplyInfo.setOperNo(user.getUserId());
    saveApplyInfo.setCreateDate(DateTimeUtil.getWorkdayString());
    saveApplyInfo.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
    saveApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
    saveApplyInfo.setBranchNo(user.getBranchNo());
    try {
      if (dao.addSaveApplyInfo(saveApplyInfo) != 1)
        throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加SaveApplyInfo失败");
    }
    catch (SQLException e) {
      throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
    }
  }

  /**
	 * 修改批次
	 */
	 public void modiSaveApplyInfo(SaveApplyInfo saveApplyInfo)throws BizAppException{
	  try {
	    if (saveApplyDao.modifySaveApplyInfo(saveApplyInfo) != 1)
	      throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改SaveApplyInfo失败");
	  }
	  catch (SQLException e) {
	    throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
	  }
	}

  /**
	 * 修改批次 批次状态修改
	 */
  public void modifySaveApplyInfo(SaveApplyInfo saveApplyInfo)
    throws BizAppException
  {
    SaveApplyInfoDao dao = new SaveApplyInfoDao();
    UserLoginfo user = ResourceUtil.getSessionLoginfo();
    saveApplyInfo.setCreateDate(DateTimeUtil.getWorkdayString());
    saveApplyInfo.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
    saveApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
    saveApplyInfo.setOperNo(user.getUserId());
    saveApplyInfo.setBranchNo(user.getBranchNo());
    try {
      if (dao.modifySaveApplyInfo(saveApplyInfo) != 1)
        throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改SaveApplyInfo失败");
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
	public boolean delApplyInfoForSaveIds(Page page, String[] saveIds)
			throws Exception {
		SaveSearchBean query = new SaveSearchBean();
		boolean bool = true;
		SaveApplyInfoDao dao=new SaveApplyInfoDao();
		for(int i=0;i<saveIds.length;i++){
			query.setSaveId(saveIds[i]);
			query.setOperStatus(CollateCodeConst.INPUT_STATUS);
				List<SaveBillInfo> billList = getSaveBillInfo(page, query);
				if(billList==null || billList.size()<=0){
					SaveApplyInfo saveApplyInfo = new SaveApplyInfo();
					saveApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
					dao.deleteSaveApplyInfo(saveApplyInfo,saveIds[i]);}
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
  public SaveBillInfo getSaveBillInfo(String saveId)
    throws BizAppException
  {
    SaveBillInfoDao dao = new SaveBillInfoDao();
    try {
      return dao.getSaveBillInfo(saveId); } catch (SQLException e) {
    }
    throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
  }

  /**
	 * 添加清单
	 * @param saveId
	 * @return
	 * @throws SQLException
	 */
  public void addSaveBillInfo(SaveBillInfo bill)
    throws BizAppException
  {
    UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
    IRcImpawnService rcImpawnService = RcServiceFactory.getRcImpawnService();
    ISequenceService seqService = ServiceFactory.getSequenceService();
    bill.setSavemxId(seqService.getSAVE_BILL_INFO_ID());
    bill.setCreateDt(DateTimeUtil.getWorkdayString());
    bill.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
    try
    {
    	bill.setOperStatus(StatusUtils.handleStatusNoCheck("CollateralizationApplyController", "saveBill", null));
    } catch (Exception e1) {
      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加SaveBillInfo时状态机查询失败");
    }
 //   bill.setIssueDt(DateTimeUtil.getWorkdayString());
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
	hist.setChannel(RcConstants.COMES_FROM_SYS);//渠道
	hist.setHoldCustName(bill.getCustName());
	rcBill.setHist(hist);
	
	bill.setAcceptProtocolNo(bill.getProtocalNo());//银承交易号
	bill.setRemitterAddr(bill.getDraweeAddr());//前台付款行地址传到出票行地址
	bill.setBillSource(CollateCodeConst.bill_sourceBank);// 设置票据来源
	bill.setOperStatus(CollateCodeConst.INPUT_STATUS);// 设置初始状态
	bill.setProdNo(CollateCodeConst.impawnBillProd_no);// 设置质押种类
	//savebillinfo.setIfBackQuery(SavebillConstantS.if_back_query);
	bill.setYzSource(CollateCodeConst.IF_YZ_SOURCE_NO);
	/* 对象转换 */
	try {
		saveBillInfoToRgctBillInfo(info, bill);
	} catch (Exception e1) {
		e1.printStackTrace();
	}
	/* 录入质押存票 */
	rcBill = rcImpawnService.inputImpawnBill(rcBill);
	bill.setRgctId(rcBill.getInfo().getId());
    try {
      if (this.saveBillDao.addSaveBillInfo(bill) != 1)
        throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加saveBillInfo失败");
    }
    catch (SQLException e) {
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
	public void saveBillInfoToRgctBillInfo(RgctBillInfo info, SaveBillInfo bill) throws Exception {
		info.setAcceptor(bill.getAcceptor());
		info.setIssueDt(bill.getIssueDt());// bill.getOutBillDate());
		info.setBillClass(bill.getBillClass());
		info.setBillType(bill.getBillType());
		info.setBillNo(bill.getBillNo());
		info.setRemitter(bill.getRemitter());
		info.setRemitterAcct(bill.getRemitterAcct());
		info.setRemitterBankNo(bill.getRemitterBankNo());
		//info.setIsAccp(bill.getIsAccp());
		info.setDraweeBankName(bill.getRemitterBankName());//付款行=出票行
		info.setRemitterBankName(bill.getRemitterBankName());
		info.setDraweeBankNo(bill.getRemitterBankNo());//付款行号=出票行号
		info.setAcceptorBankNo(bill.getRemitterBankNo());//承兑行号=出票行号
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
	 * @param SaveBillInfo
	 * @return
	 * @throws BizAppException
	 */
	public void modiSaveBillInfo(SaveBillInfo saveBillInfo) throws BizAppException{
		
		try {
			if (saveBillDao.modifySaveBillInfo(saveBillInfo) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改SaveBillInfo失败");
			}
		} catch (SQLException e) {
	         throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 修改清单(调rc)
	 */
	public void modifySaveBillInfo(SaveBillInfo savebillinfo) throws BizAppException {
		SaveBillInfoDao dao=new SaveBillInfoDao();
		try {
			if (dao.modifySaveBillInfo(savebillinfo) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改SaveBillInfo失败");
			}
			RgctBillInfoDao rgctBillInfoDao = new RgctBillInfoDao();
			IRcImpawnService rcImpawnService = RcServiceFactory.getRcImpawnService();
            RgctBillInfo rgctBillInfo = rcImpawnService.getRgctBillById(savebillinfo.getRgctId()).getInfo();
            try {
				saveBillInfoToRgctBillInfo(rgctBillInfo, savebillinfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
            rgctBillInfoDao.modifyRgctBillInfo(rgctBillInfo);
		} catch (SQLException e) {
	         throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	 /**
	 * 删除票据
	 */
	public int delBill(String ids) throws Exception{
		int rs = 0 ;
		if(StringUtils.isBlank(ids)) return rs;
		for (String id : ids.split(",")) {
			SaveBillInfo bill = this.getSaveBillInfo(id);
			bill.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "delBill", null,bill.getOperStatus()));
			rs = this.saveBillDao.modifySaveBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
			RgctBillInfoDao rgctBillInfoDao = new RgctBillInfoDao();
			IRcImpawnService rcImpawnService = RcServiceFactory.getRcImpawnService();
            RgctBillInfo rgctBillInfo = rcImpawnService.getRgctBillById(bill.getRgctId()).getInfo();
            rgctBillInfo.setDelFlag(CollateCodeConst.DEL_YES);
            rgctBillInfoDao.modifyRgctBillInfo(rgctBillInfo);
		}
		return rs;
	}

	/**
	 * 申请提交
	 */
	public int applySubmit(String ids) throws Exception {
		SaveApplyInfoDao applyDao= new SaveApplyInfoDao();
		SaveBillInfoDao billDao=new SaveBillInfoDao();
		int rs = 0;
		double auditAmt = 0.0;
		if(StringUtils.isBlank(ids)) return rs;
		String[] idArr = ids.split(",");
		List<SaveBillInfo> bills = saveBillDao.getSaveBillListByMxids(idArr);
		String saveId = billDao.getSaveBillInfo(ids.split(",")[0]).getSaveId();
		//为修改查询批次，所以不用插叙条件
		SaveApplyInfo applyinfo = saveApplyDao.getSaveApplyInfo(saveId,null);
		//额度扣减
		FacDealResult result = ServiceFactory.getCollateFacService().dealFac(applyinfo, bills, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_OCCUPY);
		result.checkFacResult(CommonConst.FAC_OPER_OCCUPY);
		//更新清单信息
		for(SaveBillInfo bill:bills){
			bill.setOperStatus(StatusUtils.handleStatus("CollateralizationApplyController", "apply", null,bill.getOperStatus()));
			bill.setApplyDate(DateTimeUtil.getWorkdayString());
			bill.setApplyTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			UserLoginfo user = ResourceUtil.getSessionLoginfo();
		    bill.setApplyOperNo(user.getUserId());
		    auditAmt += bill.getBillMoney();
			rs = billDao.modifySaveBillInfo(bill);
			if( rs <= 0 ){
				return rs;
			}
		}
		if(rs > 0){
			//查询批次下是否存在没有提交的清单
			List<SaveBillInfo> list = billDao.getSaveBillListForSaveIdStatus(saveId,StatusUtils.queryStatus("CollateralizationApplyController", "billManage", null)[0]);
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
            auditTask.setBatchId(saveId);
            auditTask.setEntityName("collateralizationAuditController.do?method=auditBillList");
            auditTask.setEntityService("saveId");
		    ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
			
			//更新批次状态为已提交
			if( list == null || list.size() == 0 ) {
				//为修改查询批次，所以不用插叙条件
				applyinfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
				rs = applyDao.modifySaveApplyInfo(applyinfo);
			}
		}
		return rs;
		
	}
	
	/**
	 * 审核提交
	 */
	public int auditSubmit(String ids,String status,String option,SaveSearchBean query) throws Exception{
		SaveBillInfoDao billDao=new SaveBillInfoDao();
		int rs = 0;
		if(StringUtils.isBlank(ids)) return rs;
		SaveApplyInfo apply = this.getSaveApplyInfo(query.getSaveId(), query);
		if("0".equals(status)||status == CollateCodeConst.PASS_NO){
			SaveApplyInfoDao applyDao=new SaveApplyInfoDao();
			apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			rs = applyDao.modifySaveApplyInfo(apply);
			
		}
		String[] idArr = ids.split(",");
		List<SaveBillInfo> bills = saveBillDao.getSaveBillListByMxids(idArr);
		if("0".equals(status)){
			//审核拒绝，释放额度
			FacDealResult result = ServiceFactory.getCollateFacService().dealFac(apply, bills, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_RELEASE);
			result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
		}
		//更新清单信息
		for(SaveBillInfo bill:bills){
			bill.setOperStatus(StatusUtils.handleStatus("CollateralizationAuditController", "audit", status,bill.getOperStatus()));
			bill.setAuditRemark(option);
//			bill.setCheckDate(DateTimeUtil.getWorkdayString());
//			bill.setCheckTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			UserLoginfo user = ResourceUtil.getSessionLoginfo();
		    bill.setCheckOperNo(user.getUserId());
			rs = billDao.modifySaveBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
		
	}
	
	/**
	 * 入库
	 * @return 
	 */
	public void accountCollateralizationBill(String ids,String saveId)
	throws Exception {
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		BranchDao branchDao = new BranchDao();
		Map<String,RgctBill> rgctmap = new HashMap<String,RgctBill>();
		IRcImpawnService rcImpawnService = RcServiceFactory.getRcImpawnService();
		SaveApplyInfoDao saveApplyInfoDao = new SaveApplyInfoDao();
		String rgctids = "";
		IDB session = DBFactory.getDB();
        Branch brch = branchDao.getBranch(user.getBrchNo());//
        SaveApplyInfo batch = saveApplyInfoDao.getSaveApplyInf(saveId);
        SaveApplyInfo apply = saveApplyDao.getSaveApplyInfo(saveId, null);
        IProductService productService = ServiceFactory.getProductService();
        ProdLimitType plt = productService.getProdLimitTypeByProdNo(batch.getProdNo());
        if (plt == null) {
            throw new Exception("获取买入类型失败");
        }
        List<SaveBillInfo> salebilllist = saveBillDao.getSaveBillListByMxids(ids.split(","));
        if(salebilllist.size()==0){
        	throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持此操作");
        }
        for (int i = 0; i < salebilllist.size(); i++) {
        	rgctids = rgctids + salebilllist.get(i).getRgctId()+",";
		}
        List<RgctBill> rgctlist = rcImpawnService.getRgctBillList(rgctids.substring(0,rgctids.length()));
        if(rgctlist.size()==0){
        	throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持此操作");
        }
        for (int i = 0; i < rgctlist.size(); i++) {
        	rgctmap.put(rgctlist.get(i).getHist().getRgctId(), rgctlist.get(i));
		}
        for (int n = 0; n < salebilllist.size(); n++) {
        	try {
        		session.beginTransaction();
				SaveBillInfo bill = salebilllist.get(n);
				if (CollateCodeConst.SALE_CANCEL_FLAG_YES.equals(bill.getApplyCancelFlag())) {
				    throw new Exception("该票据：" + bill.getBillNo() + "对方已撤销，请退回申请岗删除该票据。");
				    }
				if ("2".equals(bill.getBillClass())){
					bill.setOperStatus(StatusUtils.handleStatusNoCheck("CollateralizationStorageController", "elecStorage", null));//操作状态
				}else{
					bill.setOperStatus(StatusUtils.handleStatusNoCheck("CollateralizationStorageController", "storage", null));//操作状态
					bill.setPosition(CollateCodeConst.COLLZTN_ACCOUNT_OVER);//质押记账完成
					bill.setGathType(CollateCodeConst.GATH_TYPE_COMMON);//正常--记账完成
					bill.setAccountDate(DateTimeUtil.getWorkdayString());//入库记账日期
				    bill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
					bill.setGathDate(null);
				}
				this.saveBillDao.modifySaveBillInfo(bill);
				IAccountFacadeService acctService = ServiceFactory.getSaveAccountService();
				UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
				List<SaveBillInfo> billLis = saveBillDao.getSaveBillListByMxids(ids.split(","));
				AccountRequestDTO<SaveApplyInfo,SaveBillInfo> accountReq = new AccountRequestDTO<SaveApplyInfo,SaveBillInfo>();
				accountReq.setApply(apply);
				accountReq.setBillList(billLis);
				accountReq.setUserLogonInfo(userInfo);
				acctService.account(accountReq);
				/* 调用登记中心 */
				
				RgctBill rb = rgctmap.get(bill.getRgctId());
//                rgctList.add(rb.getInfo());
				if ("2".equals(bill.getBillClass())) {
					rb.getHist().setToOrgcode(brch.getOrgCode());
					rb.getHist().setToRole(brch.getPartnerType());
					rb.getHist().setSignDt(DateTimeUtil.getWorkdayString());
					rb.getHist().setToAcctNo("0");
					rb.getHist().setSignerSign(CommUtils.getSignerSign(rb.getHist().getToBankNo()));
				}
				rb.getHist().setIsLock("0");
				rb.getHist().setStorageBranchNo(user.getBrchNo());
				rb.getHist().setStorageBranchName(user.getBranchName());
				rb.getHist().setAcctBranchNo(user.getBrchNo());
				rb.getHist().setBillBeforeOwner(bill.getCustName());
				rb.getHist().setToBranchNo(user.getBranchId());
				rb.getHist().setBuyType(plt.getBuyIntoType());
				rcImpawnService.impawnSign(rb);
				session.endTransaction();
			} catch (Exception e) {
				try {
					session.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("处理失败:"+e.getMessage());
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "处理失败:"+e.getMessage());
			}
        }
    		
        }
	
	/**
	 * 功能描述:撤销票据状态到上一个操作状态
	 * @param ids
	 * @return
	 */
	public int cancel(String conName,String methodName,String ids) throws Exception{
		if(StringUtils.isBlank(ids)) throw new BizAppException(ISysErrorNo.PARAM_VALIDATE_EXCEPTION_CODE,"请选择撤销票据");
		String idArr[] = ids.split(",");
		int rs = 0;
		for (String savemxId : idArr) {
			SaveBillInfo bill = this.getSaveBillInfo(savemxId);
			bill.setOperStatus(StatusUtils.handleStatus(conName,methodName,null,bill.getOperStatus()));
			rs = this.saveBillDao.modifySaveBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
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
	public int totalCount(SaveBillInfo bill) throws SQLException {
		return saveBillDao.totalCount(bill);
	}

	/**
	 * 功能描述：查询批次下该status状态的清单
	 * @param saveId
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<SaveBillInfo> getSaveBillForSaveIdAndStatus(String saveId,String status) throws SQLException, Exception{
		//查询批次下该status状态的清单
		return saveBillDao.getSaveBillForSaveIdAndStatus(saveId,status);
	}
	private SaveApplyInfoDao saveApplyDao = new SaveApplyInfoDao();	
	private SaveBillInfoDao saveBillDao = new SaveBillInfoDao();

	@Override
	public SaveBillInfo getSaveBillInfoByRgctId(String rgctId)
			throws SQLException {
		return saveBillDao.getSaveBillInfoByRgctId(rgctId);
	}
	
	public List<SaveBillInfo> getSaveBillListByMxids(String[] ids) throws SQLException{
		return saveBillDao.getSaveBillListByMxids(ids);
	}
}