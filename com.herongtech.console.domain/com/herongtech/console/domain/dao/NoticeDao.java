/********************************************
* 文件名称: NoticeDao.java
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
import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.Notice;
import com.herongtech.console.domain.common.bean.ProfessionInvestDirection;
public class NoticeDao{

public int addNotice(Notice obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tnotice(notice_no,notice_name,notice,release_time)values(?,?,?,?)",
	obj.getNoticeNo(),obj.getNoticeName(),obj.getNotice(),obj.getReleaseTime());
}

public int deleteNotice(Notice obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tnotice where notice_no=?",obj.getNoticeNo());
}

public int deleteNotice(String noticeNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tnotice where notice_no=?",noticeNo);
}
public int delNotice(String noticeNos) throws SQLException {
	IDB session = DBFactory.getDB();
	String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(noticeNos));
	return session.execute("delete from tnotice where notice_no in(" + sqlInParts+  ")");
}

public int modifyNotice(Notice obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tnotice set notice_name=?,notice=?,release_time=? where notice_no=?",
	obj.getNoticeName(),obj.getNotice(),obj.getReleaseTime(),obj.getNoticeNo());
}

public int modifyNotice(Notice obj,String noticeNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tnotice set notice_name=?,notice=?,release_time=? where notice_no=?",
			obj.getNoticeName(),obj.getNotice(),obj.getReleaseTime(),noticeNo);
}

public Notice getNotice(String noticeNo) throws SQLException {
	IDB session = DBFactory.getDB();
	Notice obj = (Notice)session.getObject("select * from tnotice where notice_no=?",Notice.class,noticeNo);
	return obj;
}
public List<Notice> getNotice(Page page,Notice notice) throws SQLException {
	HsSqlString dbSql = new HsSqlString("tnotice");
/*	if (!StringUtils.isEmpty(notice.getNoticeNo())){
		dbSql.setWhere("notice_no like '%" + notice.getNoticeNo() + "%'");
	}*/
	//分页开始位置
	int startIndex = page.getCurrentResult();
	IDB session = DBFactory.getDB();
	// 获得并返回本次查询的总条数
	int count = 0;
	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
	page.setTotalResult(count);
	return session.getObjectListByListForPage(dbSql.getSqlString(), Notice.class, startIndex, page.getShowCount(), dbSql.getParamList());
}


}
