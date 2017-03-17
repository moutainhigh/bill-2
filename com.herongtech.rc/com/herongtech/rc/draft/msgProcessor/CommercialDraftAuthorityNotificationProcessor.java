package com.herongtech.rc.draft.msgProcessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.ecds056.AuthorityNotification1;
import org.ecds056.CommercialDraftAuthorityNotificationV01;
import org.ecds056.DocumentDocument;
import org.ecds056.MessageIdentification1;
import org.ecds056.Organisation1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.vo.DraftAuthority;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


/**
 * 056业务权限变更通知报文解析类
 *
 */
public class CommercialDraftAuthorityNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftAuthorityNotificationV01 root=document.getDocument().getCommercialDraftAuthorityNotification();
        MessageIdentification1 msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        
        List<DraftAuthority> authList=new ArrayList<DraftAuthority>();
        DraftAuthority draftAuth=new DraftAuthority();
        AuthorityNotification1 auth=root.getAuthrtyNtfctn();
        draftAuth.setRcvAuthrtyLis(auth.getRcvAuthrtyList());
        draftAuth.setRcvAuthrtyNb(auth.getRcvAuthrtyNb());
        draftAuth.setSndAuthrtyList(auth.getSndAuthrtyList());
        draftAuth.setSndAuthrtyNb(auth.getSndAuthrtyNb());
        Organisation1 trgrBk= root.getTrgtBk();
        draftAuth.setPtcptAcctSvcr(trgrBk.getAcct().getAcctSvcr());
        authList.add(draftAuth);
        vo.setAuthority(authList);
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc056";
    }

}
