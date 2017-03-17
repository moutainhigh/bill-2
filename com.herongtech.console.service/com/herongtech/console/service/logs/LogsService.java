/********************************************
 * 文件名称: LogsService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-11
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.logs;
import java.sql.SQLException;

import com.herongtech.console.domain.bean.Logs;
import com.herongtech.console.domain.dao.LogsDao;
import com.herongtech.console.service.interfaces.ILogsService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;


public class LogsService implements ILogsService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取系统日志表
	 */
	public  Logs getLogs(int logSerial)throws BizAppException{
		LogsDao dao=new LogsDao();
		try {
			return dao.getLogs();
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	
}
