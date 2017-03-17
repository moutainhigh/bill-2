/********************************************
* 文件名称: RgctExceptionDraftDao.java
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

import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctExceptionDraft;
import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;

public class RgctExceptionDraftDao{

public int addRgctExceptionDraft(RgctExceptionDraft obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tRGCT_EXCEPTION_DRAFT(log_id,msg_sid,draft_datetime,draft_no,draft_txt,orgnl_msg_sid,orgnl_draft_datetime,orgnl_draft_no,orgnl_draft_txt,bill_no,in_out,status,err_count)values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getLogId(),obj.getMsgSid(),obj.getDraftDatetime(),obj.getDraftNo(),obj.getDraftTxt(),
	obj.getOrgnlMsgSid(),obj.getOrgnlDraftDatetime(),obj.getOrgnlDraftNo(),obj.getOrgnlDraftTxt(),
	obj.getBillNo(),obj.getInOut(),obj.getStatus(),obj.getErrCount());
}

public int deleteRgctExceptionDraft(RgctExceptionDraft obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tRGCT_EXCEPTION_DRAFT where log_id=?",obj.getLogId());
}

public int deleteRgctExceptionDraft(String logId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tRGCT_EXCEPTION_DRAFT where log_id=?",logId);
}

public int modifyRgctExceptionDraft(RgctExceptionDraft obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tRGCT_EXCEPTION_DRAFT set msg_sid=?,draft_datetime=?,draft_no=?,draft_txt=?,orgnl_msg_sid=?,orgnl_draft_datetime=?,orgnl_draft_no=?,orgnl_draft_txt=?,bill_no=?,in_out=?,status=?,err_count=? where log_id=?",
	obj.getMsgSid(),obj.getDraftDatetime(),obj.getDraftNo(),obj.getDraftTxt(),
	obj.getOrgnlMsgSid(),obj.getOrgnlDraftDatetime(),obj.getOrgnlDraftNo(),obj.getOrgnlDraftTxt(),
	obj.getBillNo(),obj.getInOut(),obj.getStatus(),obj.getErrCount(),
	obj.getLogId());
}

public int modifyRgctExceptionDraft(RgctExceptionDraft obj,String logId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tRGCT_EXCEPTION_DRAFT set msg_sid=?,draft_datetime=?,draft_no=?,draft_txt=?,orgnl_msg_sid=?,orgnl_draft_datetime=?,orgnl_draft_no=?,orgnl_draft_txt=?,bill_no=?,in_out=?,status=?,err_count=? where log_id=?",
	obj.getMsgSid(),obj.getDraftDatetime(),obj.getDraftNo(),obj.getDraftTxt(),
	obj.getOrgnlMsgSid(),obj.getOrgnlDraftDatetime(),obj.getOrgnlDraftNo(),obj.getOrgnlDraftTxt(),
	obj.getBillNo(),obj.getInOut(),obj.getStatus(),obj.getErrCount(),
	logId);
}

public RgctExceptionDraft getRgctExceptionDraft(String logId) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctExceptionDraft obj = (RgctExceptionDraft)session.getObject("select * from tRGCT_EXCEPTION_DRAFT where log_id=?",RgctExceptionDraft.class,logId);
	return obj;
}

}
