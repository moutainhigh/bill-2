package com.herongtech.rc.service.rcservice;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;

public interface IRcPresentationService  extends IRcBaseService{
	
	/**
	 * 查询可发托的票据(银行端查票)
	 * @param sb
	 * @return
	 * @throws BizAppException
	 */
	public List<RgctBillData> queryPrecollectBill(RcBaseSearchBean sb,Page page) throws BizAppException ;
	
	/**
	 * 查询可撤销票据 (包括准签收和待签收的票据)
	 * @param sb	SearchBean
	 * @param page	Page
	 * @return	登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryReversibleBill(RcBaseSearchBean sb) throws BizAppException;
	/**
	 * 手工录入收款结果
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws BizAppException
	 */
	public RgctBill inputGether(RgctBill bill) throws BizAppException;
	
	/**
	 * 撤销提示付款背书
	 * 
	 * @param bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void cancelPayEndorse(RgctBill bill) throws BizAppException;
	
	/**
	 * 撤销逾期提示付款
	 * @param  bill 票据信息
	 * @throws BizAppException
	 */
	public void cancelOverdue(RgctBill bill) throws BizAppException;
	
	/**
	 * 查询待托收票据(网银)
	 * @param sb
	 * @param pg
	 * @return
	 * @throws BizAppException
	 */
	public String queryPrecollectBillClient(RcBaseSearchBean sb) throws BizAppException;
	/**
	 * 提示付款背书
	 * 
	 * @param bill	票据信息
	 * @throws BizAppException
	 */
	public void payEndorse(RgctBill bill) throws BizAppException;
	
	/**
	 * 逾期提示付款
	 * @param bill
	 * @throws BizAppException
	 */
	public void regOverdue(RgctBill bill) throws BizAppException;
	/**
	 * 查询提示付款待签收
	 * @param sb SearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws ServiceException
	 */
	public String queryFreeWaitSign(RcBaseSearchBean sb) throws BizAppException;
	
	/**
	 * 收到托收签收拒绝
	 * 
	 * @param bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void rejectCollect(RgctBill bill) throws BizAppException;
	
	/**
	 * 付款签收
	 * @param bill
	 * @throws BizAppException
	 */
	public void collectSign(RgctBill bill) throws BizAppException;
	
	/**
	 * 实票撤销签收
	 * @param bill
	 * @return
	 * @throws BizAppException
	 */
	public RgctBill cancelSign(RgctBill bill) throws BizAppException;
	
	/**
	 * 逾期提示付款签收
	 * @param  bill 票据信息
	 * @throws BizAppException
	 */
	public void signOverdue(RgctBill bill) throws BizAppException;
	
	/**
	 * 逾期提示付款拒绝签收
	 * @param  bill 票据信息
	 * @throws BizAppException
	 */
	public void overduePresentationNo(RgctBill bill) throws BizAppException;
	
	
}
