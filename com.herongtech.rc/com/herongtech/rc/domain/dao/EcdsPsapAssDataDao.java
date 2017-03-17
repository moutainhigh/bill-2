/********************************************
* 文件名称: EcdsPsapAssDataDao.java
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

import java.sql.SQLException;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.EcdsPsapAssData;
public class EcdsPsapAssDataDao{

public int addEcdsPsapAssData(EcdsPsapAssData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tecds_psap_ass_data(row_number,meet_code,effective_date,effective_time,update_date,update_time,process_state,remark)values(?,?,?,?,?,?,?,?)",
	obj.getBankNo(),obj.getMeetCode(),obj.getEffectiveDate(),obj.getEffectiveTime(),obj.getUpdateDate(),
	obj.getUpdateTime(),obj.getProcessState(),obj.getRemark());
}
/**清空表中数据
 * @throws SQLException */
public void truncateEcdsPsapAssData() throws SQLException{
	IDB session = DBFactory.getNewDB(); // 获取数据库连接
    String dialectName=session.getDialectName();
    if("DB2".equalsIgnoreCase(dialectName)){
    	session.execute("ALTER TABLE tECDS_PSAP_ASS_DATA ACTIVATE NOT LOGGED INITIALLY WITH EMPTY TABLE;");
    }else if("ORACLE".equalsIgnoreCase(dialectName)){
    	session.execute("truncate table tECDS_PSAP_ASS_DATA");
    }
	
}

public int deleteEcdsPsapAssData(EcdsPsapAssData obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tecds_psap_ass_data where bank_no=?",obj.getBankNo());
}

public int deleteEcdsPsapAssData(String bankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tecds_psap_ass_data where bank_no=?",bankNo);
}

public int modifyEcdsPsapAssData(EcdsPsapAssData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tecds_psap_ass_data set meet_code=?,effective_date=?,effective_time=?,update_date=?,update_time=?,process_state=?,remark=? where bank_no=?",
	obj.getMeetCode(),obj.getEffectiveDate(),obj.getEffectiveTime(),obj.getUpdateDate(),
	obj.getUpdateTime(),obj.getProcessState(),obj.getRemark(),obj.getBankNo());
}

public int modifyEcdsPsapAssData(EcdsPsapAssData obj,String bankNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tecds_psap_ass_data set meet_code=?,effective_date=?,effective_time=?,update_date=?,update_time=?,process_state=?,remark=? where bank_no=?",
	obj.getMeetCode(),obj.getEffectiveDate(),obj.getEffectiveTime(),obj.getUpdateDate(),
	obj.getUpdateTime(),obj.getProcessState(),obj.getRemark(),bankNo);
}

public EcdsPsapAssData getEcdsPsapAssData(String bankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	EcdsPsapAssData obj = (EcdsPsapAssData)session.getObject("select * from tecds_psap_ass_data where bank_no=?",EcdsPsapAssData.class,bankNo);
	return obj;
}

}
