package com.herongtech.online.trans.trans130004;

import java.util.List;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.xmlchannel.pkg.ProResult;

public class Var130004Result {
	
    private ProResult proResult;
    private List<RgctBillInfo> billList;
    private List<Var130004InfoBean> result;
    
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

	public void setResult(List<Var130004InfoBean> result) {
		this.result = result;
	}

	public List<Var130004InfoBean> getResult() {
		return result;
	}
	
	

}
