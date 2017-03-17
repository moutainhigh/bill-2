package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds054.CommercialDraftSystemStatusNotificationV01;
import org.ecds054.DocumentDocument;
import org.ecds054.MessageIdentification1;
import org.ecds054.Systemstatus3;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


public class CommercialDraftSystemStatusNotificationProcessor extends
        AbstractDraftMsgProcessor {

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject) {
        SysMgrInfoVo info=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftSystemStatusNotificationV01 root=document.getDocument().getCommercialDraftSystemStatusNotification();
        MessageIdentification1 msg=root.getMsgId();
        Systemstatus3 sysSts=root.getSysSts();
        info.setReqMsgId(msg.getId());
        info.setDraftCreDtTm(MsgUtil.convertToDate(msg.getCreDtTm()));
        info.setOrgnlSysSts(sysSts.getOrgnlSysSts());
        info.setOrgnlSysDt(DateUtil.getDateStr(sysSts.getOrgnlSysDt()));
        info.setSysSts(sysSts.getSysSts());
        info.setSysDt(DateUtil.getDateStr(sysSts.getSysDt()));
        info.setNxtSysDt(DateUtil.getDateStr(sysSts.getNxtSysDt()));
        info.setBizRefTm(sysSts.getBizRefTm());
        return info;
    }
    @Override
    protected String getServiceId() {
        return "Proc054";
    }
}
