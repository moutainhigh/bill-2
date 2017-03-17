/********************************************
 * 文件名称: DiscService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-8-11
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.busiservice.disc;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.core.util.WebBankUtil;
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.EcdsBillBean;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.bean.FacDealResult;
import com.herongtech.console.domain.common.bean.InterestReqDTO;
import com.herongtech.console.domain.common.bean.InterestResultDTO;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.domain.disc.bean.BillAllInfoBean;
import com.herongtech.console.domain.disc.bean.BillBackInfoBean;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.console.domain.disc.dao.DiscApplyInfoDao;
import com.herongtech.console.domain.disc.dao.DiscBillInfoDao;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.assu.IAssuService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.IBbspProductService;
import com.herongtech.console.service.interfaces.IFmsAgentService;
import com.herongtech.console.service.interfaces.IInterestService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.DiscCodeConst;
import com.herongtech.console.web.busicontroller.common.GuarCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctEndoHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctBillHistService;
import com.herongtech.rc.service.interfaces.IRgctBillInfoService;
import com.herongtech.rc.service.rcservice.IRcDiscService;
import com.herongtech.rc.service.rcservice.IRcEndorseService;
import com.herongtech.rc.service.rcservice.IRcRebuyService;
import com.herongtech.rc.service.rcservice.RcBaseService;
import com.herongtech.rc.service.rcservice.RcDiscService;
import com.herongtech.sysconstant.ISysErrorNo;


public class DiscService implements IDiscService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 利息试算 //批量试算 
	 * @param toDiscBill
	 * @param discApply
	 * @return
	 */
	public int interestTrial(DiscBillInfo discBill,String ids) throws Exception{
		int rs = 0;
		List<DiscBillInfo> billList= this.discBillDao.getElectricReceiveForId(ids);
		//一个批次下的清单批次相同 只取查询一次批次即可
		DiscApplyInfo discApply = this.getDiscApplyInfo(discBill.getDiscId(),null);
		//批量试算
		for(DiscBillInfo toDiscBill : billList){
			toDiscBill.setIsSameCity(discBill.getIsSameCity());
			toDiscBill.setDelayType(discBill.getDelayType());
			toDiscBill.setDelayDays(discBill.getDelayDays());
			//开始为清单利息试算 试算成功返回大于0
			rs = startInterestTrial(toDiscBill, discApply);
			//结果小于0试算失败 全部回滚
			if( rs <= 0 ) return 0;
		}
		return rs;
	}
	
	
	/**
	 * 利息试算 单个清单试算
	 * @param toDiscBill
	 * @param discApply
	 * @return
	 */
	private int startInterestTrial(DiscBillInfo toDiscBill,DiscApplyInfo discApply) throws Exception {
		IInterestService interestService = ServiceFactory.getInterestService();
		InterestReqDTO interestDTO = new InterestReqDTO();
		interestDTO.setBeginDate(DateTimeUtil.parseStringToDate(discApply.getDiscDt()));
		interestDTO.setEndDate(DateTimeUtil.parseStringToDate(toDiscBill.getDueDt()));
		interestDTO.setAmount(new BigDecimal(toDiscBill.getBillMoney()));
		interestDTO.setRate(new BigDecimal(discApply.getRate()));
		interestDTO.setProductNo(discApply.getProdNo());
		interestDTO.setBillClass(discApply.getBillClass());
		interestDTO.setBillType(discApply.getBillType());
		interestDTO.setDelayDays(toDiscBill.getDelayDays());
		interestDTO.setChargeKind(toDiscBill.getDelayType());
		interestDTO.setRateType(discApply.getRateType());
		interestDTO.setIfSameCity(toDiscBill.getIsSameCity());
		
		//进行利息试算
		InterestResultDTO interestResult = interestService.getInterest(interestDTO);
		//根据批次信息设置计算结果（不同的付息方式，设置结果不尽相同）
		setInterestResultToDiscBillInfo(toDiscBill,discApply,interestResult);
		//保存清单试算结果
		return updateDiscBillInfo(toDiscBill);
	}
	
	
	/**
	 * 根据批次信息设置计算结果（不同的付息方式，设置结果不尽相同）
	 * @param toDiscBill
	 * @param discApply
	 * @param interestResult
	 * @return
	 */
	private DiscBillInfo setInterestResultToDiscBillInfo(DiscBillInfo toDiscBill,DiscApplyInfo discApply,InterestResultDTO interestResult) throws Exception{
		//付息类型
		String payType = discApply.getPayType();
		//实付金额：是指 支付给贴现人的实际金额
		double payMoney = MathScaleUtil.subtract(toDiscBill.getBillMoney(), interestResult.getInterest().doubleValue());
		payMoney=MathScaleUtil.round(payMoney, 2);
		//试算结果设置到bean中
		if(DiscCodeConst.PAY_TYPE_BUYER.equals(payType)){
			toDiscBill.setBuyerInterest(Double.valueOf(MathScaleUtil.round(interestResult.getInterest().doubleValue(),2)));
			toDiscBill.setSalerInterest(0.0);
			payMoney=toDiscBill.getBillMoney();
		}else if(DiscCodeConst.PAY_TYPE_SALER.equals(payType)){
			toDiscBill.setBuyerInterest(0.0);
			toDiscBill.setSalerInterest(Double.valueOf(MathScaleUtil.round(interestResult.getInterest().doubleValue(),2)));
		}else if(DiscCodeConst.PAY_TYPE_AGREEMENT.equals(payType)){
			Double buyInterest=caculateBuyInteres(discApply.getBuyPayRate(),interestResult.getInterest());
			buyInterest=MathScaleUtil.round(buyInterest,2);
			Double salerInterest=caculateSaleInteres(buyInterest,interestResult.getInterest());
			salerInterest=MathScaleUtil.round(salerInterest,2);
			toDiscBill.setBuyerInterest(buyInterest);
			toDiscBill.setSalerInterest(salerInterest);
			payMoney=MathScaleUtil.round(MathScaleUtil.subtract(toDiscBill.getBillMoney(),interestResult.getInterest().doubleValue()), 2);
		}else{
			throw new Exception("付息方式为空|付息方式不匹配");
		}
		//if(CommonConst.BILL_CLASS_ELEC.equals(toDiscBill.getBillClass())){
			toDiscBill.setLocalPayMoney(payMoney);
		//}
		toDiscBill.setGaleDate(DateTimeUtil.get_YYYY_MM_DD_Date(interestResult.getGaleDate()));
		toDiscBill.setInterestDays(interestResult.getInterestCalDays());
		toDiscBill.setPayMoney(payMoney);

		return toDiscBill;
	}
	
	/**
	 * 功能描述：根据买方付息比例计算卖方实付利息
	 * @param buyInterest
	 * @param tInterestVale
	 * @return
	 * @throws ServiceException
	 */
	private Double caculateSaleInteres(Double buyInterest,BigDecimal tInterestVale) throws Exception{
		double buyPayInterest = buyInterest.doubleValue();
		double interestVale = MathScaleUtil.round(tInterestVale.doubleValue(), 4);
		double result = MathScaleUtil.subtract(interestVale, buyPayInterest);
		return Double.valueOf(result);
	}
	/**
	 * 功能描述：根据买方付息比例计算买方实付利息
	 * @param tPuyPayRate
	 * @param tInterestVale
	 * @return
	 * @throws ServiceException
	 */
	private Double caculateBuyInteres(Double tPuyPayRate,BigDecimal tInterestVale) throws Exception{
		double buyPayRate = tPuyPayRate.doubleValue();
		double interestVale = MathScaleUtil.round(tInterestVale.doubleValue(), 4);
		double result = MathScaleUtil.multiply(MathScaleUtil.divide(buyPayRate, 100), interestVale);
		return Double.valueOf(result);
	}
	/**
	 * 功能描述:更新清单信息
	 * @param discBill
	 * @return
	 * @throws SQLException 
	 */
	public int updateDiscBillInfo(DiscBillInfo discBill) throws SQLException{
		//保存清单试算结果
		return discBillDao.modifyDiscBillInfo(discBill);
	}
	
	/**
	 * 功能描述：查询清单明细
	 * @param String
	 * @return DiscBillInfo
	 * @throws BizAppException
	 */
	public DiscBillInfo getDiscBillInfo(String discmxId) throws BizAppException{
		try {
			return discBillDao.getDiscBillInfo(discmxId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"查询清单明细失败");
		}
	}
	/**
	 * 功能描述：票据删除
	 * @param ids
	 * @return
	 */
	public int delBill(String ids) throws Exception{
		int rs = 0 ;
		if(StringUtils.isBlank(ids)) return rs;
		for (String id : ids.split(",")) {
			DiscBillInfo bill = this.getDiscBillInfo(id);
			bill.setOperStatus(StatusUtils.handleStatus("DiscApplyController", "delBill", null,bill.getOperStatus()));
			rs = this.discBillDao.modifyDiscBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}
	/**
	 * 功能描述：票据记账
	 * @param ids
	 * @return
	 */
	public int doAccount(String discId, String ids) throws Exception{
		int rs = 0;
		if(StringUtils.isBlank(ids)) return rs;
		String idArr[] = ids.split(",");
		for (String id : idArr) {
			DiscBillInfo bill = this.getDiscBillInfo(id);
			bill.setOperStatus(StatusUtils.handleStatus("DiscAccountController", "doAccount", null,bill.getOperStatus()));
			bill.setAccountDate(DateTimeUtil.getWorkdayString());
			bill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			bill.setAcctOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			bill.doDiscAccount();
			rs = this.discBillDao.modifyDiscBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		IAccountFacadeService acctService = ServiceFactory.getDiscAccountService();
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		DiscApplyInfo apply = discApplyDao.getDiscApplyInfo(discId, null);
		List<DiscBillInfo> billList = discBillDao.getElectricReceiveForId(ids);
		AccountRequestDTO<DiscApplyInfo,DiscBillInfo> accountReq = new AccountRequestDTO<DiscApplyInfo,DiscBillInfo>();
		accountReq.setApply(apply);
		accountReq.setBillList(billList);
		accountReq.setUserLogonInfo(userInfo);
		acctService.account(accountReq);
		return rs;
	}
	/**
	 * 审批提交
	 * @param ids
	 * @param status 是否同意
	 * @param option  审批意见
	 * @return
	 */
	public int auditSubmit(String ids, String status, String reason) throws Exception{
		int rs = 0;
		if(StringUtils.isBlank(ids)) return rs;
		String idArr[] = ids.split(",");
		List<DiscBillInfo> billList = discBillDao.getElectricReceiveForId(ids);
		String discId = discBillDao.getDiscBillInfo(idArr[0]).getDiscId();
		DiscApplyInfo applyinfo = discApplyDao.getDiscApplyInfo(discId,null);
		if("0".equals(status)){
			//审核拒绝：释放申请时占用的额度
			FacDealResult result = ServiceFactory.getDiscFacService().dealFac(applyinfo, billList, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_RELEASE);
			result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
//			ServiceFactory.getFmsAgentService().facReleaseByBillBatch(applyinfo, billList, ResourceUtil.getSessionLoginfo());
		}
		//更新清单信息
		for(DiscBillInfo bill:billList){
			bill.setOperStatus(StatusUtils.handleStatus("DiscAuditController", "audit", status,bill.getOperStatus()));
			bill.setAuditReason(reason);
			bill.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
			bill.setAuditCommitDate(DateTimeUtil.getWorkdayString());
			bill.setAuditCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			rs = this.discBillDao.modifyDiscBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		if("0".equals(status)){
			//查询批次下是否存在没有提交的清单
			List<DiscBillInfo> list = discBillDao.getDiscBillListForDiscIdAndStatus(discId,StatusUtils.queryStatus("DiscApplyController", "searchBatch", null)[0]);
			//如果存在则更新批次状态为未提交
			if( list != null && list.size() > 0 ) {
				//为修改查询批次，所以不用插叙条件
				applyinfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
				rs = discApplyDao.modifyDiscApplyInfo(applyinfo);
			}
		}
		return rs;
	}
	
	/**
	 * 功能描述：票据申请提交
	 * @param ids
	 * @return
	 * @throws Exception 
	 * @throws BizAppException 
	 */
	public int applySubmit(String ids) throws Exception{
		//如果没有利息试算返回-999 提示前台进行利息试算
		if(!discBillDao.isInterestTrial(ids)) return -999;
		int rs = 0;
		double auditAmt = 0.0;
		if(StringUtils.isBlank(ids)) return rs;
		String[] idArr = ids.split(",");
		IFmsAgentService service = ServiceFactory.getFmsAgentService();
		List<DiscBillInfo> billList = discBillDao.getElectricReceiveForId(ids);
		String discId = discBillDao.getDiscBillInfo(idArr[0]).getDiscId();
		//为修改查询批次，所以不用插叙条件
		DiscApplyInfo applyinfo = discApplyDao.getDiscApplyInfo(discId,null);
		//额度扣减
		FacDealResult result = ServiceFactory.getDiscFacService().dealFac(applyinfo, billList, ResourceUtil.getSessionLoginfo(), CommonConst.FAC_OPER_OCCUPY);
		result.checkFacResult(CommonConst.FAC_OPER_OCCUPY);
//		service.acceptorBankCredit(applyinfo,CommonConst.FAC_SOURCE_DISC, billList, ResourceUtil.getSessionLoginfo());
		//更新清单信息
		for(DiscBillInfo bill:billList){
			//修改操作状态
			bill.setOperStatus(StatusUtils.handleStatus("DiscApplyController", "apply", null,bill.getOperStatus()));
			//修改提交时间
			bill.setApplyCommitDate(DateTimeUtil.getWorkdayString());
			bill.setApplyCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			//柜员操作行号
			bill.setOperBankNo(ResourceUtil.getSessionLoginfo().getBrchBankNo());
			//承兑行号
			bill.setAcceptorBankNo(bill.getRemitterBankNo());
			//申请提交柜员
			bill.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			auditAmt += bill.getBillMoney();
			//修改详情信息
			rs = this.discBillDao.modifyDiscBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		if(rs > 0){
			//功能描述：开启审核任务
			startAuditTask(applyinfo,auditAmt);
			//查询批次下是否存在没有提交的清单
			List<DiscBillInfo> list = discBillDao.getDiscBillListForDiscIdAndStatus(discId,StatusUtils.queryStatus("DiscApplyController", "searchBatch", null)[0]);
			//如果不存在则更新批次状态为已提交
			if( list == null || list.size() == 0 ) {
				applyinfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
				rs = discApplyDao.modifyDiscApplyInfo(applyinfo);
			}
		}
		return rs;
	}
	
	/**
	 * 功能描述：开启审核任务
	 * @param applyinfo
	 * @param auditAmt
	 * @throws Exception
	 */
	private void startAuditTask(DiscApplyInfo applyinfo,double auditAmt) throws Exception{
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
        auditTask.setBatchId(applyinfo.getDiscId());
        auditTask.setEntityName("discAuditController.do?method=auditDetailList");
        auditTask.setEntityService("discId");
	    ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
	}
	
	/**
	 * 功能描述：修改清单
	 * @param DiscBillInfo
	 * @return
	 * @throws BizAppException
	 */
	public void modDiscBillInfo(DiscBillInfo bill) throws BizAppException{
		
		try {
			if (discBillDao.modifyDiscBillInfo(bill) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加DiscBillInfo失败");
			}
		} catch (SQLException e) {
	         throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	/**
	 * 功能描述：添加清单
	 * @param discId
	 * @return
	 * @throws SQLException
	 */
	public void addDiscBillInfo(DiscBillInfo bill) throws BizAppException{
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		//出票人行号与付款人行号相同
		bill.setDraweeBankNo(bill.getRemitterBankNo());
		bill.setAcceptorBankNo(bill.getRemitterBankNo());
		EcdsBankData ecds = RcServiceFactory.getEcdsBankDataService().getEcdsBankData(bill.getRemitterBankNo());
		IRcDiscService rcDiscService = RcServiceFactory.getRcDiscService();
		ISequenceService seqService = ServiceFactory.getSequenceService();
		bill.setDiscmxId(seqService.getDISC_BILL_INFO_ID());
		bill.setCurStatus(DiscCodeConst.CUR_STATUS_NO);
		bill.setDiscType(DiscCodeConst.DISC_TYPE);
		///bill.setRgctId(seqService.getBillInfoId());
		try {
			bill.setOperStatus(StatusUtils.handleStatusNoCheck("DiscApplyController", "saveBill", null));
		} catch (Exception e1) {
			 throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加DiscBillInfo时状态机查询失败");
		}
		DiscApplyInfo apply;
		try {
			apply = this.getDiscApplyInfo(bill.getDiscId(), null);
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new BizAppException("增加DiscBillInfo时 批次信息查询失败");
		}
		/*bill.setCustAccount(apply.getCustAccount());
		bill.setCustNo(apply.getCustNo());*/
		bill.setBranchNo(userInfo.getBrchNo());
		bill.setDiscDt(DateTimeUtil.getWorkdayString());
		bill.setRate(apply.getRate());
		bill.setRateType(apply.getRateType());
		//所有手动录入的都为实物票据
		bill.setLastBillNo(CommUtils.getLastBillNo(bill.getBillNo()));
		bill.setBillClass(DiscCodeConst.BILL_CLASS_ENTITY);
		bill.setLimitProdNo(bill.getLimitProdNo()+bill.getOperStatus());
		bill.setBillSource(DiscCodeConst.BILL_SOURCE_ENTER);
		//bill.setAdscriptionId();//将柜员id保存禁清单表
		bill.setIsCancel(DiscCodeConst.IS_CANCEL_FALSE);
		bill.setYzSource("0");
		if(ecds == null) throw new BizAppException("找不到行号信息");
		bill.setDraweeAddr(ecds.getAddress());
				
		if(DiscCodeConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			bill.setAcceptor(ecds.getActorFullCall());
			if(CommUtils.isSelfBank(bill.getRemitterBankNo())){/*是否本行承兑*/
				//如果是本行承兑，则根据票号和承兑行行号去登记中心查询(如果该票存在，则比较出票日，到期日，票面金额，承兑行行号.
				//因为是根据票号和承兑行行号去查询的，实际上只要比较出票日,票面到期日，票面金额
				//checkBillInfo(bill);
				bill.setIsAccpt(DiscCodeConst.ACPT_YES);;
			}else{
				bill.setIsAccpt(DiscCodeConst.ACPT_NO);
			}
		}else{
			bill.setIsAccpt(DiscCodeConst.ACPT_NO);
		}
		
		//纸票，银承、商承的付款行名称均为手工录入值，承兑人开户行信息与出票人开户行信息一致
		/*bill.setAcceptorBankNo();
		bill.setAccptorBankName(bill.getOutBillBank());*/
		
		//在登记中心进行登记
		RgctBill rBill = new RgctBill();
		RgctBillInfo billInfo= new RgctBillInfo();
		RgctBillHist billHist = new RgctBillHist();
		billInfo.setLetterNo(bill.getBillBeforeOwner());
		copyProperties(billInfo, bill);
		rBill.setInfo(billInfo);

		billHist.setAcctBranchNo(userInfo.getBranchNo());
		billHist.setFromName(bill.getBillOwner());
		billHist.setFromCustNo(bill.getCustNo());
		billHist.setObligeeAcctNo(bill.getCustAccount());//持票人账号  权利人账号
		//billHist.setToName(bill.getBillBeforeOwner()==null?"":bill.getBillBeforeOwner());//待修改
		billHist.setToBankNo(userInfo.getBrchBankNo());
		billHist.setToBranchNo(userInfo.getBranchId());
		billHist.setToName(userInfo.getBranchName());
		billHist.setIsSameCity(bill.getIsSameCity());
		billHist.setHoldAcctNo(bill.getCustAccount());
		billHist.setHoldCustName(bill.getBillOwner());
		billHist.setIsRegress("0");//billHist.setIsRegress(bill.getDiscType());
		rBill.setHist(billHist);
		rBill = rcDiscService.inputSignBuy(rBill);
		//rcBuyService.addRgctEndo(rBill, bill.getBackInfoList());
		//本地保存
		bill.setRgctId(rBill.getInfo().getId());
		try {
			if (discBillDao.addDiscBillInfo(bill) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加DiscBillInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * billinfo 和rgctbillinfo信息
	 * @param target
	 * @param source
	 */
	private void copyProperties(RgctBillInfo target,DiscBillInfo source){
		
		target.setBillClass(source.getBillClass());//票据分类
		target.setBillType(source.getBillType());//
		target.setBillMoney(source.getBillMoney());
		target.setBillNo(source.getBillNo());//票据号码
		target.setRemark(source.getRemark());
		
		target.setDraweeBankName(source.getRemitterBankName());
		target.setDraweeBankNo(source.getDraweeBankNo());
		//target.setDraweeBranchNo(source.getRemitterAcct());
		target.setDueDt(source.getDueDt());
		
		
		target.setPayeeName(source.getPayee());//收款人全称
		target.setPayeeAcct(source.getPayeeAcct());//收款人账号
		target.setPayeeBankName(source.getPayeeBankName());//收款人开户行全称
		target.setPayeeBankNo(source.getPayeeBankNo());//收款人开户行行号
		
		target.setAcceptor(source.getAcceptor());//承兑方全称
		target.setAcceptorBankName(source.getAcceptorBankName());
		target.setIssueDt(source.getIssueDt());
		target.setIsAccpt(source.getIsAccpt());//是否本行承兑
		target.setAcceptorBankNo(source.getAcceptorBankNo());/*商|银票都一样*/
		//设置出票人相关信息
		target.setRemitterAcct(source.getRemitterAcct());//出票方帐号
		target.setRemitterBankName(source.getRemitterBankName());
		target.setRemitterBankNo(source.getRemitterBankNo());
		target.setDraweeAddr(source.getDraweeAddr());
		target.setRemitter(source.getRemitter());
	}
	
	/**
	 * 功能描述：得到待接受電票根据清单id
	 * @return
	 * @throws SQLException
	 */
	public List<DiscBillInfo> getElectricReceiveForId(String ids) throws SQLException{
		return discBillDao.getElectricReceiveForId(ids);
	}

	/**
	 * 电票贴现拒绝
	 * @param ids
	 * @throws BizAppException 
	 * @throws Exception 
	 */
	public void ElecNoReceive(String ids) throws BizAppException{
		IDB session = DBFactory.getDB();//
		String rgctids = "";
		Map<String,RgctBill> rgctmap = new HashMap<String, RgctBill>();
		try {
			List<DiscBillInfo> list= this.getElectricReceiveForId(ids);
			if (list.isEmpty()) {
				throw new BizAppException("票据据不支持该处理 ");
			}
			for (int i = 0; i < list.size(); i++) {
				rgctids += list.get(i).getRgctId() +",";
			}
			List<RgctBill> rgctlist = RcServiceFactory.getRcDiscService().getRgctBillList(rgctids.substring(0,rgctids.length()-1));
			for (int i = 0; i < rgctlist.size(); i++) {
				rgctmap.put(rgctlist.get(i).getInfo().getId(),rgctlist.get(i));
			}			
			for(int i = 0 ;i<list.size();i++){
				session.beginTransaction();
				DiscBillInfo discBill =list.get(i);
			    String status= discBill.getOperStatus();
			    String afterStatus= StatusUtils.handleStatus("DiscApplyController", "noReceive", null, status);
			    discBill.setOperStatus(afterStatus);
			    discBill.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
			    this.modDiscBillInfo(discBill);
			    RgctBill rgctBill=rgctmap.get(list.get(i).getRgctId());
			    RgctBillHist billHist = rgctBill.getHist();
				billHist.setToName(ResourceUtil.getSessionLoginfo().getBranchName());
				if(DiscCodeConst.BILL_CLASS_ELEC.equals(discBill.getBillClass())){
					billHist.setSignerSign(CommUtils.getSignerSign(billHist.getToBankNo()));
					Branch branch=ServiceFactory.getBranchService().getBranchByBrchBankNo(billHist.getToBankNo());
					billHist.setToRole(branch.getPartnerType());
					billHist.setToOrgcode(branch.getOrgCode());
				}
				billHist.setSignDt(DateTimeUtil.getWorkday());
				rgctBill.setHist(billHist);	
			    RcServiceFactory.getRcDiscService().rejectSignBuy(rgctBill);
			    session.endTransaction();
			}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new BizAppException("拒绝失败 ");
		}
	}
	
	/**
	 * 功能描述：电票贴现复核记账
	 * @return
	 * @throws Exception 
	 */
	public void discElecReviewBillListDoAccount(String ids,String discId) throws Exception{
		DiscApplyInfo discApplyInfo = this.getDiscApplyInfo(discId, null);
		List<DiscBillInfo>  list=getElectricReceiveForId(ids);
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo(); 
		String rcIds="";
		Map<String, RgctBill> rcMap=new HashMap<String, RgctBill>();
		for (int i = 0; i <list.size(); i++) {
			DiscBillInfo bill=list.get(i);
			rcIds=rcIds+bill.getRgctId()+",";
		}
		IRcRebuyService rcService = RcServiceFactory.getRcRebuyService();
		List<RgctBill> rcBillList=rcService.getRgctBillList(rcIds);
		for (int i = 0; i <rcBillList.size(); i++) {
			RgctBill rc=rcBillList.get(i);
			rcMap.put(rc.getInfo().getId(), rc);
		}
		for(int i = 0 ;i<list.size();i++){
		    DiscBillInfo discBill0 = list.get(i);
		  if(DiscCodeConst.IS_CANCEL_TRUE.equals(discBill0.getIsCancel())||discBill0.getIsCancel().equals(DiscCodeConst.IS_CANCEL_TRUE)){
			throw new BizAppException("对方已经撤销复核记账,票号为"+discBill0.getBillNo());
		  }
		}
		    IDB session = DBFactory.getDB(); // 获取数据库连接
			for(int i = 0 ;i<list.size();i++){
				try{
					DiscBillInfo discBill = list.get(i);
				session.beginTransaction();
			    String status= discBill.getOperStatus();
			    String afterStatus= StatusUtils.handleStatus("DiscAccountController", "doElecAccount", null, status);
			    discBill.setOperStatus(afterStatus);
			    discBill.setAcctOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
			    discBill.setAccountDate(DateTimeUtil.getWorkdayString());
			    discBill.setAccountTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			    this.modDiscBillInfo(discBill);
			    String rgctId = discBill.getRgctId();
			    RgctBill rgctBill =rcMap.get(rgctId);
				RgctBillHist hist = rgctBill.getHist();
					Branch branch=ServiceFactory.getBranchService().getBranchByBrchBankNo(hist.getToBankNo());
					hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
					hist.setSignerSign(CommUtils.getSignerSign(hist.getToBankNo()));
					hist.setToRole(branch.getPartnerType());
					hist.setToOrgcode(branch.getOrgCode());
					hist.setSignDt(DateTimeUtil.getWorkday());
					hist.setOldInterestRate(MathScaleUtil.divide(discBill.getRate(), 100));
					hist.setOldDisDt(discBill.getDiscDt());
					hist.setInterestDueDt(discBill.getGaleDate());
					hist.setInterest(discBill.getTotalIntrstPayment());
					hist.setInterestDays(discBill.getInterestDays());
					hist.setDelayDays(String.valueOf(discBill.getDelayDays()));
					hist.setIsRegress(discBill.getDiscType());
					hist.setBatchId(discApplyInfo.getBatchNo());
					hist.setProdAttr(discApplyInfo.getProdNo());
					hist.setBillTrack(RcConstants.BILL_SOURCE_DISC);// 1：贴现
					hist.setAcctBranchNo(branchNo);
					hist.setStorageBranchNo(branchNo);
					hist.setWorkingadsNo(discApplyInfo.getWorkingadsNo());
					hist.setWorkingadsName(discApplyInfo.getWorkingadsNo1());
					hist.setBillBeforeOwner(discApplyInfo.getCustName());
			        RcServiceFactory.getRcDiscService().discountSign(rgctBill);
			        session.endTransaction();
				}catch(SQLException e){
					try {
						session.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
					CommonLog.getCommonLogCache().errorMessage("处理失败:"+e.getMessage());
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "处理失败:"+e.getMessage());
				}	
	        }
	}
	
	/**
	 * 功能描述：电票贴现复核记账计算剩余票据总数量
	 * @return
	 * @throws Exception
	 */
	public int discElecReviewBillForTotalCount(DiscBillInfo query) throws  Exception{
		IDiscService discService = ServiceFactory.getDiscService();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		String billClass=IDict.K_BILL_CLASS.K_ELEC_BILL;
		String curStatus=StatusUtils.queryStatus("DiscAccountController", "doElecAccount", null)[0];
		query.setBranchNo(branchNo);
		query.setBillClass(billClass);
		query.setOperStatus(curStatus);
		return discService.totalCount(query);
		
	}
	

	

	/**
	 * 功能描述：得到待接受電票
	 * @return
	 * @throws SQLException
	 */
	public List<DiscBillInfo> getElectricReceive(Page page,DiscSearchBean query) throws SQLException{
		//initQueryCondition(query);
		query.setDfaultSrchTbAliasName("bill");
	    query.addProperty2TableObj("operStatus", "bill");
	    query.addSqlPropretyMapping("operStatus", "operStatus");
	    query.addSqlPropretyMapping("operBankNo", "operBankNo");
		query.addSqlPropretyMapping("orgCode", "orgCode");
	    query.addSqlPropretyMapping("custNo", "custNo");
	    OrderBean order=new OrderBean("discmxId",false);
	    query.appendOrder(order);
		return discBillDao.getDiscBillListForBatch(page,query);
	}
	
	
	/**
	 * 功能描述：根据批次id查询清单
	 * @param discId
	 * @return
	 * @throws SQLException
	 */
	public List<DiscBillInfo> getDiscBillListForBatch(Page page,DiscSearchBean query) throws Exception{
		initQueryCondition(query);
		OrderBean order=new OrderBean("discmxId",false);
	    query.appendOrder(order);
		return discBillDao.getDiscBillListForBatch(page,query);
	}
	
	
	
	/**
	 * 功能描述：根据批次id和状态查询清单
	 * @param discId
	 * @return
	 * @throws SQLException
	 */
	public List<DiscBillInfo> getDiscBillListForBatchStatus(Page page,DiscSearchBean query) throws Exception{
		initQueryCondition(query);
		OrderBean order=new OrderBean("discmxId");
	    query.appendOrder(order);
		return discBillDao.getDiscBillListForBatch(page,query);
	}
	
	/**
	 * 初始化查询条件
	 * @param query
	 */
	private void initQueryCondition(DiscSearchBean query){
		query.setBranchNo(ResourceUtil.getSessionLoginfo().getBrchNo());
		query.setDfaultSrchTbAliasName("bill");
	    query.addSpecialOpertion("endDay",BaseSearchBean.LESS_AND_EQUAL);
	    query.addSqlPropretyMapping("endDay", "dueDt");
	    query.addSpecialOpertion("startDay",BaseSearchBean.MORE_AND_EQUAL);
        query.addSqlPropretyMapping("startDay", "dueDt");
		query.addSpecialOpertion("opers",BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		query.addSqlPropretyMapping("operStatus", "operStatus");
		query.addSqlPropretyMapping("acctNo", "custAccount");
		query.addSpecialOpertion("lastBillNo", BaseSearchBean.LIKE);
		query.addSqlPropretyMapping("lastBillNo", "lastBillNo");
		query.addSqlPropretyMapping("operBankNo", "operBankNo");
		query.addSqlPropretyMapping("orgCode", "orgCode");
	}
	
	/**
	 * 功能描述：根据条件查询批次列表(申请岗位)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<DiscApplyInfo> getDiscApplyListForApply(Page page,DiscSearchBean query) throws Exception{
		query.setDfaultSrchTbAliasName("ta");
		query.addSqlPropretyMapping("acctNo", "custAccount");
		query.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		query.setBranchNo(ResourceUtil.getSessionLoginfo().getBrchNo());
		return discApplyDao.getDiscApplyListForApply(page,query);
	}
	
	/**
	 * 功能描述：根据条件查询批次列表(审核 复核记账使用)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<DiscApplyInfo> getDiscApplyListForCondition(Page page,DiscSearchBean query) throws SQLException{
		if(!"".equals(query.getBatchNo())&&query.getBatchNo()!=null){
			query.setBatchNo("%"+query.getBatchNo()+"%");
		}
		
		initQueryCondition(query);
		query.setDfaultSrchTbAliasName("bill");
	    query.addProperty2TableObj("discId", "apply");
	    query.addProperty2TableObj("applyStatus", "apply");
	    query.addSpecialOpertion("acctNo", BaseSearchBean.LIKE);
	    query.addSqlPropretyMapping("acctNo", "custAccount");
	    query.addProperty2TableObj("batchNo", "apply");
	    query.addSpecialOpertion("batchNo", BaseSearchBean.LIKE);
	    query.addSqlPropretyMapping("batchNo", "batchNo");
	    query.addSqlPropretyMapping("applyOperNo", "applyOperNo");
	    query.addSqlPropretyMapping("auditOperNo", "auditOperNo");
	    OrderBean order=new OrderBean("discId",false);
	    query.appendOrder(order);
	    return discApplyDao.getDiscApplyListForCondition(page,query);
	}
	
	/**
	 * 功能描述：得到批次详情
	 * @param discId
	 * @return
	 * @throws SQLException
	 */
	public DiscApplyInfo getDiscApplyInfo(String discId,DiscSearchBean query) throws SQLException{
		return discApplyDao.getDiscApplyInfo(discId,query);
	}
	/**
	 * 功能描述：添加批次
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	public void addDiscApplyInfo(DiscApplyInfo discApplyInfo)throws BizAppException{
		DiscApplyInfoDao dao=new DiscApplyInfoDao();
		UserLoginfo user = ResourceUtil.getSessionLoginfo(); 
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		discApplyInfo.setBatchNo(sequenceService.getDiscountApplyNo(user.getBranchNo()));
		discApplyInfo.setBranchNo(user.getBranchNo());
		discApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		discApplyInfo.setBillClass(CommonConst.BILL_CLASS_ENTITY);
		discApplyInfo.setRateType(CommonConst.RATE_TYPE_YEAR);//年利率
		discApplyInfo.setCustAccountType(CommonConst.TRADE_ACCT_TYPE_SETTLE);
		discApplyInfo.setPayType(DiscCodeConst.PROD_PAY_TYPE.get(discApplyInfo.getProdNo()));
		discApplyInfo.setDiscId(sequenceService.getDISC_APPLY_ID());
		discApplyInfo.setCreateDt(DateTimeUtil.getWorkdayString());
		discApplyInfo.setCreateTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
		try {
			if (dao.addDiscApplyInfo(discApplyInfo)!= 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加DiscApplyInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 功能描述：修改批次
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	public void modifyDiscApplyInfo(DiscApplyInfo discApplyInfo)throws BizAppException{
		//FIXME 方法不对 查出来以前的 重新赋值
		DiscApplyInfoDao dao=new DiscApplyInfoDao();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		discApplyInfo.setBranchNo(user.getBranchNo());
		discApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		discApplyInfo.setBillClass(CommonConst.BILL_CLASS_ENTITY);
		discApplyInfo.setRateType(CommonConst.RATE_TYPE_YEAR);//年利率
		discApplyInfo.setCustAccountType(CommonConst.TRADE_ACCT_TYPE_SETTLE);
		discApplyInfo.setPayType(DiscCodeConst.PROD_PAY_TYPE.get(discApplyInfo.getProdNo()));
		try {
			if (dao.modifyDiscApplyInfo(discApplyInfo)!= 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改discApplyInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	
	
	/**
	 * 功能描述：根据id删除批次
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	public boolean delApplyInfoForDiscIds(Page page,String[] discIds) throws Exception{
		DiscSearchBean query = new DiscSearchBean();
		boolean bool = true;
		for(int i=0;i<discIds.length;i++){
			query.setDiscId(discIds[i]);
			List<DiscBillInfo> billList = getDiscBillListForBatch(page, query);
			if(billList==null || billList.size()<=0){
				DiscApplyInfo discApplyInfo=new DiscApplyInfo();
				discApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
				discApplyDao.deleteDiscApplyInfo(discApplyInfo, discIds[i]);
			}else{
				bool = false;
			}
		}
		return bool;
	}


	
	private DiscBillInfoDao discBillDao = new DiscBillInfoDao();
	private DiscApplyInfoDao discApplyDao = new DiscApplyInfoDao();
	
	/**
	 * 功能描述：修改票据状态
	 * @param ids,queryStatus,afterStatus
	 * @return
	 * @throws SQLException,BizAppException
	 */
	@Override
	public int getUpdateDiscBillInfoStatus(String ids, String[] queryStatus,
			String afterStatus,String dateTime,String option) throws SQLException, BizAppException {
		return 0;//discBillDao.updateDiscBillInfoStatus(ids, queryStatus, afterStatus,dateTime,option);
	}

	/**
	 * 功能描述：通过票据中心ID查票据清单
	 * @param rgctId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public DiscBillInfo getDiscBillInfoByRgctId(String rgctId)
			throws SQLException {
		return discBillDao.getDiscBillInfoByRgctId(rgctId);
	}

	/**
	 * 功能描述：通过票据清单修改状态值
	 * @param bill
	 * @return
	 * @throws SQLException
	 */
	@Override
	public void modifyOperStatusForBill(DiscBillInfo bill) throws SQLException {
		discBillDao.modifyDiscBillInfo(bill);
	}
	
	
	
	
	/**
	 * 功能描述：清空利息等值
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	@Override
	public void modifyBill(DiscSearchBean query) throws SQLException {
		initQueryCondition(query);
		discBillDao.modifyBill(query);
	}



	/**
	 * 功能描述：根据id进行实物入库
	 * @param String ids
	 * @return int
	 * @throws Exception
	 */
	public int discStorageForDiscmxId(String ids) throws Exception{
		if(StringUtils.isBlank(ids)) return -1;//返回失败
		String idArr[] = ids.split(",");
		DiscApplyInfo apply = null;
		String prodAttr = null;
		//Map<String, DiscBillInfo> map=new HashMap<String, DiscBillInfo>();
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		IRcDiscService rcService = RcServiceFactory.getRcDiscService();
		for (int n = 0 ; n < idArr.length ; n++ ) {
			//如果批次为空查询批次 正常当n等于0是已经赋值
			if (apply == null){
				apply = this.getDiscApplyInfo(this.getDiscBillInfo(idArr[n]).getDiscId(),null);
				prodAttr = getProdAttrName(apply.getBankProdNo());
			}
			//得到清单详情
			DiscBillInfo bill = this.getDiscBillInfo(idArr[n]);
			//检查清单前置状态
			StatusUtils.handleStatus("DiscStorageController", "doStorage", null, bill.getOperStatus());
			//map.put(bill.getRgctId(), bill);
			//得到RgctBillInfo与RgctBillHist合并类
			RgctBill rgctBill = rcService.getRgctBillById(bill.getRgctId());
			//得到RgctBillHist类
			RgctBillHist hist = rgctBill.getHist();
			RgctBillInfo info = rgctBill.getInfo();
			hist.setIsLock("0");
			hist.setFromAcctNo(apply.getCustAccount());
			hist.setSignDt(DateTimeUtil.getWorkdayString());
			hist.setToBranchNo(userInfo.getBrchNo());
			hist.setDealMoney(bill.getPayMoney());
			hist.setInterestRate(MathScaleUtil.divide(apply.getRate(), 100));
			hist.setOldInterestRate(MathScaleUtil.divide(apply.getRate(), 100));
			hist.setOldDisDt(bill.getAccountDate());
			hist.setInterestDueDt(bill.getGaleDate());
			hist.setInterest(bill.getSalerInterest());
			hist.setInterestDays(bill.getInterestDays());
			hist.setDelayDays(bill.getDelayDays()+"");
			hist.setHoldCustName(userInfo.getBranchName());
			hist.setHoldAcctNo("0");
			hist.setToAcctNo("0");
			hist.setToName(userInfo.getBranchName());
			hist.setIsRegress("0");//hist.setIsRegress(bill.getDiscType());
			//纸票入库暂时不添加批次信息
			hist.setBatchId(" ");
			hist.setProdAttr(prodAttr);
			hist.setBillTrack("1");//1：贴现
			hist.setAcctBranchNo(userInfo.getBranchNo());
			info.setAcctBranchNo(userInfo.getBranchNo());
			hist.setStorageBranchNo(userInfo.getBranchNo());
			hist.setStorageBranchName(userInfo.getBranchName());
//				hist.setBuyType(BillConst.BUYTYPE_DISC);
			//hist.setWorkingadsNo(apply.getWorkingadsNo());
			//hist.setWorkingadsName(apply.getWorkin);
			//hist.setProdNo(apply.getProdNo());
			hist.setIsSameCity(bill.getIsSameCity());
			//obligeeCustNo是为了在托收时显示归属机构
			/*if(apply.isNonStopBus()){
				Branch brch=branchDao.getBranchByBrchNo(apply.getAcctBrchNo());
				hist.setObligeeCustNo(brch.getBrchName());
			}else{
				hist.setObligeeCustNo(userInfo.getBranchName());
			}*/
			//暂不考虑赎回式贴现
			/*if (DiscCodeConst.DISC_BILL_DISCTYPE_REDEEM.equals(hist.getIsRegress())) {
				hist.setBackOpenDt(apply.getRedeemDate());
				hist.setBackEndDt(apply.getRedemptionDt());
			}*/
			hist.setBillBeforeOwner(apply.getCustName());
			rgctBill.setHist(hist);
			rgctBill.setInfo(info);
			rcService.discountSign(rgctBill);
			/*if (DiscCodeConst.DISC_BILL_DISCTYPE_REDEEM.equals(rgctBill.getHist().getIsRegress())) {
				RgctBill rcBill = rcBuyService.getRgctBillById(rgctBill.getHist().getRgctId());
				RgctBillHist hist = rcBill.getHist();
				hist.setBackOpenDt(apply.getRedeemDate());
				hist.setBackEndDt(apply.getRedemptionDt());
				// 判断是否为赎回式贴现，如果是直接改状态到赎回开放日
				rcRepurchaseService.buyBackBefore(rcBill);
			}*/
		}
		
		int rs = 0;
		for (String id : idArr) {
			DiscBillInfo bill = this.getDiscBillInfo(id);
			bill.setOperStatus(StatusUtils.handleStatus("DiscStorageController", "doStorage", null,bill.getOperStatus()));
			bill.setApplyCommitDate(DateTimeUtil.getWorkdayString());
			bill.setApplyCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			rs = this.discBillDao.modifyDiscBillInfo(bill);
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
	public int totalCount(DiscBillInfo bill) throws SQLException {
		return discBillDao.totalCount(bill);
	}
	private String getProdAttrName(String prodAttrNo) throws BizAppException {
		IBbspProductService proService = ServiceFactory.getBbspProductService();
	    String prodAttrName=""; 
	    if(StringUtils.isNotBlank(prodAttrNo)){
	    	String [] attrArr=prodAttrNo.split(",");
	    	for(int i=0;i<attrArr.length;i++){
				prodAttrName += proService.getBbspProduct(attrArr[i]).getProdName();
            }
        }
	    return prodAttrName;
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
		double auditAmt = 0.0;
		List<DiscBillInfo> billList = discBillDao.getElectricReceiveForId(ids);
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		String discId = discBillDao.getDiscBillInfo(idArr[0]).getDiscId();
		//为修改查询批次，所以不用插叙条件
		DiscApplyInfo applyinfo = discApplyDao.getDiscApplyInfo(discId,null);
		//撤销申请释放额度
		if("cancelApply".equals(methodName)){
			FacDealResult result = ServiceFactory.getDiscFacService().dealFac(applyinfo, billList, userInfo, CommonConst.FAC_OPER_RELEASE);
			result.checkFacResult(CommonConst.FAC_OPER_RELEASE);
			/*IFmsAgentService service = ServiceFactory.getFmsAgentService();
			service.facReleaseByBillBatch(applyinfo,billList,userInfo);*/
		}
		//更新清单信息
		for(DiscBillInfo bill : billList){
			bill.setOperStatus(StatusUtils.handleStatus(conName,methodName,null,bill.getOperStatus()));
			bill.setCurStatus(DiscCodeConst.CUR_STATUS_NO);
			bill.setGathMneyType(RcConstants.GATH_TYPE_DEFULT);
			bill.doDiscAccountReverse();
			auditAmt += bill.getBillMoney();
			rs = this.discBillDao.modifyDiscBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		AuditTask auditTask = new AuditTask();
        auditTask.setWaitAuditAmt(auditAmt); 
        auditTask.setAtAuthorName(userInfo.getUserName()); 
        auditTask.setAtAuthorId(userInfo.getUserId()); 
        auditTask.setBrchNo(userInfo.getBrchNo()); 
        auditTask.setAtCreateDt(DateTimeUtil.getWorkdayString()); 
        auditTask.setAtCreateTime(DateTimeUtil.getTime());  
        auditTask.setProdNo(applyinfo.getProdNo());
        auditTask.setBatchNo(applyinfo.getBatchNo()); 
        auditTask.setBatchId(discId);
        auditTask.setEntityName("discApplyController.do?method=billManage");
        auditTask.setEntityService("discId");
	    ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
		
		//查询批次下是否存在没有提交的清单
		List<DiscBillInfo> list = discBillDao.getDiscBillListForDiscIdAndStatus(discId,StatusUtils.queryStatus("DiscApplyController", "searchBatch", null)[0]);
		//如果存在则更新批次状态为未提交
		if( list != null && list.size() > 0 ) {
			applyinfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			rs = discApplyDao.modifyDiscApplyInfo(applyinfo);
		}
		return rs;
	}

	/**
	 * 功能描述：点击保存时添加批次并修改该批次下的票据信息
	 * @param 
	 * @return
	 * @throws BizAppException
	 */
	@Override
	public void saveConditionForAddDiscApplyInfoAndModifyDiscBillInfo(String ids,
			DiscApplyInfo discApplyInfo) throws BizAppException {
		DiscApplyInfoDao dao=new DiscApplyInfoDao();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		/*ISequenceService sequenceService=ServiceFactory.getSequenceService();
		discApplyInfo.setBatchNo(sequenceService.getDiscountApplyNo(user.getBranchNo()));*/
		discApplyInfo.setBranchNo(user.getBranchNo());
		discApplyInfo.setRateType(CommonConst.RATE_TYPE_YEAR);//年利率
		discApplyInfo.setCreateDt(DateTimeUtil.getWorkdayString());
		discApplyInfo.setCreateTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
		discApplyInfo.setCustAccountType(CommonConst.TRADE_ACCT_TYPE_SETTLE);
		discApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		try {
			if (dao.addDiscApplyInfo(discApplyInfo)!= 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加DiscApplyInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		try{
			List<DiscBillInfo> list=discBillDao.getElectricReceiveForId(ids);
			for(int i = 0 ;i<list.size();i++){
			DiscBillInfo discBill=list.get(i);
			String status= list.get(i).getOperStatus();
			String afterStatus= StatusUtils.handleStatus("DiscApplyController", "saveElecBill", null, status);
			discBill.setCustNo(discApplyInfo.getCustNo());
			discBill.setCustAccount(discApplyInfo.getCustAccount());
			discBill.setBranchNo(user.getBranchNo());
			discBill.setOperStatus(afterStatus);
			discBill.setDiscId(discApplyInfo.getDiscId());
			discBill.setRate(discApplyInfo.getRate());
			discBill.setApplyCommitDate(DateTimeUtil.getWorkdayString());
			discBill.setApplyCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			try {
				if(discBillDao.modifyDiscBillInfo(discBill)!=1){
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改discBillInfo失败");
				}
		} catch (SQLException e) {
		}
				
			}
	
	}catch(SQLException e){
		e.printStackTrace();
		} catch (Exception e1) {
		e1.printStackTrace();
	}
	}

	/**
	 * 功能描述：点击提交时修改批次并修改该批次下的票据信息到审核岗的状态
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	public void submitConditionForAddDiscApplyInfoAndModifyDiscBillInfo(
			String ids, DiscApplyInfo discApplyInfo) throws BizAppException {
		DiscApplyInfoDao dao=new DiscApplyInfoDao();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		String discId=sequenceService.getDISC_APPLY_ID();
		discApplyInfo.setBatchNo(sequenceService.getDiscountApplyNo(user.getBranchNo()));
		discApplyInfo.setBranchNo(user.getBranchNo());
		discApplyInfo.setRateType(CommonConst.RATE_TYPE_YEAR);//年利率
		discApplyInfo.setCreateDt(DateTimeUtil.getWorkdayString());
		discApplyInfo.setCreateTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
		discApplyInfo.setCustAccountType(CommonConst.TRADE_ACCT_TYPE_SETTLE);
		discApplyInfo.setDiscId(discId);
		discApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
		try {
			if (dao.addDiscApplyInfo(discApplyInfo)!= 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加DiscApplyInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		try{
			List<DiscBillInfo> list=discBillDao.getElectricReceiveForId(ids);
			for(int i = 0 ;i<list.size();i++){
			DiscBillInfo discBill=list.get(i);
			String status= list.get(i).getOperStatus();
			String afterStatus= StatusUtils.handleStatus("DiscApplyController", "elecBillSubmit", null, status);
			discBill.setCustNo(discApplyInfo.getCustNo());
			discBill.setCustAccount(discApplyInfo.getCustAccount());
			discBill.setBranchNo(user.getBranchNo());
			discBill.setOperStatus(afterStatus);
			discBill.setDiscId(discId);
			discBill.setRate(discApplyInfo.getRate());
			discBill.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
			discBill.setApplyCommitDate(DateTimeUtil.getWorkdayString());
			discBill.setApplyCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			try {
				if(discBillDao.modifyDiscBillInfo(discBill)!=1){
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改discBillInfo失败");
				}
		} catch (SQLException e) {
		}
				
			}
	
	}catch(SQLException e){
		e.printStackTrace();
		} catch (Exception e1) {
		e1.printStackTrace();
	}
		
	}
	
	/**
	 * 功能描述：修改电票批次
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	public void modifyElecDiscApplyInfo(DiscApplyInfo discApplyInfo)throws BizAppException{
		DiscApplyInfoDao dao=new DiscApplyInfoDao();
		try {
			if (dao.modifyDiscApplyInfo(discApplyInfo)!= 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改discApplyInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}

	/**
	 * 电票审批提交
	 * @param ids
	 * @param status 是否同意
	 * @param option  审批意见
	 * @return
	 */
	public int auditElecSubmit(String ids, String status, String reason)
			throws Exception {
		int rs = 0;
		if(StringUtils.isBlank(ids)) return rs;
		for (String id : ids.split(",")) {
			DiscBillInfo bill = this.getDiscBillInfo(id);
			bill.setOperStatus(StatusUtils.handleStatus("DiscAuditController", "auditElec", status,bill.getOperStatus()));
			bill.setAuditReason(reason);
			bill.setAuditOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
			bill.setAuditCommitDate(DateTimeUtil.getWorkdayString());
			bill.setAuditCommitTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			rs = this.discBillDao.modifyDiscBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}
	/**
	 * 根据清单id查询DiscBillInfo集合（ids是清单id以，的拼接）
	 * @param ids
	 * @return
	 */
	public List<DiscBillInfo> getDiscBillInfolistbyids(String ids) throws Exception{
		List<DiscBillInfo> list = discBillDao.getElectricReceiveForId(ids);
		
		return list;
	}

	/**
	 * 根据批次id（discid）查询DiscApplyInfo
	 * @param discid
	 * @return
	 */
	public DiscApplyInfo getDiscApplyInfoBydiscid(String discid) throws Exception{
		DiscApplyInfo obj = discApplyDao.getDiscApplyInfo(discid,null);
		return obj;
	}
	
	/**
	 * 电票利息试算 
	 * @param toDiscBill
	 * @param discApply
	 * @return
	 */
	public int elecInterestTrial(DiscBillInfo discBill) throws Exception {
		int rs = 0;
		List<DiscBillInfo> billList= this.discBillDao.getDiscBillListForDiscIdAndStatus(discBill.getDiscId(),StatusUtils.queryStatus("DiscAuditController", "auditList", null)[0]);
		//一个批次下的清单批次相同 只取查询一次批次即可
		DiscApplyInfo discApply = this.getDiscApplyInfo(discBill.getDiscId(),null);
		//批量试算
		for(DiscBillInfo toDiscBill : billList){
			toDiscBill.setIsSameCity(discBill.getIsSameCity());
			toDiscBill.setDelayType(discBill.getDelayType());
			toDiscBill.setDelayDays(discBill.getDelayDays());
			//开始为清单利息试算 试算成功返回大于0
			rs = startInterestTrial(toDiscBill, discApply);
			//结果小于0试算失败 全部回滚
			if( rs <= 0 ) return 0;
		}
		return rs;
	}

	/**
	 * 申请岗直接提交时电票利息试算 
	 * @param toDiscBill
	 * @param discApply
	 * @return
	 */
	public int elecSubmitInterestTrial(DiscBillInfo discBill) throws Exception {
		int rs = 0;
		List<DiscBillInfo> billList= this.discBillDao.getDiscBillListForDiscIdAndStatus(discBill.getDiscId(),StatusUtils.queryStatus("DiscApplyController", "modifyElecBillSubmit", null)[0]);
		//一个批次下的清单批次相同 只取查询一次批次即可
		DiscApplyInfo discApply = this.getDiscApplyInfo(discBill.getDiscId(),null);
		//批量试算
		for(DiscBillInfo toDiscBill : billList){
			toDiscBill.setIsSameCity(discBill.getIsSameCity());
			toDiscBill.setDelayType(discBill.getDelayType());
			toDiscBill.setDelayDays(discBill.getDelayDays());
			//开始为清单利息试算 试算成功返回大于0
			rs = startInterestTrial(toDiscBill, discApply);
			//结果小于0试算失败 全部回滚
			if( rs <= 0 ) return 0;
		}
		return rs;
	}
	

	/**
	 * 功能描述：查询批次下该status状态的清单
	 * @param discId
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<DiscBillInfo> getDiscBillForDiscIdAndStatus(String discId,String status) throws SQLException, Exception{
		//查询批次下该status状态的清单
		return discBillDao.getDiscBillListForDiscIdAndStatus(discId,status);
	}


	@Override
	public List<DiscBillInfo> getDiscElecBillListForBatch(Page page,
			DiscSearchBean query) throws Exception {
		query.setDfaultSrchTbAliasName("bill");
	    query.addProperty2TableObj("operStatus", "bill");
	    query.addSqlPropretyMapping("operStatus", "operStatus");
	    query.addSqlPropretyMapping("operBankNo", "operBankNo");
		query.addSqlPropretyMapping("orgCode", "orgCode");
	    query.addSqlPropretyMapping("custNo", "custNo");
		OrderBean order=new OrderBean("discmxId",false);
	    query.appendOrder(order);
		return discBillDao.getDiscBillListForBatch(page,query);
	}


	@Override
	public BillAllInfoBean getElecBillDetail(String rgctId)
			throws SQLException {
		
		BillBackInfoBean backBean=new BillBackInfoBean();
		BillAllInfoBean bean = new BillAllInfoBean();
		IRgctBillInfoService rcService = RcServiceFactory.getRgctBillInfoService();
		IRgctBillHistService histService = RcServiceFactory.getRgctBillHistService();
		IAssuService assuService = ServiceFactory.getAssuService();
		IRcEndorseService endoService = RcServiceFactory.getRcEndorseService();
			try {
				RgctBillInfo bill = rcService.getRgctBillInfo(rgctId);
				RgctBillHist hist = histService.getRgctBillHist(bill.getHistId());
				RgctBill rgctBill = new RgctBill();
				rgctBill.setInfo(bill);
				rgctBill.setHist(hist);
				EcdsBillBean frontBean=WebBankUtil.createEcdsBillBean(rgctBill);
				frontBean.setBillMoney(moneyFormat(frontBean.getBillMoney()));
				bean.setFrontBean(frontBean);
				List<RgctEndoHist> endoHistList = endoService.getRgctEndoList(bill.getId());
				backBean.setEndoListBean(WebBankUtil.createEndoHist(endoHistList));
				bean.setBackBean(backBean);
				List<AssuBillInfo> grantList = assuService.queryGrantInfoByRgctIdAndAssuType(rgctId,
						new String[]{GuarCodeConst.ASSU_TYPE_REG_1,GuarCodeConst.ASSU_TYPE_ACPT_2});
				bean.setGuarnteeList(WebBankUtil.createAssu(grantList));
			} catch (BizAppException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return bean;
	}
	
	private String moneyFormat(String billMoney){
		DecimalFormat fmt=new DecimalFormat("##,###,###,###,##0.00");
		double dval=Double.parseDouble(billMoney);
		String fmtStr=fmt.format(dval);
		return fmtStr;
	}


	@Override
	public void getCheckApprove(String ids, double auditAmt)
			throws BizAppException {
		String discId;
		try {
			discId = discBillDao.getDiscBillInfo(ids.split(",")[0]).getDiscId();
			//为修改查询批次，所以不用插叙条件
			DiscApplyInfo applyinfo = discApplyDao.getDiscApplyInfo(discId,null);
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
	        auditTask.setBatchId(discId);
	        auditTask.setEntityName("discAuditController.do?method=auditBillList");
	        auditTask.setEntityService("discId");
		    ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	/**
	 * 功能描述：电票添加批次
	 * @param discApplyInfo
	 * @return   billAllInfoBean
	 * @throws SQLException
	 */
	@Override
	public void addElecDiscApplyInfo(DiscApplyInfo discApplyInfo)
			throws SQLException {
		discApplyDao.addDiscApplyInfo(discApplyInfo);
	}


	
}