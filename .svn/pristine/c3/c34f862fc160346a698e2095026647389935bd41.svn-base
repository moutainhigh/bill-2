package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds032.CommercialDraft1;
import org.ecds032.CommercialDraftCancellationRequestV01;
import org.ecds032.DocumentDocument;
import org.ecds032.MessageIdentification1;
import org.ecds032.Organisation1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;


/**
 * 032通用撤销解析类
 *
 */
public class CommercialDraftCancellationRequestProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        DraftInfoVo vo=new DraftInfoVo(); 
        RgctBillInfo bill=new RgctBillInfo();
        vo.setBill(bill);
        DocumentDocument doc=(DocumentDocument)xmlobject;
        CommercialDraftCancellationRequestV01 root=doc.getDocument().getCommercialDraftCancellationRequest();
        MessageIdentification1  msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        MessageIdentification1 orgMsg=root.getOrgnlMsgId();
        vo.setOrgnlMsgId(orgMsg.getId());
        vo.setOrgnlDraftCreDtTM(orgMsg.getCreDtTm().getTime());
        CommercialDraft1 info=root.getComrclDrft();
        bill.setBillNo(info.getIdNb());
        
        Organisation1 from=root.getCxlPropsr();
        vo.setFromAcctNo(from.getAcct().getId());
        vo.setFromBankNo(from.getAcct().getAcctSvcr());
        vo.setFromRoleType(from.getRole().toString());
        vo.setFromOrgCode(from.getCmonId());
        vo.setFromElctrncSgntr(from.getElctrncSgntr());
        
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc032";
    }

}
