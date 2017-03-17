package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.draft.common.vo.DraftInfoVo;

/**
 * 回购式贴现赎回申请
 *
 */
public class ReqInfoProcessorCommercialDraftRepurchasedDiscountRequest extends ReqInfoProcessor{

    @Override
    public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo, DraftInfoVo vo) {
        //TODO:赎回式贴现先不做，一般行没有此业务
        return null;
    }

	

}
