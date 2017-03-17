/********************************************
* 文件名称: BankInfoDao.java
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

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.BankInfo;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class BankInfoDao{

public int addBankInfo(BankInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	/*return session.execute("insert into tBANK_INFO(id,left_three_bank_no,bank_name,bank_no)values(?,?,?,?)",
	obj.getId(),obj.getLeftThreeBankNo(),obj.getBankName(),obj.getBankNo());*/
	return session.execute("insert into tBANK_INFO(left_three_bank_no,bank_name,bank_no)values(?,?,?)",
			obj.getLeftThreeBankNo(),obj.getBankName(),obj.getBankNo());
}

public int deleteBankInfo(BankInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
//	return session.execute("delete from tBANK_INFO where id=?",obj.getId());
	return session.execute("delete from tBANK_INFO where bank_no=?",obj.getBankNo());
}

/*public int deleteBankInfo(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tBANK_INFO where id=?",id);
}*/
public int deleteBankInfo(String bankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tBANK_INFO where bank_no=?",bankNo);
}

public int modifyBankInfo(BankInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	/*return session.execute("update tBANK_INFO set left_three_bank_no=?,bank_name=?,bank_no=? where id=?",
	obj.getLeftThreeBankNo(),obj.getBankName(),obj.getBankNo(),obj.getId());*/
	return session.execute("update tBANK_INFO set left_three_bank_no=?,bank_name=? where bank_no=?",
			obj.getLeftThreeBankNo(),obj.getBankName(),obj.getBankNo());
}

/*public int modifyBankInfo(BankInfo obj,Long id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tBANK_INFO set left_three_bank_no=?,bank_name=?,bank_no=? where id=?",
	obj.getLeftThreeBankNo(),obj.getBankName(),obj.getBankNo(),id);
}*/
public int modifyBankInfo(BankInfo obj,String bankNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tBANK_INFO set left_three_bank_no=?,bank_name=? where bank_no=?",
	obj.getLeftThreeBankNo(),obj.getBankName(),bankNo);
}

/*public BankInfo getBankInfo(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	BankInfo obj = (BankInfo)session.getObject("select * from tBANK_INFO where id=?",BankInfo.class,id);
	return obj;
}*/
public BankInfo getBankInfo(String bankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	BankInfo obj = (BankInfo)session.getObject("select * from tBANK_INFO where bank_no=?",BankInfo.class,bankNo);
	return obj;
}

}
