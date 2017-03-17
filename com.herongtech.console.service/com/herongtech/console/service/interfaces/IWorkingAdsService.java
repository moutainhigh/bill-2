/********************************************
 * 文件名称: IWorkingAdsService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-28
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.WorkingAds;
import com.herongtech.exception.impl.BizAppException;


public interface IWorkingAdsService {
	
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * @WorkingAds workingAdsNo 标识
	 */
	public  WorkingAds getWorkingAds(String workingAdsNo) throws BizAppException;
	
	/**
	 * 插入参数表
	 *
	 */
	public void addWorkingAds(WorkingAds workingads)throws BizAppException;
	
	/**
	 * 修改参数表
	 */
	
	public void modifyWorkingAds(WorkingAds workingads)throws BizAppException;
}
