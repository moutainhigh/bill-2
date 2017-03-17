/********************************************
* 文件名称: RebuySquareDao.java
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
package com.herongtech.console.domain.rebuy.dao;

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.rebuy.bean.RebuySquare;
public class RebuySquareDao{

public int addRebuySquare(RebuySquare obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trebuy_square(rebuy_square_id,rebuymx_id,cust_no,rebuy_id,rgct_id,remark,oper_date,oper_time,create_date,create_time,ex_serial,after_flow_no,status,branch_no,oper_no,order_id)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getRebuySquareId(),obj.getRebuymxId(),obj.getCustNo(),obj.getRebuyId(),obj.getRgctId(),
	obj.getRemark(),obj.getOperDate(),obj.getOperTime(),obj.getCreateDate(),
	obj.getCreateTime(),obj.getExSerial(),obj.getAfterFlowNo(),obj.getStatus(),
	obj.getBranchNo(),obj.getOperNo(),obj.getOrderId());
}

public int deleteRebuySquare(RebuySquare obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trebuy_square where rebuy_square_id=?",obj.getRebuySquareId());
}

public int deleteRebuySquare(String rebuySquareId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trebuy_square where rebuy_square_id=?",rebuySquareId);
}

public int modifyRebuySquare(RebuySquare obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trebuy_square set rebuymx_id=?,cust_no=?,rebuy_id=?,rgct_id=?,remark=?,oper_date=?,oper_time=?,create_date=?,create_time=?,ex_serial=?,after_flow_no=?,status=?,branch_no=?,oper_no=?,order_id=? where rebuy_square_id=?",
	obj.getRebuymxId(),obj.getCustNo(),obj.getRebuyId(),obj.getRgctId(),
	obj.getRemark(),obj.getOperDate(),obj.getOperTime(),obj.getCreateDate(),
	obj.getCreateTime(),obj.getExSerial(),obj.getAfterFlowNo(),obj.getStatus(),
	obj.getBranchNo(),obj.getOperNo(),obj.getOrderId(),obj.getRebuySquareId());
}

public int modifyRebuySquare(RebuySquare obj,String rebuySquareId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trebuy_square set rebuymx_id=?,cust_no=?,rebuy_id=?,rgct_id=?,remark=?,oper_date=?,oper_time=?,create_date=?,create_time=?,ex_serial=?,after_flow_no=?,status=?,branch_no=?,oper_no=?,order_id=? where rebuy_square_id=?",
	obj.getRebuymxId(),obj.getCustNo(),obj.getRebuyId(),obj.getRgctId(),
	obj.getRemark(),obj.getOperDate(),obj.getOperTime(),obj.getCreateDate(),
	obj.getCreateTime(),obj.getExSerial(),obj.getAfterFlowNo(),obj.getStatus(),
	obj.getBranchNo(),obj.getOperNo(),obj.getOrderId(),rebuySquareId);
}

public RebuySquare getRebuySquare(String rebuySquareId) throws SQLException {
	IDB session = DBFactory.getDB();
	RebuySquare obj = (RebuySquare)session.getObject("select * from trebuy_square where rebuy_square_id=?",RebuySquare.class,rebuySquareId);
	return obj;
}

}
