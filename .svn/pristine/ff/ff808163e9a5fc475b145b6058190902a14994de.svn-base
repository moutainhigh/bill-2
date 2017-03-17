package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftEndorsementRequest1;
import org.xmlbean.ecds034.Endorsement1;
import org.xmlbean.ecds034.Organisation1;
import org.xmlbean.ecds034.Organisation2;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 背书转让申请
 *
 */
public class ReqInfoProcessorCommercialDraftEndorsementRequest extends ReqInfoProcessor{


	@Override
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo,DraftInfoVo vo) {		
		CommercialDraftEndorsementRequest1 reqEle=reqInfo.getCommercialDraftEndorsementRequest();
		String msgId=reqEle.getMsgId().getId();
		vo.setReqMsgId(msgId);
		
		{//背书信息
			Endorsement1 endrsmt=reqEle.getEndrsmt();
			String Dt = MsgUtil.convertToString(endrsmt.getDt());;//背书申请日期
			String BanEndrsmtMk = endrsmt.getBanEndrsmtMk().toString();;//不得转让标记			
			String rmrk=endrsmt.getRmrkByPropsr();
			vo.setApplyDt(MsgUtil.converISODateTime(Dt));
            vo.setBanEndrsmtMk(BanEndrsmtMk);	
			vo.setFromRemark(rmrk);	
			
		}				
		{//背书人信息
			Organisation1 endrsr=reqEle.getEndrsr();
			String Role =endrsr.getRole().toString();//类别
			String Nm = endrsr.getNm();//名称
			String CmonId = endrsr.getCmonId();//组织机构代码
			String ElctrncSgntr = endrsr.getElctrncSgntr();//电子签名
			Account1 acct=endrsr.getAcct();
			String acctId = acct.getId();//帐号
			String AcctSvcr = acct.getAcctSvcr();//开户行行号			
			//Element acctEle2 = Dom4jUtil.getFirstChild(Dom4jUtil.getFirstChild(EndrsrEle, "Agcy"),"Acct");
			Organisation8 agcyElement = endrsr.getAgcy();
			Account2 acctEle2 = null;
			if(agcyElement != null) {
				acctEle2 = agcyElement.getAcct();
			}
			String AcctSvcr2 = null;
			if (acctEle2!=null)
				AcctSvcr2 =acctEle2.getAcctSvcr();//承接行行号
			
			vo.setFromName(Nm);
            vo.setFromElctrncSgntr(ElctrncSgntr);
            vo.setFromAcctNo(acctId);
            vo.setFromBankNo(AcctSvcr);
            vo.setFromRoleType(Role);
            vo.setFromBankName(Nm);
            vo.setFromOrgCode(CmonId);
            vo.setFromAgcyBankNo(AcctSvcr2);
			
		}		
		{//被背书人信息
			Organisation2 endrseeEle =reqEle.getEndrsee();
			String Nm = endrseeEle.getNm();//名称
			Account1 acct=endrseeEle.getAcct();
			String acctId =acct.getId();;//帐号
			String AcctSvcr = acct.getAcctSvcr();//开户行行号	
			
			vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveBankName(Nm);	
            vo.setReceiveName(Nm);
			
		}
		vo.setBusiType("010");
		return vo;
	}
   
	
}
