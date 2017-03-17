package com.herongtech.console.service.busiservice.sale;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.herongtech.commons.tools.StringUtils;
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
import com.herongtech.console.domain.acct.bean.AccountResponseDTO;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.dao.BuybackBillInfoDao;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.bean.InterestReqDTO;
import com.herongtech.console.domain.common.bean.InterestResultDTO;
import com.herongtech.console.domain.common.bean.ProdLimitType;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.console.domain.disc.dao.DiscBillInfoDao;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.bean.SaleSearchBean;
import com.herongtech.console.domain.sale.dao.SaleApplyInfoDao;
import com.herongtech.console.domain.sale.dao.SaleBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.IInterestService;
import com.herongtech.console.service.interfaces.IProdLimitTypeService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.BuybackCodeConst;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.SaleCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcSaleService;
import com.herongtech.sysconstant.ISysErrorNo;

public class SaleService implements ISaleService {

	private SaleApplyInfoDao saleApplyDao = new SaleApplyInfoDao();
	private SaleBillInfoDao saleBillDao = new SaleBillInfoDao();
//	private IAuditCommonService auditCommonService;
	/**
	 * 初始化查询条件
	 * @param searchBean
	 */
	private void initQueryCondition(SaleSearchBean searchBean){
		searchBean.addProperty2TableObj("statusArray", "bill");
		searchBean.addProperty2TableObj("operStatus", "bill");
		searchBean.addSpecialOpertion("operStatus",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("statusArray", "operStatus");
		searchBean.addProperty2TableObj("salemxId", "bill");
	}
	/**
	 * 判断转卖批次中各日期关系；
	 * 
	 * @param minDueDt
	 *            :最小票面到期日 结果返回1，则表示没有异常
	 */
	/*public String compareDateException(SaleApplyInfo applyInfo, List dateList) {
		Object[] arrDt = (Object[]) dateList.get(0);
		Date minDueDt = (Date) arrDt[0];
		Date saleDt = new Date(applyInfo.getSaleDt());
//		Date maxAcptDt = (Date) arrDt[1];
		
		 * if (applyInfo.getSaleDt().compareTo(DateTimeUtil.getWorkday()) < 0)
		 * {// 转卖日与当前营业日 // return SaleCodeConst.SALE_LESS_THAN_WORKDAY; }
		 
		if (DateTimeUtil.compartdate(saleDt, minDueDt)) {// 转卖日与最小票面到期日
			return "卖出日应小于等于最小票面到期日";
		}
//		if (compareDate(applyInfo.getSaleDt(), maxAcptDt) < 0) {// 转卖日与最大出票日
//			return " 转卖日应大于等于最大出票日";
//		}

		IHolidayService holidayService = (IHolidayService) HolidayFactory.getHolidayService();
		if (BillConst.REGRESS_YES.equals(applyInfo.getSaleType())) {// 卖出回购
			if (applyInfo.getBuybackOpenDt().compareTo(applyInfo.getSaleDt()) <= 0) {// 转卖日与卖出回购开放日
				return "卖出回购开放日应大于转卖日和营业日";
			}
			if (compareDate(applyInfo.getBuybackOpenDt(), DateTimeUtil.getWorkday()) <= 0) {// 营业日与卖出回购开放日
				return "卖出回购开放日应大于营业日和转卖日";
			}
			if (applyInfo.getRebuyDueDt().compareTo(applyInfo.getBuybackOpenDt()) < 0) {// 卖出回购开放日与卖出回购到期日
				return "卖出回购开放日应小于等于卖出回购到期日";
			}
			if (applyInfo.getRebuyDueDt().compareTo(applyInfo.getSaleDt()) <= 0) {// 转卖日与卖出回购到期日
				return "卖出回购到期日应大于转卖日";
			}
			if (DateTimeUtil.compartdate(applyInfo.getRebuyDueDt(), DateTimeUtil.getWorkday())) {// 营业日与卖出回购到期日
				return "卖出回购到期日应大于营业日";
			}
			if (compareDate(applyInfo.getRebuyDueDt(), minDueDt) > 0) {// 卖出回购到期日与最小票面到期日
				return "卖出回购到期日应小于等于最小票面到期日";
			}
			if (holidayService.ifHoliday(applyInfo.getRebuyDueDt())) {// 卖出回购到期日不能为节假日
				return "卖出回购到期日不能为节假日";
			}
		}
		// 双向卖断到期日判断
		if (SaleCodeConst.BIDECTDUE_PROD_NO.equals(applyInfo.getProdNo())) {
			if (applyInfo.getBidectDueDt().compareTo(applyInfo.getSaleDt()) <= 0) {// 转卖日与双卖到期日
				return "双卖到期日应大于转卖日";
			}
			if (compareDate(applyInfo.getBidectDueDt(), minDueDt) > 0) {// 双卖到期日与最小票面到期日
				return "双卖到期日应小于等于最小票面到期日";
			}
			// 双卖到期日不能为节假日
			if (holidayService.ifHoliday(applyInfo.getBidectDueDt())) {
				return "双卖到期日不能为节假日";
			}
			if (compareDate(applyInfo.getBidectDueDt(), DateTimeUtil.getWorkday()) <= 0) {// 转卖日与最大出票日
				return " 双卖到期日大于当前营业日";
			}

		}
		return IConstants.One;// 没有异常
	}*/
/***增、删、改、查批次信息  begin********************************************************************************************************************/
	/**
	 * 审核、记账、撤销审核、撤销记账、撤销申请等查询批次列表时使用
	 */
	public List<SaleApplyInfo> getSaleApplyListForCondition(Page page,SaleSearchBean searchBean) throws Exception {
		initQueryCondition(searchBean);
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		searchBean.setBillStorageBrchno(user.getBranchNo());//机构号
		OrderBean order=new OrderBean("saleId",false);
	    searchBean.appendOrder(order);
	    return saleApplyDao.getSaleApplyListForCondition(page, searchBean);
	}
	/**
	 * 申请查询批次列表时使用
	 * @param page
	 * @param searchBean
	 * @return
	 * @throws Exception
	 */
	public List<SaleApplyInfo> getSaleApplyListBySearchBeanForApply(Page page,SaleSearchBean searchBean) throws Exception {
		initQueryCondition(searchBean);
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		searchBean.setBillStorageBrchno(user.getBranchNo());//机构号
		OrderBean order=new OrderBean("saleId",false);
	    searchBean.appendOrder(order);
	    return saleApplyDao.getSaleApplyListBySearchBeanForApply(page, searchBean);
	}
	
	public SaleApplyInfo getSaleApplyInfo(SaleSearchBean searchBean) throws SQLException{
		return saleApplyDao.getSaleApplyInfo(searchBean);
	}
	public SaleApplyInfo getSaleApplyInfo(String saleId) throws SQLException{
		return saleApplyDao.getSaleApplyInfo(saleId);
	}
	/**
	 * 设置批次公共属性
	 * @param apply
	 * @param userInfo
	 * @throws SQLException 
	 */
	private void setBatchCommonAttr(SaleApplyInfo saleApplyInfo, UserLoginfo userInfo) throws SQLException {
		saleApplyInfo.setRateType(IConstants.INTEREST.YEAR_RATE_TYPE);//年利率
		saleApplyInfo.setInAcctType("1");//账户类型
		saleApplyInfo.setIfBidirBuy("0");//是否双卖
		saleApplyInfo.setIsDummy("0");//是否移票
		saleApplyInfo.setIsRedisc("0");//是否再贴现
		IProdLimitTypeService prodltService = ServiceFactory.getProdlLimitTypeService();
		ProdLimitType prodLimit = prodltService.getProdLimitTypeByProdNo(saleApplyInfo.getProdNo());
		saleApplyInfo.setSaleType(prodLimit.getBuyType());//转卖类型
		saleApplyInfo.setIsInner(prodLimit.getIsInner());//是否系统内
		
		/*if (SaleCodeConst.BIDECTDUE_PROD_NO.equals(saleApplyInfo.getProdNo())) {// 双卖
			saleApplyInfo.setIfBidirBuy("1");
		}*/
		
		if (RcConstants.REGRESS_NO.equals(prodLimit.getBuyType())) {// 非回购类
			saleApplyInfo.setRebuyDueDt(null);
			saleApplyInfo.setBuybackOpenDt(null);
		}
		//是否再贴现
		if (SaleCodeConst.REDISC_PROD_NO.equals(saleApplyInfo.getProdNo())
				|| SaleCodeConst.REBUY_REDISC_PROD_NO.equals(saleApplyInfo.getProdNo())) {//
			saleApplyInfo.setIsRedisc(RcConstants.REDISCOUNT_CENTER);
			saleApplyInfo.setInAcctType("2");//再贴现账户类型
		}
		if (saleApplyInfo.getIsInner().equals(RcConstants.INNER_YES)) {
			if (IDict.K_BILL_CLASS.K_ELEC_BILL.equals(saleApplyInfo.getBillClass())) {
				saleApplyInfo.setIsDummy("1");
			} else {
//				saleApplyInfo.setIfDummy(SaleTool.changeIfDummy(saleApplyInfo.getIsDummy(), saleApplyInfo.getProdNo()));
			}
			if (!StringUtils.isBlank(saleApplyInfo.getIsDummy()) && saleApplyInfo.getIsDummy().equals("1")) {// 不代保管
				saleApplyInfo.setBillPositionBranch(saleApplyInfo.getCustName());
			} else {
				saleApplyInfo.setBillPositionBranch(userInfo.getBranchName());
			}
		}
		saleApplyInfo.setBillStorageBrchno(userInfo.getBranchNo());// 因没有代保管业务，所以与BranchNo一致
		saleApplyInfo.setBillStorageBrchid(userInfo.getBranchId());// 因没有代保管业务，所以与BranchID一致
		saleApplyInfo.setBillStorageName(userInfo.getBranchName());// 因没有代保管业务，所以与BranchName一致
		saleApplyInfo.setOperNo(userInfo.getUserNo());//操作柜员
		saleApplyInfo.setApplyStatus(CommonConst.APPLY_STATUS_NEW);//设置批次状态（当新增批次或者批次中有未提交申请的票，都是这个状态）
		//线上清算--纸票的话没有线上清算的概念
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(saleApplyInfo.getBillClass()) && StringUtils.isBlank(saleApplyInfo.getIsOnline())){
			saleApplyInfo.setIsOnline(RcConstants.SETTLEMENTMARK_ZERO);
		}
		//当为系统内时，设置客户行号为机构所在行行号
		if(RcConstants.INNER_YES.equals(saleApplyInfo.getIsInner()) && !StringUtils.isBlank(saleApplyInfo.getBranchNo())){
			BranchDao branchDao = new BranchDao();
			Branch branch = branchDao.getBranch(saleApplyInfo.getBranchNo());
			saleApplyInfo.setAimBranchNo(branch.getPayBankNo());
		}
		//saleApplyInfo.setForbidFlag("0");//禁止背书标记
	}
	public void addSaleApply(SaleApplyInfo saleApplyInfo) throws BizAppException{
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		try{
			this.setBatchCommonAttr(saleApplyInfo, user);
			saleApplyInfo.setSaleId(sequenceService.getSALE_APPLY_ID());//sale_id
			saleApplyInfo.setBatchNo(sequenceService.getSaleApplyNo(user.getBranchNo()));//批次号
			saleApplyInfo.setCreateDt(DateTimeUtil.getWorkdayString());
			saleApplyInfo.setCreateTime(DateTimeUtil.get_hhmmss_time());
//			saleApplyInfo.setSaleTime(DateTimeUtil.get_hhmmss_time());
			saleApplyDao.addSaleApplyInfo(saleApplyInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException("添加批次失败:"+e.getMessage());
		}
	}
	
	public void modifySaleApply(SaleApplyInfo saleApplyInfo) throws SQLException{
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		this.setBatchCommonAttr(saleApplyInfo, user);
		SaleApplyInfo apply = this.getSaleApplyInfo(saleApplyInfo.getSaleId());
		saleApplyInfo.setCreateDt(apply.getCreateDt());
		saleApplyInfo.setCreateTime(apply.getCreateTime());
		saleApplyDao.modifySaleApplyInfo(saleApplyInfo);
	}
	public boolean delApplyInfoForSaleIds(String[] saleIds) throws BizAppException{
		SaleSearchBean query = new SaleSearchBean();
		boolean bool = true;
		List<SaleBillInfo> billList = null;
		//申请岗能看到的批次有如下情况：1-新建的批次，里面没票  2-里面有状态为申请未提交、删除的票
		try {
			for(int i=0;i<saleIds.length;i++){
				query.setSaleId(saleIds[i]);
				query.setDfaultSrchTbAliasName("bill");
				query.setOperStatus(StatusUtils.handleStatusNoCheck("SaleApplyController", "addBill", null));
				billList = saleBillDao.getSaleBillListBySearchBean(query);
				if(billList==null || billList.size()<=0){
					saleApplyDao.updateSaleApplyStatus(CommonConst.APPLY_STATUS_DELETE, saleIds[i]);
				}else{
					bool = false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException("删除批次信息失败，"+e.getMessage());
		}
		return bool;
	}
	/***增、删、改、查清单信息  begin********************************************************************************************************************/
	public List<SaleBillInfo> getSaleBillListBySearchBean(SaleSearchBean searchBean) throws SQLException{
    	return saleBillDao.getSaleBillListBySearchBean(searchBean);
    }
	public List<SaleBillInfo> getSaleBillListBySearchBeanForPage(Page page,SaleSearchBean query) throws SQLException, BizAppException{
		OrderBean order=new OrderBean("salemxId",false);
	    query.appendOrder(order);
		return saleBillDao.getSaleBillListBySearchBeanForPage(page, query);
	}
	public SaleBillInfo getSaleBillForSalemxId(String salemxId) throws SQLException{
		return saleBillDao.getSaleBillInfo(salemxId);
	}
	
	public void modifySaleBill(SaleBillInfo saleBill) throws SQLException{
		saleBillDao.modifySaleBillInfo(saleBill);
	}
	public int delBillInfoForSalemxIds(String[] salemxIds) throws BizAppException{
		int rs = 0;
		IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
		try {
			List<SaleBillInfo> billList=saleBillDao.getSaleBillInfoByIds(salemxIds);
			for(int i=0;i<billList.size();i++){
				SaleBillInfo bill=billList.get(i);
				rcSaleService.unLock(bill.getRgctId());
				bill.setOperStatus(StatusUtils.handleStatus("SaleApplyController", "delBill", null,bill.getOperStatus()));
				rs=rs+saleBillDao.modifySaleBillInfo(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException("删除清单失败"+e.getMessage());
		}
		return rs;
	}
	public List<RgctBillData> getRgctBillInfo(Page page,SaleSearchBean searchBean) throws BizAppException{
		IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
		RcBaseSearchBean rcBaseSearchBean = this.getRcBaseSearchBean(searchBean);
		List<RgctBillData> list = rcSaleService.queryPresaleBill(rcBaseSearchBean, page);
		return list;
	}
	/**
	 * 根据SaleSearchBean获取参数RcBaseSearchBean
	 * @param searchBean
	 * @return
	 */
	public RcBaseSearchBean getRcBaseSearchBean(SaleSearchBean searchBean){
		RcBaseSearchBean rcBaseSearchBean = new RcBaseSearchBean();
		rcBaseSearchBean.setHoldBankNo(ResourceUtil.getSessionLoginfo().getBrchBankNo());//获得登陆者行号进行查票
		if(org.apache.commons.lang.StringUtils.isNotEmpty(searchBean.getStartDay())){
			rcBaseSearchBean.setMinDueDt(searchBean.getStartDay());
		}
		if(org.apache.commons.lang.StringUtils.isNotEmpty(searchBean.getEndDay())){
			rcBaseSearchBean.setMaxDueDt(searchBean.getEndDay());
		}
		rcBaseSearchBean.setMinBillMoney(searchBean.getStartMoney());
		rcBaseSearchBean.setMaxBillMoney(searchBean.getEndMoney());
		rcBaseSearchBean.setBillNo(searchBean.getBillNo());
		rcBaseSearchBean.setBillClass(searchBean.getBillClass());
		rcBaseSearchBean.setBillType(searchBean.getBillType());
		return rcBaseSearchBean;
	}
	/**
	 * 添加转卖清单
	 * 1、查询登记中心列表
	 * 2、锁票，并更新hist
	 * 3、根据登记中心信息获取转卖清单，并保存
	 */
	public void addSaleBillInfo(String ids,String saleId) throws BizAppException, SQLException{
		ISequenceService sequenceFactory = ServiceFactory.getSequenceService();
		IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
		List<RgctBill> billList= rcSaleService.getRgctBillList(ids);
		SaleApplyInfo apply = this.getSaleApplyInfo(saleId);
		//TODO 转卖日、票面到期日的判断--加票时判断
		for(int i=0;i<billList.size();i++){
			RgctBill rgctBill =billList.get(i);
			if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(rgctBill.getInfo().getBillClass())&&apply.getSaleType().equals(RcConstants.REGRESS_YES)){
				boolean isBuyBackOpenDt=DateTimeUtil.compartdate(apply.getBuybackOpenDt(),rgctBill.getInfo().getIssueDt());
				if(isBuyBackOpenDt){
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据的出票日"+rgctBill.getInfo().getIssueDt()+"应小于赎回开放日"+apply.getBuybackOpenDt());
				}
				boolean isRebuyDt=DateTimeUtil.compartdate(rgctBill.getInfo().getDueDt(),apply.getRebuyDueDt());
				if(isRebuyDt){
					throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据的到期日"+rgctBill.getInfo().getDueDt()+"应大于赎回截止日"+apply.getRebuyDueDt());
				}
			}
			RgctBillHist rgctBillHist = rgctBill.getHist();
			//加锁
			rgctBillHist.setIsLock(IDict.K_LOCK.K_LOCK_YES);
			SaleBillInfo saleBill = null;
			try {
				//根据RgctBill组装SaleBillInfo对象
				saleBill = this.getSaleBillByRgctBill(rgctBill,apply.getSaleType());
				saleBill.setCurStatus(null);
				saleBill.setSalemxId(sequenceFactory.getSALE_BILL_INFO_ID());
				saleBill.setSaleId(saleId);
				saleBill.setOperStatus(StatusUtils.handleStatusNoCheck("SaleApplyController", "addBill", null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//修改表tsale_bill_info
			saleBillDao.addSaleBillInfo(saleBill);
			//修改rgctBillHist状态
			rcSaleService.updateRgctBillHist(rgctBillHist);
		}
	}
	/**
	 * 根据RgctBill登记中心拼装SaleBillInfo清单对象
	 * @param rgctBill
	 * @return
	 * @throws Exception 
	 */
	public SaleBillInfo getSaleBillByRgctBill(RgctBill rgctBill,String isDelayIn) throws Exception{
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		SaleBillInfo saleBill = new SaleBillInfo();
		RgctBillInfo rgctBillInfo = rgctBill.getInfo();
		RgctBillHist rgctBillHist = rgctBill.getHist();
		try {
			//将原对象相同的属性copy到新对象中
			BeanUtils.copyProperties(saleBill, rgctBillInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "copyProperties失败");
		}
		//因为使用了copyProperties，所以rgctBillInfo与saleBill相同的字段就不用单独赋值了，但是rgctBillHist中的还是要赋值给saleBill
		saleBill.setIsSameCity(rgctBillHist.getIsSameCity());
		saleBill.setBillOwner(rgctBillHist.getFromName());//所属客户名称=背书人全称

		saleBill.setIsOnline(rgctBillHist.getIsOnline());
		saleBill.setCreateTime(DateTimeUtil.get_hhmmss_time());
        saleBill.setCreateDate(DateTimeUtil.getWorkdayString());
		saleBill.setBuyType(rgctBillHist.getBuyType());
		String billTrack = rgctBillHist.getBillTrack();
		String origSrc = "", priorSrc = "";
		if (StringUtils.isEmpty(billTrack)) {
			origSrc = "2";
			priorSrc = "2";// 同业
		} else {
			origSrc = billTrack.substring(0, 1);// 最初来源
			priorSrc = billTrack.substring(billTrack.length() - 1);// 最近来源
		}

		saleBill.setPriorSrc(priorSrc);
		saleBill.setBillSource(origSrc);
		String buyBrchNo="";// 用于判断电票的转卖机构和来源机构是否相符
		List<DiscBillInfo> discBillList = new ArrayList<DiscBillInfo>();
		DiscBillInfo discBillInfo = null;
		DiscApplyInfo discApplyInfo = null;
		List<RebuyBillInfo> rebuyBillList = new ArrayList<RebuyBillInfo>();
		RebuyBillInfo rebuyBillInfo = null;
		RebuyApplyInfo rebuyApplyInfo = null;
		if(SaleCodeConst.DISC_BILL.equals(saleBill.getPriorSrc())){//票据来源是贴现
			//需要查询tdisc_bill_info，并将信息set到saleBill对象
			IDiscService discService = ServiceFactory.getDiscService();
			DiscSearchBean discSb = new DiscSearchBean();
			//查询条件-rgctId
			discSb.setRgctId(rgctBillInfo.getId());
			//查询条件-倒序--discService.getDiscBillListForBatch方法中有
			//查询条件-operStatus(记账完成状态，纸票是入库完成状态)
			if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(rgctBillInfo.getBillClass())){
				discSb.setOperStatus(StatusUtils.handleStatusNoCheck("DiscStorageController", "doStorage", null));
			}else if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(rgctBillInfo.getBillClass())){
				discSb.setOperStatus(StatusUtils.handleStatusNoCheck("DiscAccountController", "doFinalAccount", null));
			}
			Page page=new Page();
			page.setShowCount(Integer.MAX_VALUE);
			discSb.setDfaultSrchTbAliasName("bill");
			discBillList=discService.getDiscBillListForBatch(page, discSb);
			discBillInfo = discBillList.get(0);
			saleBill.setPriorSrcId(discBillInfo.getDiscmxId());//最近来源ID
			saleBill.setBillSourceId(discBillInfo.getDiscmxId());//最初来源ID
			discApplyInfo = discService.getDiscApplyInfo(discBillInfo.getDiscId(),null);
//			saleBill.setAdscriptionId(discApplyInfo.getProfOwnerNo());//经营机构=经营机构归属号
			saleBill.setCostRate(discApplyInfo.getCbRate());//成本利率
			saleBill.setResaleDueDt(discApplyInfo.getRedeemDate());//买入返售到期日=赎回开放日
			saleBill.setResaleOpenDt(discApplyInfo.getRedemptionDt());//原回购开放日=赎回截止日
			saleBill.setBillBeforeOwner(discApplyInfo.getCustName());//交易前手=贴现客户名称
			saleBill.setOldRate(discApplyInfo.getRate());//原买入利率
			saleBill.setOldDiscDt(discApplyInfo.getDiscDt());//原买入日
			saleBill.setOldFileNo(discBillInfo.getFileNo());//原档案编号
			saleBill.setOldGaleDate(discBillInfo.getGaleDate());//原计息到期日
			saleBill.setOldInterest(discBillInfo.getSalerInterest());//买入利息
			saleBill.setOldInstCalDays(discBillInfo.getInterestDays());//买入计息天数
			saleBill.setBuyMoney(discBillInfo.getDraftPayMoney());//买入价格
			buyBrchNo=discApplyInfo.getBranchNo();
		}else{//票据来源是转入
			//需要查询trebuy_bill_info，并将信息set到saleBill对象
			IRebuyService rebuyService = ServiceFactory.getRebuyService();
			RebuySearchBean rebuySb = new RebuySearchBean();
			rebuySb.setDfaultSrchTbAliasName("bill");
			//查询条件-rgctId
			rebuySb.setRgctId(rgctBillInfo.getId());
			//查询条件-倒序
			OrderBean order = new OrderBean("rebuymxId",false);
			rebuySb.appendOrder(order);
			//查询条件-operStatus(记账完成状态，纸票是入库完成状态)
			if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(rgctBillInfo.getBillClass())){
				rebuySb.setOperStatus(StatusUtils.handleStatusNoCheck("RebuyStorageController", "storageSubmit", null));
			}else if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(rgctBillInfo.getBillClass())){
				rebuySb.setOperStatus(StatusUtils.handleStatusNoCheck("RebuyAccountController", "accountConfirmSubmitElec", null));
			}
			Page page = new Page();
			page.setShowCount(Integer.MAX_VALUE);
			rebuyBillList = rebuyService.getRebuyBillListBySearchBean(rebuySb, page);
			rebuyBillInfo = rebuyBillList.get(0);
			saleBill.setPriorSrcId(rebuyBillInfo.getRebuymxId());
			saleBill.setBillSourceId(rebuyBillInfo.getRebuymxId());
			rebuyApplyInfo = rebuyService.getRebuyApplyInfo(rebuyBillInfo.getRebuyId());
			saleBill.setCostRate(rebuyApplyInfo.getCbRate());
			saleBill.setResaleDueDt(rebuyApplyInfo.getResaleDueDt());
			saleBill.setResaleOpenDt(rebuyApplyInfo.getResaleStaDt());
			saleBill.setBillBeforeOwner(rebuyApplyInfo.getCustName());
			saleBill.setOldRate(rebuyApplyInfo.getRate());
			saleBill.setOldDiscDt(rebuyApplyInfo.getRebuyDt());
			saleBill.setOldFileNo(rebuyApplyInfo.getFileNo());
			saleBill.setOldGaleDate(rebuyBillInfo.getGaleDate());
			saleBill.setOldInterest(rebuyBillInfo.getInterest());
			saleBill.setOldInstCalDays(rebuyBillInfo.getInterestDays());
			saleBill.setBuyMoney(rebuyBillInfo.getPayMoney());
			buyBrchNo=rebuyApplyInfo.getBranchNo();
		}
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(rgctBillInfo.getBillClass()) && (! user.getBrchNo().equals(buyBrchNo))){
			throw new BizAppException(rgctBillInfo.getBillNo()+"：买入机构为"+buyBrchNo+",与当前卖出机构不符,不允许转卖操作!");
		}
//		Object[] srcId = (Object[]) srcMap.get(rgctBillInfo.getId());//billStorageService.getBillSourceIdByRgctId(rgctBillInfo.getId());
		saleBill.setRgctId(rgctBillInfo.getId());
		saleBill.setBranchNo(user.getBranchNo());//归属机构
		saleBill.setIsDelayIn(isDelayIn);
		
		saleBill.setYzSource("0");//移植来源
//		saleBill.setBillStorage(rgctBillHist.getBillStorage());
		saleBill.setIsBuyback("0");//是否回购记账完成
		saleBill.setBidirSaleHistFlag("0");//额度控制历史控制标记
		return saleBill;
	}
	
	
	/***利息计算、利息调整  begin********************************************************************************************************************/
	/**
	 * 单个利息试算
	 */
	public InterestResultDTO interestTrial(SaleBillInfo saleBill,SaleApplyInfo saleApply) throws Exception{
		IInterestService interestService = ServiceFactory.getInterestService();
		//组装利息试算方法所需的参数对象
		InterestReqDTO interestDTO = new InterestReqDTO();
		interestDTO.setBeginDate(DateTimeUtil.parseStringToDate(saleApply.getSaleDt()));
		if (RcConstants.REGRESS_YES.equals(saleApply.getSaleType())) {// 回购
			interestDTO.setEndDate(DateTimeUtil.parseStringToDate(saleApply.getRebuyDueDt()));
		} else {
			interestDTO.setEndDate(DateTimeUtil.parseStringToDate(saleBill.getDueDt()));
		}
		interestDTO.setAmount(new BigDecimal(saleBill.getBillMoney()));
		interestDTO.setRate(new BigDecimal(saleApply.getRate()));
		interestDTO.setProductNo(saleApply.getProdNo());
		interestDTO.setBillClass(saleApply.getBillClass());
		interestDTO.setBillType(saleApply.getBillType());
		interestDTO.setDelayDays(saleBill.getDelayDays());
		interestDTO.setChargeKind(saleBill.getDelayType());
		interestDTO.setRateType(saleApply.getRateType());
		interestDTO.setIfSameCity(saleBill.getIsSameCity());
		//调用利息试算方法
		InterestResultDTO interestResult = interestService.getInterest(interestDTO);
		//将利息试算的相关信息保存到SaleBillInfo对象
		Double interest = MathScaleUtil.round(interestResult.getInterest().doubleValue(), 4);
		saleBill.setInterest(interest);
		saleBill.setGaleDate(DateTimeUtil.get_YYYY_MM_DD_Date(interestResult.getGaleDate()));
		saleBill.setInterestDays(interestResult.getInterestCalDays());
		saleBill.setReceiveMoney(MathScaleUtil.subtract(saleBill.getBillMoney(), interest));
		saleBill.setRate(saleApply.getRate());
		saleBill.setRateType(saleApply.getRateType());
		saleBillDao.modifySaleBillInfo(saleBill);
		return interestResult;
	}
	/**
	 * 批量利息试算
	 * @param searchBean
	 * @return
	 * @throws Exception
	 */
	public void interestTrial(SaleSearchBean bean) throws Exception{
		//查询SaleApplyInfo和SaleBillInfo对象，用于拼装InterestReqDTO
		String[] ids = CommUtils.couvertLong(bean.getSalemxIds());
		List<SaleBillInfo> saleBillList = null;
		SaleApplyInfo saleApply = null;
		if(ids!=null && ids.length>0){
			saleBillList = saleBillDao.getSaleBillInfoByIds(ids);
			saleApply = saleApplyDao.getSaleApplyInfo(saleBillList.get(0).getSaleId());
			for(SaleBillInfo saleBill:saleBillList){
				saleBill.setIsSameCity(bean.getIsSameCity());
				saleBill.setDelayType(bean.getDelayType());
				saleBill.setDelayDays(bean.getDelayDays());
				interestTrial(saleBill, saleApply);//调用单个利息试算方法
			}
		}
		
	}
	public boolean interestAdjust(String salemxId,String adjustMoney) throws Exception{
		String[] ids = {salemxId};
		Double adjMoy = null;
		try {
			adjMoy = Double.valueOf(adjustMoney);
		} catch (Exception e) {
			throw new BizAppException("输入的金额格式不正确");
		}
		if(checkBillsHasCalcInterest(ids)){
			SaleBillInfo saleBill = saleBillDao.getSaleBillInfo(salemxId);
			if(Double.compare(saleBill.getBillMoney(), adjMoy)<0 || adjMoy<0){
				throw new BizAppException("调整金额必须大于等于零，小于等于票面金额");
			}
			saleBill.setInterest(adjMoy);
			saleBill.setReceiveMoney(MathScaleUtil.subtract(saleBill.getBillMoney(), adjMoy));
			saleBillDao.modifySaleBillInfo(saleBill);
			return true;
		}
		return false;
	}
	/**
	 * 检查是否进行过利息试算
	 * @param ids
	 * @return
	 * @throws SQLException 
	 */
	public boolean checkBillsHasCalcInterest(String[] ids) throws SQLException{
		List<SaleBillInfo> saleBillList = saleBillDao.getSaleBillInfoByIds(ids);
		for(SaleBillInfo saleBill:saleBillList){
			if(StringUtils.isBlank(saleBill.getGaleDate()) || StringUtils.isBlank(saleBill.getDelayType())){
				return false;
			}
		}
		return true;
		
	}
	
	/**提交（撤销）申请、提交（撤销）审核、提交（撤销）记账  begin**************************************************************************************************************/
	/**
	 * 提交申请：
	 * 		（1）检查是否进行了利息计算，是则继续向下进行
	 * 		（2）构建RgctBillHist--同业间纸票
	 * 		（3）调用RC 转出背书登记方法--同业间纸票
	 * 		（4）修改SaleBillInfo信息
	 * 		（5）创建审批任务
	 * 		（6）判断是否需要修改批次状态
	 */
	public void submitSaleApply(String saleId,String[] ids) throws BizAppException{
		String[] beforeStatus = null;
		UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
		double auditAmt = 0.0;
		try{
			SaleApplyInfo applyInfo=saleApplyDao.getSaleApplyInfo(saleId);
			//TODO 检查是否允许线上清算
//			checkOnline(applyInfo);
			if(checkBillsHasCalcInterest(ids)){//检查提交申请前是否进行了利息计算
				List<SaleBillInfo> saleBillList = saleBillDao.getSaleBillInfoByIds(ids);
				for(SaleBillInfo saleBill:saleBillList){
					//判断是否为系统外纸票
					if(RcConstants.INNER_NO.equals(applyInfo.getIsInner()) && IDict.K_BILL_CLASS.K_ENTY_BILL.equals(applyInfo.getBillClass())){
						IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
						RgctBill rgctBill=rcSaleService.getRgctBillById(saleBill.getRgctId());
						//构建RgctBillHist
						this.endorChangeRgctBillHist(rgctBill, applyInfo, saleBill, userLogonInfo);		
						//在纸票提交申请时要调用rcSaleService的saleEndorse方法
						rcSaleService.saleEndorse(rgctBill);
					}
					//修改SaleBillInfo操作状态
					beforeStatus = StatusUtils.queryStatus("SaleApplyController", "submitApply", null);
					String afterStatus = StatusUtils.handleStatus("SaleApplyController", "submitApply", null,saleBill.getOperStatus());
					saleBill.setOperStatus(afterStatus);
					saleBill.setApplyOperNo(userLogonInfo.getUserNo());
					saleBill.setApplyTellerDt(DateTimeUtil.getWorkdayString());
					saleBill.setApplyTellerTime(DateTimeUtil.get_hhmmss_time());
					saleBill.setResaleDueDt(applyInfo.getRebuyDueDt());
					saleBill.setIsInner(applyInfo.getIsInner());
					saleBill.setForbidFlag(StringUtils.isEmpty(applyInfo.getForbidFlag()) ? "0" : applyInfo.getForbidFlag());
					saleBill.setIsOnline(applyInfo.getIsOnline());
					saleBillDao.modifySaleBillInfo(saleBill);
					auditAmt += saleBill.getBillMoney();
				}
				//创建审批任务
				AuditTask auditTask = new AuditTask();
	            auditTask.setWaitAuditAmt(auditAmt); 
	            auditTask.setAtAuthorName(userLogonInfo.getUserName()); 
	            auditTask.setAtAuthorId(userLogonInfo.getUserId()); 
	            auditTask.setBrchNo(userLogonInfo.getBrchNo()); 
	            auditTask.setAtCreateDt(DateTimeUtil.getWorkdayString()); 
	            auditTask.setAtCreateTime(DateTimeUtil.getTime());  
	            auditTask.setProdNo(applyInfo.getProdNo());
	            auditTask.setBatchNo(applyInfo.getBatchNo()); 
	            auditTask.setBatchId(saleId);
	            auditTask.setEntityName("saleAuditController.do?method=auditDetailList");
	            auditTask.setEntityService("saleId");
			    ServiceFactory.getAuditTasksService().addAuditTask(auditTask);
			    
				//要判断该批次下是否还有没有提交的票，没有则修改批次状态applyStatus为2（提交申请后）
				List<SaleBillInfo> saleBills = getSaleBillListForNewSearchBean(saleId, beforeStatus);
				if(saleBills.size()<=0){
					saleApplyDao.updateSaleApplyStatus(CommonConst.APPLY_STATUS_SUBMIT, saleId);
				}
			}else{
				throw new BizAppException("请先进行利息计算，再提交!");
			}
		}catch (Exception e) {
			throw new BizAppException("转卖申请提交失败:"+e.getMessage());
		}
		
	}
	/**
	 * 根据sale_id和票据操作状态查询批次下的票据列表信息
	 * @param saleId
	 * @param statusArray
	 * @return
	 * @throws SQLException
	 */
	public List<SaleBillInfo> getSaleBillListForNewSearchBean(String saleId,String[] statusArray) throws SQLException{
		SaleSearchBean searchBean = new SaleSearchBean();
		searchBean.setSaleId(saleId);
		searchBean.setStatusArray(statusArray);
		searchBean.setDfaultSrchTbAliasName("bill");
		initQueryCondition(searchBean);
		List<SaleBillInfo> saleBillList = saleBillDao.getSaleBillListBySearchBean(searchBean);
		return saleBillList;
	}
	/**检查是否允许线上清算
	 * @param apply
	 */
	/*private void checkOnline(SaleApplyInfo apply) {
		if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(apply.getBillClass()) && RcConstants.SETTLEMENTMARK_ONE.equals(apply.getIsOnline())){
			BooleanResult result = onlineService.checkOnline(apply.getAimBrchNo(), apply.getIsOnline());
			if(!result.isSuccess()){
				throw new ServiceException(result.getInfo());
			}
		}
	}*/
	/**
	 * 背书：通过转卖批次、清单构建rgctBillHist
	 * 
	 * @param rgctBill
	 * @param saleBill
	 * @param user
	 * @throws ServiceException
	 */
	private RgctBillHist endorChangeRgctBillHist(RgctBill rgctBill, SaleApplyInfo applyInfo, SaleBillInfo saleBill,
			UserLoginfo user) throws BizAppException {
		RgctBillHist hist = rgctBill.getHist();
		/** 发起方* */
		hist.setFromName(user.getBranchName());
		hist.setFromBankNo(user.getBrchBankNo());
		hist.setFromAcctNo("0");// 转出人账号
		hist.setFromBranchNo(user.getBranchNo());
		/** 接收方* */
		hist.setToBankNo(applyInfo.getAimBranchNo());
		hist.setToBranchNo(applyInfo.getBranchNo());
		hist.setToName(applyInfo.getCustName());
		if (StringUtils.isBlank(applyInfo.getInnerAccount())) {
			hist.setToAcctNo("0");
		} else {
			hist.setToAcctNo(applyInfo.getInnerAccount());
		}
		/** 利率、实付金额* */
		hist.setInterest(saleBill.getInterest());
		hist.setInterestRate(MathScaleUtil.divide(applyInfo.getRate(), 100));
		hist.setDealMoney(saleBill.getReceiveMoney());
		hist.setDelayDays(String.valueOf(saleBill.getDelayDays()));
		hist.setDelayType(applyInfo.getDelayType());
		hist.setInterestDays(saleBill.getInterestDays());
		//买入  贴现 才设置 计息到期日 卖出不应该放值
//		hist.setInterestDueDt(saleBill.getGaleDate());
		/** 赎回信息* */
		hist.setBackOpenDt(applyInfo.getBuybackOpenDt());// 赎回开放日
		hist.setBackEndDt(applyInfo.getRebuyDueDt());
//		if (applyInfo.getBuybackRate() != null) {
//			hist.setBackRate(MathScaleUtil.divide(applyInfo.getBuybackRate(), 100));// 赎回利率
//		}
		hist.setBackAmount(saleBill.getBuybackMoney());// 赎回金额
		hist.setIsOnline(applyInfo.getIsOnline());// 是否线上清算
		hist.setForbidFlag(saleBill.getForbidFlag());
		hist.setIsRegress(applyInfo.getSaleType());
		hist.setIsRediscCenter(applyInfo.getIsRedisc());
		hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);// 申请前先解锁
		hist.setChannel(RcConstants.COMES_FROM_SYS);
		hist.setEndorseDt(DateTimeUtil.getWorkdayString());
		hist.setIfInner(applyInfo.getIsInner());
		hist.setRegressDt(applyInfo.getRebuyDueDt());
		hist.setIsSameCity(saleBill.getIsSameCity());
		rgctBill.setHist(hist);
		return hist;
	}
	
	
	/**
	 * 转卖记账：
	 * 
	 * 		（1）修改SaleBillInfo状态
	 * 		（2）获取RC登记的票据信息，记账之后让借票出库查不上来
	 * 		（3）调RC方法： 转入方同意背书结果登记--纸票
	 * 		（4）修改票据来源相关信息
	 * 		（5）调用记账方法
	 */
    public void submitSaleAccount(String saleId,String[] ids,UserLoginfo userLogonInfo) throws Exception {
        if(ids == null || ids.length==0){
            throw new Exception("请选择票据");
        }
        IRcSaleService rcSaleService=RcServiceFactory.getRcSaleService();
        ISequenceService sequenceService=ServiceFactory.getSequenceService();
        List<SaleBillInfo> billList=saleBillDao.getSaleBillInfoByIds(ids);
        SaleSearchBean bean = new SaleSearchBean();
        bean.setSaleId(saleId);
        bean.setDfaultSrchTbAliasName("apply");
        SaleApplyInfo apply = saleApplyDao.getSaleApplyInfo(bean);
      //订单模式的线上清算电子票
  		/*if(RcConstants.SETTLEMENTMARK_ONE.equals(apply.getIsOnline()) && IDict.K_BILL_CLASS.K_ELEC_BILL.equals(apply.getBillClass())){
  			payNo=sttleInfoService.checkOrderStatus(SettlementOnlineInfo.BUSI_TYPE_SALE, Tools.couvertLong(sabiIds));
  		}
  		if (IDict.K_BILL_CLASS.K_ENTY_BILL.equals(apply.getBillClass())) {
			inBillStoreService.changeStatusForGathMney(rgctIds);
		}*/
  		
  		
        for(SaleBillInfo saleBill:billList){
            if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(saleBill.getBillClass())){
            	//获取RC登记的票据信息
                RgctBill bill= rcSaleService.getRgctBillById(saleBill.getRgctId());
                bill.getHist().setAcctBranchNo(null);
				bill.getHist().setStorageBranchNo(null);// 记账之后让借票出库查不上来
                
                //调RC方法 转入方同意背书结果登记
                rcSaleService.regAgreeEndorse(bill);
            }
            /*if (SaleCodeConst.SALE_BILL_IFINNER_OUT.equals(saleBillInfo.getIfInner())
					&& SaleCodeConst.SALE_BILL_CLASS_OBJ.equals(saleBillInfo.getBillClass())
					&& SaleCodeConst.SALE_SALE_TYPE_REBUY.equals(applyInfo.getSaleType())) {
				IBuybackApplyService buybackApplyService = BDServiceFactory.getBuybackApplyNewService();
				buybackApplyService.buyBackBillInit(rgctBill, saleBillInfo.getIfInner(), null);
			}*/
            
        }
      //调用记账方法
        IAccountFacadeService acctService = ServiceFactory.getSaleAccountService();
        AccountRequestDTO<SaleApplyInfo,SaleBillInfo> accReq = new AccountRequestDTO<SaleApplyInfo, SaleBillInfo>();
        accReq.setApply(apply);
        accReq.setBillList(billList);
        accReq.setUserLogonInfo(userLogonInfo);
        AccountResponseDTO accResp = acctService.account(accReq);
        
    /* // 额度控制，向转入清单出入数据
 		if (SaleCodeConst.BIDECTDUE_PROD_NO.equals(applyInfo.getProdNo())) {
 			RebuyServicFactory.getRebuyInfoCreator().createBillFromSale(applyInfo, saleList, userInfo.getBrchNo());
 		}
        List<RecoursAviodInfo> list = RecoursAviodServiceFactory.getRecoursAviodService().findBankBybankNo(applyInfo.getAimBrchNo());
     */
        String workDay=DateTimeUtil.getWorkdayString();
        for(SaleBillInfo saleBill:billList){
        	//修改SaleBillInfo信息
        	if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(saleBill.getBillClass())){
        		saleBill.setOperStatus(StatusUtils.handleStatus("SaleAccountController", "submitSaleAccount", null, saleBill.getOperStatus()));
        		if(RcConstants.REGRESS_YES.equals(saleBill.getIsDelayIn())){
        			//如果是回购，将清单信息插入到buybackbillinfo表中，纸票
                	BuybackBillInfoDao buybackBillDao=new BuybackBillInfoDao();
                	BuybackBillInfo buybackBill=new BuybackBillInfo();
                	buybackBill.setBuybackmxId(sequenceService.getPrimaryKeyValue());
                	buybackBill.setBillClass(IDict.K_BILL_CLASS.K_ENTY_BILL);
                	BeanUtils.copyProperties(buybackBill, saleBill);
                	buybackBill.setRegressDt(saleBill.getResaleDueDt());
                	buybackBill.setSaleDt(workDay);
                	buybackBill.setSaleId(saleId);
                	buybackBill.setSaleInterest(saleBill.getRate());
                	buybackBill.setSaleReceiveMoney(saleBill.getReceiveMoney());
                	buybackBill.setCreateTime(DateTimeUtil.getWorkdayString());
                	buybackBill.setOperStatus(BuybackCodeConst.ENTY_FIRST_STATUS);
                	buybackBillDao.addBuybackBillInfo(buybackBill);
                }
        	}else if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(saleBill.getBillClass())){
            	saleBill.setOperStatus(StatusUtils.handleStatus("SaleAccountController", "elecSaleAccount", null, saleBill.getOperStatus()));
        	}
            saleBill.setAccountDate(workDay);
            saleBill.setAcctOperNo(userLogonInfo.getUserNo());
            saleBill.setAcctTellerDt(DateTimeUtil.getWorkdayString());
            saleBill.setAcctTellerTime(DateTimeUtil.get_hhmmss_time());
            if(RcConstants.REGRESS_NO.equals(saleBill.getIsDelayIn())){
            	saleBill.setCurStatus(RcConstants.CUR_STATUS_SALE);
            }else{
            	saleBill.setCurStatus(RcConstants.CUR_STATUS_SALE_WAIT_BACK);
            }
//            saleBill.setSaleSquareId();// 转卖记账流水表ID，用于撤销记账（由AccountResponseDTO得到）
            saleBill.setExSerial(accResp.getForeFlowNo());//记账前台流水号（由AccountResponseDTO得到）
            /* if(!list.isEmpty()){
				if("0".equals(saleBillInfo.getIsDelayIn()) && DateTimeUtil.compartdate(saleBillInfo.getEndBillDate(), list.get(0).getRecoAviodDt())){
					saleBill.setIfRecoursAviod(SaleCodeConst.RECOURS_AVIOD_YES);
				}
			}*/
            saleBillDao.modifySaleBillInfo(saleBill);
          //修改票据来源相关信息
            updateInst(workDay, saleBill);
        }
    }
    /**
     * 修改票据来源相关信息
     * @param acctDate
     * @param saleBillInfo
     * @throws SQLException
     */
    private void updateInst(String acctDate,SaleBillInfo saleBillInfo) throws SQLException {
		if (SaleCodeConst.DISC_BILL.equals(saleBillInfo.getPriorSrc())) {// 票据来源--贴现
			DiscBillInfoDao discBillDao = new DiscBillInfoDao();
			DiscBillInfo discBill = discBillDao.getDiscBillInfo(saleBillInfo.getPriorSrcId());
			double total = MathScaleUtil.add(saleBillInfo.getInterest(), discBill.getTotalIntrstPayment());//总计利息支出
			discBill.setTransId(saleBillInfo.getSaleId());
			discBill.setTransType(RcConstants.SALE);
			discBill.setTotalIntrstPayment(total);
			
			if(RcConstants.REGRESS_NO.equals(saleBillInfo.getIsDelayIn())){//卖断
				discBill.setGathMneyDate(acctDate);
				discBill.setGathMneyType(RcConstants.GATH_TYPE_SALE);
				discBill.setCurStatus(RcConstants.CUR_STATUS_SALE);
			}else{//回购
				discBill.setCurStatus(RcConstants.CUR_STATUS_SALE_WAIT_BACK);
			}
			discBillDao.modifyDiscBillInfo(discBill);
		} else {//票据来源--转入
			RebuyBillInfoDao rebuyBillDao = new RebuyBillInfoDao();
			RebuyBillInfo reBuyBill = rebuyBillDao.getRebuyBillInfo(saleBillInfo.getPriorSrcId());
			double total = MathScaleUtil.add(saleBillInfo.getInterest(),reBuyBill.getTotalIntrstPayment());//总计利息支出
			reBuyBill.setTransId(saleBillInfo.getSaleId());
			reBuyBill.setTransType(RcConstants.SALE);
			reBuyBill.setTotalIntrstPayment(total);
				
			if(RcConstants.REGRESS_NO.equals(saleBillInfo.getIsDelayIn())){//卖断
				reBuyBill.setGathDate(acctDate);
				reBuyBill.setGathType(RcConstants.GATH_TYPE_SALE);
				reBuyBill.setCurStatus(RcConstants.CUR_STATUS_SALE);
			}else{//回购
				reBuyBill.setCurStatus(RcConstants.CUR_STATUS_SALE_WAIT_BACK);
			}
			rebuyBillDao.modifyRebuyBillInfo(reBuyBill);
		}
	}
    /**
     * 转卖审核
     */
    public void submitSaleAudit(String[] ids,String auditStatus,String option) throws Exception{
    	UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
    	String saleId=null;
    	List<SaleBillInfo> bills = saleBillDao.getSaleBillInfoByIds(ids);
    	for(SaleBillInfo saleBill : bills){
    		String after = StatusUtils.handleStatus("SaleAuditController", "audit", auditStatus, saleBill.getOperStatus());
    		saleId = saleBill.getSaleId();
    		saleBill.setOperStatus(after);
    		saleBill.setAuditReason(option);
    		saleBill.setAuditOperNo(userLogonInfo.getUserNo());
    		saleBill.setAuditTellerDt(DateTimeUtil.getWorkdayString());
    		saleBill.setAuditTellerTime(DateTimeUtil.get_hhmmss_time());
    		saleBillDao.modifySaleBillInfo(saleBill);
    	}
    	if(SaleCodeConst.AUDIT_NO.equals(auditStatus) && !StringUtils.isBlank(saleId)){//如果未通过审核，除了修改票据当前状态，还要修改批次状态
    		saleApplyDao.updateSaleApplyStatus(CommonConst.APPLY_STATUS_NEW, saleId);
    	}
    }
	
    
    /**
     * 撤销申请：
     * 
     * 		（1）调用RC撤销转出背书登记方法--纸票
     * 		（2）修改对应清单（SaleBillInfo）状态
     * 		（3）修改批次状态
     *  	（4）删除auditTask
     */
    public void cancelApply(String saleId,String salemxIds) throws BizAppException{
		try{
			IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
			List<SaleBillInfo> saleBillList = saleBillDao.getSaleBillInfoByIds(CommUtils.couvertLong(salemxIds));
			for(SaleBillInfo saleBill:saleBillList){
				if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(saleBill.getBillClass())){// && RcConstants.INNER_NO.equals(saleBill.getIsInner())
					RgctBill rgctBill=rcSaleService.getRgctBillById(saleBill.getRgctId());
					rcSaleService.cancelSaleEndorse(rgctBill);//登记中心撤销背书
					rcSaleService.lock(rgctBill.getInfo().getId());//加锁
				}
				//修改SaleBillInfo操作状态
				String afterStatus = StatusUtils.handleStatus("SaleApplyController", "cancelApply", null,saleBill.getOperStatus());
				saleBill.setOperStatus(afterStatus);
				saleBillDao.modifySaleBillInfo(saleBill);
			}
			//修改批次状态
			SaleApplyInfo applyInfo=saleApplyDao.getSaleApplyInfo(saleId);
			if(!CommonConst.APPLY_STATUS_NEW.equals(applyInfo.getApplyStatus())){
				saleApplyDao.updateSaleApplyStatus(CommonConst.APPLY_STATUS_NEW, saleId);
			}

			AuditTask auditTask = new AuditTask();
            auditTask.setProdNo(applyInfo.getProdNo());
            auditTask.setBatchNo(applyInfo.getBatchNo()); 
            auditTask.setBatchId(saleId);
			ServiceFactory.getAuditTasksService().cancelAuditTask(auditTask);
		}catch (Exception e) {
			throw new BizAppException("撤销转卖申请失败:"+e.getMessage());
		}
		
    }
    /**
     * 撤销审核
     */
    public void cancelAudit(String salemxIds,String saleId) throws BizAppException{
    	try{
//    		boolean isAuditFlag = auditCommonService.isNeedToAudit(apply.getBillStorageBrchno(), apply.getProdNo());
    		List<SaleBillInfo> saleBillList = saleBillDao.getSaleBillInfoByIds(CommUtils.couvertLong(salemxIds));
    		for(SaleBillInfo saleBill:saleBillList){
    			String after = StatusUtils.handleStatus("SaleAuditController", "cancelAudit", null,saleBill.getOperStatus());
    			saleBill.setOperStatus(after);
    			saleBillDao.modifySaleBillInfo(saleBill);
    		}
    	}catch(Exception e){
    		throw new BizAppException("撤销审核失败，"+e.getMessage());
    	}
    }
    /**
     * 撤销记账：
     * 		
     * 		（1）修改SaleBillInfo状态（纸票需要调用RC 转入方撤销签收结果登记）
     * 		（2）调用冲正方法
     */
    public void cancelAccount(String saleId,String ids) throws BizAppException{
         IRcSaleService rcSaleService=RcServiceFactory.getRcSaleService();
         try{
        	 
        	 SaleApplyInfo applyInfo = saleApplyDao.getSaleApplyInfo(saleId);
        	 if(RcConstants.SETTLEMENTMARK_ONE.equals(applyInfo.getIsOnline()) && IDict.K_BILL_CLASS.K_ELEC_BILL.equals(applyInfo.getBillClass())){
        		 throw new BizAppException("线上清算票据不允许撤销记账");
        	 }
        	 List<SaleBillInfo> billList=saleBillDao.getSaleBillInfoByIds(CommUtils.couvertLong(ids));
        	// TODO 判断释放是否手工释放
     		/*BDServiceFactory.getFacCreateFlowService().getReleaseFacCreateFlowList(1, saleBillList);*/
        	 for(SaleBillInfo saleBill:billList){
        		 if(DateTimeUtil.getWorkdayString().equals(saleBill.getAccountDate())){
        			 RgctBill rgctBill = rcSaleService.getRgctBillById(saleBill.getRgctId());
        			 if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(saleBill.getBillClass())){
        				 /*if(RcConstants.INNER_NO.equals(saleBill.getIsInner()) && RcConstants.REGRESS_YES.equals(saleBill.getIsDelayIn())){
        					IBuybackApplyService buybackApplyService = BDServiceFactory.getBuybackApplyNewService();
        					buybackApplyService.cancelCreateBill(rgctBill);
        				 }*/
        				 //RC 转入方撤销签收结果登记
        				 rcSaleService.regCancelSign(rgctBill);
        				 saleBill.setOperStatus(StatusUtils.handleStatus("SaleAccountController", "cancelAccount", null, saleBill.getOperStatus()));
        			 }else if(IDict.K_BILL_CLASS.K_ELEC_BILL.equals(saleBill.getBillClass())){
        				 saleBill.setOperStatus(StatusUtils.handleStatus("SaleAccountController", "cancelElecSaleAccount", null, saleBill.getOperStatus()));
        			 }
        			 //修改SaleBillInfo状态（注：纸票电票状态不一致）
        			 saleBill.setCurStatus(null);
        			 saleBillDao.modifySaleBillInfo(saleBill);
        			 //如果是回购，撤销记账将BuybackBillInfo表中的数据状态改为 删除状态
        			 if(RcConstants.REGRESS_YES.equals(saleBill.getIsDelayIn())){
        				 BuybackBillInfoDao buybackBillDao=new BuybackBillInfoDao();
        				 BuybackBillInfo buybackBill=buybackBillDao.getBuybackBillInfoBySalemxId(saleBill.getSalemxId());
        				 buybackBill.setOperStatus(BuybackCodeConst.DELETE_STATUS);
        				 buybackBillDao.modifyBuybackBillInfo(buybackBill);
        			 }
            	 }else{
            		 throw new BizAppException("只有当天的转卖记账才允许撤销！");
            	 }
        	 }
        	 //冲正
        	 IAccountFacadeService acctService = ServiceFactory.getSaleAccountService();
        	 SaleSearchBean bean = new SaleSearchBean();
        	 bean.setSaleId(saleId);
             SaleApplyInfo apply = saleApplyDao.getSaleApplyInfo(bean);
             UserLoginfo userInfo = ResourceUtil.getSessionLoginfo();
             AccountRequestDTO<SaleApplyInfo,SaleBillInfo> accReq = new AccountRequestDTO<SaleApplyInfo, SaleBillInfo>();
             accReq.setApply(apply);
             accReq.setBillList(billList);
             accReq.setUserLogonInfo(userInfo);
             acctService.reverseAccount(accReq);
             
          /*TODO // 额度控制，删除转入清单
     		if (SaleCodeConst.BIDECTDUE_PROD_NO.equals(applyInfo.getProdNo())) {
     			String saleBillIds = RebuyCommonTool.getSaleBillId(saleBillList);
     			RebuyServicFactory.getApplyService().delUnuseBill(saapId, saleBillIds);
     		}*/
         }catch(Exception e){
        	 throw new BizAppException("撤销记账失败，"+e.getMessage());
         }
    }
    /**
     * 电票转贴现背书
     * 1,对选中的票据进行转贴现背书申请报文013 014 015
	 * 2,工作流往下走,到等待节点等待033的确认报文,然后进入回调操作
     * @throws Exception 
     */
    public void submitSaleEndorse(String saleId,String[] ids,UserLoginfo user) throws Exception{
    	IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
    	Map<String,RgctBill> rgctmap = new HashMap<String,RgctBill>();
    	String rgctids = "";
    	IDB session = DBFactory.getDB();
    	
		List<SaleBillInfo> saleBillList = saleBillDao.getSaleBillInfoByIds(ids);
		SaleApplyInfo saleApply = saleApplyDao.getSaleApplyInfo(saleId);
		if(saleBillList.isEmpty()||saleApply==null){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持此操作");
		}
		for (int i = 0; i < saleBillList.size(); i++) {
			rgctids =rgctids + saleBillList.get(i).getRgctId()+",";
		}
		List<RgctBill> rgctlist = rcSaleService.getRgctBillList(rgctids);
		for (int i = 0; i < rgctlist.size(); i++) {
			rgctmap.put(rgctlist.get(i).getHist().getRgctId(),rgctlist.get(i));
		}
		for(SaleBillInfo saleBill : saleBillList){
			try {
				session.beginTransaction();
				RgctBill rgctBill = rgctmap.get(saleBill.getRgctId());
				this.changeRgctBillHist(rgctBill, saleApply,saleBill, user);// 更新rgctBill历史
				/** 发送报文** */
				//this.conveyChangeStatus(saleBill, null, rgctBill);// 与登记中心交互
				String saleType = saleBill.getIsDelayIn();
				
				RgctBillHist rgctHist = rgctBill.getHist();
				// 改为转出贴现背书登记
				rgctHist.setIsRegress(saleType);
				// 背书之前进行解锁
				rgctHist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
				rgctHist.setSignDt(DateTimeUtil.getWorkdayString());      		
				rgctBill.setHist(rgctHist);
				rcSaleService.saleEndorse(rgctBill);
				/** 保存清单状态* */
				saleBill.setAuditOperNo(user.getUserNo());
				saleBill.setAuditTellerTime(DateTimeUtil.getWorkdayString());
				saleBill.setOperStatus(StatusUtils.handleStatus("SaleEndorseController", "elecSaleEndorse", null, saleBill.getOperStatus()));// 背书提交 // 报文回来改为BD225
				saleBillDao.modifySaleBillInfo(saleBill);
				session.endTransaction();
			} catch (Exception e) {
				try {
					session.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("转卖背书处理失败:"+e.getMessage());
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "转卖背书处理失败:"+e.getMessage());
			}
    	}
	
    }
    private void changeRgctBillHist(RgctBill rgctBill,SaleApplyInfo batch, SaleBillInfo saleBill, UserLoginfo user) throws Exception {
		RgctBillHist hist = rgctBill.getHist();
		//RgctBillInfo info = rgctBill.getInfo();
		hist.setForbidFlag(batch.getForbidFlag());
		hist.setFromName(user.getBranchName());
		hist.setFromBankNo(user.getBrchBankNo());
		hist.setDealMoney(saleBill.getReceiveMoney());
		hist.setIsOnline(batch.getIsOnline());// 是否线上清算
		hist.setBackOpenDt(batch.getBuybackOpenDt());// 赎回开放日
		hist.setBackEndDt(batch.getRebuyDueDt());
		hist.setBackRate(MathScaleUtil.divide(batch.getBuybackRate(), 100));// 赎回利率
		hist.setBackAmount(saleBill.getBuybackMoney());// 赎回金额
		hist.setFromAcctNo("0");// 转出人账号
		hist.setFromBranchNo(user.getBranchNo());
		hist.setToBankNo(batch.getAimBranchNo());
		hist.setToBranchNo(batch.getBranchNo());
		hist.setToName(batch.getCustName());
		hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);// 申请前先解锁
		hist.setChannel(RcConstants.COMES_FROM_SYS);
		Branch branch = ServiceFactory.getBranchService().getBranchByBrchBankNo(user.getBrchBankNo());
		hist.setFromOrgcode(branch.getOrgCode());
		hist.setFromRole(branch.getPartnerType());
		if (StringUtils.isBlank(batch.getInnerAccount())) {
			hist.setToAcctNo("0");
		} else {
			hist.setToAcctNo(batch.getInnerAccount());
		}
		hist.setEndorseDt(DateTimeUtil.getWorkdayString());
		hist.setInterestRate(MathScaleUtil.divide(batch.getRate(), 100));
		hist.setInterest(saleBill.getInterest());
		hist.setIfInner(batch.getIsInner());
		hist.setDealMoney(saleBill.getReceiveMoney());
		hist.setRegressDt(batch.getRebuyDueDt());
		if (RcConstants.REGRESS_YES.equals(batch.getSaleType())) {// 转出类型0卖断，1回购
			hist.setForbidFlag(IConstants.Zero);
		}
		hist.setIsRegress(batch.getSaleType());
		hist.setIsRediscCenter(batch.getIsRedisc());
		rgctBill.setHist(hist);
	}
    /**
     * 撤销转卖电票背书
     * @param saleId
     * @param ids
     * @throws BizAppException 
     */
    public void cancelElecSaleEndorse(String saleId,String[] ids) throws BizAppException{
    	String rgctids = "";
    	Map<String,RgctBill> rgctmap = new HashMap<String, RgctBill>();
    	IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
    	IDB session = DBFactory.getDB();
    	try {
    		List<SaleBillInfo> saleBillList = saleBillDao.getSaleBillInfoByIds(ids);
    		if(saleBillList.isEmpty()){
    			throw new BizAppException(ISysErrorNo.ERR_DBERR,"票据不支持此操作");
    		}
    		for (int i = 0; i < saleBillList.size(); i++) {
				rgctids = rgctids + saleBillList.get(i).getRgctId()+",";
			}
    		List<RgctBill> rgctlist = RcServiceFactory.getRcSaleBackService().getRgctBillList(rgctids.substring(0,rgctids.length()-1));
    		for (int i = 0; i < rgctlist.size(); i++) {
				rgctmap.put(rgctlist.get(i).getHist().getRgctId(), rgctlist.get(i));
			}
    		for(SaleBillInfo saleBill : saleBillList){
    			session.beginTransaction();
    			saleBill.setOperStatus(StatusUtils.handleStatus("SaleEndorseController", "cancelElecSaleEndorse", null, saleBill.getOperStatus()));
    			saleBillDao.modifySaleBillInfo(saleBill);
    			// 调登记中心方法
    			RgctBill rgctBill = rgctmap.get(saleBill.getRgctId());
    			rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
    			rcSaleService.cancelSaleEndorse(rgctBill);// 登记中心撤销背书
    			session.endTransaction();
    		}
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new BizAppException("撤销转卖背书失败，"+e.getMessage());
		}
    }
    
    /**
     * 转卖电票背书确认签收
     * @param saleId
     * @param ids
     * @throws BizAppException 
     */
    public boolean confirmEndorse(String saleId,String[] ids) throws BizAppException{
    	boolean bool = true;
    	IRcSaleService rcSaleService = RcServiceFactory.getRcSaleService();
    	try{
    		List<SaleBillInfo> saleBillList = saleBillDao.getSaleBillInfoByIds(ids);
        	for(SaleBillInfo bill:saleBillList){
        		if (SaleCodeConst.SALE_BILL_CODE_STATUS_CONVEY.equals(bill.getOperStatus())) {//--未响应
    				/*RgctBill rgctBill = rcSaleService.getRgctBillById(bill.getRgctId());
    				rgctBill.getHist().setIsLock(IDict.K_LOCK.K_LOCK_NO);
    				rcSaleService.cancelSaleEndorse(rgctBill);// 登记中心撤销背书
    				bill.setOperStatus(StatusUtils.handleStatus("SaleEndorseController", "cancelElecSaleEndorse", null, bill.getOperStatus()));
    				saleBillDao.modifySaleBillInfo(bill);*/
        			bool=false;
        			throw new BizAppException("对方还没有对信息进行处理，不能进行签收，请等待对方回复！");

    			} else if (SaleCodeConst.SALE_BILL_CODE_STATUS_UNSIGN.equals(bill.getOperStatus())) {//驳回
    				rcSaleService.unLock(bill.getRgctId());
    				bill.setOperStatus(StatusUtils.handleStatus("SaleEndorseController", "elecDelBill", null,bill.getOperStatus()));
    				saleBillDao.modifySaleBillInfo(bill);
    				bool=true;
    			} else {//签收
    				bill.setOperStatus(StatusUtils.handleStatus("SaleEndorseController","confirmSign", null, bill.getOperStatus()));
    				saleBillDao.modifySaleBillInfo(bill);
    				bool=true;
    			}
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    		throw new BizAppException("确认签收失败，"+e.getMessage());
    	}
    	return bool;
    	
    }
    
    /**
     * 撤回：多岗的情况下撤回审核，将票撤销到提交完成状态
     */
    public void cancel(String saleId,String ids){
    	//TODO 
    }
    
	@Override
	public void agreeForRebuy(List<RebuyBillInfo> billList) throws Exception{
		IRebuyService rebuyService = ServiceFactory.getRebuyService();
		String[] salemxIds = rebuyService.getSalemxIds(billList);
		String acctDate = billList.get(0).getAccountDate();
		List<SaleBillInfo> saleBillList = saleBillDao.getSaleBillInfoByIds(salemxIds);
		for (SaleBillInfo bill : saleBillList) {
			updateInst(acctDate,bill);
			String afterStatus = StatusUtils.handleStatusNoCheck("SaleAccountController", "elecSaleAccount", null);//设置为不校验前置状态
			bill.setOperStatus(afterStatus);
			saleBillDao.modifySaleBillInfo(bill);
		}
	}
    
    
}
