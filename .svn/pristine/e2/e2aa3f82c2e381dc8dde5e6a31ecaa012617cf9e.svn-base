/********************************************
* 文件名称: EcdsPsAssDataDao.java
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
import com.herongtech.rc.domain.bean.EcdsPsAssData;
public class EcdsPsAssDataDao{

public int addEcdsPsAssData(EcdsPsAssData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tecds_ps_ass_data(by_continue_bank_no,by_continue_org_code,by_continue_role,msg_id,continue_bank_no,continue_org_code,continue_role,inure_date,by_continue_launch,continue_launch,launch_time,update_state)values(?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getByContinueBankNo(),obj.getByContinueOrgCode(),obj.getByContinueRole(),obj.getMsgId(),obj.getContinueBankNo(),
	obj.getContinueOrgCode(),obj.getContinueRole(),obj.getInureDate(),obj.getByContinueLaunch(),
	obj.getContinueLaunch(),obj.getLaunchTime(),obj.getUpdateState());
}

/**清空表中数据
 * @throws SQLException */
public void truncateEcdsPsAssData() throws SQLException{
	IDB session = DBFactory.getNewDB(); // 获取数据库连接
    String dialectName=session.getDialectName();
    if("DB2".equalsIgnoreCase(dialectName)){
    	session.execute("ALTER TABLE tECDS_PS_ASS_DATA ACTIVATE NOT LOGGED INITIALLY WITH EMPTY TABLE;");
    }else if("ORACLE".equalsIgnoreCase(dialectName)){
    	session.execute("truncate table tECDS_PS_ASS_DATA");
    }
	
}

public int modifyEcdsPsAssData(EcdsPsAssData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tecds_ps_ass_data set by_continue_bank_no=?,by_continue_org_code=?,by_continue_role=?,msg_id=?,continue_bank_no=?,continue_org_code=?,continue_role=?,inure_date=?,by_continue_launch=?,continue_launch=?,launch_time=?,update_state=? ",
	obj.getByContinueBankNo(),obj.getByContinueOrgCode(),obj.getByContinueRole(),obj.getMsgId(),
	obj.getContinueBankNo(),obj.getContinueOrgCode(),obj.getContinueRole(),obj.getInureDate(),
	obj.getByContinueLaunch(),obj.getContinueLaunch(),obj.getLaunchTime(),obj.getUpdateState());
}

public EcdsPsAssData getEcdsPsAssData(EcdsPsAssData ecdspsassdata) throws SQLException {
	IDB session = DBFactory.getDB();
	EcdsPsAssData obj = (EcdsPsAssData)session.getObject("select * from tecds_ps_ass_data where by_continue_bank_no=?",EcdsPsAssData.class,ecdspsassdata.getByContinueBankNo());
	return obj;
}

}
