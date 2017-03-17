package com.herongtech.console.service.common.entitydraftregister;

import com.herongtech.exception.impl.BizAppException;

public interface IEntityDraftRegisterService {

	/**
	 * 批量纸票登记
	 * @throws BizAppException
	 */
	public void entityRegister() throws BizAppException;
}
