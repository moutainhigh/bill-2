/********************************************
* 文件名称: RgctBillHistDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.rc.domain.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
public class RgctBillHistDao{

public int addRgctBillHist(RgctBillHist obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trgct_bill_hist(hist_id,rgct_id,cur_status,pre_status,run_status,batch_id,from_name,from_cust_no,from_acct_no,from_bank_no,from_branch_no,from_sign,to_name,to_cust_no,to_acct_no,to_bank_no,to_branch_no,sign_dt,sign_time,interest_rate,interest,deal_money,is_regress,regress_dt,regress_time,regress_price,back_open_dt,back_end_dt,back_rate,back_amount,if_inner,is_buy,signer,signer_sign,sign_flag,hold_cust_no,hold_cust_name,hold_acct_no,hold_bank_no,obligee_cust_no,obligee_cust_name,obligee_bank_no,obligee_acct_no,return_code,oper_date,oper_time,oper_no,channel,is_lock,lock_branch_no,lock_flow_name,step_name,valid_hist_id,last_hist_id,is_online,overdue_rs,is_accpt,draft_log_id,reject_code,reject_reason,is_redisc_center,assu_id,recourse_id,is_delegate,in_acct_no,in_bank_no,partner_code,pay_trade_no,forbid_flag,from_remark,to_remark,draft_info,old_interest_rate,old_dis_dt,delay_days,delay_type,bill_track,is_same_city,interest_due_dt,interest_days,firstbuy_interest,from_orgcode,to_orgcode,from_role,to_role,bill_before_owner,prod_attr,acct_branch_no,storage_branch_no,buy_type,workingads_no,workingads_name,storage_branch_name,endo_hist_id,endorses_bank_name,endorses_bank_no,endorse_dt,endo_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getHistId(),obj.getRgctId(),obj.getCurStatus(),obj.getPreStatus(),obj.getRunStatus(),
	obj.getBatchId(),obj.getFromName(),obj.getFromCustNo(),obj.getFromAcctNo(),
	obj.getFromBankNo(),obj.getFromBranchNo(),obj.getFromSign(),obj.getToName(),
	obj.getToCustNo(),obj.getToAcctNo(),obj.getToBankNo(),obj.getToBranchNo(),
	obj.getSignDt(),obj.getSignTime(),obj.getInterestRate(),obj.getInterest(),
	obj.getDealMoney(),obj.getIsRegress(),obj.getRegressDt(),obj.getRegressTime(),
	obj.getRegressPrice(),obj.getBackOpenDt(),obj.getBackEndDt(),obj.getBackRate(),
	obj.getBackAmount(),obj.getIfInner(),obj.getIsBuy(),obj.getSigner(),
	obj.getSignerSign(),obj.getSignFlag(),obj.getHoldCustNo(),obj.getHoldCustName(),
	obj.getHoldAcctNo(),obj.getHoldBankNo(),obj.getObligeeCustNo(),obj.getObligeeCustName(),
	obj.getObligeeBankNo(),obj.getObligeeAcctNo(),obj.getReturnCode(),obj.getOperDate(),
	obj.getOperTime(),obj.getOperNo(),obj.getChannel(),obj.getIsLock(),
	obj.getLockBranchNo(),obj.getLockFlowName(),obj.getStepName(),obj.getValidHistId(),
	obj.getLastHistId(),obj.getIsOnline(),obj.getOverdueRs(),obj.getIsAccpt(),
	obj.getDraftLogId(),obj.getRejectCode(),obj.getRejectReason(),obj.getIsRediscCenter(),
	obj.getAssuId(),obj.getRecourseId(),obj.getIsDelegate(),obj.getInAcctNo(),
	obj.getInBankNo(),obj.getPartnerCode(),obj.getPayTradeNo(),obj.getForbidFlag(),
	obj.getFromRemark(),obj.getToRemark(),obj.getDraftInfo(),obj.getOldInterestRate(),
	obj.getOldDisDt(),obj.getDelayDays(),obj.getDelayType(),obj.getBillTrack(),
	obj.getIsSameCity(),obj.getInterestDueDt(),obj.getInterestDays(),obj.getFirstbuyInterest(),
	obj.getFromOrgcode(),obj.getToOrgcode(),obj.getFromRole(),obj.getToRole(),
	obj.getBillBeforeOwner(),obj.getProdAttr(),obj.getAcctBranchNo(),obj.getStorageBranchNo(),
	obj.getBuyType(),obj.getWorkingadsNo(),obj.getWorkingadsName(),obj.getStorageBranchName(),
	obj.getEndoHistId(),obj.getEndorsesBankName(),obj.getEndorsesBankNo(),obj.getEndorseDt(),
	obj.getEndoStatus());
}

public int deleteRgctBillHist(RgctBillHist obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trgct_bill_hist where hist_id=?",obj.getHistId());
}

public int deleteRgctBillHist(String histId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trgct_bill_hist where hist_id=?",histId);
}

public int modifyRgctBillHist(RgctBillHist obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_bill_hist set rgct_id=?,cur_status=?,pre_status=?,run_status=?,batch_id=?,from_name=?,from_cust_no=?,from_acct_no=?,from_bank_no=?,from_branch_no=?,from_sign=?,to_name=?,to_cust_no=?,to_acct_no=?,to_bank_no=?,to_branch_no=?,sign_dt=?,sign_time=?,interest_rate=?,interest=?,deal_money=?,is_regress=?,regress_dt=?,regress_time=?,regress_price=?,back_open_dt=?,back_end_dt=?,back_rate=?,back_amount=?,if_inner=?,is_buy=?,signer=?,signer_sign=?,sign_flag=?,hold_cust_no=?,hold_cust_name=?,hold_acct_no=?,hold_bank_no=?,obligee_cust_no=?,obligee_cust_name=?,obligee_bank_no=?,obligee_acct_no=?,return_code=?,oper_date=?,oper_time=?,oper_no=?,channel=?,is_lock=?,lock_branch_no=?,lock_flow_name=?,step_name=?,valid_hist_id=?,last_hist_id=?,is_online=?,overdue_rs=?,is_accpt=?,draft_log_id=?,reject_code=?,reject_reason=?,is_redisc_center=?,assu_id=?,recourse_id=?,is_delegate=?,in_acct_no=?,in_bank_no=?,partner_code=?,pay_trade_no=?,forbid_flag=?,from_remark=?,to_remark=?,draft_info=?,old_interest_rate=?,old_dis_dt=?,delay_days=?,delay_type=?,bill_track=?,is_same_city=?,interest_due_dt=?,interest_days=?,firstbuy_interest=?,from_orgcode=?,to_orgcode=?,from_role=?,to_role=?,bill_before_owner=?,prod_attr=?,acct_branch_no=?,storage_branch_no=?,buy_type=?,workingads_no=?,workingads_name=?,storage_branch_name=?,endo_hist_id=?,endorses_bank_name=?,endorses_bank_no=?,endorse_dt=?,endo_status=? where hist_id=?",
	obj.getRgctId(),obj.getCurStatus(),obj.getPreStatus(),obj.getRunStatus(),
	obj.getBatchId(),obj.getFromName(),obj.getFromCustNo(),obj.getFromAcctNo(),
	obj.getFromBankNo(),obj.getFromBranchNo(),obj.getFromSign(),obj.getToName(),
	obj.getToCustNo(),obj.getToAcctNo(),obj.getToBankNo(),obj.getToBranchNo(),
	obj.getSignDt(),obj.getSignTime(),obj.getInterestRate(),obj.getInterest(),
	obj.getDealMoney(),obj.getIsRegress(),obj.getRegressDt(),obj.getRegressTime(),
	obj.getRegressPrice(),obj.getBackOpenDt(),obj.getBackEndDt(),obj.getBackRate(),
	obj.getBackAmount(),obj.getIfInner(),obj.getIsBuy(),obj.getSigner(),
	obj.getSignerSign(),obj.getSignFlag(),obj.getHoldCustNo(),obj.getHoldCustName(),
	obj.getHoldAcctNo(),obj.getHoldBankNo(),obj.getObligeeCustNo(),obj.getObligeeCustName(),
	obj.getObligeeBankNo(),obj.getObligeeAcctNo(),obj.getReturnCode(),obj.getOperDate(),
	obj.getOperTime(),obj.getOperNo(),obj.getChannel(),obj.getIsLock(),
	obj.getLockBranchNo(),obj.getLockFlowName(),obj.getStepName(),obj.getValidHistId(),
	obj.getLastHistId(),obj.getIsOnline(),obj.getOverdueRs(),obj.getIsAccpt(),
	obj.getDraftLogId(),obj.getRejectCode(),obj.getRejectReason(),obj.getIsRediscCenter(),
	obj.getAssuId(),obj.getRecourseId(),obj.getIsDelegate(),obj.getInAcctNo(),
	obj.getInBankNo(),obj.getPartnerCode(),obj.getPayTradeNo(),obj.getForbidFlag(),
	obj.getFromRemark(),obj.getToRemark(),obj.getDraftInfo(),obj.getOldInterestRate(),
	obj.getOldDisDt(),obj.getDelayDays(),obj.getDelayType(),obj.getBillTrack(),
	obj.getIsSameCity(),obj.getInterestDueDt(),obj.getInterestDays(),obj.getFirstbuyInterest(),
	obj.getFromOrgcode(),obj.getToOrgcode(),obj.getFromRole(),obj.getToRole(),
	obj.getBillBeforeOwner(),obj.getProdAttr(),obj.getAcctBranchNo(),obj.getStorageBranchNo(),
	obj.getBuyType(),obj.getWorkingadsNo(),obj.getWorkingadsName(),obj.getStorageBranchName(),
	obj.getEndoHistId(),obj.getEndorsesBankName(),obj.getEndorsesBankNo(),obj.getEndorseDt(),
	obj.getEndoStatus(),obj.getHistId());
}

public int modifyRgctBillHist(RgctBillHist obj,String histId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_bill_hist set rgct_id=?,cur_status=?,pre_status=?,run_status=?,batch_id=?,from_name=?,from_cust_no=?,from_acct_no=?,from_bank_no=?,from_branch_no=?,from_sign=?,to_name=?,to_cust_no=?,to_acct_no=?,to_bank_no=?,to_branch_no=?,sign_dt=?,sign_time=?,interest_rate=?,interest=?,deal_money=?,is_regress=?,regress_dt=?,regress_time=?,regress_price=?,back_open_dt=?,back_end_dt=?,back_rate=?,back_amount=?,if_inner=?,is_buy=?,signer=?,signer_sign=?,sign_flag=?,hold_cust_no=?,hold_cust_name=?,hold_acct_no=?,hold_bank_no=?,obligee_cust_no=?,obligee_cust_name=?,obligee_bank_no=?,obligee_acct_no=?,return_code=?,oper_date=?,oper_time=?,oper_no=?,channel=?,is_lock=?,lock_branch_no=?,lock_flow_name=?,step_name=?,valid_hist_id=?,last_hist_id=?,is_online=?,overdue_rs=?,is_accpt=?,draft_log_id=?,reject_code=?,reject_reason=?,is_redisc_center=?,assu_id=?,recourse_id=?,is_delegate=?,in_acct_no=?,in_bank_no=?,partner_code=?,pay_trade_no=?,forbid_flag=?,from_remark=?,to_remark=?,draft_info=?,old_interest_rate=?,old_dis_dt=?,delay_days=?,delay_type=?,bill_track=?,is_same_city=?,interest_due_dt=?,interest_days=?,firstbuy_interest=?,from_orgcode=?,to_orgcode=?,from_role=?,to_role=?,bill_before_owner=?,prod_attr=?,acct_branch_no=?,storage_branch_no=?,buy_type=?,workingads_no=?,workingads_name=?,storage_branch_name=?,endo_hist_id=?,endorses_bank_name=?,endorses_bank_no=?,endorse_dt=?,endo_status=? where hist_id=?",
	obj.getRgctId(),obj.getCurStatus(),obj.getPreStatus(),obj.getRunStatus(),
	obj.getBatchId(),obj.getFromName(),obj.getFromCustNo(),obj.getFromAcctNo(),
	obj.getFromBankNo(),obj.getFromBranchNo(),obj.getFromSign(),obj.getToName(),
	obj.getToCustNo(),obj.getToAcctNo(),obj.getToBankNo(),obj.getToBranchNo(),
	obj.getSignDt(),obj.getSignTime(),obj.getInterestRate(),obj.getInterest(),
	obj.getDealMoney(),obj.getIsRegress(),obj.getRegressDt(),obj.getRegressTime(),
	obj.getRegressPrice(),obj.getBackOpenDt(),obj.getBackEndDt(),obj.getBackRate(),
	obj.getBackAmount(),obj.getIfInner(),obj.getIsBuy(),obj.getSigner(),
	obj.getSignerSign(),obj.getSignFlag(),obj.getHoldCustNo(),obj.getHoldCustName(),
	obj.getHoldAcctNo(),obj.getHoldBankNo(),obj.getObligeeCustNo(),obj.getObligeeCustName(),
	obj.getObligeeBankNo(),obj.getObligeeAcctNo(),obj.getReturnCode(),obj.getOperDate(),
	obj.getOperTime(),obj.getOperNo(),obj.getChannel(),obj.getIsLock(),
	obj.getLockBranchNo(),obj.getLockFlowName(),obj.getStepName(),obj.getValidHistId(),
	obj.getLastHistId(),obj.getIsOnline(),obj.getOverdueRs(),obj.getIsAccpt(),
	obj.getDraftLogId(),obj.getRejectCode(),obj.getRejectReason(),obj.getIsRediscCenter(),
	obj.getAssuId(),obj.getRecourseId(),obj.getIsDelegate(),obj.getInAcctNo(),
	obj.getInBankNo(),obj.getPartnerCode(),obj.getPayTradeNo(),obj.getForbidFlag(),
	obj.getFromRemark(),obj.getToRemark(),obj.getDraftInfo(),obj.getOldInterestRate(),
	obj.getOldDisDt(),obj.getDelayDays(),obj.getDelayType(),obj.getBillTrack(),
	obj.getIsSameCity(),obj.getInterestDueDt(),obj.getInterestDays(),obj.getFirstbuyInterest(),
	obj.getFromOrgcode(),obj.getToOrgcode(),obj.getFromRole(),obj.getToRole(),
	obj.getBillBeforeOwner(),obj.getProdAttr(),obj.getAcctBranchNo(),obj.getStorageBranchNo(),
	obj.getBuyType(),obj.getWorkingadsNo(),obj.getWorkingadsName(),obj.getStorageBranchName(),
	obj.getEndoHistId(),obj.getEndorsesBankName(),obj.getEndorsesBankNo(),obj.getEndorseDt(),
	obj.getEndoStatus(),histId);
}

public RgctBillHist getRgctBillHist(String histId) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctBillHist obj = (RgctBillHist)session.getObject("select * from trgct_bill_hist where hist_id=?",RgctBillHist.class,histId);
	return obj;
}

public List<RgctBillHist> getRcHistList(String histIds) throws SQLException{
	StringBuffer sql = new StringBuffer("select t.* from trgct_bill_hist t where  hist_id in (");
	String idArr[] = histIds.split(",");
	List<Object> param = new ArrayList<Object>(idArr.length);
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.getObjectListByList(sql.toString(), RgctBillHist.class, param);
}

}
