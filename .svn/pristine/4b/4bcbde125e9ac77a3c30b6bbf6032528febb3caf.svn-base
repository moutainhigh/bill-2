/********************************************
* 文件名称: TradeBankRootDao.java
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
import com.herongtech.console.domain.bean.TradeBankRoot;
public class TradeBankRootDao{

public int addTradeBankRoot(TradeBankRoot obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into ttradebank_root(id,left_three_bank_no,bank_name,bank_no)values(?,?,?,?)",
	obj.getId(),obj.getLeftThreeBankNo(),obj.getBankName(),obj.getBankNo());
}

public int deleteTradeBankRoot(TradeBankRoot obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from ttradebank_root where bank_no=?",obj.getBankNo());
}

public int deleteTradeBankRoot(String bankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from ttradebank_root where bank_no=?",bankNo);
}

public int modifyTradeBankRoot(TradeBankRoot obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update ttradebank_root set id=?,left_three_bank_no=?,bank_name=? where bank_no=?",
	obj.getId(),obj.getLeftThreeBankNo(),obj.getBankName(),obj.getBankNo());
}

public int modifyTradeBankRoot(TradeBankRoot obj,String bankNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update ttradebank_root set id=?,left_three_bank_no=?,bank_name=? where bank_no=?",
	obj.getId(),obj.getLeftThreeBankNo(),obj.getBankName(),bankNo);
}

public TradeBankRoot getTradeBankRoot(String bankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	TradeBankRoot obj = (TradeBankRoot)session.getObject("select * from ttradebank_root where bank_no=?",TradeBankRoot.class,bankNo);
	return obj;
}

}
