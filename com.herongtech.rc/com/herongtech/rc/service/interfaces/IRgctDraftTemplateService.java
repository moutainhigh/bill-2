/********************************************
 * 文件名称: IRgctDraftTemplateService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.rc.service.interfaces;


import com.herongtech.rc.domain.bean.RgctDraftTemplate;
import com.herongtech.exception.impl.BizAppException;

public interface IRgctDraftTemplateService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 取系统参数信息
	 * 
	 * @param bankNo 参数标识
	 */
	public  RgctDraftTemplate getRgctDraftTemplate(String draftNo) throws BizAppException;
	
	/**
	 * 取系统参数信息
	 * 
	 * @param bankNo 参数标识
	 */
	public  RgctDraftTemplate getRgctDraftTemplateByName(String templateName) throws BizAppException;
	
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctDraftTemplate(RgctDraftTemplate rgctDraftTemplate)throws BizAppException;
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctDraftTemplate(RgctDraftTemplate rgctDraftTemplate)throws BizAppException;
}
