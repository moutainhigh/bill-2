package com.herongtech.rc.draft.msgProcessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.ecds059.AddedInformation1;
import org.ecds059.AlteredInformation1;
import org.ecds059.BankInformation1;
import org.ecds059.BankInformationElement1;
import org.ecds059.CommercialDraftBankInformationNotificationV01;
import org.ecds059.DeletedInformation1;
import org.ecds059.DocumentDocument;
import org.ecds059.MessageIdentification1;
import org.ecds059.Notification1;
import org.ecds059.Organisation1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.vo.DraftBankInformation;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


/**
 * 059行名行号数据变更通知报文
 *
 */
public class CommercialDraftBankInformationNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftBankInformationNotificationV01 root=document.getDocument().getCommercialDraftBankInformationNotification();
        MessageIdentification1 msg= root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        
        BankInformationElement1[] banks=root.getBkInfElmtArray();
        List<DraftBankInformation> list=new ArrayList<DraftBankInformation>(banks.length);
        for(BankInformationElement1 bank:banks){
            Organisation1 org=bank.getOrg();
            BankInformation1 info=bank.getBkInf();
            DraftBankInformation dbi=new DraftBankInformation();
            dbi.setOrgBankNo(org.getAcct().getAcctSvcr());
            AddedInformation1 addInfo=info.getAddedInf();
            DeletedInformation1 deleteInfo=info.getDeltdInf();
            AlteredInformation1 updateInfo=info.getAltrdInf();
            if(addInfo!=null){
                dbi.setChangeType("1");
                dbi.setAddedInfAdr(addInfo.getAdr());
                dbi.setAddedInfCityCd(addInfo.getCityCd());
                dbi.setAddedInfClss(addInfo.getClss());
                dbi.setAddedInfCtgy(addInfo.getCtgy());
                dbi.setAddedInfDrct(addInfo.getDrct());
                dbi.setAddedInfEmai(addInfo.getEmail());
                dbi.setAddedInfFctvDt(addInfo.getFctvDt().getTime());
                dbi.setAddedInfFullNm(addInfo.getFullNm());
                dbi.setAddedInfNdCd(addInfo.getNdCd());
                dbi.setAddedInfPBCBk(addInfo.getPBCBk());
                dbi.setAddedInfPhneNb(addInfo.getPhneNb());
                dbi.setAddedInfRmrk(addInfo.getRmrk());
                dbi.setAddedInfShrtNm(addInfo.getShrtNm());
                dbi.setAddedInfSprrLst(addInfo.getSprrLst());
                dbi.setAddedInfZpCd(addInfo.getZpCd());
            }else if(updateInfo !=null){
                dbi.setChangeType("2");
                dbi.setAddedInfAdr(updateInfo.getAdr());
                dbi.setAddedInfCityCd(updateInfo.getCityCd());
                dbi.setAddedInfClss(updateInfo.getClss());
                dbi.setAddedInfCtgy(updateInfo.getCtgy());
                dbi.setAddedInfDrct(updateInfo.getDrct());
                dbi.setAddedInfEmai(updateInfo.getEmail());
                dbi.setAddedInfFctvDt(updateInfo.getFctvDt().getTime());
                dbi.setAddedInfFullNm(updateInfo.getFullNm());
                dbi.setAddedInfNdCd(updateInfo.getNdCd());
                dbi.setAddedInfPBCBk(updateInfo.getPBCBk());
                dbi.setAddedInfPhneNb(updateInfo.getPhneNb());
                dbi.setAddedInfRmrk(updateInfo.getRmrk());
                dbi.setAddedInfShrtNm(updateInfo.getShrtNm());
                dbi.setAddedInfSprrLst(updateInfo.getSprrLst());
                dbi.setAddedInfZpCd(updateInfo.getZpCd());
            }else if(deleteInfo != null){
                dbi.setChangeType("3");
                dbi.setXpryDt(deleteInfo.getXpryDt().getTime());
            }
            list.add(dbi);
        }
        vo.setBankInf(list);
        Notification1 notis=root.getNtfctn();
        vo.setTermNb(notis.getTermNb());
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc059";
    }

}
