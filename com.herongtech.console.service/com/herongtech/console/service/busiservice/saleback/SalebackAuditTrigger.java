package com.herongtech.console.service.busiservice.saleback;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.saleback.bean.SaleBackSearchBean;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.domain.saleback.dao.SalebackBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditTrigger;
import com.herongtech.console.service.interfaces.ISaleBackService;

/**
 * @author 李江涛
 *纸票返售审批回调
 */
public class SalebackAuditTrigger implements IAuditTrigger{

	private ISaleBackService service;
	@Override
	public void firstStationAdopt(String prodNo, String batchId)
	throws Exception {
		List<SalebackBillInfo> billList = service.getSaveBillForSaveIdAndStatus(batchId, StatusUtils.queryStatus("SaleBackAuditController", "auditing", null)[0]);
		for(SalebackBillInfo bill : billList){
			bill.setOperStatus(StatusUtils.handleStatus("SaleBackAuditController", "auditing", null, bill.getOperStatus()));
			service.modifySalebackBillInfo(bill);
		}
		
	}
	@Override
	public void anyStationReject(String prodNo, String batchId)
			throws Exception {
		SalebackBillInfoDao billdao = new SalebackBillInfoDao();
		ISaleBackService salebackservice = ServiceFactory.getSaleBackService();
		UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
		List<SalebackBillInfo> billList = new ArrayList<SalebackBillInfo>();
    	SaleBackSearchBean query = new SaleBackSearchBean();
    	String status[] = StatusUtils.queryStatus("SaleBackAuditController", "audited", null);
    	query.setSalebackId(batchId);
    	query.setOpers(status);
    	billList = salebackservice.getauditbilllist(query, new Page());
    	String[] salebackIds = {};
    	for (int i = 0; i < billList.size(); i++) {
    		SalebackBillInfo salebackbill = billList.get(i);
    		salebackIds[i] = salebackbill.getSalebackmxId();
		}
    	service.submitSaleAudit(salebackIds, "0", "意见");
		
	}


	@Override
	public void lastStationAdopt(String prodNo, String batchId)
			throws Exception {
		SalebackBillInfoDao billdao = new SalebackBillInfoDao();
		ISaleBackService salebackservice = ServiceFactory.getSaleBackService();

		List<SalebackBillInfo> billList = new ArrayList<SalebackBillInfo>();
    	SaleBackSearchBean query = new SaleBackSearchBean();
    	String status[] = StatusUtils.queryStatus("SaleBackAuditController", "audited", null);
    	query.setSalebackId(batchId);
    	query.setOpers(status);
    	billList = salebackservice.getauditbilllist(query, new Page());
    	
    	String[] salebackmxids={};
    	for (int j = 0; j < billList.size(); j++) {
    		salebackmxids[j]=billList.get(j).getSalebackmxId();
			
		}
    	
    	service.submitSaleAudit(salebackmxids, "1", "意见");
		
	}
	
	public void setCollateService(ISaleBackService service) {
        this.service = service;
    }

}
