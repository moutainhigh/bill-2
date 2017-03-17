/********************************************
 * 文件名称: RequestCheckTools.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yy
 * 开发时间: 2016-7-15 下午13:14:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.core.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.exception.impl.BizAppException;

public class RequestCheckTools {

	/**
	 * 检查字段非空，必输项
	 */
	public static void checkIsNull(String field, String str) throws BizAppException {
		if (str == null || "".equals(str)) {
			throw new BizAppException(IErrorNo.BBSP0008, "字段[" + field + "]为必输项");
		}
	}
	
	public static void checkLoanSystem(String field, String loanSystem) throws BizAppException {
		if (loanSystem == null || "".equals(loanSystem)) {
			throw new BizAppException(IErrorNo.BBSP0008, "字段[" + field + "]为必输项");
		}
		if(!"1".equals(loanSystem) && !"2".equals(loanSystem)){
			throw new BizAppException(IErrorNo.BBSP0017, "字段[" + field + "]输入参数非法");
		}
	}
	
	public static void checkIsNull(String field, Object str) throws BizAppException {
		if (str == null || "".equals(str)) {
			throw new BizAppException(IErrorNo.BBSP0008, "字段[" + field + "]为必输项");
		}
	}
	
	public static void checkIsNull(String field, List list) throws BizAppException {
		if (list == null || list.isEmpty()) {
			throw new BizAppException(IErrorNo.BBSP0008, "字段[" + field + "]为必输项");
		}
	}

	/**
	 * 检查字段值必须大于0的整数
	 */
	public static void checkIntNull(String field, int intvalue)
			throws BizAppException {
		if (intvalue <= 0) {
			throw new BizAppException("字段[" + field + "]的为必输项目，且必须为大于0的整数");
		}
	}

	/**
	 * 检查字段值必须大于0的整数
	 */
	public static void checkIntNull(String field, long longvalue)
			throws BizAppException {
		if (longvalue <= 0) {
			throw new BizAppException("字段[" + field + "]的为必输项目，且必须为大于0的整数");
		}
	}

	/**
	 * 检查字段值必须大于0的整数
	 */
	public static void checkIntNull(String field, double longvalue)
			throws BizAppException {
		if (longvalue <= 0) {
			throw new BizAppException("字段[" + field + "]的为必输项目，且必须为大于0的整数");
		}
	}

	public static void checkDrcr(String field, String arg) throws BizAppException{
		if(!arg.equals("2")){
			throw new BizAppException("字段[" + field + "]输入有误，该业务只处理贷方");
		}

	}
	public static void checkIsDayEnd() throws BizAppException{
		/*IEcdsStatusService ecdsStatus= (IEcdsStatusService) SourceTemplate.getBean("EcdsStatusService");
		RgctEcdsStatus ecds = ecdsStatus.getRgctEcdsStatusByKey(MsgConstants.ECDS_DAYEND);
		if("1".equals(ecds.getPvalue())){
			throw new BizAppException("票据系统正在日终处理，已关闭，！");
		}*/

	}

	public static void checkBankNo(String field, String inBankNo) {
		/*checkIsNull(field,inBankNo);
		if(inBankNo.length() != 12){
			throw new BizAppException("行号必须为12位");
		}*/
	}
	
	public static void checkProtocalNo(String field, String protocalNo) throws BizAppException {
		checkIsNull(field, protocalNo);
		if(protocalNo.length() > 16){
			throw new BizAppException(IErrorNo.BBSP0045, "字段[" + field + "]为必输项");
		}
	}
	

	/**
	 * 前台流水号校验
	 * @param transactionId
	 * @param returnCode
	 * @return
	 */
	public static void checkTransactionId(String transactionId) throws BizAppException{
		if(StringUtils.isEmpty(transactionId)){
			throw new BizAppException(IErrorNo.BBSP0045);
		}else if(transactionId.length() != 32){
			throw new BizAppException(IErrorNo.BBSP0045);
		}
		
	}
	
	/**
	 * 验证输入的select选项值是否正确
	 * @param fieldName 字段名称
	 * @param fieldValue 输入的字段值
	 * @param trueFieldValues 正确的值数组
	 * @param trueValueDescription 正确的值描述
	 */
	public static void checkSelectOptionIsEligibility(String fieldName, String fieldValue, 
			String[] trueFieldValues, String trueValueDescription) throws BizAppException{
		
		//是否匹配到正确的值
		boolean isTrue = false;
		
		if(fieldValue==null){
			
			throw new BizAppException(IErrorNo.BBSP0008, "字段[" + fieldName + "]为必输项");
		}
		
		for(int i = 0; i < trueFieldValues.length; i++){
			if(fieldValue.equals(trueFieldValues[i])){
				isTrue = true;
				break;
			}
		}
		
		if(!isTrue){
			throw new BizAppException(IErrorNo.BBSP0061, fieldValue + fieldName + trueValueDescription);
		}
	}
}
