package com.herongtech.online.trans.trans101005;

import java.util.List;

import com.herongtech.online.trans.trans101005.Var101005Bean;
import com.herongtech.xmlchannel.pkg.ProResult;

/**101005（查询可撤消票据服务）   返回类*/
public class Var101005Result extends ProResult{

	private int currentPage;	//当前页号	int	Y	
	private int totalRows;	//总行数	int	Y
	private int retNum;	//本次返回行数	int	Y
	private List<Var101005Bean> listbean;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getRetNum() {
		return retNum;
	}
	public void setRetNum(int retNum) {
		this.retNum = retNum;
	}
	public List<Var101005Bean> getListbean() {
		return listbean;
	}
	public void setListbean(List<Var101005Bean> listbean) {
		this.listbean = listbean;
	}
}
