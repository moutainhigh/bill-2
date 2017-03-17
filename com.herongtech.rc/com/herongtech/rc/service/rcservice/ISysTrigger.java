package com.herongtech.rc.service.rcservice;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;

public interface ISysTrigger {

	/**
	 * 系统通知类回调
	 * @param rgctBill
	 * @param isSuccess
	 * @throws BizAppException
	 */
	void execute(RgctBill rgctBill) throws BizAppException;

}
