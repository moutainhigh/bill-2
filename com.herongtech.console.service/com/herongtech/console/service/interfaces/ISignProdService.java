/********************************************
 * 文件名称: ISignProdService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-21 上午11:12:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.exception.impl.BizAppException;

public interface ISignProdService {

	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
		
	/**
	 * 插入产品签约信息表
	 *
	 */
	public void addSignProd(SignProd signProd) throws BizAppException;
	
	/**
	 * 修改产品签约信息表
	 */
	public void modifySignProd(SignProd signProd) throws BizAppException; 
	
	/**
	 * 根据
	 */
	public SignProd getSignProdByBusAct(String prodId, String busSettleAct) throws BizAppException; 
	
	/**
	 * 根据服务产品编号和客户号查询产品签约信息(托收中使用了此方法)
	 * @param prodId
	 * @param custNo
	 * @return
	 * @throws BizAppException
	 */
	public SignProd getSignProdInfoByCust(String prodId, String custNo) throws BizAppException;
	
	/**
	 * 根据服务产品编号和客户号和签约状态查询产品签约信息
	 * @param page
	 * @param signProd
	 * @return
	 * @throws BizAppException
	 */
	public List<SignProd> getSignProdInfoBySignStatusCd(Page page,SignProd signProd) throws BizAppException;
	/**
	 * 根据id查询产品签约信息
	 * @param id
	 * @return
	 * @throws BizAppException
	 */
	public SignProd getSignProdById(String id)throws BizAppException;
	
	
	/**
	 * 根据服务产品编号和客户号查询产品签约信息(网银端查询签约信息)
	 * @param prodNo
	 * @param custNo
	 * @return
	 * @throws BizAppException
	 */
	public List<SignProd> getSignProdInfoByPro(String prodNo, String custNo) throws BizAppException;
	
}
