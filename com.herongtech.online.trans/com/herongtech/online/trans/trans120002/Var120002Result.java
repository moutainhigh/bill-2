package com.herongtech.online.trans.trans120002;

import java.util.List;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.xmlchannel.pkg.ProResult;

public class Var120002Result {
	
	private String totalRows;
    private String  retNum;
    private String currentPage;
    private ProResult proResult;
    private List<RgctBillInfo> billList;
    private List<Var120002InfoBean> result;
	public String getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}
	public String getRetNum() {
		return retNum;
	}
	public void setRetNum(String retNum) {
		this.retNum = retNum;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public ProResult getProResult() {
		return proResult;
	}
	public void setProResult(ProResult proResult) {
		this.proResult = proResult;
	}
	public List<RgctBillInfo> getBillList() {
		return billList;
	}
	public void setBillList(List<RgctBillInfo> billList) {
		this.billList = billList;
	}
	public void setResult(List<Var120002InfoBean> result) {
		this.result = result;
	}
	public List<Var120002InfoBean> getResult() {
		return result;
	}
    

}
