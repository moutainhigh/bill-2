package com.herongtech.rc.draft.msgProcessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.ecds063.AccessPointAndParticipant1;
import org.ecds063.AccessPointAndParticipantInformation1;
import org.ecds063.CommercialDraftAccessPointAndParticipantInformationV01;
import org.ecds063.DocumentDocument;
import org.ecds063.MessageIdentification1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftAccessPointAndParticipants;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;


/**
 * 063接入点与辖内参与者对应关系报文
 * */
public class CommercialDraftAccessPointAndParticipantInformationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
    	 SysMgrInfoVo vo=new SysMgrInfoVo();
         DocumentDocument document = (DocumentDocument)xmlobject;
         CommercialDraftAccessPointAndParticipantInformationV01 root=document.getDocument().getCommercialDraftAccessPointAndParticipantInformation();
         MessageIdentification1 msg=root.getMsgId();
         AccessPointAndParticipantInformation1 accseepap = root.getAccssPtAndPtcptInf();
         vo.setReqMsgId(msg.getId());
         vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
         AccessPointAndParticipant1[] accseepaps = accseepap.getAccssPtAndPtcptArray();
         
         List<DraftAccessPointAndParticipants> list=new ArrayList<DraftAccessPointAndParticipants>(accseepaps.length);
         for(AccessPointAndParticipant1 ap:accseepaps){
        	 DraftAccessPointAndParticipants apapInfo=new DraftAccessPointAndParticipants();
        	 apapInfo.setAccssPtCd(ap.getAccssPtCd());
        	 apapInfo.setAccssPtNm(ap.getAccssPtNm());
        	 apapInfo.setFctvDt(MsgUtil.convertToString(ap.getFctvDt()));
        	 apapInfo.setPtcptCd(ap.getPtcptCd());
        	 apapInfo.setPtcptNm(ap.getPtcptNm());
             
             list.add(apapInfo);
         }
         vo.setAccessPointAndParticipants(list);

         return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc063";
    }

}
