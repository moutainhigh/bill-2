package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds053.CommercialDraftForceLogoutV01;
import org.ecds053.DocumentDocument;
import org.ecds053.MessageContents1;
import org.ecds053.MessageIdentification1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


/**
 * 053强制退出登录报文解析类
 *
 */
public class CommercialDraftForceLogoutProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftForceLogoutV01 root=document.getDocument().getCommercialDraftForceLogout();
        MessageIdentification1 msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        MessageContents1 cnt=root.getMsgCnt();
        vo.setMsgCnts(cnt.getCnts());
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc053";
    }

}
