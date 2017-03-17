/********************************************
* 文件名称: IntoBillInfoDao.java
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
package com.herongtech.console.domain.into.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.into.bean.IntoBillInfo;
import com.herongtech.console.domain.into.bean.IntoSearchBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class IntoBillInfoDao{

	public int addIntoBillInfo(IntoBillInfo obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("insert into tinto_bill_info(intomx_id,bill_type,bill_no,issue_dt,due_dt,remitter,remitter_acct,remitter_bank_name,remitter_bank_no,bill_money,acceptor,branch_no,payee,payee_bank_name,payee_acct,bill_before_owner,bill_class,bill_source,oper_no,oper_status,into_id,rgct_id,prod_no,edu_type,query_status,create_dt,create_time,is_tc,sign_remark,if_sign_flag,cust_manager,dept_no,settle_acct,remitter_addr,is_amount,accept_protocol_no,audit_remark,query_type,query_date,query_time,query_get_date,query_get_time,forbid_flag,yz_source,if_tj,ex_serial,prod_name,is_fac,cust_no,cust_name,is_cyc,owner_party_name,org_code,query_content,return_content,audit_content,sign_content,dept_name,cust_manager_name,coll_date,account_date,apply_prod_no,apply_prod_name,apply_oper_no,check_oper_no,account_oper_no,apply_date,apply_time,check_date,check_time,account_time,req_draft_no,apply_cancel_flag,ebs_no,acceptor_bank_no,acceptor_bank_name,position,gath_type,gath_date,limit_reduce_row,owner_party_id,audit_reason,remark,req_msg_id)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
		obj.getIntomxId(),obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
		obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
		obj.getBillMoney(),obj.getAcceptor(),obj.getBranchNo(),obj.getPayee(),
		obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillClass(),
		obj.getBillSource(),obj.getOperNo(),obj.getOperStatus(),obj.getIntoId(),
		obj.getRgctId(),obj.getProdNo(),obj.getEduType(),obj.getQueryStatus(),
		obj.getCreateDt(),obj.getCreateTime(),obj.getIsTc(),obj.getSignRemark(),
		obj.getIfSignFlag(),obj.getCustManager(),obj.getDeptNo(),obj.getSettleAcct(),
		obj.getRemitterAddr(),obj.getIsAmount(),obj.getAcceptProtocolNo(),obj.getAuditRemark(),
		obj.getQueryType(),obj.getQueryDate(),obj.getQueryTime(),obj.getQueryGetDate(),
		obj.getQueryGetTime(),obj.getForbidFlag(),obj.getYzSource(),obj.getIfTj(),
		obj.getExSerial(),obj.getProdName(),obj.getIsFac(),obj.getCustNo(),
		obj.getCustName(),obj.getIsCyc(),obj.getOwnerPartyName(),obj.getOrgCode(),
		obj.getQueryContent(),obj.getReturnContent(),obj.getAuditContent(),obj.getSignContent(),
		obj.getDeptName(),obj.getCustManagerName(),obj.getCollDate(),obj.getAccountDate(),
		obj.getApplyProdNo(),obj.getApplyProdName(),obj.getApplyOperNo(),obj.getCheckOperNo(),
		obj.getAccountOperNo(),obj.getApplyDate(),obj.getApplyTime(),obj.getCheckDate(),
		obj.getCheckTime(),obj.getAccountTime(),obj.getReqDraftNo(),obj.getApplyCancelFlag(),
		obj.getEbsNo(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getPosition(),
		obj.getGathType(),obj.getGathDate(),obj.getLimitReduceRow(),obj.getOwnerPartyId(),
		obj.getAuditReason(),obj.getRemark(),obj.getReqMsgId());
	}

	public int deleteIntoBillInfo(IntoBillInfo obj) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from tinto_bill_info where intomx_id=?",obj.getIntomxId());
	}

	public int deleteIntoBillInfo(String intomxId) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from tinto_bill_info where intomx_id=?",intomxId);
	}

	public int modifyIntoBillInfo(IntoBillInfo obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update tinto_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,branch_no=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_class=?,bill_source=?,oper_no=?,oper_status=?,into_id=?,rgct_id=?,prod_no=?,edu_type=?,query_status=?,create_dt=?,create_time=?,is_tc=?,sign_remark=?,if_sign_flag=?,cust_manager=?,dept_no=?,settle_acct=?,remitter_addr=?,is_amount=?,accept_protocol_no=?,audit_remark=?,query_type=?,query_date=?,query_time=?,query_get_date=?,query_get_time=?,forbid_flag=?,yz_source=?,if_tj=?,ex_serial=?,prod_name=?,is_fac=?,cust_no=?,cust_name=?,is_cyc=?,owner_party_name=?,org_code=?,query_content=?,return_content=?,audit_content=?,sign_content=?,dept_name=?,cust_manager_name=?,coll_date=?,account_date=?,apply_prod_no=?,apply_prod_name=?,apply_oper_no=?,check_oper_no=?,account_oper_no=?,apply_date=?,apply_time=?,check_date=?,check_time=?,account_time=?,req_draft_no=?,apply_cancel_flag=?,ebs_no=?,acceptor_bank_no=?,acceptor_bank_name=?,position=?,gath_type=?,gath_date=?,limit_reduce_row=?,owner_party_id=?,audit_reason=?,remark=?,req_msg_id=? where intomx_id=?",
		obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
		obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
		obj.getBillMoney(),obj.getAcceptor(),obj.getBranchNo(),obj.getPayee(),
		obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillClass(),
		obj.getBillSource(),obj.getOperNo(),obj.getOperStatus(),obj.getIntoId(),
		obj.getRgctId(),obj.getProdNo(),obj.getEduType(),obj.getQueryStatus(),
		obj.getCreateDt(),obj.getCreateTime(),obj.getIsTc(),obj.getSignRemark(),
		obj.getIfSignFlag(),obj.getCustManager(),obj.getDeptNo(),obj.getSettleAcct(),
		obj.getRemitterAddr(),obj.getIsAmount(),obj.getAcceptProtocolNo(),obj.getAuditRemark(),
		obj.getQueryType(),obj.getQueryDate(),obj.getQueryTime(),obj.getQueryGetDate(),
		obj.getQueryGetTime(),obj.getForbidFlag(),obj.getYzSource(),obj.getIfTj(),
		obj.getExSerial(),obj.getProdName(),obj.getIsFac(),obj.getCustNo(),
		obj.getCustName(),obj.getIsCyc(),obj.getOwnerPartyName(),obj.getOrgCode(),
		obj.getQueryContent(),obj.getReturnContent(),obj.getAuditContent(),obj.getSignContent(),
		obj.getDeptName(),obj.getCustManagerName(),obj.getCollDate(),obj.getAccountDate(),
		obj.getApplyProdNo(),obj.getApplyProdName(),obj.getApplyOperNo(),obj.getCheckOperNo(),
		obj.getAccountOperNo(),obj.getApplyDate(),obj.getApplyTime(),obj.getCheckDate(),
		obj.getCheckTime(),obj.getAccountTime(),obj.getReqDraftNo(),obj.getApplyCancelFlag(),
		obj.getEbsNo(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getPosition(),
		obj.getGathType(),obj.getGathDate(),obj.getLimitReduceRow(),obj.getOwnerPartyId(),
		obj.getAuditReason(),obj.getRemark(),obj.getReqMsgId(),obj.getIntomxId());
	}

	public int modifyIntoBillInfo(IntoBillInfo obj,String intomxId) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update tinto_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,branch_no=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_class=?,bill_source=?,oper_no=?,oper_status=?,into_id=?,rgct_id=?,prod_no=?,edu_type=?,query_status=?,create_dt=?,create_time=?,is_tc=?,sign_remark=?,if_sign_flag=?,cust_manager=?,dept_no=?,settle_acct=?,remitter_addr=?,is_amount=?,accept_protocol_no=?,audit_remark=?,query_type=?,query_date=?,query_time=?,query_get_date=?,query_get_time=?,forbid_flag=?,yz_source=?,if_tj=?,ex_serial=?,prod_name=?,is_fac=?,cust_no=?,cust_name=?,is_cyc=?,owner_party_name=?,org_code=?,query_content=?,return_content=?,audit_content=?,sign_content=?,dept_name=?,cust_manager_name=?,coll_date=?,account_date=?,apply_prod_no=?,apply_prod_name=?,apply_oper_no=?,check_oper_no=?,account_oper_no=?,apply_date=?,apply_time=?,check_date=?,check_time=?,account_time=?,req_draft_no=?,apply_cancel_flag=?,ebs_no=?,acceptor_bank_no=?,acceptor_bank_name=?,position=?,gath_type=?,gath_date=?,limit_reduce_row=?,owner_party_id=?,audit_reason=?,remark=?,req_msg_id=? where intomx_id=?",
		obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
		obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
		obj.getBillMoney(),obj.getAcceptor(),obj.getBranchNo(),obj.getPayee(),
		obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillClass(),
		obj.getBillSource(),obj.getOperNo(),obj.getOperStatus(),obj.getIntoId(),
		obj.getRgctId(),obj.getProdNo(),obj.getEduType(),obj.getQueryStatus(),
		obj.getCreateDt(),obj.getCreateTime(),obj.getIsTc(),obj.getSignRemark(),
		obj.getIfSignFlag(),obj.getCustManager(),obj.getDeptNo(),obj.getSettleAcct(),
		obj.getRemitterAddr(),obj.getIsAmount(),obj.getAcceptProtocolNo(),obj.getAuditRemark(),
		obj.getQueryType(),obj.getQueryDate(),obj.getQueryTime(),obj.getQueryGetDate(),
		obj.getQueryGetTime(),obj.getForbidFlag(),obj.getYzSource(),obj.getIfTj(),
		obj.getExSerial(),obj.getProdName(),obj.getIsFac(),obj.getCustNo(),
		obj.getCustName(),obj.getIsCyc(),obj.getOwnerPartyName(),obj.getOrgCode(),
		obj.getQueryContent(),obj.getReturnContent(),obj.getAuditContent(),obj.getSignContent(),
		obj.getDeptName(),obj.getCustManagerName(),obj.getCollDate(),obj.getAccountDate(),
		obj.getApplyProdNo(),obj.getApplyProdName(),obj.getApplyOperNo(),obj.getCheckOperNo(),
		obj.getAccountOperNo(),obj.getApplyDate(),obj.getApplyTime(),obj.getCheckDate(),
		obj.getCheckTime(),obj.getAccountTime(),obj.getReqDraftNo(),obj.getApplyCancelFlag(),
		obj.getEbsNo(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getPosition(),
		obj.getGathType(),obj.getGathDate(),obj.getLimitReduceRow(),obj.getOwnerPartyId(),
		obj.getAuditReason(),obj.getRemark(),obj.getReqMsgId(),intomxId);
	}

	public IntoBillInfo getIntoBillInfo(String intomxId) throws SQLException {
		IDB session = DBFactory.getDB();
		IntoBillInfo obj = (IntoBillInfo)session.getObject("select * from tinto_bill_info where intomx_id=?",IntoBillInfo.class,intomxId);
		return obj;
	}
	
	/**
	   * 功能描述：通过票据中心ID查票据清单
	   * @param rgctId
	   * @return
	   * @throws SQLException
	   */
	  public IntoBillInfo getIntoBillInfoByRgctId(String rgctId) throws SQLException {
	  	IDB session = DBFactory.getDB();
	  	IntoBillInfo obj = (IntoBillInfo)session.getObject("select * from tinto_bill_info where rgct_id=?",IntoBillInfo.class,rgctId);
	  	return obj;
	  }

/**
 * 查询清单(申请 审核 入库)/电票未签收
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
  public List<IntoBillInfo> getIntoBillListBySearchBean(Page page, IntoSearchBean query)
    throws SQLException
  {
    IDB session = DBFactory.getDB();
    String baseSql = "select bill.* from tinto_bill_info bill";
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
    int count = session.accountByList(qc.getCountSql("bill.intomx_Id"), qc.getParameterValues());
    page.setTotalResult(count);
    return session.getObjectListByListForPage(sql, IntoBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
  }
  
  /**
   * 根据intoId状态查询清单
   *
   */
  public List<IntoBillInfo> getIntoBillListForIntoIdStatus(String intoId,String operStatus) throws SQLException {
		IDB session = DBFactory.getDB();
		String sql = "select bill.* from tinto_bill_info bill where bill.into_id = ? and bill.oper_status = ?";
		return session.getObjectList(sql, IntoBillInfo.class,intoId,operStatus);
	}
  
  /**
   * 根据明细ids查询明细清单列表
   * @param 批次id String intoId,清单状态 String operStatus
   * @return
   * @throws SQLException
   */
  public List<IntoBillInfo> getIntoBillListByMxids(String[] ids) throws SQLException {
  	IDB session = DBFactory.getDB();
  	StringBuffer sb = new StringBuffer("select bill.* from tinto_bill_info bill where bill.intomx_id in(");
  	for(int i=0;i<ids.length-1;i++){
  		sb.append("'"+ids[i]+"'"+",");
  	}
  	sb.append("'"+ids[ids.length-1]+"')");
  	String sql = sb.toString();
  	System.out.println(sql);
  	List<IntoBillInfo> list = session.getObjectList(sql,IntoBillInfo.class);
  	return list;
  }
  /**
   * 电票根据id得到清单
   * @param ids
   * @return
   * @throws SQLException
   */
  public List<IntoBillInfo> getElecReceiveForId(String ids) throws SQLException{
  	StringBuffer sql = new StringBuffer("select t.* from tinto_bill_info t where t.bill_class = '2' and intomx_id in (");
  	String idArr[] = ids.split(",");
  	List<Object> param = new ArrayList<Object>(idArr.length);
  	for (String id : idArr) {
  		sql.append("?,");
  		param.add(id);
  	}
  	sql.deleteCharAt(sql.length()-1);
  	sql.append(')');
  	IDB session = DBFactory.getDB();
  	return session.getObjectListByList(sql.toString(), IntoBillInfo.class, param);
  }
  /**
	 * 功能描述：剩余票据总数量
	 * @param bill
	 * @return
	 * @throws SQLException
	 */
  public int totalCount(IntoBillInfo bill) throws SQLException{
		BeanFormat.format(bill);
		IDB session = DBFactory.getDB();
		return session.account("select count(0) from tinto_bill_info bill where bill.bill_Class = ? and bill.bill_Type = ? and bill.branch_No = ? and bill.oper_Status = ? and bill.cust_No = ? and bill.into_Id = ?", bill.getBillClass(),bill.getBillType(),bill.getBranchNo(),bill.getOperStatus(),bill.getCustNo(),bill.getIntoId());
	}
  /**
   * 根据报文id查bill
   * @param reqDraftId
   * @return
   * @throws SQLException
   */
  public IntoBillInfo getIntoBillInfoByReqDraftId(String reqDraftId) throws SQLException {
  	IDB session = DBFactory.getDB();
  	IntoBillInfo obj = (IntoBillInfo)session.getObject("select * from tinto_bill_info where req_msg_id=?",IntoBillInfo.class,reqDraftId);
  	return obj;
  }

}
