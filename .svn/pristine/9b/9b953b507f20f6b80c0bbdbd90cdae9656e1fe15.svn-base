package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraft1;
import org.xmlbean.ecds034.CommercialDraftIssuanceRequest1;
import org.xmlbean.ecds034.Organisation11;
import org.xmlbean.ecds034.Organisation2;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.ibm.disthub2.impl.security.MsgUtil;
/**
 * 提示收票
 *
 */
public class ReqInfoProcessorCommercialDraftIssuanceRequest extends ReqInfoProcessor{


	@Override
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo,DraftInfoVo vo) {		
		RgctBillInfo info = vo.getBill();
		CommercialDraftIssuanceRequest1 reqEle=reqInfo.getCommercialDraftIssuanceRequest();
		String msgId=reqEle.getMsgId().getId();
        vo.setReqMsgId(msgId);
				
		{//出票人信息
			Organisation11 drwr=reqEle.getDrwr();
			String Role = drwr.getRole().toString();;//类别
			String CmonId = drwr.getCmonId();;//组织机构代码
			String ElctrncSgntr = drwr.getElctrncSgntr();//电子签名
			Account1 acct=drwr.getAcct();
			String acctId = acct.getId();//帐号
			String AcctSvcr = acct.getAcctSvcr();//开户行行号			
			Organisation8 agcyElement = drwr.getAgcy();
			Account2 acctEle2 = null;
			if(agcyElement != null) {
				acctEle2 = agcyElement.getAcct();
			}
			String acctSvcr2 = null;
			if (acctEle2!=null)
				acctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
			
			vo.setFromName(info.getRemitter());
            vo.setFromElctrncSgntr(ElctrncSgntr);
            vo.setFromAcctNo(acctId);
            vo.setFromBankNo(AcctSvcr);
            vo.setFromRoleType(Role);
            vo.setFromOrgCode(CmonId);
            vo.setFromAgcyBankNo(acctSvcr2);
		}
		
		{//收款人
		    vo.setReceiveAcctNo(info.getPayeeAcct());
            vo.setReceiveBankNo(info.getPayeeBankNo());
            vo.setReceiveName(info.getPayeeName());
			
		}
		vo.setBusiType("003");
		return vo;
	}
	
}
