package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlObject;
import org.ecds005.BusinessControlInformation1;
import org.ecds005.CommercialDraft1;
import org.ecds005.CommercialDraftRegisterConfirmationV01;
import org.ecds005.DocumentDocument;
import org.ecds005.MessageIdentification1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;


/**
 * 005报文解析类
 *
 */
public class CommercialDraftRegisterConfirmationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        DraftInfoVo vo=new DraftInfoVo(); 
        RgctBillInfo bill=new RgctBillInfo();
        vo.setBill(bill);
        DocumentDocument doc=(DocumentDocument)xmlobject;
        CommercialDraftRegisterConfirmationV01 root= doc.getDocument().getCommercialDraftRegisterConfirmation();
        MessageIdentification1 msg= root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        MessageIdentification1 orgMsg= root.getOrgnlMsgId();
        vo.setOrgnlMsgId(orgMsg.getId());
        vo.setOrgnlDraftCreDtTM(orgMsg.getCreDtTm().getTime());
        
        CommercialDraft1 cd=root.getComrclDrft();
        bill.setBillNo(cd.getIdNb());
//        cd.getIsseAmt()
        
        BusinessControlInformation1 bc=root.getBizCtrlInf();
        vo.setPrcCd(bc.getPrcCd());
        vo.setPrcMsg(bc.getPrcMsg());
        
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc005";
    }

}
