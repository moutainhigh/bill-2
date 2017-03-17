package com.herongtech.rc.draft.msgBuilder.replyBuilder.commonSignUp;

import org.ecds031.Guarantee2;
import org.ecds031.SignUpInformation1;
import org.ecds031.SignUpMarkCode;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;


public class GuarnteeSignUpBuilder extends SignUpBuilder {

    @Override
    public void generateSignUpInfo(SignUpInformation1 sgnInfo, DraftInfoVo vo) {
        Guarantee2 sgn=sgnInfo.addNewGuarntee();
        sgn.setDt(MsgUtil.getConverTime(vo.getSignDt()));
        sgn.setSgnUpMk(SignUpMarkCode.Enum.forString(vo.getSgnUpMk()));
    }

}
