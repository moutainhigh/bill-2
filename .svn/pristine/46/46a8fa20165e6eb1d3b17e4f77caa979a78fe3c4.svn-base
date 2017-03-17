/********************************************
* 文件名称: AcptBillInfoDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: wuzc
* 开发时间: 2016/08/29
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.acpt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptQueryCondition;
import com.herongtech.console.web.busicontroller.common.AcptCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;
public class AcptBillInfoDao{


public int addAcptBillInfo(AcptBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tacpt_bill_info(acptmx_id,acpt_id,rgct_id,esno,branch_no,protocal_no,fac_no,remitter,remitter_cust_no,remitter_acct,remitter_branch_no,remitter_bank_no,remitter_bank_name,bill_class,bill_type,voucher_type,bill_no,bill_money,currency_category,bill_status,drawee_bank_no,drawee_bank_name,drawee_addr,payee,payee_acct,payee_bank_no,payee_bank_name,issue_dt,due_dt,acpt_dt,acceptor,acceptor_acct,acceptor_bank_no,acceptor_bank_name,colltn_status,colltn_id,sspd_status,sspd_id,print_status,prsnttn_acct_no,prsnttn_name,prsnttn_bank_no,prsnttn_bank_name,payment_path,payment_dt,cust_remark,bank_remark,pay_wait_order,txl_ctrct_nb,invc_nb,real_acct_dt,real_acct_time,drwg_bck_oper_no,drwg_bck_dt,drwg_bck_time,drwg_bck_flow_no,status,apply_cancel_flag,request_msg_id,yz_source,confer_no)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getAcptmxId(),obj.getAcptId(),obj.getRgctId(),obj.getEsno(),obj.getBranchNo(),
	obj.getProtocalNo(),obj.getFacNo(),obj.getRemitter(),obj.getRemitterCustNo(),
	obj.getRemitterAcct(),obj.getRemitterBranchNo(),obj.getRemitterBankNo(),obj.getRemitterBankName(),
	obj.getBillClass(),obj.getBillType(),obj.getVoucherType(),obj.getBillNo(),
	obj.getBillMoney(),obj.getCurrencyCategory(),obj.getBillStatus(),obj.getDraweeBankNo(),
	obj.getDraweeBankName(),obj.getDraweeAddr(),obj.getPayee(),obj.getPayeeAcct(),
	obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getIssueDt(),obj.getDueDt(),
	obj.getAcptDt(),obj.getAcceptor(),obj.getAcceptorAcct(),obj.getAcceptorBankNo(),
	obj.getAcceptorBankName(),obj.getColltnStatus(),obj.getColltnId(),obj.getSspdStatus(),
	obj.getSspdId(),obj.getPrintStatus(),obj.getPrsnttnAcctNo(),obj.getPrsnttnName(),
	obj.getPrsnttnBankNo(),obj.getPrsnttnBankName(),obj.getPaymentPath(),obj.getPaymentDt(),
	obj.getCustRemark(),obj.getBankRemark(),obj.getPayWaitOrder(),obj.getTxlCtrctNb(),
	obj.getInvcNb(),obj.getRealAcctDt(),obj.getRealAcctTime(),obj.getDrwgBckOperNo(),
	obj.getDrwgBckDt(),obj.getDrwgBckTime(),obj.getDrwgBckFlowNo(),obj.getStatus(),
	obj.getApplyCancelFlag(),obj.getRequestMsgId(),obj.getYzSource(),obj.getConferNo());
}


public int deleteAcptBillInfo(AcptBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacpt_bill_info where acptmx_id=?",obj.getAcptmxId());
}

public int deleteAcptBillInfo(String acptmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacpt_bill_info where acptmx_id=?",acptmxId);
}


public int modifyAcptBillInfo(AcptBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacpt_bill_info set acpt_id=?,rgct_id=?,esno=?,branch_no=?,protocal_no=?,fac_no=?,remitter=?,remitter_cust_no=?,remitter_acct=?,remitter_branch_no=?,remitter_bank_no=?,remitter_bank_name=?,bill_class=?,bill_type=?,voucher_type=?,bill_no=?,bill_money=?,currency_category=?,bill_status=?,drawee_bank_no=?,drawee_bank_name=?,drawee_addr=?,payee=?,payee_acct=?,payee_bank_no=?,payee_bank_name=?,issue_dt=?,due_dt=?,acpt_dt=?,acceptor=?,acceptor_acct=?,acceptor_bank_no=?,acceptor_bank_name=?,colltn_status=?,colltn_id=?,sspd_status=?,sspd_id=?,print_status=?,prsnttn_acct_no=?,prsnttn_name=?,prsnttn_bank_no=?,prsnttn_bank_name=?,payment_path=?,payment_dt=?,cust_remark=?,bank_remark=?,pay_wait_order=?,txl_ctrct_nb=?,invc_nb=?,real_acct_dt=?,real_acct_time=?,drwg_bck_oper_no=?,drwg_bck_dt=?,drwg_bck_time=?,drwg_bck_flow_no=?,status=?,apply_cancel_flag=?,request_msg_id=?,yz_source=?,confer_no=? where acptmx_id=?",
	obj.getAcptId(),obj.getRgctId(),obj.getEsno(),obj.getBranchNo(),
	obj.getProtocalNo(),obj.getFacNo(),obj.getRemitter(),obj.getRemitterCustNo(),
	obj.getRemitterAcct(),obj.getRemitterBranchNo(),obj.getRemitterBankNo(),obj.getRemitterBankName(),
	obj.getBillClass(),obj.getBillType(),obj.getVoucherType(),obj.getBillNo(),
	obj.getBillMoney(),obj.getCurrencyCategory(),obj.getBillStatus(),obj.getDraweeBankNo(),
	obj.getDraweeBankName(),obj.getDraweeAddr(),obj.getPayee(),obj.getPayeeAcct(),
	obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getIssueDt(),obj.getDueDt(),
	obj.getAcptDt(),obj.getAcceptor(),obj.getAcceptorAcct(),obj.getAcceptorBankNo(),
	obj.getAcceptorBankName(),obj.getColltnStatus(),obj.getColltnId(),obj.getSspdStatus(),
	obj.getSspdId(),obj.getPrintStatus(),obj.getPrsnttnAcctNo(),obj.getPrsnttnName(),
	obj.getPrsnttnBankNo(),obj.getPrsnttnBankName(),obj.getPaymentPath(),obj.getPaymentDt(),
	obj.getCustRemark(),obj.getBankRemark(),obj.getPayWaitOrder(),obj.getTxlCtrctNb(),
	obj.getInvcNb(),obj.getRealAcctDt(),obj.getRealAcctTime(),obj.getDrwgBckOperNo(),
	obj.getDrwgBckDt(),obj.getDrwgBckTime(),obj.getDrwgBckFlowNo(),obj.getStatus(),
	obj.getApplyCancelFlag(),obj.getRequestMsgId(),obj.getYzSource(),obj.getConferNo(),
	obj.getAcptmxId());
}



public int modifyAcptBillInfo(AcptBillInfo obj,String acptmxId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacpt_bill_info set acpt_id=?,rgct_id=?,esno=?,branch_no=?,protocal_no=?,fac_no=?,remitter=?,remitter_cust_no=?,remitter_acct=?,remitter_branch_no=?,remitter_bank_no=?,remitter_bank_name=?,bill_class=?,bill_type=?,voucher_type=?,bill_no=?,bill_money=?,currency_category=?,bill_status=?,drawee_bank_no=?,drawee_bank_name=?,drawee_addr=?,payee=?,payee_acct=?,payee_bank_no=?,payee_bank_name=?,issue_dt=?,due_dt=?,acpt_dt=?,acceptor=?,acceptor_acct=?,acceptor_bank_no=?,acceptor_bank_name=?,colltn_status=?,colltn_id=?,sspd_status=?,sspd_id=?,print_status=?,prsnttn_acct_no=?,prsnttn_name=?,prsnttn_bank_no=?,prsnttn_bank_name=?,payment_path=?,payment_dt=?,cust_remark=?,bank_remark=?,pay_wait_order=?,txl_ctrct_nb=?,invc_nb=?,real_acct_dt=?,real_acct_time=?,drwg_bck_oper_no=?,drwg_bck_dt=?,drwg_bck_time=?,drwg_bck_flow_no=?,status=?,apply_cancel_flag=?,request_msg_id=?,yz_source=?,confer_no=? where acptmx_id=?",
	obj.getAcptId(),obj.getRgctId(),obj.getEsno(),obj.getBranchNo(),
	obj.getProtocalNo(),obj.getFacNo(),obj.getRemitter(),obj.getRemitterCustNo(),
	obj.getRemitterAcct(),obj.getRemitterBranchNo(),obj.getRemitterBankNo(),obj.getRemitterBankName(),
	obj.getBillClass(),obj.getBillType(),obj.getVoucherType(),obj.getBillNo(),
	obj.getBillMoney(),obj.getCurrencyCategory(),obj.getBillStatus(),obj.getDraweeBankNo(),
	obj.getDraweeBankName(),obj.getDraweeAddr(),obj.getPayee(),obj.getPayeeAcct(),
	obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getIssueDt(),obj.getDueDt(),
	obj.getAcptDt(),obj.getAcceptor(),obj.getAcceptorAcct(),obj.getAcceptorBankNo(),
	obj.getAcceptorBankName(),obj.getColltnStatus(),obj.getColltnId(),obj.getSspdStatus(),
	obj.getSspdId(),obj.getPrintStatus(),obj.getPrsnttnAcctNo(),obj.getPrsnttnName(),
	obj.getPrsnttnBankNo(),obj.getPrsnttnBankName(),obj.getPaymentPath(),obj.getPaymentDt(),
	obj.getCustRemark(),obj.getBankRemark(),obj.getPayWaitOrder(),obj.getTxlCtrctNb(),
	obj.getInvcNb(),obj.getRealAcctDt(),obj.getRealAcctTime(),obj.getDrwgBckOperNo(),
	obj.getDrwgBckDt(),obj.getDrwgBckTime(),obj.getDrwgBckFlowNo(),obj.getStatus(),
	obj.getApplyCancelFlag(),obj.getRequestMsgId(),obj.getYzSource(),obj.getConferNo(),
	acptmxId);
}

public AcptBillInfo getAcptBillInfo(String acptmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	AcptBillInfo obj = (AcptBillInfo)session.getObject("select * from tacpt_bill_info where acptmx_id=?",AcptBillInfo.class,acptmxId);
	return obj;
}

public List<AcptBillInfo> getBillListByAcptId(String acptId,String status) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.getObjectList("select * from tacpt_bill_info where acpt_id=? and status=?",AcptBillInfo.class,acptId,status);
}


public List<AcptBillInfo> getBillListAcptId(String acptId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.getObjectList("select * from tacpt_bill_info where acpt_id=?",AcptBillInfo.class,acptId);
}




public AcptBillInfo getAcptBillInfoByBillNo(String billNo) throws SQLException {
	IDB session = DBFactory.getDB();
	final String sql = "select * from tacpt_bill_info where bill_no=? order by acptmx_id desc";
	AcptBillInfo obj = (AcptBillInfo)session.getObject(sql,AcptBillInfo.class,billNo);
	return obj;
}



public int modifyAcptBillInfo(AcptApplyInfo apply) throws SQLException {
	IDB session = DBFactory.getDB();
	try {
		String stauts=StatusUtils.handleStatusNoCheck("AcptAuditController", "extendLoanNotification", null);
		final String sql = "update tacpt_bill_info set bill_status=?,branch_no=?, protocal_no =?,remitter_cust_no=? , status=? , yz_source='0' where acpt_id=? ";
		return session.execute(sql,AcptCodeConst.BILL_STATUS_ACCPTNC, apply.getBranchNo(),apply.getProtocalNo(),apply.getRemitterCustNo(),stauts,apply.getAcptId());
	} catch (Exception e) {
		throw new SQLException(e.getMessage());
	}
}





public  int updateAcptBillInfoStatus(String ids, String[] queryStatus,
		String afterStatus,String billNo,String billStatus) throws SQLException,BizAppException {
	if(queryStatus==null || queryStatus.length == 0 || StringUtils.isBlank(afterStatus)) 
			throw new BizAppException(ISysErrorNo.PARAM_VALIDATE_EXCEPTION_CODE, "前置状态与后置状态均不能为空");
	List<Object> param = new ArrayList<Object>();
	
	StringBuffer sql = new StringBuffer("update tacpt_bill_info set status = ?");
	param.add(afterStatus);
	if(!StringUtils.isBlank(billNo)){
		sql.append(",bill_no = ?");
		param.add(billNo);
	}
	if(!StringUtils.isBlank(billStatus)){
		sql.append(",bill_status = ?");
		param.add(billStatus);
	}
	sql.append(" where acptmx_id in (");
	String idArr[] = ids.split(",");
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.executeByList(sql.toString(), param);
}



public List<AcptBillInfo> getElectricReceiveForAcptmxId(String ids) throws SQLException{
	StringBuffer sql = new StringBuffer("select * from tacpt_bill_info  where acptmx_id in (");
	String idArr[] = ids.split(",");
	List<Object> param = new ArrayList<Object>(idArr.length);
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.getObjectListByList(sql.toString(), AcptBillInfo.class, param);
}

/**
 * 查询批次下清单
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<AcptBillInfo> getAcptBillListForBatch(Page page,AcptQueryCondition query) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select bill.* from tacpt_bill_info bill";
	//分页开始位置
	int startIndex = page.getCurrentResult();
	 QueryCondition qc=new QueryCondition();
	 try {
         qc.initFromSearchBean(query);
     } catch (Exception e) {
         e.printStackTrace();
     }
	 String sql=qc.getAllSqlString(baseSql);
     System.out.println(sql+qc.getParameterValues());//打印sql语句
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("bill.acptmx_Id"), qc.getParameterValues());
	page.setTotalResult(count);
	return session.getObjectListByListForPage(sql,AcptBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
}

/**
 * 交易凭证填写
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public  int updateProofOfTrading(String ids,String billNo) throws SQLException,BizAppException {
	
	List<Object> param = new ArrayList<Object>();
	
	StringBuffer sql = new StringBuffer("update tacpt_bill_info set bill_no = ?");
	param.add(billNo);
	sql.append(" where acptmx_id in (");
	String idArr[] = ids.split(",");
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.executeByList(sql.toString(), param);
}

/**
 * 通过rgctId查询银承清单纪录
 * @param rgctId
 * @return
 * @throws DAOException
 */
public AcptBillInfo getAcptBillInfoByRgctId(String rgctId) throws SQLException {
	IDB session = DBFactory.getDB();
	final String sql = "select * from tacpt_bill_info info where info.rgct_id=? ORDER BY ACPTMX_ID DESC";
	AcptBillInfo obj = (AcptBillInfo)session.getObject(sql,AcptBillInfo.class,rgctId);
	return obj;

  }
/**
 * 根据billStatus查询acptId批次下该状态的票据
 * @param batchId
 * @param billStatus
 * @return
 */
public List<AcptBillInfo> queryBillByStatus(String acptId, String billStatus)throws SQLException{
	IDB session = DBFactory.getDB();
	return session.getObjectList("select * from tacpt_bill_info where acpt_id=? and bill_status=?",AcptBillInfo.class,acptId,billStatus);
}


}
