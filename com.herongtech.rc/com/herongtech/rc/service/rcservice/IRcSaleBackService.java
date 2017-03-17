package com.herongtech.rc.service.rcservice;

import java.sql.SQLException;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;


public interface IRcSaleBackService extends IRcBaseService{
	
	/**
	 * 返售背书登记
	 * 
	 * @param bill 登记中心票据信息
	 * @throws ServiceException
	 */
	public void regBackEndorse(RgctBill bill) throws BizAppException;
	
	/**
	 * 返售方撤销返售背书登记
	 * 
	 * @param bill 登记中心票据信息
	 * @throws ServiceException
	 */
	public void cancelBackEndorse(RgctBill bill) throws BizAppException;
	
	
	/**
	 * 赎回方同意返售背书登记
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws BizAppException 
	 * @throws SQLException 
	 * @throws ServiceException
	 */
	public RgctBill regAgreeEndorse(RgctBill bill) throws BizAppException, SQLException;
	
}
