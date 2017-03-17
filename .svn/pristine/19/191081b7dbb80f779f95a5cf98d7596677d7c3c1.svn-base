package com.herongtech.rc.draft.msgProcessor;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.ecds031.CommercialDraft1;
import org.ecds031.CommercialDraftCommonSignUpV01;
import org.ecds031.Commonformation1;
import org.ecds031.DocumentDocument;
import org.ecds031.MessageIdentification1;
import org.ecds031.Organisation1;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;


public class CommercialDraftCommonSignUpProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        DraftInfoVo vo=new DraftInfoVo(); 
        RgctBillInfo bill=new RgctBillInfo();
        vo.setBill(bill);
        DocumentDocument doc=(DocumentDocument)xmlobject;
        CommercialDraftCommonSignUpV01 root=doc.getDocument().getCommercialDraftCommonSignUp();
        MessageIdentification1  msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        MessageIdentification1 orgMsg=root.getOrgnlMsgId();
        vo.setOrgnlMsgId(orgMsg.getId());
        vo.setOrgnlDraftCreDtTM(orgMsg.getCreDtTm().getTime());
        
        CommercialDraft1 info=root.getComrclDrft();
        bill.setBillNo(info.getIdNb());
        
        Commonformation1 cmonInf=root.getCmonInf();
        vo.setPrxyPropstn(cmonInf.getPrxySgntr().toString());
        vo.setFromRemark(cmonInf.getRmrkBySgnr());
        
        Organisation1 from=root.getSgnr();
        vo.setFromAcctNo(from.getAcct().getId());
        vo.setFromBankNo(from.getAcct().getAcctSvcr());
        vo.setFromRoleType(from.getRole().toString());
        vo.setFromOrgCode(from.getCmonId());
        vo.setFromElctrncSgntr(from.getElctrncSgntr());
        
//        SignUpInformation1 sgnInfo=root.getSgnUpInf();
        XmlCursor cursor = root.newCursor();
        cursor.toLastChild();//回复信息
        cursor.toFirstChild();//签收类型
        String signUpType = cursor.getName().getLocalPart();
        cursor.toFirstChild();//回复日期
        String signDt = cursor.getTextValue();
        cursor.toNextSibling();//回复标识
        String signMark= cursor.getTextValue();
        vo.setSgnUpMk(signMark);
        vo.setSignDt(MsgUtil.converISODateTime(signDt));
        vo.setBusiType(DraftConstants.SIGNUPTYPE_TO_BUSITYPE.get(signUpType));
        if(DraftConstants.SIGNUP_MARK_CODE2.equalsIgnoreCase(signMark)){
            //拒绝签收时回复类型为提示付款/逾期提示付款 拒付理由和拒付代码为必输
            if(signUpType.equalsIgnoreCase("Prsnttn") || signUpType.equalsIgnoreCase("OvrduePrsnttn")){
                cursor.toNextSibling();
                String rejectCode=cursor.getTextValue();
                vo.setRejectCode(rejectCode);
                cursor.toNextSibling();
                String rejectReason=cursor.getTextValue();
                vo.setRejectReason(rejectReason);
                
            }
        }else{//同意签收
            if(signUpType.equalsIgnoreCase("Guarntee")){//保证类型时 保证人地址
                cursor.toNextSibling();
                String guarnteeAddr=cursor.getTextValue();
                vo.setGuarnteeAddr(guarnteeAddr);
            }
        }
        
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc031";
    }

}
