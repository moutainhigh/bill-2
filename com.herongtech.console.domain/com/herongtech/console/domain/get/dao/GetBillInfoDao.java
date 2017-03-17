/********************************************
* 文件名称: GetBillInfoDao.java
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
package com.herongtech.console.domain.get.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.get.bean.GetBillInfo;
import com.herongtech.console.domain.get.bean.GetSearchBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class GetBillInfoDao{

public int addGetBillInfo(GetBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tget_bill_info(getmx_id,bill_type,bill_no,issue_dt,due_dt,remitter,remitter_acct,remitter_bank_name,remitter_bank_no,bill_money,acceptor,payee,payee_bank_name,payee_acct,bill_before_owner,bill_owner,bill_class,bill_source,oper_status,get_id,rgct_id,savebill_rela_id,branch_no,prod_no,get_way,create_dt,create_time,is_same_city,is_tc,is_lc,oper_no,cust_manager,dept_no,impawn_bail_account,remitter_addr,protocol_no,sign_remark,is_query,yz_source,in_batch_no,out_batch_no,ex_serial,prod_name,dept_name,cust_manager_name,is_fac,cust_no,cust_name,query_content,return_content,query_date,query_time,query_get_date,query_get_time,query_type,query_status,is_amount,sign_content,audit_content,audit_remark,account_date,apply_oper_no,check_oper_no,account_oper_no,apply_date,apply_time,check_date,check_time,account_time,sign_flag,acceptor_bank_no,acceptor_bank_name,position,audit_reason)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getGetmxId(),obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getGetId(),obj.getRgctId(),
	obj.getSavebillRelaId(),obj.getBranchNo(),obj.getProdNo(),obj.getGetWay(),
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

public int deleteGetBillInfo(GetBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tget_bill_info where getmx_id=?",obj.getGetmxId());
}

public int deleteGetBillInfo(String getmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tget_bill_info where getmx_id=?",getmxId);
}

public int modifyGetBillInfo(GetBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tget_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,get_id=?,rgct_id=?,savebill_rela_id=?,branch_no=?,prod_no=?,get_way=?,create_dt=?,create_time=?,is_same_city=?,is_tc=?,is_lc=?,oper_no=?,cust_manager=?,dept_no=?,impawn_bail_account=?,remitter_addr=?,protocol_no=?,sign_remark=?,is_query=?,yz_source=?,in_batch_no=?,out_batch_no=?,ex_serial=?,prod_name=?,dept_name=?,cust_manager_name=?,is_fac=?,cust_no=?,cust_name=?,query_content=?,return_content=?,query_date=?,query_time=?,query_get_date=?,query_get_time=?,query_type=?,query_status=?,is_amount=?,sign_content=?,audit_content=?,audit_remark=?,account_date=?,apply_oper_no=?,check_oper_no=?,account_oper_no=?,apply_date=?,apply_time=?,check_date=?,check_time=?,account_time=?,sign_flag=?,acceptor_bank_no=?,acceptor_bank_name=?,position=?,audit_reason=? where getmx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getGetId(),obj.getRgctId(),
	obj.getSavebillRelaId(),obj.getBranchNo(),obj.getProdNo(),obj.getGetWay(),
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
	obj.getAuditReason(),obj.getGetmxId());
}

public int modifyGetBillInfo(GetBillInfo obj,String getmxId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tget_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,get_id=?,rgct_id=?,savebill_rela_id=?,branch_no=?,prod_no=?,get_way=?,create_dt=?,create_time=?,is_same_city=?,is_tc=?,is_lc=?,oper_no=?,cust_manager=?,dept_no=?,impawn_bail_account=?,remitter_addr=?,protocol_no=?,sign_remark=?,is_query=?,yz_source=?,in_batch_no=?,out_batch_no=?,ex_serial=?,prod_name=?,dept_name=?,cust_manager_name=?,is_fac=?,cust_no=?,cust_name=?,query_content=?,return_content=?,query_date=?,query_time=?,query_get_date=?,query_get_time=?,query_type=?,query_status=?,is_amount=?,sign_content=?,audit_content=?,audit_remark=?,account_date=?,apply_oper_no=?,check_oper_no=?,account_oper_no=?,apply_date=?,apply_time=?,check_date=?,check_time=?,account_time=?,sign_flag=?,acceptor_bank_no=?,acceptor_bank_name=?,position=?,audit_reason=? where getmx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayee(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperStatus(),obj.getGetId(),obj.getRgctId(),
	obj.getSavebillRelaId(),obj.getBranchNo(),obj.getProdNo(),obj.getGetWay(),
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
	obj.getAuditReason(),getmxId);
}
/**
 * 功能描述：根据明细ids查询明细清单列表
 * @return
 * @throws SQLException
 */
public List<GetBillInfo> getGetBillListByMxids(String[] ids) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select bill.* from tget_bill_info bill where bill.getmx_id in(");
	for(int i=0;i<ids.length-1;i++){
		sb.append("'"+ids[i]+"'"+",");
	}
	sb.append("'"+ids[ids.length-1]+"')");
	String sql = sb.toString();
	System.out.println(sql);
	List<GetBillInfo> list = session.getObjectList(sql,GetBillInfo.class);
	return list;
}
public GetBillInfo getGetBillInfo(String getmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	GetBillInfo obj = (GetBillInfo)session.getObject("select * from tget_bill_info where getmx_id=?",GetBillInfo.class,getmxId);
	return obj;
}

public int totalCount(GetBillInfo bill) throws SQLException{
	BeanFormat.format(bill);
	IDB session = DBFactory.getDB();
	return session.account("select count(0) from tget_bill_info bill where bill.bill_Class = ? and bill.bill_Type = ? and bill.oper_Status = ? and bill.cust_No = ? and bill.get_Id = ?", bill.getBillClass(),bill.getBillType(),bill.getOperStatus(),bill.getCustNo(),bill.getGetId());
}

/**
 * 查询清单(申请 审核 入库)/电票未签收
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
  public List<GetBillInfo> getGetBillListBySearchBean(Page page, GetSearchBean query)
    throws SQLException
  {
    IDB session = DBFactory.getDB();
    String baseSql = "select bill.* from tget_bill_info bill";
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
    int count = session.accountByList(qc.getCountSql("bill.getmx_Id"), qc.getParameterValues());
    page.setTotalResult(count);
    return session.getObjectListByListForPage(sql, GetBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
  }
  
  /**
   * 根据getId状态查询清单
   *
   */
  public List<GetBillInfo> getGetBillListForGetIdStatus(String getId,String operStatus) throws SQLException {
		IDB session = DBFactory.getDB();
		String sql = "select bill.* from tget_bill_info bill where bill.get_id = ? and bill.oper_status = ?";
		return session.getObjectList(sql, GetBillInfo.class,getId,operStatus);
	}
  
  /**
   * 功能描述：通过票据中心ID查票据清单
   * @param rgctId
   * @return
   * @throws SQLException
   */
  public GetBillInfo getGetBillInfoByRgctId(String rgctId) throws SQLException {
  	IDB session = DBFactory.getDB();
  	GetBillInfo obj = (GetBillInfo)session.getObject("select * from tget_bill_info where rgct_id=? order by getmx_id desc",GetBillInfo.class,rgctId);
  	return obj;
  }
  /**
   * 根据主键字符串获得集合
   * @param getmxIds
   * @return
   * @throws SQLException
   */
  public List<GetBillInfo> getGetBillInfolist(String getmxIds) throws SQLException {
  	IDB session = DBFactory.getDB();
  	List<GetBillInfo> obj = session.getObjectList("select * from tget_bill_info where getmx_id in (?)",GetBillInfo.class,getmxIds);
  	return obj;
  }

}
