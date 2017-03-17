/********************************************
* 文件名称: AcctFlowDao.java
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
package com.herongtech.console.domain.acct.dao;

import java.sql.SQLException;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.acct.bean.AcctFlow;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class AcctFlowDao{

public int addAcctFlow(AcctFlow obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tacct_flow(af_id,fore_flow_no,back_flow_no,trans_no,trans_name,trans_dt,trans_tm,acct_type,acct_status,prod_no,trans_branch_no,trans_branch_name,trans_user_no,acct_branch_no,total_amount,settlement_money,settlement_interest)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getAfId(),obj.getForeFlowNo(),obj.getBackFlowNo(),obj.getTransNo(),obj.getTransName(),
	obj.getTransDt(),obj.getTransTm(),obj.getAcctType(),obj.getAcctStatus(),
	obj.getProdNo(),obj.getTransBranchNo(),obj.getTransBranchName(),obj.getTransUserNo(),
	obj.getAcctBranchNo(),obj.getTotalAmount(),obj.getSettlementMoney(),obj.getSettlementInterest());
}

public int deleteAcctFlow(AcctFlow obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacct_flow where af_id=?",obj.getAfId());
}

public int deleteAcctFlow(String afId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacct_flow where af_id=?",afId);
}

public int modifyAcctFlow(AcctFlow obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacct_flow set fore_flow_no=?,back_flow_no=?,trans_no=?,trans_name=?,trans_dt=?,trans_tm=?,acct_type=?,acct_status=?,prod_no=?,trans_branch_no=?,trans_branch_name=?,trans_user_no=?,acct_branch_no=?,total_amount=?,settlement_money=?,settlement_interest=? where af_id=?",
	obj.getForeFlowNo(),obj.getBackFlowNo(),obj.getTransNo(),obj.getTransName(),
	obj.getTransDt(),obj.getTransTm(),obj.getAcctType(),obj.getAcctStatus(),
	obj.getProdNo(),obj.getTransBranchNo(),obj.getTransBranchName(),obj.getTransUserNo(),
	obj.getAcctBranchNo(),obj.getTotalAmount(),obj.getSettlementMoney(),obj.getSettlementInterest(),
	obj.getAfId());
}

public int modifyAcctFlow(AcctFlow obj,String afId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacct_flow set fore_flow_no=?,back_flow_no=?,trans_no=?,trans_name=?,trans_dt=?,trans_tm=?,acct_type=?,acct_status=?,prod_no=?,trans_branch_no=?,trans_branch_name=?,trans_user_no=?,acct_branch_no=?,total_amount=?,settlement_money=?,settlement_interest=? where af_id=?",
	obj.getForeFlowNo(),obj.getBackFlowNo(),obj.getTransNo(),obj.getTransName(),
	obj.getTransDt(),obj.getTransTm(),obj.getAcctType(),obj.getAcctStatus(),
	obj.getProdNo(),obj.getTransBranchNo(),obj.getTransBranchName(),obj.getTransUserNo(),
	obj.getAcctBranchNo(),obj.getTotalAmount(),obj.getSettlementMoney(),obj.getSettlementInterest(),
	afId);
}

public AcctFlow getAcctFlow(String afId) throws SQLException {
	IDB session = DBFactory.getDB();
	AcctFlow obj = (AcctFlow)session.getObject("select * from tacct_flow where af_id=?",AcctFlow.class,afId);
	return obj;
}

}
