/********************************************
* 文件名称: ParamDao.java
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
import com.herongtech.console.domain.bean.Param;
public class ParamDao{

public int addParam(Param obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tsysparam(ta_code,param_id,param_name,param_value,value_name,belong_type,modi_flag,reserve1,bank_no)values(?,?,?,?,?,?,?,?,?)",
	obj.getTaCode(),obj.getParamId(),obj.getParamName(),obj.getParamValue(),obj.getValueName(),
	obj.getBelongType(),obj.getModiFlag(),obj.getReserve1(),obj.getBankNo());
}
/**获得param表中的所有数据*/
public List<Param> getParamList()throws SQLException {
	IDB session = DBFactory.getDB();
	return session.getObjectList("select * from tsysparam", Param.class);
}
/**根据参数表示号和参数值更改tsysParam表*/
public int updateParam(String paramid,String paramvalue) throws SQLException{
	IDB session = DBFactory.getDB();
	int result = session.execute("update tsysparam set param_value=? where param_id=?",paramvalue,paramid);
	return result;
}

public int deleteParam(Param obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsysparam where ta_code=? and param_id=? and bank_no=?",obj.getTaCode(),obj.getParamId(),obj.getBankNo());
}

public int deleteParam(String taCode,String paramId,String bankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsysparam where ta_code=? and param_id=? and bank_no=?",taCode,paramId,bankNo);
}

public int modifyParam(Param obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsysparam set param_name=?,param_value=?,value_name=?,belong_type=?,modi_flag=?,reserve1=? where ta_code=? and param_id=? and bank_no=?",
	obj.getParamName(),obj.getParamValue(),obj.getValueName(),obj.getBelongType(),
	obj.getModiFlag(),obj.getReserve1(),obj.getTaCode(),obj.getParamId(),
	obj.getBankNo());
}

public int modifyParam(Param obj,String taCode,String paramId,String bankNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsysparam set param_name=?,param_value=?,value_name=?,belong_type=?,modi_flag=?,reserve1=? where ta_code=? and param_id=? and bank_no=?",
	obj.getParamName(),obj.getParamValue(),obj.getValueName(),obj.getBelongType(),
	obj.getModiFlag(),obj.getReserve1(),taCode,paramId,
	bankNo);
}

public Param getParam(String taCode,String paramId,String bankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	Param obj = (Param)session.getObject("select * from tsysparam where ta_code=? and param_id=? and bank_no=?",Param.class,taCode,paramId,bankNo);
	return obj;
}

}
