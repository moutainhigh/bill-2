package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds040.BusinessControlInformation1;
import org.ecds040.CommercialDraft1;
import org.ecds040.CommercialDraftStatusChangingNotificationV01;
import org.ecds040.DocumentDocument;
import org.ecds040.MessageIdentification1;
import org.ecds040.Organisation1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;

/**
 * 040 票据状态变更通知报文解析
 * @author fqz
 *
 */
public class CommercialDraftStatusChangingNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        DraftInfoVo vo=new DraftInfoVo(); 
        RgctBillInfo rgctbill=new RgctBillInfo();
        vo.setBill(rgctbill);
        DocumentDocument doc=(DocumentDocument)xmlobject;
        CommercialDraftStatusChangingNotificationV01 root=doc.getDocument().getCommercialDraftStatusChangingNotification();
        MessageIdentification1  msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        
        CommercialDraft1 bill=root.getComrclDrft();
        rgctbill.setBillNo(bill.getIdNb());
        rgctbill.setCurStatus(bill.getSts());

        BusinessControlInformation1 infomation=root.getBizCtrlInf();
        vo.setPrcCd(infomation.getPrcCd());
        vo.setPrcMsg(infomation.getPrcMsg());
        
        Organisation1 rcvr=root.getRcvr();
        vo.setReceiveBankNo(rcvr.getAcct().getAcctSvcr());
        
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc040";
    }

}
