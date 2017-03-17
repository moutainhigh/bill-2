package com.herongtech.rc.draft.common.vo;

import com.herongtech.rc.domain.bean.RgctBillInfo;


public class DraftSender {
    
    
    
    /**
     * 请求信息
     */
    private RequestInfo reqInfo;
    
    /**
     * 消息投放队列名称
     */
    private String queueName;
    
    /**
     * 报文构造器
     */
    private String draftBuilder;
    
    /**
     * 是否加签
     */
    private String isAddSign;
    
    /**
     * 报文编号
     */
    private String mesgType;
    
    /**
     * 报文标识号
     * 
     */
    private String mesgId;
    
    /**
     * 报文参考号
     */
    private String mesgRefId;
    
    /**
     * 发送人节点号
     */
    private String senderId; 
   
    /**
     * 接收人节点号
     */
    private String receiverId;
    
    /**
     * 报文发起人
     */
    private String origSender;
    
    /**
     * 报文接收人
     */
    private String origReceiver = "9968";

    
    
    public RequestInfo getReqInfo() {
        return reqInfo;
    }


    
    public void setReqInfo(RequestInfo reqInfo) {
        this.reqInfo = reqInfo;
    }


    public String getQueueName() {
        return queueName;
    }

    
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    
    public String getDraftBuilder() {
        return draftBuilder;
    }

    
    public void setDraftBuilder(String draftBuilder) {
        this.draftBuilder = draftBuilder;
    }

    
    public String getIsAddSign() {
        return isAddSign;
    }

    
    public void setIsAddSign(String isAddSign) {
        this.isAddSign = isAddSign;
    }

    
    public String getMesgType() {
        return mesgType;
    }

    
    public void setMesgType(String mesgType) {
        this.mesgType = mesgType;
    }

    
    public String getMesgId() {
        return mesgId;
    }

    
    public void setMesgId(String mesgId) {
        this.mesgId = mesgId;
    }

    
    public String getMesgRefId() {
        return mesgRefId;
    }

    
    public void setMesgRefId(String mesgRefId) {
        this.mesgRefId = mesgRefId;
    }

    
    public String getSenderId() {
        return senderId;
    }

    
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    
    public String getReceiverId() {
        return receiverId;
    }

    
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    
    public String getOrigSender() {
        return origSender;
    }

    
    public void setOrigSender(String origSender) {
        this.origSender = origSender;
    }

    
    public String getOrigReceiver() {
        return origReceiver;
    }

    
    public void setOrigReceiver(String origReceiver) {
        this.origReceiver = origReceiver;
    }
    

}
