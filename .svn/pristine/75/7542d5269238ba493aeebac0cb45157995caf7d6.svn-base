package com.herongtech.rc.draft.msgBuilder.replyBuilder;

import org.apache.xmlbeans.XmlOptions;
import org.ecds035.CommercialDraft1;
import org.ecds035.CommercialDraftExceptionNotificationV01;
import org.ecds035.CurrencyAndAmount;
import org.ecds035.Document;
import org.ecds035.DocumentDocument;
import org.ecds035.ExceptionCode;
import org.ecds035.ExceptionInformation1;
import org.ecds035.MessageIdentification1;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;


/**
 * 035 清分失败
 *
 */
public class CommercialDraftExceptionNotificationDraftBuilder extends
AbstractDraftBuilder{

    @Override
    public String buildBody(DraftSender draftSender) throws BizAppException {
        XmlOptions options= createXmlOptions(draftSender.getMesgType());
        
        DraftInfoVo vo=(DraftInfoVo)draftSender.getReqInfo();
        RgctBillInfo bill=vo.getBill();
        DocumentDocument document = DocumentDocument.Factory.newInstance(options);
        Document doc=document.addNewDocument();
        CommercialDraftExceptionNotificationV01 commerDraft=doc.addNewCommercialDraftExceptionNotification();
        
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

        ExceptionInformation1 exception=commerDraft.addNewXcptnInf();
        exception.setXcptnCd(ExceptionCode.Enum.forString(vo.getExceptionCode()));
        
        String draft=document.xmlText(options);
        String signPart=commerDraft.xmlText();
        return addSign(draft, signPart,draftSender.getIsAddSign());
    }

}
