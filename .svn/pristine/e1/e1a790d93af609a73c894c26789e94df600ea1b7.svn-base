package com.herongtech.rc.draft.msgBuilder.replyBuilder.commonSignUp;

import org.ecds031.RepurchasedRediscountWithCentralBank2;
import org.ecds031.SignUpInformation1;
import org.ecds031.SignUpMarkCode;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;


/**
 * 回购式再贴赎回现回复
 *
 */
public class RpdRdscntWthCntrlBkSignUpBuilder extends SignUpBuilder {

    @Override
    public void generateSignUpInfo(SignUpInformation1 sgnInfo, DraftInfoVo vo) {
        RepurchasedRediscountWithCentralBank2 sgn=sgnInfo.addNewRpdRdscntWthCntrlBk();
        sgn.setDt(MsgUtil.getConverTime(vo.getSignDt()));
        sgn.setSgnUpMk(SignUpMarkCode.Enum.forString(vo.getSgnUpMk()));
    }

}
