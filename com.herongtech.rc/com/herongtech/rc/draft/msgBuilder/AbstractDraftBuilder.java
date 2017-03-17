package com.herongtech.rc.draft.msgBuilder;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.MsgHeadUtil;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.sign.SignManager;


public abstract class AbstractDraftBuilder {

    public String assembleDraft(DraftSender draftSender)throws Exception{
        String msgBody=buildBody(draftSender);
        String msgHead=buildHead(draftSender,msgBody);
        XmlOptions options = new XmlOptions();
        if (!"034".equals(draftSender.getMesgType())) {
            String namespace = "http://www.ecds" + draftSender.getMesgType()
                    + ".org";
            Map<String, String> map = new HashMap<String, String>();
            map.put("", namespace);
            options.setLoadSubstituteNamespaces(map);
        }
        XmlObject xml = XmlObject.Factory.parse(msgBody,
                options);
        MsgUtil.validator(xml,options);
        return msgHead+msgBody;
    }
    
    
    public String buildHead(DraftSender draftSender,String msgBody){
        String senderId=draftSender.getSenderId();
        String receiverId=draftSender.getReceiverId();
        String origSender=draftSender.getOrigSender();
        String origReceiver=draftSender.getOrigReceiver();
        String mesgType=draftSender.getMesgType();
        String mesgId=draftSender.getMesgId();
        String mesgRefId=draftSender.getMesgRefId();
        
        return MsgHeadUtil.getMsgHead(senderId,receiverId, origSender, origReceiver, mesgType, mesgId, mesgRefId, msgBody);
       
    }
    
    public abstract String buildBody(DraftSender draftSender)throws BizAppException ;
    
    
    
    protected XmlOptions createXmlOptions(String draftNo) {
        XmlOptions options = new XmlOptions();
        Map<String,String> map = new HashMap<String,String>();
        map.put("", "http://www.ecds"+draftNo+".org");
        options.setUseDefaultNamespace();
        options.setSaveImplicitNamespaces(map);
        options.setSavePrettyPrint();
        return options;
    }
    
    protected String addSign(String doc, String signPart, String isAddSign) throws BizAppException{
        if (IConstants.Zero.equals(isAddSign)){
            return doc;
        }else{
            String ptcptSgntr=SignManager.signData(signPart);
            return doc.replace("</Document>", "<PtcptSgntr>"+ptcptSgntr+"</PtcptSgntr></Document>");
        }
        
    }
}
