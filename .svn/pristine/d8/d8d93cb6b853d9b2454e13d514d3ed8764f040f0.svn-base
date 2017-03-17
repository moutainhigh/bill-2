/********************************************
* 文件名称: ProdLimitTypeDao.java
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
package com.herongtech.console.domain.common.dao;

import java.sql.SQLException;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.common.bean.ProdLimitType;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class ProdLimitTypeDao{

public int addProdLimitType(ProdLimitType obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tprod_limit_type(id,prod_no,limit_type,buy_type,is_inner,bill_type,buy_into_type)values(?,?,?,?,?,?,?)",
	obj.getId(),obj.getProdNo(),obj.getLimitType(),obj.getBuyType(),obj.getIsInner(),
	obj.getBillType(),obj.getBuyIntoType());
}

public int deleteProdLimitType(ProdLimitType obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tprod_limit_type where id=?",obj.getId());
}

public int deleteProdLimitType(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tprod_limit_type where id=?",id);
}

public int modifyProdLimitType(ProdLimitType obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tprod_limit_type set prod_no=?,limit_type=?,buy_type=?,is_inner=?,bill_type=?,buy_into_type=? where id=?",
	obj.getProdNo(),obj.getLimitType(),obj.getBuyType(),obj.getIsInner(),
	obj.getBillType(),obj.getBuyIntoType(),obj.getId());
}

public int modifyProdLimitType(ProdLimitType obj,Long id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tprod_limit_type set prod_no=?,limit_type=?,buy_type=?,is_inner=?,bill_type=?,buy_into_type=? where id=?",
	obj.getProdNo(),obj.getLimitType(),obj.getBuyType(),obj.getIsInner(),
	obj.getBillType(),obj.getBuyIntoType(),id);
}

public ProdLimitType getProdLimitType(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	ProdLimitType obj = (ProdLimitType)session.getObject("select * from tprod_limit_type where id=?",ProdLimitType.class,id);
	return obj;
}

public ProdLimitType getProdLimitTypeByProdNo(String prodNo) throws SQLException {
	IDB session = DBFactory.getDB();
	ProdLimitType obj = (ProdLimitType)session.getObject("select * from tprod_limit_type where prod_no=?",ProdLimitType.class,prodNo);
	return obj;
}

}
