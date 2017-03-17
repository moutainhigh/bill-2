/********************************************
* 文件名称: AcptColltnRegDao.java
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
package com.herongtech.console.domain.acpt.dao;

import java.sql.SQLException;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.acpt.bean.AcptColltnReg;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class AcptColltnRegDao{

public int addAcptColltnReg(AcptColltnReg obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tacpt_colltn_reg(id,acpt_id,branch_no,reg_flow_no,bill_no,bill_class,bill_type,busi_type,drawee_acct,drawee_name,drawee_bank_no,drawee_bank_name_name,payee_acct,payee_name,payee_bank_no,payee_bank_name,colltn_amt,pay_amt,compens_amt,payment_path,bank_type,city,pay_wait_order,reg_dt,is_delay,delay_reason,account_dt,reject_dt,reject_code,reject_reason,cust_remark,bank_remark,colltn_status,oper_no,oper_time,audit_user_no,audit_time,settle_mark,fund,yz_source)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getAcptId(),obj.getBranchNo(),obj.getRegFlowNo(),obj.getBillNo(),
	obj.getBillClass(),obj.getBillType(),obj.getBusiType(),obj.getDraweeAcct(),
	obj.getDraweeName(),obj.getDraweeBankNo(),obj.getDraweeBankNameName(),obj.getPayeeAcct(),
	obj.getPayeeName(),obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getColltnAmt(),
	obj.getPayAmt(),obj.getCompensAmt(),obj.getPaymentPath(),obj.getBankType(),
	obj.getCity(),obj.getPayWaitOrder(),obj.getRegDt(),obj.getIsDelay(),
	obj.getDelayReason(),obj.getAccountDt(),obj.getRejectDt(),obj.getRejectCode(),
	obj.getRejectReason(),obj.getCustRemark(),obj.getBankRemark(),obj.getColltnStatus(),
	obj.getOperNo(),obj.getOperTime(),obj.getAuditUserNo(),obj.getAuditTime(),
	obj.getSettleMark(),obj.getFund(),obj.getYzSource());
}

public int deleteAcptColltnReg(AcptColltnReg obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacpt_colltn_reg where id=?",obj.getId());
}

public int deleteAcptColltnReg(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacpt_colltn_reg where id=?",id);
}

public int modifyAcptColltnReg(AcptColltnReg obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacpt_colltn_reg set acpt_id=?,branch_no=?,reg_flow_no=?,bill_no=?,bill_class=?,bill_type=?,busi_type=?,drawee_acct=?,drawee_name=?,drawee_bank_no=?,drawee_bank_name_name=?,payee_acct=?,payee_name=?,payee_bank_no=?,payee_bank_name=?,colltn_amt=?,pay_amt=?,compens_amt=?,payment_path=?,bank_type=?,city=?,pay_wait_order=?,reg_dt=?,is_delay=?,delay_reason=?,account_dt=?,reject_dt=?,reject_code=?,reject_reason=?,cust_remark=?,bank_remark=?,colltn_status=?,oper_no=?,oper_time=?,audit_user_no=?,audit_time=?,settle_mark=?,fund=?,yz_source=? where id=?",
	obj.getAcptId(),obj.getBranchNo(),obj.getRegFlowNo(),obj.getBillNo(),
	obj.getBillClass(),obj.getBillType(),obj.getBusiType(),obj.getDraweeAcct(),
	obj.getDraweeName(),obj.getDraweeBankNo(),obj.getDraweeBankNameName(),obj.getPayeeAcct(),
	obj.getPayeeName(),obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getColltnAmt(),
	obj.getPayAmt(),obj.getCompensAmt(),obj.getPaymentPath(),obj.getBankType(),
	obj.getCity(),obj.getPayWaitOrder(),obj.getRegDt(),obj.getIsDelay(),
	obj.getDelayReason(),obj.getAccountDt(),obj.getRejectDt(),obj.getRejectCode(),
	obj.getRejectReason(),obj.getCustRemark(),obj.getBankRemark(),obj.getColltnStatus(),
	obj.getOperNo(),obj.getOperTime(),obj.getAuditUserNo(),obj.getAuditTime(),
	obj.getSettleMark(),obj.getFund(),obj.getYzSource(),obj.getId());
}

public int modifyAcptColltnReg(AcptColltnReg obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacpt_colltn_reg set acpt_id=?,branch_no=?,reg_flow_no=?,bill_no=?,bill_class=?,bill_type=?,busi_type=?,drawee_acct=?,drawee_name=?,drawee_bank_no=?,drawee_bank_name_name=?,payee_acct=?,payee_name=?,payee_bank_no=?,payee_bank_name=?,colltn_amt=?,pay_amt=?,compens_amt=?,payment_path=?,bank_type=?,city=?,pay_wait_order=?,reg_dt=?,is_delay=?,delay_reason=?,account_dt=?,reject_dt=?,reject_code=?,reject_reason=?,cust_remark=?,bank_remark=?,colltn_status=?,oper_no=?,oper_time=?,audit_user_no=?,audit_time=?,settle_mark=?,fund=?,yz_source=? where id=?",
	obj.getAcptId(),obj.getBranchNo(),obj.getRegFlowNo(),obj.getBillNo(),
	obj.getBillClass(),obj.getBillType(),obj.getBusiType(),obj.getDraweeAcct(),
	obj.getDraweeName(),obj.getDraweeBankNo(),obj.getDraweeBankNameName(),obj.getPayeeAcct(),
	obj.getPayeeName(),obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getColltnAmt(),
	obj.getPayAmt(),obj.getCompensAmt(),obj.getPaymentPath(),obj.getBankType(),
	obj.getCity(),obj.getPayWaitOrder(),obj.getRegDt(),obj.getIsDelay(),
	obj.getDelayReason(),obj.getAccountDt(),obj.getRejectDt(),obj.getRejectCode(),
	obj.getRejectReason(),obj.getCustRemark(),obj.getBankRemark(),obj.getColltnStatus(),
	obj.getOperNo(),obj.getOperTime(),obj.getAuditUserNo(),obj.getAuditTime(),
	obj.getSettleMark(),obj.getFund(),obj.getYzSource(),id);
}

public AcptColltnReg getAcptColltnReg(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	AcptColltnReg obj = (AcptColltnReg)session.getObject("select * from tacpt_colltn_reg where id=?",AcptColltnReg.class,id);
	return obj;
}

public AcptColltnReg getAcptColltnRegForBillNo(String billNo) throws SQLException {
	IDB session = DBFactory.getDB();
	AcptColltnReg obj = (AcptColltnReg)session.getObject("select * from tacpt_colltn_reg where bill_no=? order by id desc",AcptColltnReg.class,billNo);
	return obj;
}

public int modifyAcptColltnRegForBillNo(AcptColltnReg obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacpt_colltn_reg set id=?,acpt_id=?,branch_no=?,reg_flow_no=?,bill_class=?,bill_type=?,busi_type=?,drawee_acct=?,drawee_name=?,drawee_bank_no=?,drawee_bank_name_name=?,payee_acct=?,payee_name=?,payee_bank_no=?,payee_bank_name=?,colltn_amt=?,pay_amt=?,compens_amt=?,payment_path=?,bank_type=?,city=?,pay_wait_order=?,reg_dt=?,is_delay=?,delay_reason=?,account_dt=?,reject_dt=?,reject_code=?,reject_reason=?,cust_remark=?,bank_remark=?,colltn_status=?,oper_no=?,oper_time=?,audit_user_no=?,audit_time=?,settle_mark=?,fund=?,yz_source=? where bill_no=?",
	obj.getId(),obj.getAcptId(),obj.getBranchNo(),obj.getRegFlowNo(),
	obj.getBillClass(),obj.getBillType(),obj.getBusiType(),obj.getDraweeAcct(),
	obj.getDraweeName(),obj.getDraweeBankNo(),obj.getDraweeBankNameName(),obj.getPayeeAcct(),
	obj.getPayeeName(),obj.getPayeeBankNo(),obj.getPayeeBankName(),obj.getColltnAmt(),
	obj.getPayAmt(),obj.getCompensAmt(),obj.getPaymentPath(),obj.getBankType(),
	obj.getCity(),obj.getPayWaitOrder(),obj.getRegDt(),obj.getIsDelay(),
	obj.getDelayReason(),obj.getAccountDt(),obj.getRejectDt(),obj.getRejectCode(),
	obj.getRejectReason(),obj.getCustRemark(),obj.getBankRemark(),obj.getColltnStatus(),
	obj.getOperNo(),obj.getOperTime(),obj.getAuditUserNo(),obj.getAuditTime(),
	obj.getSettleMark(),obj.getFund(),obj.getYzSource(),obj.getBillNo());

}


}
