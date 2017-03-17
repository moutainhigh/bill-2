/********************************************
 * 文件名称: RgctValidateUtils.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 2016-8-10 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.service.rcservice;

import org.springframework.util.Assert;

import com.herongtech.console.core.constant.IDict;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;

public class RgctValidateUtils {

	public static void checkElecRequest(final RgctBill bill ) throws BizAppException{
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			return;
		}
		validateRequestHist(bill.getHist());
	}
	
	public static void checkElecResponse(final RgctBill bill ) throws BizAppException{
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			return;
		}
		validateResponseHist(bill.getHist());
	}
	
	public static void checkElecCancel(final RgctBill bill ) throws BizAppException{
		if(IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
			return;
		}
		validateCancellationHist(bill.getHist());
	}
	
	
	private static void validateRequestHist(final RgctBillHist hist) throws BizAppException{
		try {
			Assert.notNull(hist.getFromAcctNo(),"发起方帐号为空");
			Assert.notNull(hist.getFromBankNo(),"发起方行号为空");
			Assert.notNull(hist.getFromName(),"发起方名称为空");
			Assert.notNull(hist.getFromOrgcode(),"发起方组织机构代码为空");
			Assert.notNull(hist.getFromRole(),"发起方角色为空");
			Assert.notNull(hist.getFromSign(),"发起方电子签名为空");
		} catch (IllegalArgumentException e) {
			throw new BizAppException("电子票据人行报文申请发送校验："+e.getMessage(),e);
		}
	}
	
	private static void validateResponseHist(final RgctBillHist hist) throws BizAppException{
		try {
			Assert.notNull(hist.getToOrgcode(),"签收方组织机构代码为空");
			Assert.notNull(hist.getToRole(),"签收方角色为空");
			Assert.notNull(hist.getSignerSign(),"签收方电子签名为空");
			Assert.notNull(hist.getSignDt(),"签收日期为空");
		} catch (IllegalArgumentException e) {
			throw new BizAppException("电子票据人行报文签收发送校验："+e.getMessage(),e);
		}
	}
	
	private static void validateCancellationHist(final RgctBillHist hist) throws BizAppException{
		try {
			Assert.notNull(hist.getFromSign(),"撤销申请电子签名为空");
			Assert.notNull(hist.getEndorseDt(),"撤销日期为空");
		} catch (IllegalArgumentException e) {
			throw new BizAppException("电子票据人行报文撤销发送校验："+e.getMessage(),e);
		}
	}
}
