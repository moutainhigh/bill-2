/********************************************
* 文件名称: SaleSquareDao.java
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
package com.herongtech.console.domain.sale.dao;

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.sale.bean.SaleSquare;
public class SaleSquareDao{

public int addSaleSquare(SaleSquare obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tsale_square(sale_square_id,salemx_id,sale_id,rgct_id,remark,oper_date,oper_time,create_date,create_time,ex_serial,after_flow_no,status,branch_no,oper_no,order_id)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getSaleSquareId(),obj.getSalemxId(),obj.getSaleId(),obj.getRgctId(),obj.getRemark(),
	obj.getOperDate(),obj.getOperTime(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getExSerial(),obj.getAfterFlowNo(),obj.getStatus(),obj.getBranchNo(),
	obj.getOperNo(),obj.getOrderId());
}

public int deleteSaleSquare(SaleSquare obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsale_square where sale_square_id=?",obj.getSaleSquareId());
}

public int deleteSaleSquare(String saleSquareId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsale_square where sale_square_id=?",saleSquareId);
}

public int modifySaleSquare(SaleSquare obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsale_square set salemx_id=?,sale_id=?,rgct_id=?,remark=?,oper_date=?,oper_time=?,create_date=?,create_time=?,ex_serial=?,after_flow_no=?,status=?,branch_no=?,oper_no=?,order_id=? where sale_square_id=?",
	obj.getSalemxId(),obj.getSaleId(),obj.getRgctId(),obj.getRemark(),
	obj.getOperDate(),obj.getOperTime(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getExSerial(),obj.getAfterFlowNo(),obj.getStatus(),obj.getBranchNo(),
	obj.getOperNo(),obj.getOrderId(),obj.getSaleSquareId());
}

public int modifySaleSquare(SaleSquare obj,String saleSquareId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsale_square set salemx_id=?,sale_id=?,rgct_id=?,remark=?,oper_date=?,oper_time=?,create_date=?,create_time=?,ex_serial=?,after_flow_no=?,status=?,branch_no=?,oper_no=?,order_id=? where sale_square_id=?",
	obj.getSalemxId(),obj.getSaleId(),obj.getRgctId(),obj.getRemark(),
	obj.getOperDate(),obj.getOperTime(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getExSerial(),obj.getAfterFlowNo(),obj.getStatus(),obj.getBranchNo(),
	obj.getOperNo(),obj.getOrderId(),saleSquareId);
}

public SaleSquare getSaleSquare(String saleSquareId) throws SQLException {
	IDB session = DBFactory.getDB();
	SaleSquare obj = (SaleSquare)session.getObject("select * from tsale_square where sale_square_id=?",SaleSquare.class,saleSquareId);
	return obj;
}

}
