/********************************************
 * 文件名称: IBlackService.java
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

import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.console.domain.bean.BlackList;
import com.herongtech.exception.impl.BizAppException;

public interface IBlackService {

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取黑名单参数
	 * 
	 * @param id 黑名单标识
	 */
	public  BlackList getParam(String unionBankno) throws BizAppException;
	
	/**
	 * 插入黑名单表
	 *
	 */
	public void addParam(BlackList black)throws BizAppException;
	
	/**
	 * 修改黑名单表
	 */
	
	public void modifyParam(BlackList black)throws BizAppException;
	
	/**
	 * 黑名单检查	校验承兑人开户行行号,交易对手行行号是否在黑名单之列。<br>
	 * 查询规则：<br>
	 * 		票号全匹配，多张使用IN。<br>
	 * @param bills	票据
	 * @return	 List<BlackList>	查询到的黑名单
	 * @throws BizAppException	系统异常
	 */
	public  List<BlackList> getBlackListByBills(List<BillInfoDTO> bills)throws BizAppException;
}
