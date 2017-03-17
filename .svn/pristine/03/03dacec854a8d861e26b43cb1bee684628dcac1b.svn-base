/********************************************
* 文件名称: EcdsCommonDataDao.java
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
import com.herongtech.rc.domain.bean.EcdsCommonData;

import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;

public class EcdsCommonDataDao{

public int addEcdsCommonData(EcdsCommonData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tECDS_COMMON_DATA(commonality_data_code,commonality_data_name,commonality_data_value,commonality_data_updata,commonality_data_type,update_style,inure_date,postscipt,update_date)values(?,?,?,?,?,?,?,?,?)",
	obj.getCommonalityDataCode(),obj.getCommonalityDataName(),obj.getCommonalityDataValue(),obj.getCommonalityDataUpdata(),obj.getCommonalityDataType(),
	obj.getUpdateStyle(),obj.getInureDate(),obj.getPostscipt(),obj.getUpdateDate());
}
/**清空表中数据
 * @throws SQLException */
public void truncateEcdsCommonData() throws SQLException{
	IDB session = DBFactory.getNewDB(); // 获取数据库连接
    String dialectName=session.getDialectName();
    if("DB2".equalsIgnoreCase(dialectName)){
    	session.execute("ALTER TABLE tECDS_COMMON_DATA ACTIVATE NOT LOGGED INITIALLY WITH EMPTY TABLE;");
    }else if("ORACLE".equalsIgnoreCase(dialectName)){
    	session.execute("truncate table tECDS_COMMON_DATA");
    }
	
}

public int deleteEcdsCommonData(EcdsCommonData obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tECDS_COMMON_DATA where commonality_data_code=?",obj.getCommonalityDataCode());
}

public int deleteEcdsCommonData(String commonalityDataCode) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tECDS_COMMON_DATA where commonality_data_code=?",commonalityDataCode);
}

public int modifyEcdsCommonData(EcdsCommonData obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tECDS_COMMON_DATA set commonality_data_name=?,commonality_data_value=?,commonality_data_updata=?,commonality_data_type=?,update_style=?,inure_date=?,postscipt=?,update_date=? where commonality_data_code=?",
	obj.getCommonalityDataName(),obj.getCommonalityDataValue(),obj.getCommonalityDataUpdata(),obj.getCommonalityDataType(),
	obj.getUpdateStyle(),obj.getInureDate(),obj.getPostscipt(),obj.getUpdateDate(),
	obj.getCommonalityDataCode());
}

public int modifyEcdsCommonData(EcdsCommonData obj,String commonalityDataCode) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tECDS_COMMON_DATA set commonality_data_name=?,commonality_data_value=?,commonality_data_updata=?,commonality_data_type=?,update_style=?,inure_date=?,postscipt=?,update_date=? where commonality_data_code=?",
	obj.getCommonalityDataName(),obj.getCommonalityDataValue(),obj.getCommonalityDataUpdata(),obj.getCommonalityDataType(),
	obj.getUpdateStyle(),obj.getInureDate(),obj.getPostscipt(),obj.getUpdateDate(),
	commonalityDataCode);
}

public EcdsCommonData getEcdsCommonData(String commonalityDataCode) throws SQLException {
	IDB session = DBFactory.getDB();
	EcdsCommonData obj = (EcdsCommonData)session.getObject("select * from tECDS_COMMON_DATA where commonality_data_code=?",EcdsCommonData.class,commonalityDataCode);
	return obj;
}

}
