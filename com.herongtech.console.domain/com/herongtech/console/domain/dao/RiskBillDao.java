/********************************************
* 文件名称: RiskBillDao.java
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
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.RiskBill;
public class RiskBillDao{

public int addRiskBill(RiskBill obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trisk_bill(id,bill_no,msg_id,bill_type,acceptor,acceptor_bank_no,bill_money,due_dt,issue_dt,description,status,bill1,bill2,postdate,posttime,enterdate,entertime,remitter,urgeapplyname,courtname,matchfileno)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getBillNo(),obj.getMsgId(),obj.getBillType(),obj.getAcceptor(),
	obj.getAcceptorBankNo(),obj.getBillMoney(),obj.getDueDt(),obj.getIssueDt(),
	obj.getDescription(),obj.getStatus(),obj.getBill1(),obj.getBill2(),
	obj.getPostdate(),obj.getPosttime(),obj.getEnterdate(),obj.getEntertime(),
	obj.getRemitter(),obj.getUrgeapplyname(),obj.getCourtname(),obj.getMatchfileno());
}

public int deleteRiskBill(RiskBill obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trisk_bill where id=?",obj.getId());
}

public int deleteRiskBill(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trisk_bill where id=?",id);
}

public int modifyRiskBill(RiskBill obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trisk_bill set bill_no=?,msg_id=?,bill_type=?,acceptor=?,acceptor_bank_no=?,bill_money=?,due_dt=?,issue_dt=?,description=?,status=?,bill1=?,bill2=?,postdate=?,posttime=?,enterdate=?,entertime=?,remitter=?,urgeapplyname=?,courtname=?,matchfileno=? where id=?",
	obj.getBillNo(),obj.getMsgId(),obj.getBillType(),obj.getAcceptor(),
	obj.getAcceptorBankNo(),obj.getBillMoney(),obj.getDueDt(),obj.getIssueDt(),
	obj.getDescription(),obj.getStatus(),obj.getBill1(),obj.getBill2(),
	obj.getPostdate(),obj.getPosttime(),obj.getEnterdate(),obj.getEntertime(),
	obj.getRemitter(),obj.getUrgeapplyname(),obj.getCourtname(),obj.getMatchfileno(),
	obj.getId());
}

public int modifyRiskBill(RiskBill obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trisk_bill set bill_no=?,msg_id=?,bill_type=?,acceptor=?,acceptor_bank_no=?,bill_money=?,due_dt=?,issue_dt=?,description=?,status=?,bill1=?,bill2=?,postdate=?,posttime=?,enterdate=?,entertime=?,remitter=?,urgeapplyname=?,courtname=?,matchfileno=? where id=?",
	obj.getBillNo(),obj.getMsgId(),obj.getBillType(),obj.getAcceptor(),
	obj.getAcceptorBankNo(),obj.getBillMoney(),obj.getDueDt(),obj.getIssueDt(),
	obj.getDescription(),obj.getStatus(),obj.getBill1(),obj.getBill2(),
	obj.getPostdate(),obj.getPosttime(),obj.getEnterdate(),obj.getEntertime(),
	obj.getRemitter(),obj.getUrgeapplyname(),obj.getCourtname(),obj.getMatchfileno(),
	id);
}

public RiskBill getRiskBill(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	RiskBill obj = (RiskBill)session.getObject("select * from trisk_bill where id=?",RiskBill.class,id);
	return obj;
}

/**
 * 风险票检查
 * @param billNos	票号	多个票号之间使用逗号分隔
 * @param workDay	当前营业日
 * @param createDay	风险票录入日期
 * @return	List<RiskBill>	风险票集合
 * @throws SQLException
 */


public List<RiskBill> getRiskBillByBills(String billNos,String workDay,String createDay) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer();
	sb.append("SELECT * FROM TRISK_BILL WHERE  BILL2 IN (")
		.append(billNos)
		.append(") AND(DUE_DT >='")
		.append(workDay)
		.append("'OR (DUE_DT =' ' AND ENTERDATE >='")
		.append(createDay)
		.append("'))");
	List<RiskBill> objs = session.getObjectList(sb.toString(),RiskBill.class);
	return objs;
}

}
