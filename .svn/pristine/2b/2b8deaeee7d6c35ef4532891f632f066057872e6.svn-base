/********************************************
* 文件名称: BbspProductDao.java
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

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.BbspProduct;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class BbspProductDao{

public int addBbspProduct(BbspProduct obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tBBSP_PRODUCT(id,prod_name,prod_no,prod_type,prod_status,create_date,create_user_name,create_user_no,del_flag,del_user_name,del_user_no,del_date,prod_code,charge_type,summary,branchs_power)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getProdName(),obj.getProdNo(),obj.getProdType(),obj.getProdStatus(),
	obj.getCreateDate(),obj.getCreateUserName(),obj.getCreateUserNo(),obj.getDelFlag(),
	obj.getDelUserName(),obj.getDelUserNo(),obj.getDelDate(),obj.getProdCode(),
	obj.getChargeType(),obj.getSummary(),obj.getBranchsPower());
}

public int deleteBbspProduct(BbspProduct obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tBBSP_PRODUCT where prod_no=?",obj.getProdNo());
}

public int deleteBbspProduct(String prodNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tBBSP_PRODUCT where prod_no=?",prodNo);
}

public int modifyBbspProduct(BbspProduct obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tBBSP_PRODUCT set id=?,prod_name=?,prod_type=?,prod_status=?,create_date=?,create_user_name=?,create_user_no=?,del_flag=?,del_user_name=?,del_user_no=?,del_date=?,prod_code=?,charge_type=?,summary=?,branchs_power=? where prod_no=?",
	obj.getId(),obj.getProdName(),obj.getProdType(),obj.getProdStatus(),
	obj.getCreateDate(),obj.getCreateUserName(),obj.getCreateUserNo(),obj.getDelFlag(),
	obj.getDelUserName(),obj.getDelUserNo(),obj.getDelDate(),obj.getProdCode(),
	obj.getChargeType(),obj.getSummary(),obj.getBranchsPower(),obj.getProdNo());
}

public int modifyBbspProduct(BbspProduct obj,String prodNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tBBSP_PRODUCT set id=?,prod_name=?,prod_type=?,prod_status=?,create_date=?,create_user_name=?,create_user_no=?,del_flag=?,del_user_name=?,del_user_no=?,del_date=?,prod_code=?,charge_type=?,summary=?,branchs_power=? where prod_no=?",
	obj.getId(),obj.getProdName(),obj.getProdType(),obj.getProdStatus(),
	obj.getCreateDate(),obj.getCreateUserName(),obj.getCreateUserNo(),obj.getDelFlag(),
	obj.getDelUserName(),obj.getDelUserNo(),obj.getDelDate(),obj.getProdCode(),
	obj.getChargeType(),obj.getSummary(),obj.getBranchsPower(),prodNo);
}

public BbspProduct getBbspProduct(String prodNo) throws SQLException {
	IDB session = DBFactory.getDB();
	BbspProduct obj = (BbspProduct)session.getObject("select * from tBBSP_PRODUCT where pord_no=?",BbspProduct.class,prodNo);
	return obj;
}

}
