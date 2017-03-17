package com.herongtech.rc.draft.common.vo;

/**063接入点与辖内参与者对应关系报文*/
public class DraftAccessPointAndParticipants {

	private String fctvDt; /**生效日期*/
	private String ptcptCd;/**参与者号码*/
	private String ptcptNm;/**参与者名称*/
	private String accssPtCd;/**接入点号码*/
	private String accssPtNm;/**接入点名称*/
	
	/**生效日期*/
	public String getFctvDt() {
		return fctvDt;
	}
	/**生效日期*/
	public void setFctvDt(String fctvDt) {
		this.fctvDt = fctvDt;
	}
	
	
	/**参与者号码*/
	public String getPtcptCd() {
		return ptcptCd;
	}
	/**参与者号码*/
	public void setPtcptCd(String ptcptCd) {
		this.ptcptCd = ptcptCd;
	}
	
	
	/**参与者名称*/
	public String getPtcptNm() {
		return ptcptNm;
	}
	/**参与者名称*/
	public void setPtcptNm(String ptcptNm) {
		this.ptcptNm = ptcptNm;
	}
	
	
	/**接入点号码*/
	public String getAccssPtCd() {
		return accssPtCd;
	}
	/**接入点号码*/
	public void setAccssPtCd(String accssPtCd) {
		this.accssPtCd = accssPtCd;
	}
	
	
	/**接入点名称*/
	public String getAccssPtNm() {
		return accssPtNm;
	}
	/**接入点名称*/
	public void setAccssPtNm(String accssPtNm) {
		this.accssPtNm = accssPtNm;
	}
}
