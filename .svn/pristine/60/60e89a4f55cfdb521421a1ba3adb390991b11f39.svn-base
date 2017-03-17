/********************************************
* 文件名称: AcctFlowInfoDao.java
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
import com.herongtech.console.domain.acct.bean.AcctFlowInfo;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class AcctFlowInfoDao{

public int addAcctFlowInfo(AcctFlowInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tacct_flow_info(acct_info_id,af_id,bill_no,rgct_id,info_id,trans_no,trans_dt,trans_tm,prod_no,bill_amonut,settlement_amt,settlement_intrst,trans_branch_no,acct_branch_no,rema_intrst,cur_rema_intrst,discrepancy_interest,descption,pro_id)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getAcctInfoId(),obj.getAfId(),obj.getBillNo(),obj.getRgctId(),obj.getInfoId(),
	obj.getTransNo(),obj.getTransDt(),obj.getTransTm(),obj.getProdNo(),
	obj.getBillAmonut(),obj.getSettlementAmt(),obj.getSettlementIntrst(),obj.getTransBranchNo(),
	obj.getAcctBranchNo(),obj.getRemaIntrst(),obj.getCurRemaIntrst(),obj.getDiscrepancyInterest(),
	obj.getDescption(),obj.getProId());
}

public int deleteAcctFlowInfo(AcctFlowInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacct_flow_info where acct_info_id=?",obj.getAcctInfoId());
}

public int deleteAcctFlowInfo(String acctInfoId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacct_flow_info where acct_info_id=?",acctInfoId);
}

public int modifyAcctFlowInfo(AcctFlowInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacct_flow_info set af_id=?,bill_no=?,rgct_id=?,info_id=?,trans_no=?,trans_dt=?,trans_tm=?,prod_no=?,bill_amonut=?,settlement_amt=?,settlement_intrst=?,trans_branch_no=?,acct_branch_no=?,rema_intrst=?,cur_rema_intrst=?,discrepancy_interest=?,descption=?,pro_id=? where acct_info_id=?",
	obj.getAfId(),obj.getBillNo(),obj.getRgctId(),obj.getInfoId(),
	obj.getTransNo(),obj.getTransDt(),obj.getTransTm(),obj.getProdNo(),
	obj.getBillAmonut(),obj.getSettlementAmt(),obj.getSettlementIntrst(),obj.getTransBranchNo(),
	obj.getAcctBranchNo(),obj.getRemaIntrst(),obj.getCurRemaIntrst(),obj.getDiscrepancyInterest(),
	obj.getDescption(),obj.getProId(),obj.getAcctInfoId());
}

public int modifyAcctFlowInfo(AcctFlowInfo obj,String acctInfoId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacct_flow_info set af_id=?,bill_no=?,rgct_id=?,info_id=?,trans_no=?,trans_dt=?,trans_tm=?,prod_no=?,bill_amonut=?,settlement_amt=?,settlement_intrst=?,trans_branch_no=?,acct_branch_no=?,rema_intrst=?,cur_rema_intrst=?,discrepancy_interest=?,descption=?,pro_id=? where acct_info_id=?",
	obj.getAfId(),obj.getBillNo(),obj.getRgctId(),obj.getInfoId(),
	obj.getTransNo(),obj.getTransDt(),obj.getTransTm(),obj.getProdNo(),
	obj.getBillAmonut(),obj.getSettlementAmt(),obj.getSettlementIntrst(),obj.getTransBranchNo(),
	obj.getAcctBranchNo(),obj.getRemaIntrst(),obj.getCurRemaIntrst(),obj.getDiscrepancyInterest(),
	obj.getDescption(),obj.getProId(),acctInfoId);
}

public AcctFlowInfo getAcctFlowInfo(String acctInfoId) throws SQLException {
	IDB session = DBFactory.getDB();
	AcctFlowInfo obj = (AcctFlowInfo)session.getObject("select * from tacct_flow_info where acct_info_id=?",AcctFlowInfo.class,acctInfoId);
	return obj;
}

}
