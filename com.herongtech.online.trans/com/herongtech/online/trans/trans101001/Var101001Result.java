package com.herongtech.online.trans.trans101001;

import java.util.List;

import com.herongtech.xmlchannel.pkg.ProResult;
/**出票登记查询  返回类*/
public class Var101001Result extends ProResult{
	private int totalRows;//	总行数
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	private int currentPage;//	当前页号
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	private int retNum;//	本次返回行数
	public int getRetNum() {
		return retNum;
	}
	public void setRetNum(int retNum) {
		this.retNum = retNum;
	}
	List<Var101001Bean> var101001bean;
	public List<Var101001Bean> getVar101001bean() {
		return var101001bean;
	}
	public void setVar101001bean(List<Var101001Bean> var101001bean) {
		this.var101001bean = var101001bean;
	}
	
	
	
}
