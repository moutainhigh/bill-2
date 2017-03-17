/********************************************
* 文件名称: ProfessionInvestDirectionDao.java
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
import com.herongtech.console.domain.common.bean.ProfessionInvestDirection;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class ProfessionInvestDirectionDao{

public int addProfessionInvestDirection(ProfessionInvestDirection obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tprofession_invest_direction(id,profession_name)values(?,?)",
	obj.getId(),obj.getProfessionName());
}

public int deleteProfessionInvestDirection(ProfessionInvestDirection obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tprofession_invest_direction where id=?",obj.getId());
}

public int deleteProfessionInvestDirection(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tprofession_invest_direction where id=?",id);
}

public int modifyProfessionInvestDirection(ProfessionInvestDirection obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tprofession_invest_direction set profession_name=? where id=?",
	obj.getProfessionName(),obj.getId());
}

public int modifyProfessionInvestDirection(ProfessionInvestDirection obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tprofession_invest_direction set profession_name=? where id=?",
	obj.getProfessionName(),id);
}

public ProfessionInvestDirection getProfessionInvestDirection(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	ProfessionInvestDirection obj = (ProfessionInvestDirection)session.getObject("select * from tprofession_invest_direction where id=?",ProfessionInvestDirection.class,id);
	return obj;
}
public List<ProfessionInvestDirection> getAllProfessionInvestDirection() throws SQLException {
	IDB session = DBFactory.getDB();
    return session.getObjectList("select * from tprofession_invest_direction",ProfessionInvestDirection.class);
} 

}
