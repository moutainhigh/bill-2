/********************************************
* 文件名称: RgctDraftTemplateDao.java
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
import com.herongtech.rc.domain.bean.RgctDraftTemplate;

import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;

public class RgctDraftTemplateDao{

public int addRgctDraftTemplate(RgctDraftTemplate obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tRGCT_DRAFT_TEMPLATE(id,draft_no,template_name,template_ctx,busi_type,template_cn_name,sys_run_status,use_queue,is_check_draft)values(?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getDraftNo(),obj.getTemplateName(),obj.getTemplateCtx(),obj.getBusiType(),
	obj.getTemplateCnName(),obj.getSysRunStatus(),obj.getUseQueue(),obj.getIsCheckDraft());
}

public int deleteRgctDraftTemplate(RgctDraftTemplate obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tRGCT_DRAFT_TEMPLATE where draft_no=?",obj.getDraftNo());
}

public int deleteRgctDraftTemplate(String draftNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tRGCT_DRAFT_TEMPLATE where draft_no=?",draftNo);
}

public int modifyRgctDraftTemplate(RgctDraftTemplate obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tRGCT_DRAFT_TEMPLATE set id=?,template_name=?,template_ctx=?,busi_type=?,template_cn_name=?,sys_run_status=?,use_queue=?,is_check_draft=? where draft_no=?",
	obj.getId(),obj.getTemplateName(),obj.getTemplateCtx(),obj.getBusiType(),
	obj.getTemplateCnName(),obj.getSysRunStatus(),obj.getUseQueue(),obj.getIsCheckDraft(),
	obj.getDraftNo());
}

public int modifyRgctDraftTemplate(RgctDraftTemplate obj,String draftNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tRGCT_DRAFT_TEMPLATE set id=?,template_name=?,template_ctx=?,busi_type=?,template_cn_name=?,sys_run_status=?,use_queue=?,is_check_draft=? where draft_no=?",
	obj.getId(),obj.getTemplateName(),obj.getTemplateCtx(),obj.getBusiType(),
	obj.getTemplateCnName(),obj.getSysRunStatus(),obj.getUseQueue(),obj.getIsCheckDraft(),
	draftNo);
}

public RgctDraftTemplate getRgctDraftTemplate(String draftNo) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctDraftTemplate obj = (RgctDraftTemplate)session.getObject("select * from tRGCT_DRAFT_TEMPLATE where draft_no=?",RgctDraftTemplate.class,draftNo);
	return obj;
}

public RgctDraftTemplate getRgctDraftTemplateByName(String templateName) throws SQLException {
	IDB session = DBFactory.getDB();
	RgctDraftTemplate obj = (RgctDraftTemplate)session.getObject("select * from tRGCT_DRAFT_TEMPLATE where template_name=?",RgctDraftTemplate.class, templateName);
	return obj;
}

}
