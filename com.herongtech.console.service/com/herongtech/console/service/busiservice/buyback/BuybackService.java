/********************************************
 * 文件名称: BuybackService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-11-04 下午10:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.busiservice.buyback;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.buyback.bean.BuybackApplyInfo;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.bean.BuybackSearchBean;
import com.herongtech.console.domain.buyback.dao.BuybackApplyInfoDao;
import com.herongtech.console.domain.buyback.dao.BuybackBillInfoDao;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.bean.InterestReqDTO;
import com.herongtech.console.domain.common.bean.InterestResultDTO;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.dao.SaleApplyInfoDao;
import com.herongtech.console.domain.sale.dao.SaleBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.IInterestService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.BuybackCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.DiscCodeConst;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.console.web.busicontroller.common.SaleCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.sysconstant.ISysErrorNo;

public class BuybackService implements IBuybackService{
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private BuybackApplyInfoDao applyDao = new BuybackApplyInfoDao();
	private BuybackBillInfoDao billDao = new BuybackBillInfoDao();
	private SaleApplyInfoDao saleApplyDao = new SaleApplyInfoDao();
	private SaleBillInfoDao saleBillDao = new SaleBillInfoDao();
	
	
	/**
	 * 功能描述：票据申请提交
	 * @param ids
	 * @return
	 * @throws Exception 
	 * @throws BizAppException 
	 */
	public int applySubmit(String ids,BuybackSearchBean query,String buybackRate) throws Exception{
		//如果没有利息试算返回-999 提示前台进行利息试算
		if(!billDao.isInterestTrial(ids)) return -999;
		int rs = 0;
		double auditAmt = 0.0;
		if(StringUtils.isBlank(ids)) return rs;
		for (String id : ids.split(",")) {
			BuybackBillInfo bill = this.billDao.getBuybackBillInfo(id);
			//网银端已撤销
			if(CommonConst.IS_CANCEL_TRUE.equals(bill.getApplyCancelFlag())){
				return -998; //网银端已撤销
			}
//			buybackId = bill.getBuybackId();
			bill.setBuybackId(query.getBuybackId());
			//修改操作状态
			bill.setOperStatus(StatusUtils.handleStatus("BuybackApplyController", "buybackApply", "1",bill.getOperStatus()));
			bill.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			auditAmt += bill.getBillMoney();
			//修改详情信息
			rs = this.billDao.modifyBuybackBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		//为修改查询批次，所以不用插叙条件
		BuybackApplyInfo buybackApply = applyDao.getBuybackApplyInfo(query.getBuybackId(), null);
		buybackApply.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
		applyDao.modifyBuybackApplyInfo(buybackApply);
		if(rs > 0){
			//功能描述：开启审核任务
			startAuditTask(buybackApply,auditAmt);
		}
		return rs;
	}
	
	/**
	 * 功能描述：票据申请提交(纸票)
	 * @param ids
	 * @return
	 * @throws Exception 
	 * @throws BizAppException 
	 */
	public int applySubmitForEntity(String ids,BuybackSearchBean query) throws Exception{
		//如果没有利息试算返回-999 提示前台进行利息试算
		if(!billDao.isInterestTrial(ids)) {
			return -999;
			}
		int rs = 0;
		double auditAmt = 0.0;
		if(StringUtils.isBlank(ids)) {
			return rs;
			}
//		SaleApplyInfo saleApply = saleApplyDao.getSaleApplyInfo(query.getSaleId());
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		//创建批次
		/*BuybackApplyInfo buybackApply=new BuybackApplyInfo();
		buybackApply.setBuybackId(query.getBuybackId());
		buybackApply.setBatchNo(query.getBatchNo());
		buybackApply.setSaleId(query.getSaleId());
		buybackApply.setBranchNo(user.getBranchNo());
		buybackApply.setOperNo(user.getUserId());
		buybackApply.setCreateDt(DateTimeUtil.getWorkday());
		buybackApply.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		if(BuybackCodeConst.SALE_IN_PRODNO.equals(saleApply.getProdNo())){
			buybackApply.setProdNo(BuybackCodeConst.BUYBACK_IN_PRODNO);
		}else if(BuybackCodeConst.SALE_OUT_PRODNO.equals(saleApply.getProdNo())){
			buybackApply.setProdNo(BuybackCodeConst.BUYBACK_OUT_PRODNO);
		}else if(BuybackCodeConst.SALE_ZAI_PRODNO.equals(saleApply.getProdNo())){
			buybackApply.setProdNo(BuybackCodeConst.BUYBACK_ZAI_PRODNO);
		}
		buybackApply.setBillClass(saleApply.getBillClass());
		buybackApply.setBillType(saleApply.getBillType());
		buybackApply.setDueDt(saleApply.getRebuyDueDt());
		buybackApply.setBillStorageOrg(saleApply.getBillStorageBrchno());
		buybackApply.setBillStorageOrgName(saleApply.getBillStorageName());
		buybackApply.setAimBranchNo(saleApply.getAimBranchNo());//交易对手
		buybackApply.setIsDummy(saleApply.getIsDummy());
		buybackApply.setIsInner(saleApply.getIsInner());
		buybackApply.setIsOnline(saleApply.getIsOnline());
		buybackApply.setSaleDt(saleApply.getSaleDt());
		buybackApply.setInnerAccount(saleApply.getInnerAccount());
		buybackApply.setCustName(saleApply.getCustName());
		buybackApply.setRate(saleApply.getRate());
		buybackApply.setRateType("360");//rebuyDueDt
		buybackApply.setBuybackRate(Double.parseDouble(query.getBackRate()));
		buybackApply.setBuybackOpenDt(saleApply.getBuybackOpenDt());
		buybackApply.setBuybackDueDt(saleApply.getRebuyDueDt());
		buybackApply.setBuybackMoney(saleApply.getBuybackMoney());
		buybackApply.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
		this.applyDao.addBuybackApplyInfo(buybackApply);*/
		//更新清单信息
		for (String id : ids.split(",")) {
			BuybackBillInfo bill = this.billDao.getBuybackBillInfo(id);
			bill.setOperStatus(StatusUtils.handleStatus("BuybackApplyController", "entyBuybackApply", null,bill.getOperStatus()));
			bill.setBuybackId(query.getBuybackId());
			bill.setApplyOperNo(user.getUserId());
			bill.setBuybackDt(query.getBuybackDate());
			auditAmt += bill.getBillMoney();
			//修改清单信息
			rs = this.billDao.modifyBuybackBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		BuybackApplyInfo buybackApply = applyDao.getBuybackApplyInfo(query.getBuybackId(), null);
		buybackApply.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
		applyDao.modifyBuybackApplyInfo(buybackApply);
		//审批
		if(rs > 0){
			UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		    AuditTask auditTask = new AuditTask();
            auditTask.setWaitAuditAmt(auditAmt); 
            auditTask.setAtAuthorName(userInfo.getUserName()); 
            auditTask.setAtAuthorId(userInfo.getUserId()); 
            auditTask.setBrchNo(userInfo.getBrchNo()); 
            auditTask.setAtCreateDt(DateTimeUtil.getWorkdayString()); 
            auditTask.setAtCreateTime(DateTimeUtil.getTime());  
            auditTask.setProdNo(buybackApply.getProdNo());
            auditTask.setBatchNo(buybackApply.getBatchNo()); 
            auditTask.setBatchId(buybackApply.getBuybackId());
            auditTask.setEntityName("buybackAuditController.do?method=entityAuditBillList");
            auditTask.setEntityService("buybackId");
		    ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
		}
		return rs;
	}
	
	/**
	 * 功能描述：开启审核任务
	 * @param applyinfo
	 * @param auditAmt
	 * @throws Exception
	 */
	private void startAuditTask(BuybackApplyInfo applyinfo,double auditAmt) throws Exception{
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
        auditTask.setBatchId(applyinfo.getBuybackId());
        auditTask.setEntityName("buybackAuditController.do?method=auditDetailList");
        auditTask.setEntityService("buybackId");
	    ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
	}
	/**
	 * 功能描述:更新清单信息
	 * @param buyback
	 * @return
	 * @throws SQLException 
	 */
	public int updateBuybackBillInfo(BuybackBillInfo buyback) throws SQLException{
		return billDao.modifyBuybackBillInfo(buyback);
	}
	/**
	 * 功能描述：利息试算
	 * @param buyback
	 * @param ids
	 * @return 结果大于0试算成功
	 */
	public int interestTrial(BuybackBillInfo buyback,String ids,String buybackRate,BuybackApplyInfo buybackApply) throws Exception{
		int rs = 0;
		List<BuybackBillInfo> billList= this.billDao.getBuybackBillListByIds(ids);
		//一个批次下的清单批次相同 只取查询一次批次即可
		BuybackApplyInfo buybackApply2 = this.getBuybackApplyInfo(buybackApply.getBuybackId(),null);
		SaleApplyInfo saleApply = saleApplyDao.getSaleApplyInfo(buyback.getSaleId());
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		buybackApply.setBranchNo(user.getBranchNo());
		buybackApply.setOperNo(user.getUserId());
//		buybackApply.setCreateDt(DateTimeUtil.getWorkdayString());//实际回购日期
		buybackApply.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		if(BuybackCodeConst.SALE_IN_PRODNO.equals(saleApply.getProdNo())){
			buybackApply.setProdNo(BuybackCodeConst.BUYBACK_IN_PRODNO);
		}else if(BuybackCodeConst.SALE_OUT_PRODNO.equals(saleApply.getProdNo())){
			buybackApply.setProdNo(BuybackCodeConst.BUYBACK_OUT_PRODNO);
		}else if(BuybackCodeConst.SALE_ZAI_PRODNO.equals(saleApply.getProdNo())){
			buybackApply.setProdNo(BuybackCodeConst.BUYBACK_ZAI_PRODNO);
		}
		buybackApply.setBillClass(saleApply.getBillClass());
		buybackApply.setBillType(saleApply.getBillType());
		buybackApply.setDueDt(saleApply.getRebuyDueDt());
		buybackApply.setBillStorageOrg(saleApply.getBillStorageBrchno());
		buybackApply.setBillStorageOrgName(saleApply.getBillStorageName());
		buybackApply.setAimBranchNo(saleApply.getAimBranchNo());//交易对手
		buybackApply.setIsDummy(saleApply.getIsDummy());
		buybackApply.setIsInner(saleApply.getIsInner());
		buybackApply.setIsOnline(saleApply.getIsOnline());
		buybackApply.setSaleDt(saleApply.getSaleDt());
		buybackApply.setInnerAccount(saleApply.getInnerAccount());
		buybackApply.setCustName(saleApply.getCustName());
		buybackApply.setRate(saleApply.getRate());
		buybackApply.setRateType("360");//rebuyDueDt
		buybackApply.setBuybackRate(Double.parseDouble(buybackRate));
		buybackApply.setBuybackOpenDt(saleApply.getBuybackOpenDt());
		buybackApply.setBuybackDueDt(saleApply.getRebuyDueDt());
		buybackApply.setBuybackMoney(saleApply.getBuybackMoney());
		buybackApply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		if(buybackApply2==null){
			this.applyDao.addBuybackApplyInfo(buybackApply);
		}else{
			this.modifyBuybackApplyInfo(buybackApply);
		}
		//批量试算
		for(BuybackBillInfo toBuyback : billList){
			toBuyback.setDelayType(buyback.getDelayType());
			toBuyback.setDelayDays(buyback.getDelayDays());
			//开始为清单利息试算 试算成功返回大于0
			rs = startInterestTrial(toBuyback, saleApply,buybackRate);
			//结果小于0试算失败 全部回滚
			if( rs <= 0 ) return 0;
		}
		return rs;
	}
	
	/**
	 * 功能描述：利息试算(纸票)
	 * @param buyback
	 * @param ids
	 * @return 结果大于0试算成功
	 */
	public int interestTrialForEntity(BuybackBillInfo buybackBill,String ids,BuybackApplyInfo buybackApply,String buybackDt,String buybackRate,String isSameCity) throws Exception{
		int rs = 0;
		List<BuybackBillInfo> billList= this.billDao.getBuybackBillListByIds(ids);
		//一个批次下的清单批次相同 只取查询一次批次即可
		BuybackApplyInfo buybackApply2 = this.getBuybackApplyInfo(buybackApply.getBuybackId(),null);
		SaleApplyInfo saleApply = saleApplyDao.getSaleApplyInfo(buybackBill.getSaleId());
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		buybackApply.setBranchNo(user.getBranchNo());
		buybackApply.setOperNo(user.getUserId());
		buybackApply.setCreateDt(buybackDt);//实际回购日期
		buybackApply.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		if(BuybackCodeConst.SALE_IN_PRODNO.equals(saleApply.getProdNo())){
			buybackApply.setProdNo(BuybackCodeConst.BUYBACK_IN_PRODNO);
		}else if(BuybackCodeConst.SALE_OUT_PRODNO.equals(saleApply.getProdNo())){
			buybackApply.setProdNo(BuybackCodeConst.BUYBACK_OUT_PRODNO);
		}else if(BuybackCodeConst.SALE_ZAI_PRODNO.equals(saleApply.getProdNo())){
			buybackApply.setProdNo(BuybackCodeConst.BUYBACK_ZAI_PRODNO);
		}
		buybackApply.setBillClass(saleApply.getBillClass());
		buybackApply.setBillType(saleApply.getBillType());
		buybackApply.setDueDt(saleApply.getRebuyDueDt());
		buybackApply.setBillStorageOrg(saleApply.getBillStorageBrchno());
		buybackApply.setBillStorageOrgName(saleApply.getBillStorageName());
		buybackApply.setAimBranchNo(saleApply.getAimBranchNo());//交易对手
		buybackApply.setIsDummy(saleApply.getIsDummy());
		buybackApply.setIsInner(saleApply.getIsInner());
		buybackApply.setIsOnline(saleApply.getIsOnline());
		buybackApply.setSaleDt(saleApply.getSaleDt());
		buybackApply.setInnerAccount(saleApply.getInnerAccount());
		buybackApply.setCustName(saleApply.getCustName());
		buybackApply.setRate(saleApply.getRate());
		buybackApply.setRateType("360");//rebuyDueDt
		buybackApply.setBuybackRate(Double.parseDouble(buybackRate));
		buybackApply.setBuybackOpenDt(saleApply.getBuybackOpenDt());
		buybackApply.setBuybackDueDt(saleApply.getRebuyDueDt());
		buybackApply.setBuybackMoney(saleApply.getBuybackMoney());
		buybackApply.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
		if(buybackApply2==null){
			this.applyDao.addBuybackApplyInfo(buybackApply);
		}else{
			this.modifyBuybackApplyInfo(buybackApply);
		}
		//批量试算
		for(BuybackBillInfo toBuyback : billList){
			toBuyback.setDelayType(buybackBill.getDelayType());
			toBuyback.setDelayDays(buybackBill.getDelayDays());
			//开始为清单利息试算 试算成功返回大于0
			rs = startInterestTrialForEntity(toBuyback, saleApply,buybackDt,buybackRate,isSameCity);
			//结果小于0试算失败 全部回滚
			if( rs <= 0 ) {return 0;}
		}
		return rs;
	}
	
	/**
	 * 利息试算 单个清单试算
	 * @param buyback
	 * @param buybackApply
	 * @return
	 */
	private int startInterestTrial(BuybackBillInfo buyback,SaleApplyInfo saleApply,String buybackRate) throws Exception {
		IInterestService interestService = ServiceFactory.getInterestService();
		InterestReqDTO interestDTO = new InterestReqDTO();
		//实际赎回日
		interestDTO.setBeginDate(DateTimeUtil.parseStringToDate(buyback.getBuybackDt()));
		//赎回截止日
		interestDTO.setEndDate(DateTimeUtil.parseStringToDate(saleApply.getRebuyDueDt()));
		interestDTO.setAmount(new BigDecimal(buyback.getBillMoney()));
		interestDTO.setRate(new BigDecimal(buybackRate));
		if(BuybackCodeConst.SALE_IN_PRODNO.equals(saleApply.getProdNo())){
			interestDTO.setProductNo(BuybackCodeConst.BUYBACK_IN_PRODNO);
		}else if(BuybackCodeConst.SALE_OUT_PRODNO.equals(saleApply.getProdNo())){
			interestDTO.setProductNo(BuybackCodeConst.BUYBACK_OUT_PRODNO);
		}else if(BuybackCodeConst.SALE_ZAI_PRODNO.equals(saleApply.getProdNo())){
			interestDTO.setProductNo(BuybackCodeConst.BUYBACK_ZAI_PRODNO);
		}
		interestDTO.setBillClass(saleApply.getBillClass());
		interestDTO.setBillType(saleApply.getBillType());
		interestDTO.setDelayDays(buyback.getDelayDays());
		interestDTO.setChargeKind(buyback.getDelayType());
		interestDTO.setRateType(saleApply.getRateType());
		//电票赎回均为同城
		interestDTO.setIfSameCity("1");
		//进行利息试算
		InterestResultDTO interestResult = interestService.getInterest(interestDTO);
		//实付金额：是指 支付给贴现人的实际金额
		double payMoney = MathScaleUtil.subtract(buyback.getBillMoney(), interestResult.getInterest().doubleValue());
		payMoney=MathScaleUtil.round(payMoney, 2);
		buyback.setInterest(interestResult.getInterest().doubleValue());
		buyback.setBuybackMoney(payMoney);
		buyback.setInterestDays(interestResult.getInterestCalDays());
		//保存清单试算结果
		return updateBuybackBillInfo(buyback);
	}
	
	/**
	 * 利息试算 单个清单试算(纸票)
	 * @param buyback
	 * @param saleApply
	 * @return
	 */
	private int startInterestTrialForEntity(BuybackBillInfo buyback,SaleApplyInfo saleApply,String buybackDt,String buybackRate,String isSameCity) throws Exception {
		IInterestService interestService = ServiceFactory.getInterestService();
		InterestReqDTO interestDTO = new InterestReqDTO();
		//实际赎回日
		interestDTO.setBeginDate(DateTimeUtil.parseStringToDate(buybackDt));
		//赎回截止日
		interestDTO.setEndDate(DateTimeUtil.parseStringToDate(saleApply.getRebuyDueDt()));
		interestDTO.setAmount(new BigDecimal(buyback.getBillMoney()));
		interestDTO.setRate(new BigDecimal(buybackRate));
		if(BuybackCodeConst.SALE_IN_PRODNO.equals(saleApply.getProdNo())){
			interestDTO.setProductNo(BuybackCodeConst.BUYBACK_IN_PRODNO);
		}else if(BuybackCodeConst.SALE_OUT_PRODNO.equals(saleApply.getProdNo())){
			interestDTO.setProductNo(BuybackCodeConst.BUYBACK_OUT_PRODNO);
		}else if(BuybackCodeConst.SALE_ZAI_PRODNO.equals(saleApply.getProdNo())){
			interestDTO.setProductNo(BuybackCodeConst.BUYBACK_ZAI_PRODNO);
		}
		interestDTO.setBillClass(saleApply.getBillClass());
		interestDTO.setBillType(saleApply.getBillType());
		interestDTO.setDelayDays(buyback.getDelayDays());
		interestDTO.setChargeKind(buyback.getDelayType());
		interestDTO.setRateType(saleApply.getRateType());
		interestDTO.setIfSameCity(isSameCity);
		//进行利息试算
		InterestResultDTO interestResult = interestService.getInterest(interestDTO);
		//如果计息天数为负数，计息为0
		if(interestResult.getInterestCalDays()<0){
			interestResult.setInterestCalDays(0);
			interestResult.setInterest(BigDecimal.valueOf(0));
		}
		//实付金额：是指支付给贴现人的实际金额
		double payMoney = MathScaleUtil.subtract(buyback.getBillMoney(), interestResult.getInterest().doubleValue());
		payMoney=MathScaleUtil.round(payMoney, 2);
		buyback.setInterest(interestResult.getInterest().doubleValue());
		buyback.setBuybackMoney(payMoney);
		buyback.setInterestDays(interestResult.getInterestCalDays());
		//保存清单试算结果
		return updateBuybackBillInfo(buyback);
	}
	
	/**
	 * 功能描述：调整利息
	 * @param id
	 * @return
	 * @throws Exception 
	 * @throws BizAppException 
	 */
	public int interestAdjust(String id,String adjustMoney) throws Exception{
		int rs = 0;
		BuybackBillInfo buybackBill=this.getBuybackBillInfo(id);
		RgctBill rgctBill=RcServiceFactory.getRcRegBillService().getRgctBillById(buybackBill.getRgctId());
		RgctBillHist hist=rgctBill.getHist();
		RgctBillInfo info=rgctBill.getInfo();
		Double adjustMoneyD=null;
		adjustMoneyD=Double.valueOf(adjustMoney);
		if(adjustMoneyD>info.getBillMoney()||adjustMoneyD<0){
			return rs;
		}
		hist.setInterest(adjustMoneyD);
		hist.setDealMoney(MathScaleUtil.subtract(info.getBillMoney(), adjustMoneyD));
		RcServiceFactory.getRcRegBillService().updateRgctBillHist(hist);
		buybackBill.setInterest(adjustMoneyD);
		buybackBill.setBuybackMoney(MathScaleUtil.subtract(info.getBillMoney(), adjustMoneyD));
		buybackBill.setDraftPayMoney(MathScaleUtil.subtract(info.getBillMoney(), adjustMoneyD));
		rs=this.updateBuybackBillInfo(buybackBill);
		return rs;
	}
	
	/**
	 * 034建清单、批次
	 * @author songzx
	 * @param bill
	 * @throws ServiceException
	 */
	public void buyBackBillInit(RgctBill bill, String ifInner, String saleId) throws Exception {
		RgctBillInfo rgctBillInfo = bill.getInfo();
		SaleBillInfo saleBill = null;
		// 先查回购批次是否创建
		// 回购批次与转卖批次关联 纸票
		if ("1".equals(rgctBillInfo.getBillClass()) && "1".equals(ifInner)) {
			// saleBill=buybackSignAcceptDao.getSaleBillByRgctIdOrOperStatus(rgctBillInfo.getId(),SaleCodeConst.SALE_BILL_CODE_STATUS_ACCOUNT,ifInner);
			//saleBill = buybackSignAcceptDao.getEntity(SaleBillInfo.class, saleId);
		} else {
			saleBill = saleBillDao.getSaleBillInfoByRgctId(rgctBillInfo.getId(),StatusUtils.handleStatusNoCheck("SaleAccountController", "elecSaleAccount",null));
		}
		createInfoAndApply(bill, saleBill);
	}
	/**
	 * 建批次建清单
	 * 
	 * @param buybackSignAcceptService
	 * @param rgctBillInfo
	 * @param rgctBillHist
	 * @param saleBill
	 * @throws Exception 
	 */
	private void createInfoAndApply(RgctBill bill, SaleBillInfo saleBill) throws Exception {
		String buybackapId;
		SaleApplyInfo saleApplyInfo = saleApplyDao.getSaleApplyInfo(saleBill.getSaleId());
		BuybackApplyInfo buybackApplyInfo = applyDao.getBuybackApplyInfoBySaveId(saleBill.getSaleId());
		RgctBillInfo rgctBillInfo = bill.getInfo();
		RgctBillHist rgctBillHist = bill.getHist();
		if (buybackApplyInfo == null) {
			buybackapId = createBuybackApply(saleBill, saleApplyInfo, bill);
		} else {
			buybackapId = buybackApplyInfo.getBuybackId();
			if (rgctBillHist.getBackRate() != 0.0) {
				buybackApplyInfo.setBuybackRate(MathScaleUtil.multiply(rgctBillHist.getBackRate(), 100.0));
			}
			buybackApplyInfo.setIsOnline(rgctBillHist.getIsOnline());
			if(CommonConst.BILL_CLASS_ELEC.equals(saleApplyInfo.getBillClass())){
				buybackApplyInfo.setCreateDt(rgctBillHist.getRegressDt());
			}else{
				buybackApplyInfo.setCreateDt(DateTimeUtil.getWorkday());
			}
			buybackApplyInfo.setCreateTime(DateTimeUtil.get_hhmmss_time());
			//回购式转卖记撤销记账后更改转卖批次信息后再记账，需更改回购批次信息
			buybackApplyInfo.setBillStorageOrg(saleApplyInfo.getBillStorageBrchno());
			buybackApplyInfo.setBillStorageOrgName(saleApplyInfo.getBillStorageName());
			buybackApplyInfo.setAimBranchNo(saleApplyInfo.getAimBranchNo());//交易对手
			buybackApplyInfo.setIfDummy(saleApplyInfo.getIsDummy());//是否移票
			buybackApplyInfo.setIsInner(saleApplyInfo.getIsInner());//是否系统内
			buybackApplyInfo.setSaleDt(saleApplyInfo.getSaleDt());//原转出日
			buybackApplyInfo.setInnerAccount(saleApplyInfo.getInnerAccount());
			buybackApplyInfo.setCustName(saleApplyInfo.getCustName());
			buybackApplyInfo.setRate(saleApplyInfo.getRate()); //原转出利率
			buybackApplyInfo.setBuybackOpenDt(saleApplyInfo.getBuybackOpenDt());
			buybackApplyInfo.setBuybackMoney(saleApplyInfo.getBuybackMoney());
			String prodNo = "";
			if(BuybackCodeConst.SALE_IN_PRODNO.equals(saleApplyInfo.getProdNo())){
				prodNo = BuybackCodeConst.BUYBACK_IN_PRODNO;
			}else if(BuybackCodeConst.SALE_OUT_PRODNO.equals(saleApplyInfo.getProdNo())){
				prodNo = BuybackCodeConst.BUYBACK_OUT_PRODNO;
			}else if(BuybackCodeConst.SALE_ZAI_PRODNO.equals(saleApplyInfo.getProdNo())){
				prodNo = BuybackCodeConst.BUYBACK_ZAI_PRODNO;
			}
			buybackApplyInfo.setProdNo(prodNo);
			buybackApplyInfo.setBranchNo(saleApplyInfo.getBillStorageBrchno());
			buybackApplyInfo.setOperNo(saleApplyInfo.getOperNo());
			
			buybackApplyInfo.setBuybackDueDt(saleApplyInfo.getRebuyDueDt());
			
			buybackApplyInfo.setProdAttr(saleApplyInfo.getProdAttr());
			buybackApplyInfo.setSaleId(saleApplyInfo.getSaleId());
			buybackApplyInfo.setDueDt(rgctBillHist.getBackEndDt());
			buybackApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			applyDao.modifyBuybackApplyInfo(buybackApplyInfo);
		}
		createBillInfoByElec(buybackapId, saleBill, saleApplyInfo, rgctBillInfo, rgctBillHist);
	}

	private void createBillInfoByElec(String buybackapId, SaleBillInfo saleBill, SaleApplyInfo saleApplyInfo,
			RgctBillInfo rgctBillInfo, RgctBillHist rgctBillHist) throws Exception {
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		BuybackBillInfo buybackBillInfo = new BuybackBillInfo();
		buybackBillInfo.setBuybackmxId(sequenceService.getBUYBACK_BILL_INFO_ID());
		buybackBillInfo.setBillClass(rgctBillInfo.getBillClass());
		buybackBillInfo.setBillType(rgctBillInfo.getBillType());
		buybackBillInfo.setBillNo(rgctBillInfo.getBillNo());
		buybackBillInfo.setAcceptor(rgctBillInfo.getAcceptor());
		buybackBillInfo.setAcceptorBankNo(saleBill.getAcceptorBankNo());
		buybackBillInfo.setAcceptorBankName(saleBill.getAcceptorBankName());
		buybackBillInfo.setPayeeName(rgctBillInfo.getPayeeName());
		buybackBillInfo.setDraweeAddr(rgctBillInfo.getDraweeAddr());
		buybackBillInfo.setPayeeBankNo(rgctBillInfo.getPayeeBankNo());
		buybackBillInfo.setConferNo(rgctBillInfo.getConferNo());
		buybackBillInfo.setIsAccpt(rgctBillInfo.getIsAccpt());
		buybackBillInfo.setRemitterCustNo(rgctBillInfo.getRemitterCustNo());
		buybackBillInfo.setDueDt(rgctBillInfo.getDueDt());
		buybackBillInfo.setIssueDt(rgctBillInfo.getIssueDt());
		buybackBillInfo.setRemitter(rgctBillInfo.getRemitter());
		buybackBillInfo.setRemitterAcct(rgctBillInfo.getRemitterAcct());
		buybackBillInfo.setRemitterBankName(rgctBillInfo.getDraweeBankName());
		buybackBillInfo.setRemitterBankNo(rgctBillInfo.getDraweeBankNo());
		buybackBillInfo.setBillMoney(rgctBillInfo.getBillMoney());
		buybackBillInfo.setPayeeBankName(rgctBillInfo.getPayeeBankName());
		buybackBillInfo.setPayeeAcct(rgctBillInfo.getPayeeAcct());
		buybackBillInfo.setBillBeforeOwner(rgctBillHist.getFromName());// 交易前手
		buybackBillInfo.setBillOwner(rgctBillHist.getHoldCustName());// 持票人
		buybackBillInfo.setRgctId(rgctBillInfo.getId());
		buybackBillInfo.setRegressDt(saleApplyInfo.getRebuyDueDt());
		buybackBillInfo.setIsInner(saleApplyInfo.getIsInner());
		buybackBillInfo.setIsDummy(saleApplyInfo.getIsDummy());
		buybackBillInfo.setAimBranchNo(saleApplyInfo.getAimBranchNo());
//		buybackBillInfo.setInnerAccount(saleApplyInfo.getInnerAccount());
		buybackBillInfo.setSaleDt(saleApplyInfo.getSaleDt());
		buybackBillInfo.setBranchNo(saleApplyInfo.getBillStorageBrchno());
		buybackBillInfo.setBuyDeptNo(saleBill.getBuyDeptNo());
		buybackBillInfo.setCreateTime(DateTimeUtil.get_hhmmss_time());
		buybackBillInfo.setSalemxId(saleBill.getSalemxId());
		buybackBillInfo.setIsOnline(rgctBillHist.getIsOnline());
		// buybackBillInfo.setReceiveMoney(rgctBillHist.getDealMoney());
		buybackBillInfo.setDraftPayMoney(rgctBillHist.getBackAmount());
		// buybackBillInfo.setInterest(MathScaleUtil.subtract(saleBillInfo.getBillAmount(),rgctBillHist.getDealMoney()
		// ));
		buybackBillInfo.setSaleInterest(saleBill.getInterest());
		buybackBillInfo.setSaleReceiveMoney(saleBill.getReceiveMoney());
		if (rgctBillHist.getRegressDt().compareTo(rgctBillHist.getBackEndDt()) < 0) {
			buybackBillInfo.setInterestDays(DateTimeUtil.getDiffDays(rgctBillHist.getBackEndDt(),rgctBillHist.getRegressDt()));
		} else {
			buybackBillInfo.setInterestDays(Long.valueOf("0"));
		}
		//FISME BD460 暂未定义状态机
		// 清单状态
		buybackBillInfo.setOperStatus("BSa10");
		// 批次ID
		buybackBillInfo.setBuybackId(buybackapId);
		buybackBillInfo.setReqDraftNo(rgctBillHist.getDraftLogId());// 响应报文
		buybackBillInfo.setApplyCancelFlag("0");// 对方未撤销
		// buybackBillInfo.setApplyTellerNo(userInfo.getUserNo());
		buybackBillInfo.setForbidFlag(rgctBillHist.getForbidFlag());
		//保存回购清单
		billDao.addBuybackBillInfo(buybackBillInfo);
	}
	/**
	 * 回购批次表插值
	 * @param saleBillInfoList
	 * @param saapId
	 * @param userInfo
	 * @return
	 * @throws ServiceException
	 */
	public String createBuybackApply(SaleBillInfo saleBill,SaleApplyInfo saleApplyInfo,RgctBill rgctBill) throws Exception{
		BuybackApplyInfo buybackApplyInfo = new BuybackApplyInfo();
		try {
//			RgctBill rgctBill =rcSaleBackService.getRgctBillById(saleBillInfo.getRgctId());
			ISequenceService sequenceService=ServiceFactory.getSequenceService();
			String batchNo = sequenceService.getBuybackApplyNo(saleApplyInfo.getBillStorageBrchno());
			//创建BuybackApplyInfo
			buybackApplyInfo.setBillStorageOrg(saleApplyInfo.getBillStorageBrchno());
			buybackApplyInfo.setBillStorageOrgName(saleApplyInfo.getBillStorageName());
			buybackApplyInfo.setAimBranchNo(saleApplyInfo.getAimBranchNo());//交易对手);
			buybackApplyInfo.setIfDummy(saleApplyInfo.getIsDummy());//是否移票
			buybackApplyInfo.setIsInner(saleApplyInfo.getIsInner());//是否系统内
			buybackApplyInfo.setSaleDt(saleApplyInfo.getSaleDt());//原转出日
			buybackApplyInfo.setInnerAccount(saleApplyInfo.getInnerAccount());
//			buybackApplyInfo.setCustId(saleApplyInfo.getCustId());
			buybackApplyInfo.setCustName(saleApplyInfo.getCustName());
			buybackApplyInfo.setRate(saleApplyInfo.getRate()); //原转出利率
			buybackApplyInfo.setRateType("360");
			buybackApplyInfo.setIsOnline(saleApplyInfo.getIsOnline());
			buybackApplyInfo.setBuybackOpenDt(saleApplyInfo.getBuybackOpenDt());
			buybackApplyInfo.setBuybackMoney(saleApplyInfo.getBuybackMoney());
			buybackApplyInfo.setStatus("0");
			buybackApplyInfo.setBatchNo(batchNo);
			buybackApplyInfo.setBuybackId(sequenceService.getBUYBACK_APPLY_INFO_ID());
			String prodNo = "";
			if(BuybackCodeConst.SALE_IN_PRODNO.equals(saleApplyInfo.getProdNo())){
				prodNo = BuybackCodeConst.BUYBACK_IN_PRODNO;
			}else if(BuybackCodeConst.SALE_OUT_PRODNO.equals(saleApplyInfo.getProdNo())){
				prodNo = BuybackCodeConst.BUYBACK_OUT_PRODNO;
			}else if(BuybackCodeConst.SALE_ZAI_PRODNO.equals(saleApplyInfo.getProdNo())){
				prodNo = BuybackCodeConst.BUYBACK_ZAI_PRODNO;
			}
			buybackApplyInfo.setProdNo(prodNo);
			buybackApplyInfo.setBillClass(rgctBill.getInfo().getBillClass());
			buybackApplyInfo.setBillType(rgctBill.getInfo().getBillType());
			buybackApplyInfo.setBranchNo(saleApplyInfo.getBillStorageBrchno());
			buybackApplyInfo.setOperNo(saleApplyInfo.getOperNo());
			buybackApplyInfo.setDueDt(rgctBill.getHist().getBackEndDt());
			if(rgctBill.getHist().getBackRate() != 0.0){
				buybackApplyInfo.setBuybackRate(MathScaleUtil.multiply(rgctBill.getHist().getBackRate(),100.0));
			}
			buybackApplyInfo.setBuybackDueDt(saleApplyInfo.getSaleDt());
			if(CommonConst.BILL_CLASS_ELEC.equals(saleApplyInfo.getBillClass())){
				buybackApplyInfo.setCreateDt(rgctBill.getHist().getRegressDt());
			}else{
				buybackApplyInfo.setCreateDt(DateTimeUtil.getWorkday());
			}
			buybackApplyInfo.setCreateTime(DateTimeUtil.get_hhmmss_time());
			buybackApplyInfo.setProdAttr(saleApplyInfo.getProdAttr());
			buybackApplyInfo.setSaleId(saleApplyInfo.getSaleId());
			buybackApplyInfo.setIsOnline(rgctBill.getHist().getIsOnline());
			buybackApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			//保存BuybackApplyInfo
			applyDao.addBuybackApplyInfo(buybackApplyInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return buybackApplyInfo.getBuybackId();
	}
	/**
	 * 功能描述：得到到期回购批次列表
	 * @param page 分页信息
	 * @param query 查询条件
	 * @return 批次列表
	 * @throws Exception 
	 */
	public List<BuybackApplyInfo> getBuybackApplyListForCondition(Page page, BuybackSearchBean query) throws Exception{
		initQueryCondition(query);
		query.setDfaultSrchTbAliasName("BILL");
		return applyDao.getBuybackApplyListForCondition(page, query);
	}
	
	/**
	 * 功能描述：得到到期回购批次详细信息
	 * @param buybackId 批次id
	 * @param query 查询条件
	 * @return 批次列表
	 * @throws SQLException 
	 */
	public BuybackApplyInfo getBuybackApplyInfo(String buybackId, BuybackSearchBean query) throws SQLException{
		return applyDao.getBuybackApplyInfo(buybackId, query);
	}


	/**
	 * 功能描述：得到到期回购批次内的票据列表信息
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException 
	 */
	public List<BuybackBillInfo> getBuybackBillListForBatch(Page page, BuybackSearchBean query) throws SQLException{
		initQueryCondition(query);
		query.addProperty2TableObj("branchNo", "bill");
		query.addProperty2TableObj("saleId", "bill");
		return billDao.getBuybackBillList(page,query);
	}
	
	/**
	 * 功能描述：清空利息等值
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public void clearInterestTrialInfo(BuybackSearchBean query) throws SQLException {
		billDao.clearInterestTrialInfo(query);
	}
	
	/**
	 * 功能描述：清空利息等值(纸票)
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public void clearInterestTrialInfoForEntity(BuybackSearchBean query) throws SQLException {
		billDao.clearInterestTrialInfoForEntity(query);
	}
	
	/**
	 * 功能描述：是否进行过利息试算
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public boolean isInterestTrial(String ids) throws SQLException {
		return billDao.isInterestTrial(ids);
	}
	/**
	 * 初始化查询条件
	 * @param query
	 */
	private void initQueryCondition(BuybackSearchBean query){
		query.setBranchNo(ResourceUtil.getSessionLoginfo().getBrchNo());
		query.addProperty2TableObj("branchNo", "APPLY");
		query.addProperty2TableObj("applyStatus", "APPLY");
		query.setDfaultSrchTbAliasName("bill");
	    query.addSpecialOpertion("endDay",BaseSearchBean.LESS_AND_EQUAL);
	    query.addSqlPropretyMapping("endDay", "dueDt");
	    query.addSpecialOpertion("startDay",BaseSearchBean.MORE_AND_EQUAL);
        query.addSqlPropretyMapping("startDay", "dueDt");
		query.addSpecialOpertion("opers",BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		query.addSqlPropretyMapping("operStatus", "operStatus");
	}

	/**
	 * 功能描述:电票拒绝签收
	 * @throws Exception 
	 * 
	 */
	public void noReceive(String ids) throws Exception {
		List<BuybackBillInfo> list= billDao.getBuybackBillListByIds(ids);
//		String buybackId = null;
		for(int i = 0 ;i<list.size();i++){
			BuybackBillInfo buybackBill =list.get(i);
//			buybackId = buybackBill.getBuybackId();
		    buybackBill.setOperStatus(StatusUtils.handleStatus("BuybackApplyController", "buybackApply", "0", buybackBill.getOperStatus()));
		    buybackBill.setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserNo());
		    billDao.modifyBuybackBillInfo(buybackBill);
		    RgctBill rgctBill=RcServiceFactory.getRcRegBillService().getRgctBillById(list.get(0).getRgctId());
		    RgctBillHist billHist = rgctBill.getHist();
			billHist.setToName(ResourceUtil.getSessionLoginfo().getBranchName());
			if(DiscCodeConst.BILL_CLASS_ELEC.equals(buybackBill.getBillClass())){
				billHist.setSignerSign(CommUtils.getSignerSign(billHist.getToBankNo()));
				Branch branch=ServiceFactory.getBranchService().getBranchByBrchBankNo(billHist.getToBankNo());
				billHist.setToRole(branch.getPartnerType());
				billHist.setToOrgcode(branch.getOrgCode());
			}
			billHist.setSignDt(DateTimeUtil.getWorkday());
			rgctBill.setHist(billHist);	
		    RcServiceFactory.getRcBuybackService().rejectSaleBackEndorse(rgctBill);
		}
		
		//为修改查询批次，所以不用插叙条件
/*		BuybackApplyInfo applyinfo = applyDao.getBuybackApplyInfo(buybackId,null);
		//撤销审核状态到上已审核节点
		BuybackSearchBean query = new BuybackSearchBean();
		query.setBuybackId(buybackId);
		query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "buybackApply", "1")[0]);
		//查询批次下是否存在没有提交的清单
		List<BuybackBillInfo> listApply = billDao.getBuybackBillList(null, query);
		//如果存在则更新批次状态为未提交
		if( listApply == null || listApply.size() == 0 ) {
			applyinfo.setApplyStatus(CommonConst.APPLY_STATUS_SUBMIT);
			applyDao.modifyBuybackApplyInfo(applyinfo);
		}*/
	}
	
	
	/**
	 * 功能描述:撤销票据状态到上一个操作状态
	 * @param conName 状态机控制类名称
	 * @param methodName 状态机控制类方法
	 * @param ids 要撤销的票据id
	 * @return int
	 */
	public int cancel(String conName,String methodName,String param,String ids,String buybackId) throws Exception{
		if(StringUtils.isBlank(ids)) throw new BizAppException(ISysErrorNo.PARAM_VALIDATE_EXCEPTION_CODE,"请选择撤销票据");
		String idArr[] = ids.split(",");
		int rs = 0;
		double auditAmt = 0.0;
//		String buybackId = null;
		for (String buybackmxId : idArr) {
			BuybackBillInfo bill = billDao.getBuybackBillInfo(buybackmxId);
//			buybackId = bill.getBuybackId();
			bill.setOperStatus(StatusUtils.queryStatus(conName,methodName,param)[0]);
			bill.setCurStatus(DiscCodeConst.CUR_STATUS_NO);
			auditAmt += bill.getBillMoney();
			rs = this.billDao.modifyBuybackBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		//为修改查询批次，所以不用插叙条件
		BuybackApplyInfo apply = applyDao.getBuybackApplyInfo(buybackId,null);
		apply.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
		this.modifyBuybackApplyInfo(apply);
		//撤销审核状态到上已审核节点
		cancelAuditTask(auditAmt, apply);
		/*BuybackSearchBean query = new BuybackSearchBean();
		query.setBuybackId(buybackId);
		query.setOperStatus(StatusUtils.queryStatus("BuybackApplyController", "buybackApply", "1")[0]);
		
		//查询批次下是否存在没有提交的清单
		List<BuybackBillInfo> list = billDao.getBuybackBillList(null, query);
		//如果存在则更新批次状态为未提交
		if( list != null && list.size() > 0 ) {
			applyinfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
			rs = applyDao.modifyBuybackApplyInfo(applyinfo);
		}*/
		return rs;
	}
	
	/**
	 * 功能描述：撤销审核状态到上已审核节点
	 * @param auditAmt
	 * @param buybackId
	 * @throws Exception
	 */
	private void cancelAuditTask(double auditAmt,BuybackApplyInfo applyinfo) throws Exception{
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
        auditTask.setBatchId(applyinfo.getBuybackId());
        auditTask.setEntityName("discApplyController.do?method=billManage");
        auditTask.setEntityService("buybackId");
	    ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
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
		/*String userNo = ResourceUtil.getSessionLoginfo().getUserNo();
		if(StringUtils.isBlank(ids)) return rs;
		String idArr[] = ids.split(",");
		for (String buybackmxId : idArr) {
			BuybackBillInfo bill = this.billDao.getBuybackBillInfo(buybackmxId);
			bill.setOperStatus(StatusUtils.handleStatus("DiscAuditController", "audit", status,bill.getOperStatus()));
			bill.setAuditOperNo(userNo);
			bill.setIsAudited("1");
			rs = this.billDao.modifyBuybackBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		if("0".equals(status)){
			String discId = billDao.getDiscBillInfo(idArr[0]).getDiscId();
			//查询批次下是否存在没有提交的清单
			List<DiscBillInfo> list = billDao.getDiscBillListForDiscIdAndStatus(discId,StatusUtils.queryStatus("DiscApplyController", "searchBatch", null)[0]);
			//如果存在则更新批次状态为未提交
			if( list != null && list.size() > 0 ) {
				//为修改查询批次，所以不用插叙条件
				DiscApplyInfo applyinfo = discApplyDao.getDiscApplyInfo(discId,null);
				applyinfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
				rs = discApplyDao.modifyDiscApplyInfo(applyinfo);
			}
		}*/
		return rs;
	}
	
	/**
	 * 审核提交(纸票)
	 */
	public int auditSubmitForEntity(String ids, String status,BuybackSearchBean query) throws Exception {
		IBranchService brchService = ServiceFactory.getBranchService();
		int rs = 0;
		if(StringUtils.isBlank(ids)){
			return rs;
		}
		BuybackApplyInfo apply = this.getBuybackApplyInfo(query.getBuybackId(),query);
		if ("0".equals(status) || status == BuybackCodeConst.PASS_NO) {
			apply.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
			rs = applyDao.modifyBuybackApplyInfo(apply);
		}
		Branch brch = brchService.getBranch(apply.getBranchNo());
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		List<BuybackBillInfo> billList=billDao.getBuybackBillListByIds(ids);
		String rcIds="";
		Map<String, RgctBill> rcMap=new HashMap<String, RgctBill>();
		for (int i = 0; i <billList.size(); i++) {
			BuybackBillInfo bill=billList.get(i);
			rcIds=rcIds+bill.getRgctId()+",";
		}
		List<RgctBill> rcBillList=RcServiceFactory.getRcRegBillService().getRgctBillList(rcIds);
		for (int i = 0; i <rcBillList.size(); i++) {
			RgctBill rc=rcBillList.get(i);
			rcMap.put(rc.getInfo().getId(), rc);
		}
		for (int i = 0; i <billList.size(); i++) {
			BuybackBillInfo bill =billList.get(i);
			bill.setOperStatus(StatusUtils.handleStatus("BuybackAuditController","buybackAudit", status, bill.getOperStatus()));
//			bill.setCurStatus(CommonConst.CUR_STATUS_SALE_OVER);
			bill.setBranchNo(user.getBranchNo());
			bill.setAuditOperNo(user.getUserId());
			String rgctId = bill.getRgctId();
			RgctBill rgctBill =rcMap.get(rgctId);
			if (BuybackCodeConst.IS_INNER.equals(bill.getIsInner())) {
				//TODO 系统内回购
			}
			rs = billDao.modifyBuybackBillInfo(bill);
			if( rs <= 0 ){//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}
	/*public int auditSubmitForEntity(String ids, String status,BuybackSearchBean query) throws Exception {
	int rs = 0;
	if (StringUtils.isBlank(ids))
		return rs;
	if ("0".equals(status) || status == BuybackCodeConst.PASS_NO) {
		BuybackApplyInfo apply = this.getBuybackApplyInfo(query.getBuybackId(),query);
		apply.setApplyStatus(CommonConst.APPLY_STATUS_DELETE);
		rs = applyDao.modifyBuybackApplyInfo(apply);
	}
	for (String id : ids.split(",")) {
		BuybackBillInfo bill = this.getBuybackBillInfo(id);
		bill.setOperStatus(StatusUtils.handleStatus("BuybackAuditController","buybackAudit", status, bill.getOperStatus()));
		// bill.setCheckDate(DateTimeUtil.getWorkdayString());
		// bill.setCheckTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		bill.setBranchNo(user.getBranchNo());
		bill.setAuditOperNo(user.getUserId());
		rs = billDao.modifyBuybackBillInfo(bill);
		if (rs <= 0) {
			// 如果有执行失败的 直接返回失败
			return rs;
		}
	}
	return rs;

}*/
	
	/**
	 * 功能描述：票据记账
	 * @param ids
	 * @return
	 */
	public void applyAccount(String buybackId,String ids) throws Exception{
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		BranchDao branchdao = new BranchDao();
		IRcBaseService rcService = RcServiceFactory.getRcBaseService();
		Map<String,RgctBill> rgctmap = new HashMap<String, RgctBill>();
		List<BuybackBillInfo> buybacklist = billDao.getBuybackBillListByIds(ids);
		IDB session = DBFactory.getDB();
		String rgctids = "";
		if(buybacklist.isEmpty()) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "当前票据不支持此操作");
		}
		for (int i = 0; i < buybacklist.size(); i++) {
			rgctids = rgctids + buybacklist.get(i).getRgctId()+",";
		}
		List<RgctBill> rgctlist = rcService.getRgctBillList(rgctids.substring(0,rgctids.length()-1));
		if(rgctlist.size()==0) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "提交的票据中有不存在的票");
		}
		for (int i = 0; i < rgctlist.size(); i++) {
			rgctmap.put(rgctlist.get(i).getHist().getRgctId(), rgctlist.get(i));
		}
		Branch branch = branchdao.getBranch(userInfo.getBrchNo());
		
		for (BuybackBillInfo bill : buybacklist) {
			//BuybackBillInfo bill = this.billDao.getBuybackBillInfo(buybackmxId);
			try {
				session.beginTransaction();
				bill.setOperStatus(StatusUtils.handleStatus("BuybackAccountController", "buybackApplyAccount", null,bill.getOperStatus()));
				bill.setAccountDate(DateTimeUtil.getWorkdayString());
				bill.setAcctOperNo(ResourceUtil.getSessionLoginfo().getUserId());
				billDao.modifyBuybackBillInfo(bill);
				RgctBill rgctBill=rgctmap.get(bill.getRgctId());
				RgctBillHist hist = rgctBill.getHist();
				hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
				hist.setSignerSign(CommUtils.getSignerSign(hist.getToBankNo()));
				hist.setToRole(branch.getPartnerType());
				hist.setToOrgcode(branch.getOrgCode());
				hist.setSignDt(DateTimeUtil.getWorkday());
				hist.setAcctBranchNo(userInfo.getBranchNo());
				hist.setStorageBranchNo(userInfo.getBranchNo());
				hist.setToBankNo(userInfo.getBrchBankNo());
				if(RebuyCodeConst.IS_INNER_TRUE.equals(hist.getIfInner())){
					String oldBillTrack = hist.getBillTrack();
					if("3".equals(oldBillTrack.substring(oldBillTrack.length() - 1))){
						hist.setBillTrack(oldBillTrack.substring(0,oldBillTrack.length() - 1));
					}
				}
				rgctBill.setHist(hist);
				RcServiceFactory.getRcBuybackService().buyBackSign(rgctBill);
				session.endTransaction();
			} catch (Exception e) {
				try {
					session.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("回购记帐处理失败:"+e.getMessage());
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "回购记帐处理失败:"+e.getMessage());
			}
		}
	}
	
	
	/**
	 * 功能描述：票据确认记账
	 * @param buybackId
	 * @param ids
	 */
	public void confirmAccount(String buybackId, String ids)throws Exception{
		BuybackApplyInfo apply = applyDao.getBuybackApplyInfo(buybackId, null);
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		List<BuybackBillInfo> billList = billDao.getBuybackBillListByIds(ids);
		for (BuybackBillInfo bill : billList) {
			bill.setOperStatus(StatusUtils.handleStatus("BuybackAccountController", "buybackDoAccount", null,bill.getOperStatus()));
			bill.setAccountDate(DateTimeUtil.getWorkdayString());
			this.billDao.modifyBuybackBillInfo(bill);
			RgctBill rgctBill=RcServiceFactory.getRcRegBillService().getRgctBillById(bill.getRgctId());
			RgctBillHist hist = rgctBill.getHist();
			hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
		    RcServiceFactory.getRcDiscService().updateRgctBillHist(hist);
		}
		IAccountFacadeService acctService = ServiceFactory.getBuybackAccountService();
		AccountRequestDTO<BuybackApplyInfo,BuybackBillInfo> accountReq = new AccountRequestDTO<BuybackApplyInfo,BuybackBillInfo>();
		accountReq.setApply(apply);
		accountReq.setBillList(billList);
		accountReq.setUserLogonInfo(userInfo);
		acctService.account(accountReq);
	}
	
	/**
	 * 记账(纸票)
	 */
	public void accountForEntity(String ids,String buybackId) throws Exception {
		IBranchService brchService = ServiceFactory.getBranchService();
		BuybackApplyInfo apply = this.getBuybackApplyInfo(buybackId,null);
		Branch brch = brchService.getBranch(apply.getBranchNo());
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		List<BuybackBillInfo> billList=billDao.getBuybackBillListByIds(ids);
		String rcIds="";
		String salemxIds="";
		Map<String, RgctBill> rcMap=new HashMap<String, RgctBill>();
		Map<String, SaleBillInfo> saleMap=new HashMap<String, SaleBillInfo>();
		for (int i = 0; i <billList.size(); i++) {
			BuybackBillInfo bill=billList.get(i);
			rcIds=rcIds+bill.getRgctId()+",";
			salemxIds=salemxIds+bill.getSalemxId()+",";
		}
		String[] salemxIdArr = salemxIds.split(",");
		List<RgctBill> rcBillList=RcServiceFactory.getRcRegBillService().getRgctBillList(rcIds);
		List<SaleBillInfo> saleBillList=saleBillDao.getSaleBillInfoByIds(salemxIdArr);
		for (int i = 0; i <rcBillList.size(); i++) {
			RgctBill rc=rcBillList.get(i);
			rcMap.put(rc.getInfo().getId(), rc);
		}
		for (int i = 0; i <saleBillList.size(); i++) {
			SaleBillInfo saleBillInfo=saleBillList.get(i);
			saleMap.put(saleBillInfo.getSalemxId(), saleBillInfo);
		}
		for (int i = 0; i <billList.size(); i++) {
			BuybackBillInfo buybackBill =billList.get(i);
			buybackBill.setAccountDate(DateTimeUtil.getWorkdayString());
			String rgctId = buybackBill.getRgctId();
			String salemxId=buybackBill.getSalemxId();
			RgctBill rgctBill =rcMap.get(rgctId);
			SaleBillInfo saleBill=saleMap.get(salemxId);
			rgctBill.getHist().setFromBankNo(apply.getAimBranchNo());
			rgctBill.getHist().setFromName(apply.getCustName());
			rgctBill.getHist().setToBankNo(user.getBrchBankNo());
			rgctBill.getHist().setToName(user.getBranchName());
			rgctBill.getHist().setStorageBranchNo(user.getBranchNo());
			rgctBill.getHist().setStorageBranchName(user.getBranchName());
			rgctBill.getHist().setAcctBranchNo(user.getBranchNo());
			rgctBill.getHist().setIfInner(buybackBill.getIsInner());
			rgctBill.getHist().setIsLock("0");
			buybackBill.setOperStatus(StatusUtils.handleStatus("BuybackAccountController","buybackDoAccount", null, buybackBill.getOperStatus()));
			buybackBill.setCurStatus(CommonConst.CUR_STATUS_SALE_OVER);
			buybackBill.setBranchNo(user.getBranchNo());
			buybackBill.setAcctOperNo(user.getUserId());
			saleBill.setIsBuyback(SaleCodeConst.BUYBACK_YES);
			RcServiceFactory.getRcBuybackService().buyBackSign(rgctBill);
			billDao.modifyBuybackBillInfo(buybackBill);
			saleBillDao.modifySaleBillInfo(saleBill);
		}
		IAccountFacadeService acctService = ServiceFactory.getBuybackAccountService();
		AccountRequestDTO<BuybackApplyInfo,BuybackBillInfo> accountReq = new AccountRequestDTO<BuybackApplyInfo,BuybackBillInfo>();
		accountReq.setApply(apply);
		accountReq.setBillList(billList);
		accountReq.setUserLogonInfo(user);
		acctService.account(accountReq);
	}
	
	/**
	 * 根据salemxId获取清单详情
	 * @param salemxId
	 * @return
	 * @throws SQLException
	 */
	public BuybackBillInfo getBuybackBillInfoBySalemxId(String salemxId)
			throws SQLException {
		return billDao.getBuybackBillInfoBySalemxId(salemxId);
	}
	
	/**
	 * 批次列表(申请岗)
	 */
	public List<SaleApplyInfo> getSaleApplyListBySearchBean(Page page,BuybackSearchBean query) throws SQLException {
		/*if (!"".equals(query.getBatchNo()) && query.getBatchNo() != null) {
			query.setBatchNo("%" + query.getBatchNo() + "%");
		}*/
		initQueryCondition(query);
		query.setDfaultSrchTbAliasName("bill");
		query.addProperty2TableObj("branchNo", "bill");
		query.addProperty2TableObj("saleId", "apply");
		query.addProperty2TableObj("billClass", "apply");
		query.addProperty2TableObj("applyStatus", "apply");
		query.addProperty2TableObj("openDate", "apply");
		query.addSpecialOpertion("openDate",BaseSearchBean.LESS_AND_EQUAL);
		query.addSqlPropretyMapping("openDate", "buybackOpenDt");
//		query.addProperty2TableObj("batchNo", "apply");
//		query.addSqlPropretyMapping("batchNo", "batchNo");
		OrderBean order = new OrderBean("saleId", false);
		query.appendOrder(order);
		return applyDao.getSaleApplyListBySearchBean(page, query);
	}
	
	 /**
	 * 获取清单详情
	 */
  public BuybackBillInfo getBuybackBillInfo(String buybackmxId)throws BizAppException{
    try {
      return billDao.getBuybackBillInfo(buybackmxId); 
      } catch (SQLException e) {
    	  throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
    }
  }
  
  /**
	 * 修改批次
	 */
	public void modifyBuybackApplyInfo(BuybackApplyInfo buybackApplyInfo)throws BizAppException {
		try {
			if (applyDao.modifyBuybackApplyInfo(buybackApplyInfo) != 1)
				throw new BizAppException(ISysErrorNo.ERR_DBERR,"修改BuybackApplyInfo失败");
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
  
  /**
	 * 功能描述:撤销票据状态到上一个操作状态(纸票)
	 * @param ids
	 * @return
	 */
	public int cancel(String conName,String methodName,String ids) throws Exception{
		if(StringUtils.isBlank(ids)) {
			throw new BizAppException(ISysErrorNo.PARAM_VALIDATE_EXCEPTION_CODE,"请选择撤销票据");
		}
		String idArr[] = ids.split(",");
		int rs = 0;
		for (String buybackmxId : idArr) {
			BuybackBillInfo bill = this.getBuybackBillInfo(buybackmxId);
			bill.setOperStatus(StatusUtils.handleStatus(conName,methodName,null,bill.getOperStatus()));
			rs = billDao.modifyBuybackBillInfo(bill);
			if( rs <= 0 ){
				//如果有执行失败的 直接返回失败
				return rs;
			}
		}
		return rs;
	}
	
	/**
	 * 功能描述：查询批次下该status状态的清单
	 * @param buybackId
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<BuybackBillInfo> getBuybackBillForBuybackIdAndStatus(String buybackId,String status) throws SQLException, Exception{
		//查询批次下该status状态的清单
		return billDao.getBuybackBillListForBuybackIdAndStatus(buybackId,status);
	}
	
	@Override
	public String getBillIdsString(List<BuybackBillInfo> billList){
		StringBuffer sb = new StringBuffer();
		for(BuybackBillInfo bill : billList){
			sb.append(bill.getBuybackmxId()).append(",");
		}
		return sb.toString();
	}
	
}
