/********************************************
 * 文件名称: ErrmsgService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-8 下午03:30:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.errmsg;

import com.herongtech.console.domain.bean.Errmsg;
import com.herongtech.console.domain.dao.ErrmsgDao;
import com.herongtech.console.service.interfaces.IErrmsgService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 错误信息表方法
 *
 */
public class ErrmsgService implements IErrmsgService{

	/**
	 * 获取错误信息表
	 */
	public Errmsg getErrmsg(String errCode) throws BizAppException {
		ErrmsgDao dao = new ErrmsgDao();
		try {
			return dao.getErrmsg(errCode);
		} catch (Exception e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 插入错误信息表
	 *
	 */
	public void addErrmsg(Errmsg errmsg) throws BizAppException {
		ErrmsgDao dao = new ErrmsgDao();
		try {
			if(dao.addErrmsg(errmsg) != 1){
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Errmsg失败");
			}
		} catch (Exception e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}

	/**
	 * 修改错误信息表
	 */
	public void modifyErrmsg(Errmsg errmsg) throws BizAppException {
		ErrmsgDao dao = new ErrmsgDao();
		try {
			if(dao.modifyErrmsg(errmsg) != 1){
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改Errmsg失败");
			}
		} catch (Exception e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	
}
