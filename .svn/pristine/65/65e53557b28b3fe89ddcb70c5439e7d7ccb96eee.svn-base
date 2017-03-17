/********************************************
* 文件名称: OutBillInfoDao.java
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
package com.herongtech.console.domain.out.dao;

import java.lang.*;
import java.math.*;

import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctBillInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.out.bean.OutBillInfo;
import com.herongtech.console.domain.out.bean.OutSearchBean;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
public class OutBillInfoDao{

public int addOutBillInfo(OutBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tout_bill_info(outmx_id,bill_type,bill_no,issue_dt,due_dt,remitter,remitter_acct,remitter_bank_name,remitter_bank_no,bill_money,acceptor,payee,payee_bank_name,payee_acct,bill_before_owner,bill_owner,bill_class,bill_source,oper_status,out_id,rgct_id,intobill_rela_id,branch_no,prod_no,get_way,create_dt,create_time,is_same_city,is_tc,is_lc,oper_no,cust_manager,dept_no,impawn_bail_account,remitter_addr,protocol_no,sign_remark,is_query,yz_source,in_batch_no,out_batch_no,ex_serial,prod_name,dept_name,cust_manager_name,is_fac,cust_no,cust_name,query_content,return_content,query_date,query_time,query_get_date,query_get_time,query_type,query_status,is_amount,sign_content,audit_content,audit_remark,account_date,apply_oper_no,check_oper_no,account_oper_no,apply_date,apply_time,check_date,check_time,account_time,sign_flag,acceptor_bank_no,acceptor_bank_name,position,audit_reason)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getOutmxId(),obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getOutId(),obj.getRgctId(),
	obj.getIntobillRelaId(),obj.getBranchNo(),obj.getProdNo(),obj.getGetWay(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getIsSameCity(),obj.getIsTc(),
	obj.getIsLc(),obj.getOperNo(),obj.getCustManager(),obj.getDeptNo(),
	obj.getImpawnBailAccount(),obj.getRemitterAddr(),obj.getProtocolNo(),obj.getSignRemark(),
	obj.getIsQuery(),obj.getYzSource(),obj.getInBatchNo(),obj.getOutBatchNo(),
	obj.getExSerial(),obj.getProdName(),obj.getDeptName(),obj.getCustManagerName(),
	obj.getIsFac(),obj.getCustNo(),obj.getCustName(),obj.getQueryContent(),
	obj.getReturnContent(),obj.getQueryDate(),obj.getQueryTime(),obj.getQueryGetDate(),
	obj.getQueryGetTime(),obj.getQueryType(),obj.getQueryStatus(),obj.getIsAmount(),
	obj.getSignContent(),obj.getAuditContent(),obj.getAuditRemark(),obj.getAccountDate(),
	obj.getApplyOperNo(),obj.getCheckOperNo(),obj.getAccountOperNo(),obj.getApplyDate(),
	obj.getApplyTime(),obj.getCheckDate(),obj.getCheckTime(),obj.getAccountTime(),
	obj.getSignFlag(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getPosition(),
	obj.getAuditReason());
}

public int deleteOutBillInfo(OutBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tout_bill_info where outmx_id=?",obj.getOutmxId());
}

public int deleteOutBillInfo(String outmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tout_bill_info where outmx_id=?",outmxId);
}

public int modifyOutBillInfo(OutBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tout_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,out_id=?,rgct_id=?,intobill_rela_id=?,branch_no=?,prod_no=?,get_way=?,create_dt=?,create_time=?,is_same_city=?,is_tc=?,is_lc=?,oper_no=?,cust_manager=?,dept_no=?,impawn_bail_account=?,remitter_addr=?,protocol_no=?,sign_remark=?,is_query=?,yz_source=?,in_batch_no=?,out_batch_no=?,ex_serial=?,prod_name=?,dept_name=?,cust_manager_name=?,is_fac=?,cust_no=?,cust_name=?,query_content=?,return_content=?,query_date=?,query_time=?,query_get_date=?,query_get_time=?,query_type=?,query_status=?,is_amount=?,sign_content=?,audit_content=?,audit_remark=?,account_date=?,apply_oper_no=?,check_oper_no=?,account_oper_no=?,apply_date=?,apply_time=?,check_date=?,check_time=?,account_time=?,sign_flag=?,acceptor_bank_no=?,acceptor_bank_name=?,position=?,audit_reason=? where outmx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getOutId(),obj.getRgctId(),
	obj.getIntobillRelaId(),obj.getBranchNo(),obj.getProdNo(),obj.getGetWay(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getIsSameCity(),obj.getIsTc(),
	obj.getIsLc(),obj.getOperNo(),obj.getCustManager(),obj.getDeptNo(),
	obj.getImpawnBailAccount(),obj.getRemitterAddr(),obj.getProtocolNo(),obj.getSignRemark(),
	obj.getIsQuery(),obj.getYzSource(),obj.getInBatchNo(),obj.getOutBatchNo(),
	obj.getExSerial(),obj.getProdName(),obj.getDeptName(),obj.getCustManagerName(),
	obj.getIsFac(),obj.getCustNo(),obj.getCustName(),obj.getQueryContent(),
	obj.getReturnContent(),obj.getQueryDate(),obj.getQueryTime(),obj.getQueryGetDate(),
	obj.getQueryGetTime(),obj.getQueryType(),obj.getQueryStatus(),obj.getIsAmount(),
	obj.getSignContent(),obj.getAuditContent(),obj.getAuditRemark(),obj.getAccountDate(),
	obj.getApplyOperNo(),obj.getCheckOperNo(),obj.getAccountOperNo(),obj.getApplyDate(),
	obj.getApplyTime(),obj.getCheckDate(),obj.getCheckTime(),obj.getAccountTime(),
	obj.getSignFlag(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getPosition(),
	obj.getAuditReason(),obj.getOutmxId());
}

public int modifyOutBillInfo(OutBillInfo obj,String outmxId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tout_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,out_id=?,rgct_id=?,intobill_rela_id=?,branch_no=?,prod_no=?,get_way=?,create_dt=?,create_time=?,is_same_city=?,is_tc=?,is_lc=?,oper_no=?,cust_manager=?,dept_no=?,impawn_bail_account=?,remitter_addr=?,protocol_no=?,sign_remark=?,is_query=?,yz_source=?,in_batch_no=?,out_batch_no=?,ex_serial=?,prod_name=?,dept_name=?,cust_manager_name=?,is_fac=?,cust_no=?,cust_name=?,query_content=?,return_content=?,query_date=?,query_time=?,query_get_date=?,query_get_time=?,query_type=?,query_status=?,is_amount=?,sign_content=?,audit_content=?,audit_remark=?,account_date=?,apply_oper_no=?,check_oper_no=?,account_oper_no=?,apply_date=?,apply_time=?,check_date=?,check_time=?,account_time=?,sign_flag=?,acceptor_bank_no=?,acceptor_bank_name=?,position=?,audit_reason=? where outmx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getOutId(),obj.getRgctId(),
	obj.getIntobillRelaId(),obj.getBranchNo(),obj.getProdNo(),obj.getGetWay(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getIsSameCity(),obj.getIsTc(),
	obj.getIsLc(),obj.getOperNo(),obj.getCustManager(),obj.getDeptNo(),
	obj.getImpawnBailAccount(),obj.getRemitterAddr(),obj.getProtocolNo(),obj.getSignRemark(),
	obj.getIsQuery(),obj.getYzSource(),obj.getInBatchNo(),obj.getOutBatchNo(),
	obj.getExSerial(),obj.getProdName(),obj.getDeptName(),obj.getCustManagerName(),
	obj.getIsFac(),obj.getCustNo(),obj.getCustName(),obj.getQueryContent(),
	obj.getReturnContent(),obj.getQueryDate(),obj.getQueryTime(),obj.getQueryGetDate(),
	obj.getQueryGetTime(),obj.getQueryType(),obj.getQueryStatus(),obj.getIsAmount(),
	obj.getSignContent(),obj.getAuditContent(),obj.getAuditRemark(),obj.getAccountDate(),
	obj.getApplyOperNo(),obj.getCheckOperNo(),obj.getAccountOperNo(),obj.getApplyDate(),
	obj.getApplyTime(),obj.getCheckDate(),obj.getCheckTime(),obj.getAccountTime(),
	obj.getSignFlag(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getPosition(),
	obj.getAuditReason(),outmxId);
}

public OutBillInfo getOutBillInfo(String outmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	OutBillInfo obj = (OutBillInfo)session.getObject("select * from tout_bill_info where outmx_id=?",OutBillInfo.class,outmxId);
	return obj;
}

public int totalCount(OutBillInfo bill) throws SQLException{
	BeanFormat.format(bill);
	IDB session = DBFactory.getDB();
	return session.account("select count(0) from tout_bill_info bill where bill.bill_Class = ? and bill.bill_Type = ? and bill.oper_Status = ? and bill.cust_No = ? and bill.out_Id = ?", bill.getBillClass(),bill.getBillType(),bill.getOperStatus(),bill.getCustNo(),bill.getOutId());
}

/**
 * 查询清单(申请 审核 入库)/电票未签收
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
  public List<OutBillInfo> getOutBillListBySearchBean(Page page, OutSearchBean query)
    throws SQLException
  {
    IDB session = DBFactory.getDB();
    String baseSql = "select bill.* from tout_bill_info bill";
    //分页开始位置
    QueryCondition qc = new QueryCondition();
    int startIndex = page.getCurrentResult();
    try {
      qc.initFromSearchBean(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String sql = qc.getAllSqlString(baseSql);
    System.out.println(sql);
    // 获得并返回本次查询的总条数
    int count = session.accountByList(qc.getCountSql("bill.outmx_Id"), qc.getParameterValues());
    page.setTotalResult(count);
    return session.getObjectListByListForPage(sql, OutBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
  }
  
  /**
   * 根据outId状态查询清单
   *
   */
  public List<OutBillInfo> getOutBillListForOutIdStatus(String outId,String operStatus) throws SQLException {
		IDB session = DBFactory.getDB();
		String sql = "select bill.* from tout_bill_info bill where bill.out_id = ? and bill.oper_status = ?";
		return session.getObjectList(sql, OutBillInfo.class,outId,operStatus);
	}
  
  /**
   * 功能描述：通过票据中心ID查票据清单
   * @param rgctId
   * @return
   * @throws SQLException
   */
  public OutBillInfo getOutBillInfoByRgctId(String rgctId) throws SQLException {
  	IDB session = DBFactory.getDB();
  	OutBillInfo obj = (OutBillInfo)session.getObject("select * from tout_bill_info where rgct_id=? order by outmx_id desc",OutBillInfo.class,rgctId);
  	return obj;
  }
  

}
