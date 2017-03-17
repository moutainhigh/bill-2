/********************************************
* 文件名称: RebuyBillInfoDao.java
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
package com.herongtech.console.domain.rebuy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class RebuyBillInfoDao{

public int addRebuyBillInfo(RebuyBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trebuy_bill_info(rebuymx_id,bill_type,bill_no,issue_dt,due_dt,remitter,remitter_acct,remitter_bank_name,remitter_bank_no,bill_money,acceptor,payee,payee_bank_name,payee_acct,bill_before_owner,bill_owner,bill_class,bill_source,oper_status,rebuy_id,rgct_id,cust_account,is_same_city,interest,delay_days,gale_date,interest_days,pay_money,remark,create_date,create_time,is_audited,drawee_addr,payee_bank_no,confer_no,is_accpt,is_inner,limit_bill_id,limit_prod_no,rate,rate_type,remitter_cust_no,channel,is_delay_in,sale_id,is_online,forbid_flag,rebuy_dt,resale_sta_dt,resale_due_dt,limit_type,limit_no,bill_storage_org,yz_source,prod_no,owner_party_id,is_cyc,product_name,owner_party_name,is_cyc_name,ex_serial,is_buyback,rebuy_square_id,is_redisc_center,pay_trade_no,begin_date,check_interest,cust_no,cust_bank_no,cust_bank_name,delay_type,is_regress,fac_msg,is_amount,endorses_bank_no,fac_oper_type,coll_date,account_date,account_time,total_intrst_payment,gath_date,gath_type,endorses_bank_name,salemx_id,check_pay_money,adjust_flag,interest_saleback1,paymoney_saleback1,apply_cancel_flag,req_draft_no,apply_oper_no,audit_oper_no,acct_oper_no,cust_org_code,position,cust_type,acceptor_bank_no,acceptor_bank_name,cur_status,trans_id,trans_type,buyback_id,is_elec_deposit,zc_source,zc_source_id,limit_reduce_row,apply_commit_date,apply_commit_time,branch_no,oper_bank_no,audit_reason)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getRebuymxId(),obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getRebuyId(),obj.getRgctId(),
	obj.getCustAccount(),obj.getIsSameCity(),obj.getInterest(),obj.getDelayDays(),
	obj.getGaleDate(),obj.getInterestDays(),obj.getPayMoney(),obj.getRemark(),
	obj.getCreateDate(),obj.getCreateTime(),obj.getIsAudited(),obj.getDraweeAddr(),
	obj.getPayeeBankNo(),obj.getConferNo(),obj.getIsAccpt(),obj.getIsInner(),
	obj.getLimitBillId(),obj.getLimitProdNo(),obj.getRate(),obj.getRateType(),
	obj.getRemitterCustNo(),obj.getChannel(),obj.getIsDelayIn(),obj.getSaleId(),
	obj.getIsOnline(),obj.getForbidFlag(),obj.getRebuyDt(),obj.getResaleStaDt(),
	obj.getResaleDueDt(),obj.getLimitType(),obj.getLimitNo(),obj.getBillStorageOrg(),
	obj.getYzSource(),obj.getProdNo(),obj.getOwnerPartyId(),obj.getIsCyc(),
	obj.getProductName(),obj.getOwnerPartyName(),obj.getIsCycName(),obj.getExSerial(),
	obj.getIsBuyback(),obj.getRebuySquareId(),obj.getIsRediscCenter(),obj.getPayTradeNo(),
	obj.getBeginDate(),obj.getCheckInterest(),obj.getCustNo(),obj.getCustBankNo(),
	obj.getCustBankName(),obj.getDelayType(),obj.getIsRegress(),obj.getFacMsg(),
	obj.getIsAmount(),obj.getEndorsesBankNo(),obj.getFacOperType(),obj.getCollDate(),
	obj.getAccountDate(),obj.getAccountTime(),obj.getTotalIntrstPayment(),obj.getGathDate(),
	obj.getGathType(),obj.getEndorsesBankName(),obj.getSalemxId(),obj.getCheckPayMoney(),
	obj.getAdjustFlag(),obj.getInterestSaleback1(),obj.getPaymoneySaleback1(),obj.getApplyCancelFlag(),
	obj.getReqDraftNo(),obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),
	obj.getCustOrgCode(),obj.getPosition(),obj.getCustType(),obj.getAcceptorBankNo(),
	obj.getAcceptorBankName(),obj.getCurStatus(),obj.getTransId(),obj.getTransType(),
	obj.getBuybackId(),obj.getIsElecDeposit(),obj.getZcSource(),obj.getZcSourceId(),
	obj.getLimitReduceRow(),obj.getApplyCommitDate(),obj.getApplyCommitTime(),obj.getBranchNo(),
	obj.getOperBankNo(),obj.getAuditReason());
}

public int deleteRebuyBillInfo(RebuyBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trebuy_bill_info where rebuymx_id=?",obj.getRebuymxId());
}

public int modifyRebuyBillInfo(RebuyBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trebuy_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,rebuy_id=?,rgct_id=?,cust_account=?,is_same_city=?,interest=?,delay_days=?,gale_date=?,interest_days=?,pay_money=?,remark=?,create_date=?,create_time=?,is_audited=?,drawee_addr=?,payee_bank_no=?,confer_no=?,is_accpt=?,is_inner=?,limit_bill_id=?,limit_prod_no=?,rate=?,rate_type=?,remitter_cust_no=?,channel=?,is_delay_in=?,sale_id=?,is_online=?,forbid_flag=?,rebuy_dt=?,resale_sta_dt=?,resale_due_dt=?,limit_type=?,limit_no=?,bill_storage_org=?,yz_source=?,prod_no=?,owner_party_id=?,is_cyc=?,product_name=?,owner_party_name=?,is_cyc_name=?,ex_serial=?,is_buyback=?,rebuy_square_id=?,is_redisc_center=?,pay_trade_no=?,begin_date=?,check_interest=?,cust_no=?,cust_bank_no=?,cust_bank_name=?,delay_type=?,is_regress=?,fac_msg=?,is_amount=?,endorses_bank_no=?,fac_oper_type=?,coll_date=?,account_date=?,account_time=?,total_intrst_payment=?,gath_date=?,gath_type=?,endorses_bank_name=?,salemx_id=?,check_pay_money=?,adjust_flag=?,interest_saleback1=?,paymoney_saleback1=?,apply_cancel_flag=?,req_draft_no=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,cust_org_code=?,position=?,cust_type=?,acceptor_bank_no=?,acceptor_bank_name=?,cur_status=?,trans_id=?,trans_type=?,buyback_id=?,is_elec_deposit=?,zc_source=?,zc_source_id=?,limit_reduce_row=?,apply_commit_date=?,apply_commit_time=?,branch_no=?,oper_bank_no=?,audit_reason=? where rebuymx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getRebuyId(),obj.getRgctId(),
	obj.getCustAccount(),obj.getIsSameCity(),obj.getInterest(),obj.getDelayDays(),
	obj.getGaleDate(),obj.getInterestDays(),obj.getPayMoney(),obj.getRemark(),
	obj.getCreateDate(),obj.getCreateTime(),obj.getIsAudited(),obj.getDraweeAddr(),
	obj.getPayeeBankNo(),obj.getConferNo(),obj.getIsAccpt(),obj.getIsInner(),
	obj.getLimitBillId(),obj.getLimitProdNo(),obj.getRate(),obj.getRateType(),
	obj.getRemitterCustNo(),obj.getChannel(),obj.getIsDelayIn(),obj.getSaleId(),
	obj.getIsOnline(),obj.getForbidFlag(),obj.getRebuyDt(),obj.getResaleStaDt(),
	obj.getResaleDueDt(),obj.getLimitType(),obj.getLimitNo(),obj.getBillStorageOrg(),
	obj.getYzSource(),obj.getProdNo(),obj.getOwnerPartyId(),obj.getIsCyc(),
	obj.getProductName(),obj.getOwnerPartyName(),obj.getIsCycName(),obj.getExSerial(),
	obj.getIsBuyback(),obj.getRebuySquareId(),obj.getIsRediscCenter(),obj.getPayTradeNo(),
	obj.getBeginDate(),obj.getCheckInterest(),obj.getCustNo(),obj.getCustBankNo(),
	obj.getCustBankName(),obj.getDelayType(),obj.getIsRegress(),obj.getFacMsg(),
	obj.getIsAmount(),obj.getEndorsesBankNo(),obj.getFacOperType(),obj.getCollDate(),
	obj.getAccountDate(),obj.getAccountTime(),obj.getTotalIntrstPayment(),obj.getGathDate(),
	obj.getGathType(),obj.getEndorsesBankName(),obj.getSalemxId(),obj.getCheckPayMoney(),
	obj.getAdjustFlag(),obj.getInterestSaleback1(),obj.getPaymoneySaleback1(),obj.getApplyCancelFlag(),
	obj.getReqDraftNo(),obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),
	obj.getCustOrgCode(),obj.getPosition(),obj.getCustType(),obj.getAcceptorBankNo(),
	obj.getAcceptorBankName(),obj.getCurStatus(),obj.getTransId(),obj.getTransType(),
	obj.getBuybackId(),obj.getIsElecDeposit(),obj.getZcSource(),obj.getZcSourceId(),
	obj.getLimitReduceRow(),obj.getApplyCommitDate(),obj.getApplyCommitTime(),obj.getBranchNo(),
	obj.getOperBankNo(),obj.getAuditReason(),obj.getRebuymxId());
}

public RebuyBillInfo getRebuyBillInfo(String rebuymxId) throws SQLException {
	IDB session = DBFactory.getDB();
	RebuyBillInfo obj = (RebuyBillInfo)session.getObject("select * from trebuy_bill_info where rebuymx_id=?",RebuyBillInfo.class,rebuymxId);
	return obj;
}

/**
 * 根据批次id查询该批次下的票据数
 * @param rebuyId
 * @return
 * @throws SQLException
 */
public int getBillCountByRebuyId(String rebuyId) throws SQLException {
    IDB session = DBFactory.getDB();
    return session.account("select count(bill.rebuymx_id) from trebuy_bill_info bill where bill.rebuy_id=?", rebuyId);
}

/**
 * 根据searchBean查询清单信息列表（不分页）
 * @param searchBean
 * @return
 * @throws SQLException
 */
public List<RebuyBillInfo> getRebuyBillListBySearchBean(RebuySearchBean searchBean) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select bill.* from trebuy_bill_info bill where 1=1";
	 QueryCondition qc=new QueryCondition();
     try {
         qc.initFromSearchBean(searchBean);
     } catch (Exception e) {
         e.printStackTrace();
     }
	String allsql=qc.getAllSqlString(baseSql);
	List<RebuyBillInfo> billList=null;
	// 获得并返回本次查询的总条数
	if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
	    billList=session.getObjectList(allsql, RebuyBillInfo.class);
	}else{
	    billList=session.getObjectListByList(allsql,RebuyBillInfo.class, qc.getParameterValues());
	}
	return billList;
}

/**
 * 根据searchBean查询清单信息列表（分页）
 * @param searchBean
 * @param page
 * @return
 * @throws SQLException
 */
public List<RebuyBillInfo> getRebuyBillListBySearchBean(RebuySearchBean searchBean, Page page) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select bill.* from trebuy_bill_info bill where 1=1";
	 QueryCondition qc=new QueryCondition();
     try {
         qc.initFromSearchBean(searchBean);
     } catch (Exception e) {
         e.printStackTrace();
     }
	String allsql=qc.getAllSqlString(baseSql);
	System.out.println(allsql);
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	int count =0;
	List<RebuyBillInfo> billList=null;
	// 获得并返回本次查询的总条数
	if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
	    count = session.account(qc.getCountSql("distinct bill.rebuymx_id"));
	    billList=session.getObjectListForPage(allsql, RebuyBillInfo.class,startIndex, page.getShowCount());
	}else{
	    count = session.accountByList(qc.getCountSql("distinct bill.rebuymx_id"), qc.getParameterValues()); 
	    billList=session.getObjectListByListForPage(allsql,RebuyBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	page.setTotalResult(count);

	return billList;
}

public List<RebuyBillInfo> getRebuyBillListByIds(String[] ids) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select * from trebuy_bill_info where rebuymx_id in(");
	for(int i=0;i<ids.length-1;i++){
		sb.append("'"+ids[i]+"'"+",");
	}
	sb.append("'"+ids[ids.length-1]+"')");
	String sql = sb.toString();
	List<RebuyBillInfo> list = session.getObjectList(sql,RebuyBillInfo.class);
	return list;
}

/**
 * 是否已计算利息
 * @param ids
 * @return
 * @throws SQLException
 */
public boolean isInterestCalculated(String[] ids) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select count(0) from trebuy_bill_info bill where bill.delay_type = ' '";
	QueryCondition qc=new QueryCondition();
	qc.add("bill.rebuymx_id in (:rebuymxId)",ids);
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
 * 根据RebuySearchBean查询复核条件的票据张数
 * @param searchBean
 * @return
 * @throws SQLException 
 */
public int getRebuyBillCountBySearchBean(RebuySearchBean searchBean) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql = "select * from trebuy_bill_info bill where 1=1";
	QueryCondition qc = new QueryCondition();
	try {
		qc.initFromSearchBean(searchBean);
	} catch (Exception e) {
		e.printStackTrace();
	}
	String allsql=qc.getAllSqlString(baseSql);
	int count;
	if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
	    count = session.account(qc.getCountSql("distinct bill.rebuymx_id"));
	}else{
	    count = session.accountByList(qc.getCountSql("distinct bill.rebuymx_id"), qc.getParameterValues()); 
	}
	return count;
}

/**
 * 功能描述：通过票据中心ID查票据清单
 * @param rgctId
 * @return
 * @throws SQLException
 */
public RebuyBillInfo getRebuyBillInfoByRgctId(String rgctId) throws SQLException {
	IDB session = DBFactory.getDB();
	RebuyBillInfo obj = (RebuyBillInfo)session.getObject("select * from trebuy_bill_info where rgct_id=? order by rebuymx_id desc",RebuyBillInfo.class,rgctId);
	return obj;
}


/**
 * 对方已撤销申请的票据状态查询
 * @param idArr
 * @return
 * @throws SQLException
 */
public List<RebuyBillInfo> findCanceledBillByIds(String[] idArr) throws SQLException {
	if(idArr == null || idArr.length == 0){
		return null;
	}
	IDB session = DBFactory.getDB();
	StringBuffer sb =new StringBuffer("select * from trebuy_bill_info bill where bill.rebuymx_id in (");
	for(int i=0;i<idArr.length-1;i++){
		sb.append("'"+idArr[i]+"'"+",");
	}
	sb.append("'"+idArr[idArr.length-1]+"')");
	sb.append(" and apply_cancel_flag = '" + RebuyCodeConst.APPLY_CANCEL_FLAG_YES + "' order by rebuymx_id desc");
	String sql = sb.toString();
	List<RebuyBillInfo> list = session.getObjectList(sql,RebuyBillInfo.class);
	return list;
}

/**根据票据discmxIds获得集合*/
public List<RebuyBillInfo> getEntitytricReceiveForId(String discmxIds) throws SQLException{
	String sql ="select t.* from trebuy_bill_info t where t.rebuymx_id in (?)";
	IDB session = DBFactory.getDB();
	return session.getObjectList(sql, RebuyBillInfo.class, discmxIds);
}

/**
 * 根据rgctId和reqDraftNo获取RebuyBillInfo对象
 * @param id
 * @param reqDraftId
 * @return
 * @throws SQLException
 */
public RebuyBillInfo findRebuyBillByRgctIdAndDraftLogId(String id, String reqDraftId) throws SQLException {
	String sql ="select t.* from trebuy_bill_info t where t.rgct_id = ? and t.req_draft_no = ? ";
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(id);
	paramList.add(reqDraftId);
	IDB session = DBFactory.getDB();
	return session.getObjectByList(sql, RebuyBillInfo.class, paramList);
}

public void modifyRebuyBillInfoList(List<RebuyBillInfo> list) throws SQLException{
	for(RebuyBillInfo bill:list){
		this.modifyRebuyBillInfo(bill);
	}
}
}
