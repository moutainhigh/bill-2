/********************************************
 * 文件名称: IAcctBalanceService.java
 * 名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 账务余额服务
 * 版本: 2.0.0.1
 * 开发人员: fanmin
 * 开发时间: 2016年9月22日14:28:48
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import java.util.List;

import com.herongtech.console.domain.acct.bean.AcctBalance;
import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.exception.impl.BizAppException;


public interface IAcctBalanceService {

	/**
	 * 通过票号查询余额信息
	 * @param billNos	票号信息，使用逗号分隔
	 * @return	余额信息
	 * @throws BizAppException
	 */
	public List<AcctBalance> getAcctBalanceByBillNo(List<BillInfoDTO> bills)throws BizAppException;
	
	/**
	 * 通过登记中心ID查询余额信息
	 * @param rgctId
	 * @return
	 * @throws BizAppException
	 */
	public  AcctBalance getAcctBalanceByRgctId(String rgctId) throws BizAppException;
}
