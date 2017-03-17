package com.herongtech.online.trans.trans101003;

import java.util.List;

import com.herongtech.xmlchannel.pkg.ProResult;

/**101003返回类（可撤消提示收票的票据查询 ）*/
public class Var101003Result extends ProResult{
	private int currentPage;	//当前页号	int	Y	
	private int totalRows;	//	总行数	int	Y	
	private int retNum;	//	本次返回行数	int	Y	
	private List<Var101003Bean> beanlist;
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
	public List<Var101003Bean> getBeanlist() {
		return beanlist;
	}
	public void setBeanlist(List<Var101003Bean> beanlist) {
		this.beanlist = beanlist;
	}
	
}
