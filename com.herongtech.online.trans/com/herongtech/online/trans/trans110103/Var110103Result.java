package com.herongtech.online.trans.trans110103;

import java.util.List;

import com.herongtech.xmlchannel.pkg.ProResult;
/**票据删除的返回类*/
public class Var110103Result {
	private Var110103ResultNum resultnum;

	public Var110103ResultNum getResultnum() {
		return resultnum;
	}

	public void setResultnum(Var110103ResultNum resultnum) {
		this.resultnum = resultnum;
	}

	public ProResult getProResult() {
		return proResult;
	}

	public void setProResult(ProResult proResult) {
		this.proResult = proResult;
	}

	public List<Var110103Bean> getBeanlist() {
		return beanlist;
	}

	public void setBeanlist(List<Var110103Bean> beanlist) {
		this.beanlist = beanlist;
	}

	private ProResult proResult;

	private List<Var110103Bean> beanlist;
}
