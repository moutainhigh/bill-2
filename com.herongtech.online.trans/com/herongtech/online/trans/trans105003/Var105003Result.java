package com.herongtech.online.trans.trans105003;

import java.util.List;

import com.herongtech.xmlchannel.pkg.ProResult;



public class Var105003Result extends ProResult{
    private String totalRows;//总记录数
    private String currentPage;//当前页数
    private String retNum;//本次返回行数
    
    private List<Var105003InfoBean> billList;
    
    
public List<Var105003InfoBean> getBillList() {
		return billList;
	}

	public void setBillList(List<Var105003InfoBean> billList) {
		this.billList = billList;
	}
	
	public String getRetNum() {
		return retNum;
	}

	public void setRetNum(String retNum) {
		this.retNum = retNum;
	}

	//    private List<RgctBill> billList;//记录List
    /*public List<RgctBill> getBillList() {
	return billList;
}

public void setBillList(List<RgctBill> billList) {
	this.billList = billList;
}
*/
    public String getTotalRows() {
        return totalRows;
    }
    
    public void setTotalRows(String totalRows) {
        this.totalRows = totalRows;
    }
    
    public String getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }


	
    
    
    
}
