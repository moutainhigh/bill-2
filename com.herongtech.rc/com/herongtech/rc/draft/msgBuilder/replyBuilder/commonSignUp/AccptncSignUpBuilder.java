package com.herongtech.rc.draft.msgBuilder.replyBuilder.commonSignUp;

import org.apache.commons.lang.StringUtils;
import org.ecds031.CommercialDraft2;
import org.ecds031.ConsignmentCode;
import org.ecds031.SignUpInformation1;
import org.ecds031.SignUpMarkCode;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;


public class AccptncSignUpBuilder extends SignUpBuilder{

    @Override
    public void generateSignUpInfo(SignUpInformation1 sgnInfo, DraftInfoVo vo) {
        CommercialDraft2 accpt=sgnInfo.addNewAccptnc();
        accpt.setAccptrSgnUpDt(MsgUtil.getConverTime(vo.getSignDt()));
        accpt.setAccptrSgnUpMk(SignUpMarkCode.Enum.forString(vo.getSgnUpMk()));
        accpt.setUcondlPrmsMrk(ConsignmentCode.Enum.forString(DraftConstants.UCONDL_CONSGNMT_MRK));
        if(StringUtils.isNotBlank(vo.getAccptncAgrmtNb())){

            accpt.setAccptncAgrmtNb(vo.getAccptncAgrmtNb());
         
            }
        
    }

}
