/********************************************
 * 文件名称: RgctStatusMappingDao.java
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
import java.util.ArrayList;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctStatusMapping;

public class RgctStatusMappingDao {

	public int addRgctStatusMapping(RgctStatusMapping obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session
				.execute(
						"insert into TRGCT_STATUS_MAPPING(id,bbsp_status_code,ecds_status_code,status_name)values(?,?,?,?)",
						obj.getId(), obj.getBbspStatusCode(),
						obj.getEcdsStatusCode(), obj.getStatusName());
	}

	public int deleteRgctStatusMapping(RgctStatusMapping obj)
			throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from TRGCT_STATUS_MAPPING where id=?",
				obj.getId());
	}

	public int deleteRgctStatusMapping(String id) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from TRGCT_STATUS_MAPPING where id=?",
				id);
	}

	public int modifyRgctStatusMapping(RgctStatusMapping obj)
			throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session
				.execute(
						"update TRGCT_STATUS_MAPPING set bbsp_status_code=?,ecds_status_code=?,status_name=? where id=?",
						obj.getBbspStatusCode(), obj.getEcdsStatusCode(),
						obj.getStatusName(), obj.getId());
	}

	public int modifyRgctStatusMapping(RgctStatusMapping obj, String id)
			throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session
				.execute(
						"update TRGCT_STATUS_MAPPING set bbsp_status_code=?,ecds_status_code=?,status_name=? where id=?",
						obj.getBbspStatusCode(), obj.getEcdsStatusCode(),
						obj.getStatusName(), id);
	}

	public RgctStatusMapping getRgctStatusMapping(String id)
			throws SQLException {
		IDB session = DBFactory.getDB();
		RgctStatusMapping obj = (RgctStatusMapping) session.getObject(
				"select * from TRGCT_STATUS_MAPPING where id=?",
				RgctStatusMapping.class, id);
		return obj;
	}

	/**获得表TRGCT_STATUS_MAPPING所有数据*/
	public List<RgctStatusMapping> getRgctStatusMappingAllData(){
		IDB session = DBFactory.getDB();
		List<RgctStatusMapping> obj = null;
		try {
			obj = session.getObjectListByList("select * from trgct_status_mapping",RgctStatusMapping.class,new ArrayList<Object>());
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return obj;
	}
}
