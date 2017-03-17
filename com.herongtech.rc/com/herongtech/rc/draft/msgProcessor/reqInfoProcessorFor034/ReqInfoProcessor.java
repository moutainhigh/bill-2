package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.draft.common.vo.DraftInfoVo;


/**
 *请求信息处理器
 */
public abstract class ReqInfoProcessor {
    

    public abstract DraftInfoVo parseReqInfo(RequestInformation1 reqInfo,DraftInfoVo vo);
}
