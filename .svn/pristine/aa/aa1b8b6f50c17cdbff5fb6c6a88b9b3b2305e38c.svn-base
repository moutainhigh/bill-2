package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds033.BusinessControlInformation1;
import org.ecds033.CommercialDraftCommonStatusV01;
import org.ecds033.DocumentDocument;
import org.ecds033.MessageIdentification1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;


public class CommercialDraftCommonStatusProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        DraftInfoVo vo=new DraftInfoVo(); 
        RgctBillInfo bill=new RgctBillInfo();
        vo.setBill(bill);
        DocumentDocument doc=(DocumentDocument)xmlobject;
        CommercialDraftCommonStatusV01 root=doc.getDocument().getCommercialDraftCommonStatus();
        MessageIdentification1  msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        MessageIdentification1 orgMsg=root.getOrgnlMsgId();
        vo.setOrgnlMsgId(orgMsg.getId());
        vo.setOrgnlDraftCreDtTM(orgMsg.getCreDtTm().getTime());
        BusinessControlInformation1 infomation=root.getBizCtrlInf();
        vo.setPrcCd(infomation.getPrcCd());
        vo.setPrcMsg(infomation.getPrcMsg());
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc033";
    }

}
