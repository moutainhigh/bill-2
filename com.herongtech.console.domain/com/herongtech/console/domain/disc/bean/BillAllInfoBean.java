package com.herongtech.console.domain.disc.bean;

import java.util.List;

import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.domain.bean.EcdsBillBean;


public class BillAllInfoBean {

	private EcdsBillBean frontBean;//正面信息
	
	private BillBackInfoBean backBean;//反面信息
	
	private List<AssuBillInfo> guarnteeList;//保证信息

	public List<AssuBillInfo> getGuarnteeList() {
		return guarnteeList;
	}

	public void setGuarnteeList(List<AssuBillInfo> guarnteeList) {
		this.guarnteeList = guarnteeList;
	}

	public EcdsBillBean getFrontBean() {
		return frontBean;
	}

	public void setFrontBean(EcdsBillBean frontBean) {
		this.frontBean = frontBean;
	}

	public BillBackInfoBean getBackBean() {
		return backBean;
	}

	public void setBackBean(BillBackInfoBean backBean) {
		this.backBean = backBean;
	}
	
	
	
}
