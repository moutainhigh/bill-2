package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds064.CommercialDraftParticipantStatusNotificationV01;
import org.ecds064.DocumentDocument;
import org.ecds064.MessageIdentification1;
import org.ecds064.Participant1;
import org.ecds064.Status1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


public class CommercialDraftParticipantStatusNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftParticipantStatusNotificationV01 root=document.getDocument().getCommercialDraftParticipantStatusNotification();
        MessageIdentification1 msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        Participant1 part=root.getPtcpt();
        vo.setPtcptTp(part.getTp());
        vo.setPtcptNb(part.getNb());
        
        Status1 sts=root.getSts();
        vo.setPtcptSts(sts.getSts());
        vo.setFctvTp(sts.getFctvTp());
        vo.setFctvDt(sts.getFctvDt().getTime());
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc064";
    }

}
