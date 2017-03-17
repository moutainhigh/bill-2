package com.herongtech.console.service.busiservice.rebuy;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.bean.FacDealResult;
import com.herongtech.console.domain.common.bean.InterestReqDTO;
import com.herongtech.console.domain.common.bean.InterestResultDTO;
import com.herongtech.console.domain.common.bean.ProdLimitType;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.console.domain.rebuy.dao.RebuyApplyInfoDao;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.domain.saleback.dao.SalebackBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.sale.ISaleService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.IInterestService;
import com.herongtech.console.service.interfaces.ISaleBackService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctBillInfoService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.service.rcservice.IRcRebuyService;
import com.herongtech.sysconstant.ISysErrorNo;

public class RebuyService implements IRebuyService {

	private RebuyBillInfoDao infoDao = new RebuyBillInfoDao();
	private RebuyApplyInfoDao applyDao = new RebuyApplyInfoDao();
	
	@Override
	public RebuyApplyInfo getApplyInfoById(String rebuyId) throws SQLException {
		return applyDao.getRebuyApplyInfo(rebuyId);
	}
	
	@Override
	public RebuyApplyInfo getApplyInfoById(String rebuyId,RebuySearchBean searchBean) throws SQLException {
		return applyDao.getRebuyApplyInfo(rebuyId,searchBean);
	}

	/**
	 * 新增批次信息
	 * @throws SQLException 
	 */
	@Override
	public void addBatch(RebuyApplyInfo apply) throws Exception {
		ISequenceService seqService = ServiceFactory.getSequenceService();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		IProductService prodService = ServiceFactory.getProductService();
		ProdLimitType prodLimType = prodService.getProdLimitTypeByProdNo(apply.getProdNo());
		if(prodLimType==null){
			throw new BizAppException("产品类型未配置：["+apply.getProdNo()+"]");
		}
		apply.setDiscType(prodLimType.getBuyType());
		apply.setRebuyId(seqService.getREBUY_APPLY_ID());
		apply.setBatchNo(seqService.getRebuyApplyNo(user.getBranchNo()));
		apply.setIsInner(RebuyCodeConst.IS_INNER_FALSE);
		apply.setRateType("360");//年利率
		apply.setBranchNo(user.getBranchNo());
		apply.setCustName(apply.getCustBankName());
		apply.setCustNo(apply.getCustBankNo());
		apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		apply.setCreateDt(DateTimeUtil.getWorkdayString());
		apply.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		try {
			if(applyDao.addRebuyApplyInfo(apply) != 1){
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RebuyApplyInfo失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}

	/**
	 * 修改批次信息
	 * @throws BizAppException 
	 */
	@Override
	public void modifyBatch(RebuyApplyInfo apply) throws Exception {
		RebuyApplyInfo srcApply;
		String rebuyId = apply.getRebuyId();
		srcApply = applyDao.getRebuyApplyInfo(rebuyId);
		//判断转入日跟利息是否修改，若修改，则将票据清单利息相关字段清零，需重新计算利息
		if((!apply.getRebuyDt().equals(srcApply.getRebuyDt()))||(!(apply.getRate()==srcApply.getRate()))){
			RebuySearchBean searchBean = new RebuySearchBean();
			searchBean.setRebuyId(rebuyId);
			searchBean.setDfaultSrchTbAliasName("bill");
			List<RebuyBillInfo> billList = infoDao.getRebuyBillListBySearchBean(searchBean);
			for(RebuyBillInfo bill : billList){
				bill.setInterest(0);
				bill.setPayMoney(0);
				bill.setGaleDate(null);
				bill.setInterestDays(null);
				bill.setDelayDays(null);
				bill.setDelayType(null);
				infoDao.modifyRebuyBillInfo(bill);
			}
		}
		srcApply.setCustManage(apply.getCustManage());
		srcApply.setCustManagerName(apply.getCustManagerName());
		srcApply.setDeptName(apply.getDeptName());
		srcApply.setBillType(apply.getBillType());
		srcApply.setProdNo(apply.getProdNo());
		srcApply.setRebuyDt(apply.getRebuyDt());
		srcApply.setTradeAcct(apply.getTradeAcct());
		srcApply.setTradeAcctName(apply.getTradeAcctName());
		srcApply.setRate(apply.getRate());
		srcApply.setCbRate(apply.getCbRate());
		try {
			if(applyDao.modifyRebuyApplyInfo(srcApply) != 1){
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改RebuyApplyInfo失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}

	/**
	 * 功能描述：根据条件查询批次列表(审核 复核记账使用)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<RebuyApplyInfo> getRebuyApplyListBySearchBean(Page page,
			RebuySearchBean searchBean) throws SQLException {
	    return applyDao.getRebuyApplyListBySearchBean(page,searchBean);
	}

	
	/**
	 * 根据批次id查询该批次下的票据张数
	 */
	@Override
	public int getBillCountByRebuyId(String rebuyId) throws SQLException {
		return infoDao.getBillCountByRebuyId(rebuyId);
	}

	/**
	 * 根据批次ids删除批次信息
	 */
	@Override
	public int deleteBatchesByRebuyId(String[] ids,String status) throws SQLException {
		return applyDao.updateApplyStatusByIds(ids,status);
	}

	@Override
	public List<RebuyBillInfo> getRebuyBillListBySearchBean(
			RebuySearchBean searchBean, Page page) throws SQLException {
		searchBean.setDfaultSrchTbAliasName("bill");
		searchBean.addSpecialOpertion("mxIds",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("mxIds", "rebuymxId");
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		OrderBean order = new OrderBean("rebuymxId",false);
		searchBean.appendOrder(order);
		return infoDao.getRebuyBillListBySearchBean(searchBean, page);
	}

	/**
	 * 根据清单id查询清单信息
	 */
	@Override
	public RebuyBillInfo getBillInfoByRebuymxId(String rebuymxId)
			throws SQLException {
		return infoDao.getRebuyBillInfo(rebuymxId);
	}

	
	
	/**
	 * 申请提交
	 */
	@Override
	public int applySubmit(String ids,String rebuyId) throws Exception {
		int rs = 0;
		if(StringUtils.isBlank(ids)) return rs;
		String[] idArr = ids.split(",");
//		if(!infoDao.isInterestCalculated(idArr)) return -999;//如果没有利息试算返回-999 提示前台进行利息试算
		UserLoginfo userInfo=ResourceUtil.getSessionLoginfo();
		String[] curStatus = StatusUtils.queryStatus("RebuyApplyController", "applySubmit", null);
		double auditAmt = 0.0;
		List<RebuyBillInfo> billList = infoDao.getRebuyBillListByIds(idArr);
		RebuyApplyInfo applyinfo = applyDao.getRebuyApplyInfo(rebuyId);
		//扣减额度
		FacDealResult result = ServiceFactory.getRebuyFacService().dealFac(applyinfo, billList, userInfo, CommonConst.FAC_OPER_OCCUPY);
		//更新清单:只有额度都扣减成功的才能向下执行
		result.checkFacResult(CommonConst.FAC_OPER_OCCUPY);
		for(RebuyBillInfo bill:billList){
			String afterStatus = StatusUtils.handleStatus("RebuyApplyController", "applySubmit", null,bill.getOperStatus());
			bill.setOperStatus(afterStatus);
			bill.setApplyOperNo(userInfo.getUserId());
			//承兑行号
			bill.setAcceptorBankNo(bill.getRemitterBankNo());
			bill.setApplyCommitDate(DateTimeUtil.getWorkdayString());
			bill.setApplyCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			auditAmt += bill.getBillMoney();
			rs = infoDao.modifyRebuyBillInfo(bill);
			if( rs <= 0 ){//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		/*IFmsAgentService service = ServiceFactory.getFmsAgentService();
		service.acceptorBankCredit(applyinfo,CommonConst.FAC_SOURCE_REBUY, billList,userInfo);*/
		if(rs>0){
			int count = this.getInfoCountByRebuyIdAndStatus(rebuyId,curStatus);
			if(count==0){
				this.changeApplyStatusById(rebuyId, CommonConst.APPLY_STATUS_SUBMIT);
			}
			//审批流程任务创建
		    AuditTask auditTask = new AuditTask();
            auditTask.setWaitAuditAmt(auditAmt); 
            auditTask.setAtAuthorName(userInfo.getUserName()); 
            auditTask.setAtAuthorId(userInfo.getUserId()); 
            auditTask.setBrchNo(userInfo.getBrchNo()); 
            auditTask.setAtCreateDt(DateTimeUtil.getWorkdayString()); 
            auditTask.setAtCreateTime(DateTimeUtil.getTime());  
            auditTask.setProdNo(applyinfo.getProdNo());
            auditTask.setBatchNo(applyinfo.getBatchNo()); 
            auditTask.setBatchId(rebuyId);
            auditTask.setEntityName("rebuyAuditController.do?method=auditDetailList");
            auditTask.setEntityService("rebuyId");
		    ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
		}
		return rs;
	}
	
	/**是否计算过利息*/
	@Override
	public boolean israte(String ids)throws Exception{
		if(StringUtils.isBlank(ids)) return false;
		String[] idArr = ids.split(",");
		return infoDao.isInterestCalculated(idArr);
	}

	/**
	 * 审核提交
	 */
	@Override
	public int auditSubmit(String ids,String rebuyId, String status,String reason) throws Exception {
		int rs = 0;
		if(StringUtils.isBlank(ids)) return rs;
		String[] idArr = ids.split(",");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		List<RebuyBillInfo> rebuyBills = infoDao.getRebuyBillListByIds(idArr);
		if("0".equals(status)){
			this.changeApplyStatusById(rebuyId, CommonConst.APPLY_STATUS_NEW);
			RebuyApplyInfo apply = applyDao.getRebuyApplyInfo(rebuyId);
			//审核拒绝，释放额度
			FacDealResult result = ServiceFactory.getRebuyFacService().dealFac(apply, rebuyBills, user, CommonConst.FAC_OPER_RELEASE);
			//只有额度都扣减成功的才能向下执行
			result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
		}
		//更新清单:
		for(RebuyBillInfo bill:rebuyBills){
			String afterStatus = StatusUtils.handleStatus("RebuyAuditController", "auditSubmit", status, bill.getOperStatus());
			bill.setAuditOperNo(user.getUserId());
			bill.setOperStatus(afterStatus);
			bill.setAuditReason(reason);
			rs = infoDao.modifyRebuyBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}
	
	/**电票转入审核通过*/
	@Override
	public int elecauditSubmit(String ids, String rebuyId, String status,
			String reason) throws Exception {
		int rs = 0;
		if(StringUtils.isBlank(ids)) return rs;
		String[] idArr = ids.split(",");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		List<RebuyBillInfo> rebuyBills = infoDao.getRebuyBillListByIds(idArr);
		if("0".equals(status)){
			this.changeApplyStatusById(rebuyId, CommonConst.APPLY_STATUS_NEW);
			RebuyApplyInfo apply = applyDao.getRebuyApplyInfo(rebuyId);
			//审核拒绝，释放额度
			FacDealResult result = ServiceFactory.getRebuyFacService().dealFac(apply, rebuyBills, user, CommonConst.FAC_OPER_RELEASE);
			result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
		}
		//更新清单信息
		for(RebuyBillInfo bill : rebuyBills){
			String afterStatus = StatusUtils.handleStatus("RebuyAuditController", "auditSubmitElec", status, bill.getOperStatus());
			bill.setAuditOperNo(user.getUserId());
			bill.setOperStatus(afterStatus);
			bill.setAuditReason(reason);
			rs = infoDao.modifyRebuyBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}
	
	/**
	 * 复核提交
	 */
	@Override
	public int reauditSubmit(String ids,String status) throws Exception {
		int rs = 0;
		if(StringUtils.isBlank(ids)) return rs;
		String[] idArr = ids.split(",");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		for (String id : idArr) {
			RebuyBillInfo bill = this.getRebuyBillInfo(id);
			String afterStatus = StatusUtils.handleStatus("RebuyAccountController", "reauditSubmit", status, bill.getOperStatus());
			bill.setAuditOperNo(user.getUserId());
			bill.setOperStatus(afterStatus);
			rs = infoDao.modifyRebuyBillInfo(bill);
			if( rs <= 0 ){//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}

	/**
	 * 记账提交
	 */
	@Override
	public int accountSubmit(String ids,String rebuyId) throws Exception {
		ISaleBackService salebackservice = ServiceFactory.getSaleBackService();
		List<RebuyBillInfo>  rebuylist = new ArrayList<RebuyBillInfo>();//返售纸票初始化所用
		int rs = 0;
		long start=System.currentTimeMillis();
		if(StringUtils.isBlank(ids)) return rs;
		String[] idArr = ids.split(",");
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		List<RebuyBillInfo> billList = infoDao.getRebuyBillListByIds(idArr);
		for (int i = 0; i < billList.size(); i++) {
			RebuyBillInfo bill = billList.get(i);
			String afterStatus = StatusUtils.handleStatus("RebuyAccountController", "accountSubmit", null, bill.getOperStatus());
			bill.setAcctOperNo(user.getUserId());
			bill.setOperStatus(afterStatus);
			bill.setAccountDate(DateTimeUtil.getWorkdayString());
			bill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			bill.setGathType(RcConstants.GATH_TYPE_COMMON);
			bill.setGathDate("");
			bill.setTotalIntrstPayment(0);
			bill.setTransType(CommonConst.NORMAL);
			bill.setTransId("");
			if("0".equals(bill.getIsRegress())){
				bill.setCurStatus(RebuyCodeConst.CUR_STATUS_REBUY);
			}else{
				bill.setCurStatus(RebuyCodeConst.CUR_STATUS_REBUY_REDEEM);
				rebuylist.add(bill);
			}
			rs = infoDao.modifyRebuyBillInfo(bill);
			if( rs <= 0 ){//如果有执行失败的直接返回失败
				return rs;
			}
		}
		
		IAccountFacadeService acctService = ServiceFactory.getRebuyAccountService();
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		RebuyApplyInfo apply = applyDao.getRebuyApplyInfo(rebuyId, null);
		
		AccountRequestDTO<RebuyApplyInfo,RebuyBillInfo> accountReq = new AccountRequestDTO<RebuyApplyInfo,RebuyBillInfo>();
		accountReq.setApply(apply);
		accountReq.setBillList(billList);
		accountReq.setUserLogonInfo(userInfo);
		acctService.account(accountReq);
		long end=System.currentTimeMillis();
		System.err.println("转入记账"+billList.size()+"耗时:"+String.valueOf(end-start));
		salebackservice.initsalebackbill(rebuylist,apply);
		return rs;
	}
	
	/**
	 * 入库提交
	 * @throws SQLException 
	 */
	@Override
	public int storageSubmit(String ids,String rebuyId) throws Exception {
		long start=System.currentTimeMillis();
		int rs = 0;
		if(StringUtils.isBlank(ids)){
			return rs;
		}
		String[] idArr = ids.split(",");
		RebuyApplyInfo apply = applyDao.getRebuyApplyInfo(rebuyId);
		IBranchService brchService = ServiceFactory.getBranchService();
		Branch brch = brchService.getBranch(apply.getBranchNo());
		List<RebuyBillInfo> billList=infoDao.getRebuyBillListByIds(idArr);
		String rcIds="";
		Map<String, RgctBill> rcMap=new HashMap<String, RgctBill>();
		for (int i = 0; i <billList.size(); i++) {
			RebuyBillInfo bill=billList.get(i);
			rcIds=rcIds+bill.getRgctId()+",";
		}
		IRcRebuyService rcService = RcServiceFactory.getRcRebuyService();
		List<RgctBill> rcBillList=rcService.getRgctBillList(rcIds);
		for (int i = 0; i <rcBillList.size(); i++) {
			RgctBill rc=rcBillList.get(i);
			rcMap.put(rc.getInfo().getId(), rc);
		}
		for (int i = 0; i <billList.size(); i++) {
			RebuyBillInfo bill =billList.get(i);
			String afterStatus = StatusUtils.handleStatus("RebuyStorageController", "storageSubmit", null,bill.getOperStatus());
			bill.setOperStatus(afterStatus);
			rs = infoDao.modifyRebuyBillInfo(bill);
			if( rs <= 0 ){//如果有执行失败的 直接返回失败
				return rs;
			}
			String rgctId = bill.getRgctId();
			RgctBill rgctBill =rcMap.get(rgctId);
			initRgct(rgctBill,apply,bill,brch);
			rcService.rebuyEndorseSign(rgctBill);
		}
		long end=System.currentTimeMillis();
		System.err.println("转入入库"+billList.size()+"耗时:"+String.valueOf(end-start));
		return rs;
	}

	public RgctBill initRgct(RgctBill rgctBill, RebuyApplyInfo apply,
			RebuyBillInfo rebuyBill,Branch brch) throws Exception {
		RgctBillHist hist = rgctBill.getHist();
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(apply.getBillClass())){
			hist.setIsLock(CommonConst.LOCK_NO);//发报文之前手工解锁
			hist.setToOrgcode(brch.getOrgCode());
			hist.setToRole(brch.getPartnerType());
			hist.setSignerSign( CommUtils.getSignerSign(rgctBill.getHist().getToBankNo()));
		}else{
			hist.setToName(brch.getBranchName());
			hist.setToBankNo(brch.getPayBankNo());
			hist.setToBranchNo(brch.getBranchNo());
			hist.setToRole(brch.getPartnerType());
			hist.setToOrgcode(brch.getOrgCode());
		}
		hist.setBillBeforeOwner(apply.getCustName());
		hist.setAcctBranchNo(apply.getBranchNo());//账务机构
		hist.setStorageBranchNo(apply.getBillStorageOrg());//实物保管机构
		hist.setStorageBranchName(apply.getBillStorageOrgName());//实物保管机构
//		if(apply.isElecDeposit()){//是否满足电票托管条件
//			hist.setBuyType(BillConst.BUY_ELEC_DEPOSIT);
//		}else{
			IProductService prodService = ServiceFactory.getProductService();
			//买入类型
			ProdLimitType plt=prodService.getProdLimitTypeByProdNo(apply.getProdNo());
			if(plt != null){
				hist.setBuyType(plt.getBuyIntoType());
			}
//		}
		hist.setHoldAcctNo("0");//当前持有人帐号
		hist.setSignDt(DateTimeUtil.getWorkdayString());
		hist.setSignFlag(IConstants.YES);
		hist.setBatchId(apply.getBatchNo());//转入批次号
		hist.setProdAttr(apply.getProdAttr());
//		hist.setProdNo(apply.getProdNo());
		hist.setWorkingadsNo(apply.getProfOwnerNo());
		hist.setWorkingadsName(apply.getProfOwner());
		//业务所属
		hist.setObligeeAcctNo(apply.getTradeAcct());
		hist.setObligeeBankNo(apply.getCustBankNo());
		hist.setObligeeCustNo(brch.getBranchName());
		
		//利息相关
		initRgctInterestInfo(apply, rebuyBill, hist);
		
		if(apply.isOutterPaper()){
			hist.setIfInner(apply.getIsInner());   //是否为系统内
			hist.setIsBuy(CommonConst.BUY_YES);         //是否买入
			hist.setIsRegress(apply.getDiscType()); //回购标记(0:买断不回购;1.回购)
			hist.setOperTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));//操作时间
			hist.setIsOnline(apply.getIsOnline());	    //线上清算标识
			hist.setInAcctNo(apply.getTradeAcct());		//入帐帐号
		}
		
		return rgctBill;
	}
	
	
	private void initRgctInterestInfo(RebuyApplyInfo app, RebuyBillInfo bill,RgctBillHist hist) {
		hist.setIsSameCity(bill.getIsSameCity());
		hist.setBackOpenDt(app.getResaleStaDt());
		hist.setBackEndDt(app.getResaleDueDt());
//		if(app.getSalebackRate()!=null){
			hist.setBackRate(MathScaleUtil.divide(app.getSalebackRate(),100));
//		}else{
//			hist.setBackRate(0d);
//		}
		hist.setBackAmount(app.getSalebackMoney());
		hist.setDelayDays(String.valueOf(bill.getDelayDays()));
		hist.setDelayType(bill.getDelayType());
		hist.setInterestDays(bill.getInterestDays());
		hist.setInterestDueDt(bill.getGaleDate());
		hist.setEndorseDt(app.getRebuyDt());
		hist.setInterest(bill.getInterest());
		hist.setInterestRate(MathScaleUtil.divide(app.getRate(), 100));
		hist.setDealMoney(bill.getPayMoney());
		hist.setOldInterestRate(MathScaleUtil.divide(app.getRate(), 100));
		hist.setOldDisDt(bill.getAccountDate());
	}

	/**
	 * 批次列表查询（转入申请专用）
	 */
	@Override
	public List<RebuyApplyInfo> getRebuyApplyListBySearchBeanOnlyForApply(
			Page page, RebuySearchBean searchBean) throws Exception {
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		searchBean.setDfaultSrchTbAliasName("apply");
		searchBean.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		searchBean.setBranchNo(user.getBranchNo());
		searchBean.setBillClass(CommonConst.BILL_CLASS_ENTITY);//纸票
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		searchBean.addProperty2TableObj("opers", "bill");
		OrderBean order = new OrderBean("rebuyId",false);
		searchBean.appendOrder(order);
		searchBean.setOpers(StatusUtils.queryStatus("RebuyApplyController", "searchApplyBatch", null));
		return applyDao.getRebuyApplyListBySearchBeanOnlyForApply(page, searchBean);
	}

	@Override
	public int getInfoCountByRebuyIdAndStatus(String rebuyId, String[] curStatus) throws SQLException {
		return applyDao.getInfoCountByRebuyIdAndStatus(rebuyId,curStatus);
	}

	@Override
	public int changeApplyStatusById(String rebuyId, String status) throws SQLException {
		return applyDao.changeApplyStatusById(rebuyId, status);
	}

	@Override
	public RebuyApplyInfo getRebuyApplyInfo(String rebuyId) throws SQLException {
		return applyDao.getRebuyApplyInfo(rebuyId);
	}
	
	@Override
	public RebuyBillInfo getRebuyBillInfo(String rebuymxId) throws SQLException {
		return infoDao.getRebuyBillInfo(rebuymxId);
	}

	/**
	 * 修改清单信息
	 * @param bill
	 * @throws BizAppException 
	 * @throws SQLException 
	 */
	@Override
	public void modRebuyBillInfo(String billId, RebuyBillInfo bill) throws Exception {
		RebuyBillInfo srcBill;
		try {
			srcBill = infoDao.getRebuyBillInfo(billId);
		} catch (SQLException e1) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询待修改RebuyBillInfo信息失败");
		}
		//遍历bill对象的所有页面属性，并赋值给sourceBill
		srcBill.setIssueDt(bill.getIssueDt());
		srcBill.setBillNo(bill.getBillNo());
		srcBill.setRemitter(bill.getRemitter());
		srcBill.setPayee(bill.getPayee());
		srcBill.setRemitterAcct(bill.getRemitterAcct());
		srcBill.setPayeeAcct(bill.getPayeeAcct());
		srcBill.setRemitterBankName(bill.getRemitterBankName());
		srcBill.setPayeeBankName(bill.getPayeeBankName());
		srcBill.setBillMoney(bill.getBillMoney());
		srcBill.setDueDt(bill.getDueDt());
		srcBill.setRemitterBankNo(bill.getRemitterBankNo());
		srcBill.setDraweeAddr(bill.getDraweeAddr());
		srcBill.setAcceptorBankName(bill.getAcceptorBankName());
		srcBill.setIsSameCity(bill.getIsSameCity());
		srcBill.setRemark(bill.getRemark());
		srcBill.setBillBeforeOwner(bill.getBillBeforeOwner());
		srcBill.setInterest(0);
		srcBill.setPayMoney(0);
		srcBill.setGaleDate(null);
		srcBill.setInterestDays(null);
		srcBill.setDelayDays(null);
		srcBill.setDelayType(null);
		if (infoDao.modifyRebuyBillInfo(srcBill) != 1) {
		      throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改RebuyBillInfo失败");
		}
		
	}

	/**
	 * 新增清单信息
	 * @param bill
	 * @throws BizAppException 
	 * @throws Exception 
	 */
	@Override
	public void addRebuyBillInfo(RebuyBillInfo bill,String batchId) throws Exception {
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		RebuyApplyInfo apply = this.getApplyInfoById(batchId);
		bill = this.createRebuyBillBy(bill, apply, user);
		IRcRebuyService rcService = RcServiceFactory.getRcRebuyService();
		//在登记中心进行登记
		RgctBillInfo info = new RgctBillInfo();
		RgctBillHist hist = new RgctBillHist();
		
		RgctBill rgctBill = new RgctBill(info,hist);
		rgctBill = this.initRgctBill(apply, bill, user, rgctBill);
		rgctBill = rcService.inputSignRebuy(rgctBill);
		//本地保存
		bill.setRgctId(rgctBill.getInfo().getId());
		try {
			if (infoDao.addRebuyBillInfo(bill) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RebuyBillInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}

	/**
	 * 创建转入清单
	 * @param bill
	 * @param apply
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public RebuyBillInfo createRebuyBillBy(RebuyBillInfo bill, RebuyApplyInfo apply,
			UserLoginfo user) throws Exception{
		ISequenceService seqService = ServiceFactory.getSequenceService();
		
		bill.setRebuyId(apply.getRebuyId());
		bill.setRebuymxId(seqService.getREBUY_BILL_INFO_ID());
		bill.setRebuyDt(apply.getRebuyDt());
		bill.setYzSource("0");
		
		bill.setIsRediscCenter("0");
		bill.setIsRegress(apply.getDiscType());
		
		bill.setBranchNo(user.getBranchNo());
		bill.setCreateDate(DateTimeUtil.getWorkdayString());
		bill.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		bill.setBillClass(CommonConst.BILL_CLASS_ENTITY);// 实物票
		
		//交易对手
		bill.setBillOwner(apply.getCustName());
		bill.setCustBankNo(apply.getCustBankNo());
		bill.setCustBankName(apply.getCustBankName());
		bill.setCustOrgCode(apply.getCustOrgCode());
		bill.setCustNo(apply.getCustNo());

		EcdsBankData ecds = RcServiceFactory.getEcdsBankDataService().getEcdsBankData(bill.getRemitterBankNo());
		if(ecds == null) throw new BizAppException("找不到出票人开户行行号信息");
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){//如果是银票
			//设置承兑行信息，承兑行即付款行
			bill.setAcceptor(ecds.getActorFullCall());
			bill.setAcceptorBankNo(ecds.getRowNumber());
			bill.setAcceptorBankName(ecds.getActorFullCall());
			if(CommUtils.isSelfBank(bill.getRemitterBankNo())){//判断是否本行承兑
				bill.setIsAccpt(RebuyCodeConst.SELF_ACPT_YES);
			}else{
				bill.setIsAccpt(RebuyCodeConst.SELF_ACPT_NO);
			}
		}else{
			bill.setIsAccpt(RebuyCodeConst.SELF_ACPT_NO);
		}
		bill.setOperStatus(StatusUtils.handleStatusNoCheck("RebuyApplyController", "saveBill", null));
		bill.setBillSource(RebuyCodeConst.BILL_SOURCE_ENTRY);
		bill.setIsInner(RebuyCodeConst.IS_INNER_FALSE);//系统外才需要录票
		bill.setIsOnline(CommonConst.ONLINE_NOT);
		bill.setForbidFlag(CommonConst.ENDORSE_FORBID_NO);//禁止背书标记
		bill.setIsBuyback("0");
		bill.setApplyCancelFlag(RebuyCodeConst.APPLY_CANCEL_FLAG_NO);//申请撤销标志
			
		return bill;
	}
	
	/**
	 * 根据转入信息初始化RC信息
	 * @param app
	 * @param bill
	 * @param user
	 * @param rgctBill
	 * @return
	 */
	public  RgctBill initRgctBill(RebuyApplyInfo app, RebuyBillInfo bill,UserLoginfo user,RgctBill rgctBill) {
		
		RgctBillInfo info = rgctBill.getInfo();
		info.setBillType(bill.getBillType());
		info.setBillClass(bill.getBillClass());
		info.setAcceptorDate(bill.getIssueDt());
		info.setIssueDt(bill.getIssueDt());
		info.setDueDt(bill.getDueDt());
		info.setBillNo(bill.getBillNo());
		info.setBillMoney(bill.getBillMoney());
		
		info.setDraweeBankName(bill.getRemitterBankName());
		info.setDraweeBankNo(bill.getRemitterBankNo());
		info.setDraweeAddr(bill.getDraweeAddr());
		
		info.setRemitter(bill.getRemitter());
		info.setRemitterAcct(bill.getRemitterAcct());
		info.setRemitterBankName(bill.getRemitterBankName());
		info.setRemitterBankNo(bill.getRemitterBankNo());
		
		//承兑人
		info.setAcceptor(bill.getAcceptor());
		info.setAcceptorBankNo(bill.getAcceptorBankNo());
		info.setAcceptorBankName(bill.getAcceptorBankName());
		
		info.setPayeeName(bill.getPayee());
		info.setPayeeAcct(bill.getPayeeAcct());
		info.setBillType(bill.getBillType());
		info.setPayeeBankName(bill.getPayeeBankName());
		info.setIsAccpt(bill.getIsAccpt());
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			info.setAcceptorBankNo(bill.getRemitterBankNo());
		}
		info.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		info.setRemark(bill.getRemark());
		
		RgctBillHist hist = rgctBill.getHist();
		hist.setRgctId(info.getId());             //票据中心主ID
		hist.setFromName(app.getCustName());         //背书人全称
		hist.setFromCustNo(app.getCustNo());       //背书人客户号
		hist.setFromAcctNo(app.getCustAccount());		 //背书人帐号
		hist.setFromBankNo(app.getCustBankNo());       //背书人行号
		hist.setFromOrgcode(app.getCustOrgCode());
		
		hist.setToName(user.getBranchName());           //被背书人全称
		hist.setToAcctNo("0");		 //被背书人帐号
		hist.setToBankNo(user.getBrchBankNo());         //被背书人行号
		hist.setToBranchNo(user.getBranchNo());         //被背书人机构号
		hist.setEndorseDt(app.getRebuyDt());          //背书日期
		hist.setIfInner(app.getIsInner());          //是否为系统内
		hist.setIsBuy(CommonConst.BUY_YES);            //是否买入
		hist.setIsRegress(app.getDiscType()); //回购标记/追索标记(0:买断不回购;1.回购)
		hist.setBackOpenDt(app.getResaleStaDt());		 //回购开放日
		hist.setBackEndDt(app.getResaleDueDt());			 //回购截止日
		hist.setHoldCustName(user.getBranchName());     //当前持有人全称
		hist.setHoldAcctNo("0");       //当前持有人帐号
		hist.setHoldBankNo(user.getBrchBankNo());		//当前持有人行号
		hist.setObligeeCustNo(app.getCustNo());	//当前权利人客户号
		hist.setObligeeAcctNo(app.getCustAccount());       //当前权利人帐号
		hist.setObligeeBankNo(app.getCustBankNo());		//当前权利人行号
		hist.setOperTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));           //操作时间
		hist.setOperNo(user.getUserNo());			 //操作员号
		hist.setIsOnline(app.getIsOnline());	     //线上清算标识
		hist.setInAcctNo(app.getTradeAcct());		//入帐帐号
		hist.setForbidFlag(bill.getForbidFlag());		//禁止背书标记
		
		return rgctBill;
	}

	/**
	 * 批量计算利息
	 */
	@Override
	public void calculateInterest(String[] ids, String isSameCity, String delayType, String delayDays) throws Exception {
		List<RebuyBillInfo> rebuyBillList = null;
		RebuyApplyInfo rebuyApply = null;
		if(ids!=null && ids.length>0){
			rebuyBillList = infoDao.getRebuyBillListByIds(ids);
			rebuyApply = applyDao.getRebuyApplyInfo(rebuyBillList.get(0).getRebuyId());
			for(RebuyBillInfo rebuyBill:rebuyBillList){
				rebuyBill.setIsSameCity(isSameCity);
				rebuyBill.setDelayType(delayType);
				rebuyBill.setDelayDays(Long.parseLong(delayDays));
				calculateSingleInterest(rebuyBill, rebuyApply);//调用单个利息试算方法
			}
		}
	}

	/**
	 * 单张票据计算利息
	 * @param rebuyBill
	 * @param rebuyApply
	 * @return
	 * @throws Exception
	 */
	public InterestResultDTO calculateSingleInterest(RebuyBillInfo rebuyBill,RebuyApplyInfo rebuyApply) throws Exception{
		IInterestService interestService = ServiceFactory.getInterestService();
		InterestReqDTO interestDTO = new InterestReqDTO();
		interestDTO.setBeginDate(DateTimeUtil.parseStringToDate(rebuyApply.getRebuyDt()));
		if (RcConstants.REGRESS_YES.equals(rebuyApply.getDiscType())) {// 回购
			interestDTO.setEndDate(DateTimeUtil.parseStringToDate(rebuyApply.getResaleDueDt()));
		} else {
			interestDTO.setEndDate(DateTimeUtil.parseStringToDate(rebuyBill.getDueDt()));
		}
		interestDTO.setAmount(new BigDecimal(rebuyBill.getBillMoney()));
		interestDTO.setRate(new BigDecimal(rebuyApply.getRate()));
		interestDTO.setProductNo(rebuyApply.getProdNo());
		interestDTO.setBillClass(rebuyApply.getBillClass());
		interestDTO.setBillType(rebuyApply.getBillType());
		interestDTO.setDelayDays(rebuyBill.getDelayDays());
		interestDTO.setChargeKind(rebuyBill.getDelayType());
		interestDTO.setRateType(rebuyApply.getRateType());
		interestDTO.setIfSameCity(rebuyBill.getIsSameCity());
		//调用利息试算方法
		InterestResultDTO interestResult = interestService.getInterest(interestDTO);
		//将利息试算的相关信息保存到rebuyBillInfo对象
		rebuyBill.setInterest(interestResult.getInterest().doubleValue());
		double payMoney = MathScaleUtil.subtract(rebuyBill.getBillMoney(), interestResult.getInterest().doubleValue());
		payMoney=MathScaleUtil.round(payMoney, 2);
		rebuyBill.setPayMoney(payMoney);
		rebuyBill.setGaleDate(DateTimeUtil.get_YYYY_MM_DD_Date(interestResult.getGaleDate()));
		rebuyBill.setInterestDays(interestResult.getInterestCalDays());
		infoDao.modifyRebuyBillInfo(rebuyBill);
		return interestResult;
	}

	/**
	 * 批量计算利息
	 */
	@Override
	public void calculateInterestElec(String[] ids, String isSameCity, String delayType, String delayDays) throws Exception {
		List<RebuyBillInfo> rebuyBillList = null;
		RebuyApplyInfo rebuyApply = null;
		if(ids!=null && ids.length>0){
			rebuyBillList = infoDao.getRebuyBillListByIds(ids);
			rebuyApply = applyDao.getRebuyApplyInfo(rebuyBillList.get(0).getRebuyId());
			for(RebuyBillInfo rebuyBill:rebuyBillList){
				rebuyBill.setIsSameCity(isSameCity);
				rebuyBill.setDelayType(delayType);
				rebuyBill.setDelayDays(Long.parseLong(delayDays));
				calculateSingleInterestElec(rebuyBill, rebuyApply);//调用单个利息试算方法
			}
		}
	}

	/**
	 * 单张票据计算利息
	 * @param rebuyBill
	 * @param rebuyApply
	 * @return
	 * @throws Exception
	 */
	public InterestResultDTO calculateSingleInterestElec(RebuyBillInfo rebuyBill,RebuyApplyInfo rebuyApply) throws Exception{
		IInterestService interestService = ServiceFactory.getInterestService();
		InterestReqDTO interestDTO = new InterestReqDTO();
		interestDTO.setBeginDate(DateTimeUtil.parseStringToDate(rebuyApply.getRebuyDt()));
		if (RcConstants.REGRESS_YES.equals(rebuyApply.getDiscType())) {// 回购
			interestDTO.setEndDate(DateTimeUtil.parseStringToDate(rebuyApply.getResaleDueDt()));
		} else {
			interestDTO.setEndDate(DateTimeUtil.parseStringToDate(rebuyBill.getDueDt()));
		}
		interestDTO.setAmount(new BigDecimal(rebuyBill.getBillMoney()));
		interestDTO.setRate(new BigDecimal(rebuyApply.getRate()));
		interestDTO.setProductNo(rebuyApply.getProdNo());
		interestDTO.setBillClass(rebuyApply.getBillClass());
		interestDTO.setBillType(rebuyApply.getBillType());
		interestDTO.setDelayDays(rebuyBill.getDelayDays());
		interestDTO.setChargeKind(rebuyBill.getDelayType());
		interestDTO.setRateType(rebuyApply.getRateType());
		interestDTO.setIfSameCity(rebuyBill.getIsSameCity());
		//调用利息试算方法
		InterestResultDTO interestResult = interestService.getInterest(interestDTO);
		//将利息试算的相关信息保存到rebuyBillInfo对象
		rebuyBill.setCheckInterest(interestResult.getInterest().doubleValue());
		double payMoney = MathScaleUtil.subtract(rebuyBill.getBillMoney(), interestResult.getInterest().doubleValue());
		payMoney=MathScaleUtil.round(payMoney, 2);
		rebuyBill.setCheckPayMoney(payMoney);
		rebuyBill.setGaleDate(DateTimeUtil.get_YYYY_MM_DD_Date(interestResult.getGaleDate()));
		rebuyBill.setInterestDays(interestResult.getInterestCalDays());
		infoDao.modifyRebuyBillInfo(rebuyBill);
		return interestResult;
	}
	
	/**
	 * 根据ids删除清单信息
	 */
	@Override
	public void deleteBillsByRebuymxId(String ids) throws Exception{
		if(StringUtils.isBlank(ids)) throw new BizAppException("清单id不能为空");
		String[] idArr = ids.split(",");
		IRgctBillInfoService rcBillService = RcServiceFactory.getRgctBillInfoService();
		for(String id : idArr){
			RebuyBillInfo bill = this.getRebuyBillInfo(id);
			RgctBillInfo rcBill = rcBillService.getRgctBillInfo(bill.getRgctId());
			rcBill.setDelFlag(IConstants.YES);
			rcBillService.modifyRgctBillInfo(rcBill);
			String status = StatusUtils.handleStatus("RebuyApplyController", "delBill", null, bill.getOperStatus());
			bill.setOperStatus(status);
			bill.setRebuyId(" ");
			bill.setRgctId(" ");
			infoDao.modifyRebuyBillInfo(bill);
		}
	}

	/**
	 * 撤销申请
	 * @throws SQLException 
	 */
	@Override
	public void revokeApply(String ids,String rebuyId) throws Exception {
		if(StringUtils.isBlank(ids)) throw new BizAppException("清单id不能为空");
		RebuyApplyInfo apply = this.getApplyInfoById(rebuyId);
		apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		applyDao.modifyRebuyApplyInfo(apply);
		String[] idArr = ids.split(",");
		List<RebuyBillInfo> billList = infoDao.getRebuyBillListByIds(idArr);
		//撤销申请释放额度
		FacDealResult result = ServiceFactory.getRebuyFacService().dealFac(apply, billList, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_RELEASE);
		result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
		//更新清单
		for(RebuyBillInfo bill : billList){
			String status = StatusUtils.handleStatus("RebuyApplyController", "revokeApply", null, bill.getOperStatus());
			bill.setOperStatus(status);
			infoDao.modifyRebuyBillInfo(bill);
		}
		/*IFmsAgentService service = ServiceFactory.getFmsAgentService();
		service.facReleaseByBillBatch(apply,billList,ResourceUtil.getSessionLoginfo());*/
	}

	/**
	 * 撤销审核
	 */
	@Override
	public void revokeAudit(String ids) throws Exception {
		if(StringUtils.isBlank(ids)) throw new BizAppException("清单id不能为空");
		String[] idArr = ids.split(",");
		for(String id : idArr){
			RebuyBillInfo bill = this.getRebuyBillInfo(id);
			String status = StatusUtils.handleStatus("RebuyAuditController", "revokeAudit", null, bill.getOperStatus());
			bill.setOperStatus(status);
			infoDao.modifyRebuyBillInfo(bill);
		}
	}

	/**
	 * 撤销记账
	 */
	@Override
	public void revokeAccount(String ids,String rebuyId) throws Exception {
		SalebackBillInfoDao salebackdao = new SalebackBillInfoDao();
		if(StringUtils.isBlank(ids)) throw new BizAppException("清单id不能为空");
		String[] idArr = ids.split(",");
		String rgctids = "";
		for(String id : idArr){
			RebuyBillInfo bill = this.getRebuyBillInfo(id);
			rgctids =rgctids+ bill.getRgctId()+",";
			String status = StatusUtils.handleStatus("RebuyAccountController", "revokeAccount", null, bill.getOperStatus());
			bill.setOperStatus(status);
			bill.setGathType(RcConstants.GATH_TYPE_DEFULT);
			bill.setGathDate("");
			bill.setTotalIntrstPayment(0);
			bill.setTransType(CommonConst.NORMAL);
			bill.setTransId("");
			infoDao.modifyRebuyBillInfo(bill);
		}
		IAccountFacadeService acctService = ServiceFactory.getRebuyAccountService();
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		RebuyApplyInfo apply = applyDao.getRebuyApplyInfo(rebuyId, null);
		List<RebuyBillInfo> billList = infoDao.getRebuyBillListByIds(idArr);
		AccountRequestDTO<RebuyApplyInfo,RebuyBillInfo> accountReq = new AccountRequestDTO<RebuyApplyInfo,RebuyBillInfo>();
		accountReq.setApply(apply);
		accountReq.setBillList(billList);
		accountReq.setUserLogonInfo(userInfo);
		acctService.reverseAccount(accountReq);
		List<SalebackBillInfo> salebacklist = salebackdao.getSalebackBillInfolistForRgctId(rgctids.substring(0,rgctids.length()-1));
		for (int i = 0; i < salebacklist.size(); i++) {
			SalebackBillInfo salebackbill = salebacklist.get(i);
			salebackbill.setOperStatus(StatusUtils.handleStatusNoCheck("salebackAccountController", "entityrebuycancelaccountstatus", null));
			salebackdao.modifySalebackBillInfo(salebackbill);
		}
	}

	/**
	 * 根据searchBean查询符合条件的票据张数
	 * @throws SQLException 
	 */
	@Override
	public int getRebuyBillCountBySearchBean(RebuySearchBean searchBean) throws SQLException {
		return infoDao.getRebuyBillCountBySearchBean(searchBean);
	}

	public List<BillInfoDTO> getBillInfoDTO(String ids) throws BizAppException {
		if (StringUtils.isBlank(ids)) {
			return null;
		}
		List<BillInfoDTO> dtoList = null;
		try {
			List<RebuyBillInfo> billList = infoDao.getRebuyBillListByIds(ids.split(","));
			dtoList = new ArrayList<BillInfoDTO>(billList.size());
			for (RebuyBillInfo source : billList) {
				BillInfoDTO dest = new BillInfoDTO();
				dest.setMxId(source.getRebuymxId());
				dest.setBillNo(source.getBillNo());
				dest.setBillType(source.getBillType());
				dest.setBillClass(source.getBillClass());
				dest.setRemitter(source.getRemitter());
				dest.setAcceptor(source.getAcceptor());
				dest.setIssueDt(source.getIssueDt());
				dest.setDueDt(source.getDueDt());
				dest.setBillMoney(source.getBillMoney());
				dest.setRemitterBankNo(source.getRemitterBankNo());
				dest.setRemitterBankName(source.getRemitterBankName());
				dest.setAcceptorBankNo(source.getAcceptorBankNo());
				dest.setCustBankNo(source.getCustBankNo());
				// 银承：黑名单检查承兑行、交易对手、中间背书人
				// 商承：交易对手、中间背书人
				if(StringUtils.isNotBlank(source.getCustBankNo())){
					dest.addCheckBankNo(source.getCustBankNo());
				}
				if(StringUtils.isNotBlank(source.getEndorsesBankNo())){
					dest.addCheckBankNo(source.getEndorsesBankNo());
				}
				if (StringUtils.equals(IDict.K_BILL_TYPE.K_BANK_BILL, source.getBillType())
						&&StringUtils.isNotBlank(source.getAcceptorBankNo())) {
					dest.addCheckBankNo(source.getAcceptorBankNo());
				}
				dtoList.add(dest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	@Override
	public String getBillClassStringbybillclass(String billclass)
			throws BizAppException {
		RebuyApplyInfoDao dao = new RebuyApplyInfoDao();
		String billcs="";
		try {
			billcs = dao.getBillClassStringbybillclass(billclass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return billcs;
	}


	@Override
	public String getBillTypeStringbybilltype(String billtype)
			throws BizAppException {
		RebuyApplyInfoDao dao = new RebuyApplyInfoDao();
		String billtp = "";
		try {
			billtp = dao.getBillTypeStringbybilltype(billtype);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return billtp;
	}

	@Override
	public List<RebuyBillInfo> getRebuyBillListByRebuyIdAndStatus(
			String rebuyId, String[] status) throws Exception {
		RebuySearchBean searchBean = new RebuySearchBean();
		searchBean.setRebuyId(rebuyId);
		searchBean.setOpers(status);
		searchBean.addSpecialOpertion("opers",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("opers", "operStatus");
		OrderBean order = new OrderBean("rebuymxId",false);
		searchBean.appendOrder(order);
		searchBean.setDfaultSrchTbAliasName("bill");
		return infoDao.getRebuyBillListBySearchBean(searchBean);
	}

	@Override
	public int modifyRebuyBillInfo(RebuyBillInfo bill) throws Exception {
		return infoDao.modifyRebuyBillInfo(bill);
	}

	@Override
	public List<RebuyBillInfo> getRebuyBillListBydiscmxIds(String discmxIds)
			throws Exception {
		return infoDao.getEntitytricReceiveForId(discmxIds);
	}

	@Override
	public RebuyBillInfo getRebuyBillInfoByRgctId(String rgctId)
			throws SQLException {
		return infoDao.getRebuyBillInfoByRgctId(rgctId);
	}

	@Override
	public void rejectApplyElec(String ids) throws Exception {
		if(StringUtils.isBlank(ids)) throw new BizAppException("清单id不能为空");
		String[] idArr = ids.split(",");
		List<RebuyBillInfo> billList = infoDao.getRebuyBillListByIds(idArr);
		IRcBaseService rcService = RcServiceFactory.getRcBaseService();
		IRcRebuyService rcRebuyService = RcServiceFactory.getRcRebuyService();
		IBranchService brchServ = ServiceFactory.getBranchService();
		Branch branch  = brchServ.getBranch(billList.get(0).getBranchNo());
		for(RebuyBillInfo bill : billList){
			RgctBill rgctBill = rcService.getRgctBillById(bill.getRgctId());
			RgctBillHist hist = rgctBill.getHist();
			hist.setSignerSign(CommUtils.getSignerSign(hist.getToBankNo()));
			hist.setSignDt(DateTimeUtil.getWorkday());
			hist.setSignFlag(IConstants.NO);
			hist.setToOrgcode(branch.getOrgCode());
			hist.setToRole(branch.getPartnerType());
			hist.setIsLock("0");
			rcRebuyService.rejectRebuyEndorse(rgctBill);
			//FIXME 拒绝加中间状态
//			String afterStatus = StatusUtils.handleStatus("", "", null, bill.getOperStatus());
//			bill.setOperStatus(afterStatus);
			infoDao.modifyRebuyBillInfo(bill);
		}
	}

	@Override
	public List<RebuyBillInfo> getRebuyBillListByIds(String ids) throws SQLException {
		List<RebuyBillInfo> billList = new ArrayList<RebuyBillInfo>();
		if(!StringUtils.isBlank(ids)){
			String[] idArr = ids.split(",");
			billList = infoDao.getRebuyBillListByIds(idArr);
		}
		return billList;
	}

	@Override
	public RebuyApplyInfo saveElecBill(String ids, RebuyApplyInfo batch) throws Exception {
		String[] idArr = ids.split(",");
		List<RebuyBillInfo> billList = infoDao.getRebuyBillListByIds(idArr);
		if(billList == null || billList.isEmpty()){
			throw new BizAppException("未找到对应清单");
		}
		
		ISequenceService seqService = ServiceFactory.getSequenceService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		
		//批次号
		batch.setRebuyId(seqService.getREBUY_APPLY_ID());
		batch.setBatchNo(seqService.getRebuyApplyNo(user.getBranchNo()));
		batch.setRateType("360");//年利率
		batch.setBranchNo(user.getBranchNo());
		
		if(!batch.isRegress()){
			batch.setResaleStaDt(null);// 返售开放日
			batch.setResaleDueDt(null);// 返售截止日
		}
		
		//不代保管
		batch.setIsDummy(RebuyCodeConst.IsDummyYes);
		batch.setBillStorageOrg(user.getBranchNo());
		batch.setBillStorageOrgName(user.getBranchName());

		batch.setBusiBranchNo(user.getBranchNo());
		batch.setBusiBranchName(user.getBranchName());
		batch.setBusiBrchBankNo(user.getBrchBankNo());
		//batch.setCreateTime(DateTimeUtil.getWorkday());
		batch.setCreateDt(batch.getRebuyDt());
		if("00".equals(batch.getProfOwnerNo())){
			batch.setProfOwnerNo(batch.getBusiBranchNo());
			batch.setProfOwner(batch.getBusiBranchName());
		}
		
		applyDao.addRebuyApplyInfo(batch);
		
		saveBillList(billList,batch,user);
		
		return batch;
	}

	public void saveBillList(List<RebuyBillInfo> billList, RebuyApplyInfo batch, UserLoginfo user) throws Exception{
		//关联
		for(RebuyBillInfo bill:billList) {
			bill.setCustType(batch.getCustType());
			bill.setRebuyId(batch.getRebuyId());
//			bill.setBranchNo(user.getBranchNo());
			String afterStatus = StatusUtils.handleStatus("RebuyApplyController", "saveElecBill", null, bill.getOperStatus());
			bill.setOperStatus(afterStatus);
			bill.setApplyOperNo(user.getUserNo());
//			if(batch.isElecDeposit()){
//				bill.setIsElecDeposit(RebuyCodeConst.ELEC_DEPOSIT_YES);
//			}else{
//				bill.setIsElecDeposit(RebuyCodeConst.ELEC_DEPOSIT_NO);
//			}
			infoDao.modifyRebuyBillInfo(bill);
		}
	}
	
	@Override
	public RebuyApplyInfo sumInfo(RebuyApplyInfo batch, String ids) throws SQLException {
		String[] idArr = ids.split(",");
		if(idArr!=null&&idArr.length>0){
			batch = applyDao.sumApplyInfo(batch, idArr);
		}
		return batch;
	}

	@Override
	public List<RebuyBillInfo> checkCancelState(String ids) throws Exception {
		List<RebuyBillInfo> billList = infoDao.findCanceledBillByIds(ids.split(","));
		boolean isCanceled = false;
		if(billList == null || billList.isEmpty()){
			return null ;
		}else{
			isCanceled = true;
		}
		if(isCanceled){
			throw new BizAppException("转卖方["+billList.get(0).getBillOwner()+"]已经撤销转卖申请,请退回到申请岗，在修改申请菜单从批次中移除该票据。 "+
					" 票号：" + Arrays.toString(this.getBillNos(billList)));
		}
		return billList;
	}

	/**
	 * 获取billList中所有billNo 
	 * @param billList
	 * @return
	 */
	private String[] getBillNos(List<RebuyBillInfo> billList) {
		String[] billNoArr = new String[billList.size()];
		for (int i=0;i<billList.size();i++){
			RebuyBillInfo bill = billList.get(i);
			if(bill!=null ){
				billNoArr[i]=bill.getBillNo();
			}
		}
		return billNoArr ;
	}

	@Override
	public String getBillIdsString(List<RebuyBillInfo> billList){
		StringBuffer sb = new StringBuffer();
		for(RebuyBillInfo bill : billList){
			sb.append(bill.getRebuymxId()).append(",");
		}
		return sb.toString();
	}
	
	@Override
	public void applySubmitElec(RebuyApplyInfo batch, String ids) throws Exception{
		RebuyApplyInfo oldApply = applyDao.getRebuyApplyInfo(batch.getRebuyId());
		String[] idArr = ids.split(",");
		List<RebuyBillInfo> billList = infoDao.getRebuyBillListByIds(idArr);
		if(billList == null || billList.isEmpty()){
			throw new BizAppException("未找到对应清单");
		}
//		if(oldApply.isOutterElec()){
//			checkBillStorageType(billList);//多次买卖校验是否已做转出处理
//		}
		FacDealResult result = ServiceFactory.getRebuyFacService().dealFac(oldApply, billList, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_OCCUPY);
		//更新清单:只有额度都扣减成功的才能向下执行
		result.checkFacResult(CommonConst.FAC_OPER_OCCUPY);
		billList = updateBillStatus(oldApply, billList);
	}
	
	/**更新清单状态
	 * @param batch
	 * @param billIds
	 * @return
	 * @throws Exception 
	 */
	private List<RebuyBillInfo> updateBillStatus(RebuyApplyInfo batch, List<RebuyBillInfo> billList) throws Exception {

		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		for (RebuyBillInfo bill: billList) {
			String afterStatus = StatusUtils.handleStatus("RebuyApplyController", "applySubmitElec", null, bill.getOperStatus());
			bill.setOperStatus(afterStatus);
			bill.setApplyOperNo(user.getUserId());
			bill.setApplyCommitDate(DateTimeUtil.getWorkday());
			bill.setApplyCommitTime(DateTimeUtil.getNowDateTime());
//			boolean flag = !"10".equals(batch.getCustType());
			//交易对手类型：4系统内机构，10财务公司或代理行，12系统外同业机构  
			//非财务公司，客户号置为空
//			if(flag){
//				bill.setCustNo(null);
//			}
//			bill.setIsElecDeposit(batch.isElecDeposit()? RebuyCode.ELEC_DEPOSIT_YES : RebuyCode.ELEC_DEPOSIT_NO);
			infoDao.modifyRebuyBillInfo(bill);
		}
		return billList;
	}
		
	/**电票转入撤销审核*/
	@Override
	public void elecrevokeAudit(String ids) throws Exception {
		if(StringUtils.isBlank(ids)) throw new BizAppException("清单id不能为空");
		String[] idArr = ids.split(",");
		for(String id : idArr){
			RebuyBillInfo bill = this.getRebuyBillInfo(id);
			String status = StatusUtils.handleStatus("RebuyAuditController", "revokeAuditElec", null, bill.getOperStatus());
			bill.setOperStatus(status);
			infoDao.modifyRebuyBillInfo(bill);
		}
	}

	@Override
	public void revokeApplyElec(String ids) throws Exception{
		if(StringUtils.isBlank(ids)) throw new BizAppException("清单id不能为空");
//		RebuyApplyInfo apply = this.getApplyInfoById(rebuyId);
//		apply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
//		applyDao.modifyRebuyApplyInfo(apply);
		String[] idArr = ids.split(",");
		List<RebuyBillInfo> rebuyBills = infoDao.getRebuyBillListByIds(idArr);
		if(rebuyBills==null || rebuyBills.isEmpty()){
			throw new BizAppException("查询不到清单信息");
		}
		RebuyApplyInfo apply = applyDao.getRebuyApplyInfo(rebuyBills.get(0).getRebuyId());
		
		//释放额度
		FacDealResult result = ServiceFactory.getRebuyFacService().dealFac(apply, rebuyBills, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_RELEASE);
		result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
		//更新清单
		for(RebuyBillInfo bill:rebuyBills){
			String status = StatusUtils.handleStatus("RebuyApplyController", "revokeApplyElec", null, bill.getOperStatus());
			bill.setOperStatus(status);
			infoDao.modifyRebuyBillInfo(bill);
		}
	}

	@Override
	public RebuyApplyInfo modifyElecBill(String ids, RebuyApplyInfo batch) throws SQLException {
		RebuyApplyInfo oldApp = applyDao.getRebuyApplyInfo(batch.getRebuyId());
		
		oldApp.setCustManage(batch.getCustManage());// //客户经理编号
		oldApp.setCustManagerName(batch.getCustManagerName());// 客户经理名称
		oldApp.setDeptName(batch.getDeptName());// //客户经理所属部门
		oldApp.setDeptNo(batch.getDeptNo());// 客户经理所属部门编号
		if("00".equals(batch.getProfOwnerNo())){
			oldApp.setProfOwnerNo(oldApp.getBusiBranchNo());
			oldApp.setProfOwner(oldApp.getBusiBranchName());
		}
		
		oldApp.setProdAttr(batch.getProdAttr());
		oldApp.setProductAtts(batch.getProductAtts());
		
		oldApp.setCbRate(batch.getCbRate());// 成本利率
		
		//入账账户
		oldApp.setTradeAcct(batch.getTradeAcct());
		oldApp.setTradeAcctName(batch.getTradeAcctName());
		oldApp.setTradeAcctType(batch.getTradeAcctType());
		oldApp.setTradeAcctOrg(batch.getTradeAcctOrg());
		
		applyDao.modifyRebuyApplyInfo(oldApp);
		return oldApp;
	}

	@Override
	public List<RebuyBillInfo> clearInterest(List<RebuyBillInfo> billList) throws SQLException {
		for(RebuyBillInfo info : billList){
			info.setDelayDays(null);
			info.setDelayType(null);
			info.setBeginDate(null);
			info.setGaleDate(null);
			info.setInterestDays(null);
			info.setCheckInterest(0);
			info.setCheckPayMoney(0);
			infoDao.modifyRebuyBillInfo(info);
		}
		return billList;
	}

	@Override
	public void addElecBill(String ids, String rebuyId) throws Exception {
		RebuyApplyInfo batch = this.getRebuyApplyInfo(rebuyId);
		List<RebuyBillInfo> billList = this.getRebuyBillListByIds(ids);
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		this.saveBillList(billList, batch, user);
	}

	@Override
	public void removeElecBill(List<RebuyBillInfo> billList) throws Exception {
		//关联
		for(RebuyBillInfo bill:billList) {
			bill.setCustType("");
			bill.setRebuyId("");
			String afterStatus = StatusUtils.handleStatus("RebuyApplyController", "removeApplyElec", null, bill.getOperStatus());
			bill.setOperStatus(afterStatus);
			bill.setApplyOperNo("");
//			if(batch.isElecDeposit()){
//				bill.setIsElecDeposit("");
//			}else{
//				bill.setIsElecDeposit("");
//			}
			infoDao.modifyRebuyBillInfo(bill);
		}
	}

	@Override
	public List<RebuyBillInfo> elecAccountSubmit(String ids, String rebuyId) throws Exception{
		RebuyApplyInfo batch = this.getApplyInfoById(rebuyId);
		IBranchService branchService = ServiceFactory.getBranchService();
		Branch branch = branchService.getBranch(batch.getBusiBranchNo());
		IRcBaseService rcService = RcServiceFactory.getRcBaseService();
		IRcRebuyService rcRebuyService = RcServiceFactory.getRcRebuyService();
		Map<String,RgctBill> rgctmap = new HashMap<String, RgctBill>();
		IDB session = DBFactory.getDB();
		List<RebuyBillInfo> billList = infoDao.getRebuyBillListByIds(ids.split(","));
		String rgctids = "";
		if(billList.size()==0){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持此操作");
		}
		for (int i = 0; i < billList.size(); i++) {
			rgctids = rgctids + billList.get(i).getRgctId()+",";
		}
		List<RgctBill> rgctlist = rcService.getRgctBillList(rgctids.substring(0,rgctids.length()-1));
		if(rgctlist.size()==0){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持此操作");
		}
		for (int i = 0; i < rgctlist.size(); i++) {
			rgctmap.put(billList.get(i).getRgctId(), rgctlist.get(i));
		}
		for(RebuyBillInfo bill:billList){
			try {
				session.beginTransaction();
				String afterStatus = StatusUtils.handleStatus("RebuyAccountController", "accountSubmitElec", null, bill.getOperStatus());
				bill.setOperStatus(afterStatus);
				infoDao.modifyRebuyBillInfo(bill);
				RgctBill rgctBill = rgctmap.get(bill.getRgctId());
				this.initRgct(rgctBill, batch, bill, branch);
				rcRebuyService.rebuyEndorseSign(rgctBill);
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
		return billList;
	}

	@Override
	public void confirmAccount(RebuyApplyInfo batch, String ids) throws Exception{
		String[] idArr = ids.split(",");
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IRcBaseService rcBaseService = RcServiceFactory.getRcBaseService();
		for (String id : idArr) {
			RebuyBillInfo bill = this.getRebuyBillInfo(id);
			String afterStatus = StatusUtils.handleStatus("RebuyAccountController", "accountConfirmSubmitElec", null, bill.getOperStatus());
			bill.setAcctOperNo(user.getUserId());
			bill.setOperStatus(afterStatus);
			bill.setAccountDate(DateTimeUtil.getWorkdayString());
			bill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			bill.setGathType(RcConstants.GATH_TYPE_COMMON);
			bill.setGathDate("");
			bill.setTotalIntrstPayment(0);
			bill.setTransType(CommonConst.NORMAL);
			bill.setTransId("");
			if("0".equals(bill.getIsRegress())){
				bill.setCurStatus(RebuyCodeConst.CUR_STATUS_REBUY);
			}else{
				bill.setCurStatus(RebuyCodeConst.CUR_STATUS_REBUY_REDEEM);
			}
			infoDao.modifyRebuyBillInfo(bill);
			
			RgctBill rgctBill = rcBaseService.getRgctBillById(bill.getRgctId());
			RgctBillHist hist = rgctBill.getHist();
			if (CommonConst.LOCK_YES.equals(hist.getIsLock())) { // 已经被上锁
				hist.setIsLock(CommonConst.LOCK_NO);
				hist.setLockBranchNo("");
				hist.setLockFlowName("");
				rcBaseService.updateRgctBillHist(hist);
			}
		}
		
		RebuyApplyInfo apply = this.getRebuyApplyInfo(batch.getRebuyId());
		List<RebuyBillInfo> billList = this.getRebuyBillListByIds(ids);
		//若为系统内，则调用转卖的签收方法
		if(apply.isInner()){
			ISaleService saleService = ServiceFactory.getISaleService();
			saleService.agreeForRebuy(billList);
		}
		IAccountFacadeService acctService = ServiceFactory.getRebuyAccountService();
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		AccountRequestDTO<RebuyApplyInfo,RebuyBillInfo> accountReq = new AccountRequestDTO<RebuyApplyInfo,RebuyBillInfo>();
		accountReq.setApply(apply);
		accountReq.setBillList(billList);
		accountReq.setUserLogonInfo(userInfo);
		acctService.account(accountReq);
	}

	/**
	 * 获取billList中所有salemxId
	 * @param billList
	 * @return
	 */
	@Override
	public String[] getSalemxIds(List<RebuyBillInfo> billList) {
		String[] billNoArr = new String[billList.size()];
		for (int i=0;i<billList.size();i++){
			RebuyBillInfo bill = billList.get(i);
			if(bill!=null ){
				billNoArr[i]=bill.getSalemxId();
			}
		}
		return billNoArr ;
	}
	
	@Override
	public String getBillIdsString(String rebuyId) throws SQLException {
		RebuySearchBean searchBean = new RebuySearchBean();
		searchBean.setRebuyId(rebuyId);
		searchBean.setDfaultSrchTbAliasName("bill");
		List<RebuyBillInfo> billList = infoDao.getRebuyBillListBySearchBean(searchBean);
		String billIds = this.getBillIdsString(billList);
		return billIds;
	}

	@Override
	public boolean validStatus(String rebuyId) throws SQLException {
		boolean isSameStatus = true;
		RebuySearchBean searchBean = new RebuySearchBean();
		searchBean.setDfaultSrchTbAliasName("bill");
		searchBean.setRebuyId(rebuyId);
		OrderBean order = new OrderBean("rebuymxId",false);
		searchBean.appendOrder(order);
		List<RebuyBillInfo>  billList = infoDao.getRebuyBillListBySearchBean(searchBean);
		if(billList!=null&&billList.size()!=0){
			String baseStatus = billList.get(0).getOperStatus();
			for (RebuyBillInfo bill : billList) {
				if(!baseStatus.equals(bill.getOperStatus())){
					isSameStatus = false;
					break;
				}
			}
		}
		return isSameStatus;
	}
	
}
