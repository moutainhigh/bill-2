/********************************************
 * 文件名称: IRcBaseService.java
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

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.rcservice.IRcBaseService;

public interface IRcRegBillService extends IRcBaseService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**新增票据信息
	 */
	public void regBill(RgctBill rgctBill)throws BizAppException;
	
	
	/* 查询可出票信息  */
	public String getRegBillSql(RcBaseSearchBean sb) throws BizAppException;
	
	/**
	 * 出票信息登记
	 * @param bill	票据信息
	 * @throws BizAppException
	 */
	public void registerRequest(RgctBill bill) throws BizAppException;
	
	/**
	 * 001-005处理
	 * @param bill
	 * @throws BizAppException
	 */
	public void registerYes(RgctBill bill) throws BizAppException;
	
	/**
	 * 可撤票查询
	 * @param sb
	 * @return
	 * @throws BizAppException
	 */
	public String queryRemitterBack(RcBaseSearchBean sb)
			throws BizAppException;
	
	/**
	 * 撤票
	 * @param bill
	 * @throws BizAppException
	 */
	public void remitterBack(RgctBill bill) throws BizAppException ;
}
