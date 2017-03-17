package com.herongtech.console.service.busiservice.rebuy;

import java.util.List;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.service.common.audit.interfaces.IAuditTrigger;

public class RebuyAuditTrigger implements IAuditTrigger {

	private IRebuyService rebuyService;
	
	public IRebuyService getRebuyService() {
		return rebuyService;
	}

	public void setRebuyService(IRebuyService rebuyService) {
		this.rebuyService = rebuyService;
	}

	@Override
	public void firstStationAdopt(String prodNo, String batchId)
			throws Exception {
		String[] preStatus = StatusUtils.queryStatus("RebuyAuditController", "auditing", null);
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListByRebuyIdAndStatus(batchId, preStatus);
		for(RebuyBillInfo bill:billList){
			String afterStatus = StatusUtils.handleStatus("RebuyAuditController", "auditing", null,bill.getOperStatus());
			bill.setOperStatus(afterStatus);
			rebuyService.modifyRebuyBillInfo(bill);
		}
	}

	@Override
	public void lastStationAdopt(String prodNo, String batchId)
			throws Exception {
		String[] preStatus = StatusUtils.queryStatus("RebuyAuditController", "auditComplete", null);
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListByRebuyIdAndStatus(batchId, preStatus);
		for(RebuyBillInfo bill:billList){
			rebuyService.auditSubmit(bill.getRebuymxId(), batchId, IConstants.YES, " ");
		}
	}

	@Override
	public void anyStationReject(String prodNo, String batchId)
			throws Exception {
		String[] preStatus = StatusUtils.queryStatus("RebuyAuditController", "auditSubmit", IConstants.NO);
		List<RebuyBillInfo> billList = rebuyService.getRebuyBillListByRebuyIdAndStatus(batchId, preStatus);
		for(RebuyBillInfo bill:billList){
			rebuyService.auditSubmit(bill.getRebuymxId(), batchId, IConstants.NO, " ");
		}
	}

}
