/********************************************
 * 文件名称: IRcAcptBillService.java
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
import java.sql.SQLException;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IRcAcptBillService extends IRcBaseService {
	
	/**
	 * 查询可操作提示承兑票据
	 * @param sb	SearchBean
	 * @param page	Page
	 * @return	登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException 
	 */
	public String queryAcpt(RcBaseSearchBean sb) throws BizAppException ;
	/**
	 * 提示承兑
	 * @param bill	票据信息
	 * @throws BizAppException 
	 */
	public void acceptanceRequest(RgctBill bill) throws BizAppException ;
	/**
	 * 查询提示承兑待确认
	 * @param sb	SearchBean
	 * @param page	Page
	 * @return	登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException 
	 */
	public String queryAcceptanceWaitConfirm(RcBaseSearchBean sb) throws BizAppException ;
	/**
	 * 撤销申请
	 * @param bill	票据信息
	 * @throws BizAppException 
	 */
	public void cancelRequest(RgctBill bill) throws BizAppException ;
	/**
	 * 查询提示承兑待签收
	 * @param sb	SearchBean
	 * @param page	Page
	 * @return	登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException 
	 */
	public String queryAcceptNotifyWaitSign(RcBaseSearchBean sb) throws BizAppException ; 
	/**
	 * 准签收
	 * @param bill	票据信息
	 * @return	RgctBill  票据信息
	 * @throws BizAppException 
	 */
	public RgctBill preSign(RgctBill bill) throws BizAppException ; 
	/**
	 * 撤消准签收
	 * @param bill	票据信息
	 * @return	RgctBill  票据信息
	 * @throws BizAppException 
	 */
	public RgctBill cancelPreSign(RgctBill bill) throws BizAppException ;
	/**
	 * 提示承兑签收
	 * @param bill	票据信息
	 * @throws BizAppException 
	 */
	public void acptSign(RgctBill bill) throws BizAppException ; 
	/**
	 * 提示承兑拒绝
	 * @param bill	票据信息
	 * @throws BizAppException 
	 */
	public void acptBack(RgctBill bill) throws BizAppException ; 
	
	/**
	 * 查询可撤销票据 (包括准签收和待签收的票据)
	 * @param sb	SearchBean
	 * @param page	Page
	 * @return	登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws ServiceException
	 */
	public String queryReversibleBill(RcBaseSearchBean sb) throws BizAppException ; 
}
