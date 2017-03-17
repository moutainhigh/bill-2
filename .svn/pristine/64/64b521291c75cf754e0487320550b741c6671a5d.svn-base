/********************************************
 * 文件名称: IRgctExceptionDraftService.java
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

package com.herongtech.rc.service.rgctexceptiondraft;

import java.sql.SQLException;

import com.herongtech.rc.domain.bean.RgctExceptionDraft;
import com.herongtech.rc.domain.dao.RgctExceptionDraftDao;
import com.herongtech.rc.service.interfaces.IRgctExceptionDraftService;
import com.herongtech.sysconstant.ISysErrorNo;
import com.herongtech.exception.impl.BizAppException;

public class RgctExceptionDraftService implements IRgctExceptionDraftService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 取系统参数信息
	 * 
	 * @param bankNo 参数标识
	 */
	public  RgctExceptionDraft getRgctExceptionDraft(String logId) throws BizAppException{
		RgctExceptionDraftDao dao = new RgctExceptionDraftDao();
		try {
			return dao.getRgctExceptionDraft(logId);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
		
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctExceptionDraft(RgctExceptionDraft rgctExceptionDraft)throws BizAppException{
		RgctExceptionDraftDao dao=new RgctExceptionDraftDao();
		try {
			if (dao.addRgctExceptionDraft(rgctExceptionDraft) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctExceptionDraft失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctExceptionDraft(RgctExceptionDraft rgctExceptionDraft)throws BizAppException{
		RgctExceptionDraftDao dao=new RgctExceptionDraftDao();
		try {
			if (dao.modifyRgctExceptionDraft(rgctExceptionDraft) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctExceptionDraft失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
}
