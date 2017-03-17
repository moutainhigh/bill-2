package com.herongtech.rc.draft.msgProcessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.ecds062.AccessPoint1;
import org.ecds062.AccessPointsInformation1;
import org.ecds062.CommercialDraftAccessPointsInformationV01;
import org.ecds062.DocumentDocument;
import org.ecds062.MessageIdentification1;




import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.vo.DraftAccessPoints;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;

/**
 * 062接入点信息变更通知报文解析类
 * */
public class CommercialDraftAccessPointsInformationProcessor  extends AbstractDraftMsgProcessor{
    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
       
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftAccessPointsInformationV01 root=document.getDocument().getCommercialDraftAccessPointsInformation();
        MessageIdentification1 msg=root.getMsgId();
        AccessPointsInformation1 apdata=root.getAccssPtsInf();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        AccessPoint1[] arr=apdata.getAccssPtArray();
        List<DraftAccessPoints> list=new ArrayList<DraftAccessPoints>(arr.length);
        for(AccessPoint1 ap:arr){
            DraftAccessPoints apInfo=new DraftAccessPoints();
            apInfo.setAccssPtCd(ap.getAccssPtCd());
            apInfo.setAccssPtNm(ap.getAccssPtNm());
            apInfo.setAltrnTp(ap.getAltrnTp());
            apInfo.setCcpcCd(ap.getCCPCCd());
            apInfo.setCcpcNm(ap.getCCPCNm());
            list.add(apInfo);
        }
        vo.setAccssPtsInf(list);
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc062";
    }
}
