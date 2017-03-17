package com.herongtech.rc.draft.msgBuilder.replyBuilder.commonSignUp;

import org.ecds031.RepurchasedRediscountWithCommercialBank2;
import org.ecds031.SignUpInformation1;
import org.ecds031.SignUpMarkCode;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;


/**
 * 
 *回购式转贴现赎回
 */
public class RpdRdscntWthComrclBkSignUpBuilder extends SignUpBuilder {

    @Override
    public void generateSignUpInfo(SignUpInformation1 sgnInfo, DraftInfoVo vo) {
        RepurchasedRediscountWithCommercialBank2 sgn=sgnInfo.addNewRpdRdscntWthComrclBk();
        sgn.setDt(MsgUtil.getConverTime(vo.getSignDt()));
        sgn.setSgnUpMk(SignUpMarkCode.Enum.forString(vo.getSgnUpMk()));
    }

}
