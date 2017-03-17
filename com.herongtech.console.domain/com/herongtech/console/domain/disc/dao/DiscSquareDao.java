/********************************************
* 文件名称: DiscSquareDao.java
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
package com.herongtech.console.domain.disc.dao;

import java.sql.SQLException;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.disc.bean.DiscSquare;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class DiscSquareDao{

public int addDiscSquare(DiscSquare obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tdisc_square(disc_square_id,disc_id,status,create_date,create_time,branch_no,oper_no,order_id,ex_serial,sale_order_id,total_counts,total_amount,total_pay_money,total_interest,account_date,account_time)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getDiscSquareId(),obj.getDiscId(),obj.getStatus(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getBranchNo(),obj.getOperNo(),obj.getOrderId(),obj.getExSerial(),
	obj.getSaleOrderId(),obj.getTotalCounts(),obj.getTotalAmount(),obj.getTotalPayMoney(),
	obj.getTotalInterest(),obj.getAccountDate(),obj.getAccountTime());
}

public int deleteDiscSquare(DiscSquare obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdisc_square where disc_square_id=?",obj.getDiscSquareId());
}

public int deleteDiscSquare(String discSquareId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdisc_square where disc_square_id=?",discSquareId);
}

public int modifyDiscSquare(DiscSquare obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdisc_square set disc_id=?,status=?,create_date=?,create_time=?,branch_no=?,oper_no=?,order_id=?,ex_serial=?,sale_order_id=?,total_counts=?,total_amount=?,total_pay_money=?,total_interest=?,account_date=?,account_time=? where disc_square_id=?",
	obj.getDiscId(),obj.getStatus(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getBranchNo(),obj.getOperNo(),obj.getOrderId(),obj.getExSerial(),
	obj.getSaleOrderId(),obj.getTotalCounts(),obj.getTotalAmount(),obj.getTotalPayMoney(),
	obj.getTotalInterest(),obj.getAccountDate(),obj.getAccountTime(),obj.getDiscSquareId());
}

public int modifyDiscSquare(DiscSquare obj,String discSquareId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdisc_square set disc_id=?,status=?,create_date=?,create_time=?,branch_no=?,oper_no=?,order_id=?,ex_serial=?,sale_order_id=?,total_counts=?,total_amount=?,total_pay_money=?,total_interest=?,account_date=?,account_time=? where disc_square_id=?",
	obj.getDiscId(),obj.getStatus(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getBranchNo(),obj.getOperNo(),obj.getOrderId(),obj.getExSerial(),
	obj.getSaleOrderId(),obj.getTotalCounts(),obj.getTotalAmount(),obj.getTotalPayMoney(),
	obj.getTotalInterest(),obj.getAccountDate(),obj.getAccountTime(),discSquareId);
}

public DiscSquare getDiscSquare(String discSquareId) throws SQLException {
	IDB session = DBFactory.getDB();
	DiscSquare obj = (DiscSquare)session.getObject("select * from tdisc_square where disc_square_id=?",DiscSquare.class,discSquareId);
	return obj;
}

}
