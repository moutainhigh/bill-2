package com.herongtech.console.service.busiservice.buyback;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.bean.BuybackSearchBean;
import com.herongtech.console.service.common.audit.interfaces.IAuditTrigger;

public class BuybackAuditTrigger implements IAuditTrigger {
    
    private IBuybackService buybackService;

    @Override
    public void firstStationAdopt(String prodNo, String batchId)
            throws Exception {
    	List<BuybackBillInfo> billList = buybackService.getBuybackBillForBuybackIdAndStatus(batchId, StatusUtils.queryStatus("BuybackAuditController", "auditing", null)[0]);
    	for(BuybackBillInfo bill : billList){
    		bill.setOperStatus(StatusUtils.handleStatus("BuybackAuditController", "auditing", null,bill.getOperStatus()));
    		buybackService.updateBuybackBillInfo(bill);
    	}
    }

    @Override
    public void lastStationAdopt(String prodNo, String batchId)
            throws Exception {
    	List<BuybackBillInfo> billList = new ArrayList<BuybackBillInfo>();
    	BuybackSearchBean query=new BuybackSearchBean();
    	query.setBuybackId(batchId);
    	String status[] = StatusUtils.queryStatus("BuybackAuditController", "auditComplete", null);
    	for (String string : status) {
    		billList.addAll(buybackService.getBuybackBillForBuybackIdAndStatus(batchId, string));
		}
    	for(BuybackBillInfo bill : billList){
    		buybackService.auditSubmitForEntity(bill.getBuybackmxId(), "1", query);
    	}
    }

    @Override
    public void anyStationReject(String prodNo, String batchId)
            throws Exception {
    	List<BuybackBillInfo> billList = new ArrayList<BuybackBillInfo>();
    	BuybackSearchBean query=new BuybackSearchBean();
    	query.setBuybackId(batchId);
    	String status[] = StatusUtils.queryStatus("BuybackAuditController", "buybackAudit", "0");
    	for (String string : status) {
    		billList.addAll(buybackService.getBuybackBillForBuybackIdAndStatus(batchId, string));
		}
    	for(BuybackBillInfo bill : billList){
    		buybackService.auditSubmitForEntity(bill.getBuybackmxId(), "0", query);
    	}
    }

    
    public void setBuybackService(IBuybackService buybackService) {
        this.buybackService = buybackService;
    }
    

}
