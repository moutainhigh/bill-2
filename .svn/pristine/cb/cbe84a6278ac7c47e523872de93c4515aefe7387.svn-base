/********************************************
* 文件名称: BlackListDao.java
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
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.BlackList;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class BlackListDao{

public int addBlackList(BlackList obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tblack_list(id,enterprise_name,union_bankno,description,create_dt,create_time,oper_branch_no,oper_no,oper_name)values(?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getEnterpriseName(),obj.getUnionBankno(),obj.getDescription(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getOperBranchNo(),obj.getOperNo(),obj.getOperName());
}

public int deleteBlackList(BlackList obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tblack_list where union_bankno=?",obj.getUnionBankno());
}

public int deleteBlackList(String unionBankno) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tblack_list where union_bankno=?",unionBankno);
}

public int modifyBlackList(BlackList obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tblack_list set id=?,enterprise_name=?,description=?,create_dt=?,create_time=?,oper_branch_no=?,oper_no=?,oper_name=? where union_bankno=?",
	obj.getId(),obj.getEnterpriseName(),obj.getDescription(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getOperBranchNo(),obj.getOperNo(),obj.getOperName(),
	obj.getUnionBankno());
}

public int modifyBlackList(BlackList obj,String unionBankno) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tblack_list set id=?,enterprise_name=?,description=?,create_dt=?,create_time=?,oper_branch_no=?,oper_no=?,oper_name=? where union_bankno=?",
	obj.getId(),obj.getEnterpriseName(),obj.getDescription(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getOperBranchNo(),obj.getOperNo(),obj.getOperName(),
	unionBankno);
}

public BlackList getBlackList(String unionBankno) throws SQLException {
	IDB session = DBFactory.getDB();
	BlackList obj = (BlackList)session.getObject("select * from tblack_list where union_bankno=?",BlackList.class,unionBankno);
	return obj;
}
	/**
	 * 黑名单检查
	 * @param bankNos	行号	多个行号之间使用逗号分隔
	 * @return	List<BlackList>	黑名单集合
	 * @throws SQLException
	 */
	public List<BlackList> getBlackBillByBills(String bankNos) throws SQLException {
		IDB session = DBFactory.getDB();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM TBLACK_LIST WHERE  UNION_BANKNO IN (")
			.append(bankNos)
			.append(")");
		List<BlackList> objs = session.getObjectList(sb.toString(),BlackList.class);
		return objs;
	}


}
