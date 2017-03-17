/********************************************
* 文件名称: MonthBillDetailInfoDao.java
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
package com.herongtech.console.domain.dao;

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.MonthBillDetailInfo;
public class MonthBillDetailInfoDao{

public int addMonthBillDetailInfo(MonthBillDetailInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tmonth_bill_detail_info(id,storemx_id,bill_no,bill_type,bill_money,issue_dt,due_dt,convery_mark,remitter_role,remitter_orgcode,remitter,remitter_acct,remitter_bank_no,credit_rate,credit_agency,credit_duedt,acceptor,acceptor_acct,acceptor_bank_no,payee,payee_acct,payee_bank_no,repeal_dt,repeal_time,hist_remitter_role,hist_orgcode,hist_remitter_acct,hist_remitter_bankno,hist_remitter_acpt_bankno)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getStoremxId(),obj.getBillNo(),obj.getBillType(),obj.getBillMoney(),
	obj.getIssueDt(),obj.getDueDt(),obj.getConveryMark(),obj.getRemitterRole(),
	obj.getRemitterOrgcode(),obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankNo(),
	obj.getCreditRate(),obj.getCreditAgency(),obj.getCreditDuedt(),obj.getAcceptor(),
	obj.getAcceptorAcct(),obj.getAcceptorBankNo(),obj.getPayee(),obj.getPayeeAcct(),
	obj.getPayeeBankNo(),obj.getRepealDt(),obj.getRepealTime(),obj.getHistRemitterRole(),
	obj.getHistOrgcode(),obj.getHistRemitterAcct(),obj.getHistRemitterBankno(),obj.getHistRemitterAcptBankno());
}

public int deleteMonthBillDetailInfo(MonthBillDetailInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();

	return session.execute("delete from tmonth_bill_detail_info where id=?",obj.getId());

}

public int modifyMonthBillDetailInfo(MonthBillDetailInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tmonth_bill_detail_info set id=?,storemx_id=?,bill_no=?,bill_type=?,bill_money=?,issue_dt=?,due_dt=?,convery_mark=?,remitter_role=?,remitter_orgcode=?,remitter=?,remitter_acct=?,remitter_bank_no=?,credit_rate=?,credit_agency=?,credit_duedt=?,acceptor=?,acceptor_acct=?,acceptor_bank_no=?,payee=?,payee_acct=?,payee_bank_no=?,repeal_dt=?,repeal_time=?,hist_remitter_role=?,hist_orgcode=?,hist_remitter_acct=?,hist_remitter_bankno=?,hist_remitter_acpt_bankno=? ",
	obj.getId(),obj.getStoremxId(),obj.getBillNo(),obj.getBillType(),
	obj.getBillMoney(),obj.getIssueDt(),obj.getDueDt(),obj.getConveryMark(),
	obj.getRemitterRole(),obj.getRemitterOrgcode(),obj.getRemitter(),obj.getRemitterAcct(),
	obj.getRemitterBankNo(),obj.getCreditRate(),obj.getCreditAgency(),obj.getCreditDuedt(),
	obj.getAcceptor(),obj.getAcceptorAcct(),obj.getAcceptorBankNo(),obj.getPayee(),
	obj.getPayeeAcct(),obj.getPayeeBankNo(),obj.getRepealDt(),obj.getRepealTime(),
	obj.getHistRemitterRole(),obj.getHistOrgcode(),obj.getHistRemitterAcct(),obj.getHistRemitterBankno(),
	obj.getHistRemitterAcptBankno());
}

public MonthBillDetailInfo getMonthBillDetailInfo() throws SQLException {
	IDB session = DBFactory.getDB();
	MonthBillDetailInfo obj = (MonthBillDetailInfo)session.getObject("select * from tmonth_bill_detail_info",MonthBillDetailInfo.class);
	return obj;
}

}
