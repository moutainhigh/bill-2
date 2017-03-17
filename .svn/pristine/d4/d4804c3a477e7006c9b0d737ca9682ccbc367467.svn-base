package com.herongtech.rc.draft.msgBuilder.replyBuilder.commonSignUp;

import org.apache.commons.lang.StringUtils;
import org.ecds031.DishonorCode;
import org.ecds031.OverduePresentation2;
import org.ecds031.SignUpInformation1;
import org.ecds031.SignUpMarkCode;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;


public class OvrduePrsnttnSignUpBuilder extends SignUpBuilder {

    @Override
    public void generateSignUpInfo(SignUpInformation1 sgnInfo, DraftInfoVo vo) {
        OverduePresentation2 sgn=sgnInfo.addNewOvrduePrsnttn();
        sgn.setDt(MsgUtil.getConverTime(vo.getSignDt()));
        sgn.setSgnUpMk(SignUpMarkCode.Enum.forString(vo.getSgnUpMk()));
        if(vo.getSgnUpMk().equalsIgnoreCase(DraftConstants.SIGNUP_MARK_CODE2)){
        	 if(StringUtils.isNotBlank(vo.getRejectCode())){
        		 sgn.setDshnrCd(DishonorCode.Enum.forString(vo.getRejectCode()));

        	 }
        	 if(StringUtils.isNotBlank(vo.getRejectReason())){
                 sgn.setDshnrRsn(vo.getRejectReason());

        	 }
        	 
        }
    }

}
