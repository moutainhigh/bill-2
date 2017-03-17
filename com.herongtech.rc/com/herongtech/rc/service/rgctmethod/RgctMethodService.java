/********************************************
 * 文件名称: EcdsApDataService.java
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

package com.herongtech.rc.service.rgctmethod;

import java.sql.SQLException;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;
import com.herongtech.rc.service.interfaces.IRgctMethodService;
import com.herongtech.sysconstant.ISysErrorNo;


public class RgctMethodService implements IRgctMethodService {

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 取ecds交易行信息参数信息
	 */
	public  RgctMethod getRgctMethod(Long id) throws BizAppException {
		RgctMethodDao dao=new RgctMethodDao();
		try {
			return dao.getRgctMethod(id);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}
	
	/**
	 * 取系统参数信息
	 * 
	 * @param bankNo 参数标识
	 */
	public  RgctMethod getRgctMethod(String subSystem, String interfaceName, String methodName) throws BizAppException{
		RgctMethodDao dao=new RgctMethodDao();
		try {
			return dao.getRgctMethod(subSystem, interfaceName, methodName);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctMethod(RgctMethod RgctMethod)throws BizAppException{
		RgctMethodDao dao=new RgctMethodDao();
		try {
			if (dao.addRgctMethod(RgctMethod) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctMethod失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctMethod(RgctMethod RgctMethod)throws BizAppException {
		
		RgctMethodDao dao=new RgctMethodDao();
		try {
			if (dao.modifyRgctMethod(RgctMethod) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctMethod失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
    
}
