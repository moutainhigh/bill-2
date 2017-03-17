package com.herongtech.rc.draft.msgBuilder.sysmgr;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds065.AlterationBindingCode;
import org.ecds065.BindingInformation1;
import org.ecds065.CommercialDraftCertificateBindingRequestV01;
import org.ecds065.Document;
import org.ecds065.DocumentDocument;
import org.ecds065.MessageIdentification1;
import org.ecds065.Organisation1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;


/**
 * 065 数字证书行号绑定关系变更
 *
 */
public class CommercialDraftCertificateBindingRequestDraftBuilder extends
AbstractDraftBuilder{

    @Override
    public String buildBody(DraftSender draftSender) throws BizAppException {
        XmlOptions options= createXmlOptions(draftSender.getMesgType());
        SysMgrInfoVo vo=(SysMgrInfoVo)draftSender.getReqInfo();
        DocumentDocument document = DocumentDocument.Factory.newInstance(options);
        Document doc=document.addNewDocument();
        CommercialDraftCertificateBindingRequestV01 commerDraft=doc.addNewCommercialDraftCertificateBindingRequest();
        MessageIdentification1 msgid=commerDraft.addNewMsgId();
        msgid.setId(vo.getReqMsgId());                                         //报文标识
        msgid.setCreDtTm(MsgUtil.getCurrentTime());
       
       
        BindingInformation1 bind= commerDraft.addNewBindgInf();
        bind.setAltrnTp(AlterationBindingCode.Enum.forString(vo.getAltrnTp()));
        if (StringUtils.isNotBlank(vo.getRmrk())) {
        	
        	bind.setRmrk(vo.getRmrk());
		}
//        if(vo.getPtcpt()==null || vo.getPtcpt().size() == 0)
//            throw new BizAppException("");
        for(int i=0;i<vo.getPtcpt().size();i++){
            Organisation1 info= commerDraft.addNewPtcpt();
            info.addNewAcct().setAcctSvcr(vo.getPtcpt().get(i));
        }
        String draft=document.xmlText(options);
        String signPart=commerDraft.xmlText();
        return addSign(draft, signPart,draftSender.getIsAddSign());
    }

}
