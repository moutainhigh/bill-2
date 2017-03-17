/********************************************
 * 文件名称: TailAfterSearchService.java
 * 名称:    基础技术平台V2.0
 * 模块名称: 票据追踪查询类
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 版本:    2.0.0.1
 * 开发人员: wzc
 * 开发时间: 2016-12-15 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.tailAfterSearch;
import java.sql.SQLException;
import java.util.List;

import com.herongtech.appconfig.SysConfigUtil;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptColltnReg;
import com.herongtech.console.domain.acpt.dao.AcptApplyInfoDao;
import com.herongtech.console.domain.acpt.dao.AcptBillInfoDao;
import com.herongtech.console.domain.acpt.dao.AcptColltnRegDao;
import com.herongtech.console.domain.buyback.bean.BuybackApplyInfo;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.dao.BuybackApplyInfoDao;
import com.herongtech.console.domain.buyback.dao.BuybackBillInfoDao;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.dao.DiscApplyInfoDao;
import com.herongtech.console.domain.disc.dao.DiscBillInfoDao;
import com.herongtech.console.domain.get.bean.GetApplyInfo;
import com.herongtech.console.domain.get.bean.GetBillInfo;
import com.herongtech.console.domain.get.dao.GetApplyInfoDao;
import com.herongtech.console.domain.get.dao.GetBillInfoDao;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.dao.RebuyApplyInfoDao;
import com.herongtech.console.domain.rebuy.dao.RebuyBillInfoDao;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.dao.SaleApplyInfoDao;
import com.herongtech.console.domain.sale.dao.SaleBillInfoDao;
import com.herongtech.console.domain.saleback.bean.SalebackApplyInfo;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.domain.saleback.dao.SalebackApplyInfoDao;
import com.herongtech.console.domain.saleback.dao.SalebackBillInfoDao;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.dao.SaveApplyInfoDao;
import com.herongtech.console.domain.save.dao.SaveBillInfoDao;
import com.herongtech.console.domain.subcoll.bean.SubcollApplyInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.domain.subcoll.dao.SubcollApplyInfoDao;
import com.herongtech.console.domain.subcoll.dao.SubcollBillInfoDao;
import com.herongtech.console.service.common.machinestatus.StatusDict;
import com.herongtech.console.service.common.product.ProductService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;


/**
 * 票据跟踪查询Service
 *
 */
public class TailAfterSearchService implements ITailAfterSearchService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 功能描述：跟踪查询
	 * @param quary
	 * @return
	 * @throws BizAppException 
	 */
	public RgctBill getTailAfterServiceCondition(String billNo) throws BizAppException{
	    return RcServiceFactory.getRcBaseService().getRgctBillByBillNo(billNo);	
	}
	/**
	 * 获取票据所属模块和当前操作状态
	 * 
	 * @return
	 */
	public String[] getModelCodeAndOperStatus(RgctBill bill) throws Exception {
		RgctBillInfo billInfo = bill.getInfo();
//		RgctBillHist billHist = bill.getHist();
//		String fromBankNo = billHist.getFromBankNo();
//		String toBankNo = billHist.getToBankNo();
		if(billInfo==null){
			throw new BizAppException("在本系统内查询不到该票据信息！");
		}
		String curStatus = billInfo.getCurStatus();//当前状态
		String rgctId = billInfo.getId();//登记中心ID
		String billNo = billInfo.getBillNo();//票号
		String operStatus = "";
		String modelCode = "";
		String batchNo="";//往前台传的批次号
		String createDt="";//交易日期
		String prodNo="";//产品编码名称
		String custNo="";//客户号
		if(StringUtils.isBlank(curStatus)){
			throw new BizAppException("票据【"+billNo+"】的当前状态值为空！");
		}
//		boolean fromIsInner = MsgUtil.isSelfBank(fromBankNo);
//		boolean toIsInner = MsgUtil.isSelfBank(toBankNo);
		String curStatusStr = curStatus.split("_")[0];
		
		if(IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_B.equals(curStatusStr)){//提示承兑
			modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_ACPT;
			AcptBillInfo acptBill = new AcptBillInfoDao().getAcptBillInfoByRgctId(rgctId);
			AcptApplyInfo  acptApplyInfo = new AcptApplyInfoDao().getAcptApplyInfo(acptBill.getAcptId());
			batchNo = 	acptApplyInfo.getBatchNo();
			createDt=  acptBill.getAcptDt();
			operStatus = acptBill.getStatus();
			prodNo=acptApplyInfo.getProdNo();
			custNo=acptBill.getRemitterCustNo();
		}else if(IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_F.equals(curStatusStr) || IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_G.equals(curStatusStr)){//贴现
			modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_DISC;
			DiscBillInfo discBill = new DiscBillInfoDao().getDiscBillInfoByRgctId(rgctId);
			DiscApplyInfo applyInfo=new DiscApplyInfoDao().getDiscApplyInfo(discBill.getDiscId(), null);
			batchNo=applyInfo.getBatchNo();
			createDt=applyInfo.getDiscDt();
			prodNo=applyInfo.getProdNo();	
			custNo=discBill.getCustNo();
			operStatus = discBill.getOperStatus();
		}else if(IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_O.equals(curStatusStr)){//质押
			modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_SAVE;
			SaveBillInfo saveBill = new SaveBillInfoDao().getSaveBillInfoByRgctId(rgctId);
			SaveApplyInfo applyInfo =new SaveApplyInfoDao().getSaveApplyInf(saveBill.getSaveId());
			batchNo = applyInfo.getBatchNo();
			createDt = saveBill.getCreateDt();
			operStatus = saveBill.getOperStatus();
			custNo=saveBill.getCustNo();
			prodNo=applyInfo.getProdNo();
		}else if(IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_I.equals(curStatusStr) || IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_J.equals(curStatusStr)){//转贴现（买断式、回购式）
			/*if(fromIsInner && toIsInner){//系统内
				//转卖-------
				modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_SALE;
				SaleBillInfo saleBill = new SaleBillInfoDao().getSaleBillInfoByRgcId(rgctId);
				if(saleBill!=null){
					operStatus = saleBill.getOperStatus();
				}
				if(!StringUtils.isBlank(operStatus)&&!this.isDeleteStatus(operStatus)){
					break out;
				}
				//转入-------
				modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_REBUY;
				RebuyBillInfo rebuyBill = new RebuyBillInfoDao().getRebuyBillInfoByRgctId(rgctId);
				if(rebuyBill!=null){
					operStatus = rebuyBill.getOperStatus();
				}
			}else if(fromIsInner && !toIsInner){//同业转贴现转出
				modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_SALE;
				SaleBillInfo saleBill = new SaleBillInfoDao().getSaleBillInfoByRgcId(rgctId);
				operStatus = saleBill.getOperStatus();
			}else if(!fromIsInner && toIsInner){//同业转贴现转入
*/				modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_REBUY;
				RebuyBillInfo rebuyBill = new RebuyBillInfoDao().getRebuyBillInfoByRgctId(rgctId);
				RebuyApplyInfo applyInfo=new RebuyApplyInfoDao().getRebuyApplyInfo(rebuyBill.getRebuyId());
				batchNo = applyInfo.getBatchNo();
				createDt = applyInfo.getRebuyDt();
				prodNo=applyInfo.getProdNo();
				custNo=rebuyBill.getCustNo();
				if(rebuyBill!=null){
					operStatus = rebuyBill.getOperStatus();
				}
			/*}*/
		}/*else if(IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_L.equals(curStatusStr) || IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_M.equals(curStatusStr)){//再贴现
			modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_SALE;
			SaleBillInfo saleBill = new SaleBillInfoDao().getSaleBillInfoByRgcId(rgctId);
			operStatus = saleBill.getOperStatus();
		}else if(IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_P.equals(curStatusStr)){//解质押
			modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_GET;
			GetBillInfo getBill = new GetBillInfoDao().getGetBillInfoByRgctId(rgctId);
			operStatus = getBill.getOperStatus();
		}else if(IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_R.equals(curStatusStr) || IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_S.equals(curStatusStr)){//托收
			SubcollBillInfo subcollBillInfo = new SubcollBillInfoDao().getSubcollBillForRgctId(rgctId);	
			SubcollApplyInfo  applyInfo = new SubcollApplyInfoDao().getSubcollApplyInfobyid(subcollBillInfo.getBillNo());
			batchNo=applyInfo.getBatchId();
			createDt= applyInfo.getCollDt();
			prodNo=applyInfo.getProdNo();
		}else if(IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_K.equals(curStatusStr) || IDict.K_CUR_STATUS_FLAG.K_CUR_STATUS_FLAG_N.equals(curStatusStr)){//返售、回购
			if(fromIsInner && toIsInner){//系统内
				//返售-------
				modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_SALEBACK;
				SalebackBillInfo salebackBill = new SalebackBillInfoDao().getSalebackBillInfoForRgctId(rgctId);
				if(salebackBill!=null){
					operStatus = salebackBill.getOperStatus();
				}
				if(!StringUtils.isBlank(operStatus)&&!this.isDeleteStatus(operStatus)){
					break out;
				}
				//回购-------
				modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_BUYBACK;
				List<BuybackBillInfo> buybackBillList = new BuybackBillInfoDao().getBuybackBillInfoByRgctid(rgctId);
				if(buybackBillList!=null){
					operStatus = buybackBillList.get(0).getOperStatus();
				}
			}else if(fromIsInner && !toIsInner){//同业返售
				modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_SALEBACK;
				SalebackBillInfo salebackBill = new SalebackBillInfoDao().getSalebackBillInfoForRgctId(rgctId);
				operStatus = salebackBill.getOperStatus();
			}else if(!fromIsInner && toIsInner){//同业回购
				modelCode = IDict.K_MODEL_CODE.K_MODEL_CODE_BUYBACK;
				List<BuybackBillInfo> buybackBillList = new BuybackBillInfoDao().getBuybackBillInfoByRgctid(rgctId);
				operStatus = buybackBillList.get(0).getOperStatus();
			}
		}else{
			throw new BizAppException("票据当前状态标志【"+curStatusStr+"】暂时未处理");
		}*/
		CommonLog.getCommonLogCache().infoMessage("票据【"+billNo+"】当前状态为"+curStatus);
		if(!StringUtils.isBlank(operStatus)){
			CommonLog.getCommonLogCache().infoMessage("查询出来的票据所在模块为【"+(IDict.K_MENU_CODE_2_NAME.get(modelCode)==null ? modelCode : IDict.K_MENU_CODE_2_NAME.get(modelCode))+"】，操作状态为"+operStatus);
		}
		String operStatus_new = "";
		String modelCode_new = "";
		String operStatus_temp = "";
		
		String batchNo_new = "";
		String prodNo_new = "";
		String createDt_new = "";
		String custNo_new="";
		/*boolean isLock = IDict.K_LOCK.K_LOCK_YES.equals(billHist.getIsLock());
		out:if(isLock){
		}*/
		out:if(true){
			//对方发托，我方承兑的情况
			AcptColltnReg acptColltnReg = new AcptColltnRegDao().getAcptColltnRegForBillNo(billNo);
			if(acptColltnReg!=null){
				AcptBillInfo acptBill = new AcptBillInfoDao().getAcptBillInfoByBillNo(billNo);
				AcptApplyInfo acptApply = new AcptApplyInfoDao().getAcptApplyInfo(acptColltnReg.getAcptId());
				createDt_new = acptColltnReg.getRegDt();
				batchNo_new = acptApply.getBatchNo();
				prodNo_new = acptApply.getProdNo();
				operStatus_temp = acptBill.getBillStatus();
				custNo_new = acptBill.getRemitterCustNo();
			}
			if(!StringUtils.isBlank(operStatus_temp)){
				modelCode_new = IDict.K_MODEL_CODE.K_MODEL_CODE_ACPT+"_2";
				operStatus_new = operStatus_temp;
				break out;
			}
			//托收-------------------
			SubcollBillInfo subcollInfo = new SubcollBillInfoDao().getSubcollBillForRgctId(rgctId);
			if(subcollInfo!=null){
				SubcollApplyInfo subCollApply = new SubcollApplyInfoDao().getSubcollApplyInfobyid(subcollInfo.getSubcollId());
				createDt_new = subcollInfo.getCreateTime();
				batchNo_new = subCollApply.getBatchId();
				prodNo_new = subCollApply.getProdNo();
				operStatus_temp = subcollInfo.getOperStatus();
				custNo_new = subcollInfo.getBelongCustNo();
			}
			if(!StringUtils.isBlank(operStatus_temp)&&!this.isDeleteStatus(operStatus_temp)){
				operStatus_new = operStatus_temp;
				modelCode_new = IDict.K_MODEL_CODE.K_MODEL_CODE_SUBCOLL;
				break out;
			}
			//转卖-------------------
			SaleBillInfo saleBill = new SaleBillInfoDao().getSaleBillInfoByRgcId(rgctId);
			if(saleBill!=null){
				SaleApplyInfo saleApply = new SaleApplyInfoDao().getSaleApplyInfo(saleBill.getSaleId());
				createDt_new = saleApply.getSaleDt();
				batchNo_new = saleApply.getBatchNo();
				prodNo_new = saleApply.getProdNo();
				operStatus_temp = saleBill.getOperStatus();
				custNo_new = saleBill.getRemitterCustNo();
			}
			if(!StringUtils.isBlank(operStatus_temp)&&!this.isDeleteStatus(operStatus_temp)){
				if(!StringUtils.isBlank(createDt) && !StringUtils.isBlank(createDt_new) && DateTimeUtil.compartdate(createDt, createDt_new)){
					operStatus_new = operStatus_temp;
					modelCode_new = IDict.K_MODEL_CODE.K_MODEL_CODE_SALE;
					break out;
				}
			}
			//解质押-------------------
			GetBillInfo getBill = new GetBillInfoDao().getGetBillInfoByRgctId(rgctId);
			if(getBill!=null){
				GetApplyInfo getApply = new GetApplyInfoDao().getGetApplyInfo(getBill.getGetId());
				createDt_new = getBill.getCreateDt();
				batchNo_new = getApply.getBatchNo();
				prodNo_new = getApply.getProdNo();
				operStatus_temp = getBill.getOperStatus();
				custNo_new = getBill.getCustNo();
			}
			if(!StringUtils.isBlank(operStatus_temp)&&!this.isDeleteStatus(operStatus_temp)){
				if(!StringUtils.isBlank(createDt) && !StringUtils.isBlank(createDt_new) && DateTimeUtil.compartdate(createDt, createDt_new)){
					operStatus_new = operStatus_temp;
					modelCode_new = IDict.K_MODEL_CODE.K_MODEL_CODE_GET;
					break out;
				}
			}
			//返售-------------------
			List<SalebackBillInfo> salebackBillList = new SalebackBillInfoDao().getSalebackBillInfolistForRgctId(rgctId);
			if(salebackBillList!=null&&salebackBillList.size()>0){
				SalebackBillInfo saleBackBill = salebackBillList.get(0);
				SalebackApplyInfo saleBackApply = new SalebackApplyInfoDao().getSalebackApplyInfo(saleBackBill.getSalebackId());
				createDt_new = saleBackBill.getCreateDt();
				batchNo_new = saleBackApply.getBatchNo();
				prodNo_new = saleBackApply.getProdNo();
				operStatus_temp = saleBackBill.getOperStatus();
				custNo_new = saleBackBill.getRemitterCustNo();
			}
			if(!StringUtils.isBlank(operStatus_temp)&&!this.isDeleteStatus(operStatus_temp)){
				if(!StringUtils.isBlank(createDt) && !StringUtils.isBlank(createDt_new) && DateTimeUtil.compartdate(createDt, createDt_new)){
					operStatus_new = operStatus_temp;
					modelCode_new = IDict.K_MODEL_CODE.K_MODEL_CODE_SALEBACK;
					break out;
				}
			}
			
			//回购-------------------
			List<BuybackBillInfo> buybackBillList = new BuybackBillInfoDao().getBuybackBillInfoByRgctid(rgctId);
			if(buybackBillList!=null && buybackBillList.size()>0){
				BuybackBillInfo buybackBill = buybackBillList.get(0);
				BuybackApplyInfo buyBackApply = new BuybackApplyInfoDao().getBuybackApplyInfo(buybackBill.getBuybackId(), null);
				createDt_new = buyBackApply.getCreateDt();
				batchNo_new = buyBackApply.getBatchNo();
				prodNo_new = buyBackApply.getProdNo();
				operStatus_temp = buybackBill.getOperStatus();
				custNo_new = buybackBill.getRemitterCustNo();
			}
			if(!StringUtils.isBlank(operStatus_temp)&&!this.isDeleteStatus(operStatus_temp)){
				if(!StringUtils.isBlank(createDt) && !StringUtils.isBlank(createDt_new) && DateTimeUtil.compartdate(createDt, createDt_new)){
					modelCode_new = IDict.K_MODEL_CODE.K_MODEL_CODE_BUYBACK;
					operStatus_new = operStatus_temp;
					break out;
				}
			}
		}
		/*out:if(curStatus.indexOf("02")>=0){
			
		}*/
		if(!StringUtils.isBlank(operStatus_new)){
			operStatus = operStatus_new;
			modelCode = modelCode_new;
			createDt = createDt_new;
			batchNo = batchNo_new;
			prodNo = prodNo_new;
			custNo = custNo_new;
			CommonLog.getCommonLogCache().infoMessage("最终查询出来的票据所在模块为【"+(IDict.K_MENU_CODE_2_NAME.get(modelCode)==null ? modelCode : IDict.K_MENU_CODE_2_NAME.get(modelCode))+"】，操作状态为"+operStatus);
		}
		if(StringUtils.isBlank(operStatus)){
			throw new BizAppException("搜索不到票据【"+billNo+"】所在位置");
		}
		String producName = "";
		if(!StringUtils.isBlank(prodNo)){
			producName = new ProductService().getProductInfoByProdNo(prodNo).getProdName();
		}
		String[] result = {modelCode,operStatus,(IDict.K_MENU_CODE_2_NAME.get(modelCode)==null ? modelCode : IDict.K_MENU_CODE_2_NAME.get(modelCode)),batchNo,createDt,producName,custNo};
		return result;
	}
	
	public boolean isDeleteStatus(String operStatus){
		if(StringUtils.isBlank(operStatus)){
			return false;
		}
		if(operStatus.substring(operStatus.length()-2).equals("00")){
			return true;
		}
		return false;
	}
	
	/**
	 * 功能描述：获取票据流程图中所有节点集合
	 * @throws Exception 
	 */
	public List<StatusDict> getStatusDictListByRgctBillInfo(RgctBill bill,String modelCode) throws Exception{
		String param = SysConfigUtil.getSysConfig().getValue(IConstants.AuditConstant.SWITCH_NAME);//是否开启审批流
		return this.getStatusDictList(param, modelCode, bill.getInfo().getBillClass());
	}
	/**
	 * 查询StatusDictList
	 * @param param
	 * @param modelCode
	 * @param billClass
	 * @return
	 * @throws SQLException
	 */
	public List<StatusDict> getStatusDictList(String param,String modelCode,String billClass) throws SQLException{
		IDB session = DBFactory.getDB();
		return session.getObjectList("select * from tstatus_dict where bill_class=? and model_code=? and (param is null or param='' or param=?)", StatusDict.class, billClass,modelCode,param);
	}
}
