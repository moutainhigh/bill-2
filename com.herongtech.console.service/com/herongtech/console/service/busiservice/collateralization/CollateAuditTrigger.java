package com.herongtech.console.service.busiservice.collateralization;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
import com.herongtech.console.service.common.audit.interfaces.IAuditTrigger;


public class CollateAuditTrigger implements IAuditTrigger {
    
    private ICollateralizationService collateService;

    @Override
    public void firstStationAdopt(String prodNo, String batchId)
            throws Exception {
    	List<SaveBillInfo> billList = collateService.getSaveBillForSaveIdAndStatus(batchId, StatusUtils.queryStatus("CollateralizationAuditController", "auditing", null)[0]);
    	for(SaveBillInfo bill : billList){
    		bill.setOperStatus(StatusUtils.handleStatus("CollateralizationAuditController", "auditing", null,bill.getOperStatus()));
    		collateService.modiSaveBillInfo(bill);
    	}
    }

    @Override
    public void lastStationAdopt(String prodNo, String batchId)
            throws Exception {
    	List<SaveBillInfo> billList = new ArrayList<SaveBillInfo>();
    	SaveSearchBean query = new SaveSearchBean();
    	query.setSaveId(batchId);
    	String status[] = StatusUtils.queryStatus("CollateralizationAuditController", "auditComplete", null);
    	for (String string : status) {
    		billList.addAll(collateService.getSaveBillForSaveIdAndStatus(batchId, string));
		}
    	for(SaveBillInfo bill : billList){
    		collateService.auditSubmit(bill.getSavemxId(), "1", " ", query);
    	}
    }

    @Override
    public void anyStationReject(String prodNo, String batchId)
            throws Exception {
    	List<SaveBillInfo> billList = new ArrayList<SaveBillInfo>();
    	SaveSearchBean query = new SaveSearchBean();
    	query.setSaveId(batchId);
    	String status[] = StatusUtils.queryStatus("CollateralizationAuditController", "audit", "0");
    	for (String string : status) {
    		billList.addAll(collateService.getSaveBillForSaveIdAndStatus(batchId, string));
		}
    	for(SaveBillInfo bill : billList){
    		collateService.auditSubmit(bill.getSavemxId(), "0", " ", query);
    	}
    }

    
    public void setCollateService(ICollateralizationService collateService) {
        this.collateService = collateService;
    }
    

}
