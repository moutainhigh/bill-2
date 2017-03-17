package com.herongtech.console.service.busiservice.disc;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.service.common.audit.interfaces.IAuditTrigger;


public class DiscAuditTrigger implements IAuditTrigger {
    
    private IDiscService discService;

    @Override
    public void firstStationAdopt(String prodNo, String batchId)
            throws Exception {
    	List<DiscBillInfo> billList = discService.getDiscBillForDiscIdAndStatus(batchId, StatusUtils.queryStatus("DiscAuditController", "auditing", null)[0]);
    	for(DiscBillInfo bill : billList){
    		bill.setOperStatus(StatusUtils.handleStatus("DiscAuditController", "auditing", null,bill.getOperStatus()));
    		discService.modDiscBillInfo(bill);
    	}
    }

    @Override
    public void lastStationAdopt(String prodNo, String batchId)
            throws Exception {
    	List<DiscBillInfo> billList = new ArrayList<DiscBillInfo>();
    	String status[] = StatusUtils.queryStatus("DiscAuditController", "auditComplete", null);
    	for (String string : status) {
    		billList.addAll(discService.getDiscBillForDiscIdAndStatus(batchId, string));
		}
    	for(DiscBillInfo bill : billList){
    		discService.auditSubmit(bill.getDiscmxId(), "1", " ");
    	}
    }

    @Override
    public void anyStationReject(String prodNo, String batchId)
            throws Exception {
    	List<DiscBillInfo> billList = new ArrayList<DiscBillInfo>();
    	String status[] = StatusUtils.queryStatus("DiscAuditController", "audit", "0");
    	for (String string : status) {
    		billList.addAll(discService.getDiscBillForDiscIdAndStatus(batchId, string));
		}
    	for(DiscBillInfo bill : billList){
    		discService.auditSubmit(bill.getDiscmxId(), "0", " ");
    	}
    }

    
    public void setDiscService(IDiscService discService) {
        this.discService = discService;
    }
    

}
