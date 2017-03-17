/********************************************
 * 文件名称: IDraftDrwrWrapper.java
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
package com.herongtech.console.service.interfaces;

import com.herongtech.console.domain.bean.EcdsBillBean;
import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.exception.impl.BizAppException;

/**
 * 贴现 网银接口 对应的服务组装(事务一致)
 */
public interface IDraftDrwrWrapper {
	
	/**
	 * 删除票据信息
	 * @param rgctId
	 */
	public void deleteDraftEcdsRequest(Long rgctId) throws BizAppException;
	
	/**
	 * 新增票据
	 * @param bill
	 */
	public void addNewBillDraft(TransPub transPub) throws BizAppException;
	
	/**
	 * 修改票据
	 * @throws BizAppException
	 */
	public void updateDraftEcdsRequest(EcdsBillBean bean) throws BizAppException;
	
	/**
	 * 票据新增校验
	 * @throws BizAppException
	 */
	public void checkNewBillDraft(TransPub transPub) throws BizAppException;
	
	
	/**
	 * 票据修改校验
	 * @throws BizAppException
	 */
	public void checkOldBillDraft(TransPub transPub) throws BizAppException;
}
