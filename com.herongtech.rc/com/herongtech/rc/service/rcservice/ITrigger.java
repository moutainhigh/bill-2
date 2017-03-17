package com.herongtech.rc.service.rcservice;

import java.sql.SQLException;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;

public interface ITrigger {

	/**
	 * 034回调
	 * @param rgctBill
	 * @param isSuccess
	 * @throws BizAppException
	 */
	void transFor034execute(RgctBill rgctBill) throws BizAppException;
	
	
	/**
	 * 申请的033回调
	 * @param rgctBill
	 * @param isSign
	 * @param isSuccess
	 * @throws BizAppException
	 * @throws Exception 
	 */
	void applyFor033execute(RgctBill rgctBill,boolean isSuccess) throws BizAppException, Exception;
	
	/**
	 * 032的033回调
	 * @param rgctBill
	 * @param isSign
	 * @param isSuccess
	 * @throws BizAppException
	 * @throws Exception 
	 */
	void cancelFor033execute(RgctBill rgctBill,boolean isSuccess) throws BizAppException, Exception;
	
	/**
	 * 031签收-033的回调
	 * @param rgctBill
	 * @param isSign
	 * @param isSuccess
	 * @throws BizAppException
	 * @throws SQLException 
	 * @throws Exception 
	 */
	void signFor033execute(RgctBill rgctBill, String isSign,boolean isSuccess) throws  Exception;
	
	/**
	 * 申请方收到转发的031
	 * @param rgctBill
	 * @param isSign
	 * @param isSuccess
	 * @throws BizAppException
	 * @throws Exception 
	 */
	void transFor031execute(RgctBill rgctBill, String isSign) throws BizAppException, Exception;
	
	
	
	/**
	 * 签收方收到转发的032
	 * @param rgctBill
	 * @param isSign
	 * @param isSuccess
	 * @throws BizAppException
	 */
	void transFor032execute(RgctBill rgctBill) throws BizAppException;
	
	/**
	 * 签收方收到转发的035
	 * @param rgctBill
	 * @throws BizAppException
	 */
	void transFor035execute(RgctBill rgctBill) throws BizAppException;

}
