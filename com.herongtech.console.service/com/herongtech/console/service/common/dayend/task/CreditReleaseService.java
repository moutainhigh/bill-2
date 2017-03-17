package com.herongtech.console.service.common.dayend.task;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.DayEndBaseService;
import com.herongtech.console.service.common.fac.IFacRenewService;
import com.herongtech.context.Context;
import com.herongtech.log.CommonLog;
/**
 * 额度释放任务
 * @author fqz
 *
 */
public class CreditReleaseService extends DayEndBaseService {

	@Override
	public String getServiceId() {
		return "DAYEND-creditRelease";
	}

	@Override
	protected void action(Context arg0) throws Exception {
		CommonLog.getCommonLogCache().infoMessage("批量额度释放任务开始=======");
		IFacRenewService facRenewService = ServiceFactory.getFacRenewService();
		facRenewService.facBatchRelease();
		CommonLog.getCommonLogCache().infoMessage("批量额度释放任务完成=======");
	}

	@Override
	public String getTransName() {
		return "额度释放";
	}

	@Override
	public String getTransVersion() {
		return "1.0";
	}

}
