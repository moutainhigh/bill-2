/********************************************
* 文件名称: TradeBankDao.java
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
import com.herongtech.console.domain.bean.TradeBank;
public class TradeBankDao{

public int addTradeBank(TradeBank obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into ttradebank(id,child_bankno,child_bankname,parent_bankname,parent_bankno,cust_name,create_date,create_time,org_code,partner_type,cust_no)values(?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getChildBankno(),obj.getChildBankname(),obj.getParentBankname(),obj.getParentBankno(),
	obj.getCustName(),obj.getCreateDate(),obj.getCreateTime(),obj.getOrgCode(),obj.getPartnerType(),obj.getCustNo());
}

public int deleteTradeBank(TradeBank obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from ttradebank where child_bankno=?",obj.getChildBankno());
}

public int deleteTradeBank(String childBankno) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from ttradebank where child_bankno=?",childBankno);
}

public int modifyTradeBank(TradeBank obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update ttradebank set id=?,child_bankname=?,parent_bankname=?,parent_bankno=?,cust_name=?,create_date=?,create_time=?,cust_no=?,org_code=?,partner_type=? where child_bankno=?",
	obj.getId(),obj.getChildBankname(),obj.getParentBankname(),obj.getParentBankno(),
	obj.getCustName(),obj.getCreateDate(),obj.getCreateTime(),obj.getCustNo(),obj.getOrgCode(),obj.getPartnerType(),
	obj.getChildBankno());
}

public int modifyTradeBank(TradeBank obj,String childBankno) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update ttradebank set id=?,child_bankname=?,parent_bankname=?,parent_bankno=?,cust_name=?,create_date=?,create_time=?,cust_no=?,org_code=?,partner_type=? where child_bankno=?",
	obj.getId(),obj.getChildBankname(),obj.getParentBankname(),obj.getParentBankno(),
	obj.getCustName(),obj.getCreateDate(),obj.getCreateTime(),obj.getCustNo(),obj.getOrgCode(),obj.getPartnerType(),
	childBankno);
}

public TradeBank getTradeBank(String childBankno) throws SQLException {
	IDB session = DBFactory.getDB();
	TradeBank obj = (TradeBank)session.getObject("select * from ttradebank where child_bankno=?",TradeBank.class,childBankno);
	return obj;
}

}
