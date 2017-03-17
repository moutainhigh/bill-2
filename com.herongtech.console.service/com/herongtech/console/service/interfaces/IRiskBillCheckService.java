/********************************************
 * 文件名称: IRiskBillCheckService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 风险验证，包括
 * 风险票：票面要素和风险票数据库匹配
 * 黑名单：与系统维护的黑名单行号匹配
 * 					贴现检查承兑行
 * 					转入检检查承兑行及交易对手
 * 系统版本: 2.0.0.1
 * 开发人员: fanmin
 * 开发时间: 2016年9月20日11:43:29
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.interfaces;

import java.util.List;

import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.exception.impl.BizAppException;


public interface IRiskBillCheckService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 黑名单机构、风险票据检查
	 * @param methodName 方法名称
	 * @param serviceName 服务名称
	 * @param strIds 票据id字符串，以逗号分隔
	 * @return	List<BillInfoDTO> 风险票集合
	 * @throws BizAppException 
	 */
	public List<BillInfoDTO> checkRiskInfo(String methodName,String serviceName,String ids) throws BizAppException;

}
