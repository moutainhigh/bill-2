package com.herongtech.online.trans.trans130001;

import java.util.List;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.xmlchannel.pkg.ProResult;

public class Var130001Result {
	
	private String totalRows;
    private String  retNum;
    private String currentPage;
    private ProResult proResult;
    private List<RgctBillInfo> billList;
    private List<Var130001InfoBean> result;
    
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
    
    public List<RgctBillInfo> getBillList() {
        return billList;
    }
    
    public void setBillList(List<RgctBillInfo> billList) {
        this.billList = billList;
    }

	public void setProResult(ProResult proResult) {
		this.proResult = proResult;
	}

	public ProResult getProResult() {
		return proResult;
	}

	public void setResult(List<Var130001InfoBean> result) {
		this.result = result;
	}

	public List<Var130001InfoBean> getResult() {
		return result;
	}
	
	

}
