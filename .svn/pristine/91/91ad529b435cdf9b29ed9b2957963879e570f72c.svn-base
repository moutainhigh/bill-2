/********************************************
 * 文件名称: IRcBillNotifyService.java
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
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
/**
 * 提示收票
 * @author vincent
 *
 */
public interface IRcBillNotifyService extends IRcBaseService {

	/**
	 * 提示收票申请查询
	 * @param sb
	 * @param page
	 * @return	String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryIssuance(RcBaseSearchBean sb) throws BizAppException;
	/**
	 * 提示收票
	 * @param bill	票据信息
	 * @throws BizAppException
	 */
	public void issuanceRequest(RgctBill bill) throws BizAppException;
	/**
	 * 查询提示收票待确认
	 * @param sb	SearchBean
	 * @param page	Page
	 * @return	登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryIssuanceWaitConfirm(RcBaseSearchBean sb) throws BizAppException;
	/**
	 * 撤销申请
	 * @param bill	票据信息
	 * @throws BizAppException
	 */
	public void cancelRequest(RgctBill bill) throws BizAppException;
	/**
	 * 提示收票签收
	 * @param bill	票据信息
	 * @throws BizAppException
	 */
	public void payeeSign(RgctBill bill) throws BizAppException;
	/**
	 * 提示收票拒绝
	 * @param bill	票据信息
	 * @throws BizAppException
	 */
	public void payeeReject(RgctBill bill) throws BizAppException;
	/**
	 * 查询可撤销提示收票票据
	 * @param sb	SearchBean
	 * @param page	Page
	 * @return	登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryBillWaitSign(RcBaseSearchBean sb) throws BizAppException;
	

	
}
