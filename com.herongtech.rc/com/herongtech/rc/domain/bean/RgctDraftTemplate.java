/********************************************
* 文件名称: RgctDraftTemplate.java
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
package com.herongtech.rc.domain.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class RgctDraftTemplate{
	//ID
	private Long id = 0l;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	//DRAFT_NO
	private String draftNo = " ";
	public String getDraftNo(){
		return draftNo;
	}
	public void setDraftNo(String draftNo){
		this.draftNo = draftNo;
	}

	//TEMPLATE_NAME
	private String templateName = " ";
	public String getTemplateName(){
		return templateName;
	}
	public void setTemplateName(String templateName){
		this.templateName = templateName;
	}

	//TEMPLATE_CTX
	private String templateCtx = " ";
	public String getTemplateCtx(){
		return templateCtx;
	}
	public void setTemplateCtx(String templateCtx){
		this.templateCtx = templateCtx;
	}

	//BUSI_TYPE
	private String busiType = " ";
	public String getBusiType(){
		return busiType;
	}
	public void setBusiType(String busiType){
		this.busiType = busiType;
	}

	//TEMPLATE_CN_NAME
	private String templateCnName = " ";
	public String getTemplateCnName(){
		return templateCnName;
	}
	public void setTemplateCnName(String templateCnName){
		this.templateCnName = templateCnName;
	}

	//SYS_RUN_STATUS
	private String sysRunStatus = " ";
	public String getSysRunStatus(){
		return sysRunStatus;
	}
	public void setSysRunStatus(String sysRunStatus){
		this.sysRunStatus = sysRunStatus;
	}

	//USE_QUEUE
	private String useQueue = " ";
	public String getUseQueue(){
		return useQueue;
	}
	public void setUseQueue(String useQueue){
		this.useQueue = useQueue;
	}

	//IS_CHECK_DRAFT
	private String isCheckDraft = " ";
	public String getIsCheckDraft(){
		return isCheckDraft;
	}
	public void setIsCheckDraft(String isCheckDraft){
		this.isCheckDraft = isCheckDraft;
	}


}
