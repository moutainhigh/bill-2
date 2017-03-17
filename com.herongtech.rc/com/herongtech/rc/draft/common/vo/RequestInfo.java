package com.herongtech.rc.draft.common.vo;

import java.util.Date;


public class RequestInfo {

    private String serviceId;   //服务ID  
    private String reqMsgId;        //往报（发到ECDS去）时该字段为申请报文ID/来报（从ECDS过来）时该字段只表示报文的ID【034报文id与请求体里面的ID是一致的】
    private String respMsgId;       //回复报文ID
    private String orgnlMsgId;      //原申请报文ID
    private Date draftCreDtTm;      //报文时间（发送报文取的是系统时间不用赋值，收到报文时为报文时间）
    private Date orgnlDraftCreDtTM; //原申请报文时间
    private String exceptionCode;
    private String prcCd;           //报文处理码（033/052报文字段）
    private String prcMsg;          //处理信息
    
    private String fromBankNo;  //申请人开户行行号
    private String fromBankName;//申请人开户行名称
    private String fromName;    //申请人名称
    private String fromAcctNo;  //申请人账号
    private String fromRemark;  //申请人备注
    private String fromRoleType;//申请人参与者类别
    private String fromOrgCode; //申请人组织机构代码
    private String fromElctrncSgntr;//申请人电子签名
    private String fromAgcyBankNo;//申请人承接行行号
    
    private String receiveName;    //接收人名称
    private String receiveBankNo;  //接收人开户行行号
    private String receiveBankName;//接收人开户行名称
    private String receiveAcctNo;  //接收人账号
    private String receiveRemark;  //接收人备注(回复人备注)
    private String receiveRoleType;//接收人参与者类别
    private String receiveOrgCode; //接收人组织机构代码
    private String receiveElctrncSgntr;//接收人电子签名
    
    private String toBankNo;//报文接收行行号--（036报文字段）
    
    
    
    
    public String getServiceId() {
        return serviceId;
    }
    
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    
    public String getReqMsgId() {
        return reqMsgId;
    }
    
    public void setReqMsgId(String reqMsgId) {
        this.reqMsgId = reqMsgId;
    }
    
    public String getRespMsgId() {
        return respMsgId;
    }
    
    public void setRespMsgId(String respMsgId) {
        this.respMsgId = respMsgId;
    }
    
    public String getOrgnlMsgId() {
        return orgnlMsgId;
    }
    
    public void setOrgnlMsgId(String orgnlMsgId) {
        this.orgnlMsgId = orgnlMsgId;
    }
    
    public Date getDraftCreDtTm() {
        return draftCreDtTm;
    }

    
    public void setDraftCreDtTm(Date draftCreDtTm) {
        this.draftCreDtTm = draftCreDtTm;
    }

    
    public Date getOrgnlDraftCreDtTM() {
        return orgnlDraftCreDtTM;
    }

    
    public void setOrgnlDraftCreDtTM(Date orgnlDraftCreDtTM) {
        this.orgnlDraftCreDtTM = orgnlDraftCreDtTM;
    }

    
    public String getExceptionCode() {
        return exceptionCode;
    }

    
    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
    public String getPrcCd() {
        return prcCd;
    }

    
    public void setPrcCd(String prcCd) {
        this.prcCd = prcCd;
    }

    
    public String getPrcMsg() {
        return prcMsg;
    }

    
    public void setPrcMsg(String prcMsg) {
        this.prcMsg = prcMsg;
    }
    
    
    public String getFromBankNo() {
        return fromBankNo;
    }
    
    public void setFromBankNo(String fromBankNo) {
        this.fromBankNo = fromBankNo;
    }
    
    public String getFromBankName() {
        return fromBankName;
    }
    
    public void setFromBankName(String fromBankName) {
        this.fromBankName = fromBankName;
    }
    
    public String getFromName() {
        return fromName;
    }
    
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    
    public String getFromAcctNo() {
        return fromAcctNo;
    }
    
    public void setFromAcctNo(String fromAcctNo) {
        this.fromAcctNo = fromAcctNo;
    }
    
    public String getFromRemark() {
        return fromRemark;
    }
    
    public void setFromRemark(String fromRemark) {
        this.fromRemark = fromRemark;
    }
    
    public String getFromRoleType() {
        return fromRoleType;
    }
    
    public void setFromRoleType(String fromRoleType) {
        this.fromRoleType = fromRoleType;
    }
    
    public String getFromOrgCode() {
        return fromOrgCode;
    }
    
    public void setFromOrgCode(String fromOrgCode) {
        this.fromOrgCode = fromOrgCode;
    }
    
    public String getFromElctrncSgntr() {
        return fromElctrncSgntr;
    }
    
    public void setFromElctrncSgntr(String fromElctrncSgntr) {
        this.fromElctrncSgntr = fromElctrncSgntr;
    }
    
    public String getFromAgcyBankNo() {
        return fromAgcyBankNo;
    }
    
    public void setFromAgcyBankNo(String fromAgcyBankNo) {
        this.fromAgcyBankNo = fromAgcyBankNo;
    }
    
    public String getReceiveName() {
        return receiveName;
    }
    
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
    
    public String getReceiveBankNo() {
        return receiveBankNo;
    }
    
    public void setReceiveBankNo(String receiveBankNo) {
        this.receiveBankNo = receiveBankNo;
    }
    
    public String getReceiveBankName() {
        return receiveBankName;
    }
    
    public void setReceiveBankName(String receiveBankName) {
        this.receiveBankName = receiveBankName;
    }
    
    public String getReceiveAcctNo() {
        return receiveAcctNo;
    }
    
    public void setReceiveAcctNo(String receiveAcctNo) {
        this.receiveAcctNo = receiveAcctNo;
    }
    
    
    public String getReceiveRemark() {
        return receiveRemark;
    }

    
    public void setReceiveRemark(String receiveRemark) {
        this.receiveRemark = receiveRemark;
    }

    public String getReceiveRoleType() {
        return receiveRoleType;
    }
    
    public void setReceiveRoleType(String receiveRoleType) {
        this.receiveRoleType = receiveRoleType;
    }
    
    public String getReceiveOrgCode() {
        return receiveOrgCode;
    }
    
    public void setReceiveOrgCode(String receiveOrgCode) {
        this.receiveOrgCode = receiveOrgCode;
    }
    
    public String getReceiveElctrncSgntr() {
        return receiveElctrncSgntr;
    }
    
    public void setReceiveElctrncSgntr(String receiveElctrncSgntr) {
        this.receiveElctrncSgntr = receiveElctrncSgntr;
    }

	public String getToBankNo() {
		return toBankNo;
	}

	public void setToBankNo(String toBankNo) {
		this.toBankNo = toBankNo;
	}
    
}
