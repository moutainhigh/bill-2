package com.herongtech.rc.service.rcservice;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IRcRebuyService extends IRcBaseService{
	/**
	 * 手工转入受理录入
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill inputSignRebuy(RgctBill bill) throws BizAppException;
	
	/**
	 * 转入签收
	 * @param bill
	 * @throws ServiceException
	 */
	public void rebuyEndorseSign(RgctBill bill) throws BizAppException;
	
	/**
	 * 拒绝转入背书
	 * 
	 * @param bill 登记中心票据信息
	 * @throws ServiceException
	 */
	public void rejectRebuyEndorse(RgctBill bill) throws BizAppException;
	
}
