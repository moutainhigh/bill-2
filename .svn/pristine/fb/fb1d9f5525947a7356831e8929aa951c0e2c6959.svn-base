/********************************************
* 文件名称: RgctBillInfoDao.java
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
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctBillInfo;
public class RgctBillInfoDao{

public int addRgctBillInfo(RgctBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trgct_bill_info(id,hist_id,req_draft_id,resp_draft_id,temp_hist_id,ebs_no,bill_class,bill_type,bill_no,issue_dt,is_accpt,remitter,remitter_cust_no,remitter_acct,remitter_sign,remitter_bank_no,remitter_bank_name,drawee_bank_name,drawee_bank_no,drawee_branch_no,drawee_addr,payee_name,payee_acct,payee_bank_no,payee_bank_name,acceptor,acceptor_cust_no,acceptor_acct,acceptor_bank_no,acceptor_bank_name,acceptor_sign,assure_bank_name,assure_bank_no,letter_no,assure_self,bill_source,bill_money,due_dt,confer_no,info_forbid_flag,deduct_flag,is_acpt_acct,bill_usage,remark,cancel_reason,cancel_remark,create_dt,create_time,del_flag,invoice_no,remitter_remark,drwr_creditrating,drwr_creditrating_agency,drwr_creditrating_duedt,acptr_creditrating,acptr_creditrating_agency,acptr_creditrating_duedt,busi_deep,acceptor_date,recourse_flag,remitter_role,remitter_org_code,acceptor_org_code,payee_org_code,cur_status,acct_branch_no,storage_branch_no,acceptor_role)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getHistId(),obj.getReqDraftId(),obj.getRespDraftId(),obj.getTempHistId(),obj.getEbsNo(),obj.getBillClass(),
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getIsAccpt(),
	obj.getRemitter(),obj.getRemitterCustNo(),obj.getRemitterAcct(),obj.getRemitterSign(),
	obj.getRemitterBankNo(),obj.getRemitterBankName(),obj.getDraweeBankName(),obj.getDraweeBankNo(),
	obj.getDraweeBranchNo(),obj.getDraweeAddr(),obj.getPayeeName(),obj.getPayeeAcct(),
	obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getAcceptor(),obj.getAcceptorCustNo(),
	obj.getAcceptorAcct(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getAcceptorSign(),
	obj.getAssureBankName(),obj.getAssureBankNo(),obj.getLetterNo(),obj.getAssureSelf(),
	obj.getBillSource(),obj.getBillMoney(),obj.getDueDt(),obj.getConferNo(),
	obj.getInfoForbidFlag(),obj.getDeductFlag(),obj.getIsAcptAcct(),obj.getBillUsage(),
	obj.getRemark(),obj.getCancelReason(),obj.getCancelRemark(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getDelFlag(),obj.getInvoiceNo(),obj.getRemitterRemark(),
	obj.getDrwrCreditrating(),obj.getDrwrCreditratingAgency(),obj.getDrwrCreditratingDuedt(),
	obj.getAcptrCreditrating(),obj.getAcptrCreditratingAgency(),obj.getAcptrCreditratingDuedt(),obj.getBusiDeep(),
	obj.getAcceptorDate(),obj.getRecourseFlag(),obj.getRemitterRole(),obj.getRemitterOrgCode(),
	obj.getAcceptorOrgCode(),obj.getPayeeOrgCode(),obj.getCurStatus(),
	obj.getAcctBranchNo(),obj.getStorageBranchNo(),obj.getAcceptorRole());
}

public int deleteRgctBillInfo(RgctBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trgct_bill_info where id=?",obj.getId());
}

public int deleteRgctBillInfo(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trgct_bill_info where id=?",id);
}

public int modifyRgctBillInfo(RgctBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_bill_info set hist_id=?,req_draft_id=?,resp_draft_id=?,temp_hist_id=?,ebs_no=?,bill_class=?,bill_type=?,bill_no=?,issue_dt=?,is_accpt=?,remitter=?,remitter_cust_no=?,remitter_acct=?,remitter_sign=?,remitter_bank_no=?,remitter_bank_name=?,drawee_bank_name=?,drawee_bank_no=?,drawee_branch_no=?,drawee_addr=?,payee_name=?,payee_acct=?,payee_bank_no=?,payee_bank_name=?,acceptor=?,acceptor_cust_no=?,acceptor_acct=?,acceptor_bank_no=?,acceptor_bank_name=?,acceptor_sign=?,assure_bank_name=?,assure_bank_no=?,letter_no=?,assure_self=?,bill_source=?,bill_money=?,due_dt=?,confer_no=?,info_forbid_flag=?,deduct_flag=?,is_acpt_acct=?,bill_usage=?,remark=?,cancel_reason=?,cancel_remark=?,create_dt=?,create_time=?,del_flag=?,invoice_no=?,remitter_remark=?,drwr_creditrating=?,drwr_creditrating_agency=?,drwr_creditrating_duedt=?,acptr_creditrating=?,acptr_creditrating_agency=?,acptr_creditrating_duedt=?,busi_deep=?,acceptor_date=?,recourse_flag=?,remitter_role=?,remitter_org_code=?,acceptor_org_code=?,payee_org_code=?,cur_status=?,acct_branch_no=?,storage_branch_no=?,acceptor_role=? where id=?",
	obj.getHistId(),obj.getReqDraftId(),obj.getRespDraftId(),obj.getTempHistId(),obj.getEbsNo(),obj.getBillClass(),
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getIsAccpt(),
	obj.getRemitter(),obj.getRemitterCustNo(),obj.getRemitterAcct(),obj.getRemitterSign(),
	obj.getRemitterBankNo(),obj.getRemitterBankName(),obj.getDraweeBankName(),obj.getDraweeBankNo(),
	obj.getDraweeBranchNo(),obj.getDraweeAddr(),obj.getPayeeName(),obj.getPayeeAcct(),
	obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getAcceptor(),obj.getAcceptorCustNo(),
	obj.getAcceptorAcct(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getAcceptorSign(),
	obj.getAssureBankName(),obj.getAssureBankNo(),obj.getLetterNo(),obj.getAssureSelf(),
	obj.getBillSource(),obj.getBillMoney(),obj.getDueDt(),obj.getConferNo(),
	obj.getInfoForbidFlag(),obj.getDeductFlag(),obj.getIsAcptAcct(),obj.getBillUsage(),
	obj.getRemark(),obj.getCancelReason(),obj.getCancelRemark(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getDelFlag(),obj.getInvoiceNo(),obj.getRemitterRemark(),
	obj.getDrwrCreditrating(),obj.getDrwrCreditratingAgency(),obj.getDrwrCreditratingDuedt(),
	obj.getAcptrCreditrating(),obj.getAcptrCreditratingAgency(),obj.getAcptrCreditratingDuedt(),obj.getBusiDeep(),
	obj.getAcceptorDate(),obj.getRecourseFlag(),obj.getRemitterRole(),obj.getRemitterOrgCode(),
	obj.getAcceptorOrgCode(),obj.getPayeeOrgCode(),obj.getCurStatus(),
	obj.getAcctBranchNo(),obj.getStorageBranchNo(),obj.getAcceptorRole(),
	obj.getId());
}

public int modifyRgctBillInfo(RgctBillInfo obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_bill_info set hist_id=?,req_draft_id=?,resp_draft_id=?,temp_hist_id=?,ebs_no=?,bill_class=?,bill_type=?,bill_no=?,issue_dt=?,is_accpt=?,remitter=?,remitter_cust_no=?,remitter_acct=?,remitter_sign=?,remitter_bank_no=?,remitter_bank_name=?,drawee_bank_name=?,drawee_bank_no=?,drawee_branch_no=?,drawee_addr=?,payee_name=?,payee_acct=?,payee_bank_no=?,payee_bank_name=?,acceptor=?,acceptor_cust_no=?,acceptor_acct=?,acceptor_bank_no=?,acceptor_bank_name=?,acceptor_sign=?,assure_bank_name=?,assure_bank_no=?,letter_no=?,assure_self=?,bill_source=?,bill_money=?,due_dt=?,confer_no=?,info_forbid_flag=?,deduct_flag=?,is_acpt_acct=?,bill_usage=?,remark=?,cancel_reason=?,cancel_remark=?,create_dt=?,create_time=?,del_flag=?,invoice_no=?,remitter_remark=?,drwr_creditrating=?,drwr_creditrating_agency=?,drwr_creditrating_duedt=?,acptr_creditrating=?,acptr_creditrating_agency=?,acptr_creditrating_duedt=?,busi_deep=?,acceptor_date=?,recourse_flag=?,remitter_role=?,remitter_org_code=?,acceptor_org_code=?,payee_org_code=?,cur_status=?,acct_branch_no=?,storage_branch_no=?,acceptor_role=? where id=?",
	obj.getHistId(),obj.getReqDraftId(),obj.getRespDraftId(),obj.getTempHistId(),obj.getEbsNo(),obj.getBillClass(),
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getIsAccpt(),
	obj.getRemitter(),obj.getRemitterCustNo(),obj.getRemitterAcct(),obj.getRemitterSign(),
	obj.getRemitterBankNo(),obj.getRemitterBankName(),obj.getDraweeBankName(),obj.getDraweeBankNo(),
	obj.getDraweeBranchNo(),obj.getDraweeAddr(),obj.getPayeeName(),obj.getPayeeAcct(),
	obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getAcceptor(),obj.getAcceptorCustNo(),
	obj.getAcceptorAcct(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getAcceptorSign(),
	obj.getAssureBankName(),obj.getAssureBankNo(),obj.getLetterNo(),obj.getAssureSelf(),
	obj.getBillSource(),obj.getBillMoney(),obj.getDueDt(),obj.getConferNo(),
	obj.getInfoForbidFlag(),obj.getDeductFlag(),obj.getIsAcptAcct(),obj.getBillUsage(),
	obj.getRemark(),obj.getCancelReason(),obj.getCancelRemark(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getDelFlag(),obj.getInvoiceNo(),obj.getRemitterRemark(),
	obj.getDrwrCreditrating(),obj.getDrwrCreditratingAgency(),obj.getDrwrCreditratingDuedt(),
	obj.getAcptrCreditrating(),obj.getAcptrCreditratingAgency(),obj.getAcptrCreditratingDuedt(),obj.getBusiDeep(),
	obj.getAcceptorDate(),obj.getRecourseFlag(),obj.getRemitterRole(),obj.getRemitterOrgCode(),
	obj.getAcceptorOrgCode(),obj.getPayeeOrgCode(),obj.getCurStatus(),
	obj.getAcctBranchNo(),obj.getStorageBranchNo(),obj.getAcceptorRole(),
	id);
}

public RgctBillInfo getRgctBillInfo(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctBillInfo obj = (RgctBillInfo)session.getObject("select * from trgct_bill_info where id=?",RgctBillInfo.class,id);
	return obj;
}

public RgctBillInfo getRgctBillInfoByBillNo(String billNo) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctBillInfo obj = (RgctBillInfo)session.getObject("select * from trgct_bill_info where bill_no=?",RgctBillInfo.class,billNo);
	return obj;
}

public RgctBillInfo getRgctBillInfoByReqDraftId(String reqDraftId) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctBillInfo obj = (RgctBillInfo)session.getObject("select * from trgct_bill_info where req_draft_id=?",RgctBillInfo.class,reqDraftId);
	return obj;
}

public List<RgctBillInfo> getRcInfoList(String rgctIds) throws SQLException{
	StringBuffer sql = new StringBuffer("select t.* from trgct_bill_info t where  id in (");
	String idArr[] = rgctIds.split(",");
	List<Object> param = new ArrayList<Object>(idArr.length);
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.getObjectListByList(sql.toString(), RgctBillInfo.class, param);
}

public List getMinDueDtByRgctIds(String ids) throws SQLException{
	IDB session = DBFactory.getDB();
	StringBuffer sql = new StringBuffer("select min(dueDt) from trgct_bill_info   where id in (");
	String idArr[] = CommUtils.couvertLong(ids);
	List<Object> param = new ArrayList<Object>(idArr.length);
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	
	return (List) session.getResultSetByList(sql.toString(), param);
}

}
