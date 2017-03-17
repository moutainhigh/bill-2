/********************************************
 * 文件名称: ParamService.java
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

package com.herongtech.console.service.param;

import java.sql.SQLException;

import com.herongtech.console.cache.DraftMappingCache;
import com.herongtech.console.cache.SysParamCache;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.domain.bean.Param;
import com.herongtech.console.domain.dao.ParamDao;
import com.herongtech.console.service.interfaces.IParamService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 系统参数取法
 *
 */
public class ParamService implements IParamService{
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取系统参数表
	 */
	public  Param getParam(String paramId)throws BizAppException{
		ParamDao dao=new ParamDao();
		try {
			return dao.getParam(IConstants.DEFAULT_PARAM_CODE, paramId, IConstants.DEFAULT_BANK_NO);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入系统参数表
	 */
	public void addParam(Param param)throws BizAppException{
		ParamDao dao=new ParamDao();
		
		try {
			if (dao.addParam(param) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Param失败");
			}else{
				SysParamCache.getInstance().addparammap(param.getParamId(), param.getParamValue());		
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改系统参数表
	 */
	public void modifyParam(Param param)throws BizAppException{
		ParamDao dao=new ParamDao();
		try {
			if (dao.modifyParam(param) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改Param失败");
			}else{				
				SysParamCache.getInstance().updateSysParam(param.getParamId(), param.getParamValue());
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**更改缓存和数据库中的Param数据*/
	public void updateparam(String paramid,String paramvalue)throws BizAppException{
		ParamDao dao = new ParamDao();
		try {
			
			if (dao.updateParam(paramid, paramvalue)!= 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改Param失败");
			}else{
				SysParamCache.getInstance().updateSysParam(paramid, paramvalue);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
