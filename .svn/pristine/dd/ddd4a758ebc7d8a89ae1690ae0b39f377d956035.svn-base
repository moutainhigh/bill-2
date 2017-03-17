package com.herongtech.rc.draft.msgProcessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.ecds055.CommercialDraftCommonDataNotificationV01;
import org.ecds055.CommonData1;
import org.ecds055.DocumentDocument;
import org.ecds055.MessageIdentification1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftCommonData;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;


/**
 * 055公共数据变更通知报文解析类
 *
 */
public class CommercialDraftCommonDataNotificationProcessor extends AbstractDraftMsgProcessor{

    @Override
    protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)
            throws BizAppException {
        SysMgrInfoVo vo=new SysMgrInfoVo();
        DocumentDocument document = (DocumentDocument)xmlobject;
        CommercialDraftCommonDataNotificationV01 root=document.getDocument().getCommercialDraftCommonDataNotification();
        MessageIdentification1 msg=root.getMsgId();
        vo.setReqMsgId(msg.getId());
        vo.setDraftCreDtTm(msg.getCreDtTm().getTime());
        CommonData1[] dataArr=root.getCmonDtArray();
        List<DraftCommonData> cmonDtList=new ArrayList<DraftCommonData>(dataArr.length);
        for(CommonData1 data:dataArr){
            DraftCommonData dcd=new DraftCommonData();
            dcd.setAltrnTp(data.getAltrnTp());
            dcd.setCmonDtCd(data.getCmonDtCd());
            dcd.setCmonDtNm(data.getCmonDtNm());
            dcd.setCmonDtTp(data.getCmonDtTp());
            dcd.setCmonDtVal(data.getCmonDtVal());
            dcd.setFctvDt(MsgUtil.convertToString(data.getFctvDt()));
            dcd.setFctvTp(data.getFctvTp());
            cmonDtList.add(dcd);
        }
        
        vo.setCmonDt(cmonDtList);
        return vo;
    }

    @Override
    protected String getServiceId() {
        return "Proc055";
    }

}
