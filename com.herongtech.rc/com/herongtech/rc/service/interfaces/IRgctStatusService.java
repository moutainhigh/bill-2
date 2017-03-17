/********************************************
 * 文件名称: IRgctStatusService.java
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


import java.util.List;

import com.herongtech.rc.domain.bean.RgctStatus;
import com.herongtech.exception.impl.BizAppException;

public interface IRgctStatusService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 
	 */
	public  List<RgctStatus> getRgctStatus(long methodId, String param) throws BizAppException;
	
	/**
	 *
	 */
	public void addRgctStatus(RgctStatus RgctStatus)throws BizAppException;
	
	/**
	 */
	
	public void modifyRgctStatus(RgctStatus RgctStatus)throws BizAppException;
}
