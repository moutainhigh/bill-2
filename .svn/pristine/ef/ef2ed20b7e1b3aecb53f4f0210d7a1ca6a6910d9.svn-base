package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds036.BusinessControlInformation1;
import org.ecds036.CommercialDraft1;
import org.ecds036.CommercialDraftSettlementResultNotificationV01;
import org.ecds036.DocumentDocument;
import org.ecds036.MessageIdentification1;
import org.ecds036.Organisation1;
import org.ecds036.Organisation2;
import org.ecds036.Organisation3;
import org.ecds036.Transfer1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;

/**
 * 036报文解析
 * @author fqz
 *
 */
public class CommercialDraftSettlementResultNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        DraftInfoVo vo=new DraftInfoVo(); 
        RgctBillInfo bill=new RgctBillInfo();
        vo.setBill(bill);
        DocumentDocument doc=(DocumentDocument)xmlobject;
        CommercialDraftSettlementResultNotificationV01 root=doc.getDocument().getCommercialDraftSettlementResultNotification();
       
        MessageIdentification1  msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        
        MessageIdentification1 orgMsg=root.getOrgnlMsgId();
        vo.setOrgnlMsgId(orgMsg.getId());
        vo.setOrgnlDraftCreDtTM(orgMsg.getCreDtTm().getTime());
        
        CommercialDraft1 info=root.getComrclDrft();
        bill.setBillNo(info.getIdNb());
        
        BusinessControlInformation1 infomation=root.getBizCtrlInf();
        vo.setPrcCd(infomation.getPrcCd());
        vo.setPrcMsg(infomation.getPrcMsg());
        
        
        Organisation2 from=root.getOrgnlPropsr();
        vo.setFromName(from.getNm());
        vo.setFromAcctNo(from.getAcct().getId());
        vo.setFromBankNo(from.getAcct().getAcctSvcr());
        vo.setFromRoleType(from.getRole().toString());
        vo.setFromOrgCode(from.getCmonId());
        
        Organisation1 receive=root.getSgnr();
        vo.setReceiveAcctNo(receive.getAcct().getId());
        vo.setReceiveName(receive.getNm());
        vo.setReceiveRoleType(receive.getRole().toString());
        vo.setReceiveOrgCode(receive.getCmonId());
        vo.setReceiveBankNo(receive.getAcct().getAcctSvcr());
        
        Organisation3 rcvr=root.getRcvr();
        vo.setToBankNo(rcvr.getAcct().getAcctSvcr());
        
        Transfer1 trans=root.getTrf();
        vo.setTrfRef(trans.getTrfRef());
        vo.setTrfId(trans.getId());
        vo.setSignDt(trans.getTradDt().getTime());
        
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc036";
    }

}
