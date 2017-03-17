/********************************************
* 文件名称: ProductDao.java
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
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class ProductDao{

public int addProduct(Product obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tproduct(id,prod_no,prod_name,prod_status,create_time,parent_prod_no)values(?,?,?,?,?,?)",
	obj.getId(),obj.getProdNo(),obj.getProdName(),obj.getProdStatus(),obj.getCreateTime(),
	obj.getParentProdNo());
}

public int deleteProduct(Product obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tproduct where id=?",obj.getId());
}

public int deleteProduct(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tproduct where id=?",id);
}

public int modifyProduct(Product obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tproduct set prod_no=?,prod_name=?,prod_status=?,create_time=?,parent_prod_no=? where id=?",
	obj.getProdNo(),obj.getProdName(),obj.getProdStatus(),obj.getCreateTime(),
	obj.getParentProdNo(),obj.getId());
}

public int modifyProduct(Product obj,Long id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tproduct set prod_no=?,prod_name=?,prod_status=?,create_time=?,parent_prod_no=? where id=?",
	obj.getProdNo(),obj.getProdName(),obj.getProdStatus(),obj.getCreateTime(),
	obj.getParentProdNo(),id);
}

public Product getProduct(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	Product obj = (Product)session.getObject("select * from tproduct where id=?",Product.class,id);
	return obj;
}

public List<Product> getAllProduct() throws SQLException{
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select tp.* from tproduct tp");
	return session.getObjectList(sb.toString(), Product.class);
}
public Product getProductForPidAndBillTypeAndBuyType(String pId,String billType,String isInner,String buyType) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select tp.prod_no,tp.prod_name from tproduct tp,");
	sb.append("tprod_limit_type tpl where tp.prod_no = tpl.prod_no and tp.parent_prod_no = ?");
	sb.append(" and tp.prod_status = '1' and tpl.bill_type = ? and tpl.is_inner = ? and tpl.buy_type = ?");
	return session.getObject(sb.toString(),Product.class, pId,billType,isInner,buyType);
}
public List<Product> getProductForPidAndBillType(String pId,String billType,String isInner) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select tp.prod_no,tp.prod_name from tproduct tp,");
	sb.append("tprod_limit_type tpl where tp.prod_no = tpl.prod_no and tp.parent_prod_no = ?");
	sb.append(" and tp.prod_status = '1' and tpl.bill_type = ? and tpl.is_inner = ?");
	return session.getObjectList(sb.toString(),Product.class, pId,billType,isInner);
}
public List<Product> getProductForPidAndBillType(String pId,String billType) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select tp.prod_no,tp.prod_name from tproduct tp,");
	sb.append("tprod_limit_type tpl where tp.prod_no = tpl.prod_no and tp.parent_prod_no = ?");
	sb.append(" and tp.prod_status = '1' and tpl.bill_type = ?");
	return session.getObjectList(sb.toString(),Product.class, pId,billType);
}

public List<Product> getProductForPid(String pId) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select tp.prod_no,tp.prod_name from tproduct tp,");
	sb.append("tprod_limit_type tpl where tp.prod_no = tpl.prod_no and tp.parent_prod_no = ?");
	sb.append(" and tp.prod_status = '1'");
	return session.getObjectList(sb.toString(),Product.class, pId);
}

public List<Product> getProductForPidAndIsInner(String pId,String isInner) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select tp.prod_no,tp.prod_name from tproduct tp,");
	sb.append("tprod_limit_type tpl where tp.prod_no = tpl.prod_no and tp.parent_prod_no = ?");
	sb.append(" and tp.prod_status = '1' and tpl.is_inner=?");
	return session.getObjectList(sb.toString(),Product.class, pId,isInner);
}

public Product getProductByProdNo(String prodNo) throws SQLException {
	IDB session = DBFactory.getDB();
	Product obj = (Product)session.getObject("select * from tproduct where prod_no = ?",Product.class,prodNo);
	return obj;
}

}
