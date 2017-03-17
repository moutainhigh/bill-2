package com.herongtech.rc.draft.msgBuilder.replyBuilder.commonSignUp;

import org.ecds031.CommercialDraft3;
import org.ecds031.SignUpInformation1;
import org.ecds031.SignUpMarkCode;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;


public class IssncSignUpBuilder extends SignUpBuilder {

    @Override
    public void generateSignUpInfo(SignUpInformation1 sgnInfo, DraftInfoVo vo) {
        CommercialDraft3 sgn=sgnInfo.addNewIssnc();
        sgn.setPyeeSgnUpDt(MsgUtil.getConverTime(vo.getSignDt()));
        sgn.setPyeeSgnUpMk(SignUpMarkCode.Enum.forString(vo.getSgnUpMk()));
    }

}
