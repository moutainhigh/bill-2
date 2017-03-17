/********************************************
* 文件名称: EntityRegisterInfoDao.java
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
package com.herongtech.console.domain.entityregister.dao;

import java.sql.SQLException;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.entityregister.bean.EntityRegisterInfo;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class EntityRegisterInfoDao{

public int addEntityRegisterInfo(EntityRegisterInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tentity_register_info(id,register_date,register_time,oper_no,register_type,bill_type,bill_no,bill_money,issue_dt,due_dt,accept_dt,accept_time,acceptor,acceptor_bank_no,remitter,payee_name,sign_cancel_dt,sign_cancel_time,dscnt_dt,dscnt_propsr_nm,dscnt_propsr_bank_no,dscnt_bk_nm,dscnt_bk_bank_no,rdscnt_wthcomrclbk_dt,rdscnt_wthcntrlbk_dt,collztn_dt,collztn_propsr_nm,collztn_bk_nm,collztn_bank_no,unchain_collztn_dt,unchain_collztn_time,former_collztn_bk_nm,former_collztn_bank_no,former_collztn_propsr_nm,colltn_dt,colltn_count,colltn_bank_nm,colltn_bank_no,prncpl_nm,sttlm_pay_dt,sttlm_pay_time,pmtrfsed_dt,dshnr_cd,dshnr_rsn,sspdg_pmt_tp,sspdg_pmt_dt,sspdg_pmt_time,propsr_nm,oprtr_nm,anlg_sspdg_pmt_tp,anlg_sspdg_pmt_dt,anlg_sspdg_pmt_time,dispel_propsr_nm,dispel_oprtr_nm,remark,oper_status,contract_no,invoice_no,agree_no,approve_status,is_submit,reply_flag,error_reason,msg_id,intrstrate_1,intrstrate_2,dscntagreeno,oper_branch_no,reg_branch_no)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getRegisterDate(),obj.getRegisterTime(),obj.getOperNo(),obj.getRegisterType(),
	obj.getBillType(),obj.getBillNo(),obj.getBillMoney(),obj.getIssueDt(),
	obj.getDueDt(),obj.getAcceptDt(),obj.getAcceptTime(),obj.getAcceptor(),
	obj.getAcceptorBankNo(),obj.getRemitter(),obj.getPayeeName(),obj.getSignCancelDt(),
	obj.getSignCancelTime(),obj.getDscntDt(),obj.getDscntPropsrNm(),obj.getDscntPropsrBankNo(),
	obj.getDscntBkNm(),obj.getDscntBkBankNo(),obj.getRdscntWthcomrclbkDt(),obj.getRdscntWthcntrlbkDt(),
	obj.getCollztnDt(),obj.getCollztnPropsrNm(),obj.getCollztnBkNm(),obj.getCollztnBankNo(),
	obj.getUnchainCollztnDt(),obj.getUnchainCollztnTime(),obj.getFormerCollztnBkNm(),obj.getFormerCollztnBankNo(),
	obj.getFormerCollztnPropsrNm(),obj.getColltnDt(),obj.getColltnCount(),obj.getColltnBankNm(),
	obj.getColltnBankNo(),obj.getPrncplNm(),obj.getSttlmPayDt(),obj.getSttlmPayTime(),
	obj.getPmtrfsedDt(),obj.getDshnrCd(),obj.getDshnrRsn(),obj.getSspdgPmtTp(),
	obj.getSspdgPmtDt(),obj.getSspdgPmtTime(),obj.getPropsrNm(),obj.getOprtrNm(),
	obj.getAnlgSspdgPmtTp(),obj.getAnlgSspdgPmtDt(),obj.getAnlgSspdgPmtTime(),obj.getDispelPropsrNm(),
	obj.getDispelOprtrNm(),obj.getRemark(),obj.getOperStatus(),obj.getContractNo(),
	obj.getInvoiceNo(),obj.getAgreeNo(),obj.getApproveStatus(),obj.getIsSubmit(),
	obj.getReplyFlag(),obj.getErrorReason(),obj.getMsgId(),obj.getIntrstrate1(),
	obj.getIntrstrate2(),obj.getDscntagreeno(),obj.getOperBranchNo(),obj.getRegBranchNo());
}

public int deleteEntityRegisterInfo(EntityRegisterInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tentity_register_info where id=?",obj.getId());
}

public int deleteEntityRegisterInfo(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tentity_register_info where id=?",id);
}

/**
 * 更新实体信息
 * @param obj
 * @return
 * @throws SQLException
 */
public int modifyEntityRegisterInfo(EntityRegisterInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tentity_register_info set register_date=?,register_time=?,oper_no=?,register_type=?,bill_type=?,bill_no=?,bill_money=?,issue_dt=?,due_dt=?,accept_dt=?,accept_time=?,acceptor=?,acceptor_bank_no=?,remitter=?,payee_name=?,sign_cancel_dt=?,sign_cancel_time=?,dscnt_dt=?,dscnt_propsr_nm=?,dscnt_propsr_bank_no=?,dscnt_bk_nm=?,dscnt_bk_bank_no=?,rdscnt_wthcomrclbk_dt=?,rdscnt_wthcntrlbk_dt=?,collztn_dt=?,collztn_propsr_nm=?,collztn_bk_nm=?,collztn_bank_no=?,unchain_collztn_dt=?,unchain_collztn_time=?,former_collztn_bk_nm=?,former_collztn_bank_no=?,former_collztn_propsr_nm=?,colltn_dt=?,colltn_count=?,colltn_bank_nm=?,colltn_bank_no=?,prncpl_nm=?,sttlm_pay_dt=?,sttlm_pay_time=?,pmtrfsed_dt=?,dshnr_cd=?,dshnr_rsn=?,sspdg_pmt_tp=?,sspdg_pmt_dt=?,sspdg_pmt_time=?,propsr_nm=?,oprtr_nm=?,anlg_sspdg_pmt_tp=?,anlg_sspdg_pmt_dt=?,anlg_sspdg_pmt_time=?,dispel_propsr_nm=?,dispel_oprtr_nm=?,remark=?,oper_status=?,contract_no=?,invoice_no=?,agree_no=?,approve_status=?,is_submit=?,reply_flag=?,error_reason=?,msg_id=?,intrstrate_1=?,intrstrate_2=?,dscntagreeno=?,oper_branch_no=?,reg_branch_no=? where id=?",
	obj.getRegisterDate(),obj.getRegisterTime(),obj.getOperNo(),obj.getRegisterType(),
	obj.getBillType(),obj.getBillNo(),obj.getBillMoney(),obj.getIssueDt(),
	obj.getDueDt(),obj.getAcceptDt(),obj.getAcceptTime(),obj.getAcceptor(),
	obj.getAcceptorBankNo(),obj.getRemitter(),obj.getPayeeName(),obj.getSignCancelDt(),
	obj.getSignCancelTime(),obj.getDscntDt(),obj.getDscntPropsrNm(),obj.getDscntPropsrBankNo(),
	obj.getDscntBkNm(),obj.getDscntBkBankNo(),obj.getRdscntWthcomrclbkDt(),obj.getRdscntWthcntrlbkDt(),
	obj.getCollztnDt(),obj.getCollztnPropsrNm(),obj.getCollztnBkNm(),obj.getCollztnBankNo(),
	obj.getUnchainCollztnDt(),obj.getUnchainCollztnTime(),obj.getFormerCollztnBkNm(),obj.getFormerCollztnBankNo(),
	obj.getFormerCollztnPropsrNm(),obj.getColltnDt(),obj.getColltnCount(),obj.getColltnBankNm(),
	obj.getColltnBankNo(),obj.getPrncplNm(),obj.getSttlmPayDt(),obj.getSttlmPayTime(),
	obj.getPmtrfsedDt(),obj.getDshnrCd(),obj.getDshnrRsn(),obj.getSspdgPmtTp(),
	obj.getSspdgPmtDt(),obj.getSspdgPmtTime(),obj.getPropsrNm(),obj.getOprtrNm(),
	obj.getAnlgSspdgPmtTp(),obj.getAnlgSspdgPmtDt(),obj.getAnlgSspdgPmtTime(),obj.getDispelPropsrNm(),
	obj.getDispelOprtrNm(),obj.getRemark(),obj.getOperStatus(),obj.getContractNo(),
	obj.getInvoiceNo(),obj.getAgreeNo(),obj.getApproveStatus(),obj.getIsSubmit(),
	obj.getReplyFlag(),obj.getErrorReason(),obj.getMsgId(),obj.getIntrstrate1(),
	obj.getIntrstrate2(),obj.getDscntagreeno(),obj.getOperBranchNo(),obj.getRegBranchNo(),
	obj.getId());
}

public int modifyEntityRegisterInfo(EntityRegisterInfo obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tentity_register_info set register_date=?,register_time=?,oper_no=?,register_type=?,bill_type=?,bill_no=?,bill_money=?,issue_dt=?,due_dt=?,accept_dt=?,accept_time=?,acceptor=?,acceptor_bank_no=?,remitter=?,payee_name=?,sign_cancel_dt=?,sign_cancel_time=?,dscnt_dt=?,dscnt_propsr_nm=?,dscnt_propsr_bank_no=?,dscnt_bk_nm=?,dscnt_bk_bank_no=?,rdscnt_wthcomrclbk_dt=?,rdscnt_wthcntrlbk_dt=?,collztn_dt=?,collztn_propsr_nm=?,collztn_bk_nm=?,collztn_bank_no=?,unchain_collztn_dt=?,unchain_collztn_time=?,former_collztn_bk_nm=?,former_collztn_bank_no=?,former_collztn_propsr_nm=?,colltn_dt=?,colltn_count=?,colltn_bank_nm=?,colltn_bank_no=?,prncpl_nm=?,sttlm_pay_dt=?,sttlm_pay_time=?,pmtrfsed_dt=?,dshnr_cd=?,dshnr_rsn=?,sspdg_pmt_tp=?,sspdg_pmt_dt=?,sspdg_pmt_time=?,propsr_nm=?,oprtr_nm=?,anlg_sspdg_pmt_tp=?,anlg_sspdg_pmt_dt=?,anlg_sspdg_pmt_time=?,dispel_propsr_nm=?,dispel_oprtr_nm=?,remark=?,oper_status=?,contract_no=?,invoice_no=?,agree_no=?,approve_status=?,is_submit=?,reply_flag=?,error_reason=?,msg_id=?,intrstrate_1=?,intrstrate_2=?,dscntagreeno=?,oper_branch_no=?,reg_branch_no=? where id=?",
	obj.getRegisterDate(),obj.getRegisterTime(),obj.getOperNo(),obj.getRegisterType(),
	obj.getBillType(),obj.getBillNo(),obj.getBillMoney(),obj.getIssueDt(),
	obj.getDueDt(),obj.getAcceptDt(),obj.getAcceptTime(),obj.getAcceptor(),
	obj.getAcceptorBankNo(),obj.getRemitter(),obj.getPayeeName(),obj.getSignCancelDt(),
	obj.getSignCancelTime(),obj.getDscntDt(),obj.getDscntPropsrNm(),obj.getDscntPropsrBankNo(),
	obj.getDscntBkNm(),obj.getDscntBkBankNo(),obj.getRdscntWthcomrclbkDt(),obj.getRdscntWthcntrlbkDt(),
	obj.getCollztnDt(),obj.getCollztnPropsrNm(),obj.getCollztnBkNm(),obj.getCollztnBankNo(),
	obj.getUnchainCollztnDt(),obj.getUnchainCollztnTime(),obj.getFormerCollztnBkNm(),obj.getFormerCollztnBankNo(),
	obj.getFormerCollztnPropsrNm(),obj.getColltnDt(),obj.getColltnCount(),obj.getColltnBankNm(),
	obj.getColltnBankNo(),obj.getPrncplNm(),obj.getSttlmPayDt(),obj.getSttlmPayTime(),
	obj.getPmtrfsedDt(),obj.getDshnrCd(),obj.getDshnrRsn(),obj.getSspdgPmtTp(),
	obj.getSspdgPmtDt(),obj.getSspdgPmtTime(),obj.getPropsrNm(),obj.getOprtrNm(),
	obj.getAnlgSspdgPmtTp(),obj.getAnlgSspdgPmtDt(),obj.getAnlgSspdgPmtTime(),obj.getDispelPropsrNm(),
	obj.getDispelOprtrNm(),obj.getRemark(),obj.getOperStatus(),obj.getContractNo(),
	obj.getInvoiceNo(),obj.getAgreeNo(),obj.getApproveStatus(),obj.getIsSubmit(),
	obj.getReplyFlag(),obj.getErrorReason(),obj.getMsgId(),obj.getIntrstrate1(),
	obj.getIntrstrate2(),obj.getDscntagreeno(),obj.getOperBranchNo(),obj.getRegBranchNo(),
	id);
}

public EntityRegisterInfo getEntityRegisterInfo(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	EntityRegisterInfo obj = (EntityRegisterInfo)session.getObject("select * from tentity_register_info where id=?",EntityRegisterInfo.class,id);
	return obj;
}

/**
 * 根据请求报文编号查询票据
 * @param msgId
 * @return
 * @throws SQLException
 */
public EntityRegisterInfo getEntityRegisterInfoByMsgId(String msgId) throws SQLException {
	IDB session = DBFactory.getDB();
	EntityRegisterInfo obj = (EntityRegisterInfo)session.getObject("select * from tentity_register_info where msg_id=?",EntityRegisterInfo.class,msgId);
	return obj;
}
}
