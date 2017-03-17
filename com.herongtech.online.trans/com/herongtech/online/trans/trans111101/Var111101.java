package com.herongtech.online.trans.trans111101;

import java.util.List;

import com.herongtech.console.domain.common.bean.InterestReqByStringDTO;


public class Var111101 {
	private String beginDate;//起息日
	private String endDate;//票面到期日
	private List<InterestReqByStringDTO> insList;
	private List<Var111101InfoBean> result;
	private String errNum=null;			//错误条数
	private String totNum=null;			//总条数
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<InterestReqByStringDTO> getInsList() {
		return insList;
	}
	public void setInsList(List<InterestReqByStringDTO> insList) {
		this.insList = insList;
	}
	public String getErrNum() {
		return errNum;
	}
	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}
	public String getTotNum() {
		return totNum;
	}
	public void setTotNum(String totNum) {
		this.totNum = totNum;
	}
	public List<Var111101InfoBean> getResult() {
		return result;
	}
	public void setResult(List<Var111101InfoBean> result) {
		this.result = result;
	}
	
			
	

}
