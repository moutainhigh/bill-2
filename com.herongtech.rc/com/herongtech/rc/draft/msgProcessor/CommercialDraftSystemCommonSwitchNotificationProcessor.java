package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds067.CommercialDraftSystemCommonSwitchNotificationV01;
import org.ecds067.DocumentDocument;
import org.ecds067.HvpsSystemInformation1;
import org.ecds067.MessageIdentification1;
import org.ecds067.Switch1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


public class CommercialDraftSystemCommonSwitchNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftSystemCommonSwitchNotificationV01 root=document.getDocument().getCommercialDraftSystemCommonSwitchNotification();
        MessageIdentification1 msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        
        HvpsSystemInformation1 hvps=root.getHvpsSysInf();
        if(hvps != null){
        vo.setNxtSysDt(MsgUtil.convertToString(hvps.getNxtSysDt()));
        }
        Switch1 swi=root.getSwtch();
        vo.setSwitchTp(swi.getTp().toString());
        vo.setSwitchOnOffMk(swi.getSwtchOnOffMrkr().toString());
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc067";
    }

}
