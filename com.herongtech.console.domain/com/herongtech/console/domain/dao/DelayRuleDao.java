/********************************************
* 文件名称: DelayRuleDao.java
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
import com.herongtech.console.domain.bean.DelayRule;
public class DelayRuleDao{

public int addDelayRule(DelayRule obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tdelay_rule(id,prod_no,bill_class,bill_type,delay_type,delay_days,oper_type,rule_desc,branch_no)values(?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getProdNo(),obj.getBillClass(),obj.getBillType(),obj.getDelayType(),
	obj.getDelayDays(),obj.getOperType(),obj.getRuleDesc(),obj.getBranchNo());
}

public int deleteDelayRule(DelayRule obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdelay_rule where id=?",obj.getId());
}

public int deleteDelayRule(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdelay_rule where id=?",id);
}

public int modifyDelayRule(DelayRule obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdelay_rule set prod_no=?,bill_class=?,bill_type=?,delay_type=?,delay_days=?,oper_type=?,rule_desc=?,branch_no=? where id=?",
	obj.getProdNo(),obj.getBillClass(),obj.getBillType(),obj.getDelayType(),
	obj.getDelayDays(),obj.getOperType(),obj.getRuleDesc(),obj.getBranchNo(),
	obj.getId());
}

public int modifyDelayRule(DelayRule obj,Long id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdelay_rule set prod_no=?,bill_class=?,bill_type=?,delay_type=?,delay_days=?,oper_type=?,rule_desc=?,branch_no=? where id=?",
	obj.getProdNo(),obj.getBillClass(),obj.getBillType(),obj.getDelayType(),
	obj.getDelayDays(),obj.getOperType(),obj.getRuleDesc(),obj.getBranchNo(),
	id);
}

public DelayRule getDelayRule(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	DelayRule obj = (DelayRule)session.getObject("select * from tdelay_rule where id=?",DelayRule.class,id);
	return obj;
}

public DelayRule getDelayRule(String productNo, String billClass, String billType) throws SQLException{
    
    IDB session = DBFactory.getDB();
    DelayRule obj = (DelayRule)session.getObject("select * from tdelay_rule where prod_No=? and bill_Class=? and bill_Type=? ",DelayRule.class,productNo,billClass,billType);
    return obj;
}

/**取出所有delayrule的数据
 * @throws SQLException */
public List<DelayRule> getAllDelayRuleList() throws SQLException{
	IDB session = DBFactory.getDB();
	List<DelayRule> delayRulelist = session.getObjectList("select * from tdelay_rule", DelayRule.class);
	return delayRulelist;
}

}
