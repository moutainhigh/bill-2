/********************************************
* 文件名称: RgctDraftLogDao.java
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


import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctDraftLog;
public class RgctDraftLogDao{

public int addRgctDraftLog(RgctDraftLog obj) throws SQLException  {
	IDB session = DBFactory.getDB();	
	String dialectName=session.getDialectName();//数据库类型
	
	if("ORACLE".equalsIgnoreCase(dialectName)){
		return oraclesave(obj);
	}
	
	if("DB2".equalsIgnoreCase(dialectName)){
		return normalsave(obj);
	}
	return 0;
}

public int deleteRgctDraftLog(RgctDraftLog obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trgct_draft_log where log_id=?",obj.getLogId());
}

public int deleteRgctDraftLog(String logId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trgct_draft_log where log_id=?",logId);
}

public int modifyRgctDraftLog(RgctDraftLog obj) throws SQLException {
	IDB session = DBFactory.getDB();	
	String dialectName=session.getDialectName();//数据库类型
	
	if("ORACLE".equalsIgnoreCase(dialectName)){
		return oracleupdate(obj);
	}
	
	if("DB2".equalsIgnoreCase(dialectName)){
		return normalupdate(obj);
	}
	return 0;
}
/**正常数据库更改*/
public int normalupdate(RgctDraftLog obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_draft_log set rgct_id=?,rgct_hist_id=?,draft_no_req=?,req_draft=?,req_dt=?,req_time=?,req_sid=?,req_bank_no=?,resp_draft=?,resp_dt=?,resp_time=?,resp_sid=?,resp_bank_no=?,draft_no_resp=?,req_msg_id=?,from_draft_no=?,in_out=?,bill_no=?,reply_flag=?,is_check_draft=?,accept_bank_no=?,process_flag=?,send_flag=?,draft_info=?,entity_id=?,entity_no=?,entity_type=? where log_id=?",
	obj.getRgctId(),obj.getRgctHistId(),obj.getDraftNoReq(),obj.getReqDraft(),
	obj.getReqDt(),obj.getReqTime(),obj.getReqSid(),obj.getReqBankNo(),
	obj.getRespDraft(),obj.getRespDt(),obj.getRespTime(),obj.getRespSid(),
	obj.getRespBankNo(),obj.getDraftNoResp(),obj.getReqMsgId(),obj.getFromDraftNo(),
	obj.getInOut(),obj.getBillNo(),obj.getReplyFlag(),obj.getIsCheckDraft(),
	obj.getAcceptBankNo(),obj.getProcessFlag(),obj.getSendFlag(),obj.getDraftInfo(),
	obj.getEntityId(),obj.getEntityNo(),obj.getEntityType(),obj.getLogId());
}

/**oracle数据库时clob的更改*/
public int oracleupdate(RgctDraftLog obj)throws SQLException{
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	 session.execute("update trgct_draft_log set rgct_id=?,rgct_hist_id=?,draft_no_req=?,req_dt=?,req_time=?,req_sid=?,req_bank_no=?,resp_draft=?,resp_dt=?,resp_time=?,resp_sid=?,resp_bank_no=?,draft_no_resp=?,req_msg_id=?,from_draft_no=?,in_out=?,bill_no=?,reply_flag=?,is_check_draft=?,accept_bank_no=?,process_flag=?,send_flag=?,draft_info=?,entity_id=?,entity_no=?,entity_type=? where log_id=?",
	obj.getRgctId(),obj.getRgctHistId(),obj.getDraftNoReq(),
	obj.getReqDt(),obj.getReqTime(),obj.getReqSid(),obj.getReqBankNo(),
	" ",obj.getRespDt(),obj.getRespTime(),obj.getRespSid(),
	obj.getRespBankNo(),obj.getDraftNoResp(),obj.getReqMsgId(),obj.getFromDraftNo(),
	obj.getInOut(),obj.getBillNo(),obj.getReplyFlag(),obj.getIsCheckDraft(),
	obj.getAcceptBankNo(),obj.getProcessFlag(),obj.getSendFlag(),obj.getDraftInfo(),
	obj.getEntityId(),obj.getEntityNo(),obj.getEntityType(),obj.getLogId());
	
	Connection con = session.getConnection();
	con.setAutoCommit(false);  //不写这个就报错
    Statement st =  con.createStatement(); 
     try {
        	 ResultSet rs = st.executeQuery("select resp_draft from trgct_draft_log where log_id='"+obj.getLogId()+"' for update");  
        	 if (rs.next()){  
        		 //得到java.sql.Clob对象后强制转换为oracle.sql.CLOB  
        		 oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob("resp_draft");  
        		 Writer outStream = clob.getCharacterOutputStream();  
        		 //data是传入的字符串，定义：String data  
        		 char[] c =obj.getRespDraft().toCharArray();
        		 outStream.write(c, 0, c.length);  
        		 outStream.flush();
        		 outStream.close();
        	 	}
        	 	st.close();
		       con.commit(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	       
     
	return 1;
}

public int modifyRgctDraftLog(RgctDraftLog obj,String logId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trgct_draft_log set rgct_id=?,rgct_hist_id=?,draft_no_req=?,req_draft=?,req_dt=?,req_time=?,req_sid=?,req_bank_no=?,resp_draft=?,resp_dt=?,resp_time=?,resp_sid=?,resp_bank_no=?,draft_no_resp=?,req_msg_id=?,from_draft_no=?,in_out=?,bill_no=?,reply_flag=?,is_check_draft=?,accept_bank_no=?,process_flag=?,send_flag=?,draft_info=?,entity_id=?,entity_no=?,entity_type=? where log_id=?",
	obj.getRgctId(),obj.getRgctHistId(),obj.getDraftNoReq(),obj.getReqDraft(),
	obj.getReqDt(),obj.getReqTime(),obj.getReqSid(),obj.getReqBankNo(),
	obj.getRespDraft(),obj.getRespDt(),obj.getRespTime(),obj.getRespSid(),
	obj.getRespBankNo(),obj.getDraftNoResp(),obj.getReqMsgId(),obj.getFromDraftNo(),
	obj.getInOut(),obj.getBillNo(),obj.getReplyFlag(),obj.getIsCheckDraft(),
	obj.getAcceptBankNo(),obj.getProcessFlag(),obj.getSendFlag(),obj.getDraftInfo(),
	obj.getEntityId(),obj.getEntityNo(),obj.getEntityType(),logId);
}

public RgctDraftLog getRgctDraftLog(String logId) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctDraftLog obj = (RgctDraftLog)session.getObject("select * from trgct_draft_log where log_id=?",RgctDraftLog.class,logId);
	return obj;
}

public RgctDraftLog getRgctDraftLogByReqSid(String reqSid) throws SQLException {
    IDB session = DBFactory.getDB();
    RgctDraftLog obj = (RgctDraftLog)session.getObject("select * from tRGCT_DRAFT_LOG where req_sid = ? order by LOG_ID asc",RgctDraftLog.class, reqSid);
    return obj;
}

public RgctDraftLog getDraftLogByRespSid(String respSid) throws SQLException {
    IDB session = DBFactory.getDB();
    RgctDraftLog obj = (RgctDraftLog)session.getObject("select * from tRGCT_DRAFT_LOG where resp_sid = ? order by LOG_ID asc",RgctDraftLog.class, respSid);
    return obj;
}


public RgctDraftLog getDraftLogByReqMsgId(String reqMsgId) throws SQLException {
    IDB session = DBFactory.getDB();
    RgctDraftLog obj = (RgctDraftLog)session.getObject("select * from tRGCT_DRAFT_LOG where req_msg_id=? order by LOG_ID asc", RgctDraftLog.class, reqMsgId);
    return obj;
}

public RgctDraftLog getSignDraftLogByReqMsgId(String reqMsgId) throws SQLException {
    IDB session = DBFactory.getDB();
    RgctDraftLog obj = (RgctDraftLog)session.getObject("select * from tRGCT_DRAFT_LOG where  req_msg_id=? and draft_no_req='031' ", RgctDraftLog.class, reqMsgId);
    return obj;
}

public RgctDraftLog getrespDraftByReqSid(String reqSid) throws SQLException {
    IDB session = DBFactory.getDB();
    RgctDraftLog obj = (RgctDraftLog)session.getObject("select * from tRGCT_DRAFT_LOG where req_sid=? and resp_Sid !='' order by LOG_ID desc", RgctDraftLog.class, reqSid);
    return obj;
}

/**正常存法*/
private static int normalsave(RgctDraftLog obj) throws SQLException{
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trgct_draft_log(log_id,rgct_id,rgct_hist_id,draft_no_req,req_draft,req_dt,req_time,req_sid,req_bank_no,resp_draft,resp_dt,resp_time,resp_sid,resp_bank_no,draft_no_resp,req_msg_id,from_draft_no,in_out,bill_no,reply_flag,is_check_draft,accept_bank_no,process_flag,send_flag,draft_info,entity_id,entity_no,entity_type)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
			obj.getLogId(),obj.getRgctId(),obj.getRgctHistId(),obj.getDraftNoReq(),obj.getReqDraft(),
			obj.getReqDt(),obj.getReqTime(),obj.getReqSid(),obj.getReqBankNo(),
			obj.getRespDraft(),obj.getRespDt(),obj.getRespTime(),obj.getRespSid(),
			obj.getRespBankNo(),obj.getDraftNoResp(),obj.getReqMsgId(),obj.getFromDraftNo(),
			obj.getInOut(),obj.getBillNo(),obj.getReplyFlag(),obj.getIsCheckDraft(),
			obj.getAcceptBankNo(),obj.getProcessFlag(),obj.getSendFlag(),obj.getDraftInfo(),
			obj.getEntityId(),obj.getEntityNo(),obj.getEntityType());

}
/**oracle存法*/
private static int oraclesave(RgctDraftLog obj) throws SQLException{
	IDB session = DBFactory.getDB();	
	Connection con = session.getConnection();
	session.execute("insert into trgct_draft_log(log_id,rgct_id,rgct_hist_id,draft_no_req,req_draft,req_dt,req_time,req_sid,req_bank_no,resp_draft,resp_dt,resp_time,resp_sid,resp_bank_no,draft_no_resp,req_msg_id,from_draft_no,in_out,bill_no,reply_flag,is_check_draft,accept_bank_no,process_flag,send_flag,draft_info,entity_id,entity_no,entity_type)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				obj.getLogId(),obj.getRgctId(),obj.getRgctHistId(),obj.getDraftNoReq()," ",
				obj.getReqDt(),obj.getReqTime(),obj.getReqSid(),obj.getReqBankNo(),
				" ",obj.getRespDt(),obj.getRespTime(),obj.getRespSid(),
				obj.getRespBankNo(),obj.getDraftNoResp(),obj.getReqMsgId(),obj.getFromDraftNo(),
				obj.getInOut(),obj.getBillNo(),obj.getReplyFlag(),obj.getIsCheckDraft(),
				obj.getAcceptBankNo(),obj.getProcessFlag(),obj.getSendFlag(),obj.getDraftInfo(),
				obj.getEntityId(),obj.getEntityNo(),obj.getEntityType());
		try {
			con.setAutoCommit(false);  //不写这个就报错
		       Statement st =  con.createStatement(); 
		       //插入一个空对象empty_clob()  
		       //st.executeUpdate("insert into trgct_draft_log(log_id,rgct_id,rgct_hist_id,draft_no_req,req_draft,req_dt,req_time,req_sid,req_bank_no,resp_draft,resp_dt,resp_time,resp_sid,resp_bank_no,draft_no_resp,req_msg_id,from_draft_no,in_out,bill_no,reply_flag,is_check_draft,accept_bank_no,process_flag,send_flag,draft_info,entity_id,entity_no,entity_type)values('"+obj.getLogId()+"','"+obj.getRgctId()+"','"+obj.getRgctHistId()+"','"+obj.getDraftNoReq()+"',empty_clob(),'"+obj.getReqDt()+"','"+obj.getReqTime()+"','"+obj.getReqSid()+"','"+obj.getReqBankNo()+"','"+obj.getRespDraft()+"','"+obj.getRespDt()+"','"+obj.getRespTime()+"','"+obj.getRespSid()+"','"+obj.getRespBankNo()+"','"+obj.getDraftNoResp()+"','"+obj.getReqMsgId()+"','"+obj.getFromDraftNo()+"','"+obj.getInOut()+"','"+obj.getBillNo()+"','"+obj.getReplyFlag()+"','"+obj.getIsCheckDraft()+"','"+obj.getAcceptBankNo()+"','"+obj.getProcessFlag()+"','"+obj.getSendFlag()+"','"+obj.getDraftInfo()+"','"+obj.getEntityId()+"','"+obj.getEntityNo()+"','"+obj.getEntityType()+"')"); 
		       ResultSet rs = st.executeQuery("select req_draft from trgct_draft_log where log_id='"+obj.getLogId()+"' for update");  
		       if (rs.next()){  
		           //得到java.sql.Clob对象后强制转换为oracle.sql.CLOB  
		           oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob("req_draft");  
		           Writer outStream = clob.getCharacterOutputStream();  
		           //data是传入的字符串，定义：String data  
		           char[] c = obj.getReqDraft().toCharArray();
		           outStream.write(c, 0, c.length); 
		           outStream.flush();
		           outStream.close();
		       }  
		       rs = st.executeQuery("select resp_draft from trgct_draft_log where log_id='"+obj.getLogId()+"' for update");  
		       if (rs.next())  
		       {  
		           //得到java.sql.Clob对象后强制转换为oracle.sql.CLOB  
		           oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob("resp_draft");  
		           Writer outStream = clob.getCharacterOutputStream();  
		           //data是传入的字符串，定义：String data  
		           char[] c =obj.getRespDraft().toCharArray();
		           outStream.write(c, 0, c.length);  
		           outStream.flush();
		           outStream.close();
		       }  	       
		       
		       st.close();
		       con.commit();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;

}


}
