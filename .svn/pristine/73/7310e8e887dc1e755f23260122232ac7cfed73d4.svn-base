package com.herongtech.console.service.busiservice.sale;

import java.util.List;

import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.bean.SaleSearchBean;
import com.herongtech.console.service.common.audit.interfaces.IAuditTrigger;
import com.herongtech.console.web.busicontroller.common.SaleCodeConst;

public class SaleAuditTrigger implements IAuditTrigger {
	private ISaleService saleService;
	public void setSaleService(ISaleService saleService) {
		this.saleService = saleService;
	}

	@Override
	public void firstStationAdopt(String prodNo, String batchId) throws Exception {
		UserLoginfo userLogonInfo= ResourceUtil.getSessionLoginfo();
		SaleSearchBean searchBean = new SaleSearchBean();
		searchBean.setDfaultSrchTbAliasName("bill");
		searchBean.setSaleId(batchId);
		searchBean.setOperStatus(StatusUtils.queryStatus("SaleAuditController", "auditing", null)[0]);
		List<SaleBillInfo> saleBillList = saleService.getSaleBillListBySearchBean(searchBean);
		for(SaleBillInfo saleBill : saleBillList){
			saleBill.setOperStatus(StatusUtils.handleStatus("SaleAuditController", "auditing", null, saleBill.getOperStatus()));
			saleBill.setAuditOperNo(userLogonInfo.getUserNo());
    		saleBill.setAuditTellerDt(DateTimeUtil.getWorkdayString());
    		saleBill.setAuditTellerTime(DateTimeUtil.get_hhmmss_time());
			saleService.modifySaleBill(saleBill);
		}
	}

	@Override
	public void lastStationAdopt(String prodNo, String batchId) throws Exception {
		this.submitSaleAudit(SaleCodeConst.AUDIT_YES,batchId);
	}

	@Override
	public void anyStationReject(String prodNo, String batchId) throws Exception {
		this.submitSaleAudit(SaleCodeConst.AUDIT_NO,batchId);
	}
	
	private void submitSaleAudit(String auditResult,String batchId) throws Exception {
		SaleSearchBean searchBean = new SaleSearchBean();
		searchBean.setDfaultSrchTbAliasName("bill");
		searchBean.setSaleId(batchId);
		searchBean.setOperStatus(StatusUtils.queryStatus("SaleAuditController", "auditing", null)[0]);
		/*searchBean.setStatusArray(StatusUtils.queryStatus("SaleApplyController", "billManege", null));
		searchBean.addProperty2TableObj("operStatus", "bill");
		searchBean.addProperty2TableObj("statusArray", "bill");
		searchBean.addSpecialOpertion("operStatus",BaseSearchBean.IN);
		searchBean.addSqlPropretyMapping("statusArray", "operStatus");*/

		List<SaleBillInfo> saleBillList = saleService.getSaleBillListBySearchBean(searchBean);
		
		String[] salemxIds = {};
		for(int i=0;i<saleBillList.size();i++){
			salemxIds[i] = saleBillList.get(i).getSalemxId();
		}
		saleService.submitSaleAudit(salemxIds, auditResult, "意见");
	}

}
