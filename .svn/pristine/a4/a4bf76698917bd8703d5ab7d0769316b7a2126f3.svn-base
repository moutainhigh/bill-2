/********************************************
* 文件名称: DiscBillInfoDao.java
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
package com.herongtech.console.domain.disc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.bean.DiscInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class DiscBillInfoDao{

public int addDiscBillInfo(DiscBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tdisc_bill_info(discmx_id,bill_type,issue_dt,due_dt,remitter,remitter_acct,remitter_bank_name,remitter_bank_no,acceptor,acceptor_acct,acceptor_bank_no,acceptor_bank_name,acceptor_branch_no,bill_money,drawee_addr,payee_bank_no,payee,payee_bank_name,payee_acct,bill_before_owner,bill_owner,bill_class,bill_source,oper_status,disc_id,rgct_id,cust_no,cust_account,saler_interest,delay_days,gale_date,interest_days,buyer_interest,pay_money,is_same_city,remark,is_audited,confer_no,is_accpt,limit_prod_no,rate,rate_type,disc_dt,disc_type,file_no,no_lou_loans,is_online,redeem_end_date,bank_prod_no,local_pay_money,is_amount,forbid_flag,is_third_amount,out_apply_no,branch_no,oper_bank_no,yz_source,product_id,owner_party_id,is_cyc,is_cancel,redeem_open_dt,disc_square_id,in_acct_no,in_bank_no,ex_serial,audit_reason,update_dt,update_time,account_date,account_time,coll_dt,bill_no,apply_oper_no,audit_oper_no,acct_oper_no,gath_mney_type,gath_mney_date,apply_commit_date,apply_commit_time,audit_commit_date,audit_commit_time,acct_commit_date,acct_commit_time,total_intrst_payment,delay_type,org_code,query_result,query_content,return_content,req_msg_id,in_store_dt,in_store_time,invoice_no,draft_pay_money,coll_account_dt,protocal_no,change_dt,change_time,last_bill_no,cur_status,trans_id,buyback_id,trans_type,is_redeem,limit_reduce_row,owner_party_name)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getDiscmxId(),obj.getBillType(),obj.getIssueDt(),obj.getDueDt(),obj.getRemitter(),
	obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),obj.getAcceptor(),
	obj.getAcceptorAcct(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getAcceptorBranchNo(),
	obj.getBillMoney(),obj.getDraweeAddr(),obj.getPayeeBankNo(),obj.getPayee(),
	obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),
	obj.getBillClass(),obj.getBillSource(),obj.getOperStatus(),obj.getDiscId(),
	obj.getRgctId(),obj.getCustNo(),obj.getCustAccount(),obj.getSalerInterest(),
	obj.getDelayDays(),obj.getGaleDate(),obj.getInterestDays(),obj.getBuyerInterest(),
	obj.getPayMoney(),obj.getIsSameCity(),obj.getRemark(),obj.getIsAudited(),
	obj.getConferNo(),obj.getIsAccpt(),obj.getLimitProdNo(),obj.getRate(),
	obj.getRateType(),obj.getDiscDt(),obj.getDiscType(),obj.getFileNo(),
	obj.getNoLouLoans(),obj.getIsOnline(),obj.getRedeemEndDate(),
	obj.getBankProdNo(),obj.getLocalPayMoney(),obj.getIsAmount(),obj.getForbidFlag(),
	obj.getIsThirdAmount(),obj.getOutApplyNo(),obj.getBranchNo(),obj.getOperBankNo(),
	obj.getYzSource(),obj.getProductId(),obj.getOwnerPartyId(),obj.getIsCyc(),
	obj.getIsCancel(),obj.getRedeemOpenDt(),obj.getDiscSquareId(),obj.getInAcctNo(),
	obj.getInBankNo(),obj.getExSerial(),obj.getAuditReason(),obj.getUpdateDt(),
	obj.getUpdateTime(),obj.getAccountDate(),obj.getAccountTime(),obj.getCollDt(),
	obj.getBillNo(),obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),
	obj.getGathMneyType(),obj.getGathMneyDate(),obj.getApplyCommitDate(),obj.getApplyCommitTime(),
	obj.getAuditCommitDate(),obj.getAuditCommitTime(),obj.getAcctCommitDate(),obj.getAcctCommitTime(),
	obj.getTotalIntrstPayment(),obj.getDelayType(),obj.getOrgCode(),obj.getQueryResult(),
	obj.getQueryContent(),obj.getReturnContent(),obj.getReqMsgId(),obj.getInStoreDt(),
	obj.getInStoreTime(),obj.getInvoiceNo(),obj.getDraftPayMoney(),obj.getCollAccountDt(),
	obj.getProtocalNo(),obj.getChangeDt(),obj.getChangeTime(),obj.getLastBillNo(),
	obj.getCurStatus(),obj.getTransId(),obj.getBuybackId(),obj.getTransType(),
	obj.getIsRedeem(),obj.getLimitReduceRow(),obj.getOwnerPartyName());
}

public int deleteDiscBillInfo(DiscBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdisc_bill_info where discmx_id=?",obj.getDiscmxId());
}

public int deleteDiscBillInfo(String discmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdisc_bill_info where discmx_id=?",discmxId);
}

public int modifyDiscBillInfo(DiscBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdisc_bill_info set bill_type=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,acceptor=?,acceptor_acct=?,acceptor_bank_no=?,acceptor_bank_name=?,acceptor_branch_no=?,bill_money=?,drawee_addr=?,payee_bank_no=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,disc_id=?,rgct_id=?,cust_no=?,cust_account=?,saler_interest=?,delay_days=?,gale_date=?,interest_days=?,buyer_interest=?,pay_money=?,is_same_city=?,remark=?,is_audited=?,confer_no=?,is_accpt=?,limit_prod_no=?,rate=?,rate_type=?,disc_dt=?,disc_type=?,file_no=?,no_lou_loans=?,is_online=?,redeem_end_date=?,bank_prod_no=?,local_pay_money=?,is_amount=?,forbid_flag=?,is_third_amount=?,out_apply_no=?,branch_no=?,oper_bank_no=?,yz_source=?,product_id=?,owner_party_id=?,is_cyc=?,is_cancel=?,redeem_open_dt=?,disc_square_id=?,in_acct_no=?,in_bank_no=?,ex_serial=?,audit_reason=?,update_dt=?,update_time=?,account_date=?,account_time=?,coll_dt=?,bill_no=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,gath_mney_type=?,gath_mney_date=?,apply_commit_date=?,apply_commit_time=?,audit_commit_date=?,audit_commit_time=?,acct_commit_date=?,acct_commit_time=?,total_intrst_payment=?,delay_type=?,org_code=?,query_result=?,query_content=?,return_content=?,req_msg_id=?,in_store_dt=?,in_store_time=?,invoice_no=?,draft_pay_money=?,coll_account_dt=?,protocal_no=?,change_dt=?,change_time=?,last_bill_no=?,cur_status=?,trans_id=?,buyback_id=?,trans_type=?,is_redeem=?,limit_reduce_row=?,owner_party_name=? where discmx_id=?",
	obj.getBillType(),obj.getIssueDt(),obj.getDueDt(),obj.getRemitter(),
	obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),obj.getAcceptor(),
	obj.getAcceptorAcct(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getAcceptorBranchNo(),
	obj.getBillMoney(),obj.getDraweeAddr(),obj.getPayeeBankNo(),obj.getPayee(),
	obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),
	obj.getBillClass(),obj.getBillSource(),obj.getOperStatus(),obj.getDiscId(),
	obj.getRgctId(),obj.getCustNo(),obj.getCustAccount(),obj.getSalerInterest(),
	obj.getDelayDays(),obj.getGaleDate(),obj.getInterestDays(),obj.getBuyerInterest(),
	obj.getPayMoney(),obj.getIsSameCity(),obj.getRemark(),obj.getIsAudited(),
	obj.getConferNo(),obj.getIsAccpt(),obj.getLimitProdNo(),obj.getRate(),
	obj.getRateType(),obj.getDiscDt(),obj.getDiscType(),obj.getFileNo(),
	obj.getNoLouLoans(),obj.getIsOnline(),obj.getRedeemEndDate(),
	obj.getBankProdNo(),obj.getLocalPayMoney(),obj.getIsAmount(),obj.getForbidFlag(),
	obj.getIsThirdAmount(),obj.getOutApplyNo(),obj.getBranchNo(),obj.getOperBankNo(),
	obj.getYzSource(),obj.getProductId(),obj.getOwnerPartyId(),obj.getIsCyc(),
	obj.getIsCancel(),obj.getRedeemOpenDt(),obj.getDiscSquareId(),obj.getInAcctNo(),
	obj.getInBankNo(),obj.getExSerial(),obj.getAuditReason(),obj.getUpdateDt(),
	obj.getUpdateTime(),obj.getAccountDate(),obj.getAccountTime(),obj.getCollDt(),
	obj.getBillNo(),obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),
	obj.getGathMneyType(),obj.getGathMneyDate(),obj.getApplyCommitDate(),obj.getApplyCommitTime(),
	obj.getAuditCommitDate(),obj.getAuditCommitTime(),obj.getAcctCommitDate(),obj.getAcctCommitTime(),
	obj.getTotalIntrstPayment(),obj.getDelayType(),obj.getOrgCode(),obj.getQueryResult(),
	obj.getQueryContent(),obj.getReturnContent(),obj.getReqMsgId(),obj.getInStoreDt(),
	obj.getInStoreTime(),obj.getInvoiceNo(),obj.getDraftPayMoney(),obj.getCollAccountDt(),
	obj.getProtocalNo(),obj.getChangeDt(),obj.getChangeTime(),obj.getLastBillNo(),
	obj.getCurStatus(),obj.getTransId(),obj.getBuybackId(),obj.getTransType(),
	obj.getIsRedeem(),obj.getLimitReduceRow(),obj.getOwnerPartyName(),obj.getDiscmxId());
}

public int modifyDiscBillInfo(DiscBillInfo obj,String discmxId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdisc_bill_info set bill_type=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,acceptor=?,acceptor_acct=?,acceptor_bank_no=?,acceptor_bank_name=?,acceptor_branch_no=?,bill_money=?,drawee_addr=?,payee_bank_no=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,disc_id=?,rgct_id=?,cust_no=?,cust_account=?,saler_interest=?,delay_days=?,gale_date=?,interest_days=?,buyer_interest=?,pay_money=?,is_same_city=?,remark=?,is_audited=?,confer_no=?,is_accpt=?,limit_prod_no=?,rate=?,rate_type=?,disc_dt=?,disc_type=?,file_no=?,no_lou_loans=?,is_online=?,redeem_end_date=?,bank_prod_no=?,local_pay_money=?,is_amount=?,forbid_flag=?,is_third_amount=?,out_apply_no=?,branch_no=?,oper_bank_no=?,yz_source=?,product_id=?,owner_party_id=?,is_cyc=?,is_cancel=?,redeem_open_dt=?,disc_square_id=?,in_acct_no=?,in_bank_no=?,ex_serial=?,audit_reason=?,update_dt=?,update_time=?,account_date=?,account_time=?,coll_dt=?,bill_no=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,gath_mney_type=?,gath_mney_date=?,apply_commit_date=?,apply_commit_time=?,audit_commit_date=?,audit_commit_time=?,acct_commit_date=?,acct_commit_time=?,total_intrst_payment=?,delay_type=?,org_code=?,query_result=?,query_content=?,return_content=?,req_msg_id=?,in_store_dt=?,in_store_time=?,invoice_no=?,draft_pay_money=?,coll_account_dt=?,protocal_no=?,change_dt=?,change_time=?,last_bill_no=?,cur_status=?,trans_id=?,buyback_id=?,trans_type=?,is_redeem=?,limit_reduce_row=?,owner_party_name=? where discmx_id=?",
	obj.getBillType(),obj.getIssueDt(),obj.getDueDt(),obj.getRemitter(),
	obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),obj.getAcceptor(),
	obj.getAcceptorAcct(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getAcceptorBranchNo(),
	obj.getBillMoney(),obj.getDraweeAddr(),obj.getPayeeBankNo(),obj.getPayee(),
	obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),
	obj.getBillClass(),obj.getBillSource(),obj.getOperStatus(),obj.getDiscId(),
	obj.getRgctId(),obj.getCustNo(),obj.getCustAccount(),obj.getSalerInterest(),
	obj.getDelayDays(),obj.getGaleDate(),obj.getInterestDays(),obj.getBuyerInterest(),
	obj.getPayMoney(),obj.getIsSameCity(),obj.getRemark(),obj.getIsAudited(),
	obj.getConferNo(),obj.getIsAccpt(),obj.getLimitProdNo(),obj.getRate(),
	obj.getRateType(),obj.getDiscDt(),obj.getDiscType(),obj.getFileNo(),
	obj.getNoLouLoans(),obj.getIsOnline(),obj.getRedeemEndDate(),
	obj.getBankProdNo(),obj.getLocalPayMoney(),obj.getIsAmount(),obj.getForbidFlag(),
	obj.getIsThirdAmount(),obj.getOutApplyNo(),obj.getBranchNo(),obj.getOperBankNo(),
	obj.getYzSource(),obj.getProductId(),obj.getOwnerPartyId(),obj.getIsCyc(),
	obj.getIsCancel(),obj.getRedeemOpenDt(),obj.getDiscSquareId(),obj.getInAcctNo(),
	obj.getInBankNo(),obj.getExSerial(),obj.getAuditReason(),obj.getUpdateDt(),
	obj.getUpdateTime(),obj.getAccountDate(),obj.getAccountTime(),obj.getCollDt(),
	obj.getBillNo(),obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),
	obj.getGathMneyType(),obj.getGathMneyDate(),obj.getApplyCommitDate(),obj.getApplyCommitTime(),
	obj.getAuditCommitDate(),obj.getAuditCommitTime(),obj.getAcctCommitDate(),obj.getAcctCommitTime(),
	obj.getTotalIntrstPayment(),obj.getDelayType(),obj.getOrgCode(),obj.getQueryResult(),
	obj.getQueryContent(),obj.getReturnContent(),obj.getReqMsgId(),obj.getInStoreDt(),
	obj.getInStoreTime(),obj.getInvoiceNo(),obj.getDraftPayMoney(),obj.getCollAccountDt(),
	obj.getProtocalNo(),obj.getChangeDt(),obj.getChangeTime(),obj.getLastBillNo(),
	obj.getCurStatus(),obj.getTransId(),obj.getBuybackId(),obj.getTransType(),
	obj.getIsRedeem(),obj.getLimitReduceRow(),obj.getOwnerPartyName(),discmxId);
}

public DiscBillInfo getDiscBillInfo(String discmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	DiscBillInfo obj = (DiscBillInfo)session.getObject("select * from tdisc_bill_info where discmx_id=?",DiscBillInfo.class,discmxId);
	return obj;
}

/**
 * 查询批次下清单
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<DiscBillInfo> getDiscBillListForBatch(Page page,DiscSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select bill.* from tdisc_bill_info bill";
	//分页开始位置
	int startIndex = page.getCurrentResult();
	 QueryCondition qc=new QueryCondition();
	 try {
         qc.initFromSearchBean(query);
     } catch (Exception e) {
         e.printStackTrace();
     }
	 String sql=qc.getAllSqlString(baseSql);
     System.out.println(sql);
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("bill.discmx_Id"), qc.getParameterValues());
	page.setTotalResult(count);
	return session.getObjectListByListForPage(sql,DiscBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
}

public List<DiscBillInfo> getElectricReceiveForId(String ids) throws SQLException{
	StringBuffer sql = new StringBuffer("select * from tdisc_bill_info  where discmx_id in (");
	String idArr[] = ids.split(",");
	List<Object> param = new ArrayList<Object>(idArr.length);
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.getObjectListByList(sql.toString(), DiscBillInfo.class, param);
}

public DiscBillInfo getDiscBillInfoByRgctId(String rgctId) throws SQLException {
	IDB session = DBFactory.getDB();
	DiscBillInfo obj = (DiscBillInfo)session.getObject("select * from tdisc_bill_info where rgct_id=? order by discmx_id desc",DiscBillInfo.class,rgctId);
	return obj;
}

public DiscBillInfo getDiscBillInfoByReqDraftId(String reqDraftId) throws SQLException {
	IDB session = DBFactory.getDB();
	DiscBillInfo obj = (DiscBillInfo)session.getObject("select * from tdisc_bill_info where req_msg_id=?",DiscBillInfo.class,reqDraftId);
	return obj;
}

public boolean isInterestTrial(String ids) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select count(0) from tdisc_bill_info bill where bill.delay_type = ' '";
	QueryCondition qc=new QueryCondition();
	qc.add("bill.discmx_id in (:discmxId)",ids.split(","));
	try {
        qc.initFromSearchBean(null);
    } catch (Exception e) {
        e.printStackTrace();
    }
	String sql=qc.getAllSqlString(baseSql);
	// 获得并返回本次查询的总条数
	int count = session.accountByList(sql, qc.getParameterValues());
	return count > 0 ? false : true;
}



/**
 * 功能描述：查询批次下的清单  根据批次id和清单状态
 * @param 批次id String discId,清单状态 String operStatus
 * @return
 * @throws SQLException
 */
public List<DiscBillInfo> getDiscBillListForDiscIdAndStatus(String discId,String operStatus) throws SQLException {
	IDB session = DBFactory.getDB();
	String sql = "select bill.* from tdisc_bill_info bill where bill.disc_id = ? and bill.oper_status = ?";
	return session.getObjectList(sql, DiscBillInfo.class,discId,operStatus);
}

//清空利息等信息
public void modifyBill(DiscSearchBean query) throws SQLException{
	IDB session = DBFactory.getDB();
	session.execute("update tdisc_bill_info set interest_days=0, delay_type=' ',pay_money=0.0,gale_date=' ',delay_days=0,saler_interest=0.0,buyer_interest=0.0 where disc_id=? and oper_status = 'BS012'",query.getDiscId());
}

public int totalCount(DiscBillInfo bill) throws SQLException{
	BeanFormat.format(bill);
	IDB session = DBFactory.getDB();
	return session.account("select count(0) from tdisc_bill_info bill where bill.bill_Class = ? and bill.bill_Type = ? and bill.branch_No = ? and bill.disc_Dt = ? and bill.rate = ? and bill.oper_Status = ? and bill.cust_Account = ? and bill.disc_Id = ?", bill.getBillClass(),bill.getBillType(),bill.getBranchNo(),bill.getDiscDt(),bill.getRate(),bill.getOperStatus(),bill.getCustAccount(),bill.getDiscId());
}

//发生查询（根据界面的查询条件，查询批次和清单）
public List<DiscInfo>  getDiscBillListBystatus(Page page,DiscSearchBean query)throws SQLException{
	QueryCondition qc=new QueryCondition();
	IDB session = DBFactory.getDB();
	String baseSql = "select bill.*,apply.* from tdisc_bill_info bill,tdisc_apply_info apply where bill.disc_id=apply.disc_id";
	try {
		qc.initFromSearchBean(query);
	} catch (Exception e1) {
		e1.printStackTrace();
	}
	String allSql = qc.getAllSqlString(baseSql);
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	int count = session.accountByList(qc.getCountSql("distinct bill.discmx_Id"), qc.getParameterValues()); 
  	 page.setTotalResult(count);
	return session.getBeanListByListForPage(allSql,DiscInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
}

}


