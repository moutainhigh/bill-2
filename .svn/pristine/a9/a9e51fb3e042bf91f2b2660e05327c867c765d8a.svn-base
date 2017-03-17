/********************************************
 * 文件名称: ICustInfoAcctService.java
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

import java.util.List;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.exception.impl.BizAppException;

public interface ICustInfoAcctService {

	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取客户帐号信息参数
	 * 
	 * @param id 客户帐号信息标识
	 */
    public  CustInfoAcct getParam(String custNo,String acctNo) throws BizAppException;
    
    /**
	 * 获取客户帐号信息参数
	 * 
	 */
    public  CustInfoAcct getParam(String acctNo) throws BizAppException;
    /**
	 * 获取客户帐号信息参数根据custNo
	 * 
	 */
    public  CustInfoAcct getParamByCustNo(String custNo) throws BizAppException;
	
	/**
	 * 插入客户帐号信息表
	 *
	 */
	public void addParam(CustInfoAcct custIA)throws BizAppException;
	
	/**
	 * 修改客户帐号信息表
	 */
	
	public void modifyParam(CustInfoAcct custIA)throws BizAppException;
	
	/**
	 * 根据客户号查找客户信息
	 */
	public List<CustInfoAcct> resultList1(String custNo)throws BizAppException;

}
