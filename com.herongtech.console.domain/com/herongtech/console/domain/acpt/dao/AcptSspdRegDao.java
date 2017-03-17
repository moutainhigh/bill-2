/********************************************
* 文件名称: AcptSspdRegDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: wuzc
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.acpt.dao;

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.acpt.bean.AcptSspdReg;
public class AcptSspdRegDao{

public int addAcptSspdReg(AcptSspdReg obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tacpt_sspd_reg(id,acpt_id,sspd_acct,sspd_name,sspd_cert_type,sspd_cert_no,sspd_agent_name,sspd_agent_cert_type,sspd_agent_cert_no,sspd_dt,sspd_branch_no,sspd_oper_name,sspd_oper_no,sspd_time,sspd_flow_no,anlg_sspd_acct,anlg_sspd_name,anlg_sspd_cert_type,anlg_sspd_cert_no,anlg_sspd_agent_name,anlg_sspd_agent_cert_type,anlg_sspd_agent_cert_no,anlg_sspd_dt,anlg_sspd_branch_no,anlg_sspd_oper_name,anlg_sspd_oper_no,anlg_sspd_time,anlg_sspd_flow_no,remitter_acct,remitter,bill_type,voucher_type,bill_no,bill_money,bill_status,issue_dt,due_dt,acceptor_branch_no,acceptor_bank_no,acceptor_bank_name,sspd_status,is_pub_exhort,is_anlg_notification,remark,yz_source)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getAcptId(),obj.getSspdAcct(),obj.getSspdName(),obj.getSspdCertType(),
	obj.getSspdCertNo(),obj.getSspdAgentName(),obj.getSspdAgentCertType(),obj.getSspdAgentCertNo(),
	obj.getSspdDt(),obj.getSspdBranchNo(),obj.getSspdOperName(),obj.getSspdOperNo(),
	obj.getSspdTime(),obj.getSspdFlowNo(),obj.getAnlgSspdAcct(),obj.getAnlgSspdName(),
	obj.getAnlgSspdCertType(),obj.getAnlgSspdCertNo(),obj.getAnlgSspdAgentName(),obj.getAnlgSspdAgentCertType(),
	obj.getAnlgSspdAgentCertNo(),obj.getAnlgSspdDt(),obj.getAnlgSspdBranchNo(),obj.getAnlgSspdOperName(),
	obj.getAnlgSspdOperNo(),obj.getAnlgSspdTime(),obj.getAnlgSspdFlowNo(),obj.getRemitterAcct(),
	obj.getRemitter(),obj.getBillType(),obj.getVoucherType(),obj.getBillNo(),
	obj.getBillMoney(),obj.getBillStatus(),obj.getIssueDt(),obj.getDueDt(),
	obj.getAcceptorBranchNo(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getSspdStatus(),
	obj.getIsPubExhort(),obj.getIsAnlgNotification(),obj.getRemark(),obj.getYzSource());
}

public int deleteAcptSspdReg(AcptSspdReg obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacpt_sspd_reg where id=?",obj.getId());
}

public int deleteAcptSspdReg(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacpt_sspd_reg where id=?",id);
}

public int modifyAcptSspdReg(AcptSspdReg obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacpt_sspd_reg set acpt_id=?,sspd_acct=?,sspd_name=?,sspd_cert_type=?,sspd_cert_no=?,sspd_agent_name=?,sspd_agent_cert_type=?,sspd_agent_cert_no=?,sspd_dt=?,sspd_branch_no=?,sspd_oper_name=?,sspd_oper_no=?,sspd_time=?,sspd_flow_no=?,anlg_sspd_acct=?,anlg_sspd_name=?,anlg_sspd_cert_type=?,anlg_sspd_cert_no=?,anlg_sspd_agent_name=?,anlg_sspd_agent_cert_type=?,anlg_sspd_agent_cert_no=?,anlg_sspd_dt=?,anlg_sspd_branch_no=?,anlg_sspd_oper_name=?,anlg_sspd_oper_no=?,anlg_sspd_time=?,anlg_sspd_flow_no=?,remitter_acct=?,remitter=?,bill_type=?,voucher_type=?,bill_no=?,bill_money=?,bill_status=?,issue_dt=?,due_dt=?,acceptor_branch_no=?,acceptor_bank_no=?,acceptor_bank_name=?,sspd_status=?,is_pub_exhort=?,is_anlg_notification=?,remark=?,yz_source=? where id=?",
	obj.getAcptId(),obj.getSspdAcct(),obj.getSspdName(),obj.getSspdCertType(),
	obj.getSspdCertNo(),obj.getSspdAgentName(),obj.getSspdAgentCertType(),obj.getSspdAgentCertNo(),
	obj.getSspdDt(),obj.getSspdBranchNo(),obj.getSspdOperName(),obj.getSspdOperNo(),
	obj.getSspdTime(),obj.getSspdFlowNo(),obj.getAnlgSspdAcct(),obj.getAnlgSspdName(),
	obj.getAnlgSspdCertType(),obj.getAnlgSspdCertNo(),obj.getAnlgSspdAgentName(),obj.getAnlgSspdAgentCertType(),
	obj.getAnlgSspdAgentCertNo(),obj.getAnlgSspdDt(),obj.getAnlgSspdBranchNo(),obj.getAnlgSspdOperName(),
	obj.getAnlgSspdOperNo(),obj.getAnlgSspdTime(),obj.getAnlgSspdFlowNo(),obj.getRemitterAcct(),
	obj.getRemitter(),obj.getBillType(),obj.getVoucherType(),obj.getBillNo(),
	obj.getBillMoney(),obj.getBillStatus(),obj.getIssueDt(),obj.getDueDt(),
	obj.getAcceptorBranchNo(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getSspdStatus(),
	obj.getIsPubExhort(),obj.getIsAnlgNotification(),obj.getRemark(),obj.getYzSource(),
	obj.getId());

}

public int modifyAcptSspdReg(AcptSspdReg obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacpt_sspd_reg set acpt_id=?,sspd_acct=?,sspd_name=?,sspd_cert_type=?,sspd_cert_no=?,sspd_agent_name=?,sspd_agent_cert_type=?,sspd_agent_cert_no=?,sspd_dt=?,sspd_branch_no=?,sspd_oper_name=?,sspd_oper_no=?,sspd_time=?,sspd_flow_no=?,anlg_sspd_acct=?,anlg_sspd_name=?,anlg_sspd_cert_type=?,anlg_sspd_cert_no=?,anlg_sspd_agent_name=?,anlg_sspd_agent_cert_type=?,anlg_sspd_agent_cert_no=?,anlg_sspd_dt=?,anlg_sspd_branch_no=?,anlg_sspd_oper_name=?,anlg_sspd_oper_no=?,anlg_sspd_time=?,anlg_sspd_flow_no=?,remitter_acct=?,remitter=?,bill_type=?,voucher_type=?,bill_no=?,bill_money=?,bill_status=?,issue_dt=?,due_dt=?,acceptor_branch_no=?,acceptor_bank_no=?,acceptor_bank_name=?,sspd_status=?,is_pub_exhort=?,is_anlg_notification=?,remark=?,yz_source=? where id=?",
	obj.getAcptId(),obj.getSspdAcct(),obj.getSspdName(),obj.getSspdCertType(),
	obj.getSspdCertNo(),obj.getSspdAgentName(),obj.getSspdAgentCertType(),obj.getSspdAgentCertNo(),
	obj.getSspdDt(),obj.getSspdBranchNo(),obj.getSspdOperName(),obj.getSspdOperNo(),
	obj.getSspdTime(),obj.getSspdFlowNo(),obj.getAnlgSspdAcct(),obj.getAnlgSspdName(),
	obj.getAnlgSspdCertType(),obj.getAnlgSspdCertNo(),obj.getAnlgSspdAgentName(),obj.getAnlgSspdAgentCertType(),
	obj.getAnlgSspdAgentCertNo(),obj.getAnlgSspdDt(),obj.getAnlgSspdBranchNo(),obj.getAnlgSspdOperName(),
	obj.getAnlgSspdOperNo(),obj.getAnlgSspdTime(),obj.getAnlgSspdFlowNo(),obj.getRemitterAcct(),
	obj.getRemitter(),obj.getBillType(),obj.getVoucherType(),obj.getBillNo(),
	obj.getBillMoney(),obj.getBillStatus(),obj.getIssueDt(),obj.getDueDt(),
	obj.getAcceptorBranchNo(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getSspdStatus(),
	obj.getIsPubExhort(),obj.getIsAnlgNotification(),obj.getRemark(),obj.getYzSource(),
	id);
}

public AcptSspdReg getAcptSspdReg(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	AcptSspdReg obj = (AcptSspdReg)session.getObject("select * from tacpt_sspd_reg where id=?",AcptSspdReg.class,id);
	return obj;
}

}
