/********************************************
 * 文件名称: ISerialnoService.java
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

package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.Serialno;
import com.herongtech.exception.impl.BizAppException;


public interface ISerialnoService {
	
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取
	 * 
	 * @param key_no 序列名称
	 */
	public  Serialno getSerialno(String key_no) throws BizAppException;
	
	/**
	 * 插入参数表
	 *
	 */
	public void addSerialno(Serialno serialno)throws BizAppException;
	
	/**
	 * 修改参数表
	 */
	
	public void modifySerialno(Serialno serialno)throws BizAppException;
}
