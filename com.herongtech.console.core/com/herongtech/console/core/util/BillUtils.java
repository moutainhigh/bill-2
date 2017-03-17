/********************************************
 * 文件名称: BrowserUtils.java
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
package com.herongtech.console.core.util;

import com.herongtech.console.cache.DictCache;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.rc.domain.bean.RgctBill;

/**
 * 票据工具类
 */
public class BillUtils {
   	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	/**
	 * 产生票据唯一序列号
	 * 
	 * @param billNo 票号：电子10位，实物12位
	 * @param acptDt 出票日：yyyyMMdd
	 * @param bankNo 付款行12位行号
	 * @param isElec 是否电子
	 * @param isBankBill 是否银票
	 * @return 票据唯一序列号
	 */
	public static String generateEbsNo(String billNo, String acptDt,
			String bankNo, boolean isElec, boolean isBankBill) {
		StringBuffer sb = new StringBuffer(50);
		sb.append(bankNo.substring(0, 3)); // 联行号前3位，行分类
		
		if (isBankBill) { // 银票
			sb.append(IDict.K_BILL_TYPE.K_BANK_BILL);
		} else { // 商票
			sb.append(IDict.K_BILL_TYPE.K_CORP_BILL);
		}

		if (isElec) { // 电子
			sb.append(acptDt.substring(0, 4));
			sb.append(billNo);
		} else { // 实物
			// 联行号前3位：313、314、401、402，可能需要使用后四位城市编码区分
			sb.append(acptDt);
			sb.append(billNo.toUpperCase());
		}

		return sb.toString();
	}
	
	
	/**
	 * 根据票据状态代码获取票据状态名称
	 * @param statusCode
	 * @return	票据状态名称
	 */
	public static String getBillStatusNameByCode(String statusCode) {
		//ICodeDefineUtil codeUtil = CodeDefineFactory.getCodeDefineUtil();
		String codeName = null;
		if(statusCode != null && statusCode.length() > 0){
			String stageName = DictCache.getInstance().getItemValue(IDict.K_F001.NAME, statusCode.substring(0,statusCode.indexOf("_"))); //codeUtil.getCodeName(statusCode.substring(0,statusCode.indexOf("_")), "F001");
			String statusName = DictCache.getInstance().getItemValue(IDict.K_F002.NAME, statusCode.substring(statusCode.indexOf("_") + 1,statusCode.length())); //codeUtil.getCodeName(statusCode.substring(statusCode.indexOf("_") + 1,statusCode.length()), "F002");
			codeName = stageName.trim() + statusName.trim();
		}
		return codeName;
	}
}