package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds041.BusinessInformation2;
import org.ecds041.CommercialDraft1;
import org.ecds041.CommercialDraftBusinessNotificationV01;
import org.ecds041.Destruction1;
import org.ecds041.Document;
import org.ecds041.DocumentDocument;
import org.ecds041.Guarantee5;
import org.ecds041.MessageIdentification1;
import org.ecds041.Organisation1;
import org.ecds041.Organisation2;
import org.ecds041.Organisation3;
import org.ecds041.Recourse5;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;


/**
 * 041业务通知报文
 *通知规则如下：
 *   1.撤票后，会用此报文通知出票保证人，承兑人，承兑保证人，此时票据状态为“票据已作废”。
 *   2. 追索同意清偿签收后，会用此报文通知其他接到追索通知报文的被追索人，此时票据状态为“拒付追索同意清偿已签收”或“非拒付追索同意清偿已签收”
 *   3. 承兑保证签收后，会用此报文通知出票人，此时票据状态为“提示承兑已签收”
 */
public class CommercialDraftBusinessNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        DraftInfoVo vo=new DraftInfoVo();
        RgctBillInfo rgctbill=new RgctBillInfo();
        vo.setBill(rgctbill);
        DocumentDocument document=(DocumentDocument)xmlobject;
        Document doc=document.getDocument();
        CommercialDraftBusinessNotificationV01 root=doc.getCommercialDraftBusinessNotification();
        MessageIdentification1 msgId=root.getMsgId();
        vo.setReqMsgId(msgId.getId());
        vo.setDraftCreDtTm(msgId.getCreDtTm().getTime());
        
        CommercialDraft1 bill=root.getComrclDrft();
        rgctbill.setBillNo(bill.getIdNb());
        
        Organisation1 from=root.getRqstr();
        vo.setFromName(from.getNm());
        vo.setFromAcctNo(from.getAcct().getId());
        vo.setFromBankNo(from.getAcct().getAcctSvcr());
        vo.setFromRoleType(from.getRole().toString());
        vo.setFromOrgCode(from.getCmonId());
        
        Organisation2 receive=root.getSgnr();
        if(receive != null){
            vo.setReceiveAcctNo(receive.getAcct().getId());
            vo.setReceiveName(receive.getNm());
            vo.setReceiveRoleType(receive.getRole().toString());
            vo.setReceiveOrgCode(receive.getCmonId());
            vo.setReceiveBankNo(receive.getAcct().getAcctSvcr());
        }
        Organisation3 rcvr=root.getRcvr();
        vo.setReceiveBankNo(rcvr.getAcct().getAcctSvcr());
        
        BusinessInformation2 infomation=root.getBizInf();
        Destruction1 dest=infomation.getDstrctn();
        Guarantee5 guar=infomation.getGuarntee();
        Recourse5 rcrs=infomation.getRcrs();
        if(dest != null){
            vo.setSignDt(MsgUtil.convertToDate(dest.getDt()));
        }
        //TODO:追索，保证暂不考虑
        return vo;
    }

    @Override
    protected String getServiceId() {
        
        return "Proc041";
    }

}
