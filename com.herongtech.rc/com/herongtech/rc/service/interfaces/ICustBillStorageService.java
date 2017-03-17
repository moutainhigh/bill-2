package com.herongtech.rc.service.interfaces;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.CustBillStorage;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;

public interface ICustBillStorageService {	/**
	 * 出票
	 * 
	 * @param billStorage
	 * @throws ServiceException
	 */
	public void saveCustBillStorage(CustBillStorage custBillStorage)throws BizAppException;

	/**
	 * 处理034报文
	 * 
	 * @param billStorage
	 * @throws ServiceException
	 */

	public void saveCustBillStorage034(CustBillStorage billStorage) throws BizAppException;
	/**
	 * 撤票、承兑、质押、解质押、结清
	 * 
	 * @param rgctId
	 * @param balanceType
	 * @throws ServiceException
	 */
	public void updateCustBillStorage(String rgctId, String busiType, RgctBill rgctBill) throws BizAppException;
	/**
	 * 余额查询
	 * 
	 * @param billStorageQueryBean
	 * @param page
	 * @return page
	 * @throws ServiceException
	 */
	String  serchCustBillStorageBalance(RcBaseSearchBean searchBean)
			throws BizAppException;
	
}
