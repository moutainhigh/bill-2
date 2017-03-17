/********************************************
 * 文件名称: IRiskBillService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fanmin
 * 开发时间: 2016年9月20日11:46:48
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.interfaces;

import java.util.List;

import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.console.domain.bean.RiskBill;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;


public interface IRiskBillService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取票面信息
	 * 
	 * 
	 */
	public  RiskBill getRiskBill(String id) throws BizAppException;
	
	/**
	 * 插入风险票表
	 *
	 */
	public void addRiskBill(RiskBill riskbill)throws BizAppException;
	
	/**
	 * 修改风险票表
	 */
	
	public void modifyRiskBill(RiskBill riskbill)throws BizAppException;
	
	/**
	 * 风险票检查<br>
	 * 查询规则：<br>
	 * 		1：票号全匹配，多张使用IN。<br>
	 * 		2：风险票数据中票据到期日不为空<br>
	 * 					&nbsp;&nbsp; 使用到期日晚于当前营业日<br>
	 * 		3：风险票数据票据到期日为空<br>
	 * 					&nbsp;&nbsp; 使用风险票创建日期晚于营业日向前推6个月的日期<br>
	 * @param bills	风险票数组
	 * @return	查询到的风险票
	 * @throws BizAppException	系统异常
	 */
	public List<RiskBill> getRiskBillByBills(List<BillInfoDTO> bills)throws BizAppException;

}
