package com.herongtech.rc.draft.msgBuilder.replyBuilder.commonSignUp;

import org.ecds031.CentralBankSellingDrafts2;
import org.ecds031.SignUpInformation1;
import org.ecds031.SignUpMarkCode;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;


/**
 * 央行卖票回复
 *
 */
public class CntrlBkSellgDrftsSignUpBuilder extends SignUpBuilder{

    @Override
    public void generateSignUpInfo(SignUpInformation1 sgnInfo, DraftInfoVo vo) {
        CentralBankSellingDrafts2 sgn=sgnInfo.addNewCntrlBkSellgDrfts();
        sgn.setDt(MsgUtil.getConverTime(vo.getSignDt()));
        sgn.setSgnUpMk(SignUpMarkCode.Enum.forString(vo.getSgnUpMk()));
    }

}
