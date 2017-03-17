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

package com.herongtech.rc.service.rgctdrafttemplate;


import java.sql.SQLException;

import com.herongtech.rc.domain.bean.RgctDraftTemplate;
import com.herongtech.rc.domain.dao.EcdsBankDataDao;
import com.herongtech.rc.domain.dao.RgctDraftTemplateDao;
import com.herongtech.rc.service.interfaces.IRgctDraftTemplateService;
import com.herongtech.sysconstant.ISysErrorNo;
import com.herongtech.exception.impl.BizAppException;

public class RgctDraftTemplateService implements IRgctDraftTemplateService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 取系统参数信息
	 * 
	 * @param bankNo 参数标识
	 */
	public  RgctDraftTemplate getRgctDraftTemplate(String draftNo) throws BizAppException{
		RgctDraftTemplateDao dao=new RgctDraftTemplateDao();
		try {
			return dao.getRgctDraftTemplate(draftNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 取系统参数信息
	 * 
	 * @param bankNo 参数标识
	 */
	public  RgctDraftTemplate getRgctDraftTemplateByName(String templateName) throws BizAppException{
		RgctDraftTemplateDao dao=new RgctDraftTemplateDao();
		try {
			return dao.getRgctDraftTemplateByName(templateName);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctDraftTemplate(RgctDraftTemplate rgctDraftTemplate)throws BizAppException{
		RgctDraftTemplateDao dao=new RgctDraftTemplateDao();
		try {
			if (dao.addRgctDraftTemplate(rgctDraftTemplate) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctDraftTemplate失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctDraftTemplate(RgctDraftTemplate rgctDraftTemplate)throws BizAppException{
		RgctDraftTemplateDao dao=new RgctDraftTemplateDao();
		try {
			if (dao.modifyRgctDraftTemplate(rgctDraftTemplate) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改RgctDraftTemplate失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
}
