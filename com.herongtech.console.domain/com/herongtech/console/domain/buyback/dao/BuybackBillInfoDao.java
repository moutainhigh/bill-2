/********************************************
* 文件名称: BuybackBillInfoDao.java
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
package com.herongtech.console.domain.buyback.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.bean.BuybackSearchBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class BuybackBillInfoDao{

public int addBuybackBillInfo(BuybackBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tbuyback_bill_info(buybackmx_id,bill_type,bill_no,issue_dt,due_dt,remitter,remitter_acct,remitter_bank_name,remitter_bank_no,bill_money,acceptor,payee_name,payee_bank_name,payee_acct,bill_before_owner,bill_owner,bill_class,bill_source,oper_status,buyback_id,rgct_id,branch_no,cust_account,create_time,is_audited,regress_dt,drawee_addr,payee_bank_no,confer_no,is_accpt,interest,sale_dt,is_dummy,is_inner,aim_branch_no,inner_account,limit_bill_id,limit_prod_no,buy_dept_no,remitter_cust_no,buyback_money,is_online,salemx_id,ex_serial,interest_days,pay_trade_no,account_date,apply_oper_no,audit_oper_no,acct_oper_no,delay_type,delay_days,forbid_flag,sale_receive_money,sale_interest,draft_pay_money,apply_cancel_flag,req_draft_no,acceptor_bank_no,acceptor_bank_name,cur_status,sale_id,buyback_dt)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getBuybackmxId(),obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayeeName(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getBuybackId(),obj.getRgctId(),
	obj.getBranchNo(),obj.getCustAccount(),obj.getCreateTime(),obj.getIsAudited(),
	obj.getRegressDt(),obj.getDraweeAddr(),obj.getPayeeBankNo(),obj.getConferNo(),
	obj.getIsAccpt(),obj.getInterest(),obj.getSaleDt(),obj.getIsDummy(),
	obj.getIsInner(),obj.getAimBranchNo(),obj.getInnerAccount(),obj.getLimitBillId(),
	obj.getLimitProdNo(),obj.getBuyDeptNo(),obj.getRemitterCustNo(),obj.getBuybackMoney(),
	obj.getIsOnline(),obj.getSalemxId(),obj.getExSerial(),obj.getInterestDays(),
	obj.getPayTradeNo(),obj.getAccountDate(),obj.getApplyOperNo(),obj.getAuditOperNo(),
	obj.getAcctOperNo(),obj.getDelayType(),obj.getDelayDays(),obj.getForbidFlag(),
	obj.getSaleReceiveMoney(),obj.getSaleInterest(),obj.getDraftPayMoney(),obj.getApplyCancelFlag(),
	obj.getReqDraftNo(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getCurStatus(),
	obj.getSaleId(),obj.getBuybackDt());
}

public int deleteBuybackBillInfo(BuybackBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tbuyback_bill_info where buybackmx_id=?",obj.getBuybackmxId());
}

public int deleteBuybackBillInfo(String buybackmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tbuyback_bill_info where buybackmx_id=?",buybackmxId);
}

public int modifyBuybackBillInfo(BuybackBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tbuyback_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee_name=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,buyback_id=?,rgct_id=?,branch_no=?,cust_account=?,create_time=?,is_audited=?,regress_dt=?,drawee_addr=?,payee_bank_no=?,confer_no=?,is_accpt=?,interest=?,sale_dt=?,is_dummy=?,is_inner=?,aim_branch_no=?,inner_account=?,limit_bill_id=?,limit_prod_no=?,buy_dept_no=?,remitter_cust_no=?,buyback_money=?,is_online=?,salemx_id=?,ex_serial=?,interest_days=?,pay_trade_no=?,account_date=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,delay_type=?,delay_days=?,forbid_flag=?,sale_receive_money=?,sale_interest=?,draft_pay_money=?,apply_cancel_flag=?,req_draft_no=?,acceptor_bank_no=?,acceptor_bank_name=?,cur_status=?,sale_id=?,buyback_dt=? where buybackmx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayeeName(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getBuybackId(),obj.getRgctId(),
	obj.getBranchNo(),obj.getCustAccount(),obj.getCreateTime(),obj.getIsAudited(),
	obj.getRegressDt(),obj.getDraweeAddr(),obj.getPayeeBankNo(),obj.getConferNo(),
	obj.getIsAccpt(),obj.getInterest(),obj.getSaleDt(),obj.getIsDummy(),
	obj.getIsInner(),obj.getAimBranchNo(),obj.getInnerAccount(),obj.getLimitBillId(),
	obj.getLimitProdNo(),obj.getBuyDeptNo(),obj.getRemitterCustNo(),obj.getBuybackMoney(),
	obj.getIsOnline(),obj.getSalemxId(),obj.getExSerial(),obj.getInterestDays(),
	obj.getPayTradeNo(),obj.getAccountDate(),obj.getApplyOperNo(),obj.getAuditOperNo(),
	obj.getAcctOperNo(),obj.getDelayType(),obj.getDelayDays(),obj.getForbidFlag(),
	obj.getSaleReceiveMoney(),obj.getSaleInterest(),obj.getDraftPayMoney(),obj.getApplyCancelFlag(),
	obj.getReqDraftNo(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getCurStatus(),
	obj.getSaleId(),obj.getBuybackDt(),obj.getBuybackmxId());
}

public int modifyBuybackBillInfo(BuybackBillInfo obj,String buybackmxId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tbuyback_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee_name=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,buyback_id=?,rgct_id=?,branch_no=?,cust_account=?,create_time=?,is_audited=?,regress_dt=?,drawee_addr=?,payee_bank_no=?,confer_no=?,is_accpt=?,interest=?,sale_dt=?,is_dummy=?,is_inner=?,aim_branch_no=?,inner_account=?,limit_bill_id=?,limit_prod_no=?,buy_dept_no=?,remitter_cust_no=?,buyback_money=?,is_online=?,salemx_id=?,ex_serial=?,interest_days=?,pay_trade_no=?,account_date=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,delay_type=?,delay_days=?,forbid_flag=?,sale_receive_money=?,sale_interest=?,draft_pay_money=?,apply_cancel_flag=?,req_draft_no=?,acceptor_bank_no=?,acceptor_bank_name=?,cur_status=?,sale_id=?,buyback_dt=? where buybackmx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayeeName(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getBuybackId(),obj.getRgctId(),
	obj.getBranchNo(),obj.getCustAccount(),obj.getCreateTime(),obj.getIsAudited(),
	obj.getRegressDt(),obj.getDraweeAddr(),obj.getPayeeBankNo(),obj.getConferNo(),
	obj.getIsAccpt(),obj.getInterest(),obj.getSaleDt(),obj.getIsDummy(),
	obj.getIsInner(),obj.getAimBranchNo(),obj.getInnerAccount(),obj.getLimitBillId(),
	obj.getLimitProdNo(),obj.getBuyDeptNo(),obj.getRemitterCustNo(),obj.getBuybackMoney(),
	obj.getIsOnline(),obj.getSalemxId(),obj.getExSerial(),obj.getInterestDays(),
	obj.getPayTradeNo(),obj.getAccountDate(),obj.getApplyOperNo(),obj.getAuditOperNo(),
	obj.getAcctOperNo(),obj.getDelayType(),obj.getDelayDays(),obj.getForbidFlag(),
	obj.getSaleReceiveMoney(),obj.getSaleInterest(),obj.getDraftPayMoney(),obj.getApplyCancelFlag(),
	obj.getReqDraftNo(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getCurStatus(),
	obj.getSaleId(),obj.getBuybackDt(),buybackmxId);
}

public BuybackBillInfo getBuybackBillInfo(String buybackmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	BuybackBillInfo obj = (BuybackBillInfo)session.getObject("select * from tbuyback_bill_info where buybackmx_id=?",BuybackBillInfo.class,buybackmxId);
	return obj;
}
public BuybackBillInfo getBuybackBillInfoForId(String buybackId) throws SQLException {
	IDB session = DBFactory.getDB();
	BuybackBillInfo obj = (BuybackBillInfo)session.getObject("select * from tbuyback_bill_info where buyback_id=?",BuybackBillInfo.class,buybackId);
	return obj;
}

public List<BuybackBillInfo> getBuybackBillList(Page page,BuybackSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="SELECT BILL.* FROM TBUYBACK_BILL_INFO BILL";
	query.setDfaultSrchTbAliasName("BILL");
	query.addSpecialOpertion("mxIds",BaseSearchBean.IN);
	query.addSqlPropretyMapping("mxIds", "buybackmxId");
	 QueryCondition qc=new QueryCondition();
	 try {
         qc.initFromSearchBean(query);
     } catch (Exception e) {
         e.printStackTrace();
     }
	 String sql=qc.getAllSqlString(baseSql);
	if(page == null){
		return session.getObjectListByList(sql,BuybackBillInfo.class,qc.getParameterValues());
	}else{
		//分页开始位置
		int startIndex = page.getCurrentResult();
		// 获得并返回本次查询的总条数
		int count = session.accountByList(qc.getCountSql("BILL.BUYBACKMX_ID"), qc.getParameterValues());
		page.setTotalResult(count);
		return session.getObjectListByListForPage(sql,BuybackBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	
}

/**
 * 功能描述：查询批次下的清单  根据批次id和清单状态
 * @param 批次id String buybackId,清单状态 String operStatus
 * @return
 * @throws SQLException
 */
public List<BuybackBillInfo> getBuybackBillListForBuybackIdAndStatus(String buybackId,String operStatus) throws SQLException {
	IDB session = DBFactory.getDB();
	String sql = "select bill.* from tbuyback_bill_info bill where bill.buyback_id = ? and bill.oper_status = ?";
	return session.getObjectList(sql, BuybackBillInfo.class,buybackId,operStatus);
}

/**
 * 功能描述：清空利息等值
 * @param query
 * @return
 * @throws SQLException
 */
public void clearInterestTrialInfo(BuybackSearchBean query) throws SQLException {
   	IDB session = DBFactory.getDB();
	session.execute("update tbuyback_bill_info set interest = 0.0,interest_days=0,delay_type=' ',buyback_money=0.0,delay_days=0 where sale_id = ?  and oper_status = ?",query.getSaleId(),query.getOperStatus());
}

/**
 * 功能描述：清空利息等值(纸票)
 * @param query
 * @return
 * @throws SQLException
 */
public void clearInterestTrialInfoForEntity(BuybackSearchBean query) throws SQLException {
   	IDB session = DBFactory.getDB();
	session.execute("update tbuyback_bill_info set interest = 0.0,interest_days=0,delay_type=' ',buyback_money=0.0,delay_days=0 where sale_id = ?  and oper_status = ?",query.getSaleId(),query.getOperStatus());
}

/**
 * 功能描述：是否进行过利息试算
 * @param ids
 * @return
 * @throws SQLException
 */
public boolean isInterestTrial(String ids) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select count(0) from tbuyback_bill_info bill where bill.delay_type = ' '";
	QueryCondition qc=new QueryCondition();
	qc.add("bill.buybackmx_id in (:buybackmxId)",ids.split(","));
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

public BuybackBillInfo getBuybackBillInfoByReqDraftNo(String reqDraftNo) throws SQLException {
	IDB session = DBFactory.getDB();
	BuybackBillInfo obj = (BuybackBillInfo)session.getObject("SELECT T.* FROM TBUYBACK_BILL_INFO T WHERE T.REQ_DRAFT_NO = ?",BuybackBillInfo.class,reqDraftNo);
	return obj;
}

public List<BuybackBillInfo> getBuybackBillListByIds(String ids) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="SELECT BILL.* FROM TBUYBACK_BILL_INFO BILL";
	QueryCondition qc=new QueryCondition();
	qc.add("BILL.BUYBACKMX_ID IN (:buybackmxId)",ids.split(","));
	try {
		qc.initFromSearchBean(null);
	} catch (Exception e) {
		e.printStackTrace();
	}
	String sql=qc.getAllSqlString(baseSql);
	return session.getObjectListByList(sql,BuybackBillInfo.class,qc.getParameterValues());
	
}

/**
 * 通过rgctid查询出回购数据清单
 * @param rgctid
 * @return
 * @throws SQLException 
 */
public List<BuybackBillInfo> getBuybackBillInfoByRgctid(String rgctid) throws SQLException{
	IDB session = DBFactory.getDB();
	List<BuybackBillInfo> list = session.getObjectList("select * from tbuyback_bill_info where rgct_id=? order by buybackmx_Id desc",BuybackBillInfo.class,rgctid);
	return list;
}

/**
 * 功能描述：通过salemxId查票据清单
 * @param salemxId
 * @return
 * @throws SQLException
 */
public BuybackBillInfo getBuybackBillInfoBySalemxId(String salemxId) throws SQLException {
	IDB session = DBFactory.getDB();
	BuybackBillInfo obj = (BuybackBillInfo)session.getObject("select * from tbuyback_bill_info where salemx_id=?",BuybackBillInfo.class,salemxId);
	return obj;
}

public BuybackBillInfo getSaleBillInfoByRgcId(String rgctId) throws SQLException{
	IDB session = DBFactory.getDB();
	BuybackBillInfo obj = (BuybackBillInfo)session.getObject("select * from tbuyback_bill_info where rgct_id=? ORDER BY buybackmx_Id DESC",BuybackBillInfo.class,rgctId);
	return obj;
}


}
