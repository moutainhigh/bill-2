package com.herongtech.rc.service.rcservice;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IRcAssuranceService  extends IRcBaseService{
	
	/**
	 * 查询可保证票据服务
	 * @param sb	RcBaseSearchBean
	 * @param page
	 * @param assureType	保证类别
	 * @return	登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryAssure(RcBaseSearchBean sb) throws BizAppException;
	
	/**
	 * 保证申请
	 * @param bill 登记中心票据列表 RgctBill
	 * @param info 保证信息
	 */
	public void assuranceApply(RgctBill bill) throws BizAppException;
	
	/**
	 * 查询保证待签收的票据
	 * @param sb  RcBaseSearchBean
	 * @param page
	 * @return 登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryAssuWaitSign(RcBaseSearchBean sb)throws BizAppException;
	
	/**
	 * 撤销保证
	 */
	public void cancelAssure(RgctBill bill) throws BizAppException;
	
	/**
	 * 保证签收
	 * @param bill
	 * @throws BizAppException
	 */
	public void assuSign(RgctBill bill) throws BizAppException;
	
	/**
	 * 保证拒绝签收
	 * @param bill
	 * @param info
	 * @return
	 * @throws BizAppException
	 */
	public void registerAssuReject(RgctBill bill) throws BizAppException;
	
}
