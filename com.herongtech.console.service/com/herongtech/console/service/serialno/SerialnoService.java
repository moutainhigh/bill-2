/********************************************
 * 文件名称: SerialnoService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2016-7-12 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.serialno;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.Serialno;
import com.herongtech.console.domain.dao.SerialnoDao;
import com.herongtech.console.service.interfaces.ISerialnoService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 系统参数取法
 *
 */
public class SerialnoService implements ISerialnoService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取序列表
	 */
	public  Serialno getSerialno(String key_no)throws BizAppException{
		SerialnoDao dao=new SerialnoDao();
		try {
			return dao.getSerialno(key_no);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入序列表
	 */
	public void addSerialno(Serialno serialno)throws BizAppException{
		SerialnoDao dao=new SerialnoDao();
		try {
			if (dao.addSerialno(serialno) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Serialno失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改序列表
	 */
	public void modifySerialno(Serialno serialno)throws BizAppException{
		SerialnoDao dao=new SerialnoDao();
		try {
			if (dao.modifySerialno(serialno) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改Serialno失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
}
