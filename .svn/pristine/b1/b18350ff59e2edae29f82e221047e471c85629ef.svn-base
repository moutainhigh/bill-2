/********************************************
* 文件名称: HolidayDao.java
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.domain.bean.Holiday;
import com.herongtech.console.domain.bean.Param;
public class HolidayDao{

public int addHoliday(Holiday obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tHOLIDAY(id,dt,week,holiday_name,is_holiday)values(?,?,?,?,?)",
	obj.getId(),obj.getDt(),obj.getWeek(),obj.getHolidayName(),obj.getIsHoliday());
}

public int deleteHoliday(String time) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tHOLIDAY where dt=?",time);
}

public int deleteHoliday(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("update tHOLIDAY set is_holiday='0' where id=?",id);
}

public int modifyHoliday(Holiday obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tHOLIDAY set id=?,week=?,holiday_name=?,is_holiday=? where dt=?",
	obj.getId(),obj.getWeek(),obj.getHolidayName(),obj.getIsHoliday(),
	obj.getDt());
}

public int modifyHoliday(Holiday obj,Long id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tHOLIDAY set dt=?,week=?,holiday_name=?,is_holiday=? where id=?",
	obj.getDt(),obj.getWeek(),obj.getHolidayName(),obj.getIsHoliday(),
	id);
}

/**根据日期得到当天日期对象*/
public Holiday getHoliday(String day) throws SQLException {
	IDB session = DBFactory.getDB();
	Holiday obj = (Holiday)session.getObject("select * from tHOLIDAY where dt=?",Holiday.class,day);
	return obj;
}

//李江涛   根据时间的年查看数据库中是否有这年假期的存储
public List<Holiday> searchDataList(String date) throws SQLException{
	IDB session = DBFactory.getDB();
	List<Holiday> holidays = new ArrayList<Holiday>();
	

	HsSqlString dbSql = new HsSqlString("tholiday");
	if (!StringUtils.isEmpty(date)){
		dbSql.setWhere("dt like '%" + date + "%'");
	}
	
	holidays = session.getObjectListByList(dbSql.getSqlString(), Holiday.class, dbSql.getParamList());
	return holidays;
}

/**获得数据库中前台需要显示的所有假期*/
public List<Holiday> searchHolidayDataList(String date) throws SQLException{
	IDB session = DBFactory.getDB();
	List<Holiday> holidays = new ArrayList<Holiday>();
	

	HsSqlString dbSql = new HsSqlString("tholiday");
	if (!StringUtils.isEmpty("1")){
		dbSql.setWhere("is_holiday =1");
	}
	
	holidays = session.getObjectListByList(dbSql.getSqlString(), Holiday.class, dbSql.getParamList());
	return holidays;
}

/**根据时间取出对应假期数据*/
public Holiday getNowdayHoliday(String str) throws SQLException{
	IDB session = DBFactory.getDB();
	Holiday obj = (Holiday)session.getObject("select * from tHOLIDAY where is_holiday = '1' and dt=?",Holiday.class,str);
	return obj;
}

/**取出数据库中所有记录（添加一年功能需要）*/
public List<Holiday> getAllData() throws SQLException{
	IDB session = DBFactory.getDB();
	HsSqlString dbSql = new HsSqlString("tholiday");
	List<Holiday> holidays = session.getObjectListByList(dbSql.getSqlString(), Holiday.class, dbSql.getParamList());
	return holidays;
}

/**
 * 取出date后count天的日期
 */
public List getSomeHolidays(Date date,int count)throws SQLException{
    IDB session = DBFactory.getDB();
    return null;
    
}



}
