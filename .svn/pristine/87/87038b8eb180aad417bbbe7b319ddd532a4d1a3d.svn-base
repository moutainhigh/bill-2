package com.herongtech.rc.draft.msgBuilder.replyBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds031.Account1;
import org.ecds031.CommercialDraft1;
import org.ecds031.CommercialDraftCommonSignUpV01;
import org.ecds031.Commonformation1;
import org.ecds031.CurrencyAndAmount;
import org.ecds031.Document;
import org.ecds031.DocumentDocument;
import org.ecds031.MessageIdentification1;
import org.ecds031.Organisation1;
import org.ecds031.Organisation2;
import org.ecds031.ProxySignatureCode;
import org.ecds031.RoleCode;
import org.ecds031.SignUpInformation1;

import com.herongtech.commons.tools.StringUtil;
import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;
import com.herongtech.rc.draft.msgBuilder.replyBuilder.commonSignUp.SignUpBuilder;


/**
 * 031通用回复
 *
 */
public class CommercialDraftCommonSignUpDraftBuilder extends
AbstractDraftBuilder{

    @Override
    public String buildBody(DraftSender draftSender) throws BizAppException {
        XmlOptions options= createXmlOptions(draftSender.getMesgType());
        
        DraftInfoVo vo=(DraftInfoVo)draftSender.getReqInfo();
        RgctBillInfo bill=vo.getBill();
        DocumentDocument document = DocumentDocument.Factory.newInstance(options);
        Document doc=document.addNewDocument();
        CommercialDraftCommonSignUpV01 commerDraft=doc.addNewCommercialDraftCommonSignUp();
       
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
        
        Commonformation1 infomation= commerDraft.addNewCmonInf();
        //TODO:收票、背书、提示付款、逾期提示付款的回复允许银行代理（PS00）,其余固定为PS01。这里先默认PS01，后面再根据业务判断
        infomation.setPrxySgntr(ProxySignatureCode.Enum.forString(DraftConstants.ProxySignatureCode2));
        /*if("".equals(vo.get)){
            infomation.setPrxySgntr(ProxySignatureCode.Enum.forString(DraftConstants.ProxySignatureCode1)); 
        }*/

        if(StringUtils.isNotBlank(vo.getReceiveRemark())){
        infomation.setRmrkBySgnr(vo.getReceiveRemark());
        }

        
        Organisation1 from=commerDraft.addNewSgnr();
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
        
        
        
        SignUpInformation1 signUp=commerDraft.addNewSgnUpInf();
        String builderClass=DraftConstants.BusiTypeToSignUpBuilderMap.get(vo.getBusiType());
        
        SignUpBuilder sgnUpbuilder=null;
        try {
            sgnUpbuilder=(SignUpBuilder)Class.forName("com.herongtech.rc.draft.msgBuilder.replyBuilder.commonSignUp."+builderClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(sgnUpbuilder!=null){
            sgnUpbuilder.generateSignUpInfo(signUp, vo);
        }
        String draft=document.xmlText(options);
        String signPart=commerDraft.xmlText();
        return addSign(draft, signPart,draftSender.getIsAddSign());
    }

}
