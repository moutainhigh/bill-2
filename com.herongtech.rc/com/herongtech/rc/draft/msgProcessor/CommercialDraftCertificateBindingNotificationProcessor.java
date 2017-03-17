package com.herongtech.rc.draft.msgProcessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.ecds066.BindingDetails;
import org.ecds066.CommercialDraftCertificateBindingNotificationV01;
import org.ecds066.DocumentDocument;
import org.ecds066.MessageIdentification1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.vo.BindingDetail;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


/**
 * 066数字证书行号绑定关系变更通知报文解析类
 *
 */
public class CommercialDraftCertificateBindingNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftCertificateBindingNotificationV01 root=document.getDocument().getCommercialDraftCertificateBindingNotification();
        MessageIdentification1 msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        BindingDetails[] arr=root.getBindgDtlsArray();
        List<BindingDetail> list=new ArrayList<BindingDetail>(arr.length);
        for(BindingDetails bind:arr){
            BindingDetail dtl=new BindingDetail();
            dtl.setPtcptBankNo(bind.getPtcpt().getAcct().getAcctSvcr());
            dtl.setBindTp(bind.getBindgInf().getTp().toString());
            dtl.setCertInfCN(bind.getCertInf().getCN());
            dtl.setCertInfSN(bind.getCertInf().getSN());
            list.add(dtl);
        }
        vo.setBindList(list);
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc066";
    }

}
