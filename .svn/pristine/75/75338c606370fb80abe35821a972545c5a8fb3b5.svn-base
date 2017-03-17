/********************************************
* 文件名称: WorkingAdsDao.java
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
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.WorkingAds;
public class WorkingAdsDao{

public int addWorkingAds(WorkingAds obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into TWORKING_ADS(working_ads_no,working_ads_name,status,del_flag)values(?,?,?,'0')",
	obj.getWorkingAdsNo(),obj.getWorkingAdsName(),obj.getStatus());
}

public int deleteWorkingAds(WorkingAds obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from TWORKING_ADS where working_ads_no=?",obj.getWorkingAdsNo());
}

public int deleteWorkingAds(String workingAdsNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from TWORKING_ADS where working_ads_no=?",workingAdsNo);
}

public int modifyWorkingAds(WorkingAds obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update TWORKING_ADS set working_ads_name=?,status=?,del_flag='0' where working_ads_no=?",
	obj.getWorkingAdsName(),obj.getStatus(),obj.getWorkingAdsNo());
}

public int modifyWorkingAds(WorkingAds obj,String workingAdsNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update TWORKING_ADS set working_ads_name=?,status=?,del_flag=? where working_ads_no=?",
	obj.getWorkingAdsName(),obj.getStatus(),obj.getDelFlag(),workingAdsNo);
}

public WorkingAds getWorkingAds(String workingAdsNo) throws SQLException {
	IDB session = DBFactory.getDB();
	WorkingAds obj = (WorkingAds)session.getObject("select * from TWORKING_ADS where working_ads_no=? and del_flag='0'",WorkingAds.class,workingAdsNo);
	return obj;
}

}
