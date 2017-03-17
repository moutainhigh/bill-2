package com.herongtech.online.trans.trans103003;

import java.util.List;

import com.herongtech.xmlchannel.pkg.ProResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public class Var103003Result {
   
    private ProResult proResult;
    private String totRecNum;//总记录数
    private String currentPage;//当前页数
    private String pageSize;//本次返回行数
	
   


	public String getCurrentPage() {
		return currentPage;
	}



	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}



	public String getPageSize() {
		return pageSize;
	}



	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	private List<Var103003InfoBean> result;
    
    
    
    public ProResult getProResult() {
        return proResult;
    }


    
    public void setProResult(ProResult proResult) {
        this.proResult = proResult;
    }


    public List<Var103003InfoBean> getResult() {
        return result;
    }

    
    public void setResult(List<Var103003InfoBean> result) {
        this.result = result;
    }



	public String getTotRecNum() {
		return totRecNum;
	}



	public void setTotRecNum(String totRecNum) {
		this.totRecNum = totRecNum;
	}
}
