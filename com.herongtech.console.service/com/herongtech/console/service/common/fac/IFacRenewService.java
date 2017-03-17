package com.herongtech.console.service.common.fac;

import com.herongtech.exception.impl.BizAppException;

public interface IFacRenewService {
	/**
	 * 批量释放额度
	 * @throws BizAppException
	 */
	public void facBatchRelease() throws BizAppException;
}
