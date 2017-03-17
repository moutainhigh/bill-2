/********************************************
* 文件名称: EcdsApDataDao.java
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
import com.herongtech.rc.domain.bean.EcdsApData;

import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
public class EcdsApDataDao{

public int addEcdsApData(EcdsApData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tECDS_AP_DATA(meet_code,meet_name,nonce_ccpc,meet_state,inure_date,logout_date,update_time,lately_update_work,meet_enter_state,enter_identify,this_month_set_first_time,this_month_set_second,first_limited,second_limited)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getMeetCode(),obj.getMeetName(),obj.getNonceCcpc(),obj.getMeetState(),obj.getInureDate(),
	obj.getLogoutDate(),obj.getUpdateTime(),obj.getLatelyUpdateWork(),obj.getMeetEnterState(),
	obj.getEnterIdentify(),obj.getThisMonthSetFirstTime(),obj.getThisMonthSetSecond(),obj.getFirstLimited(),
	obj.getSecondLimited());
}
/**清空表中数据
 * @throws SQLException */
public void truncateEcdsApData() throws SQLException{
	IDB session = DBFactory.getNewDB(); // 获取数据库连接
    String dialectName=session.getDialectName();
    if("DB2".equalsIgnoreCase(dialectName)){
    	session.execute("ALTER TABLE tECDS_AP_DATA ACTIVATE NOT LOGGED INITIALLY WITH EMPTY TABLE;");
    }else if("ORACLE".equalsIgnoreCase(dialectName)){
    	session.execute("truncate table tECDS_AP_DATA");
    }
	
}

public int deleteEcdsApData(EcdsApData obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tECDS_AP_DATA where meet_code=?",obj.getMeetCode());
}

public int deleteEcdsApData(String meetCode) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tECDS_AP_DATA where meet_code=?",meetCode);
}

public int modifyEcdsApData(EcdsApData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tECDS_AP_DATA set meet_name=?,nonce_ccpc=?,meet_state=?,inure_date=?,logout_date=?,update_time=?,lately_update_work=?,meet_enter_state=?,enter_identify=?,this_month_set_first_time=?,this_month_set_second=?,first_limited=?,second_limited=? where meet_code=?",
	obj.getMeetName(),obj.getNonceCcpc(),obj.getMeetState(),obj.getInureDate(),
	obj.getLogoutDate(),obj.getUpdateTime(),obj.getLatelyUpdateWork(),obj.getMeetEnterState(),
	obj.getEnterIdentify(),obj.getThisMonthSetFirstTime(),obj.getThisMonthSetSecond(),obj.getFirstLimited(),
	obj.getSecondLimited(),obj.getMeetCode());
}

public int modifyEcdsApData(EcdsApData obj,String meetCode) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tECDS_AP_DATA set meet_name=?,nonce_ccpc=?,meet_state=?,inure_date=?,logout_date=?,update_time=?,lately_update_work=?,meet_enter_state=?,enter_identify=?,this_month_set_first_time=?,this_month_set_second=?,first_limited=?,second_limited=? where meet_code=?",
	obj.getMeetName(),obj.getNonceCcpc(),obj.getMeetState(),obj.getInureDate(),
	obj.getLogoutDate(),obj.getUpdateTime(),obj.getLatelyUpdateWork(),obj.getMeetEnterState(),
	obj.getEnterIdentify(),obj.getThisMonthSetFirstTime(),obj.getThisMonthSetSecond(),obj.getFirstLimited(),
	obj.getSecondLimited(),meetCode);
}

public EcdsApData getEcdsApData(String meetCode) throws SQLException {
	IDB session = DBFactory.getDB();
	EcdsApData obj = (EcdsApData)session.getObject("select * from tECDS_AP_DATA where meet_code=?",EcdsApData.class,meetCode);
	return obj;
}

}
