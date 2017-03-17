/********************************************
* 文件名称: EcdsBankAssDataDao.java
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
import com.herongtech.rc.domain.bean.EcdsBankAssData;
import com.herongtech.rc.domain.bean.EcdsBankData;
public class EcdsBankAssDataDao{

public int addEcdsBankAssData(EcdsBankAssData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tecds_bank_ass_data(id,row_number,actor_type,row_other_code,super_actor,local_node_code,root_super_actor,cate_people_code,city_code,actor_full_call,actor_for_short,address,post_edit,phone,email,inure_date,update_dt,update_time,log_update_expect,state,oper_status,remark)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getRowNumber(),obj.getActorType(),obj.getRowOtherCode(),obj.getSuperActor(),
	obj.getLocalNodeCode(),obj.getRootSuperActor(),obj.getCatePeopleCode(),obj.getCityCode(),
	obj.getActorFullCall(),obj.getActorForShort(),obj.getAddress(),obj.getPostEdit(),
	obj.getPhone(),obj.getEmail(),obj.getInureDate(),obj.getUpdateDt(),
	obj.getUpdateTime(),obj.getLogUpdateExpect(),obj.getState(),obj.getOperStatus(),
	obj.getRemark());
}

/**清空表中数据
 * @throws SQLException */
public void truncateEcdsBankAssData() throws SQLException{
	IDB session = DBFactory.getNewDB(); // 获取数据库连接
    String dialectName=session.getDialectName();
    if("DB2".equalsIgnoreCase(dialectName)){
    	session.execute("ALTER TABLE tECDS_BANK_ASS_DATA ACTIVATE NOT LOGGED INITIALLY WITH EMPTY TABLE;");
    }else if("ORACLE".equalsIgnoreCase(dialectName)){
    	session.execute("truncate table tECDS_BANK_ASS_DATA");
    }
	
}

public int deleteEcdsBankAssData(EcdsBankAssData obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tecds_bank_ass_data where row_number=?",obj.getRowNumber());
}

public int deleteEcdsBankAssData(String rowNumber) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tecds_bank_ass_data where row_number=?",rowNumber);
}

public int modifyEcdsBankAssData(EcdsBankAssData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tecds_bank_ass_data set id=?,actor_type=?,row_other_code=?,super_actor=?,local_node_code=?,root_super_actor=?,cate_people_code=?,city_code=?,actor_full_call=?,actor_for_short=?,address=?,post_edit=?,phone=?,email=?,inure_date=?,update_dt=?,update_time=?,log_update_expect=?,state=?,oper_status=?,remark=? where row_number=?",
	obj.getId(),obj.getActorType(),obj.getRowOtherCode(),obj.getSuperActor(),
	obj.getLocalNodeCode(),obj.getRootSuperActor(),obj.getCatePeopleCode(),obj.getCityCode(),
	obj.getActorFullCall(),obj.getActorForShort(),obj.getAddress(),obj.getPostEdit(),
	obj.getPhone(),obj.getEmail(),obj.getInureDate(),obj.getUpdateDt(),
	obj.getUpdateTime(),obj.getLogUpdateExpect(),obj.getState(),obj.getOperStatus(),
	obj.getRemark(),obj.getRowNumber());
}

public int modifyEcdsBankAssData(EcdsBankAssData obj,String rowNumber) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tecds_bank_ass_data set id=?,actor_type=?,row_other_code=?,super_actor=?,local_node_code=?,root_super_actor=?,cate_people_code=?,city_code=?,actor_full_call=?,actor_for_short=?,address=?,post_edit=?,phone=?,email=?,inure_date=?,update_dt=?,update_time=?,log_update_expect=?,state=?,oper_status=?,remark=? where row_number=?",
	obj.getId(),obj.getActorType(),obj.getRowOtherCode(),obj.getSuperActor(),
	obj.getLocalNodeCode(),obj.getRootSuperActor(),obj.getCatePeopleCode(),obj.getCityCode(),
	obj.getActorFullCall(),obj.getActorForShort(),obj.getAddress(),obj.getPostEdit(),
	obj.getPhone(),obj.getEmail(),obj.getInureDate(),obj.getUpdateDt(),
	obj.getUpdateTime(),obj.getLogUpdateExpect(),obj.getState(),obj.getOperStatus(),
	obj.getRemark(),rowNumber);
}

public EcdsBankAssData getEcdsBankAssData(String rowNumber) throws SQLException {
	IDB session = DBFactory.getDB();
	EcdsBankAssData obj = (EcdsBankAssData)session.getObject("select * from tecds_bank_ass_data where row_number=?",EcdsBankAssData.class,rowNumber);
	return obj;
}

}
