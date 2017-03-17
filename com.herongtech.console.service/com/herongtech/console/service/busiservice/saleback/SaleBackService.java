package com.herongtech.console.service.busiservice.saleback;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor.ROSE;

import com.herongtech.commons.tools.StringUtils;
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
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.acct.bean.AccountResponseDTO;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.bean.InterestReqDTO;
import com.herongtech.console.domain.common.bean.InterestResultDTO;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.dao.RebuyApplyInfoDao;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.domain.saleback.bean.SaleBackSearchBean;
import com.herongtech.console.domain.saleback.bean.SalebackApplyInfo;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.domain.saleback.dao.SalebackApplyInfoDao;
import com.herongtech.console.domain.saleback.dao.SalebackBillInfoDao;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.IInterestService;
import com.herongtech.console.service.interfaces.ISaleBackService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.console.web.busicontroller.common.SaleCodeConst;
import com.herongtech.console.web.busicontroller.common.SalebackCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.dao.EcdsBankDataDao;

import com.herongtech.rc.service.rcservice.IRcSaleBackService;

import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcSaleService;
import com.herongtech.sysconstant.ISysErrorNo;






/**
 * @author 李江涛
 *
 */
public class SaleBackService implements ISaleBackService{

	SalebackApplyInfoDao applyDao = new SalebackApplyInfoDao();
	SalebackBillInfoDao billDao = new SalebackBillInfoDao();
	RebuyBillInfoDao rebuydao = new RebuyBillInfoDao();
	RebuyApplyInfo rebuyapply = new RebuyApplyInfo();
	@Override
	public List<RebuyApplyInfo> getRebuyApplyInfoBydisctypeandnowdate(Page page,
			SaleBackSearchBean query) throws Exception {
		SalebackApplyInfoDao dao = new SalebackApplyInfoDao();
		List<RebuyApplyInfo> rebuyapplylist = new ArrayList<RebuyApplyInfo>();
		
		try {
			
			if("1".equals(query.getBillClass())){
				query.setDfaultSrchTbAliasName("apply");
				query.setWorktime(DateTimeUtil.getWorkdayString());
				query.addSpecialOpertion("worktime",BaseSearchBean.LESS_AND_EQUAL);
				query.addSqlPropretyMapping("worktime", "resaleStaDt");
				
		
				String[] opers = StatusUtils.queryStatus("SalebackApplyController", "applysubmitentity", null);//query.setOperStatus("BSz601");//前置状态			
				query.setOperStatus(opers[0]);
				query.addSpecialOpertion("opers",BaseSearchBean.IN);
				query.addSqlPropretyMapping("operStatus", "operStatus");
				UserLoginfo user = ResourceUtil.getSessionLoginfo();
				query.setBranchNo(user.getBranchNo());
				
				query.addSqlPropretyMapping("branchNo", "branchNo");
				query.addProperty2TableObj("branchNo", "bill");
				query.addProperty2TableObj("operStatus", "bill");
				query.addProperty2TableObj("isInner", "apply");
				
			}else{
				query.setDfaultSrchTbAliasName("bill");
				query.setBillClass("2");
				query.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applysubmitelec", null)[0]);//前置状态		
				query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
				query.addSqlPropretyMapping("operStatus", "operStatus");
				UserLoginfo user = ResourceUtil.getSessionLoginfo();
				query.setBranchNo(user.getBranchNo());
				query.addSqlPropretyMapping("branchNo", "branchNo");
				
			}
			
			
			rebuyapplylist = dao.getRebuyApplyInfoBydisctypeandnowdate(page, query);
		} catch (SQLException e) {
		
			e.printStackTrace();
		} 
		return rebuyapplylist;
	}

	
	@Override
	public List<RebuyApplyInfo> getRebuyApplyInfoBydisctypeandnowdatetwo(Page page,
			SaleBackSearchBean query) throws Exception {
		SalebackApplyInfoDao dao = new SalebackApplyInfoDao();
		List<RebuyApplyInfo> rebuyapplylist = new ArrayList<RebuyApplyInfo>();
		
		try {
			
			if("1".equals(query.getBillClass())){
				query.setDfaultSrchTbAliasName("apply");
				query.setWorktime(DateTimeUtil.getWorkdayString());
				query.addSpecialOpertion("worktime",BaseSearchBean.LESS_AND_EQUAL);
				query.addSqlPropretyMapping("worktime", "resaleStaDt");
				
		
				String[] opers = StatusUtils.queryStatus("SalebackApplyController", "applysubmitentity", null);//query.setOperStatus("BSz601");//前置状态			
				query.setOperStatus(opers[0]);
				query.addSpecialOpertion("opers",BaseSearchBean.IN);
				query.addSqlPropretyMapping("operStatus", "operStatus");
				UserLoginfo user = ResourceUtil.getSessionLoginfo();
				query.setBranchNo(user.getBranchNo());
				
				query.addSqlPropretyMapping("branchNo", "branchNo");
				query.addProperty2TableObj("branchNo", "bill");
				query.addProperty2TableObj("operStatus", "bill");
				query.addProperty2TableObj("isInner", "apply");
				
			}else{
				query.setDfaultSrchTbAliasName("bill");
				query.setBillClass("2");
				query.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applysubmitelec", null)[0]);//前置状态		
				query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
				query.addSqlPropretyMapping("operStatus", "operStatus");
				UserLoginfo user = ResourceUtil.getSessionLoginfo();
				query.setBranchNo(user.getBranchNo());
				query.addSqlPropretyMapping("branchNo", "branchNo");
				
			}
			
			
			rebuyapplylist = dao.getSaleBackApplyForConditiontwo(page,query);
		} catch (SQLException e) {
		
			e.printStackTrace();
		} 
		return rebuyapplylist;
	}
	
	/**
	 * 功能描述：生成返售批次
	 * @param 
	 * @return
	 * @throws BizAppException
	 */
	@Override
	public SalebackApplyInfo saveConditionForAddSalebackApplyInfoAndModifySalebackBillInfo(String rebuyId,String newsalebackId,String iscancel) throws BizAppException{
		SalebackApplyInfoDao dao=new SalebackApplyInfoDao();
		SalebackApplyInfo salebackApplyInfo = new SalebackApplyInfo();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		RebuyApplyInfoDao rebuyappdao = new RebuyApplyInfoDao();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();	
		String salebackId = sequenceService.getSALEBACK_APPLY_INFO_ID();
		String batchNo = sequenceService.getDiscountApplyNo(ResourceUtil.getSessionLoginfo().getBranchNo());
		try {
			
			if(newsalebackId!=null&&!"".equals(newsalebackId)&&"1".equals(iscancel)){
				return dao.getSalebackApplyInfo(newsalebackId);
			}else{
				
				RebuyApplyInfo rebuyappinfo = rebuyappdao.getRebuyApplyInfo(rebuyId);
				salebackApplyInfo.setSalebackId(salebackId);
				salebackApplyInfo.setBatchNo(batchNo);
				salebackApplyInfo.setAimBranchNo(rebuyappinfo.getBusiBranchNo());
				salebackApplyInfo.setBillType(rebuyappinfo.getBillType());
				salebackApplyInfo.setBillClass(rebuyappinfo.getBillClass());
				salebackApplyInfo.setInnerAccount(rebuyappinfo.getTradeAcct());
				salebackApplyInfo.setBuyDt(rebuyappinfo.getRebuyDt());
				salebackApplyInfo.setBuyTime(rebuyappinfo.getCreateTime());
				salebackApplyInfo.setSalebackDueDt(rebuyappinfo.getResaleDueDt());
				salebackApplyInfo.setIsDummy(rebuyappinfo.getIsDummy());
				salebackApplyInfo.setIsInner(rebuyappinfo.getIsInner());
				salebackApplyInfo.setBranchNo(user.getBranchNo());
				salebackApplyInfo.setProdNo(rebuyappinfo.getProdNo());
				salebackApplyInfo.setStatus(rebuyappinfo.getApplyStatus());
				salebackApplyInfo.setCreateDt(DateTimeUtil.getWorkdayString());
				salebackApplyInfo.setCreateTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
				salebackApplyInfo.setOperNo(user.getUserNo());
				salebackApplyInfo.setRateType(CommonConst.RATE_TYPE_YEAR);
				//salebackApplyInfo.setIsOnline(rebuyappinfo.getIsOnline());
				salebackApplyInfo.setSalebackOpenDt(rebuyappinfo.getResaleStaDt());
				salebackApplyInfo.setSalebackDueDt(rebuyappinfo.getResaleDueDt());
				salebackApplyInfo.setSalebackRate(rebuyappinfo.getSalebackRate());
				salebackApplyInfo.setSalebackMoney(rebuyappinfo.getSalebackMoney());
				salebackApplyInfo.setCustName(rebuyappinfo.getCustBankName());
				
				salebackApplyInfo.setDelayType(rebuyappinfo.getDelayType());
				//salebackApplyInfo.setDelayDays(Long.parseLong(rebuyappinfo.getDelayDays()));
				salebackApplyInfo.setBillStorageOrg(rebuyappinfo.getBillStorageOrg());
				salebackApplyInfo.setBillStorageOrgName(rebuyappinfo.getBillStorageOrgName());
				//salebackApplyInfo.setInAcctNo(rebuyappinfo.getTradeAcct());
				salebackApplyInfo.setInAcctType(rebuyappinfo.getTradeAcctType());
				salebackApplyInfo.setInAcctName(rebuyappinfo.getTradeAcctName());
				salebackApplyInfo.setOrderId(rebuyappinfo.getOrderId());
				salebackApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);
				
				
				if (dao.addSalebackApplyInfo(salebackApplyInfo)!= 1) {
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加SalebackApplyInfo失败");
				}
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		return salebackApplyInfo;
	}

	
	/**查出可反售的票据清单
	 * @throws Exception */
	@Override
	public List<SalebackBillInfo> getSalebackBillInfolist(Page page,SaleBackSearchBean bean,String iscancel,String rateids) throws Exception {
		SalebackBillInfoDao billdao = new SalebackBillInfoDao();
		List<SalebackBillInfo> lisst = new ArrayList<SalebackBillInfo>();
		String operstatus = StatusUtils.queryStatus("SalebackApplyController", "applysubmitelec", null)[0];//初始状态 BSb601
	
		try {
			if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bean.getBillClass())){//纸票
				bean.setWorkday(null);
				bean.setWorktime(null);
				bean.setDfaultSrchTbAliasName("bill");
				operstatus = StatusUtils.queryStatus("SalebackApplyController", "applysubmitentity", null)[0];//query.setOperStatus("BSz601");//前置状态
				bean.setOperStatus(operstatus);
				OrderBean order=new OrderBean("salebackmxId",false);
				bean.appendOrder(order);
				lisst=billdao.getSalebackBillInfolistentity(page,bean);
				if(rateids==null){
						for (int i = 0; i < lisst.size(); i++) {	
							
							lisst.get(i).setInterest(0.0);
							lisst.get(i).setInterestDays(0l);		
							lisst.get(i).setEndorseDt(null);
							lisst.get(i).setSalebackMoney(0.0);
							lisst.get(i).setSalebackId("  ");
							billdao.modifySalebackBillInfo(lisst.get(i));
						}
				}else{
						for (int i = 0; i < lisst.size(); i++) {	
							
								if(rateids.indexOf(lisst.get(i).getSalebackmxId())!=-1){
									continue;
								}
						
							lisst.get(i).setInterest(0.0);
							lisst.get(i).setInterestDays(0l);		
							lisst.get(i).setEndorseDt(null);
							lisst.get(i).setSalebackMoney(0.0);
							lisst.get(i).setSalebackId("  ");
							billdao.modifySalebackBillInfo(lisst.get(i));
						}
						
								
				}
				
				
					lisst=billdao.getSalebackBillInfolistentity(page,bean);
					
				
			}else{
				bean.setWorkday(null);
				bean.setWorktime(null);
				bean.setIsInner(null);
				bean.setOperStatus(operstatus);
				bean.setDfaultSrchTbAliasName("bill");
				OrderBean order=new OrderBean("salebackmxId",false);
				bean.appendOrder(order);
				lisst = billdao.getSalebackBillInfolistentity(page,bean);
				 for (int i = 0; i < lisst.size(); i++) {			
					lisst.get(i).setInterest(0.0);
					lisst.get(i).setInterestDays(0l);		
					lisst.get(i).setEndorseDt(null);
					lisst.get(i).setSalebackMoney(0.0);
					lisst.get(i).setSalebackId(null);
					billDao.modifySalebackBillInfo(lisst.get(i));
				}
				 bean.appendOrder(order);
				 lisst = billdao.getSalebackBillInfolistentity(page,bean);
			}
			
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询SalebackBillInfo失败");
		} catch (Exception e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询SalebackBillInfo失败");
		}
		return lisst;
	}
	
	
	
	/**
	 * 根据清单集合得到所有id主键
	 * @param billlist
	 * @return
	 */
	public String getallids(List<SalebackBillInfo> billlist){
		String salebackIdss = "";
		for (int i = 0; i < billlist.size(); i++) {
			if(i== billlist.size()-1){
				salebackIdss+=billlist.get(i).getSalebackmxId();
				
			}else{
				
				salebackIdss+=billlist.get(i).getSalebackmxId()+",";
			}
			
			
		}
		return salebackIdss;
	}
	
	
	/**
	 * 批量利息试算
	 * @param searchBean
	 * @return
	 * @throws Exception
	 */
	public void interestTrial(SaleBackSearchBean bean) throws Exception{
		//查询SaleApplyInfo和SaleBillInfo对象，用于拼装InterestReqDTO
		SalebackApplyInfoDao backapplydao = new SalebackApplyInfoDao();
		SalebackBillInfoDao backbill = new SalebackBillInfoDao();
		List<SalebackBillInfo> salebackBillList = null;
		SalebackApplyInfo salebackApply = null;
		//先关联批次和清单
		SalebackApplyInfo apply = applyDao.getSalebackApplyInfo(bean.getSalebackId());
		ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
		ICustInfoAcctService custInfoAcctService = ServiceFactory.getCustInfoAcctService();
		CustInfoAcct custInfoAcct = custInfoAcctService.getParam(bean.getInAcctNo().trim());
		CustInfo custInfo = custInfoService.getParam(custInfoAcct.getCustNo());
		apply.setIsOnline(bean.getIsOnline());
		apply.setRate(bean.getRate());
		apply.setInAcctNo(bean.getInAcctNo());
		apply.setInAcctName(custInfo.getCustName());
		modifySalebackApplyInfo(apply);
		String[] idss = bean.getSalebackmxId().split(",");
		for (int i = 0; i < idss.length; i++) {
			SalebackBillInfo billinfo = billDao.getSalebackBillInfo(idss[i]);
			billinfo.setSalebackId(apply.getSalebackId());
			billDao.modifySalebackBillInfo(billinfo);
		}	
		//利息试算
		if(bean.getSalebackmxId()!=null && bean.getSalebackmxId().length()>0){
			salebackBillList = backbill.getConfirmReceiveBillForId(bean.getSalebackmxId());
			salebackApply = backapplydao.getSalebackApplyInfo(bean.getSalebackId());
			for(SalebackBillInfo salebackBill:salebackBillList){
				
				salebackBill.setDelayType(bean.getDelayType());
				salebackBill.setDelayDays(bean.getDelayDays());
				salebackApply.setCreateDt(bean.getCreateDt());
				salebackApply.setDelayDays(bean.getDelayDays());
				interestTrial(salebackBill, salebackApply,bean.getIsSameCity());//调用单个利息试算方法
			}
		}
		
	}
	
	/**
	 * 单个利息试算
	 */
	public InterestResultDTO interestTrial(SalebackBillInfo saleBill,SalebackApplyInfo saleApply,String isSameCity) throws Exception{
		IInterestService interestService = ServiceFactory.getInterestService();
		SalebackBillInfoDao dao = new SalebackBillInfoDao();
		//组装利息试算方法所需的参数对象
		InterestReqDTO interestDTO = new InterestReqDTO();
		interestDTO.setBeginDate(DateTimeUtil.parseStringToDate(saleApply.getCreateDt()));//改过
		interestDTO.setEndDate(DateTimeUtil.parseStringToDate(saleApply.getSalebackDueDt()));//到期日
		interestDTO.setAmount(new BigDecimal(saleBill.getBillMoney()));//票面金额
		interestDTO.setRate(new BigDecimal(saleApply.getRate()));//利率
		interestDTO.setProductNo(saleApply.getProdNo());
		interestDTO.setBillClass(saleApply.getBillClass());
		interestDTO.setBillType(saleApply.getBillType());
		interestDTO.setDelayDays(saleBill.getDelayDays());//顺延天数
		interestDTO.setChargeKind(saleBill.getDelayType());
		interestDTO.setRateType(saleApply.getRateType());
		interestDTO.setIfSameCity(isSameCity);
		//调用利息试算方法
		InterestResultDTO interestResult = interestService.getInterest(interestDTO);
		//将利息试算的相关信息保存到SalebackBillInfo对象
		Double interest = MathScaleUtil.round(interestResult.getInterest().doubleValue(), 4);
		//saleBill.setGaleDate(DateTimeUtil.get_YYYY_MM_DD_Date(interestResult.getGaleDate()));//计息到期日
		saleBill.setInterest(interest);//总利息
		saleBill.setInterestDays(interestResult.getInterestCalDays());
		if(!DateTimeUtil.compartdate(saleApply.getCreateDt(), saleApply.getSalebackDueDt())&&interest<=0){
			saleBill.setInterest(0.0);//总利息
			saleBill.setInterestDays(0l);
			saleBill.setSalebackMoney(MathScaleUtil.subtract(saleBill.getBillMoney(), 0));	
		}else{
			saleBill.setSalebackMoney(MathScaleUtil.subtract(saleBill.getBillMoney(), interest));			
		}
	//	saleBill.setReceiveMoney(MathScaleUtil.subtract(saleBill.getBillMoney(), interest));//实收金额
	//	saleBill.setRate(saleApply.getRate()); //利率
	//	saleBill.setRateType(saleApply.getRateType());//利率类型
		saleBill.setEndorseDt(DateTimeUtil.getWorkdayString());//反手日期
		saleBill.setSalebackId(saleApply.getSalebackId());
		
		applyDao.modifySalebackApplyInfo(saleApply);
		dao.modifySalebackBillInfo(saleBill);
		return interestResult;
	}
	
	/**返售申请提交
	 * @throws SQLException 
	 * @throws Exception */
	public boolean salebackapplysubmit(String salebackmxIds) throws Exception {
		SalebackBillInfoDao dao = new SalebackBillInfoDao();
		IRcSaleBackService rcsalebackservice = RcServiceFactory.getRcSaleBackService();
		Map<String,RgctBill> rgctmap = new HashMap<String, RgctBill>();
		List<SalebackBillInfo> salebacllist;
		String rgctids = "";
		salebacllist = dao.getConfirmReceiveBillForId(salebackmxIds);
		if(null==salebacllist||salebacllist.size()<=0){
			return false;
		}
		for (int i = 0; i < salebacllist.size(); i++) {
			rgctids = rgctids + salebacllist.get(i).getRgctId()+",";
		}
		List<RgctBill> rgctlist = rcsalebackservice.getRgctBillList(rgctids.substring(0, rgctids.length()-1));
		for (int i = 0; i < rgctlist.size(); i++) {
			rgctmap.put(rgctlist.get(i).getHist().getRgctId(),rgctlist.get(i));
		}
		for (int i = 0; i < salebacllist.size(); i++) {
			RgctBill rgctbill = rgctmap.get(salebacllist.get(i).getRgctId());
			RgctBillHist hist = rgctbill.getHist();
			hist.setIsLock(IDict.K_LOCK.K_LOCK_YES);
			salebacllist.get(i).setApplyOperNo(ResourceUtil.getSessionLoginfo().getUserId());
			salebacllist.get(i).setOperStatus(StatusUtils.handleStatusNoCheck("SalebackApplyController", "applysubmitelec", null)); //保存之后状态暂时BSb602
			rcsalebackservice.updateRgctBillHist(hist);
			dao.modifySalebackBillInfo(salebacllist.get(i));
		}
	
		return true;
	}
	
	/**是否利息试算*/
	public boolean getSalebackBillInfolistbysalebackmxIds(String salebackmxids,String salebackId) throws SQLException{
			SalebackBillInfoDao dao = new SalebackBillInfoDao();
			List<SalebackBillInfo> list = new ArrayList<SalebackBillInfo>();
			list = dao.getConfirmReceiveBillForId(salebackmxids);
		
				
				for (int i = 0; i <list.size(); i++) {
					if(salebackId.equals(list.get(i).getSalebackId())){
						
						continue;						
						
					}else{
						return true;
					}
				}
			
			return false;
	}
	

	/**
	 * 功能描述：根据条件查询批次列表
	 * @param page
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<SalebackApplyInfo> getSaleBackApplyForCondition(Page page,
			SaleBackSearchBean query) throws Exception {
		SalebackApplyInfoDao saleBackApplyDao = new SalebackApplyInfoDao();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(query.getBillClass())){
			String[] opers = StatusUtils.queryStatus("SaleBackAuditController", "auditselect", null);
			query.setDfaultSrchTbAliasName("apply");
			query.setOpers(opers);
			query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);			
			query.setBranchNo(branchNo);
			query.addSpecialOpertion("opers",BaseSearchBean.IN);
			query.addSqlPropretyMapping("opers", "operStatus");
			query.addProperty2TableObj("salebackId", "apply");
			query.addProperty2TableObj("applyOperNo", "bill");
			
			query.setWorktime(DateTimeUtil.getWorkdayString());
			query.addSpecialOpertion("worktime",BaseSearchBean.LESS_AND_EQUAL);
			query.addSqlPropretyMapping("worktime", "salebackOpenDt");
			
			query.addProperty2TableObj("opers", "bill");
			query.addProperty2TableObj("branchNo", "bill");
		}else{
			String[] opers = StatusUtils.queryStatus("salebackAccountController", "salebackaccountyeselec", null);
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);			
			query.setBranchNo(branchNo);
			query.setOpers(opers);
			query.setDfaultSrchTbAliasName("bill");
			query.addSpecialOpertion("opers",BaseSearchBean.IN);
			query.addSqlPropretyMapping("opers", "operStatus");
			query.addSqlPropretyMapping("operStatus", "operStatus");
			query.addProperty2TableObj("salebackId", "apply");
		}
		
		OrderBean order=new OrderBean("salebackId",false);
	    query.appendOrder(order);
		return saleBackApplyDao.getSaleBackApplyForCondition(page, query);
	}
	
	public List<SalebackApplyInfo> getSaleBackaccountbatch(Page page,
			SaleBackSearchBean query) throws SQLException{
		SalebackApplyInfoDao saleBackApplyDao = new SalebackApplyInfoDao();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);			
		query.setBranchNo(branchNo);
		query.setDfaultSrchTbAliasName("bill");
		query.addSpecialOpertion("opers",BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		query.addSqlPropretyMapping("operStatus", "operStatus");
		query.addProperty2TableObj("salebackId", "apply");
		OrderBean order=new OrderBean("salebackId",false);
	    query.appendOrder(order);
		return saleBackApplyDao.getSaleBackApplyForCondition(page, query);
	}
	

	/**
	 * 功能描述：根据条件查询批次列表(审核)多状态
	 * @param page
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<SalebackApplyInfo> getSaleBackAuditForCondition(Page page,
			SaleBackSearchBean query) throws Exception {
		SalebackApplyInfoDao saleBackApplyDao = new SalebackApplyInfoDao();
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		query.setDfaultSrchTbAliasName("apply");
		String[] opers = StatusUtils.queryStatus("SaleBackAuditController", "auditselect", null);
		query.setOpers(opers);
		query.setBranchNo(branchNo);
		query.addSpecialOpertion("opers",BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		
		query.addProperty2TableObj("opers", "bill");
		query.addProperty2TableObj("branchNo", "bill");
		query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);			

		
		query.setWorktime(DateTimeUtil.getWorkdayString());
		query.addSpecialOpertion("worktime",BaseSearchBean.LESS_AND_EQUAL);
		query.addSqlPropretyMapping("worktime", "salebackOpenDt");
		
		
		OrderBean order=new OrderBean("salebackId",false);
	    query.appendOrder(order);
		return saleBackApplyDao.getSaleBackApplyForCondition(page, query);
	}
	
	/**
	 * 获得审核清单
	 * @param query
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<SalebackBillInfo> getauditbilllist(SaleBackSearchBean query,Page page) throws Exception{
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
			query.setDfaultSrchTbAliasName("bill");
			
			query.addSpecialOpertion("opers",BaseSearchBean.IN);
			query.addSqlPropretyMapping("opers", "operStatus");
			query.addSqlPropretyMapping("operStatus", "operStatus");
			query.setBranchNo(user.getBranchNo());
			query.addSqlPropretyMapping("branchNo", "branchNo");
			List<SalebackBillInfo> listbills =billDao.getsalebackBillListBySearchBean(query,page);
			return listbills;

	}
	
	
	 /**
     * 返售审核
     */
    public void submitSaleAudit(String[] ids,String auditStatus,String option) throws Exception{
    	UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
    	String salebackId=null;
    	for(int i=0;i<ids.length;i++){
    		SalebackBillInfo Bill = billDao.getSalebackBillInfo(ids[i]);
    		String after = StatusUtils.handleStatus("SaleBackAuditController", "salebackaudit", auditStatus, Bill.getOperStatus());
    		salebackId = Bill.getSalebackId();
    		Bill.setOperStatus(after);
    		Bill.setAuditReason(option);
    		Bill.setAuditOperNo(userLogonInfo.getUserNo());
    		billDao.modifySalebackBillInfo(Bill);
    	}
    	if(SaleCodeConst.AUDIT_NO.equals(auditStatus) && !StringUtils.isBlank(salebackId)){//如果未通过审核，除了修改票据当前状态，还要修改批次状态
    		applyDao.updateSaleApplyStatus(CommonConst.APPLY_STATUS_NEW, salebackId);
    	}
    }
    
    
	
	/**
	 * 撤销审核差批次
	 * @param query
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SalebackApplyInfo> getcancelauditbatchlist(SaleBackSearchBean query,Page page) throws Exception{
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);			
		query.setBranchNo(branchNo);
		String[] opers = StatusUtils.queryStatus("SaleBackAuditController", "cancelaudit", null);
		query.setOpers(opers);
		query.setDfaultSrchTbAliasName("bill");
		query.addSpecialOpertion("opers",BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		query.addSqlPropretyMapping("operStatus", "operStatus");
		query.addProperty2TableObj("salebackId", "apply");
		
		
		query.setWorktime(DateTimeUtil.getWorkdayString());
		query.addSpecialOpertion("worktime",BaseSearchBean.LESS_AND_EQUAL);
		query.addSqlPropretyMapping("worktime", "salebackOpenDt");
		
		query.addProperty2TableObj("worktime", "apply");
		
		OrderBean order=new OrderBean("salebackId",false);
	    query.appendOrder(order);
		return applyDao.getSaleBackApplyForCondition(page, query);
	}
	
	/**
	 * 撤销审核查清单
	 * @param query
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SalebackBillInfo> getcancelauditbilllist(SaleBackSearchBean query,Page page) throws Exception{
		String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
		query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);			
		query.setBranchNo(branchNo);
		String[] opers = StatusUtils.queryStatus("SaleBackAuditController", "cancelaudit", null);
		query.setOpers(opers);
		query.setDfaultSrchTbAliasName("bill");
		query.addSpecialOpertion("opers",BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		query.addSqlPropretyMapping("operStatus", "operStatus");
		query.addProperty2TableObj("salebackId", "apply");

		OrderBean order=new OrderBean("salebackId",false);
	    query.appendOrder(order);
		return billDao.getsalebackBillListBySearchBean(query,page);
	}
	
	/**
	 * 撤销审核
	 * @param query
	 * @param page
	 * @throws Exception 
	 */
	public void cancelaudit(SaleBackSearchBean query,Page page,String salebackmxId) throws Exception{

		String opers = StatusUtils.handleStatusNoCheck("SaleBackAuditController", "cancelaudit", null);
		
		List<SalebackBillInfo> billlist = billDao.getConfirmReceiveBillForId(salebackmxId);
		for (int i = 0; i < billlist.size(); i++) {
			SalebackBillInfo bill = billlist.get(i);
			bill.setOperStatus(opers);
			billDao.modifySalebackBillInfo(bill);
		}
			
	}

	/**
	 * 功能描述：得到批次
	 * @param salebackId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public SalebackApplyInfo getSalebackApplyInfo(String salebackId,
			SaleBackSearchBean query) throws SQLException {
		SalebackApplyInfoDao saleBackApplyDao = new SalebackApplyInfoDao();
		return saleBackApplyDao.getSalebackApplyInfo(salebackId, query);
	}


	/**
	 * 功能描述：根据批次id查询清单
	 * @param salebackId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<SalebackBillInfo> getSaleBackBillListForBatch(Page page,
			SaleBackSearchBean query) throws SQLException {
		SalebackBillInfoDao saleBackBillDao = new SalebackBillInfoDao();
		
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		query.setDfaultSrchTbAliasName("bill");
		query.addSpecialOpertion("opers",BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		OrderBean order=new OrderBean("salebackmxId",false);
	    query.appendOrder(order);
		return saleBackBillDao.getRebuyBillListBySearchBean(query,page);
	}
	
	/**
	 * 功能描述：根据批次id查询清单
	 * @param salebackId
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<SalebackBillInfo> getSaleBackBillListForBatchtwo(Page page,
			String salebackId,String rateids,String rebuyId) throws Exception  {
		
		SalebackBillInfoDao billsdao = new SalebackBillInfoDao();
		SaleBackSearchBean query = new SaleBackSearchBean();
		query.setSalebackId(null);
		query.setRebuyId(rebuyId);
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		SalebackBillInfoDao saleBackBillDao = new SalebackBillInfoDao();
		query.setBranchNo(ResourceUtil.getSessionLoginfo().getBranchNo());
		query.setDfaultSrchTbAliasName("bill");
		query.setBillClass("2");
		query.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applysubmitelec", null)[0]);
		query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
		query.addSqlPropretyMapping("operStatus", "operStatus");
		query.addSqlPropretyMapping("branchNo", "branchNo");
		query.addSqlPropretyMapping("rebuyId", "rebuyId");
		OrderBean order=new OrderBean("salebackmxId",false);
	    query.appendOrder(order);
	   List<SalebackBillInfo> lisst= saleBackBillDao.getSalebackBillInfolistentity(page,query);
		for (int i = 0; i < lisst.size(); i++) {	
			
			if(rateids.indexOf(lisst.get(i).getSalebackmxId())!=-1){
				continue;
			}
			SalebackBillInfo bill = lisst.get(i);
			bill.setInterest(0.0);
			bill.setInterestDays(0l);		
			bill.setEndorseDt(null);
			bill.setSalebackMoney(0.0);
			bill.setSalebackId(null);
			billsdao.modifySalebackBillInfo(bill);
		}
		return lisst;
	
	}
	/**
	 * 功能描述：得到返售确认電票根据清单id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<SalebackBillInfo> getConfirmReceiveBillForId(String ids)
			throws SQLException {
		SalebackBillInfoDao saleBackBillDao = new SalebackBillInfoDao();
		return saleBackBillDao.getConfirmReceiveBillForId(ids);
	}


	/**
     * 返售确认提交
     * @param saleId
     * @param ids
	 * @throws Exception 
     */
	@Override
	public boolean confirmSubmit(String salebackId, String ids)
			throws Exception {
		boolean bool = true;
		IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
		SalebackBillInfoDao saleBackBillDao = new SalebackBillInfoDao();
		try {
			List<SalebackBillInfo> list = saleBackBillDao.getConfirmReceiveBillForId(ids);
			for(SalebackBillInfo saleBackBill:list){
				if(StatusUtils.handleStatusNoCheck("SalebackApplyController", "endorsedelec", null).equals(saleBackBill.getOperStatus())){
					bool=false;
        			throw new BizAppException("对方还没有对信息进行处理，不能进行签收，请等待对方回复！");
				}else if(StatusUtils.handleStatusNoCheck("SalebackApplyController", "endorsenotelec", null).equals(saleBackBill.getOperStatus())){
					rcSaleService.unLock(saleBackBill.getRgctId());
					saleBackBill.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applysubmitelec", null)[0]);
					saleBackBillDao.modifySalebackBillInfo(saleBackBill);
					bool=true;
				}else{
					saleBackBill.setOperStatus(StatusUtils.handleStatusNoCheck("salebackAccountController", "salebackaccountyeselec", null));
					saleBackBillDao.modifySalebackBillInfo(saleBackBill);
					bool=true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException("确认签收失败，"+e.getMessage());
		}
		return bool;
	}


	/**
     * 确认记账
     * @param ids
	 * @throws Exception 
     */
	@Override
	public void accouontSubmit(String ids) throws Exception {
		IRcSaleBackService rcsalebackservice = RcServiceFactory.getRcSaleBackService();
		Map<String,RgctBill> rgctbillmap = new HashMap<String, RgctBill>();
		SalebackBillInfoDao saleBackBillDao = new SalebackBillInfoDao();
		UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
		String rebuyid = "";
		String rgctids = "";
		List<SalebackBillInfo> list = saleBackBillDao.getConfirmReceiveBillForId(ids);
		Map<String,SalebackBillInfo> salebackbillmap = new HashMap<String, SalebackBillInfo>();
		if (list.isEmpty()) {
			throw new BizAppException("该票不允许该操作");
		}
		SalebackApplyInfo salebackapply = applyDao.getSalebackApplyInfo(list.get(0).getSalebackId());
		for(SalebackBillInfo saleBackBill:list){
			rgctids = rgctids+saleBackBill.getRgctId()+",";
		}
		List<RgctBill> rgctList = rcsalebackservice.getRgctBillList(rgctids.substring(0,rgctids.length()-1));
		for (int i = 0; i < rgctList.size(); i++) {
			rgctbillmap.put(rgctList.get(i).getHist().getRgctId(),rgctList.get(i));
		}
		
		//调用记账方法
        IAccountFacadeService acctService = ServiceFactory.getSalebackAccountService();
        AccountRequestDTO<SalebackApplyInfo,SalebackBillInfo> accReq = new AccountRequestDTO<SalebackApplyInfo,SalebackBillInfo>();
        accReq.setApply(salebackapply);
        accReq.setBillList(list);
        accReq.setUserLogonInfo(userInfo);
        AccountResponseDTO accResp = acctService.account(accReq);
		
        //改相关表状态
        for (int i = 0; i < list.size(); i++) {
        	SalebackBillInfo salebackBill =list.get(i);
        	salebackBill.setOperStatus(StatusUtils.handleStatusNoCheck("salebackAccountController", "salebackaccountelec", null));
        	salebackBill.setCurStatus(SalebackCodeConst.CUR_STATUS_REBUY_REDEEM_OVER);    // 10 买入返售结清
        	salebackBill.setAcctOperNo(userInfo.getUserNo());
        	salebackBill.setIsAudited("5");
        	salebackBill.setExSerial(accResp.getBackFlowNo());
        	salebackBill.setAccountDt(DateTimeUtil.getWorkdayString());
        	rebuyid = rebuyid +salebackBill.getRebuymxId()+",";
        	salebackbillmap.put(salebackBill.getRebuymxId(), salebackBill);
        	billDao.modifySalebackBillInfo(salebackBill);
		}
        String rebuyIds = rebuyid.substring(0,list.size()-1);
        
        List<RebuyBillInfo> rebuyBillInfo = rebuydao.getEntitytricReceiveForId(rebuyIds);
        for (int i = 0; i < rebuyBillInfo.size(); i++) {
        	RebuyBillInfo rebuybill = rebuyBillInfo.get(i);
        	SalebackBillInfo salebackbill = salebackbillmap.get(rebuybill.getRebuymxId());
        	rebuybill.setIsBuyback("1");
        	rebuybill.setGathDate(salebackbill.getAccountDt());
        	rebuybill.setGathType(RebuyCodeConst.GATH_TYPE_BUYBACK);// 4 贴现赎回/同业返售/系统内返售(系统内回购记账)
        	rebuybill.setTotalIntrstPayment(salebackbill.getInterest());//总计利息支出
        	rebuybill.setCurStatus(RebuyCodeConst.CUR_STATUS_REBUY_REDEEM_OVER);// 10 买入返售结清
        	rebuybill.setTransType(CommonConst.REDEEM);// 返售已赎回
        	rebuybill.setTransId(salebackbill.getSalebackmxId());	
        	rebuydao.modifyRebuyBillInfo(rebuybill);
		}
		
	}
	
	/**-------------------------撤销申请start--------------------------------*/
	
	/**
	 * 撤销申请批次查询
	 * @return
	 * @throws Exception 
	 */
	public List<SalebackApplyInfo> getSalebackApplyInfolist() throws Exception{
		SalebackApplyInfoDao dao = new SalebackApplyInfoDao();
		return dao.getSalebackApplyInfolist();
	}

/**根据searchbean撤销申请批次查询
 * @throws Exception 
 * @throws Exception */
	@Override
	public List<SalebackApplyInfo> getSalebackApplyInfolistbysearchbean(
			SaleBackSearchBean query, Page page) throws Exception{
		query.setDfaultSrchTbAliasName("bill");
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(query.getBillClass())){
			String[] opers = StatusUtils.queryStatus("SaleBackAuditController", "auditselect", null);
			query.setOpers(opers);
			query.addSpecialOpertion("opers",BaseSearchBean.IN);
			query.addSqlPropretyMapping("opers", "operStatus");

		}else{
			
			query.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applycancelelec", null)[0]);//前置状态
			query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
			query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
			query.addSqlPropretyMapping("operStatus", "operStatus");
		}
			

		query.addSqlPropretyMapping("branchNo", "branchNo");
		
		SalebackApplyInfoDao dao = new SalebackApplyInfoDao();
		List<SalebackApplyInfo> list = new ArrayList<SalebackApplyInfo>();
		try {
			 list = dao.getRebuyApplyListBySearchBean(page, query);
			
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询失败");
		}
		return list;
	}
	
	/**根据searchbean撤销申请清单查询
	 * @throws Exception */
	public List<SalebackBillInfo> getSalebackBillInfolistbysearchbean(SaleBackSearchBean query, Page page) throws Exception{
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(query.getBillClass())){
			String[] opers = StatusUtils.queryStatus("SaleBackAuditController", "auditselect", null);
			query.setDfaultSrchTbAliasName("bill");
			query.setOpers(opers);
			query.addSpecialOpertion("opers",BaseSearchBean.IN);
			query.addSqlPropretyMapping("opers", "operStatus");
			query.setBranchNo(user.getBranchNo());
			query.addSqlPropretyMapping("branchNo", "branchNo");
			List<SalebackBillInfo> listbills =billDao.getsalebackBillListBySearchBean(query,page);
			return listbills;
		}
		query.setDfaultSrchTbAliasName("bill");
		query.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applycancelelec", null)[0]);//前置状态
		query.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
		query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
		query.addSqlPropretyMapping("operStatus", "operStatus");
		query.setBranchNo(user.getBranchNo());
		query.addSqlPropretyMapping("branchNo", "branchNo");
		SalebackBillInfoDao dao = new SalebackBillInfoDao();
		List<SalebackBillInfo> listbill = dao.getRebuyBillListBySearchBean(query, page);
		
		return listbill;
	}
	
	/**根据批次主键获得批次*/
	public SalebackApplyInfo getSalebackApplyInfo(Page page,String salebackId) throws SQLException{
		SalebackApplyInfo apply = new SalebackApplyInfo();
		SalebackApplyInfoDao dao = new SalebackApplyInfoDao();
		SaleBackSearchBean query = new SaleBackSearchBean();
		query.setSalebackId(salebackId);
		query.setDfaultSrchTbAliasName("apply");
		
		try {
			apply = dao.getSaleBackApplyForConditions(page,query).get(0);
			apply.setCreateDt(DateTimeUtil.getWorkdayString());
		} catch (BizAppException e) {
			
			e.printStackTrace();
		}
		return apply;
	}
	
	/**根据清单ids(清单主键字符串)和状态更改清单状态   
	 * @throws SQLException 
	 * @throws BizAppException */
	public void editsalebackbillstatus(String ids,String status,String salebackId) throws SQLException, BizAppException{
		SalebackBillInfoDao dao = new SalebackBillInfoDao();
		List<SalebackBillInfo> billlist = new ArrayList<SalebackBillInfo>();
		SalebackApplyInfo apply = applyDao.getSalebackApplyInfo(salebackId);
		apply.setCreateDt(null);
		String rgctids = "";
		applyDao.modifySalebackApplyInfo(apply);
		billlist = dao.getConfirmReceiveBillForId(ids);
		for (int i = 0; i < billlist.size(); i++) {
			rgctids = rgctids + billlist.get(i).getRgctId()+",";
			billlist.get(i).setOperStatus(status);	
			billlist.get(i).setInterest(0.0);
			billlist.get(i).setInterestDays(0l);
			billlist.get(i).setEndorseDt(null);
			billlist.get(i).setSalebackMoney(0.0);
			dao.modifySalebackBillInfo(billlist.get(i));
		}
		List<RgctBill> rgctlist = RcServiceFactory.getRcSaleBackService().getRgctBillList(rgctids.substring(0,rgctids.length()-1));//
		for (int i = 0; i < rgctlist.size(); i++) {
			RgctBillHist hist = rgctlist.get(i).getHist();
			hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			RcServiceFactory.getRcSaleBackService().updateRgctBillHist(hist);
		}
	}
	
	/**-------------------------撤销申请end--------------------------------*/
	
	
	/**---------------------------背书申请start------------------------------*/
	/**根据searchbean背书批次查询
	 * @throws Exception */
	@Override
	public List<SalebackApplyInfo> getSalebackendorseApplyInfolistbysearchbean(
			SaleBackSearchBean query, Page page) throws Exception {
		query.setDfaultSrchTbAliasName("bill");
		query.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "applycancelelec", null)[0]);//前置状态
		query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
		query.addSqlPropretyMapping("operStatus", "operStatus");
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		query.addSqlPropretyMapping("branchNo", "branchNo");
		
		query.setBillClass("2");
		SalebackApplyInfoDao dao = new SalebackApplyInfoDao();
		List<SalebackApplyInfo> list = new ArrayList<SalebackApplyInfo>();
		try {
			 list = dao.getRebuyApplyListBySearchBean(page, query);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询失败");
		}
		return list;
	}
	
	/**
     * 电票返售背书 1,对选中的票据进行背书申请报文
	 * 2,工作流往下走,到等待节点等待033的确认报文,然后进入回调操作
	 * @throws Exception 
     */
    public void submitSalebackEndorse(String salebackId,String ids,UserLoginfo userInfo) throws Exception{
    	IRcSaleBackService rcSalebackService = RcServiceFactory.getRcSaleBackService();
    	IRebuyService rebuyapplyservice = ServiceFactory.getRebuyService();
    	Map<String,RgctBill> rgctmap = new HashMap<String, RgctBill>();
    	IDB session = DBFactory.getDB();
    	double auditAmt=0.0;
    	String rgctids = "";
    	
		List<SalebackBillInfo> salebackBillList = billDao.getConfirmReceiveBillForId(ids);
		Branch brch = ServiceFactory.getBranchService().getBranch(userInfo.getBrchNo());
		SalebackApplyInfo apply = applyDao.getSalebackApplyInfo(salebackId);
		RebuyApplyInfo rebuyapply = rebuyapplyservice.getApplyInfoById(salebackBillList.get(0).getRebuyId());
		if(salebackBillList.isEmpty()||apply==null||rebuyapply==null){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持此操作");
		}
		for (int i = 0; i < salebackBillList.size(); i++) {
			rgctids = rgctids +salebackBillList.get(i).getRgctId()+",";
		}
		List<RgctBill> rgctlist = RcServiceFactory.getRcSaleBackService().getRgctBillList(rgctids.substring(0,rgctids.length()-1));
		for (int i = 0; i < rgctlist.size(); i++) {
			rgctmap.put(rgctlist.get(i).getHist().getRgctId(), rgctlist.get(i));
		}
		for(SalebackBillInfo salebackBill : salebackBillList){
    		try {
    			session.beginTransaction();
				RgctBill rgctBill = rgctmap.get(salebackBill.getRgctId());
				RgctBillHist rgctHist = rgctBill.getHist();
				rgctHist.setIsLock("0");
				rgctHist.setFromBranchNo(userInfo.getBrchNo());
				rgctHist.setFromBankNo(userInfo.getBrchBankNo());
				rgctHist.setFromName(userInfo.getBranchName());
				rgctHist.setDealMoney(salebackBill.getSalebackMoney());// 电子票返售金额
				rgctHist.setToBankNo(rebuyapply.getCustBankNo());
				rgctHist.setToName(apply.getCustName());
				rgctHist.setIsOnline(apply.getIsOnline());// 是否线上清算
				rgctHist.setBackOpenDt(apply.getSalebackOpenDt());// 返售开放日
				rgctHist.setBackEndDt(apply.getSalebackDueDt());
				rgctHist.setEndorseDt(apply.getCreateDt());// 生成返售申请的日期setEndorseDt
				rgctHist.setInterestDays(salebackBill.getInterestDays());
				rgctHist.setBackRate(MathScaleUtil.multiply(apply.getSalebackRate(), 0.01));
				rgctHist.setBackAmount(salebackBill.getSalebackMoney());// 实物返售金额
				rgctHist.setRegressDt(apply.getCreateTime());// 回购日-生成返售申请的日期
				rgctBill.getHist().setSignerSign(CommUtils.getSignerSign(rgctBill.getHist().getFromBankNo()));                                //MsgUtil.isSelfBank(rgctHist.getToBankNo()));
				rgctBill.getHist().setFromOrgcode(brch.getOrgCode());
				rgctBill.getHist().setFromRole(brch.getPartnerType());
				rgctBill.setHist(rgctHist);
				
				/** 保存清单状态* */
				//同业间纸票，返售要变更实物保管机构
				if(SalebackCodeConst.IS_INNER_FALSE.equals(salebackBill.getIsInner()) && IDict.K_BILL_CLASS.K_ENTY_BILL.equals(salebackBill.getBillClass())){
					rgctBill.getHist().setStorageBranchNo(null);
					rgctBill.getHist().setStorageBranchName(null);
				}
				if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(salebackBill.getBillClass())){
					String opers = StatusUtils.handleStatusNoCheck("SalebackApplyController", "applysubmitentity", null);
					salebackBill.setOperStatus(opers);
				}else{
					salebackBill.setOperStatus(StatusUtils.handleStatusNoCheck("SalebackApplyController", "endorseelec", null));// 背书提交 
				}
				
				salebackBill.setEndorseDt(DateTimeUtil.getWorkdayString());
				salebackBill.setAuditOperNo(userInfo.getUserNo());
				salebackBill.setEndorseTime(DateTimeUtil.getTime());
				billDao.modifySalebackBillInfo(salebackBill);
				rcSalebackService.regBackEndorse(rgctBill);
				
				auditAmt += salebackBill.getBillMoney();
				session.endTransaction();
			} catch (Exception e) {
				try {
					session.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "返售背书失败");
			}
    	}
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(apply.getBillClass())){
			//创建审批任务
		
			AuditTask auditTask = new AuditTask();
			auditTask.setWaitAuditAmt(auditAmt); 
			auditTask.setAtAuthorName(userInfo.getUserName()); 
			auditTask.setAtAuthorId(userInfo.getUserId()); 
			auditTask.setBrchNo(userInfo.getBrchNo()); 
			auditTask.setAtCreateDt(DateTimeUtil.getWorkdayString()); 
			auditTask.setAtCreateTime(DateTimeUtil.getTime());  
			auditTask.setProdNo(apply.getProdNo());
			auditTask.setBatchNo(apply.getBatchNo()); 
			auditTask.setBatchId(salebackId);
			auditTask.setEntityName("SaleBackAuditController.do?method=auditbilllist");
			auditTask.setEntityService("salebackId");
			ServiceFactory.getAuditTasksService().addAuditTask(auditTask);

		}
    		
		
    }
    /** 
     *纸票申请提交 
     */
    public void submitSalebackapplyentity(String salebackId,String ids,UserLoginfo userInfo) throws Exception{
    	IRcSaleBackService rcSalebackService = RcServiceFactory.getRcSaleBackService();
    	IRebuyService rebuyapplyservice = ServiceFactory.getRebuyService();
    	Map<String,RgctBill> rgctmap = new HashMap<String, RgctBill>();
    	double auditAmt=0.0;
    	String rgctids = "";
    	
		List<SalebackBillInfo> salebackBillList = billDao.getConfirmReceiveBillForId(ids);
		Branch brch = ServiceFactory.getBranchService().getBranch(userInfo.getBrchNo());
		SalebackApplyInfo apply = applyDao.getSalebackApplyInfo(salebackId);
		RebuyApplyInfo rebuyapply = rebuyapplyservice.getApplyInfoById(salebackBillList.get(0).getRebuyId());
		if(salebackBillList.isEmpty()||apply==null||rebuyapply==null){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持此操作");
		}
		for (int i = 0; i < salebackBillList.size(); i++) {
			rgctids =rgctids + salebackBillList.get(i).getRgctId()+",";
		}
		List<RgctBill> rgctlist = RcServiceFactory.getRcSaleBackService().getRgctBillList(rgctids.substring(0,rgctids.length()-1));
		for (int i = 0; i < rgctlist.size(); i++) {
			rgctmap.put(rgctlist.get(i).getHist().getRgctId(), rgctlist.get(i));
		}
		try {
			for(SalebackBillInfo salebackBill : salebackBillList){
				RgctBill rgctBill = rgctmap.get(salebackBill.getRgctId());
				RgctBillHist rgctHist = rgctBill.getHist();
				rgctHist.setIsLock("0");
				rgctHist.setFromBranchNo(userInfo.getBrchNo());
				rgctHist.setFromBankNo(userInfo.getBrchBankNo());
				rgctHist.setFromName(userInfo.getBranchName());
				rgctHist.setDealMoney(salebackBill.getSalebackMoney());// 电子票返售金额
				rgctHist.setToBankNo(rebuyapply.getCustBankNo());
				rgctHist.setToName(apply.getCustName());
				rgctHist.setIsOnline(apply.getIsOnline());// 是否线上清算
				rgctHist.setBackOpenDt(apply.getSalebackOpenDt());// 返售开放日
				rgctHist.setBackEndDt(apply.getSalebackDueDt());
				rgctHist.setEndorseDt(apply.getCreateDt());// 生成返售申请的日期setEndorseDt
				rgctHist.setInterestDays(salebackBill.getInterestDays());
				rgctHist.setBackRate(MathScaleUtil.multiply(apply.getSalebackRate(), 0.01));
				rgctHist.setBackAmount(salebackBill.getSalebackMoney());// 实物返售金额
				rgctHist.setRegressDt(apply.getCreateDt());// 回购日-生成返售申请的日期
				rgctBill.getHist().setSignerSign(CommUtils.getSignerSign(rgctBill.getHist().getFromBankNo()));                                //MsgUtil.isSelfBank(rgctHist.getToBankNo()));
				rgctBill.getHist().setFromOrgcode(brch.getOrgCode());
				rgctBill.getHist().setFromRole(brch.getPartnerType());
				rgctBill.setHist(rgctHist);
				
				/** 保存清单状态* */
				//同业间纸票，返售要变更实物保管机构
				if(SalebackCodeConst.IS_INNER_FALSE.equals(salebackBill.getIsInner()) && IDict.K_BILL_CLASS.K_ENTY_BILL.equals(salebackBill.getBillClass())){
					rgctBill.getHist().setStorageBranchNo(null);
					rgctBill.getHist().setStorageBranchName(null);
				}
				if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(salebackBill.getBillClass())){
					String opers = StatusUtils.handleStatusNoCheck("SalebackApplyController", "applysubmitentity", null);
					salebackBill.setOperStatus(opers);
				}else{
					salebackBill.setOperStatus(StatusUtils.handleStatusNoCheck("SalebackApplyController", "endorseelec", null));// 背书提交 
				}
				
				salebackBill.setEndorseDt(DateTimeUtil.getWorkdayString());
				salebackBill.setApplyOperNo(userInfo.getUserId());
				salebackBill.setEndorseTime(DateTimeUtil.getTime());
				billDao.modifySalebackBillInfo(salebackBill);
				rcSalebackService.regBackEndorse(rgctBill);
				
				auditAmt += salebackBill.getBillMoney();
				
	    	}
			if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(apply.getBillClass())){
				//创建审批任务
				AuditTask auditTask = new AuditTask();
				auditTask.setWaitAuditAmt(auditAmt); 
				auditTask.setAtAuthorName(userInfo.getUserName()); 
				auditTask.setAtAuthorId(userInfo.getUserId()); 
				auditTask.setBrchNo(userInfo.getBrchNo()); 
				auditTask.setAtCreateDt(DateTimeUtil.getWorkdayString()); 
				auditTask.setAtCreateTime(DateTimeUtil.getTime());  
				auditTask.setProdNo(apply.getProdNo());
				auditTask.setBatchNo(apply.getBatchNo()); 
				auditTask.setBatchId(salebackId);
				auditTask.setEntityName("SaleBackAuditController.do?method=auditbilllist");
				auditTask.setEntityService("salebackId");
				ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "返售背书失败");
		}
		
    		
		
    } 
    
	/**---------------------------背书申请end------------------------------*/
	
	
	/**根据searchbean撤销背书批次查询
	 * @throws Exception */
	@Override
	public List<SalebackApplyInfo> getSalebackendorsecxInfolistbysearchbean(
			SaleBackSearchBean query, Page page) throws Exception {
		query.setDfaultSrchTbAliasName("bill");
		query.setOperStatus(StatusUtils.handleStatusNoCheck("SalebackApplyController", "endorsedelec", null));//前置状态
		query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
		query.addSqlPropretyMapping("operStatus", "operStatus");
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		query.addSqlPropretyMapping("branchNo", "branchNo");
		
		query.setBillClass("2");
		SalebackApplyInfoDao dao = new SalebackApplyInfoDao();
		List<SalebackApplyInfo> list = new ArrayList<SalebackApplyInfo>();
		try {
			 list = dao.getRebuyApplyListBySearchBean(page, query);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询失败");
		}
		return list;
	}
	
	/**根据searchbean撤销申请背书清单查询
	 * @throws Exception */
	public List<SalebackBillInfo> getSalebackcxBillInfolistbysearchbean(SaleBackSearchBean query, Page page) throws Exception{
		query.setDfaultSrchTbAliasName("bill");
		query.setOperStatus(StatusUtils.queryStatus("SalebackApplyController", "cancelendorseelec", null)[0]);//前置状态
		query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
		query.addSqlPropretyMapping("operStatus", "operStatus");
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		query.setBranchNo(user.getBranchNo());
		query.addSqlPropretyMapping("branchNo", "branchNo");
		query.setBillClass("2");
		SalebackBillInfoDao dao = new SalebackBillInfoDao();
		List<SalebackBillInfo> listbill = dao.getRebuyBillListBySearchBean(query, page);
		return listbill;
	}


	@Override
	public SalebackBillInfo getSalebackBillInfoForRgctId(String rgctId)
			throws BizAppException {
		List<SalebackBillInfo> billInfo = new ArrayList<SalebackBillInfo>();
		try {
			billInfo = billDao.getSalebackBillInfolistForRgctId(rgctId);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "获取票据信息失败");
		}
		return billInfo.get(0);
	}


	@Override
	public void modifySalebackBillInfo(SalebackBillInfo billInfo)
			throws BizAppException {
		try {
			billDao.modifySalebackBillInfo(billInfo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "更新实体信息失败");
		}
		
	}
		@Override
	public void cancelElecSaleBackEndorse(String ids) throws BizAppException {
		Map<String,RgctBill> rgctmap = new HashMap<String, RgctBill>();
		IDB session = DBFactory.getDB();
    	try {
    		List<SalebackBillInfo> billList = billDao.getConfirmReceiveBillForId(ids);
    		String rgctids = "";
    		String operstatuszj = StatusUtils.handleStatusNoCheck("SalebackApplyController", "cancelendorsezjelec", null);
    		String operstatus = StatusUtils.queryStatus("SalebackApplyController", "cancelendorseelec", null)[0];
    		for (int i = 0; i <billList.size(); i++) {
    			rgctids = rgctids + billList.get(i).getRgctId()+",";
			}
    		List<RgctBill> rgctlist = RcServiceFactory.getRcSaleBackService().getRgctBillList(rgctids.substring(0,rgctids.length()-1));
    		for (int i = 0; i < rgctlist.size(); i++) {
    			rgctmap.put(rgctlist.get(i).getHist().getRgctId(), rgctlist.get(i));
			}
    		for(SalebackBillInfo saleBackBill : billList){
    			session.beginTransaction();
    			if (!operstatus.equals(saleBackBill.getOperStatus())) {
					throw new BizAppException("该票的当前状态不支持该处理！票号：" + saleBackBill.getBillNo());
				}
    			saleBackBill.setOperStatus(operstatuszj);//撤销回调回来之前的中间状态
    			// 调登记中心方法
    			RgctBill rgctBill = rgctmap.get(saleBackBill.getRgctId());
    			rgctBill.getHist().setEndorseDt(DateTimeUtil.getWorkdayString());
				rgctBill.getHist().setChannel(RcConstants.COMES_FROM_SYS);
				rgctBill.getHist().setFromSign(CommUtils.getSignerSign(rgctBill.getHist().getFromBankNo()));// 电子签名
    			IRcSaleBackService rcSaleBackService = RcServiceFactory.getRcSaleBackService();
    			billDao.modifySalebackBillInfo(saleBackBill);
    			rcSaleBackService.cancelBackEndorse(rgctBill);// 登记中心撤销背书
    			session.endTransaction();
    		}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new BizAppException("撤销电票到期反售背书失败，"+e.getMessage());
		}
    
	}
		@Override
		public void modifySalebackApplyInfo(SalebackApplyInfo obj) throws SQLException{
			applyDao.modifySalebackApplyInfo(obj);
		}


		/**根据批次主键获得批次*/
		public SalebackApplyInfo getSalebackApplyInfo(String salebackId) throws SQLException{
			SalebackApplyInfo apply = new SalebackApplyInfo();
			SalebackApplyInfoDao dao = new SalebackApplyInfoDao();
			apply = dao.getSalebackApplyInfo(salebackId);
			
			return apply;
		}


		@Override
		public SalebackApplyInfo copyrebuytosalebackapply(
				SalebackApplyInfo batch, RebuyApplyInfo apply) throws BizAppException {
		//	batch.setCreateDt(DateTimeUtil.getWorkdayString());
			batch.setTotalNum(apply.getTotalNum());
			batch.setTotalMoney(apply.getTotalMoney());
			batch.setActualAmount(apply.getActualAmount());
			batch.setTotalInterest(apply.getTotalInterest());
			return batch;
		}

		/**
		 * 把转入下的所有清单关联到新返售批次
		 * @param salebackId
		 * @param salebackIdss
		 * @throws BizAppException 
		 */
		public void salebackaplyandsalebackbill(SaleBackSearchBean bean,String salebackIdss) throws BizAppException{
			try {
				SalebackApplyInfo apply = applyDao.getSalebackApplyInfo(bean.getSalebackId());
				ICustInfoService custInfoService = ServiceFactory.getCustInfoService();
				ICustInfoAcctService custInfoAcctService = ServiceFactory.getCustInfoAcctService();
				// 取用户基本信息
				
				CustInfoAcct custInfoAcct = custInfoAcctService.getParam(bean.getInAcctNo().trim());
				CustInfo custInfo = custInfoService.getParam(custInfoAcct.getCustNo());
				apply.setIsOnline(bean.getIsOnline());
				apply.setRate(bean.getRate());
				apply.setInAcctNo(bean.getInAcctNo());
				apply.setInAcctName(custInfo.getCustName());
				modifySalebackApplyInfo(apply);
				String[] idss = salebackIdss.split(",");
				for (int i = 0; i < idss.length; i++) {
					SalebackBillInfo billinfo = billDao.getSalebackBillInfo(idss[i]);
					billinfo.setSalebackId(apply.getSalebackId());
					billDao.modifySalebackBillInfo(billinfo);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		/**
		 * 040转卖断处理
		 * @param rgctId
		 * @throws SQLException 
		 * @throws ServiceException
		 */
		public void overdueBill(String rgctId) throws SQLException{
			List<SalebackBillInfo>  bill=billDao.getSalebackBillInfolistForRgctId(rgctId);
			if(bill!=null){
				bill.get(0).setOperStatus("BSb600");//删除状态
				billDao.modifySalebackBillInfo(bill.get(0));
			}
		}


		
		/**初始化纸票返售数据
		 * @throws Exception */
		@Override
		public void initsalebackbill(List<RebuyBillInfo> billlist,RebuyApplyInfo rebuyApply) throws Exception {
//			
//			SaleBackSearchBean query = new SaleBackSearchBean();
//			String dt=DateTimeUtil.getWorkdayString();//获得当前营业日
//			query.setIsDelayIn("1");
//			query.setWorkday(DateTimeUtil.getWorkdayString());
//			query.setWorktime(DateTimeUtil.getWorkdayString());
//			query.addSpecialOpertion("worktime",BaseSearchBean.MORE_AND_EQUAL);
//			query.addSqlPropretyMapping("worktime", "resaleDueDt");
//			query.addSpecialOpertion("workday",BaseSearchBean.LESS_AND_EQUAL);
//			query.addSqlPropretyMapping("workday", "resaleStaDt");
//			query.setDfaultSrchTbAliasName("bill");
//			
//			query.setOperStatus("BS140");//前置状态
//			query.setBillClass("1");
//			query.addSpecialOpertion("operStatus",BaseSearchBean.IN);
//			query.addSqlPropretyMapping("operStatus", "operStatus");
//			UserLoginfo user = ResourceUtil.getSessionLoginfo();
//			query.setBranchNo(user.getBranchNo());
//			query.addSqlPropretyMapping("branchNo", "branchNo");
//			
//			List<RebuyBillInfo> billlist = billDao.getRebuyBillListinit(query,new Page());
//			RebuyApplyInfoDao rebuyapplyDao = new RebuyApplyInfoDao();
			EcdsBankDataDao ecdsBankDao=new EcdsBankDataDao();
			String operstatus = StatusUtils.queryStatus("SalebackApplyController", "applysubmitentity", null)[0];
			for (int i = 0; i < billlist.size(); i++) {
				SalebackBillInfo saleBackBill= new SalebackBillInfo();
				RebuyBillInfo rebuyBill = billlist.get(i);
				
				if(billDao.getSalebackBillInfo(rebuyBill.getRebuymxId())!=null){
					continue;
				}
				saleBackBill.setRebuyId(rebuyApply.getRebuyId());
				saleBackBill.setSalebackmxId(rebuyBill.getRebuymxId());
				saleBackBill.setAcceptor(rebuyBill.getAcceptor());
				saleBackBill.setAcceptorBankNo(rebuyBill.getAcceptorBankNo());
				saleBackBill.setAcceptorBankName(rebuyBill.getAcceptorBankName());
				saleBackBill.setBranchNo(rebuyBill.getBranchNo());
				saleBackBill.setBillMoney(rebuyBill.getBillMoney());
				saleBackBill.setBillClass(rebuyBill.getBillClass());
				saleBackBill.setBillNo(rebuyBill.getBillNo());
				saleBackBill.setBillOwner(rebuyBill.getBillOwner());
				saleBackBill.setBillSource(rebuyBill.getBillSource());
				saleBackBill.setBillType(rebuyBill.getBillType());
				saleBackBill.setDueDt(rebuyBill.getDueDt());
				saleBackBill.setIsAccpt(rebuyBill.getIsAccpt());
				saleBackBill.setRemitterAcct(rebuyBill.getRemitterAcct());
				saleBackBill.setRemitterBankName(ecdsBankDao.getEcdsBankData(rebuyBill.getRemitterBankNo()).getActorFullCall());
				saleBackBill.setRemitterBankNo(rebuyBill.getRemitterBankNo());
				saleBackBill.setIssueDt(rebuyBill.getIssueDt());
				saleBackBill.setRemitter(rebuyBill.getRemitter());
				saleBackBill.setPayeeName(rebuyBill.getPayee());
				saleBackBill.setPayeeAcct(rebuyBill.getPayeeAcct());
//				saleBackBill.setPayeeBankName(ecdsBankDao.getEcdsBankData(rebuyBill.getPayeeBankNo()).getActorFullCall());//收款人开户行行号
				saleBackBill.setPayeeBankNo(rebuyBill.getPayeeBankNo());
				saleBackBill.setRgctId(rebuyBill.getRgctId());
				saleBackBill.setRemitterCustNo(rebuyBill.getRemitterCustNo());
				saleBackBill.setCreateDt(DateTimeUtil.getWorkdayString());
				saleBackBill.setCreateTime(DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());
				saleBackBill.setForbidFlag(rebuyBill.getForbidFlag());
				saleBackBill.setRebuyId(rebuyBill.getRebuyId());
				saleBackBill.setRebuymxId(rebuyBill.getRebuymxId());
				saleBackBill.setBuyDeptNo(rebuyApply.getDeptNo());
				saleBackBill.setOperStatus(operstatus);
				saleBackBill.setBuyDt(rebuyBill.getRebuyDt());
				saleBackBill.setIsOnline(rebuyBill.getIsOnline());// 是否线上清算
				saleBackBill.setCustAccount(rebuyBill.getCustAccount());
				saleBackBill.setIsInner(rebuyBill.getIsInner());
				saleBackBill.setSalebackDueDt(rebuyApply.getResaleDueDt());
				saleBackBill.setIsDummy("0");
//				saleBackBill.setBillStorageOrg(hist.getStorageBranchNo());//存放机构号
//				saleBackBill.setBillStorageOrgName(hist.getStorageBranchName());//存放机构名称
				billDao.addSalebackBillInfo(saleBackBill);
				
			}
			
		}
		
		/**
		 * 纸票转入撤销记账后对反手清单的处理（删除）
		 * @param ids
		 * @throws Exception
		 */
		public void rebuycancelaccountaftersalebackupdatestatus(List<RebuyBillInfo> rebuylist) throws Exception{
			String opers = StatusUtils.handleStatusNoCheck("salebackAccountController", "entityrebuycancelaccountstatus", null);
			for (int i = 0; i < rebuylist.size(); i++) {
				String salebackmxid = rebuylist.get(i).getRebuymxId();
				billDao.rebuycancelaccountupdatesalebackstatus(salebackmxid,opers);
			}	
		}
		

		@Override
		public SaleBackSearchBean initsalebackSearchbean(SaleBackSearchBean bean) {
			SaleBackSearchBean query = new SaleBackSearchBean();
			query.setBillClass(bean.getBillClass());
			query.setIsInner(bean.getIsInner());
			query.setRebuyId(bean.getRebuyId());
			return query;
		}
		
		/**
		 * 审批回调变更状态
		 * @param batchId
		 * @param status
		 * @return
		 */
		public List<SalebackBillInfo> getSaveBillForSaveIdAndStatus(String batchId,String status){
			
			List<SalebackBillInfo> listbill = new ArrayList<SalebackBillInfo>();
			try {
				listbill = billDao.getSaveBillForSaveIdAndStatus(batchId, status);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return listbill;
		}
		
		/**
		 * 返售记账批次查询
		 * @param query
		 * @param page
		 * @return
		 * @throws Exception
		 */
		public List<SalebackApplyInfo> getaccountbatchlist(SaleBackSearchBean query,Page page) throws Exception{
			SalebackApplyInfoDao saleBackApplyDao = new SalebackApplyInfoDao();
			String branchNo= ResourceUtil.getSessionLoginfo().getBranchNo();
			query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);			
			query.setBranchNo(branchNo);
			
			query.setDfaultSrchTbAliasName("bill");
			query.addSpecialOpertion("opers",BaseSearchBean.IN);
			query.setIsInner(SalebackCodeConst.IS_INNER_FALSE);
			query.addSqlPropretyMapping("opers", "operStatus");
			query.addSqlPropretyMapping("operStatus", "operStatus");
			query.addProperty2TableObj("salebackId", "apply");
			
		
			query.setWorktime(DateTimeUtil.getWorkdayString());
			query.addSpecialOpertion("worktime",BaseSearchBean.LESS_AND_EQUAL);
			query.addSqlPropretyMapping("worktime", "salebackOpenDt");
			
			query.addProperty2TableObj("worktime", "apply");
			
			OrderBean order=new OrderBean("salebackId",false);
		    query.appendOrder(order);
			return saleBackApplyDao.getSaleBackApplyForCondition(page, query);
		}
		
		/**
		 * 记账清单查询
		 * @param query
		 * @param page
		 * @return
		 * @throws SQLException
		 */
		public List<SalebackBillInfo> getaccountbilllist(SaleBackSearchBean query,Page page) throws SQLException{
			UserLoginfo user = ResourceUtil.getSessionLoginfo();
			query.setDfaultSrchTbAliasName("bill");
			query.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);	
			query.setIsInner(SalebackCodeConst.IS_INNER_FALSE);
			query.addSpecialOpertion("opers",BaseSearchBean.IN);
			query.addSqlPropretyMapping("opers", "operStatus");
			query.addSqlPropretyMapping("operStatus", "operStatus");
			query.setBranchNo(user.getBranchNo());
			query.addProperty2TableObj("salebackId", "apply");
			query.addSqlPropretyMapping("branchNo", "branchNo");
			List<SalebackBillInfo> listbills =billDao.getsalebackBillListBySearchBean(query,page);
			return listbills;
		}
		
		
		
		/**
		 * 纸票返售记账提交
		 * @param query
		 * @param salebackmxIds
		 * @throws Exception 
		 */
		public void entityaccountapply(SaleBackSearchBean query,String salebackmxIds) throws Exception{
			IRcSaleBackService rcsalebackservice = RcServiceFactory.getRcSaleBackService();
			Map<String,RgctBill> rgctbillmap = new HashMap<String, RgctBill>();
			UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
			query.setDfaultSrchTbAliasName("apply");
			SalebackApplyInfo apply = applyDao.getSaleBackApplyForCondition(new Page(),query).get(0);
			String rebuyid = "";
			String rgctids = "";
			List<SalebackBillInfo> billList = billDao.getConfirmReceiveBillForId(salebackmxIds);
			for (int i = 0; i < billList.size(); i++) {
				rgctids = rgctids + billList.get(i).getRgctId()+",";
			}
			List<RgctBill> rgctbills = rcsalebackservice.getRgctBillList(rgctids.substring(0,rgctids.length()-1));
			for (int i = 0; i < rgctbills.size(); i++) {
				rgctbillmap.put(rgctbills.get(i).getHist().getRgctId(), rgctbills.get(i));
			}
			for (int i = 0; i < billList.size(); i++) {
				SalebackBillInfo salebackBill = billList.get(i);
				RgctBill rgctBill = rgctbillmap.get(billList.get(i).getRgctId());	
				RgctBillHist rgctHist = rgctBill.getHist();
				rgctHist.setFromBranchNo(userInfo.getBranchId());
				rgctHist.setFromBankNo(userInfo.getBrchBankNo());
				rgctHist.setFromName(userInfo.getBranchName());
				rgctHist.setDealMoney(salebackBill.getSalebackMoney());// 返售金额
				rgctHist.setToBankNo(apply.getAimBranchNo());
				rgctHist.setToName(apply.getCustName());
				rgctHist.setHoldBankNo(apply.getAimBranchNo());
				rgctHist.setHoldCustName(apply.getCustName());
				
				rgctHist.setIsOnline(apply.getIsOnline());// 是否线上清算
				rgctHist.setBackOpenDt(apply.getSalebackOpenDt());// 返售开放日
				rgctHist.setBackEndDt(apply.getSalebackDueDt());
				rgctHist.setEndorseDt(apply.getCreateTime());// 生成返售申请的日期 by
				rgctHist.setBackRate(MathScaleUtil.multiply(apply.getSalebackRate(), 0.01));
				rgctHist.setBackAmount(salebackBill.getSalebackMoney());
				rgctHist.setAcctBranchNo(null);
//				rgctHist.setBackRate(RateTran.getYearRate(salebackApply.getRateType(), salebackApply.getSalebackRate()));// 返售利率
//				rgctHist.setBackAmount(salebackBill.getSalebackMoney());// 实物返售金额
				rgctHist.setRegressDt(apply.getCreateDt());// 回购日-生成返售申请的日期
				rgctHist.setIsLock("0");
				rgctBill.setHist(rgctHist);
				rgctBill.getHist().setAcctBranchNo(null);
				rgctBill.getHist().setStorageBranchNo(null);// 记账之后让借票出库查不上来   
	            //调RC方法 转入方同意背书结果登记
				rcsalebackservice.regAgreeEndorse(rgctBill);
			}
			
			//调用记账方法
	        IAccountFacadeService acctService = ServiceFactory.getSalebackAccountService();
	        AccountRequestDTO<SalebackApplyInfo,SalebackBillInfo> accReq = new AccountRequestDTO<SalebackApplyInfo,SalebackBillInfo>();
	        accReq.setApply(apply);
	        accReq.setBillList(billList);
	        accReq.setUserLogonInfo(userInfo);
	        AccountResponseDTO accResp = acctService.account(accReq);
	        
	        Map<String,SalebackBillInfo> salebackmap = new HashMap<String,SalebackBillInfo>();
	        
	        //改相关表状态
	        for (int i = 0; i < billList.size(); i++) {
	        	SalebackBillInfo salebackBill =billList.get(i);
				String opers = StatusUtils.handleStatusNoCheck("salebackAccountController", "accountapply", null);
	        	salebackBill.setOperStatus(opers);
	        	salebackBill.setCurStatus(SalebackCodeConst.CUR_STATUS_REBUY_REDEEM_OVER);    // 10 买入返售结清
	        	salebackBill.setAcctOperNo(userInfo.getUserNo());
	        	salebackBill.setIsAudited("5");
	        	salebackBill.setExSerial(accResp.getBackFlowNo());
	        	salebackBill.setAccountDt(DateTimeUtil.getWorkdayString());
	        	rebuyid = rebuyid +salebackBill.getRebuymxId()+",";
	        	salebackmap.put(salebackBill.getRebuymxId(), salebackBill);
	        	billDao.modifySalebackBillInfo(salebackBill);
			}
	        String rebuyIds = rebuyid.substring(0,billList.size()-1);
	        
	        List<RebuyBillInfo> rebuyBillInfo = rebuydao.getEntitytricReceiveForId(rebuyIds);
	        for (int i = 0; i < rebuyBillInfo.size(); i++) {
	        	RebuyBillInfo rebuybill = rebuyBillInfo.get(i);
	        	SalebackBillInfo salebackbill = salebackmap.get(rebuybill.getRebuymxId());
	        	rebuybill.setIsBuyback("1");
	        	rebuybill.setGathDate(salebackbill.getAccountDt());
	        	rebuybill.setGathType(RebuyCodeConst.GATH_TYPE_BUYBACK);// 4 贴现赎回/同业返售/系统内返售(系统内回购记账)
	        	rebuybill.setTotalIntrstPayment(salebackbill.getInterest());//总计利息支出
	        	rebuybill.setCurStatus(RebuyCodeConst.CUR_STATUS_REBUY_REDEEM_OVER);// 10 买入返售结清
	        	rebuybill.setTransType(CommonConst.REDEEM);// 返售已赎回
	        	rebuybill.setTransId(salebackbill.getSalebackmxId());	
	        	rebuydao.modifyRebuyBillInfo(rebuybill);
			}
	        
	        
  
		}
		
		/**
		 * 纸票撤销记账
		 * @param query
		 * @param salebackmxIds
		 * @throws Exception 
		 */
		public void entitycancelaccount(SaleBackSearchBean query,String salebackmxIds) throws Exception{
			UserLoginfo userLoginfo = ResourceUtil.getSessionLoginfo();
			query.setDfaultSrchTbAliasName("apply");
			SalebackApplyInfo apply = applyDao.getSaleBackApplyForCondition(new Page(),query).get(0);
			String mxIds = "";
			String rebuyid = "";
			List<SalebackBillInfo> billList = billDao.getConfirmReceiveBillForId(salebackmxIds);
			
			//冲正
	       	IAccountFacadeService acctService = ServiceFactory.getSalebackAccountService();
            UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
            AccountRequestDTO<SalebackApplyInfo,SalebackBillInfo> accReq = new AccountRequestDTO<SalebackApplyInfo, SalebackBillInfo>();
            accReq.setApply(apply);
            accReq.setBillList(billList);
            accReq.setUserLogonInfo(userInfo);
            acctService.reverseAccount(accReq);
			
			//更改相关表的状态
			 Map<String,SalebackBillInfo> salebackmap = new HashMap<String,SalebackBillInfo>();
		     for (int i = 0; i < billList.size(); i++) {
		       SalebackBillInfo salebackBill =billList.get(i);
		       String opers = StatusUtils.handleStatusNoCheck("salebackAccountController", "entitycancelaccount", null);
		       salebackBill.setOperStatus(opers);
		       salebackBill.setCurStatus(SalebackCodeConst.CUR_STATUS_REBUY_REDEEM_OVER);    // 10 买入返售结清
		       salebackBill.setAcctOperNo(userLoginfo.getUserNo());
		       salebackBill.setIsAudited("1");
		       salebackBill.setAccountDt(DateTimeUtil.getWorkdayString());
		       rebuyid = rebuyid +salebackBill.getRebuymxId()+",";
		       salebackmap.put(salebackBill.getRebuymxId(), salebackBill);
		       billDao.modifySalebackBillInfo(salebackBill);
			 }
		   List<RebuyBillInfo> rebuylist = rebuydao.getEntitytricReceiveForId(rebuyid.substring(0,rebuyid.length()-1));
		   for (int i = 0; i < rebuylist.size(); i++) {
			   RebuyBillInfo rebuybillinfo = rebuylist.get(i);
			   rebuybillinfo.setIsBuyback("0");
			   rebuybillinfo.setGathDate(null);
			   rebuybillinfo.setGathType(SalebackCodeConst.GATH_TYPE_COMMON);
			   rebuybillinfo.setTotalIntrstPayment(0.0d);//总计利息支出
			   rebuybillinfo.setCurStatus(SalebackCodeConst.CUR_STATUS_REBUY_REDEEM);
			
			   rebuydao.modifyRebuyBillInfo(rebuybillinfo);
		   } 
		   
		}
	
		@Override
		public String isnewcreatebatch(String rebuyId,String newsalebackId,String iscancel) throws BizAppException{	
			if(newsalebackId!=null&&!"".equals(newsalebackId)&&"1".equals(iscancel)){
				return "0";
			}
			return "1";
		}	
}
