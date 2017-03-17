package com.herongtech.rc.draft.msgProcessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.ecds044.CommercialDraft1;
import org.ecds044.CommercialDraftBusiness1;
import org.ecds044.CommercialDraftDraftInformationNotificationV01;
import org.ecds044.CommercialDraftShowADraftRequest1;
import org.ecds044.DocumentDocument;
import org.ecds044.MessageIdentification1;
import org.ecds044.Organisation1;
import org.ecds044.Organisation2;
import org.ecds044.Organisation4;
import org.ecds044.RequestInformation1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034.HistoryInfoProcessor;


/**
 * 044票据查验
 *
 */
public class CommercialDraftDraftInformationNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        DraftInfoVo vo=new DraftInfoVo(); 
        RgctBillInfo bill=new RgctBillInfo();
        vo.setBill(bill);
        DocumentDocument doc=(DocumentDocument)xmlobject;
        CommercialDraftDraftInformationNotificationV01 root=doc.getDocument().getCommercialDraftDraftInformationNotification();
        MessageIdentification1  msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        
        /*票据信息*/
        CommercialDraft1 comDraft=root.getComrclDrft();
        bill.setBillType(comDraft.getTp().toString());
        bill.setBillNo(comDraft.getIdNb());
        bill.setBillMoney(NumberUtils.createDouble(comDraft.getIsseAmt().getStringValue()));
        bill.setIssueDt(MsgUtil.convertToString(comDraft.getIsseDt()));
        bill.setDueDt(MsgUtil.convertToString(comDraft.getDueDt()));
        bill.setInfoForbidFlag(comDraft.getBanEndrsmtMk().toString());
        bill.setRemark(comDraft.getRmrk());
        /*出票人信息*/
        Organisation4 remitter=comDraft.getDrwr();
        bill.setRemitter(remitter.getNm());
        bill.setRemitterRole(remitter.getRole().toString());
        bill.setRemitterOrgCode(remitter.getCmonId());
        bill.setRemitterAcct(remitter.getAcct().getId());
        bill.setRemitterBankNo(remitter.getAcct().getAcctSvcr());
        bill.setDrwrCreditrating(remitter.getCdtRatgs());
        bill.setDrwrCreditratingAgency(remitter.getCdtRatgAgcy());
        bill.setDrwrCreditratingDuedt(MsgUtil.convertToString(remitter.getCdtRatgDueDt()));
        /*承兑人信息*/
        Organisation2  acceptor=comDraft.getAccptr();
        bill.setAcceptor(acceptor.getNm());
        bill.setAcceptorAcct(acceptor.getAcct().getId());
        bill.setAcceptorBankNo(acceptor.getAcct().getAcctSvcr());
        /*收款人信息*/
        Organisation2 payee=comDraft.getPyee();
        bill.setPayeeName(payee.getNm());
        bill.setPayeeAcct(payee.getAcct().getId());
        bill.setPayeeBankNo(payee.getAcct().getAcctSvcr());
        /*请求信息*/
        RequestInformation1 req=root.getReqInf();
        CommercialDraftShowADraftRequest1 reqInfo=req.getCommercialDraftShowADraftRequest();
        Organisation1 from=reqInfo.getShowr();
        vo.setFromAcctNo(from.getAcct().getId());
        vo.setFromBankNo(from.getAcct().getAcctSvcr());
        vo.setFromRoleType(from.getRole().toString());
        vo.setFromName(from.getNm());
        vo.setFromOrgCode(from.getCmonId());
        vo.setFromElctrncSgntr(from.getElctrncSgntr());
        
        Organisation2 receive= reqInfo.getShowee();
        vo.setReceiveAcctNo(receive.getAcct().getId());
        vo.setReceiveBankNo(receive.getAcct().getAcctSvcr());
        vo.setReceiveName(receive.getNm());
        
        /*历史信息*/
        CommercialDraftBusiness1[] history=comDraft.getComrclDrftBizArray();
        List<DraftInfoVo> endorseHist=new ArrayList<DraftInfoVo>();
        try {
            
            for(int i=0; i<history.length; i++){
                CommercialDraftBusiness1 comrclDrftBiz = history[i];
                XmlCursor histCur = comrclDrftBiz.newCursor();
                histCur.toFirstChild();
                String hizName = histCur.getName().getLocalPart();
                                
                HistoryInfoProcessor hip = (HistoryInfoProcessor) Class.forName(
                        "com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034.HistoryInfoProcessor"
                        + hizName).newInstance();
                DraftInfoVo endorHist=hip.parseHistoryInfo((org.xmlbean.ecds034.CommercialDraftBusiness1)comrclDrftBiz, bill);
                endorseHist.add(endorHist);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        vo.setEndorseHist(endorseHist);
        
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc044";
    }

}
