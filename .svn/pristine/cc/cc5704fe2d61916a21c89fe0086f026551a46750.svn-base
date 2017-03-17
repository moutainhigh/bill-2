package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds035.CommercialDraft1;
import org.ecds035.CommercialDraftExceptionNotificationV01;
import org.ecds035.DocumentDocument;
import org.ecds035.ExceptionInformation1;
import org.ecds035.MessageIdentification1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;


public class CommercialDraftExceptionNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        DraftInfoVo vo=new DraftInfoVo(); 
        RgctBillInfo bill=new RgctBillInfo();
        vo.setBill(bill);
        DocumentDocument doc=(DocumentDocument)xmlobject;
        CommercialDraftExceptionNotificationV01 root=doc.getDocument().getCommercialDraftExceptionNotification();
        MessageIdentification1  msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        MessageIdentification1 orgMsg=root.getOrgnlMsgId();
        vo.setOrgnlMsgId(orgMsg.getId());
        vo.setOrgnlDraftCreDtTM(orgMsg.getCreDtTm().getTime());
        CommercialDraft1 info=root.getComrclDrft();
        bill.setBillNo(info.getIdNb());
        ExceptionInformation1 excep=root.getXcptnInf();
        vo.setExceptionCode(excep.getXcptnCd().toString());
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc035";
    }

}
