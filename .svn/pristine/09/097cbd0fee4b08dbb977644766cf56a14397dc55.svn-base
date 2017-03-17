package com.herongtech.console.service.common.fac;

import java.util.List;

import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.FacCreateFlow;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IFmsAgentService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;

public class FacRenewService implements IFacRenewService {
	private IFmsAgentService fmsAgentService = ServiceFactory.getFmsAgentService();
	private final String[] batchReleaseSql = {
	        // 托收收回销帐
			"select  facflow.*  from tSubColl_bill_info  bill, tFac_Create_Flow facflow  where bill.rgct_Id=facflow.rgct_Id  and bill.yz_Source='0' and  facflow.status='1' and bill.status='2' and bill.gath_Date <= (select workday from tBusi_Date )",
			// 卖断同业记账（不含贴现来源的商承、双卖）
			"select  facflow.*  from tSale_Bill_Info bill, tFac_Create_Flow facflow, tSale_Apply_Info apply  where bill.rgct_Id=facflow.rgct_Id  and bill.sale_id=apply.sale_id and bill.bill_Source_Id=facflow.bill_Id and apply.if_Bidir_Buy='0' and bill.yz_Source='0' and  bill.is_Delay_In ='0' and (bill.bill_Source='2' or (bill.bill_Source='1' and bill.bill_Type = '1')) and bill.oper_Status='BS236' and bill.is_Inner='0' and facflow.status='1' and bill.account_Date <= (select workday from tBusi_Date) ",
			// 返售到期释放
			"select  facflow.*  from Tsaleback_Bill_Info bill, tFac_Create_Flow facflow  where bill.rgct_Id=facflow.rgct_Id  and bill.Is_Inner='0' and (bill.oper_Status='BSz636' or bill.oper_Status='BSb636') and facflow.status='1'  and facflow.source='2' and bill.account_dt <= (select workday from tBusi_Date )",
			//解质押
			"select  facflow.*  from tget_Bill_Info getBill,tsave_bill_info saveBill, tFac_Create_Flow facflow  where saveBill.rgct_id=facflow.rgct_id and getBill.Savebill_Rela_Id=saveBill.savemx_id and facflow.status='1' and getBill.oper_status='BS836' and getBill.Account_Date <= (select workday from tBusi_Date )"
			};
			// 贴现赎回
			//"select  facflow.*  from tDisc_Bill_Info bill, tFac_Create_Flow facflow  where bill.rgct_Id=facflow.rgct_Id  and bill.yz_Source='0' and  bill.bill_Type = '1'  and bill.oper_Status='BD147'  and bill.gath_Mney_Type = '4' and facflow.status='1'  and bill.gath_Mney_Date <= (select workday from tBusi_Date )",
			// 双卖票据到期未赎回至票面到期日释放
			//"select  facflow.*  from tSale_Bill_Info bill, tFac_Create_Flow facflow, tSale_Apply_Info apply  where bill.rgct_Id=facflow.rgct_Id and bill.sale_id=apply.sale_id  and apply.if_Bidir_Buy='1' and bill.yz_Source='0' and  bill.is_Delay_In ='0' and bill.oper_Status='BS236' and facflow.status='1'  and bill.due_dt <= (select workday from tBusi_Date) and bill.bidir_Sale_Hist_Flag ='0'"
			// 商承(贴现来源)至票面到期日释放
			//"select  facflow.*  from tDisc_Bill_Info bill, tFac_Create_Flow facflow  where bill.rgct_Id=facflow.rgct_Id  and bill.yz_Source='0' and  bill.bill_Source='1' and bill.bill_Type = '2' and bill.is_Delay_In ='0' and bill.oper_Status='BD245' and facflow.status='1'  and bill.end_Bill_Date <= (select workday from tBusi_Date)" };

	private final String[] nameArr = {
			"托收收回销帐",
			"卖断同业记账（不含贴现来源的商承、双卖）",
			"返售到期释放",
			"解质押"
	};
	/***
	 * 通过类型查询额度信息<br>
	 * 1:托收收回销帐<br>
	 * 2.卖断同业记账（不含贴现来源的商承、双卖）<br>
	 * 3.返售到期释放<br>
	 * 4.解质押
	 * 
	 * 5.贴现赎回<br>
	 * 6.双卖票据到期未赎回至票面到期日释放 <br>
	 * 7:卖断同业商承(贴现来源)至票面到期日释放<br>
	 * @throws BizAppException
	 */
	public void facBatchRelease() {
		IDB session = DBFactory.getDB();
		UserLoginfo userInfo = ResourceUtil.getVirtualUserLoginfo();
		this.releaseFac(session, userInfo);
		/*this.releaseFacOfSale(session, userInfo);
		this.releaseFacOfSaleBack(session, userInfo);
		this.releaseFacOfSubcoll(session, userInfo);
		this.releaseFacOfRepurCollate(session, userInfo);*/
		//以下场景暂时不做
//		this.releaseFacOfSale_Double(session, userInfo);
//		this.releaseFacOfDisc(session, userInfo);
		
	}
	public void releaseFac(IDB session,UserLoginfo userInfo){
		String name = "";
		try {
			for(int i=0;i<batchReleaseSql.length;i++){
				name = nameArr[i];
				String sql = batchReleaseSql[i];
				if(!this.checkFacFlowCount(session, sql,name)){
					continue;
				}
				List<FacCreateFlow> list = session.getObjectList(sql, FacCreateFlow.class);
				fmsAgentService.facReleaseByBillBatch(null, list, userInfo);//TODO apply暂时传null
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().infoMessage("【"+name+"】额度释放异常|"+e.getMessage());
		}
	}
	public int getFacFlowCount(IDB session,String sql) throws Exception{
		QueryCondition qc = new QueryCondition();
		qc.setBasicSql(sql);
		qc.setBasicSql_low(sql);
		return session.account(qc.getCountSql("facflow.id"));
	}
	public boolean checkFacFlowCount(IDB session,String sql,String name) throws Exception{
		int total = this.getFacFlowCount(session, sql);
		CommonLog.getCommonLogCache().infoMessage("【"+name+"】释放总数:" + total);
		if (total == 0) {
			return false;
		}else{
			return true;
		}
	}
	//以下写法有问题：sql中查询的是facflow.*，但是获取的list却是saveBillInfo、SaleBillInfo等对象，导致在调用fmsAgentService.facReleaseByBillBatch时facCreateFlow为空(如果是日终释放，会将list中的对象统一转成facCreateFlow)--空指针异常
	/**
	 * 释放【解质押】相关额度
	 * 查询条件：
	 * 		getBill.oper_status='BS836'--票据状态为“BS836记账完成”
	 * 		facflow.status='1'--额度为“占用”状态
	 * 		getBill.account_Date <= (select workday from tBusi_Date )--记账日期在营业日期之前（包含营业日）
	 * @param session
	 * @param userInfo
	 *//*
	public void releaseFacOfRepurCollate(IDB session,UserLoginfo userInfo){
		String sql = "select  facflow.*  from tget_Bill_Info getBill,tsave_bill_info saveBill, tFac_Create_Flow facflow  where saveBill.rgct_id=facflow.rgct_id and getBill.Savebill_Rela_Id=saveBill.savemx_id and facflow.status='1' and getBill.oper_status='BS836' and getBill.Account_Date <= (select workday from tBusi_Date )";
		try {
			if(!this.checkFacFlowCount(session, sql)){
				return;
			}
			List<SaveBillInfo> list = session.getObjectList(sql, SaveBillInfo.class);
			if(list!=null && !list.isEmpty()){
				SaveApplyInfo apply = new SaveApplyInfoDao().getSaveApplyInf(list.get(0).getSaveId());
				fmsAgentService.facReleaseByBillBatch(apply, list, userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().infoMessage("【解质押】额度释放异常|"+e.getMessage());
		}
	}
	
	*//**释放【卖断同业记账（不含贴现来源的商承、双卖）】相关额度
	 * 查询条件：
	 * 		apply.if_Bidir_Buy='0' --不是双卖
	 * 		bill.yz_Source='0' --移植来源：0:默认值 1:票据卖出回购 2：人行电票
	 *		bill.is_Delay_In ='0' --不是回购
	 * 		(bill.bill_Source='2' or (bill.bill_Source='1' and bill.bill_Type = '1')) --贴现的银票 or 转入的票
	 * 		bill.oper_Status='BS236'--记账完成
	 * 		bill.is_Inner='0' --同业间
	 * 		facflow.status='1'--额度为“占用”状态
	 * 		bill.account_Date <= (select workday from tBusi_Date)--记账日期在营业日期之前（包含营业日）
	 * 
	 * @param session
	 * @param userInfo
	 *//*
	public void releaseFacOfSale(IDB session,UserLoginfo userInfo) {
		try {
			String sql = "select  facflow.*  from tSale_Bill_Info bill, tFac_Create_Flow facflow, tSale_Apply_Info apply  where bill.rgct_Id=facflow.rgct_Id  and bill.sale_id=apply.sale_id and bill.bill_Source_Id=facflow.bill_Id and apply.if_Bidir_Buy='0' and bill.yz_Source='0' and  bill.is_Delay_In ='0' and (bill.bill_Source='2' or (bill.bill_Source='1' and bill.bill_Type = '1')) and bill.oper_Status='BS236' and bill.is_Inner='0' and facflow.status='1' and bill.account_Date <= (select workday from tBusi_Date) ";
			if(!this.checkFacFlowCount(session, sql)){
				return;
			}
			List<SaleBillInfo> list = session.getObjectList(sql, SaleBillInfo.class);
			if(list!=null && list.size()>0){
				SaleApplyInfo apply = new SaleApplyInfoDao().getSaleApplyInfo(list.get(0).getSaleId());
				fmsAgentService.facReleaseByBillBatch(apply,list, userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().infoMessage("【卖断同业记账（不含贴现来源的商承、双卖）】额度释放异常|"+e.getMessage());
		}
	}
	
	*//**
	 * 释放【托收收回销帐】相关额度
	 * 查询条件：
	 * 		bill.yz_Source='0'--移植来源：0:默认值   1：实物系统系统  2 ：企业系统-票据池  3：企业系统-代保管  4：风控质押  5：人行电票
	 * 		facflow.status='1'--额度为“占用”状态
	 * 		bill.status='2'--票据为“结清”状态：1，托收在途；2,结清;3,退票
	 * 		bill.gath_Date <= (select workday from tBusi_Date )--记账日期在营业日期之前（包含营业日）
	 * @param session
	 * @param userInfo
	 *//*
	public void releaseFacOfSubcoll(IDB session,UserLoginfo userInfo) {
		try {
			String sql = "select  facflow.*  from tSubColl_bill_info  bill, tFac_Create_Flow facflow  where bill.rgct_Id=facflow.rgct_Id  and bill.yz_Source='0' and  facflow.status='1' and bill.status='2' and bill.gath_Date <= (select workday from tBusi_Date )";
			if(!this.checkFacFlowCount(session, sql)){
				return;
			}
			List<SubcollBillInfo> list = session.getObjectList(sql, SubcollBillInfo.class);
			if(list!=null && list.size()>0){
				SubcollApplyInfo apply = new SubcollApplyInfoDao().getSubcollApplyInfobyid(list.get(0).getSubcollId());
				fmsAgentService.facReleaseByBillBatch(apply,list, userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().infoMessage("【托收收回销帐】额度释放异常|"+e.getMessage());
		}
	}
	*//**
	 * 释放【返售到期释放】相关额度
	 * 查询条件：
	 * 		bill.Is_Inner='0'--同业间
	 * 		(bill.oper_Status='BSz636' or bill.oper_Status='BD420')--电票记账完成状态、纸票记账完成状态
	 * 		facflow.status='1'--额度为“占用”状态
	 * 		facflow.source='2'--额度扣减来源：1：贴现，2：转贴现，3：质押
	 * 		bill.account_dt <= (select workday from tBusi_Date )--记账日期在营业日期之前（包含营业日）
	 * @param session
	 * @param userInfo
	 *//*
	public void releaseFacOfSaleBack(IDB session,UserLoginfo userInfo) {
		try {
			//TODO 票据状态待修改（返售李江涛还没有开发完成）
			String sql = "select  facflow.*  from Tsaleback_Bill_Info bill, tFac_Create_Flow facflow  where bill.rgct_Id=facflow.rgct_Id  and bill.Is_Inner='0' and (bill.oper_Status='BSz636' or bill.oper_Status='BD420') and facflow.status='1'  and facflow.source='2' and bill.account_dt <= (select workday from tBusi_Date )";
			if(!this.checkFacFlowCount(session, sql)){
				return;
			}
			List<SalebackBillInfo> list = session.getObjectList(sql, SalebackBillInfo.class);
			if(list!=null && list.size()>0){
				SalebackApplyInfo apply = new SalebackApplyInfoDao().getSalebackApplyInfo(list.get(0).getSalebackId());
				fmsAgentService.facReleaseByBillBatch(apply,list, userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().infoMessage("【返售到期释放】额度释放异常|"+e.getMessage());
		}
	}
	*//**
	 * 释放【贴现赎回】相关额度--暂时不做
	 * @param session
	 * @param userInfo
	 *//*
	public void releaseFacOfDisc(IDB session,UserLoginfo userInfo) {
		try {
			String sql = "select  facflow.*  from tDisc_Bill_Info bill, tFac_Create_Flow facflow  where bill.rgct_Id=facflow.rgct_Id  and bill.yz_Source='0' and  bill.bill_Type = '1'  and bill.oper_Status='BD147'  and bill.gath_Mney_Type = '4' and facflow.status='1'  and bill.gath_Mney_Date <= (select workday from tBusi_Date )";
			if(!this.checkFacFlowCount(session, sql)){
				return;
			}
			List<DiscBillInfo> list = session.getObjectList(sql, DiscBillInfo.class);
			if(list!=null && list.size()>0){
				DiscApplyInfo apply = new DiscApplyInfoDao().getDiscApplyInfo(list.get(0).getDiscId(), null);
				fmsAgentService.facReleaseByBillBatch(apply,list, userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().infoMessage("【贴现赎回】额度释放异常|"+e.getMessage());
		}
	}
	*//**
	 * 释放【双卖票据到期未赎回至票面到期日释放】相关额度--暂时不做
	 * @param session
	 * @param userInfo
	 *//*
	public void releaseFacOfSale_Double(IDB session,UserLoginfo userInfo) {
		try {
			String sql = "select  facflow.*  from tSale_Bill_Info bill, tFac_Create_Flow facflow, tSale_Apply_Info apply  where bill.rgct_Id=facflow.rgct_Id and bill.sale_id=apply.sale_id  and apply.if_Bidir_Buy='1' and bill.yz_Source='0' and  bill.is_Delay_In ='0' and bill.oper_Status='BS236' and facflow.status='1'  and bill.due_dt <= (select workday from tBusi_Date) and bill.bidir_Sale_Hist_Flag ='0'";
			if(!this.checkFacFlowCount(session, sql)){
				return;
			}
			List<DiscBillInfo> list = session.getObjectList(sql, DiscBillInfo.class);
			if(list!=null && list.size()>0){
				DiscApplyInfo apply = new DiscApplyInfoDao().getDiscApplyInfo(list.get(0).getDiscId(), null);
				fmsAgentService.facReleaseByBillBatch(apply,list, userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().infoMessage("【双卖票据到期未赎回至票面到期日释放】额度释放异常|"+e.getMessage());
		}
	}*/
}
