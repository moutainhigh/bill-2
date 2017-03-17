/********************************************
 * 文件名称: FmsAgentServiceImpl.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.busiservice.common;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.cache.SysParamCache;
import com.herongtech.console.cache.TradeBankRootCache;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.bean.CustLimitFlow;
import com.herongtech.console.domain.bean.TradeBank;
import com.herongtech.console.domain.bean.TradeBankRoot;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.FacBean;
import com.herongtech.console.domain.common.bean.FacCreateFlow;
import com.herongtech.console.domain.common.bean.SearchFacCreateFlowBean;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.dao.DiscBillInfoDao;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.dao.SaveBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ICustLimitFlowService;
import com.herongtech.console.service.interfaces.IFacCreateFlowService;
import com.herongtech.console.service.interfaces.IFmsAgentService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;

/**
 * 新额度代理类
 * @author zcz
 *
 */
public class FmsAgentServiceImpl implements IFmsAgentService{
	/**
	 * 第三方额度批量占用
	 * @param apply 批次信息
	 * @param source 来源
	 * @param list 清单列表
	 * @param info 用户信息
	 * @return
	 * @throws Exception
	 */
	public Map<String, FacBean> acceptorBankCredit(Object apply,String source,List<?> list, UserLoginfo info) throws Exception {
		Map<String, FacBean> map=new HashMap<String, FacBean>();
		//Map<Long, Object> objMap=new HashMap<Long, Object>();
		if (CommonConst.SWITCH_FLAG_OUTER.equals(SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.LIMIT_USED_SWITCH))) {//暂时不实现对外的
			/*String workTime = DateTimeUtil.getWorkday();
			IFacCreateFlowService service = ServiceFactory.getFacCreateFlowService();
			List<FacCreateFlow> billList=new ArrayList<FacCreateFlow>();
			Map<String, FacCreateFlow> billMap=new HashMap<String, FacCreateFlow>();
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				FacCreateFlow fac = new FacCreateFlow();
				setBillInfo(source, workTime, obj, fac,objMap);
				billList.add(fac);
				billMap.put(fac.getLoanRequisition(), fac);
			}
			IFmsProxyService facService = (IFmsProxyService) AgentFactory.getIntance(Fms_SERVICE);
			String flowNo = getFlowNo(source);
			List facList = null;
			if(CommonConst.FAC_SOURCE_REBUY.equals(source)){//转贴现
				facList=facService.rebuyAcceptorBankCredit(billList, flowNo, info);
			}else{
				facList = facService.acceptorBankCredit(billList, flowNo, info);// 扣承兑行的额度
			}
			if (facList != null && facList.size() > 0) {
				for (int i = 0; i < facList.size(); i++) {
					String isSucces="0";
					DiscAccountBean facBean = (DiscAccountBean) facList.get(i);
					FacCreateFlow fac =billMap.get(facBean.getLoanRequisition());
					if("2120".equals(facBean.getProductId())){
						facBean.setProductName("转贴现额度");
					}else if("4011".equals(facBean.getProductId())){
						facBean.setProductName("贴现额度");
					}else if("3003".equals(facBean.getProductId())){
						facBean.setProductName("票据池额度");
					}
					if("Y".equals(facBean.getIsCyc())){
						facBean.setIsCycName("可循环");
					}else{
						facBean.setIsCycName("不可循环");
					}
					facBean.setBillNo(fac.getBillNo());
					facBean.setRgctId(fac.getRgctId());
					facBean.setBillId(String.valueOf(fac.getBillId()));
//					facBean.setOwnerPartyName(facBean.getOwnerPartyName());
					
					fac.setIsCyc(facBean.getIsCyc());
					fac.setOwnerPartyId(facBean.getOwnerPartyId());
					fac.setProductId(facBean.getProductId());
					Object obj=objMap.get(fac.getBillId());
					
					if (facBean.isType()) {
						fac.setStatus(CommonConst.FAC_STATUS_ZERO);
						isSucces="1";
					}else{//失败也需要保存
						fac.setStatus(CommonConst.FAC_STATUS_THREE);
					}
					updateEntity(isSucces, facBean, obj);//更新业务清单
					fac.setCreateTime(DateTimeUtil.getWorkday());
					fac.setBranchNo(info.getBrchNo());
					service.addFacCreateFlow(fac);// 保存额度扣减流水，便于释放
					map.put(fac.getRgctId(), facBean);
					
				}
			}*/
		}else if (CommonConst.SWITCH_FLAG_INNER.equals(SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.LIMIT_USED_SWITCH))) {//实现对内部的额度减扣
			this.fac(list,map,source,info);
		}else if (CommonConst.SWITCH_FLAG_NO.equals(SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.LIMIT_USED_SWITCH))) {
			//不进行额度减扣工作
		}
		return map;
	}
	
	private void fac(List<?> list, Map<String, FacBean> map,String source,UserLoginfo info)throws Exception{
		String workTime = DateTimeUtil.getWorkday();
		IFacCreateFlowService service = ServiceFactory.getFacCreateFlowService();
		ICustLimitFlowService custService = ServiceFactory.getCustLimitFlowService();
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			FacBean discAccountBean = new FacBean();
			FacCreateFlow fac = new FacCreateFlow();
			CustLimitFlow clFlow = new CustLimitFlow();
			fac.setId(ServiceFactory.getSequenceService().getFacCreateFlowId());
			fac.setCreateDate(DateTimeUtil.getWorkday());
			fac.setCreateTime(DateUtil.getTime());
			fac.setStatus(CommonConst.FAC_STATUS_ONE);// 暂时假状态
			fac.setSource(source);
			fac.setBranchNo(info.getBrchNo());
//			fac.setFacType(FacCode.FAC_TYPE_ONE);
			if (obj instanceof DiscBillInfo) {
				DiscBillInfo billInfo = (DiscBillInfo) obj;
				discAccountBean.setBillId(billInfo.getDiscmxId());
				discAccountBean.setRgctId(billInfo.getRgctId());
				discAccountBean.setBillNo(billInfo.getBillNo());
				//设置facCreateFlow（额度扣减流水）相关值
				this.setDiscFacFlow((DiscBillInfo)obj, fac, workTime);
				//设置custLimitFlow（扣减对象额度操作流水）相关值
				clFlow.setOperCustNo(this.bankInfo(billInfo.getAcceptorBankNo()));
				clFlow.setOperCustName(billInfo.getAcceptorBankName());
				clFlow.setOperMoney(billInfo.getBillMoney());
				clFlow.setOperClass(CommonConst.CUST_LIMIT_CLASS_DISC);
				clFlow.setOperMxId(billInfo.getDiscmxId());
			}
			if (obj instanceof SaveBillInfo) {
				SaveBillInfo billInfo = (SaveBillInfo) obj;
				discAccountBean.setBillId(billInfo.getSavemxId());
				discAccountBean.setBillNo(billInfo.getBillNo());
				discAccountBean.setRgctId(billInfo.getRgctId());
				//设置facCreateFlow（额度扣减流水）相关值
				this.setSaveFacFlow((SaveBillInfo)obj, fac, workTime);
				//设置custLimitFlow（扣减对象额度操作流水）相关值
				clFlow.setOperCustNo(this.bankInfo(billInfo.getAcceptorBankNo()));
				clFlow.setOperCustName(billInfo.getAcceptorBankName());
				clFlow.setOperMoney(billInfo.getBillMoney());
				clFlow.setOperClass(CommonConst.CUST_LIMIT_CLASS_SAVE);
				clFlow.setOperMxId(billInfo.getSavemxId());
			}
			if (obj instanceof RebuyBillInfo) {
				RebuyBillInfo billInfo = (RebuyBillInfo) obj;
				discAccountBean.setBillId(billInfo.getRebuymxId());
				discAccountBean.setBillNo(billInfo.getBillNo());
				discAccountBean.setRgctId(billInfo.getRgctId());
				//设置facCreateFlow（额度扣减流水）相关值
				this.setRebuyFacFlow(billInfo, fac, workTime);
				//设置custLimitFlow（扣减对象额度操作流水）相关值
				clFlow.setOperCustNo(this.bankInfo(billInfo.getAcceptorBankNo()));
				clFlow.setOperCustName(billInfo.getAcceptorBankName());
				clFlow.setOperMoney(billInfo.getBillMoney());
				clFlow.setOperClass(CommonConst.CUST_LIMIT_CLASS_TRANSFER);
				clFlow.setOperMxId(billInfo.getRebuymxId());
			}
			clFlow.setFlowNo(fac.getLoanRequisition());
			//插入额度扣减流水信息
			service.addFacCreateFlow(fac);
			//占用扣减对象额度，并插入扣减对象额度操作流水信息
			custService.usedCustLimitFlow(clFlow);
			discAccountBean.setType(true);
			//更新清单信息
			this.updateEntity(discAccountBean, obj);
			map.put(discAccountBean.getRgctId(), discAccountBean);
		}
	}
	private void updateEntity(FacBean facBean,Object obj) throws Exception {
		if (obj instanceof DiscBillInfo) {
			DiscBillInfo bill=(DiscBillInfo) obj;
			bill.setIsAmount(facBean.isType()? IConstants.YES:IConstants.NO);
			bill.setIsCyc(facBean.getIsCyc());
			bill.setProductId(facBean.getProductName());
			bill.setOwnerPartyId(facBean.getOwnerPartyName());
//			bill.setCommMsg(facBean.getMessage());
			new DiscBillInfoDao().modifyDiscBillInfo(bill);
		}else if(obj instanceof RebuyBillInfo){
			RebuyBillInfo bill=(RebuyBillInfo) obj;
			bill.setFacOperType(CommonConst.FAC_OPER_OCCUPY);
			bill.setIsAmount(facBean.isType()? IConstants.YES:IConstants.NO);
			bill.setLimitType(facBean.getFacType());
//			bill.setProductId(facBean.getProductId());
			bill.setProductName(facBean.getProductName());
			bill.setOwnerPartyId(facBean.getOwnerPartyId());
			bill.setOwnerPartyName(facBean.getOwnerPartyName());
			bill.setIsCyc(facBean.getIsCyc());
			bill.setIsCycName(facBean.getIsCycName());
			bill.setFacMsg(facBean.getMessage());
			new RebuyBillInfoDao().modifyRebuyBillInfo(bill);
		}else if(obj instanceof SaveBillInfo){
			SaveBillInfo bill=(SaveBillInfo) obj;
			bill.setIsAmount(facBean.isType()? IConstants.YES:IConstants.NO);
			bill.setIsCyc(facBean.getIsCyc());
			bill.setOwnerPartyName(facBean.getOwnerPartyName());
			bill.setEduType(facBean.getProductName());
			new SaveBillInfoDao().modifySaveBillInfo(bill);
		}
	}
	private void setRebuyFacFlow(RebuyBillInfo billInfo,FacCreateFlow fac,String workTime) throws BizAppException{
		fac.setBillId(billInfo.getRebuymxId());
		fac.setBillNo(billInfo.getBillNo());
		fac.setBillClass(billInfo.getBillClass());
		fac.setBillType(billInfo.getBillType());
		fac.setBillMoney(billInfo.getBillMoney());
		fac.setOwnerPartyId(this.bankInfo(billInfo.getAcceptorBankNo()));
		if(StringUtils.isBlank(billInfo.getAcceptorBankNo())) {
			throw new BizAppException("承兑人开户行行号为空！");
		}
		fac.setRemitterBankNo(bankInfo(billInfo.getAcceptorBankNo()));
		//TODO 承兑行若为被代理行或间接的财务公司的情况待优化
		/*if(CommonConst.BILL_CLASS_ENTITY.equals(billInfo.getBillClass()) && "1".equals(billInfo.getBillType())){
			if(StringUtils.isBlank(billInfo.getAcceptorBankNo())) {
				throw new BizAppException("承兑人开户行行号为空！");
			}
			fac.setRemitterBankNo(bankInfo(billInfo.getAcceptorBankNo()));
		}
		//承兑行若为被代理行或间接的财务公司取新增的额度扣减对象
		if(CommonConst.BILL_CLASS_ELEC.equals(billInfo.getBillClass()) && "1".equals(billInfo.getBillType())){
			if(StringUtils.isBlank(billInfo.getLimitReduceRow())) {
				throw new BizAppException("承兑人开户行行号为空！");
			}
			fac.setRemitterBankNo(bankInfo(billInfo.getLimitReduceRow()));
		}*/
		
		if(CommonConst.CUST_BANK.equals(billInfo.getCustType())){
			fac.setCustNo(bankInfo(billInfo.getCustBankNo()));
		}else if(CommonConst.CUST_OTHER.equals(billInfo.getCustType())){
			fac.setCustNo(billInfo.getCustNo());
		}
		/*if(billInfo.getCustNo()!=null && !"".equals(billInfo.getCustNo())){
			fac.setCustNo(billInfo.getCustNo());
		}else {
			fac.setCustNo(bankInfo(billInfo.getCustBankNo()));
			
		}*/
		if(CommonConst.BILL_CLASS_ENTITY.equals(billInfo.getBillClass())){
			fac.setLoanRequisition(billInfo.getBillNo()+billInfo.getRebuymxId()+workTime);//
		}else{
			fac.setLoanRequisition(billInfo.getBillNo()+workTime);//
		}
		fac.setRgctId(billInfo.getRgctId());
		
		fac.setDueDt(billInfo.getDueDt());
		fac.setAcceptor(billInfo.getAcceptor());
		fac.setProductId(CommonConst.CUST_LIMIT_CLASS_TRANSFER);
	}
	private void setSaveFacFlow(SaveBillInfo billInfo,FacCreateFlow fac,String workTime) throws BizAppException{
		fac.setBillId(billInfo.getSavemxId());
		fac.setBillNo(billInfo.getBillNo());
		fac.setBillClass(billInfo.getBillClass());
		fac.setBillType(billInfo.getBillType());
		fac.setBillMoney(billInfo.getBillMoney());
		fac.setCustNo(billInfo.getCustNo());
		fac.setOwnerPartyId(this.bankInfo(billInfo.getAcceptorBankNo()));
		//TODO 承兑行若为被代理行或间接的财务公司的情况待优化
		if(StringUtils.isBlank(billInfo.getAcceptorBankNo())) {
			throw new BizAppException("承兑人开户行行号为空！");
		}
		fac.setRemitterBankNo(bankInfo(billInfo.getAcceptorBankNo()));
		fac.setLoanRequisition(billInfo.getBillNo()+billInfo.getSavemxId()+workTime);//
		/*if(CommonConst.BILL_CLASS_ENTITY.equals(billInfo.getBillClass())){
			if(StringUtils.isBlank(billInfo.getAcceptorBankNo())) {
				throw new BizAppException("承兑人开户行行号为空！");
			}
			fac.setRemitterBankNo(bankInfo(billInfo.getAcceptorBankNo()));
			fac.setLoanRequisition(billInfo.getBillNo()+billInfo.getSavemxId()+workTime);//
		}else{
			if(StringUtils.isBlank(billInfo.getLimitReduceRow())) {
				throw new BizAppException("承兑人开户行行号为空！");
			}
			fac.setRemitterBankNo(bankInfo(billInfo.getLimitReduceRow()));
			fac.setLoanRequisition(billInfo.getBillNo()+workTime);//
		}*/
		fac.setRgctId(billInfo.getRgctId());
		fac.setDueDt(billInfo.getDueDt());
		fac.setAcceptor(billInfo.getAcceptor());
		fac.setProductId("3");

	}
	
	private void setDiscFacFlow(DiscBillInfo billInfo,FacCreateFlow fac,String workTime) throws BizAppException{
		fac.setBillId(billInfo.getDiscmxId());
		fac.setBillNo(billInfo.getBillNo());
		fac.setBillClass(billInfo.getBillClass());
		fac.setBillType(billInfo.getBillType());
		fac.setBillMoney(billInfo.getBillMoney());
		fac.setCustNo(billInfo.getCustNo());
		fac.setOwnerPartyId(this.bankInfo(billInfo.getAcceptorBankNo()));
		if(CommonConst.BILL_CLASS_ENTITY.equals(billInfo.getBillClass())){
			fac.setLoanRequisition(billInfo.getBillNo()+billInfo.getDiscmxId()+workTime);//
		}else{
			fac.setLoanRequisition(billInfo.getBillNo()+workTime);//
		}
		fac.setRgctId(billInfo.getRgctId());
		//TODO 承兑行若为被代理行或间接的财务公司的情况待优化
		if(StringUtils.isBlank(billInfo.getAcceptorBankNo())) {
			throw new BizAppException("承兑人开户行行号为空！");
		}
		fac.setRemitterBankNo(bankInfo(billInfo.getAcceptorBankNo()));
		/*if(CommonConst.BILL_CLASS_ENTITY.equals(billInfo.getBillClass())){
			if(StringUtils.isBlank(billInfo.getAcceptorBankNo())) {
				throw new BizAppException("承兑人开户行行号为空！");
			}
			fac.setRemitterBankNo(bankInfo(billInfo.getAcceptorBankNo()));
		}else{
			if(StringUtils.isBlank(billInfo.getLimitReduceRow())) {
				throw new BizAppException("承兑人开户行行号为空！");
			}
			fac.setRemitterBankNo(bankInfo(billInfo.getLimitReduceRow()));
		}*/
		fac.setDueDt(billInfo.getDueDt());
		fac.setAcceptor(billInfo.getAcceptor());
		fac.setProductId(CommonConst.CUST_LIMIT_CLASS_DISC);
	}
	
	/**
	 * 任意行号查对应总行行号
	 * @param bankNo
	 * @return
	 * @throws BizAppException 
	 */
	private String bankInfo(String bankNo) throws BizAppException{
		if(!StringUtils.isBlank(bankNo)){
			// 查大行信息表
			TradeBankRoot tradeBankRoot = TradeBankRootCache.getInstance().getValue(StringUtils.substring(bankNo, 0, 3));
			if(tradeBankRoot==null){
				//查询行号总分关系表
				TradeBank tradeBank = ServiceFactory.getTradeBankService().getTradeBank(bankNo);
				if(tradeBank!=null){
					return tradeBank.getParentBankno();
				}else{
					return bankNo;
				}
			}else{
				return tradeBankRoot.getBankNo();
			}
		}else{
			return null;
		}
	}
	/**
	 * 第三方额度批量释放
	 * @param list
	 * @param info
	 * @param source
	 * @param billList
	 * @param facType1
	 */
	public Map<String, FacBean> facReleaseByBillBatch(Object apply,List<?> list, UserLoginfo info) throws Exception{
		Map<String, FacBean> map=new HashMap<String, FacBean>();
		//Map<String, FacCreateFlow> facMap=new HashMap<String, FacCreateFlow>();
		//Map<Long, Object> objMap=new HashMap<Long, Object>();
		if (CommonConst.SWITCH_FLAG_OUTER.equals(SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.LIMIT_USED_SWITCH))) {/*
			String billNo = null;
			Long billId=null;
			String source=null;
			IFacCreateFlowService service = FacCreateFlowFactory.getFacCreateFlowSerivce();
			List<DiscAccountBean> discList = new ArrayList<DiscAccountBean>();
			for (int j = 0; j < list.size(); j++) {
				Object obj = list.get(j);
				if (obj instanceof DiscBillInfo) {
					DiscBillInfo billInfo = (DiscBillInfo) obj;
					billNo = billInfo.getBillNo();
					billId=billInfo.getDibiId();
					source=FacCode.FAC_SOURCE_DISC;
					searchFac(billNo, billId, source, service, discList,facMap);
					objMap.put(billId, billInfo);
				}else if (obj instanceof SaveBillInfo) {
					SaveBillInfo billInfo = (SaveBillInfo) obj;
					billNo = billInfo.getBillNo();
					billId=billInfo.getSavebillId();
					if(SavebillConstantS.impawnBillProd_no.equals(billInfo.getProdNo())){
						source=FacCode.FAC_SOURCE_SAVE;
					}else{
						source=FacCode.FAC_SOURCE_SAVE_TWO;
					}
					searchFac(billNo, billId, source, service, discList,facMap);
					objMap.put(billId, billInfo);
				}else if (obj instanceof RebuyBillInfo) {
					RebuyBillInfo billInfo = (RebuyBillInfo) obj;
					billNo = billInfo.getBillNo();
					billId=billInfo.getRebiId();
					source=FacCode.FAC_SOURCE_REBUY;
					searchFac(billNo, billId, source, service, discList,facMap);
					objMap.put(billId, billInfo);
				}else if(obj instanceof FacCreateFlow) {
					FacCreateFlow facCreateFlow = (FacCreateFlow) obj;
					LogTool.log_dayend(facCreateFlow.getBillNo());
					BigDecimal sum = new BigDecimal(facCreateFlow.getBillMoney()).setScale(2,BigDecimal.ROUND_HALF_UP);
					DiscAccountBean discAccountBean = new DiscAccountBean();
					discAccountBean.setLoanRequisition(facCreateFlow.getLoanRequisition());
					discAccountBean.setFaceAmount(sum);
					discList.add(discAccountBean);
					facMap.put(facCreateFlow.getLoanRequisition(), facCreateFlow);
				}
				
			}
			IFmsProxyService facService = (IFmsProxyService) AgentFactory.getIntance(Fms_SERVICE);
			String flowNo = flowGenerator.getFlowNo();
			List retList = facService.facRelease(discList, flowNo, info);// 扣承兑行的额度
			for(int i=0;i<retList.size();i++){
				DiscAccountBean bean=(DiscAccountBean) retList.get(i);
				FacCreateFlow facCreateFlow = updateFacInfo(facMap, objMap,
						service, bean);
				
				map.put(facCreateFlow.getRgctId(), bean);
			}*/
		} else if (CommonConst.SWITCH_FLAG_INNER.equals(SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.LIMIT_USED_SWITCH))) {
			String billNo = null;
			String billId=null;
			String source=null;
			FacBean bean = new FacBean();
			IFacCreateFlowService service = ServiceFactory.getFacCreateFlowService();
			ICustLimitFlowService custService = ServiceFactory.getCustLimitFlowService();
			FacCreateFlow facCreateFlow = null;
			for (int j = 0; j < list.size(); j++) {
				Object obj = list.get(j);
				CustLimitFlow clFlow = new CustLimitFlow();
					
				if (obj instanceof DiscBillInfo) {//贴现【撤销申请、审核拒绝】额度释放
					DiscBillInfo billInfo = (DiscBillInfo) obj;
					billNo = billInfo.getBillNo();
					billId=billInfo.getDiscmxId();
					source=CommonConst.FAC_SOURCE_DISC;
					facCreateFlow = searchFac(billNo, billId, source, service, bean);
					//设置custLimitFlow（扣减对象额度操作流水）相关值
					clFlow.setOperCustNo(this.bankInfo(billInfo.getAcceptorBankNo()));
					clFlow.setOperCustName(billInfo.getAcceptorBankName());
					clFlow.setOperMoney(billInfo.getBillMoney());
					clFlow.setOperClass(CommonConst.CUST_LIMIT_CLASS_DISC);
					clFlow.setOperMxId(billInfo.getDiscmxId());
				}else if (obj instanceof SaveBillInfo) {//质押【撤销申请、审核拒绝】额度释放
					SaveBillInfo billInfo = (SaveBillInfo) obj;
					billNo = billInfo.getBillNo();
					billId=billInfo.getSavemxId();
					source=CommonConst.FAC_SOURCE_SAVE;
					facCreateFlow = searchFac(billNo, billId, source, service, bean);
					//设置custLimitFlow（扣减对象额度操作流水）相关值
					clFlow.setOperCustNo(this.bankInfo(billInfo.getAcceptorBankNo()));
					clFlow.setOperCustName(billInfo.getAcceptorBankName());
					clFlow.setOperMoney(billInfo.getBillMoney());
					clFlow.setOperClass(CommonConst.CUST_LIMIT_CLASS_SAVE);
					clFlow.setOperMxId(billInfo.getSavemxId());
				}else if (obj instanceof RebuyBillInfo) {//转贴现转入【撤销申请、审核拒绝】额度释放
					RebuyBillInfo billInfo = (RebuyBillInfo) obj;
					billNo = billInfo.getBillNo();
					billId=billInfo.getRebuymxId();
					source=CommonConst.FAC_SOURCE_REBUY;
					facCreateFlow = searchFac(billNo, billId, source, service, bean);
					//设置custLimitFlow（扣减对象额度操作流水）相关值
					clFlow.setOperCustNo(this.bankInfo(billInfo.getAcceptorBankNo()));
					clFlow.setOperCustName(billInfo.getAcceptorBankName());
					clFlow.setOperMoney(billInfo.getBillMoney());
					clFlow.setOperClass(CommonConst.CUST_LIMIT_CLASS_TRANSFER);
					clFlow.setOperMxId(billInfo.getRebuymxId());
				}else if(obj instanceof FacCreateFlow) {//日终批量额度释放
					facCreateFlow = (FacCreateFlow) obj;
					BigDecimal sum = new BigDecimal(facCreateFlow.getBillMoney()).setScale(2,BigDecimal.ROUND_HALF_UP);
					bean.setLoanRequisition(facCreateFlow.getLoanRequisition());
					bean.setFaceAmount(sum);
					//设置custLimitFlow（扣减对象额度操作流水）相关值
					clFlow.setOperCustNo(facCreateFlow.getOwnerPartyId());
					clFlow.setOperCustName(facCreateFlow.getAcceptor());
					clFlow.setOperMoney(facCreateFlow.getBillMoney());
				}
				bean.setType(true);
				clFlow.setFlowNo(facCreateFlow.getLoanRequisition());
				//释放扣减对象额度，并插入扣减对象额度操作流水
				custService.releaseCustLimitFlow(clFlow);
				FacCreateFlow facAfter = updateFacInfo(facCreateFlow,service, bean);
				//更新清单信息
				this.updateRePleaseEntity(bean, obj);
				map.put(facAfter.getRgctId(), bean);
			}
		} else if (CommonConst.SWITCH_FLAG_NO.equals(SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.LIMIT_USED_SWITCH))) {
			//不进行额度减扣
		}
		return map;
	}
	private void updateRePleaseEntity(FacBean bean, Object obj) throws Exception {
		if (bean.isType()) {
			if (obj instanceof DiscBillInfo) {
				DiscBillInfo bill=(DiscBillInfo) obj;
				//bill.setOperStatus(DiscCodeConst.DISC_BILL_CODE_STATUS_110);
				bill.setIsAmount(IConstants.NO);
				bill.setIsCyc("");
				bill.setProductId("");
				bill.setOwnerPartyId("");
				new DiscBillInfoDao().modifyDiscBillInfo(bill);
			}else if(obj instanceof RebuyBillInfo){
				RebuyBillInfo bill=(RebuyBillInfo) obj;
				bill.setFacOperType(CommonConst.FAC_OPER_RELEASE);
				bill.setLimitType(bean.getFacType());
//				bill.setProductId(bean.getProductId());
				bill.setProductName(bean.getProductName());
				bill.setOwnerPartyId(bean.getOwnerPartyId());
				bill.setOwnerPartyName(bean.getOwnerPartyName());
				bill.setIsCyc(bean.getIsCyc());
				bill.setIsCycName(bean.getIsCycName());
				bill.setIsAmount(bean.isType()?IConstants.YES:IConstants.NO);
				bill.setFacMsg(bean.getMessage());
				new RebuyBillInfoDao().modifyRebuyBillInfo(bill);
			}else if(obj instanceof SaveBillInfo){
				SaveBillInfo bill=(SaveBillInfo) obj;
				bill.setIsAmount(bean.isType()?IConstants.YES:IConstants.NO);
				bill.setIsCyc(bean.getIsCyc());
				bill.setOwnerPartyName(bean.getOwnerPartyName());
				bill.setEduType(bean.getProductName());
				new SaveBillInfoDao().modifySaveBillInfo(bill);
			}
		}
		
	}
	
	/**
	 * 功能描述：更新额度流水信息
	 * @param facMap
	 * @param objMap
	 * @param service
	 * @param bean
	 * @return
	 * @throws BizAppException 
	 * @throws SQLException 
	 */
	public FacCreateFlow updateFacInfo(FacCreateFlow facCreateFlow,IFacCreateFlowService service,FacBean bean) throws BizAppException, SQLException {
		bean.setBillNo(facCreateFlow.getBillNo());
		bean.setBillId(facCreateFlow.getBillId());
		bean.setRgctId(facCreateFlow.getRgctId());
		if (bean.isType()) {
			facCreateFlow.setStatus(CommonConst.FAC_STATUS_ZERO);// 额度释放状态
			facCreateFlow.setReleaseDate(DateTimeUtil.getWorkday());//释放时间
			facCreateFlow.setReleaseTime(DateTimeUtil.get_hhmmss_time());
			service.updateFacCreateFlow(facCreateFlow);
		} 
		return facCreateFlow;
	}

	private FacCreateFlow searchFac(String billNo, String billId, String source,
			IFacCreateFlowService service, FacBean discAccountBean) throws SQLException {
		FacCreateFlow facCreateFlow = new FacCreateFlow();
		SearchFacCreateFlowBean searchBean=new SearchFacCreateFlowBean();
		searchBean.setSource(source);
		searchBean.setStatus(CommonConst.FAC_STATUS_ONE);
		searchBean.setBillId(billId);
		List<FacCreateFlow> facList = service.getFacCreateFlowByCondition(searchBean);
		if (facList != null && facList.size() > 0) {
			facCreateFlow = facList.get(0);
			BigDecimal sum = new BigDecimal(facCreateFlow.getBillMoney()).setScale(2,BigDecimal.ROUND_HALF_UP);
			discAccountBean.setLoanRequisition(facCreateFlow.getLoanRequisition());
			discAccountBean.setFaceAmount(sum);
			//facMap.put(facCreateFlow.getLoanRequisition(), facCreateFlow);
		}
		return facCreateFlow;
	}
	
}
