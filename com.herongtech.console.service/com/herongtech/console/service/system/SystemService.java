/********************************************
 * 文件名称: SystemService.java
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

package com.herongtech.console.service.system;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.herongtech.console.core.util.BrowserUtils;
import com.herongtech.console.core.util.ContextHolderUtils;
import com.herongtech.console.core.util.IpConfigMac;
import com.herongtech.console.domain.bean.Logs;
import com.herongtech.console.domain.bean.RoleUser;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.service.interfaces.ISystemService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

public class SystemService implements ISystemService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	/**
	 * 添加日志
	 */
	public void addLog(String logcontent, Short loglevel, Short operatetype) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		String broswer = BrowserUtils.checkBrowse(request);
		
		Logs log = new Logs();
		
		
		log.setSummary(logcontent);
		log.setIp(IpConfigMac.getIpAddr(request));
		log.setBroswer(broswer);
	}
	
	public List<RoleUser> getUserRoleList(User user) throws BizAppException{
		List<RoleUser> list = null;
		
		try {
			IDB session = DBFactory.getDB();
			list = session.getObjectList("select * from trole_user where user_id = ?",RoleUser.class, user.getUserId());
		} catch (Exception e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库查询异常");
		}
		return list;
	}
}
