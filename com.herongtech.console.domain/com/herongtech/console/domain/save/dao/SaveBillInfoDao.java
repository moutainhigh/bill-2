/********************************************
* 文件名称: SaveBillInfoDao.java
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
package com.herongtech.console.domain.save.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;
public class SaveBillInfoDao{

	public int addSaveBillInfo(SaveBillInfo obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("insert into tsave_bill_info(savemx_id,bill_type,bill_no,issue_dt,due_dt,remitter,remitter_acct,remitter_bank_name,remitter_bank_no,bill_money,acceptor,branch_no,payee,payee_bank_name,payee_acct,bill_before_owner,bill_class,bill_source,oper_no,oper_status,save_id,rgct_id,prod_no,edu_type,query_status,create_dt,create_time,is_tc,sign_remark,if_sign_flag,cust_manager,dept_no,settle_acct,remitter_addr,is_amount,accept_protocol_no,audit_remark,query_type,query_date,query_time,query_get_date,query_get_time,forbid_flag,yz_source,if_tj,ex_serial,prod_name,is_fac,cust_no,cust_name,is_cyc,owner_party_name,org_code,query_content,return_content,audit_content,sign_content,dept_name,cust_manager_name,coll_date,account_date,apply_prod_no,apply_prod_name,apply_oper_no,check_oper_no,account_oper_no,apply_date,apply_time,check_date,check_time,account_time,req_draft_no,apply_cancel_flag,ebs_no,acceptor_bank_no,acceptor_bank_name,position,gath_type,gath_date,limit_reduce_row,owner_party_id,audit_reason,remark,req_msg_id)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
		obj.getSavemxId(),obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
		obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
		obj.getBillMoney(),obj.getAcceptor(),obj.getBranchNo(),obj.getPayee(),
		obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillClass(),
		obj.getBillSource(),obj.getOperNo(),obj.getOperStatus(),obj.getSaveId(),
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

public int deleteSaveBillInfo(SaveBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsave_bill_info where savemx_id=?",obj.getSavemxId());
}

public int deleteSaveBillInfo(String savemxId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsave_bill_info where savemx_id=?",savemxId);
}

public int modifySaveBillInfo(SaveBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsave_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,branch_no=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_class=?,bill_source=?,oper_no=?,oper_status=?,save_id=?,rgct_id=?,prod_no=?,edu_type=?,query_status=?,create_dt=?,create_time=?,is_tc=?,sign_remark=?,if_sign_flag=?,cust_manager=?,dept_no=?,settle_acct=?,remitter_addr=?,is_amount=?,accept_protocol_no=?,audit_remark=?,query_type=?,query_date=?,query_time=?,query_get_date=?,query_get_time=?,forbid_flag=?,yz_source=?,if_tj=?,ex_serial=?,prod_name=?,is_fac=?,cust_no=?,cust_name=?,is_cyc=?,owner_party_name=?,org_code=?,query_content=?,return_content=?,audit_content=?,sign_content=?,dept_name=?,cust_manager_name=?,coll_date=?,account_date=?,apply_prod_no=?,apply_prod_name=?,apply_oper_no=?,check_oper_no=?,account_oper_no=?,apply_date=?,apply_time=?,check_date=?,check_time=?,account_time=?,req_draft_no=?,apply_cancel_flag=?,ebs_no=?,acceptor_bank_no=?,acceptor_bank_name=?,position=?,gath_type=?,gath_date=?,limit_reduce_row=?,owner_party_id=?,audit_reason=?,remark=?,req_msg_id=? where savemx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getBranchNo(),obj.getPayee(),
	obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperNo(),obj.getOperStatus(),obj.getSaveId(),
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
	obj.getAuditReason(),obj.getRemark(),obj.getReqMsgId(),obj.getSavemxId());
}

public int modifySaveBillInfo(SaveBillInfo obj,String savemxId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsave_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,branch_no=?,payee=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_class=?,bill_source=?,oper_no=?,oper_status=?,save_id=?,rgct_id=?,prod_no=?,edu_type=?,query_status=?,create_dt=?,create_time=?,is_tc=?,sign_remark=?,if_sign_flag=?,cust_manager=?,dept_no=?,settle_acct=?,remitter_addr=?,is_amount=?,accept_protocol_no=?,audit_remark=?,query_type=?,query_date=?,query_time=?,query_get_date=?,query_get_time=?,forbid_flag=?,yz_source=?,if_tj=?,ex_serial=?,prod_name=?,is_fac=?,cust_no=?,cust_name=?,is_cyc=?,owner_party_name=?,org_code=?,query_content=?,return_content=?,audit_content=?,sign_content=?,dept_name=?,cust_manager_name=?,coll_date=?,account_date=?,apply_prod_no=?,apply_prod_name=?,apply_oper_no=?,check_oper_no=?,account_oper_no=?,apply_date=?,apply_time=?,check_date=?,check_time=?,account_time=?,req_draft_no=?,apply_cancel_flag=?,ebs_no=?,acceptor_bank_no=?,acceptor_bank_name=?,position=?,gath_type=?,gath_date=?,limit_reduce_row=?,owner_party_id=?,audit_reason=?,remark=?,req_msg_id=? where savemx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getBranchNo(),obj.getPayee(),
	obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperNo(),obj.getOperStatus(),obj.getSaveId(),
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
	obj.getAuditReason(),obj.getRemark(),obj.getReqMsgId(),savemxId);
}

public SaveBillInfo getSaveBillInfo(String savemxId) throws SQLException {
	IDB session = DBFactory.getDB();
	SaveBillInfo obj = (SaveBillInfo)session.getObject("select * from tsave_bill_info where savemx_id=?",SaveBillInfo.class,savemxId);
	return obj;
}

/**
 * 根据报文id查bill
 * @param reqDraftId
 * @return
 * @throws SQLException
 */
public SaveBillInfo getSaveBillInfoByReqDraftId(String reqDraftId) throws SQLException {
	IDB session = DBFactory.getDB();
	SaveBillInfo obj = (SaveBillInfo)session.getObject("select * from tsave_bill_info where req_msg_id=?",SaveBillInfo.class,reqDraftId);
	return obj;
}

/**
 * 根据savemxid该操作状态
 * @param bill
 * @throws SQLException
 */
public void modifyOperStatusForBill(SaveBillInfo bill) throws SQLException{
	BeanFormat.format(bill);
	IDB session = DBFactory.getDB();
	session.execute("update tsave_bill_info set oper_status = ? where savemx_id=?",bill.getOperStatus(),bill.getSavemxId());
}

/**
 * 电票根据id得到清单
 * @param ids
 * @return
 * @throws SQLException
 */
public List<SaveBillInfo> getElecReceiveForId(String ids) throws SQLException{
	StringBuffer sql = new StringBuffer("select t.* from tsave_bill_info t where t.bill_class = '2' and savemx_id in (");
	String idArr[] = ids.split(",");
	List<Object> param = new ArrayList<Object>(idArr.length);
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.getObjectListByList(sql.toString(), SaveBillInfo.class, param);
}

 /**
 * 查询清单(申请 审核 入库)/电票未签收
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
  public List<SaveBillInfo> getSaveBillListBySearchBean(Page page, SaveSearchBean query)
    throws SQLException
  {
    IDB session = DBFactory.getDB();
    String baseSql = "select bill.* from tsave_bill_info bill";
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
    int count = session.accountByList(qc.getCountSql("bill.savemx_Id"), qc.getParameterValues());
    page.setTotalResult(count);
    return session.getObjectListByListForPage(sql, SaveBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
  }
  
  /**
   * 根据saveId状态查询清单
   *
   */
  public List<SaveBillInfo> getSaveBillListForSaveIdStatus(String saveId,String operStatus) throws SQLException {
		IDB session = DBFactory.getDB();
		String sql = "select bill.* from tsave_bill_info bill where bill.save_id = ? and bill.oper_status = ?";
		return session.getObjectList(sql, SaveBillInfo.class,saveId,operStatus);
	}
  
  /**
   * 功能描述：根据明细ids查询明细清单列表
   * @param 批次id String saveId,清单状态 String operStatus
   * @return
   * @throws SQLException
   */
  public List<SaveBillInfo> getSaveBillListByMxids(String[] ids) throws SQLException {
  	IDB session = DBFactory.getDB();
  	StringBuffer sb = new StringBuffer("select bill.* from tsave_bill_info bill where bill.savemx_id in(");
  	for(int i=0;i<ids.length-1;i++){
  		sb.append("'"+ids[i]+"'"+",");
  	}
  	sb.append("'"+ids[ids.length-1]+"')");
  	String sql = sb.toString();
  	System.out.println(sql);
  	List<SaveBillInfo> list = session.getObjectList(sql,SaveBillInfo.class);
  	return list;
  }
  
  /**
 * 查询批次(申请岗)
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
  public List<SaveApplyInfo> getSaveApplyListForApply(Page page, SaveSearchBean query)
    throws Exception
  {
    StringBuilder select = new StringBuilder("select distinct ta.*");
    select.append(" from tsave_apply_info ta left join tsave_bill_info tb on");
    select.append(" tb.save_id = ta.save_id where 1=1 ");
    IDB session = DBFactory.getDB();
    QueryCondition qc = new QueryCondition();
    OrderBean order = new OrderBean("saveId",false);
	query.appendOrder(order);
    qc.initFromSearchBean(query);
    
    String sql = qc.getAllSqlString(select.toString());
    System.out.println(sql);
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("distinct ta.save_id"),qc.getParameterValues());
	page.setTotalResult(count);
	List<SaveApplyInfo> applyList = session.getObjectListByListForPage(sql,SaveApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	query.setOperStatus(StatusUtils.queryStatus("CollateralizationApplyController", "billManage", null)[0]);
	sumApplyInfo(applyList, query);
	return applyList;
    
  }
  
  /**
 * 查询批次(审核 入库岗)
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
  public List<SaveApplyInfo> getSaveApplyListBySearchBean(Page page, SaveSearchBean query)
    throws SQLException
  {
    IDB session = DBFactory.getDB();
    String baseSql = "select distinct apply.* from tsave_apply_info apply,tsave_bill_info bill where bill.save_id = apply.save_id";
    QueryCondition qc = new QueryCondition();
    try {
      qc.initFromSearchBean(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String allsql = qc.getAllSqlString(baseSql);
    int startIndex = (page.getCurrentPage() - 1) * page.getShowCount() + 1;
    if (startIndex < 0)
      startIndex = 0;
    int count = 0;
    List<SaveApplyInfo> applyList=null;

    if ((qc.getParameterValues() == null) || (qc.getParameterValues().size() == 0)) {
      count = session.account(qc.getCountSql("distinct apply.save_id"));
      applyList = session.getObjectListForPage(allsql, SaveApplyInfo.class, startIndex, page.getShowCount(), new Object[0]);
    } else {
      count = session.accountByList(qc.getCountSql("distinct apply.save_id"), qc.getParameterValues());
      applyList = session.getObjectListByListForPage(allsql, SaveApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
    }
    System.out.println(allsql);
    page.setTotalResult(count);
    sumApplyInfo(applyList, query);
    /*for (SaveApplyInfo apply : applyList) {
      query.setSaveId(apply.getSaveId());
      sumApplyInfo(applyList, query);
    }*/
    return applyList;
  }

  /**
 * 合计批次信息
 *
 * 
 */
  public void sumApplyInfo(List<SaveApplyInfo> applyList,SaveSearchBean query) throws SQLException{
	    if(applyList == null || applyList.size() == 0) return;
	    IDB session = DBFactory.getDB();
	    //批次汇总票据张数，与票据金额sql
	    StringBuilder sb = new StringBuilder("select count(bill.savemx_id)||'' c_num,sum(bill.bill_money) from tsave_bill_info bill where bill.save_id = ?");
	    List<Object> param = new ArrayList<Object>();
	    //占第一位 为第一个问号赋值
	    param.add("");
	    if(query != null){
	        if(StringUtils.isNotBlank(query.getBillClass())){
	            sb.append(" and bill.bill_class = ? ");
	            param.add(query.getBillClass().trim());
	        }
	        if(StringUtils.isNotBlank(query.getBillType())){
	            sb.append(" and bill.bill_type = ? ");
	            param.add(query.getBillType().trim());
	        }
	        if(StringUtils.isNotBlank(query.getOperStatus())){
	            sb.append(" and bill.oper_status = ? ");
	            param.add(query.getOperStatus().trim());
	        }
	        if(StringUtils.isNotBlank(query.getApplyOperNo())){
	            sb.append(" and bill.apply_oper_no = ? ");
	            param.add(query.getApplyOperNo().trim());
	        }
	        if(StringUtils.isNotBlank(query.getCheckOperNo())){
	            sb.append(" and bill.check_oper_no = ? ");
	            param.add(query.getCheckOperNo().trim());
	        }
	        if(StringUtils.isNotBlank(query.getPosition())){
  	            sb.append(" and bill.position = ? ");
  	            param.add(query.getPosition().trim());
  	        }
	        if(query.getOpers()!=null){
	            sb.append(" and bill.oper_status in (");
	            for (Object status : query.getOpers()) {
	            	sb.append("?,");
	        		param.add(status);
	        	}
	            sb.deleteCharAt(sb.length()-1);
	            sb.append(')');
	        }
	    }
	    for(SaveApplyInfo apply:applyList){
	    	//删除第一位 为第一个问号赋值
	    	param.remove(0);
	    	// 为第一个问号赋值
	    	param.add(0, apply.getSaveId());
		    IData data=session.getDataByList(sb.toString(), param);
		    apply.setTotalNum(data.getString(1));
		    apply.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)));
	    }
	}
  
  /**
 * 更改状态值(审核入库)
 *
 *
 */
  public int updateSaveBillInfoStatus(String ids, String[] queryStatus, String afterStatus, String dateTime, String option)
  throws SQLException, BizAppException
{
  if ((queryStatus == null) || (queryStatus.length == 0) || (StringUtils.isBlank(afterStatus)))
    throw new BizAppException(ISysErrorNo.PARAM_VALIDATE_EXCEPTION_CODE, "前置状态与后置状态均不能为空");
  List<Object> param = new ArrayList<Object>();
  param.add(afterStatus);
  StringBuffer sql = new StringBuffer("update tsave_bill_info set oper_status = ?");
  
  if(StringUtils.isNotBlank(dateTime)){
		if("apply".equals(dateTime)){
			sql.append(",apply_date = ?,apply_time = ?");
			param.add(DateTimeUtil.getWorkdayString());
		    param.add(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
		}/*else if("audit".equals(dateTime)){
	           sql.append(",audit_date = ?,audit_time = ?");
	    }*/
  }
  if (StringUtils.isNotBlank(option)) {
    sql.append(",audit_remark = ?");
    param.add(option);
  }
  sql.append(" where savemx_id in (");
  String[] idArr = ids.split(",");
  for (String id : idArr) {
    sql.append("?,");
    param.add(id);
  }
  sql.deleteCharAt(sql.length() - 1);
  sql.append(") and oper_status in (");
  for (String status : queryStatus) {
    sql.append("?,");
    param.add(status);
  }
  sql.deleteCharAt(sql.length() - 1);
  sql.append(')');
  IDB session = DBFactory.getDB();
  return session.executeByList(sql.toString(), param);
}
  /**
   * 功能描述：查询批次下的清单  根据批次id和清单状态
   * @param 批次id String saveId,清单状态 String operStatus
   * @return
   * @throws SQLException
   */
  public List<SaveBillInfo> getSaveBillForSaveIdAndStatus(String saveId,String operStatus) throws SQLException {
  	IDB session = DBFactory.getDB();
  	String sql = "select bill.* from tsave_bill_info bill where bill.save_id = ? and bill.oper_status = ?";
  	return session.getObjectList(sql, SaveBillInfo.class,saveId,operStatus);
  }
  
  public int totalCount(SaveBillInfo bill) throws SQLException{
		BeanFormat.format(bill);
		IDB session = DBFactory.getDB();
		return session.account("select count(0) from tsave_bill_info bill where bill.bill_Class = ? and bill.bill_Type = ? and bill.branch_No = ? and bill.oper_Status = ? and bill.cust_No = ? and bill.save_Id = ?", bill.getBillClass(),bill.getBillType(),bill.getBranchNo(),bill.getOperStatus(),bill.getCustNo(),bill.getSaveId());
	}
  
  /**
   * 功能描述：通过票据中心ID查票据清单
   * @param rgctId
   * @return
   * @throws SQLException
   */
  public SaveBillInfo getSaveBillInfoByRgctId(String rgctId) throws SQLException {
  	IDB session = DBFactory.getDB();
  	SaveBillInfo obj = (SaveBillInfo)session.getObject("select * from tsave_bill_info where rgct_id=? order by savemx_id desc",SaveBillInfo.class,rgctId);
  	return obj;
  }
}
