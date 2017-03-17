package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds052.ActionType2;
import org.ecds052.BusinessControlInformation1;
import org.ecds052.CommercialDraftLoginOrLogoutResponseV01;
import org.ecds052.DocumentDocument;
import org.ecds052.MessageIdentification1;
import org.ecds052.SystemStatus1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


/**
 * 052登录/退出应答报文
 *
 */
public class CommercialDraftLoginOrLogoutResponseProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftLoginOrLogoutResponseV01 root=document.getDocument().getCommercialDraftLoginOrLogoutResponse();
        MessageIdentification1 msg= root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        
        ActionType2 actp=root.getActnTp();
        vo.setActnTpMk(actp.getTpMk().toString());
        
        BusinessControlInformation1 bci=root.getBizCtrlInf();
        vo.setPrcCd(bci.getPrcCd());
        vo.setPrcMsg(bci.getPrcMsg());
        MessageIdentification1 orgMsg= root.getOrgnlMsgId();
        vo.setOrgnlMsgId(orgMsg.getId());
        vo.setOrgnlDraftCreDtTM(orgMsg.getCreDtTm().getTime());
        
        SystemStatus1 sys=root.getSysSts();
        vo.setSysSts(sys.getSts());
        vo.setSysDt(MsgUtil.convertToString(sys.getVldtyDt()));
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc052";
    }

}
