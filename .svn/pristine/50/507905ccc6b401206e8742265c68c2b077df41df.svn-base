package com.herongtech.rc.draft.msgProcessor;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;

public abstract class AbstractDraftMsgProcessor {

    public RequestInfo processMsg(DraftMessage message) throws BizAppException {
        XmlObject xmlObj = treatCommonData(message);
        RequestInfo reqInfo= doProcess(message, xmlObj);
        reqInfo.setServiceId(getServiceId());
        return reqInfo;
    }

    /**
     * 初始化通用数据,将XML转化成XMLBean对象
     * 
     * @return
     */
    private XmlObject treatCommonData(DraftMessage message)
            throws BizAppException {
        try {
            XmlOptions options = new XmlOptions();
            if (!"034".equals(message.getMsgNo())) {
                String namespace = "http://www.ecds" + message.getMsgNo()
                        + ".org";
                Map<String, String> map = new HashMap<String, String>();
                map.put("", namespace);
                options.setLoadSubstituteNamespaces(map);
            }
            XmlObject xml = XmlObject.Factory.parse(message.getMsgBody(),
                    options);

            /*XmlCursor cursor = xml.newCursor();
            cursor.toFirstChild();
            cursor.toFirstChild();
            cursor.toNextToken();
            cursor.toChild("MsgId");
            cursor.toFirstChild();
            String orginMsgId = cursor.getTextValue();
            cursor.toNextSibling();
            String orginCreDtTm = cursor.getTextValue();
            message.setOrginMsgId(orginMsgId);
            message.setOrginCreDtTm(orginCreDtTm);*/
            return xml;
        } catch (XmlException e) {
            throw new BizAppException(e.getMessage(),e);
        }
    }

    abstract protected RequestInfo doProcess(DraftMessage message, XmlObject xmlobject)throws BizAppException;

    
    abstract protected String getServiceId();
    
    
    
}
