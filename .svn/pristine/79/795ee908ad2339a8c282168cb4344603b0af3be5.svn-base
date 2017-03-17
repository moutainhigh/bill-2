

package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.apache.commons.lang.math.NumberUtils;
import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftRepurchasedRediscountWithCommercialBankRequest1;
import org.xmlbean.ecds034.Organisation11;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RepurchasedRediscountWithCommercialBank1;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;

/**
 * 回购式转贴现赎回申请
 * 
 */
public class ReqInfoProcessorCommercialDraftRepurchasedRediscountWithCommercialBankRequest extends ReqInfoProcessor{

    @Override
    public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo, DraftInfoVo vo) {
        CommercialDraftRepurchasedRediscountWithCommercialBankRequest1 reqEle=reqInfo.getCommercialDraftRepurchasedRediscountWithCommercialBankRequest();
        String msgId=reqEle.getMsgId().getId();
        vo.setReqMsgId(msgId);
        
        RepurchasedRediscountWithCommercialBank1 rpdInfo=reqEle.getRpdRdscntWthComrclBk();
        vo.setBanEndrsmtMk(rpdInfo.getBanEndrsmtMk().toString());
        vo.setBtchNb(rpdInfo.getBtchNb());
        vo.setApplyDt(MsgUtil.convertToDate(rpdInfo.getDt()));
        vo.setRpdIntrstRate(NumberUtils.createDouble(rpdInfo.getIntrstRate().toString()));
        vo.setFromRemark(rpdInfo.getRmrkByPropsr());
        vo.setSettleMk(rpdInfo.getSttlmMk().toString());
        vo.setRpdAmt(NumberUtils.createDouble(rpdInfo.getAmt().getStringValue())) ;
        
        
        Organisation11 from=reqEle.getOrgnlRcvgBkOfRdscntWthComrclBk();
        String Role = from.getRole().toString();//类型
        String ElctrncSgntr = from.getElctrncSgntr();//电子签名
        String CmonId = from.getCmonId();//组织机构代码
        Account1  acctEle =  from.getAcct();
        String acctId = acctEle.getId();//帐号
        String AcctSvcr = acctEle.getAcctSvcr();//开户行行号         
        Organisation8 agcyElement = from.getAgcy();
        Account2  acctEle2 = null;
        String acctSvcr2 = null;
        if(agcyElement != null) {
            acctEle2 = agcyElement.getAcct();
            if (acctEle2!=null){
                acctSvcr2 =acctEle2.getAcctSvcr();//承接行行号
                
            }
        }
        vo.setFromElctrncSgntr(ElctrncSgntr);
        vo.setFromAcctNo(acctId);
        vo.setFromBankNo(AcctSvcr);
        vo.setFromRoleType(Role);
        vo.setFromOrgCode(CmonId);
        vo.setFromAgcyBankNo(acctSvcr2);
        
        vo.setBusiType("014");
        return vo;
    }

	
}
