package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds060.CommercialDraftSettlementOnlineNotificationV01;
import org.ecds060.DocumentDocument;
import org.ecds060.HvpsSystemDate1;
import org.ecds060.MessageIdentification1;
import org.ecds060.SettlementOnline1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


public class CommercialDraftSettlementOnlineNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftSettlementOnlineNotificationV01 root=document.getDocument().getCommercialDraftSettlementOnlineNotification();
        MessageIdentification1 msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        
        HvpsSystemDate1 hvps=root.getHvpsSysInf();
        vo.setNxtSysDt(MsgUtil.convertToString(hvps.getNxtSysDt()));
        
        SettlementOnline1 sttlo=root.getSttlmOnline();
        vo.setSttlmOnlineMrk(sttlo.getSttlmOnlineMrk().toString());
        vo.setSttlmRmrk(sttlo.getRmrk());
        
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc060";
    }

}
