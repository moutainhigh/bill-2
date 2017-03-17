/********************************************
* 文件名称: RgctEcdsStatusDao.java
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
package com.herongtech.rc.domain.dao;

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctEcdsStatus;

import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;

public class RgctEcdsStatusDao{

public int addRgctEcdsStatus(RgctEcdsStatus obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tRGCT_ECDS_STATUS(pname,pvalue,pname_cn,pvalue_cn)values(?,?,?,?)",
	obj.getPname(),obj.getPvalue(),obj.getPnameCn(),obj.getPvalueCn());
}

public int deleteRgctEcdsStatus(RgctEcdsStatus obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tRGCT_ECDS_STATUS where pname=?",obj.getPname());
}

public int deleteRgctEcdsStatus(String pname) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tRGCT_ECDS_STATUS where pname=?",pname);
}

public int modifyRgctEcdsStatus(RgctEcdsStatus obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tRGCT_ECDS_STATUS set pvalue=?,pname_cn=?,pvalue_cn=? where pname=?",
	obj.getPvalue(),obj.getPnameCn(),obj.getPvalueCn(),obj.getPname());
}

public int modifyRgctEcdsStatus(RgctEcdsStatus obj,String pname) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tRGCT_ECDS_STATUS set pvalue=?,pname_cn=?,pvalue_cn=? where pname=?",
	obj.getPvalue(),obj.getPnameCn(),obj.getPvalueCn(),pname);
}

public RgctEcdsStatus getRgctEcdsStatus(String pname) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctEcdsStatus obj = (RgctEcdsStatus)session.getObject("select * from tRGCT_ECDS_STATUS where pname=?",RgctEcdsStatus.class,pname);
	return obj;
}

}
