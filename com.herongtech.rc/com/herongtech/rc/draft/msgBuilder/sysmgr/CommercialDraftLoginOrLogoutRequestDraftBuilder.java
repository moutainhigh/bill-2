package com.herongtech.rc.draft.msgBuilder.sysmgr;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds032.Organisation2;
import org.ecds051.ActionType1;
import org.ecds051.CommercialDraftLoginOrLogoutRequestV01;
import org.ecds051.Document;
import org.ecds051.DocumentDocument;
import org.ecds051.MessageIdentification1;
import org.ecds051.TypeMarkCode;

import com.herongtech.commons.tools.StringUtil;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;


/**
 * 051登录/登出申请报文
 *
 */
public class CommercialDraftLoginOrLogoutRequestDraftBuilder extends
AbstractDraftBuilder{

    @Override
    public String buildBody(DraftSender draftSender) throws BizAppException {
        XmlOptions options= createXmlOptions(draftSender.getMesgType());
        SysMgrInfoVo vo=(SysMgrInfoVo)draftSender.getReqInfo();
        DocumentDocument document = DocumentDocument.Factory.newInstance(options);
        Document doc=document.addNewDocument();
        CommercialDraftLoginOrLogoutRequestV01 commerDraft=doc.addNewCommercialDraftLoginOrLogoutRequest();
        MessageIdentification1 msgid=commerDraft.addNewMsgId();
        msgid.setId(vo.getReqMsgId());                                         //报文标识
        msgid.setCreDtTm(MsgUtil.getCurrentTime());
        ActionType1 actionType=commerDraft.addNewActnTp();
        //actionType.setIdCd(vo.getIdCd());
        actionType.setTpMk(TypeMarkCode.Enum.forString(vo.getActnTpMk()));
        actionType.setOrgnlIdCd(vo.getOrgnlIdCd());
        if(StringUtils.isNotBlank(vo.getIdCd())){
           
        	actionType.setIdCd(vo.getIdCd());
        }
        String draft=document.xmlText(options);
        String signPart=commerDraft.xmlText();
        return addSign(draft, signPart,draftSender.getIsAddSign());
    }

}
