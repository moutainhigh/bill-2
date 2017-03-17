package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds058.CommercialDraftParticipantSubstitutionNotificationV01;
import org.ecds058.DocumentDocument;
import org.ecds058.FctvInformation1;
import org.ecds058.MessageIdentification1;
import org.ecds058.Organisation1;
import org.ecds058.ParticipantSubstitution01;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


public class CommercialDraftParticipantSubstitutionNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftParticipantSubstitutionNotificationV01 root=document.getDocument().getCommercialDraftParticipantSubstitutionNotification();
        MessageIdentification1 msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        
        FctvInformation1 fctv=root.getFctvInf();
        vo.setFctvDt(fctv.getFctvDt().getTime());
        
        ParticipantSubstitution01 ps=root.getPtcptSbstitn();
        Organisation1 orgn=ps.getOrgnlPtcpt();
        Organisation1 cur=ps.getCurPtcpt();
        vo.setOrgnlPtcptRole(orgn.getRole().toString());
        vo.setOrgnlPtcptNm(orgn.getCmonId());
        vo.setOrgnlPtcptCmonId(orgn.getCmonId());
        vo.setOrgnlPtcptBankNo(orgn.getAcct().getAcctSvcr());
        
        vo.setCurPtcptBankNo(cur.getAcct().getAcctSvcr());
        vo.setCurPtcptCmonId(cur.getCmonId());
        vo.setCurPtcptNm(cur.getNm());
        vo.setCurPtcptRole(cur.getRole().toString());
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc058";
    }

}
