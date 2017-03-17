package com.herongtech.rc.draft.msgBuilder.replyBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds032.Account1;
import org.ecds032.CommercialDraft1;
import org.ecds032.CommercialDraftCancellationRequestV01;
import org.ecds032.CurrencyAndAmount;
import org.ecds032.Document;
import org.ecds032.DocumentDocument;
import org.ecds032.MessageIdentification1;
import org.ecds032.Organisation1;
import org.ecds032.Organisation2;
import org.ecds032.RoleCode;

import com.herongtech.commons.tools.StringUtil;
import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;


/**
 * 032通用撤销
 *
 */
public class CommercialDraftCancellationRequestDraftBuilder extends AbstractDraftBuilder{

    @Override
    public String buildBody(DraftSender draftSender) throws BizAppException {
        XmlOptions options= createXmlOptions(draftSender.getMesgType());
        
        DraftInfoVo vo=(DraftInfoVo)draftSender.getReqInfo();
        RgctBillInfo bill=vo.getBill();
        DocumentDocument document = DocumentDocument.Factory.newInstance(options);
        Document doc=document.addNewDocument();
        CommercialDraftCancellationRequestV01 commerDraft=doc.addNewCommercialDraftCancellationRequest();
        MessageIdentification1 msgid=commerDraft.addNewMsgId();
        msgid.setId(vo.getReqMsgId());              //报文标识
        msgid.setCreDtTm(MsgUtil.getCurrentTime()); //报文时间
        MessageIdentification1 orgnMsgId=commerDraft.addNewOrgnlMsgId();
        orgnMsgId.setId(vo.getOrgnlMsgId());              
        orgnMsgId.setCreDtTm(MsgUtil.getConverTime(vo.getOrgnlDraftCreDtTM())); 
        CommercialDraft1 draftInfo=commerDraft.addNewComrclDrft();
        draftInfo.setIdNb(bill.getBillNo());
        CurrencyAndAmount AMT=draftInfo.addNewIsseAmt();
        AMT.setCcy(DraftConstants.CURRENCY_TYPE);
        AMT.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
        
        Organisation1 from=commerDraft.addNewCxlPropsr();
        Account1 acct=from.addNewAcct();
        acct.setId(vo.getFromAcctNo());
        acct.setAcctSvcr(vo.getFromBankNo());
        from.setCmonId(vo.getFromOrgCode());
        from.setElctrncSgntr(vo.getFromElctrncSgntr());
        from.setRole(RoleCode.Enum.forString(vo.getFromRoleType()));
        if(StringUtils.isNotBlank(vo.getFromAgcyBankNo())){
            Organisation2 agcy=from.addNewAgcy();
            agcy.addNewAcct().setAcctSvcr(vo.getFromAgcyBankNo());
        }
        
        String draft=document.xmlText(options);
        String signPart=commerDraft.xmlText();
        return addSign(draft, signPart,draftSender.getIsAddSign());
    }

}
