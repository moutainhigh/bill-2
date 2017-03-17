/**
 * Copyright (c) 1995-2011 Hundsun Technologies Inc. All Rights Reserved
 * Create on Jan 10, 2012
 * Author:zcz;
 */

package com.herongtech.console.service.interfaces;

import java.util.List;
import java.util.Map;

import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.FacBean;

public interface IFmsAgentService {

	/**
	 * 第三方额度批量占用
	 * @param source
	 * @param list
	 * @param info
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, FacBean> acceptorBankCredit(Object apply,String source,List<?> list, UserLoginfo info) throws Exception;
	/**
	 * 第三方额度批量释放
	 * @param list
	 * @param info
	 * @param source
	 * @param billList
	 * @param facType1
	 */
	public Map<String, FacBean> facReleaseByBillBatch(Object apply,List<?> list, UserLoginfo info) throws Exception;
}
